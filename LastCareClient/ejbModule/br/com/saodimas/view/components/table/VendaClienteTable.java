package br.com.saodimas.view.components.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.VendaClienteTableModel;



public class VendaClienteTable extends JTable{

	private static final long serialVersionUID = -5292359318052529850L;

	public VendaClienteTable() {
		initialize();
		
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		VendaClienteTableModel model = new VendaClienteTableModel();
		this.setModel(model);
		model.loadData(null);
		
		//this.getColumnModel().getColumn(VendaClienteTableModel.DATA).setMaxWidth(80);
		this.getColumnModel().getColumn(VendaClienteTableModel.DATA).setMinWidth(80);
		
		//this.getColumnModel().getColumn(VendaClienteTableModel.TIPO_PAGAMENTO).setMaxWidth(80);
		this.getColumnModel().getColumn(VendaClienteTableModel.TIPO_PAGAMENTO).setMinWidth(80);
		
		//this.getColumnModel().getColumn(VendaClienteTableModel.TIPO_VENDA).setMaxWidth(80);
		this.getColumnModel().getColumn(VendaClienteTableModel.PARCELAMENTO).setMinWidth(80);
		
		//this.getColumnModel().getColumn(VendaClienteTableModel.VALOR).setMaxWidth(80);
		this.getColumnModel().getColumn(VendaClienteTableModel.VALOR).setMinWidth(80);
		
		//this.getColumnModel().getColumn(VendaClienteTableModel.VENDEDOR).setMaxWidth(80);
		this.getColumnModel().getColumn(VendaClienteTableModel.VENDEDOR).setMinWidth(80);
		
		this.getColumnModel().getColumn(VendaClienteTableModel.DATA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaClienteTableModel.TIPO_PAGAMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaClienteTableModel.PARCELAMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaClienteTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaClienteTableModel.VENDEDOR).setCellRenderer(new DefaultTableCellRenderer());
		
		
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
