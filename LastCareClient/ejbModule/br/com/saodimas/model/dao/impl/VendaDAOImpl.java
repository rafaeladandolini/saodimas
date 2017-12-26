package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.beans.ParcelaVO;
import br.com.saodimas.model.beans.ProdutoVendaVO;
import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.model.dao.ClienteDAO;
import br.com.saodimas.model.dao.ColaboradorDAO;
import br.com.saodimas.model.dao.FormaPagamentoDAO;
import br.com.saodimas.model.dao.ObitoDAO;
import br.com.saodimas.model.dao.ParcelaDAO;
import br.com.saodimas.model.dao.ParcelamentoDAO;
import br.com.saodimas.model.dao.ProdutoVendaDAO;
import br.com.saodimas.model.dao.ServicoVendaDAO;
import br.com.saodimas.model.dao.VendaDAO;

public class VendaDAOImpl implements VendaDAO {

	public void delete(VendaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			ServicoVendaDAO servicoVendaDAO = new ServicoVendaDAOImpl();
			servicoVendaDAO.deleteAllServicoVendaByVenda(entity, conn);
			
			ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAOImpl();
			produtoVendaDAO.deleteAllProdutoVendaByVenda(entity, conn);
			
			ParcelaDAO parcelaDAO = new ParcelaDAOImpl();
			parcelaDAO.getAllParcelasByVenda(entity, conn);
			
			ProdutoVendaDAO dao = new ProdutoVendaDAOImpl();
			dao.deleteAllProdutoVendaByVenda(entity, conn);

			String sql = "DELETE FROM VENDA WHERE ID_VENDA = ? ";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, entity.getId());
			stmt.executeUpdate();

			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	public List<VendaVO> findAll(Connection conn) throws SQLException {

		List<VendaVO> list = new ArrayList<VendaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM VENDA";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(this.createVOFromResultSet(rs, conn));
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		}

