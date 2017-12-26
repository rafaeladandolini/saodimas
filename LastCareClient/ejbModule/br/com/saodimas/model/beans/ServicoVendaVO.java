package br.com.saodimas.model.beans;


import java.io.Serializable;


public class ServicoVendaVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String TABELA = "SERVICO_VENDA";
	public static final String ID_SERVICO_VENDA = "ID_SERVICO_VENDA";
	public static final String ID_SERVICO = "ID_SERVICO";
	public static final String ID_VENDA = "ID_VENDA";
	public static final String QUANTIDADE = "QUANTIDADE";
	public static final String TOTAL = "TOTAL";
	public static final String VALOR = "VALOR";
	
	private Integer id;
	private ServicoVO servico;
	private VendaVO venda;
	private int quantidade;
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
	public VendaVO getVenda() {
		return venda;
	}
	public void setVenda(VendaVO venda) {
		this.venda = venda;
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
	
	@Override
	public String toString() {
		return this.getServico() != null ? this.getServico().getNome() + " (" + this.getQuantidade() + ")" : "";
	}
	
	@Override
	public boolean equals(Object so) {
		if(so == null) return false;
		return ((ServicoVendaVO)so).getId() == this.getId(); 
	}
}
