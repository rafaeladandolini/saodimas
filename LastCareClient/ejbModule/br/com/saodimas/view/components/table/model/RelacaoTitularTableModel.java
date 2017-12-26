package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.RelacaoVO;

@SuppressWarnings("serial")
public class RelacaoTitularTableModel extends DefaultTableModel {
	public static final int RELACAO = -1;
	public static final int NOME_RELACAO = 0;
	public static final int TIPO_RELACAO = 1;
	public static final int STATUS = 2;
	
	private String[] columnNames = {"Descrição","Relacionado com...", "Status"};
	private Vector <RelacaoVO> dataVector;
	
	public RelacaoTitularTableModel() {
		dataVector = new Vector<RelacaoVO>();
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
		case RELACAO:		return " " + dataVector.get(row);
		case NOME_RELACAO:	return " " + dataVector.get(row).getDescricao();
		case TIPO_RELACAO:	return " " + dataVector.get(row).getTipoRelacao();
		case STATUS:        return dataVector.get(row).getStatus();
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(RelacaoVO r) {
    	dataVector.add(r);
        fireTableDataChanged();
    }
    
    /**
     * Remove uma relação com o titular da tabela.
     * @param (RelacaoTitular)c
     */
    public void remove(RelacaoVO r) {
    	dataVector.remove(r);
        fireTableDataChanged();
    }
    
    /**
     * Remove uma relação com o titular da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }

	/**
	 * Remove todas relações com o titular da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case RELACAO:		return RelacaoVO.class;
		case NOME_RELACAO:	return String.class;
		case TIPO_RELACAO:	return String.class;
		case STATUS:        return String.class;
		default: return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de relações com o titular.
     * @param (List<RelacaoTitular>)listaRelacoes
     */
    public void loadData(List<RelacaoVO> listaRelacoes){
    	if(listaRelacoes!= null){
    		dataVector.clear();
    		dataVector.addAll(listaRelacoes);
    		fireTableDataChanged();
    	}
    }
}
