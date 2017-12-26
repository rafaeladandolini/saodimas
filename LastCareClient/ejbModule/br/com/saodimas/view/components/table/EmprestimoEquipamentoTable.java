package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.EmprestimoEquipamentoTableModel;


public class EmprestimoEquipamentoTable extends JTable{

	private static final long serialVersionUID = -5292359318052529850L;

	public EmprestimoEquipamentoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		EmprestimoEquipamentoTableModel model = new EmprestimoEquipamentoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.ID_APOLICE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.TITULAR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.ID_EQUIPAMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.DATA_DEVOLUCAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.DATA_EMPRESTIMO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.DATA_PREVISTA_DEVOLUCAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.OBSERVACAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.CIDADE).setCellRenderer(new DefaultTableCellRenderer());
		
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.ID_APOLICE).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.TITULAR).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.ID_EQUIPAMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.DATA_DEVOLUCAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.DATA_EMPRESTIMO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.DATA_PREVISTA_DEVOLUCAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.OBSERVACAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoTableModel.CIDADE).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
