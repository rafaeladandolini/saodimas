package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.ServicoVO;

public class ServicoVO implements Serializable{

	public static final String TABELA = "SERVICO";
	public static final String ID_SERVICO = "ID_SERVICO";
	public static final String NOME = "NOME";
	public static final String VALOR = "VALOR";
	public static final String REFERENCIA_VALOR = "REFERENCIA_VALOR";
	public static final String STATUS = "STATUS";

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Double valor;
	private String referenciaValor;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getReferenciaValor() {
		return referenciaValor;
	}
	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
	
	@Override
	public boolean equals(Object serv) {
		if(serv == null) return false;
		return ((ServicoVO)serv).getId() == this.getId() || ((ServicoVO)serv).getNome().toUpperCase().compareTo(this.getNome().toUpperCase()) == 0; 
	}
}
