package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoPlanoVO;
import br.com.saodimas.model.beans.ServicoPlanoVO;
import br.com.saodimas.model.dao.PlanoDAO;
import br.com.saodimas.model.dao.ProdutoPlanoDAO;
import br.com.saodimas.model.dao.ServicoPlanoDAO;

public class PlanoDAOImpl implements PlanoDAO{

	public List<PlanoVO> getAllPlanosByStatus(String status, Connection conn) throws SQLException{
		List<PlanoVO> list = new ArrayList<PlanoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PLANO WHERE STATUS = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, status);
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


	public void delete(PlanoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM PLANO WHERE ID_PLANO = ? ";
		
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, entity.getId());
			stmt.executeUpdate();
			
			ServicoPlanoDAO servicoPlanoDAO = new ServicoPlanoDAOImpl();
			servicoPlanoDAO.deleteAllServicoPlanoByPlano(entity, conn);
			
			ProdutoPlanoDAO produtoPlanoDAO = new ProdutoPlanoDAOImpl();
			produtoPlanoDAO.deleteAllProdutoPlanoByPlano(entity, conn);
						
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
		}
		
	}

	public List<PlanoVO> findAll(Connection conn) throws SQLException {
		
		List<PlanoVO> list = new ArrayList<PlanoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PLANO";
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

	public void save(PlanoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO PLANO" +
					" ( " +
					" DESCRICAO, " +
					" LIMITE_ASSOCIADO, " +
					" CARENCIA, " +
					" VALOR_MENSALIDADE, " +
					" EMPRESARIAL, " +
					" STATUS, " +
					" ASSOCIADO_EXTRA" +
					" ) " +
					" VALUES (?,?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getDescricao());
			stmt.setInt(2,entity.getLimiteAssociado());
			stmt.setInt(3,entity.getCarencia());
			stmt.setDouble(4,entity.getMensalidade());
			stmt.setBoolean(5,entity.isEmpresarial());
			stmt.setString(6,entity.getStatus());
			stmt.setInt(7, entity.getAssociado_extra());
			
			stmt.executeUpdate();
			
			ProdutoPlanoDAO produtoDao = new ProdutoPlanoDAOImpl();
			produtoDao.saveAll(entity.getProdutos(), entity, conn);
			
			ServicoPlanoDAO servicoDAO = new ServicoPlanoDAOImpl();
			servicoDAO.saveAll(entity.getServicos(), conn);
			
			conn.commit();
		
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(PlanoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE PLANO SET DESCRICAO = ?," +
						" LIMITE_ASSOCIADO = ?, " +
						" CARENCIA = ?, " +
						" VALOR_MENSALIDADE = ?, " +
						" EMPRESARIAL = ?, " +
						" STATUS = ?," +
						" ASSOCIADO_EXTRA = ? " +
						" WHERE ID_PLANO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getDescricao());
			stmt.setInt(2,entity.getLimiteAssociado());
			stmt.setInt(3,entity.getCarencia());
			stmt.setDouble(4,entity.getMensalidade());
			stmt.setBoolean(5,entity.isEmpresarial());
			stmt.setString(6,entity.getStatus());
			stmt.setInt(7, entity.getAssociado_extra());
			stmt.setInt(8,entity.getId());
			
						
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}
	
	private PlanoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		PlanoVO vo = new PlanoVO();
		vo.setCarencia(rs.getInt(PlanoVO.CARENCIA));
		vo.setDescricao(rs.getString(PlanoVO.DESCRICAO));
		vo.setEmpresarial(rs.getBoolean(PlanoVO.EMPRESARIAL));
		vo.setId(rs.getInt(PlanoVO.ID_PLANO));
		vo.setLimiteAssociado(rs.getInt(PlanoVO.LIMITE_ASSOCIADO));
		vo.setMensalidade(rs.getDouble(PlanoVO.VALOR_MENSALIDADE));
		vo.setStatus(rs.getString(PlanoVO.STATUS));
		vo.setAssociado_extra(rs.getInt(PlanoVO.ASSOCIADO_EXTRA));
		
		ProdutoPlanoDAO produtoPlanoDAO = new  ProdutoPlanoDAOImpl();
		List<ProdutoPlanoVO> listP = produtoPlanoDAO.getAllProdutoPlanoByPlano(vo, conn);
		vo.setProdutos(new HashSet<ProdutoPlanoVO>(listP));
		
		ServicoPlanoDAO servicoPlanoDAO = new  ServicoPlanoDAOImpl();
		List<ServicoPlanoVO> listS = servicoPlanoDAO.getAllServicoPlanoByPlano(vo, conn);
		vo.setServicos(new HashSet<ServicoPlanoVO>(listS));
				
		return vo;
	}


	public PlanoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM PLANO WHERE ID_PLANO = ?";
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

		
}
