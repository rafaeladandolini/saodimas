package br.com.saodimas.model.beans;

import java.io.Serializable;

public class ProdutoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String TIPO_PRODUTO_CONSUMO_INTERNO = "Consumo Interno";
	public static final String TIPO_PRODUTO_VENDA = "Venda";
	
	public static final String TABELA = "PRODUTO";
	public static final String ID_PRODUTO = "ID_PRODUTO";
	public static final String NOME = "NOME";
	public static final String DESCRICAO = "DESCRICAO";
	public static final String VALOR = "VALOR";
	public static final String STATUS = "STATUS";
	public static final String TIPO_PRODUTO = "TIPO_PRODUTO";
	public static final String REFERENCIA_VALOR = "REFERENCIA_VALOR";

	private Integer id;
	private String nome;
	private Double valor;
	private String descricao;
	private String status;
	private String tipoProduto; //tipodeprodutoparavendaouconsumo
	private String referenciaValor;
	
	
	
	public String getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
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
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
	
	@Override
	public boolean equals(Object prod) {
		if(prod == null) return false;
		return ((ProdutoVO)prod).getId() == this.getId() || ((ProdutoVO)prod).getNome().toUpperCase().compareTo(this.getNome().toUpperCase()) == 0; 
	}
}
