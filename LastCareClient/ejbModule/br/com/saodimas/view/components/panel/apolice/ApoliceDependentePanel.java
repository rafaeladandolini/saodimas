package br.com.saodimas.view.components.panel.apolice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.table.DependenteTable;
import br.com.saodimas.view.components.table.model.DependenteTableModel;

@SuppressWarnings("serial")
public class ApoliceDependentePanel extends JPanel {
	public static final String FORM_NAME = "Dependentes";

	private ApoliceVO apolice;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JToolBar barControle;
	private DependenteTable tbDependentes;

	private BarraNotificacao barNotificacao;
	private ActionListener listenerControles;
	private JButton btVoltar;
	private JButton btAvancar;
	private JButton btSalvar;
	private JButton btCancelar;

	private int etapa;
	private int numEtapas;

	private static final Dimension DBUTTON = new Dimension(30,30);

	public ApoliceDependentePanel(ActionListener listenerControles, int etapa, int numEtapas, BarraNotificacao bar) {
		this.etapa = etapa;
		this.numEtapas = numEtapas;
		this.listenerControles = listenerControles;
		this.barNotificacao = bar;
		initialize();
		configure();
	}

	@Override
	public void setVisible(boolean flag) {
		if (flag) barNotificacao.mostrarMensagem(getDica(), BarraNotificacao.DEFAULT);
		else barNotificacao.escondeMensagem();
		
		super.setVisible(flag);
		if (flag) btNovo.requestFocus();
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	public void setApolice(ApoliceVO a) {
		apolice = a;
		if (apolice != null){
			List<AssociadoVO> dps = apolice.getDependentes();
			if (dps != null){
				DependenteTableModel model = (DependenteTableModel)tbDependentes.getModel();
				model.removeAll();
				for (AssociadoVO associado : dps) {
					model.add((AssociadoVO)associado);
				}
			}
			else {
				limparCampos();
				apolice.setDependentes(new ArrayList<AssociadoVO>());
			}
		}
		else limparCampos();
	}

	public void limparCampos() {
		DependenteTableModel model = (DependenteTableModel)tbDependentes.getModel();
		model.removeAll();
		model.fireTableDataChanged();
	}

	public String getDica(){
		if(apolice != null){
			if(apolice.getPlano() != null){
				int limiteAtual = apolice.getPlano().getLimiteAssociado() - getCountAssociadosVivos();
				if (limiteAtual > 0){
					btNovo.setEnabled(true);
					return "Você pode adicionar mais " + limiteAtual + " dependente" + (limiteAtual > 1 ? "s!" : "!");
				}
				else{
					btNovo.setEnabled(false);
					return "Todos dependentes cobertos pelo plano " + apolice.getPlano().getDescricao() + " foram cadastrados. Limite: " + apolice.getPlano().getLimiteAssociado() + ".";
				}
			}
		}
		return "";
	} 

	private int getCountAssociadosVivos()
	{
		int x = 0;
		DependenteTableModel model = (DependenteTableModel) tbDependentes.getModel();
		Vector<AssociadoVO> list = model.getDataVector();
		for (AssociadoVO associadoVO : list) {
			if(associadoVO.getObito() == null)
				x++;
		}
		return x;
	}
	
	@Override
	public void requestFocus() {
		btNovo.requestFocus();
	}

	private void initialize() {
		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addRelative.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastrar um novo dependente!");
		btNovo.setPreferredSize(DBUTTON);

		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Clique para editar um dependente!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);

		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Clique para remover um dependente");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);

		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);

