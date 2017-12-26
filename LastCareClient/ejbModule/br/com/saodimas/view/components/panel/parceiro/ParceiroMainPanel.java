package br.com.saodimas.view.components.panel.parceiro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ParceiroVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.iframe.ParceiroIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.ParceiroTable;
import br.com.saodimas.view.components.table.model.ParceiroTableModel;

@SuppressWarnings("serial")
public class ParceiroMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Parceiro"; 
	public static final String MENSAGEM_PADRAO = " Manutenção de parceiros. Você pode incluir, alterar ou excluir um parceiro.";
	
	private BarraNotificacao barNotificacao;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JToolBar barControle;
	
	private ParceiroTable tbParceiros;
	private ParceiroIFrame iframeParceiro;
	
	private AbstractButton ultimoComando;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public ParceiroMainPanel(){
		initialize();
		configure();
	}
	
	public void carregarParceirosTable()
	{
		((ParceiroTableModel)tbParceiros.getModel()).loadData(Controller.getInstance().getParceirosAll());
	}
	
	public ParceiroIFrame getParceiroIFrame() {
		return iframeParceiro;
	}

	public void selecionaUltimoComando(){
		if (ultimoComando != null){
			if (ultimoComando != btEditar && ultimoComando != btExcluir){
				tbParceiros.getSelectionModel().clearSelection();
				ultimoComando.requestFocus();
			}
			else tbParceiros.requestFocus();
		}
	}	

	@Override
	public void setEnabled(boolean enabled) {
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbParceiros.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbParceiros.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbParceiros.setEnabled(enabled);
		super.setEnabled(enabled);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			ultimoComando = btNovo;
			selecionaUltimoComando();
			carregarParceirosTable();
			barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, BarraNotificacao.DICA);
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao("");
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;
		
		iframeParceiro = new ParceiroIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();
				super.setVisible(flag);
			}
		};
		iframeParceiro.setLocation(x - iframeParceiro.getSize().width / 2, y - iframeParceiro.getSize().height / 2);
				
		getDesktop().add(iframeParceiro);
				
		tbParceiros = new ParceiroTable();
		tbParceiros.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbParceiros.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbParceiros.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				super.keyPressed(e);
			}
		});
		
		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addEmployee.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra um novo parceiro!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbParceiros.getKeyListeners()[0]);
		
		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de um parceiro!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbParceiros.getKeyListeners()[0]);
		
		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um parceiro!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbParceiros.getKeyListeners()[0]);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		
		ultimoComando = btNovo;
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infParceiros = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infParceiros.add(barControle, c);
		
		JScrollPane depPanel = new JScrollPane(tbParceiros);
		depPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infParceiros.add(depPanel, c);
		
		infParceiros.setBorder(BorderFactory.createTitledBorder("Parceiros"));
		
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
		c.gridy = 1; panelPrincipal.add(infParceiros, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}
	
	public void mostrarMensagem(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbParceiros.getSelectedRow() > -1);
			btExcluir.setEnabled(tbParceiros.getSelectedRow() > -1);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ultimoComando = (AbstractButton)e.getSource();
			
			if (e.getSource() == btNovo){
				iframeParceiro.setParceiro(null);
				iframeParceiro.setVisible(true);
				iframeParceiro.habilitarControles(false);				
			}
			else if (e.getSource() == btEditar){
				int row = tbParceiros.getSelectedRow();
				ParceiroTableModel model = (ParceiroTableModel)tbParceiros.getModel();
				ParceiroVO dep = (ParceiroVO)model.getSelectedValue(row);
				
				iframeParceiro.setParceiro(dep);
				iframeParceiro.setVisible(true);
				iframeParceiro.habilitarControles(false);				
			}
			else{
				if (mostraConfirmacao("Confirmação", "Confirma a exclusão do parceiro?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbParceiros.getSelectedRow();
					ParceiroTableModel model = (ParceiroTableModel)tbParceiros.getModel();
					ParceiroVO col = (ParceiroVO)model.getSelectedValue(row);
					try{
						Controller.getInstance().deleteParceiro(col);
						barNotificacao.mostrarMensagem("Parceiro excluído com sucesso.",BarraNotificacao.SUCESSO);
					} catch (MensagemSaoDimasException ex) {
						barNotificacao.mostrarMensagem(ex.getMessage(),	BarraNotificacao.ERRO);
					}
					carregarParceirosTable();
				}
			}
		}
	}

}
