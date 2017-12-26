package br.com.saodimas.view.components.table.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;

@SuppressWarnings("serial")
public class EmprestimoEquipamentoApoliceTableModel extends AbstractTableModel{
	
	public static final int ID_EQUIPAMENTO = 0;
	public static final int DATA_EMPRESTIMO = 1;
	public static final int DATA_PREVISTA_DEVOLUCAO = 2;
	public static final int EMPRESTADO_PARA = 3;
	public static final int OBSERVACAO = 4;
		
	private String[] columnNames = {"Equipamento","Data Emprestimo","Data Prevista Devolução","Emprestado para","Observação"};
	private Vector <EmprestimoEquipamentoVO> dataVector;
	
	public EmprestimoEquipamentoApoliceTableModel() {
		dataVector = new Vector<EmprestimoEquipamentoVO>();
	}
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return dataVector.size();
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}
	
	public Object getSelectedValue(int row){
		return dataVector.get(row);
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
		case ID_EQUIPAMENTO: return dataVector.get(row).getEquipamentoVO().getDescricao(); 
		case DATA_EMPRESTIMO: return dataVector.get(row).getDataEmpresatimo() == null ? "": new SimpleDateFormat("dd/MM/yyyy").format(dataVector.get(row).getDataEmpresatimo());
		case DATA_PREVISTA_DEVOLUCAO: return dataVector.get(row).getDataPrevisaDevolucao() == null ? "": new SimpleDateFormat("dd/MM/yyyy").format(dataVector.get(row).getDataPrevisaDevolucao());
		case OBSERVACAO: return dataVector.get(row).getObservacao();
		case EMPRESTADO_PARA: return dataVector.get(row).getAssociado() != null ?dataVector.get(row).getAssociado().getNome() : "";
			
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(EmprestimoEquipamentoVO dep) {
    	dataVector.add(dep);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela.
     * @param (Colaborador)c
     */
    public void remove(EmprestimoEquipamentoVO c) {
    	dataVector.remove(c);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela na posição específicada.
     * @param (int)row
     */
    public void removeAt(int pos){
    	dataVector.remove(pos);
        fireTableDataChanged();
    }

	/**
	 * Remove todos colaboradores da tabela.
	 */
	public void removeAll(){
		dataVector.clear();
		fireTableDataChanged();
	}

	public Class<?> getColumnClass(int column) {
		switch (column) {
		case ID_EQUIPAMENTO:            return String.class;
		case EMPRESTADO_PARA:           return String.class;
		case DATA_EMPRESTIMO:	        return Date.class;
		case DATA_PREVISTA_DEVOLUCAO:	return Date.class;
		case OBSERVACAO:	            return String.class;
		default:                        return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de colaboradores.
     * @param (List<Colaborador>)listaColaboradores
     */
    public void loadData(List<EmprestimoEquipamentoVO> listaColaboradores){
    	if(listaColaboradores!= null){
    		dataVector.clear();
    		dataVector.addAll(listaColaboradores);
    		fireTableDataChanged();
    	}
    	
    }
        
}


