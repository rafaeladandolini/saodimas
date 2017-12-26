package br.com.saodimas.model.exception;

import java.io.Serializable;

public class MensagemSaoDimasException extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;

	public MensagemSaoDimasException(String mensagem) {
		super(mensagem);
	}

	public MensagemSaoDimasException() {
		super();
	}
}
