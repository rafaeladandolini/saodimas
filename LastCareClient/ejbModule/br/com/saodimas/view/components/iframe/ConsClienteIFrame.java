package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.financeiro.cliente.ConsultaClientePanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class ConsClienteIFrame extends JInternalFrame {
	private ConsultaClientePanel consClientePanel;
	
	public ConsClienteIFrame() {
		super("Consulta Cliente", false, true, false);
		initialize();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible){
			consClientePanel.limparCampos();
			consClientePanel.focoPadrao();
		}
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		consClientePanel = new ConsultaClientePanel();
		
		getContentPane().add(consClientePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/search.gif"));
		pack();
	}
}