package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.servico.ConsultaServicoPanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class ConsServicoIFrame extends JInternalFrame {
	private ConsultaServicoPanel consServicoPanel;
	
	public ConsServicoIFrame() {
		super("Consulta serviço", false, true, false);
		initialize();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) consServicoPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		consServicoPanel = new ConsultaServicoPanel();
		
		getContentPane().add(consServicoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/search.gif"));
		pack();
	}
}
