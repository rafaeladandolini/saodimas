package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.ProdutoVO;

public interface ProdutoDAO extends GenericDAO<ProdutoVO> {

	List<ProdutoVO> getAllProdutosByStatus(String status, Connection conn) throws SQLException;
	List<ProdutoVO> getAllProdutosByStatusTipoProduto(String status, String tipoProduto, Connection conn) throws SQLException;
	void saveAll(Set<ProdutoVO> list, Connection conn) throws SQLException;
	List<ProdutoVO> consultaProduto(String tipo, String[] parametros,
			String status, Connection conn) throws SQLException;
}
