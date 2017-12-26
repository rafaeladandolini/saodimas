package br.com.saodimas.view.components.table.renderer;

import java.awt.Component;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import br.com.saodimas.model.beans.ProdutoPlanoVO;
import br.com.saodimas.model.beans.ServicoPlanoVO;

/**
 * Classe que define a aparência de spinner para o editor de uma JTable.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class SpinnerRenderer extends JSpinner implements TableCellRenderer{
	public SpinnerRenderer() {
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		int valor = 1;
		
		if (value.getClass() == ProdutoPlanoVO.class) valor = ((ProdutoPlanoVO)value).getQuantidade();
		else if (value.getClass() == ServicoPlanoVO.class) valor = ((ServicoPlanoVO)value).getQuantidade();
		
		getModel().setValue(valor);
		return this;
	}
}