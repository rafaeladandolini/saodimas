package br.com.saodimas.view.components.panel.plano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.iframe.PlanoIFrame;
import br.com.saodimas.view.components.iframe.PlanoProdutoIFrame;
import br.com.saodimas.view.components.iframe.PlanoServicoIFrame;
import br.com.saodimas.view.components.iframe.ProdutoIFrame;
import br.com.saodimas.view.components.iframe.ServicoIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.PlanoTable;
import br.com.saodimas.view.components.table.model.PlanoTableModel;

@SuppressWarnings("serial")
public class PlanoMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Plano"; 
	public static final String MENSAGEM_PADRAO = "   Pressione a tecla [INSERT] para Cadastrar, [ENTER] ou [DELETE] para editar/excluir um plano selecionado!";
	
	private BarraNotificacao barNotificacao;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JToolBar barControle;
	
	private PlanoTable tbPlano;
	private PlanoIFrame iframePlano;
	private PlanoServicoIFrame iframeSelecaoServico;
	private PlanoProdutoIFrame iframeSelecaoProduto;
	private ServicoIFrame iframeServico;
	private ProdutoIFrame iframeProduto;
	
	private AbstractButton ultimoComando;
		
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public PlanoMainPanel() {
		initialize();
		configure();
	}
	
	public void carregarPlanosTable()
	{
		((PlanoTableModel)tbPlano.getModel()).loadData(Controller.getInstance().getPlanoAll());
	}
	
	public PlanoIFrame getIframePlano() {
		return iframePlano;
	}

	public PlanoServicoIFrame getIframeSelecaoServico() {
		return iframeSelecaoServico;
	}

	public PlanoProdutoIFrame getIframeSelecaoProduto() {
		return iframeSelecaoProduto;
	}

	public ServicoIFrame getIframeServico() {
		return iframeServico;
	}

	public ProdutoIFrame getIframeProduto() {
		return iframeProduto;
	}
	
	public void adicionaProduto(ProdutoVO p){
		iframePlano.adicionaProduto(p);
	}
	
	public void adicionaServico(ServicoVO s) {
		iframePlano.adicionaServico(s);
	}

	public void selecionaUltimoComando(){
		if (ultimoComando != null){
			if (ultimoComando != btEditar && ultimoComando != btExcluir){
				tbPlano.getSelectionModel().clearSelection();
				ultimoComando.requestFocus();
			}
			else tbPlano.requestFocus();
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbPlano.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbPlano.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbPlano.setEnabled(enabled);
		super.setEnabled(enabled);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			ultimoComando = btNovo;
			selecionaUltimoComando();
			carregarPlanosTable();
		}
	}
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;
		
		iframeSelecaoServico = new PlanoServicoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				super.setVisible(flag);
				this.habilitarControles(false);
			}
		};
		iframeSelecaoServico.setLocation(x - iframeSelecaoServico.getSize().width / 2, y - iframeSelecaoServico.getSize().height / 2);
		
		iframeSelecaoProduto = new PlanoProdutoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				super.setVisible(flag);
				this.habilitarControles(false);
			}
		};
		iframeSelecaoProduto.setLocation(x - iframeSelecaoProduto.getSize().width / 2, y - iframeSelecaoProduto.getSize().height / 2);
		
		iframeServico = new ServicoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				super.setVisible(flag);
				this.habilitarControles(false);
			}
		};
		iframeServico.setLocation(x - iframeServico.getSize().width / 2, y - iframeServico.getSize().height / 2);
		
		iframeProduto = new ProdutoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				super.setVisible(flag);
				this.habilitarControles(false);
			}
		};
		iframeProduto.setLocation(x - iframeProduto.getSize().width / 2, y - iframeProduto.getSize().height / 2);
		
		iframePlano = new PlanoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();				
				if (flag == false){
					iframeSelecaoProduto.setVisible(false);
					iframeSelecaoServico.setVisible(false);
					iframeProduto.setVisible(false);
					iframeServico.setVisible(false);
				} 
				super.setVisible(flag);
			}
		};
		iframePlano.setLocation(x - iframePlano.getSize().width / 2, y - iframePlano.getSize().height / 2);
		
		getDesktop().add(iframeSelecaoServico);
		getDesktop().add(iframeSelecaoProduto);
		getDesktop().add(iframeServico);
		getDesktop().add(iframeProduto);
		getDesktop().add(iframePlano);
		
		tbPlano = new PlanoTable();
		tbPlano.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbPlano.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbPlano.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				super.keyPressed(e);
			}
		});
		
		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addPlan.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra um novo plano!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbPlano.getKeyListeners()[0]);
		
		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de um plano!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbPlano.getKeyListeners()[0]);
		
		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um plano!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbPlano.getKeyListeners()[0]);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infPlano = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infPlano.add(barControle, c);
		
		JScrollPane prodPanel = new JScrollPane(tbPlano);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infPlano.add(prodPanel, c);
		
		infPlano.setBorder(BorderFactory.createTitledBorder("Planos"));
		
		JPanel panelPrincipal = new JPanel(new GridBagLayout()); 
		c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; panelPrincipal.add(barNotificacao, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; panelPrincipal.add(infPlano, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);	
	}
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbPlano.getSelectedRow() > -1);
			btExcluir.setEnabled(tbPlano.getSelectedRow() > -1);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ultimoComando = (AbstractButton)e.getSource();
			if (e.getSource() == btNovo){
				iframePlano.setPlano(null);
				iframePlano.setVisible(true);
				iframePlano.habilitarControles(false);				
			}
			else if (e.getSource() == btEditar){
				int row = tbPlano.getSelectedRow();
				PlanoTableModel model = (PlanoTableModel)tbPlano.getModel();
				PlanoVO plan = (PlanoVO)model.getSelectedValue(row);
				iframePlano.setPlano(plan);
				iframePlano.setVisible(true);
				iframePlano.habilitarControles(false);				
			}
			else{
				if (mostraConfirmacao("Confirmação", "Confirma a exclusão do plano?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbPlano.getSelectedRow();
					PlanoTableModel model = (PlanoTableModel)tbPlano.getModel();
					PlanoVO prod = (PlanoVO)model.getSelectedValue(row);
					try{
						Controller.getInstance().deletePlano(prod);
						barNotificacao.mostrarMensagem("Plano removido com sucesso.", BarraNotificacao.INFO);
					} catch (MensagemSaoDimasException ex) {
						barNotificacao.mostrarMensagem(ex.getMessage(),	BarraNotificacao.ERRO);
					}
					carregarPlanosTable();
				}
			}
		}
	}

}
