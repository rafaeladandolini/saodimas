package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;

public interface ProdutoObitoDAO extends GenericDAO<ProdutoObitoVO> {

		List<ProdutoObitoVO> getAllProdutoObitoByObito(ObitoVO obito, Connection conn) throws SQLException;
		void deleteAllProdutoObitoByObito(ObitoVO obito, Connection conn) throws SQLException;

}
