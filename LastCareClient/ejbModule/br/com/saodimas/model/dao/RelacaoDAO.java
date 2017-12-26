package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.RelacaoVO;

public interface RelacaoDAO extends GenericDAO<RelacaoVO>{

	List<RelacaoVO> getAllRelacoesByTipoStatus(String status, String tipo, Connection conn)throws SQLException;
}
