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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.util.ExpReg;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class ConsultaClientePanel extends JPanel {
	private static final String MENSAGEM_PADRAO = " ";
	private static final int QUALQUER = 0;
	private static final int NOME = 1;
	private static final int DOCUMENTO = 2;
	private static final int CIDADE = 3;
	private static final int ENDERECO = 4;
	private static final int OBSERVACAO = 5;

	private static final String[] OPCOES_CONSULTA = {"Qualquer","Nome","Documento", "Cidade", "Endereço", "Observação"}; 

	private BarraNotificacao barNotificacao;
	private JComboBox<String> cbOpcoes;
	private JComboBox<CidadeVO> cbCidades;

	private JTextField txtNome;
	private JTextField txtDocumento;
	private JTextField txtEndereco;
	private JTextField txtObservacao;
	
	private ButtonGroup bgrStatus;
	private JRadioButton rdQualquer;
	private JRadioButton rdAtivo;
	private JRadioButton rdSuspenso;
	private JRadioButton rdCancelado;
	private JButton btCancelar;
	private JButton btEnviar;

	private JLabel lbOpcoes;
	private JLabel lbNome;
	private JLabel lbDocumento;
	private JLabel lbStatus;
	private JLabel lbCidades;
	private JLabel lbEndereco;
	private JLabel lbObservacao;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);

	public ConsultaClientePanel() {
		initialize();
		configure();
	}

	public void limparCampos() {
		txtNome.setText("");
		txtDocumento.setText("");
		txtEndereco.setText("");
		txtObservacao.setText("");
		rdQualquer.setSelected(true);
		cbOpcoes.setSelectedIndex(1);
		barNotificacao.escondeMensagem();
		
	}

	public void focoPadrao(){
		txtNome.requestFocus();
	}
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);

		lbOpcoes = new JLabel("Consultar por... ", JLabel.RIGHT);
		lbOpcoes.setPreferredSize(DLABEL);
		lbOpcoes.setMinimumSize(DLABEL);

		cbOpcoes = new JComboBox<String>(OPCOES_CONSULTA);
		cbOpcoes.setSelectedIndex(1);
		cbOpcoes.setPreferredSize(DFIELDM);
		cbOpcoes.setMinimumSize(DFIELDM);
		cbOpcoes.addItemListener(new EventoComboOpcoes());

		lbCidades = new JLabel("Cidade.: ", JLabel.RIGHT);
		lbCidades.setPreferredSize(DLABEL);
		lbCidades.setMinimumSize(DLABEL);
		lbCidades.setVisible(false);
		
		List<CidadeVO> list = Controller.getInstance().getCidadeAll();
		CidadeVO[] array = new CidadeVO[list.size()];
		
		cbCidades = new JComboBox<CidadeVO>(list.toArray(array));
		cbCidades.setSelectedIndex(1);
		cbCidades.setPreferredSize(DFIELDM);
		cbCidades.setMinimumSize(DFIELDM);
		cbCidades.setVisible(false);
				
	
		lbNome = new JLabel("Que contenha... ", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		lbNome.setVisible(false);
		
		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);
		txtNome.setVisible(false);
		
		lbDocumento = new JLabel("Igual a... ", JLabel.RIGHT);
		lbDocumento.setPreferredSize(DLABEL);
		lbDocumento.setMinimumSize(DLABEL);
		lbDocumento.setVisible(false);

		CustomDocument doc = new CustomDocument(ExpReg.NUMERIC, 14);
		txtDocumento = new JTextField();
		txtDocumento.setDocument(doc);
		txtDocumento.setPreferredSize(DFIELDM);
		txtDocumento.setVisible(false);

		lbEndereco = new JLabel("Que contenha... ", JLabel.RIGHT);
		lbEndereco.setPreferredSize(DLABEL);
		lbEndereco.setMinimumSize(DLABEL);
		lbEndereco.setVisible(false);
		
		CustomDocument txtEnd = new CustomDocument(50);
		txtEndereco = new JTextField();
		txtEndereco.setDocument(txtEnd);
		txtEndereco.setPreferredSize(DFIELDM);
		txtEndereco.setVisible(false);
		
		lbObservacao = new JLabel("Que contenha... ", JLabel.RIGHT);
		lbObservacao.setPreferredSize(DLABEL);
		lbObservacao.setMinimumSize(DLABEL);
		lbObservacao.setVisible(false);
		
		CustomDocument txtObs = new CustomDocument(50);
		txtObservacao = new JTextField();
		txtObservacao.setDocument(txtObs);
		txtObservacao.setPreferredSize(DFIELDM);
		txtObservacao.setVisible(false);
		
		lbStatus = new JLabel("Com o Status... ", JLabel.RIGHT);
		lbStatus.setPreferredSize(DLABEL);
		lbStatus.setMinimumSize(DLABEL);

		bgrStatus = new ButtonGroup();
		rdQualquer = new JRadioButton("Qualquer");
		rdAtivo = new JRadioButton(ListasComuns.STATUS_ITENS[0]);
		rdSuspenso = new JRadioButton(ListasComuns.STATUS_ITENS[2]);
		rdCancelado = new JRadioButton(ListasComuns.STATUS_ITENS[1]);

		bgrStatus.add(rdQualquer);
		bgrStatus.add(rdAtivo);
		bgrStatus.add(rdSuspenso);
		bgrStatus.add(rdCancelado);

		bgrStatus.setSelected(rdQualquer.getModel(), true);

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		btEnviar = new JButton("Enviar", new ImageIcon("imagens/submit.gif"));
		btEnviar.addActionListener(new EventoBotaoControle());
		btEnviar.setHorizontalAlignment(JButton.LEFT);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infApolice = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;

		c.gridy = 0; infApolice.add(lbOpcoes, c);
		c.gridy = 1; infApolice.add(lbNome, c);
		c.gridy = 2; infApolice.add(lbDocumento, c);
		c.gridy = 3; infApolice.add(lbCidades, c);
		c.gridy = 4; infApolice.add(lbEndereco, c);
		c.gridy = 5; infApolice.add(lbObservacao, c);
		c.gridy = 6; infApolice.add(lbStatus, c);
		

		JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelStatus.add(rdQualquer);
		panelStatus.add(rdAtivo);
		panelStatus.add(rdSuspenso);
		panelStatus.add(rdCancelado);
		panelStatus.setMinimumSize(new Dimension(200, 22));
		panelStatus.setBorder(BorderFactory.createEmptyBorder());

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infApolice.add(cbOpcoes, c);
		c.gridy = 1; infApolice.add(txtNome, c);
		c.gridy = 2; infApolice.add(txtDocumento, c);
		c.gridy = 3; infApolice.add(cbCidades, c);
		c.gridy = 4; infApolice.add(txtEndereco, c);
		c.gridy = 5; infApolice.add(txtObservacao, c);
		
		c.weighty = 1;
		c.gridy = 6; infApolice.add(panelStatus, c);
		

		infApolice.setBorder(BorderFactory.createTitledBorder("Parâmetros da consulta"));
		adicionarAtalhosComandos(infApolice);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btEnviar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);

		JPanel panelApolice = new JPanel(new BorderLayout());
		panelApolice.add(barNotificacao, BorderLayout.NORTH);
		panelApolice.add(infApolice, BorderLayout.CENTER);
		panelApolice.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelApolice);

		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		formPrincipal.add(panelApolice);
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
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btEnviar.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}

	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c.getClass() == ClienteMainPanel.class) return c;
			c = c.getParent();
		}

		return c;
	}		

	private String getSelectedStatus(){
		if(rdAtivo.isSelected())return ListasComuns.STATUS_ITENS[0];
		else if(rdCancelado.isSelected())return ListasComuns.STATUS_ITENS[1];
		else if(rdSuspenso.isSelected())return ListasComuns.STATUS_ITENS[2];
		else return "Qualquer";

	}

	private String[] getParamentros()
	{
		List<String> p = new ArrayList<String>();
		String[] parametros = null;

		if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[NOME]))
			p.add(txtNome.getText());
		else if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[CIDADE]))
			p.add(((CidadeVO)cbCidades.getSelectedItem()).getId().toString());
		else if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[DOCUMENTO]))
			p.add(txtDocumento.getText());
		else if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[ENDERECO]))	
			p.add(txtEndereco.getText());
		else if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[OBSERVACAO]))	
			p.add(txtObservacao.getText());
		else
			p = null;

		if(p != null){
			parametros = new String[p.size()];
			p.toArray(parametros);
		}
		return parametros;
	}

	private void consultaApolicesAtivas(){
		ClienteMainPanel c = (ClienteMainPanel)getPainelPrincipal();
		String tipo = cbOpcoes.getSelectedItem().toString();
		List <ClienteVO> apolicesEncontradas = Controller.getInstance().consultaClientes(tipo, getParamentros(), getSelectedStatus());
		if (apolicesEncontradas != null && apolicesEncontradas.size() > 0){
				c.carregarClientesTable((apolicesEncontradas));
				c.mostrarMensagem("Consulta efetuada com sucesso! " + (apolicesEncontradas.size() == 1 ? " Apenas 1 apólice encontrada ..." : apolicesEncontradas.size() + " apólices encontradas ..."), BarraNotificacao.SUCESSO);
				c.getIframeConsultaCliente().setVisible(false);
				/*if(apolicesEncontradas.size() == 1)
				{
					c.abrirApoliceDiretoPesquisa();
				}	*/
		}
		else{
			c.carregarClientesTable(null);
			c.mostrarMensagem("", BarraNotificacao.DEFAULT);
			barNotificacao.mostrarMensagem("Nenhuma apólice encontrada com esses critérios...", BarraNotificacao.ERRO);
		}
	}


	private class EventoComboOpcoes implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			
			
			boolean showNome		= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[NOME];
			boolean showDocumento	= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[DOCUMENTO];
			boolean showCidade		= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[CIDADE];
			boolean showEndereco	= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[ENDERECO];
			boolean showObservaocao	= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[OBSERVACAO];
			
		
			lbNome.setVisible(showNome);
			lbDocumento.setVisible(showDocumento);
			lbCidades.setVisible(showCidade);
			lbEndereco.setVisible(showEndereco);
			lbObservacao.setVisible(showObservaocao);
			
			txtNome.setVisible(showNome);
			txtDocumento.setVisible(showDocumento);
			
			cbCidades.setVisible(showCidade);
			txtEndereco.setVisible(showEndereco);
			txtObservacao.setVisible(showObservaocao);
		

			Component c = getPainelPrincipal();
			try{
				((ClienteMainPanel)c).getIframeConsultaCliente().pack();
			}
			catch(ClassCastException cex){}
		}
	}
	
	private void validarCamposPesquisa()throws MensagemSaoDimasException
	{
		if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[NOME]) && (txtNome.getText() == null || txtNome.getText().trim().equals("")))
				throw new MensagemSaoDimasException("Você deve informar um nome para busca."); 
		
		if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[ENDERECO]) && (txtEndereco.getText() == null || txtEndereco.getText().trim().equals("")))
			throw new MensagemSaoDimasException("Você deve informar um endereco para busca.");
		
		if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[OBSERVACAO]) && (txtObservacao.getText() == null || txtObservacao.getText().trim().equals("")))
			throw new MensagemSaoDimasException("Você deve informar um texto para busca.");
				
		else if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[DOCUMENTO]) && (txtDocumento.getText() == null || txtDocumento.getText().trim().equals("")))
			throw new MensagemSaoDimasException("Você deve informar num. do documneto.");
		
		
	}

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ClienteMainPanel c = (ClienteMainPanel)getPainelPrincipal();
			
			if (e.getSource() == btCancelar)
				c.getIframeConsultaCliente().setVisible(false);
			
			else if (e.getSource() == btEnviar)
			{
				try{
					validarCamposPesquisa();
					consultaApolicesAtivas();
				}catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(), BarraNotificacao.ERRO);
				}
			}

		}
	}
}
