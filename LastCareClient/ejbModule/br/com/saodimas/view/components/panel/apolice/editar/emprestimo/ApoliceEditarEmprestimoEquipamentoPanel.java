package br.com.saodimas.view.components.panel.apolice.editar.emprestimo;

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
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.EmprestimoEquipamentoApoliceTable;
import br.com.saodimas.view.components.table.model.EmprestimoEquipamentoApoliceTableModel;

@SuppressWarnings("serial")
public class ApoliceEditarEmprestimoEquipamentoPanel extends JPanel {
	public static final String FORM_NAME = "Emprestimos Equipamentos Apolice"; 
		
	private ApoliceVO apolice;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btDevolucao;
	private JToolBar barControle;
	private EmprestimoEquipamentoApoliceTable tbEmprestimo;

	private BarraNotificacao barNotificacao;
	private static final Dimension DBUTTON = new Dimension(30, 30);

	public ApoliceEditarEmprestimoEquipamentoPanel() {
		initialize();
		configure();
	}

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag){
			btNovo.requestFocus();
			this.carregarEmprestimos();
		}
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	public void setApolice(ApoliceVO a) {
		apolice = a;
	}

	public void carregarEmprestimos()
	{
		if(apolice != null)
		{
			List<EmprestimoEquipamentoVO> list = Controller.getInstance().getEmprestimoEquipamentoAllByApolice(apolice);
			EmprestimoEquipamentoApoliceTableModel model = ((EmprestimoEquipamentoApoliceTableModel)tbEmprestimo.getModel());
			model.removeAll();
			apolice.setEmprestimos(list);
			model.loadData(list);
			model.fireTableDataChanged();
		}
			
	}
	
		
	@Override
	public void requestFocus() {
		btNovo.requestFocus();
	}

	private void initialize() {
		
		barNotificacao = new BarraNotificacao("");
		
		tbEmprestimo = new EmprestimoEquipamentoApoliceTable();
		tbEmprestimo.getSelectionModel().addListSelectionListener(
				new EventoTabela());
		tbEmprestimo.getModel().addTableModelListener(new EventoTabela());
		tbEmprestimo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled())
					btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbEmprestimo.addKeyListener(new KeyAdapter() {
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
		btNovo.setIcon(new ImageIcon("imagens/novo_emprestimo.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastrar um novo emprestimo!");
		btNovo.setPreferredSize(DBUTTON);

		btEditar = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbEmprestimo.getRowCount() > 0
						&& tbEmprestimo.getSelectedRow() > -1;
				super.setEnabled(enable & habilitar);
			}
		};
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Clique para editar um emprestimo!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);

		btExcluir = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbEmprestimo.getRowCount() > 0
						&& tbEmprestimo.getSelectedRow() > -1;
				super.setEnabled(enable & habilitar);
			}
		};
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Clique para remover um emprestimo");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		
		btDevolucao = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbEmprestimo.getRowCount() > 0
						&& tbEmprestimo.getSelectedRow() > -1;
				super.setEnabled(enable & habilitar);
			}
		};
		btDevolucao.setIcon(new ImageIcon("imagens/devolver_emprestimo.gif"));
		btDevolucao.addActionListener(new EventoBotaoControle());
		btDevolucao.setToolTipText("Clique para devolver um equipamento");
		btDevolucao.setPreferredSize(DBUTTON);
		btDevolucao.setEnabled(false);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		barControle.add(btDevolucao);
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

		JScrollPane tbDepPanel = new JScrollPane(tbEmprestimo);
		tbDepPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		depPanel.add(tbDepPanel, c);

		depPanel.setBorder(BorderFactory.createTitledBorder("Emprestimos"));
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
			btEditar.setEnabled(tbEmprestimo.getSelectedRow() > -1
					&& tbEmprestimo.getRowCount() > 0 ); 
			btExcluir.setEnabled(tbEmprestimo.getSelectedRow() > -1
					&& tbEmprestimo.getRowCount() > 0);
			btDevolucao.setEnabled(tbEmprestimo.getSelectedRow() > -1
					&& tbEmprestimo.getRowCount() > 0);
		}
	}
	
	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btNovo) {
				if(apolice.getStatus().equalsIgnoreCase("Ativo"))
				{
					c.getIframeEmprestimoEquipamento().setVisible(true);
					c.getIframeEmprestimoEquipamento().setApolice(apolice);
					c.getIframeEmprestimoEquipamento().setEmprestimoEquipamentoVO(null);
					
				}else{
					JOptionPane.showMessageDialog(null, "Nao e possivel realizar emprestimo: Plano " + apolice.getStatus().toUpperCase() , "Aviso", JOptionPane.INFORMATION_MESSAGE );
				}
			} else if (e.getSource() == btEditar) 
			{
				
				if(apolice.getStatus().equalsIgnoreCase("Ativo"))
				{
					c.getIframeEmprestimoEquipamento().setApolice(apolice);
					c.getIframeEmprestimoEquipamento().setVisible(true);
					EmprestimoEquipamentoVO d = (EmprestimoEquipamentoVO) ((EmprestimoEquipamentoApoliceTableModel) tbEmprestimo.getModel()).getSelectedValue(tbEmprestimo.getSelectedRow());
					c.getIframeEmprestimoEquipamento().setEmprestimoEquipamentoVO(d);
				}
				else{
					JOptionPane.showMessageDialog(null, "Nao e possivel realizar emprestimo: Plano " + apolice.getStatus().toUpperCase() , "Aviso", JOptionPane.INFORMATION_MESSAGE );
				}
				
				
				
			} else if (e.getSource() == btExcluir) {

				try {
					EmprestimoEquipamentoApoliceTableModel model = ((EmprestimoEquipamentoApoliceTableModel) tbEmprestimo.getModel());
					EmprestimoEquipamentoVO d = (EmprestimoEquipamentoVO) model.getSelectedValue(tbEmprestimo.getSelectedRow());
					if (c.mostraConfirmacao("Confirmação","Confirma a exclusão do emprestimo?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
							d.setApolice(apolice);
							Controller.getInstance().deleteEmprestimoEquipamento(d);
							//model.remove(d);
							//model.fireTableDataChanged();
							notificar("Emprestimo deletado com sucesso.", BarraNotificacao.SUCESSO);
							c.carregarEquipamentos();
							c.carregarDevolucoes();
							c.atualizarInformacoesSuperiores();
					}
				}catch (MensagemSaoDimasException ex) {
					notificar(ex.getMessage(),BarraNotificacao.AVISO);
				} catch (Exception ex) {
					notificar("Falha ao excluir o emprestimo.",
							BarraNotificacao.ERRO);
				}
			}
			else if (e.getSource() == btDevolucao) {
				c.getIframeDevolucaoEmprestimoEquipamento().setVisible(true);
				EmprestimoEquipamentoVO d = (EmprestimoEquipamentoVO) ((EmprestimoEquipamentoApoliceTableModel) tbEmprestimo.getModel()).getSelectedValue(tbEmprestimo.getSelectedRow());
				c.getIframeDevolucaoEmprestimoEquipamento().setEmprestimoEquipamentoVO(d);
				
			}

		}
	}

}
