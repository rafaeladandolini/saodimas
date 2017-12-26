package br.com.saodimas.principal;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

import org.jvnet.substance.skin.SubstanceStDismasLookAndFeel;

public class SaoDimasSettings implements Serializable{
	public static final long serialVersionUID = 2281613341501147357L;
	
	private String ultimoLogin;
	private String nomeTema;
	private String enderecoProxy;
	private String portaProxy;
	private String loginProxy;
	private String senhaProxy;
	private boolean usarProxy;
	
	private String conexaoBancoDados;
	
	public SaoDimasSettings(){
		this.ultimoLogin = "";
		this.nomeTema = new SubstanceStDismasLookAndFeel().getName();
		this.enderecoProxy = "";
		this.portaProxy = "";
		this.loginProxy = "";
		this.senhaProxy = "";
		this.usarProxy = false;
		this.conexaoBancoDados = "";
	}
	
	public boolean salvarPreferencias(){
		try {
	        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("preferencias.xml")));
	        encoder.writeObject(this);
	        encoder.close();
	        return true;

	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return false;
	    }		
	}
	
	public boolean carregarPreferencias() {
		try {
	        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("preferencias.xml")));
	        SaoDimasSettings settings = (SaoDimasSettings)decoder.readObject();
	        this.ultimoLogin	= settings.getUltimoLogin();
	        this.nomeTema		= settings.getNomeTema();
	        this.enderecoProxy	= settings.getEnderecoProxy();
	        this.portaProxy		= settings.getPortaProxy();
	        this.loginProxy		= settings.getLoginProxy();
	        this.senhaProxy		= settings.getSenhaProxy();
	        this.usarProxy		= settings.isUsarProxy();
	        this.conexaoBancoDados = settings.getConexaoBancoDados();
	        
	        decoder.close();
	        return true;
	    } catch (Exception e) {
	    	System.err.println("Arquivo de Configuração não encontrado! Um novo arquivo será gerado ...");
	    	this.salvarPreferencias();
	    	return false;
	    }	
	}
	
	public String getUltimoLogin() {
		return ultimoLogin;
	}
	public void setUltimoLogin(String ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}
	public String getNomeTema() {
		return nomeTema;
	}
	public void setNomeTema(String nomeTema) {
		this.nomeTema = nomeTema;
	}
	public String getEnderecoProxy() {
		return enderecoProxy;
	}
	public void setEnderecoProxy(String enderecoProxy) {
		this.enderecoProxy = enderecoProxy;
	}
	public String getPortaProxy() {
		return portaProxy;
	}
	public void setPortaProxy(String portaProxy) {
		this.portaProxy = portaProxy;
	}
	public String getLoginProxy() {
		return loginProxy;
	}
	public void setLoginProxy(String loginProxy) {
		this.loginProxy = loginProxy;
	}
	public String getSenhaProxy() {
		return senhaProxy;
	}
	public void setSenhaProxy(String senhaProxy) {
		this.senhaProxy = senhaProxy;
	}

	public boolean isUsarProxy() {
		return usarProxy;
	}

	public void setUsarProxy(boolean usarProxy) {
		this.usarProxy = usarProxy;
	}

	public String getConexaoBancoDados() {
		return conexaoBancoDados;
	}

	public void setConexaoBancoDados(String conexaoBancoDados) {
		this.conexaoBancoDados = conexaoBancoDados;
	}
	
	
}
