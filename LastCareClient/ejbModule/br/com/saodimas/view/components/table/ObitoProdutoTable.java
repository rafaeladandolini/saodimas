package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ObitoProdutoTableModel;



@SuppressWarnings("serial")
public class ObitoProdutoTable extends JTable {
	public ObitoProdutoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ObitoProdutoTableModel model = new ObitoProdutoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(ObitoProdutoTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(ObitoProdutoTableModel.QUANTIDADE).setMinWidth(70);
		this.getColumnModel().getColumn(ObitoProdutoTableModel.QUANTIDADE).setMaxWidth(100);
		
		//this.getColumnModel().getColumn(ObitoProdutoTableModel.REFERENCIA).setMinWidth(100);
		//this.getColumnModel().getColumn(ObitoProdutoTableModel.REFERENCIA).setMaxWidth(100);

		this.getColumnModel().getColumn(ObitoProdutoTableModel.VALOR).setMinWidth(70);
		this.getColumnModel().getColumn(ObitoProdutoTableModel.VALOR).setMaxWidth(100);
		
		this.getColumnModel().getColumn(ObitoProdutoTableModel.TOTAL).setMinWidth(70);
		this.getColumnModel().getColumn(ObitoProdutoTableModel.TOTAL).setMaxWidth(100);
		
		this.getColumnModel().getColumn(ObitoProdutoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoProdutoTableModel.QUANTIDADE).setCellRenderer(new DefaultTableCellRenderer());
		//this.getColumnModel().getColumn(ObitoProdutoTableModel.REFERENCIA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoProdutoTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoProdutoTableModel.TOTAL).setCellRenderer(new DefaultTableCellRenderer());
		
			
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoProdutoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoProdutoTableModel.REFERENCIA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
