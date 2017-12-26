package br.com.saodimas.view.components.table;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.view.components.table.model.FaturaTableModel;
import br.com.saodimas.view.components.table.renderer.JTableFaturasRenderer;


/**
 * Classe que define o painel que cont�m o JTable de servi�os.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")

public class FaturaTable extends JTable{
	private JTable table;
	
	public FaturaTable() {
		initialize();
	}
	
	public void initialize(){
		table = this;
		
		FaturaTableModel model = new FaturaTableModel();
		this.setModel(model);
		model.loadData(null);

		TableColumn tc = this.getColumnModel().getColumn(FaturaTableModel.SELECAO);
				
		tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
		
		this.getColumnModel().getColumn(FaturaTableModel.SELECAO).setMinWidth(25);
		this.getColumnModel().getColumn(FaturaTableModel.SELECAO).setMaxWidth(25);
		
		this.getColumnModel().getColumn(FaturaTableModel.SEM_CARNE).setMinWidth(40);
		this.getColumnModel().getColumn(FaturaTableModel.SEM_CARNE).setMaxWidth(60);

		this.getColumnModel().getColumn(FaturaTableModel.NUMFATURA).setMinWidth(30);
		//this.getColumnModel().getColumn(FaturaTableModel.NUMFATURA).setMaxWidth(30);

		this.getColumnModel().getColumn(FaturaTableModel.VENCIMENTO).setMinWidth(100);
		//this.getColumnModel().getColumn(FaturaTableModel.VENCIMENTO).setMaxWidth(100);
		
		this.getColumnModel().getColumn(FaturaTableModel.PAGAMENTO).setMinWidth(100);
		//this.getColumnModel().getColumn(FaturaTableModel.PAGAMENTO).setMaxWidth(100);
		
		this.getColumnModel().getColumn(FaturaTableModel.VALOR).setMinWidth(90);
		//this.getColumnModel().getColumn(FaturaTableModel.VALOR).setMaxWidth(90);
		
		this.getColumnModel().getColumn(FaturaTableModel.TOTAL).setMinWidth(90);
		//this.getColumnModel().getColumn(FaturaTableModel.TOTAL).setMaxWidth(90);
		
				
		
		this.getColumnModel().getColumn(FaturaTableModel.QUITADO_POR).setMinWidth(140);
		this.getColumnModel().getColumn(FaturaTableModel.QUITADO_POR).setMaxWidth(140);
		
		this.getColumnModel().getColumn(FaturaTableModel.IS_PARCEIRO).setMinWidth(140);
		this.getColumnModel().getColumn(FaturaTableModel.IS_PARCEIRO).setMaxWidth(140);
	
		this.getColumnModel().getColumn(FaturaTableModel.DATA_BAIXA).setMinWidth(30);
		
		this.getColumnModel().getColumn(FaturaTableModel.NUMFATURA).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaTableModel.VENCIMENTO).setCellRenderer(new DefaultTableCellRenderer());
		
		
		
		this.getColumnModel().getColumn(FaturaTableModel.PAGAMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaTableModel.TOTAL).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaTableModel.QUITADO_POR).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaTableModel.IS_PARCEIRO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(FaturaTableModel.DATA_BAIXA).setCellRenderer(new DefaultTableCellRenderer());
		//this.getColumnModel().getColumn(FaturaTableModel.SEM_CARNE).setCellRenderer(new DefaultTableCellRenderer());

		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaTableModel.NUMFATURA).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaTableModel.VENCIMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaTableModel.PAGAMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaTableModel.VALOR).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaTableModel.TOTAL).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaTableModel.QUITADO_POR).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaTableModel.IS_PARCEIRO).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(FaturaTableModel.DATA_BAIXA).getCellRenderer()).setHorizontalAlignment(JLabel.CENTER);

		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private class MyItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if (source instanceof AbstractButton == false) return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for(int i = 0; i < table.getRowCount(); i++)
			{
				Object obj = ((FaturaTableModel)table.getModel()).getSelectedValue(i); 
				if(((FaturaVO)obj).getDataPagamento() == null)
					((FaturaTableModel)table.getModel()).setValueAt(new Boolean(checked), i, FaturaTableModel.SELECAO);
			}
		}
		
	}

	private class CheckBoxHeader extends JCheckBox implements TableCellRenderer, MouseListener {
		protected CheckBoxHeader rendererComponent;
		protected int column;
		protected boolean mousePressed = false;
		
		public CheckBoxHeader(ItemListener itemListener) {
			rendererComponent = this;
			rendererComponent.setOpaque(false);
			rendererComponent.addItemListener(itemListener);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (table != null) {
				JTableHeader header = table.getTableHeader();
				if (header != null) {
					rendererComponent.setForeground(header.getForeground());
					rendererComponent.setBackground(header.getBackground());
					rendererComponent.setFont(header.getFont());
					header.addMouseListener(rendererComponent);
				}
			}		
			setColumn(column);
			rendererComponent.setText("");
			rendererComponent.setToolTipText("Selecionar todas as faturas");
			//setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			return rendererComponent;
		}
		protected void setColumn(int column) {
			this.column = column;
		}
		@SuppressWarnings("unused")
		public int getColumn() {
			return column;
		}
		protected void handleClickEvent(MouseEvent e) {
			if (mousePressed) {
				mousePressed=false;
				JTableHeader header = (JTableHeader)(e.getSource());
				JTable tableView = header.getTable();
				TableColumnModel columnModel = tableView.getColumnModel();
				int viewColumn = columnModel.getColumnIndexAtX(e.getX());
				int column = tableView.convertColumnIndexToModel(viewColumn);

				if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
					doClick();
				}
			}
		}
		public void mouseClicked(MouseEvent e) {
			handleClickEvent(e);
			((JTableHeader)e.getSource()).repaint();
		}
		public void mousePressed(MouseEvent e) {
			mousePressed = true;
		}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
	}

	
}

