package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.dao.ApoliceDAO;
import br.com.saodimas.model.dao.CidadeDAO;
import br.com.saodimas.model.dao.ClienteDAO;
import br.com.saodimas.model.dao.ConsultaConstants;

public class ClienteDAOImpl implements ClienteDAO{

	public List<ClienteVO> getAllClientesByStatus(String status, Connection conn) throws SQLException{
		List<ClienteVO> list = new ArrayList<ClienteVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CLIENTE WHERE STATUS = ? ORDER BY NOME ";
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


	public void delete(ClienteVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ? ";
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

	public List<ClienteVO> findAll(Connection conn) throws SQLException {
		
		List<ClienteVO> list = new ArrayList<ClienteVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CLIENTE ORDER BY NOME";
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

	public void save(ClienteVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		
		
		try {
			String sql = "INSERT INTO CLIENTE(" +
					" NOME," +
					" ENDERECO," +
					" BAIRRO," +
					" CEP, " +
					" CONTATO, " +
					" CONTATO_2, " +
					" CPF, " +
					" RG, " +
					" OBSERVACAO, " +
					" ID_CIDADE," +
					" DATA_NASCIMENTO," +
					" COMPLEMENTO_ENDERECO," +
					" EMAIL," +
					" NOME_PAI," +
					" NOME_MAE," +
					" PROFISSAO, ";
				
			if(entity.getApolice() != null)
				sql +=" ID_APOLICE,";
					
			sql+=" STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,";
			
			if(entity.getApolice() != null)
				sql += "?,";
			
			sql += "?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i =1;
			stmt.setString(i++,entity.getNome());
			stmt.setString(i++,entity.getEndereco());
			stmt.setString(i++,entity.getBairro());
			stmt.setString(i++,entity.getCep());
			stmt.setString(i++,entity.getContato());
			stmt.setString(i++,entity.getContato2());
			stmt.setString(i++,entity.getCpf());
			stmt.setString(i++,entity.getRg());
			stmt.setString(i++,entity.getObservacao());
			stmt.setInt(i++,entity.getCidade().getId());
			stmt.setDate(i++,entity.getDataNascimento() != null ? new java.sql.Date(entity.getDataNascimento().getTime()): null);
			stmt.setString(i++,entity.getComplementoEndereco());
			stmt.setString(i++,entity.getEmail());
			stmt.setString(i++,entity.getNomePai());
			stmt.setString(i++,entity.getNomeMae());
			stmt.setString(i++,entity.getProfissao());
			if(entity.getApolice() != null)
				stmt.setInt(i++,entity.getApolice().getId());
			stmt.setString(i++,entity.getStatus());
		
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(ClienteVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE CLIENTE SET " +
					" NOME = ?," +
					" ENDERECO = ?," +
					" BAIRRO = ?," +
					" CEP = ?, " +
					" CONTATO = ?, " +
					" CONTATO_2 = ?, " +
					" CPF = ?, " +
					" RG = ?, " +
					" OBSERVACAO = ?, " +
					" ID_CIDADE = ?," +
					" DATA_NASCIMENTO = ?," +
					" COMPLEMENTO_ENDERECO = ?," +
					" EMAIL = ?," +
					" NOME_PAI = ?," +
					" NOME_MAE = ?," +
					" PROFISSAO = ?," +
					" STATUS = ?";
			
			if(entity.getApolice() != null)
				sql += ", ID_APOLICE = ?";
			
			sql += " WHERE ID_CLIENTE = ? ";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i =1;
			stmt.setString(i++,entity.getNome());
			stmt.setString(i++,entity.getEndereco());
			stmt.setString(i++,entity.getBairro());
			stmt.setString(i++,entity.getCep());
			stmt.setString(i++,entity.getContato());
			stmt.setString(i++,entity.getContato2());
			stmt.setString(i++,entity.getCpf());
			stmt.setString(i++,entity.getRg());
			stmt.setString(i++,entity.getObservacao());
			stmt.setInt(i++,entity.getCidade().getId());
			stmt.setDate(i++,entity.getDataNascimento() != null ? new java.sql.Date(entity.getDataNascimento().getTime()): null);
			stmt.setString(i++,entity.getComplementoEndereco());
			stmt.setString(i++,entity.getEmail());
			stmt.setString(i++,entity.getNomePai());
			stmt.setString(i++,entity.getNomeMae());
			stmt.setString(i++,entity.getProfissao());
			stmt.setString(i++,entity.getStatus());
			if(entity.getApolice() != null)
				stmt.setInt(i++,entity.getApolice().getId());	
			
			stmt.setInt(i++,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ClienteVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ClienteVO vo = new ClienteVO();
		vo.setBairro(rs.getString(ClienteVO.BAIRRO));
		vo.setCep(rs.getString(ClienteVO.CEP));
	
		CidadeDAO cidadeDAO = new CidadeDAOImpl();
		int id_cidade = rs.getInt(ClienteVO.ID_CIDADE);
		vo.setCidade(cidadeDAO.findById(id_cidade, conn));
	
		vo.setContato(rs.getString(ClienteVO.CONTATO));
		vo.setContato2(rs.getString(ClienteVO.CONTATO_2));
		vo.setEndereco(rs.getString(ClienteVO.ENDERECO));
		vo.setId(rs.getInt(ClienteVO.ID_CLIENTE));
		vo.setNome(rs.getString(ClienteVO.NOME));
		vo.setObservacao(rs.getString(ClienteVO.OBSERVACAO));
		vo.setStatus(rs.getString(ClienteVO.STATUS));
		vo.setCpf(rs.getString(ClienteVO.CPF));
		vo.setRg(rs.getString(ClienteVO.RG));
		vo.setDataNascimento(rs.getDate(ClienteVO.DATA_NASCIMENTO));
		vo.setComplementoEndereco(rs.getString(ClienteVO.COMPLEMENTO_ENDERECO));
		vo.setEmail(rs.getString(ClienteVO.EMAIL));
		vo.setNomePai(rs.getString(ClienteVO.NOME_PAI));
		vo.setNomeMae(rs.getString(ClienteVO.NOME_MAE));
		vo.setProfissao(rs.getString(ClienteVO.PROFISSAO));
		
		int id_apolice = rs.getInt(ClienteVO.ID_APOLICE); 
		if(id_apolice > 0)
		{
			ApoliceDAO dao = new ApoliceDAOImpl();
			vo.setApolice(dao.carregarApoliceSimples(id_apolice, conn));
		}
		
		return vo;
	}


	public ClienteVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?";
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


	public void saveAll(Set<ClienteVO> list, Connection conn) throws SQLException {
		Iterator<ClienteVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
	}
	
	public List<ClienteVO> consultaClientes(String tipo, String[] parametros,
			String status, Connection conn) throws SQLException {
		
		String sql = " SELECT * FROM CLIENTE C";
		
		if(tipo.endsWith(ConsultaConstants.QUALQUER) && status.equals(ConsultaConstants.QUALQUER))
		{
			sql = " SELECT * FROM CLIENTE C ";
		}						
		else if(tipo.equals(ConsultaConstants.NOME))
		{
			sql = " SELECT * FROM CLIENTE C WHERE C.NOME LIKE '%" + parametros[0] +"%' ";

		}else if(tipo.equals(ConsultaConstants.ENDERECO))
		{
			sql = " SELECT * FROM CLIENTE C WHERE ENDERECO LIKE '%"+ parametros[0] + "%'";
		}
		else if(tipo.equals(ConsultaConstants.OBSERVACAO))
		{
			sql = " SELECT * FROM CLIENTE C WHERE OBSERVACAO LIKE '%"+ parametros[0] + "%'";
		}
		else if(tipo.equals(ConsultaConstants.DOCUMENTO))
		{
			sql = "  SELECT * FROM CLIENTE C WHERE C.CPF = "+ parametros[0];
		}else if(tipo.equals(ConsultaConstants.CIDADE))
		{
			sql = "  SELECT * FROM CLIENTE C WHERE C.ID_CIDADE = "+ parametros[0];
		}	
		
		
		if(!status.equals(ConsultaConstants.QUALQUER))
		{
			if(sql.contains(" WHERE "))
				sql += " AND C.STATUS = '" + status + "'";
			else
				sql = " SELECT * FROM CLIENTE C WHERE C.STATUS = '" + status + "'";
		}
		
		sql += " ORDER BY C.NOME";
		
		List<ClienteVO> list = new ArrayList<ClienteVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
										
				list.add(createVOFromResultSet(rs, conn));
				
			}

		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		}
	
		return list;
	}

		
}