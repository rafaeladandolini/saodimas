package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.dao.ServicoDAO;
import br.com.saodimas.model.dao.ServicoObitoDAO;

public class ServicoObitoDAOImpl implements ServicoObitoDAO{

	public void delete(ServicoObitoVO entity, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM SERVICO_OBITO WHERE ID_SERVICO_OBITO = ? ";
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

	public List<ServicoObitoVO> findAll(Connection conn) throws SQLException{
		
		List<ServicoObitoVO> list = new ArrayList<ServicoObitoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_OBITO";
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

	public void save(ServicoObitoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO SERVICO_OBITO" +
					" (ID_SERVICO," +
					" ID_OBITO, " +
					" QUANTIDADE, " +
					" OBSERVACAO, " +
					" TOTAL, " +
					" VALOR)" +
					" VALUES (?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getServico().getId());
			stmt.setInt(2, entity.getObito().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setString(4, entity.getObservacao());
			stmt.setDouble(5, entity.getTotal());
			stmt.setDouble(6, entity.getValor());
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}

	public void update(ServicoObitoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE SERVICO_OBITO SET " +
						 " ID_SERVICO = ?, " +
						 " ID_OBITO = ?, " +
						 " QUANTIDADE = ?, " +
						 " OBSERVACAO = ?, " +
						 " TOTAL = ?, " +
						 " VALOR = ? " + 
						 " WHERE ID_SERVICO_OBITO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getServico().getId());
			stmt.setInt(2, entity.getObito().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setString(4, entity.getObservacao());
			stmt.setDouble(5, entity.getTotal());
			stmt.setDouble(6, entity.getValor());
			stmt.setInt(7, entity.getId());
						
			stmt.executeUpdate();
			conn.commit();
		}finally {
			if (stmt != null)
				stmt.close();
		}
		
	}
	
	private ServicoObitoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ServicoObitoVO vo = new ServicoObitoVO();
		vo.setId(rs.getInt(ServicoObitoVO.ID_SERVICO_OBITO));
		vo.setObservacao(rs.getString(ServicoObitoVO.OBSERVACAO));
		vo.setQuantidade(rs.getInt(ServicoObitoVO.QUANTIDADE));
		
		int id_servico = rs.getInt(ServicoObitoVO.ID_SERVICO);
		ServicoDAO servicoDAo = new ServicoDAOImpl();
		ServicoVO servico = servicoDAo.findById(id_servico, conn);
		vo.setServico(servico);
		
		vo.setTotal(rs.getDouble(ServicoObitoVO.TOTAL));
		vo.setValor(rs.getDouble(ServicoObitoVO.VALOR));
		
		return vo;
		
	}

	public ServicoObitoVO findById(Integer id, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_OBITO WHERE ID_SERVICO_OBITO = ?";
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

	public List<ServicoObitoVO> getAllServicoObitoByObito(ObitoVO obito, Connection conn) throws SQLException {
		
		List<ServicoObitoVO> list = new ArrayList<ServicoObitoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_OBITO WHERE ID_OBITO = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obito.getId());
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

	public void deleteAllServicoObitoByObito(ObitoVO obito, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM SERVICO_OBITO WHERE ID_OBITO = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, obito.getId());
			stmt.executeUpdate();
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
	}

		
}