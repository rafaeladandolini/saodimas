package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.dao.ConsultaConstants;
import br.com.saodimas.model.dao.ServicoDAO;

public class ServicoDAOImpl implements ServicoDAO{

	public List<ServicoVO> getAllServicosByStatus(String status, Connection conn) throws SQLException{
		List<ServicoVO> list = new ArrayList<ServicoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO WHERE STATUS = ? ORDER BY NOME";
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


	public void delete(ServicoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM SERVICO WHERE ID_SERVICO = ? ";
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

	public List<ServicoVO> findAll(Connection conn) throws SQLException {
		
		List<ServicoVO> list = new ArrayList<ServicoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO ORDER BY NOME";
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

	public void save(ServicoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO SERVICO(NOME,REFERENCIA_VALOR,STATUS,VALOR) VALUES (?,?,?,?)";
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getReferenciaValor());
			stmt.setString(3,entity.getStatus());
			stmt.setDouble(4,entity.getValor());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ServicoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE SERVICO SET NOME = ?," +
						" REFERENCIA_VALOR = ?, " +
						" STATUS = ?, " +
						" VALOR = ? " +
						" WHERE ID_SERVICO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getReferenciaValor());
			stmt.setString(3,entity.getStatus());
			stmt.setDouble(4,entity.getValor());
			stmt.setInt(5,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ServicoVO createVOFromResultSet(ResultSet rs) throws SQLException
	{
		ServicoVO vo = new ServicoVO();
		vo.setId(rs.getInt(br.com.saodimas.model.beans.ServicoVO.ID_SERVICO));
		vo.setNome(rs.getString(ServicoVO.NOME));
		vo.setReferenciaValor(rs.getString(ServicoVO.REFERENCIA_VALOR));
		vo.setStatus(rs.getString(ServicoVO.STATUS));
		vo.setValor(rs.getDouble(ServicoVO.VALOR));
		
		return vo;
	}


	public ServicoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM SERVICO WHERE ID_SERVICO = ?";
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
	
	public void saveAll(Set<ServicoVO> list, Connection conn) throws SQLException {
		Iterator<ServicoVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
	}


	public List<ServicoVO> consultaServico(String tipo, String[] parametros,
			String status, Connection conn) throws SQLException {
		
		if(status.equals(ConsultaConstants.QUALQUER) && tipo.equals(ConsultaConstants.QUALQUER))
		{
			return this.findAll(conn);
		}
		
		String sql = " SELECT * FROM SERVICO";
				
		if(tipo.equals(ConsultaConstants.NOME))
		{
			sql += " WHERE NOME LIKE '%"+parametros[0]+"%'";
			
		}else if(tipo.equals(ConsultaConstants.CUSTO))
		{
			sql += " WHERE VALOR >= "+Double.parseDouble(parametros[0].replace(",", ".")) + " AND VALOR <= " + Double.parseDouble(parametros[1].replace(",", "."));
		}
		
		if(!status.equals(ConsultaConstants.QUALQUER))
		{
			if(sql.contains("WHERE"))
				sql += " AND STATUS = '" +  status +"'";
			else
				sql += " WHERE STATUS = '" +  status +"'";
		}
		
		List<ServicoVO> list = new ArrayList<ServicoVO>();
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