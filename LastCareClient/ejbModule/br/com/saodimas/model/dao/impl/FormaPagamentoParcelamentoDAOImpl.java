package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.FormaPagamentoParcelamentoVO;
import br.com.saodimas.model.dao.FormaPagamentoDAO;
import br.com.saodimas.model.dao.FormaPagamentoParcelamentoDAO;
import br.com.saodimas.model.dao.ParcelamentoDAO;



public class FormaPagamentoParcelamentoDAOImpl implements FormaPagamentoParcelamentoDAO{


	public void delete(FormaPagamentoParcelamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM FORMA_PAGAMENTO_PARCELAMENTO WHERE ID_FORMA_PAGAMENTO_PARCELAMENTO = ? ";
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

	public List<FormaPagamentoParcelamentoVO> findAll(Connection conn) throws SQLException {
		
		List<FormaPagamentoParcelamentoVO> list = new ArrayList<FormaPagamentoParcelamentoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FORMA_PAGAMENTO_PARCELAMENTO FP, PARCELAMENTO P WHERE FP.ID_PARCELAMENTO = P.ID_PARCELAMENTO ORDER BY FP.DESCRICAO";
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

	public void save(FormaPagamentoParcelamentoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO FORMA_PAGAMENTO_PARCELAMENTO(" +
					"ID_FORMA_PAGAMENTO, " +
					"ID_PARCELAMENTO) VALUES (?,?)";
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,entity.getFormaPagamento().getId());
			stmt.setInt(2,entity.getParcelamento().getId());
		

			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(FormaPagamentoParcelamentoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE FORMA_PAGAMENTO_PARCELAMENTO SET " +
			"ID_FORMA_PAGAMENTO = ?, " +
			"ID_PARCELAMENTO = ? WHERE ID_FORMA_PAGAMENTO_PARCELAMENTO = ?";
			
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,entity.getFormaPagamento().getId());
			stmt.setInt(2,entity.getParcelamento().getId());
			stmt.setInt(3,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	
	
	private FormaPagamentoParcelamentoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		FormaPagamentoParcelamentoVO vo = new FormaPagamentoParcelamentoVO();
		
		int id_fp = rs.getInt(FormaPagamentoParcelamentoVO.ID_FORMA_PAGAMENTO);
		if(id_fp > 0)
		{
			FormaPagamentoDAO formaPDao = new FormaPagamentoDAOImpl();
			vo.setFormaPagamento(formaPDao.findById(id_fp, conn));
		}
		
		int id_par = rs.getInt(FormaPagamentoParcelamentoVO.ID_PARCELAMENTO);
		if(id_par > 0)
		{
			ParcelamentoDAO parDAO = new ParcelamentoDAOImpl();
			vo.setParcelamento(parDAO.findById(id_par, conn));
		}		

		
		vo.setId(rs.getInt(FormaPagamentoParcelamentoVO.ID_FORMA_PAGAMENTO_PARCELAMENTO));
	
		
		return vo;
	}


	public FormaPagamentoParcelamentoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FORMA_PAGAMENTO_PARCELAMENTO WHERE ID_FORMA_PAGAMENTO_PARCELAMENTO = ?";
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
	
	public void saveAll(Set<FormaPagamentoParcelamentoVO> list, Connection conn) throws SQLException {
		Iterator<FormaPagamentoParcelamentoVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
	}



	
	
}