package br.com.saodimas.model.exception;

import java.io.Serializable;

public class GenericDAOException extends Exception implements Serializable{

	private static final long serialVersionUID = -8041383041018426263L;

	public GenericDAOException(String mensagem) {
		super(mensagem);
	}

	public GenericDAOException() {
		super();
	}
}
