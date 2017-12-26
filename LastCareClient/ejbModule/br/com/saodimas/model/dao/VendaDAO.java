package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.beans.VendaVO;

public interface VendaDAO extends GenericDAO<VendaVO>{

	List<VendaVO> getAllVendasByCliente(ClienteVO cliente, Connection conn) throws SQLException;
	public void finalizarVenda(VendaVO venda, String statusVenda, Connection conn) throws SQLException;

	
}
