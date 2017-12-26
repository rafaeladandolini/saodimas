package br.com.saodimas.view.components.panel.financeiro.cheques;

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
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ChequeVO;
import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.beans.ContaVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.components.text.MoedaTextField;

@SuppressWarnings("serial")
public class ChequePanel extends JPanel {
	
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	
	private ChequeVO cheque;
	private BarraNotificacao barNotificacao;
		
	private JComboBox<ContaVO> cbConta;
	private JTextField txtNumero;
	private MoedaTextField txtValor;
	private DataTextField txtDataEmissao;
	private DataTextField txtDataVencimento;
	private JTextField txtDescricao;
	private JComboBox<ClienteVO> cbCliente;
	
	private JLabel lbConta;
	private JLabel lbNumero;
	private JLabel lbValor;
	private JLabel lbDataEmisao;
	private JLabel lbDtaVencimento;
	private JLabel lbCliente;
	private JLabel lbDescricao;
	
	private JButton btCancelar;
	private JButton btSalvar;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	private static final Dimension DFIELDP = new Dimension(75, 22);
	
	public ChequePanel() {
		initialize();
		configure();
	}

	
	public void setCheque(ChequeVO cheque) {
		this.cheque = cheque;
		this.carregarClientes();
		this.carregarContas();
		if(cheque !=null)
		{
			txtDataEmissao.setText(formataData(cheque.getDataEmissao()));
			txtDataVencimento.setText(formataData(cheque.getDataVencimento()));
			txtDescricao.setText(cheque.getDescricao());
			txtNumero.setText(cheque.getNumero());
			txtValor.setValor((Double)cheque.getValor());
			cbCliente.setSelectedItem(cheque.getCliente());
			cbConta.setSelectedItem(cheque.getConta());
		}else
		{
			limparCampos();
		}
	}
		
	private void carregarContas(){
		List<ContaVO> list = Controller.getInstance().getAllContas();
		cbConta.removeAllItems();
		for(ContaVO c : list)
			cbConta.addItem(c);		
	}
	
	private void carregarClientes(){
		List<ClienteVO> list = Controller.getInstance().getAllClientes();
		cbCliente.removeAllItems();
		for(ClienteVO b : list)
			cbCliente.addItem(b);	
	}
	
