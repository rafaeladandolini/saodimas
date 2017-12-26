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

import br.com.saodimas.model.beans.ServicoPlanoVO;


/**
 * Classe que define um editor como spinner para uma célula de um JTable.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class SpinnerEditorServico extends AbstractCellEditor implements TableCellEditor, ChangeListener {
	final JSpinner spinner = new JSpinner();
	private ServicoPlanoVO servico;

	public SpinnerEditorServico() {
		spinner.addChangeListener(this);
		spinner.setModel(new SpinnerNumberModel(1, 1, 99999, 1));	
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
		servico = (ServicoPlanoVO)value;
		spinner.setValue(servico.getQuantidade());
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
		if (servico != null){
			servico.setQuantidade((Integer)spinner.getValue());
		}
	}
}

