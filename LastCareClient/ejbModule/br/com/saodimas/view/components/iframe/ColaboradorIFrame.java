package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.view.components.panel.colaborador.ColaboradorPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ColaboradorIFrame extends JInternalFrame {
	private ColaboradorPanel colaboradorPanel;
	
	public ColaboradorIFrame() {
		super("Novo Colaborador", false, true, false);
		initialize();
	}
	
	public ColaboradorIFrame(ColaboradorVO colaborador) {
		super("Editar Colaborador", false, true, false);
		initialize();
		colaboradorPanel.setColaborador(colaborador);
	}
	
	public ColaboradorVO getColaborador() {
		return colaboradorPanel.getColaborador();
	}

	public void setColaborador(ColaboradorVO colaborador) {
		setTitle((colaborador == null)? "Novo Colaborador" : "Editar Colaborador");
		colaboradorPanel.setColaborador(colaborador);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) colaboradorPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		colaboradorPanel = new ColaboradorPanel();
		
		getContentPane().add(colaboradorPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/colaborador.gif"));
		setTitle((colaboradorPanel.getColaborador() == null)? "Novo Colaborador" : "Editar Colaborador");
		pack();
	}
}
