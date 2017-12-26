package br.com.saodimas.view.components.panel.financeiro.cliente.editar;

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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.table.VendaClienteTable;
import br.com.saodimas.view.components.table.model.VendaClienteTableModel;

@SuppressWarnings("serial")
public class ClienteEditarVendaPanel extends JPanel {
	public static final String FORM_NAME = "Editar Vendas";
	public static final String MENSAGEM_PADRAO = "Realize manutenção das vendas do cliente.";

	private ClienteVO cliente;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btCarteirinha;
	private JToolBar barControle;
	private VendaClienteTable tbVendas;

	private BarraNotificacao barNotificacao;
	private static final Dimension DBUTTON = new Dimension(30, 30);

	public ClienteEditarVendaPanel() {
		initialize();
		configure();
	}

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag)
			btNovo.requestFocus();
	}

	public void setCliente(ClienteVO c) {
		cliente = c;
		if (cliente != null)
			this.carregarVendas();
		else {
			VendaClienteTableModel model = (VendaClienteTableModel) tbVendas.getModel();
			model.removeAll();
		}

		
	}

	public void carregarVendas()
	{
		VendaClienteTableModel model = (VendaClienteTableModel) tbVendas.getModel();
		model.removeAll();
		List<VendaVO> listVenda = Controller.getInstance().getAllVendasByCliente(cliente);
		model.loadData(listVenda);
		model.fireTableDataChanged();
		this.cliente.setVendas(listVenda);
	}
	

	
	@Override
	public void requestFocus() {
		btNovo.requestFocus();
	}

	private void initialize() {
		
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		tbVendas = new VendaClienteTable();
		tbVendas.getSelectionModel().addListSelectionListener(
				new EventoTabela());
		tbVendas.getModel().addTableModelListener(new EventoTabela());
		tbVendas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled())
					btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbVendas.addKeyListener(new KeyAdapter() {
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
		btNovo.setIcon(new ImageIcon("imagens/addContract.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastrar uma nova venda!");
		btNovo.setPreferredSize(DBUTTON);

		btEditar = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbVendas.getRowCount() > 0
						&& tbVendas.getSelectedRow() > -1;
				super.setEnabled(enable & habilitar);
			}
		};
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Clique para editar uma venda!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);

		btExcluir = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbVendas.getRowCount() > 0
						&& tbVendas.getSelectedRow() > -1;
				super.setEnabled(enable & habilitar);
			}
		};
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Clique para remover uma venda");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);

		btCarteirinha = new JButton(new ImageIcon("imagens/imprimir.gif"));
		btCarteirinha.addActionListener(new EventoBotaoControle());
		btCarteirinha.setToolTipText("Clique para imprimir uma venda");
		btCarteirinha.setPreferredSize(DBUTTON);
		btCarteirinha.setEnabled(false);
		
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		barControle.add(btCarteirinha);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel depPanel = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; depPanel.add(barNotificacao, c);
		
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		depPanel.add(barControle, c);

		JScrollPane tbDepPanel = new JScrollPane(tbVendas);
		tbDepPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		depPanel.add(tbDepPanel, c);

		depPanel.setBorder(BorderFactory.createTitledBorder("Vendas"));
		depPanel.setPreferredSize(new Dimension(350, 350));
		setLayout(new BorderLayout());

		add(depPanel, BorderLayout.CENTER);
	}

	public void notificar(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}

	private ClienteEditarMainPanel getPainelPrincipal() {
		Component c = getParent();
		while (c != null) {
			if (c instanceof ClienteEditarMainPanel)
				return (ClienteEditarMainPanel) c;
			c = c.getParent();
		}
		return (ClienteEditarMainPanel) c;
	}

	private class EventoTabela implements TableModelListener,
			ListSelectionListener {
		public void tableChanged(TableModelEvent e) {
		}

		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbVendas.getSelectedRow() > -1
					&& tbVendas.getRowCount() > 0);
			btExcluir.setEnabled(tbVendas.getSelectedRow() > -1
					&& tbVendas.getRowCount() > 0);
		}
	}
	

	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ClienteEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btNovo) {
				c.getIframeVenda().setVisible(true);
				//c.getIframeVenda().setTableModel((VendaClienteTableModel) tbVendas.getModel());
				c.getIframeVenda().setVenda(null);
				c.getIframeVenda().setCliente(cliente);
				
									
			} else if (e.getSource() == btEditar) {
								
				VendaVO d = (VendaVO) ((VendaClienteTableModel) tbVendas.getModel()).getSelectedValue(tbVendas.getSelectedRow());
				c.getIframeVenda().setVisible(true);
				//c.getIframeVenda().setTableModel((VendaClienteTableModel) tbVendas.getModel());
				c.getIframeVenda().setVenda(d);
				c.getIframeVenda().setCliente(cliente);
				
			
			} else if (e.getSource() == btExcluir) {

				try {
					VendaClienteTableModel model = ((VendaClienteTableModel) tbVendas.getModel());
					VendaVO venda = (VendaVO) model.getSelectedValue(tbVendas.getSelectedRow());
					if (c.mostraConfirmacao("Confirmação","Confirma a exclusão da venda?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						venda.setCliente(cliente);
						Controller.getInstance().deleteVenda(venda);
						carregarVendas();
						notificar("Venda deletada com sucesso.", BarraNotificacao.SUCESSO);
						
					}
				}catch (MensagemSaoDimasException ex) {
					ex.printStackTrace();
					notificar(ex.getMessage(),BarraNotificacao.AVISO);
				} catch (Exception ex) {
					ex.printStackTrace();
					notificar("Falha ao excluir a venda.",BarraNotificacao.ERRO);
				}
			}

		}
	}
}