package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoPlanoVO;
import br.com.saodimas.model.beans.ProdutoVO;

public class ProdutoPlanoVO implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String TABELA = "PRODUTO_PLANO";
	public static final String ID_PRODUTO_PLANO = "ID_PRODUTO_PLANO";
	public static final String ID_PRODUTO = "ID_PRODUTO";
	public static final String ID_PLANO = "ID_PLANO";
	public static final String QUANTIDADE = "QUANTIDADE";
	
	private Integer id;
	private ProdutoVO produto;
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
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}
	
	@Override
	public String toString() {
		return this.getProduto() != null ? this.getProduto().getNome() + " (" + this.getQuantidade() + ")" : "";
	}
	
	@Override
	public boolean equals(Object sp) {
		if(sp == null) return false;
		return ((ProdutoPlanoVO)sp).getId() == this.getId(); 
	}
}
