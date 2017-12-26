package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.RelacaoTitularTableModel;


/**
 * Classe que define o painel que contém o JTable relacoes com o titular.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class RelacaoTitularTable extends JTable {
	public RelacaoTitularTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		RelacaoTitularTableModel model = new RelacaoTitularTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(RelacaoTitularTableModel.NOME_RELACAO).setMinWidth(110);
		
		this.getColumnModel().getColumn(RelacaoTitularTableModel.TIPO_RELACAO).setMinWidth(75);
		this.getColumnModel().getColumn(RelacaoTitularTableModel.TIPO_RELACAO).setMaxWidth(75);
		
		this.getColumnModel().getColumn(RelacaoTitularTableModel.STATUS).setMinWidth(90);
		this.getColumnModel().getColumn(RelacaoTitularTableModel.STATUS).setMaxWidth(90);
		
		this.getColumnModel().getColumn(RelacaoTitularTableModel.NOME_RELACAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(RelacaoTitularTableModel.TIPO_RELACAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(RelacaoTitularTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(RelacaoTitularTableModel.NOME_RELACAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(RelacaoTitularTableModel.TIPO_RELACAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(RelacaoTitularTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
