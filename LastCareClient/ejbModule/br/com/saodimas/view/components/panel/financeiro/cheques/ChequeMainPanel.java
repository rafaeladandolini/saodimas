package br.com.saodimas.view.components.panel.financeiro.cheques;

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
import java.util.Date;

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
import br.com.saodimas.model.beans.ChequeVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.iframe.ChequeIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.ChequeTable;
import br.com.saodimas.view.components.table.model.ChequeTableModel;


@SuppressWarnings("serial")
public class ChequeMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Cheques"; 
	public static final String MENSAGEM_PADRAO = "Pressione a tecla [INSERT] para Cadastrar, [ENTER] ou [DELETE] para editar/excluir uma cheque selecionado!";
	
	private BarraNotificacao barNotificacao;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btBaixar;
	private JToolBar barControle;
	
	private ChequeTable tbCheques;
	private ChequeIFrame iframeCheque;
	
			
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public ChequeMainPanel() {
		initialize();
		configure();
		this.carregarCheques();
	}
	
	public void carregarCheques()
	{
		((ChequeTableModel)tbCheques.getModel()).loadData(Controller.getInstance().getAllCheques());
	}
	
	public ChequeIFrame getIframeCheque() {
		return iframeCheque;
	}

	
	@Override
	public void setEnabled(boolean enabled) {
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbCheques.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbCheques.getSelectedRow() > -1);
		btBaixar.setEnabled(enabled && tbCheques.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbCheques.setEnabled(enabled);
		super.setEnabled(enabled);
	}
	
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;
		
		iframeCheque = new ChequeIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeCheque.setLocation(x - iframeCheque.getSize().width / 2, y - iframeCheque.getSize().height / 2);
		
		getDesktop().add(iframeCheque);
		
		tbCheques = new ChequeTable();
		tbCheques.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbCheques.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		
				
		tbCheques.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				super.keyPressed(e);
			}
		});
		
		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addCity.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra uma nova cidade!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbCheques.getKeyListeners()[0]);
		
		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de uma cidade!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbCheques.getKeyListeners()[0]);
		
		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove uma cidade!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbCheques.getKeyListeners()[0]);
		
		btBaixar = new JButton();
		btBaixar.setIcon(new ImageIcon("imagens/submit.gif"));
		btBaixar.addActionListener(new EventoBotaoControle());
		btBaixar.setToolTipText("Baixar um cheque!");
		btBaixar.setPreferredSize(DBUTTON);
		btBaixar.setEnabled(false);
			
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		barControle.addSeparator();
		barControle.add(btBaixar);	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infCidade = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infCidade.add(barControle, c);
		
		JScrollPane prodPanel = new JScrollPane(tbCheques);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infCidade.add(prodPanel, c);
		
		infCidade.setBorder(BorderFactory.createTitledBorder("Cheques"));
		
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
		c.gridy = 1; panelPrincipal.add(infCidade, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);	
	}
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbCheques.getSelectedRow() > -1);
			btExcluir.setEnabled(tbCheques.getSelectedRow() > -1);
			btBaixar.setEnabled(tbCheques.getSelectedRow() > -1);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			

			if (e.getSource() == btExcluir)
			{
				if (mostraConfirmacao("Confirmação", "Confirma a exclusão da cidade?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbCheques.getSelectedRow();
					ChequeTableModel model = (ChequeTableModel)tbCheques.getModel();
					ChequeVO c = (ChequeVO)model.getSelectedValue(row);
					try{
						Controller.getInstance().deleteCheque(c);
						barNotificacao.mostrarMensagem("Cheque removido com sucesso.", BarraNotificacao.INFO);
					} catch (MensagemSaoDimasException ex) {
						barNotificacao.mostrarMensagem(ex.getMessage(),	BarraNotificacao.ERRO);
					}
					carregarCheques();
				}
			}else if (e.getSource() == btBaixar)
			{
				if (mostraConfirmacao("Confirmação", "Confirma a baixa do cheque?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbCheques.getSelectedRow();
					ChequeTableModel model = (ChequeTableModel)tbCheques.getModel();
					ChequeVO c = (ChequeVO)model.getSelectedValue(row);
					try{
						Controller.getInstance().baixarCheque(c, new Date());
						barNotificacao.mostrarMensagem("Cheque baixado com sucesso.", BarraNotificacao.INFO);
					} catch (MensagemSaoDimasException ex) {
						barNotificacao.mostrarMensagem(ex.getMessage(),	BarraNotificacao.ERRO);
					}
					carregarCheques();
				}				
			}else if (e.getSource() == btNovo)
			{
				iframeCheque.setCheque(null);
				iframeCheque.setVisible(true);
				iframeCheque.habilitarControles(false);				
			}
			else if (e.getSource() == btEditar)
			{
				int row = tbCheques.getSelectedRow();
				ChequeTableModel model = (ChequeTableModel)tbCheques.getModel();
				ChequeVO cid = (ChequeVO)model.getSelectedValue(row);
				
				iframeCheque.setCheque(cid);
				iframeCheque.setVisible(true);
				iframeCheque.habilitarControles(false);				
			}
		}
	}
}
