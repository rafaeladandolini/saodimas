package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ServicoVendaVO;

@SuppressWarnings("serial")
public class VendaServicoTableModel extends DefaultTableModel {
	public static final int NOME = 0;
	public static final int QUANTIDADE = 1;
	public static final int REFERENCIA = 2;
	public static final int VALOR = 3;
	public static final int TOTAL = 4;

	private String[] columnNames = {"Nome","Quantidade","Referência","Valor Venda","Total"};
	private Vector <ServicoVendaVO> dataVector;
	private DecimalFormat formatter = new DecimalFormat("#0.00");
	
	public VendaServicoTableModel() {
		dataVector = new Vector<ServicoVendaVO>();
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
		case NOME:			return dataVector.get(row).getServico() !=  null ? dataVector.get(row).getServico().getNome() : "";
		case QUANTIDADE:	return dataVector.get(row).getQuantidade();
		case REFERENCIA:	return dataVector.get(row).getServico() ==  null ? "" : dataVector.get(row).getServico().getReferenciaValor() + ((dataVector.get(row).getQuantidade() > 1)? "s" : "");
		case VALOR:         return formatter.format(dataVector.get(row).getValor());
		//case TOTAL: return new Double(0);
		case TOTAL:			return formatter.format(dataVector.get(row).getTotal()); 
		//case VALOR: dataVector.get(row).getValor();
		//case TOTAL: dataVector.get(row).getTotal();
		default:            return "";//dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ServicoVendaVO s) {
    	dataVector.add(s);
        fireTableDataChanged();
    }
    
    /**
     * Remove um serviço da tabela.
     * @param (ServicoObito)s
     */
    public void remove(ServicoVendaVO s) {
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
	 * Remove todos serviços do óbito na tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case NOME:			return String.class;
		case QUANTIDADE:	return Integer.class;
		case REFERENCIA:	return String.class;
		case VALOR:      	return Double.class;
		case TOTAL:	        return double.class;
		default: return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex == QUANTIDADE; 
    }

    /**
     * Alimenta a tabela com uma lista de serviços.
     * @param (List<ServicoObito>)listaServicos
     */
    public void loadData(List<ServicoVendaVO> listaServicos){
    	if(listaServicos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaServicos);
    		fireTableDataChanged();
    	}
    }
    
    @Override
    public Vector <ServicoVendaVO> getDataVector() {
    	return dataVector;
    }
}