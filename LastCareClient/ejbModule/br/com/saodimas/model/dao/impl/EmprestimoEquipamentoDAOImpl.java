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

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.model.beans.EquipamentoVO;
import br.com.saodimas.model.dao.ApoliceDAO;
import br.com.saodimas.model.dao.AssociadoDAO;
import br.com.saodimas.model.dao.EmprestimoEquipamentoDAO;
import br.com.saodimas.model.dao.EquipamentoDAO;
import br.com.saodimas.view.util.ListasComuns;

public class EmprestimoEquipamentoDAOImpl implements EmprestimoEquipamentoDAO{


	public void delete(EmprestimoEquipamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM EMPRESTIMO_EQUIPAMENTO WHERE ID_EMPRESTIMO_EQUIPAMENTO = ? ";
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

	public List<EmprestimoEquipamentoVO> findAll(Connection conn) throws SQLException {
		
		List<EmprestimoEquipamentoVO> list = new ArrayList<EmprestimoEquipamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO EE, APOLICE A WHERE EE.ID_APOLICE = A.ID_APOLICE ORDER BY EE.DATA_EMPRESTIMO";
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

	public void save(EmprestimoEquipamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO EMPRESTIMO_EQUIPAMENTO(" +
					"ID_EQUIPAMENTO, " +
					"DATA_EMPRESTIMO, " +
					"DATA_PREVISTA_DEVOLUCAO, " +
					"DATA_DEVOLUCAO, " +
					"ID_APOLICE, " +
					"ID_ASSOCIADO, " +
					"OBSERVACAO) VALUES (?,?,?,?,?,?,?)";
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,entity.getEquipamentoVO().getId());
			stmt.setDate(2,entity.getDataEmpresatimo()!= null ? new Date(entity.getDataEmpresatimo().getTime()) : null);
			stmt.setDate(3,entity.getDataPrevisaDevolucao()!= null ? new Date(entity.getDataPrevisaDevolucao().getTime()) : null);
			stmt.setDate(4,entity.getDataDevolucao()!= null ? new Date(entity.getDataDevolucao().getTime()) : null);
			stmt.setInt(5,entity.getApolice().getId());
			stmt.setInt(6,entity.getAssociado().getId());
			stmt.setString(7,entity.getObservacao());

			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(EmprestimoEquipamentoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE EMPRESTIMO_EQUIPAMENTO SET " +
			"ID_EQUIPAMENTO = ?, " +
			"DATA_EMPRESTIMO = ?, " +
			"DATA_PREVISTA_DEVOLUCAO = ?, " +
			"DATA_DEVOLUCAO = ?, " +
			"ID_APOLICE = ?, " +
			"ID_ASSOCIADO = ?, " +
			"OBSERVACAO = ? WHERE ID_EMPRESTIMO_EQUIPAMENTO = ?";
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,entity.getEquipamentoVO().getId());
			stmt.setDate(2,entity.getDataEmpresatimo()!= null ? new Date(entity.getDataEmpresatimo().getTime()) : null);
			stmt.setDate(3,entity.getDataPrevisaDevolucao()!= null ? new Date(entity.getDataPrevisaDevolucao().getTime()) : null);
			stmt.setDate(4,entity.getDataDevolucao()!= null ? new Date(entity.getDataDevolucao().getTime()) : null);
			stmt.setInt(5,entity.getApolice().getId());
			stmt.setInt(6,entity.getAssociado() == null ? 0 : entity.getAssociado().getId());
			stmt.setString(7,entity.getObservacao());
			stmt.setInt(8,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	public void estornoDevolucao(EmprestimoEquipamentoVO entity, Connection conn) throws SQLException {
			
			PreparedStatement stmt = null;
	
			try {
				String sql = "UPDATE EMPRESTIMO_EQUIPAMENTO SET " +
				" DATA_DEVOLUCAO = ? " +
				" WHERE ID_EMPRESTIMO_EQUIPAMENTO = ?";
				
				conn.setAutoCommit(false);
				
				stmt = conn.prepareStatement(sql);
				stmt.setDate(1,null);
				stmt.setInt(2,entity.getId());
				
				stmt.executeUpdate();
				conn.commit();
			} finally {
				if (stmt != null)
					stmt.close();
			}
			
			
		}
	
	
	private EmprestimoEquipamentoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		EmprestimoEquipamentoVO vo = new EmprestimoEquipamentoVO();
		
		int id_apo = rs.getInt(EmprestimoEquipamentoVO.ID_APOLICE);
		if(id_apo > 0)
		{
			ApoliceDAO daoApolice = new ApoliceDAOImpl();
			vo.setApolice(daoApolice.carregarApoliceSimples(id_apo, conn));
		}
				
		vo.setDataDevolucao(rs.getDate(EmprestimoEquipamentoVO.DATA_DEVOLUCAO));
		vo.setDataEmpresatimo(rs.getDate(EmprestimoEquipamentoVO.DATA_EMPRESTIMO));
		vo.setDataPrevisaDevolucao(rs.getDate(EmprestimoEquipamentoVO.DATA_PREVISTA_DEVOLUCAO));
		
		int id_equi = rs.getInt(EmprestimoEquipamentoVO.ID_EQUIPAMENTO);
		if(id_equi > 0)
		{
			EquipamentoDAO daoEquipamento = new EquipamentoDAOImpl();
			vo.setEquipamentoVO(daoEquipamento.findById(id_equi, conn));
		}
		
		int idAssociado = rs.getInt(EmprestimoEquipamentoVO.ID_ASSOCIADO);
		if(idAssociado > 0)
		{
			AssociadoDAO daoAssociado = new AssociadoDAOImpl();
			vo.setAssociado(daoAssociado.findById(idAssociado, conn));
		}
		
		vo.setId(rs.getInt(EmprestimoEquipamentoVO.ID_EMPRESTIMO_EQUIPAMENTO));
		vo.setObservacao(rs.getString(EmprestimoEquipamentoVO.OBSERVACAO));
		
		return vo;
	}


	public EmprestimoEquipamentoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO WHERE ID_EMPRESTIMO_EQUIPAMENTO = ?";
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
	
	public void saveAll(Set<EmprestimoEquipamentoVO> list, Connection conn) throws SQLException {
		Iterator<EmprestimoEquipamentoVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
	}

	public List<EmprestimoEquipamentoVO> getAllEmpretimoEquipamentoVOByApolice(
			ApoliceVO apolice, Connection conn) throws SQLException {
		
		List<EmprestimoEquipamentoVO> list = new ArrayList<EmprestimoEquipamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO WHERE ID_APOLICE = ? AND DATA_DEVOLUCAO IS NULL";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,apolice.getId());
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
	
	public List<EmprestimoEquipamentoVO> getAllDevolucoesEquipamentoVOByApolice(
			ApoliceVO apolice, Connection conn) throws SQLException {
		
		List<EmprestimoEquipamentoVO> list = new ArrayList<EmprestimoEquipamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO WHERE ID_APOLICE = ? AND DATA_DEVOLUCAO IS NOT NULL";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,apolice.getId());
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

	public int getQtdeEquipamentosEmEmprestimo(
			EquipamentoVO equipamento, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String qtde = "0";
		try{
			String sql = "SELECT count(*) AS 'QTDE' FROM emprestimo_equipamento e WHERE e.ID_EQUIPAMENTO = ? AND e.DATA_DEVOLUCAO IS NULL;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, equipamento.getId());
			rs = stmt.executeQuery();
			
			
			while(rs.next())
			{
				qtde = rs.getString("QTDE");
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return new Integer(qtde).intValue();
	}

	public List<EmprestimoEquipamentoVO> consultaEmprestimosByEquipamento(
			EquipamentoVO equipamento, CidadeVO cidade, String status,  Connection conn) throws SQLException {
		
		List<EmprestimoEquipamentoVO> list = new ArrayList<EmprestimoEquipamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		// todos
		String sql = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO";
		if(status.equalsIgnoreCase(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[1]))
			sql += " WHERE DATA_DEVOLUCAO IS NULL ORDER BY DATA_EMPRESTIMO";
		else if(status.equalsIgnoreCase(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[2]))
			sql += " WHERE DATA_DEVOLUCAO IS NOT NULL ORDER BY DATA_EMPRESTIMO"; 
		
		try{
			
			if(equipamento.getId() != null && cidade.getId() == null)
			{
				sql = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO E WHERE E.ID_EQUIPAMENTO = ? ";
				if(status.equalsIgnoreCase(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[1]))
					sql += " AND E.DATA_DEVOLUCAO IS NULL";
				else if(status.equalsIgnoreCase(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[2]))
					sql += " AND E.DATA_DEVOLUCAO IS NOT NULL"; 
				sql += " ORDER BY E.DATA_EMPRESTIMO";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,equipamento.getId());
			}
			else if(equipamento.getId() != null && cidade.getId() != null)
			{
				sql = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO E INNER JOIN APOLICE A ON E.ID_APOLICE = A.ID_APOLICE WHERE E.ID_EQUIPAMENTO = ? AND A.ID_CIDADE = ? ";
				if(status.equalsIgnoreCase(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[1]))
					sql += " AND E.DATA_DEVOLUCAO IS NULL";
				else if(status.equalsIgnoreCase(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[2]))
					sql += " AND E.DATA_DEVOLUCAO IS NOT NULL"; 		
				sql += " ORDER BY E.DATA_EMPRESTIMO";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,equipamento.getId());
				stmt.setInt(2,cidade.getId());
			}
			else if(equipamento.getId() == null && cidade.getId() != null)
			{
				sql = "SELECT * FROM EMPRESTIMO_EQUIPAMENTO E INNER JOIN APOLICE A ON E.ID_APOLICE = A.ID_APOLICE WHERE A.ID_CIDADE = ?";
				
				if(status.equalsIgnoreCase(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[1]))
					sql += " AND E.DATA_DEVOLUCAO IS NULL";
				else if(status.equalsIgnoreCase(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[2]))
					sql += " AND E.DATA_DEVOLUCAO IS NOT NULL";
				
				sql += " ORDER BY E.DATA_EMPRESTIMO";
				
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,cidade.getId());
			}else
			{
				stmt = conn.prepareStatement(sql);
			}
				
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