package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.editor.SpinnerEditorProduto;
import br.com.saodimas.view.components.table.model.PlanoProdutoTableModel;
import br.com.saodimas.view.components.table.renderer.SpinnerRenderer;


/**
 * Classe que define o painel que contém o JTable de produtos.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class PlanoProdutoTable extends JTable {
	public PlanoProdutoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		PlanoProdutoTableModel model = new PlanoProdutoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(PlanoProdutoTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(PlanoProdutoTableModel.QUANTIDADE).setMinWidth(100);
		this.getColumnModel().getColumn(PlanoProdutoTableModel.QUANTIDADE).setMaxWidth(100);
		
		this.getColumnModel().getColumn(PlanoProdutoTableModel.REFERENCIA).setMinWidth(100);
		this.getColumnModel().getColumn(PlanoProdutoTableModel.REFERENCIA).setMaxWidth(100);
		
		this.getColumnModel().getColumn(PlanoProdutoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(PlanoProdutoTableModel.QUANTIDADE).setCellRenderer(new SpinnerRenderer());
		this.getColumnModel().getColumn(PlanoProdutoTableModel.REFERENCIA).setCellRenderer(new DefaultTableCellRenderer());

		this.getColumnModel().getColumn(PlanoProdutoTableModel.QUANTIDADE).setCellEditor(new SpinnerEditorProduto());

		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoProdutoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoProdutoTableModel.REFERENCIA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
