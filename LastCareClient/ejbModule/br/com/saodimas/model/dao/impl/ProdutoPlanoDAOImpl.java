package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoPlanoVO;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.dao.ProdutoDAO;
import br.com.saodimas.model.dao.ProdutoPlanoDAO;

public class ProdutoPlanoDAOImpl implements ProdutoPlanoDAO{

	public void delete(ProdutoPlanoVO entity, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PRODUTO_PLANO WHERE ID_PRODUTO_PLANO = ? ";
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

	public List<ProdutoPlanoVO> findAll(Connection conn) throws SQLException{
		
		List<ProdutoPlanoVO> list = new ArrayList<ProdutoPlanoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_PLANO";
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

	public void save(ProdutoPlanoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO PRODUTO_PLANO" +
					" (ID_PRODUTO," +
					" ID_PLANO, " +
					" QUANTIDADE)" +
					" VALUES (?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getProduto().getId());
			stmt.setInt(2, entity.getPlano().getId());
			stmt.setInt(3, entity.getQuantidade());
									
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}

	public void update(ProdutoPlanoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE PRODUTO_PLANO SET " +
						 " ID_PRODUTO = ?, " +
						 " ID_PLANO = ?, " +
						 " QUANTIDADE = ? " +
						 " WHERE ID_PRODUTO_PLANO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getProduto().getId());
			stmt.setInt(2, entity.getPlano().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setInt(4, entity.getId());
						
			stmt.executeUpdate();
			conn.commit();
		}finally {
			if (stmt != null)
				stmt.close();
		}
		
	}
	
	private ProdutoPlanoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ProdutoPlanoVO vo = new ProdutoPlanoVO();
		vo.setId(rs.getInt(ProdutoPlanoVO.ID_PRODUTO_PLANO));
		vo.setQuantidade(rs.getInt(ProdutoPlanoVO.QUANTIDADE));
		
		int id_prod = rs.getInt(ProdutoPlanoVO.ID_PRODUTO);
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
		ProdutoVO produto = produtoDAO.findById(id_prod, conn);
		vo.setProduto(produto);
		
		return vo;
	}
	


	public ProdutoPlanoVO findById(Integer id, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_PLANO WHERE ID_PRODUTO_PLANO = ?";
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

	public List<ProdutoPlanoVO> getAllProdutoPlanoByPlano(PlanoVO plano, Connection conn) throws SQLException {
		
		List<ProdutoPlanoVO> list = new ArrayList<ProdutoPlanoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO_PLANO WHERE ID_PLANO = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, plano.getId());
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

	public void saveAll(Set<ProdutoPlanoVO> list, PlanoVO plano, Connection conn)
			throws SQLException {
		
		Iterator<ProdutoPlanoVO> it = list.iterator();
		while(it.hasNext())
		{
			ProdutoPlanoVO vo = it.next();
			vo.setPlano(plano);
			this.save(vo, conn);
		}
			
			
	}

	public void deleteAllProdutoPlanoByPlano(PlanoVO plano, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PRODUTO_PLANO WHERE ID_PLANO = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, plano.getId());
			stmt.executeUpdate();
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
	}
		
}
