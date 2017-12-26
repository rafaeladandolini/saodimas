package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ProdutoTableModel;


/**
 * Classe que define o painel que contém o JTable de produtos.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ProdutoTable extends JTable {
	public ProdutoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ProdutoTableModel model = new ProdutoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(ProdutoTableModel.CODIGO).setMinWidth(30);
		this.getColumnModel().getColumn(ProdutoTableModel.CODIGO).setMaxWidth(50);
		this.getColumnModel().getColumn(ProdutoTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(ProdutoTableModel.DESCRICAO).setMinWidth(150);
				
		this.getColumnModel().getColumn(ProdutoTableModel.STATUS).setMinWidth(90);
		this.getColumnModel().getColumn(ProdutoTableModel.STATUS).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ProdutoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ProdutoTableModel.DESCRICAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ProdutoTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ProdutoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ProdutoTableModel.DESCRICAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ProdutoTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
