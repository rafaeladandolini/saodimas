package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.model.beans.VendaVO;

public interface ServicoVendaDAO extends GenericDAO<ServicoVendaVO>{
	
	List<ServicoVendaVO> getAllServicoVendaByVenda(VendaVO venda, Connection conn) throws SQLException;
	void deleteAllServicoVendaByVenda(VendaVO venda, Connection conn) throws SQLException;
}
