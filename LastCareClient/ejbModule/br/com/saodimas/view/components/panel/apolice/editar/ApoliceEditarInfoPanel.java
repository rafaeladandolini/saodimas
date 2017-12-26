package br.com.saodimas.view.components.panel.apolice.editar;

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.EmpresaVO;
import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.button.ErrorButton;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.menu.BarraMenu;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.table.renderer.SpinnerRenderer;
import br.com.saodimas.view.components.text.CNPJTextField;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.validators.ValidaDatas;

@SuppressWarnings("serial")
public class ApoliceEditarInfoPanel extends JPanel {
	public static final String FORM_NAME = "Ap�lice";

	private ApoliceVO apolice;
	private JComboBox cbSituacao;
	private JTextField txtNumContrato;
	private DataTextField spnDataAdesao;
	private JComboBox cbPlano;
	private JTextField txtRazaoSocial;
	private CNPJTextField txtCNPJ;
	private JTextArea txaObservacoes;
	private JTextArea txaObservacoesOcultas;
	private SpinnerRenderer spinnerQtdeCarteirinhas;
	
	private DataTextField spnDataSuspensao;
	private DataTextField spnDataCancelamento;
	private JTextArea txtMotivoCancelamento;
	private JTextField txtMotivoSuspensao;

	private JLabel lbSituacao;
	private JLabel lbNumContrato;
	private JLabel lbDataAdesao;
	private JLabel lbPlano;
	private JLabel lbRazaoSocial;
	private JLabel lbCNPJ;
	private JLabel lbCarteirinhas;
	
	private JLabel lbDataSuspensao;
	private JLabel lbMotivoSuspensao;
	private JLabel lbDataCancelamento;
	private JLabel lbMotivoCancelamento;

	private JPanel infApolice;

	private ErrorButton btErroPlano;
	private ErrorButton btErroRazaoSocial;
	private ErrorButton btErroCNPJ;

	private BarraNotificacao barNotificacao;
	private ApoliceInfoListener infoListener;

	private static final Dimension DLABEL = new Dimension(120, 22);
	private static final Dimension DFIELDM = new Dimension(115, 22);

	public ApoliceEditarInfoPanel(BarraNotificacao bar) {
		this.barNotificacao = bar;
		initialize();
		configure();
	}

	public void atulizadaDadosApolice() throws MensagemSaoDimasException {
		String erro = null;

		apolice.setStatus(cbSituacao.getSelectedItem().toString());
		apolice.setNumeroContrato(txtNumContrato.getText());
		apolice.setPlano((PlanoVO) cbPlano.getSelectedItem());

		if (!ValidaDatas.isDataValida(spnDataAdesao.getText()))
			erro = "Data de ades�o invalida. Altera��es n�o foram salvas.";
		else
			apolice.setDataAdesao(ValidaDatas.formatDate(spnDataAdesao
					.getText()));

		if (apolice.getPlano().isEmpresarial()) {
			if (apolice.getEmpresa() == null) {
				apolice.setEmpresa(new EmpresaVO());
				apolice.getEmpresa().setStatus(ListasComuns.STATUS_APOLICE[0]);
			}
			apolice.getEmpresa().setDocCNPJ(txtCNPJ.getText());
			apolice.getEmpresa().setRazaoSocial(txtRazaoSocial.getText());
		}
		apolice.setObservacao(txaObservacoes.getText());
		apolice.setObservacaoOculta(txaObservacoesOcultas.getText());
		apolice.setQuantidadeCarteirinhas((Integer) (spinnerQtdeCarteirinhas.getValue()));
		
		apolice.setDataCancelamento(null);
		apolice.setDataSuspensao(null);
		apolice.setMotivoCancelamento("");
		apolice.setMotivoSuspensao("");
		
		if(apolice.getStatus().equals(ListasComuns.STATUS_APOLICE[1]))
		{	
			if(!ValidaDatas.isDataValida(spnDataSuspensao.getText()))
				erro = "Data de suspens�o invalida. Altera��es n�o foram salvas.";
			else{
				apolice.setDataSuspensao(ValidaDatas.formatDate(spnDataSuspensao.getText()));
				apolice.setMotivoSuspensao(txtMotivoSuspensao.getText().trim());
			}
			
		}else if(apolice.getStatus().equals(ListasComuns.STATUS_APOLICE[2]))
		{
			if(!ValidaDatas.isDataValida(spnDataCancelamento.getText()))
				erro = "Data de cancelamento invalida. Altera��es n�o foram salvas.";
			else{
				apolice.setDataCancelamento(ValidaDatas.formatDate(spnDataCancelamento.getText()));
				apolice.setMotivoCancelamento(txtMotivoCancelamento.getText().trim());
			}
		}
		
		if (erro != null)
			throw new MensagemSaoDimasException(erro);

	}

