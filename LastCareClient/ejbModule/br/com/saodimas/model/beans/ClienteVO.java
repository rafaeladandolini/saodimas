package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ClienteVO implements Serializable{
 	
	private static final long serialVersionUID = 1L;
	
	public static final String TABELA = "CLIENTE";
	
	public static final String ID_CLIENTE = "ID_CLIENTE";
	public static final String NOME = "NOME";
	public static final String ENDERECO = "ENDERECO";
	public static final String BAIRRO = "BAIRRO";
	public static final String CEP = "CEP";
	public static final String CONTATO = "CONTATO";
	public static final String CONTATO_2 = "CONTATO_2";
	public static final String OBSERVACAO = "OBSERVACAO";
	public static final String ID_CIDADE = "ID_CIDADE";
	public static final String ID_APOLICE = "ID_APOLICE";
	public static final String STATUS = "STATUS";
	public static final String RG = "RG";
	public static final String CPF = "CPF";
	public static final String COMPLEMENTO_ENDERECO = "COMPLEMENTO_ENDERECO";
	public static final String DATA_NASCIMENTO = "DATA_NASCIMENTO";
	public static final String NOME_PAI = "NOME_PAI";
	public static final String NOME_MAE = "NOME_MAE";
	public static final String EMAIL = "EMAIL";
	public static final String PROFISSAO = "profissao";
	
	
	
	private Integer id;
	private String nome;
	private String endereco;
	private String bairro;
	private String cep;
	private String contato;
	private String contato2;
	private String observacao;
	private CidadeVO cidade;
	private ApoliceVO apolice;
	private String status;
	private String cpf;
	private String rg;
	private String email;
	private String profissao;
	private String complementoEndereco;
	private Date dataNascimento;
	private String nomePai;
	private String nomeMae;
	
	private List<VendaVO> vendas;
	
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getContato2() {
		return contato2;
	}
	public void setContato2(String contato2) {
		this.contato2 = contato2;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public CidadeVO getCidade() {
		return cidade;
	}
	public void setCidade(CidadeVO cidade) {
		this.cidade = cidade;
	}
	public ApoliceVO getApolice() {
		return apolice;
	}
	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
		
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
			
	public List<VendaVO> getVendas() {
		return vendas;
	}
	public void setVendas(List<VendaVO> listaVendas) {
		this.vendas = listaVendas;
	}
	
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	@Override
	public String toString() {
		return this.getNome();
	}
	
	
	}
