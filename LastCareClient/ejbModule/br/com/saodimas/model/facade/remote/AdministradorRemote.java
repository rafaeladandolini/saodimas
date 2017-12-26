package br.com.saodimas.model.facade.remote;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.BancoVO;
import br.com.saodimas.model.beans.CemiterioVO;
import br.com.saodimas.model.beans.ChequeVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.CompraVO;
import br.com.saodimas.model.beans.ContaVO;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.model.beans.EquipamentoVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.model.beans.FormaPagamentoVO;
import br.com.saodimas.model.beans.FornecedorVO;
import br.com.saodimas.model.beans.GrupoTrabalhoVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ParceiroVO;
import br.com.saodimas.model.beans.ParcelaVO;
import br.com.saodimas.model.beans.ParcelamentoVO;
import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoCompraVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.beans.ProdutoVendaVO;
import br.com.saodimas.model.beans.RelacaoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.model.beans.TipoAtendimentoVO;
import br.com.saodimas.model.beans.TipoContaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;

/**
 * Interface para acesso remoto que defini os metodos da Seguranca
 * @author Daniel/Rafaela
 *
 */

public interface AdministradorRemote {
	
		
	public ProdutoVO insertProduto(ProdutoVO produto)throws  SQLException, MensagemSaoDimasException ;
	public ProdutoVO updateProduto(ProdutoVO produto)throws SQLException, MensagemSaoDimasException;
	public void deleteProduto(ProdutoVO produto)throws SQLException, MensagemSaoDimasException;
	public List<ProdutoVO> listProdutos()throws SQLException, MensagemSaoDimasException;
	public List<ProdutoVO> listProdutosByStatus(String status)throws SQLException, MensagemSaoDimasException;
	public List<ProdutoVO> listProdutosByStatusTipoProduto(String status, String tipoProduto)throws SQLException, MensagemSaoDimasException;
	public List<ProdutoVO> listConsultaProdutos(String tipo, String[] paramentros, String status)throws SQLException, MensagemSaoDimasException;
	
	public ServicoVO insertServico(ServicoVO servico)throws SQLException, MensagemSaoDimasException;
	public ServicoVO updateServico(ServicoVO servico)throws SQLException, MensagemSaoDimasException;
	public void deleteServico(ServicoVO servico)throws SQLException, MensagemSaoDimasException;
	public List<ServicoVO> listServicos()throws SQLException, MensagemSaoDimasException;
	public List<ServicoVO> listServicosByStatus(String status)throws SQLException, MensagemSaoDimasException;
	public List<ServicoVO> listConsultaServicos(String tipo, String[] paramentros, String status)throws SQLException, MensagemSaoDimasException;
	
	public PlanoVO insertPlano(PlanoVO plano)throws SQLException, MensagemSaoDimasException;
	public PlanoVO updatePlano(PlanoVO plano)throws SQLException, MensagemSaoDimasException;
	public void deletePlano(PlanoVO plano)throws SQLException, MensagemSaoDimasException;
	public List<PlanoVO> listPlanos()throws SQLException, MensagemSaoDimasException;
	public List<PlanoVO> listPlanosByStatus(String status)throws SQLException, MensagemSaoDimasException;
	
	public ColaboradorVO insertColaborador(ColaboradorVO colaborador)throws SQLException, MensagemSaoDimasException;
	public ColaboradorVO updateColaborador(ColaboradorVO colaborador)throws SQLException, MensagemSaoDimasException;
	public void deleteColaborador(ColaboradorVO colaborador)throws SQLException, MensagemSaoDimasException;
	public List<ColaboradorVO> listColaboradores()throws SQLException, MensagemSaoDimasException;
	
	public List<GrupoTrabalhoVO> listGrupoTrabalhos()throws SQLException, MensagemSaoDimasException;
	