		return list;

	}

	public void save(VendaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
	
			String sql = "INSERT INTO VENDA" +
			" (DATA_VENDA," + 
			" ID_CLIENTE," + 
			" ID_COLABORADOR, " +
			" ID_FORMA_PAGAMENTO, " +
			" ID_PARCELAMENTO, " +
			" VALOR, " +
			" VALOR_DESCONTO, "  +
			" VALOR_ACRESCIMO, " +
			" STATUS_VENDA, " +
			" ID_OBITO, " +
			" OBSERVACAO) " + 
			" VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);

			stmt.setDate(1, entity.getDataVenda() != null ? new Date(entity	.getDataVenda().getTime()) : null);
			stmt.setInt(2, entity.getCliente().getId());
			stmt.setInt(3, entity.getColaborador().getId());
			stmt.setInt(4, entity.getFormaPagamento().getId());
			stmt.setInt(5, entity.getParcelamento().getId());
			stmt.setDouble(6, entity.getValor());
			stmt.setDouble(7, entity.getDesconto());
			stmt.setDouble(8, entity.getAcrescimo());
			stmt.setString(9,entity.getStatusVenda());
			stmt.setInt(10, entity.getObito()!= null ? entity.getObito().getId() : 0);
			stmt.setString(11, entity.getObservacao());

			stmt.executeUpdate();
			conn.commit();

			entity.setId(this.getIdInserido(conn));
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	public void update(VendaVO entity, Connection conn) throws SQLException {

		PreparedStatement stmt = null;

		try {
		

			String sql = " UPDATE VENDA SET " + 
			" DATA_VENDA = ?, " +
			" ID_CLIENTE = ?, " +
			" ID_COLABORADOR = ?, " +
			" ID_FORMA_PAGAMENTO = ?, " +
			" ID_PARCELAMENTO = ?, " +
			" VALOR = ?, " +
			" VALOR_DESCONTO = ?, " +
			" VALOR_ACRESCIMO = ?, " +
			" STATUS_VENDA = ?, " +
			" ID_OBITO = ?, " +
			" OBSERVACAO = ? " +
			" WHERE ID_VENDA = ?";
			
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);

			stmt.setDate(1, entity.getDataVenda() != null ? new Date(entity.getDataVenda().getTime()) : null);
			stmt.setInt(2, entity.getCliente().getId());
			stmt.setInt(3, entity.getColaborador().getId());
			stmt.setInt(4, entity.getFormaPagamento().getId());
			stmt.setInt(5, entity.getParcelamento().getId());
			stmt.setDouble(6, entity.getValor());
			stmt.setDouble(7, entity.getDesconto());
			stmt.setDouble(8, entity.getAcrescimo());
			stmt.setString(9,  entity.getStatusVenda());
			stmt.setDouble(10, entity.getObito() != null ? entity.getObito().getId() : 0);
			stmt.setString(11, entity.getObservacao());
			stmt.setInt(12, entity.getId());

			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	private VendaVO createVOFromResultSet(ResultSet rs, Connection conn)
			throws SQLException {
		VendaVO vo = new VendaVO();

		vo.setId(rs.getInt(VendaVO.ID_VENDA));
		
		int id_cliente = rs.getInt(VendaVO.ID_CLIENTE);
		if(id_cliente > 0){
			ClienteDAO cliente = new ClienteDAOImpl();
			vo.setCliente(cliente.findById(id_cliente, conn));
		}
		
		int id_colaborador = rs.getInt(VendaVO.ID_COLABORADOR);
		if(id_colaborador > 0){
			ColaboradorDAO colaborador = new ColaboradorDAOImpl();
			vo.setColaborador(colaborador.findById(id_colaborador, conn));
		}
		
		int id_obito = rs.getInt(VendaVO.ID_OBITO);
		if(id_obito > 0){
			ObitoDAO obitoDao = new ObitoDAOImpl();
			vo.setObito(obitoDao.findById(id_obito, conn));
		}
		
		vo.setDataVenda(rs.getDate(VendaVO.DATA_VENDA));
		
		int id_par = rs.getInt(VendaVO.ID_PARCELAMENTO);
		if(id_par > 0){
			ParcelamentoDAO parDAO = new ParcelamentoDAOImpl();
			vo.setParcelamento(parDAO.findById(id_par, conn));
		}
		
		int id_for_pag = rs.getInt(VendaVO.ID_PARCELAMENTO);
		if(id_for_pag > 0){
			FormaPagamentoDAO parDAO = new FormaPagamentoDAOImpl();
			vo.setFormaPagamento(parDAO.findById(id_for_pag, conn));
		}
		
		vo.setValor(rs.getDouble(VendaVO.VALOR));
		vo.setDesconto(rs.getDouble(VendaVO.VALOR_DESCONTO));
		vo.setAcrescimo(rs.getDouble(VendaVO.VALOR_ACRESCIMO));
		vo.setObservacao(rs.getString(VendaVO.OBSERVACAO));
		
		ProdutoVendaDAO produtoDAO = new ProdutoVendaDAOImpl();
		List<ProdutoVendaVO> listP = produtoDAO.getAllProdutoVendaByVenda(vo, conn);
		vo.setProdutos((listP));
		
		ServicoVendaDAO servicoDAO = new ServicoVendaDAOImpl();
		List<ServicoVendaVO> listS = servicoDAO.getAllServicoVendaByVenda(vo, conn);
		vo.setServicos((listS));
		
		ParcelaDAO parcelaDAo = new ParcelaDAOImpl();
		List<ParcelaVO> listPar = parcelaDAo.getAllParcelasByVenda(vo, conn);
		vo.setParcelas((listPar));
		
		vo.setStatusVenda(rs.getString(VendaVO.STATUS_VENDA));
		
		return vo;
	}

	public VendaVO findById(Integer id, Connection conn) throws SQLException {

		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM VENDA WHERE ID_VENDA = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				return this.createVOFromResultSet(rs, conn);
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		}

		return null;
	}
	
	public List<VendaVO> getAllVendasByCliente(ClienteVO cliente, Connection conn) throws SQLException {
		
		List<VendaVO> list = new ArrayList<VendaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = " select * from venda v where v.id_cliente = ? group by v.id_venda order by v.data_venda";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getId());
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				VendaVO vo = this.createVOFromResultSet(rs, conn);
				list.add(vo);
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;

	}


	private int getIdInserido(Connection conn) throws SQLException {

		ResultSet rs = null;
		PreparedStatement stmt = null;

		String sql = " SELECT @@IDENTITY AS ID FROM VENDA";

		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();

		int id = 0;
		while (rs.next())
			id = rs.getInt("ID");

		return id;

	}

	@Override
	public void finalizarVenda(VendaVO venda, String statusVenda, Connection conn) throws SQLException {

		PreparedStatement stmt = null;

		try {

			String sql = " UPDATE VENDA SET " + " STATUS_VENDA = ? " + " WHERE ID_VENDA = ?";

			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, statusVenda);
			stmt.setInt(2, venda.getId());

			stmt.executeUpdate();
			conn.commit();
			
			venda.setStatusVenda(statusVenda);
			
		} finally {
			if (stmt != null)
				stmt.close();
		}

		
	}

	
	
}
