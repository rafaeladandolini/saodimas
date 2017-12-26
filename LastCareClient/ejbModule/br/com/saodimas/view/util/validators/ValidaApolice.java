package br.com.saodimas.view.util.validators;

import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.PlanoVO;



public class ValidaApolice {

	public static String validarEndereco(String addr){
		String mensagem = "";
		if (addr == null || addr.trim().length() < 3) mensagem = "Na apólice deve constar o endereço do associado!";
		
		return mensagem;
	} 

	public static String validarBairro(String bairro){
		String mensagem = "";
		if (bairro == null || bairro.trim().length() == 0) mensagem = "Na apólice deve constar o bairro da residência do associado!";

		return mensagem;
	}

	public static String validarCEP(String cep){
		String mensagem = "";
		if (cep == null || cep.trim().length() != 10) mensagem = "Na apólice deve constar um CEP válido!";

		return mensagem;
	}

	public static String validarPlano(PlanoVO p){
		String mensagem = "";
		if (p == null) mensagem = "A apólice deve estar associada a um plano!";

		return mensagem;
	}

	public static String validaRazaoSocial(String razaoSocial) {
		String mensagem = "";
		if (razaoSocial == null || razaoSocial.trim().length() < 3) mensagem = "A Razão Social da empresa deve ser preenchida se o plano for corporativo!";

		return mensagem;
	}

	public static String validaCNPJ(String cnpj) {
		String mensagem = "";
		
		if (cnpj == null || cnpj.trim().length() != 18)  mensagem = "O CNPJ da empresa deve ser preenchido se o plano for corporativo!";
		else{
			cnpj = cnpj.replaceAll("\\p{Punct}", "");
			int soma = 0;
			int digitoVerif;  
			String cnpjCalc = cnpj.substring(0,12);  
			char[] chrCnpj = cnpj.toCharArray();  

			/* Primeira parte */  
			for(int i = 0; i < 4; i++)  
				if (chrCnpj[i] - 48 >= 0 && chrCnpj[i] - 48 <= 9)  
					soma += (chrCnpj[i] - 48) * (6 - (i + 1)) ; 
			for(int i = 0; i < 8; i++)  
				if (chrCnpj[i + 4] - 48 >= 0 && chrCnpj[i + 4] - 48 <= 9)  
					soma += (chrCnpj[i + 4] - 48) * (10 - (i + 1)) ;  
			digitoVerif = 11 - (soma % 11);  

			cnpjCalc += (digitoVerif == 10 || digitoVerif == 11) ? "0" : Integer.toString(digitoVerif);  

			/* Segunda parte */  
			soma = 0;  
			for (int i = 0; i < 5; i++)  
				if (chrCnpj[i] - 48 >=0 && chrCnpj[i] - 48 <= 9)  
					soma += (chrCnpj[i] - 48) * (7 - (i + 1)) ;  
			for (int i = 0; i < 8; i++)  
				if (chrCnpj[i + 5] - 48 >= 0 && chrCnpj[i + 5] - 48 <=9)  
					soma += (chrCnpj[i+5] - 48) * (10 - (i + 1)) ;  
			digitoVerif = 11 - (soma % 11);  
			cnpjCalc += ( digitoVerif == 10 || digitoVerif == 11) ? "0" : Integer.toString(digitoVerif);
			
			if (!cnpj.equals(cnpjCalc)) mensagem = "O CNPJ da empresa não é válido!";
		}

		return mensagem;
	}

	public static String validaCidade(CidadeVO cidade) {
		String mensagem = "";
		if (cidade == null) mensagem = "Na apólice deve constar a cidade da residência do associado!";

		return mensagem;
	}

	public static String validaEstado(String estado) {
		String mensagem = "";
		if (estado == null || estado.trim().length() == 0) mensagem = "Na apólice deve constar o estado da residência do associado!";

		return mensagem;
	}
	
	public static String validarNumApolice(String numApolice)
	{
		String mensagem = "";
		if (numApolice == null || numApolice.trim().length() == 0) mensagem = "Número Obrigatório";
			return mensagem;
	}
}
