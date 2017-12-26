package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.FormaPagamentoVO;


public class FormaPagamentoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "FORMA_PAGAMENTO";
	public static final String ID_FORMA_PAGAMENTO = "ID_FORMA_PAGAMENTO";
	public static final String DESCRICAO = "DESCRICAO";

	
	private Integer id;
	private String descricao;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.getDescricao();
	}

	@Override
	public boolean equals(Object tipo) {
		if(tipo == null || !(tipo instanceof FormaPagamentoVO)) return false;
		return ((FormaPagamentoVO)tipo).getId() == this.getId() ;
	}
	

}