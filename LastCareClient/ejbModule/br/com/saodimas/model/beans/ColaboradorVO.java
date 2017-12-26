package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;

import br.com.saodimas.view.components.menu.BarraMenu;

public class ColaboradorVO implements Serializable{

	public static final String ADMINISTRADOR = "Administrador";
	public static final String FUNCIONARIO = "Funcionário";
	public static final String FUNCIONARIO_ESP = "Funcionário Especial";
	
	private static final long serialVersionUID = 1L;
	public static final String TABELA = "COLABORADOR";
	public static final String ID_COLABORADOR = "ID_COLABORADOR";
	public static final String NOME = "NOME";
	public static final String LOGIN = "LOGIN";
	public static final String SENHA = "SENHA";
	public static final String DATA_CADASTRO = "DATA_CASTRO";
	public static final String STATUS = "STATUS";
	public static final String MATRICULA = "MATRICULA";
	public static final String TIPO_COLABORADOR = "TIPO_COLABORADOR";
	
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private Date dataCadastro;
	private String status;
	private String matricula;
	private String tipoColaborador;
	//private Set<FaturaVO> faturas;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoColaborador(String tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
	
	public int getPerfilColoborador()
	{
		if(this.tipoColaborador.startsWith("A"))
			return BarraMenu.PERFIL_ADMIN;
		else if(this.tipoColaborador.contains("Esp"))
			return BarraMenu.PERFIL_FUNC_ESPECIAL;
		else if (this.tipoColaborador.startsWith("F"))
			return BarraMenu.PERFIL_FUNC;
		else
			return BarraMenu.SEM_PERFIL;
	}
	/*
	public Set<FaturaVO> getFaturas() {
		return faturas;
	}

	public void setFaturas(Set<FaturaVO> faturas) {
		this.faturas = faturas;
	}*/

	@Override
	public boolean equals(Object col) {
		if(col == null) return false;
		if(col instanceof ColaboradorVO){
			return ((ColaboradorVO)col).getId() == this.getId() || 
					((ColaboradorVO)col).getLogin().toUpperCase().compareTo(this.getLogin().toUpperCase()) == 0 ;
			}
		return false;
	}
}
