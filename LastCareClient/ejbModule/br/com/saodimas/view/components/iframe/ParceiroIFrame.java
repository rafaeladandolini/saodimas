package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ParceiroVO;
import br.com.saodimas.view.components.panel.parceiro.ParceiroPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ParceiroIFrame extends JInternalFrame {
	
	private ParceiroPanel parceiroPanel;
	
	public ParceiroIFrame() {
		super("Novo Parceiro", false, true, false);
		initialize();
	}
	
	public ParceiroIFrame(ParceiroVO parceiro) {
		super("Editar Parceiro", false, true, false);
		initialize();
		parceiroPanel.setParceiro(parceiro);
	}
	
	public ParceiroVO getParceiro() {
		return parceiroPanel.getParceiro();
	}

	public void setParceiro(ParceiroVO parceiro) {
		setTitle((parceiro == null)? "Novo Parceiro" : "Editar Parceiro");
		parceiroPanel.setParceiro(parceiro);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) parceiroPanel.setVisible(visible);
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		parceiroPanel = new ParceiroPanel();
		
		getContentPane().add(parceiroPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/colaborador.gif"));
		setTitle((parceiroPanel.getParceiro() == null)? "Novo Parceiro" : "Editar Parceiro");
		pack();
		this.setSize(390, 420);
	}
}