	public ClienteVO insertCliente(ClienteVO cliente)throws SQLException, MensagemSaoDimasException;
	public ClienteVO updateCliente(ClienteVO cliente)throws SQLException, MensagemSaoDimasException;
	public void deleteCliente(ClienteVO cliente)throws SQLException, MensagemSaoDimasException;
	public List<ClienteVO> listClientes()throws SQLException, MensagemSaoDimasException;
	public List<ClienteVO> listConsultaClientes(String tipo, String[] parametros, String status)throws SQLException, MensagemSaoDimasException;
	
	
	public CompraVO insertCompra(CompraVO c)throws SQLException, MensagemSaoDimasException;
	public CompraVO updateCompra(CompraVO c)throws SQLException, MensagemSaoDimasException;
	public void deleteCompra(CompraVO c)throws SQLException, MensagemSaoDimasException;
	public List<CompraVO> listCompras()throws SQLException, MensagemSaoDimasException;
	public void deleteProdutosCompra(List<ProdutoCompraVO> list)throws SQLException, MensagemSaoDimasException;
	
	public VendaVO insertVenda(VendaVO c)throws SQLException, MensagemSaoDimasException;
	public VendaVO updateVenda(VendaVO c)throws SQLException, MensagemSaoDimasException;
	public void deleteVenda(VendaVO c)throws SQLException, MensagemSaoDimasException;
	public List<VendaVO> listVendas()throws SQLException, MensagemSaoDimasException;
	public void deleteProdutosVenda(List<ProdutoVendaVO> list)throws SQLException, MensagemSaoDimasException;
	public void deleteServicosVenda(List<ServicoVendaVO> list)throws SQLException, MensagemSaoDimasException;
	public List<VendaVO> listVendasByCliente(ClienteVO cliente)throws SQLException, MensagemSaoDimasException;
	
	public FornecedorVO insertEmpresaCompra(FornecedorVO empresa)throws SQLException, MensagemSaoDimasException;
	public FornecedorVO updateEmpresaCompra(FornecedorVO empresa)throws SQLException, MensagemSaoDimasException;
	public void deleteEmpresaCompra(FornecedorVO empresa)throws SQLException, MensagemSaoDimasException;
	public List<FornecedorVO> listEmpresasCompra()throws SQLException, MensagemSaoDimasException;
	
	public CidadeVO insertCidade(CidadeVO cidade)throws SQLException, MensagemSaoDimasException;
	public CidadeVO updateCidade(CidadeVO cidade)throws SQLException, MensagemSaoDimasException;
	public void deleteCidade(CidadeVO cidade)throws SQLException, MensagemSaoDimasException;
	public List<CidadeVO> listCidades()throws SQLException, MensagemSaoDimasException;
	public List<CidadeVO> getCidadeAllByEstado(String estado)throws SQLException, MensagemSaoDimasException;
	
	public RelacaoVO insertRelacao(RelacaoVO relacao)throws SQLException, MensagemSaoDimasException;
	public RelacaoVO updateRelacao(RelacaoVO relacao)throws SQLException, MensagemSaoDimasException;
	public void deleteRelacao(RelacaoVO relacao)throws SQLException, MensagemSaoDimasException;
	public List<RelacaoVO> listRelacoes()throws SQLException, MensagemSaoDimasException;
	public List<RelacaoVO> listRelacoes(String tipo, String status)throws SQLException, MensagemSaoDimasException;

