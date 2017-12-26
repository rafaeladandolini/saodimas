package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.beans.ProdutoVendaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.model.dao.ProdutoDAO;
import br.com.saodimas.model.dao.ProdutoVendaDAO;

public class ProdutoVendaDAOImpl implements ProdutoVendaDAO{

	public void delete(ProdutoVendaVO entity, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PRODUTO_VENDA WHERE ID_PRODUTO_VENDA = ? ";
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

	public List<ProdutoVendaVO> findAll(Connection conn) throws SQLException{
		
		List<ProdutoVendaVO> list = new ArrayList<ProdutoVendaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_VENDA";
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

	public void save(ProdutoVendaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO PRODUTO_VENDA" +
					" (ID_PRODUTO," +
					" ID_VENDA, " +
					" QUANTIDADE, " +
					" TOTAL, " +
					" VALOR)" +
					" VALUES (?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getProduto().getId());
			stmt.setInt(2, entity.getVenda().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setDouble(4, entity.getTotal());
			stmt.setDouble(5, entity.getValor());
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}

	public void update(ProdutoVendaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE PRODUTO_VENDA SET " +
						 " ID_PRODUTO = ?, " +
						 " ID_VENDA = ?, " +
						 " QUANTIDADE = ?, " +
						 " TOTAL = ?, " +
						 " VALOR = ? " + 
						 " WHERE ID_PRODUTO_VENDA = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getProduto().getId());
			stmt.setInt(2, entity.getVenda().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setDouble(4, entity.getTotal());
			stmt.setDouble(5, entity.getValor());
			stmt.setInt(6, entity.getId());
						
			stmt.executeUpdate();
			conn.commit();
		}finally {
			if (stmt != null)
				stmt.close();
		}
		
	}
	
	private ProdutoVendaVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ProdutoVendaVO vo = new ProdutoVendaVO();
		vo.setId(rs.getInt(ProdutoVendaVO.ID_PRODUTO_VENDA));
		vo.setQuantidade(rs.getInt(ProdutoVendaVO.QUANTIDADE));
		
		int id_prod = rs.getInt(ProdutoVendaVO.ID_PRODUTO);
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
		ProdutoVO produto = produtoDAO.findById(id_prod, conn);
		vo.setProduto(produto);
		
		vo.setTotal(rs.getDouble(ProdutoVendaVO.TOTAL));
		vo.setValor(rs.getDouble(ProdutoVendaVO.VALOR));
		
		return vo;
		
	}

	public ProdutoVendaVO findById(Integer id, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_VENDA WHERE ID_PRODUTO_VENDA = ?";
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

	public List<ProdutoVendaVO> getAllProdutoVendaByVenda(VendaVO venda, Connection conn) throws SQLException {
		
		List<ProdutoVendaVO> list = new ArrayList<ProdutoVendaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_VENDA WHERE ID_VENDA = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, venda.getId());
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

	public void deleteAllProdutoVendaByVenda(VendaVO venda, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PRODUTO_VENDA WHERE ID_VENDA = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,venda.getId());
			stmt.executeUpdate();
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
	}
		
}
