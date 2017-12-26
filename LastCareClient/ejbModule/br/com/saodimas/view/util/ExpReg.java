package br.com.saodimas.view.util;


/**
 * Contém apenas strings constantes que definem o padrão dos dados a serem inseridos 
 * pelo usuário.
 * @author Daniel
 *
 */
public final class ExpReg {
	public static final String DATE = "^((\\d){1,2}\\/(\\d){1,2}\\/(\\d){4})$";
	public static final String CPF = "^((\\d){3}\\.(\\d){3}\\.(\\d){3}\\-(\\d){2})$";
	public static final String CNPJ = "^((\\d){2}\\.(\\d){3}\\.(\\d){3}\\/(\\d){4}\\-(\\d){2})$";
	public static final String PHONE = "^(\\((\\d){2}\\) (\\d){4}\\-(\\d){4})$";
	public static final String CEP = "^((\\d){2}\\.(\\d){3}\\-(\\d{3}))$";
	public static final String LOGIN = "^([\\p{Alnum}])$";
	public static final String ALPHA = "^(\\p{Alpha}+)$";
	public static final String NUMERIC = "^([\\p{Digit}]+)$";
	public static final String ALPHANUMERIC = "^(\\p{Alnum}+)$";
	public static final String NO_SPACES = "^(^\\s+)$";
	public static final String NO_METACHARS = "^([\\p{Alnum}\\p{Blank}\\.]+)$";

//	public static void main(String args[]){
//		Pattern p;
//		Matcher m;
//		String data = "6/9/9999";
//		
//		p = Pattern.compile(DATE);
//		m = p.matcher(data);
//		
//		if (m.matches()){
//			System.out.println("A data parece válida...");
//		}
//		else{
//			System.err.println("A data não parace válida!");
//		}

}
