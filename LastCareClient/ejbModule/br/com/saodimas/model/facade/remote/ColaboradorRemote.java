package br.com.saodimas.model.facade.remote;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;


public interface ColaboradorRemote {


	public ApoliceVO searchApoliceByNumeroContrato(String numeroContrato)
			throws Exception;

	public void insertDependente(ApoliceVO apolice, AssociadoVO dependente)
			throws Exception;

}
