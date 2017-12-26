package br.com.saodimas.model.beans;

import java.io.Serializable;

public class GrupoTrabalhoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABELA = "COLABORADOR";
	public static final String ID_GRUPO_TRABALHO = "ID_GRUPO_TRABALHO";
	public static final String NOME_GRUPO = "NOME_GRUPO";

	private Integer id;
	private String nomeGrupo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	@Override
	public boolean equals(Object col) {
		if (col == null)
			return false;
		if (col instanceof GrupoTrabalhoVO) {
			return ((GrupoTrabalhoVO) col).getId() == this.getId()
					|| ((GrupoTrabalhoVO) col).getNomeGrupo().toUpperCase()
							.compareTo(this.getNomeGrupo().toUpperCase()) == 0;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nomeGrupo;
	}
}
