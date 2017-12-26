package br.com.saodimas.view.components.panel.servico;

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
import java.util.List;

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
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.iframe.ConsServicoIFrame;
import br.com.saodimas.view.components.iframe.ServicoIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.ServicoTable;
import br.com.saodimas.view.components.table.model.ServicoTableModel;

@SuppressWarnings("serial")
public class ServicoMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Serviços";
	public static final String MENSAGEM_PADRAO = "   Pressione a tecla [F3] para Pesquisar, [INSERT] para Cadastrar e [ENTER] ou [DELETE] para editar/excluir um serviço selecionado!";

	private BarraNotificacao barNotificacao;
	private JButton btPesquisar;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JToolBar barControle;

	private ServicoTable tbServicos;
	private ServicoIFrame iframeServico;
	private ConsServicoIFrame iframeConsultaServico;

	private AbstractButton ultimoComando;

	private static final Dimension DBUTTON = new Dimension(30,30);

	public ServicoMainPanel(){
		initialize();
		configure();
	}

	public void carregarServicosTable()
	{
		carregarServicosTable(Controller.getInstance().getServicoAll());
	}
	
	public void carregarServicosTable(List<ServicoVO> list)
	{
		((ServicoTableModel)tbServicos.getModel()).loadData(list);
	}
	
	public void mostrarMensagem(String msg, int tipo){
		barNotificacao.mostrarMensagem(msg, tipo);
	}
	
	public ServicoIFrame getIframeServico() {
		return iframeServico;
	}

	public ConsServicoIFrame getIframeConsultaServico() {
		return iframeConsultaServico;
	}

	public void selecionaUltimoComando(){
		if (ultimoComando != null){
			if (ultimoComando != btEditar && ultimoComando != btExcluir){
				tbServicos.getSelectionModel().clearSelection();
				ultimoComando.requestFocus();
			}
			else tbServicos.requestFocus();
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		btPesquisar.setEnabled(enabled);
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbServicos.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbServicos.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbServicos.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			ultimoComando = btPesquisar;
			selecionaUltimoComando();
			if (ultimoComando == btPesquisar && tbServicos.getRowCount() == 0){
				iframeConsultaServico.setVisible(true);
				iframeConsultaServico.habilitarControles(false);				
			}			
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;

		iframeServico = new ServicoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();
				super.setVisible(flag);
			}
		};
		iframeServico.setLocation(x - iframeServico.getSize().width / 2, y - iframeServico.getSize().height / 2);

		iframeConsultaServico = new ConsServicoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();
				super.setVisible(flag);
			}
		};
		iframeConsultaServico.setLocation(x - iframeConsultaServico.getSize().width / 2, y - iframeConsultaServico.getSize().height / 2);

		getDesktop().add(iframeServico);
		getDesktop().add(iframeConsultaServico);

		tbServicos = new ServicoTable();
		tbServicos.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbServicos.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbServicos.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_F3) btPesquisar.doClick();
				super.keyPressed(e);
			}
		});
		
		btPesquisar = new JButton();
		btPesquisar.setIcon(new ImageIcon("imagens/search.gif"));
		btPesquisar.addActionListener(new EventoBotaoControle());
		btPesquisar.setToolTipText("Pesquisar serviços!");
		btPesquisar.setPreferredSize(DBUTTON);
		btPesquisar.addKeyListener(tbServicos.getKeyListeners()[0]);

		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addServices.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra um novo serviço!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbServicos.getKeyListeners()[0]);

		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de um serviço!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbServicos.getKeyListeners()[0]);

		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um serviço!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbServicos.getKeyListeners()[0]);

		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btPesquisar);
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);

		ultimoComando = btPesquisar;
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infServico = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infServico.add(barControle, c);
		
		JScrollPane servPanel = new JScrollPane(tbServicos);
		servPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infServico.add(servPanel, c);

		infServico.setBorder(BorderFactory.createTitledBorder("Serviços"));

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
		c.gridy = 1; panelPrincipal.add(infServico, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}

	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbServicos.getSelectedRow() > -1);
			btExcluir.setEnabled(tbServicos.getSelectedRow() > -1);
		}
	}

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ultimoComando = (AbstractButton)e.getSource();
			if (e.getSource() == btPesquisar){
				iframeConsultaServico.setVisible(true);
				iframeConsultaServico.habilitarControles(false);				
			}
			else if (e.getSource() == btNovo){
				iframeServico.setServico(null);
				iframeServico.setVisible(true);
				iframeServico.habilitarControles(false);				
			}
			else if (e.getSource() == btEditar){
				int row = tbServicos.getSelectedRow();
				if (row >= 0){
					ServicoTableModel model = (ServicoTableModel)tbServicos.getModel();
					ServicoVO dep = (ServicoVO)model.getSelectedValue(row);

					iframeServico.setServico(dep);
					iframeServico.setVisible(true);
					iframeServico.habilitarControles(false);
				}
			}
			else{
				int row = tbServicos.getSelectedRow();
				if (row >= 0){
					if (mostraConfirmacao("Confirmação", "Confirma a exclusão do serviço?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
						ServicoTableModel model = (ServicoTableModel)tbServicos.getModel();
						ServicoVO ser = (ServicoVO)model.getSelectedValue(row);
						try {
							Controller.getInstance().deleteServico(ser);
							barNotificacao.mostrarMensagem("Serviço removido com sucesso.", BarraNotificacao.INFO);
						} catch (MensagemSaoDimasException ex) {
							barNotificacao.mostrarMensagem(ex.getMessage(), BarraNotificacao.ERRO);
						}
						carregarServicosTable();
					}
				}
			}
		}
	}

}
