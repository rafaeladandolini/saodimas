package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.view.components.panel.produto.ProdutoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ProdutoIFrame extends JInternalFrame {
	private ProdutoPanel produtoPanel;
	
	public ProdutoIFrame() {
		super("Novo produto", false, true, false);
		initialize();
	}
	
	public ProdutoIFrame(ProdutoVO produto) {
		super("Editar produto", false, true, false);
		initialize();
		produtoPanel.setProduto(produto);
	}
	
	public ProdutoVO getProduto() {
		return produtoPanel.getProduto();
	}

	public void setProduto(ProdutoVO produto) {
		setTitle((produto == null)? "Novo Produto" : "Editar Produto");
		produtoPanel.setProduto(produto);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) produtoPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		produtoPanel = new ProdutoPanel();
		
		getContentPane().add(produtoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/produtos.gif"));
		setTitle((produtoPanel.getProduto() == null)? "Novo Produto" : "Editar Produto");
		pack();
	}
}
