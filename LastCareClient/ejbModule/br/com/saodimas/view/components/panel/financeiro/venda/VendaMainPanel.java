package br.com.saodimas.view.components.panel.financeiro.venda;

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
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.iframe.VendaIFrame;
import br.com.saodimas.view.components.iframe.VendaProdutoIFrame;
import br.com.saodimas.view.components.iframe.VendaServicoIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.VendaTable;
import br.com.saodimas.view.components.table.model.VendaTableModel;




@SuppressWarnings("serial")
public class VendaMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Vendas"; 
	public static final String MENSAGEM_PADRAO = "   Pressione a tecla [F3] para Pesquisar, [INSERT] para Cadastrar e [ENTER] ou [DELETE] para editar/excluir uma venda selecionado!";
	
	private BarraNotificacao barNotificacao;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JToolBar barControle;
	
	private VendaTable tbVendas;
	private VendaIFrame iframeVenda;
	private VendaProdutoIFrame iframeVendaProduto;
	private VendaServicoIFrame iframeVendaServico;
	
	private AbstractButton ultimoComando;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public VendaMainPanel(){
		initialize();
		configure();
	}
	
	
	public void carregarVendas()
	{
		((VendaTableModel)tbVendas.getModel()).loadData(Controller.getInstance().getAllVendas());
	}
	
	public VendaIFrame getIFrameVenda() {
		return iframeVenda;
	}
	
	public VendaProdutoIFrame getIFrameVendaProduto() {
		return iframeVendaProduto;
	}
	
	public VendaServicoIFrame getIFrameVendaServico() {
		return iframeVendaServico;
	}

	public void selecionaUltimoComando(){
		if (ultimoComando != null){
			if (ultimoComando != btEditar && ultimoComando != btExcluir){
				tbVendas.getSelectionModel().clearSelection();
				ultimoComando.requestFocus();
			}
			else tbVendas.requestFocus();
		}
	}	

	@Override
	public void setEnabled(boolean enabled) {
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbVendas.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbVendas.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbVendas.setEnabled(enabled);
		super.setEnabled(enabled);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			carregarVendas();
			barNotificacao.mostrarMensagem("", BarraNotificacao.DICA);
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao("");
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;
		
		iframeVenda = new VendaIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();
				super.setVisible(flag);
			}
		};
		iframeVenda.setLocation(x - iframeVenda.getSize().width / 2, y - iframeVenda.getSize().height / 2);
		
		getDesktop().add(iframeVenda);
		
		iframeVendaProduto = new VendaProdutoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				super.setVisible(flag);
				this.habilitarControles(false);
			}
		};
		iframeVendaProduto.setLocation(x - iframeVendaProduto.getSize().width / 2, y - iframeVendaProduto.getSize().height / 2);
		getDesktop().add(iframeVendaProduto);
		
				
		tbVendas = new VendaTable();
		tbVendas.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbVendas.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbVendas.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				super.keyPressed(e);
			}
		});
		
		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addEmployee.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra uma  nova venda!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbVendas.getKeyListeners()[0]);
		
		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita uma venda!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbVendas.getKeyListeners()[0]);
		
		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove uma venda!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbVendas.getKeyListeners()[0]);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		
		ultimoComando = btNovo;
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infColaborador = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infColaborador.add(barControle, c);
		
		JScrollPane depPanel = new JScrollPane(tbVendas);
		depPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infColaborador.add(depPanel, c);
		
		infColaborador.setBorder(BorderFactory.createTitledBorder("Vendas"));
		
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
		c.gridy = 1; panelPrincipal.add(infColaborador, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}
	
	public void mostrarMensagem(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbVendas.getSelectedRow() > -1);
			btExcluir.setEnabled(tbVendas.getSelectedRow() > -1);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ultimoComando = (AbstractButton)e.getSource();
			
			if (e.getSource() == btNovo){
				iframeVenda.setVenda(null);
				iframeVenda.setVisible(true);
				iframeVenda.habilitarControles(false);				
			}
			else if (e.getSource() == btEditar){
				int row = tbVendas.getSelectedRow();
				VendaTableModel model = (VendaTableModel)tbVendas.getModel();
				VendaVO dep = (VendaVO)model.getSelectedValue(row);
				
				iframeVenda.setVenda(dep);
				iframeVenda.setVisible(true);
				iframeVenda.habilitarControles(false);				
			}
			else{
				if (mostraConfirmacao("Confirmação", "Confirma a exclusão da compra?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbVendas.getSelectedRow();
					VendaTableModel model = (VendaTableModel)tbVendas.getModel();
					VendaVO venda = (VendaVO)model.getSelectedValue(row);
					try{
						Controller.getInstance().deleteVenda(venda);
					} catch (MensagemSaoDimasException ex) {
						barNotificacao.mostrarMensagem(ex.getMessage(),	BarraNotificacao.ERRO);
					}
					carregarVendas();
				}
			}
		}
	}

}
