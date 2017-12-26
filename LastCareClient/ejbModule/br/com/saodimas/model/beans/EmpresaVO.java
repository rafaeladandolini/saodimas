package br.com.saodimas.model.beans;

import java.io.Serializable;

import br.com.saodimas.model.beans.EmpresaVO;


public class EmpresaVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TABELA = "EMPRESA";
	public static final String ID_EMPRESA = "ID_EMPRESA";
	public static final String DOC_CNPJ = "DOC_CNPJ";
	public static final String RAZAO_SOCIAL = "RAZAO_SOCIAL";
	public static final String STATUS = "STATUS";
	
	private Integer id;
	private String docCNPJ;
	private String razaoSocial;
	private String status;
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocCNPJ() {
		return docCNPJ;
	}

	public void setDocCNPJ(String docCNPJ) {
		this.docCNPJ = docCNPJ;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	@Override
	public String toString() {
		return this.getRazaoSocial();
	}
	
	@Override
	
	public boolean equals(Object empresa) {
		if(empresa == null) return false;
		return ((EmpresaVO)empresa).getId() == this.getId() || ((EmpresaVO)empresa).getDocCNPJ().compareTo(this.getDocCNPJ()) == 0; 
	}
}
