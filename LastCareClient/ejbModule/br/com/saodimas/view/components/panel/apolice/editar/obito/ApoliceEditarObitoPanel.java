package br.com.saodimas.view.components.panel.apolice.editar.obito;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.relatorio.GeradorRelatorio;
import br.com.saodimas.relatorio.NomeRelatorio;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.ObitoTable;
import br.com.saodimas.view.components.table.model.ObitoTableModel;

@SuppressWarnings("serial")
public class ApoliceEditarObitoPanel extends JPanel {
	public static final String FORM_NAME = "Editar Óbitos";
	public static final String MENSAGEM_PADRAO = "Realize manutenção dos obitos.";

	private ApoliceVO apolice;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btImprimir;
	private JButton btVisualizar;
	private JToolBar barControle;
	private ObitoTable tbObitos;
	private BarraNotificacao barNotificacao;

	private static final Dimension DBUTTON = new Dimension(30, 30);

	public ApoliceEditarObitoPanel() {
		initialize();
		configure();
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	public List<ProdutoObitoVO> getRemoverProdutosObitos() {
		ApoliceEditarMainPanel c = getPainelPrincipal();
		return c.getIframeObito().getProdutosObitoRemover();
	}
	
	public List<ServicoObitoVO> getRemoverServicosObitos() {
		ApoliceEditarMainPanel c = getPainelPrincipal();
		return c.getIframeObito().getServicosObitoRemover();
	} 
	
	public void notificar(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}
	
	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
		this.carregarObitos();
	}
	
