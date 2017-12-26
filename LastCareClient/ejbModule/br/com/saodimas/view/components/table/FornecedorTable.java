package br.com.saodimas.view.components.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.saodimas.view.components.table.model.EmpresaCompraTableModel;



@SuppressWarnings("serial")
public class FornecedorTable extends JTable {
	public FornecedorTable() {
		initialize();
	}

	
	private void initialize() {
		EmpresaCompraTableModel model = new EmpresaCompraTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(EmpresaCompraTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(EmpresaCompraTableModel.CONTATO).setMinWidth(75);
		
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmpresaCompraTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmpresaCompraTableModel.CONTATO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
