package br.com.saodimas.view.components.panel.produto;

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
import java.util.List;

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
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.iframe.ConsProdutoIFrame;
import br.com.saodimas.view.components.iframe.ProdutoIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.ProdutoTable;
import br.com.saodimas.view.components.table.model.ProdutoTableModel;

@SuppressWarnings("serial")
public class ProdutoMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Produtos"; 
	public static final String MENSAGEM_PADRAO = "   Pressione a tecla [F3] para Pesquisar, [INSERT] para Cadastrar e [ENTER] ou [DELETE] para editar/excluir um produto selecionado!";

	private BarraNotificacao barNotificacao;
	private JButton btPesquisar;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JToolBar barControle;

	private ProdutoTable tbProdutos;
	private ProdutoIFrame iframeProduto;
	private ConsProdutoIFrame iframeConsultaProduto;

	private AbstractButton ultimoComando;

	private static final Dimension DBUTTON = new Dimension(30,30);

	public ProdutoMainPanel(){
		initialize();
		configure();
	}

	public void carregarProdutosTable()
	{
		carregarProdutosTable(Controller.getInstance().getProdutoAll());
	}
	
	public void carregarProdutosTable(List<ProdutoVO> list)
	{
		((ProdutoTableModel)tbProdutos.getModel()).loadData(list);
	}
	
	public void mostrarMensagem(String msg, int tipo){
		barNotificacao.mostrarMensagem(msg, tipo);
	}
	
	public ProdutoIFrame getIframeProduto() {
		return iframeProduto;
	}

	public ConsProdutoIFrame getIframeConsultaProduto() {
		return iframeConsultaProduto;
	}

	public void selecionaUltimoComando(){
		if (ultimoComando != null){
			if (ultimoComando != btEditar && ultimoComando != btExcluir){
				tbProdutos.getSelectionModel().clearSelection();
				ultimoComando.requestFocus();
			}
			else tbProdutos.requestFocus();
		}
	}	

	@Override
	public void setEnabled(boolean enabled) {
		btPesquisar.setEnabled(enabled);
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbProdutos.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbProdutos.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbProdutos.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			ultimoComando = btPesquisar;
			selecionaUltimoComando();
			if (ultimoComando == btPesquisar && tbProdutos.getRowCount() == 0){
				iframeConsultaProduto.setVisible(true);
				iframeConsultaProduto.habilitarControles(false);				
			}
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;

		iframeProduto = new ProdutoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();				
				super.setVisible(flag);
			}
		};
		iframeProduto.setLocation(x - iframeProduto.getSize().width / 2, y - iframeProduto.getSize().height / 2);

		iframeConsultaProduto = new ConsProdutoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();				
				super.setVisible(flag);
			}
		};
		iframeConsultaProduto.setLocation(x - iframeConsultaProduto.getSize().width / 2, y - iframeConsultaProduto.getSize().height / 2);

		getDesktop().add(iframeProduto);
		getDesktop().add(iframeConsultaProduto);

		tbProdutos = new ProdutoTable();
		tbProdutos.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbProdutos.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbProdutos.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_F3) btPesquisar.doClick();
				super.keyPressed(e);
			}
		});

		btPesquisar = new JButton();
		btPesquisar.setIcon(new ImageIcon("imagens/search.gif"));
		btPesquisar.addActionListener(new EventoBotaoControle());
		btPesquisar.setToolTipText("Pesquisar produtos!");
		btPesquisar.setPreferredSize(DBUTTON);
		btPesquisar.addKeyListener(tbProdutos.getKeyListeners()[0]);

		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addProducts.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra um novo produto!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbProdutos.getKeyListeners()[0]);

		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de um produto!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbProdutos.getKeyListeners()[0]);

		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um produto!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbProdutos.getKeyListeners()[0]);

		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btPesquisar);
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);

		ultimoComando = btPesquisar;
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infProduto = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infProduto.add(barControle, c);

		JScrollPane prodPanel = new JScrollPane(tbProdutos);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infProduto.add(prodPanel, c);

		infProduto.setBorder(BorderFactory.createTitledBorder("Produtos"));

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
		c.gridy = 1; panelPrincipal.add(infProduto, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}

	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbProdutos.getSelectedRow() > -1);
			btExcluir.setEnabled(tbProdutos.getSelectedRow() > -1);
		}
	}

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ultimoComando = (AbstractButton)e.getSource();
			if (e.getSource() == btPesquisar){
				iframeConsultaProduto.setVisible(true);
				iframeConsultaProduto.habilitarControles(false);				
			}
			else if (e.getSource() == btNovo){
				iframeProduto.setProduto(null);
				iframeProduto.setVisible(true);
				iframeProduto.habilitarControles(false);				
			}
			else if (e.getSource() == btEditar){
				int row = tbProdutos.getSelectedRow();
				if (row >= 0){
					ProdutoTableModel model = (ProdutoTableModel)tbProdutos.getModel();
					ProdutoVO prod = (ProdutoVO)model.getSelectedValue(row);

					iframeProduto.setProduto(prod);
					iframeProduto.setVisible(true);
					iframeProduto.habilitarControles(false);
				}
			}
			else{
				int row = tbProdutos.getSelectedRow();
				if (row >= 0){
					if (mostraConfirmacao("Confirmação", "Confirma a exclusão do produto?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
						ProdutoTableModel model = (ProdutoTableModel)tbProdutos.getModel();
						ProdutoVO produto = (ProdutoVO)model.getSelectedValue(row);
						try {
							Controller.getInstance().deleteProduto(produto);
							barNotificacao.mostrarMensagem("Produto removido com sucesso.", BarraNotificacao.INFO);
						} catch (MensagemSaoDimasException ex) {
							barNotificacao.mostrarMensagem(ex.getMessage(), BarraNotificacao.ERRO);
						}
						carregarProdutosTable();
					}
				}
			}
		}
	}
}
