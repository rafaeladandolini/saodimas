package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FaturaVO implements Serializable, Comparable<FaturaVO>{

	private static final long serialVersionUID = 1L;
	public static final String TABELA = "FATURA";
	public static final String ID_FATURA = "ID_FATURA";
	public static final String ID_APOLICE = "ID_APOLICE";
	public static final String NUMERO_FATURA = "NUMERO_FATURA";
	public static final String DATA_EMISSAO = "DATA_EMISSAO";
	public static final String DATA_VENCIMENTO = "DATA_VENCIMENTO";
	public static final String DATA_PAGAMENTO = "DATA_PAGAMENTO";
	public static final String DATA_BAIXA = "DATA_BAIXA";
	public static final String VALOR = "VALOR";
	public static final String VALOR_MULTA = "VALOR_MULTA";
	public static final String VALOR_DESCONTO = "VALOR_DESCONTO";
	public static final String VALOR_PARCEIRO = "VALOR_PARCEIRO";
	public static final String STATUS = "STATUS";
	public static final String ID_COLABORADOR = "ID_COLABORADOR";
	public static final String ID_PARCEIRO = "ID_PARCEIRO";
	public static final String PAGAMENTO_SEM_CARNE = "PAGAMENTO_SEM_CARNE";

	private Integer id;
	private ApoliceVO apolice;
	private int numeroFatura;
	private Date dataEmissao;
	private Date dataVencimento;
	private Date dataPagamento;
	private Date dataBaixa;
	private double valor;
	private double valorMulta;
	private double valorDesconto;
	private double valorParceiro;
	private String status;
	private ColaboradorVO colaborador;
	private double valorTotal;
	private boolean selecionada;
	private String isPagamentoSemCarne;

	private ParceiroVO parceiro;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
	}

	public int getNumeroFatura() {
		return numeroFatura;
	}

	public void setNumeroFatura(int numeroFatura) {
		this.numeroFatura = numeroFatura;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}
	
	

	
	public String getIsPagamentoSemCarne() {
		return isPagamentoSemCarne;
	}

	public void setIsPagamentoSemCarne(String isPagamentoSemCarne) {
		this.isPagamentoSemCarne = isPagamentoSemCarne;
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

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isSelecionada() {
		return selecionada;
	}

	public void setSelecionada(boolean selecionada) {
		this.selecionada = selecionada;
	}
	
	public ColaboradorVO getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorVO colaborador) {
		this.colaborador = colaborador;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return this.getApolice() != null ? this.getApolice().getNumeroContrato() + " - " + sdf.format(this.getDataVencimento()) : "";
	}
	
	@Override
	public boolean equals(Object fatura) {
		if(fatura == null) return false;
		return ((FaturaVO)fatura).getId() == this.getId(); 
	}

	public double getValorParceiro() {
		return valorParceiro;
	}

	public void setValorParceiro(double valorParceiro) {
		this.valorParceiro = valorParceiro;
	}

	public ParceiroVO getParceiro() {
		return parceiro;
	}

	public void setParceiro(ParceiroVO parceiro) {
		this.parceiro = parceiro;
	}

	public int compareTo(FaturaVO arg0) {
		return new Integer(numeroFatura+"").compareTo(new Integer(arg0.getNumeroFatura()+""));
	}
	
}