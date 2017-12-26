package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.view.components.panel.servico.ServicoSelecaoPanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class PlanoServicoIFrame extends JInternalFrame {
	private ServicoSelecaoPanel servicoPanel;
	
	public PlanoServicoIFrame() {
		super("Seleção de Serviços", false, true, false);
		initialize();
	}
	
	public ServicoVO getServico() {
		return servicoPanel.getServicoSelecionado();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (servicoPanel != null){
			if (visible) servicoPanel.recarregarTabela();
			else servicoPanel.limparTabela();
		}
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		servicoPanel = new ServicoSelecaoPanel();
		
		getContentPane().add(servicoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/servicos.gif"));
		pack();
	}
}
