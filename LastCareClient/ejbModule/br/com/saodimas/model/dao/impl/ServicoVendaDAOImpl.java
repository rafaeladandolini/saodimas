package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.model.dao.ServicoDAO;
import br.com.saodimas.model.dao.ServicoVendaDAO;

public class ServicoVendaDAOImpl implements ServicoVendaDAO{

	public void delete(ServicoVendaVO entity, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM SERVICO_VENDA WHERE ID_SERVICO_VENDA = ? ";
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

	public List<ServicoVendaVO> findAll(Connection conn) throws SQLException{
		
		List<ServicoVendaVO> list = new ArrayList<ServicoVendaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_VENDA";
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

	public void save(ServicoVendaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO SERVICO_VENDA" +
					" (ID_SERVICO," +
					" ID_VENDA, " +
					" QUANTIDADE, " +
					" TOTAL, " +
					" VALOR)" +
					" VALUES (?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getServico().getId());
			stmt.setInt(2, entity.getVenda().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setDouble(4, entity.getTotal());
			stmt.setDouble(5, entity.getValor());
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}

	public void update(ServicoVendaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE SERVICO_VENDA SET " +
						 " ID_SERVICO = ?, " +
						 " ID_VENDA = ?, " +
						 " QUANTIDADE = ?, " +
						 " TOTAL = ?, " +
						 " VALOR = ? " + 
						 " WHERE ID_SERVICO_VENDA = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getServico().getId());
			stmt.setInt(2, entity.getVenda().getId());
			stmt.setInt(3, entity.getQuantidade());
			stmt.setDouble(4, entity.getTotal());
			stmt.setDouble(5, entity.getValor());
			stmt.setInt(6, entity.getId());
						
			stmt.executeUpdate();
			conn.commit();
		}finally {
			if (stmt != null)
				stmt.close();
		}
		
	}
	
	private ServicoVendaVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ServicoVendaVO vo = new ServicoVendaVO();
		vo.setId(rs.getInt(ServicoVendaVO.ID_SERVICO_VENDA));
		vo.setQuantidade(rs.getInt(ServicoVendaVO.QUANTIDADE));
		
		int id_servico = rs.getInt(ServicoVendaVO.ID_SERVICO);
		ServicoDAO servicoDAo = new ServicoDAOImpl();
		ServicoVO servico = servicoDAo.findById(id_servico, conn);
		vo.setServico(servico);
		
		vo.setTotal(rs.getDouble(ServicoVendaVO.TOTAL));
		vo.setValor(rs.getDouble(ServicoVendaVO.VALOR));
		
		return vo;
		
	}

	public ServicoVendaVO findById(Integer id, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_VENDA WHERE ID_SERVICO_VENDA = ?";
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

	public List<ServicoVendaVO> getAllServicoVendaByVenda(VendaVO venda, Connection conn) throws SQLException {
		
		List<ServicoVendaVO> list = new ArrayList<ServicoVendaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO_VENDA WHERE ID_VENDA = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, venda.getId());
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

	public void deleteAllServicoVendaByVenda(VendaVO venda, Connection conn)
			throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM SERVICO_VENDA WHERE ID_VENDA = ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, venda.getId());
			stmt.executeUpdate();
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
	}

		
}