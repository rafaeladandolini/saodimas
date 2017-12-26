package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ObitoParticularConsultaTableModel;


/**
 * Classe que define o painel que contém o JTable de obitos.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ObitoParticularConsultaTable extends JTable {
	public ObitoParticularConsultaTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ObitoParticularConsultaTableModel model = new ObitoParticularConsultaTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.TIPO).setMinWidth(40);
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.TIPO).setMaxWidth(120);
		
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.NOME).setMinWidth(100);
		
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.ATESTADO).setMinWidth(120);
		//this.getColumnModel().getColumn(ObitoConsultaTableModel.ATESTADO).setMaxWidth(120);
		
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.DATAOBITO).setMinWidth(100);
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.DATAOBITO).setMaxWidth(100);

		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.CAUSA).setMinWidth(100);
		//this.getColumnModel().getColumn(ObitoConsultaTableModel.CAUSA).setMaxWidth(120);

		
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.ATESTADO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.DATAOBITO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.CAUSA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.CIDADE).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.ATESTADO).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.DATAOBITO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.CIDADE).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoParticularConsultaTableModel.CAUSA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}