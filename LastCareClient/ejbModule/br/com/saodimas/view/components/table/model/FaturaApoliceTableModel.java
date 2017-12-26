package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.FaturaVO;

@SuppressWarnings("serial")
public class FaturaApoliceTableModel extends DefaultTableModel {
	public static final int NUMFATURA = 0;
	public static final int VALOR = 1;
	public static final int VENCIMENTO = 2;
	public static final int SITUACAO = 3;
	public static final int QUITADO_POR = 4;
	
	private String[] columnNames = {"Nº. Fatura","Total","Vencimento","Situação","Quitado por"};
	private Vector <FaturaVO> dataVector;
	
	public FaturaApoliceTableModel() {
		dataVector = new Vector<FaturaVO>();
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
	public Vector <FaturaVO> getDataVector() {
		return dataVector;
	}

	public List<FaturaVO> getDataSet() {
		
		List<FaturaVO> faturaSet = new ArrayList<FaturaVO>();
		
		for (FaturaVO faturaVO : dataVector) {
			faturaSet.add(faturaVO);
		}
		return faturaSet;		
	}
	
	public Object getValueAt(int row, int col) {
		switch (col) {
		case NUMFATURA:		return dataVector.get(row).getNumeroFatura() + "";
		case VALOR:
			DecimalFormat df = new DecimalFormat("#,###,###.00");
			df.setCurrency(Currency.getInstance(new Locale("pt","br")));
			Double valorTotal = dataVector.get(row).getValor() - dataVector.get(row).getValorDesconto() + dataVector.get(row).getValorMulta();
			String preco = "R$ " + df.format(valorTotal);
			
			return preco;
		case VENCIMENTO:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(dataVector.get(row).getDataVencimento()) + "";
		case SITUACAO:		return (dataVector.get(row).getDataPagamento() == null)? "Em aberto" : "Pago";
		case QUITADO_POR: return (dataVector.get(row).getColaborador() == null)? "" : dataVector.get(row).getColaborador().getNome(); 
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
    }

    public void add(FaturaVO s) {
    	dataVector.add(s);
        fireTableDataChanged();
    }
    
    /**
     * Remove uma fatura da tabela.
     * @param (Fatura)s
     */
    public void remove(FaturaVO s) {
    	dataVector.remove(s);
        fireTableDataChanged();
    }
    
    /**
     * Remove uma fatura da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }
    
	/**
	 * Remove todos produtos da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case NUMFATURA:		return Integer.class;
		case VALOR:			return String.class;
		case VENCIMENTO:	return Date.class;
		case SITUACAO:		return String.class;
		case QUITADO_POR:	return String.class;
		default: return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de faturas.
     * @param (List<Fatura>)listaFaturas
     */
    public void loadData(List<FaturaVO> listaFatura){
    	if(listaFatura!= null){
    		dataVector.clear();
    		dataVector.addAll(listaFatura);
    		fireTableDataChanged();
    	}
    }
}
