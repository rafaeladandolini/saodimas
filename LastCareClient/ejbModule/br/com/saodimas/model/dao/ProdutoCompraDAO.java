package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.CompraVO;
import br.com.saodimas.model.beans.ProdutoCompraVO;

public interface ProdutoCompraDAO extends GenericDAO<ProdutoCompraVO> {

		List<ProdutoCompraVO> getAllProdutoCompraByCompra(CompraVO compra, Connection conn) throws SQLException;
		void deleteAllProdutoCompraByCompra(CompraVO compra, Connection conn) throws SQLException;

}
