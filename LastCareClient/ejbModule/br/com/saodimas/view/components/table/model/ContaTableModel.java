package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.BancoVO;
import br.com.saodimas.model.beans.ContaVO;



@SuppressWarnings("serial")
public class ContaTableModel extends AbstractTableModel{
	public static final int BANCO = 0;
	public static final int AGENCIA = 1;
	public static final int NUMERO_CONTA = 2;
	public static final int NOME = 3;
	public static final int TIPO_CONTA = 4;

		
	private String[] columnNames = {"Banco", "Agência", "Número Conta", "Nome", "Tipo Conta"};
	private Vector <ContaVO> dataVector;
	
	public ContaTableModel() {
		dataVector = new Vector<ContaVO>();
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
		case BANCO:	          return dataVector.get(row).getBanco() == null ? "" : dataVector.get(row).getBanco().toString();
		case AGENCIA:		  return dataVector.get(row).getAgencia();
		case NUMERO_CONTA:	  return dataVector.get(row).getNumero();
		case NOME:		      return dataVector.get(row).getNome();
		case TIPO_CONTA:	  return dataVector.get(row).getTipoConta() == null? "" :dataVector.get(row).getTipoConta().getDescricao();
			
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ContaVO dep) {
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
     * Alimenta a tabela com uma lista de contas.
     *  */
    public void loadData(List<ContaVO> listContas){
    	if(listContas!= null){
    		dataVector.clear();
    		dataVector.addAll(listContas);
    		fireTableDataChanged();
    	}
    	
    }
}


