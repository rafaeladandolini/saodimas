package br.com.saodimas.view.components.panel.apolice.editar.fatura.parceiro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.FaturaTable;
import br.com.saodimas.view.components.table.model.FaturaTableModel;

@SuppressWarnings("serial")
public class ApoliceEditarFaturaParceiroPanel extends JPanel {
	public static final String FORM_NAME = "Editar Faturas";
	public static final String MENSAGEM_PADRAO = "Realize manutenção das faturas.";

	private ApoliceVO apolice;
	private JButton btQuitarParceiro;
	private JToolBar barControle;
	private BarraNotificacao barNotificacao;

	private List<FaturaVO> removerFaturas;
	private FaturaTable tbFaturas;

	private static final Dimension DBUTTON = new Dimension(30, 30);

	public ApoliceEditarFaturaParceiroPanel() {
		initialize();
		configure();
	}

	public List<FaturaVO> getRemoverFaturas() {
		return removerFaturas;
	}
	
	public void limparRemoverDependentes(){
		this.removerFaturas= new ArrayList<FaturaVO>();	
	}

	public void setApolice(ApoliceVO a) {
		this.apolice = a;
		limparCampos();
		if (a != null) {
			if (a.getFaturas() != null) {
				FaturaTableModel model = (FaturaTableModel) tbFaturas
						.getModel();
				List<FaturaVO> listFaturas = new ArrayList<FaturaVO>();
				listFaturas.addAll(a.getFaturas());
				model.removeAll();
				model.loadData(listFaturas);
			}
		}
		removerFaturas.clear();
	}

	public void limparCampos() {
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		model.removeAll();
		model.fireTableDataChanged();
	}

	@Override
	public void setEnabled(boolean enabled) {
		btQuitarParceiro.setEnabled(enabled && faturasSelecionadas() > 0);
		barControle.setEnabled(enabled);
		tbFaturas.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {

		}
	}

	private void initialize() {

		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		removerFaturas = new ArrayList<FaturaVO>();
		tbFaturas = new FaturaTable();
		tbFaturas.getModel().addTableModelListener(new EventoTabela());
		tbFaturas.getSelectionModel().addListSelectionListener(
				new EventoTabela());
		tbFaturas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btQuitarParceiro.isEnabled())
					btQuitarParceiro.doClick();
				super.mouseClicked(e);
			}
		});
		tbFaturas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btQuitarParceiro.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_X) {
					FaturaTableModel model = (FaturaTableModel) tbFaturas
							.getModel();
					int row = tbFaturas.getSelectedRow();
					if (row >= 0) {
						boolean marcar = !(Boolean) model.getValueAt(row,
								FaturaTableModel.SELECAO);
						model.setValueAt(new Boolean(marcar), row,
								FaturaTableModel.SELECAO);
					}
				}
				super.keyPressed(e);
			}
		});

		btQuitarParceiro = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() > 0
						|| tbFaturas.getSelectedRow() > -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btQuitarParceiro.setIcon(new ImageIcon("imagens/quitarFaturas.gif"));
		btQuitarParceiro.addActionListener(new EventoBotaoControle());
		btQuitarParceiro.setToolTipText("Dar baixa nas faturas selecionadas - Parceiro!");
		btQuitarParceiro.setPreferredSize(DBUTTON);
		btQuitarParceiro.setEnabled(false);
		btQuitarParceiro.addKeyListener(tbFaturas.getKeyListeners()[0]);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btQuitarParceiro);
		barControle.addSeparator();

	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infFatura = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infFatura.add(barNotificacao, c);
		
		
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		infFatura.add(barControle, c);

		JScrollPane tbFatPanel = new JScrollPane(tbFaturas);
		tbFatPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		infFatura.add(tbFatPanel, c);

		infFatura.setBorder(BorderFactory.createTitledBorder("Faturas"));
		infFatura.setPreferredSize(new Dimension(350, 350));
		setLayout(new BorderLayout());

		add(infFatura, BorderLayout.CENTER);
	}

	private ApoliceEditarMainPanel getPainelPrincipal() {
		Component c = getParent();
		while (c != null) {
			if (c instanceof ApoliceEditarMainPanel)
				return (ApoliceEditarMainPanel) c;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel) c;
	}

	private int faturasSelecionadas() {
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		int numLinhas = model.getRowCount();
		int fatSelecionadas = 0;

		if (model.getDataVector().size() > 0)
			for (int i = 0; i < numLinhas; i++)
				if ((Boolean) model.getValueAt(i, FaturaTableModel.SELECAO)) {
					fatSelecionadas += 1;
					((FaturaVO) model.getSelectedValue(i)).setSelecionada(true);
				}
		return fatSelecionadas;
	}
	
	private void quitarFaturasParceiros() {
		
		int idSelecao = tbFaturas.getSelectedRow();
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		if (idSelecao >= 0 && faturasSelecionadas() == 0)
			((FaturaVO) model.getSelectedValue(idSelecao)).setSelecionada(true);
		ApoliceEditarMainPanel c = getPainelPrincipal();
		c.getIframeQuitarParceiro().setApolice(apolice);
		c.getIframeQuitarParceiro().setTableModel(model);
		c.getIframeQuitarParceiro().setVisible(true);
		c.getIframeQuitarParceiro().habilitarControles(false);
		
	}

	private class EventoTabela implements TableModelListener,
			ListSelectionListener {
		public void tableChanged(TableModelEvent ev) {
			int qntde = faturasSelecionadas();
			btQuitarParceiro.setEnabled(qntde> 0);
	
		}

		public void valueChanged(ListSelectionEvent e) {
			boolean linhaSelecionada = (tbFaturas.getSelectedRow() > -1);
			
			btQuitarParceiro.setEnabled(linhaSelecionada);
			if (linhaSelecionada) {
				FaturaTableModel model = (FaturaTableModel) tbFaturas
						.getModel();
				FaturaVO fat = model.getDataVector().get(
						tbFaturas.getSelectedRow());
				btQuitarParceiro.setEnabled(fat.getDataPagamento() == null);
			}
		}
	}

	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btQuitarParceiro)
				quitarFaturasParceiros();
		}

			}
	
	public void notificar(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}
}
