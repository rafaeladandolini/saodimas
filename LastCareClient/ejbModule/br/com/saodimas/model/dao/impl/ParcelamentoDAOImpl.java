package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.FormaPagamentoVO;
import br.com.saodimas.model.beans.ParcelamentoVO;

import br.com.saodimas.model.dao.ParcelamentoDAO;

public class ParcelamentoDAOImpl implements ParcelamentoDAO{

	public void delete(ParcelamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PARCELAMENTO WHERE ID_PARCELAMENTO = ? ";
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

	public List<ParcelamentoVO> findAll(Connection conn) throws SQLException {
		
		List<ParcelamentoVO> list = new ArrayList<ParcelamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCELAMENTO";
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

	public void save(ParcelamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO PARCELAMENTO(DESCRICAO, QUANTIDADE) VALUES (?)";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getDescricao());
			stmt.setInt(2, entity.getQuantidade());
			
						
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ParcelamentoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE PARCELAMENTO SET DESCRICAO = ?, QUANTIDADE = ?" +
						 " WHERE ID_PARCELAMENTO = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, entity.getDescricao());
			stmt.setInt(2,  entity.getQuantidade());
			stmt.setInt(3,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ParcelamentoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ParcelamentoVO vo = new ParcelamentoVO();
		vo.setId(rs.getInt(ParcelamentoVO.ID_PARCELAMENTO));
		vo.setDescricao(rs.getString(ParcelamentoVO.DESCRICAO));
		vo.setQuantidade(rs.getInt(ParcelamentoVO.QUANTIDADE));
						
		return vo;
	}


	
	public ParcelamentoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCELAMENTO WHERE ID_PARCELAMENTO = ?";
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
	public List<ParcelamentoVO> getAllParcelamentoByFormaPagamento(FormaPagamentoVO formaPag, Connection conn)
			throws SQLException {
	
		List<ParcelamentoVO> list = new ArrayList<ParcelamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PARCELAMENTO P INNER JOIN FORMA_PAGAMENTO_PARCELAMENTO FPP ON P.ID_PARCELAMENTO = FPP.ID_PARCELAMENTO"
					+ " WHERE FPP.ID_FORMA_PAGAMENTO = ?  ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, formaPag.getId());
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
