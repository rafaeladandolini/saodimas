package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ParcelaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.model.dao.ParcelaDAO;

public class ParcelaDAOImpl implements ParcelaDAO{

	public void delete(ParcelaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PARCELA WHERE ID_PARCELA = ? ";
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

	public List<ParcelaVO> findAll(Connection conn) throws SQLException {
		
		List<ParcelaVO> list = new ArrayList<ParcelaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCELA";
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

	public void save(ParcelaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO PARCELA(DATA_VENCIMENTO, NUMERACAO, VALOR, ID_VENDA) VALUES (?,?,?,?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setDate(1,  entity.getDataVencimento() != null ? new java.sql.Date(entity.getDataVencimento().getTime()): null);
			stmt.setString(2, entity.getNumeracao());
			stmt.setDouble(3,  entity.getValor());
			stmt.setInt(4,  entity.getVenda().getId());
			
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ParcelaVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE PARCELA SET DATA_VENCIMENTO = ?, VALOR = ?" +
						 " WHERE ID_PARCEL = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setDate(1,  entity.getDataVencimento() != null ? new java.sql.Date(entity.getDataVencimento().getTime()): null);
			stmt.setDouble(2,  entity.getValor());
			stmt.setInt(3,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ParcelaVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ParcelaVO vo = new ParcelaVO();
		vo.setId(rs.getInt(ParcelaVO.ID_PARCELA));
		vo.setDataVencimento(rs.getDate(ParcelaVO.DATA_VENCIMENTO));
		vo.setNumeracao(rs.getString(ParcelaVO.NUMERACAO));
		vo.setValor(rs.getDouble(ParcelaVO.VALOR));
		
		//VendaDAO vendaDao = new VendaDAOImpl();
		//vo.setVenda(vendaDao.findById(rs.getInt(ParcelaVO.ID_VENDA), conn));
						
		return vo;
	}


	
	public ParcelaVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCELA WHERE ID_PARCELA = ?";
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

	
	
	@Override
	public List<ParcelaVO> getAllParcelasByVenda(VendaVO venda, Connection conn)
			throws SQLException {
	
		List<ParcelaVO> list = new ArrayList<ParcelaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCELA P WHERE P.ID_VENDA = ?  ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, venda.getId());
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

	@Override
	public void deleteAllParcelasByVenda(VendaVO venda, Connection conn) throws SQLException {
	
				
			ResultSet rs = null;
			PreparedStatement stmt = null;
			
			try{
				String sql = "DELETE FROM PARCELA WHERE ID_VENDA = ? ";
				
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
