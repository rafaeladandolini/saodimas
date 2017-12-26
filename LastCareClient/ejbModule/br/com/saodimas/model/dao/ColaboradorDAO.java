package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.saodimas.model.beans.ColaboradorVO;

public interface ColaboradorDAO extends GenericDAO<ColaboradorVO>{

	ColaboradorVO autenticarColaborador(String login, String senha, Connection conn)throws SQLException;
	
}
