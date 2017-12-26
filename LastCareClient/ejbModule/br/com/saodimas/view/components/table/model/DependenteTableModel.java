package br.com.saodimas.view.components.table.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import br.com.saodimas.model.beans.AssociadoVO;

/**
 * Classe que define a apresentação da Jtable que representa os dependentes disponíveis. 
 * @author daniel/rafaela
 *
 */
@SuppressWarnings("serial")
public class DependenteTableModel extends DefaultTableModel{
	
	public static final int DEPENDENTE = -1;
	public static final int NOME = 0;
	public static final int DATA_NASC = 1;
	public static final int GENERO = 2;
	public static final int CPF = 3;
	public static final int PARENTESCO = 4;
	public static final int DATA_ADESAO = 5;
	public static final int OK = 6;
	public static final int CARTAO_NOVO = 7;
	
	
	private ImageIcon iconPositovo = new ImageIcon("imagens/sem_carencia.png", "Sem carência");
	private ImageIcon iconNegativo = new ImageIcon("imagens/com_carencia.png", "Com carência");
	
	private ImageIcon iconCartao = new ImageIcon("imagens/cartao_novo.png", "Com cartão");
	
		
	private String[] columnNames = {"Nome","Data Nasc.", "Sexo", "CPF", "Rel. Titular", "Data Adesão", "Carência","Cartão Novo",};
	private Vector <AssociadoVO> dataVector;
	
	public DependenteTableModel() {
		dataVector = new Vector<AssociadoVO>();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return dataVector == null ? 0 : dataVector.size();
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	public Object getSelectedValue(int row){
		return dataVector.get(row);
	}
	
	@Override
	public Vector<AssociadoVO> getDataVector() {
		return dataVector;
	}

	
	public List<AssociadoVO> getDataSet() {
		List<AssociadoVO> assoSet = new ArrayList<AssociadoVO>();
		for (AssociadoVO associadoVO : dataVector) {
			assoSet.add(associadoVO);
		}
		return assoSet;
	}	
	
	public Object getValueAt(int row, int col) {
    	SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

    	boolean isPositivo = true;
    	Date dataAdesao = dataVector.get(row).getDataAdesao(); 
    	if(dataAdesao != null)
    	{
    		Date dataAtual60 = null;
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.DAY_OF_MONTH, -60);
    		dataAtual60 = cal.getTime();
    		
    		if(dataAtual60.before(dataAdesao))
    			isPositivo = false;
    	}
    	    	
		switch (col) {
		case NOME:			return dataVector.get(row).getNome();
		case CPF:           return dataVector.get(row).getCPF();
		case DATA_NASC:		return dataVector.get(row).getDataNascimento() != null ?f.format(dataVector.get(row).getDataNascimento()) : "";
		case GENERO:		return dataVector.get(row).getSexo();
		case PARENTESCO:	return dataVector.get(row).getRelacao();
		case DATA_ADESAO:   return (dataVector.get(row).getDataAdesao() != null ? f.format(dataVector.get(row).getDataAdesao()) : "");
		case OK:			return isPositivo ? iconPositovo : iconNegativo ;
		case CARTAO_NOVO:   return dataVector.get(row).getQtdeCartao() > 0 ? iconCartao : null;
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(AssociadoVO dep) {
    	dataVector.add(dep);
        fireTableDataChanged();
    }
    
    /**
     * Remove um dependente da tabela.
     * @param (Dependente)dep
     */
    public void remove(AssociadoVO dep) {
    	dataVector.remove(dep);
        fireTableDataChanged();
    }
    
    /**
     * Remove um dependente da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }
    
	/**
	 * Remove todos dependentes da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case NOME:			return String.class;
		case DATA_NASC:		return String.class;
		case GENERO:		return String.class;
		case PARENTESCO:	return String.class;
		case DATA_ADESAO:	return String.class;
		case OK:            return ImageIcon.class;
		case CARTAO_NOVO:   return ImageIcon.class;
		default: return String.class;
		}
	}
	
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de dependentes.
     * @param (List<Dependente>)listaDependentes
     */
    public void loadData(List<AssociadoVO> listaDependentes){
    	if(listaDependentes!= null){
    		dataVector.clear();
    		dataVector.addAll(listaDependentes);
    		fireTableDataChanged();
    	}
    }
}
