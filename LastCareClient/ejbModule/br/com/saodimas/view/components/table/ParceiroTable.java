package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ParceiroTableModel;


/**
 * Classe que define o painel que contém o JTable de parceiros.
 * @author daniel/rafaela
 *
 */

public class ParceiroTable extends JTable{

	private static final long serialVersionUID = -5292359318052529850L;

	public ParceiroTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ParceiroTableModel model = new ParceiroTableModel();
		this.setModel(model);
		model.loadData(null);
		
		
		this.getColumnModel().getColumn(ParceiroTableModel.DESCRICAO).setMinWidth(110);
		
		this.getColumnModel().getColumn(ParceiroTableModel.RESPONSAVEL).setMinWidth(110);
				
		this.getColumnModel().getColumn(ParceiroTableModel.TELEFONE).setMinWidth(90);
		this.getColumnModel().getColumn(ParceiroTableModel.TELEFONE).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ParceiroTableModel.ID_CIDADE).setMinWidth(90);
				
		this.getColumnModel().getColumn(ParceiroTableModel.STATUS).setMinWidth(90);
		this.getColumnModel().getColumn(ParceiroTableModel.STATUS).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ParceiroTableModel.DESCRICAO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ParceiroTableModel.RESPONSAVEL).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ParceiroTableModel.TELEFONE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ParceiroTableModel.ID_CIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ParceiroTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ParceiroTableModel.DESCRICAO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ParceiroTableModel.RESPONSAVEL).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ParceiroTableModel.TELEFONE).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ParceiroTableModel.ID_CIDADE).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ParceiroTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
