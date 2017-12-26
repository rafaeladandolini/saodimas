package br.com.saodimas.model.beans;

import java.io.Serializable;

public class ServicoPlanoVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TABELA = "SERVICO_PLANO";
	public static final String ID_SERVICO_PLANO = "ID_SERVICO_PLANO";
	public static final String ID_SERVICO = "ID_SERVICO";
	public static final String ID_PLANO = "ID_PLANO";
	public static final String QUANTIDADE = "QUANTIDADE";
	

	private Integer id;
	private ServicoVO servico;
	private PlanoVO plano;
	private int quantidade;

	public PlanoVO getPlano() {
		return plano;
	}
	public void setPlano(PlanoVO plano) {
		this.plano = plano;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ServicoVO getServico() {
		return servico;
	}
	public void setServico(ServicoVO servico) {
		this.servico = servico;
	}
	
	@Override
	public String toString() {
		return this.getServico() != null ? this.getServico().getNome() + " (" + this.getQuantidade() + ")" : "";
	}
	
	@Override
	public boolean equals(Object sp) {
		if(sp == null) return false;
		return ((ServicoPlanoVO)sp).getId() == this.getId(); 
	}
}
