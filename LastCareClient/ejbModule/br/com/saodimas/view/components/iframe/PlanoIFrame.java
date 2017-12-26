package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.view.components.panel.plano.PlanoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class PlanoIFrame extends JInternalFrame {
	private PlanoPanel planoPanel;
	
	public PlanoIFrame() {
		super("Novo plano", false, true, false);
		initialize();
	}
	
	public PlanoIFrame(PlanoVO plano) {
		super("Editar plano", false, true, false);
		initialize();
		planoPanel.setPlano(plano);
	}
	
	public PlanoVO getPlano() {
		return planoPanel.getPlano();
	}

	public void setPlano(PlanoVO plano) {
		setTitle((plano == null)? "Novo Plano" : "Editar Plano");
		planoPanel.setPlano(plano);
	}
	
	public void adicionaProduto(ProdutoVO p){
		planoPanel.adicionaProduto(p);
	}
	
	public void adicionaServico(ServicoVO s) {
		planoPanel.adicionaServico(s);
	}
	

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) planoPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		planoPanel = new PlanoPanel();
		
		getContentPane().add(planoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/planos.gif"));
		setTitle((planoPanel.getPlano() == null)? "Novo Plano" : "Editar Plano");
		pack();
	}

}
