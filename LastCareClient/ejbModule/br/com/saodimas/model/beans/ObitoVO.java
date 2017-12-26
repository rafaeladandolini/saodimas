package br.com.saodimas.model.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ObitoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABELA = "OBITO";
	public static final String ID_OBITO = "ID_OBITO";
	public static final String ID_ASSOCIADO = "ID_ASSOCIADO";
	public static final String ID_APOLICE = "ID_APOLICE";
	public static final String DATA_OBITO = "DATA_OBITO";
	public static final String CAUSA = "CAUSA";
	public static final String NUMERO_ATESTADO = "NUMERO_ATESTADO";
	public static final String DATA_EXPEDICAO = "DATA_EXPEDICAO";
	
	public static final String LOCAL_FACELIMENTO = "LOCAL_FALECIMENTO";
	public static final String ID_CIDADE_FALECIMENTO = "ID_CIDADE_FALECIMENTO";
	public static final String LOCAL_VELORIO = "LOCAL_VELORIO";
	public static final String ID_CIDADE_VELORIO = "ID_CIDADE_VELORIO";
	public static final String ID_CEMITERIO = "ID_CEMITERIO";
	public static final String ID_MOTORISTA_VIAGEM = "ID_MOTORISTA_VIAGEM";
	public static final String ID_MOTORISTA_CORTEJO = "ID_MOTORISTA_CORTEJO";
	public static final String DATA_SEPULTAMENTO = "DATA_SEPULTAMENTO";
	public static final String HORA_SEPULTAMENTO = "HORA_SEPULTAMENTO";
		
	public static final String ID_TIPO_ATENDIMETNO = "ID_TIPO_ATENDIMENTO";
	public static final String NOME_FALECIDO = "NOME_FALECIDO";
	
	
	private Integer id;
	private AssociadoVO associado;
	private ApoliceVO apolice;
	
	private Date data;
	private String causa;
	private String numeroAtestado;
	private Date dataExpedicao;
	
	private String localFalecimento;
	private CidadeVO cidadeFalecimento;
	private String localVelorio;
	private CidadeVO cidadeVelorio;
	private CemiterioVO cemiterio;
	private GrupoTrabalhoVO grupoTrabalhoViagem;
	private GrupoTrabalhoVO grupoTrabalhoCortejo;
	private Date dataSepultamento;
	private String horaSepultamento;
	
	private TipoAtendimentoVO tipoAtendimento;
	private String nomeFalecido;
	
	private List<ProdutoObitoVO> produtos;
	private List<ServicoObitoVO> servicos;
	
	
	public String getNomeFalecido() {
		return nomeFalecido;
	}

	public void setNomeFalecido(String nomeFalecido) {
		this.nomeFalecido = nomeFalecido;
	}
	
	public TipoAtendimentoVO getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(TipoAtendimentoVO tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	
	public List<ProdutoObitoVO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoObitoVO> produtos) {
		this.produtos = produtos;
	}

	public List<ServicoObitoVO> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoObitoVO> servicos) {
		this.servicos = servicos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AssociadoVO getAssociado() {
		return associado;
	}

	public void setAssociado(AssociadoVO associado) {
		this.associado = associado;
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public String getNumeroAtestado() {
		return numeroAtestado;
	}

	public void setNumeroAtestado(String numeroAtestado) {
		this.numeroAtestado = numeroAtestado;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}
	
		
	public String getLocalFalecimento() {
		return localFalecimento;
	}

	public void setLocalFalecimento(String localFalecimento) {
		this.localFalecimento = localFalecimento;
	}

	public CidadeVO getCidadeFalecimento() {
		return cidadeFalecimento;
	}

	public void setCidadeFalecimento(CidadeVO cidadeFalecimento) {
		this.cidadeFalecimento = cidadeFalecimento;
	}

	public String getLocalVelorio() {
		return localVelorio;
	}

	public void setLocalVelorio(String localVelorio) {
		this.localVelorio = localVelorio;
	}

	public CidadeVO getCidadeVelorio() {
		return cidadeVelorio;
	}

	public void setCidadeVelorio(CidadeVO cidadeVelorio) {
		this.cidadeVelorio = cidadeVelorio;
	}

	public CemiterioVO getCemiterio() {
		return cemiterio;
	}

	public void setCemiterio(CemiterioVO cemiterio) {
		this.cemiterio = cemiterio;
	}

		
	public GrupoTrabalhoVO getGrupoTrabalhoViagem() {
		return grupoTrabalhoViagem;
	}

	public void setGrupoTrabalhoViagem(GrupoTrabalhoVO grupoTrabalhoViagem) {
		this.grupoTrabalhoViagem = grupoTrabalhoViagem;
	}

	public GrupoTrabalhoVO getGrupoTrabalhoCortejo() {
		return grupoTrabalhoCortejo;
	}

	public void setGrupoTrabalhoCortejo(GrupoTrabalhoVO grupoTrabalhoCortejo) {
		this.grupoTrabalhoCortejo = grupoTrabalhoCortejo;
	}

	public Date getDataSepultamento() {
		return dataSepultamento;
	}

	public void setDataSepultamento(Date dataSepultamento) {
		this.dataSepultamento = dataSepultamento;
	}

	public String getHoraSepultamento() {
		return horaSepultamento;
	}

	public void setHoraSepultamento(String horaSepultamento) {
		this.horaSepultamento = horaSepultamento;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String tempoVida = sdf.format(this.getAssociado().getDataNascimento()) + "* - " + sdf.format(this.getData()) + "+";
		return this.getAssociado() != null ? this.getAssociado().getNome() + " (" + tempoVida + ")" : "";
	}
	
	@Override
	public boolean equals(Object obito) {
		if(obito == null) return false;
		return ((ObitoVO)obito).getId() == this.getId() || ((ObitoVO)obito).getAssociado().equals(this.getAssociado()); 
	}
}