	private void loadPlanos() {
		cbPlano.removeAllItems();
		List<PlanoVO> list = Controller.getInstance().getPlanosAtivos();
		for (PlanoVO planoVO : list) {
			cbPlano.addItem(planoVO);

			if (apolice != null) {
				if (planoVO.getId().intValue() == apolice.getPlano().getId()
						.intValue())
					cbPlano.setSelectedItem(planoVO);
			}

		}

		if (apolice != null) {
			if (!apolice.getPlano().getStatus().equals("Ativo"))
				cbPlano.addItem(apolice.getPlano());
		}
	}

	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag) {
			cbPlano.requestFocus();
		}
		this.setEnableCamposPorPerfil();
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
		removeListeners();

		if (apolice != null) {
			cbSituacao.setSelectedItem(apolice.getStatus());
			spinnerQtdeCarteirinhas.setValue(apolice
					.getQuantidadeCarteirinhas());
			txtNumContrato.setText(apolice.getNumeroContrato());
			spnDataAdesao.setText((new SimpleDateFormat("dd/MM/yyyy").format(apolice.getDataAdesao())));
			txaObservacoes.setText(apolice.getObservacao());
			txaObservacoesOcultas.setText(apolice.getObservacaoOculta());
			spinnerQtdeCarteirinhas.setValue(apolice.getQuantidadeCarteirinhas());
			
			if (apolice.getPlano() != null) {
				loadPlanos();
			}
			if (apolice.getEmpresa() != null) {
				txtRazaoSocial.setText(apolice.getEmpresa().getRazaoSocial());
				txtCNPJ.setText(apolice.getEmpresa().getDocCNPJ());
			}
			
			if(apolice.getDataCancelamento() != null)
				spnDataCancelamento.setText(new SimpleDateFormat("dd/MM/yyyy").format(apolice.getDataCancelamento()));
			else
				spnDataCancelamento.setText("");
			
			if(apolice.getDataSuspensao() != null)
				spnDataSuspensao.setText(new SimpleDateFormat("dd/MM/yyyy").format(apolice.getDataSuspensao()));
			else
				spnDataSuspensao.setText("");
				
			txtMotivoCancelamento.setText(apolice.getMotivoCancelamento());
			txtMotivoSuspensao.setText(apolice.getMotivoSuspensao());
				
			addListeners();

		}
		if (apolice.getPlano() != null) 
			this.atualizarDadosPlano(apolice.getPlano());
		
		this.configurarCamposSupensaoECancelamento(apolice.getStatus());
		this.setEnableCamposPorPerfil();
	}

	@Override
	public void requestFocus() {
		cbPlano.requestFocus();
	}

	private void initialize() {
		infoListener = new ApoliceInfoListener();

		lbSituacao = new JLabel("Status:  ", JLabel.RIGHT);
		lbSituacao.setPreferredSize(DLABEL);
		lbSituacao.setMinimumSize(DLABEL);

		cbSituacao = new JComboBox(ListasComuns.STATUS_APOLICE);
		cbSituacao.setPreferredSize(DFIELDM);
		cbSituacao.setMinimumSize(DFIELDM);
		cbSituacao.setEnabled(apolice != null);
		cbSituacao.addItemListener(new ApoliceInfoListener());

		lbCarteirinhas = new JLabel("Qtde Carteirihas: ", JLabel.RIGHT);
		lbCarteirinhas.setPreferredSize(DLABEL);
		lbCarteirinhas.setMinimumSize(DLABEL);

		spinnerQtdeCarteirinhas = new SpinnerRenderer();
		spinnerQtdeCarteirinhas.setPreferredSize(DFIELDM);
		spinnerQtdeCarteirinhas.setMinimumSize(DFIELDM);

		lbNumContrato = new JLabel("N�m. Apolice  ", JLabel.RIGHT);
		lbNumContrato.setPreferredSize(DLABEL);
		lbNumContrato.setMinimumSize(DLABEL);

		txtNumContrato = new JTextField();
		txtNumContrato.setPreferredSize(DFIELDM);
		txtNumContrato.setMinimumSize(DFIELDM);
		txtNumContrato.setEditable(false);

		lbDataAdesao = new JLabel("Data Ades�o:  ", JLabel.RIGHT);
		lbDataAdesao.setPreferredSize(DLABEL);
		lbDataAdesao.setMinimumSize(DLABEL);

		spnDataAdesao = new DataTextField();
		spnDataAdesao.setPreferredSize(DFIELDM);
		spnDataAdesao.setMinimumSize(DFIELDM);

		lbPlano = new JLabel("Plano: *", JLabel.RIGHT);
		lbPlano.setPreferredSize(DLABEL);
		lbPlano.setMinimumSize(DLABEL);

		cbPlano = new JComboBox();
		cbPlano.setPreferredSize(DFIELDM);
		cbPlano.setMinimumSize(DFIELDM);
		cbPlano.addItemListener(new ApoliceInfoListener());

		lbRazaoSocial = new JLabel("Raz�o Social: ", JLabel.RIGHT);
		lbRazaoSocial.setPreferredSize(DLABEL);
		lbRazaoSocial.setMinimumSize(DLABEL);

		CustomDocument nomeDoc = new CustomDocument(50);
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setDocument(nomeDoc);
		txtRazaoSocial.setPreferredSize(DFIELDM);

		lbCNPJ = new JLabel("C.N.P.J.: ", JLabel.RIGHT);
		lbCNPJ.setPreferredSize(DLABEL);
		lbCNPJ.setMinimumSize(DLABEL);

		txtCNPJ = new CNPJTextField();
		txtCNPJ.setPreferredSize(DFIELDM);
		txtCNPJ.setMinimumSize(DFIELDM);

		txaObservacoes = new JTextArea();
		txaObservacoes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txaObservacoes.setDocument(new CustomDocument(255));

		txaObservacoesOcultas = new JTextArea();
		txaObservacoesOcultas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txaObservacoesOcultas.setDocument(new CustomDocument(255));
		
		lbDataSuspensao = new JLabel("Data Suspens�o:  ", JLabel.RIGHT);
		lbDataSuspensao.setPreferredSize(DLABEL);
		lbDataSuspensao.setMinimumSize(DLABEL);

		spnDataSuspensao = new DataTextField();
		spnDataSuspensao.setPreferredSize(DFIELDM);
		spnDataSuspensao.setMinimumSize(DFIELDM);
		
		lbDataCancelamento = new JLabel("Data Cancelamento:  ", JLabel.RIGHT);
		lbDataCancelamento.setPreferredSize(DLABEL);
		lbDataCancelamento.setMinimumSize(DLABEL);

		spnDataCancelamento = new DataTextField();
		spnDataCancelamento.setPreferredSize(DFIELDM);
		spnDataCancelamento.setMinimumSize(DFIELDM);

		lbMotivoCancelamento = new JLabel("Motivo Cancelamento:  ", JLabel.RIGHT);
		lbMotivoCancelamento.setPreferredSize(DLABEL);
		lbMotivoCancelamento.setMinimumSize(DLABEL);
		
		txtMotivoCancelamento = new JTextArea();
		txtMotivoCancelamento.setPreferredSize(DFIELDM);
		txtMotivoCancelamento.setMinimumSize(DFIELDM);
			
		lbMotivoSuspensao = new JLabel("Motivo Suspens�o:  ", JLabel.RIGHT);
		lbMotivoSuspensao.setPreferredSize(DLABEL);
		lbMotivoSuspensao.setMinimumSize(DLABEL);
		
		txtMotivoSuspensao = new JTextField();
		txtMotivoSuspensao.setPreferredSize(DFIELDM);
		txtMotivoSuspensao.setMinimumSize(DFIELDM);
	
		
		txaObservacoes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txaObservacoes.setDocument(new CustomDocument(255));
		
		btErroPlano = new ErrorButton(cbPlano, barNotificacao);
		btErroRazaoSocial = new ErrorButton(txtRazaoSocial, barNotificacao);
		btErroCNPJ = new ErrorButton(txtCNPJ, barNotificacao);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		infApolice = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;  infApolice.add(lbSituacao, c);	
		c.gridy = 1;  infApolice.add(lbDataAdesao, c);
		c.gridy = 2;  infApolice.add(lbNumContrato, c);
		c.gridy = 3;  infApolice.add(lbPlano, c);
		c.gridy = 4;  infApolice.add(lbRazaoSocial, c);
		c.gridy = 5;  infApolice.add(lbCNPJ, c);
		c.gridy = 6;  infApolice.add(lbDataSuspensao, c);
		c.gridy = 7;  infApolice.add(lbMotivoSuspensao, c);
		c.gridy = 8;  infApolice.add(lbDataCancelamento, c);
		c.gridy = 9;  infApolice.add(lbMotivoCancelamento, c);
	
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0;  infApolice.add(cbSituacao, c);
		c.gridy = 1;  infApolice.add(spnDataAdesao, c);
		c.gridy = 2;  infApolice.add(txtNumContrato, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 3;  infApolice.add(cbPlano, c);
		c.gridy = 4;  infApolice.add(txtRazaoSocial, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 5;  infApolice.add(txtCNPJ, c);
		c.gridy = 6;  infApolice.add(spnDataSuspensao, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 7;  infApolice.add(txtMotivoSuspensao, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 8;  infApolice.add(spnDataCancelamento, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 9;  infApolice.add(txtMotivoCancelamento, c);
		c.weighty = 1;
		c.gridwidth = 3;
		c.gridy = 10;
		infApolice.add(new JLabel(""), c);

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 3;  infApolice.add(btErroPlano, c);
		c.gridy = 4;  infApolice.add(btErroRazaoSocial, c);
		c.gridy = 5;  infApolice.add(btErroCNPJ, c);

		infApolice.setBorder(BorderFactory.createTitledBorder("Ap�lice"));

		JScrollPane paneObs = new JScrollPane(txaObservacoes);
		paneObs.setBorder(BorderFactory.createTitledBorder("Observa��es"));
		paneObs.setPreferredSize(new Dimension(50, 10));

		JScrollPane paneObsOcultas = new JScrollPane(txaObservacoesOcultas);
		paneObsOcultas.setBorder(BorderFactory.createTitledBorder("Observa��es Ocultas"));
		paneObsOcultas.setPreferredSize(new Dimension(50, 10));

		JPanel panelPrincipal = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		JPanel panelOutros = new JPanel(new GridBagLayout());
		panelOutros.setBorder(BorderFactory.createTitledBorder("Outras Informa��es"));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;   panelOutros.add(lbCarteirinhas, c);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0;   panelOutros.add(spinnerQtdeCarteirinhas, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;   panelPrincipal.add(infApolice, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		panelPrincipal.add(panelOutros, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridx = 0;
		c.gridy = 2;
		panelPrincipal.add(paneObs, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridx = 0;
		c.gridy = 3;
		panelPrincipal.add(paneObsOcultas, c);

		setLayout(new BorderLayout());

		JLabel lbTitulo = new JLabel("Dados da Ap�lice", JLabel.LEADING);
		lbTitulo.setLayout(null);
		lbTitulo.setBackground(new Color(255, 255, 255));
		lbTitulo.setFont(lbTitulo.getFont().deriveFont(Font.BOLD, 12));
		lbTitulo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				BorderFactory.createEmptyBorder(2, 0, 3, 1)));

		add(lbTitulo, BorderLayout.NORTH);
		add(panelPrincipal, BorderLayout.CENTER);
	}

	private void addListeners() {

		cbSituacao.addItemListener(infoListener);
		txtNumContrato.addFocusListener(infoListener);
		spnDataAdesao.addFocusListener(infoListener);
		cbPlano.addItemListener(infoListener);
		txtRazaoSocial.addFocusListener(infoListener);
		txtCNPJ.addFocusListener(infoListener);
		txaObservacoes.addFocusListener(infoListener);
		txaObservacoesOcultas.addFocusListener(infoListener);
		spinnerQtdeCarteirinhas.addFocusListener(infoListener);
		spnDataCancelamento.addFocusListener(infoListener);
		txtMotivoCancelamento.addFocusListener(infoListener);
		spnDataSuspensao.addFocusListener(infoListener);
		txtMotivoSuspensao.addFocusListener(infoListener);
	}

	private void removeListeners() {
		cbSituacao.removeItemListener(infoListener);
		txtNumContrato.removeFocusListener(infoListener);
		spnDataAdesao.removeFocusListener(infoListener);
		cbPlano.removeItemListener(infoListener);
		txtRazaoSocial.removeFocusListener(infoListener);
		txtCNPJ.removeFocusListener(infoListener);
		txaObservacoes.removeFocusListener(infoListener);
		txaObservacoesOcultas.removeFocusListener(infoListener);
		spinnerQtdeCarteirinhas.removeFocusListener(infoListener);
		spnDataCancelamento.removeFocusListener(infoListener);
		txtMotivoCancelamento.removeFocusListener(infoListener);
		spnDataSuspensao.removeFocusListener(infoListener);
		txtMotivoSuspensao.removeFocusListener(infoListener);
	}

	private ApoliceEditarMainPanel getPainelPrincipal() {
		Component c = getParent();
		while (c != null) {
			if (c instanceof ApoliceEditarMainPanel)
				break;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel) c;
	}

	private void verificaQuantidadeDependentesCompativelPlano() {
		PlanoVO plano = (PlanoVO) cbPlano.getSelectedItem();
		if (apolice != null && plano != null) {

			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (apolice.getDependentes() != null
					&& apolice.getDependentes().size() > plano
							.getLimiteAssociado()) {
				c.mostrarMensagemDependente(
						"H� mais dependentes no apolice do que o plano selecionado permite.",
						BarraNotificacao.ERRO);
			} else {
				c.mostrarMensagemDicaDependente();
			}
		}
	}

	public PlanoVO getPlanoSelecionadoComboBox() {
		if (cbPlano != null) {
			return (PlanoVO) cbPlano.getSelectedItem();
		}

		return null;
	}

	public void atualizarDadosPlano(PlanoVO vo) {
		boolean isPJ = vo.isEmpresarial();
		lbRazaoSocial.setVisible(isPJ);
		txtRazaoSocial.setVisible(isPJ);
		if (btErroRazaoSocial.getMensagem() != null)
			btErroRazaoSocial.setVisible(isPJ);
		lbCNPJ.setVisible(isPJ);
		txtCNPJ.setVisible(isPJ);
		if (btErroCNPJ.getMensagem() != null)
			btErroCNPJ.setVisible(isPJ);

		verificaQuantidadeDependentesCompativelPlano();

	}
	
	public void configurarCamposSupensaoECancelamento(String statusPlano){
		boolean isCancelamento = false, isSuspenso = false;
		
		isCancelamento = statusPlano.equals(ListasComuns.STATUS_APOLICE[2]);
		isSuspenso = statusPlano.equals(ListasComuns.STATUS_APOLICE[1]);
		
		lbDataCancelamento.setVisible(isCancelamento);
		spnDataCancelamento.setVisible(isCancelamento);
		lbMotivoCancelamento.setVisible(isCancelamento);
		txtMotivoCancelamento.setVisible(isCancelamento);
		
		lbDataSuspensao.setVisible(isSuspenso);
		spnDataSuspensao.setVisible(isSuspenso);
		lbMotivoSuspensao.setVisible(isSuspenso);
		txtMotivoSuspensao.setVisible(isSuspenso);
	}

	public void setEnableCamposPorPerfil() {
		ColaboradorVO funcionario = SaoDimasMain.colaborador;
		int perfil = BarraMenu.SEM_PERFIL;
		
		if(funcionario != null) perfil = funcionario.getPerfilColoborador();

		txtCNPJ.setEditable(perfil == BarraMenu.PERFIL_ADMIN);
		txaObservacoes.setEditable(perfil == BarraMenu.PERFIL_ADMIN || perfil == BarraMenu.PERFIL_FUNC_ESPECIAL);
		txaObservacoesOcultas.setEditable(perfil == BarraMenu.PERFIL_ADMIN || perfil == BarraMenu.PERFIL_FUNC_ESPECIAL);
		txtNumContrato.setEditable(perfil == BarraMenu.PERFIL_ADMIN);
		txtRazaoSocial.setEditable(perfil == BarraMenu.PERFIL_ADMIN);
		cbPlano.setEnabled(perfil == BarraMenu.PERFIL_ADMIN || perfil == BarraMenu.PERFIL_FUNC_ESPECIAL);
		cbSituacao.setEnabled(perfil == BarraMenu.PERFIL_ADMIN || perfil == BarraMenu.PERFIL_FUNC_ESPECIAL);
		spnDataAdesao.setEditable(perfil == BarraMenu.PERFIL_ADMIN || perfil == BarraMenu.PERFIL_FUNC_ESPECIAL);

	}

	protected class ApoliceInfoListener extends FocusAdapter implements
			ItemListener {
		public void apoliceChange() {
			if (apolice != null) {
				boolean hasChanged = getPainelPrincipal().hasChanged();
				boolean isPJ = apolice.getPlano().isEmpresarial();
				String dataAdesao = new SimpleDateFormat("dd/MM/yyyy").format(apolice.getDataAdesao());

				hasChanged = hasChanged	|| !apolice.getStatus().equals(cbSituacao.getSelectedItem());
				hasChanged = hasChanged	|| (apolice.getNumeroContrato().compareTo(txtNumContrato.getText()) != 0);
				hasChanged = hasChanged	|| !dataAdesao.equals(spnDataAdesao.getText());

				if (cbPlano.getSelectedItem() != null)
					hasChanged = hasChanged	|| !apolice.getPlano().equals(cbPlano.getSelectedItem());
				
				if (apolice.getEmpresa() != null) {
					hasChanged = hasChanged	|| (apolice.getEmpresa().getRazaoSocial().compareTo(txtRazaoSocial.getText()) != 0 && isPJ);
					hasChanged = hasChanged	|| (apolice.getEmpresa().getDocCNPJ().compareTo(txtCNPJ.getText()) != 0 && isPJ);
				}
				
				hasChanged = hasChanged	|| ((apolice.getObservacao() != null ? apolice.getObservacao() : "").compareTo(txaObservacoes.getText()) != 0);
				hasChanged = hasChanged	|| ((apolice.getObservacaoOculta() != null ? apolice.getObservacaoOculta() : "").compareTo(txaObservacoesOcultas.getText()) != 0);
				hasChanged = hasChanged	|| apolice.getQuantidadeCarteirinhas() != ((Integer) spinnerQtdeCarteirinhas.getValue()).intValue();

				if(!apolice.getStatus().equals(ListasComuns.STATUS_APOLICE[0])){
					if(!apolice.getStatus().equals(ListasComuns.STATUS_APOLICE[1])){
						String dataSUspensao = apolice.getDataSuspensao() != null ? new SimpleDateFormat("dd/MM/yyyy").format(apolice.getDataSuspensao()) : "";
						hasChanged = hasChanged	|| !dataSUspensao.equals(spnDataSuspensao.getText());
						hasChanged = hasChanged	|| ((apolice.getMotivoSuspensao() != null ? apolice.getMotivoSuspensao() : "").compareTo(txtMotivoSuspensao.getText()) != 0);
					}else if (!apolice.getStatus().equals(ListasComuns.STATUS_APOLICE[2])){
						String dataCancelamento = apolice.getDataCancelamento() != null ? new SimpleDateFormat("dd/MM/yyyy").format(apolice.getDataCancelamento()) : "";
						hasChanged = hasChanged	|| !dataCancelamento.equals(spnDataCancelamento.getText());
						hasChanged = hasChanged	|| ((apolice.getMotivoCancelamento() != null ? apolice.getMotivoCancelamento() : "").compareTo(txtMotivoCancelamento.getText()) != 0);
					}
				}
				
				if (hasChanged)
					barNotificacao.mostrarMensagem(	"Altera��es foram efetuadas!",	BarraNotificacao.INFO);

				getPainelPrincipal().setChanged(hasChanged);
			}
		}

		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == cbPlano) {
				PlanoVO vo = (PlanoVO) e.getItem();
				atualizarDadosPlano(vo);
				this.apoliceChange();
			}else if (e.getSource() == cbSituacao) {
				String status = (String) e.getItem();
				configurarCamposSupensaoECancelamento(status);
				this.apoliceChange();
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

}