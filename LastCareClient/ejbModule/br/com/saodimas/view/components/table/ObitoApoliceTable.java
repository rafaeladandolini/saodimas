package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ObitoApoliceTableModel;


/**
 * Classe que define o painel que contém o JTable de apólices.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ObitoApoliceTable extends JTable {
	public ObitoApoliceTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ObitoApoliceTableModel model = new ObitoApoliceTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(ObitoApoliceTableModel.CONTRATO).setMinWidth(110);
		
		this.getColumnModel().getColumn(ObitoApoliceTableModel.ASSOCIADO).setMinWidth(100);
		this.getColumnModel().getColumn(ObitoApoliceTableModel.ASSOCIADO).setMaxWidth(100);
		
		this.getColumnModel().getColumn(ObitoApoliceTableModel.DOCUMENTO).setMinWidth(100);
		this.getColumnModel().getColumn(ObitoApoliceTableModel.DOCUMENTO).setMaxWidth(100);
		
		this.getColumnModel().getColumn(ObitoApoliceTableModel.CONTRATO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoApoliceTableModel.ASSOCIADO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoApoliceTableModel.DOCUMENTO).setCellRenderer(new DefaultTableCellRenderer());

		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoApoliceTableModel.CONTRATO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoApoliceTableModel.ASSOCIADO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoApoliceTableModel.DOCUMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}