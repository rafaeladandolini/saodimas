package br.com.saodimas.view.components.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.view.components.table.model.ParcelaTableModel;


/**
 * Classe que define o painel que contém o JTable de serviços.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")

public class ParcelaTable extends JTable{
	//private JTable table;
	
	public ParcelaTable() {
		initialize();
	}
	
	public void initialize(){
				
		ParcelaTableModel model = new ParcelaTableModel();
		this.setModel(model);
		model.loadData(null);

		//TableColumn tc = this.getColumnModel().getColumn(ParcelaTableModel.SELECAO);
				
		//tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
		
		this.getColumnModel().getColumn(ParcelaTableModel.SELECAO).setMinWidth(25);
		this.getColumnModel().getColumn(ParcelaTableModel.SELECAO).setMaxWidth(25);
		this.getColumnModel().getColumn(ParcelaTableModel.VENCIMENTO).setMinWidth(100);
		this.getColumnModel().getColumn(ParcelaTableModel.VALOR).setMinWidth(90);
	
		
		this.getColumnModel().getColumn(ParcelaTableModel.VENCIMENTO).setCellRenderer(new DefaultTableCellRenderer());
		this.getColumnModel().getColumn(ParcelaTableModel.VALOR).setCellRenderer(new DefaultTableCellRenderer());
	
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ParcelaTableModel.VENCIMENTO).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		((DefaultTableCellRenderer)this.getColumnModel().getColumn(ParcelaTableModel.VALOR).getCellRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		

		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setDragEnabled(false);
		this.setRowHeight(22);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	/*
	private class MyItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if (source instanceof AbstractButton == false) return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for(int i = 0; i < table.getRowCount(); i++)
			{
				Object obj = ((ParcelaTableModel)table.getModel()).getSelectedValue(i); 
				//if(((ParcelaVO)obj).getDataVencimento() != null)
				((ParcelaTableModel)table.getModel()).setValueAt(new Boolean(checked), i, ParcelaTableModel.SELECAO);
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
		
	}/*/

	
}

