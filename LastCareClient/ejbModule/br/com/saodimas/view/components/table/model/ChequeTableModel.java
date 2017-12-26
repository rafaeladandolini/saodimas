package br.com.saodimas.view.components.table.model;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ChequeVO;

@SuppressWarnings("serial")
public class ChequeTableModel  extends DefaultTableModel {
	//public static final int CHEQUE = -1;
	public static final int BANCO = 0;
	public static final int CONTA = 1;
	public static final int NUMERO = 2;
	public static final int DATA_EMISSAO = 3;
	public static final int DATA_VENCIMENTO = 4;
	public static final int CLIENTE_FORNECEDOR = 5;
	public static final int DESCRICAO = 6;
	public static final int DATA_CADASTRO = 7;
	public static final int VALOR = 8;
	public static final int DATA_COMPENSACAO = 9;
		
	private String[] columnNames = {"Banco", "Conta", "Numero", "Data Emissao", "Vencimento", "Cliente / Fornecedor", "Descrição", "Data Cadastro", "Valor", "Data Compensação"};
	private Vector <ChequeVO> dataVector;
	
	public ChequeTableModel() {
		dataVector = new Vector<ChequeVO>();
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
			
		case BANCO:                 return dataVector.get(row).getConta().getBanco();
		case CONTA:                 return "Ag.: " + dataVector.get(row).getConta().getAgencia() + " " + dataVector.get(row).getConta().getTipoConta().getDescricao() + " nº: " + dataVector.get(row).getConta().getNumero();
		case NUMERO:                return dataVector.get(row).getNumero();
		case DATA_EMISSAO:          return dataVector.get(row).getDataEmissao();
		case DATA_VENCIMENTO:       return dataVector.get(row).getDataVencimento();
		case CLIENTE_FORNECEDOR:    return dataVector.get(row).getCliente() != null ? dataVector.get(row).getCliente().getNome() : "";
		case DESCRICAO:             return dataVector.get(row).getDescricao();
		case DATA_CADASTRO:         return dataVector.get(row).getDataCadastro();
		case VALOR:                 return formataPreco(dataVector.get(row).getValor());
		case DATA_COMPENSACAO:      return dataVector.get(row).getDataCompensacao();
		
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(ChequeVO c) {
    	dataVector.add(c);
        fireTableDataChanged();
    }
    
    /**
     * Remove uma cidade da tabela.
     * @param (Cidade)c
     */
    public void remove(ChequeVO c) {
    	dataVector.remove(c);
        fireTableDataChanged();
    }
    
    /**
     * Remove uma cidade da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }

	/**
	 * Remove todas cidades da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case BANCO:                 return String.class;
		case CONTA:                 return String.class;
		case NUMERO:                return String.class;
		case DATA_EMISSAO:          return Date.class; 
		case DATA_VENCIMENTO:       return Date.class; 
		case CLIENTE_FORNECEDOR:    return String.class;
		case DESCRICAO:             return String.class;
		case DATA_CADASTRO:         return Date.class; 
		case VALOR:                 return String.class;
		case DATA_COMPENSACAO:      return Date.class; 
		default: return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

  
    public void loadData(List<ChequeVO> listaCidades){
    	if(listaCidades!= null){
    		dataVector.clear();
    		dataVector.addAll(listaCidades);
    		fireTableDataChanged();
    	}
    }
    
    private String formataPreco(Double valor)
    {
    	DecimalFormat df = new DecimalFormat("#,###,###.00");
		df.setCurrency(Currency.getInstance(new Locale("pt","br")));
		String preco = "R$ " + df.format(valor);
		return preco;
    }
}
