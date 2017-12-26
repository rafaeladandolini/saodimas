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
import br.com.saodimas.model.beans.ServicoPlanoVO;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.dao.ServicoDAO;
import br.com.saodimas.model.dao.ServicoPlanoDAO;

public class ServicoPlanoDAOImpl implements ServicoPlanoDAO{

	public void delete(ServicoPlanoVO entity, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM SERVICO_PLANO WHERE ID_SERVICO_PLANO = ? ";
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

	public List<ServicoPlanoVO> findAll(Connection conn) throws SQLException{
		
		List<ServicoPlanoVO> list = new ArrayList<ServicoPlanoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_PLANO";
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

	public void save(ServicoPlanoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO SERVICO_PLANO" +
					" (ID_SERVICO," +
					" ID_PLANO, " +
					" QUANTIDADE)" +
					" VALUES (?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getServico().getId());
			stmt.setInt(2, entity.getPlano().getId());
			stmt.setInt(3, entity.getQuantidade());
									
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}

	public void update(ServicoPlanoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE SERVICO_PLANO SET " +
						 " ID_SERVICO = ?, " +
						 " ID_PLANO = ?, " +
						 " QUANTIDADE = ? " +
						 " WHERE ID_SERVICO_PLANO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getServico().getId());
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
	
	private ServicoPlanoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ServicoPlanoVO vo = new ServicoPlanoVO();
		vo.setId(rs.getInt(ServicoPlanoVO.ID_SERVICO_PLANO));
		vo.setQuantidade(rs.getInt(ServicoPlanoVO.QUANTIDADE));
		
		int id_servico = rs.getInt(ServicoPlanoVO.ID_SERVICO);
		ServicoDAO servicoDAO = new ServicoDAOImpl();
		ServicoVO servico = servicoDAO.findById(id_servico, conn);
		vo.setServico(servico);
		
		return vo;
	}
	


	public ServicoPlanoVO findById(Integer id, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_PLANO WHERE ID_SERVICO_PLANO = ?";
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

	public List<ServicoPlanoVO> getAllServicoPlanoByPlano(PlanoVO plano, Connection conn) throws SQLException {
		
		List<ServicoPlanoVO> list = new ArrayList<ServicoPlanoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_PLANO WHERE ID_PLANO = ? ";
			
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

	public void saveAll(Set<ServicoPlanoVO> list, Connection conn)
			throws SQLException {
		
		Iterator<ServicoPlanoVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
			
	}

	public void deleteAllServicoPlanoByPlano(PlanoVO plano, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM SERVICO_PLANO WHERE ID_PLANO = ? ";
			
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
