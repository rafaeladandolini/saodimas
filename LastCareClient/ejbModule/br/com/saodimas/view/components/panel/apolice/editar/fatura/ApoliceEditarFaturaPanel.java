package br.com.saodimas.view.components.panel.apolice.editar.fatura;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.relatorio.GeradorRelatorio;
import br.com.saodimas.relatorio.NomeRelatorio;
import br.com.saodimas.view.components.menu.BarraMenu;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.FaturaTable;
import br.com.saodimas.view.components.table.model.FaturaTableModel;

@SuppressWarnings("serial")
public class ApoliceEditarFaturaPanel extends JPanel {
	public static final String FORM_NAME = "Editar Faturas";
	public static final String MENSAGEM_PADRAO = "Realize manuten��o das faturas.";

	private ApoliceVO apolice;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btImprimir;
	private JButton btImprimirComprovante;
	private JButton btQuitar;
	private JButton btQuitarParceiro;
	private JButton btBaixaSemCarne;
	private JButton btDetalhar;
	private JButton btEstornarBaixaFatura;
	private JToolBar barControle;
	private BarraNotificacao barNotificacao;

	private FaturaTable tbFaturas;

	private static final Dimension DBUTTON = new Dimension(30, 30);

	public ApoliceEditarFaturaPanel() {
		initialize();
		configure();
	}

	public void setApolice(ApoliceVO a) {
		this.apolice = a;
		this.carregarFaturas();
	}

	public void limparCampos() {
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		model.removeAll();
		model.fireTableDataChanged();

	}

