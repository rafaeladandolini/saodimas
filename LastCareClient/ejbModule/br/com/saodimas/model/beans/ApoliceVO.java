package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Classe representante da Apolice
 * @author Rafaela
 * @date 31-07-2008
 */

public class ApoliceVO implements Serializable{
 	
	private static final long serialVersionUID = 1L;
	
	public static final String TABELA = "APOLICE";
	
	public static final String ID_APOLICE = "ID_APOLICE";
	public static final String NUMERO_CONTRATO = "NUMERO_CONTRATO";
	public static final String DATA_ADESAO = "DATA_ADESAO";
	public static final String OBSERVACAO = "OBSERVACAO";
	public static final String OBSERVACAOOCULTA = "OBSERVACAOOCULTA";
	public static final String CELULAR = "CELULAR";
	public static final String TELEFONE = "TELEFONE";
	public static final String ENDERECO = "ENDERECO";
	public static final String BAIRRO = "BAIRRO";
	public static final String CEP = "CEP";
	public static final String EMAIL = "EMAIL";
	public static final String SENHA = "SENHA";
	public static final String LOGIN = "LOGIN";
	public static final String STATUS = "STATUS";
	public static final String ID_ASSOCIADO = "ID_ASSOCIADO";
	public static final String ID_CIDADE = "ID_CIDADE";
	public static final String ID_EMPRESA = "ID_EMPRESA";
	public static final String ID_PLANO = "ID_PLANO";
	public static final String ID_COLABORADOR = "ID_COLABORADOR";
	public static final String SIS_ANT_FATURAS = "SIS_ANT_FATURAS";
	public static final String SIS_ANT_OBITOS = "SIS_ANT_OBITOS";
	public static final String SIS_ANT_OBS = "SIS_ANT_OBS";
	
	public static final String QTDE_CARTEIRINHAS = "QTDE_CARTEIRINHAS";
	public static final String COMPLEMENTO_ENDERECO = "COMPLEMENTO_ENDERECO";
	
	public static final String DATA_SUSPENSAO = "DATA_SUSPENSAO";
	public static final String MOTIVO_SUSPENSAO = "MOTIVO_SUSPENSAO";
	public static final String DATA_CANCELAMENTO = "DATA_CANCELAMENTO";
	public static final String MOTIVO_CANCELAMENTO = "MOTIVO_CANCELAMENTO";
	
	

	private Integer id;
	private String numeroContrato;
	private Date dataAdesao;
	private String observacao;
	private String observacaoOculta;
	private String celular;
	private String telefone;
	private String endereco;
	private String bairro;
	private String compEndereco;
	private String cep;
	private String email;
	private String senha;
	private String login;
	private String status;
	private AssociadoVO titular;
	private CidadeVO cidade;
	private EmpresaVO empresa;
	private PlanoVO plano;
	private ColaboradorVO colaborador;
	private List<AssociadoVO> dependentes;
	private List<FaturaVO> faturas;
	private List<ObitoVO> obitos;
	private List<EmprestimoEquipamentoVO> emprestimos;
	private List<EmprestimoEquipamentoVO> devolucoes;
	private Date dataSuspensao;
	private String motivoSuspensao;
	private Date dataCancelamento;
	private String motivoCancelamento;
	private int faturasPagasAntigas;
	private int obitoOcorridosAntigos;
	private String observacaoAntiga;
	private int quantidadeCarteirinhas;
	

	public int getQuantidadeCarteirinhas() {
		return quantidadeCarteirinhas;
	}

	public void setQuantidadeCarteirinhas(int quantidadeCarteirinhas) {
		this.quantidadeCarteirinhas = quantidadeCarteirinhas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCompEndereco() {
		return compEndereco;
	}

	public void setCompEndereco(String compEndereco) {
		this.compEndereco = compEndereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Date getDataAdesao() {
		return dataAdesao;
	}

	public void setDataAdesao(Date dataAdesao) {
		this.dataAdesao = dataAdesao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public AssociadoVO getTitular() {
		return titular;
	}

	public void setTitular(AssociadoVO titular) {
		this.titular = titular;
	}

	public List<AssociadoVO> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<AssociadoVO> dependentes) {
		this.dependentes = dependentes;
	}

	public PlanoVO getPlano() {
		return plano;
	}

	public void setPlano(PlanoVO plano) {
		this.plano = plano;
	}

	public ColaboradorVO getColaborador() {
		return colaborador;
	}

	public void setColaborador(ColaboradorVO colaborador) {
		this.colaborador = colaborador;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public CidadeVO getCidade() {
		return cidade;
	}

	public void setCidade(CidadeVO cidade) {
		this.cidade = cidade;
	}

	public EmpresaVO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	public List<FaturaVO> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<FaturaVO> faturas) {
		this.faturas = faturas;
	}

	public List<ObitoVO> getObitos() {
		return obitos;
	}

	public void setObitos(List<ObitoVO> obitos) {
		this.obitos = obitos;
	}
	
	public List<EmprestimoEquipamentoVO> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<EmprestimoEquipamentoVO> em) {
		this.emprestimos = em;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	//
	public String getNomeTitular() {
		return titular.getNome();
	}
	
	public String getNomeCidade() {
		return cidade.getNome();
	}
	
	public String get() {
		return cidade.getNome();
	}

	public int getFaturasPagasAntigas() {
		return faturasPagasAntigas;
	}

	public void setFaturasPagasAntigas(int faturasPagasAntigas) {
		this.faturasPagasAntigas = faturasPagasAntigas;
	}

	public int getObitoOcorridosAntigos() {
		return obitoOcorridosAntigos;
	}

	public void setObitoOcorridosAntigos(int obitoOcorridosAntigos) {
		this.obitoOcorridosAntigos = obitoOcorridosAntigos;
	}

	public String getObservacaoAntiga() {
		return observacaoAntiga;
	}

	public void setObservacaoAntiga(String observacaoAntiga) {
		this.observacaoAntiga = observacaoAntiga;
	}
	
	public String getObservacaoOculta() {
		return observacaoOculta;
	}

	public List<EmprestimoEquipamentoVO> getDevolucoes() {
		return devolucoes;
	}

	public void setDevolucoes(List<EmprestimoEquipamentoVO> devolucoes) {
		this.devolucoes = devolucoes;
	}
	
	public void setObservacaoOculta(String observacaoOculta) {
		this.observacaoOculta = observacaoOculta;
	}
	
		
	public Date getDataSuspensao() {
		return dataSuspensao;
	}

	public void setDataSuspensao(Date dataSuspensao) {
		this.dataSuspensao = dataSuspensao;
	}

	public String getMotivoSuspensao() {
		return motivoSuspensao;
	}

	public void setMotivoSuspensao(String motivoSuspensao) {
		this.motivoSuspensao = motivoSuspensao;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getMotivoCancelamento() {
		return motivoCancelamento;
	}

	public void setMotivoCancelamento(String motivoCancelamento) {
		this.motivoCancelamento = motivoCancelamento;
	}

	public int getQtdeEmprestimos()
	{
		if(emprestimos != null)
			return emprestimos.size();
		return 0;
	}
	
	public int getQtdeDevolucoes()
	{
		if(devolucoes != null)
			return devolucoes.size();
		return 0;
	}

	public int getQtdeObitos()
	{
		return obitos.size();
	}
	
	}
