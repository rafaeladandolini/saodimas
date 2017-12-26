package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.ColaboradorVO;

/**
 * Classe que define a apresentação da Jtable que representa os colaboradores disponíveis. 
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ColaboradorTableModel extends AbstractTableModel{
	public static final int LOGIN = 0;
	public static final int NOME = 1;
	public static final int TIPO = 2;
	public static final int STATUS = 3;
	
	private String[] columnNames = {"Login","Nome","Tipo","Status"};
	private Vector <ColaboradorVO> dataVector;
	
	public ColaboradorTableModel() {
		dataVector = new Vector<ColaboradorVO>();
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
		case NOME:		return dataVector.get(row).getNome();
		case LOGIN:		return dataVector.get(row).getLogin();
		case TIPO:		return dataVector.get(row).getTipoColaborador();
		case STATUS:	return dataVector.get(row).getStatus();
			
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ColaboradorVO dep) {
    	dataVector.add(dep);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela.
     * @param (Colaborador)c
     */
    public void remove(ColaboradorVO c) {
    	dataVector.remove(c);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }

	/**
	 * Remove todos colaboradores da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case LOGIN:		return String.class;
		case NOME:		return String.class;
		case TIPO:		return String.class;
		case STATUS:	return String.class;
		default: return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de colaboradores.
     * @param (List<Colaborador>)listaColaboradores
     */
    public void loadData(List<ColaboradorVO> listaColaboradores){
    	if(listaColaboradores!= null){
    		dataVector.clear();
    		dataVector.addAll(listaColaboradores);
    		fireTableDataChanged();
    	}
    	
    }
}


