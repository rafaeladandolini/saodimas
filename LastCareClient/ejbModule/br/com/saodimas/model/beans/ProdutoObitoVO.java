package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.model.beans.ProdutoVO;

public class ProdutoObitoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABELA = "PRODUTO_OBITO";
	public static final String ID_PRODUTO_OBITO = "ID_PRODUTO_OBITO";
	public static final String ID_PRODUTO = "ID_PRODUTO";
	public static final String ID_OBITO = "ID_OBITO";
	public static final String QUANTIDADE = "QUANTIDADE";
	public static final String TOTAL = "TOTAL";
	public static final String VALOR = "VALOR";

	private Integer id;
	private ProdutoVO produto;
	private ObitoVO obito;
	private int quantidade;
	private double total;
	private Double valor;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}
	public ObitoVO getObito() {
		return obito;
	}
	public void setObito(ObitoVO obito) {
		this.obito = obito;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return this.getProduto() != null ? this.getProduto().getNome() + " (" + this.getQuantidade() + ")" : "";
	}
	
	@Override
	public boolean equals(Object sp) {
		if(sp == null) return false;
		return ((ProdutoObitoVO)sp).getId() == this.getId(); 
	}	
}
