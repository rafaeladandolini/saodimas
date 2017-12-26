package br.com.saodimas.view.components.table.renderer;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * Classe que define a aparência de botão para o editor de uma JTable.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class CheckRenderer extends JCheckBox implements TableCellRenderer, TableCellEditor {
	public CheckRenderer(String tooltipText) {
		setToolTipText(tooltipText);
		setOpaque(false);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setSelected(isSelected);
		repaint();
		return this;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		setSelected(isSelected);
		repaint();
		System.out.println("entrei no editor do check");
		return this;
	}

	public boolean isCellEditable(EventObject e) {
		return true;
	}

	public void cancelCellEditing() {
	}

	public boolean stopCellEditing() {
		return true;
	}
	
	public Object getCellEditorValue() {
		return new Boolean(isSelected());
	}
	
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}
	
	public void addCellEditorListener(CellEditorListener l) {
	}
	
	public void removeCellEditorListener(CellEditorListener l) {
	}
}