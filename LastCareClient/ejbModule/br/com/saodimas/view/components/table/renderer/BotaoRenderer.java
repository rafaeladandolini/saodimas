package br.com.saodimas.view.components.table.renderer;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 * Classe que define a aparência de botão para o editor de uma JTable.
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class BotaoRenderer extends JButton implements TableCellRenderer {
	public BotaoRenderer(String imagem, Dimension dim) {
		setIcon(new ImageIcon(imagem));
		setBackground(null);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		return this;
	}
}
