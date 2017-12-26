package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.saodimas.model.beans.ChequeVO;
import br.com.saodimas.model.dao.ChequeDAO;
import br.com.saodimas.model.dao.ClienteDAO;
import br.com.saodimas.model.dao.ContaDAO;

public class ChequeDAOImpl implements ChequeDAO{

	public void delete(ChequeVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM CHEQUE WHERE ID_CHEQUE = ? ";
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

	public List<ChequeVO> findAll(Connection conn) throws SQLException {
		
		List<ChequeVO> list = new ArrayList<ChequeVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CHEQUE ORDER BY DATA_VENCIMENTO";
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

	public void save(ChequeVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO CHEQUE(DESCRICAO, NUMERO, ID_CLIENTE, DATA_CADASTRO, DATA_COMPENSACAO, DATA_EMISSAO, DATA_VENCIMENTO, IS_EMITIDO, VALOR,";
			
		//	if(entity.getFornecedor() != null)
		//		sql += "   ID_FORNECEDOR,";
				
			sql	+= " ID_CONTA) VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 0;
			stmt.setString(++i,entity.getDescricao());
			stmt.setString(++i, entity.getNumero());
			stmt.setInt(++i, entity.getCliente().getId());
			stmt.setDate(++i, entity.getDataCadastro() != null ? new java.sql.Date(entity.getDataCadastro().getTime()) : null);
			stmt.setDate(++i, entity.getDataCompensacao() != null ? new java.sql.Date(entity.getDataCompensacao().getTime()) : null);
			stmt.setDate(++i, entity.getDataEmissao() != null ? new java.sql.Date(entity.getDataEmissao().getTime()) : null);
			stmt.setDate(++i, entity.getDataVencimento() != null ? new java.sql.Date(entity.getDataVencimento().getTime()): null);
			//if(entity.getFornecedor() != null)
			//	stmt.setInt(++i, entity.getFornecedor() == null ? null : entity.getFornecedor().getId());
			stmt.setBoolean(++i, entity.isEmitido());
			stmt.setDouble(++i, entity.getValor());
			stmt.setInt(++i, entity.getConta().getId());
			
							
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ChequeVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE CHEQUE SET DESCRICAO = ?, NUMERO = ?, ID_CLIENTE = ?, DATA_CADASTRO = ?,"
					+ " DATA_COMPENSACAO = ?, DATA_EMISSAO = ?, DATA_VENCIMENTO = ?, ID_FORNECEDOR = ?, ID_CONTA " 
					+ " WHERE ID_CHEQUE = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 0;
			stmt.setString(++i,entity.getDescricao());
			stmt.setString(++i, entity.getNumero());
			stmt.setInt(++i, entity.getCliente().getId());
			stmt.setDate(++i, entity.getDataCadastro() != null ? new java.sql.Date(entity.getDataCadastro().getTime()) : null);
			stmt.setDate(++i, entity.getDataCompensacao() != null ? new java.sql.Date(entity.getDataCompensacao().getTime()) : null);
			stmt.setDate(++i, entity.getDataEmissao() != null ? new java.sql.Date(entity.getDataEmissao().getTime()) : null);
			stmt.setDate(++i, entity.getDataVencimento() != null ? new java.sql.Date(entity.getDataVencimento().getTime()): null);
			stmt.setInt(++i, entity.getFornecedor().getId());
			stmt.setInt(++i, entity.getConta().getId());
			stmt.setInt(++i, entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ChequeVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ChequeVO vo = new ChequeVO();
		vo.setId(rs.getInt(ChequeVO.ID_CHEQUE));
		
		int idC = rs.getInt(ChequeVO.ID_CLIENTE);
		ClienteDAO cDao = new ClienteDAOImpl();
		vo.setCliente(cDao.findById(idC, conn));
		
		int idConta = rs.getInt(ChequeVO.ID_CONTA);
		ContaDAO contaDAo = new ContaDAOImpl();
		vo.setConta(contaDAo.findById(idConta, conn));
				
		int idFor = rs.getInt(ChequeVO.ID_FORNECEDOR);
		FornecedorDAOImpl daoFor = new FornecedorDAOImpl();
		vo.setFornecedor(daoFor.findById(idFor, conn));
		
		vo.setDataCadastro(rs.getDate(ChequeVO.DATA_CADASTRO));
		vo.setDataCompensacao(rs.getDate(ChequeVO.DATA_COMPENSACAO));
		vo.setDataEmissao(rs.getDate(ChequeVO.DATA_EMISSAO));
		vo.setDataVencimento(rs.getDate(ChequeVO.DATA_VENCIMENTO));
		vo.setDescricao(rs.getString(ChequeVO.DESCRICAO));
		vo.setEmitido(rs.getBoolean(ChequeVO.IS_EMITIDO));
		vo.setNumero(rs.getString(ChequeVO.NUMERO));
		vo.setValor(rs.getDouble(ChequeVO.VALOR));
				
		return vo;
	}


	
	public ChequeVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CHEQUE WHERE ID_CHEQUE = ?";
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
	public ChequeVO baixarCheque(ChequeVO entity, Date dataBaixa, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE CHEQUE SET DATA_COMPENSACAO = ? WHERE ID_CHEQUE = ?";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 0;
			
			stmt.setDate(++i, dataBaixa != null ? new java.sql.Date(dataBaixa.getTime()) : null);
			stmt.setInt(++i, entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
			
			entity.setDataCompensacao(dataBaixa);
			return entity;
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
}
