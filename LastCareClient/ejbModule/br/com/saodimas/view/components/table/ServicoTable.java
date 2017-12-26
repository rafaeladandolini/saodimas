package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ServicoTableModel;


/**
 * Classe que define o painel que contém o JTable de serviços.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ServicoTable extends JTable {
	public ServicoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ServicoTableModel model = new ServicoTableModel();
		this.setModel(model);
		model.loadData(null);
				
		this.getColumnModel().getColumn(ServicoTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(ServicoTableModel.PRECO).setMinWidth(150);
		this.getColumnModel().getColumn(ServicoTableModel.PRECO).setMaxWidth(150);
		
		this.getColumnModel().getColumn(ServicoTableModel.STATUS).setMinWidth(90);
		this.getColumnModel().getColumn(ServicoTableModel.STATUS).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ServicoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ServicoTableModel.PRECO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ServicoTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ServicoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ServicoTableModel.PRECO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ServicoTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}