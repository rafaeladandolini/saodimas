package br.com.saodimas.view.util.validators;

import java.util.Date;

public class ValidaFatura {
	public static String validaVencimento(Date data){
		String mensagem = "";
		if (data == null) mensagem = "A Data de Vencimento deve ser preenchida!";
		return mensagem;
	}
	
	public static String validaValor(Double valor){
		String mensagem = "";
		if (valor == 0) mensagem = "O Valor da Fatura deve ser maior que zero!";
		
		return mensagem;
	}
}
