package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ServicoPlanoVO;

@SuppressWarnings("serial")
public class PlanoServicoTableModel extends DefaultTableModel {
	public static final int NOME = 0;
	public static final int QUANTIDADE = 1;
	public static final int REFERENCIA = 2;
	
	private String[] columnNames = {"Nome","Quantidade","Referência"};
	private Vector <ServicoPlanoVO> dataVector;
	
	public PlanoServicoTableModel() {
		dataVector = new Vector<ServicoPlanoVO>();
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
	
	@Override
	public Vector <ServicoPlanoVO> getDataVector() {
		return dataVector;
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case NOME:			return dataVector.get(row).getServico().getNome();
		case QUANTIDADE:	return dataVector.get(row);//.getQuantidade();
		case REFERENCIA:	return dataVector.get(row).getServico().getReferenciaValor() + ((dataVector.get(row).getQuantidade() > 1)? "s" : "");
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ServicoPlanoVO s) {
    	dataVector.add(s);
        fireTableDataChanged();
    }
    
    /**
     * Remove um serviço da tabela.
     * @param (ServicoPlano)s
     */
    public void remove(ServicoPlanoVO s) {
    	dataVector.remove(s);
        fireTableDataChanged();
    }
    
    /**
     * Remove um serviço da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }
    
	/**
	 * Remove todos serviços do plano na tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case NOME:			return String.class;
		case QUANTIDADE:	return String.class;
		case REFERENCIA:	return String.class;
		default: return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex == QUANTIDADE; 
    }

    /**
     * Alimenta a tabela com uma lista de serviços.
     * @param (List<ServicoPlano>)listaServicos
     */
    public void loadData(List<ServicoPlanoVO> listaServicos){
    	if(listaServicos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaServicos);
    		fireTableDataChanged();
    	}
    }
}
