package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.colaborador.ConsultaColaboradorPanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class ConsColaboradorIFrame extends JInternalFrame {
	private ConsultaColaboradorPanel consColaboradorPanel;
	
	public ConsColaboradorIFrame() {
		super("Consulta colaborador", false, true, false);
		initialize();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) consColaboradorPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		consColaboradorPanel = new ConsultaColaboradorPanel();
		
		getContentPane().add(consColaboradorPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/search.gif"));
		pack();
	}
}
