package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.RelacaoVO;
import br.com.saodimas.model.dao.AssociadoDAO;
import br.com.saodimas.model.dao.RelacaoDAO;

public class RelacaoDAOImpl implements RelacaoDAO{

	public void delete(RelacaoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM RELACAO WHERE ID_RELACAO = ? ";
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

	public List<RelacaoVO> findAll(Connection conn) throws SQLException {
		
		List<RelacaoVO> list = new ArrayList<RelacaoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM RELACAO";
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

	public void save(RelacaoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO RELACAO(DESCRICAO,TIPO_RELACAO,STATUS) VALUES (?,?,?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getDescricao());
			stmt.setString(2,entity.getTipoRelacao());
			stmt.setString(3,entity.getStatus());
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(RelacaoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE RELACAO SET DESCRICAO = ?," +
						" TIPO_RELACAO = ?, " +
						" STATUS = ? " +
						" WHERE ID_RELACAO = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, entity.getDescricao());
			stmt.setString(2, entity.getTipoRelacao());
			stmt.setString(3, entity.getStatus());
			stmt.setInt(4,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private RelacaoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		RelacaoVO vo = new RelacaoVO();
		vo.setId(rs.getInt(RelacaoVO.ID_RELACAO));
		vo.setDescricao(rs.getString(RelacaoVO.DESCRICAO));
		vo.setStatus(rs.getString(RelacaoVO.STATUS));
		vo.setTipoRelacao(rs.getString(RelacaoVO.TIPO_RELACAO));
		
		AssociadoDAO associadoDAO = new AssociadoDAOImpl();
		int id_asso =  rs.getInt(RelacaoVO.ID_ASSOCIADO);
		if(id_asso > 0)
			vo.setAssociado(associadoDAO.findById(id_asso, conn));
				
		return vo;
	}


	public List<RelacaoVO> getAllRelacoesByTipoStatus(String status,
			String tipo, Connection conn) throws SQLException {
		
		List<RelacaoVO> list = new ArrayList<RelacaoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM RELACAO WHERE STATUS = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, status);
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

	public RelacaoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM RELACAO WHERE ID_RELACAO = ?";
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
