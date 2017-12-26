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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.text.LoginTextField;
import br.com.saodimas.view.util.validators.ValidaLogon;

public class AltSenhaPanel extends JPanel {
	
	private static final long serialVersionUID = 8101369435393715845L;

	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	public static final String FORM_NAME = "Alterar Senha"; 

	private BarraNotificacao barNotificacao;
	private LoginTextField txtLogin;
	private JPasswordField pwdSenhaAtual;
	private JPasswordField pwdNovaSenha;
	private JPasswordField pwdConfSenha;
	private JButton btCancelar;
	private JButton btOk;
	
	private JLabel lbLogin;
	private JLabel lbSenhaAtual;
	private JLabel lbNovaSenha;
	private JLabel lbConfSenha;

	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public AltSenhaPanel() {
		initialize();
		configure();
		setCamposAlterarSenha();
	}

	public void setCamposAlterarSenha()
	{
		ColaboradorVO colaborador = SaoDimasMain.colaborador;
		if(colaborador != null)
			txtLogin.setText(colaborador.getLogin());
	}
	
	@SuppressWarnings("static-access")
	public void limparCampos() {
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, barNotificacao.DICA);
		pwdSenhaAtual.setText("");
		pwdNovaSenha.setText("");
		pwdConfSenha.setText("");
	}

	private void initialize() {
		
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbLogin = new JLabel("Login:  ", JLabel.RIGHT);
		lbLogin.setPreferredSize(DLABEL);
		lbLogin.setMinimumSize(DLABEL);
		
		txtLogin = new LoginTextField();
		txtLogin.setPreferredSize(DFIELDM);
		txtLogin.setMinimumSize(DFIELDM);
		txtLogin.setEditable(false);
		
		lbSenhaAtual = new JLabel("Senha Atual: *", JLabel.RIGHT);
		pwdSenhaAtual = new JPasswordField();
		pwdSenhaAtual.setPreferredSize(DFIELDM);
		pwdSenhaAtual.setMinimumSize(DFIELDM);
		
		lbNovaSenha = new JLabel("Nova Senha: *", JLabel.RIGHT);
		pwdNovaSenha = new JPasswordField();
		pwdNovaSenha.setPreferredSize(DFIELDM);
		pwdNovaSenha.setMinimumSize(DFIELDM);
		
		lbConfSenha = new JLabel("Confirme a senha: *", JLabel.RIGHT);
		pwdConfSenha = new JPasswordField();
		pwdConfSenha.setPreferredSize(DFIELDM);
		pwdConfSenha.setMinimumSize(DFIELDM);
		
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
		c.gridy = 1; infUser.add(lbSenhaAtual, c);
		c.gridy = 2; infUser.add(lbNovaSenha, c);
		c.gridy = 3; infUser.add(lbConfSenha, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(txtLogin, c);
		c.gridy = 1; infUser.add(pwdSenhaAtual, c);
		c.gridy = 2; infUser.add(pwdNovaSenha, c);
		c.weighty = 1;
		c.gridy = 3; infUser.add(pwdConfSenha, c);
		
		infUser.setBorder(BorderFactory.createTitledBorder("Manutenção da Conta"));
		
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
			if (e.getSource() == btCancelar){
				Component c = getPainelPrincipal();
				try{
					((CustomLayeredPanel)c).getIframeManutencaoConta().setVisible(false);
				}
				catch(ClassCastException cex){
					cex.printStackTrace();
				}
			}
			if (e.getSource() == btOk){
				
				try {
					validaCampos();
					ColaboradorVO colaborador = SaoDimasMain.colaborador;
					colaborador.setSenha(new String(pwdNovaSenha.getPassword()));
					SaoDimasMain.colaborador = Controller.getInstance().updateColaborador(colaborador);
					Component c = getPainelPrincipal();

					if (c != null)((CustomLayeredPanel) c).getIframeManutencaoConta().setVisible(false);
					limparCampos();
				} catch (ClassCastException cex) {
					cex.printStackTrace();
				} catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(),	barNotificacao.ERRO);
					ex.printStackTrace();
				}
					
				
			} 
		}
	}
	
	private void validaCampos()throws MensagemSaoDimasException
	{
		ValidaLogon.validaCamposAltSenha(new String(pwdSenhaAtual.getPassword()),
										 new String(pwdNovaSenha.getPassword()),
										 new String(pwdConfSenha.getPassword()));
	}
}	