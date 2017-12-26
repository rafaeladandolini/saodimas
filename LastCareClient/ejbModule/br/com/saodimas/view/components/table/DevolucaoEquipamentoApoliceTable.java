package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.DevolucaoEquipamentoApoliceTableModel;


public class DevolucaoEquipamentoApoliceTable extends JTable{

	private static final long serialVersionUID = -5292359318052529850L;

	public DevolucaoEquipamentoApoliceTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		DevolucaoEquipamentoApoliceTableModel model = new DevolucaoEquipamentoApoliceTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.ID_EQUIPAMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_DEVOLUCAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_EMPRESTIMO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_PREVISTA_DEVOLUCAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.OBSERVACAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.EMPRESTADO_PARA).setCellRenderer(new DefaultTableCellRenderer());
		
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.ID_EQUIPAMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_DEVOLUCAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_EMPRESTIMO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_PREVISTA_DEVOLUCAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.OBSERVACAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.EMPRESTADO_PARA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_DEVOLUCAO).setMinWidth(90);
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_DEVOLUCAO).setMaxWidth(90);
		
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_EMPRESTIMO).setMinWidth(90);
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_EMPRESTIMO).setMaxWidth(90);
		
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_PREVISTA_DEVOLUCAO).setMinWidth(90);
		this.getColumnModel().getColumn(DevolucaoEquipamentoApoliceTableModel.DATA_PREVISTA_DEVOLUCAO).setMaxWidth(90);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
