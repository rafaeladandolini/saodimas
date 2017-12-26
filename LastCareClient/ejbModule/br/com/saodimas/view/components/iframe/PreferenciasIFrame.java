package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.preferencia.PreferenciasPanel;
import br.com.saodimas.view.util.WinManager;




@SuppressWarnings("serial")
public class PreferenciasIFrame extends JInternalFrame {
	private PreferenciasPanel preferenciasPanel;
	
	public PreferenciasIFrame() {
		super("Preferências", false, true, false);
		initialize();
	}
	
	@Override
	public void setVisible(boolean visible) {
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
		preferenciasPanel = new PreferenciasPanel();
		
		getContentPane().add(preferenciasPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/config.gif"));
		pack();
	}
}
