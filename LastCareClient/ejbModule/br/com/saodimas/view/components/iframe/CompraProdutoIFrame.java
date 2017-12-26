package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ProdutoCompraVO;
import br.com.saodimas.view.components.panel.financeiro.compra.SelecaoProdutoCompraPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class CompraProdutoIFrame extends JInternalFrame {
	private SelecaoProdutoCompraPanel produtoPanel;
	
	public CompraProdutoIFrame() {
		super("Seleção de Produtos", false, true, false);
		initialize();
	}
	
	public void setProduto(ProdutoCompraVO produto)
	{
		produtoPanel.setProduto(produto);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (produtoPanel != null){
			if (visible) produtoPanel.recarregarTabela();
		}
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		produtoPanel = new SelecaoProdutoCompraPanel();
		
		getContentPane().add(produtoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/produtos.gif"));
		pack();
		setSize(600, 400);
	}

}
