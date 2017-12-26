package br.com.saodimas.view.components.iframe;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.view.components.panel.apolice.editar.dependente.DependentePanel;
import br.com.saodimas.view.components.table.model.DependenteTableModel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class DependenteIFrame extends JInternalFrame{
	private DependentePanel dependentePanel;
	
	public DependenteIFrame() {
		super("Novo dependente", false, true, false);
		initialize();
	}

	public AssociadoVO getDependente() {
		return dependentePanel.getDependente();
	}

	public void setDependente(AssociadoVO dependente) {
		setTitle((dependente == null)? "Novo Dependente" : "Editar Dependente");
		dependentePanel.setDependente(dependente);
	}
	
	public DependenteTableModel getTableModel() {
		return dependentePanel.getTableModel();
	}

	public void setTableModel(DependenteTableModel tableModel) {
		dependentePanel.setTableModel(tableModel);
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
		dependentePanel = new DependentePanel();
		
		getContentPane().add(dependentePanel);
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setFrameIcon(new ImageIcon("imagens/dependentes.gif"));
		setTitle((dependentePanel.getDependente() == null)? "Novo Dependente" : "Editar Dependente");
		pack();
	}
	
	public void setApolice(ApoliceVO apolice)
	{
		dependentePanel.setApolice(apolice);
	}
}
