package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.CemiterioVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.dao.CemiterioDAO;
import br.com.saodimas.model.dao.CidadeDAO;

public class CemiterioDAOImpl implements CemiterioDAO{

	public void delete(CemiterioVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM RELACAO WHERE ID_CEMITERIO = ? ";
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

	public List<CemiterioVO> findAll(Connection conn) throws SQLException {
		
		List<CemiterioVO> list = new ArrayList<CemiterioVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CEMITERIO";
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

	public void save(CemiterioVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO CEMITERIO(NOME,ID_CIDADE) VALUES (?,?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setInt(2,entity.getCidade().getId());
		
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(CemiterioVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE CEMITERIO SET NOME = ?," +
						" ID_CIDADE = ? " +
						" WHERE ID_CEMITERIO = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, entity.getNome());
			stmt.setInt(2, entity.getCidade().getId());
			stmt.setInt(3,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private CemiterioVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		CemiterioVO vo = new CemiterioVO();
		vo.setId(rs.getInt(CemiterioVO.ID_CEMITEERIO));
		vo.setNome(rs.getString(CemiterioVO.NOME));
		
		CidadeDAO cidadeDAO = new CidadeDAOImpl();
		int id_asso =  rs.getInt(CidadeVO.ID_CIDADE);
		if(id_asso > 0)
			vo.setCidade(cidadeDAO.findById(id_asso, conn));
				
		return vo;
	}


	
	public CemiterioVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CEMITERIO WHERE ID_CEMITERIO = ?";
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
