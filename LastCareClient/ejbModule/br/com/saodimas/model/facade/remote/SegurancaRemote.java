package br.com.saodimas.model.facade.remote;


import br.com.saodimas.model.beans.ColaboradorVO;

/**
 * Interface para acesso remoto que define os metodos da Seguranca
 * @author Daniel/Rafaela
 *
 */

public interface SegurancaRemote {

	
	public ColaboradorVO autenticarColaborador(String login, String senha) throws Exception;
	
}
