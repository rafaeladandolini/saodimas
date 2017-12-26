package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.view.components.panel.apolice.editar.emprestimo.DevolucaoEmprestimoEquipamentoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class DevolucaoEmprestimoEquipamentoIFrame extends JInternalFrame {
	private DevolucaoEmprestimoEquipamentoPanel equipamentoPanel;
	
	public DevolucaoEmprestimoEquipamentoIFrame() {
		super("Devolução", false, true, false);
		initialize();
	}
		
	public DevolucaoEmprestimoEquipamentoIFrame(EmprestimoEquipamentoVO vo) {
		super("Devolução ", false, true, false);
		initialize();
		equipamentoPanel.setEmprestimoEquipamento(vo);
	}
	
	public void setEmprestimoEquipamentoVO(EmprestimoEquipamentoVO vo) {
		setTitle("Devolução");
		equipamentoPanel.setEmprestimoEquipamento(vo);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) equipamentoPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		equipamentoPanel = new DevolucaoEmprestimoEquipamentoPanel();
		
		getContentPane().add(equipamentoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/colaborador.gif"));
		setTitle("Devolução");
		pack();
	}
}
