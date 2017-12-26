package br.com.saodimas.view.util.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Executa o servico de consulta endereco por CEP pelo m[etodo REST.
 * @author republicavirtual
 * 
 */

public class EnderecoService {
	public static final String OK = "1";
	private static final String URL_SERVICO = "http://cep.republicavirtual.com.br/web_cep.php";

	public static EnderecoVO consultar(String cepEndereco) throws Exception{
		String cep = cepEndereco.trim().replaceAll("\\p{Punct}", "");
		String urlString = URL_SERVICO;

		// os parametros a serem enviados
		Properties parameters = new Properties();
		parameters.setProperty("cep", cep);
		parameters.setProperty("formato", "xml");

		// o iterador, para criar a URL
		Iterator <Object> i = parameters.keySet().iterator();
		int counter = 0;

		// enquanto ainda existir parametros
		while (i.hasNext()) {
			String name = (String) i.next();
			String value = parameters.getProperty(name);

			// adiciona com um conector (? ou &)
			// o primeiro é ?, depois são &
			urlString += (++counter == 1 ? "?" : "&") + name + "=" + value;
		}

		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Request-Method", "GET");

		// seta a variavel para ler o resultado
		connection.setDoInput(true);
		connection.setDoOutput(false);

		// conecta com a url destino
		connection.connect();

		// abre a conexão pra input
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		// le ate o final
		StringBuffer newData = new StringBuffer();
		String s = "";
		while (null != ((s = reader.readLine()))) {
			newData.append(s);
		}
		reader.close();

		// Controi classe a partir do XML 
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("webservicecep", EnderecoVO.class);
		
		return ((EnderecoVO)xstream.fromXML(newData.toString()));
	}
}
