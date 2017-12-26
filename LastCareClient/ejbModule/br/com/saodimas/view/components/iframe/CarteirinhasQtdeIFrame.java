package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.view.components.panel.apolice.CarteirinhasQuantidadePanel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class CarteirinhasQtdeIFrame extends JInternalFrame {
	private CarteirinhasQuantidadePanel consApolicePanel;
	
	public CarteirinhasQtdeIFrame() {
		super("Quantidade de carteirinhas", false, true, false);
		initialize();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible){
			consApolicePanel.limparCampos();
		}
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		consApolicePanel = new CarteirinhasQuantidadePanel();
		getContentPane().add(consApolicePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/search.gif"));
		pack();
	}
	public void setApolice(ApoliceVO apolice)
	{
		this.consApolicePanel.setApolice(apolice);
	}
}