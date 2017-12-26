package br.com.saodimas.view.components.table.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ObitoVO;

@SuppressWarnings("serial")
public class ObitoParticularConsultaTableModel extends DefaultTableModel {
	public static final int OBITO = -1;
	public static final int TIPO = 0;
	public static final int DATAOBITO = 1;
	public static final int NOME = 2;
	public static final int CAUSA = 3;
	public static final int ATESTADO = 4;
	public static final int CIDADE = 5;
	
	private String[] columnNames = {"Tipo","Data do �bito","Nome","Causa","Atestado", "Cidade"};
	private Vector <ObitoVO> dataVector;

	public ObitoParticularConsultaTableModel() {
		dataVector = new Vector<ObitoVO>();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return (dataVector != null)? dataVector.size() : 0;
	}

	
	@SuppressWarnings("rawtypes")
	@Override
	public Vector getDataVector() {
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
		case TIPO : return dataVector.get(row).getApolice().getNumeroContrato() + "";
		case ATESTADO:	return dataVector.get(row).getNumeroAtestado();
		case NOME:		return dataVector.get(row).getAssociado().getNome();
		case DATAOBITO:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(dataVector.get(row).getData());
		case CAUSA:		return dataVector.get(row).getCausa();
		case CIDADE : return dataVector.get(row).getApolice().getCidade().getNome() + "";
		default: return dataVector.get(row);
		}
	}

	public void setValueAt(Object value, int row, int col) {
		fireTableCellUpdated(row, col);
	}

	public void add(ObitoVO p) {
		dataVector.add(p);
		fireTableDataChanged();
	}

	/**
	 * Remove um �bito da tabela.
	 * @param (Obito)p
	 */
	public void remove(ObitoVO p) {
		dataVector.remove(p);
		fireTableDataChanged();
	}

	/**
	 * Remove um �bito da tabela na posi��o espec�ficada.
	 * @param (int)row
	 */
	public void removeAt(int pos){
		dataVector.remove(pos);
		fireTableDataChanged();
	}

	/**
	 * Remove todos �bitos da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case NOME:			return String.class;
		case DATAOBITO:		return Date.class;
		default: return String.class;
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false; 
	}

	/**
	 * Alimenta a tabela com uma lista de �bitos.
	 * @param (List<Obito>)listaObitos
	 */
	public void loadData(List<ObitoVO> listaObitos){
		if(listaObitos!= null){
    		dataVector.clear();
    		dataVector.addAll(listaObitos);
    		fireTableDataChanged();
    	}
	}
}
