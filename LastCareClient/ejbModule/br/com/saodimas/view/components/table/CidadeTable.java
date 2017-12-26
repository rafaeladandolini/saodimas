package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.CidadeTableModel;


/**
 * Classe que define o painel que contém o JTable de cidades.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class CidadeTable extends JTable {
	public CidadeTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		CidadeTableModel model = new CidadeTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(CidadeTableModel.NOME_CIDADE).setMinWidth(110);
		
		this.getColumnModel().getColumn(CidadeTableModel.ESTADO).setMinWidth(75);
		this.getColumnModel().getColumn(CidadeTableModel.ESTADO).setMaxWidth(75);
		
		this.getColumnModel().getColumn(CidadeTableModel.STATUS).setMinWidth(90);
		this.getColumnModel().getColumn(CidadeTableModel.STATUS).setMaxWidth(90);
		
		this.getColumnModel().getColumn(CidadeTableModel.NOME_CIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(CidadeTableModel.ESTADO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(CidadeTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(CidadeTableModel.NOME_CIDADE).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(CidadeTableModel.ESTADO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(CidadeTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
