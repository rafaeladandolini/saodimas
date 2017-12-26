package br.com.saodimas.view.components.panel.preferencia;

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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.principal.SaoDimasSettings;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class PreferenciasPanel extends JPanel {
	public static final String FORM_NAME = "Preferências";
	private static final String MENSAGEM_PADRAO = "Configure o Proxy se necessário ou mude a aparência do sistema!";

	private SaoDimasSettings settings;

	private JRadioButton rdDireto;
	private JRadioButton rdProxy;
	private ButtonGroup bgrTipoAcesso;
	private JTextField txtEndProxy;
	private JTextField txtLoginProxy;
	private JTextField txtPortaProxy;
	private JPasswordField passSenhaProxy;
	private JComboBox cbTemas;
	
	private JLabel lbEndProxy;
	private JLabel lbLoginProxy;
	private JLabel lbPortaProxy;
	private JLabel lbSenhaProxy;
	private JLabel lbTemas;
	
	private BarraNotificacao barNotificacao;
	private JButton btOk;
	private JButton btCancelar;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	private static final Dimension DFIELDP = new Dimension(60,22);
	
	public PreferenciasPanel() {
		initialize();
		configure();
	}
	
	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag) requestFocus();
	}

	@Override
	public void requestFocus() {
		if (rdDireto.isSelected()) rdDireto.requestFocus();
		else rdProxy.requestFocus();
	}

	private void initialize() {
		settings = Controller.getInstance().carregarPreferencias();
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		rdDireto = new JRadioButton("Conexão Direta");
		rdDireto.addActionListener(new EventoRadioOpcao());
		
		rdProxy = new JRadioButton("Conexão com Proxy");
		rdProxy.addActionListener(new EventoRadioOpcao());
		
		bgrTipoAcesso = new ButtonGroup();
		bgrTipoAcesso.add(rdDireto);
		bgrTipoAcesso.add(rdProxy);
		
		lbEndProxy = new JLabel("Servidor:  ", JLabel.RIGHT);
		lbEndProxy.setPreferredSize(DLABEL);
		lbEndProxy.setMinimumSize(DLABEL);
				
		txtEndProxy = new JTextField();
		txtEndProxy.setPreferredSize(DFIELDM);
		txtEndProxy.setMinimumSize(DFIELDM);
		txtEndProxy.setText(settings.getEnderecoProxy());
		
		lbPortaProxy = new JLabel("Porta:  ", JLabel.RIGHT);
		lbPortaProxy.setPreferredSize(DLABEL);
		lbPortaProxy.setMinimumSize(DLABEL);
		
		CustomDocument portaDoc = new CustomDocument("^([\\p{Digit}]+)$", 4);
		txtPortaProxy = new JTextField();
		txtPortaProxy.setDocument(portaDoc);
		txtPortaProxy.setPreferredSize(DFIELDP);
		txtPortaProxy.setMinimumSize(DFIELDP);
		txtPortaProxy.setText(settings.getPortaProxy());
				
		lbLoginProxy = new JLabel("Login:  ", JLabel.RIGHT);
		lbLoginProxy.setPreferredSize(DLABEL);
		lbLoginProxy.setMinimumSize(DLABEL);
		
		txtLoginProxy = new JTextField();
		txtLoginProxy.setPreferredSize(DFIELDM);
		txtLoginProxy.setMinimumSize(DFIELDM);
		txtLoginProxy.setText(settings.getLoginProxy());
		
		lbSenhaProxy = new JLabel("Senha:  ", JLabel.RIGHT);
		lbSenhaProxy.setPreferredSize(DLABEL);
		lbSenhaProxy.setMinimumSize(DLABEL);
		
		passSenhaProxy = new JPasswordField();
		passSenhaProxy.setPreferredSize(DFIELDM);
		passSenhaProxy.setMinimumSize(DFIELDM);
		passSenhaProxy.setText(settings.getSenhaProxy());
		
		lbTemas = new JLabel("Tema:  ", JLabel.RIGHT);
		lbTemas.setPreferredSize(DLABEL);
		lbTemas.setMinimumSize(DLABEL);
		
		cbTemas = new JComboBox(ListasComuns.TEMAS);
		LookAndFeel tema = null;
		for(int i = 0; i < ListasComuns.TEMAS.length; i++){
			if (ListasComuns.TEMAS[i].getName().compareTo(settings.getNomeTema()) == 0){
				tema = ListasComuns.TEMAS[i];
				break;
			}
		}
		if (tema != null) cbTemas.setSelectedItem(tema);
		cbTemas.addItemListener(new EventoComboTema());
		
		
		btOk = new JButton("OK", new ImageIcon("imagens/accept.gif"));
		btOk.addActionListener(new EventoBotaoComando());
		btOk.setHorizontalAlignment(JButton.LEFT);
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoComando());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		if (settings.isUsarProxy()) 
			rdProxy.doClick();
		else
			rdDireto.doClick();
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();

		c = new GridBagConstraints();
		JPanel infConexao = new JPanel(new GridBagLayout());
		
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridy = 0; infConexao.add(rdDireto, c);
		c.gridy = 1; infConexao.add(rdProxy, c);
		c.gridwidth = 1;
		c.gridy = 2; infConexao.add(lbEndProxy, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 3; infConexao.add(lbPortaProxy, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 4; infConexao.add(lbLoginProxy, c);
		c.gridy = 5; infConexao.add(lbSenhaProxy, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 2; infConexao.add(txtEndProxy, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 3; infConexao.add(txtPortaProxy, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 4; infConexao.add(txtLoginProxy, c);
		c.weighty = 1;
		c.gridy = 5; infConexao.add(passSenhaProxy, c);
		
		infConexao.setBorder(BorderFactory.createTitledBorder("Acesso à Internet"));
		
		c = new GridBagConstraints();
		JPanel infAparencia = new JPanel(new GridBagLayout());
		
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infAparencia.add(lbTemas, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infAparencia.add(cbTemas, c);
		c.weighty = 1;
		c.gridy = 5; infAparencia.add(new JLabel(), c);
		
		infAparencia.setBorder(BorderFactory.createTitledBorder("Aparência"));
		
		JPanel panelPrincipal = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 0;
		c.gridy = 0; panelPrincipal.add(infConexao, c);

		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1; panelPrincipal.add(infAparencia, c);
		
		setLayout(new BorderLayout());
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		controle.add(btOk);
		controle.add(btCancelar);
		
		add(barNotificacao, BorderLayout.NORTH);
		add(panelPrincipal, BorderLayout.CENTER);
		add(controle, BorderLayout.SOUTH);
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof CustomLayeredPanel) return c;
			c = c.getParent();
		}
		return c;
	}
	
	private class EventoRadioOpcao implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			lbEndProxy.setEnabled(e.getSource() == rdProxy);
			lbPortaProxy.setEnabled(e.getSource() == rdProxy);
			lbLoginProxy.setEnabled(e.getSource() == rdProxy);
			lbSenhaProxy.setEnabled(e.getSource() == rdProxy);
			
			txtEndProxy.setEnabled(e.getSource() == rdProxy);
			txtPortaProxy.setEnabled(e.getSource() == rdProxy);
			txtLoginProxy.setEnabled(e.getSource() == rdProxy);
			passSenhaProxy.setEnabled(e.getSource() == rdProxy);
			
			settings.setUsarProxy(e.getSource() == rdProxy);
		}
	}
	
	private class EventoComboTema implements ItemListener{
		public void itemStateChanged(ItemEvent ev) {
			if (settings.getNomeTema().compareTo(((LookAndFeel)ev.getItem()).getName()) == 0)
				barNotificacao.mostrarMensagem("Será necessário reiniciar o programa!", BarraNotificacao.AVISO);
			else
				barNotificacao.escondeMensagem();
		}
	}
	
	private class EventoBotaoComando implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			CustomLayeredPanel c = (CustomLayeredPanel)getPainelPrincipal();
			if (e.getSource() == btOk){
				settings.setEnderecoProxy(txtEndProxy.getText());
				settings.setPortaProxy(txtPortaProxy.getText());
				settings.setLoginProxy(txtLoginProxy.getText());
				settings.setSenhaProxy(new String(passSenhaProxy.getPassword()));
				settings.setUsarProxy(rdProxy.isSelected());
				if (settings.getNomeTema().compareTo(((LookAndFeel)cbTemas.getSelectedItem()).getName()) != 0){
					settings.setNomeTema(((LookAndFeel)cbTemas.getSelectedItem()).getName());
					if (c.mostraConfirmacao("Confirmação", "Deseja encerrar o sistema agora, para aplicar a alteração do tema?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
						settings.salvarPreferencias();
						WinManager.getJanelaInicial().dispose();
					}
				}
				settings.salvarPreferencias();
				c.getIframePrefs().setVisible(false);
			}
			else if (e.getSource() == btCancelar){
				c.getIframePrefs().setVisible(false);
			}
		}
	}
}