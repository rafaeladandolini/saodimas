package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.FaturaApoliceTableModel;


/**
 * Classe que define o painel que contém o JTable de serviços.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")

public class FaturaApoliceTable extends JTable{
	public FaturaApoliceTable() {
		initialize();
	}
	
	public void initialize(){
		FaturaApoliceTableModel model = new FaturaApoliceTableModel();
		this.setModel(model);
		model.loadData(null);

		this.getColumnModel().getColumn(FaturaApoliceTableModel.NUMFATURA).setMinWidth(75);
		this.getColumnModel().getColumn(FaturaApoliceTableModel.NUMFATURA).setMaxWidth(75);

		this.getColumnModel().getColumn(FaturaApoliceTableModel.VALOR).setMinWidth(110);

		this.getColumnModel().getColumn(FaturaApoliceTableModel.VENCIMENTO).setMinWidth(75);
		this.getColumnModel().getColumn(FaturaApoliceTableModel.VENCIMENTO).setMaxWidth(75);

		this.getColumnModel().getColumn(FaturaApoliceTableModel.SITUACAO).setMinWidth(75);
		this.getColumnModel().getColumn(FaturaApoliceTableModel.SITUACAO).setMaxWidth(75);

		this.getColumnModel().getColumn(FaturaApoliceTableModel.NUMFATURA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaApoliceTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaApoliceTableModel.VENCIMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaApoliceTableModel.SITUACAO).setCellRenderer(new DefaultTableCellRenderer());
		
		this.getColumnModel().getColumn(FaturaApoliceTableModel.QUITADO_POR).setCellRenderer(new DefaultTableCellRenderer());

		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaApoliceTableModel.NUMFATURA).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaApoliceTableModel.VALOR).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaApoliceTableModel.VENCIMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaApoliceTableModel.SITUACAO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaApoliceTableModel.QUITADO_POR).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);

		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}