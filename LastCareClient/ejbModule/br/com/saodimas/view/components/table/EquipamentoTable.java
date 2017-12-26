package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.EquipamentoTableModel;


public class EquipamentoTable extends JTable{

	private static final long serialVersionUID = -5292359318052529850L;

	public EquipamentoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		EquipamentoTableModel model = new EquipamentoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		this.getColumnModel().getColumn(EquipamentoTableModel.DATA_AQUISICAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EquipamentoTableModel.DESCRICAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EquipamentoTableModel.MODELO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EquipamentoTableModel.QUANTIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EquipamentoTableModel.QTDE_DISPONIVEL).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EquipamentoTableModel.QTDE_EMPRESTADA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EquipamentoTableModel.OBSERVACAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EquipamentoTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(EquipamentoTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
		
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.DATA_AQUISICAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.DESCRICAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.MODELO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.QUANTIDADE).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.QTDE_DISPONIVEL).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.QTDE_EMPRESTADA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.OBSERVACAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(EquipamentoTableModel.VALOR).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
