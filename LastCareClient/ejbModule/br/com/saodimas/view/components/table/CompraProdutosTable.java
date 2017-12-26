package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.CompraProdutoTableModel;



@SuppressWarnings("serial")
public class CompraProdutosTable extends JTable {
	public CompraProdutosTable() {
		initialize();
	}

	private void initialize() {
		CompraProdutoTableModel model = new CompraProdutoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(CompraProdutoTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(CompraProdutoTableModel.QUANTIDADE).setMinWidth(70);
		this.getColumnModel().getColumn(CompraProdutoTableModel.QUANTIDADE).setMaxWidth(100);
		
		this.getColumnModel().getColumn(CompraProdutoTableModel.VALOR_CUSTO).setMinWidth(70);
		this.getColumnModel().getColumn(CompraProdutoTableModel.VALOR_CUSTO).setMaxWidth(100);
		
		//this.getColumnModel().getColumn(CompraProdutoTableModel.VALOR_VENDA).setMinWidth(70);
		//this.getColumnModel().getColumn(CompraProdutoTableModel.VALOR_VENDA).setMaxWidth(100);
		
		this.getColumnModel().getColumn(CompraProdutoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(CompraProdutoTableModel.QUANTIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(CompraProdutoTableModel.VALOR_CUSTO).setCellRenderer(new DefaultTableCellRenderer());
		//this.getColumnModel().getColumn(CompraProdutoTableModel.VALOR_VENDA).setCellRenderer(new DefaultTableCellRenderer());
				
			
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(CompraProdutoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
				
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
