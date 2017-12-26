package br.com.saodimas.view.components.panel.financeiro.cliente.editar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.view.components.button.ErrorButton;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.CPFTextField;
import br.com.saodimas.view.components.text.DataTextField;

@SuppressWarnings("serial")
public class ClienteEditarDadosClientePanel extends JPanel {
	public static final String FORM_NAME = "Cliente";

	private ClienteVO cliente;
	private JTextField txtNome;
	private CPFTextField txtCPF;
	private JTextField txtRG;
	private DataTextField spnDataNascimento;
	private JTextField txtProfissao;
	private JTextField txtNomeMae;
	private JTextField txtNomePai;
	private JTextArea txaObservacoes;
	
	private JLabel lbNome;
	private JLabel lbCPF;
	private JLabel lbRG;
	private JLabel lbDataNascimento;
	private JLabel lbProfissao;
	private JLabel lbNomeMae;
	private JLabel lbNomePai;
	private JLabel lbObservacoes;
	
	private ErrorButton btErroNome;
	private ErrorButton btErroCPF;
	private ErrorButton btErroDataNasc;

	private BarraNotificacao barNotificacao;
	private ClienteListener titularListener;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);

	
	public ClienteEditarDadosClientePanel(BarraNotificacao bar) {
		this.barNotificacao = bar;
		initialize();
		configure();
	}

	public void atualizaDadosCliente()
	{
		if (cliente != null){
			cliente.setNome(txtNome.getText());
			cliente.setDataNascimento(this.parseDate(spnDataNascimento.getText()));
			cliente.setCpf(txtCPF.getText());
			cliente.setRg(txtRG.getText());
			cliente.setProfissao(txtProfissao.getText().trim());
			cliente.setNomeMae(txtNomeMae.getText().trim());
			cliente.setNomePai(txtNomePai.getText().trim());
			cliente.setObservacao(txaObservacoes.getText().trim());
		}
	}

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag) txtNome.requestFocus();
	}


	public void setCliente(ClienteVO cliente) {
		
		if(cliente != null){
			this.cliente = cliente;
			removeListeners();
	
			String nome = (cliente == null ? "" : cliente.getNome());
			String cpf = (cliente == null ? "" : cliente.getCpf());
			String rg = (cliente == null ? "" : cliente.getRg());
			String dataNasc = (cliente == null ? spnDataNascimento.getText() : this.parseDate(cliente.getDataNascimento()));
			txtNome.setText(nome);
			txtCPF.setText(cpf);
			txtRG.setText(rg);
			if(dataNasc != null)
				spnDataNascimento.setText(dataNasc);
			txtProfissao.setText(cliente.getProfissao());
			txtNomeMae.setText(cliente.getNomeMae());
			txtNomePai.setText(cliente.getNomePai());
			txaObservacoes.setText(cliente.getObservacao());
			
			addListeners();
		}
	}

	@Override
	public void requestFocus() {
		txtNome.requestFocus();
	}

	private void initialize() {
		titularListener = new ClienteListener();

		lbNome = new JLabel("Nome: *", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);

		CustomDocument nomeDoc = new CustomDocument(100);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);

		lbCPF = new JLabel("C.P.F.: ", JLabel.RIGHT);
		lbCPF.setPreferredSize(DLABEL);
		lbCPF.setMinimumSize(DLABEL);

		txtCPF = new CPFTextField();
		txtCPF.setPreferredSize(DFIELDM);
		txtCPF.setMinimumSize(DFIELDM);

		lbRG = new JLabel("RG.: ", JLabel.RIGHT);
		lbRG.setPreferredSize(DLABEL);
		lbRG.setMinimumSize(DLABEL);

		CustomDocument rgDoc = new CustomDocument(15);
		txtRG = new JTextField();
		txtRG.setPreferredSize(DFIELDM);
		txtRG.setDocument(rgDoc);
		txtRG.setMinimumSize(DFIELDM);

		
		lbDataNascimento = new JLabel("Data Nascimento: ", JLabel.RIGHT);
		lbDataNascimento.setPreferredSize(DLABEL);
		lbDataNascimento.setMinimumSize(DLABEL);

		spnDataNascimento = new DataTextField();
		spnDataNascimento.setPreferredSize(DFIELDM);
		spnDataNascimento.setMinimumSize(DFIELDM);

		lbProfissao = new JLabel("Profissão: ", JLabel.RIGHT);
		lbProfissao.setPreferredSize(DLABEL);
		lbProfissao.setMinimumSize(DLABEL);
		
		txtProfissao = new JTextField();
		txtProfissao.setDocument(new CustomDocument(50));
		txtProfissao.setPreferredSize(DFIELDM);
		
		lbNomePai = new JLabel("Nome pai: ", JLabel.RIGHT);
		lbNomePai.setPreferredSize(DLABEL);
		lbNomePai.setMinimumSize(DLABEL);
		
		txtNomePai  = new JTextField();
		txtNomePai.setDocument(new CustomDocument(50));
		txtNomePai.setPreferredSize(DFIELDM);
		
		lbNomeMae = new JLabel("Nome mãe: ", JLabel.RIGHT);
		lbNomeMae.setPreferredSize(DLABEL);
		lbNomeMae.setMinimumSize(DLABEL);
		
		txtNomeMae = new JTextField();
		txtNomeMae.setDocument(new CustomDocument(50));
		txtNomeMae.setPreferredSize(DFIELDM);
		
		lbObservacoes = new JLabel("Observações: ", JLabel.RIGHT);
		lbObservacoes.setPreferredSize(DLABEL);
		lbObservacoes.setMinimumSize(DLABEL);
		
		txaObservacoes = new JTextArea();
		txaObservacoes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txaObservacoes.setDocument(new CustomDocument(255));		

		btErroNome = new ErrorButton(txtNome, barNotificacao);
		btErroCPF = new ErrorButton(txtCPF, barNotificacao);
		btErroDataNasc = new ErrorButton(spnDataNascimento, barNotificacao);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infPessoais = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infPessoais.add(lbNome, c);
		c.gridy = 1; infPessoais.add(lbCPF, c);
		c.gridy = 2; infPessoais.add(lbRG, c);
		c.gridy = 3; infPessoais.add(lbDataNascimento, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infPessoais.add(txtNome, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1; infPessoais.add(txtCPF, c);
		c.gridy = 2; infPessoais.add(txtRG, c);
		c.weighty = 1;
		c.gridy = 3; infPessoais.add(spnDataNascimento, c);

		

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 0; infPessoais.add(btErroNome, c);
		c.gridy = 1; infPessoais.add(btErroCPF, c);
		c.gridy = 2; infPessoais.add(btErroDataNasc, c);

		infPessoais.setBorder(BorderFactory.createTitledBorder("Cliente"));

		JPanel panelPrincipal = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		JScrollPane paneObs = new JScrollPane(txaObservacoes);
		paneObs.setBorder(BorderFactory.createTitledBorder("Observações"));
		paneObs.setPreferredSize(new Dimension(50, 10));
		
		JPanel panelOutros = new JPanel(new GridBagLayout());
		panelOutros.setBorder(BorderFactory.createTitledBorder("Outras Informações"));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(1, 1, 1, 1);
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 0.4;
		c.gridy = 0; panelOutros.add(lbProfissao, c);
		c.gridy = 1; panelOutros.add(lbNomeMae, c);
		c.gridy = 2; panelOutros.add(lbNomePai, c);
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; panelOutros.add(txtProfissao, c);
		c.gridy = 1; panelOutros.add(txtNomeMae, c);
		c.gridy = 2; panelOutros.add(txtNomePai, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(1, 1, 1, 1);
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 0;
		c.gridy = 0; panelPrincipal.add(infPessoais, c);

		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1; panelPrincipal.add(panelOutros, c);		
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.6;
		c.gridx = 0;
		c.gridy = 2; panelPrincipal.add(paneObs, c);
		
		
		setLayout(new BorderLayout());

		JLabel lbTitulo = new JLabel("Dados Gerais do cliente", JLabel.LEADING);
		lbTitulo.setLayout(null);
		lbTitulo.setBackground(new Color(255, 255, 255));
		lbTitulo.setFont(lbTitulo.getFont().deriveFont(Font.BOLD, 12));
		lbTitulo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				BorderFactory.createEmptyBorder(2, 0, 3, 1)
		)
		);
		
		add(lbTitulo, BorderLayout.NORTH);
		add(panelPrincipal, BorderLayout.CENTER);
	}

	private void addListeners(){
		txtNome.addFocusListener(titularListener);
		txtCPF.addFocusListener(titularListener);
		txtRG.addFocusListener(titularListener);
		spnDataNascimento.addFocusListener(titularListener);
		txtProfissao.addFocusListener(titularListener);
		txtNomeMae.addFocusListener(titularListener);
		txtNomePai.addFocusListener(titularListener);
		txaObservacoes.addFocusListener(titularListener);
	}

	private void removeListeners(){
		txtNome.removeFocusListener(titularListener);
		txtCPF.removeFocusListener(titularListener);
		txtRG.removeFocusListener(titularListener);
		spnDataNascimento.removeFocusListener(titularListener);
		txtProfissao.removeFocusListener(titularListener);
		txtNomeMae.removeFocusListener(titularListener);
		txtNomePai.removeFocusListener(titularListener);
		txaObservacoes.removeFocusListener(titularListener);
	}

	private ClienteEditarMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ClienteEditarMainPanel) break;
			c = c.getParent();
		}
		return (ClienteEditarMainPanel)c;
	}

	private class ClienteListener extends FocusAdapter{
		public void apoliceChange() {
			if (cliente != null ){
				boolean hasChanged = getPainelPrincipal().hasChanged();
				String nome = cliente.getNome();
				String cpf = cliente.getCpf() == null ? "" : cliente.getCpf();
				String rg = cliente.getRg() == null ? "" : cliente.getRg();
				String dataNasc = parseDate(cliente.getDataNascimento());
				String dataDefault = spnDataNascimento.getText();
				String profissao  = cliente.getProfissao() == null ? "" : cliente.getProfissao();
				String nomePai = cliente.getNomePai() == null ? "" : cliente.getNomePai();
				String nomeMae = cliente.getNomeMae() == null ? "" : cliente.getNomeMae();
				String observacao = cliente.getObservacao() == null ? "" : cliente.getObservacao();

				hasChanged = hasChanged || (nome.compareTo(txtNome.getText()) != 0);
				hasChanged = hasChanged || (cpf.compareTo(txtCPF.getText()) != 0);
				hasChanged = hasChanged || (rg.compareTo(txtRG.getText()) != 0);
				hasChanged = hasChanged || (dataDefault.compareTo(dataNasc) != 0);
				hasChanged = hasChanged || (profissao.compareTo(txtProfissao.getText()) != 0);
				hasChanged = hasChanged || (nomePai.compareTo(txtNomePai.getText()) != 0);
				hasChanged = hasChanged || (nomeMae.compareTo(txtNomeMae.getText()) != 0);
				hasChanged = hasChanged || (observacao.compareTo(txaObservacoes.getText()) != 0);
				
				if (hasChanged)
					barNotificacao.mostrarMensagem("Alterações foram efetuadas!", BarraNotificacao.INFO);
				getPainelPrincipal().setChanged(hasChanged);
			}
		}

		public void focusGained(FocusEvent e) {
			apoliceChange();
			super.focusGained(e);
		}

		public void focusLost(FocusEvent e) {
			apoliceChange();
			super.focusLost(e);
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
