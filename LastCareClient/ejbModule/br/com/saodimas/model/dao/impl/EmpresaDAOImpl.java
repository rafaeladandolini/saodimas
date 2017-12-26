package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.EmpresaVO;
import br.com.saodimas.model.dao.EmpresaDAO;

public class EmpresaDAOImpl implements EmpresaDAO{

	public void delete(EmpresaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM EMPRESA WHERE ID_EMPRESA = ? ";
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

	public List<EmpresaVO> findAll(Connection conn) throws SQLException {
		
		List<EmpresaVO> list = new ArrayList<EmpresaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM EMPRESA";
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

	public void save(EmpresaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO EMPRESA(DOC_CNPJ,RAZAO_SOCIAL,STATUS) VALUES (?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getDocCNPJ());
			stmt.setString(2,entity.getRazaoSocial());
			stmt.setString(3,entity.getStatus());
						
			stmt.executeUpdate();
			conn.commit();
			
			entity.setId(this.getIdInserido(conn));
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(EmpresaVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = " UPDATE EMPRESA SET " +
						 " DOC_CNPJ = ?," +
						 " RAZAO_SOCIAL = ?," +
						 " STATUS = ? "+
						 " WHERE ID_EMPRESA = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getDocCNPJ());
			stmt.setString(2,entity.getRazaoSocial());
			stmt.setString(3,entity.getStatus());
			stmt.setInt(4,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private EmpresaVO createVOFromResultSet(ResultSet rs) throws SQLException
	{
		EmpresaVO vo = new EmpresaVO();
		vo.setId(rs.getInt(EmpresaVO.ID_EMPRESA));
		vo.setDocCNPJ(rs.getString(EmpresaVO.DOC_CNPJ));
		vo.setRazaoSocial(rs.getString(EmpresaVO.RAZAO_SOCIAL));
		vo.setStatus(rs.getString(EmpresaVO.STATUS));
				
		return vo;
	}


	public EmpresaVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM EMPRESA WHERE ID_EMPRESA = ?";
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
	
	private int getIdInserido(Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = " SELECT @@IDENTITY AS ID FROM EMPRESA";
		
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		int id = 0;
		while(rs.next())
			id = rs.getInt("ID");
		
		return id;
			
		
	}

	
}
