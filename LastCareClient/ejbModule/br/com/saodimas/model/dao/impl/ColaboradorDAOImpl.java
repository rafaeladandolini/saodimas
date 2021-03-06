package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.dao.ColaboradorDAO;

public class ColaboradorDAOImpl implements ColaboradorDAO{

	public ColaboradorVO autenticarColaborador(String login, String senha,
			Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM COLABORADOR WHERE LOGIN = ? AND SENHA  = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,login);
			stmt.setString(2,senha);
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

	public void delete(ColaboradorVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM COLABORADOR WHERE ID_COLABORADOR = ? ";
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

	public List<ColaboradorVO> findAll(Connection conn) throws SQLException {
		
		List<ColaboradorVO> list = new ArrayList<ColaboradorVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM COLABORADOR ORDER BY NOME";
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

	public void save(ColaboradorVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO COLABORADOR" +
					"( NOME, " +
					" LOGIN, " +
					" SENHA, " +
					" DATA_CASTRO, " +
					" STATUS, " +
					" MATRICULA, " +
					" TIPO_COLABORADOR)" +
					" VALUES (?,?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getLogin());
			stmt.setString(3,entity.getSenha());
			stmt.setDate(4, new java.sql.Date(entity.getDataCadastro().getTime()));
			stmt.setString(5,entity.getStatus());
			stmt.setString(6,entity.getMatricula());
			stmt.setString(7,entity.getTipoColaborador());
			
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ColaboradorVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE COLABORADOR SET NOME = ?," +
						" LOGIN = ?, " +
						" SENHA = ?, " +
						" DATA_CASTRO = ?, " +
						" STATUS = ?, " +
						" MATRICULA = ?, " +
						" TIPO_COLABORADOR = ? " +
						" WHERE ID_COLABORADOR = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getLogin());
			stmt.setString(3,entity.getSenha());
			stmt.setDate(4, new java.sql.Date(entity.getDataCadastro().getTime()));
			stmt.setString(5,entity.getStatus());
			stmt.setString(6,entity.getMatricula());
			stmt.setString(7,entity.getTipoColaborador());
			stmt.setInt(8,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		

		
	}
	
	private ColaboradorVO createVOFromResultSet(ResultSet rs) throws SQLException
	{
		ColaboradorVO vo = new ColaboradorVO();
		vo.setDataCadastro(rs.getDate(ColaboradorVO.DATA_CADASTRO));
		vo.setId(rs.getInt(ColaboradorVO.ID_COLABORADOR));
		vo.setLogin(rs.getString(ColaboradorVO.LOGIN));
		vo.setMatricula(rs.getString(ColaboradorVO.MATRICULA));
		vo.setNome(rs.getString(ColaboradorVO.NOME));
		vo.setSenha(rs.getString(ColaboradorVO.SENHA));
		vo.setStatus(rs.getString(ColaboradorVO.STATUS));
		vo.setTipoColaborador(rs.getString(ColaboradorVO.TIPO_COLABORADOR));
			
		return vo;
	}


	public ColaboradorVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM COLABORADOR WHERE ID_COLABORADOR = ?";
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
	



			
}
