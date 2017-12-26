package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;

public interface AssociadoDAO extends GenericDAO<AssociadoVO>{

		List<AssociadoVO> getAllDependentesByApolice(ApoliceVO apolice, Connection conn) throws SQLException;
		void deleteAllDependentesByApolice(ApoliceVO apolice, Connection conn) throws SQLException;
		void saveTitular(AssociadoVO associadoVO, Connection conn)throws SQLException;
		//void updateIdApoliceTitular(AssociadoVO associadoVO, Connection conn)throws SQLException;
		void deleteTitularByApolice(ApoliceVO apolice, Connection conn) throws SQLException;
		void atualizaDadosImpressaoCartao (AssociadoVO associadoVO, Connection conn)throws SQLException;
}

