package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.view.components.panel.cidade.CidadePanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class CidadeIFrame extends JInternalFrame {
	private CidadePanel cidadePanel;
	
	public CidadeIFrame() {
		super("Nova cidade", false, true, false);
		initialize();
	}
	
	public CidadeIFrame(CidadeVO cidade) {
		super("Editar cidade", false, true, false);
		initialize();
		cidadePanel.setCidade(cidade);
	}
	
	public CidadeVO getCidade() {
		return cidadePanel.getCidade();
	}

	public void setCidade(CidadeVO cidade) {
		setTitle((cidade == null)? "Nova Cidade" : "Editar Cidade");
		cidadePanel.setCidade(cidade);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) cidadePanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		cidadePanel = new CidadePanel();
		
		getContentPane().add(cidadePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/cidades.gif"));
		setTitle((cidadePanel.getCidade() == null)? "Nova Cidade" : "Editar Cidade");
		pack();
	}

}
