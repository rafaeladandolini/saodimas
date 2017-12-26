package br.com.saodimas.view.components.panel.financeiro.cliente;

import java.awt.BorderLayout;
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
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.CEPTextField;
import br.com.saodimas.view.components.text.CPFTextField;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.components.text.FoneTextField;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class ClientePanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";

	private ClienteVO cliente;
	private BarraNotificacao barNotificacao;
		
	private JTextField txtNome;
	private JTextField txtProfissao;
	private JTextField txtComplementoEndereco;
	private DataTextField spnDataNascimento;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private CEPTextField txtCEP;
	private FoneTextField txtContato;
  	private FoneTextField txtContato2;
	private JTextField txtEmail;
	private JTextField txtNomePai;
	private JTextField txtNomeName;
	private JComboBox<CidadeVO> cbCidades;
	private JComboBox<String> cbEstado;
	private JComboBox<String> cbStatus;
	private JTextArea txtaObs;
	private CPFTextField txtCPF;
	private JTextField txtRG;
	private JToolBar barConsultaEndereco;
	
	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbNome;
	private JLabel lbCPF;
	private JLabel lbRG;
	private JLabel lbDataNascimento;
	private JLabel lbProfissao;
	private JLabel lbEndereco;
	private JLabel lbComplementoEndereco;
	private JLabel lbBairro;
	private JLabel lbCEP;
	private JLabel lbContato;
	private JLabel lbContato2;
	private JLabel lbEmail;
	private JLabel lbCidade;
	private JLabel lbEstado;
	private JLabel lbStatus;
	private JLabel lbObservacao;
	private JLabel lbNomePai;
	private JLabel lbNomeMae;
	
	
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(200,22);
	
	public ClientePanel() {
		initialize();
		configure();
	}

	public ClienteVO getClienteVO() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
		if(cliente != null){
			this.limparCampos();
			txtaObs.setText(cliente.getObservacao());
			txtBairro.setText(cliente.getBairro());
			txtCEP.setText(cliente.getCep());
			txtContato.setText(cliente.getContato());
			txtContato2.setText(cliente.getContato2());
			txtCPF.setText(cliente.getCpf());
			txtEndereco.setText(cliente.getEndereco());
			txtNome.setText(cliente.getNome());
			txtRG.setText(cliente.getRg());
			cbEstado.setSelectedItem(cliente.getCidade().getEstado());
			cbStatus.setSelectedItem(cliente.getStatus());
			cbCidades.setSelectedItem(cliente.getCidade());
			txtEmail.setText(cliente.getEmail());
			txtNomePai.setText(cliente.getNomePai());
			txtNomeName.setText(cliente.getNomeMae());
			txtProfissao.setText(cliente.getProfissao());
			txtComplementoEndereco.setText(cliente.getComplementoEndereco());
			spnDataNascimento.setText(parseDate(cliente.getDataNascimento()));
			
		}
		else{limparCampos();}
	}

	public void limparCampos() {
	
		txtaObs.setText("");
		txtBairro.setText("");
		txtCEP.setText("");
		txtContato.setText("");
		txtContato2.setText("");
		txtCPF.setText("");
		txtEndereco.setText("");
		txtNome.setText("");
		txtRG.setText("");
		cbEstado.setSelectedItem("PR");
		this.loadCidadeByEstado("PR");
		cbStatus.setSelectedIndex(0);
		txtNomePai.setText("");
		txtNomeName.setText("");
		txtComplementoEndereco.setText("");
		txtEmail.setText("");
		txtProfissao.setText("");
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,BarraNotificacao.DEFAULT);
		spnDataNascimento.setText("");
	}
	
	private void loadCidadeByEstado(String estado)
	{
		List<CidadeVO> list = Controller.getInstance().getCidadeAllByEstado(estado);
		cbCidades.removeAllItems();
		for (int i=0; i< list.size(); i++) {
			cbCidades.addItem(list.get(i));
		}
		cbCidades.repaint();
	}
	
	public void focoPadrao(){
		txtNome.requestFocus();
	}
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbNome = new JLabel("Nome *: ", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		
		CustomDocument nomeDoc = new CustomDocument(150);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);
		
		lbDataNascimento = new JLabel("Data Nasc.: ", JLabel.RIGHT);
		lbDataNascimento.setPreferredSize(DLABEL);
		lbDataNascimento.setMinimumSize(DLABEL);
		
		spnDataNascimento = new DataTextField();
		spnDataNascimento.setPreferredSize(DFIELDM);
		spnDataNascimento.setMinimumSize(DFIELDM);
		
		lbCPF = new JLabel("C.P.F.: ", JLabel.RIGHT);
		lbCPF.setMinimumSize(DLABEL);
		lbCPF.setPreferredSize(DLABEL);

		txtCPF = new CPFTextField();
		txtCPF.setPreferredSize(new Dimension(100,22));

		lbRG = new JLabel("RG.: ", JLabel.RIGHT);
		lbRG.setPreferredSize(DLABEL);
		lbRG.setMinimumSize(DLABEL);

		CustomDocument rgDoc = new CustomDocument(15);
		txtRG = new JTextField();
		txtRG.setDocument(rgDoc);
		txtRG.setPreferredSize(new Dimension(100,22));
		
		lbEndereco = new JLabel("Endereço: ", JLabel.RIGHT);
		lbEndereco.setPreferredSize(DLABEL);
		lbEndereco.setMinimumSize(DLABEL);
		
		CustomDocument endDoc = new CustomDocument(200);
		txtEndereco = new JTextField();
		txtEndereco.setDocument(endDoc);
		txtEndereco.setPreferredSize(DFIELDM);
				
		lbBairro = new JLabel("Bairro: ", JLabel.RIGHT);
		lbBairro.setPreferredSize(DLABEL);
		lbBairro.setMinimumSize(DLABEL);
		
		CustomDocument bairroDoc = new CustomDocument(150);
		txtBairro = new JTextField();
		txtBairro.setDocument(bairroDoc);
		txtBairro.setPreferredSize(new Dimension(150,22));
			
		lbCEP = new JLabel("CEP: ", JLabel.RIGHT);
		lbCEP.setPreferredSize(DLABEL);
		lbCEP.setMinimumSize(DLABEL);
		
		txtCEP = new CEPTextField();
		txtCEP.setPreferredSize(new Dimension(70,22));
		
		lbComplementoEndereco = new JLabel("Complemento: ", JLabel.RIGHT);
		lbComplementoEndereco.setPreferredSize(DLABEL);
		lbComplementoEndereco.setMinimumSize(DLABEL);
		
		CustomDocument compDoc = new CustomDocument(200);
		txtComplementoEndereco = new JTextField();
		txtComplementoEndereco.setDocument(compDoc);
		txtComplementoEndereco.setPreferredSize(DFIELDM);
		
		lbContato = new JLabel("Contato: ", JLabel.RIGHT);
		lbContato.setPreferredSize(DLABEL);
		lbContato.setMinimumSize(DLABEL);

		txtContato = new FoneTextField();
		txtContato.setPreferredSize(new Dimension(100,22));

		lbContato2 = new JLabel("Contato 2:  ", JLabel.RIGHT);
		lbContato2.setPreferredSize(DLABEL);
		lbContato2.setMinimumSize(DLABEL);

		txtContato2 = new FoneTextField();
		txtContato2.setPreferredSize(new Dimension(100,22));
		
		lbEmail = new JLabel("E-mail:  ", JLabel.RIGHT);
		lbEmail.setPreferredSize(DLABEL);
		lbEmail.setMinimumSize(DLABEL);

		CustomDocument mailDoc = new CustomDocument("^([\\p{Lower}\\p{Digit}\\.\\@\\-\\_]+)$", 100);
		txtEmail = new JTextField();
		txtEmail.setDocument(mailDoc);
		txtEmail.setPreferredSize(DFIELDM);
		txtEmail.setMinimumSize(DFIELDM);
		
		lbStatus = new JLabel("Status:  ", JLabel.RIGHT);
		lbStatus.setPreferredSize(DLABEL);
		lbStatus.setMinimumSize(DLABEL);

		cbStatus = new JComboBox<String>(ListasComuns.STATUS_CLIENTE);
		cbStatus.setPreferredSize(DFIELDM);
		cbStatus.setMinimumSize(DFIELDM);
		
		lbObservacao = new JLabel("Obs.:  ", JLabel.RIGHT);
		lbObservacao.setPreferredSize(DLABEL);
		lbObservacao.setMinimumSize(DLABEL);
		
		txtaObs = new JTextArea();
		txtaObs.setDocument(new CustomDocument(255));
		txtaObs.setPreferredSize(new Dimension(200,60));
		
		lbCidade = new JLabel("Cidade: ", JLabel.RIGHT);
		lbCidade.setPreferredSize(DLABEL);
		lbCidade.setMinimumSize(DLABEL);

		cbCidades = new JComboBox<CidadeVO>();
		cbCidades.setPreferredSize(DFIELDM);
		
		lbEstado = new JLabel("Estado: ", JLabel.RIGHT);
		lbEstado.setPreferredSize(DLABEL);
		lbEstado.setMinimumSize(DLABEL);
				
		cbEstado= new JComboBox<String>(ListasComuns.ESTADOS);
		cbEstado.setSelectedItem("PR");
		cbEstado.addItemListener(new EventoComboBox());
		
		lbProfissao = new JLabel("Profissão : ", JLabel.RIGHT);
		lbProfissao.setPreferredSize(DLABEL);
		lbProfissao.setMinimumSize(DLABEL);
		
		CustomDocument nomeP = new CustomDocument(150);
		txtProfissao = new JTextField();
		txtProfissao.setDocument(nomeP);
		txtProfissao.setPreferredSize(DFIELDM);
		
		lbNomePai = new JLabel("Nome Pai : ", JLabel.RIGHT);
		lbNomePai.setPreferredSize(DLABEL);
		lbNomePai.setMinimumSize(DLABEL);
		
		CustomDocument nomePai = new CustomDocument(150);
		txtNomePai = new JTextField();
		txtNomePai.setDocument(nomePai);
		txtNomePai.setPreferredSize(DFIELDM);
		
		lbNomeMae = new JLabel("Nome Mãe *: ", JLabel.RIGHT);
		lbNomeMae.setPreferredSize(DLABEL);
		lbNomeMae.setMinimumSize(DLABEL);
		
		CustomDocument nomeMae = new CustomDocument(150);
		txtNomeName = new JTextField();
		txtNomeName.setDocument(nomeMae);
		txtNomeName.setPreferredSize(DFIELDM);
				
		barConsultaEndereco = new JToolBar();
		barConsultaEndereco.setFloatable(false);
		barConsultaEndereco.setOpaque(false);
		barConsultaEndereco.setBorderPainted(false);
		barConsultaEndereco.setBorder(BorderFactory.createEmptyBorder());
		barConsultaEndereco.setMargin(new Insets(0, 0, 0, 0));
		barConsultaEndereco.setPreferredSize(new Dimension(115, 22));
		barConsultaEndereco.setMinimumSize(new Dimension(115, 22));
			
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infUser = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;

		c.gridy = 0; infUser.add(lbStatus, c);
		c.gridy = 1; infUser.add(lbNome, c);
		c.gridy = 2; infUser.add(lbCPF, c);
		c.gridy = 3; infUser.add(lbRG, c);
		c.gridy = 4; infUser.add(lbDataNascimento, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 5; infUser.add(lbEndereco, c);
		c.gridy = 6; infUser.add(lbBairro, c);
		c.gridy = 7; infUser.add(lbCEP, c);
		c.gridy = 8; infUser.add(lbComplementoEndereco, c);
		c.gridy = 9; infUser.add(lbEstado, c);
		c.gridy = 10; infUser.add(lbCidade, c);
		c.gridy = 11; infUser.add(lbContato, c);
		c.gridy = 12; infUser.add(lbContato2, c);
		c.gridy = 13; infUser.add(lbEmail, c);
		c.gridy = 14; infUser.add(lbProfissao, c);
		c.gridy = 15; infUser.add(lbNomePai, c);
		c.gridy = 16; infUser.add(lbNomeMae, c);
		c.gridy = 17; infUser.add(lbObservacao, c);	
		
				
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(cbStatus, c);
		c.gridy = 1; infUser.add(txtNome, c);
		c.gridy = 2; infUser.add(txtCPF, c);
		c.gridy = 3; infUser.add(txtRG, c);
		c.gridy = 4; infUser.add(spnDataNascimento, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 5; infUser.add(txtEndereco, c);
		c.gridy = 6; infUser.add(txtBairro, c);
		c.gridy = 7; infUser.add(txtCEP, c);
		c.gridy = 8; infUser.add(txtComplementoEndereco, c);
		c.gridy = 9; infUser.add(cbEstado, c);
		c.gridy = 10; infUser.add(cbCidades, c);
		c.gridy = 11; infUser.add(txtContato, c);
		c.gridy = 12; infUser.add(txtContato2, c);
		c.gridy = 13; infUser.add(txtEmail, c);
		c.gridy = 14; infUser.add(txtProfissao, c);
		c.gridy = 15; infUser.add(txtNomePai, c);
		c.gridy = 16; infUser.add(txtNomeName, c);
		c.gridy = 17; infUser.add(txtaObs, c);
		

		infUser.setBorder(BorderFactory.createTitledBorder("Cliente"));
		adicionarAtalhosComandos(infUser);
		
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(infUser, BorderLayout.CENTER);
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
			if (c instanceof ClienteMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	

	private class EventoBotaoControle implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			
			Component c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				try{
					((ClienteMainPanel)c).getIframeCliente().setVisible(false);
				}
				catch(ClassCastException cex){
					
					cex.printStackTrace();
				}
			}else
			if (e.getSource() == btSalvar)
			{
				salvarCliente();	
			}
		}
	}
	
	@SuppressWarnings({ "static-access"})
	private void salvarCliente()
	{
		try{
			this.validaCampos();
			ClienteVO novoCliente = new ClienteVO();
			Component c = getPainelPrincipal();
			
			novoCliente.setBairro(txtBairro.getText());
			novoCliente.setCep(txtCEP.getText());
			novoCliente.setCidade((CidadeVO)cbCidades.getSelectedItem());
			novoCliente.setContato(txtContato.getText());
			novoCliente.setContato2(txtContato2.getText());
			novoCliente.setCpf(txtCPF.getText());
			novoCliente.setEndereco(txtEndereco.getText());
			novoCliente.setNome(txtNome.getText());
			novoCliente.setObservacao(txtaObs.getText());
			novoCliente.setRg(txtRG.getText());
			novoCliente.setStatus(cbStatus.getSelectedItem().toString());
			novoCliente.setEmail(txtEmail.getText());
			novoCliente.setComplementoEndereco(txtComplementoEndereco.getText());
			novoCliente.setProfissao(txtProfissao.getText());
			novoCliente.setNomeMae(txtNomeName.getText());
			novoCliente.setNomePai(txtNomePai.getText());
			novoCliente.setDataNascimento(this.parseDate(spnDataNascimento.getText()));
		
			if (cliente != null) {
				novoCliente.setId(cliente.getId());
				Controller.getInstance().updateCliente(novoCliente);
				((ClienteMainPanel) c).mostrarMensagem("Alterações efetuadas com sucesso.",	barNotificacao.SUCESSO);
			} else {
				Controller.getInstance().insertCliente(novoCliente);
				((ClienteMainPanel) c).mostrarMensagem("Cadastro efetuado com sucesso.", barNotificacao.SUCESSO);
			}

			((ClienteMainPanel) c).carregarClientesTable();
			((ClienteMainPanel) c).getIframeCliente().setVisible(false);

		} catch (MensagemSaoDimasException ex) {
			barNotificacao
					.mostrarMensagem(ex.getMessage(), barNotificacao.ERRO);
			ex.printStackTrace();
		}
	}
	
	private void validaCampos()throws MensagemSaoDimasException
	{
		if(txtNome.getText().trim().equals(""))
		{
			txtNome.requestFocus();
			throw new MensagemSaoDimasException("Campo nome é obrigatório...");
		}
	}
	
	private class EventoComboBox implements ItemListener
	{
		public void itemStateChanged(ItemEvent e) {
			String estado = e.getItem().toString();
			loadCidadeByEstado(estado);
		}
		
	}
	
	private Date parseDate(String strData){
		if(strData == null || strData.trim().equals(""))
			return null;
		SimpleDateFormat st = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return st.parse(strData);
		} catch (ParseException e) {
			barNotificacao.mostrarMensagem("Data nascimento inválida!", BarraNotificacao.ERRO);
			e.printStackTrace();
		}
		return null;
	}
	
	private String parseDate(Date data){
		if(data == null)
			return "";
		SimpleDateFormat st = new SimpleDateFormat("dd/MM/yyyy");
		return st.format(data);
	}
}	