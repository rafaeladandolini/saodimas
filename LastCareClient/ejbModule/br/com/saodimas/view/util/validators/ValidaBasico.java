package br.com.saodimas.view.util.validators;

import br.com.saodimas.model.exception.MensagemSaoDimasException;

public class ValidaBasico {

	public static void validaCamposCidade(String cidade)
			throws MensagemSaoDimasException {

		if (cidade.trim().length() == 0)
			throw new MensagemSaoDimasException(
					"O campo cidade deve ser preenchido.");
	}

	public static void validaCamposRelacao(String relacao)
			throws MensagemSaoDimasException {

		if (relacao.trim().length() == 0)
			throw new MensagemSaoDimasException(
					"O campo relação deve ser preenchido.");
	}

	public static void validaCamposProduto(String nome)
			throws MensagemSaoDimasException {

		if (nome.trim().length() == 0 /*|| valor.trim().length() == 0*/)
			throw new MensagemSaoDimasException(
					"Campos obrigatórios(*) não preenchidos.");

		/*valor = valor.replace(".", "");
		valor = valor.replace(",", ".");
		double valord = Double.parseDouble(valor);
		if (valord <= 0.0)
			throw new MensagemSaoDimasException("Informe um valor.");*/

	}

	public static void validaCamposServico(String nome, String valor)
			throws MensagemSaoDimasException {

		if (nome.trim().length() == 0 || valor.trim().length() == 0)
			throw new MensagemSaoDimasException(
					"Campos obrigatórios(*) não preenchidos.");

		valor = valor.replace(".", "");
		valor = valor.replace(",", ".");
		double valord = Double.parseDouble(valor);
		if (valord <= 0.0)
			throw new MensagemSaoDimasException("Informe um valor.");
	}

	public static void validaCamposPlano(String nome, String valor)
			throws MensagemSaoDimasException {

		if (nome.trim().length() == 0 || valor.trim().length() == 0)
			throw new MensagemSaoDimasException(
					"Campos obrigatórios(*) não preenchidos.");

		valor = valor.replace(".", "");
		valor = valor.replace(",", ".");
		double valord = Double.parseDouble(valor);
		if (valord <= 0.0)
			throw new MensagemSaoDimasException("Informe um valor.");
	}

}
