package br.com.saodimas.view.components.panel.apolice.editar.obito;

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

import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.ObitoProdutoTable;
import br.com.saodimas.view.components.table.model.ObitoProdutoTableModel;


@SuppressWarnings("serial")
public class ObitoProdutoPanel extends JPanel {
	private JButton btAdicionar;
	private JButton btRemover;
	private JButton btEditar;
	private JToolBar barControle;
	
	private List<ProdutoObitoVO> removerProdutosObitos;
	private ObitoProdutoTable tbProdutos;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public ObitoProdutoPanel() {
		initialize();
		configure();
	}
	
	public List<ProdutoObitoVO> getProdutosObitoRemover(){ return removerProdutosObitos;}

	public void adicionaProduto(ProdutoObitoVO p){
		ObitoProdutoTableModel model = (ObitoProdutoTableModel)tbProdutos.getModel();
		boolean editando = false; 
		
	
			for(Object item: model.getDataVector()){
				ProdutoObitoVO planProd = (ProdutoObitoVO)item;
				if ((p.getId() == null && p.getValor().equals(planProd.getValor()) && planProd.getProduto().getId().equals(p.getProduto().getId())))
				{
					planProd.setQuantidade(planProd.getQuantidade() + p.getQuantidade());
					planProd.setTotal(p.getValor() * planProd.getQuantidade());
					planProd.setValor(p.getValor());
					editando = true;
					break;
				}else						
				if(p.getId() != null && p.getId().equals(planProd.getId())){
						planProd.setQuantidade(p.getQuantidade());
						planProd.setTotal(p.getValor() * planProd.getQuantidade());
						planProd.setValor(p.getValor());
						editando = true;
						break;
					}
			}
					
    	if (!editando){
    	
    		model.add(p);
    	}
		model.fireTableDataChanged();
		tbProdutos.getSelectionModel().addSelectionInterval(0, 0);
	}
	
	public List<ProdutoObitoVO> getAllProdutos(ObitoVO p)
	{
		ObitoProdutoTableModel model = (ObitoProdutoTableModel)tbProdutos.getModel();
		Vector<ProdutoObitoVO> v = model.getDataVector();
		List<ProdutoObitoVO> s = new ArrayList<ProdutoObitoVO>();
		
		for (ProdutoObitoVO produtoObitoVO : v) {
			produtoObitoVO.setObito(p);
			s.add(produtoObitoVO);
		}
		return s;
	}

	public void carregarProdutos(List<ProdutoObitoVO> produtos){
		removerProdutosObitos.clear();
		
		ObitoProdutoTableModel model = (ObitoProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
		for (ProdutoObitoVO prodPlano : produtos) {
			model.add(prodPlano);
		}
	}
	
	public void removeAllProdutos()
	{
		ObitoProdutoTableModel model = (ObitoProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
	}
	
	public void setBloqueioCampos(boolean value)
	{
		btAdicionar.setEnabled(!value);
		btEditar.setEnabled(!value);
		btRemover.setEnabled(!value);
	}
	private void initialize() {
		
		removerProdutosObitos = new ArrayList<ProdutoObitoVO>();
		
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
		
		
		tbProdutos = new ObitoProdutoTable();
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
	
	private ApoliceEditarMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceEditarMainPanel) return (ApoliceEditarMainPanel)c;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel)c;
	}	
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btRemover.setEnabled(tbProdutos.getSelectedRow() > -1);
			btEditar.setEnabled(tbProdutos.getSelectedRow() > -1);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btAdicionar){
				c.getIframeSelecaoProduto().setVisible(true);
				c.getIframeSelecaoProduto().setProduto(null);
			}
			else if (e.getSource() == btRemover){
				if (c.mostraConfirmacao("Confirmação", "Confirma a remoção do produto para este óbito?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					ObitoProdutoTableModel model = (ObitoProdutoTableModel)tbProdutos.getModel();
					int row = tbProdutos.getSelectedRow();
					removerProdutosObitos.add((ProdutoObitoVO)model.getSelectedValue(row));
					model.removeAt(row);
															
				}
			}
			else if (e.getSource() == btEditar){
				int row = tbProdutos.getSelectedRow();
				if (row >= 0){
					ObitoProdutoTableModel model = (ObitoProdutoTableModel)tbProdutos.getModel();
					ProdutoObitoVO prod = (ProdutoObitoVO)model.getSelectedValue(row);
					c.getIframeSelecaoProduto().setVisible(true);
					c.getIframeSelecaoProduto().setProduto(prod);
				}
			}
		}
	}


}
