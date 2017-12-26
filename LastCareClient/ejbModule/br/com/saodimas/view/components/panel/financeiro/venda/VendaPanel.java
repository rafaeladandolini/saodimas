package br.com.saodimas.view.components.panel.financeiro.venda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.beans.FormaPagamentoVO;
import br.com.saodimas.model.beans.ParcelaVO;
import br.com.saodimas.model.beans.ParcelamentoVO;
import br.com.saodimas.model.beans.ProdutoVendaVO;
import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.financeiro.cliente.editar.ClienteEditarMainPanel;
import br.com.saodimas.view.components.panel.financeiro.venda.produtoServico.VendaProdutoPanel;
import br.com.saodimas.view.components.panel.financeiro.venda.produtoServico.VendaServicoPanel;
import br.com.saodimas.view.components.table.ParcelaTable;
import br.com.saodimas.view.components.table.model.ParcelaTableModel;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.components.text.MoedaTextField;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class VendaPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";

	private VendaVO vendaVO;
	private ClienteVO cliente;
	private BarraNotificacao barNotificacao;
	
	private JLabel lbDataVenda;
	private JLabel lbTipoPagamento;
	private JLabel lbParcelamento;
	private JLabel lbValor;
	private JLabel lbValorDesconto;
	private JLabel lbValorAcrescimo;
	private JLabel lbvalorTotal;
	private JLabel lbObservacao;

	private JButton btCancelar;
	private JButton btSalvar;
	private JButton btFinalizarVenda;

	private DataTextField dataVenda;
	private JComboBox cbFormaPagamento;
	private JComboBox cbParcelamento;;
	private MoedaTextField txtValor;
	private MoedaTextField txtValorDesconto;
	private MoedaTextField txtValorAcrescimo;
	private MoedaTextField txtValorTotal;
	private JTextField txtaObservacao;

	private VendaProdutoPanel produtosPanel;
	private VendaServicoPanel servicosPanel;
	
	private ParcelaTable tbParcelas;
	
	private static final Dimension DLABEL = new Dimension(120, 22);
	private static final Dimension DFIELDM = new Dimension(115, 22);

		
	public VendaPanel() {
		initialize();
		configure();
	}

	public VendaVO getVendaVO() {
		return vendaVO;
	}

	public void setCliente(ClienteVO cliente){
		this.cliente = cliente;
	}
	
	public void adicionaProduto(ProdutoVendaVO p) {
		produtosPanel.adicionaProduto(p);
		this.calcularTotalCompra();
	}

	public void adicionaservico(ServicoVendaVO p) {
		servicosPanel.adicionaServico(p);
		this.calcularTotalCompra();
	}


	public void setVenda(VendaVO venda) {
		this.limparCampos();
		this.vendaVO = venda;
	
		dataVenda.setText(formataData(new Date()));
		this.carregarFormasPagamentos();
		this.carregarParcelamentoByFormaPagamento(null);

		if (venda != null) {
			dataVenda.setText(formataData(venda.getDataVenda()));
			txtaObservacao.setText(venda.getObservacao());
			txtValorAcrescimo.setValor(venda.getAcrescimo());
			txtValorDesconto.setValor(venda.getDesconto());
			produtosPanel.carregarProdutos(venda.getProdutos());
			servicosPanel.carregarServicos(venda.getServicos());
			cbFormaPagamento.setSelectedItem(venda.getFormaPagamento());
			this.carregarParcelamentoByFormaPagamento(venda.getFormaPagamento());
			cbParcelamento.setSelectedItem(venda.getParcelamento());
			this.carregarParcelas();
			this.calcularTotalCompra();
			
		} else {
			
			produtosPanel.carregarProdutos(null);
			servicosPanel.carregarServicos(null);
		}
	}


	public void limparCampos() {

		dataVenda.setText("");
		txtaObservacao.setText("");
		txtValorAcrescimo.setValor(0d);
		txtValorDesconto.setValor(0d);
		txtValor.setValor(0d);
		txtValorTotal.setValor(0d);
		produtosPanel.carregarProdutos(null);
		servicosPanel.carregarServicos(null);
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,	BarraNotificacao.DEFAULT);
	}

	public void focoPadrao() {
		dataVenda.requestFocus();
	}

	private void initialize() {

		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
	
		lbDataVenda = new JLabel("Data Venda *: ", JLabel.RIGHT);
		lbDataVenda.setPreferredSize(DLABEL);
		lbDataVenda.setMinimumSize(DLABEL);

		dataVenda = new DataTextField();
		dataVenda.setPreferredSize(DFIELDM);
		dataVenda.setMinimumSize(DFIELDM);
		
		lbTipoPagamento = new JLabel("Tipo Pagamento: ", JLabel.RIGHT);
		lbTipoPagamento.setPreferredSize(DLABEL);
		lbTipoPagamento.setMinimumSize(DLABEL);

		cbFormaPagamento = new JComboBox<FormaPagamentoVO>();
		cbFormaPagamento.addItemListener(new EventoComboBox());
		
		lbParcelamento = new JLabel("Parcelamento: ", JLabel.RIGHT);
		lbParcelamento.setPreferredSize(DLABEL);
		lbParcelamento.setMinimumSize(DLABEL);

		cbParcelamento = new JComboBox<ParcelamentoVO>();
		cbParcelamento.addItemListener(new EventoComboBox());

		lbValor = new JLabel("Valor:  ", JLabel.RIGHT);
		lbValor.setPreferredSize(DLABEL);
		lbValor.setMinimumSize(DLABEL);

		txtValor = new MoedaTextField();
		txtValor.setPreferredSize(DFIELDM);
		txtValor.setMinimumSize(DFIELDM);
				
		lbValorDesconto = new JLabel("Desconto:  ", JLabel.RIGHT);
		lbValorDesconto.setPreferredSize(DLABEL);
		lbValorDesconto.setMinimumSize(DLABEL);

		txtValorDesconto = new MoedaTextField();
		txtValorDesconto.setPreferredSize(DFIELDM);
		txtValorDesconto.setMinimumSize(DFIELDM);
		txtValorDesconto.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				calcularTotalCompra();
				super.keyReleased(e);
			}
		});
				
		lbValorAcrescimo = new JLabel("Acrescimo:  ", JLabel.RIGHT);
		lbValorAcrescimo.setPreferredSize(DLABEL);
		lbValorAcrescimo.setMinimumSize(DLABEL);

		txtValorAcrescimo = new MoedaTextField();
		txtValorAcrescimo.setPreferredSize(DFIELDM);
		txtValorAcrescimo.setMinimumSize(DFIELDM);
		txtValorAcrescimo.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				calcularTotalCompra();
				super.keyReleased(e);
			}
		});
		
		lbvalorTotal = new JLabel("Total:  ", JLabel.RIGHT);
		lbvalorTotal.setPreferredSize(DLABEL);
		lbvalorTotal.setMinimumSize(DLABEL);

		txtValorTotal = new MoedaTextField();
		txtValorTotal.setPreferredSize(DFIELDM);
		txtValorTotal.setMinimumSize(DFIELDM);
		txtValorTotal.setEditable(false);
		
		lbObservacao = new JLabel("Observação: ", JLabel.RIGHT);
		lbObservacao.setPreferredSize(DLABEL);
		lbObservacao.setMinimumSize(DLABEL);

		CustomDocument obsDoc = new CustomDocument(200);
		txtaObservacao = new JTextField();
		txtaObservacao.setDocument(obsDoc);
	
		btCancelar = new JButton("Cancelar",new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		
		btFinalizarVenda = new JButton("Finzalizar", new ImageIcon("imagens/save.gif"));
		btFinalizarVenda.addActionListener(new EventoBotaoControle());
		btFinalizarVenda.setHorizontalAlignment(JButton.LEFT);

		produtosPanel = new VendaProdutoPanel();
		servicosPanel = new VendaServicoPanel();
		
		tbParcelas = new ParcelaTable();
	}

	private void configure() {

		GridBagConstraints c = new GridBagConstraints();

		JPanel panelPricipal = new JPanel(new GridBagLayout());
		
		JPanel panelDadosVenda = new JPanel(new GridBagLayout());
		panelDadosVenda.setBorder(BorderFactory.createTitledBorder("Venda"));
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.gridwidth = 1;
		c.gridy = 0;
		c.gridx = 0;    panelDadosVenda.add(lbDataVenda, c);
		c.gridx = 1;	panelDadosVenda.add(dataVenda, c);
		c.gridx = 2;	panelDadosVenda.add(lbObservacao, c);
		c.gridx = 3;	panelDadosVenda.add(txtaObservacao, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.gridwidth = 0;
		c.gridx = 0;
		c.gridy = 0;	panelPricipal.add(panelDadosVenda, c);
		c.gridwidth = 1;
		c.gridy = 1;	panelPricipal.add(produtosPanel, c);
		c.gridx = 1;	
		c.gridy = 1;    panelPricipal.add(servicosPanel, c);
		
		
		JPanel panelFechamentoDadosVenda = new JPanel(new GridBagLayout());
		panelFechamentoDadosVenda.setBorder(BorderFactory.createTitledBorder("Fechamento Venda"));
		
		c.insets = new Insets(1, 1, 2, 1);
		c.gridx = 0;
		c.gridy = 0;	panelFechamentoDadosVenda.add(lbTipoPagamento, c);
		c.gridy = 1;	panelFechamentoDadosVenda.add(lbParcelamento, c);
		c.gridy = 2;	panelFechamentoDadosVenda.add(lbValor, c);
		
		c.gridx = 2;
		c.gridy = 0;	panelFechamentoDadosVenda.add(lbValorDesconto, c);
		c.gridy = 1;	panelFechamentoDadosVenda.add(lbValorAcrescimo, c);
		c.gridy = 2;	panelFechamentoDadosVenda.add(lbvalorTotal, c);

		c.gridx = 1;
		c.gridy = 0;	panelFechamentoDadosVenda.add(cbFormaPagamento, c);
		c.gridy = 1;	panelFechamentoDadosVenda.add(cbParcelamento, c);
		c.gridy = 2;	panelFechamentoDadosVenda.add(txtValor, c);
		
		c.gridx = 3;
		c.gridy = 0;	panelFechamentoDadosVenda.add(txtValorDesconto, c);
		c.gridy = 1;	panelFechamentoDadosVenda.add(txtValorAcrescimo, c);
		c.gridy = 2;	panelFechamentoDadosVenda.add(txtValorTotal, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.gridwidth = 0;
		c.gridx = 0;
		c.gridy = 3;	panelPricipal.add(panelFechamentoDadosVenda, c);
		

		JScrollPane prodPanel = new JScrollPane(tbParcelas);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		prodPanel.setPreferredSize(new Dimension(200, 150));
		
		c.fill = GridBagConstraints.BOTH;		
		c.gridy = 4;	panelPricipal.add(prodPanel, c);
		
		
		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER));
		formPrincipal.add(panelPricipal);
				
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btFinalizarVenda);
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		
		setLayout(new BorderLayout());
		add(barNotificacao, BorderLayout.NORTH);
		add(formPrincipal, BorderLayout.CENTER);
		add(controle, BorderLayout.SOUTH);
				
	}

	public void carregarFormasPagamentos()
	{
		List<FormaPagamentoVO> list = Controller.getInstance().getAllFormaPagamento();
		cbFormaPagamento.removeAllItems();
		cbFormaPagamento.addItem("Selecione..");
		for(FormaPagamentoVO vo : list){
			cbFormaPagamento.addItem(vo);
		}
	}
	
	public void carregarParcelamentoByFormaPagamento(FormaPagamentoVO formaPag)
	{
		cbParcelamento.removeAllItems();
		cbParcelamento.addItem("Selecione..");
		
		if (formaPag != null && formaPag instanceof FormaPagamentoVO) {

			List<ParcelamentoVO> list = Controller.getInstance().getAllParcelamentoByFormaPagamento(formaPag);

			for (ParcelamentoVO vo : list) {
				cbParcelamento.addItem(vo);
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private void salvarVenda() {

		try {
			this.validaCampos();
			Component c = getPanelPrincipal();

			VendaVO novaVenda = new VendaVO();

			novaVenda.setDataVenda(formataData(dataVenda.getText()));
			novaVenda.setColaborador(SaoDimasMain.colaborador);
			novaVenda.setObservacao(txtaObservacao.getText());
			novaVenda.setValor(txtValor.getValor());
			novaVenda.setAcrescimo(txtValorAcrescimo.getValor());
			novaVenda.setDesconto(txtValorDesconto.getValor());
			novaVenda.setFormaPagamento(cbFormaPagamento.getSelectedItem() != null ?(FormaPagamentoVO) cbFormaPagamento.getSelectedItem() : null);
			novaVenda.setParcelamento(cbParcelamento.getSelectedItem() != null ? (ParcelamentoVO) cbParcelamento.getSelectedItem() : null);
			novaVenda.setProdutos(produtosPanel.getAllProdutos(novaVenda));
			novaVenda.setServicos(servicosPanel.getAllServicos(novaVenda));
			novaVenda.setCliente(this.cliente);
			novaVenda.setParcelas(((ParcelaTableModel)tbParcelas.getModel()).getList());
			novaVenda.setStatusVenda(ListasComuns.STATUS_VENDA[0]);

			if (vendaVO != null) {
				novaVenda.setId(vendaVO.getId());
				Controller.getInstance().deleteProdutosVenda(produtosPanel.getProdutosVendaRemover());
				Controller.getInstance().deleteServicosVenda(servicosPanel.getServicosVendaRemover());
				Controller.getInstance().updateVenda(novaVenda);
				((ClienteEditarMainPanel) c).mostrarMensagemVendas("Alterações efetuadas com sucesso.",barNotificacao.INFO);
			} else {
				Controller.getInstance().insertVenda(novaVenda);
				((ClienteEditarMainPanel) c).mostrarMensagemVendas("Cadastro efetuado com sucesso.", barNotificacao.INFO);
			}

			((ClienteEditarMainPanel) c).getVendaClientePanel().carregarVendas();
			((ClienteEditarMainPanel) c).getIframeVenda().setVisible(false);

		} catch (MensagemSaoDimasException ex) {
			barNotificacao.mostrarMensagem(ex.getMessage(), barNotificacao.ERRO);
			ex.printStackTrace();
		} catch (ParseException e) {
			barNotificacao.mostrarMensagem(e.getMessage(), barNotificacao.ERRO);
			e.printStackTrace();
		}
	}

	private String formataData(Date date) {
		SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
		return simpledataformat.format(date);

	}

	private Date formataData(String date) throws ParseException {
		SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
		return simpledataformat.parse(date);

	}

	private void validaCampos() throws MensagemSaoDimasException {
		if (dataVenda.getText().trim().equals("")) {
			throw new MensagemSaoDimasException(
					"Campo DATA COMPRA é obrigatório...");
		}else if (!dataVenda.getText().trim().equals("")) {
			try {
				formataData(dataVenda.getText().trim());
			} catch (ParseException e) {
				throw new MensagemSaoDimasException(
						"Campo DATA COMPRA não é valido...");
			}
		}

	}

	private Component getPanelPrincipal() {
		Component c = getParent();
		while (c != null) {
			if (c instanceof ClienteEditarMainPanel)
				return (ClienteEditarMainPanel) c;
			c = c.getParent();
		}
		return (ClienteEditarMainPanel) c;
	}
	

	
	private class EventoBotaoControle implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			ClienteEditarMainPanel c = (ClienteEditarMainPanel)getPanelPrincipal();
			if (e.getSource() == btCancelar) {
				try {
					((ClienteEditarMainPanel) c).getIframeVenda().setVisible(false);
				} catch (ClassCastException cex) {
					cex.printStackTrace();
				}
			} else if (e.getSource() == btSalvar) {
				salvarVenda();
			}
			
			else if (e.getSource() == btFinalizarVenda) {
				
				if (c.mostraConfirmacao("Confirmação", "Tem certeza que deseja finalizar a venda?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					try {
						Controller.getInstance().finalizarVenda(vendaVO);
					} catch (MensagemSaoDimasException e1) {
						barNotificacao.mostrarMensagem(e1.getMessage(), barNotificacao.ERRO);
						e1.printStackTrace();
					}
				}
			}

		}

	}
	
	public void calcularTotalCompra() {

		List<ProdutoVendaVO> list = produtosPanel.getAllProdutos(vendaVO);
		txtValor.setValor(0.0);
		for (ProdutoVendaVO vo : list) {
			txtValor.setValor(txtValor.getValor()	+ vo.getQuantidade() * vo.getValor());
		}
		
		txtValorTotal.setValor(txtValor.getValor() + txtValorAcrescimo.getValor() - txtValorDesconto.getValor());
		
		List<ServicoVendaVO> listSer = servicosPanel.getAllServicos(vendaVO);
		for (ServicoVendaVO vo : listSer) {
			txtValor.setValor(txtValor.getValor()	+ vo.getQuantidade() * vo.getValor());
		}
		
		txtValorTotal.setValor(txtValor.getValor() + txtValorAcrescimo.getValor() - txtValorDesconto.getValor());
		this.gerarParcelas();
	}
	
	private void carregarParcelas(){
			
		ParcelaTableModel model = (ParcelaTableModel)tbParcelas.getModel();
		model.loadData(vendaVO.getParcelas());
	}
	
	private void gerarParcelas(){
		
		if(cbParcelamento.getSelectedItem() instanceof ParcelamentoVO){
			Calendar c = Calendar.getInstance();
			Date dataInicio =  new Date(c.getTimeInMillis());
			ParcelaTableModel model = (ParcelaTableModel)tbParcelas.getModel();
			model.removeAll();
			ParcelamentoVO parcelamento = (ParcelamentoVO)cbParcelamento.getSelectedItem();
			if(parcelamento.getQuantidade() == 0)
			{
				ParcelaVO parcela = new ParcelaVO();
				parcela.setNumeracao("1/1");
				parcela.setDataVencimento(dataInicio);
				parcela.setValor(txtValorTotal.getValor());
				parcela.setVenda(vendaVO);
				model.add(parcela);
			}else{
				for(int i = 1 ; i <= parcelamento.getQuantidade(); i++ ){
					ParcelaVO parcela = new ParcelaVO();
					parcela.setNumeracao(i+"/"+ parcelamento.getQuantidade());
					Date dataVenc = getProximoVencimento(dataInicio);
					parcela.setDataVencimento(dataVenc);
					dataInicio =  dataVenc;
					parcela.setValor(txtValorTotal.getValor() / parcelamento.getQuantidade());
					parcela.setVenda(vendaVO);
					model.add(parcela);
				}
			}
		}
	}
	
		
	private Date getProximoVencimento(Date dataVencimento){
		
		Calendar c = new GregorianCalendar();
		c.setTime(dataVencimento);
		
		int mes = c.get(Calendar.MONTH);
		int dia = c.get(Calendar.DAY_OF_MONTH);
		
		mes = (mes % 12) + 1;
		
		if(dia > 28 && mes == 2)
			c.set(Calendar.DAY_OF_MONTH, 28);
		else if(dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11))
			c.set(Calendar.DAY_OF_MONTH, 30);
		else
			c.set(Calendar.DAY_OF_MONTH, dia);
		
		c.set(Calendar.MONTH,mes);
		
		return new Date(c.getTimeInMillis());
	}

	private class EventoComboBox implements ItemListener
	{
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource() == cbFormaPagamento){
				if(e.getItem() instanceof FormaPagamentoVO){
					FormaPagamentoVO formaPagamento = (FormaPagamentoVO) e.getItem();
					carregarParcelamentoByFormaPagamento(formaPagamento);
				}
			}else if(e.getSource() == cbParcelamento){
				if(txtValorTotal.getText().equals("0,00"))
					barNotificacao.mostrarMensagem("Você deve informar produtos/servicos antes.", barNotificacao.INFO);
				else
					gerarParcelas();
			}
		}
		
	}

}
