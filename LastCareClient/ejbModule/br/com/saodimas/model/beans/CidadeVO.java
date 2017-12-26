package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.CidadeVO;


public class CidadeVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "CIDADE";
	public static final String ID_CIDADE = "ID_CIDADE";
	public static final String NOME_CIDADE = "NOME_CIDADE";
	public static final String ESTADO = "ESTADO";
	public static final String STATUS = "STATUS";
	
	private Integer id;
	private String nome;
	private String estado;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
	
	@Override
	public boolean equals(Object cidade) {
		if(cidade == null)return false;
		
		return ((CidadeVO)cidade).getId() == this.getId() || 
				(((CidadeVO)cidade).getNome().toUpperCase().compareTo(this.getNome().toUpperCase()) == 0 && 
					((CidadeVO)cidade).getEstado().toUpperCase().compareTo(this.getEstado().toUpperCase()) == 0); 
	}
}