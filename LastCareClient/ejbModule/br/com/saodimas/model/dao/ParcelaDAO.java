package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ParcelaVO;
import br.com.saodimas.model.beans.VendaVO;

public interface ParcelaDAO extends GenericDAO<ParcelaVO>{

	public List<ParcelaVO> getAllParcelasByVenda(VendaVO venda, Connection conn) throws SQLException;
	void deleteAllParcelasByVenda(VendaVO venda, Connection conn) throws SQLException;
}

	
