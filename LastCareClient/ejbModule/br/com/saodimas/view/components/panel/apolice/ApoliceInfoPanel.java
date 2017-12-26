package br.com.saodimas.view.components.panel.apolice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.EmpresaVO;
import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.view.components.button.ErrorButton;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.CNPJTextField;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.util.ExpReg;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.validators.ValidaApolice;

@SuppressWarnings("serial")
public class ApoliceInfoPanel extends JPanel {
	public static final String FORM_NAME = "Apólice";

	private ApoliceVO apolice;
	private JTextField txtNumContrato;
	private DataTextField txtDataAdesao;
	private JComboBox cbPlano;
	private JTextField txtRazaoSocial;
	private CNPJTextField txtCNPJ;
	private JTextArea txaObservacoes;
	private JTextField txtNumApolice;

	private JLabel lbNumContrato;
	private JLabel lbDataAdesao;
	private JLabel lbPlano;
	private JLabel lbRazaoSocial;
	private JLabel lbCNPJ;
	private JLabel lbNumApolice;

	private ErrorButton btDataAdesao;
	private ErrorButton btErroPlano;
	private ErrorButton btErroRazaoSocial;
	private ErrorButton btErroCNPJ;
	private ErrorButton btErroNumero;

	private BarraNotificacao barNotificacao;
	private ActionListener listenerControles;
	private JButton btVoltar;
	private JButton btAvancar;
	private JButton btSalvar;
	private JButton btCancelar;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);

	private int etapa;
	private int numEtapas;

	public ApoliceInfoPanel(ActionListener listenerControles, int etapa, int numEtapas, BarraNotificacao bar) {
		this.etapa = etapa;
		this.numEtapas = numEtapas;
		this.listenerControles = listenerControles;
		this.barNotificacao = bar;
		initialize();
		configure();
	}
	
	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
		///loadPlanos();
		//loadFieldsPlano(((PlanoVO) cbPlano.getSelectedItem()).isEmpresarial());

	}

	@Override
	public void setVisible(boolean flag) {
		if (flag) barNotificacao.mostrarMensagem("", BarraNotificacao.DEFAULT);
		else barNotificacao.escondeMensagem();
		
		super.setVisible(flag);
		
		if (flag){
			loadPlanos();
			cbPlano.requestFocus();
			if(txtNumApolice.getText().equals(""))
				this.carregarNumContratoProximo();
		}
	}

	public void limparCampos() {
		txtNumApolice.setText("");
		txtNumContrato.setText("");
		if(cbPlano.getItemCount() > 0)
			cbPlano.setSelectedIndex(0);
		txtRazaoSocial.setText("");
		txtCNPJ.setText("");
		txaObservacoes.setText("");
		loadPlanos();
	}

	@Override
	public void requestFocus() {
		cbPlano.requestFocus();
	}

	private void loadPlanos()
	{
		cbPlano.removeAllItems();
		List<PlanoVO> list = Controller.getInstance().getPlanosAtivos();
		for (PlanoVO planoVO : list) {
			cbPlano.addItem(planoVO);
		}
		cbPlano.repaint();
	}
	
	
	private void initialize() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();

		lbNumApolice = new JLabel("Núm. Apólice:  ", JLabel.RIGHT);
		lbNumApolice.setPreferredSize(DLABEL);
		lbNumApolice.setMinimumSize(DLABEL);
		lbNumApolice.setVisible(false);
		
		txtNumApolice = new JTextField("Indefinido");
		txtNumApolice.setPreferredSize(DFIELDM);
		txtNumApolice.setMinimumSize(DFIELDM);
		txtNumApolice.setEnabled(false);
		txtNumApolice.setVisible(false);
		
		//mudança por causa do meu pai, que quer informar o numero da apólice;
		lbNumContrato = new JLabel("Núm. Apolice:  ", JLabel.RIGHT);
		lbNumContrato.setPreferredSize(DLABEL);
		lbNumContrato.setMinimumSize(DLABEL);
	
		txtNumContrato = new JTextField();
		txtNumContrato.setPreferredSize(DFIELDM);
		txtNumContrato.setMinimumSize(DFIELDM);
		txtNumContrato.setDocument(new CustomDocument(ExpReg.NUMERIC));
						
		lbDataAdesao = new JLabel("Data Adesão:  ", JLabel.RIGHT);
		lbDataAdesao.setPreferredSize(DLABEL);
		lbDataAdesao.setMinimumSize(DLABEL);

		txtDataAdesao = new DataTextField();
		txtDataAdesao.setText(f.format(new Date(c.getTimeInMillis())));
		txtDataAdesao.setPreferredSize(DFIELDM);
		txtDataAdesao.setMinimumSize(DFIELDM);
		txtDataAdesao.setEditable(true);
		
		
		lbPlano = new JLabel("Plano: *", JLabel.RIGHT);
		lbPlano.setPreferredSize(DLABEL);
		lbPlano.setMinimumSize(DLABEL);
				
		cbPlano= new JComboBox();
		cbPlano.setPreferredSize(DFIELDM);
		cbPlano.setMinimumSize(DFIELDM);
		cbPlano.addItemListener(new EventoComboPlano());
		
		lbRazaoSocial = new JLabel("Razão Social: *", JLabel.RIGHT);
		lbRazaoSocial.setPreferredSize(DLABEL);
		lbRazaoSocial.setMinimumSize(DLABEL);

		CustomDocument nomeDoc = new CustomDocument(50);
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setDocument(nomeDoc);
		txtRazaoSocial.setPreferredSize(DFIELDM);

		lbCNPJ = new JLabel("C.N.P.J.: *", JLabel.RIGHT);
		lbCNPJ.setPreferredSize(DLABEL);
		lbCNPJ.setMinimumSize(DLABEL);

		txtCNPJ = new CNPJTextField();
		txtCNPJ.setPreferredSize(DFIELDM);
		txtCNPJ.setMinimumSize(DFIELDM);

		txaObservacoes = new JTextArea();
		txaObservacoes.setPreferredSize(new Dimension(150, 100));
		txaObservacoes.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		btDataAdesao = new ErrorButton(txtDataAdesao, barNotificacao);
		btErroPlano = new ErrorButton(cbPlano, barNotificacao);
		btErroRazaoSocial = new ErrorButton(txtRazaoSocial, barNotificacao);
		btErroCNPJ = new ErrorButton(txtCNPJ, barNotificacao);
		btErroNumero = new ErrorButton(txtNumContrato, barNotificacao);

		btVoltar = new JButton("Voltar", new ImageIcon("imagens/back.gif"));
		btVoltar.addActionListener(listenerControles);
		btVoltar.setHorizontalAlignment(JButton.LEFT);
		btVoltar.setActionCommand(ApolicePanel.ACAO_VOLTAR);

		btAvancar = new JButton("Próximo", new ImageIcon("imagens/next.gif"));
		btAvancar.addActionListener(new EventoBotaoComando());
		btAvancar.setHorizontalAlignment(JButton.LEFT);
		btAvancar.setActionCommand(ApolicePanel.ACAO_AVANCAR);

		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoComando());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		btSalvar.setActionCommand(ApolicePanel.ACAO_SALVAR);		

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(listenerControles);
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		btCancelar.setActionCommand(ApolicePanel.ACAO_CANCELAR);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infApolice = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infApolice.add(lbNumApolice, c);
		c.gridy = 1; infApolice.add(lbNumContrato, c);
		c.gridy = 2; infApolice.add(lbDataAdesao, c);
		c.gridy = 3; infApolice.add(lbPlano, c);
		c.gridy = 4; infApolice.add(lbRazaoSocial, c);
		c.gridy = 5; infApolice.add(lbCNPJ, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infApolice.add(txtNumApolice, c);
		c.gridy = 1; infApolice.add(txtNumContrato, c);
		c.gridy = 2; infApolice.add(txtDataAdesao, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 3; infApolice.add(cbPlano, c);
		c.gridy = 4; infApolice.add(txtRazaoSocial, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 5; infApolice.add(txtCNPJ, c);
		c.weighty = 1;
		c.gridwidth = 3;
		c.gridy = 7; infApolice.add(new JLabel(""), c);

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 1; infApolice.add(btErroNumero, c);
		c.gridy = 2; infApolice.add(btDataAdesao, c);
		c.gridy = 3; infApolice.add(btErroPlano, c);
		c.gridy = 4; infApolice.add(btErroRazaoSocial, c);
		c.gridy = 5; infApolice.add(btErroCNPJ, c);

		infApolice.setBorder(BorderFactory.createTitledBorder("Apólice"));

		JScrollPane paneObs = new JScrollPane(txaObservacoes);
		paneObs.setBorder(BorderFactory.createTitledBorder("Observações"));

		JPanel panelPrincipal = new JPanel(new GridBagLayout()); 
		c = new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; panelPrincipal.add(infApolice, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; panelPrincipal.add(paneObs, c);

		setLayout(new BorderLayout());

		JLabel lbTitulo = new JLabel("Dados da Apólice (" + etapa + "/" + numEtapas + ")", new ImageIcon("imagens/apolice.gif"), JLabel.LEADING);
		lbTitulo.setLayout(null);
		lbTitulo.setBackground(new Color(255, 255, 255));
		lbTitulo.setFont(lbTitulo.getFont().deriveFont(Font.BOLD, 12));
		lbTitulo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				BorderFactory.createEmptyBorder(2, 0, 3, 1)
		)
		);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		if (etapa > 1) controle.add(btVoltar);
		if (etapa == numEtapas) controle.add(btSalvar);
		else controle.add(btAvancar);
		controle.add(btCancelar);

		add(lbTitulo, BorderLayout.NORTH);
		add(panelPrincipal, BorderLayout.CENTER);
		add(controle, BorderLayout.SOUTH);
		setBorder(BorderFactory.createRaisedBevelBorder());
	}

	private void loadFieldsPlano(boolean isPJ)
	{
		lbRazaoSocial.setVisible(isPJ);
		txtRazaoSocial.setVisible(isPJ);
		if (btErroRazaoSocial.getMensagem() != null) btErroRazaoSocial.setVisible(isPJ);
		lbCNPJ.setVisible(isPJ);
		txtCNPJ.setVisible(isPJ);
		if (btErroCNPJ.getMensagem() != null) btErroCNPJ.setVisible(isPJ);
	}
	
	public void carregarNumContratoProximo()
	{
		txtNumContrato.setText(Controller.getInstance().getProximoNumContrato()+"");
	}

	private class EventoComboPlano implements ItemListener{
		public void itemStateChanged(ItemEvent ev) {
			boolean isPJ = ((PlanoVO)ev.getItem()).isEmpresarial();
			loadFieldsPlano(isPJ);
		}
	}
	
	private class EventoBotaoComando implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btAvancar || e.getSource() == btSalvar){
				boolean isPJ = false;
				String erros = "";
				erros += ValidaApolice.validarPlano((PlanoVO)cbPlano.getSelectedItem());
				
				Date dataAdesao = null;
				try {
					dataAdesao = new SimpleDateFormat("dd/MM/yyyy").parse(txtDataAdesao.getText());
				} catch (ParseException e1) {
					erros += "Data Inválida";
				}
				
				if (erros.length() != 0)
					btDataAdesao.setMensagem(erros);
			    
				else {
					isPJ = ((PlanoVO)cbPlano.getSelectedItem()).isEmpresarial();
					if (isPJ){
						erros += ValidaApolice.validaRazaoSocial(txtRazaoSocial.getText());
						btErroRazaoSocial.setMensagem(ValidaApolice.validaRazaoSocial(txtRazaoSocial.getText()));

						erros += ValidaApolice.validaCNPJ(txtCNPJ.getText());
						btErroCNPJ.setMensagem(ValidaApolice.validaCNPJ(txtCNPJ.getText()));
					}
					else{
						btErroRazaoSocial.setMensagem(null);
						btErroCNPJ.setMensagem(null);
					}
					
					boolean isNumValido = Controller.getInstance().isNumContratoValido(txtNumContrato.getText());
					if(!isNumValido)
					{
						erros = ("Numero já existente.");
						btErroNumero.setMensagem("Número já existente.");
					}
						
											
					if (erros.length() > 0) barNotificacao.mostrarMensagem("Erro ao submeter os dados! A causa será exibida clicando no botão ao lado de cada campo!", BarraNotificacao.AVISO);
					else{
						btErroPlano.setMensagem(null);
						btErroRazaoSocial.setMensagem(null);
						btErroCNPJ.setMensagem(null);
						btErroNumero.setMensagem(null);
						btDataAdesao.setMensagem(null);
										
						apolice.setNumeroContrato(txtNumContrato.getText());
						apolice.setDataAdesao(dataAdesao);
						apolice.setPlano((PlanoVO)cbPlano.getSelectedItem());
						if(apolice.getPlano() != null)
							if(apolice.getPlano().isEmpresarial())
								apolice.setEmpresa(new EmpresaVO());
						if(apolice.getEmpresa() != null){
							apolice.getEmpresa().setDocCNPJ(isPJ ? txtCNPJ.getText() : "");
							apolice.getEmpresa().setRazaoSocial(isPJ ? txtRazaoSocial.getText() : "");
							apolice.getEmpresa().setStatus(ListasComuns.STATUS_ITENS[0]);
						}
						apolice.setObservacao(txaObservacoes.getText());
						
						listenerControles.actionPerformed(e);
					}
				}
			}
		}
	}
}