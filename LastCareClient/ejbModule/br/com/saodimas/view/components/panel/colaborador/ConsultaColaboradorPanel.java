package br.com.saodimas.view.components.panel.colaborador;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.spinner.DataSpinner;
import br.com.saodimas.view.components.text.LoginTextField;
import br.com.saodimas.view.util.ExpReg;
import br.com.saodimas.view.util.ListasComuns;


@SuppressWarnings("serial")
public class ConsultaColaboradorPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = " ";
	
	private static final int QUALQUER = 0;
	private static final int NOME = 1;
	private static final int DATA = 2;
	private static final int LOGIN = 3;
	private static final int MATRICULA = 4;
	private static final String[] OPCOES_CONSULTA = {"Qualquer","Nome","Data de Cadastro","Login","Matrícula"}; 
	
	private BarraNotificacao barNotificacao;
	private JComboBox cbOpcoes;
	private JTextField txtNome;
	private LoginTextField txtLogin;
	private JSpinner txtDataIni;
	private JSpinner txtDataFin;
	private JPanel panelPeriodo;
	private JTextField txtMatricula;
	private ButtonGroup bgrStatus;
	private JButton btCancelar;
	private JButton btEnviar;
	
	private JLabel lbOpcoes;
	private JLabel lbNome;
	private JLabel lbLogin;
	private JLabel lbDataCadastro;
	private JLabel lbMatricula;
	private JLabel lbStatus;
	
	private List<JRadioButton> listaStatus;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public ConsultaColaboradorPanel() {
		initialize();
		configure();
	}

	public void limparCampos() {
		txtNome.setText("");
		txtLogin.setText("");
	}

	public void focoPadrao(){
		cbOpcoes.requestFocus();
	}	
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbOpcoes = new JLabel("Consultar por... ", JLabel.RIGHT);
		lbOpcoes.setPreferredSize(DLABEL);
		lbOpcoes.setMinimumSize(DLABEL);
		
		cbOpcoes = new JComboBox(OPCOES_CONSULTA);
		cbOpcoes.setPreferredSize(DFIELDM);
		cbOpcoes.setMinimumSize(DFIELDM);
		cbOpcoes.addItemListener(new EventoComboOpcoes());
		
		lbNome = new JLabel("Que contenha... ", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		lbNome.setVisible(false);
		
		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);
		txtNome.setVisible(false);
		
		lbLogin = new JLabel("Que contenha... ", JLabel.RIGHT);
		lbLogin.setPreferredSize(DLABEL);
		lbLogin.setMinimumSize(DLABEL);
		lbLogin.setVisible(false);
		
		txtLogin = new LoginTextField();
		txtLogin.setPreferredSize(DFIELDM);
		txtLogin.setMinimumSize(DFIELDM);
		txtLogin.setVisible(false);
		
		lbDataCadastro = new JLabel("No período entre...", JLabel.RIGHT);
		lbDataCadastro.setPreferredSize(DLABEL);
		lbDataCadastro.setMinimumSize(DLABEL);
		lbDataCadastro.setVisible(false);
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -500);
		String dataIni = f.format(new Date(c.getTimeInMillis()));
		
		c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		String dataSel = f.format(new Date(c.getTimeInMillis()));
		
		String dataFin = f.format(new Date(Calendar.getInstance().getTimeInMillis()));

		txtDataIni = new DataSpinner(dataSel, dataIni, dataFin);
		txtDataFin = new DataSpinner(dataFin, dataIni, dataFin);

		panelPeriodo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelPeriodo.add(txtDataIni);
		panelPeriodo.add(new JLabel(" a "));
		panelPeriodo.add(txtDataFin);
		panelPeriodo.setMinimumSize(new Dimension(200, 22));
		panelPeriodo.setBorder(BorderFactory.createEmptyBorder());
		panelPeriodo.setVisible(false);
		
		lbMatricula = new JLabel("Que contenha... ", JLabel.RIGHT);
		lbMatricula.setPreferredSize(DLABEL);
		lbMatricula.setMinimumSize(DLABEL);
		lbMatricula.setVisible(false);
		
		CustomDocument matriculaDoc = new CustomDocument(ExpReg.NUMERIC, 10);
		
		txtMatricula = new JTextField("");
		txtMatricula.setDocument(matriculaDoc);
		txtMatricula.setPreferredSize(DFIELDM);
		txtMatricula.setMinimumSize(DFIELDM);
		txtMatricula.setVisible(false);
		
		lbStatus = new JLabel("Com o Status... ", JLabel.RIGHT);
		lbStatus.setPreferredSize(DLABEL);
		lbStatus.setMinimumSize(DLABEL);
		
		bgrStatus = new ButtonGroup();
		listaStatus = new ArrayList<JRadioButton>();
		listaStatus.add(new JRadioButton("Qualquer"));
		bgrStatus.add(listaStatus.get(listaStatus.size()-1));
		for (String situacao : ListasComuns.STATUS_ITENS) {
			listaStatus.add(new JRadioButton(situacao));
			bgrStatus.add(listaStatus.get(listaStatus.size()-1));
		}
		bgrStatus.setSelected(listaStatus.get(0).getModel(), true);
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btEnviar = new JButton("Enviar", new ImageIcon("imagens/submit.gif"));
		btEnviar.addActionListener(new EventoBotaoControle());
		btEnviar.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infUser = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;

		c.gridy = 0; infUser.add(lbOpcoes, c);
		c.gridy = 1; infUser.add(lbNome, c);
		c.gridy = 2; infUser.add(lbDataCadastro, c);
		c.gridy = 3; infUser.add(lbLogin, c);
		c.gridy = 4; infUser.add(lbMatricula, c);
		c.gridy = 5; infUser.add(lbStatus, c);
		
		JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEADING));
		for (JRadioButton rd : listaStatus) {
			panelStatus.add(rd);
		}
		panelStatus.setMinimumSize(new Dimension(200, 22));
		panelStatus.setBorder(BorderFactory.createEmptyBorder());
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(cbOpcoes, c);
		c.fill = GridBagConstraints.NONE;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 1; infUser.add(txtNome, c);
		c.gridy = 2; infUser.add(panelPeriodo, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 3; infUser.add(txtLogin, c);
		c.gridy = 4; infUser.add(txtMatricula, c);
		c.weighty = 1;
		c.gridy = 5; infUser.add(panelStatus, c);
		
		infUser.setBorder(BorderFactory.createTitledBorder("Parâmetros da consulta"));
		adicionarAtalhosComandos(infUser);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btEnviar);
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
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btEnviar.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}
	
