package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.TipoAtendimentoVO;

public interface ObitoDAO extends GenericDAO<ObitoVO>{

	List<ObitoVO> getAllObitosByApolice(ApoliceVO apolice, Connection conn) throws SQLException;
	void deleteAllObitosByApolice(ApoliceVO apolice, Connection conn) throws SQLException;
	ObitoVO getObitoByDependente(AssociadoVO dependente, Connection conn)throws SQLException;;
	List<ObitoVO> consultaObito(String tipo, Object[] parametros, CidadeVO cidade, TipoAtendimentoVO tipoAtendimento,
			Connection conn) throws SQLException;
}
