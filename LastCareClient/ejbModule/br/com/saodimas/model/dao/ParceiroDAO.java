package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ParceiroVO;

public interface ParceiroDAO extends GenericDAO<ParceiroVO>{
	
	List<ParceiroVO> getAllParceirosAtivos(Connection conn)throws SQLException;
}
