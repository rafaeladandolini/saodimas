package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ServicoVO;

@SuppressWarnings("serial")
public class ServicoTableModel extends DefaultTableModel {
	public static final int SERVICO = -1;
	public static final int NOME = 0;
	public static final int PRECO = 1;
	public static final int STATUS = 2;
	
	private String[] columnNames = {"Nome","Custo", "Status"};
	private Vector <ServicoVO> dataVector;
	
	public ServicoTableModel() {
		dataVector = new Vector<ServicoVO>();
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
		case NOME:		return dataVector.get(row).getNome();
		case PRECO:
			DecimalFormat df = new DecimalFormat("#,###,###.00");
			df.setCurrency(Currency.getInstance(new Locale("pt","br")));
			String preco = "R$ " + df.format(dataVector.get(row).getValor());
			
			return preco + " por " + dataVector.get(row).getReferenciaValor();
		case STATUS: return	 dataVector.get(row).getStatus();
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ServicoVO s) {
    	dataVector.add(s);
        fireTableDataChanged();
    }
    
    /**
     * Remove um serviço da tabela.
     * @param (ServicoVO)s
     */
    public void remove(ServicoVO s) {
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
	 * Remove todos serviços da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case NOME:		return String.class;
		case PRECO:		return String.class;
		case STATUS:    return String.class;
		default: return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de serviços.
     * @param (List<Servico>)listaServicos
     */
    public void loadData(List<ServicoVO> listaServicos){
    	if(listaServicos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaServicos);
    		fireTableDataChanged();
    	}
    }
}