package br.com.saodimas.model.facade;

import java.sql.Connection;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.dao.connection.SqlConnector;
import br.com.saodimas.model.dao.factory.DAOFactory;
import br.com.saodimas.model.facade.remote.ColaboradorRemote;

public class Colaborador implements ColaboradorRemote {

	public void insertDependente(ApoliceVO apolice, AssociadoVO dependente)
			throws Exception {

		Connection conn = null;
		try {
			conn = SqlConnector.getInstance().getConnection();
			dependente.setApolice(apolice);
			DAOFactory.DEFAULT.buildAssociadoDAO().save(dependente, conn);

		} finally {
			
		}

	}

	public ApoliceVO searchApoliceByNumeroContrato(String numeroContrato)
			throws Exception {

		Connection conn = null;
		try {
			conn = SqlConnector.getInstance().getConnection();
			return DAOFactory.DEFAULT.buildApoliceDAO()
					.searchApoliceByNumeroContrato(numeroContrato, conn);

		} finally {
			
		}

	}

}
