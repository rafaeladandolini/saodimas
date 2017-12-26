package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.model.beans.EquipamentoVO;

public interface EmprestimoEquipamentoDAO extends GenericDAO<EmprestimoEquipamentoVO>{

	List<EmprestimoEquipamentoVO> getAllEmpretimoEquipamentoVOByApolice(ApoliceVO apolice, Connection conn) throws SQLException;
	List<EmprestimoEquipamentoVO> getAllDevolucoesEquipamentoVOByApolice(ApoliceVO apolice, Connection conn) throws SQLException;
	void estornoDevolucao(EmprestimoEquipamentoVO vo, Connection conn)throws SQLException;
	int getQtdeEquipamentosEmEmprestimo(EquipamentoVO equipamento, Connection conn) throws SQLException;
	List<EmprestimoEquipamentoVO> consultaEmprestimosByEquipamento(EquipamentoVO equipamento, CidadeVO cidade, String status,  Connection conn) throws SQLException;
}
