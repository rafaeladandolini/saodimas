package br.com.saodimas.model.facade;

import java.sql.Connection;

import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.dao.connection.SqlConnector;
import br.com.saodimas.model.dao.factory.DAOFactory;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.model.facade.remote.SegurancaRemote;


public class Seguranca implements SegurancaRemote{
	
	
	public ColaboradorVO autenticarColaborador(String login, String senha) throws Exception
	{
		Connection conn = null;
		ColaboradorVO colaborador = null;
		
		try{
			conn = SqlConnector.getInstance().getConnection();
			
			colaborador = DAOFactory.DEFAULT.buildColaboradorDAO().autenticarColaborador(login, senha, conn);
			if(colaborador == null)
				throw new MensagemSaoDimasException("Login e/ou senha inválidos(s).");
			
			if(!colaborador.getStatus().equals("Ativo"))
				throw new MensagemSaoDimasException(colaborador.getTipoColaborador() + " Desativado.");
			
			return colaborador;
			
		}finally
		{
		
		}	
	}
	
	
}