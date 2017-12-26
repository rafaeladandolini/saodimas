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

import br.com.saodimas.model.beans.ParceiroVO;
import br.com.saodimas.model.dao.CidadeDAO;
import br.com.saodimas.model.dao.ParceiroDAO;
import br.com.saodimas.view.util.ListasComuns;

public class ParceiroDAOImpl implements ParceiroDAO{

	public List<ParceiroVO> getAllParceirosAtivos(Connection conn) throws SQLException{
		List<ParceiroVO> list = new ArrayList<ParceiroVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCEIRO WHERE STATUS = '" + ListasComuns.STATUS_ITENS[0] + "' ORDER BY DESCRICAO";
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


	public void delete(ParceiroVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PARCEIRO WHERE ID_PARCEIRO = ? ";
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

	public List<ParceiroVO> findAll(Connection conn) throws SQLException {
		
		List<ParceiroVO> list = new ArrayList<ParceiroVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCEIRO ORDER BY DESCRICAO";
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

	public void save(ParceiroVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO PARCEIRO(" +
					"DESCRICAO, " +
					"RESPONSAVEL, " +
					"DATA_INICIO, " +
					"ENDERECO, " +
					"TELEFONE, " +
					"ID_CIDADE, " +
					"OBSERVACAO, " +
					"STATUS) VALUES (?,?,?,?,?,?,?,?)";
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,entity.getDescricao());
			stmt.setString(2,entity.getResponsavel());
			stmt.setDate(3,entity.getDataInicio() != null ? new Date(entity.getDataInicio().getTime()) : null);
			stmt.setString(4,entity.getEndereco());
			stmt.setString(5,entity.getTelefone());
			stmt.setInt(6,entity.getCidade().getId());
			stmt.setString(7,entity.getObservacao());
			stmt.setString(8,entity.getStatus());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ParceiroVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE PARCEIRO SET " +
					"DESCRICAO = ?, " +
					"RESPONSAVEL = ?, " +
					"DATA_INICIO = ?, " +
					"ENDERECO = ?, " +
					"TELEFONE = ?, " +
					"ID_CIDADE = ?, " +
					"OBSERVACAO = ?, " +
					"STATUS = ? WHERE ID_PARCEIRO = ?";
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,entity.getDescricao());
			stmt.setString(2,entity.getResponsavel());
			stmt.setDate(3,entity.getDataInicio() != null ? new Date(entity.getDataInicio().getTime()) : null);
			stmt.setString(4,entity.getEndereco());
			stmt.setString(5,entity.getTelefone());
			stmt.setInt(6,entity.getCidade().getId());
			stmt.setString(7,entity.getObservacao());
			stmt.setString(8,entity.getStatus());
			stmt.setInt(9,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ParceiroVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ParceiroVO vo = new ParceiroVO();
		int id_cidade = rs.getInt(ParceiroVO.ID_CIDADE);
		CidadeDAO cidadeDAO = new CidadeDAOImpl();
		vo.setCidade(cidadeDAO.findById(id_cidade, conn));
		vo.setDataInicio(rs.getDate(ParceiroVO.DATA_INICIO));
		vo.setDescricao(rs.getString(ParceiroVO.DESCRICAO));
		vo.setEndereco(rs.getString(ParceiroVO.ENDERECO));
		vo.setId(rs.getInt(ParceiroVO.ID_PARCEIRO));
		vo.setObservacao(rs.getString(ParceiroVO.OBSERVACAO));
		vo.setResponsavel(rs.getString(ParceiroVO.RESPONSAVEL));
		vo.setStatus(rs.getString(ParceiroVO.STATUS));
		vo.setTelefone(rs.getString(ParceiroVO.TELEFONE));
		
		return vo;
	}


	public ParceiroVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCEIRO WHERE ID_PARCEIRO = ?";
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
	
	public void saveAll(Set<ParceiroVO> list, Connection conn) throws SQLException {
		Iterator<ParceiroVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
	}
	
}