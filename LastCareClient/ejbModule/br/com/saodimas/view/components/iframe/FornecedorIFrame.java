package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.FornecedorVO;
import br.com.saodimas.view.components.panel.financeiro.fornecedor.FornecedorPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class FornecedorIFrame extends JInternalFrame {
	private FornecedorPanel fornecedorPanel;
	
	public FornecedorIFrame() {
		super("Nova Empresa", false, true, false);
		initialize();
	}
	
	public FornecedorIFrame(FornecedorVO empresa) {
		super("Editar Empresa", false, true, false);
		initialize();
		fornecedorPanel.setEmpresaCompra(empresa);
	}
	

	public void setEmpresaCompra(FornecedorVO empresa) {
		setTitle((empresa == null)? "Novo Fornecedor" : "Editar Fornecedor");
		fornecedorPanel.setEmpresaCompra(empresa);
	}

	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) fornecedorPanel.focoPadrao();
	}
	
	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}
	
	private void initialize(){
		fornecedorPanel = new FornecedorPanel();
		
		getContentPane().add(fornecedorPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/relacaoTitular.gif"));
		setTitle((fornecedorPanel.getEmpresaCompraVO() == null)? "Novo Fornecedor" : "Editar Fornecedor");
		pack();
	}

}
