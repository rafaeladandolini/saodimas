package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.beans.ProdutoVendaVO;
import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.view.components.panel.financeiro.venda.VendaPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class VendaIFrame extends JInternalFrame {
	private VendaPanel vendaPanel;
	
	public VendaIFrame() {
		super("Nova Venda", false, true, false);
		initialize();
	}
	
	public VendaIFrame(VendaVO venda) {
		super("Editar Venda", false, true, false);
		initialize();
		vendaPanel.setVenda(venda);
	}
	

	public void setVenda(VendaVO venda) {
		setTitle((venda == null)? "Nova Venda" : "Editar Venda");
		vendaPanel.setVenda(venda);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) vendaPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		vendaPanel = new VendaPanel();
		
		getContentPane().add(vendaPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/relacaoTitular.gif"));
		setTitle((vendaPanel.getVendaVO() == null)? "Nova Venda" : "Editar Venda");
		this.pack();
	}

	public void adicionarProduto(ProdutoVendaVO produtoAdicionar) {
		 vendaPanel.adicionaProduto(produtoAdicionar);
		
	}
	
	public void adicionarServico(ServicoVendaVO servicoAdicionar) {
		 vendaPanel.adicionaservico(servicoAdicionar);
		
	}
	
	public void setCliente(ClienteVO cliente){
		vendaPanel.setCliente(cliente);
	}
	
	

}
