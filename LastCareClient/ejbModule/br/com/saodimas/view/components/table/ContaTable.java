package br.com.saodimas.view.components.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.saodimas.view.components.table.model.ContaTableModel;



@SuppressWarnings("serial")
public class ContaTable extends JTable {
	public ContaTable() {
		initialize();
	}

	
	private void initialize() {
		ContaTableModel model = new ContaTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(ContaTableModel.BANCO).setMinWidth(110);
		this.getColumnModel().getColumn(ContaTableModel.AGENCIA).setMinWidth(75);
		this.getColumnModel().getColumn(ContaTableModel.NOME).setMinWidth(110);
		this.getColumnModel().getColumn(ContaTableModel.NUMERO_CONTA).setMinWidth(110);
		this.getColumnModel().getColumn(ContaTableModel.TIPO_CONTA).setMinWidth(110);
		
		
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(ContaTableModel.BANCO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(ContaTableModel.AGENCIA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(ContaTableModel.NUMERO_CONTA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(ContaTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(ContaTableModel.TIPO_CONTA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
