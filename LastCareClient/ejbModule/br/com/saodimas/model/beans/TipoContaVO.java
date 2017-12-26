package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.TipoContaVO;


public class TipoContaVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "TIPO_CONTA";
	public static final String ID_TIPO_CONTA = "ID_TIPO_CONTA";
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
		if(tipo == null) return false;
		return ((TipoContaVO)tipo).getId() == this.getId() || ((TipoContaVO)tipo).getDescricao().toUpperCase().compareTo(this.getDescricao().toUpperCase()) == 0; 
	}
	

}