//	private Component getPainelPrincipal(){
//		Component c = getParent();
//		while (c != null){
//			if (c instanceof ColaboradorMainPanel) return c;
//			c = c.getParent();
//		}
//		return c;
//	}
	
	private class EventoComboOpcoes implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			boolean showNome		= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[NOME];
			boolean showData		= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[DATA];
			boolean showLogin		= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[LOGIN];
			boolean showMatricula	= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[MATRICULA];
			
			lbNome.setVisible(showNome);
			lbDataCadastro.setVisible(showData);
			lbLogin.setVisible(showLogin);
			lbMatricula.setVisible(showMatricula);
			
			txtNome.setVisible(showNome);
			panelPeriodo.setVisible(showData);
			txtLogin.setVisible(showLogin);
			txtMatricula.setVisible(showMatricula);
			
//			Component c = getPainelPrincipal();
//			try{
//				((ColaboradorMainPanel)c).getIframeConsultaColaborador().pack();
//			}
//			catch(ClassCastException cex){
//				cex.printStackTrace();
//			}
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btCancelar){
//				ConsColaboradorIFrame iframe = getIFrame();
//				Component c = getPainelPrincipal();
//				try{
//					((ColaboradorMainPanel)c).getIframeConsultaColaborador().setVisible(false);
//				}
//				catch(ClassCastException cex){
//					cex.printStackTrace();
//				}
			} 
		}
	}
}
