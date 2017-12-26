package br.com.saodimas.model.beans;

import java.io.Serializable;

public class FornecedorVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String TABELA = "FORNECEDOR";
	public static final String ID_FORNECEDOR = "ID_FORNECEDOR";
	public static final String NOME = "NOME";
	public static final String CONTATO = "CONTATO";
		
	private Integer id;
	private String nome;
	private String contato;
	
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
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
	
	@Override
	public boolean equals(Object serv) {
		if(serv == null) return false;
		return ((FornecedorVO)serv).getId() == this.getId(); 
	}
	
}
