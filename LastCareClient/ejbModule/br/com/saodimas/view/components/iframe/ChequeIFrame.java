package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ChequeVO;
import br.com.saodimas.view.components.panel.financeiro.cheques.ChequePanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ChequeIFrame extends JInternalFrame {
	private ChequePanel chequePanel;
	
	public ChequeIFrame() {
		super("Novo cheque", false, true, false);
		initialize();
	}
	
	public ChequeIFrame(ChequeVO cheque) {
		super("Editar cheque", false, true, false);
		initialize();
		chequePanel.setCheque(cheque);
	}
	
	public void setCheque(ChequeVO cheque) {
		setTitle((cheque == null)? "Novo cheque" : "Editar cheque");
		chequePanel.setCheque(cheque);
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
		chequePanel = new ChequePanel();
		
		getContentPane().add(chequePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/cidades.gif"));
		pack();
	}

}
