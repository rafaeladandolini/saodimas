package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.BancoVO;



@SuppressWarnings("serial")
public class BancoTableModel extends AbstractTableModel{
	public static final int CODIGO_BANCO = 0;
	public static final int NOME_BANCO = 1;

		
	private String[] columnNames = {"Código", "Nome"};
	private Vector <BancoVO> dataVector;
	
	public BancoTableModel() {
		dataVector = new Vector<BancoVO>();
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
		case CODIGO_BANCO:	  return dataVector.get(row).getCodigoBanco();
		case NOME_BANCO:		  return dataVector.get(row).getNomeBanco();
			
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(BancoVO dep) {
    	dataVector.add(dep);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela.
     * @param (Colaborador)c
     */
    public void remove(BancoVO c) {
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
    public void loadData(List<BancoVO> listBanco){
    	if(listBanco!= null){
    		dataVector.clear();
    		dataVector.addAll(listBanco);
    		fireTableDataChanged();
    	}
    	
    }
}


