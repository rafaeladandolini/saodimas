package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.view.components.panel.apolice.editar.emprestimo.EmprestimoEquipamentoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class EmprestimoEquipamentoIFrame extends JInternalFrame {
	private EmprestimoEquipamentoPanel equipamentoPanel;
	
	public EmprestimoEquipamentoIFrame() {
		super("Novo Emprestimo", false, true, false);
		initialize();
	}
	
	public void setApolice(ApoliceVO apolice)
	{
		this.equipamentoPanel.setApolice(apolice);
	}
	
	public EmprestimoEquipamentoIFrame(EmprestimoEquipamentoVO vo) {
		super("Editar Colaborador", false, true, false);
		initialize();
		equipamentoPanel.setEmprestimoEquipamento(vo);
	}
	
	public void setEmprestimoEquipamentoVO(EmprestimoEquipamentoVO vo) {
		setTitle((vo == null)? "Novo Emprestimo" : "Editar Empretimo");
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
		equipamentoPanel = new EmprestimoEquipamentoPanel();
		
		getContentPane().add(equipamentoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/emprestimo.gif"));
		setTitle((equipamentoPanel.getEmprestimoEquipamentoVO() == null)? "Novo Emprestimo" : "Editar Emprestimo");
		pack();
		this.setSize(470, 320);
	}
}
