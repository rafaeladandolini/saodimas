package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.obito.ConsultaObitoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ConsObitoIFrame extends JInternalFrame {
	private ConsultaObitoPanel consultaObito;
	
	public ConsObitoIFrame() {
		super("Consulta Obito", false, true, false);
		initialize();
	}
	
	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		
		if (visible){
			consultaObito.limparCampos();
			consultaObito.focoPadrao();
		}
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		consultaObito = new ConsultaObitoPanel();
		
		getContentPane().add(consultaObito);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/search.gif"));
		this.setSize(450, 240);
		
		
		
	}
}
