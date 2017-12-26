package br.com.saodimas.view.components.table.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.ApoliceVO;

/**
 * Classe que define a apresenta��o da Jtable que representa as ap�lices dispon�veis. 
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class ApoliceTableModel extends AbstractTableModel{
	public static final int APOLICE = -1;
	public static final int CONTRATO = 0;
	public static final int NOME = 1;
	public static final int ENDERECO = 2;
	public static final int CIDADE = 3;
	public static final int PLANO = 4;
	public static final int DATA = 5;
	public static final int STATUS = 6;
	
	
	
	private String[] columnNames = {"N. Ap�lice","Nome do Titular", "Endere�o", "Cidade","Plano","Data de Ades�o", "Status"};
	private Vector <ApoliceVO> dataVector;
	
	public ApoliceTableModel() {
		dataVector = new Vector<ApoliceVO>();
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
		//case NUMERO:     return  dataVector.get(row).getId();
		case NOME:		return dataVector.get(row).getTitular() != null ? dataVector.get(row).getTitular().getNome() : "Undefined";
		case CONTRATO:	return dataVector.get(row).getNumeroContrato();
		case STATUS:	return dataVector.get(row).getStatus();
		case PLANO:		return dataVector.get(row).getPlano() != null ? dataVector.get(row).getPlano().getDescricao() : "";
		case DATA:		return new SimpleDateFormat("dd/MM/yyyy").format(dataVector.get(row).getDataAdesao());
		case ENDERECO:	return dataVector.get(row).getEndereco() + ", " + dataVector.get(row).getBairro();
		case CIDADE:	return dataVector.get(row).getCidade() != null ? dataVector.get(row).getCidade().getNome() : "";
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ApoliceVO ap) {
    	dataVector.add(ap);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela.
     * @param (Apolice)c
     */
    public void remove(ApoliceVO ap) {
    	dataVector.remove(ap);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela na posi��o espec�ficada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }

	/**
	 * Remove todos ap�lices da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {

		case CONTRATO:	return String.class;
		case NOME:		return String.class;
		case STATUS:	return String.class;
		case PLANO:		return String.class;
		case ENDERECO:	return String.class;
		case CIDADE:	return String.class;
		case DATA:		return Date.class;
		default: return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de ap�lices.
     * @param (List<Apolice>)listaApolices
     */
    public void loadData(List<ApoliceVO> listaApolices){
    	if(listaApolices!= null){
    		dataVector.clear();
    		dataVector.addAll(listaApolices);
    		fireTableDataChanged();
    	}
    	else removeAll();
    }
    
    public List<ApoliceVO> getList()
    {
    	return dataVector;
    }
    
}


