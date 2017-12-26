package br.com.saodimas.view.util.validators;


public class ValidaDependente {
	public static String validaNome(String nome){
		String mensagem = "";
		if (nome == null || nome.length() == 0) mensagem = "O Nome deve ser preenchido!";

		return mensagem;
	}

	public static String validaCPF(String cpf) {
		String mensagem = "";
		if (cpf != null && cpf.length() > 0){
			cpf = cpf.replaceAll("\\p{Punct}", "");
			int     d1, d2;
			int     digito1, digito2, resto;
			int     digitoCPF;
			String  nDigResult;

			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;

			for (int nCount = 1; nCount < cpf.length() -1; nCount++){
				digitoCPF = Integer.valueOf (cpf.substring(nCount -1, nCount)).intValue();
				d1 = d1 + ( 11 - nCount ) * digitoCPF;
				d2 = d2 + ( 12 - nCount ) * digitoCPF;
			}

			resto = (d1 % 11);

			if (resto < 2) digito1 = 0;
			else digito1 = 11 - resto;

			d2 += 2 * digito1;

			resto = (d2 % 11);

			if (resto < 2) digito2 = 0;
			else digito2 = 11 - resto;

			String nDigVerific = cpf.substring (cpf.length()-2, cpf.length());
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

			if (!nDigVerific.equals(nDigResult)) mensagem = "O CPF, quando informado, deve ser válido!";
		}
		return mensagem;
	}
}
