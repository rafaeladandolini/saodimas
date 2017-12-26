package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.editor.SpinnerEditorServico;
import br.com.saodimas.view.components.table.model.PlanoServicoTableModel;
import br.com.saodimas.view.components.table.renderer.SpinnerRenderer;


/**
 * Classe que define o painel que contém o JTable de serviços.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class PlanoServicoTable extends JTable {
	public PlanoServicoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		PlanoServicoTableModel model = new PlanoServicoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(PlanoServicoTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(PlanoServicoTableModel.QUANTIDADE).setMinWidth(100);
		this.getColumnModel().getColumn(PlanoServicoTableModel.QUANTIDADE).setMaxWidth(100);
		
		this.getColumnModel().getColumn(PlanoServicoTableModel.REFERENCIA).setMinWidth(100);
		this.getColumnModel().getColumn(PlanoServicoTableModel.REFERENCIA).setMaxWidth(100);
		
		this.getColumnModel().getColumn(PlanoServicoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(PlanoServicoTableModel.QUANTIDADE).setCellRenderer(new SpinnerRenderer());
		this.getColumnModel().getColumn(PlanoServicoTableModel.REFERENCIA).setCellRenderer(new DefaultTableCellRenderer());

		this.getColumnModel().getColumn(PlanoServicoTableModel.QUANTIDADE).setCellEditor(new SpinnerEditorServico());

		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoServicoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(PlanoServicoTableModel.REFERENCIA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
