package br.com.saodimas.view.components.panel.apolice.editar.dependente;

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
import java.util.Vector;

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
import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.relatorio.GeradorRelatorio;
import br.com.saodimas.relatorio.NomeRelatorio;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.DependenteTable;
import br.com.saodimas.view.components.table.model.DependenteTableModel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class ApoliceEditarDependentePanel extends JPanel {
	public static final String FORM_NAME = "Editar Dependentes";
	public static final String MENSAGEM_PADRAO = "Realize manutenção dos dependentes.";

	private ApoliceVO apolice;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btCarteirinha;
	private JButton btCarteirinhaQtde;
	private JButton btTermoCarteirinhaImpressao;
	private JToolBar barControle;
	private DependenteTable tbDependentes;

	private BarraNotificacao barNotificacao;
	private static final Dimension DBUTTON = new Dimension(30, 30);

	public ApoliceEditarDependentePanel() {
		initialize();
		configure();
	}

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag)
			btNovo.requestFocus();
	}

	public void setApolice(ApoliceVO a) {
		apolice = a;
		if (apolice != null)
			this.carregarDependentes();
		else {
			DependenteTableModel model = (DependenteTableModel) tbDependentes
					.getModel();
			model.removeAll();
		}

		this.mostrarDica();

	}
	

	public void carregarDependentes()
	{
		DependenteTableModel model = (DependenteTableModel) tbDependentes.getModel();
		model.removeAll();
		List<AssociadoVO> listDep = Controller.getInstance().getAllDependentesByApolice(apolice);
		model.loadData(listDep);
		model.fireTableDataChanged();
		this.apolice.setDependentes(listDep);
	}
	
	public void mostrarDica() {
		if (apolice != null) {
			PlanoVO plano = getPainelPrincipal().getPlanoSelecionadoCombo();
			if (plano != null) {
				int limiteAtual = plano.getLimiteAssociado() + plano.getAssociado_extra()- getCountAssociadosVivos();
				if (limiteAtual > 0) {
					btNovo.setEnabled(true);
					barNotificacao.mostrarMensagem("Você pode adicionar mais " + limiteAtual
							+ " dependente" + (limiteAtual > 1 ? "s!" : "!") , BarraNotificacao.DICA);
				} else if (limiteAtual == 0){
					btNovo.setEnabled(false);
					barNotificacao.mostrarMensagem("Todos dependentes cobertos pelo plano "
							+ plano.getDescricao()
							+ " foram cadastrados. Limite: "
							+ (plano.getLimiteAssociado() + plano.getAssociado_extra()) + ".", BarraNotificacao.AVISO);
				}else 
				{
					barNotificacao.mostrarMensagem("Há mais dependentes cadastrados do que o permitido pelo plano "
					+ plano.getDescricao()
					+ ". Qtde permitida de dependetes: "
					+ (plano.getLimiteAssociado() + plano.getAssociado_extra()) + ".", BarraNotificacao.ERRO);
				}
			}
		}
	}

	private int getCountAssociadosVivos()
	{
		int x = 0;
		DependenteTableModel model = (DependenteTableModel) tbDependentes.getModel();
		Vector<AssociadoVO> list = model.getDataVector();
		for (AssociadoVO associadoVO : list) {
			if(associadoVO.getObito() == null)
				x++;
		}
		return x;
	}
	@Override
	public void requestFocus() {
		btNovo.requestFocus();
	}

	private void initialize() {
		
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		this.mostrarDica();
		
		tbDependentes = new DependenteTable();
		tbDependentes.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbDependentes.getModel().addTableModelListener(new EventoTabela());
		tbDependentes.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled())
					btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbDependentes.addKeyListener(new KeyAdapter() {
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

		btNovo = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				int limiteAtual = apolice != null ? apolice.getPlano()
						.getLimiteAssociado() + apolice.getPlano().getAssociado_extra()
						- getCountAssociadosVivos() : 1;
				boolean habilitar = limiteAtual > 0;
				super.setEnabled(enable & habilitar);
			}
		};
		
		btNovo.setIcon(new ImageIcon("imagens/addRelative.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastrar um novo dependente!");
		btNovo.setPreferredSize(DBUTTON);

		btEditar = new JButton();
		
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Clique para editar um dependente!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);

		btExcluir = new JButton();
		
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Clique para remover um dependente");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);

		btCarteirinha = new JButton(new ImageIcon("imagens/equipamento.gif"));
		btCarteirinha.addActionListener(new EventoBotaoControle());
		btCarteirinha.setToolTipText("Clique para imprimir uma carteirinha");
		btCarteirinha.setPreferredSize(DBUTTON);
		btCarteirinha.setEnabled(false);
		
		btCarteirinhaQtde = new JButton(new ImageIcon("imagens/add_carteirinha.png"));
		btCarteirinhaQtde.addActionListener(new EventoBotaoControle());
		btCarteirinhaQtde.setToolTipText("Atualizar qtde cartão novo para o associado.");
		btCarteirinhaQtde.setPreferredSize(DBUTTON);
		btCarteirinhaQtde.setEnabled(false);
		
		btTermoCarteirinhaImpressao = new JButton(new ImageIcon("imagens/imprimir.gif"));
		btTermoCarteirinhaImpressao.addActionListener(new EventoBotaoControle());
		btTermoCarteirinhaImpressao.setToolTipText("Imprimir termo responsábilidade cartões");
		btTermoCarteirinhaImpressao.setPreferredSize(DBUTTON);
		btTermoCarteirinhaImpressao.setEnabled(false);
		
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
		barControle.add(btCarteirinha);
		barControle.addSeparator();
		barControle.add(btCarteirinhaQtde);
		barControle.add(btTermoCarteirinhaImpressao);
		
		
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

		JScrollPane tbDepPanel = new JScrollPane(tbDependentes);
		tbDepPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		depPanel.add(tbDepPanel, c);

		depPanel.setBorder(BorderFactory.createTitledBorder("Dependentes"));
		depPanel.setPreferredSize(new Dimension(350, 350));
		setLayout(new BorderLayout());

		add(depPanel, BorderLayout.CENTER);
	}

	public void notificar(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
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

		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbDependentes.getSelectedRowCount() == 1);
			btExcluir.setEnabled(tbDependentes.getSelectedRowCount() == 1);
			btCarteirinhaQtde.setEnabled(tbDependentes.getSelectedRowCount() == 1);
		}
	}

	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btNovo) {
				c.getIframeDependente().setVisible(true);
				c.getIframeDependente().setApolice(apolice);
				c.getIframeDependente().setTableModel((DependenteTableModel) tbDependentes.getModel());
				c.getIframeDependente().setDependente(null);
				
			}else if(e.getSource() == btCarteirinha){
				
				List<AssociadoVO> list = new ArrayList<AssociadoVO>();
				AssociadoVO d = null;
				
				int count = tbDependentes.getSelectedRowCount(); 
				if(count == 1){
					d = (AssociadoVO) ((DependenteTableModel) tbDependentes.getModel()).getSelectedValue(tbDependentes.getSelectedRow());
					list.add(d);
				}else if(count > 1){
					int linhasSelecionadas[] = tbDependentes.getSelectedRows();
					for(int i = 0 ; i < linhasSelecionadas.length; i++)
					{
						d = (AssociadoVO) ((DependenteTableModel) tbDependentes.getModel()).getSelectedValue(linhasSelecionadas[i]);
						list.add(d);				}
				}
				
				if(apolice.getEmpresa() != null)
					new GeradorRelatorio().gerarCarteirinhasDependentes(NomeRelatorio.CARTEIRINHA_EMPRESARIAL, list, apolice);
				else 
					new GeradorRelatorio().gerarCarteirinhasDependentes(NomeRelatorio.CARTEIRINHA_UNICA, list, apolice);
				
			}else if(e.getSource() == btTermoCarteirinhaImpressao){
				
				AssociadoVO d = null;
				List<AssociadoVO> listaCarteirinhas = new ArrayList<AssociadoVO>();
				
				int count = tbDependentes.getSelectedRowCount(); 
				if(count == 1){
					d = (AssociadoVO) ((DependenteTableModel) tbDependentes.getModel()).getSelectedValue(tbDependentes.getSelectedRow());
					listaCarteirinhas.add(d);
				}else if(count > 1){
					int linhasSelecionadas[] = tbDependentes.getSelectedRows();
					for(int i = 0 ; i < linhasSelecionadas.length; i++)
					{
						d = (AssociadoVO) ((DependenteTableModel) tbDependentes.getModel()).getSelectedValue(linhasSelecionadas[i]);
						listaCarteirinhas.add(d);
						
					}
				}
				
				new GeradorRelatorio().gerarTermoCarteinha(NomeRelatorio.TERMO_RESPONSABILIDADE_CARTEIRINHA, apolice, listaCarteirinhas);
				
			} else if (e.getSource() == btEditar) {
								
				AssociadoVO d = (AssociadoVO) ((DependenteTableModel) tbDependentes.getModel()).getSelectedValue(tbDependentes.getSelectedRow());
				if(d.getObito() == null){
					c.getIframeDependente().setApolice(apolice);
					c.getIframeDependente().setVisible(true);
					c.getIframeDependente().setTableModel((DependenteTableModel) tbDependentes.getModel());
					c.getIframeDependente().setDependente(d);
					
				}else
				{
					JOptionPane.showMessageDialog(WinManager.getJanelaInicial(),"Óbito cadastrado, não é possível efetuar a alteração","Informação", JOptionPane.INFORMATION_MESSAGE);
				}
			
				} else if (e.getSource() == btCarteirinhaQtde)
				{
					int result = JOptionPane.CANCEL_OPTION;
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					AssociadoVO d = (AssociadoVO) ((DependenteTableModel) tbDependentes.getModel()).getSelectedValue(tbDependentes.getSelectedRow());
					
					if(d.getQtdeCartao() >= 1){
						result = JOptionPane.showConfirmDialog (null, "Já foi impresso " + d.getQtdeCartao() + " cartão(es) para esse associado. \nÚltima impressão dia: " + new SimpleDateFormat("dd/MM/yyyy").format(d.getDataUltimaImpressao()) +". \nDeseja atualizar uma nova impressão?" ,"Informação",dialogButton);
					}else{					

						result = JOptionPane.showConfirmDialog (null, "Informar impressão cartão novo ?","Informação",dialogButton);
					}
					
	                if(result == JOptionPane.YES_OPTION)
	                {
						try {
						
							
							Controller.getInstance().atualizarDadosImpressaoCartao(d);
								
							JOptionPane.showMessageDialog(WinManager.getJanelaInicial(), "Dados atualizados com sucesso.","Informação", JOptionPane.INFORMATION_MESSAGE);
							carregarDependentes();
							
								
						} catch (MensagemSaoDimasException ex) {
							ex.printStackTrace();
							notificar(ex.getMessage(), BarraNotificacao.AVISO);
						} catch (Exception ex) {
							ex.printStackTrace();
							notificar("Falha ao atualizar as informações", BarraNotificacao.ERRO);
						}
					}	
						
	
				} 
				else if (e.getSource() == btExcluir) {

				try {
					DependenteTableModel model = ((DependenteTableModel) tbDependentes.getModel());
					AssociadoVO d = (AssociadoVO) model.getSelectedValue(tbDependentes.getSelectedRow());
					if (d.getObito() != null) {
						JOptionPane.showMessageDialog(WinManager.getJanelaInicial(),"Dependente não pode ser excluído.\nObito em seu nome.","Informação", JOptionPane.INFORMATION_MESSAGE);
					} 
					else
					if (c.mostraConfirmacao("Confirmação","Confirma a exclusão do dependente?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						d.setApolice(apolice);
						Controller.getInstance().deleteAssociado(d);
						carregarDependentes();
						notificar("Dependente deletado com sucesso.", BarraNotificacao.SUCESSO);
						
					}
					
				
				}catch (MensagemSaoDimasException ex) {
					ex.printStackTrace();
					notificar(ex.getMessage(),BarraNotificacao.AVISO);
				} catch (Exception ex) {
					ex.printStackTrace();
					notificar("Falha ao excluir o dependente.",
							BarraNotificacao.ERRO);
				}
			}

		}
	}
}