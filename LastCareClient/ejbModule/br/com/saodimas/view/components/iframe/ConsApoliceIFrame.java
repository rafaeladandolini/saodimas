package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.apolice.ConsultaApolicePanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class ConsApoliceIFrame extends JInternalFrame {
	private ConsultaApolicePanel consApolicePanel;
	
	public ConsApoliceIFrame() {
		super("Consulta apólice", false, true, false);
		initialize();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible){
			consApolicePanel.limparCampos();
			consApolicePanel.focoPadrao();
		}
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		consApolicePanel = new ConsultaApolicePanel();
		
		getContentPane().add(consApolicePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/search.gif"));
		pack();
	}
}