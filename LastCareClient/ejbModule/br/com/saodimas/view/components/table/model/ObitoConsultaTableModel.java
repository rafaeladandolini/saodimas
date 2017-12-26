package br.com.saodimas.view.components.table.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.ObitoVO;

@SuppressWarnings("serial")
public class ObitoConsultaTableModel extends DefaultTableModel {
	public static final int OBITO = -1;
	
	public static final int NUMERO_ATENDIMENTO = 0;
	public static final int TIPO_ATENDIMENTO = 1;
	public static final int DATAOBITO = 2;
	public static final int APOLICE = 3;
	public static final int NOME = 4;
	public static final int CIDADE = 5;
	
	private String[] columnNames = {"Nº Atendimento","Tipo Atendimento","Data do Óbito","Apolice","Nome","Cidade"};
	private Vector <ObitoVO> dataVector;

	public ObitoConsultaTableModel() {
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
		case NUMERO_ATENDIMENTO: return dataVector.get(row).getId().toString();
		case TIPO_ATENDIMENTO: 
			return dataVector.get(row).getTipoAtendimento() != null? dataVector.get(row).getTipoAtendimento().getDescricao() : "";
		case APOLICE : 
			return dataVector.get(row).getApolice() != null ? dataVector.get(row).getApolice().getNumeroContrato() + "" : "";
		case NOME:		
			return dataVector.get(row).getAssociado() != null ? dataVector.get(row).getAssociado().getNome() : dataVector.get(row).getNomeFalecido();
		case DATAOBITO:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(dataVector.get(row).getData());
		case CIDADE : 
			if(dataVector.get(row).getApolice() != null) return  dataVector.get(row).getApolice().getCidade().getNome();
			else if (dataVector.get(row).getCidadeVelorio() != null) return dataVector.get(row).getCidadeVelorio().getNome();
			else return "";
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
	 * Remove um óbito da tabela.
	 * @param (Obito)p
	 */
	public void remove(ObitoVO p) {
		dataVector.remove(p);
		fireTableDataChanged();
	}

	/**
	 * Remove um óbito da tabela na posição específicada.
	 * @param (int)row
	 */
	public void removeAt(int pos){
		dataVector.remove(pos);
		fireTableDataChanged();
	}

	/**
	 * Remove todos óbitos da tabela.
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
	 * Alimenta a tabela com uma lista de óbitos.
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
