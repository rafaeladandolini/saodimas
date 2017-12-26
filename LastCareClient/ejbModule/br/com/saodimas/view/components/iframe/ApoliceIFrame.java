package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.apolice.ApolicePanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class ApoliceIFrame extends JInternalFrame {
	private ApolicePanel apolicePanel;
	
	public ApoliceIFrame() {
		super("Nova Apólice", false, true, false);
		initialize();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible){
			apolicePanel.focoPadrao();
			apolicePanel.setVisible(visible);
		}		
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		apolicePanel = new ApolicePanel();
		
		getContentPane().add(apolicePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/apolice.gif"));
		setTitle("Nova Apólice");
		pack();
	}
}

