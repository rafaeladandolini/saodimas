package br.com.saodimas.view.components.table.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ProdutoPlanoVO;

@SuppressWarnings("serial")
public class PlanoProdutoTableModel extends DefaultTableModel {
	public static final int NOME = 0;
	public static final int QUANTIDADE = 1;
	public static final int REFERENCIA = 2;
	
	private String[] columnNames = {"Nome","Quantidade","Referência"};
	private Vector <ProdutoPlanoVO> dataVector;
	
	public PlanoProdutoTableModel() {
		dataVector = new Vector<ProdutoPlanoVO>();
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
		case NOME:			return dataVector.get(row).getProduto().getNome();
		case QUANTIDADE:	return dataVector.get(row);
		//case REFERENCIA:	return dataVector.get(row).getProduto().getReferenciaValor() + ((dataVector.get(row).getQuantidade() > 1)? "s" : "");
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ProdutoPlanoVO p) {
    	dataVector.add(p);
        fireTableDataChanged();
    }
    
    /**
     * Remove um produto da tabela.
     * @param (ProdutoPlano)p
     */
    public void remove(ProdutoPlanoVO p) {
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
	 * Remove todos produtos do plano na tabela.
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

    @Override
    public Vector <ProdutoPlanoVO> getDataVector() {
    	return dataVector;
    }
    
    /**
     * Alimenta a tabela com uma lista de produtos.
     * @param (List<ProdutoPlano>)listaProdutos
     */
    public void loadData(List<ProdutoPlanoVO> listaProdutos){
    	if(listaProdutos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaProdutos);
    		fireTableDataChanged();
    	}
    }
}
