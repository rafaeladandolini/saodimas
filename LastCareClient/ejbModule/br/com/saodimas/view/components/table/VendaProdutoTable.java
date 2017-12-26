package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.VendaProdutoTableModel;



@SuppressWarnings("serial")
public class VendaProdutoTable extends JTable {
	public VendaProdutoTable() {
		initialize();
	}

	private void initialize() {
		VendaProdutoTableModel model = new VendaProdutoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(VendaProdutoTableModel.NOME).setMinWidth(110);
		this.getColumnModel().getColumn(VendaProdutoTableModel.QUANTIDADE).setMinWidth(70);
		this.getColumnModel().getColumn(VendaProdutoTableModel.QUANTIDADE).setMaxWidth(100);
		
		this.getColumnModel().getColumn(VendaProdutoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaProdutoTableModel.QUANTIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaProdutoTableModel.VALOR_VENDA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaProdutoTableModel.TOTAL_PAGAR).setCellRenderer(new DefaultTableCellRenderer());
			
			
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(VendaProdutoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
				
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
