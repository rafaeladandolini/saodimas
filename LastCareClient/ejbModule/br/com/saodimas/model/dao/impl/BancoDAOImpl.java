package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.BancoVO;
import br.com.saodimas.model.dao.BancoDAO;

public class BancoDAOImpl implements BancoDAO{

	public void delete(BancoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM BANCO WHERE ID_BANCO = ? ";
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

	public List<BancoVO> findAll(Connection conn) throws SQLException {
		
		List<BancoVO> list = new ArrayList<BancoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM BANCO";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				list.add(this.createVOFromResultSet(rs, conn));
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;

	}

	public void save(BancoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO BANCO(NOME_BANCO, CODIGO_BANCO) VALUES (?,?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNomeBanco());
			stmt.setString(2,entity.getCodigoBanco());
			
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(BancoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE BANCO SET CODIGO_BANCO = ?, NOME_BANCO = ?" +
						 " WHERE ID_BANCO = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, entity.getCodigoBanco());
			stmt.setString(2, entity.getNomeBanco());
			stmt.setInt(3,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private BancoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		BancoVO vo = new BancoVO();
		vo.setId(rs.getInt(BancoVO.ID_BANCO));
		vo.setCodigoBanco(rs.getString(BancoVO.CODIGO_BANCO));
		vo.setNomeBanco(rs.getString(BancoVO.NOME_BANCO));
				
		return vo;
	}


	
	public BancoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM BANCO WHERE ID_BANCO = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next())
				return this.createVOFromResultSet(rs, conn);
			
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return null;
	}
	
}