	public void carregarObitos()
	{
		ObitoTableModel model = (ObitoTableModel)tbObitos.getModel();
		model.removeAll();
		if(this.apolice != null)
		{
			List<ObitoVO> listObitos = Controller.getInstance().getAllObitosByApolice(apolice);
			apolice.setObitos(listObitos);
			model.loadData(listObitos);
		}
		model.fireTableDataChanged();
	}

	
	@Override
	public void setEnabled(boolean enabled) {
    	btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbObitos.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbObitos.getSelectedRow() > -1);
		btVisualizar.setEnabled(enabled && tbObitos.getSelectedRow() > -1);
		btImprimir.setEnabled(enabled && tbObitos.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbObitos.setEnabled(enabled);
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
		
		tbObitos = new ObitoTable();
		tbObitos.getModel().addTableModelListener(new EventoTabela());
		tbObitos.getSelectionModel().addListSelectionListener(
				new EventoTabela());
		tbObitos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled())
					btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbObitos.addKeyListener(new KeyAdapter() {
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
		btNovo.setIcon(new ImageIcon("imagens/addDeath.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra um novo registro de óbito!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbObitos.getKeyListeners()[0]);

		btEditar = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbObitos.getRowCount() > 0
						&& tbObitos.getSelectedRow() > -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de um registro de óbito!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbObitos.getKeyListeners()[0]);

		btExcluir = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbObitos.getRowCount() > 0
						&& tbObitos.getSelectedRow() > -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um registro de óbito!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbObitos.getKeyListeners()[0]);
		
		btImprimir = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbObitos.getRowCount() > 0
						&& tbObitos.getSelectedRow() > -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btImprimir.setIcon(new ImageIcon("imagens/imprimir.gif"));
		btImprimir.addActionListener(new EventoBotaoControle());
		btImprimir.setToolTipText("Imprime informações de um registro de óbito!");
		btImprimir.setPreferredSize(DBUTTON);
		btImprimir.setEnabled(false);
		btImprimir.addKeyListener(tbObitos.getKeyListeners()[0]);

		btVisualizar = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbObitos.getRowCount() > 0
						&& tbObitos.getSelectedRow() > -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btVisualizar.setIcon(new ImageIcon("imagens/search.gif"));
		btVisualizar.addActionListener(new EventoBotaoControle());
		btVisualizar.setToolTipText("Visualizar!");
		btVisualizar.setPreferredSize(DBUTTON);
		btVisualizar.setEnabled(false);
		btVisualizar.addKeyListener(tbObitos.getKeyListeners()[0]);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btVisualizar);
		barControle.addSeparator();
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		barControle.addSeparator();
		barControle.add(btImprimir);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infObito = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infObito.add(barNotificacao, c);
		
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		infObito.add(barControle, c);

		JScrollPane tbFatPanel = new JScrollPane(tbObitos);
		tbFatPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		infObito.add(tbFatPanel, c);

		infObito.setBorder(BorderFactory.createTitledBorder("Obitos"));
		infObito.setPreferredSize(new Dimension(350, 350));
		setLayout(new BorderLayout());

		add(infObito, BorderLayout.CENTER);
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

	private class EventoTabela implements TableModelListener,
			ListSelectionListener {
		public void tableChanged(TableModelEvent e) {
		}

		public void valueChanged(ListSelectionEvent e) {
			btEditar.setEnabled(tbObitos.getSelectedRow() > -1);
			btExcluir.setEnabled(tbObitos.getSelectedRow() > -1);
			btVisualizar.setEnabled(tbObitos.getSelectedRow() > -1);
			btImprimir.setEnabled(tbObitos.getSelectedRow() > -1);
		}
	}

	/*private boolean isApoliceEmDia()
	{
		Calendar c = Calendar.getInstance();
		List<FaturaVO> faturas = apolice.getFaturas();
		
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date dataHoje = new Date(c.getTimeInMillis());
		Date dataVencimento = null;
		
		for (FaturaVO faturaVO : faturas) {
			c.setTimeInMillis(faturaVO.getDataVencimento().getTime());
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			dataVencimento = new Date(c.getTimeInMillis());
			if((dataVencimento.equals(dataHoje) || dataVencimento.before(dataHoje))
				&& faturaVO.getDataPagamento() == null )
				
				return false;
		}
		
		if(faturas.size() == 0)
			return false;
		return true;
	}*/
	
	private boolean hasAssociadosVivos()
	{
		List<AssociadoVO> dependentes = apolice.getDependentes();
		for (AssociadoVO dep : dependentes) {
			if(dep.getObito() == null)
				return true;
		}
		
		return false;
	}
	
	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = getPainelPrincipal();
			 if (e.getSource() == btNovo) {
				//if(!SaoDimasMain.colaborador.getTipoColaborador().equals(ListasComuns.ADMINISTRADOR) && !isApoliceEmDia())
				//	barNotificacao.mostrarMensagem("Não é possível cadastrar o obito. Apolice com pendência de Faturas.", BarraNotificacao.ERRO);
				/*else*/
				if(!hasAssociadosVivos())
					barNotificacao.mostrarMensagem("Não é possível cadastrar o obito. Não há associados disponíveis.", BarraNotificacao.ERRO);
				else if(apolice.getStatus().equalsIgnoreCase("Ativo"))
				{
					c.getIframeObito().setConfigVisualizarObito(false, false);
					c.getIframeObito().setObito(null);
					c.getIframeObito().adicionaApolice(apolice);
					c.getIframeObito().setVisible(true);
					c.getIframeObito().habilitarControles(false);
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Não é possivel cadastrar óbito: Plano " + apolice.getStatus().toUpperCase() , "Aviso", JOptionPane.INFORMATION_MESSAGE );
				}
			} else if (e.getSource() == btEditar) {
				
				int row = tbObitos.getSelectedRow();
				ObitoTableModel model = (ObitoTableModel) tbObitos.getModel();
				ObitoVO obi = (ObitoVO) model.getSelectedValue(row);
				//c.getIframeObito().adicionaApolice(apolice);
				obi.setApolice(apolice);
				c.getIframeObito().setConfigVisualizarObito(false, false);
				c.getIframeObito().setObito(obi);
				c.getIframeObito().setVisible(true);
				c.getIframeObito().habilitarControles(false);
				
			} 
			else if (e.getSource() == btVisualizar) {
				
				int row = tbObitos.getSelectedRow();
				ObitoTableModel model = (ObitoTableModel) tbObitos.getModel();
				ObitoVO obi = (ObitoVO) model.getSelectedValue(row);
				c.getIframeObito().setObito(obi);
				c.getIframeObito().setVisible(true);
				c.getIframeObito().habilitarControles(false);
				c.getIframeObito().setConfigVisualizarObito(true, false);
				
			} 
			else if (e.getSource() == btExcluir) {
				if (c.mostraConfirmacao("Confirmação","Confirma a exclusão do óbito?",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					int row = tbObitos.getSelectedRow();
					ObitoTableModel model = (ObitoTableModel) tbObitos.getModel();
					ObitoVO removeObito = (ObitoVO) model.getSelectedValue(row);
					
					if(apolice.getObitos().remove(removeObito)){
					
						removeObito.setApolice(apolice);
						removeObito.getAssociado().setObito(null);
						
						try {
							Controller.getInstance().deleteObito(removeObito);
							carregarObitos();
							c.carregarDependentes();
							c.atualizarInformacoesSuperiores();
							barNotificacao.mostrarMensagem("Obito deletado com sucesso.", BarraNotificacao.SUCESSO);
							
						} catch (MensagemSaoDimasException e1) {
							e1.printStackTrace();
							barNotificacao.mostrarMensagem("Não foi possível remover o obito. Erro:" + e1.getMessage(), BarraNotificacao.ERRO);
						}
						
					}
				}

			} else if (e.getSource() == btImprimir) {
				int row = tbObitos.getSelectedRow();
				ObitoTableModel model = (ObitoTableModel) tbObitos.getModel();
				ObitoVO obitoImprimir = (ObitoVO) model.getSelectedValue(row);
				List<ObitoVO> list = new ArrayList<ObitoVO>();
				obitoImprimir.setApolice(apolice);
				list.add(obitoImprimir);
				new GeradorRelatorio().gerarComSubRelatorioObito(NomeRelatorio.DADOS_OBITO,list);
				
				
			}
		}
	}
}
