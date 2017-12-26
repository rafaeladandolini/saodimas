package br.com.saodimas.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.CommunicationException;
import javax.swing.JOptionPane;

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
import br.com.saodimas.model.facade.Administrador;
import br.com.saodimas.model.facade.Seguranca;
import br.com.saodimas.model.facade.remote.AdministradorRemote;
import br.com.saodimas.model.facade.remote.SegurancaRemote;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.principal.SaoDimasSettings;

public class Controller {

	private static Controller controller; 
	private static ColaboradorVO usuario;

    private Controller() { }

    public static Controller getInstance() {
        if(controller==null)
        	controller = new Controller ();
        return controller;
    }
    
    public ColaboradorVO getUsuarioLogado(){
    	return usuario;
    }
	
    public void setUsuarioLogado(ColaboradorVO u) {
		usuario = u;
	}
    
    
	public void insertCidade(CidadeVO cidade) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertCidade(cidade);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar cidade:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertFatura(FaturaVO fatura) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertFatura(fatura);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar fatura:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertPlano(PlanoVO plano) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();;
			administradorRemote.insertPlano(plano);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar plano:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertProduto(ProdutoVO produto) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertProduto(produto);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar produto:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertServico(ServicoVO servico) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertServico(servico);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar servico:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}

	public void insertRelacao(RelacaoVO relacao) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertRelacao(relacao);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar rela��o:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertColaborador(ColaboradorVO colaborador) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertColaborador(colaborador);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar colaborador:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertCliente(ClienteVO cliente) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertCliente(cliente);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar cliente:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertCompra(CompraVO compra) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertCompra(compra);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar cliente:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertVenda(VendaVO venda) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertVenda(venda);
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new MensagemSaoDimasException("Erro ao salvar cliente:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertEmpresaCompra(FornecedorVO empresa) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertEmpresaCompra(empresa);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar empresa:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertEquipamento(EquipamentoVO equipamento) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertEquipamento(equipamento);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar equipamento:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertEmprestimoEquipamento(EmprestimoEquipamentoVO emprestimoEquipamento) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertEmprestimoEquipamento(emprestimoEquipamento);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar emprestimo:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertParceiro(ParceiroVO parceiro) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertParceiro(parceiro);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar parceiro:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void insertObito(ObitoVO obito) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.insertObito(obito);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar obito:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	
	public void updateCidade(CidadeVO cidade) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.updateCidade(cidade);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar cidade:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void updateEmpresaCompra(FornecedorVO empresa) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.updateEmpresaCompra(empresa);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar empresa:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}

	public void updatePlano(PlanoVO plano) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.updatePlano(plano);
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar plano:" + e.getMessage());	
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void updateProduto(ProdutoVO produto) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.updateProduto(produto);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar produto:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void updateServico(ServicoVO servico) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.updateServico(servico);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar servico:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}

	public void updateRelacao(RelacaoVO relacao) throws MensagemSaoDimasException
	{
		try {
		AdministradorRemote administradorRemote = new Administrador();
		administradorRemote.updateRelacao(relacao);
		
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar rela��o:" + e.getMessage());	
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void updateFatura(FaturaVO fat) throws MensagemSaoDimasException
	{
		try {
		AdministradorRemote administradorRemote = new Administrador();
		administradorRemote.updateFatura(fat);
		
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar fatura:" + e.getMessage());	
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public ColaboradorVO updateColaborador(ColaboradorVO colaborador) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.updateColaborador(colaborador);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar colaborador:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return colaborador;
	}
	
	public ClienteVO updateCliente(ClienteVO c) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.updateCliente(c);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar cliente:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return c;
	}
	
	public ContaVO updateConta(ContaVO c) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.updateConta(c);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar conta:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return c;
	}
	
	public ChequeVO updateCheque(ChequeVO c) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.updateCheque(c);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar conta:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return c;
	}
	
	public ContaVO insertConta(ContaVO c) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.insertConta(c);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar conta:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return c;
	}
	
	public ChequeVO insertCheque(ChequeVO c) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.insertCheque(c);
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new MensagemSaoDimasException("Erro ao salvar Cheque:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return c;
	}
	
	public CompraVO updateCompra(CompraVO c) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.updateCompra(c);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar compra:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return c;
	}
	
	public VendaVO updateVenda(VendaVO venda) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.updateVenda(venda);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar compra:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return venda;
	}
	
	public void finalizarVenda(VendaVO venda) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.finalizarvenda(venda);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar venda:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	
	}
	
	public EquipamentoVO updateEquipamento(EquipamentoVO vo) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.updateEquipamento(vo);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar colaborador:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return vo;
	}
	
	public EmprestimoEquipamentoVO updateEmprestimoEquipamento(EmprestimoEquipamentoVO vo) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.updateEmprestimoEquipamento(vo);
			return vo;
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar emprestimo:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return vo;
	}
	
	public ParceiroVO updateParceiro(ParceiroVO parceiro) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			return administradorRemote.updateParceiro(parceiro);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar parceiro:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return parceiro;
	}
	
	public void updateObito(ObitoVO obito) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.updateObito(obito);
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new MensagemSaoDimasException("Erro ao salvar obito:" + e.getMessage());	
		} catch (Exception e) {
			e.printStackTrace();
			msgAviso(e);
			
		}
	}
	
	public void saveOrUpdateDependente(AssociadoVO associado) throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			if(associado.getId() != null)
				administradorRemote.updateAssociado(associado);
			else
				administradorRemote.insertAssociado(associado);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar dependente:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
		
	public void deleteCidade(CidadeVO cidade)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteCidade(cidade);
			
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteCheque(ChequeVO cheque)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteCheque(cheque);
			
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void baixarCheque(ChequeVO cheque, Date dataBaixa)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.baixarCheque(cheque, dataBaixa);
			
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	
	public void deleteConta(ContaVO conta)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteConta(conta);
			
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteEmpresaCompra(FornecedorVO empresa)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteEmpresaCompra(empresa);
			
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deletePlano(PlanoVO plano)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deletePlano(plano);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteProduto(ProdutoVO produto)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteProduto(produto);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteServico(ServicoVO servico)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteServico(servico);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}

	public void deleteRelacao(RelacaoVO relacao)throws MensagemSaoDimasException
	{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteRelacao(relacao);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteColaborador(ColaboradorVO colaborador)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteColaborador(colaborador);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteCliente(ClienteVO cliente)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteCliente(cliente);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteCompra(CompraVO compra)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteCompra(compra);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}

	public void deleteVenda(VendaVO venda)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteVenda(venda);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}

	
	public void deleteEquipamento(EquipamentoVO equipamento)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteEquipamento(equipamento);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	
	public void deleteEmprestimoEquipamento(EmprestimoEquipamentoVO equipamento)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteEmprestimoEquipamento(equipamento);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	
	public void estornarDevolu��oEquipamento(EmprestimoEquipamentoVO equipamento)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.estornoDevolucaoEquipamento(equipamento);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	
	public void estornarBaixaFatura(FaturaVO fatura)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.estornarBaixaFatura(fatura);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	
	public void deleteParceiro(ParceiroVO parceiro)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteParceiro(parceiro);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteObito(ObitoVO obito)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteObito(obito);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteObitos(List<ObitoVO> obitos)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteObito(obitos);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteServicosObitos(List<ServicoObitoVO> obitos)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteServicosObito(obitos);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteProdutosObitos(List<ProdutoObitoVO> obitos)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteProdutosObito(obitos);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteProdutosCompra(List<ProdutoCompraVO> produtos)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteProdutosCompra(produtos);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteProdutosVenda(List<ProdutoVendaVO> produtos)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteProdutosVenda(produtos);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public void deleteServicosVenda(List<ServicoVendaVO> servicos)throws MensagemSaoDimasException{
		try {
			AdministradorRemote administradorRemote = new Administrador();
			administradorRemote.deleteServicosVenda(servicos);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	
	public void deleteFaturas(List<FaturaVO> faturas)throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			administradorRemote.deleteFatura(faturas);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
	}
	
	public List<CidadeVO> getCidadeAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listCidades();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ChequeVO> getAllCheques()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listCheques();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<TipoAtendimentoVO> getAllTiposAtendimentoVOs()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listTipoAtendimento();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TipoContaVO> getAllTiposConta()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listTipoContas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ContaVO> getAllContas()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listContas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<BancoVO> getAllBancos()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listBancos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<ClienteVO> getAllClientes()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listClientes();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CompraVO> getAllCompras()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listCompras();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<VendaVO> getAllVendas()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listVendas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PlanoVO> getPlanoAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listPlanos();
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<FornecedorVO> getEmpresaCompraAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listEmpresasCompra();
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PlanoVO> getPlanosAtivos()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listPlanosByStatus("Ativo");
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProdutoVO> getProdutoAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listProdutos();
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProdutoVO> getProdutoAtivos()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listProdutosByStatus("Ativo");
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProdutoVO> getProdutoVendasAtivos()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listProdutosByStatusTipoProduto("Ativo", ProdutoVO.TIPO_PRODUTO_VENDA);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProdutoVO> getProdutoConsumoInternoAtivos()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listProdutosByStatus("Ativo");
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ServicoVO> getServicoAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listServicos();
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}

	public List<ServicoVO> getServicoAtivos()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listServicosByStatus("Ativo");
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<RelacaoVO> getRelacoesAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listRelacoes();
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<RelacaoVO> getRelacoesAtivasByTipo(String tipo)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listRelacoes(tipo, "Ativo");
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}

	public List<ColaboradorVO> getColaboradorAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listColaboradores();
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<GrupoTrabalhoVO> getGrupoTrabalhoAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listGrupoTrabalhos();
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CemiterioVO> getCemiteriosAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listCemiterios();
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EquipamentoVO> getEquipamentoAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			List<EquipamentoVO> list = administradorRemote.listEquipamentos();
			return list;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<FormaPagamentoVO> getAllFormaPagamento()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			List<FormaPagamentoVO> list = administradorRemote.listFormaPagamento();
			return list;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<ParcelamentoVO> getAllParcelamentoByFormaPagamento(FormaPagamentoVO formP)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			List<ParcelamentoVO> list = administradorRemote.listParcelamentoByFormaPagamento(formP);
			return list;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ParcelaVO> getAllParcelaByVenda(VendaVO venda)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			List<ParcelaVO> list = administradorRemote.listParcelaByVenda(venda);
			return list;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EquipamentoVO> getEquipamentoAllComQde()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			List<EquipamentoVO> list = administradorRemote.listEquipamentosComQtde();
			return list;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EmprestimoEquipamentoVO> getEmprestimoEquipamentoAllByApolice(ApoliceVO apolice)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listEmprestimoEquipamentoByApolice(apolice);
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EmprestimoEquipamentoVO> getDevolucoesEquipamentoAllByApolice(ApoliceVO apolice)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listDevolucoesEquipamentoByApolice(apolice);
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EmprestimoEquipamentoVO> consultaEmprestimos( EquipamentoVO equipamento, CidadeVO cidade, String status)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.consultaEmprestimosByEquipamento(equipamento, cidade, status);
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EmprestimoEquipamentoVO> getEmprestimoEquipamentoAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listEmprestimoEquipamento();
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ParceiroVO> getParceirosAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listParceiros();
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ParceiroVO> getParceirosAllAtivos()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listParceirosAtivo();
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ObitoVO> getObitoAll()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listObitos();
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ApoliceVO insertApolice(ApoliceVO apolice) throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.insertApolice(apolice);
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar apolice:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	public ApoliceVO updateApolice(ApoliceVO apolice) throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.updateApolice(apolice);
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteApolice(ApoliceVO apolice)throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			administradorRemote.deleteApolice(apolice);
		}catch (MensagemSaoDimasException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		
	}
	
	public AssociadoVO updateAssociado(AssociadoVO associado) throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.updateAssociado(associado);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar dependente:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ApoliceVO> listApolice()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			List<ApoliceVO> list = administradorRemote.listApolice();
			
			return list;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ApoliceVO> listApoliceOrderByCidade()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			List<ApoliceVO> list = administradorRemote.listApoliceOrderByCidade();
			
			return list;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ApoliceVO> listApoliceOrderByCidade(CidadeVO cidade)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			List<ApoliceVO> list = administradorRemote.listApoliceByCidadeOrderByCidade(cidade);
			
			return list;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}

	public void deleteAssociado(AssociadoVO associado)throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			administradorRemote.deleteAssociado(associado);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		
	}
	
	public void atualizarDadosImpressaoCartao(AssociadoVO associado)throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			administradorRemote.atualizarDadosCartao(associado);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		
	}
	
	public void deleteAssociado(List<AssociadoVO> associado)throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			administradorRemote.deleteAssociado(associado);
		}catch (MensagemSaoDimasException e) {
			throw e;
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		
	}
	
	public ColaboradorVO autenticarColaborador(String login, String senha) throws MensagemSaoDimasException
	{
		SegurancaRemote segurancaRemote;
		segurancaRemote = new Seguranca();
		try{
		usuario = segurancaRemote.autenticarColaborador(login, senha);
		}catch(MensagemSaoDimasException ex){
			throw ex;
		}catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return usuario;
	
	}
	
	public List<CidadeVO> getCidadeAllByEstado(String estado)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.getCidadeAllByEstado(estado);
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<FaturaVO> quitarFaturas(List<FaturaVO> faturas) throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.updateFaturas(faturas);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao quitar a fatura:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return faturas;
	}
	
	public void destacarFatura(FaturaVO fatura) throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			administradorRemote.faturaDestacada(fatura);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao quitar a fatura:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		
	}
	
	public FaturaVO quitarFatura(FaturaVO fatura) throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.updateFatura(fatura);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao quitar aa fatura:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return fatura;
	}
	
	
	public List<FaturaVO> insertFaturas(List<FaturaVO> faturas, ApoliceVO apolice) throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.insertFaturas(faturas, apolice);
			
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao salvar fatura:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return faturas;
	}
	
		
	public SaoDimasSettings carregarPreferencias(){
		SaoDimasSettings settings = new SaoDimasSettings();
		settings.carregarPreferencias();

		return settings;
	}
	
	public List<ApoliceVO> consultaApolices(String tipo, String[] paramentros, String status)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listConsultaApolices(tipo, paramentros, status);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ClienteVO> consultaClientes(String tipo, String[] paramentros, String status)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listConsultaClientes(tipo, paramentros, status);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProdutoVO> consultaProdutos(String tipo, String[] paramentros, String status)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listConsultaProdutos(tipo, paramentros, status);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ServicoVO> consultaServicos(String tipo, String[] paramentros, String status)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listConsultaServicos(tipo, paramentros, status);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ObitoVO> consultaObitos(String tipo, String parametros[], CidadeVO cidade, TipoAtendimentoVO tipoAtendimento)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listConsultaObitos(tipo, parametros, cidade, tipoAtendimento);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	private void msgAviso(Exception e)
	{
		String mensagem = "";
		if(SaoDimasMain.splash != null)
			SaoDimasMain.splash.dispose();
		
		if(e instanceof CommunicationException)
			mensagem = "Inicialize o Servidor.";
		else
			mensagem = "Erro inesperado. Contate o administrador do sistema." + e.getMessage() ;
			
			JOptionPane.showMessageDialog(null,mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
			
				
	}
	
	public ApoliceVO carregarApolice(Integer idApolice) throws MensagemSaoDimasException
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.getApoliceById(idApolice);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new MensagemSaoDimasException("Erro ao carregar a apolice:" + e.getMessage());
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ApoliceVO> carregarApolices(List<ApoliceVO> list) throws MensagemSaoDimasException
	{
		List<ApoliceVO> listaCarregada = new ArrayList<ApoliceVO>();
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			for (ApoliceVO apoliceVO : list) {
				listaCarregada.add(administradorRemote.getApoliceById(apoliceVO.getId()));
			}		
					
		}catch (SQLException e) {
			throw new MensagemSaoDimasException("Erro ao carregar a apolice:" + e.getMessage());	
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return listaCarregada;
	}
	
	public Integer getProximoNumContrato()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.getProximoNumeroContrato();
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public boolean isNumContratoValido(String number)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.isNumContratoValido(number);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return false;
	}
	/*
	public List<FaturaVO> getFaturasDiaColaborador(ColaboradorVO colaboradorVO)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.getListFaturasDiaColaborador(colaboradorVO, Calendar.getInstance().getTime());
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}*/
	
	public List<FaturaVO> getAllFaturasByApolice(ApoliceVO apolice)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listFaturas(apolice);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<AssociadoVO> getAllDependentesByApolice(ApoliceVO apolice)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listAssociadosByApolice(apolice);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<VendaVO> getAllVendasByCliente(ClienteVO cliente)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listVendasByCliente(cliente);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<ObitoVO> getAllObitosByApolice(ApoliceVO apolice)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listObitosByApolice(apolice);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	public List<FaturaVO> getFaturasDia()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.getListFaturasDia(Calendar.getInstance().getTime());
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}*/
	
	/*public List<FaturaVO> getFaturasDia(Date dataInicio, Date dataFim)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.getListFaturasDia(dataInicio, dataFim);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}*/
	
	public List<FaturaVO> consultaFaturas(ColaboradorVO colaboradorVO, ParceiroVO parceiro, Boolean parceiroTodosNenhum,Date dataInicio, Date dataFim, Date dataInicioBaixa, Date dataFimBaixa)
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.consultaFaturas(colaboradorVO, parceiro, parceiroTodosNenhum, dataInicio,dataFim, dataInicioBaixa, dataFimBaixa);
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EquipamentoVO> getAllEquipamentosDisponiveis()
	{
		AdministradorRemote administradorRemote;
		try {
			administradorRemote = new Administrador();
			return administradorRemote.listEquipamentoDisponiveis();
			
		} catch (Exception e) {
			msgAviso(e);
			e.printStackTrace();
		}
		return null;
	}
}