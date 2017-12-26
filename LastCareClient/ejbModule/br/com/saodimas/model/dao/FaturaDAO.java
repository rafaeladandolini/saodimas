package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.model.beans.ParceiroVO;

public interface FaturaDAO extends GenericDAO<FaturaVO>{

	List<FaturaVO> getAllFaturasByApolice(ApoliceVO apolice, Connection conn)throws SQLException;
	//List<FaturaVO> getAllFaturasByDateColaborador(ColaboradorVO  colaboradorVO, Date date, Connection conn)throws SQLException;
	//List<FaturaVO> getAllFaturasByDate(Date date, Connection conn)throws SQLException;
	//List<FaturaVO> getAllFaturasByDate(Date dateInicio, Date dateFim, Connection conn)throws SQLException;
	List<FaturaVO> getAllFaturasByDateColaboradorParceiro(ColaboradorVO  colaboradorVO, ParceiroVO parceiro, Boolean parceiroTodosNenhum, Date dataInicio, Date dataFim, Date dataInicioBaixa, Date dataFimBaixa, Connection conn)throws SQLException;
	void deleteAllFaturasByApolice(ApoliceVO apolice, Connection conn) throws SQLException;
	void destacadaFatura(FaturaVO fatura, Connection conn) throws SQLException;
	void estornarBaixaFatura(FaturaVO fatura, Connection conn) throws SQLException;
	
}
