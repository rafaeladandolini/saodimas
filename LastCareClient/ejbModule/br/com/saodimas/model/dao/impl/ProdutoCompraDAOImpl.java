package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.CompraVO;
import br.com.saodimas.model.beans.ProdutoCompraVO;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.dao.ProdutoCompraDAO;
import br.com.saodimas.model.dao.ProdutoDAO;

public class ProdutoCompraDAOImpl implements ProdutoCompraDAO{
	
	public void delete(ProdutoCompraVO entity, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PRODUTO_COMPRA WHERE ID_PRODUTO_COMPRA = ? ";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, entity.getId());
			stmt.executeUpdate();
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
	}

	public List<ProdutoCompraVO> findAll(Connection conn) throws SQLException{
		
		List<ProdutoCompraVO> list = new ArrayList<ProdutoCompraVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_COMPRA";
			stmt = conn.prepareStatement(sql);
			stmt.execute();
			
			rs = stmt.getResultSet();
			
			while(rs.next())
				list.add(this.createVOFromResultSet(rs, conn));
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;
	}

	public void save(ProdutoCompraVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO PRODUTO_COMPRA" +
					" (ID_PRODUTO," +
					" ID_COMPRA, " +
					" QUANTIDADE, " +
					" VALOR_CUSTO)" +
					" VALUES (?,?,?,?)";
			
			/*String sql = "INSERT INTO PRODUTO_COMPRA" +
					" (ID_PRODUTO," +
					" ID_COMPRA, " +
					" QUANTIDADE, " +
					" VALOR_CUSTO, " +
					" VALOR_VENDA)" +
					" VALUES (?,?,?,?,?)";*/
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getProduto().getId());
			stmt.setInt(2, entity.getCompra().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setDouble(4, entity.getValorCusto());
			//stmt.setDouble(5, entity.getValorVenda());
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}

	public void update(ProdutoCompraVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE PRODUTO_COMPRA SET " +
						 " ID_PRODUTO = ?, " +
						 " ID_COMPRA = ?, " +
						 " QUANTIDADE = ?, " +
						 " VALOR_CUSTO = ?" +
						 /*+ ", " +
						 " VALOR_VENDA = ? " +*/ 
						 " WHERE ID_PRODUTO_COMPRA = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getProduto().getId());
			stmt.setInt(2, entity.getCompra().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setDouble(4, entity.getValorCusto());
			//stmt.setDouble(5, entity.getValorVenda());
			stmt.setInt(5, entity.getId());
						
			stmt.executeUpdate();
			conn.commit();
		}finally {
			if (stmt != null)
				stmt.close();
		}
		
	}
	
	private ProdutoCompraVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ProdutoCompraVO vo = new ProdutoCompraVO();
		vo.setId(rs.getInt(ProdutoCompraVO.ID_PRODUTO_COMPRA));
		vo.setQuantidade(rs.getInt(ProdutoCompraVO.QUANTIDADE));
		
		int id_prod = rs.getInt(ProdutoCompraVO.ID_PRODUTO);
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
		ProdutoVO produto = produtoDAO.findById(id_prod, conn);
		vo.setProduto(produto);
		
		vo.setValorCusto(rs.getDouble(ProdutoCompraVO.VALOR_CUSTO));
		//vo.setValorVenda(rs.getDouble(ProdutoCompraVO.VALOR_VENDA));
		
		return vo;
		
	}

	public ProdutoCompraVO findById(Integer id, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_COMPRA WHERE ID_PRODUTO_COMPRA = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return this.createVOFromResultSet(rs, conn);
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return null;
	}

	public List<ProdutoCompraVO> getAllProdutoCompraByCompra(CompraVO compra, Connection conn) throws SQLException {
		
		List<ProdutoCompraVO> list = new ArrayList<ProdutoCompraVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_COMPRA WHERE ID_COMPRA = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, compra.getId());
			stmt.executeQuery();
			
			rs = stmt.getResultSet();
			
			while(rs.next())
				list.add(this.createVOFromResultSet(rs, conn));
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;
	}

	public void deleteAllProdutoCompraByCompra(CompraVO compra, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PRODUTO_COMPRA WHERE ID_COMPRA = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,compra.getId());
			stmt.executeUpdate();
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
	}
		
}
