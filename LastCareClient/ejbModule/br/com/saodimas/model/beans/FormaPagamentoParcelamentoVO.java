package br.com.saodimas.model.beans;

import java.io.Serializable;

public class FormaPagamentoParcelamentoVO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	public static final String ID_FORMA_PAGAMENTO_PARCELAMENTO = "ID_FORMA_PAGAMENTO_PARCELAMENTO";
	public static final String ID_FORMA_PAGAMENTO = "ID_FORMA_PAGAMENTO";
	public static final String ID_PARCELAMENTO = "ID_PARCELAMENTO";

	
	
	private Integer id;
	private FormaPagamentoVO formaPagamento;
	private ParcelamentoVO parcelamento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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

	
	
	
	
}
