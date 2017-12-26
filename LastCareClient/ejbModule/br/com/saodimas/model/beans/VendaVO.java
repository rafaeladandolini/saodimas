package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class VendaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABELA = "VENDA";
	
	public static final String ID_VENDA = "ID_VENDA";
	public static final String DATA_VENDA = "DATA_VENDA";
	public static final String ID_CLIENTE = "ID_CLIENTE";
	public static final String ID_COLABORADOR = "ID_COLABORADOR";
	public static final String ID_FORMA_PAGAMENTO = "ID_FORMA_PAGAMENTO";
	public static final String ID_PARCELAMENTO = "ID_PARCELAMENTO";
	public static final String VALOR = "VALOR";
	public static final String VALOR_DESCONTO = "VALOR_DESCONTO";
	public static final String VALOR_ACRESCIMO = "VALOR_ACRESCIMO";
	public static final String OBSERVACAO = "OBSERVACAO";
	public static final String STATUS_VENDA = "STATUS_VENDA"; 
	
	public static final String ID_OBITO = "ID_OBITO";
	
		
	private Integer id;
	private Date dataVenda;
	private ClienteVO cliente;
	private ColaboradorVO colaborador;
	private String descricao;
	private FormaPagamentoVO formaPagamento;
	private ParcelamentoVO parcelamento;
	private Double valor;
	private Double desconto;
	private Double acrescimo;
	private String observacao;
	private ObitoVO obito;
	private String statusVenda;
	
	private List<ProdutoVendaVO> produtos;
	private List<ServicoVendaVO> servicos;
	private List<ParcelaVO> parcelas;
	
	
	
	public String getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(String statusVenda) {
		this.statusVenda = statusVenda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public ColaboradorVO getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorVO colaborador) {
		this.colaborador = colaborador;
	}

	public List<ProdutoVendaVO> getProdutos() {
		return produtos;
	}

	public void setServicos(List<ServicoVendaVO> servicos) {
		this.servicos = servicos;
	}

	public List<ServicoVendaVO> getServicos() {
		return servicos;
	}

	public void setProdutos(List<ProdutoVendaVO> produtos) {
		this.produtos = produtos;
	}
	
	public String getDescricao(){return this.descricao;}
	public void setDescricao(String descricao){this.descricao = descricao;}

	

	public FormaPagamentoVO getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamentoVO formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public ParcelamentoVO getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(ParcelamentoVO parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(Double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ObitoVO getObito() {
		return obito;
	}

	public void setObito(ObitoVO obito) {
		this.obito = obito;
	}

	public List<ParcelaVO> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaVO> parcelas) {
		this.parcelas = parcelas;
	}

		
}

