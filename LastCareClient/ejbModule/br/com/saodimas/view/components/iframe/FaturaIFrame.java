package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.view.components.panel.apolice.editar.fatura.FaturaPanel;
import br.com.saodimas.view.components.table.model.FaturaTableModel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class FaturaIFrame extends JInternalFrame {
	private FaturaPanel faturaPanel;

	public FaturaIFrame() {
		super("Nova fatura", false, true, false);
		initialize();
	}

	public FaturaVO getFatura() {
		return faturaPanel.getFatura();
	}

	public void setFatura(FaturaVO fatura) {
		setTitle((fatura == null) ? "Nova Fatura" : "Editar Fatura");
		faturaPanel.setFatura(fatura);
	}

	public void setTableModel(FaturaTableModel tableModel) {
		faturaPanel.setFaturasTableModel(tableModel);
	}
	
	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible) faturaPanel.focoPadrao();
	}

	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}

	private void initialize(){
		faturaPanel = new FaturaPanel();

		getContentPane().add(faturaPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/faturas.gif"));
		setTitle((faturaPanel.getFatura() == null)? "Nova Fatura" : "Editar Fatura");
		pack();
	}
	
	public void setApolice(ApoliceVO apolice)
	{
		faturaPanel.setApolice(apolice);
	}
}
