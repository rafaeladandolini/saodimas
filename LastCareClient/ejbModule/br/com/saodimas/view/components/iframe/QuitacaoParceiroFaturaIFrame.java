package br.com.saodimas.view.components.iframe;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.view.components.panel.apolice.editar.fatura.QuitacaoParceiroPanel;
import br.com.saodimas.view.components.table.model.FaturaTableModel;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class QuitacaoParceiroFaturaIFrame extends JInternalFrame {
	private QuitacaoParceiroPanel quitacaoPanel;

	public QuitacaoParceiroFaturaIFrame() {
		super("Quitar faturas", false, true, false);
		initialize();
	}

	public void setTableModel(FaturaTableModel tableModel) {
		quitacaoPanel.setFaturasTableModel(tableModel);
	}
	
	public void setApolice(ApoliceVO apoliceVO) {
		this.quitacaoPanel.setApolice(apoliceVO); 
	}
	
	@Override
	public void setVisible(boolean visible) {
		habilitarControles(!visible);
		super.setVisible(visible);
		if (visible){
			quitacaoPanel.focoPadrao();
			quitacaoPanel.recalcular();
		}
	}

	public void habilitarControles(boolean enable){
		if (enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().habilitarTudo();
		if (!enable && WinManager.getJanelaInicial() != null)
			WinManager.getJanelaInicial().desabilitarTudo();
	}

	private void initialize(){
		quitacaoPanel = new QuitacaoParceiroPanel();

		getContentPane().add(quitacaoPanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/quitarFaturas.gif"));
		setSize(new Dimension(400,300));
	}
}
