package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.colaborador.LogonPanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class LogonIFrame extends JInternalFrame {
	private LogonPanel logonPanel;
	
	public LogonIFrame() {
		super("Efetuar Logon", false, true, false);
		initialize();
	}
	
	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (logonPanel != null) logonPanel.setVisible(visible);
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		logonPanel = new LogonPanel();
		logonPanel.setVisible(true);
		getContentPane().add(logonPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/auth.gif"));
		pack();
	}
}
