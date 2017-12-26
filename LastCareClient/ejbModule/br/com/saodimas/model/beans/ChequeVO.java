package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;


public class ChequeVO implements Serializable, Comparable<ChequeVO>{
	
	private static final long serialVersionUID = 1L;
	
	public static final String TABELA = "CHEQUE";
	public static final String ID_CHEQUE = "ID_CHEQUE";
	public static final String ID_CONTA = "ID_CONTA";
	public static final String NUMERO = "NUMERO";
	public static final String DATA_EMISSAO = "DATA_EMISSAO";
	public static final String DATA_VENCIMENTO = "DATA_VENCIMENTO";
	public static final String DATA_COMPENSACAO = "DATA_COMPENSACAO";
	public static final String IS_EMITIDO = "IS_EMITIDO";
	public static final String DESCRICAO = "DESCRICAO";
	public static final String ID_CLIENTE = "ID_CLIENTE";
	public static final String DATA_CADASTRO = "DATA_CADASTRO";
	public static final String ID_FORNECEDOR = "ID_FORNECEDOR";
	public static final String VALOR = "VALOR";

	
	private Integer id;
	private ContaVO conta;
	private String numero;
	private Date dataEmissao;
	private Date dataVencimento;
	private Date dataCompensacao;
	private boolean isEmitido;
	private String descricao;
	private ClienteVO cliente;
	private FornecedorVO fornecedor;
	private Date dataCadastro;
	private Double valor;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ContaVO getConta() {
		return conta;
	}

	public void setConta(ContaVO conta) {
		this.conta = conta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataCompensacao() {
		return dataCompensacao;
	}

	public void setDataCompensacao(Date dataCompensacao) {
		this.dataCompensacao = dataCompensacao;
	}

	public boolean isEmitido() {
		return isEmitido;
	}

	public void setEmitido(boolean isEmitido) {
		this.isEmitido = isEmitido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public FornecedorVO getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorVO fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public boolean equals(Object tipo) {
		if(tipo == null) return false;
		return ((ChequeVO)tipo).getId() == this.getId(); 
	}

	@Override
	public int compareTo(ChequeVO o) {
		return o.getId().compareTo(this.getId());
	}

}