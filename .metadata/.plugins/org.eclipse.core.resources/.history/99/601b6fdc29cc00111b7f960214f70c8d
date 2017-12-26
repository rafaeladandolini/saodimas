package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.RelacaoVO;
import br.com.saodimas.view.components.panel.relacao.RelacaoTitularPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class RelacaoTitularIFrame extends JInternalFrame {
	private RelacaoTitularPanel relacaoTitularPanel;
	
	public RelacaoTitularIFrame() {
		super("Nova Relação", false, true, false);
		initialize();
	}
	
	public RelacaoTitularIFrame(RelacaoVO relacaoTitular) {
		super("Editar Relação", false, true, false);
		initialize();
		relacaoTitularPanel.setRelacaoTitular(relacaoTitular);
	}
	
	public RelacaoVO getRelacaoTitular() {
		return relacaoTitularPanel.getRelacaoTitular();
	}

	public void setRelacao(RelacaoVO relacao) {
		setTitle((relacao == null)? "Nova Relação" : "Editar Relação");
		relacaoTitularPanel.setRelacaoTitular(relacao);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) relacaoTitularPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		relacaoTitularPanel = new RelacaoTitularPanel();
		
		getContentPane().add(relacaoTitularPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/relacaoTitular.gif"));
		setTitle((relacaoTitularPanel.getRelacaoTitular() == null)? "Nova Relação" : "Editar Relação");
		pack();
	}

}
