package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.FornecedorVO;
import br.com.saodimas.model.dao.FornecedorDAO;

public class FornecedorDAOImpl implements FornecedorDAO{

	public void delete(FornecedorVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM FORNECEDOR WHERE ID_FORNECEDOR = ? ";
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

	public List<FornecedorVO> findAll(Connection conn) throws SQLException {
		
		List<FornecedorVO> list = new ArrayList<FornecedorVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FORNECEDOR ORDER BY NOME";
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

	public void save(FornecedorVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO FORNECEDOR(NOME,CONTATO) VALUES (?,?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getContato());
									
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(FornecedorVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE FORNECEDOR SET NOME = ?," +
						" CONTATO = ? " +
						" WHERE ID_FORNECEDOR = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, entity.getNome());
			stmt.setString(2, entity.getContato());
			stmt.setInt(3,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private FornecedorVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		FornecedorVO vo = new FornecedorVO();
		vo.setId(rs.getInt(FornecedorVO.ID_FORNECEDOR));
		vo.setNome(rs.getString(FornecedorVO.NOME));
		vo.setContato(rs.getString(FornecedorVO.CONTATO));
				
		return vo;
	}

	public FornecedorVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FORNECEDOR WHERE ID_FORNECEDOR = ?";
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
