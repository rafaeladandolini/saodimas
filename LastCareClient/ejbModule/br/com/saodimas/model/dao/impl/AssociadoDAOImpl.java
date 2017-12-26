package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.dao.AssociadoDAO;
import br.com.saodimas.model.dao.ObitoDAO;
import br.com.saodimas.model.dao.RelacaoDAO;

public class AssociadoDAOImpl implements AssociadoDAO{

	public void delete(AssociadoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM ASSOCIADO WHERE ID_ASSOCIADO = ? ";
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

	public List<AssociadoVO> findAll(Connection conn) throws SQLException {
		
		List<AssociadoVO> list = new ArrayList<AssociadoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM ASSOCIADO";
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

	public void save(AssociadoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO ASSOCIADO" +
					"(NOME, " +
					" CPF, " +
					" DATA_NASCIMENTO, " +
					" SEXO, " +
					" STATUS, " +
					" ID_RELACAO, ID_APOLICE, RG, DATA_ADESAO)" +
					" VALUES (?,?,?,?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,entity.getNome());
			stmt.setString(2,entity.getCPF());
			stmt.setDate(3, entity.getDataNascimento() != null ? new java.sql.Date(entity.getDataNascimento().getTime()): null);
			stmt.setString(4,entity.getSexo()+"");
			stmt.setString(5,entity.getStatus());
			stmt.setInt(6,entity.getRelacao() != null ? entity.getRelacao().getId(): 0);
			stmt.setInt(7, entity.getApolice() != null ? entity.getApolice()
					.getId() : 0);
			stmt.setString(8, entity.getRg());
			stmt.setDate(9, entity.getDataAdesao() != null ? new java.sql.Date(entity.getDataAdesao().getTime()): null);
			
			stmt.executeUpdate();
			conn.commit();
			
			entity.setId(this.getIdInserido(conn));
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(AssociadoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE ASSOCIADO SET NOME = ?," +
						" CPF = ?, " +
						" DATA_NASCIMENTO = ?, " +
						" SEXO = ?, " +
						" RG = ?, " +
						" STATUS = ?, " +
						" DATA_ADESAO = ?";
			
			if(entity.getRelacao() != null)
				 sql += ", ID_RELACAO = ? ";
			
			   	 sql += " WHERE ID_ASSOCIADO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 1;
			stmt.setString(i++,entity.getNome());
			stmt.setString(i++,entity.getCPF());
			stmt.setDate(i++, entity.getDataNascimento() != null ? new java.sql.Date(entity.getDataNascimento().getTime()) : null);
			stmt.setString(i++,entity.getSexo()+"");
			stmt.setString(i++, entity.getRg());
			stmt.setString(i++,entity.getStatus());
			stmt.setDate(i++, entity.getDataAdesao() != null ? new java.sql.Date(entity.getDataAdesao().getTime()) : null);
			if(entity.getRelacao() != null)
				stmt.setInt(i++,entity.getRelacao() != null ? entity.getRelacao().getId(): null);
			stmt.setInt(i++,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	
public void atualizaDadosImpressaoCartao(AssociadoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE ASSOCIADO SET DATA_ULTIMA_IMPRESSAO = ?," +
						" QTDE_CARTAO = ? WHERE ID_ASSOCIADO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 1;
			
			stmt.setDate(i++, entity.getDataUltimaImpressao() != null ? new java.sql.Date(entity.getDataUltimaImpressao().getTime()) : null);
			stmt.setInt(i++,entity.getQtdeCartao());
			stmt.setInt(i++,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/*
	 * public void updateIdApoliceTitular(AssociadoVO entity, Connection conn)
	 * throws SQLException {
	 * 
	 * PreparedStatement stmt = null;
	 * 
	 * try { String sql = "UPDATE ASSOCIADO SET ID_APOLICE = ?," +
	 * " DATA_ADESAO = ?, "; sql += " ID_RELACAO = ? "; sql +=
	 * " WHERE ID_ASSOCIADO = ?";
	 * 
	 * conn.setAutoCommit(false); stmt = conn.prepareStatement(sql);
	 * 
	 * int i = 1; stmt.setInt(i++,entity.getApolice().getId());
	 * stmt.setDate(i++, entity.getDataAdesao() != null ? new
	 * java.sql.Date(entity.getDataAdesao().getTime()) : null);
	 * stmt.setInt(i++,1); stmt.setInt(i++,entity.getId());
	 * 
	 * stmt.executeUpdate(); conn.commit(); } finally { if (stmt != null)
	 * stmt.close(); }
	 * 
	 * 
	 * }
	 */
	
	private AssociadoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		AssociadoVO vo = new AssociadoVO();
		vo.setCPF(rs.getString(AssociadoVO.CPF_STR));
		vo.setDataNascimento(rs.getDate(AssociadoVO.DATA_NASCIMENTO));
		vo.setDataAdesao(rs.getDate(AssociadoVO.DATA_ADESAO));
		vo.setId(rs.getInt(AssociadoVO.ID_ASSOCIADO));
		vo.setNome(rs.getString(AssociadoVO.NOME));
		
		RelacaoDAO dao = new RelacaoDAOImpl();
		int id_relacao = rs.getInt(AssociadoVO.ID_RELACAO); 
		if(id_relacao > 0)
			vo.setRelacao(dao.findById(id_relacao, conn));
		
		vo.setSexo(rs.getString(AssociadoVO.SEXO).charAt(0));
		vo.setStatus(rs.getString(AssociadoVO.STATUS));
		vo.setRg(rs.getString(AssociadoVO.RG));
		vo.setQtdeCartao(rs.getInt(AssociadoVO.QTDE_CARTAO));
		vo.setDataUltimaImpressao(rs.getDate(AssociadoVO.DATA_ULTIMA_IMPRESSAO));

		return vo;
	}
	

	public AssociadoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM ASSOCIADO WHERE ID_ASSOCIADO = ?";
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
	
	
	public List<AssociadoVO> getAllDependentesByApolice(ApoliceVO apolice, Connection conn) throws SQLException {
		
		List<AssociadoVO> list = new ArrayList<AssociadoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = " select * from associado a, obito o where a.id_apolice = ? and  (a.id_associado not in (select id_associado from obito where id_apolice = ? )) group by a.id_associado order by a.nome";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, apolice.getId());
			stmt.setInt(2, apolice.getId());
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				AssociadoVO vo = this.createVOFromResultSet(rs, conn);
				ObitoDAO dao = new ObitoDAOImpl(); 
				vo.setObito(dao.getObitoByDependente(vo, conn));
				list.add(vo);
			}
			
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;

	}

	public void deleteAllDependentesByApolice(ApoliceVO apolice, Connection conn)
			throws SQLException {
		
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM ASSOCIADO WHERE ID_APOLICE = ? ";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, apolice.getId());
			stmt.executeUpdate();
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
		}
		
	}

	public void saveTitular(AssociadoVO entity, Connection conn)
			throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO ASSOCIADO" + 
					"(NOME, " + " " +
					"CPF, " +
					" DATA_NASCIMENTO, " +
					" SEXO, " + 
					" STATUS, RG)" + 
					" VALUES (?,?,?,?,?,?)";

			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, entity.getNome());
			stmt.setString(2, entity.getCPF());
			stmt.setDate(3,
					entity.getDataNascimento() != null ? new java.sql.Date(
							entity.getDataNascimento().getTime()) : null);
			stmt.setString(4, entity.getSexo() + "");
			stmt.setString(5, entity.getStatus());
			stmt.setString(6, entity.getRg());
			
			
			stmt.executeUpdate();
			conn.commit();
			
			entity.setId(this.getIdInserido(conn));
			
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	private int getIdInserido(Connection conn) throws SQLException {

		ResultSet rs = null;
		PreparedStatement stmt = null;

		String sql = " SELECT @@IDENTITY AS ID FROM ASSOCIADO";

		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();

		int id = 0;
		while (rs.next())
			id = rs.getInt("ID");

		return id;

	}

	public void deleteTitularByApolice(ApoliceVO apolice, Connection conn)
			throws SQLException {
		
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM ASSOCIADO WHERE ID_ASSOCIADO = ? ";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, apolice.getTitular().getId());
			stmt.executeUpdate();
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
		}
		
	}

}