		tbDependentes = new DependenteTable();
		tbDependentes.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbDependentes.getModel().addTableModelListener(new EventoModelo());
		tbDependentes.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbDependentes.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				super.keyPressed(e);
			}
		});

		btVoltar = new JButton("Voltar", new ImageIcon("imagens/back.gif"));
		btVoltar.addActionListener(listenerControles);
		btVoltar.setHorizontalAlignment(JButton.LEFT);
		btVoltar.setActionCommand(ApolicePanel.ACAO_VOLTAR);

		btAvancar = new JButton("Próximo", new ImageIcon("imagens/next.gif"));
		btAvancar.addActionListener(new EventoBotaoComando());
		btAvancar.setHorizontalAlignment(JButton.LEFT);
		btAvancar.setActionCommand(ApolicePanel.ACAO_AVANCAR);

		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(listenerControles);
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		btSalvar.setActionCommand(ApolicePanel.ACAO_SALVAR);		

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(listenerControles);
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		btCancelar.setActionCommand(ApolicePanel.ACAO_CANCELAR);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel depPanel = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; depPanel.add(barControle, c);

		JScrollPane tbDepPanel = new JScrollPane(tbDependentes);
		tbDepPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; depPanel.add(tbDepPanel, c);

		depPanel.setBorder(BorderFactory.createTitledBorder("Dependentes"));
		depPanel.setPreferredSize(new Dimension(350,350));
		setLayout(new BorderLayout());

		JLabel lbTitulo = new JLabel("Dependentes (" + etapa + "/" + numEtapas + ")", new ImageIcon("imagens/dependentes.gif"), JLabel.LEADING);
		lbTitulo.setLayout(null);
		lbTitulo.setBackground(new Color(255, 255, 255));
		lbTitulo.setFont(lbTitulo.getFont().deriveFont(Font.BOLD, 12));
		lbTitulo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				BorderFactory.createEmptyBorder(2, 0, 3, 1)
		)
		);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		if (etapa > 1) controle.add(btVoltar);
		if (etapa == numEtapas) controle.add(btSalvar);
		else controle.add(btAvancar);
		controle.add(btCancelar);

		add(lbTitulo, BorderLayout.NORTH);
		add(depPanel, BorderLayout.CENTER);
		add(controle, BorderLayout.SOUTH);
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	private void notificar(final String mensagem, final int tipoMensagem){
		new Thread() {
			@Override
			public synchronized void start() {
				try{
					barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
					System.out.println(mensagem);
				}
				catch(ClassCastException cex){cex.printStackTrace();}
				super.start();
			}

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				try{
					Thread.sleep(10000);
					barNotificacao.mostrarMensagem(getDica(), BarraNotificacao.DICA);
					stop();
				}
				catch(Exception ex){ex.printStackTrace();}
			}
		}.start();
	}
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}

	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbDependentes.getSelectedRow() > -1);
			btExcluir.setEnabled(tbDependentes.getSelectedRow() > -1);
		}
	}
	
	private class EventoModelo implements TableModelListener{
		public void tableChanged(TableModelEvent e) {
			barNotificacao.mostrarMensagem(getDica(), BarraNotificacao.DICA);
		}
	}

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ApoliceMainPanel c = (ApoliceMainPanel)getPainelPrincipal();
			if (e.getSource() == btNovo){
				c.getIframeDependente().setVisible(true);
				c.getIframeDependente().setApolice(apolice);
				c.getIframeDependente().setTableModel((DependenteTableModel)tbDependentes.getModel());
				c.getIframeDependente().setDependente(null);
			}
			else if (e.getSource() == btEditar){
				AssociadoVO d = (AssociadoVO) ((DependenteTableModel)tbDependentes.getModel()).getSelectedValue(tbDependentes.getSelectedRow());
				if(d.getObito() == null){
					c.getIframeDependente().setDependente(d);
					c.getIframeDependente().setVisible(true);
					c.getIframeDependente().setTableModel((DependenteTableModel)tbDependentes.getModel());
				}else
				{
					c.mostrarMensagem("Óbito cadastrado, não é possível efetuar a alteração", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			else if (e.getSource() == btExcluir){
				DependenteTableModel model = ((DependenteTableModel)tbDependentes.getModel());
				AssociadoVO d = (AssociadoVO) model.getSelectedValue(tbDependentes.getSelectedRow());
				
				if(d.getObito() != null){
				
					if (c.mostraConfirmacao("Confirmação", "Confirma a remoção do dependente?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
						try{
							
							Controller.getInstance().deleteAssociado(d);
							model.remove(d);
							model.fireTableDataChanged();
							notificar("Dependente \"" + d.getNome() + "\" excluido com sucesso!", BarraNotificacao.SUCESSO);
						}
						catch(Exception ex){
							notificar("Falha ao excluir o dependente.", BarraNotificacao.ERRO);

						}
					}
				}else
				{
					c.mostrarMensagem("Óbito cadastrado, não é possível efetuar a alteração", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	private class EventoBotaoComando implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btAvancar || e.getSource() == btSalvar){
				DependenteTableModel model = (DependenteTableModel)tbDependentes.getModel();
				int limiteAtual = apolice.getPlano().getLimiteAssociado() - getCountAssociadosVivos();
				if (limiteAtual < 0)
					notificar("O núm. de dependentes (" + getCountAssociadosVivos() + ") excede o limite do plano " + apolice.getPlano().getDescricao() + "(" + apolice.getPlano().getLimiteAssociado() + ").", BarraNotificacao.ERRO);
				else{
					apolice.setDependentes(model.getDataSet());
					listenerControles.actionPerformed(e);
				}
				
			}
		}
	}

}