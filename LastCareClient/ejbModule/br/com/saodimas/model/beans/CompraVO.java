package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class CompraVO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABELA = "COMPRA";
	public static final String ID_COMPRA = "ID_COMPRA";
	public static final String DATA_COMPRA = "DATA_COMPRA";
	public static final String DATA_ENTREGA = "DATA_ENTREGA";
	public static final String FORMA_PAGAMENTO = "FORMA_PAGAMENTO";
	public static final String DESCRICAO = "DESCRICAO";
	public static final String VENDEDOR = "VENDEDOR";
	public static final String OBSERVACAO = "OBSERVACAO";
	public static final String ID_FORNECEDOR = "ID_FORNECEDOR";
	
	private Integer id;
	private Date dataCompra;
	private Date dataEntrega;
	private String formaPagamento;
	private String descricao;
	private String vendedor;
	private String observacao;
	private FornecedorVO fornecedor;
	private List<ProdutoCompraVO> produtos;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public FornecedorVO getFornecedor() {
		return fornecedor;
	}
	public void setEmpresa(FornecedorVO fornecedor) {
		this.fornecedor = fornecedor;
	}
	public List<ProdutoCompraVO> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<ProdutoCompraVO> produtos) {
		this.produtos = produtos;
	}
	
	
}

