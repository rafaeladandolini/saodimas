package br.com.saodimas.view.components.table.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.CompraVO;
import br.com.saodimas.model.beans.VendaVO;



@SuppressWarnings("serial")
public class VendaClienteTableModel extends AbstractTableModel{
	public static final int DATA           = 0;
	public static final int VENDEDOR       = 1;
	public static final int TIPO_PAGAMENTO = 2;
	public static final int PARCELAMENTO   = 3;
	public static final int VALOR          = 4;
	public static final int ACRESCIMO      = 5;
	public static final int DESCONTO       = 6;
	public static final int TOTAL          = 7;

		
	private String[] columnNames = {"Data Venda", "Vendedor", "Tipo Pagamento","Parcelamento", "Valor", "Acrescimo", "Desconto", "Total"};
	private Vector <VendaVO> dataVector;
	
	public VendaClienteTableModel() {
		dataVector = new Vector<VendaVO>();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return dataVector.size();
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	public Object getSelectedValue(int row){
		return dataVector.get(row);
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
			case DATA:{
			if(dataVector.get(row).getDataVenda()== null)
				return null;
				SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
				return sdff.format(dataVector.get(row).getDataVenda()) + "";
			}
			case VENDEDOR:        return dataVector.get(row).getColaborador().getNome();
			case PARCELAMENTO:      return dataVector.get(row).getParcelamento();
			case TIPO_PAGAMENTO:  return dataVector.get(row).getFormaPagamento();
			case VALOR:           return dataVector.get(row).getValor();
			case DESCONTO:        return dataVector.get(row).getDesconto();
			case ACRESCIMO:       return dataVector.get(row).getAcrescimo();
			case TOTAL:           return dataVector.get(row).getValor() + dataVector.get(row).getAcrescimo() - dataVector.get(row).getDesconto();
					
			default: return "";
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(VendaVO dep) {
    	dataVector.add(dep);
        fireTableDataChanged();
    }
    
 
    public void remove(CompraVO c) {
    	dataVector.remove(c);
        fireTableDataChanged();
    }
    

    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }


	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		
		default: return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }


    public void loadData(List<VendaVO> listVendas){
    	if(listVendas!= null){
    		dataVector.clear();
    		dataVector.addAll(listVendas);
    		fireTableDataChanged();
    	}
    	
    }
}


