package br.com.saodimas.model.beans;

import java.io.Serializable;

public class ProdutoVendaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABELA = "PRODUTO_VENDA";
	
	public static final String ID_PRODUTO_VENDA = "ID_PRODUTO_VENDA";
	public static final String ID_VENDA = "ID_VENDA";
	public static final String ID_PRODUTO = "ID_PRODUTO";
	public static final String QUANTIDADE = "QUANTIDADE";
	public static final String VALOR = "VALOR";
	public static final String TOTAL = "TOTAL";

	private Integer id;
	private VendaVO venda;
	private ProdutoVO produto;
	private int quantidade;
	private Double valor;
	private Double total;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public VendaVO getVenda() {
		return venda;
	}
	public void setVenda(VendaVO venda) {
		this.venda = venda;
	}
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	
}
