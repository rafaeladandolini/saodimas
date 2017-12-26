package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.ParcelamentoVO;


public class ParcelamentoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "PARCELAMENTO";
	public static final String ID_PARCELAMENTO = "ID_PARCELAMENTO";
	public static final String DESCRICAO = "DESCRICAO";
	public static final String QUANTIDADE = "QUANTIDADE";

	
	private Integer id;
	private String descricao;
	private int quantidade;


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
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public boolean equals(Object tipo) {
		if(tipo == null || !(tipo instanceof ParcelamentoVO)) return false;
		return ((ParcelamentoVO)tipo).getId() == this.getId() || ((ParcelamentoVO)tipo).getDescricao().toUpperCase().compareTo(this.getDescricao().toUpperCase()) == 0; 
	}
	

}