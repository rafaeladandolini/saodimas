package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.CidadeVO;

public interface ApoliceDAO extends GenericDAO<ApoliceVO>{
	
	ApoliceVO searchApoliceByNumeroContrato(String numeroContrato, Connection conn)throws SQLException;
	List<ApoliceVO> consultaApolice(String tipo, String[] parametros, String status, Connection conn)throws SQLException;
	boolean isNumContratoValido(String num, Connection conn) throws SQLException;
	Integer getProximoNumContrato(Connection conn)throws SQLException;
	ApoliceVO carregarApoliceSimples(Integer idApolice, Connection conn)throws SQLException;
	List<ApoliceVO> findAllOrderByCidade(Connection conn)throws SQLException;
	List<ApoliceVO> findByCidadeOrderByCidade(CidadeVO cidade, Connection conn)throws SQLException;
}
