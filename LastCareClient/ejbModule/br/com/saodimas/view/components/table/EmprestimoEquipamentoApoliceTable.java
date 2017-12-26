package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.EmprestimoEquipamentoApoliceTableModel;


public class EmprestimoEquipamentoApoliceTable extends JTable{

	private static final long serialVersionUID = -5292359318052529850L;

	public EmprestimoEquipamentoApoliceTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		EmprestimoEquipamentoApoliceTableModel model = new EmprestimoEquipamentoApoliceTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.ID_EQUIPAMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.DATA_EMPRESTIMO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.DATA_PREVISTA_DEVOLUCAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.OBSERVACAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.EMPRESTADO_PARA).setCellRenderer(new DefaultTableCellRenderer());
		
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.ID_EQUIPAMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.DATA_EMPRESTIMO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.DATA_PREVISTA_DEVOLUCAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.OBSERVACAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.EMPRESTADO_PARA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
			
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.DATA_EMPRESTIMO).setMinWidth(90);
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.DATA_EMPRESTIMO).setMaxWidth(90);
		
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.DATA_PREVISTA_DEVOLUCAO).setMinWidth(90);
		this.getColumnModel().getColumn(EmprestimoEquipamentoApoliceTableModel.DATA_PREVISTA_DEVOLUCAO).setMaxWidth(90);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
