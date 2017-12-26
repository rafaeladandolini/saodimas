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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.principal.SaoDimasSettings;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.text.LoginTextField;
import br.com.saodimas.view.util.validators.ValidaLogon;

public class LogonPanel extends JPanel {
	
	private static final long serialVersionUID = -2627580624196239652L;
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	public static final String FORM_NAME = "Logon"; 

	private BarraNotificacao barNotificacao;
	private LoginTextField txtLogin;
	private JPasswordField pwdSenha;
	private JButton btCancelar;
	private JButton btOk;

	private JLabel lbLogin;
	private JLabel lbSenha;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);

	public LogonPanel() {
		initialize();
		configure();
	}
	
	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag){
			SaoDimasSettings settings = new SaoDimasSettings();
			settings.carregarPreferencias();
			txtLogin.setText(settings.getUltimoLogin());
			txtLogin.selectAll();
		}
	}

	@SuppressWarnings("static-access")
	public void limparCampos() {
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, barNotificacao.DICA);
		txtLogin.setText("");
		pwdSenha.setText("");
	}

	private void initialize() {
		
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbLogin = new JLabel("Login: *", JLabel.RIGHT);
		lbLogin.setPreferredSize(DLABEL);
		lbLogin.setMinimumSize(DLABEL);

		txtLogin = new LoginTextField();
		txtLogin.setPreferredSize(DFIELDM);
		txtLogin.setMinimumSize(DFIELDM);
		txtLogin.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) pwdSenha.requestFocus();
				else super.keyPressed(e);
			}
		});

		lbSenha = new JLabel("Senha: *", JLabel.RIGHT);
		pwdSenha = new JPasswordField();
		pwdSenha.setPreferredSize(DFIELDM);
		pwdSenha.setMinimumSize(DFIELDM);
		pwdSenha.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btOk.doClick();
				else super.keyPressed(e);
			}
		});

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		btOk = new JButton("OK", new ImageIcon("imagens/accept.gif"));
		btOk.addActionListener(new EventoBotaoControle());
		btOk.setHorizontalAlignment(JButton.LEFT);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infUser = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;

		c.gridy = 0; infUser.add(lbLogin, c);
		c.gridy = 1; infUser.add(lbSenha, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(txtLogin, c);
		c.weighty = 1;
		c.gridy = 1; infUser.add(pwdSenha, c);

		infUser.setBorder(BorderFactory.createTitledBorder("Identificação"));

		setLayout(new BorderLayout());

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btOk);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));

		add(barNotificacao, BorderLayout.NORTH);
		add(infUser, BorderLayout.CENTER);
		add(controle, BorderLayout.SOUTH);
	}

	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof CustomLayeredPanel) return c;
			c = c.getParent();
		}

		return c;
	}	

	private class EventoBotaoControle implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			CustomLayeredPanel c = (CustomLayeredPanel)getPainelPrincipal();
			if (e.getSource() == btCancelar){
				c.getIframeLogon().setVisible(false);
			}
			else if (e.getSource() == btOk){
				try {
					validarCampos();
					SaoDimasMain.colaborador = Controller.getInstance().autenticarColaborador(txtLogin.getText(), new String(pwdSenha.getPassword()));
					SaoDimasSettings settings = new SaoDimasSettings();
					settings.carregarPreferencias();
					settings.setUltimoLogin(SaoDimasMain.colaborador.getLogin());
					settings.salvarPreferencias();

					c.getIframeLogon().setVisible(false);
					limparCampos();
					
				}catch (MensagemSaoDimasException ex) {
					ex.printStackTrace();
					barNotificacao.mostrarMensagem(ex.getMessage(), barNotificacao.ERRO);
				} catch (Exception et) {
					et.printStackTrace();
					barNotificacao.mostrarMensagem(et.getMessage(), barNotificacao.ERRO);
				}
			}
		
		}
	}
	
	private void validarCampos() throws MensagemSaoDimasException
	{
		ValidaLogon.validaCamposLogon(txtLogin.getText(), new String(pwdSenha.getPassword()));
	}
}	