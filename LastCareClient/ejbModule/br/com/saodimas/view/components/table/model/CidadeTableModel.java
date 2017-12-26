package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.CidadeVO;

@SuppressWarnings("serial")
public class CidadeTableModel  extends DefaultTableModel {
	public static final int CIDADE = -1;
	public static final int NOME_CIDADE = 0;
	public static final int ESTADO = 1;
	public static final int STATUS = 2;
	
	private String[] columnNames = {"Cidade","Estado", "Status"};
	private Vector <CidadeVO> dataVector;
	
	public CidadeTableModel() {
		dataVector = new Vector<CidadeVO>();
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
		case CIDADE:		return " " + dataVector.get(row);
		case NOME_CIDADE:	return " " + dataVector.get(row).getNome();
		case ESTADO:		return " " + dataVector.get(row).getEstado();
		case STATUS:        return " " + dataVector.get(row).getStatus();
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(CidadeVO c) {
    	dataVector.add(c);
        fireTableDataChanged();
    }
    
    /**
     * Remove uma cidade da tabela.
     * @param (Cidade)c
     */
    public void remove(CidadeVO c) {
    	dataVector.remove(c);
        fireTableDataChanged();
    }
    
    /**
     * Remove uma cidade da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }

	/**
	 * Remove todas cidades da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case CIDADE:		return CidadeVO.class;
		case NOME_CIDADE:	return String.class;
		case ESTADO:		return String.class;
		case STATUS:        return String.class;
		default: return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de cidades.
     * @param (List<Cidade>)listaCidades
     */
    public void loadData(List<CidadeVO> listaCidades){
    	if(listaCidades!= null){
    		dataVector.clear();
    		dataVector.addAll(listaCidades);
    		fireTableDataChanged();
    	}
    }
}
