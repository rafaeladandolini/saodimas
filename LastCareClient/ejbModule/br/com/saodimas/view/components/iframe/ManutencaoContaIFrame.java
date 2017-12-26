package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.colaborador.AltSenhaPanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class ManutencaoContaIFrame  extends JInternalFrame {
	private AltSenhaPanel altSenhaPanel;
	
	public ManutencaoContaIFrame() {
		super("Alterar Senha", false, true, false);
		initialize();
	}
	
	@Override
	public void setVisible(boolean visible) {
		if(altSenhaPanel != null)
			altSenhaPanel.setCamposAlterarSenha();
		habilitarControles(!visible);
		super.setVisible(visible);
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		altSenhaPanel = new AltSenhaPanel();
		
		getContentPane().add(altSenhaPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/config.gif"));
		pack();
	}
}
