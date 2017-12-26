package br.com.saodimas.model.facade;

import java.sql.Connection;
import java.util.List;

import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.dao.connection.SqlConnector;
import br.com.saodimas.model.dao.factory.DAOFactory;
import br.com.saodimas.model.facade.remote.RelatorioRemote;


public class Relatorio implements RelatorioRemote {

	
	public List<AssociadoVO> listPerfilAssociado() throws Exception {
		
		Connection conn = null;
		try{
			conn = SqlConnector.getInstance().getConnection();
			return DAOFactory.DEFAULT.buildAssociadoDAO().findAll(conn);
		
		}finally{
			
		}
	}

}
