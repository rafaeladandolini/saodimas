package br.com.saodimas.view.components.table.editor;

import java.awt.Component;
import java.awt.Dimension;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 * Classe que define um editor em forma de botão para uma célula de um JTable.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class BotaoEditor extends JLabel implements TableCellEditor {
	//private JInternalFrame iframeEditor;
	
	public BotaoEditor(JInternalFrame iframeEditor) {
		Dimension dim = new Dimension(20, 20);
		setIcon(new ImageIcon("imagens/edit.gif"));
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		setDoubleBuffered(true);
		//this.iframeEditor = iframeEditor;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//		Object objSelecionado = table.getValueAt(row, column);
//		if (iframeEditor.getClass().isInstance(new DependenteIFrame())){
//			((DependenteIFrame)iframeEditor).setDependente((Dependente)objSelecionado);
//			((DependenteIFrame)iframeEditor).setVisible(true);
//		}
		
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
		return null;
	}
	
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}
	
	public void addCellEditorListener(CellEditorListener l) {
	}
	
	public void removeCellEditorListener(CellEditorListener l) {
	}

}
