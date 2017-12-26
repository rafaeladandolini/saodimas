package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.EquipamentoVO;

public interface EquipamentoDAO extends GenericDAO<EquipamentoVO>{
	
	public List<EquipamentoVO> getAllEquipamentosDisponiveis(Connection conn) throws SQLException;
}
