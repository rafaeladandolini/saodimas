package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ContaVO;
import br.com.saodimas.model.dao.BancoDAO;
import br.com.saodimas.model.dao.ContaDAO;
import br.com.saodimas.model.dao.TipoContaDAO;

public class ContaDAOImpl implements ContaDAO{

	public void delete(ContaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM CONTA WHERE ID_CONTA = ? ";
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

	public List<ContaVO> findAll(Connection conn) throws SQLException {
		
		List<ContaVO> list = new ArrayList<ContaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CONTA";
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

	public void save(ContaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO CONTA(ID_BANCO, AGENCIA , NUMERO , NOME, ID_TIPO_CONTA ) VALUES (?,?,?,?,?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,entity.getBanco().getId());
			stmt.setString(2,entity.getAgencia());
			stmt.setString(3,entity.getNumero());
			stmt.setString(4,entity.getNome());
			stmt.setInt(5,entity.getTipoConta().getId());
			
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ContaVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE CONTA SET ID_BANCO = ?, AGENCIA = ?, NUMERO = ?, NOME = ?, ID_TIPO_CONTA = ? " +
						 " WHERE ID_CONTA = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getBanco().getId());
			stmt.setString(2, entity.getAgencia());
			stmt.setString(3, entity.getNumero());
			stmt.setString(4, entity.getNome());
			stmt.setInt(5, entity.getTipoConta().getId());
			stmt.setInt(6,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ContaVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ContaVO vo = new ContaVO();
		vo.setId(rs.getInt(ContaVO.ID_CONTA));
		vo.setNome(rs.getString(ContaVO.NOME));
		vo.setAgencia(rs.getString(ContaVO.AGENCIA));
		vo.setNumero(rs.getString(ContaVO.NUMERO));
		
		BancoDAO daoBanco = new BancoDAOImpl();
		int id_banco = rs.getInt(ContaVO.ID_BANCO);
		vo.setBanco(daoBanco.findById(id_banco, conn));
				
		TipoContaDAO daoTipoConta = new TipoContaDAOImpl();
		int id_tipoConta = rs.getInt(ContaVO.ID_TIPO_CONTA);
		vo.setTipoConta(daoTipoConta.findById(id_tipoConta, conn));
						
		return vo;
	}


	
	public ContaVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CONTA WHERE ID_CONTA = ?";
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
