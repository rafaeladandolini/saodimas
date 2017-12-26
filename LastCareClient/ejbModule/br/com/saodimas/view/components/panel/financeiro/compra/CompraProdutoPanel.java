package br.com.saodimas.view.components.panel.financeiro.compra;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.saodimas.model.beans.CompraVO;
import br.com.saodimas.model.beans.ProdutoCompraVO;
import br.com.saodimas.view.components.table.CompraProdutosTable;
import br.com.saodimas.view.components.table.model.CompraProdutoTableModel;
import br.com.saodimas.view.components.text.MoedaTextField;


@SuppressWarnings("serial")
public class CompraProdutoPanel extends JPanel{
	
	private JButton btAdicionar;
	private JButton btRemover;
	private JToolBar barControle;
	
	private JLabel lbTotalProduto;
	private MoedaTextField txtTotalProduto;
	
	private List<ProdutoCompraVO> removerProdutosCOmpra;
	private CompraProdutosTable tbProdutos;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public CompraProdutoPanel() {
		initialize();
		configure();
	}
	
	
	public List<ProdutoCompraVO> getProdutosCompraRemover(){ return removerProdutosCOmpra;}

	public void adicionaProduto(ProdutoCompraVO p){
		CompraProdutoTableModel model = (CompraProdutoTableModel)tbProdutos.getModel();
		boolean editando = false; 
		
	
			for(Object item: model.getDataVector()){
				ProdutoCompraVO planProd = (ProdutoCompraVO)item;
										
				if(p.getId() != null && p.getId().equals(planProd.getId())){
						planProd.setQuantidade(p.getQuantidade());
						planProd.setValorCusto(p.getValorCusto());
						editando = true;
						break;
					}
			}
					
    	if (!editando){
    	
    		model.add(p);
    	}
		model.fireTableDataChanged();
		tbProdutos.getSelectionModel().addSelectionInterval(0, 0);
		this.calcularTotalCompra();
	}
	
	public List<ProdutoCompraVO> getAllProdutos(CompraVO p)
	{
		CompraProdutoTableModel model = (CompraProdutoTableModel)tbProdutos.getModel();
		Vector<ProdutoCompraVO> v = model.getDataVector();
		List<ProdutoCompraVO> s = new ArrayList<ProdutoCompraVO>();
		
		for (ProdutoCompraVO produtoObitoVO : v) {
			produtoObitoVO.setCompra(p);
			s.add(produtoObitoVO);
		}
		return s;
	}

	public void carregarProdutos(List<ProdutoCompraVO> produtos){
		removerProdutosCOmpra.clear();
		
		CompraProdutoTableModel model = (CompraProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
		if(produtos != null){
			for (ProdutoCompraVO prodPlano : produtos) {
				model.add(prodPlano);
			}
		}
		this.calcularTotalCompra();
	}
	
	public void calcularTotalCompra(){
		
		List<ProdutoCompraVO> list = ((CompraProdutoTableModel)tbProdutos.getModel()).getDataVector();
		txtTotalProduto.setValor(0.0);
		for(ProdutoCompraVO vo : list)
		{
			txtTotalProduto.setValor(txtTotalProduto.getValor() + vo.getQuantidade() * vo.getValorCusto());
		}
		
	}
	
	public void removeAllProdutos()
	{
		CompraProdutoTableModel model = (CompraProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
	}
	
	public void setBloqueioCampos(boolean value)
	{
		btAdicionar.setEnabled(!value);
	//	btEditar.setEnabled(!value);
		btRemover.setEnabled(!value);
	}
	private void initialize() {
		
		
		removerProdutosCOmpra = new ArrayList<ProdutoCompraVO>();
		
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

		lbTotalProduto = new JLabel("TOTAL COMPRA:  ", JLabel.RIGHT);
		lbTotalProduto.setPreferredSize(DLABEL);
		lbTotalProduto.setMinimumSize(DLABEL);

		txtTotalProduto = new MoedaTextField();
		txtTotalProduto.setPreferredSize(DFIELDM);
		txtTotalProduto.setMinimumSize(DFIELDM);
		txtTotalProduto.setEditable(false);
	
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btAdicionar);
		barControle.add(btRemover);
		
		
		tbProdutos = new CompraProdutosTable();
		tbProdutos.getSelectionModel().addListSelectionListener(new EventoTabela());
		
		
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infProdutos = new JPanel(new GridBagLayout());

		JPanel panelTotal = new JPanel();
		panelTotal.add(lbTotalProduto);
		panelTotal.add(txtTotalProduto);
		
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infProdutos.add(barControle, c);
		
		JScrollPane prodPanel = new JScrollPane(tbProdutos);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infProdutos.add(prodPanel, c);
		c.gridy = 2; infProdutos.add(panelTotal, c);
		
		infProdutos.setBorder(BorderFactory.createTitledBorder("Produtos"));
				
		setLayout(new BorderLayout());
		add(infProdutos, BorderLayout.CENTER);
	}
	
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btRemover.setEnabled(tbProdutos.getSelectedRow() > -1);
			
		}
	}
	

	private CompraMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof CompraMainPanel) return (CompraMainPanel)c;
			c = c.getParent();
		}
		return (CompraMainPanel)c;
	}	
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			CompraMainPanel c = getPainelPrincipal();
			if (e.getSource() == btAdicionar){
				c.getIFrameCompraProduto().setVisible(true);
				c.getIFrameCompraProduto().setProduto(null);
			}
			else if (e.getSource() == btRemover){
				if (c.mostraConfirmacao("Confirmação", "Confirma a remoção do produto para este óbito?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					CompraProdutoTableModel model = (CompraProdutoTableModel)tbProdutos.getModel();
					int row = tbProdutos.getSelectedRow();
					removerProdutosCOmpra.add((ProdutoCompraVO)model.getSelectedValue(row));
					model.removeAt(row);
					calcularTotalCompra();
				}
			}
			
		}
	}


}
