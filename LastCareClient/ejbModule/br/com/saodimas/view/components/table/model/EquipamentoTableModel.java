package br.com.saodimas.view.components.table.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.saodimas.model.beans.EquipamentoVO;

@SuppressWarnings("serial")
public class EquipamentoTableModel extends AbstractTableModel{
	
	public static final int DESCRICAO = 0;
	public static final int DATA_AQUISICAO = 1;
	public static final int MODELO = 2;
	public static final int VALOR = 3;
	public static final int OBSERVACAO = 4;
	public static final int QUANTIDADE = 5;
	public static final int QTDE_EMPRESTADA = 6;
	public static final int QTDE_DISPONIVEL = 7;
	public static final int STATUS = 8;
	
	private String[] columnNames = {"Descrição","Data Aquisição","Modelo","Valor","Observação","Quantidade","Qtde Emprestimos","Qtde Dísponível","Status"};
	private Vector <EquipamentoVO> dataVector;
	
	public EquipamentoTableModel() {
		dataVector = new Vector<EquipamentoVO>();
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
		case DESCRICAO: return dataVector.get(row).getDescricao(); 
		case DATA_AQUISICAO: return new SimpleDateFormat("dd/MM/yyyy").format(dataVector.get(row).getDataAquisicao());
		case MODELO: return dataVector.get(row).getModelo();
		case VALOR: return this.formataPreco(dataVector.get(row).getValor());
		case QUANTIDADE: return dataVector.get(row).getQuantidade();
		case QTDE_DISPONIVEL: return dataVector.get(row).getQuantidade() - dataVector.get(row).getQtdeEmprestimo() ;
		case QTDE_EMPRESTADA: return dataVector.get(row).getQtdeEmprestimo();
		case OBSERVACAO: return dataVector.get(row).getObservacao();
		case STATUS: return dataVector.get(row).getStatus();
			
		default: return dataVector.get(row);
		}
	}
	
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public void add(EquipamentoVO dep) {
    	dataVector.add(dep);
        fireTableDataChanged();
    }
    
    /**
     * Remove um colaborador da tabela.
     * @param (Colaborador)c
     */
    public void remove(EquipamentoVO c) {
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
		case DESCRICAO:		    return String.class;
		case DATA_AQUISICAO:	return Date.class;
		case MODELO:		    return String.class;
		case VALOR :	        return Double.class;
		case QUANTIDADE:        return Integer.class;
		case OBSERVACAO:	    return String.class;
		case STATUS:	        return String.class;
		default:                return String.class;
		}
	}
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false; 
    }

    /**
     * Alimenta a tabela com uma lista de colaboradores.
     * @param (List<Colaborador>)listaColaboradores
     */
    public void loadData(List<EquipamentoVO> listaColaboradores){
    	if(listaColaboradores!= null){
    		dataVector.clear();
    		dataVector.addAll(listaColaboradores);
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
}