	public ObitoVO insertObito(ObitoVO obito)throws SQLException, MensagemSaoDimasException;
	public ObitoVO updateObito(ObitoVO obito)throws SQLException, MensagemSaoDimasException;
	public void deleteObito(ObitoVO obito)throws SQLException, MensagemSaoDimasException;
	public void deleteObito(List<ObitoVO> obito)throws SQLException, MensagemSaoDimasException;
	public void deleteProdutosObito(List<ProdutoObitoVO> list)throws SQLException, MensagemSaoDimasException;
	public void deleteServicosObito(List<ServicoObitoVO> list)throws SQLException, MensagemSaoDimasException;
	public List<ObitoVO> listObitos()throws SQLException, MensagemSaoDimasException;
	public List<ObitoVO> listObitosByApolice(ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public List<ObitoVO> listConsultaObitos(String tipo, String parametros[], CidadeVO cidade, TipoAtendimentoVO tipoAtendimento)throws SQLException, MensagemSaoDimasException;
	
	public ApoliceVO insertApolice(ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public ApoliceVO updateApolice(ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public void deleteApolice(ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public List<ApoliceVO> listApolice()throws SQLException, MensagemSaoDimasException;
	public List<ApoliceVO> listApoliceOrderByCidade()throws SQLException, MensagemSaoDimasException;
	public List<ApoliceVO> listApoliceByCidadeOrderByCidade(CidadeVO cidade)throws SQLException, MensagemSaoDimasException;
	public List<ApoliceVO> listConsultaApolices(String tipo, String[] parametros, String status)throws SQLException, MensagemSaoDimasException;
	public ApoliceVO getApoliceById(Integer id) throws SQLException, MensagemSaoDimasException;
	public Integer getProximoNumeroContrato()throws SQLException, MensagemSaoDimasException;
	public boolean isNumContratoValido(String number)throws SQLException, MensagemSaoDimasException;
	
	public AssociadoVO insertAssociado(AssociadoVO associado)throws SQLException, MensagemSaoDimasException;
	public AssociadoVO updateAssociado(AssociadoVO associado)throws SQLException, MensagemSaoDimasException;
	public void deleteAssociado(AssociadoVO associado)throws SQLException, MensagemSaoDimasException;
	public void deleteAssociado(List<AssociadoVO> associado)throws SQLException, MensagemSaoDimasException;
	public List<AssociadoVO> listAssociados()throws SQLException, MensagemSaoDimasException;
	public List<AssociadoVO> listAssociadosByApolice(ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public void atualizarDadosCartao(AssociadoVO associado)throws SQLException, MensagemSaoDimasException;
	
	public List<FaturaVO> insertFaturas(List<FaturaVO> fatura, ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public FaturaVO insertFatura(FaturaVO fatura)throws SQLException, MensagemSaoDimasException;
	public FaturaVO updateFatura(FaturaVO fatura)throws SQLException, MensagemSaoDimasException;
	public List<FaturaVO> updateFaturas(List<FaturaVO> faturas)throws SQLException, MensagemSaoDimasException;
	public void deleteFatura(FaturaVO fatura)throws SQLException, MensagemSaoDimasException;
	public void deleteFatura(List<FaturaVO> fatura)throws SQLException, MensagemSaoDimasException;
	public List<FaturaVO> listFaturas(ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public void faturaDestacada(FaturaVO fatura)throws SQLException, MensagemSaoDimasException;
	public void estornarBaixaFatura(FaturaVO vo)throws SQLException, MensagemSaoDimasException;
	
	//public List<FaturaVO> getListFaturasDiaColaborador(ColaboradorVO colaboradorVO, Date data)throws SQLException, MensagemSaoDimasException;
	//public List<FaturaVO> getListFaturasDia(Date data)throws SQLException, MensagemSaoDimasException;
	
	public List<FaturaVO> consultaFaturas(ColaboradorVO colaboradorVO, ParceiroVO parceiro,Boolean parceirosTodosNenehum, Date dataInicio, Date dataFim, Date dataInicioBaixa, Date dataFimBaixa)throws SQLException, MensagemSaoDimasException;
	//public List<FaturaVO> getListFaturasDia(Date dataInicio, Date dataFim)throws SQLException, MensagemSaoDimasException;
	
	public ParceiroVO insertParceiro(ParceiroVO parceiro)throws SQLException, MensagemSaoDimasException;
	public ParceiroVO updateParceiro(ParceiroVO parceiro)throws SQLException, MensagemSaoDimasException;
	public void deleteParceiro(ParceiroVO parceiro)throws SQLException, MensagemSaoDimasException;
	public List<ParceiroVO> listParceiros()throws SQLException, MensagemSaoDimasException;
	public List<ParceiroVO> listParceirosAtivo()throws SQLException, MensagemSaoDimasException;
	
	public EquipamentoVO insertEquipamento(EquipamentoVO vo)throws SQLException, MensagemSaoDimasException;
	public EquipamentoVO updateEquipamento(EquipamentoVO vo)throws SQLException, MensagemSaoDimasException;
	public void deleteEquipamento(EquipamentoVO vo)throws SQLException, MensagemSaoDimasException;
	public List<EquipamentoVO> listEquipamentos()throws SQLException, MensagemSaoDimasException;
	public List<EquipamentoVO> listEquipamentosComQtde()throws SQLException, MensagemSaoDimasException;
	public List<EquipamentoVO> listEquipamentoDisponiveis()throws SQLException, MensagemSaoDimasException;
	
	public EmprestimoEquipamentoVO insertEmprestimoEquipamento(EmprestimoEquipamentoVO vo)throws SQLException, MensagemSaoDimasException;
	public EmprestimoEquipamentoVO updateEmprestimoEquipamento(EmprestimoEquipamentoVO vo)throws SQLException, MensagemSaoDimasException;
	public void deleteEmprestimoEquipamento(EmprestimoEquipamentoVO vo)throws SQLException, MensagemSaoDimasException;
	public List<EmprestimoEquipamentoVO> listEmprestimoEquipamento()throws SQLException, MensagemSaoDimasException;
	public List<EmprestimoEquipamentoVO> listEmprestimoEquipamentoByApolice(ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public List<EmprestimoEquipamentoVO> listDevolucoesEquipamentoByApolice(ApoliceVO apolice)throws SQLException, MensagemSaoDimasException;
	public void estornoDevolucaoEquipamento(EmprestimoEquipamentoVO vo)throws SQLException, MensagemSaoDimasException;
	public List<EmprestimoEquipamentoVO> consultaEmprestimosByEquipamento(EquipamentoVO equipamento, CidadeVO cidade, String status)throws SQLException, MensagemSaoDimasException;
	
	public List<CemiterioVO> listCemiterios()throws SQLException, MensagemSaoDimasException;
	
	public List<TipoAtendimentoVO> listTipoAtendimento()throws SQLException, MensagemSaoDimasException;
	
	public List<FormaPagamentoVO> listFormaPagamento()throws SQLException, MensagemSaoDimasException;
	
	public List<ParcelamentoVO> listParcelamentoByFormaPagamento(FormaPagamentoVO formaPagamento)throws SQLException, MensagemSaoDimasException;
	
	public List<ParcelaVO> listParcelaByVenda(VendaVO venda) throws SQLException, MensagemSaoDimasException;
	
	public List<ContaVO> listContas()throws SQLException, MensagemSaoDimasException;
	public void deleteConta(ContaVO conta)throws SQLException, MensagemSaoDimasException;
	public ContaVO updateConta(ContaVO conta)throws SQLException, MensagemSaoDimasException;
	public ContaVO insertConta(ContaVO conta)throws SQLException, MensagemSaoDimasException;
	
	public List<TipoContaVO> listTipoContas()throws SQLException, MensagemSaoDimasException;
	
	public List<BancoVO> listBancos()throws SQLException, MensagemSaoDimasException;
	
	public List<ChequeVO> listCheques()throws SQLException, MensagemSaoDimasException;
	public ChequeVO insertCheque(ChequeVO cheque)throws SQLException, MensagemSaoDimasException;
	public void deleteCheque(ChequeVO cheque)throws SQLException, MensagemSaoDimasException;
	public ChequeVO updateCheque(ChequeVO cheque)throws SQLException, MensagemSaoDimasException;
	public ChequeVO baixarCheque(ChequeVO cheque, Date dataBaixa)throws SQLException, MensagemSaoDimasException;
	
	public void finalizarvenda(VendaVO venda) throws SQLException, MensagemSaoDimasException;
}
