package br.com.saodimas.model.dao.factory.impl;

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
import br.com.saodimas.model.dao.factory.DAOFactory;
import br.com.saodimas.model.dao.impl.ApoliceDAOImpl;
import br.com.saodimas.model.dao.impl.AssociadoDAOImpl;
import br.com.saodimas.model.dao.impl.BancoDAOImpl;
import br.com.saodimas.model.dao.impl.CemiterioDAOImpl;
import br.com.saodimas.model.dao.impl.ChequeDAOImpl;
import br.com.saodimas.model.dao.impl.CidadeDAOImpl;
import br.com.saodimas.model.dao.impl.ClienteDAOImpl;
import br.com.saodimas.model.dao.impl.ColaboradorDAOImpl;
import br.com.saodimas.model.dao.impl.CompraDAOImpl;
import br.com.saodimas.model.dao.impl.ContaDAOImpl;
import br.com.saodimas.model.dao.impl.EmpresaDAOImpl;
import br.com.saodimas.model.dao.impl.EmprestimoEquipamentoDAOImpl;
import br.com.saodimas.model.dao.impl.EquipamentoDAOImpl;
import br.com.saodimas.model.dao.impl.FaturaDAOImpl;
import br.com.saodimas.model.dao.impl.FormaPagamentoDAOImpl;
import br.com.saodimas.model.dao.impl.FormaPagamentoParcelamentoDAOImpl;
import br.com.saodimas.model.dao.impl.FornecedorDAOImpl;
import br.com.saodimas.model.dao.impl.GrupoTrabalhoDAOImpl;
import br.com.saodimas.model.dao.impl.ObitoDAOImpl;
import br.com.saodimas.model.dao.impl.ParceiroDAOImpl;
import br.com.saodimas.model.dao.impl.ParcelaDAOImpl;
import br.com.saodimas.model.dao.impl.ParcelamentoDAOImpl;
import br.com.saodimas.model.dao.impl.PlanoDAOImpl;
import br.com.saodimas.model.dao.impl.ProdutoCompraDAOImpl;
import br.com.saodimas.model.dao.impl.ProdutoDAOImpl;
import br.com.saodimas.model.dao.impl.ProdutoObitoDAOImpl;
import br.com.saodimas.model.dao.impl.ProdutoVendaDAOImpl;
import br.com.saodimas.model.dao.impl.RelacaoDAOImpl;
import br.com.saodimas.model.dao.impl.ServicoDAOImpl;
import br.com.saodimas.model.dao.impl.ServicoObitoDAOImpl;
import br.com.saodimas.model.dao.impl.ServicoVendaDAOImpl;
import br.com.saodimas.model.dao.impl.TipoAtendimentoDAOImpl;
import br.com.saodimas.model.dao.impl.TipoContaDAOImpl;
import br.com.saodimas.model.dao.impl.VendaDAOImpl;


public class HibernateDAOFactory extends DAOFactory{

	public ApoliceDAO buildApoliceDAO() {
		return new ApoliceDAOImpl();
	}

	public AssociadoDAO buildAssociadoDAO() {
		return new AssociadoDAOImpl();
	}

	public CidadeDAO buildCidadeDAO() {
		
		return new CidadeDAOImpl();
	}

	public ColaboradorDAO buildColaboradorDAO() {
		return new ColaboradorDAOImpl();
	}
	
	public ClienteDAO buildClienteDAO() {
		return new ClienteDAOImpl();
	}
	
	public CompraDAO buildCompraDAO() {
		return new CompraDAOImpl();
	}

	public EmpresaDAO buildEmpresaDAO() {
		return new EmpresaDAOImpl();
	}
	
	public FornecedorDAO buildEmpresaCompraDAO() {
		return new FornecedorDAOImpl();
	}

	public FaturaDAO buildFaturaDAO() {
		return new FaturaDAOImpl();
	}

	public ObitoDAO buildObitoDAO() {
		return new ObitoDAOImpl();
	}

	public PlanoDAO buildPlanoDAO() {
		return new PlanoDAOImpl();
	}

	public ProdutoDAO buildProdutoDAO() {
		return new ProdutoDAOImpl();
	}

	public RelacaoDAO buildRelacaoDAO() {
		return new RelacaoDAOImpl();
	}

	public ServicoDAO buildServicoDAO() {
		return new ServicoDAOImpl();
	}

	public ProdutoObitoDAO buildProdutoObitoDAO() {
		return new ProdutoObitoDAOImpl();
	}
	
	public ProdutoCompraDAO buildProdutoCompraDAO() {
		return new ProdutoCompraDAOImpl();
	}


	public ServicoObitoDAO buildServicoObitoDAO() {
		return new ServicoObitoDAOImpl();
	}

	public ParceiroDAO buildParceiroDAO() {
		return new ParceiroDAOImpl();
	}

	@Override
	public EquipamentoDAO buildEquipamentoDAO() {
		return new EquipamentoDAOImpl();
	}

	@Override
	public EmprestimoEquipamentoDAO buildEmprestimoEquipamentoDAO() {
		return new EmprestimoEquipamentoDAOImpl();
	}

	@Override
	public CemiterioDAO buildCemiterioDAO() {
		return new CemiterioDAOImpl();
	}

	@Override
	public TipoAtendimentoDAO buildTipoAtendimentoDAO() {
		return new TipoAtendimentoDAOImpl();
	}

	@Override
	public GrupoTrabalhoDAO buildGrupoTrabalhoDAO() {
		return new GrupoTrabalhoDAOImpl();
	}

	@Override
	public VendaDAOImpl buildVendaDAO() {
		return new VendaDAOImpl();
	}

	@Override
	public ProdutoVendaDAO buildProdutoVendaDAO() {
		return new ProdutoVendaDAOImpl();
	}

	@Override
	public ServicoVendaDAO buildServicoVendaDAO() {
		return new ServicoVendaDAOImpl();
	}

	@Override
	public FormaPagamentoDAO buildFormaPagamentoDAO() {
		return new FormaPagamentoDAOImpl();
	}

	@Override
	public ParcelamentoDAO buildParcelamentoDAO() {
		return new ParcelamentoDAOImpl();
	}

	@Override
	public FormaPagamentoParcelamentoDAO buildFormaPagamentoParcelamentoDAO() {
		return new FormaPagamentoParcelamentoDAOImpl();
	}
	
	public ParcelaDAO buildParcelaDAO() {
		return new ParcelaDAOImpl();
	}

	@Override
	public ContaDAO buildContaDAO() {
		return new ContaDAOImpl();
	}

	@Override
	public BancoDAO buildBancoDAO() {
		return new BancoDAOImpl();
	}

	@Override
	public TipoContaDAO buildTipoContaDAO() {
		return new TipoContaDAOImpl();
	}

	@Override
	public ChequeDAO buildChequeDAO() {
		return new ChequeDAOImpl();
	}
}