	@Override
	public void setEnabled(boolean enabled) {
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbFaturas.getSelectedRow() == 1);
		btEstornarBaixaFatura.setEnabled(enabled && tbFaturas.getSelectedRow() == 1);
		btExcluir.setEnabled(enabled && tbFaturas.getSelectedRow() > 0);
		btImprimir.setEnabled(enabled && tbFaturas.getSelectedRow() > 0);
		btQuitar.setEnabled(enabled && tbFaturas.getSelectedRow() > 0);
		btQuitarParceiro.setEnabled(enabled && tbFaturas.getSelectedRow() > 0);
		btDetalhar.setEnabled(enabled && tbFaturas.getSelectedRow() == 1);
		barControle.setEnabled(enabled);
		tbFaturas.setEnabled(enabled);
		btBaixaSemCarne.setEnabled(enabled && tbFaturas.getSelectedRow() == 1 && faturaSelecionadaIsPagamentoSemCarne());
		super.setEnabled(enabled);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {

		}
	}
	
	/*
	 * Verifica se a fatura selecionada � fatura paga sem carne
	 * PAGAMENTO_SEM_CARNE = SIM
	 */
	private boolean faturaSelecionadaIsPagamentoSemCarne(){
		int row = tbFaturas.getSelectedRow();
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		FaturaVO fat = (FaturaVO) model.getSelectedValue(row);
		return fat.getIsPagamentoSemCarne() != null && fat.getIsPagamentoSemCarne().equals("Sim");
	}

	private void initialize() {

		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);

		tbFaturas = new FaturaTable();
		tbFaturas.getModel().addTableModelListener(new EventoTabela());
		tbFaturas.getSelectionModel().addListSelectionListener(
				new EventoTabela());

		tbFaturas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled())
					btEditar.doClick();
				super.mouseClicked(e);
			}
		});

		tbFaturas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE)
					btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT)
					btNovo.doClick();
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

		tbFaturas.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {   
			  
	          @Override  
	          public Component getTableCellRendererComponent(JTable table, Object value,   
	                  boolean isSelected, boolean hasFocus, int row, int column) {   
	              super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);   
	       //A primeira coluna � 0   
	              Object ref = table.getValueAt(row, 0);   
	     //Coloca cor em todas as linhas,COLUNA(0) que tem o nome "EMPRESTADO"   
	              if (ref != null && ref.equals("EMPRESTADO")) {   
	  
	                  setBackground(Color.green);   
	              } else {   
	                  setBackground(Color.red);   
	              }   
	              return this;   
	          }   
	      });  
		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addBill.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra uma nova fatura!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btEditar = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() == 1
						&& tbFaturas.getSelectedRow() != -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de uma fatura!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btExcluir = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() > 0
						&& tbFaturas.getSelectedRow() != -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um fatura!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btImprimir = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() > 0;
				// && tbFaturas.getSelectedRow() != -1;
				super.setEnabled(enable && habilitar);

			}
		};
		btImprimir.setIcon(new ImageIcon("imagens/imprimir.gif"));
		btImprimir.addActionListener(new EventoBotaoControle());
		btImprimir.setToolTipText("Imprimir faturas selecionadas!");
		btImprimir.setPreferredSize(DBUTTON);
		btImprimir.setEnabled(false);
		btImprimir.addKeyListener(tbFaturas.getKeyListeners()[0]);
		
		btImprimirComprovante = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() > 0;
				// && tbFaturas.getSelectedRow() != -1;
				super.setEnabled(enable && habilitar);

			}
		};
		btImprimirComprovante.setIcon(new ImageIcon("imagens/imprimir.gif"));
		btImprimirComprovante.addActionListener(new EventoBotaoControle());
		btImprimirComprovante.setToolTipText("Imprimir comprovante de pagamento das faturas selecionadas!");
		btImprimirComprovante.setPreferredSize(DBUTTON);
		btImprimirComprovante.setEnabled(false);
		btImprimirComprovante.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btQuitar = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() > 0;
				super.setEnabled(enable && habilitar);
			}
		};
		btQuitar.setIcon(new ImageIcon("imagens/quitarFaturas.gif"));
		btQuitar.addActionListener(new EventoBotaoControle());
		btQuitar.setToolTipText("Dar baixa nas faturas selecionadas!");
		btQuitar.setPreferredSize(DBUTTON);
		btQuitar.setEnabled(false);
		btQuitar.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btQuitarParceiro = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() > 0;
				super.setEnabled(enable && habilitar);

			}
		};
		btQuitarParceiro.setIcon(new ImageIcon("imagens/quitarFaturas.gif"));
		btQuitarParceiro.addActionListener(new EventoBotaoControle());
		btQuitarParceiro.setToolTipText("Dar baixa nas faturas com parceiro!");
		btQuitarParceiro.setPreferredSize(DBUTTON);
		btQuitarParceiro.setEnabled(false);
		btQuitarParceiro.addKeyListener(tbFaturas.getKeyListeners()[0]);
		
		
		btBaixaSemCarne = new JButton(){
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() == 1
						&& tbFaturas.getSelectedRow() != -1
						&& faturaSelecionadaIsPagamentoSemCarne();
				super.setEnabled(enable && habilitar);
				setEnableCamposPorPerfil();
			}
		};
		
		btBaixaSemCarne.setIcon(new ImageIcon("imagens/seta_abaixo_verde.png"));
		btBaixaSemCarne.addActionListener(new EventoBotaoControle());
		btBaixaSemCarne.setToolTipText("Destacado pagamento sem carne");
		btBaixaSemCarne.setPreferredSize(DBUTTON);
		btBaixaSemCarne.setEnabled(false);
		
		btDetalhar = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() == 1
						&& tbFaturas.getSelectedRow() != -1;
				super.setEnabled(enable && habilitar);
				setEnableCamposPorPerfil();
			}
		};
		btDetalhar.setIcon(new ImageIcon("imagens/resumeContract.gif"));
		btDetalhar.addActionListener(new EventoBotaoControle());
		btDetalhar.setToolTipText("Detalhar fatura");
		btDetalhar.setPreferredSize(DBUTTON);
		btDetalhar.setEnabled(false);
		btDetalhar.addKeyListener(tbFaturas.getKeyListeners()[0]);
		
		

		btEstornarBaixaFatura = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = faturasSelecionadas() == 1
						&& tbFaturas.getSelectedRow() != -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btEstornarBaixaFatura.setIcon(new ImageIcon("imagens/estornarbaixa.gif"));
		btEstornarBaixaFatura.addActionListener(new EventoBotaoControle());
		btEstornarBaixaFatura.setToolTipText("Estornar baixa de fatura");
		btEstornarBaixaFatura.setPreferredSize(DBUTTON);
		btEstornarBaixaFatura.setEnabled(false);


		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		barControle.add(btDetalhar);
		barControle.add(new JLabel("   "));
		barControle.addSeparator();
		barControle.add(new JLabel("   "));
		barControle.add(btQuitar);
		barControle.add(btQuitarParceiro);
		barControle.add(btBaixaSemCarne);
		barControle.add(new JLabel("   "));
		barControle.addSeparator();
		barControle.add(new JLabel("   "));
		barControle.add(btImprimir);
		barControle.add(btImprimirComprovante);
		barControle.add(new JLabel("                                    "));
		barControle.addSeparator();
		barControle.add(new JLabel("   "));
		barControle.add(btEstornarBaixaFatura);
		
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
		c.gridy = 0;
		infFatura.add(barNotificacao, c);

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

	public void carregarFaturas() {
		this.limparCampos();
		if (apolice != null) {
			FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
			List<FaturaVO> listFaturas = Controller.getInstance().getAllFaturasByApolice(apolice);
			apolice.setFaturas(listFaturas);
			model.loadData(listFaturas);
			model.fireTableDataChanged();
		}

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

	private void selecionarProximasFaturas() {
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		for (int i = 0; i < model.getDataVector().size(); i++) {
			FaturaVO fatura = (FaturaVO) model.getValueAt(i,
					FaturaTableModel.FATURA);
			if (i >= tbFaturas.getSelectedRow()) {
				fatura.setSelecionada(true);
			}
		}
		model.fireTableDataChanged();
	}

	private void novaFatura() {
		ApoliceEditarMainPanel c = getPainelPrincipal();
		c.getIframeFatura().setTableModel((FaturaTableModel) tbFaturas.getModel());
		c.getIframeFatura().setFatura(null);
		c.getIframeFatura().setVisible(true);
		c.getIframeFatura().habilitarControles(false);
	}

	private void editarFatura() {
		ApoliceEditarMainPanel c = getPainelPrincipal();
		int row = tbFaturas.getSelectedRow();
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		FaturaVO fat = (FaturaVO) model.getSelectedValue(row);
		
		if(fat.getDataPagamento() != null)
		{
			JOptionPane.showMessageDialog(null, "N�o � poss�vel editar uma fatura j� dada baixa.", "Aten��o", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		fat.setSelecionada(true);

		c.getIframeFatura().setTableModel(
				(FaturaTableModel) tbFaturas.getModel());
		c.getIframeFatura().setFatura(fat);
		c.getIframeFatura().setVisible(true);
		c.getIframeFatura().habilitarControles(false);
	}

	private void excluirFaturas() {
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		int idSelecao = tbFaturas.getSelectedRow();
		ApoliceEditarMainPanel c = getPainelPrincipal();
		if (idSelecao == -1)
			return;

		FaturaVO primeiraFatura = (FaturaVO) model.getSelectedValue(idSelecao);
		
			if (primeiraFatura.getDataPagamento() != null && SaoDimasMain.colaborador.getTipoColaborador().equals(ColaboradorVO.FUNCIONARIO))
			{
				JOptionPane.showMessageDialog(null, "N�o � poss�vel excluir uma fatura j� dada baixa.", "Aten��o", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			selecionarProximasFaturas();
			int numSelecionadas = faturasSelecionadas();
			String mensagem = (numSelecionadas == 1 ? "Confirma a exclus�o da fatura?"
					: "Todas faturas com vencimento posteriores a "
							+ new SimpleDateFormat("dd/MM/yyyy").format(primeiraFatura.getDataVencimento())
							+ " tamb�m ser�o exclu�das!\nDeseja excluir essas " + numSelecionadas + " faturas?");
			if (c.mostraConfirmacao("Confirma��o", mensagem, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				List<FaturaVO> faturasSelecionadas = new ArrayList<FaturaVO>();
				for (Object item : model.getDataVector()) {
					FaturaVO f = (FaturaVO) item;
					int row = model.getDataVector().indexOf(f);
					if ((Boolean) model.getValueAt(row, FaturaTableModel.SELECAO)) {
						faturasSelecionadas.add(f);
					}
				}
				try {
				Controller.getInstance().deleteFaturas(faturasSelecionadas);
				this.carregarFaturas();
			
		} catch (MensagemSaoDimasException e) {
			c.mostrarMensagemFatura("Erro ao excluir a fatura.", BarraNotificacao.ERRO);
		}
			}
		
	}

	private List<FaturaVO> getFaturasSelecionadas() {
		List<FaturaVO> list = new ArrayList<FaturaVO>();
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		int numLinhas = model.getRowCount();

		if (model.getDataVector().size() > 0)
			for (int i = 0; i < numLinhas; i++)
				if ((Boolean) model.getValueAt(i, FaturaTableModel.SELECAO)) {
					FaturaVO fatu = (FaturaVO) model.getSelectedValue(i);

					// formata a data para n�o dar erro no ireport - impress�o
					// de fatura.
					if (fatu.getDataVencimento() != null) {
						java.sql.Date dateSql = new java.sql.Date(fatu
								.getDataVencimento().getTime());
						fatu.setDataVencimento(dateSql);
					}

					fatu.setApolice(this.apolice);
					list.add(fatu);
				}

		return list;
	}

	private void imprimirFaturas() {
		List<FaturaVO> listFaturasImprimir = getFaturasSelecionadas();
		new GeradorRelatorio().gerar(NomeRelatorio.CARNE_FATURAS,
				listFaturasImprimir);
	}
	
	private void imprimirComprovanteFaturas() {

		List<FaturaVO> listFaturasImprimir = getFaturasSelecionadas();
		try{
			this.validaSeFoiDadoBaixa(listFaturasImprimir);
			new GeradorRelatorio().gerar(NomeRelatorio.COMPROVANTES_FATURAS,
					listFaturasImprimir);
			
		}catch(MensagemSaoDimasException e){
			JOptionPane.showMessageDialog(null,e.getMessage(),"Aten��o", JOptionPane.ERROR_MESSAGE);
		}
			
	}

	private void validaSeFoiDadoBaixa(List<FaturaVO> listFaturasImprimir)throws MensagemSaoDimasException
	{
		for(FaturaVO vo : listFaturasImprimir){
			if(vo.getDataPagamento() == null || (vo.getIsPagamentoSemCarne() != null && !vo.getIsPagamentoSemCarne().equalsIgnoreCase("Sim")))
				throw new MensagemSaoDimasException("S� � permitido impress�o de comprovante sem carne se a fatura j� foi dada baixa no sistema e com op��o sem carne marcada!");
		}
	}
	private void quitarFaturas() {
		int idSelecao = tbFaturas.getSelectedRow();
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		if (idSelecao >= 0 && faturasSelecionadas() == 0)
			((FaturaVO) model.getSelectedValue(idSelecao)).setSelecionada(true);
		ApoliceEditarMainPanel c = getPainelPrincipal();
		c.getIframeQuitar().setApolice(apolice);
		c.getIframeQuitar().setTableModel(model);
		c.getIframeQuitar().setVisible(true);
		c.getIframeQuitar().habilitarControles(false);


	}
	

	
	private void destacadaBaixaFaturaPagaSemCarne() {
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		int idSelecao = tbFaturas.getSelectedRow();
		ApoliceEditarMainPanel c = getPainelPrincipal();
		if (idSelecao == -1)
			return;

		FaturaVO faturavo = (FaturaVO) model.getSelectedValue(idSelecao);
			
		if (c.mostraConfirmacao("Confirma��o", "Confirmar destaque do carne da fatura paga sem carne?",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			try {
				faturavo.setIsPagamentoSemCarne("Destacada");
				Controller.getInstance().destacarFatura(faturavo);
				this.carregarFaturas();
			} catch (MensagemSaoDimasException e) {
				c.mostrarMensagemFatura("Erro ao alterar dados da fatura.",
						BarraNotificacao.ERRO);
			}

		}
	}

	private void quitarFaturasParceiro() {
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

	private void detalharFatura() {
		int idSelecao = tbFaturas.getSelectedRow();
		FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
		FaturaVO fat = ((FaturaVO) model.getSelectedValue(idSelecao));
		ApoliceEditarMainPanel c = getPainelPrincipal();
		c.getIframeDetalharFatura().setFatura(fat);
		c.getIframeDetalharFatura().setVisible(true);
		c.getIframeDetalharFatura().habilitarControles(false);

	}
	
	private void estornarBaixaFatura() {
		ApoliceEditarMainPanel c = getPainelPrincipal();
		try {
			int idSelecao = tbFaturas.getSelectedRow();
			FaturaTableModel model = (FaturaTableModel) tbFaturas.getModel();
			FaturaVO fat = ((FaturaVO) model.getSelectedValue(idSelecao));
			if (c.mostraConfirmacao("Confirma��o","Confirma o estorno da baixa da fatura?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				Controller.getInstance().estornarBaixaFatura(fat);
				notificar("Estorno da baixa da fatura efetuado com sucesso.", BarraNotificacao.SUCESSO);
			}
			
			this.carregarFaturas();
		}catch (MensagemSaoDimasException ex) {
			notificar(ex.getMessage(),BarraNotificacao.AVISO);
		} catch (Exception ex) {
			notificar("Falha ao estornar a baixa da fatura.",BarraNotificacao.ERRO);
		}
	

	}

	private class EventoTabela implements TableModelListener,
			ListSelectionListener {
		public void tableChanged(TableModelEvent ev) {
			if (ev.getColumn() > -1) {
				int qntde = faturasSelecionadas();
				btImprimir.setEnabled(qntde > 0);
				btImprimirComprovante.setEnabled(qntde > 0);
				btQuitar.setEnabled(qntde > 0);
				btQuitarParceiro.setEnabled(qntde > 0);
				btDetalhar.setEnabled(qntde > 0);
				btBaixaSemCarne.setEnabled(qntde == 1);
				int idSelecao = tbFaturas.getSelectedRow();
				btExcluir.setEnabled(qntde > 0 && idSelecao != -1);
				btEditar.setEnabled(qntde == 1);
				btEstornarBaixaFatura.setEnabled(qntde == 1);
				btQuitarParceiro.setEnabled(qntde == 1);
				setEnableCamposPorPerfil();
			}
		}

		public void valueChanged(ListSelectionEvent e) {
			boolean linhaSelecionada = (tbFaturas.getSelectedRow() > -1);
			int qtdeFatSelecionadas = faturasSelecionadas();
			
			btEditar.setEnabled(linhaSelecionada && qtdeFatSelecionadas < 2);
			btEstornarBaixaFatura.setEnabled(linhaSelecionada && qtdeFatSelecionadas < 2);
			btBaixaSemCarne.setEnabled(linhaSelecionada && qtdeFatSelecionadas < 2 && faturaSelecionadaIsPagamentoSemCarne());
			btExcluir.setEnabled(linhaSelecionada
					&& tbFaturas.getSelectedRow() != -1);
			btImprimir.setEnabled(linhaSelecionada);
			btQuitar.setEnabled(linhaSelecionada);
			btQuitarParceiro.setEnabled(linhaSelecionada);
			btDetalhar.setEnabled(linhaSelecionada);
			if (linhaSelecionada) {
				FaturaTableModel model = (FaturaTableModel) tbFaturas
						.getModel();
				FaturaVO fat = model.getDataVector().get(
						tbFaturas.getSelectedRow());
				btQuitar.setEnabled(fat.getDataPagamento() == null);
				btQuitarParceiro.setEnabled(fat.getDataPagamento() == null);
				btEditar.setEnabled(fat.getDataPagamento() == null);
				btDetalhar.setEnabled(fat.getDataPagamento() != null);
			}
		}
	}

	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btNovo)
				novaFatura();

			else if (e.getSource() == btEditar)
				editarFatura();

			else if (e.getSource() == btExcluir)
				excluirFaturas();

			else if (e.getSource() == btImprimir)
				imprimirFaturas();
			
			else if (e.getSource() == btImprimirComprovante)
				imprimirComprovanteFaturas();

			else if (e.getSource() == btQuitar)
				quitarFaturas();

			else if (e.getSource() == btQuitarParceiro)
				quitarFaturasParceiro();
			
			else if (e.getSource() == btBaixaSemCarne)
				destacadaBaixaFaturaPagaSemCarne();

			else if (e.getSource() == btDetalhar)
				detalharFatura();
			
			else if (e.getSource() == btEstornarBaixaFatura)
				estornarBaixaFatura();

		}

	}

	public void notificar(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}

	public void setEnableCamposPorPerfil() {
		ColaboradorVO funcionario = SaoDimasMain.colaborador;
		int perfil = (funcionario == null) ? BarraMenu.SEM_PERFIL
				: (funcionario.getTipoColaborador().startsWith("A")) ? BarraMenu.PERFIL_ADMIN
						: BarraMenu.PERFIL_FUNC;

		btQuitarParceiro.setEnabled(perfil == BarraMenu.PERFIL_ADMIN);
	}
	


}