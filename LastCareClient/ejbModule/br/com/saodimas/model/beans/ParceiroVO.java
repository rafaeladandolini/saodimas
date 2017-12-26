package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;

public class ParceiroVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String ID_PARCEIRO = "ID_PARCEIRO";
	public static final String DESCRICAO = "DESCRICAO";
	public static final String RESPONSAVEL = "RESPONSAVEL";
	public static final String DATA_INICIO = "DATA_INICIO";
	public static final String ENDERECO = "ENDERECO";
	public static final String TELEFONE = "TELEFONE";
	public static final String ID_CIDADE = "ID_CIDADE";
	public static final String OBSERVACAO = "OBSERVACAO";
	public static final String STATUS = "STATUS";
	
	
	private Integer id;
	private String descricao;
	private String responsavel;
	private String endereco;
	private String telefone;
	private CidadeVO cidade;
	private String observacao;
	private String status;
	private Date dataInicio;
	
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
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public CidadeVO getCidade() {
		return cidade;
	}
	public void setCidade(CidadeVO cidade) {
		this.cidade = cidade;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@Override
	public String toString() {
		return getDescricao();
	}
	
	
}
	