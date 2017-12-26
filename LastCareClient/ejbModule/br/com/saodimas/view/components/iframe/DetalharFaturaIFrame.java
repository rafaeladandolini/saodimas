package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.view.components.panel.apolice.editar.fatura.DetatalharFaturaPanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class DetalharFaturaIFrame extends JInternalFrame {
	private DetatalharFaturaPanel detalharPanel;
	
	public DetalharFaturaIFrame() {
		super("Detalhe da fatura", false, true, false);
		initialize();
	}
	
	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (detalharPanel != null) detalharPanel.setVisible(visible);
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		detalharPanel = new DetatalharFaturaPanel();
		detalharPanel.setVisible(true);
		getContentPane().add(detalharPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/auth.gif"));
		pack();
	}
	
	public void setFatura(FaturaVO fatura)
	{
		if(fatura != null)
			detalharPanel.setFatura(fatura);
	}
}
