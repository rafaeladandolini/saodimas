package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.ClienteVO;



@SuppressWarnings("serial")
public class ClienteTableModel extends AbstractTableModel{
	public static final int CODIGO = 0;
	public static final int NOME = 1;
	public static final int ENDERECO = 2;
	public static final int CPF = 3;
	public static final int RG = 4;
	public static final int CONTATOS = 5;
	public static final int OBSERVACAO = 6;
	public static final int CIDADE = 7;
	//public static final int APOLICE = 8;
	public static final int STATUS = 8;
		
	private String[] columnNames = {"Código", "Nome", "Endereço", "CPF", "RG","Contatos", "Observação", "Cidade"/*, "Apolice"*/, "Status"};
	private Vector <ClienteVO> dataVector;
	
	public ClienteTableModel() {
		dataVector = new Vector<ClienteVO>();
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
		case CODIGO:	  return dataVector.get(row).getId();
		case NOME:		  return dataVector.get(row).getNome();
		case ENDERECO:	  return dataVector.get(row).getEndereco() +", " + dataVector.get(row).getBairro() + ", " + dataVector.get(row).getCep();
		case CONTATOS:	  return dataVector.get(row).getContato() + " - " + dataVector.get(row).getContato2() ;
		case OBSERVACAO:  return dataVector.get(row).getObservacao();
		case CIDADE:	  return dataVector.get(row).getCidade().getNome();
		//case APOLICE:	  return dataVector.get(row).getApolice() != null ? dataVector.get(row).getApolice().getNumeroContrato() : "";		
		case STATUS:	  return dataVector.get(row).getStatus();
		case CPF: 		  return dataVector.get(row).getCpf();
		case RG:  		  return dataVector.get(row).getRg();
			
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ClienteVO dep) {
    	dataVector.add(dep);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela.
     * @param (Colaborador)c
     */
    public void remove(ClienteVO c) {
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
    public void loadData(List<ClienteVO> listaColaboradores){
    	if(listaColaboradores!= null){
    		dataVector.clear();
    		dataVector.addAll(listaColaboradores);
    		fireTableDataChanged();
    	}
    	
    }
}


