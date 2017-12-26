package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.CompraVO;
import br.com.saodimas.model.dao.CompraDAO;
import br.com.saodimas.model.dao.FornecedorDAO;
import br.com.saodimas.model.dao.ProdutoCompraDAO;

public class CompraDAOImpl implements CompraDAO {

	public void delete(CompraVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			ProdutoCompraDAO dao = new ProdutoCompraDAOImpl();
			dao.deleteAllProdutoCompraByCompra(entity, conn);

			String sql = "DELETE FROM COMPRA WHERE ID_COMPRA = ? ";
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

	public List<CompraVO> findAll(Connection conn) throws SQLException {

		List<CompraVO> list = new ArrayList<CompraVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM COMPRA ORDER BY DATA_COMPRA";
			
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

	public void save(CompraVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO COMPRA" + " (DATA_COMPRA,"
					+ " DESCRICAO," + " VENDEDOR," + " OBSERVACAO,"
					+ " ID_FORNECEDOR, DATA_ENTREGA)" + " VALUES (?,?,?,?,?,?)";

			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);

			stmt.setDate(1, entity.getDataCompra() != null ? new Date(entity.getDataCompra().getTime()) : null);
			stmt.setString(2, entity.getDescricao());
			stmt.setString(3, entity.getVendedor());
			stmt.setString(4, entity.getObservacao());
			stmt.setInt(5, entity.getFornecedor().getId());
			stmt.setDate(6, entity.getDataEntrega() != null ? new Date(entity.getDataEntrega().getTime()) : null);

			stmt.executeUpdate();
			conn.commit();

			entity.setId(this.getIdInserido(conn));
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	public void update(CompraVO entity, Connection conn) throws SQLException {

		PreparedStatement stmt = null;

		try {
			String sql = " UPDATE COMPRA SET " + " DATA_COMPRA = ?, "
					+ " DESCRICAO = ?, " + " VENDEDOR = ?, "
					+ " OBSERVACAO = ?, " + " ID_FORNECEDOR = ?"
					+ " WHERE ID_COMPRA = ?";

			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);

			stmt.setDate(1, entity.getDataCompra() != null ? new Date(entity
					.getDataCompra().getTime()) : null);
			stmt.setString(2, entity.getDescricao());
			stmt.setString(3, entity.getVendedor());
			stmt.setString(4, entity.getObservacao());
			stmt.setInt(5, entity.getFornecedor().getId());
			stmt.setInt(6, entity.getId());

			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	private CompraVO createVOFromResultSet(ResultSet rs, Connection conn)
			throws SQLException {
		CompraVO vo = new CompraVO();

		vo.setId(rs.getInt(CompraVO.ID_COMPRA));
		vo.setDataCompra(rs.getDate(CompraVO.DATA_COMPRA));
		vo.setDescricao(rs.getString(CompraVO.DESCRICAO));
		vo.setVendedor(rs.getString(CompraVO.VENDEDOR));
		vo.setObservacao(rs.getString(CompraVO.OBSERVACAO));
		int idEmpresa = rs.getInt(CompraVO.ID_FORNECEDOR);

		FornecedorDAO dao = new FornecedorDAOImpl();
		vo.setEmpresa(dao.findById(idEmpresa, conn));
		
		ProdutoCompraDAO daoCompraPr = new ProdutoCompraDAOImpl();
		vo.setProdutos(daoCompraPr.getAllProdutoCompraByCompra(vo, conn));

		return vo;
	}

	public CompraVO findById(Integer id, Connection conn) throws SQLException {

		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			String sql = "SELECT * FROM COMPRA WHERE ID_COMPRA = ?";
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

	private int getIdInserido(Connection conn) throws SQLException {

		ResultSet rs = null;
		PreparedStatement stmt = null;

		String sql = " SELECT @@IDENTITY AS ID FROM COMPRA";

		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();

		int id = 0;
		while (rs.next())
			id = rs.getInt("ID");

		return id;

	}

	
	
}
