package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ApoliceTableModel;


/**
 * Classe que define o painel que contém o JTable de apólices.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ApoliceTable extends JTable{
	public ApoliceTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		ApoliceTableModel model = new ApoliceTableModel();
		this.setModel(model);
		model.loadData(null);
		

		//this.getColumnModel().getColumn(ApoliceTableModel.NUMERO).setMinWidth(120);
		//this.getColumnModel().getColumn(ApoliceTableModel.NUMERO).setMaxWidth(90);
		
				
		this.getColumnModel().getColumn(ApoliceTableModel.CONTRATO).setMinWidth(50);
		this.getColumnModel().getColumn(ApoliceTableModel.CONTRATO).setMaxWidth(50);
		
		this.getColumnModel().getColumn(ApoliceTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(ApoliceTableModel.ENDERECO).setMinWidth(110);
		
		this.getColumnModel().getColumn(ApoliceTableModel.CIDADE).setMinWidth(110);
		
		this.getColumnModel().getColumn(ApoliceTableModel.PLANO).setMinWidth(90);
		this.getColumnModel().getColumn(ApoliceTableModel.PLANO).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ApoliceTableModel.STATUS).setMinWidth(90);
		this.getColumnModel().getColumn(ApoliceTableModel.STATUS).setMaxWidth(90);
		
		this.getColumnModel().getColumn(ApoliceTableModel.DATA).setMinWidth(80);
		this.getColumnModel().getColumn(ApoliceTableModel.DATA).setMaxWidth(80);
				
		
		this.getColumnModel().getColumn(ApoliceTableModel.CONTRATO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ApoliceTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ApoliceTableModel.PLANO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ApoliceTableModel.DATA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ApoliceTableModel.ENDERECO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ApoliceTableModel.CIDADE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ApoliceTableModel.STATUS).setCellRenderer(new DefaultTableCellRenderer());
		
		//((DefaultTableCellRenderer)this.getColumnModel().getColumn(ApoliceTableModel.NUMERO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ApoliceTableModel.CONTRATO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ApoliceTableModel.NOME).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ApoliceTableModel.PLANO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ApoliceTableModel.DATA).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ApoliceTableModel.ENDERECO).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ApoliceTableModel.CIDADE).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ApoliceTableModel.STATUS).getCellRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}
}
