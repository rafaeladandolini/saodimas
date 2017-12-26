package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.VendaServicoTableModel;


/**
 * Classe que define o painel que contém o JTable de serviços.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class VendaServicoTable extends JTable {
	public VendaServicoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		VendaServicoTableModel model = new VendaServicoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(VendaServicoTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(VendaServicoTableModel.QUANTIDADE).setMinWidth(70);
		this.getColumnModel().getColumn(VendaServicoTableModel.QUANTIDADE).setMaxWidth(100);
		
		this.getColumnModel().getColumn(VendaServicoTableModel.REFERENCIA).setMinWidth(100);
		this.getColumnModel().getColumn(VendaServicoTableModel.REFERENCIA).setMaxWidth(100);
		
		this.getColumnModel().getColumn(VendaServicoTableModel.VALOR).setMinWidth(70);
		this.getColumnModel().getColumn(VendaServicoTableModel.VALOR).setMaxWidth(100);
		
		this.getColumnModel().getColumn(VendaServicoTableModel.TOTAL).setMinWidth(70);
		this.getColumnModel().getColumn(VendaServicoTableModel.TOTAL).setMaxWidth(100);
		
		this.getColumnModel().getColumn(VendaServicoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaServicoTableModel.QUANTIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaServicoTableModel.REFERENCIA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaServicoTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(VendaServicoTableModel.TOTAL).setCellRenderer(new DefaultTableCellRenderer());

		((DefaultTableCellRenderer)this.getColumnModel().getColumn(VendaServicoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(VendaServicoTableModel.REFERENCIA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}