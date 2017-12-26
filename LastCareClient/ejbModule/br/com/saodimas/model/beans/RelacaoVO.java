package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.RelacaoVO;

public class RelacaoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String TABELA = "RELACAO";
	public static final String ID_RELACAO = "ID_RELACAO";
	public static final String DESCRICAO = "DESCRICAO";
	public static final String TIPO_RELACAO = "TIPO_RELACAO";
	public static final String STATUS = "STATUS";
	public static final String ID_ASSOCIADO = "ID_ASSOCIALDO";
	
	private Integer id;
	private String descricao;
	private String tipoRelacao;
	private String status;
	private AssociadoVO associado;

	public AssociadoVO getAssociado() {
		return associado;
	}

	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(String tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}
	
	@Override
	public String toString() {
		return this.getDescricao();
	}
	
	@Override
	public boolean equals(Object rel) {
		if(rel == null) return false;
		return ((RelacaoVO)rel).getId() == this.getId() || ((RelacaoVO)rel).getDescricao().toUpperCase().compareTo(this.getDescricao().toUpperCase()) == 0; 
	}
}
