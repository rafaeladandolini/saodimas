package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.saodimas.model.beans.FormaPagamentoVO;
import br.com.saodimas.model.beans.ParcelamentoVO;

public interface ParcelamentoDAO extends GenericDAO<ParcelamentoVO>{

	public List<ParcelamentoVO> getAllParcelamentoByFormaPagamento(FormaPagamentoVO formaPag, Connection conn) throws SQLException;
}

	
