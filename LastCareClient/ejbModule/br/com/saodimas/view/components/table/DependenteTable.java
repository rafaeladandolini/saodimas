package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.DependenteTableModel;


/**
 * Classe que define o painel que contém o JTable dos produtos disponíveis para compra.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class DependenteTable extends JTable{
	public DependenteTable() {
		initialize();
	}

	/**
	 * Instancia os componentes gráficos e os configura.
	 */
	private void initialize() {
		DependenteTableModel model = new DependenteTableModel();
		this.setModel(model);
		model.loadData(null);
				
		
		this.getColumnModel().getColumn(DependenteTableModel.NOME).setMinWidth(110);
		
		this.getColumnModel().getColumn(DependenteTableModel.DATA_NASC).setMaxWidth(80);
		this.getColumnModel().getColumn(DependenteTableModel.DATA_NASC).setMinWidth(80);
		
		this.getColumnModel().getColumn(DependenteTableModel.GENERO).setMaxWidth(60);
		this.getColumnModel().getColumn(DependenteTableModel.GENERO).setMinWidth(60);
		
		this.getColumnModel().getColumn(DependenteTableModel.PARENTESCO).setMaxWidth(80);
		this.getColumnModel().getColumn(DependenteTableModel.PARENTESCO).setMinWidth(80);
		
		this.getColumnModel().getColumn(DependenteTableModel.DATA_ADESAO).setMaxWidth(80);
		this.getColumnModel().getColumn(DependenteTableModel.DATA_ADESAO).setMinWidth(80);
		
		this.getColumnModel().getColumn(DependenteTableModel.CPF).setMaxWidth(100);
		this.getColumnModel().getColumn(DependenteTableModel.CPF).setMinWidth(100);
		
		this.getColumnModel().getColumn(DependenteTableModel.OK).setMaxWidth(80);
		this.getColumnModel().getColumn(DependenteTableModel.OK).setMinWidth(80);
		
		this.getColumnModel().getColumn(DependenteTableModel.CARTAO_NOVO).setMaxWidth(80);
		this.getColumnModel().getColumn(DependenteTableModel.CARTAO_NOVO).setMinWidth(80);
		
		//this.getColumnModel().getColumn(DependenteTableModel.OK).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DependenteTableModel.NOME).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DependenteTableModel.CPF).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DependenteTableModel.DATA_NASC).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DependenteTableModel.GENERO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(DependenteTableModel.PARENTESCO).setCellRenderer(new DefaultTableCellRenderer());
		
		this.getColumnModel().getColumn(DependenteTableModel.DATA_ADESAO).setCellRenderer(new DefaultTableCellRenderer());
		//this.getColumnModel().getColumn(DependenteTableModel.CARTAO_NOVO).setCellRenderer(new DefaultTableCellRenderer());
		
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DependenteTableModel.DATA_NASC).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DependenteTableModel.GENERO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(DependenteTableModel.DATA_ADESAO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
				
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	}
	
}