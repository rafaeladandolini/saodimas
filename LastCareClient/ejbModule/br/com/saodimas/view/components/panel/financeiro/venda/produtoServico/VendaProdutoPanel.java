package br.com.saodimas.view.components.panel.financeiro.venda.produtoServico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.saodimas.model.beans.ProdutoVendaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.view.components.panel.financeiro.cliente.editar.ClienteEditarMainPanel;
import br.com.saodimas.view.components.table.VendaProdutoTable;
import br.com.saodimas.view.components.table.model.VendaProdutoTableModel;


@SuppressWarnings("serial")
public class VendaProdutoPanel extends JPanel {
	private JButton btAdicionar;
	private JButton btRemover;
	private JButton btEditar;
	private JToolBar barControle;
	
	private List<ProdutoVendaVO> removerProdutosVendas;
	private VendaProdutoTable tbProdutos;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public VendaProdutoPanel() {
		initialize();
		configure();
	}
	
	public List<ProdutoVendaVO> getProdutosVendaRemover(){ return removerProdutosVendas;}

	public void adicionaProduto(ProdutoVendaVO p){
		
		VendaProdutoTableModel model = (VendaProdutoTableModel) tbProdutos.getModel();
		boolean editando = false;
		
		for (Object item : model.getDataVector()) {
			ProdutoVendaVO planProd = (ProdutoVendaVO) item;

			if(p.getProduto() != null && p.getProduto().getId().equals(planProd.getProduto().getId()) && p.getValor().equals(planProd.getValor())){
					planProd.setQuantidade(p.getQuantidade() + planProd.getQuantidade());
					planProd.setTotal(p.getValor() * planProd.getQuantidade());
					planProd.setValor(p.getValor());
					editando = true;
					break;
				}
		}

		if (!editando) {

			model.add(p);
		}
		
		model.fireTableDataChanged();
		tbProdutos.getSelectionModel().addSelectionInterval(0, 0);
	
	}
	
	public List<ProdutoVendaVO> getAllProdutos(VendaVO venda)
	{
		VendaProdutoTableModel model = (VendaProdutoTableModel)tbProdutos.getModel();
		Vector<ProdutoVendaVO> vVendas = model.getDataVector();
		List<ProdutoVendaVO> list = new ArrayList<ProdutoVendaVO>();
		
		for (ProdutoVendaVO produtovendaVO : vVendas) {
			produtovendaVO.setVenda(venda);
			list.add(produtovendaVO);
		}
		return list;
	}

	public void carregarProdutos(List<ProdutoVendaVO> produtos){
		removerProdutosVendas.clear();
		
		VendaProdutoTableModel model = (VendaProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
		if(produtos != null){
			for (ProdutoVendaVO prodPlano : produtos) {
				model.add(prodPlano);
			}
		}
	}
	
	public void removeAllProdutos()
	{
		VendaProdutoTableModel model = (VendaProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
	}
	
	public void setBloqueioCampos(boolean value)
	{
		btAdicionar.setEnabled(!value);
		btEditar.setEnabled(!value);
		btRemover.setEnabled(!value);
	}
	private void initialize() {
		
		removerProdutosVendas = new ArrayList<ProdutoVendaVO>();
		
		btAdicionar = new JButton();
		btAdicionar.setIcon(new ImageIcon("imagens/add.gif"));
		btAdicionar.addActionListener(new EventoBotaoControle());
		btAdicionar.setToolTipText("Clique para adicionar um produto ao plano!");
		btAdicionar.setPreferredSize(DBUTTON);
		
		btRemover = new JButton();
		btRemover.setIcon(new ImageIcon("imagens/remove.gif"));
		btRemover.addActionListener(new EventoBotaoControle());
		btRemover.setToolTipText("Clique para remover um produto do plano!");
		btRemover.setPreferredSize(DBUTTON);
		btRemover.setEnabled(false);

		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Editar Produto!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btAdicionar);
		barControle.add(btEditar);
		barControle.add(btRemover);
		
		
		tbProdutos = new VendaProdutoTable();
		tbProdutos.getSelectionModel().addListSelectionListener(new EventoTabela());
		
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infProdutos = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infProdutos.add(barControle, c);
		
		JScrollPane prodPanel = new JScrollPane(tbProdutos);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		prodPanel.setPreferredSize(new Dimension(500, 200));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infProdutos.add(prodPanel, c);
		
		infProdutos.setBorder(BorderFactory.createTitledBorder("Produtos"));
				
		setLayout(new BorderLayout());
		add(infProdutos, BorderLayout.CENTER);
	}
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ClienteEditarMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btRemover.setEnabled(tbProdutos.getSelectedRow() > -1);
			btEditar.setEnabled(tbProdutos.getSelectedRow() > -1);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ClienteEditarMainPanel c = (ClienteEditarMainPanel)getPainelPrincipal();
			if (e.getSource() == btAdicionar){
				c.getIframeVendaProduto().setVisible(true);
				c.getIframeVendaProduto().setProduto(null);
			}
			else if (e.getSource() == btRemover){
				if (c.mostraConfirmacao("Confirmação", "Confirma a remoção do produto para este óbito?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					VendaProdutoTableModel model = (VendaProdutoTableModel)tbProdutos.getModel();
					int row = tbProdutos.getSelectedRow();
					removerProdutosVendas.add((ProdutoVendaVO)model.getSelectedValue(row));
					model.removeAt(row);
															
				}
			}
			else if (e.getSource() == btEditar){
				int row = tbProdutos.getSelectedRow();
				if (row >= 0){
					VendaProdutoTableModel model = (VendaProdutoTableModel)tbProdutos.getModel();
					ProdutoVendaVO prod = (ProdutoVendaVO)model.getSelectedValue(row);
					c.getIframeVendaProduto().setVisible(true);
					c.getIframeVendaProduto().setProduto(prod);
				}
			}
		}
	}


}
