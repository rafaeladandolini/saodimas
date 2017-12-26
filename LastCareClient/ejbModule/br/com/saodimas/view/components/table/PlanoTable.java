package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.PlanoTableModel;


/**
 * Classe que define o painel que contém o JTable de planos.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class PlanoTable extends JTable {
	public PlanoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		PlanoTableModel model = new PlanoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(PlanoTableModel.DESCRICAO).setMinWidth(110);
		
		this.getColumnModel().getColumn(PlanoTableModel.TIPO).setMinWidth(75);
		this.getColumnModel().getColumn(PlanoTableModel.TIPO).setMaxWidth(75);
		
		this.getColumnModel().getColumn(PlanoTableModel.CARENCIA).setMinWidth(100);
		this.getColumnModel().getColumn(PlanoTableModel.CARENCIA).setMaxWidth(100);
		
		this.getColumnModel().getColumn(PlanoTableModel.ASSOCIADOS).setMinWidth(100);
		this.getColumnModel().getColumn(PlanoTableModel.ASSOCIADOS).setMaxWidth(100);
		
		this.getColumnModel().getColumn(PlanoTableModel.ASSOCIADOS_EXTRA).setMinWidth(100);
		this.getColumnModel().getColumn(PlanoTableModel.ASSOCIADOS_EXTRA).setMaxWidth(100);
		
		this.getColumnModel().getColumn(PlanoTableModel.MENSALIDADE).setMinWidth(100);
		this.getColumnModel().getColumn(PlanoTableModel.MENSALIDADE).setMaxWidth(100);
		
		this.getColumnModel().getColumn(PlanoTableModel.STATUS).setMinWidth(90);
		this.getColumnModel().getColumn(PlanoTableModel.STATUS).setMaxWidth(90);
		
		this.getColumnModel().getColumn(PlanoTableModel.DESCRICAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(PlanoTableModel.TIPO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(PlanoTableModel.CARENCIA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(PlanoTableModel.ASSOCIADOS).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(PlanoTableModel.ASSOCIADOS_EXTRA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(PlanoTableModel.MENSALIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(PlanoTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoTableModel.DESCRICAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoTableModel.TIPO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoTableModel.CARENCIA).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoTableModel.ASSOCIADOS).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoTableModel.ASSOCIADOS_EXTRA).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoTableModel.MENSALIDADE).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
