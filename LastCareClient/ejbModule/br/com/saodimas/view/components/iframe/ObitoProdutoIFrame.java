package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.view.components.panel.apolice.editar.obito.produtoServico.SelecaoProdutoObitoPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ObitoProdutoIFrame extends JInternalFrame {
	private SelecaoProdutoObitoPanel produtoPanel;
	
	public ObitoProdutoIFrame() {
		super("Seleção de Produtos", false, true, false);
		initialize();
	}
	
	public void setProduto(ProdutoObitoVO produto)
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
		produtoPanel = new SelecaoProdutoObitoPanel();
		
		getContentPane().add(produtoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/produtos.gif"));
		this.setSize(430, 190);
	}

}
