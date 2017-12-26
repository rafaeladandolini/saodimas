package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.view.components.panel.produto.ProdutoSelecaoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class PlanoProdutoIFrame extends JInternalFrame {
	private ProdutoSelecaoPanel produtoPanel;
	
	public PlanoProdutoIFrame() {
		super("Seleção de Produtos", false, true, false);
		initialize();
	}
	
	public ProdutoVO getProduto() {
		return produtoPanel.getProdutoSelecionado();
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (produtoPanel != null){
			if (visible) produtoPanel.recarregarTabela();
			else produtoPanel.limparTabela();
		}
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		produtoPanel = new ProdutoSelecaoPanel();
		
		getContentPane().add(produtoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/produtos.gif"));
		pack();
	}

}
