package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ServicoPlanoVO;

public interface ServicoPlanoDAO extends GenericDAO<ServicoPlanoVO> {

		List<ServicoPlanoVO> getAllServicoPlanoByPlano(PlanoVO plano, Connection conn) throws SQLException;
		void saveAll(Set<ServicoPlanoVO> list, Connection conn) throws SQLException;
		void deleteAllServicoPlanoByPlano(PlanoVO plano, Connection conn) throws SQLException;
 }
