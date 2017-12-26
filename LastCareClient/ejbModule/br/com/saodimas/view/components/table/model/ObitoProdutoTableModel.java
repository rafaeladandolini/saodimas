package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ProdutoObitoVO;

@SuppressWarnings("serial")
public class ObitoProdutoTableModel extends DefaultTableModel {
	public static final int NOME = 0;
	public static final int QUANTIDADE = 1;
	//public static final int REFERENCIA = 2;
	public static final int VALOR = 2;
	public static final int TOTAL = 3;
	
	private String[] columnNames = {"Nome","Quantidade",/*"Referência"*/"Valor Venda","Total"};
	private Vector <ProdutoObitoVO> dataVector;
	DecimalFormat formatter = new DecimalFormat("#0.00");
	
	public ObitoProdutoTableModel() {
		dataVector = new Vector<ProdutoObitoVO>();
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
		case NOME:			return dataVector.get(row).getProduto() !=  null ? dataVector.get(row).getProduto().getNome() : "";
		case QUANTIDADE:	return dataVector.get(row).getQuantidade();
	//	case REFERENCIA:	return dataVector.get(row).getProduto() ==  null ? "" : dataVector.get(row).getProduto().getReferenciaValor() + ((dataVector.get(row).getQuantidade() > 1)? "s" : "");
		case VALOR:         return formatter.format(dataVector.get(row).getValor());
		case TOTAL:			return formatter.format(dataVector.get(row).getTotal()); 
		default:            return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ProdutoObitoVO p) {
    	dataVector.add(p);
        fireTableDataChanged();
    }
    
    /**
     * Remove um produto da tabela.
     * @param (ProdutoObito)p
     */
    public void remove(ProdutoObitoVO p) {
    	dataVector.remove(p);
        fireTableDataChanged();
    }
    
    /**
     * Remove um produto da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }
    
	/**
	 * Remove todos produtos do óbito na tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case NOME:			return String.class;
		case QUANTIDADE:	return Integer.class;
		//case REFERENCIA:	return String.class;
		case VALOR:      	return Double.class;
		case TOTAL:	        return Double.class;
		default: return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex == QUANTIDADE; 
    }

    /**
     * Alimenta a tabela com uma lista de produtos.
     * @param (List<ProdutoObito>)listaProdutos
     */
    public void loadData(List<ProdutoObitoVO> listaProdutos){
    	if(listaProdutos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaProdutos);
    		fireTableDataChanged();
    	}
    }
    
    @Override
    public Vector <ProdutoObitoVO> getDataVector() {
    	return dataVector;
    }
}