package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.TipoAtendimentoVO;
import br.com.saodimas.model.dao.TipoAtendimentoDAO;

public class TipoAtendimentoDAOImpl implements TipoAtendimentoDAO{

	public void delete(TipoAtendimentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM TIPO_ATENDIMENTO WHERE ID_TIPO_ATENDIMENTO = ? ";
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

	public List<TipoAtendimentoVO> findAll(Connection conn) throws SQLException {
		
		List<TipoAtendimentoVO> list = new ArrayList<TipoAtendimentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM TIPO_ATENDIMENTO";
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

	public void save(TipoAtendimentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO TIPO_ATENDIMENTO(DESCRICAO) VALUES (?)";
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

	public void update(TipoAtendimentoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE TIPO_ATENDIMENTO SET DESCRICAO = ?" +
						 " WHERE ID_TIPO_ATENDIMENTO = ?";
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
	
	private TipoAtendimentoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		TipoAtendimentoVO vo = new TipoAtendimentoVO();
		vo.setId(rs.getInt(TipoAtendimentoVO.ID_TIPO_ATENDIMENTO));
		vo.setDescricao(rs.getString(TipoAtendimentoVO.DESCRICAO));
		
				
		return vo;
	}


	
	public TipoAtendimentoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM TIPO_ATENDIMENTO WHERE ID_TIPO_ATENDIMENTO = ?";
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
