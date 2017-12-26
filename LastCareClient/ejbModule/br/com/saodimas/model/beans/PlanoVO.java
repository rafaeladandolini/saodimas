package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Set;

/**
 * Classe representante do plano
 * @author Rafaela
 * @date 31-07-2008
 */

public class PlanoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String TABELA = "PLANO";
	public static final String ID_PLANO = "ID_PLANO";
	public static final String DESCRICAO = "DESCRICAO";
	public static final String LIMITE_ASSOCIADO = "LIMITE_ASSOCIADO";
	public static final String CARENCIA = "CARENCIA";
	public static final String VALOR_MENSALIDADE = "VALOR_MENSALIDADE";
	public static final String EMPRESARIAL = "EMPRESARIAL";
	public static final String STATUS = "STATUS";
	public static final String ASSOCIADO_EXTRA = "ASSOCIADO_EXTRA";
	
	
	private Integer id;
	private String descricao;
	private int limiteAssociado;
	private int carencia;
	private double mensalidade;
	private boolean empresarial;
	private String status;
	private int associado_extra;
	private Set<ProdutoPlanoVO> produtos;
	private Set<ServicoPlanoVO> servicos;
		

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

	public int getLimiteAssociado() {
		return limiteAssociado;
	}

	public void setLimiteAssociado(int limiteAssociado) {
		this.limiteAssociado = limiteAssociado;
	}

	public int getCarencia() {
		return carencia;
	}

	public void setCarencia(int carencia) {
		this.carencia = carencia;
	}

	public double getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(double mensalidade) {
		this.mensalidade = mensalidade;
	}

	public boolean isEmpresarial() {
		return empresarial;
	}

	public void setEmpresarial(boolean empresarial) {
		this.empresarial = empresarial;
	}

	public int getAssociado_extra() {
		return associado_extra;
	}

	public void setAssociado_extra(int associado_extra) {
		this.associado_extra = associado_extra;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public Set<ProdutoPlanoVO> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<ProdutoPlanoVO> produtos) {
		this.produtos = produtos;
	}

	public Set<ServicoPlanoVO> getServicos() {
		return servicos;
	}

	public void setServicos(Set<ServicoPlanoVO> servicos) {
		this.servicos = servicos;
	}

	@Override
	public String toString() {
		return getDescricao();
	}
	
	@Override
	public boolean equals(Object serv) {
		if(serv == null) return false;
		return ((PlanoVO)serv).getId() == this.getId() || ((PlanoVO)serv).getDescricao().toUpperCase().compareTo(this.getDescricao().toUpperCase()) == 0; 
	}
}
