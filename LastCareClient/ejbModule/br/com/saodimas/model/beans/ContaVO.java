package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.ContaVO;


public class ContaVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "CONTA";
	public static final String ID_CONTA = "ID_CONTA";
	public static final String ID_BANCO = "ID_BANCO";
	public static final String AGENCIA = "AGENCIA";
	public static final String NUMERO = "NUMERO";
	public static final String NOME = "NOME";
	public static final String ID_TIPO_CONTA = "ID_TIPO_CONTA";

	
	private Integer id;
	private BancoVO banco;
	private String agencia;
	private String numero;
	private String nome;
	private TipoContaVO tipoConta;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public BancoVO getBanco() {
		return banco;
	}

	public void setBanco(BancoVO banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoContaVO getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoContaVO tipoConta) {
		this.tipoConta = tipoConta;
	}

	@Override
	public String toString() {
		return this.getBanco().getNomeBanco() + " - " + this.getNome();
	}

	@Override
	public boolean equals(Object tipo) {
		if(tipo == null) return false;
		return ((ContaVO)tipo).getId() == this.getId(); 
	}
	

}