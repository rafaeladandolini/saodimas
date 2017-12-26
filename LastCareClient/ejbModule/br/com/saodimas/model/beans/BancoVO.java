package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.BancoVO;


public class BancoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "BANCO";
	public static final String ID_BANCO = "ID_BANCO";
	public static final String NOME_BANCO = "NOME_BANCO";
	public static final String CODIGO_BANCO = "CODIGO_BANCO";

	
	private Integer id;
	private String nomeBanco;
	private String codigoBanco;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	@Override
	public String toString() {
		return this.getCodigoBanco() + " - " + this.getNomeBanco();
	}

	@Override
	public boolean equals(Object tipo) {
		if(tipo == null) return false;
		return ((BancoVO)tipo).getId() == this.getId() || ((BancoVO)tipo).getNomeBanco().toUpperCase().compareTo(this.getNomeBanco().toUpperCase()) == 0; 
	}
	

}