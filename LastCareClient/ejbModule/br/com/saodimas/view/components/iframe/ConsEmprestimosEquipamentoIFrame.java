package br.com.saodimas.view.components.iframe;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.view.components.panel.equipamento.ConsultaEmprestimoPanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class ConsEmprestimosEquipamentoIFrame extends JInternalFrame {
	private ConsultaEmprestimoPanel consEmprestimoPanel;
	
	public ConsEmprestimosEquipamentoIFrame() {
		super("Consulta emprestimos", false, true, false);
		initialize();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) consEmprestimoPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		consEmprestimoPanel = new ConsultaEmprestimoPanel();
		
		getContentPane().add(consEmprestimoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/search.gif"));
		setSize(new Dimension(500,220));
		//this.pack();
		
		
	}
}
