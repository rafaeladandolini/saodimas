package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;

import br.com.saodimas.model.beans.CarteirinhaVO;

/*
 * NAO USA ESSA CLASSE AINDA>
 */
public class CarteirinhaVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "CARTEIRINHA";
	public static final String ID_CARTEIRINHA = "ID_CARTEIRINHA";
	public static final String DATA_IMPRESSAO = "DATA_IMPRESSAO";
	public static final String ID_ASSOCIADO = "ID_ASSOCIADO";

	private Integer id;
	private Date dataImpressao;
	private AssociadoVO associado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataImpressao() {
		return dataImpressao;
	}
	public void setDataImpressao(Date dataImpressao) {
		this.dataImpressao = dataImpressao;
	}
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}	


	
	

}