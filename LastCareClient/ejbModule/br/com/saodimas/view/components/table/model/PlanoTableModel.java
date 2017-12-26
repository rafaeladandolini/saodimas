package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.PlanoVO;

@SuppressWarnings("serial")
public class PlanoTableModel extends DefaultTableModel {
	public static final int DESCRICAO = 0;
	public static final int TIPO = 1;
	public static final int CARENCIA = 2;
	public static final int ASSOCIADOS = 3;
	public static final int ASSOCIADOS_EXTRA = 4;
	public static final int MENSALIDADE = 5;
	public static final int STATUS = 6;
	
	private String[] columnNames = {"Descrição","Tipo","Carência (dias)","Lim. Associados", "Lim. Extra Associados", "Mensalidade", "Status"};
	private Vector <PlanoVO> dataVector;
	
	public PlanoTableModel() {
		dataVector = new Vector<PlanoVO>();
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
		case DESCRICAO:			return " " + dataVector.get(row).getDescricao();
		case TIPO:			return " " + (dataVector.get(row).isEmpresarial() ? "Empresarial" : "Particular");
		case CARENCIA:		return dataVector.get(row).getCarencia();
		case ASSOCIADOS:	return dataVector.get(row).getLimiteAssociado();
		case ASSOCIADOS_EXTRA:	return dataVector.get(row).getAssociado_extra();
		case MENSALIDADE:
			DecimalFormat df = new DecimalFormat("#,###,###.00");
			df.setCurrency(Currency.getInstance(new Locale("pt","br")));
			return " R$ " + df.format(dataVector.get(row).getMensalidade());
		case STATUS:      return  " " + dataVector.get(row).getStatus();
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(PlanoVO p) {
    	dataVector.add(p);
        fireTableDataChanged();
    }
    
    /**
     * Remove um plano da tabela.
     * @param (Plano)p
     */
    public void remove(PlanoVO p) {
    	dataVector.remove(p);
        fireTableDataChanged();
    }
    
    /**
     * Remove um plano da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }

	/**
	 * Remove todos planos da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case DESCRICAO:			return String.class;
		case TIPO:			return String.class;
		case CARENCIA:		return String.class;
		case ASSOCIADOS:	return String.class;
		case ASSOCIADOS_EXTRA:	return String.class;
		case MENSALIDADE:	return String.class;
		case STATUS:        return String.class;
		default: return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de planos.
     * @param (List<Plano>)listaPlanos
     */
    public void loadData(List<PlanoVO> listaPlanos){
    	if(listaPlanos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaPlanos);
    		fireTableDataChanged();
    	}
    	else removeAll();
    }
}
