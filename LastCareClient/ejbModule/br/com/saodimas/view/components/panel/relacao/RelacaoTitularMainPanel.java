package br.com.saodimas.view.components.panel.relacao;

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
import br.com.saodimas.model.beans.RelacaoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.iframe.RelacaoTitularIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.RelacaoTitularTable;
import br.com.saodimas.view.components.table.model.RelacaoTitularTableModel;

@SuppressWarnings("serial")
public class RelacaoTitularMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Relação Titular";
	public static final String MENSAGEM_PADRAO = "   Pressione a tecla [INSERT] para Cadastrar, [ENTER] ou [DELETE] para editar/excluir uma relação selecionada!";

	private BarraNotificacao barNotificacao;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JToolBar barControle;

	private RelacaoTitularTable tbRelacaoTitular;
	private RelacaoTitularIFrame iframeRelacaoTitular;

	private AbstractButton ultimoComando;

	private static final Dimension DBUTTON = new Dimension(30, 30);

	public RelacaoTitularMainPanel() {
		initialize();
		configure();
	}

	public RelacaoTitularIFrame getIframeRelacaoTitular() {
		return iframeRelacaoTitular;
	}

	public void carregarRelacoesTable() {
		((RelacaoTitularTableModel) tbRelacaoTitular.getModel())
				.loadData(Controller.getInstance().getRelacoesAll());
	}

	public void selecionaUltimoComando() {
		if (ultimoComando != null) {
			if (ultimoComando != btEditar && ultimoComando != btExcluir) {
				tbRelacaoTitular.getSelectionModel().clearSelection();
				ultimoComando.requestFocus();
			} else
				tbRelacaoTitular.requestFocus();
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbRelacaoTitular.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbRelacaoTitular.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbRelacaoTitular.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			ultimoComando = btNovo;
			selecionaUltimoComando();
			carregarRelacoesTable();
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;

		iframeRelacaoTitular = new RelacaoTitularIFrame() {
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null)
					getGlass().setVisible(flag);
				if (!flag)
					selecionaUltimoComando();
				super.setVisible(flag);
			}
		};
		iframeRelacaoTitular.setLocation(x
				- iframeRelacaoTitular.getSize().width / 2, y
				- iframeRelacaoTitular.getSize().height / 2);

		getDesktop().add(iframeRelacaoTitular);

		tbRelacaoTitular = new RelacaoTitularTable();
		tbRelacaoTitular.getSelectionModel().addListSelectionListener(
				new EventoTabela());
		tbRelacaoTitular.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled())
					btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbRelacaoTitular.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE)
					btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT)
					btNovo.doClick();
				super.keyPressed(e);
			}
		});

		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addRelation.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra uma nova relação!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbRelacaoTitular.getKeyListeners()[0]);

		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de uma relação!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbRelacaoTitular.getKeyListeners()[0]);

		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove uma relação!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbRelacaoTitular.getKeyListeners()[0]);

		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infRelacaoTitular = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		infRelacaoTitular.add(barControle, c);

		JScrollPane prodPanel = new JScrollPane(tbRelacaoTitular);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		infRelacaoTitular.add(prodPanel, c);

		infRelacaoTitular.setBorder(BorderFactory
				.createTitledBorder("Relações com o Titular"));

		JPanel panelPrincipal = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		panelPrincipal.add(barNotificacao, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		panelPrincipal.add(infRelacaoTitular, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}

	private class EventoTabela implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbRelacaoTitular.getSelectedRow() > -1);
			btExcluir.setEnabled(tbRelacaoTitular.getSelectedRow() > -1);
		}
	}

	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ultimoComando = (AbstractButton) e.getSource();
			if (e.getSource() == btNovo) {
				iframeRelacaoTitular.setRelacao(null);
				iframeRelacaoTitular.setVisible(true);
				iframeRelacaoTitular.habilitarControles(false);
			} else if (e.getSource() == btEditar) {
				int row = tbRelacaoTitular.getSelectedRow();
				RelacaoTitularTableModel model = (RelacaoTitularTableModel) tbRelacaoTitular
						.getModel();
				RelacaoVO plan = (RelacaoVO) model.getSelectedValue(row);

				iframeRelacaoTitular.setRelacao(plan);
				iframeRelacaoTitular.setVisible(true);
				iframeRelacaoTitular.habilitarControles(false);
			} else {
				if (mostraConfirmacao("Confirmação", "Confirma a exclusão da relação?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbRelacaoTitular.getSelectedRow();
					RelacaoTitularTableModel model = (RelacaoTitularTableModel) tbRelacaoTitular.getModel();
					RelacaoVO relTitular = (RelacaoVO) model.getSelectedValue(row);
					try {
						Controller.getInstance().deleteRelacao(relTitular);
						barNotificacao.mostrarMensagem("Relação removida com sucesso.", BarraNotificacao.INFO);
					} catch (MensagemSaoDimasException ex) {
						barNotificacao.mostrarMensagem(ex.getMessage(),	BarraNotificacao.ERRO);
					}
					carregarRelacoesTable();
				}
			}
		}
	}
}
