package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.FaturaVO;

@SuppressWarnings("serial")
public class AdminFaturaTableModel extends DefaultTableModel {
	
	public static final int VENCIMENTO = 0;
	public static final int PAGAMENTO = 1;
	public static final int DATA_BAIXA = 2;
	public static final int NUMAPOLICE = 3;
	public static final int NUMFATURA = 4;
	public static final int TITULAR = 5;
	public static final int VALOR = 6;
	public static final int DESCONTO = 7;
	public static final int MULTA = 8;
	public static final int VALOR_PARCEIRO = 9;
	public static final int TOTAL = 10;
	public static final int PARCEIRO = 11;
	public static final int QUITADO_POR = 12;
	
	private String[] columnNames = {"Vencimento","Pagamento","Baixa","Nº Apólice","Nº Fatura","Titular","Valor", "Desconto", "Multa", "Valor Parceiro" ,"Total","Parceiro","Quitado por"};
	private Vector <FaturaVO> dataVector;
	
	public AdminFaturaTableModel() {
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

	public Set<FaturaVO> getDataSet() {
		
		Set<FaturaVO> faturaSet = new HashSet<FaturaVO>();
		
		for (FaturaVO faturaVO : dataVector) {
			faturaSet.add(faturaVO);
		}
		return faturaSet;		
	}
	
	public Object getValueAt(int row, int col) {
		switch (col) {
		case NUMAPOLICE:	return dataVector.get(row).getApolice().getNumeroContrato() + "";
		case NUMFATURA:		return dataVector.get(row).getNumeroFatura() + "";
		case TITULAR:       return dataVector.get(row).getApolice().getTitular().getNome();
		case VALOR :        return 	this.formatValor(dataVector.get(row).getValor());
		case DESCONTO:      return 	this.formatValor(dataVector.get(row).getValorDesconto());
		case MULTA:         return 	this.formatValor(dataVector.get(row).getValorMulta());
		case VALOR_PARCEIRO:       return  this.formatValor(dataVector.get(row).getValorParceiro());
		case TOTAL:
			
			Double valorTotal = dataVector.get(row).getValor() - dataVector.get(row).getValorDesconto() + dataVector.get(row).getValorMulta() - dataVector.get(row).getValorParceiro();
			return 	this.formatValor(valorTotal);
		case PAGAMENTO:
			return this.formataData(dataVector.get(row).getDataPagamento());		
		case VENCIMENTO:
			return this.formataData(dataVector.get(row).getDataVencimento());		
		case DATA_BAIXA:
			if(dataVector.get(row).getDataBaixa() != null){
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				return sdf1.format(dataVector.get(row).getDataBaixa()) + "";
			}else
				return "";
		case QUITADO_POR:	return (dataVector.get(row).getColaborador() == null)?  "" : dataVector.get(row).getColaborador().getNome();
		case PARCEIRO:	return (dataVector.get(row).getParceiro() == null)?  "" : dataVector.get(row).getParceiro().getDescricao();
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
		case NUMAPOLICE:     return String.class;
		case NUMFATURA:		 return Integer.class;
		case VALOR:			 return String.class;
		case DESCONTO:		 return String.class;
		case MULTA:			 return String.class;
		case VALOR_PARCEIRO: return String.class;
		case TOTAL:			 return String.class;
		case PAGAMENTO:	     return Date.class;
		case VENCIMENTO:	 return Date.class;
		case QUITADO_POR:	 return String.class;
		case PARCEIRO:   	 return String.class;
		default:             return String.class;
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
    
    private String formatValor(Double valor)
    {
    	DecimalFormat df = new DecimalFormat("#,###,###.00");
		df.setCurrency(Currency.getInstance(new Locale("pt","br")));
		String preco = "R$ " + df.format(valor);
		return preco;
    }
    
    private String formataData(Date data)
    {
    	if(data == null) return null;
		
    	SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
		return sdff.format(data);
    }
    
}
