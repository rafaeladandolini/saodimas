package br.com.saodimas.view.components.panel.admFuncionarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.model.beans.ParceiroVO;
import br.com.saodimas.relatorio.GeradorRelatorio;
import br.com.saodimas.relatorio.NomeRelatorio;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.spinner.DataSpinner;
import br.com.saodimas.view.components.table.AdminFaturaTable;
import br.com.saodimas.view.components.table.model.AdminFaturaTableModel;

@SuppressWarnings("serial")
public class AdminFaturaGeralMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "AdminFaturasGeral"; 
	public static final String MENSAGEM_PADRAO = "   Resumo das faturas quitadas no período informado";
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	
	private BarraNotificacao barNotificacao;
	private AdminFaturaTable tbFaturas;
	private JToolBar barControle;
	private JLabel labelTotal, labelNomeFunc, labelsDias, labelParceiros, labelDataBaixa;
	private DataSpinner spnDataIni, spnDataFim;
	private DataSpinner spnDataIniBaixa, spnDataFimBaixa;
	private JComboBox comboFuncionarios;
	private JComboBox comboParceiros;
	private JButton btnConsultar, btImprimir;;
	private JPanel panelBusca;
	private JCheckBox checkDataBaixa, checkDataPagamento;
	private String totalImpressao;
	private List<FaturaVO> listImpressao;
	
	public AdminFaturaGeralMainPanel(){
		
		initialize();
		configure();
	}
	/*
	public void carregarFaturasDiaTable() {
		List<FaturaVO> lis = Controller.getInstance().getFaturasDia(); 
		((AdminFaturaTableModel) tbFaturas.getModel())
				.loadData(lis);
		
		Double total = 0.0;
		DecimalFormat df = new DecimalFormat("#,###,###.00");
		df.setCurrency(Currency.getInstance(new Locale("pt","br")));
		for (FaturaVO faturaVO : lis) {
			total += faturaVO.getValor() - faturaVO.getValorDesconto() + faturaVO.getValorMulta() - faturaVO.getValorParceiro();
		}
		String totalCalculado = "R$ " + df.format(total);
		labelTotal.setText("Total:" + totalCalculado); 
	}
	
	public void carregarFaturas() {
		List<FaturaVO> lis = Controller.getInstance().getFaturasDia(); 
		((AdminFaturaTableModel) tbFaturas.getModel())
				.loadData(lis);
		
		Double total = 0.0;
		DecimalFormat df = new DecimalFormat("#,###,###.00");
		df.setCurrency(Currency.getInstance(new Locale("pt","br")));
		for (FaturaVO faturaVO : lis) {
			total += faturaVO.getValor() - faturaVO.getValorDesconto() + faturaVO.getValorMulta() - faturaVO.getValorParceiro();
		}
		String totalCalculado = "R$ " + df.format(total);
		labelTotal.setText("Total:" + totalCalculado); 
	}*/

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			this.carregarFuncionarios();
			this.carregarParceiros();
			//carregarFaturasDiaTable();
		}
	}
	
	
	@Override
	public void setEnabled(boolean enabled) {
		tbFaturas.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	private void initialize() {
		tbFaturas = new AdminFaturaTable();
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		labelTotal = new JLabel("");
		btImprimir = new JButton("Impressão Relatório",new ImageIcon("imagens/imprimir.gif"));
		btImprimir.addActionListener(new EventoBotao());
		btImprimir.setHorizontalAlignment(JButton.LEFT);
		btImprimir.setToolTipText("Imprimir relatório das faturas");
		btImprimir.setEnabled(false);
		
		panelBusca = new JPanel(new GridLayout(3,1));
		
		JPanel panelBuscaSuperior = new JPanel();
		JPanel panelBuscaInferior = new JPanel();
		JPanel panelBuscaDataBaixa = new JPanel();
				
		labelNomeFunc = new JLabel("Funcionário:");
		comboFuncionarios = new JComboBox();
		
		labelParceiros = new JLabel("Parceiro:");
		comboParceiros = new JComboBox();
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -500);
		String dataIni = f.format(new Date(c.getTimeInMillis()));

		c = Calendar.getInstance();
			
		String dataSel = f.format(new Date(c.getTimeInMillis()));

		String dataFin = f.format(new Date(Calendar.getInstance().getTimeInMillis()));
		
		labelsDias = new JLabel("Data de pagamento entre os dias:");
		spnDataIni = new DataSpinner(dataSel, dataIni, dataFin);
		spnDataIni.setPreferredSize(DFIELDM);
		spnDataIni.setMinimumSize(DFIELDM);
		spnDataIni.setEnabled(true);
		
			
		spnDataFim = new DataSpinner(dataSel, dataIni, dataFin);
		spnDataFim.setPreferredSize(DFIELDM);
		spnDataFim.setMinimumSize(DFIELDM);
		spnDataFim.setEnabled(true);
		
	
		
		labelDataBaixa = new JLabel("Data da baixa entre os dias:");
		spnDataIniBaixa = new DataSpinner(dataSel, dataIni, dataFin);
		spnDataIniBaixa.setPreferredSize(DFIELDM);
		spnDataIniBaixa.setMinimumSize(DFIELDM);
		spnDataIniBaixa.setEnabled(false);
			
		spnDataFimBaixa = new DataSpinner(dataSel, dataIni, dataFin);
		spnDataFimBaixa.setPreferredSize(DFIELDM);
		spnDataFimBaixa.setMinimumSize(DFIELDM);
		spnDataFimBaixa.setEnabled(false);
		
		checkDataBaixa = new JCheckBox();
			
		checkDataBaixa.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				if(checkDataBaixa.isSelected())
				{
					spnDataFimBaixa.setEnabled(true);
					spnDataIniBaixa.setEnabled(true);
				}else
				{
					spnDataFimBaixa.setEnabled(false);
					spnDataIniBaixa.setEnabled(false);
					
				}
				
			}
		});
		
		
		checkDataPagamento = new JCheckBox();
		checkDataPagamento.setSelected(true);
		
		checkDataPagamento.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				
				if(checkDataPagamento.isSelected())
				{
					spnDataFim.setEnabled(true);
					spnDataIni.setEnabled(true);
				}else
				{
					spnDataFim.setEnabled(false);
					spnDataIni.setEnabled(false);
					
				}
				
			}
		});
		//this.carregarFuncionarios();
		
		btnConsultar = new JButton("Consultar", new ImageIcon("imagens/submit.gif"));
		btnConsultar.addActionListener(new EventoBotao());
		
		panelBuscaSuperior.add(labelNomeFunc);
		panelBuscaSuperior.add(comboFuncionarios);
		panelBuscaSuperior.add(labelParceiros);
		panelBuscaSuperior.add(comboParceiros);
		
		panelBuscaInferior.add(checkDataPagamento);
		panelBuscaInferior.add(labelsDias);
		panelBuscaInferior.add(spnDataIni);
		panelBuscaInferior.add(spnDataFim);
		
		panelBuscaDataBaixa.add(checkDataBaixa);
		panelBuscaDataBaixa.add(labelDataBaixa);
		panelBuscaDataBaixa.add(spnDataIniBaixa);
		panelBuscaDataBaixa.add(spnDataFimBaixa);
		
		panelBuscaDataBaixa.add(btnConsultar);
		
		panelBusca.add(panelBuscaSuperior);
		panelBusca.add(panelBuscaInferior);
		panelBusca.add(panelBuscaDataBaixa);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(labelTotal);
		barControle.add(btImprimir);
		
	}
	
	private void carregarFuncionarios()
	{
		comboFuncionarios.removeAllItems();
		
		String colTodos = "Todos";
		
		comboFuncionarios.addItem(colTodos);
		
		
		List<ColaboradorVO> listCol = Controller.getInstance().getColaboradorAll();
		for (ColaboradorVO vo : listCol) { 
			comboFuncionarios.addItem(vo);
		}
	}
	
	private void carregarParceiros()
	{
		comboParceiros.removeAllItems();
		
		String colTodos = "Todos";
		String colSemParceiro = "Sem parceiro";
		String colComParceiro = "Só com parceiro";
		comboParceiros.addItem(colTodos);
		comboParceiros.addItem(colSemParceiro);
		comboParceiros.addItem(colComParceiro);
		
		
		List<ParceiroVO> listCol = Controller.getInstance().getParceirosAll();
		for (ParceiroVO vo : listCol) { 
			comboParceiros.addItem(vo);
		}
	}

	private void configure() {
		
		GridBagConstraints c = new GridBagConstraints();
		JPanel infCidade = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infCidade.add(barControle, c);
		
		JScrollPane prodPanel = new JScrollPane(tbFaturas);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infCidade.add(prodPanel, c);
		
		infCidade.setBorder(BorderFactory.createTitledBorder("Listagem das faturas quitadas no período selecionado acima"));
		
		JPanel panelPrincipal = new JPanel(new GridBagLayout()); 
		c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; panelPrincipal.add(barNotificacao, c);

	
		c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; panelPrincipal.add(panelBusca, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2; panelPrincipal.add(infCidade, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);	
	}
	
	private class EventoBotao implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == btnConsultar){
				List<FaturaVO> lis = null; 
				Object objTodos = comboFuncionarios.getSelectedItem(); 
				Object objTodosParceiro = comboParceiros.getSelectedItem(); 
				
				ColaboradorVO colaborador = null;
				ParceiroVO parceiro = null;
				Boolean parceiroTodosNenhum = null;
				
				Date dataInicioPagamento = null;
				Date dataFimPagamento    = null;
				
				Date dataInicioBaixa = null;
				Date dataFimBaixa    = null;
				
				if(checkDataPagamento.isSelected()){
					dataInicioPagamento = (Date)spnDataIni.getValue();
					dataFimPagamento    = (Date)spnDataFim.getValue();
				}
				
				if(checkDataBaixa.isSelected()){
					dataInicioBaixa = (Date)spnDataIniBaixa.getValue();
					dataFimBaixa    = (Date)spnDataFimBaixa.getValue();
				}
				
				if(objTodos instanceof ColaboradorVO)
					colaborador = (ColaboradorVO) objTodos;
				 
				if(objTodosParceiro instanceof ParceiroVO)
					parceiro = (ParceiroVO)objTodosParceiro;
				else if(objTodosParceiro instanceof String)
				{
					if(objTodosParceiro.equals("Só com parceiro"))
						parceiroTodosNenhum = new Boolean(true);
					else if(objTodosParceiro.equals("Sem parceiro"))
						parceiroTodosNenhum = new Boolean(false);
				}
					
					
				
				lis = Controller.getInstance().consultaFaturas(colaborador, parceiro,parceiroTodosNenhum, dataInicioPagamento, dataFimPagamento, dataInicioBaixa, dataFimBaixa);
				((AdminFaturaTableModel) tbFaturas.getModel()).loadData(lis);
				
				Double total = 0.0;
				DecimalFormat df = new DecimalFormat("#,###,###.00");
				df.setCurrency(Currency.getInstance(new Locale("pt","br")));
				for (FaturaVO faturaVO : lis) {
					total += faturaVO.getValor() - faturaVO.getValorDesconto() - faturaVO.getValorParceiro() + faturaVO.getValorMulta();
				}
				String totalCalculado = "R$ " + df.format(total);
				labelTotal.setText("Total:" + totalCalculado); 
				totalImpressao = totalCalculado;
				listImpressao = lis;
				btImprimir.setEnabled(true);
			}else if (e.getSource() == btImprimir)
			{
				new GeradorRelatorio().gerarComParametro(NomeRelatorio.RELATORIO_FATURAS, listImpressao, "TOTAL", totalImpressao, "QTDE", listImpressao.size() + "");
			}
		}
	}
	
}
