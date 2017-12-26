package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ProdutoVO;

@SuppressWarnings("serial")
public class ProdutoTableModel extends DefaultTableModel {
	public static final int PRODUTO = -1;
	public static final int CODIGO = 0;
	public static final int NOME = 1;
	public static final int DESCRICAO = 2;
	public static final int VALOR = 3;
	public static final int REFERENCIA = 4;
	public static final int TIPO_PRODUTO = 5;
	public static final int STATUS = 6;

	private String[] columnNames = {"Código","Nome","Descrição","Valor Venda", "Ref.", "Tipo Produto","Status"};
	private Vector <ProdutoVO> dataVector;

	public ProdutoTableModel() {
		dataVector = new Vector<ProdutoVO>();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return (dataVector != null)? dataVector.size() : 0;
	}

	@Override
	public Vector<ProdutoVO> getDataVector() {
		return dataVector;
	}

	public String getColumnName(int column){
		return columnNames[column];
	}

	public Object getSelectedValue(int row){
		return dataVector.get(row);
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case CODIGO:       return dataVector.get(row).getId() + "";
		case NOME:		   return dataVector.get(row).getNome();
		case VALOR:		
		{
			if(dataVector.get(row).getTipoProduto().equalsIgnoreCase(ProdutoVO.TIPO_PRODUTO_CONSUMO_INTERNO))
				return "";
			else{
				if(dataVector.get(row).getValor() != null)
					return this.formataPreco(dataVector.get(row).getValor());
				else return "";
			}
		}
		case DESCRICAO:    return dataVector.get(row).getDescricao();
		case STATUS:       return dataVector.get(row).getStatus();
		case REFERENCIA:   return dataVector.get(row).getReferenciaValor();
		case TIPO_PRODUTO: return dataVector.get(row).getTipoProduto();
		default: return dataVector.get(row);
		}
	}

	  private String formataPreco(double valor)
	    {
		  
	    	DecimalFormat df = new DecimalFormat("#,###,###.00");
			df.setCurrency(Currency.getInstance(new Locale("pt","br")));
			String preco = "R$ " + df.format(valor);
			return preco;
	    }
	
	public void setValueAt(Object value, int row, int col) {
		fireTableCellUpdated(row, col);
	}

	public void add(ProdutoVO p) {
		dataVector.add(p);
		fireTableDataChanged();
	}

	/**
	 * Remove um produto da tabela.
	 * @param (Produto)p
	 */
	public void remove(ProdutoVO p) {
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
	 * Remove todos produtos da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case NOME:		return String.class;
		case DESCRICAO:	return String.class;
		case VALOR:	return String.class;
		case TIPO_PRODUTO:	return String.class;
		case REFERENCIA:	return String.class;
		case STATUS:    return String.class;
		default: return String.class;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false; 
	}

	/**
	 * Alimenta a tabela com uma lista de produtos.
	 * @param (List<Produto>)listaProdutos
	 */
	public void loadData(List<ProdutoVO> listaProdutos){
		if(listaProdutos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaProdutos);
    		fireTableDataChanged();
    	}
		else removeAll();
	}
}
