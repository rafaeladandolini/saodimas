package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.CemiterioVO;


public class CemiterioVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "CEMITERIO";
	public static final String ID_CEMITEERIO = "ID_CEMITERIO";
	public static final String NOME = "NOME";
	public static final String ID_CIDADE = "ID_CIDADE";

	
	private Integer id;
	private String nome;
	private CidadeVO cidade;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public CidadeVO getCidade() {
		return cidade;
	}
	public void setCidade(CidadeVO cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return this.getNome();
	}

	@Override
	public boolean equals(Object cemiterio) {
		if(cemiterio == null) return false;
		return ((CemiterioVO)cemiterio).getId() == this.getId() || ((CemiterioVO)cemiterio).getNome().toUpperCase().compareTo(this.getNome().toUpperCase()) == 0; 
	}
	

}