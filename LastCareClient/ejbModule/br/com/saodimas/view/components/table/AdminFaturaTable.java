package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.AdminFaturaTableModel;


/**
 * Classe que define o painel que contém o JTable de serviços.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")

public class AdminFaturaTable extends JTable{
	public AdminFaturaTable() {
		initialize();
	}
	
	public void initialize(){
		AdminFaturaTableModel model = new AdminFaturaTableModel();
		this.setModel(model);
		model.loadData(null);

		this.getColumnModel().getColumn(AdminFaturaTableModel.NUMAPOLICE).setMinWidth(3);
		this.getColumnModel().getColumn(AdminFaturaTableModel.NUMAPOLICE).setMaxWidth(75);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.NUMFATURA).setMinWidth(30);
		this.getColumnModel().getColumn(AdminFaturaTableModel.NUMFATURA).setMaxWidth(75);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.TITULAR).setMinWidth(120);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.VALOR).setMinWidth(50);
		this.getColumnModel().getColumn(AdminFaturaTableModel.VALOR).setMaxWidth(75);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.VALOR_PARCEIRO).setMinWidth(50);
		this.getColumnModel().getColumn(AdminFaturaTableModel.VALOR_PARCEIRO).setMaxWidth(90);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.DESCONTO).setMinWidth(50);
		this.getColumnModel().getColumn(AdminFaturaTableModel.DESCONTO).setMaxWidth(75);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.MULTA).setMinWidth(50);
		this.getColumnModel().getColumn(AdminFaturaTableModel.MULTA).setMaxWidth(75);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.TOTAL).setMinWidth(50);
		this.getColumnModel().getColumn(AdminFaturaTableModel.TOTAL).setMaxWidth(75);

		this.getColumnModel().getColumn(AdminFaturaTableModel.PAGAMENTO).setMinWidth(80);
		this.getColumnModel().getColumn(AdminFaturaTableModel.PAGAMENTO).setMaxWidth(130);

		this.getColumnModel().getColumn(AdminFaturaTableModel.VENCIMENTO).setMinWidth(80);
		this.getColumnModel().getColumn(AdminFaturaTableModel.VENCIMENTO).setMaxWidth(130);

		this.getColumnModel().getColumn(AdminFaturaTableModel.DATA_BAIXA).setMinWidth(80);
		this.getColumnModel().getColumn(AdminFaturaTableModel.DATA_BAIXA).setMaxWidth(110);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.PARCEIRO).setMinWidth(75);
		//this.getColumnModel().getColumn(AdminFaturaTableModel.PARCEIRO).setMaxWidth(75);
		
		this.getColumnModel().getColumn(AdminFaturaTableModel.QUITADO_POR).setMinWidth(75);
		this.getColumnModel().getColumn(AdminFaturaTableModel.QUITADO_POR).setMaxWidth(75);
		

		this.getColumnModel().getColumn(AdminFaturaTableModel.VENCIMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.NUMAPOLICE).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.NUMFATURA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.DESCONTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.MULTA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.TOTAL).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.PAGAMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.PARCEIRO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.QUITADO_POR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.VALOR_PARCEIRO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.DATA_BAIXA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(AdminFaturaTableModel.TITULAR).setCellRenderer(new DefaultTableCellRenderer());

		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.VENCIMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.NUMAPOLICE).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.NUMFATURA).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.TITULAR).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.VALOR).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.DESCONTO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.MULTA).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.TOTAL).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.PAGAMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.DATA_BAIXA).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.VALOR_PARCEIRO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.PARCEIRO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(AdminFaturaTableModel.QUITADO_POR).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);

		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}