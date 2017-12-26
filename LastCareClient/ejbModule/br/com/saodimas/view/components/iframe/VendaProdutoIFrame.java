package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ProdutoVendaVO;
import br.com.saodimas.view.components.panel.financeiro.venda.produtoServico.SelecaoProdutoVendaPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class VendaProdutoIFrame extends JInternalFrame {
	private SelecaoProdutoVendaPanel produtoPanel;
	
	public VendaProdutoIFrame() {
		super("Seleção de Produtos", false, true, false);
		initialize();
	}
	

	public void setProduto(ProdutoVendaVO produto)
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
		produtoPanel = new SelecaoProdutoVendaPanel();
		
		getContentPane().add(produtoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/produtos.gif"));
		setSize(430, 220);
	}

}
