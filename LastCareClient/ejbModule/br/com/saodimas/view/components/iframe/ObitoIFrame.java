package br.com.saodimas.view.components.iframe;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;
import br.com.saodimas.view.components.panel.apolice.editar.obito.ObitoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ObitoIFrame extends JInternalFrame {
	private ObitoPanel obitoPanel;
	
	public ObitoIFrame() {
		super("Novo Óbito", false, true, false);
		initialize();
	}
	
	public ObitoIFrame(ObitoVO obito) {
		super("Editar Óbito", false, true, false);
		initialize();
		obitoPanel.setObito(obito);
		setTitle(obito == null? "Novo Óbito" : "Editar Óbito");
	}
	
	public List<ProdutoObitoVO> getProdutosObitoRemover() {
		return obitoPanel.getProdutosObitoRemover();
	}
	
	public List<ServicoObitoVO> getServicosObitoRemover() {
		return obitoPanel.getServicosObitoRemover();
	}
	
	public void setObito(ObitoVO obito) {
		setTitle((obito == null)? "Novo Óbito" : "Editar Óbito");
		obitoPanel.setObito(obito);
	}

	public void adicionaApolice(ApoliceVO a){
		obitoPanel.adicionaApolice(a);
	}
	
	public void adicionaProduto(ProdutoObitoVO p){
		obitoPanel.adicionaProduto(p);
	}
	
	public void adicionaServico(ServicoObitoVO s) {
		obitoPanel.adicionaServico(s);
	}
	
	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		obitoPanel = new ObitoPanel();
		
		getContentPane().add(obitoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/obitos.gif"));
		pack();
		setSize(500,600);
	}

	public ObitoPanel getObitoPanel() {
		return obitoPanel;
	}
	
	public void setConfigVisualizarObito(boolean isTelaConsulta, boolean isTelaGerenciarObito) {
		obitoPanel.colocarModoVisualizacao(isTelaConsulta, isTelaGerenciarObito);
		this.setTitle("Visualização");
	}
}
