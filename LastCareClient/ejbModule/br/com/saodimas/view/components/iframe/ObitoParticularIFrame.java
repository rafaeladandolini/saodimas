package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.view.components.panel.obito.particular.ObitoParticularPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ObitoParticularIFrame extends JInternalFrame {
	private ObitoParticularPanel obitoPanel;
	
	public ObitoParticularIFrame() {
		super("Novo Óbito", false, true, false);
		initialize();
	}
	
	public ObitoParticularIFrame(ObitoVO obito) {
		super("Editar Óbito", false, true, false);
		initialize();
		obitoPanel.setObito(obito);
		setTitle(obito == null? "Novo Óbito" : "Editar Óbito");
	}
	
	
	public void setObito(ObitoVO obito) {
		setTitle((obito == null)? "Novo Óbito" : "Editar Óbito");
		obitoPanel.colocarModoVisualizacao(false);
		obitoPanel.setObito(obito);
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
		obitoPanel = new ObitoParticularPanel();
		
		getContentPane().add(obitoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/obitos.gif"));
		pack();
		setSize(415,560);
	}

	public ObitoParticularPanel getObitoPanel() {
		return obitoPanel;
	}
	
	public void setConfigVisualizarObito(boolean isTelaConsulta) {
		obitoPanel.colocarModoVisualizacao(isTelaConsulta);
		this.setTitle("Visualização");
	}
}
