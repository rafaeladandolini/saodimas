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

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.table.FaturaApoliceTable;
import br.com.saodimas.view.components.table.model.FaturaApoliceTableModel;
import br.com.saodimas.view.components.table.model.FaturaTableModel;

@SuppressWarnings("serial")
public class ApoliceFaturaPanel extends JPanel {
	public static final String FORM_NAME = "Faturas";
	
	private ApoliceVO apolice;
	private JButton btEditar;
	private JButton btRemover;
	private JButton btNovo;
	private JToolBar barControle;
	private FaturaApoliceTable tbFaturasApolice;
	
	private BarraNotificacao barNotificacao;
	private ActionListener listenerControles;
	private JButton btVoltar;
	private JButton btAvancar;
	private JButton btSalvar;
	private JButton btCancelar;

	private int etapa;
	private int numEtapas;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	private static final String MENSAGEM_PADRAO = "Adicione ao menos uma fatura para esta apólice!!!";
	
	public ApoliceFaturaPanel(ActionListener listenerControles, int etapa, int numEtapas, BarraNotificacao bar) {
		this.etapa = etapa;
		this.numEtapas = numEtapas;
		this.listenerControles = listenerControles;
		this.barNotificacao = bar;
		initialize();
		configure();
	}
	
	@Override
	public void setVisible(boolean flag) {
		if (flag) barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, BarraNotificacao.DICA);
		else barNotificacao.escondeMensagem();
		
