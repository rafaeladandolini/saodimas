package br.com.saodimas.view.components.panel.financeiro.compra;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.CompraVO;
import br.com.saodimas.model.beans.FornecedorVO;
import br.com.saodimas.model.beans.ProdutoCompraVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.DataTextField;

@SuppressWarnings("serial")
public class CompraPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";

	private CompraVO compraVo;
	private BarraNotificacao barNotificacao;
		
	private DataTextField dataCompra;
	private JTextField txtDescricao;
	private JTextField txtVendedor;
	private JTextField txtObservacao;
	private JComboBox cbFornecedor;
	
	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbDataCompra;
	private JLabel lbDescricao;
	private JLabel lbVendedor;
	private JLabel lbObservacao;
	private JLabel lbFornecedor;
		
		
	private JTabbedPane tabbedPanel;
	private CompraProdutoPanel panelProdutos;

	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(200,22);
	
	
	public CompraPanel() {
		initialize();
		configure();
	}

		
	public CompraVO getCompraVO() {
		return compraVo;
	}
	
	public void adicionarProduto(ProdutoCompraVO prooduto)
	{
		panelProdutos.adicionaProduto(prooduto);
		
	}

	public void setCompra(CompraVO compra) {
		this.limparCampos();
		this.compraVo = compra;
		this.carregarEmpresas();
		
		if(compra != null){
			dataCompra.setText(formataData(compra.getDataCompra()));
			txtDescricao.setText(compra.getDescricao());
			txtObservacao.setText(compra.getObservacao());
			txtVendedor.setText(compra.getVendedor());
			cbFornecedor.setSelectedItem(compra.getFornecedor());
			panelProdutos.carregarProdutos(compra.getProdutos());
		}else{
			panelProdutos.carregarProdutos(null);
		}
	}

	public void carregarEmpresas()
	{
		List<FornecedorVO> list = Controller.getInstance().getEmpresaCompraAll();
		cbFornecedor.removeAllItems();
		for (int i=0; i< list.size(); i++) {
			cbFornecedor.addItem(list.get(i));
		}
		cbFornecedor.repaint();
	}
	
	public void limparCampos() {
	
		dataCompra.setText("");
		txtDescricao.setText("");
		txtObservacao.setText("");
		txtVendedor.setText("");
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, BarraNotificacao.DEFAULT);
	}
	
	
	public void focoPadrao(){
		dataCompra.requestFocus();
	}
	
	private void initialize() {
		
		
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		tabbedPanel = new JTabbedPane();
		panelProdutos = new CompraProdutoPanel();
		
		lbDataCompra = new JLabel("Data Compra *: ", JLabel.RIGHT);
		lbDataCompra.setPreferredSize(DLABEL);
		lbDataCompra.setMinimumSize(DLABEL);
		
		dataCompra = new DataTextField();
		dataCompra.setPreferredSize(DFIELDM);
		dataCompra.setMinimumSize(DFIELDM);
		
		lbFornecedor = new JLabel("Empresa: ", JLabel.RIGHT);
		lbFornecedor.setPreferredSize(DLABEL);
		lbFornecedor.setMinimumSize(DLABEL);
		
		cbFornecedor = new JComboBox();
		
		lbDescricao = new JLabel("Descrição *: ", JLabel.RIGHT);
		lbDescricao.setPreferredSize(DLABEL);
		lbDescricao.setMinimumSize(DLABEL);
		
		CustomDocument desDoc = new CustomDocument(150);
		txtDescricao = new JTextField();
		txtDescricao.setDocument(desDoc);
		txtDescricao.setPreferredSize(DFIELDM);
		
		lbVendedor = new JLabel("Vendedor: ", JLabel.RIGHT);
		lbVendedor.setPreferredSize(DLABEL);
		lbVendedor.setMinimumSize(DLABEL);
		
		CustomDocument venDoc = new CustomDocument(100);
		txtVendedor = new JTextField();
		txtVendedor.setDocument(venDoc);
		txtVendedor.setPreferredSize(DFIELDM);
		
		lbObservacao = new JLabel("Observação: ", JLabel.RIGHT);
		lbObservacao.setPreferredSize(DLABEL);
		lbObservacao.setMinimumSize(DLABEL);
		
		CustomDocument obsDoc = new CustomDocument(255);
		txtObservacao = new JTextField();
		txtObservacao.setDocument(obsDoc);
		txtObservacao.setPreferredSize(DFIELDM);

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		
					
	}
	
	private void configure() {
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel dadosCompraPAnel = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;

		c.gridy = 0; dadosCompraPAnel.add(lbDataCompra,c);
		c.gridy = 1; dadosCompraPAnel.add(lbFornecedor, c);
		c.gridy = 2; dadosCompraPAnel.add(lbDescricao, c);
		c.gridy = 3; dadosCompraPAnel.add(lbVendedor, c);
		c.gridy = 4; dadosCompraPAnel.add(lbObservacao, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		c.gridy = 0; dadosCompraPAnel.add(dataCompra, c);
		c.gridy = 1; dadosCompraPAnel.add(cbFornecedor, c);
		c.gridy = 2; dadosCompraPAnel.add(txtDescricao, c);
		c.gridy = 3; dadosCompraPAnel.add(txtVendedor, c);
		c.gridy = 4; dadosCompraPAnel.add(txtObservacao, c);
			
		
		dadosCompraPAnel.setBorder(BorderFactory.createTitledBorder("Compra"));
		adicionarAtalhosComandos(dadosCompraPAnel);
		
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		tabbedPanel.addTab("Dados da Compra", new ImageIcon("imagens/obitos.gif"), dadosCompraPAnel);
		tabbedPanel.addTab("Produtos", new ImageIcon("imagens/obitos.gif"), panelProdutos);
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.add(barNotificacao, BorderLayout.NORTH);
		panelPrincipal.add(tabbedPanel, BorderLayout.CENTER);
		panelPrincipal.add(controle, BorderLayout.SOUTH);
			
		setLayout(new BorderLayout());
		add(panelPrincipal, BorderLayout.CENTER);
		adicionarAtalhosComandos(this);
	}
	
	private void adicionarAtalhosComandos(JPanel panel){
		for (Component c : panel.getComponents()) {
			c.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btCancelar.doClick();
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btSalvar.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof CompraMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	

		
	private class EventoBotaoControle implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			
			Component c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				try{
					((CompraMainPanel)c).getIFrameCompra().setVisible(false);
				}
				catch(ClassCastException cex){
					cex.printStackTrace();
				}
			}else
			if (e.getSource() == btSalvar)
			{
				salvarCompra();	
			} 
		}
	}
	
	@SuppressWarnings({ "static-access"})
	private void salvarCompra()
	{
		try{
			this.validaCampos();
			Component c = getPainelPrincipal();
			CompraVO novaCompra = new CompraVO();
		
		
			novaCompra.setDataCompra(formataData(dataCompra.getText()));
			novaCompra.setDescricao(txtDescricao.getText());
			novaCompra.setEmpresa((FornecedorVO)cbFornecedor.getSelectedItem());
			novaCompra.setObservacao(txtObservacao.getText());
			novaCompra.setVendedor(txtVendedor.getText());
			novaCompra.setProdutos(panelProdutos.getAllProdutos(novaCompra));
			
			
			
			if (compraVo != null) {
				novaCompra.setId(compraVo.getId());
				Controller.getInstance().deleteProdutosCompra(panelProdutos.getProdutosCompraRemover());
				Controller.getInstance().updateCompra(novaCompra);
				((CompraMainPanel) c).mostrarMensagem(
						"Alterações efetuadas com sucesso.",
						barNotificacao.INFO);
			} else {
				Controller.getInstance().insertCompra(novaCompra);
				((CompraMainPanel) c).mostrarMensagem(
						"Cadastro efetuado com sucesso.", barNotificacao.INFO);
			}

			((CompraMainPanel) c).carregarCompras();
			((CompraMainPanel) c).getIFrameCompra().setVisible(false);

		} catch (MensagemSaoDimasException ex) {
			barNotificacao
					.mostrarMensagem(ex.getMessage(), barNotificacao.ERRO);
			ex.printStackTrace();
		} catch (ParseException e) {
			barNotificacao
			.mostrarMensagem(e.getMessage(), barNotificacao.ERRO);
			e.printStackTrace();
		}
	}
	
	private String formataData(Date date)
	{
		SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
		return simpledataformat.format(date);
		
	}
	
	private Date formataData(String date) throws ParseException
	{
		SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
		return simpledataformat.parse(date);
		
	}
	
	
	private void validaCampos()throws MensagemSaoDimasException
	{
		if(dataCompra.getText().trim().equals(""))
		{
			throw new MensagemSaoDimasException("Campo DATA COMPRA é obrigatório...");
		}else if(txtDescricao.getText().trim().equals(""))
		{
			throw new MensagemSaoDimasException("Campo DESCRICAO é obrigatório...");
		}else if(!dataCompra.getText().trim().equals(""))
		{
			try {
				formataData(dataCompra.getText().trim());
			} catch (ParseException e) {
				throw new MensagemSaoDimasException("Campo DATA COMPRA não é valido...");
			}
		}
			
	}
	
	
}	