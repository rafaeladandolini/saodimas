package br.com.saodimas.view.components.table.editor;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;

import br.com.saodimas.model.beans.ProdutoPlanoVO;

/**
 * Classe que define um editor como spinner para uma célula de um JTable.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class SpinnerEditorProduto extends AbstractCellEditor implements TableCellEditor, ChangeListener {
	final JSpinner spinner = new JSpinner();
	private ProdutoPlanoVO produto;

	public SpinnerEditorProduto() {
		spinner.addChangeListener(this);
		spinner.setModel(new SpinnerNumberModel(1, 1, 99999, 1));	
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
		produto = (ProdutoPlanoVO)value;
		spinner.setValue(produto.getQuantidade());
		return spinner;
	}

	public boolean isCellEditable(EventObject evt) {
		if (evt instanceof MouseEvent) {
			return ((MouseEvent)evt).getClickCount() >= 1;
		}
		return true;
	}

	public Object getCellEditorValue() {
		return spinner.getValue();
	}

	public void stateChanged(ChangeEvent arg0) {
		if (produto != null){
			produto.setQuantidade((Integer)spinner.getValue());
		}
	}
}

