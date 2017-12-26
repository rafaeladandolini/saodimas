package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoPlanoVO;

public interface ProdutoPlanoDAO extends GenericDAO<ProdutoPlanoVO> {

		List<ProdutoPlanoVO> getAllProdutoPlanoByPlano(PlanoVO plano, Connection conn) throws SQLException;
		void saveAll(Set<ProdutoPlanoVO> list, PlanoVO plano, Connection conn) throws SQLException;
		void deleteAllProdutoPlanoByPlano(PlanoVO plano, Connection conn) throws SQLException;
 }
