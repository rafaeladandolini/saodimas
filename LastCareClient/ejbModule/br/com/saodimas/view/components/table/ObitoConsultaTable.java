package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ObitoConsultaTableModel;


/**
 * Classe que define o painel que contém o JTable de obitos.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ObitoConsultaTable extends JTable {
	public ObitoConsultaTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ObitoConsultaTableModel model = new ObitoConsultaTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(ObitoConsultaTableModel.NUMERO_ATENDIMENTO).setMinWidth(40);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.NUMERO_ATENDIMENTO).setMaxWidth(120);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.TIPO_ATENDIMENTO).setMinWidth(160);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.TIPO_ATENDIMENTO).setMaxWidth(160);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.APOLICE).setMinWidth(40);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.APOLICE).setMaxWidth(120);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.NOME).setMinWidth(100);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.DATAOBITO).setMinWidth(100);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.DATAOBITO).setMaxWidth(100);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.CIDADE).setMinWidth(300);
		this.getColumnModel().getColumn(ObitoConsultaTableModel.CIDADE).setMaxWidth(300);

		
		this.getColumnModel().getColumn(ObitoConsultaTableModel.NUMERO_ATENDIMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoConsultaTableModel.APOLICE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoConsultaTableModel.TIPO_ATENDIMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoConsultaTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoConsultaTableModel.DATAOBITO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoConsultaTableModel.CIDADE).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoConsultaTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoConsultaTableModel.DATAOBITO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoConsultaTableModel.CIDADE).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoConsultaTableModel.TIPO_ATENDIMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoConsultaTableModel.NUMERO_ATENDIMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}