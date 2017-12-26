package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.EquipamentoVO;
import br.com.saodimas.model.dao.EquipamentoDAO;

public class EquipamentoDAOImpl implements EquipamentoDAO{


	public void delete(EquipamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM EQUIPAMENTO WHERE ID_EQUIPAMENTO = ? ";
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

	public List<EquipamentoVO> findAll(Connection conn) throws SQLException {
		
		List<EquipamentoVO> list = new ArrayList<EquipamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM EQUIPAMENTO ORDER BY DESCRICAO";
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

	public void save(EquipamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO EQUIPAMENTO(" +
					"DESCRICAO, " +
					"DATA_AQUISICAO, " +
					"MODELO, " +
					"VALOR, " +
					"OBSERVACAO, " +
					"STATUS, QUANTIDADE) VALUES (?,?,?,?,?,?,?)";
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,entity.getDescricao());
			stmt.setDate(2,entity.getDataAquisicao()!= null ? new Date(entity.getDataAquisicao().getTime()) : null);
			stmt.setString(3,entity.getModelo());
			stmt.setDouble(4,entity.getValor());
			stmt.setString(5,entity.getObservacao());
			stmt.setString(6,entity.getStatus());
			stmt.setInt(7,entity.getQuantidade());
					
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(EquipamentoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE EQUIPAMENTO SET " +
			"DESCRICAO = ?, " +
			"DATA_AQUISICAO = ?, " +
			"MODELO = ?, " +
			"VALOR = ?, " +
			"OBSERVACAO = ?, " +
			"STATUS = ?, " +
			"QUANTIDADE = ? WHERE ID_EQUIPAMENTO = ?";
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,entity.getDescricao());
			stmt.setDate(2,entity.getDataAquisicao()!= null ? new Date(entity.getDataAquisicao().getTime()) : null);
			stmt.setString(3,entity.getModelo());
			stmt.setDouble(4,entity.getValor());
			stmt.setString(5,entity.getObservacao());
			stmt.setString(6,entity.getStatus());
			stmt.setInt(7,entity.getQuantidade());
			stmt.setInt(8,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private EquipamentoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		EquipamentoVO vo = new EquipamentoVO();
		
		vo.setDataAquisicao(rs.getDate(EquipamentoVO.DATA_AQUISICAO));
		vo.setDescricao(rs.getString(EquipamentoVO.DESCRICAO));
		vo.setId(rs.getInt(EquipamentoVO.ID_EQUIPAMENTO));
		vo.setModelo(rs.getString(EquipamentoVO.MODELO));
		vo.setObservacao(rs.getString(EquipamentoVO.OBSERVACAO));
		vo.setStatus(rs.getString(EquipamentoVO.STATUS));
		vo.setValor(rs.getDouble(EquipamentoVO.VALOR));
		vo.setQuantidade(rs.getInt(EquipamentoVO.QUANTIDADE));
		
		return vo;
	}


	public EquipamentoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM EQUIPAMENTO WHERE ID_EQUIPAMENTO = ?";
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
	
	public void saveAll(Set<EquipamentoVO> list, Connection conn) throws SQLException {
		Iterator<EquipamentoVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
	}

	public List<EquipamentoVO> getAllEquipamentosDisponiveis(Connection conn)
			throws SQLException {

		List<EquipamentoVO> list = new ArrayList<EquipamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = " SELECT * FROM EQUIPAMENTO E WHERE ID_EQUIPAMENTO NOT IN  " +
					     " ( "+
						 "      SELECT ID_EQUIPAMENTO FROM EMPRESTIMO_EQUIPAMENTO WHERE DATA_DEVOLUCAO IS NULL" +
						 "  ) ";
			
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
	
}