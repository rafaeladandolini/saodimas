package br.com.saodimas.view.components.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.saodimas.view.components.table.model.VendaTableModel;



public class VendaTable extends JTable{

	private static final long serialVersionUID = -5292359318052529850L;

	public VendaTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		VendaTableModel model = new VendaTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
