package br.com.saodimas.view.components.panel.plano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoPlanoVO;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.view.components.table.PlanoProdutoTable;
import br.com.saodimas.view.components.table.model.PlanoProdutoTableModel;

@SuppressWarnings("serial")
public class PlanoProdutoPanel extends JPanel {
	private JButton btAdicionar;
	private JButton btRemover;
	private JButton btNovo;
	private JToolBar barControle;

	private PlanoProdutoTable tbProdutos;

	private static final Dimension DBUTTON = new Dimension(30,30);

	public PlanoProdutoPanel() {
		initialize();
		configure();
	}

	public void removeAllProdutos()
	{
		PlanoProdutoTableModel model = (PlanoProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
	}

	public Set<ProdutoPlanoVO> getAllProdutos(PlanoVO p)
	{
		PlanoProdutoTableModel model = (PlanoProdutoTableModel)tbProdutos.getModel();
		Vector<ProdutoPlanoVO> v = model.getDataVector();
		Set<ProdutoPlanoVO> s = new HashSet<ProdutoPlanoVO>();

		for (ProdutoPlanoVO produtoPlanoVO : v) {
			produtoPlanoVO.setPlano(p);
			s.add(produtoPlanoVO);
		}
		return s;
	}

	public void adicionaProduto(ProdutoVO p){
		PlanoProdutoTableModel model = (PlanoProdutoTableModel)tbProdutos.getModel();
		boolean prodExists = false; 
		for(Object item: model.getDataVector()){
			ProdutoPlanoVO planProd = (ProdutoPlanoVO)item;
			int quantidade = planProd.getQuantidade();
			if (p.getNome().compareToIgnoreCase(planProd.getProduto().getNome()) == 0){
				planProd.setQuantidade(quantidade + 1);
				prodExists = true;
				System.out.println("Achei o produto na tabela!");
				break;
			}
		}
		if (!prodExists){
			ProdutoPlanoVO pp = new ProdutoPlanoVO();
			pp.setProduto(p);
			pp.setQuantidade(1);
			model.add(pp);
		}
		model.fireTableDataChanged();
		tbProdutos.getSelectionModel().addSelectionInterval(0, 0);
	}

	public void carregarProdutos(List<ProdutoPlanoVO> produtos){
		PlanoProdutoTableModel model = (PlanoProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
		for (ProdutoPlanoVO prodPlano : produtos) {
			model.add(prodPlano);
		}
	}

	private void initialize() {
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

		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addProducts.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastrar um novo produto!");
		btNovo.setPreferredSize(DBUTTON);

		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btAdicionar);
		barControle.add(btRemover);
		barControle.addSeparator();
		barControle.add(btNovo);

		tbProdutos = new PlanoProdutoTable();
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
		infProdutos.setMaximumSize(new Dimension(305, 75));
		infProdutos.setPreferredSize(new Dimension(305, 75));

		setLayout(new BorderLayout());
		add(infProdutos, BorderLayout.CENTER);
	}

	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof PlanoMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}		

	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btRemover.setEnabled(tbProdutos.getSelectedRow() > -1);
		}
	}

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			PlanoMainPanel c = (PlanoMainPanel)getPainelPrincipal();
			if (e.getSource() == btAdicionar){
				c.getIframeSelecaoProduto().setVisible(true);
			}
			else if (e.getSource() == btRemover){
				if (c.mostraConfirmacao("Confirmação", "Confirma a remoção do produto para este plano?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbProdutos.getSelectedRow();
					PlanoProdutoTableModel model = (PlanoProdutoTableModel)tbProdutos.getModel();
					model.removeAt(row);
					model.fireTableDataChanged();
				}
			}
			else if (e.getSource() == btNovo){
				c.getIframeProduto().setVisible(true);
			}
		}
	}
}
