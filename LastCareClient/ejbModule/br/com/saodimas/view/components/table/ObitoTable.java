package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ObitoTableModel;


/**
 * Classe que define o painel que cont�m o JTable de obitos.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ObitoTable extends JTable {
	public ObitoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gr�ficos e os configura.
	 */
	private void initialize() {
		ObitoTableModel model = new ObitoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(ObitoTableModel.ATESTADO).setMinWidth(120);
		this.getColumnModel().getColumn(ObitoTableModel.ATESTADO).setMaxWidth(120);

		this.getColumnModel().getColumn(ObitoTableModel.NOME).setMinWidth(120);
		
		this.getColumnModel().getColumn(ObitoTableModel.DATAOBITO).setMinWidth(100);
		this.getColumnModel().getColumn(ObitoTableModel.DATAOBITO).setMaxWidth(100);

		this.getColumnModel().getColumn(ObitoTableModel.CAUSA).setMinWidth(120);
		this.getColumnModel().getColumn(ObitoTableModel.CAUSA).setMaxWidth(120);

		
		this.getColumnModel().getColumn(ObitoTableModel.ATESTADO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoTableModel.DATAOBITO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoTableModel.CAUSA).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoTableModel.ATESTADO).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoTableModel.DATAOBITO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoTableModel.CAUSA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}


	
	
}