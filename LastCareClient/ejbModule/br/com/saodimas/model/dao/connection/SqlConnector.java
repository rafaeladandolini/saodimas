package br.com.saodimas.model.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.saodimas.model.exception.MensagemSaoDimasException;

public class SqlConnector {

	private static SqlConnector sqlConnector =  new SqlConnector();
	
	public static SqlConnector getInstance() {
		if(sqlConnector == null)
			sqlConnector = new SqlConnector();
		return sqlConnector;
	}
	
	private Connection conn = null;
	
	public Connection getConnection() throws SQLException, MensagemSaoDimasException
	{
		if(conn == null)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//conn = DriverManager.getConnection("jdbc:mysql://SAODIMAS-PC:3306/saodimas", "rafaela","root");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/saodimas", "root", "root");
				//SaoDimasSettings settings = new SaoDimasSettings();
				//settings.carregarPreferencias();
				//conn = DriverManager.getConnection(settings.getConexaoBancoDados(), "root", "root");
				
				conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new MensagemSaoDimasException();
			}catch(SQLException ee)		
			{	ee.printStackTrace();
			
				throw new MensagemSaoDimasException("Erro de conexão com bando de dados. Informe o Administrador.");
			}catch (Exception eee) {
				throw new MensagemSaoDimasException("Erro de conexão com bando de dados"+ eee.getMessage());
			}
		}
		
		return conn;
		
	}
	
	public void closehConnection() throws SQLException
	{
		if(conn != null)
			conn.close();
	}
}
