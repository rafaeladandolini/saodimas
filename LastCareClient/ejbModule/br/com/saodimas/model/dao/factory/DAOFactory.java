package br.com.saodimas.model.dao.factory;

import br.com.saodimas.model.dao.ApoliceDAO;
import br.com.saodimas.model.dao.AssociadoDAO;
import br.com.saodimas.model.dao.BancoDAO;
import br.com.saodimas.model.dao.CemiterioDAO;
import br.com.saodimas.model.dao.ChequeDAO;
import br.com.saodimas.model.dao.CidadeDAO;
import br.com.saodimas.model.dao.ClienteDAO;
import br.com.saodimas.model.dao.ColaboradorDAO;
import br.com.saodimas.model.dao.CompraDAO;
import br.com.saodimas.model.dao.ContaDAO;
import br.com.saodimas.model.dao.EmpresaDAO;
import br.com.saodimas.model.dao.EmprestimoEquipamentoDAO;
import br.com.saodimas.model.dao.EquipamentoDAO;
import br.com.saodimas.model.dao.FaturaDAO;
import br.com.saodimas.model.dao.FormaPagamentoDAO;
import br.com.saodimas.model.dao.FormaPagamentoParcelamentoDAO;
import br.com.saodimas.model.dao.FornecedorDAO;
import br.com.saodimas.model.dao.GrupoTrabalhoDAO;
import br.com.saodimas.model.dao.ObitoDAO;
import br.com.saodimas.model.dao.ParceiroDAO;
import br.com.saodimas.model.dao.ParcelaDAO;
import br.com.saodimas.model.dao.ParcelamentoDAO;
import br.com.saodimas.model.dao.PlanoDAO;
import br.com.saodimas.model.dao.ProdutoCompraDAO;
import br.com.saodimas.model.dao.ProdutoDAO;
import br.com.saodimas.model.dao.ProdutoObitoDAO;
import br.com.saodimas.model.dao.ProdutoVendaDAO;
import br.com.saodimas.model.dao.RelacaoDAO;
import br.com.saodimas.model.dao.ServicoDAO;
import br.com.saodimas.model.dao.ServicoObitoDAO;
import br.com.saodimas.model.dao.ServicoVendaDAO;
import br.com.saodimas.model.dao.TipoAtendimentoDAO;
import br.com.saodimas.model.dao.TipoContaDAO;
import br.com.saodimas.model.dao.VendaDAO;
import br.com.saodimas.model.dao.factory.impl.HibernateDAOFactory;

public abstract class DAOFactory {

	private static final DAOFactory HIBERNATE = new HibernateDAOFactory();

	public static final DAOFactory DEFAULT = HIBERNATE;

	public abstract TipoAtendimentoDAO buildTipoAtendimentoDAO();
	
	public abstract ApoliceDAO buildApoliceDAO();

	public abstract AssociadoDAO buildAssociadoDAO();

	public abstract CidadeDAO buildCidadeDAO();

	public abstract ColaboradorDAO buildColaboradorDAO();
	
	public abstract GrupoTrabalhoDAO buildGrupoTrabalhoDAO();
	
	public abstract ClienteDAO buildClienteDAO();
	
	public abstract CompraDAO buildCompraDAO();
	
	public abstract VendaDAO buildVendaDAO();
	
	public abstract FormaPagamentoDAO buildFormaPagamentoDAO();
	
	public abstract ParcelamentoDAO buildParcelamentoDAO();
	
	public abstract ParcelaDAO buildParcelaDAO();
	
	public abstract ContaDAO buildContaDAO();
	
	public abstract ChequeDAO buildChequeDAO();
	
	public abstract BancoDAO buildBancoDAO();
	
	public abstract TipoContaDAO buildTipoContaDAO();
	
	public abstract FormaPagamentoParcelamentoDAO buildFormaPagamentoParcelamentoDAO();

	public abstract EmpresaDAO buildEmpresaDAO();
	
	public abstract FornecedorDAO buildEmpresaCompraDAO();
	
	public abstract EquipamentoDAO buildEquipamentoDAO();
	
	public abstract CemiterioDAO buildCemiterioDAO();
	
	public abstract EmprestimoEquipamentoDAO buildEmprestimoEquipamentoDAO();

	public abstract FaturaDAO buildFaturaDAO();

	public abstract ObitoDAO buildObitoDAO();

	public abstract ParceiroDAO buildParceiroDAO();
	
	public abstract PlanoDAO buildPlanoDAO();

	public abstract ProdutoDAO buildProdutoDAO();
	
	public abstract ProdutoObitoDAO buildProdutoObitoDAO();
	
	public abstract ProdutoCompraDAO buildProdutoCompraDAO();
	
	public abstract ProdutoVendaDAO buildProdutoVendaDAO();
	
	public abstract ServicoVendaDAO buildServicoVendaDAO();

	public abstract RelacaoDAO buildRelacaoDAO();
	
	public abstract ServicoDAO buildServicoDAO();
	
	public abstract ServicoObitoDAO buildServicoObitoDAO();

}