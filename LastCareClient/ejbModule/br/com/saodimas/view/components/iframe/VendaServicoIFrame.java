package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.view.components.panel.financeiro.venda.produtoServico.SelecaoServicoVendaPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class VendaServicoIFrame extends JInternalFrame {
	
	private SelecaoServicoVendaPanel servicoPanel;
	
	public VendaServicoIFrame() {
		super("Seleção de Serviços", false, true, false);
		initialize();
	}
	
	public void setServico(ServicoVendaVO obitoVO) {
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
		servicoPanel = new SelecaoServicoVendaPanel();
		
		getContentPane().add(servicoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/servicos.gif"));
		this.setSize(390, 240);
		//this.pack();
	}
}
