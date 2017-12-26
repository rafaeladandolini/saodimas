package br.com.saodimas.view.components.button;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import br.com.saodimas.view.components.panel.BarraNotificacao;


@SuppressWarnings("serial")
public class ErrorButton extends JButton {
	private String mensagem;
	private Component campo;
	private BarraNotificacao barNotificacao;
	
	public ErrorButton(Component campo, BarraNotificacao destinoMsg) {
		this.campo = campo;
		this.barNotificacao = destinoMsg;
		initialize();
	}
	
	@Override
	public void setVisible(boolean flag) {
		if (campo != null){
				campo.addFocusListener(new FocusAdapter(){
					@Override
					public void focusGained(FocusEvent e) {
						super.focusGained(e);
						if (barNotificacao != null) barNotificacao.mostrarMensagem(mensagem, BarraNotificacao.ERRO);
					}
				});
				campo.setBackground(flag ? new Color(255,229,229) : Color.WHITE);
			}
		super.setVisible(flag);
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
		if (mensagem != null && mensagem.length() > 0){
			setToolTipText(mensagem);
			setVisible(true);
		}
		else{
			this.mensagem = null;
			setVisible(false);
		}
	}
	public Component getCampo() {
		return campo;
	}
	public void setCampo(Component campo) {
		this.campo = campo;
	}
	public BarraNotificacao getBarNotificacao() {
		return barNotificacao;
	}
	public void setBarNotificacao(BarraNotificacao barNotificacao) {
		this.barNotificacao = barNotificacao;
	}
	
	private void mostraMensagem(){
		if (barNotificacao != null && mensagem != null){
			
			new Thread() {
				@Override
				public synchronized void start() {
					barNotificacao.mostrarMensagem(mensagem, BarraNotificacao.ERRO);
					super.start();
				}
				
				@SuppressWarnings("deprecation")
				@Override
				public void run() {
					try{
						Thread.sleep(10000);
						barNotificacao.escondeMensagem();
						if (campo != null)
							campo.setBackground(Color.WHITE);
						stop();
					}
					catch(Exception ex){ex.printStackTrace();}
				}
			}.start();	
			
			if (campo != null){
				campo.requestFocus();
				campo.setBackground(new Color(255, 229, 229));
			}
		}
	}
	
	private void initialize(){
		setVisible(false);
		setFocusable(true);
		setIcon(new ImageIcon("imagens/error.gif"));
		addActionListener(new EventoBotaoErro());
	}
	
	private class EventoBotaoErro implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mostraMensagem();
		}
	}
}
