package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.model.beans.ParceiroVO;
import br.com.saodimas.model.dao.ApoliceDAO;
import br.com.saodimas.model.dao.ColaboradorDAO;
import br.com.saodimas.model.dao.FaturaDAO;
import br.com.saodimas.model.dao.ParceiroDAO;
import br.com.saodimas.view.util.ListasComuns;

public class FaturaDAOImpl implements FaturaDAO{

	public List<FaturaVO> getAllFaturasByApolice(ApoliceVO apolice,
			Connection conn) throws SQLException {
		List<FaturaVO> list = new ArrayList<FaturaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FATURA WHERE ID_APOLICE = ? ORDER BY ABS(NUMERO_FATURA)";
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,apolice.getId());
			rs = stmt.executeQuery();
		
			
			while(rs.next())
			{
				list.add(this.createVOFromResultSet(rs, conn));
			}
			
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
		}
		
		return list;
	}

	public List<FaturaVO> getAllFaturasByDateColaboradorParceiro(
			ColaboradorVO colaboradorVO, ParceiroVO parceiro, Boolean parceiroTodosNenhum, Date dataInicio, Date dataFim, Date dataInicioBaixa, Date dataFimBaixa, Connection conn)
			throws SQLException {
		
		List<FaturaVO> list = new ArrayList<FaturaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		boolean isWhereAnd = false;
		
		try{
			String sql = "SELECT * FROM FATURA F INNER JOIN APOLICE A ON F.ID_APOLICE = A.ID_APOLICE " +
					                           " INNER JOIN ASSOCIADO ASS ON A.ID_ASSOCIADO = ASS.ID_ASSOCIADO";
			
			if(dataInicio != null || dataInicioBaixa != null || colaboradorVO != null || parceiro != null)
				sql += " WHERE ";
							
			
			if(dataInicio != null && dataFim != null){
				sql+=	"  F.DATA_PAGAMENTO >= ? AND F.DATA_PAGAMENTO <= ?";
				isWhereAnd = true;
			}
			
			
			if(dataInicioBaixa != null && dataFimBaixa != null ){
				if(isWhereAnd)
					sql+= " AND ";
				sql+=	"  F.DATA_BAIXA >= ? AND F.DATA_BAIXA <= ?";
				isWhereAnd = true;
			}
			
					
			if(colaboradorVO != null){
				if(isWhereAnd)
					sql += " AND ";
				sql += " F.ID_COLABORADOR = ?";
				isWhereAnd = true;
			}
			
			//  parceiro em especifico
			if(parceiro != null){
				if(isWhereAnd)
					sql += " AND ";
				sql += " F.ID_PARCEIRO = ?";
			}
			
			//                                                                 sem parceiro
			if(parceiro == null && parceiroTodosNenhum != null && parceiroTodosNenhum == false){
				if(isWhereAnd)
					sql += " AND ";
				sql += " F.ID_PARCEIRO IS NULL";
			}
			
			//                                                             s� com parceiro
			if(parceiro == null && parceiroTodosNenhum != null && parceiroTodosNenhum == true){
				if(isWhereAnd)
					sql += " AND ";
				sql += " F.ID_PARCEIRO IS NOT NULL";
			}
			
			sql += " ORDER BY DATA_PAGAMENTO, ABS(NUMERO_CONTRATO), ABS(NUMERO_FATURA)";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			int i = 1;
			if(dataInicio != null){
				stmt.setDate(i++,new java.sql.Date(dataInicio.getTime()));
				stmt.setDate(i++,new java.sql.Date(dataFim.getTime()));
			}
			
			if(dataInicioBaixa != null){
				stmt.setDate(i++,new java.sql.Date(dataInicioBaixa.getTime()));
				stmt.setDate(i++,new java.sql.Date(dataFimBaixa.getTime()));
			}
			
			if(colaboradorVO != null)
				stmt.setInt(i++, colaboradorVO.getId());
			if(parceiro != null)
				stmt.setInt(i++, parceiro.getId());	
			
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				list.add(this.createVOFromResultSet(rs, conn));
			}
			
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;
		
	}

	public void delete(FaturaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM FATURA WHERE ID_FATURA = ? ";
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

	public List<FaturaVO> findAll(Connection conn) throws SQLException {
		
		List<FaturaVO> list = new ArrayList<FaturaVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FATURA ";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				list.add(this.createVOFromResultSet(rs, conn));
			}
			
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return list;

	}

	public void save(FaturaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO FATURA" +
					"( " +
					" ID_APOLICE, " +
					" NUMERO_FATURA, " +
					" DATA_EMISSAO, " +
					" DATA_VENCIMENTO, " +
					" DATA_PAGAMENTO, " +
					" DATA_BAIXA, " +
					" VALOR, " +
					" VALOR_MULTA, " +
					" VALOR_DESCONTO," +
					" VALOR_PARCEIRO, " +
					" STATUS," +
					" PAGAMENTO_SEM_CARNE " +
					")" +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, entity.getApolice().getId());
			stmt.setInt(2, entity.getNumeroFatura());
			stmt.setDate(3, entity.getDataEmissao() != null ? new java.sql.Date(entity.getDataEmissao().getTime()): null);
			stmt.setDate(4, entity.getDataVencimento() != null ? new java.sql.Date(entity.getDataVencimento().getTime()): null);
			stmt.setDate(5, entity.getDataPagamento() != null ? new java.sql.Date(entity.getDataPagamento().getTime()): null);
			stmt.setDate(6, entity.getDataBaixa() != null ? new java.sql.Date(entity.getDataBaixa().getTime()): null);
			stmt.setDouble(7, entity.getValor());
			stmt.setDouble(8, entity.getValorMulta());
			stmt.setDouble(9, entity.getValorDesconto());
			stmt.setDouble(10, entity.getValorParceiro());
			stmt.setString(11, entity.getStatus());
			stmt.setString(12,entity.getIsPagamentoSemCarne());
			
			stmt.executeUpdate();
			conn.commit();
			
			entity.setId(this.getIdInserido(conn));
			
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
	}

	public void update(FaturaVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE FATURA SET ID_APOLICE = ?," +
						" NUMERO_FATURA = ?, " +
						" DATA_EMISSAO = ?, " +
						" DATA_VENCIMENTO = ?, " +
						" DATA_PAGAMENTO = ?, " +
						" DATA_BAIXA = ?, " +
						" VALOR = ?, " +
						" VALOR_MULTA = ?, " +
						" VALOR_DESCONTO = ?, " +
						" VALOR_PARCEIRO = ?, " +
						" STATUS = ?," +
						" PAGAMENTO_SEM_CARNE = ? ";
			
			if(entity.getColaborador() != null)
				sql += ", ID_COLABORADOR = ? ";
			
				sql += ", ID_PARCEIRO = ? ";
				sql += " WHERE ID_FATURA = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 1;
			stmt.setInt(i++,entity.getApolice().getId());
			stmt.setInt(i++,entity.getNumeroFatura());
			stmt.setDate(i++, entity.getDataEmissao() != null ? new java.sql.Date(entity.getDataEmissao().getTime()): null);
			stmt.setDate(i++, entity.getDataVencimento() != null ? new java.sql.Date(entity.getDataVencimento().getTime()): null);
			stmt.setDate(i++, entity.getDataPagamento() != null ? new java.sql.Date(entity.getDataPagamento().getTime()): null);
			stmt.setDate(i++, entity.getDataBaixa() != null ? new java.sql.Date(entity.getDataBaixa().getTime()): null);
			stmt.setDouble(i++,entity.getValor());
			stmt.setDouble(i++,entity.getValorMulta());
			stmt.setDouble(i++,entity.getValorDesconto());
			stmt.setDouble(i++,entity.getValorParceiro());
			stmt.setString(i++,entity.getStatus());
			stmt.setString(i++,entity.getIsPagamentoSemCarne());
			
			if(entity.getColaborador() != null)
				stmt.setInt(i++,entity.getColaborador() != null ? entity.getColaborador().getId() : null);
			
			if(entity.getParceiro() != null)
				stmt.setInt(i++,entity.getParceiro().getId());
			else
				stmt.setObject(i++, null);
			
			stmt.setInt(i++,entity.getId());
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		
	}
	
	public void destacadaFatura(FaturaVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE FATURA SET PAGAMENTO_SEM_CARNE = ? WHERE ID_FATURA = ?" ;
				
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 1;
			stmt.setString(i++,entity.getIsPagamentoSemCarne());
			stmt.setInt(i++,entity.getId());
				
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
			
		}

		
	}
	
	private FaturaVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		FaturaVO vo = new FaturaVO();
		vo.setValorParceiro(rs.getDouble(FaturaVO.VALOR_PARCEIRO));
		vo.setDataEmissao(rs.getDate(FaturaVO.DATA_EMISSAO));
		vo.setDataPagamento(rs.getDate(FaturaVO.DATA_PAGAMENTO));
		vo.setDataVencimento(rs.getDate(FaturaVO.DATA_VENCIMENTO));
		vo.setDataBaixa(rs.getDate(FaturaVO.DATA_BAIXA));
		vo.setId(rs.getInt(FaturaVO.ID_FATURA));
		vo.setNumeroFatura(rs.getInt(FaturaVO.NUMERO_FATURA));
		vo.setStatus(rs.getString(FaturaVO.STATUS));
		vo.setValor(rs.getDouble(FaturaVO.VALOR));
		vo.setValorDesconto(rs.getDouble(FaturaVO.VALOR_DESCONTO));
		vo.setValorMulta(rs.getDouble(FaturaVO.VALOR_MULTA));
		vo.setIsPagamentoSemCarne(rs.getString(FaturaVO.PAGAMENTO_SEM_CARNE));
		
		int id_col = rs.getInt(FaturaVO.ID_COLABORADOR);
		
		ColaboradorDAO colaboradorDAO = new ColaboradorDAOImpl();
		if(id_col >0)
			vo.setColaborador(colaboradorDAO.findById(id_col, conn));
		
		int idParceiro = rs.getInt(FaturaVO.ID_PARCEIRO);
		
		ParceiroDAO parceiroDAO = new ParceiroDAOImpl();
		if(idParceiro >0)
			vo.setParceiro(parceiroDAO.findById(id_col, conn));
		
		int id_par = rs.getInt(FaturaVO.ID_PARCEIRO);
		
		ParceiroDAO parDAO = new ParceiroDAOImpl();
		if(id_par >0)
			vo.setParceiro(parDAO.findById(id_par, conn));
		
		ApoliceDAO apoliceDAO = new ApoliceDAOImpl();
		vo.setApolice(apoliceDAO.carregarApoliceSimples(rs.getInt(FaturaVO.ID_APOLICE), conn));
				
		return vo;
	}


	public FaturaVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM FATURA WHERE ID_FATURA = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,id);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				return this.createVOFromResultSet(rs, conn);
			}
			
			conn.commit();
		}finally{
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		
		return null;
	}
	
	public void saveAll(Set<FaturaVO> list, Connection conn) throws SQLException {
		Iterator<FaturaVO> it = list.iterator();
		while(it.hasNext())
			this.save(it.next(), conn);
	}

	public void deleteAllFaturasByApolice(ApoliceVO apolice, Connection conn)
			throws SQLException {

		PreparedStatement stmt = null;

		try {
			String sql = "DELETE FROM FATURA WHERE ID_APOLICE = ? ";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, apolice.getId());
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	private int getIdInserido(Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = " SELECT @@IDENTITY AS ID FROM FATURA";
		
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		int id = 0;
		while(rs.next())
			id = rs.getInt("ID");
		
		return id;
			
		
	}

	public void estornarBaixaFatura(FaturaVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "UPDATE FATURA SET " +
					" DATA_PAGAMENTO = ?, " +
					" DATA_BAIXA = ?, " +
					" VALOR_MULTA = ?, " +
					" VALOR_DESCONTO = ?, " +
					" VALOR_PARCEIRO = ?, " +
					" STATUS = ?," +
					" PAGAMENTO_SEM_CARNE = ? ";
		
		if(entity.getColaborador() != null)
			sql += ", ID_COLABORADOR = ? ";
		
		if(entity.getParceiro() != null)
			sql += ", ID_PARCEIRO = ? ";
		
			sql += " WHERE ID_FATURA = ?";
				
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(sql);
			
			int i = 1;
			stmt.setDate(i++, null);
			stmt.setDate(i++, null);
			stmt.setDouble(i++, 0);
			stmt.setDouble(i++,0);
			stmt.setDouble(i++,0);
			stmt.setString(i++, ListasComuns.STATUS_FATURA[0]);
			stmt.setString(i++,null);
			
			if(entity.getColaborador() != null)
				stmt.setObject(i++,null);
			
			if(entity.getParceiro() != null)
				stmt.setObject(i++, null);
			
			stmt.setInt(i++,entity.getId());
				
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
			
		}
		
	}

			
	
}
