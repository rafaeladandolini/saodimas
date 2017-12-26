package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ProdutoVendaVO;


@SuppressWarnings("serial")
public class VendaProdutoTableModel extends DefaultTableModel {
	public static final int CODIGO = 0;
	public static final int NOME = 1;
	public static final int QUANTIDADE = 2;
	public static final int VALOR_VENDA = 3;
	public static final int TOTAL_PAGAR = 4;
	
	private String[] columnNames = {"Código","Nome","Quantidade","Valor Venda", "Total à Pagar"};
	private Vector <ProdutoVendaVO> dataVector;
	DecimalFormat formatter = new DecimalFormat("#0.00");
	
	public VendaProdutoTableModel() {
		dataVector = new Vector<ProdutoVendaVO>();
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
		case CODIGO:        return dataVector.get(row).getId();	
		case NOME:			return dataVector.get(row).getProduto() !=  null ? dataVector.get(row).getProduto().getNome() : "";
		case QUANTIDADE:	return dataVector.get(row).getQuantidade();
		case VALOR_VENDA:   return formatter.format(dataVector.get(row).getValor());
		case TOTAL_PAGAR:   return formatter.format(dataVector.get(row).getValor() * dataVector.get(row).getQuantidade());
		default:            return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ProdutoVendaVO p) {
    	dataVector.add(p);
        fireTableDataChanged();
    }
    
 
    public void remove(ProdutoVendaVO p) {
    	dataVector.remove(p);
        fireTableDataChanged();
    }
    
  
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }
    

	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case CODIGO:		     	return Integer.class;
		case NOME:			        return String.class;
		case QUANTIDADE:	        return Integer.class;
		case VALOR_VENDA:	        return Double.class;
		default: return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex == QUANTIDADE; 
    }

  
    public void loadData(List<ProdutoVendaVO> listaProdutos){
    	if(listaProdutos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaProdutos);
    		fireTableDataChanged();
    	}
    }
    
    @Override
    public Vector <ProdutoVendaVO> getDataVector() {
    	return dataVector;
    }
}