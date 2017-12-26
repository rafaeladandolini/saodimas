package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;

public class ParcelaVO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "FATURA";
	public static final String ID_PARCELA = "ID_PARCELA";
	public static final String NUMERACAO = "NUMERACAO";
	public static final String DATA_VENCIMENTO = "DATA_VENCIMENTO";
	public static final String VALOR = "VALOR";
	public static final String ID_VENDA = "ID_VENDA";
	
	private Integer id;
	private String numeracao;
	private Date dataVencimento;
	private Double valor;
	private boolean selecionada;
	private VendaVO venda;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeracao() {
		return numeracao;
	}
	public void setNumeracao(String numeracao) {
		this.numeracao = numeracao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public boolean isSelecionada() {
		return selecionada;
	}
	public void setSelecionada(boolean selecionada) {
		this.selecionada = selecionada;
	}
	
	public VendaVO getVenda() {
		return venda;
	}
	public void setVenda(VendaVO venda) {
		this.venda = venda;
	}
	@Override
	public boolean equals(Object parcela) {
		if(parcela == null) return false;
		return ((ParcelaVO)parcela).getId() == this.getId(); 
	}
	
}
