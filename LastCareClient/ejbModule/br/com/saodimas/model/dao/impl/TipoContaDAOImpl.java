package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.TipoContaVO;
import br.com.saodimas.model.dao.TipoContaDAO;

public class TipoContaDAOImpl implements TipoContaDAO{

	public void delete(TipoContaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM TIPO_CONTA WHERE ID_TIPO_CONTA = ? ";
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

	public List<TipoContaVO> findAll(Connection conn) throws SQLException {
		
		List<TipoContaVO> list = new ArrayList<TipoContaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM TIPO_CONTA";
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

	public void save(TipoContaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO TIPO_CONTA(DESCRICAO) VALUES (?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getDescricao());
			
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(TipoContaVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE TIPO_CONTA SET DESCRICAO = ?" +
						 " WHERE ID_TIPO_CONTA = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, entity.getDescricao());
			stmt.setInt(2,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private TipoContaVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		TipoContaVO vo = new TipoContaVO();
		vo.setId(rs.getInt(TipoContaVO.ID_TIPO_CONTA));
		vo.setDescricao(rs.getString(TipoContaVO.DESCRICAO));
		
				
		return vo;
	}


	
	public TipoContaVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM TIPO_CONTA WHERE ID_TIPO_CONTA = ?";
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
