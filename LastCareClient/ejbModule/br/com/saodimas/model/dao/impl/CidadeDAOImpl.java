package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.dao.CidadeDAO;

public class CidadeDAOImpl implements CidadeDAO{


	public List<CidadeVO> getAllCidadesByEstado(String estado, Connection conn) throws SQLException{
		
		List<CidadeVO> list = new ArrayList<CidadeVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CIDADE WHERE ESTADO = ? ORDER BY NOME_CIDADE";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,estado);
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

	public void delete(CidadeVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM CIDADE WHERE ID_CIDADE = ? ";
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

	public List<CidadeVO> findAll(Connection conn) throws SQLException {
		
		List<CidadeVO> list = new ArrayList<CidadeVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CIDADE ORDER BY NOME_CIDADE";
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

	public void save(CidadeVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO CIDADE(NOME_CIDADE,ESTADO,STATUS) VALUES (?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getEstado());
			stmt.setString(3,entity.getStatus());
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(CidadeVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE CIDADE SET NOME_CIDADE = ?," +
						" ESTADO = ?, " +
						" STATUS = ? " +
						" WHERE ID_CIDADE = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getEstado());
			stmt.setString(3,entity.getStatus());
			stmt.setInt(4,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private CidadeVO createVOFromResultSet(ResultSet rs) throws SQLException
	{
		CidadeVO vo = new CidadeVO();
		vo.setId(rs.getInt(CidadeVO.ID_CIDADE));
		vo.setNome(rs.getString(CidadeVO.NOME_CIDADE));
		vo.setEstado(rs.getString(CidadeVO.ESTADO));
		vo.setStatus(rs.getString(CidadeVO.STATUS));
		
		return vo;
	}


	public CidadeVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CIDADE WHERE ID_CIDADE = ?";
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


		
}
