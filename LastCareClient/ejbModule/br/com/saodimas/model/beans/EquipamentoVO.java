package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;

public class EquipamentoVO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	public static final String ID_EQUIPAMENTO = "ID_EQUIPAMENTO";
	public static final String DESCRICAO = "DESCRICAO";
	public static final String DATA_AQUISICAO = "DATA_AQUISICAO";
	public static final String MODELO = "MODELO" ;
	public static final String VALOR = "VALOR";
	public static final String OBSERVACAO = "OBSERVACAO";
	public static final String STATUS = "STATUS";
	public static final String QUANTIDADE = "QUANTIDADE";
		
	private Integer id;
	private String descricao;
	private Date dataAquisicao;
	private String modelo;
	private Double valor;
	private String observacao;
	private String status;
	private int quantidade;
	private int qtdeEmprestimo;
	
	public int getQtdeEmprestimo() {
		return qtdeEmprestimo;
	}
	public void setQtdeEmprestimo(int qtdeEmprestimo) {
		this.qtdeEmprestimo = qtdeEmprestimo;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataAquisicao() {
		return dataAquisicao;
	}
	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
	
}
