package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ServicoObitoVO;
import br.com.saodimas.view.components.panel.apolice.editar.obito.produtoServico.SelecaoServicoObitoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ObitoServicoIFrame extends JInternalFrame {
	private SelecaoServicoObitoPanel servicoPanel;
	
	public ObitoServicoIFrame() {
		super("Seleção de Serviços", false, true, false);
		initialize();
	}
	
	public void setServico(ServicoObitoVO obitoVO) {
		servicoPanel.setServico(obitoVO);
	} 

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (servicoPanel != null){
			if (visible) servicoPanel.recarregarTabela();
		}
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		servicoPanel = new SelecaoServicoObitoPanel();
		
		getContentPane().add(servicoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/servicos.gif"));
		this.setSize(390, 240);
		//this.pack();
	}
}
