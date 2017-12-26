package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.FornecedorVO;


@SuppressWarnings("serial")
public class EmpresaCompraTableModel extends DefaultTableModel {
	public static final int EMPRESA_COMPRA = -1;
	public static final int NOME = 0;
	public static final int CONTATO = 1;
		
	private String[] columnNames = {"Nome","Contato"};
	private Vector <FornecedorVO> dataVector;
	
	public EmpresaCompraTableModel() {
		dataVector = new Vector<FornecedorVO>();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return (dataVector != null)? dataVector.size() : 0;
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	public Object getSelectedValue(int row){
		return dataVector.get(row);
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case NOME:		return " " + dataVector.get(row).getNome();
		case CONTATO:	return " " + dataVector.get(row).getContato();
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(FornecedorVO r) {
    	dataVector.add(r);
        fireTableDataChanged();
    }
    

    public void remove(FornecedorVO r) {
    	dataVector.remove(r);
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
		case EMPRESA_COMPRA:		return FornecedorVO.class;
		case NOME:	return String.class;
		case CONTATO:	return String.class;
		default: return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }


    public void loadData(List<FornecedorVO> listEmpresas){
    	if(listEmpresas!= null){
    		dataVector.clear();
    		dataVector.addAll(listEmpresas);
    		fireTableDataChanged();
    	}
    }
}