		super.setVisible(flag);
		if (flag) btNovo.requestFocus();
	}


	public ApoliceVO getAploice() {
		return apolice;
	}

	public void setApolice(ApoliceVO a) {
		apolice = a;
		if (apolice != null){
			List<FaturaVO> f = apolice.getFaturas();
			if (f != null){
				FaturaApoliceTableModel model = (FaturaApoliceTableModel)tbFaturasApolice.getModel();
				model.removeAll();
				for (FaturaVO fatura : f) {
					model.add(fatura);
				}
			}
			else{
				limparCampos();
				apolice.setFaturas(new ArrayList<FaturaVO>());
			}
		}
		else limparCampos();
	}
	
	public void adicionaFatura(FaturaVO d){
		FaturaTableModel model = (FaturaTableModel)tbFaturasApolice.getModel();
		List<FaturaVO> f = apolice.getFaturas();
		if(!model.getDataVector().contains(d)){
    		model.add(d);
    		if (f != null) f.add(d);
    		model.fireTableDataChanged();
    		tbFaturasApolice.getSelectionModel().addSelectionInterval(0, 0);
    	}
		else if (model.getDataVector().contains(d)){
			barNotificacao.mostrarMensagem("A fatura já está cadastrado!", BarraNotificacao.ERRO);
		}
	}
	
	public void limparCampos() {
		FaturaApoliceTableModel model = (FaturaApoliceTableModel)tbFaturasApolice.getModel();
		model.removeAll();
		model.fireTableDataChanged();
	}
	
	@Override
	public void requestFocus() {
		btNovo.requestFocus();
	}

	private void initialize() {
		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addBill.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastrar uma nova fatura!");
		btNovo.setPreferredSize(DBUTTON);
		
		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Clique para editar uma fatura!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);

		btRemover = new JButton();
		btRemover.setIcon(new ImageIcon("imagens/remove.gif"));
		btRemover.addActionListener(new EventoBotaoControle());
		btRemover.setToolTipText("Clique para remover uma fatura");
		btRemover.setPreferredSize(DBUTTON);
		btRemover.setEnabled(false);

		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btRemover);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btRemover);
		
		tbFaturasApolice = new FaturaApoliceTable();
		tbFaturasApolice.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbFaturasApolice.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbFaturasApolice.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btRemover.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				super.keyPressed(e);
			}
		});
		tbFaturasApolice.getModel().addTableModelListener(new EventoModeloTabela());
		
		btVoltar = new JButton("Voltar", new ImageIcon("imagens/back.gif"));
		btVoltar.addActionListener(listenerControles);
		btVoltar.setHorizontalAlignment(JButton.LEFT);
		btVoltar.setActionCommand(ApolicePanel.ACAO_VOLTAR);
		
		btAvancar = new JButton("Próximo", new ImageIcon("imagens/next.gif"));
		btAvancar.addActionListener(new EventoBotaoComando());
		btAvancar.setHorizontalAlignment(JButton.LEFT);
		btAvancar.setActionCommand(ApolicePanel.ACAO_AVANCAR);

		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoComando());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		btSalvar.setActionCommand(ApolicePanel.ACAO_SALVAR);		
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(listenerControles);
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		btCancelar.setActionCommand(ApolicePanel.ACAO_CANCELAR);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel fatPanel = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; fatPanel.add(barControle, c);
		
		JScrollPane tbFatPanel = new JScrollPane(tbFaturasApolice);
		tbFatPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; fatPanel.add(tbFatPanel, c);
		
		fatPanel.setBorder(BorderFactory.createTitledBorder("Faturas"));
		fatPanel.setPreferredSize(new Dimension(350,350));
		setLayout(new BorderLayout());
		
		JLabel lbTitulo = new JLabel("Faturas (" + etapa + "/" + numEtapas + ")", new ImageIcon("imagens/faturas.gif"), JLabel.LEADING);
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
		add(fatPanel, BorderLayout.CENTER);
		add(controle, BorderLayout.SOUTH);
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	private void notificar(final String mensagem, final int tipoMensagem){
		new Thread() {
			@Override
			public synchronized void start() {
				try{
					barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
				}
				catch(ClassCastException cex){cex.printStackTrace();}
				super.start();
			}

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				try{
					Thread.sleep(10000);
					barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, BarraNotificacao.DICA);
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
			btEditar.setEnabled(tbFaturasApolice.getSelectedRow() > -1);
			btRemover.setEnabled(tbFaturasApolice.getSelectedRow() > -1);
		}
	}
	
	private class EventoModeloTabela implements TableModelListener{
		public void tableChanged(TableModelEvent e) {
			if (tbFaturasApolice.getRowCount() == 0){
				btNovo.setEnabled(true);
				barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, BarraNotificacao.DICA);				
			}
			else barNotificacao.mostrarMensagem("A apólice está completa! Clique em \"Salvar\" para concluir!", BarraNotificacao.DICA);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ApoliceMainPanel c = (ApoliceMainPanel)getPainelPrincipal();
			if (e.getSource() == btNovo){
				FaturaVO f = new FaturaVO();
				f.setApolice(apolice);
				c.getIframeFatura().setVisible(true);
				c.getIframeFatura().setApolice(apolice);
				c.getIframeFatura().setFatura(f);
			}
			else if (e.getSource() == btEditar){
				c.getIframeFatura().setVisible(true);
				FaturaVO f = (FaturaVO) ((FaturaTableModel)tbFaturasApolice.getModel()).getSelectedValue(tbFaturasApolice.getSelectedRow());
				c.getIframeFatura().setFatura(f);
			}
			else if (e.getSource() == btRemover){
				if (c.mostraConfirmacao("Confirmação", "Confirma a remoção da fatura?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					try{
						FaturaTableModel model = ((FaturaTableModel)tbFaturasApolice.getModel());
						FaturaVO f = (FaturaVO) model.getSelectedValue(tbFaturasApolice.getSelectedRow());
						model.remove(f);
						model.fireTableDataChanged();
						notificar("Fatura \"" + f.getNumeroFatura() + "\" excluida com sucesso!", BarraNotificacao.SUCESSO);
					}
					catch(Exception ex){
						ex.printStackTrace();
						notificar("Falha ao excluir a fatura.", BarraNotificacao.ERRO);
					}
				}
			}
		}
	}
	
	private class EventoBotaoComando implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btAvancar || e.getSource() == btSalvar){
				FaturaApoliceTableModel model = (FaturaApoliceTableModel)tbFaturasApolice.getModel();
				if (model.getRowCount() > 0) {
					apolice.setFaturas((model.getDataSet()));
					listenerControles.actionPerformed(e);
					
			}else
					barNotificacao.mostrarMensagem("Você deve cadastrar no mímino 01 fatura para a apólice!", BarraNotificacao.ERRO);
			}
		}
	}
	
}