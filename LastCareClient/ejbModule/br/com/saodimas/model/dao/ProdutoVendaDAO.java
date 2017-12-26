package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ProdutoVendaVO;
import br.com.saodimas.model.beans.VendaVO;

public interface ProdutoVendaDAO extends GenericDAO<ProdutoVendaVO> {

		List<ProdutoVendaVO> getAllProdutoVendaByVenda(VendaVO venda, Connection conn) throws SQLException;
		void deleteAllProdutoVendaByVenda(VendaVO venda, Connection conn) throws SQLException;

}
