package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.dao.ConsultaConstants;
import br.com.saodimas.model.dao.ProdutoDAO;

public class ProdutoDAOImpl implements ProdutoDAO{

	public List<ProdutoVO> getAllProdutosByStatus(String status, Connection conn) throws SQLException{
		List<ProdutoVO> list = new ArrayList<ProdutoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO WHERE STATUS = ? ORDER BY NOME ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, status);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				list.add(this.createVOFromResultSet(rs));
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;
	}
	
	public List<ProdutoVO> getAllProdutosByStatusTipoProduto(String status,String tipoProduto, Connection conn) throws SQLException{
		List<ProdutoVO> list = new ArrayList<ProdutoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO WHERE STATUS = ? AND TIPO_PRODUTO = ? ORDER BY NOME ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setString(2, tipoProduto);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				list.add(this.createVOFromResultSet(rs));
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;
	}


	public void delete(ProdutoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PRODUTO WHERE ID_PRODUTO = ? ";
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, entity.getId());
			stmt.executeUpdate();
			
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
		}
		
	}

	public List<ProdutoVO> findAll(Connection conn) throws SQLException {
		
		List<ProdutoVO> list = new ArrayList<ProdutoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO ORDER BY NOME";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				list.add(this.createVOFromResultSet(rs));
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;

	}

	public void save(ProdutoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO PRODUTO(NOME,REFERENCIA_VALOR,STATUS,DESCRICAO,VALOR,TIPO_PRODUTO) VALUES (?,?,?,?,?,?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getReferenciaValor());
			stmt.setString(3,entity.getStatus());
			stmt.setString(4,entity.getDescricao());
			stmt.setDouble(5,entity.getValor());
			stmt.setString(6,entity.getTipoProduto());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ProdutoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE PRODUTO SET NOME = ?," +
						" STATUS = ?, " +
						" DESCRICAO = ?, " +
						" TIPO_PRODUTO = ?," +
						" REFERENCIA_VALOR = ?," +
						" VALOR = ? " +
						" WHERE ID_PRODUTO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getStatus());
			stmt.setString(3,entity.getDescricao());
			stmt.setString(4,entity.getTipoProduto());
			stmt.setString(5,entity.getReferenciaValor());
			stmt.setDouble(6,entity.getValor());
			stmt.setInt(7,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ProdutoVO createVOFromResultSet(ResultSet rs) throws SQLException
	{
		ProdutoVO vo = new ProdutoVO();
		vo.setId(rs.getInt(ProdutoVO.ID_PRODUTO));
		vo.setNome(rs.getString(ProdutoVO.NOME));
		vo.setReferenciaValor(rs.getString(ProdutoVO.REFERENCIA_VALOR));
		vo.setStatus(rs.getString(ProdutoVO.STATUS));
		vo.setDescricao(rs.getString(ProdutoVO.DESCRICAO));
		vo.setValor(rs.getDouble(ProdutoVO.VALOR));
		vo.setTipoProduto(rs.getString(ProdutoVO.TIPO_PRODUTO));
		
		return vo;
	}


	public ProdutoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PRODUTO WHERE ID_PRODUTO = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return this.createVOFromResultSet(rs);
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return null;
	}


	public void saveAll(Set<ProdutoVO> list, Connection conn) throws SQLException {
		Iterator<ProdutoVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
	}


	public List<ProdutoVO> consultaProduto(String tipo, String[] parametros,
			String status, Connection conn) throws SQLException {
		
		if(status.equals(ConsultaConstants.QUALQUER) && tipo.equals(ConsultaConstants.QUALQUER))
		{
			return this.findAll(conn);
		}
		
		String sql = " SELECT * FROM PRODUTO";
				
		if(tipo.equals(ConsultaConstants.NOME))
		{
			sql += " WHERE NOME LIKE '%"+parametros[0]+"%'";
			
		}else
			if(tipo.equals(ConsultaConstants.DESCRICAO))
			{
				sql += " WHERE DESCRICAO LIKE '%"+parametros[0]+"%'";
				
			}else if (tipo.equals(ConsultaConstants.TIPO_PRODUTO))
			{
				sql += " WHERE TIPO_PRODUTO LIKE '%"+parametros[0]+"%'";
			}
		
		if(!status.equals(ConsultaConstants.QUALQUER))
		{
			if(sql.contains("WHERE"))
				sql += " AND STATUS = '" +  status + "'";
			else
				sql += " WHERE STATUS = '" + status + "'";
		}
		
		sql += " ORDER BY NOME";
		
		List<ProdutoVO> list = new ArrayList<ProdutoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				list.add(this.createVOFromResultSet(rs));
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;
	}

		
}
