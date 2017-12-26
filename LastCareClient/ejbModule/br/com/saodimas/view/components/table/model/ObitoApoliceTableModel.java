package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ApoliceVO;

@SuppressWarnings("serial")
public class ObitoApoliceTableModel extends DefaultTableModel {
	public static final int APOLICE = -1;
	public static final int CONTRATO = 0;
	public static final int ASSOCIADO = 1;
	public static final int DOCUMENTO = 2;
	
	private String[] columnNames = {"Contrato","Associado","Documento"};
	private Vector <ApoliceVO> dataVector;
	
	public ObitoApoliceTableModel() {
		dataVector = new Vector<ApoliceVO>();
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
		case CONTRATO:	return dataVector.get(row).getNumeroContrato();
		case ASSOCIADO:	return dataVector.get(row).getTitular().getNome();
		case DOCUMENTO:	return dataVector.get(row).getTitular().getCPF();
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ApoliceVO o) {
    	dataVector.add(o);
        fireTableDataChanged();
    }
    
    /**
     * Remove um óbito da tabela.
     * @param (Obito)o
     */
    public void remove(ApoliceVO o) {
    	dataVector.remove(o);
        fireTableDataChanged();
    }
    
    /**
     * Remove um óbito da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }
    
	/**
	 * Remove todos óbitos na tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case CONTRATO:	return String.class;
		case ASSOCIADO:	return String.class;
		case DOCUMENTO:	return String.class;
		default: return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex == ASSOCIADO; 
    }

    /**
     * Alimenta a tabela com uma lista de óbitos.
     * @param (List<Obito>)listaObitos
     */
    public void loadData(List<ApoliceVO> listaApolices){
    	if (listaApolices != null){
    		dataVector.clear();
    		dataVector.addAll(listaApolices);
    		fireTableDataChanged();
    	}
    }
}