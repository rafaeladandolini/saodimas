package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;

public interface ServicoObitoDAO extends GenericDAO<ServicoObitoVO>{
	
	List<ServicoObitoVO> getAllServicoObitoByObito(ObitoVO obito, Connection conn) throws SQLException;
	void deleteAllServicoObitoByObito(ObitoVO obito, Connection conn) throws SQLException;
}
