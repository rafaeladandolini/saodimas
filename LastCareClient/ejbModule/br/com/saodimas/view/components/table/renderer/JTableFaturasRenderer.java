package br.com.saodimas.view.components.table.renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import br.com.saodimas.model.beans.FaturaVO;

public class JTableFaturasRenderer extends JLabel implements TableCellRenderer{
	     
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			FaturaVO fat = (FaturaVO)value;              
            if(fat.getDataPagamento() == null && fat.getDataVencimento().before(new Date())){
                 setBackground(Color.RED);
            }else{
                 setBackground(Color.LIGHT_GRAY);
            }
            return this;
		}
	}
	
