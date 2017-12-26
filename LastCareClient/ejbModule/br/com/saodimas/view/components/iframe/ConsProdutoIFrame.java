package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.produto.ConsultaProdutoPanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class ConsProdutoIFrame extends JInternalFrame {
	private ConsultaProdutoPanel consProdutoPanel;
	
	public ConsProdutoIFrame() {
		super("Consulta produto", false, true, false);
		initialize();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) consProdutoPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		consProdutoPanel = new ConsultaProdutoPanel();
		
		getContentPane().add(consProdutoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/search.gif"));
		pack();
	}
}
