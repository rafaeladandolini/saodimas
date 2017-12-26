package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ContaVO;
import br.com.saodimas.view.components.panel.financeiro.cheques.ContaPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ContaIFrame extends JInternalFrame {
	private ContaPanel contaPanel;
	
	public ContaIFrame() {
		super("Nova conta", false, true, false);
		initialize();
	}
	
	public ContaIFrame(ContaVO conta) {
		super("Editar conta", false, true, false);
		initialize();
		contaPanel.setConta(conta);
	}
	
	public void setConta(ContaVO conta) {
		setTitle((conta == null)? "Nova conta" : "Editar contar");
		contaPanel.setConta(conta);
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
		contaPanel = new ContaPanel();
		
		getContentPane().add(contaPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/cidades.gif"));
		pack();
	}

}
