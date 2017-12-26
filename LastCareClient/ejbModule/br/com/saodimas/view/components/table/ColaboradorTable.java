package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ColaboradorTableModel;


/**
 * Classe que define o painel que contém o JTable de colaboradores.
 * @author daniel/rafaela
 *
 */

public class ColaboradorTable extends JTable{

	private static final long serialVersionUID = -5292359318052529850L;

	public ColaboradorTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ColaboradorTableModel model = new ColaboradorTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(ColaboradorTableModel.LOGIN).setMinWidth(90);
		this.getColumnModel().getColumn(ColaboradorTableModel.LOGIN).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ColaboradorTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(ColaboradorTableModel.TIPO).setMinWidth(90);
		this.getColumnModel().getColumn(ColaboradorTableModel.TIPO).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ColaboradorTableModel.STATUS).setMinWidth(90);
		this.getColumnModel().getColumn(ColaboradorTableModel.STATUS).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ColaboradorTableModel.LOGIN).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ColaboradorTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ColaboradorTableModel.TIPO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ColaboradorTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ColaboradorTableModel.LOGIN).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ColaboradorTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ColaboradorTableModel.TIPO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ColaboradorTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
