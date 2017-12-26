package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ObitoServicoTableModel;


/**
 * Classe que define o painel que contém o JTable de serviços.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ObitoServicoTable extends JTable {
	public ObitoServicoTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ObitoServicoTableModel model = new ObitoServicoTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(ObitoServicoTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(ObitoServicoTableModel.QUANTIDADE).setMinWidth(70);
		this.getColumnModel().getColumn(ObitoServicoTableModel.QUANTIDADE).setMaxWidth(100);
		
		this.getColumnModel().getColumn(ObitoServicoTableModel.REFERENCIA).setMinWidth(100);
		this.getColumnModel().getColumn(ObitoServicoTableModel.REFERENCIA).setMaxWidth(100);
		
		this.getColumnModel().getColumn(ObitoServicoTableModel.VALOR).setMinWidth(70);
		this.getColumnModel().getColumn(ObitoServicoTableModel.VALOR).setMaxWidth(100);
		
		this.getColumnModel().getColumn(ObitoServicoTableModel.TOTAL).setMinWidth(70);
		this.getColumnModel().getColumn(ObitoServicoTableModel.TOTAL).setMaxWidth(100);
		
		this.getColumnModel().getColumn(ObitoServicoTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoServicoTableModel.QUANTIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoServicoTableModel.REFERENCIA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoServicoTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ObitoServicoTableModel.TOTAL).setCellRenderer(new DefaultTableCellRenderer());

		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoServicoTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ObitoServicoTableModel.REFERENCIA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}