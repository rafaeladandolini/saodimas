package br.com.saodimas.model.facade.remote;

import java.util.List;

import br.com.saodimas.model.beans.AssociadoVO;

public interface RelatorioRemote {

	
	public List<AssociadoVO> listPerfilAssociado() throws Exception;
}
