package br.com.saodimas.model.beans;


import java.io.Serializable;


public class ServicoObitoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String TABELA = "SERVICO_OBITO";
	public static final String ID_SERVICO_OBITO = "ID_SERVICO_OBITO";
	public static final String ID_SERVICO = "ID_SERVICO";
	public static final String ID_OBITO = "ID_OBITO";
	public static final String QUANTIDADE = "QUANTIDADE";
	public static final String OBSERVACAO = "OBSERVACAO";
	public static final String TOTAL = "TOTAL";
	public static final String VALOR = "VALOR";
	
	private Integer id;
	private ServicoVO servico;
	private ObitoVO obito;
	private int quantidade;
	private String observacao;
	private double total;
	private Double valor;
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
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
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return this.getServico() != null ? this.getServico().getNome() + " (" + this.getQuantidade() + ")" : "";
	}
	
	@Override
	public boolean equals(Object so) {
		if(so == null) return false;
		return ((ServicoObitoVO)so).getId() == this.getId(); 
	}
}
