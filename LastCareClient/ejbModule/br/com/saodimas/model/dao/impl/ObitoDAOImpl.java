package br.com.saodimas.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;
import br.com.saodimas.model.beans.TipoAtendimentoVO;
import br.com.saodimas.model.dao.ApoliceDAO;
import br.com.saodimas.model.dao.AssociadoDAO;
import br.com.saodimas.model.dao.CemiterioDAO;
import br.com.saodimas.model.dao.CidadeDAO;
import br.com.saodimas.model.dao.GrupoTrabalhoDAO;
import br.com.saodimas.model.dao.ObitoDAO;
import br.com.saodimas.model.dao.ProdutoObitoDAO;
import br.com.saodimas.model.dao.ServicoObitoDAO;
import br.com.saodimas.model.dao.TipoAtendimentoDAO;
import br.com.saodimas.view.components.panel.obito.ConsultaObitoPanel;

public class ObitoDAOImpl implements ObitoDAO{

	public void delete(ObitoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		
		try{
			ServicoObitoDAO servicoPlanoDAO = new ServicoObitoDAOImpl();
			servicoPlanoDAO.deleteAllServicoObitoByObito(entity, conn);
			
			ProdutoObitoDAO produtoObitoDAO = new ProdutoObitoDAOImpl();
			produtoObitoDAO.deleteAllProdutoObitoByObito(entity, conn);
			
			String sql = "DELETE FROM OBITO WHERE ID_OBITO = ? ";
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

	public List<ObitoVO> findAll(Connection conn) throws SQLException {
		
		List<ObitoVO> list = new ArrayList<ObitoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM OBITO ORDER BY DATA_OBITO";
			
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

	public void save(ObitoVO entity, Connection conn) throws SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "INSERT INTO OBITO ("; 
			
			if(entity.getAssociado() != null) sql+=" ID_ASSOCIADO,";
			
			if(entity.getApolice() != null) sql+=" ID_APOLICE,";
				
			 sql += " DATA_OBITO," +
					" CAUSA," +
					" NUMERO_ATESTADO," +
					" DATA_EXPEDICAO," +
					" LOCAL_FALECIMENTO," +
					" ID_CIDADE_FALECIMENTO," +
					" ID_MOTORISTA_VIAGEM," +
					" LOCAL_VELORIO," +
					" ID_CIDADE_VELORIO," +
					" ID_MOTORISTA_CORTEJO," +
					" ID_CEMITERIO," +
					" HORA_SEPULTAMENTO," +
					" DATA_SEPULTAMENTO, " +
					" ID_TIPO_ATENDIMENTO, " +
					" NOME_FALECIDO " +
					")" +
					" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"; 
			
			 if(entity.getApolice() != null) sql+=",?";
			 if(entity.getAssociado() != null) sql+=",?";
					
					sql += " )";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 0;
			
			if(entity.getAssociado() != null) 
				stmt.setInt(++i,entity.getAssociado() != null ? entity.getAssociado().getId() : 0);
			if(entity.getApolice() != null) 
				stmt.setInt(++i,entity.getApolice().getId());
			
			stmt.setDate(++i, entity.getData() != null ? new Date(entity.getData().getTime()) : null);
			stmt.setString(++i,entity.getCausa());
			stmt.setString(++i,entity.getNumeroAtestado());
			stmt.setDate(++i, entity.getDataExpedicao() != null ? new Date(entity.getDataExpedicao().getTime()) : null);
			stmt.setString(++i,entity.getLocalFalecimento());
			stmt.setInt(++i,entity.getCidadeFalecimento() != null ? entity.getCidadeFalecimento().getId() : 0);
			stmt.setInt(++i,entity.getGrupoTrabalhoViagem() != null ? entity.getGrupoTrabalhoViagem().getId() : 0);
			stmt.setString(++i,entity.getLocalVelorio() );
			stmt.setInt(++i, entity.getCidadeVelorio() != null ? entity.getCidadeVelorio().getId() : 0);
			stmt.setInt(++i, entity.getGrupoTrabalhoCortejo() != null ? entity.getGrupoTrabalhoCortejo().getId() : 0);
			stmt.setInt(++i, entity.getCemiterio() != null ? entity.getCemiterio().getId() : 0 );
			stmt.setString(++i, entity.getHoraSepultamento().trim());
			stmt.setDate(++i, entity.getDataSepultamento() != null ? new Date(entity.getDataSepultamento().getTime()) : null);
			stmt.setInt(++i, entity.getTipoAtendimento() != null ? entity.getTipoAtendimento().getId() : 0 );
			stmt.setString(++i,entity.getNomeFalecido());
			stmt.executeUpdate();
			conn.commit();
			
			entity.setId(this.getIdInserido(conn));
		} finally {
			if (stmt != null)
				stmt.close();
		}

		
	}

	public void update(ObitoVO entity, Connection conn) throws SQLException {
		
		PreparedStatement stmt = null;

		try {
			String sql = 
				    " UPDATE OBITO SET ";
			
				    		
			if(entity.getAssociado() != null) sql += " ID_ASSOCIADO          = ?, ";		
					
			if(entity.getApolice() != null) sql+=" ID_APOLICE                = ?,";
					 
			
			sql +=	" DATA_OBITO            = ?, " +
					" CAUSA                 = ?, " +
					" NUMERO_ATESTADO       = ?, " +
					" DATA_EXPEDICAO        = ?, "+
					" LOCAL_FALECIMENTO     = ?," +
					" ID_CIDADE_FALECIMENTO = ?," +
					" ID_MOTORISTA_VIAGEM   = ?," +
					" LOCAL_VELORIO         = ?," +
					" ID_CIDADE_VELORIO     = ?," +
					" ID_MOTORISTA_CORTEJO  = ?," +
					" ID_CEMITERIO          = ?, " +
					" HORA_SEPULTAMENTO     = ?, " +
					" DATA_SEPULTAMENTO     = ?, " +
					" ID_TIPO_ATENDIMENTO    = ?, " +
					" NOME_FALECIDO          = ? " +
					" WHERE ID_OBITO = ?";
			
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			
			int i = 0;
			
			if(entity.getAssociado() != null) 
				stmt.setInt(++i,entity.getAssociado() != null ? entity.getAssociado().getId() : 0);
			if(entity.getApolice() != null) 
				stmt.setInt(++i,entity.getApolice().getId());
			
			stmt.setDate(++i, entity.getData() != null ? new Date(entity.getData().getTime()) : null);
			stmt.setString(++i,entity.getCausa());
			stmt.setString(++i,entity.getNumeroAtestado());
			stmt.setDate(++i, entity.getDataExpedicao() != null ? new Date(entity.getDataExpedicao().getTime()) : null);
			stmt.setString(++i,entity.getLocalFalecimento());
			stmt.setInt(++i,entity.getCidadeFalecimento() != null ? entity.getCidadeFalecimento().getId() : 0);
			stmt.setInt(++i,entity.getGrupoTrabalhoViagem() != null ? entity.getGrupoTrabalhoViagem().getId() : 0);
			stmt.setString(++i,entity.getLocalVelorio() );
			stmt.setInt(++i, entity.getCidadeVelorio() != null ? entity.getCidadeVelorio().getId() : 0);
			stmt.setInt(++i, entity.getGrupoTrabalhoCortejo() != null ? entity.getGrupoTrabalhoCortejo().getId() : 0);
			stmt.setInt(++i, entity.getCemiterio() != null ? entity.getCemiterio().getId() : 0 );
			stmt.setString(++i, entity.getHoraSepultamento().trim());
			stmt.setDate(++i, entity.getDataSepultamento() != null ? new Date(entity.getDataSepultamento().getTime()) : null);
			stmt.setInt(++i, entity.getTipoAtendimento() != null ? entity.getTipoAtendimento().getId() : 0 );
			stmt.setString(++i,entity.getNomeFalecido());
			stmt.setInt(++i,entity.getId());
			
			
			stmt.executeUpdate();
			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		
	}
	
	private ObitoVO createVOFromResultSet(ResultSet rs, Connection conn) throws SQLException
	{
		ObitoVO vo = new ObitoVO();
		
		
		AssociadoDAO assDao = new AssociadoDAOImpl();
		int id_ass = rs.getInt(ObitoVO.ID_ASSOCIADO);
		if(id_ass > 0)
			vo.setAssociado(assDao.findById(id_ass, conn));
				
		vo.setNomeFalecido(rs.getString(ObitoVO.NOME_FALECIDO));
		vo.setCausa(rs.getString(ObitoVO.CAUSA));
		vo.setData(rs.getDate(ObitoVO.DATA_OBITO));
		vo.setDataExpedicao(rs.getDate(ObitoVO.DATA_EXPEDICAO));
		vo.setId(rs.getInt(ObitoVO.ID_OBITO));
		vo.setNumeroAtestado(rs.getString(ObitoVO.NUMERO_ATESTADO));
		
		vo.setLocalFalecimento(rs.getString(ObitoVO.LOCAL_FACELIMENTO));
		vo.setLocalVelorio(rs.getString(ObitoVO.LOCAL_VELORIO));
		
		vo.setDataSepultamento(rs.getDate(ObitoVO.DATA_SEPULTAMENTO));
		vo.setHoraSepultamento(rs.getString(ObitoVO.HORA_SEPULTAMENTO));
		
		CidadeDAO cidadeDAO = new CidadeDAOImpl();
		int idCidadeF =  rs.getInt(ObitoVO.ID_CIDADE_FALECIMENTO);
		if(idCidadeF > 0)
			vo.setCidadeFalecimento(cidadeDAO.findById(idCidadeF, conn));
		
		int idCidadeV =  rs.getInt(ObitoVO.ID_CIDADE_VELORIO);
		if(idCidadeV > 0)
			vo.setCidadeVelorio(cidadeDAO.findById(idCidadeV, conn));
		
		GrupoTrabalhoDAO colDAO = new GrupoTrabalhoDAOImpl();
		int idMotoristaV =  rs.getInt(ObitoVO.ID_MOTORISTA_VIAGEM);
		if(idMotoristaV > 0)
			vo.setGrupoTrabalhoViagem(colDAO.findById(idMotoristaV, conn));
		
		int idMotoristaF =  rs.getInt(ObitoVO.ID_MOTORISTA_CORTEJO);
		if(idMotoristaF > 0)
			vo.setGrupoTrabalhoCortejo(colDAO.findById(idMotoristaF, conn));
		
		CemiterioDAO cemiterioDAO = new CemiterioDAOImpl();
		int idCemiterio =  rs.getInt(ObitoVO.ID_CEMITERIO);
		if(idCemiterio > 0)
			vo.setCemiterio(cemiterioDAO.findById(idCemiterio, conn));
		
		TipoAtendimentoDAO tipoDAO = new TipoAtendimentoDAOImpl();
		int idTipo =  rs.getInt(ObitoVO.ID_TIPO_ATENDIMETNO);
		if(idTipo > 0)
			vo.setTipoAtendimento(tipoDAO.findById(idTipo, conn));
		
				
		ServicoObitoDAO servicoDAO = new ServicoObitoDAOImpl();
		List<ServicoObitoVO> list =  servicoDAO.getAllServicoObitoByObito(vo, conn);
		vo.setServicos((list));
		
		ProdutoObitoDAO produtoDAO = new ProdutoObitoDAOImpl();
		List<ProdutoObitoVO> listP = produtoDAO.getAllProdutoObitoByObito(vo, conn);
		vo.setProdutos((listP));
		
		ApoliceDAO apoliceDAO = new ApoliceDAOImpl();
		vo.setApolice(apoliceDAO.carregarApoliceSimples(rs.getInt(ObitoVO.ID_APOLICE), conn));
		
		return vo;
	}


	public ObitoVO findById(Integer id, Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM OBITO WHERE ID_OBITO = ?";
			
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

	public List<ObitoVO> getAllObitosByApolice(ApoliceVO apolice,
			Connection conn) throws SQLException {
		
		List<ObitoVO> list = new ArrayList<ObitoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try{
			String sql = "SELECT * FROM OBITO WHERE ID_APOLICE = ? ORDER BY DATA_OBITO";
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, apolice.getId());
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

	public void deleteAllObitosByApolice(ApoliceVO apolice, Connection conn)
			throws SQLException {
		
		PreparedStatement stmt = null;
		
		try{
			String sql = "DELETE FROM OBITO WHERE ID_APOLICE = ? ";
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
	
private int getIdInserido(Connection conn) throws SQLException {
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = " SELECT @@IDENTITY AS ID FROM OBITO";
		
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		int id = 0;
		while(rs.next())
			id = rs.getInt("ID");
		
		return id;
			
		
	}

public ObitoVO getObitoByDependente(AssociadoVO dependente, Connection conn)
		throws SQLException {
	ResultSet rs = null;
	PreparedStatement stmt = null;
	
	try{
		String sql = "SELECT * FROM OBITO WHERE ID_ASSOCIADO = ?";
		conn.setAutoCommit(false);
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1,dependente.getId());
		rs = stmt.executeQuery();
		conn.commit();
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

	@Override
	public List<ObitoVO> consultaObito(String tipo, Object[] parametros, CidadeVO cidade, TipoAtendimentoVO tipoAtendimento,
			Connection conn) throws SQLException {
		
		String sql = "SELECT * FROM OBITO O";
		
		if(tipo.equals(ConsultaObitoPanel.OPCOES_CONSULTA[ConsultaObitoPanel.QUALQUER]))
		{
			boolean isTemWhere = false;
			sql = "SELECT * FROM OBITO O";
			if(cidade != null && cidade.getId() != null){
				sql += " INNER JOIN APOLICE AP ON O.ID_APOLICE = AP.ID_APOLICE WHERE AP.ID_CIDADE = " + cidade.getId();
				isTemWhere = true;
			}
			if(tipoAtendimento != null){
				if(isTemWhere)
					sql += " AND O.ID_TIPO_ATENDIMENTO = " + tipoAtendimento.getId();
				else
					sql += " WHERE O.ID_TIPO_ATENDIMENTO = " + tipoAtendimento.getId();
			}
				
		
		}else if(tipo.equals(ConsultaObitoPanel.OPCOES_CONSULTA[ConsultaObitoPanel.DATA]))
		{
				sql = " SELECT * FROM OBITO O";
				if(cidade != null && cidade.getId() != null)
					sql += " INNER JOIN APOLICE A ON O.ID_APOLICE = A.ID_APOLICE";
				
				boolean temWhere = false; 
				if(parametros[0] != null)
				{
					sql += " WHERE DATA_OBITO >= '" + parametros[0] + "'";
					temWhere = true;
				}
				if(parametros[1] != null)
				{
					if(!temWhere)
						sql += " WHERE ";
					else
						sql += " AND ";
					
					sql += " DATA_OBITO <= '" + parametros[1] + "'";
				}
						
							
				if(cidade != null && cidade.getId() != null)
					sql += " AND A.ID_CIDADE = " + cidade.getId();
				
				if(tipoAtendimento != null)
					sql += " AND O.ID_TIPO_ATENDIMENTO = " + tipoAtendimento.getId();
				
				
				
				
		}else if(tipo.equals(ConsultaObitoPanel.OPCOES_CONSULTA[ConsultaObitoPanel.NOME]))
		{
			sql = " SELECT " +
					" ID_OBITO, DATA_OBITO, CAUSA, NUMERO_ATESTADO, DATA_EXPEDICAO,LOCAL_FALECIMENTO, ID_CIDADE_FALECIMENTO, ID_MOTORISTA_VIAGEM," +
					" LOCAL_VELORIO, ID_CIDADE_VELORIO, ID_MOTORISTA_CORTEJO, ID_CEMITERIO, HORA_SEPULTAMENTO, DATA_SEPULTAMENTO, " +
					" ID_TIPO_ATENDIMENTO,  NOME_FALECIDO, OB.ID_ASSOCIADO, OB.ID_APOLICE " +
					" FROM OBITO OB INNER JOIN ASSOCIADO AA ON OB.ID_ASSOCIADO = AA.ID_ASSOCIADO";
			
			if(cidade != null && cidade.getId() != null)
				sql += " INNER JOIN APOLICE A ON OB.ID_APOLICE = A.ID_APOLICE";
			
			
			sql += " WHERE AA.NOME LIKE '%" + parametros[0] +"%' ";
			
			if(cidade != null && cidade.getId() != null)
				sql += " AND A.ID_CIDADE = " + cidade.getId();
			
			if(tipoAtendimento != null)
				sql += " AND OB.ID_TIPO_ATENDIMENTO = " + tipoAtendimento.getId();
			
			sql += " UNION ALL SELECT " +
					" ID_OBITO, DATA_OBITO, CAUSA, NUMERO_ATESTADO, DATA_EXPEDICAO,LOCAL_FALECIMENTO, ID_CIDADE_FALECIMENTO, ID_MOTORISTA_VIAGEM," +
					" LOCAL_VELORIO, ID_CIDADE_VELORIO, ID_MOTORISTA_CORTEJO, ID_CEMITERIO, HORA_SEPULTAMENTO, DATA_SEPULTAMENTO, " +
					" ID_TIPO_ATENDIMENTO,  NOME_FALECIDO, OB.ID_ASSOCIADO, OB.ID_APOLICE " +
					" FROM OBITO OB";
			
			if(cidade != null && cidade.getId() != null)
				sql += " INNER JOIN APOLICE A ON OB.ID_APOLICE = A.ID_APOLICE";
					
			sql+= " WHERE OB.NOME_FALECIDO LIKE '%"+ parametros[0]+ "%'";
			
			if(cidade != null && cidade.getId() != null)
				sql += " AND A.ID_CIDADE = " + cidade.getId();
			
			if(tipoAtendimento != null)
				sql += " AND OB.ID_TIPO_ATENDIMENTO = " + tipoAtendimento.getId();
		}
		
		
		else if(tipo.equals(ConsultaObitoPanel.OPCOES_CONSULTA[ConsultaObitoPanel.CONTRATO]))
		{
				sql = " SELECT * FROM OBITO OB INNER JOIN APOLICE AP ON OB.ID_APOLICE = AP.ID_APOLICE WHERE AP.NUMERO_CONTRATO = "+ parametros[0];
		}
		
		
		sql += " ORDER BY DATA_OBITO";
		
		List<ObitoVO> list = new ArrayList<ObitoVO>();
		ResultSet rs = null;
		PreparedStatement stmt = null;

		try {
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(this.createVOFromResultSet(rs, conn));
			}

			conn.commit();
		} finally {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		}

		return list;

			
	}

	
	
}
