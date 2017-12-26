package br.com.saodimas.view.components.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.saodimas.view.components.table.model.ChequeTableModel;



@SuppressWarnings("serial")
public class ChequeTable extends JTable {
	public ChequeTable() {
		initialize();
	}

	
	private void initialize() {
		ChequeTableModel model = new ChequeTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(ChequeTableModel.DATA_CADASTRO).setMinWidth(30);
		this.getColumnModel().getColumn(ChequeTableModel.DATA_COMPENSACAO).setMinWidth(30);
		this.getColumnModel().getColumn(ChequeTableModel.DATA_EMISSAO).setMinWidth(30);
		this.getColumnModel().getColumn(ChequeTableModel.DATA_VENCIMENTO).setMinWidth(30);
		this.getColumnModel().getColumn(ChequeTableModel.BANCO).setMinWidth(60);
		this.getColumnModel().getColumn(ChequeTableModel.CLIENTE_FORNECEDOR).setMinWidth(80);
		this.getColumnModel().getColumn(ChequeTableModel.CONTA).setMinWidth(60);
		this.getColumnModel().getColumn(ChequeTableModel.DESCRICAO).setMinWidth(100);
		this.getColumnModel().getColumn(ChequeTableModel.NUMERO).setMinWidth(10);
		this.getColumnModel().getColumn(ChequeTableModel.VALOR).setMinWidth(30);
		
		/*((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.BANCO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.CONTA).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.DATA_CADASTRO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.DATA_COMPENSACAO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.DATA_EMISSAO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.DATA_VENCIMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.CONTA).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.DESCRICAO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.NUMERO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ChequeTableModel.VALOR).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		*/
				
		
		//this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
