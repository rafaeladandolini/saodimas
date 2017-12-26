package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.ParceiroVO;

/**
 * Classe que define a apresentação da Jtable que representa os colaboradores disponíveis. 
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ParceiroTableModel extends AbstractTableModel{

	public static final int DESCRICAO = 0;
	public static final int RESPONSAVEL = 1;
	public static final int TELEFONE = 2;
	public static final int ID_CIDADE = 3;
	public static final int STATUS = 4;
	
	private String[] columnNames = {"Descrição","Nome Responsável","Telefone","Cidade","Status"};
	private Vector <ParceiroVO> dataVector;
	
	public ParceiroTableModel() {
		dataVector = new Vector<ParceiroVO>();
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
		case DESCRICAO:		return dataVector.get(row).getDescricao();
		case RESPONSAVEL:	return dataVector.get(row).getResponsavel();
		case TELEFONE:		return dataVector.get(row).getTelefone();
		case ID_CIDADE:	    return dataVector.get(row).getCidade().getNome();
		case STATUS:	    return dataVector.get(row).getStatus();
			
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ParceiroVO dep) {
    	dataVector.add(dep);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela.
     * @param (Colaborador)c
     */
    public void remove(ParceiroVO c) {
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
		case DESCRICAO:	  return String.class;
		case RESPONSAVEL: return String.class;
		case TELEFONE:	  return String.class;
		case ID_CIDADE:	  return String.class;
		case STATUS:	  return String.class;
		default:          return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de colaboradores.
     * @param (List<Colaborador>)listaColaboradores
     */
    public void loadData(List<ParceiroVO> listParceiro){
    	if(listParceiro!= null){
    		dataVector.clear();
    		dataVector.addAll(listParceiro);
    		fireTableDataChanged();
    	}
    	
    }
}


