package br.com.saodimas.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import br.com.saodimas.model.beans.ChequeVO;


public interface ChequeDAO extends GenericDAO<ChequeVO>{
	
	public ChequeVO baixarCheque(ChequeVO cheque, Date dataBaixa, Connection conn) throws SQLException;
	
}

	
