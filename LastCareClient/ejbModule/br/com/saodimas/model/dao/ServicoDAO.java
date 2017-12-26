package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.ServicoVO;

public interface ServicoDAO extends GenericDAO<ServicoVO> {

	List<ServicoVO> getAllServicosByStatus(String status, Connection conn)
			throws SQLException;

	void saveAll(Set<ServicoVO> list, Connection conn) throws SQLException;
	
	List<ServicoVO> consultaServico(String tipo, String[] parametros,
			String status, Connection conn) throws SQLException;

}
