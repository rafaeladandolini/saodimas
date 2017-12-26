package br.com.saodimas.view.components.table.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.CompraVO;



@SuppressWarnings("serial")
public class CompraTableModel extends AbstractTableModel{
	public static final int DATA = 0;
	public static final int EMPRESA = 1;
	public static final int DESCRICAO = 2;
	public static final int VENDEDOR = 3;
	public static final int OBSERVACAO = 4;
	
		
	private String[] columnNames = {"Data Compra", "Empresa", "Descricao", "Vendedor", "Observacao"};
	private Vector <CompraVO> dataVector;
	
	public CompraTableModel() {
		dataVector = new Vector<CompraVO>();
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
		case DATA:
		if(dataVector.get(row).getDataCompra()== null)
			return null;
			SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
			return sdff.format(dataVector.get(row).getDataCompra()) + "";
		case EMPRESA:	  return dataVector.get(row).getFornecedor().getNome();
		case DESCRICAO:	  return dataVector.get(row).getDescricao();
		case VENDEDOR:	  return dataVector.get(row).getVendedor();
		case OBSERVACAO:  return dataVector.get(row).getObservacao();
		
			
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(CompraVO dep) {
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


    public void loadData(List<CompraVO> listaColaboradores){
    	if(listaColaboradores!= null){
    		dataVector.clear();
    		dataVector.addAll(listaColaboradores);
    		fireTableDataChanged();
    	}
    	
    }
}


