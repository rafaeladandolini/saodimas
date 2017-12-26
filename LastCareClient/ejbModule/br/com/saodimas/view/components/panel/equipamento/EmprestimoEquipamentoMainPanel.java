package br.com.saodimas.view.components.panel.equipamento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.relatorio.GeradorRelatorio;
import br.com.saodimas.relatorio.NomeRelatorio;
import br.com.saodimas.view.components.iframe.ConsEmprestimosEquipamentoIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.EmprestimoEquipamentoTable;
import br.com.saodimas.view.components.table.model.EmprestimoEquipamentoTableModel;

@SuppressWarnings("serial")
public class EmprestimoEquipamentoMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Emprestimos Equipamentos"; 
	public static final String MENSAGEM_PADRAO = " Listagem dos equipamentos emprestados";

	private BarraNotificacao barNotificacao;
	private JToolBar barControle;
	private JButton btnImprimir;
	private JButton btPesquisar;
	
		
	private EmprestimoEquipamentoTable tbEquipamento;
	private ConsEmprestimosEquipamentoIFrame iframeEquipamentoEmprestimo;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public EmprestimoEquipamentoMainPanel(){
		initialize();
		configure();
	}
	
	public void carregarEmprestimoEquipamentoTable()
	{
		((EmprestimoEquipamentoTableModel)tbEquipamento.getModel()).loadData(Controller.getInstance().getEmprestimoEquipamentoAll());
	}
	
	public void carregarEmprestimoEquipamentoTable(List<EmprestimoEquipamentoVO> list)
	{
		((EmprestimoEquipamentoTableModel)tbEquipamento.getModel()).loadData(list);
	}
	
	public ConsEmprestimosEquipamentoIFrame getIFrameConsEmprestimoEquipamento() {
		return iframeEquipamentoEmprestimo;
	}

	@Override
	public void setEnabled(boolean enabled) {
		barControle.setEnabled(enabled);
		tbEquipamento.setEnabled(enabled);
		super.setEnabled(enabled);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			//this.carregarEmprestimoEquipamentoTable();
			barNotificacao.mostrarMensagem("", BarraNotificacao.DICA);
			this.iframeEquipamentoEmprestimo.setVisible(true);
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao("");
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;
		
		iframeEquipamentoEmprestimo = new ConsEmprestimosEquipamentoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeEquipamentoEmprestimo.setLocation(x -iframeEquipamentoEmprestimo.getSize().width / 2, y - iframeEquipamentoEmprestimo.getSize().height / 2);
				
		getDesktop().add(iframeEquipamentoEmprestimo);
				
		tbEquipamento = new EmprestimoEquipamentoTable();
		
		btnImprimir = new JButton();
		btnImprimir.setIcon(new ImageIcon("imagens/imprimir.gif"));
		btnImprimir.addActionListener(new EventoBotaoControle());
		btnImprimir.setToolTipText("Imprimir listagem emprestimo");
		btnImprimir.setPreferredSize(DBUTTON);
		

		btPesquisar = new JButton();
		btPesquisar.setIcon(new ImageIcon("imagens/search.gif"));
		btPesquisar.addActionListener(new EventoBotaoControle());
		btPesquisar.setToolTipText("Pesquisar");
		btPesquisar.setPreferredSize(DBUTTON);
		
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btPesquisar);
		barControle.add(btnImprimir);
		

	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infColaborador = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infColaborador.add(barControle, c);
		
		JScrollPane depPanel = new JScrollPane(tbEquipamento);
		depPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infColaborador.add(depPanel, c);
		
		infColaborador.setBorder(BorderFactory.createTitledBorder("Emprestimo Equipamentos"));
		
		JPanel panelPrincipal = new JPanel(new GridBagLayout()); 
		c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; panelPrincipal.add(barNotificacao, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; panelPrincipal.add(infColaborador, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}
	
	public void mostrarMensagem(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			
			if (e.getSource() == btnImprimir){
				new GeradorRelatorio().gerar(NomeRelatorio.RELATORIO_EMPRESTIMO_EQUIPAMENTO, ((EmprestimoEquipamentoTableModel)tbEquipamento.getModel()).getList()); 
				
			}
			
			if (e.getSource() == btPesquisar){
				iframeEquipamentoEmprestimo.setVisible(true);
				iframeEquipamentoEmprestimo.habilitarControles(false); 
				
			}
		}
	}

}