	@SuppressWarnings("static-access")
	public void limparCampos(){
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,	barNotificacao.DICA);
		txtDescricao.setText("");
		txtNumero.setText("");
		txtValor.setValor(0d);
		
	}
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
	
		lbCliente = new JLabel("Cliente:* ", JLabel.RIGHT);
		lbCliente.setPreferredSize(DLABEL);
		lbCliente.setMinimumSize(DLABEL);
		
		cbCliente = new JComboBox<ClienteVO>();
		cbCliente.setPreferredSize(DFIELDM);
		cbCliente.setMinimumSize(DFIELDM);
		
		lbConta = new JLabel("Conta:* ", JLabel.RIGHT);
		lbConta.setPreferredSize(DLABEL);
		lbConta.setMinimumSize(DLABEL);
		
		cbConta = new JComboBox<ContaVO>();
		cbConta.setPreferredSize(DFIELDM);
		cbConta.setMinimumSize(DFIELDM);
		
		lbNumero = new JLabel("Numeração: *", JLabel.RIGHT);
		lbNumero.setPreferredSize(DLABEL);
		lbNumero.setMinimumSize(DLABEL);
		
		CustomDocument nDoc = new CustomDocument(10);
		txtNumero = new JTextField();
		txtNumero.setDocument(nDoc);
		txtNumero.setPreferredSize(DFIELDM);
		
		lbValor = new JLabel("Valor: *", JLabel.RIGHT);
		lbValor.setPreferredSize(DLABEL);
		lbValor.setMinimumSize(DLABEL);
		
		txtValor = new MoedaTextField();
		txtValor.setPreferredSize(DFIELDP);
		txtValor.setMinimumSize(DFIELDP);
		
		lbDataEmisao = new JLabel("Data Emissão: *", JLabel.RIGHT);
		lbDataEmisao.setPreferredSize(DLABEL);
		lbDataEmisao.setMinimumSize(DLABEL);
		
		String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		txtDataEmissao = new DataTextField();
		txtDataEmissao.setText(today);
		txtDataEmissao.setPreferredSize(DFIELDM);
		txtDataEmissao.setMinimumSize(DFIELDM);
		txtDataEmissao.setHorizontalAlignment(JTextField.RIGHT);
				
		lbDtaVencimento = new JLabel("Data Vencimento: *", JLabel.RIGHT);
		lbDtaVencimento.setPreferredSize(DLABEL);
		lbDtaVencimento.setMinimumSize(DLABEL);
		
		txtDataVencimento = new DataTextField();
		txtDataVencimento.setText(today);
		txtDataVencimento.setPreferredSize(DFIELDM);
		txtDataVencimento.setMinimumSize(DFIELDM);
		txtDataVencimento.setHorizontalAlignment(JTextField.RIGHT);	
		
		lbDescricao = new JLabel("Descrição: *", JLabel.RIGHT);
		lbDescricao.setPreferredSize(DLABEL);
		lbDescricao.setMinimumSize(DLABEL);
		
		CustomDocument nDesc = new CustomDocument(60);
		txtDescricao = new JTextField();
		txtDescricao.setDocument(nDesc);
		txtDescricao.setPreferredSize(DFIELDM);
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infCidade = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infCidade.add(lbCliente, c);
		c.gridy = 1; infCidade.add(lbConta, c);
		c.gridy = 2; infCidade.add(lbDataEmisao, c);
		c.gridy = 3; infCidade.add(lbValor, c);
		c.gridy = 4; infCidade.add(lbNumero, c);
		c.gridy = 5; infCidade.add(lbDtaVencimento, c);
		c.gridy = 6; infCidade.add(lbDescricao, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		//c.weightx = 1;
		//c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infCidade.add(cbCliente, c);
		c.gridy = 1; infCidade.add(cbConta, c);
		c.gridy = 2; infCidade.add(txtDataEmissao, c);
		c.gridy = 3; infCidade.add(txtValor, c);
		c.gridy = 4; infCidade.add(txtNumero, c);
		c.gridy = 5; infCidade.add(txtDataVencimento, c);
		c.gridy = 6; infCidade.add(txtDescricao, c);
		
		infCidade.setBorder(BorderFactory.createTitledBorder("Cheque"));
		adicionarAtalhosComandos(infCidade);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		JPanel panelCidade = new JPanel(new BorderLayout()); 
		panelCidade.add(infCidade, BorderLayout.CENTER);
		panelCidade.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelCidade);
		
		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(panelCidade, BorderLayout.CENTER);
		panelColaborador.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelColaborador);
		
		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		formPrincipal.add(panelColaborador);
		adicionarAtalhosComandos(formPrincipal);
		
		setLayout(new BorderLayout());
		add(formPrincipal, BorderLayout.CENTER);
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
			if (c instanceof ChequeMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
		
	private class EventoBotaoControle implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e){
			
			if (e.getSource() == btCancelar){
				Component c = getPainelPrincipal();
				((ChequeMainPanel)c).getIframeCheque().setVisible(false);
			
			} else
			
			if (e.getSource() == btSalvar)
			{
				try {
					validarCampos();
				
					Component c = getPainelPrincipal();
					ChequeVO novoCheque = new ChequeVO();
					novoCheque.setCliente((ClienteVO)cbCliente.getSelectedItem());
					novoCheque.setConta((ContaVO)cbConta.getSelectedItem());
					novoCheque.setDataCadastro(new Date());
					novoCheque.setDataEmissao(transformarData(txtDataEmissao.getText()));
					novoCheque.setDataVencimento(transformarData(txtDataVencimento.getText()));
					novoCheque.setDescricao(txtDescricao.getText());
					novoCheque.setEmitido(true);
					//novoCheque.setFornecedor(fornecedor);
					novoCheque.setNumero(txtNumero.getText());
					novoCheque.setValor(txtValor.getValor());
					
					if(cheque != null){
						novoCheque.setId(cheque.getId());
						Controller.getInstance().updateCheque(novoCheque);
						
					}else
					{
						Controller.getInstance().insertCheque(novoCheque);
						
					}				
					((ChequeMainPanel)c).carregarCheques();
					((ChequeMainPanel)c).getIframeCheque().setVisible(false);
					limparCampos();
					
				} catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(),	barNotificacao.ERRO);
					ex.printStackTrace();
				}

			}
		}
	}
	
	private void validarCampos()throws MensagemSaoDimasException
	{
		/*if(txtAgencia.getText().trim().equals(""))
			throw new MensagemSaoDimasException("Campo agência é obrigatório... ");
			
		if(txtNomeConta.getText().trim().equals(""))
			throw new MensagemSaoDimasException("Campo nome é obrigatório... ");
			
		if(txtNumeroConta.getText().trim().equals(""))
			throw new MensagemSaoDimasException("Campo número é obrigatório... ");*/
	}
	
	private Date transformarData(String data)
	{
		try{
			SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
			return simpledataformat.parse(data);
		}catch (ParseException e) {
			
		}
		return new Date();
	}
	
	private String formataData(Date date)
	{
		SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
		return simpledataformat.format(date);
		
	}
	
	}	