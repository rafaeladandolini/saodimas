package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.CidadeVO;

public interface CidadeDAO extends GenericDAO<CidadeVO>{

	List<CidadeVO> getAllCidadesByEstado(String estado, Connection conn)throws SQLException;
}
