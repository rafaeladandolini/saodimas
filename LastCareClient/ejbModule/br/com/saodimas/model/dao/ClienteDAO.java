package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import br.com.saodimas.model.beans.ClienteVO;

public interface ClienteDAO extends GenericDAO<ClienteVO> {

	List<ClienteVO> consultaClientes(String tipo, String[] parametros, String status, Connection conn)throws SQLException;
	
}
