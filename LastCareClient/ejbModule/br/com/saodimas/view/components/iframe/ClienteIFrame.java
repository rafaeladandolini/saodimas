package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.view.components.panel.financeiro.cliente.ClientePanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ClienteIFrame extends JInternalFrame {
	private ClientePanel clientePanel;
	
	public ClienteIFrame() {
		super("Novo Cliente", false, true, false);
		initialize();
	}
	
	public ClienteIFrame(ClienteVO cliente) {
		super("Editar Cliente", false, true, false);
		initialize();
		clientePanel.setCliente(cliente);
	}
	
	public ClienteVO getCliente() {
		return clientePanel.getClienteVO();
	}

	public void setCliente(ClienteVO cliente) {
		setTitle((cliente == null)? "Novo Cliente" : "Editar Cliente");
		clientePanel.setCliente(cliente);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) clientePanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		clientePanel = new ClientePanel();
		
		getContentPane().add(clientePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/colaborador.gif"));
		setTitle((clientePanel.getClienteVO() == null)? "Novo Cliente" : "Editar Cliente");
		pack();
	}
}
