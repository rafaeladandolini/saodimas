package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.EquipamentoVO;
import br.com.saodimas.view.components.panel.equipamento.EquipamentoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class EquipamentoIFrame extends JInternalFrame {
	private EquipamentoPanel equipamentoPanel;
	
	public EquipamentoIFrame() {
		super("Novo Equipamento", false, true, false);
		initialize();
	}
	
	public EquipamentoIFrame(EquipamentoVO vo) {
		super("Editar Colaborador", false, true, false);
		initialize();
		equipamentoPanel.setEquipamento(vo);
	}
	
	public void setEquipamentoVO(EquipamentoVO vo) {
		setTitle((vo == null)? "Novo Equipamento" : "Editar Equipamento");
		equipamentoPanel.setEquipamento(vo);
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
		equipamentoPanel = new EquipamentoPanel();
		
		getContentPane().add(equipamentoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/equipamento.gif"));
		setTitle((equipamentoPanel.getEquipamentoVO() == null)? "Novo Equipamento" : "Editar Equipamento");
		pack();
	}
}
