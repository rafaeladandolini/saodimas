package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.view.components.panel.servico.ServicoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ServicoIFrame extends JInternalFrame {
	private ServicoPanel servicoPanel;
	
	public ServicoIFrame() {
		super("Novo Serviço", false, true, false);
		initialize();
	}
	
	public ServicoIFrame(ServicoVO servico) {
		super("Editar Serviço", false, true, false);
		initialize();
		servicoPanel.setServico(servico);
	}
	
	public ServicoVO getServico() {
		return servicoPanel.getServico();
	}

	public void setServico(ServicoVO servico) {
		setTitle((servico == null)? "Novo Serviço" : "Editar Serviço");
		servicoPanel.setServico(servico);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) servicoPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		servicoPanel = new ServicoPanel();
		
		getContentPane().add(servicoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/servicos.gif"));
		setTitle((servicoPanel.getServico() == null)? "Novo Serviço" : "Editar Serviço");
		pack();
	}
}
