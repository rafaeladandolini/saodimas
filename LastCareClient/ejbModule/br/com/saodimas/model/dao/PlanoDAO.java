package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.PlanoVO;

public interface PlanoDAO extends GenericDAO<PlanoVO>{

	List<PlanoVO> getAllPlanosByStatus(String status, Connection conn)throws SQLException;
}
