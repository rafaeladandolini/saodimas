package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;

public class EmprestimoEquipamentoVO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	public static final String ID_EMPRESTIMO_EQUIPAMENTO = "ID_EMPRESTIMO_EQUIPAMENTO";
	public static final String ID_EQUIPAMENTO = "ID_EQUIPAMENTO";
	public static final String ID_ASSOCIADO = "ID_ASSOCIADO";
	public static final String DATA_EMPRESTIMO = "DATA_EMPRESTIMO";
	public static final String DATA_PREVISTA_DEVOLUCAO = "DATA_PREVISTA_DEVOLUCAO";
	public static final String DATA_DEVOLUCAO = "DATA_DEVOLUCAO";
	public static final String ID_APOLICE = "ID_APOLICE";
	public static final String OBSERVACAO = "OBSERVACAO";
	
	
	private Integer id;
	private AssociadoVO associado;
	private EquipamentoVO equipamentoVO;
	private Date dataEmpresatimo;
	private Date dataPrevisaDevolucao;
	private Date dataDevolucao;
	private ApoliceVO apolice;
	private String observacao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EquipamentoVO getEquipamentoVO() {
		return equipamentoVO;
	}
	public void setEquipamentoVO(EquipamentoVO equipamentoVO) {
		this.equipamentoVO = equipamentoVO;
	}
	
	public AssociadoVO getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}
	public Date getDataEmpresatimo() {
		return dataEmpresatimo;
	}
	public void setDataEmpresatimo(Date dataEmpresatimo) {
		this.dataEmpresatimo = dataEmpresatimo;
	}
	public Date getDataPrevisaDevolucao() {
		return dataPrevisaDevolucao;
	}
	public void setDataPrevisaDevolucao(Date dataPrevisaDevolucao) {
		this.dataPrevisaDevolucao = dataPrevisaDevolucao;
	}
	public Date getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public ApoliceVO getApolice() {
		return apolice;
	}
	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
}
