package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.BancoTableModel;


/**
 * Classe que define o painel que contém o JTable de cidades.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class BancoTable extends JTable {
	public BancoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		BancoTableModel model = new BancoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(BancoTableModel.CODIGO_BANCO).setMinWidth(110);
		
		this.getColumnModel().getColumn(BancoTableModel.NOME_BANCO).setMinWidth(75);
		
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(BancoTableModel.CODIGO_BANCO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(BancoTableModel.NOME_BANCO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
