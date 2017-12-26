package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.FormaPagamentoVO;
import br.com.saodimas.model.dao.FormaPagamentoDAO;

public class FormaPagamentoDAOImpl implements FormaPagamentoDAO{

	public void delete(FormaPagamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM FORMA_PAGAMENTO WHERE ID_FORMA_PAGAMENTO = ? ";
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

	public List<FormaPagamentoVO> findAll(Connection conn) throws SQLException {
		
		List<FormaPagamentoVO> list = new ArrayList<FormaPagamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FORMA_PAGAMENTO";
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

	public void save(FormaPagamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO FORMA_PAGAMENTO(DESCRICAO) VALUES (?)";
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

	public void update(FormaPagamentoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE FORMA_PAGAMENTO SET DESCRICAO = ?" +
						 " WHERE ID_FORMA_PAGAMENTO = ?";
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
	
	private FormaPagamentoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		FormaPagamentoVO vo = new FormaPagamentoVO();
		vo.setId(rs.getInt(FormaPagamentoVO.ID_FORMA_PAGAMENTO));
		vo.setDescricao(rs.getString(FormaPagamentoVO.DESCRICAO));
		
				
		return vo;
	}


	
	public FormaPagamentoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FORMA_PAGAMENTO WHERE ID_FORMA_PAGAMENTO = ?";
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
