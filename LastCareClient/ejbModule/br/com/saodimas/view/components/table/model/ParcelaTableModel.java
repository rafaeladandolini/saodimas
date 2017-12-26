package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ParcelaVO;

@SuppressWarnings("serial")
public class ParcelaTableModel extends DefaultTableModel {
	public static final int PARCELA = -1;
	public static final int SELECAO = 0;
	public static final int NUMERACAO = 1;
	public static final int VENCIMENTO = 2;
	public static final int VALOR = 3;
	
	
	private String[] columnNames = {"", "Parcela", "Vencimento","Valor"};
	private Vector <ParcelaVO> dataVector;
		
	public ParcelaTableModel() {
		dataVector = new Vector<ParcelaVO>();
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
	public Vector<ParcelaVO> getDataVector() {
		return dataVector;
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case SELECAO:
			return dataVector.get(row).isSelecionada();
		case NUMERACAO:     return dataVector.get(row).getNumeracao() + "";
		case VENCIMENTO:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(dataVector.get(row).getDataVencimento()) + "";
		case VALOR: return this.formataPreco(dataVector.get(row).getValor());
		
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
    	if (col == SELECAO) dataVector.get(row).setSelecionada((Boolean)value);
        fireTableCellUpdated(row, col);
    }

    public void add(ParcelaVO s) {
    	s.setSelecionada(false);
    	dataVector.add(s);
    	fireTableDataChanged();
    }
    
    /**
     * Remove uma fatura da tabela.
     * @param (Fatura)s
     */
    public void remove(ParcelaVO s) {
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
		
		case SELECAO: 		return Boolean.class; 
		case VENCIMENTO:    return Date.class;
		case VALOR:         return Double.class;
		default:            return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return columnIndex == SELECAO; 
    }

    /**
     * Alimenta a tabela com uma lista de faturas.
     * @param (List<Fatura>)listaFaturas
     */
    public void loadData(List<ParcelaVO> listaFatura){
    	if(listaFatura!= null){
    		removeAll();
    		for (ParcelaVO fat : listaFatura) {
				fat.setSelecionada(new Boolean(false));
    			dataVector.add(fat);
			}
    		fireTableDataChanged();
    	}
    }
    
    private String formataPreco(double valor)
    {
    	DecimalFormat df = new DecimalFormat("#,###,###.00");
		df.setCurrency(Currency.getInstance(new Locale("pt","br")));
		String preco = "R$ " + df.format(valor);
		return preco;
    }
    
    public List<ParcelaVO> getList()
    {
    	return dataVector;
    }
}
