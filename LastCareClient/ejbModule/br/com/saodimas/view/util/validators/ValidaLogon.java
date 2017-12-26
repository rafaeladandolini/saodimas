package br.com.saodimas.view.util.validators;

import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.principal.SaoDimasMain;

public class ValidaLogon {

	public static void validaCamposLogon(String logon, String senha)throws MensagemSaoDimasException{
		
		if (logon.trim().length() == 0 || senha.trim().length() == 0)
			throw new MensagemSaoDimasException("Os campos Login e Senha s�o obrigat�rios.");
	}
	
	public static void validaCamposAltSenha(String senhaAtual, String novaSenha, String confSenha)throws MensagemSaoDimasException{
		
		if (senhaAtual.trim().length() == 0 || novaSenha.trim().length() == 0 || confSenha.trim().length() == 0)
			throw new MensagemSaoDimasException("Campos obrigat�rios(*) n�o preenchidos.");
		
		if(!(SaoDimasMain.colaborador.getSenha().equals(senhaAtual)
				&& novaSenha.equals(confSenha)))
			throw new MensagemSaoDimasException("Senha(s) inv�lidas.");
			
	}
	
}
