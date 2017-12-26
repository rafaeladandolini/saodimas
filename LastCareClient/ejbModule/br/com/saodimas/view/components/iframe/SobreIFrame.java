package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.sobre.SobrePanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class SobreIFrame extends JInternalFrame {
	private SobrePanel sobrePanel;
	
	public SobreIFrame() {
		super("Sobre a Assistência Familiar São Dimas", false, true, false);
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
		sobrePanel = new SobrePanel();
		
		getContentPane().add(sobrePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/info.gif"));
		pack();
	}
}
