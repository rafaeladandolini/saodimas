package br.com.saodimas.relatorio;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.view.JasperViewer;

public class GeradorRelatorio {

	private JRFileVirtualizer fileVirtualizer;
	
	public GeradorRelatorio(){
		fileVirtualizer = new JRFileVirtualizer(100, "c:\\Temp");
	}
	
	
	public void gerar(String nomeRelatorio, List listaObjetos) {

		
		// Stream com o .jasper
		InputStream relJasper = getClass().getResourceAsStream(nomeRelatorio);

		// O datasource
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaObjetos);

		// Parametros do relatorios
		@SuppressWarnings("rawtypes")
		Map parametros = new HashMap();
		parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);

		JasperPrint impressao = null;
		JasperViewer viewer = null;
		try {
			impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
			viewer = new JasperViewer(impressao, false);
			viewer.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório, contate o administrador do sistema: "+e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE );
		}finally
		{
			
		}


	}
	
	public void gerarComParametro(String nomeRelatorio, List listaObjetos, String parametro, String valor, String parametro2, String valor2) {

		
		// Stream com o .jasper
		InputStream relJasper = getClass().getResourceAsStream(nomeRelatorio);

		// O datasource
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaObjetos);

		// Parametros do relatorios
		@SuppressWarnings("rawtypes")
		Map parametros = new HashMap();
		parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
		if(parametro != null)
			parametros.put(parametro, valor);
		if(parametro2 != null)
			parametros.put(parametro2, valor2);

		JasperPrint impressao = null;
		try {
			impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
			JasperViewer viewer = new JasperViewer(impressao, false);
			viewer.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório, contate o administrador do sistema: "+e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE );
		}


	}
	
	
	
	@SuppressWarnings("unchecked")
	public void gerarComSubRelatorioDependentes(String nomeRelatorio, List<ApoliceVO> listaObjetos) {

		
		// Stream com o .jasper
		InputStream relJasper = getClass().getResourceAsStream(nomeRelatorio);

		// O datasource
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaObjetos);

		// Parametros do relatorios
		Map parametros = new HashMap();
		parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
		
		ApoliceVO apolice = listaObjetos != null? listaObjetos.get(0) : null;
		
		if(apolice!= null)
		{
			
			List<AssociadoVO> set = apolice.getDependentes();	
			Collections.sort(set);
			parametros.put("COLECAO_DEPENDENTES", set);
			
		}
			
		
		JasperPrint impressao = null;
		try {
			impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
			JasperViewer viewer = new JasperViewer(impressao, false);
			viewer.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório, contate o administrador do sistema: "+e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE );
		}


	}
	
	
	@SuppressWarnings("unchecked")
	public void gerarComSubRelatorioObito(String nomeRelatorio, List<ObitoVO> listaObjetos) {

		
		// Stream com o .jasper
		InputStream relJasper = getClass().getResourceAsStream(nomeRelatorio);

		// O datasource
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaObjetos);

		// Parametros do relatorios
		Map parametros = new HashMap();
		parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
		
		ObitoVO obito = listaObjetos != null? listaObjetos.get(0) : null;
		
		if(obito!= null)
		{
			List<ProdutoObitoVO> list = obito.getProdutos(); 
			parametros.put("COLECAO_PRODUTOS", list);
			
			List<ServicoObitoVO> listS = obito.getServicos(); 
			parametros.put("COLECAO_SERVICOS", listS);
		}
			
		
		JasperPrint impressao = null;
		try {
			impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
			JasperViewer viewer = new JasperViewer(impressao, false);
			viewer.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório, contate o administrador do sistema: "+e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE );
		}


	}
	
	@SuppressWarnings("unchecked")
	public void gerarComSubRelatorioFinanceiro(String nomeRelatorio, List<ApoliceVO> listaObjetos) {
		
		// Stream com o .jasper
		InputStream relJasper = getClass().getResourceAsStream(nomeRelatorio);

		// O datasource
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaObjetos);

		// Parametros do relatorios
		Map parametros = new HashMap();
		parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
		
		ApoliceVO apolice = listaObjetos != null? listaObjetos.get(0) : null;
		
		if(apolice!= null)
		{
			List<FaturaVO> l = apolice.getFaturas();
			Collections.sort(l);
			parametros.put("COLECAO_FATURAS", l);
		}
			
		
		JasperPrint impressao = null;
		try {
			impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
			JasperViewer viewer = new JasperViewer(impressao, false);
			viewer.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório, contate o administrador do sistema: "+e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE );
		}


	}
	
	@SuppressWarnings("unchecked")
	public void gerarCarteirinhasDependentes(String nomeRelatorio, List<ApoliceVO> listaObjetos) {

		
		// Stream com o .jasper
		InputStream relJasper = getClass().getResourceAsStream(nomeRelatorio);

		// O datasource
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaObjetos);

		// Parametros do relatorios
		Map parametros = new HashMap();
		parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
		
		ApoliceVO apolice = listaObjetos != null? listaObjetos.get(0) : null;
		
		if(apolice!= null)
		{
			List<AssociadoVO> l = apolice.getDependentes();
			Collections.sort(l);
			String dep = "";
			for (AssociadoVO associadoVO : l) {
				if(associadoVO.getObito() == null)
					dep += associadoVO.getRelacao().getDescricao()+" : "+ associadoVO.getNome() + "\n";
			}
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR,c.get(Calendar.YEAR) + 2);
								
			parametros.put("VALIDADE", (c.get(Calendar.MONTH) + 1 ) + "/" + c.get(Calendar.YEAR));
			parametros.put("DEPENDENTES", dep);
		}
			
		
		JasperPrint impressao = null;
		try {
			impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
			JasperViewer viewer = new JasperViewer(impressao, false);
			viewer.setVisible(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório, contate o administrador do sistema: "+e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE );
		}


	}
	
	public void gerarCarteirinhasDependentes(String nomeRelatorio, List<AssociadoVO> listaObjetos, ApoliceVO apolice) {

		
		// Stream com o .jasper
		InputStream relJasper = getClass().getResourceAsStream(nomeRelatorio);

		// O datasource
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaObjetos);

		// Parametros do relatorios
		Map parametros = new HashMap();
		parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
		
		parametros.put("NUMERO_CONTRATO", apolice.getNumeroContrato());
		parametros.put("PLANO", apolice.getPlano().getDescricao());
		parametros.put("CIDADE", apolice.getCidade().getNome());
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR,c.get(Calendar.YEAR) + 2);
		
				
		parametros.put("VALIDADE", (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
				
		
		if(apolice.getEmpresa() != null)
			parametros.put("EMPRESA", apolice.getEmpresa().getRazaoSocial());
		
		JasperPrint impressao = null;
		try {
			impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
			JasperViewer viewer = new JasperViewer(impressao, false);
			viewer.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório, contate o administrador do sistema: "+e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE );
		}


	}
	
	
	public void gerarTermoCarteinha(String nomeRelatorio, ApoliceVO apolice,  List<AssociadoVO> listaNomes) {

		// Stream com o .jasper
				InputStream relJasper = getClass().getResourceAsStream(nomeRelatorio);

				// O datasource
				List<ApoliceVO> listA = new ArrayList<ApoliceVO>();
				listA.add(apolice);
				JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listA);

				// Parametros do relatorios
				Map parametros = new HashMap();
				parametros.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
				
				Collections.sort(listaNomes);
				parametros.put("COLECAO_DEPENDENTES", listaNomes);
					
						
				JasperPrint impressao = null;
				try {
					impressao = JasperFillManager.fillReport(relJasper, parametros, ds);
					JasperViewer viewer = new JasperViewer(impressao, false);
					viewer.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório, contate o administrador do sistema: "+e.getMessage(), "erro", JOptionPane.ERROR_MESSAGE );
				}


	}


}
