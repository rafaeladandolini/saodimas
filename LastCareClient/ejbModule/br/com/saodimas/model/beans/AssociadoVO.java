package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AssociadoVO implements Serializable, Comparable<AssociadoVO>{

	private static final long serialVersionUID = 1L;
	
	public static final String TABELA = "ASSOCIADO";
	public static final String ID_ASSOCIADO = "ID_ASSOCIADO";
	public static final String NOME = "NOME";
	public static final String CPF_STR = "CPF";
	public static final String DATA_NASCIMENTO = "DATA_NASCIMENTO";
	public static final String SEXO = "SEXO";
	public static final String STATUS = "STATUS";
	public static final String ID_RELACAO = "ID_RELACAO";
	public static final String ID_APOLICE = "ID_APOLICE";
	public static final String RG = "RG";
	public static final String DATA_ADESAO = "DATA_ADESAO";
	public static final String DATA_ULTIMA_IMPRESSAO = "DATA_ULTIMA_IMPRESSAO";
	public static final String QTDE_CARTAO = "QTDE_CARTAO";

	private Integer id;
	private String nome;
	private String CPF;
	private Date dataNascimento;
	private char sexo;
	private String status;
	private RelacaoVO relacao;
	private ObitoVO obito;
	private ApoliceVO apolice;
	private Integer idade;
	private String condicao;
	private String rg;
	private Date dataAdesao;
	private int qtdeCartao;
	private Date dataUltimaImpressao;
	
	public Date getDataAdesao() {
		return dataAdesao;
	}

	public void setDataAdesao(Date dataAdesao) {
		this.dataAdesao = dataAdesao;
	}

	public String getCondicao() {
		this.condicao = obito == null ? "Vivo" : "Falecido";
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = obito == null ? "Vivo" : "Falecido";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		CPF = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public ObitoVO getObito() {
		return obito;
	}

	public void setObito(ObitoVO obito) {
		this.obito = obito;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RelacaoVO getRelacao() {
		return relacao;
	}

	public void setRelacao(RelacaoVO relacao) {
		this.relacao = relacao;
	}

	public ApoliceVO getApolice() {
		return apolice;
	}
	
	
	public int getQtdeCartao() {
		return qtdeCartao;
	}

	public void setQtdeCartao(int qtdeCarteirinhas) {
		this.qtdeCartao = qtdeCarteirinhas;
	}

	public Date getDataUltimaImpressao() {
		return dataUltimaImpressao;
	}

	public void setDataUltimaImpressao(Date dataUltimaImpressao) {
		this.dataUltimaImpressao = dataUltimaImpressao;
	}

	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
	}
	
	public Integer getIdade(){
		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(dataNascimento);
		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		dateOfBirth.add(Calendar.YEAR, age);

		if (today.before(dateOfBirth))age--;
		idade = age;
		return idade;
	}

	
	public void setIdade(Integer idade){
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.add(Calendar.YEAR, -idade);
		dataNascimento = new Date(dateOfBirth.getTimeInMillis());
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
	public int compareTo(AssociadoVO arg0) {
		return relacao.getId().compareTo(arg0.getRelacao().getId());
		
		
	}

	
}
