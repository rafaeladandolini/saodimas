package br.com.saodimas.view.components.panel.apolice.editar.emprestimo.devolucao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import br.com.saodimas.view.components.table.DevolucaoEquipamentoApoliceTable;
import br.com.saodimas.view.components.table.model.DevolucaoEquipamentoApoliceTableModel;

@SuppressWarnings("serial")
public class ApoliceEditarDevolucaoEquipamentoPanel extends JPanel {
	public static final String FORM_NAME = "Devoluções de Equipamentos por Apolice"; 
		
	private ApoliceVO apolice;
	private JButton btExtornoDevolucao;
	private JToolBar barControle;
	private DevolucaoEquipamentoApoliceTable tbEmprestimo;

	private BarraNotificacao barNotificacao;
	private static final Dimension DBUTTON = new Dimension(30, 30);

	public ApoliceEditarDevolucaoEquipamentoPanel() {
		initialize();
		configure();
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	public void setApolice(ApoliceVO a) {
		apolice = a;
		
	}
	

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag){
			this.carregarDevolucoes();
		}
	}

	public void carregarDevolucoes()
	{
		if(apolice != null)
		{
			List<EmprestimoEquipamentoVO> list = Controller.getInstance().getDevolucoesEquipamentoAllByApolice(apolice);
			DevolucaoEquipamentoApoliceTableModel model = ((DevolucaoEquipamentoApoliceTableModel)tbEmprestimo.getModel());
			model.removeAll();
			apolice.setDevolucoes(list);
			model.loadData(list);
			model.fireTableDataChanged();
		}
			
	}
	
		
	private void initialize() {
		
		barNotificacao = new BarraNotificacao("");
		
		tbEmprestimo = new DevolucaoEquipamentoApoliceTable();
		tbEmprestimo.getSelectionModel().addListSelectionListener(
				new EventoTabela());
		tbEmprestimo.getModel().addTableModelListener(new EventoTabela());
		
			
		btExtornoDevolucao = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbEmprestimo.getRowCount() > 0
						&& tbEmprestimo.getSelectedRow() > -1;
				super.setEnabled(enable & habilitar);
			}
		};
		btExtornoDevolucao.setIcon(new ImageIcon("imagens/relacaoTitular.gif"));
		btExtornoDevolucao.addActionListener(new EventoBotaoControle());
		btExtornoDevolucao.setToolTipText("Clique para estornar a devolução de um equipamento");
		btExtornoDevolucao.setPreferredSize(DBUTTON);
		btExtornoDevolucao.setEnabled(false);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		
		barControle.add(btExtornoDevolucao);
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

	depPanel.setBorder(BorderFactory.createTitledBorder("Devoluções"));
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
			
			btExtornoDevolucao.setEnabled(tbEmprestimo.getSelectedRow() > -1
					&& tbEmprestimo.getRowCount() > 0);
		}
	}
	
	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btExtornoDevolucao) {

				try {
					DevolucaoEquipamentoApoliceTableModel model = ((DevolucaoEquipamentoApoliceTableModel) tbEmprestimo.getModel());
					EmprestimoEquipamentoVO d = (EmprestimoEquipamentoVO) model.getSelectedValue(tbEmprestimo.getSelectedRow());
					if (c.mostraConfirmacao("Confirmação","Confirma o estorno da devolução?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						d.setApolice(apolice);
							Controller.getInstance().estornarDevoluçãoEquipamento(d);
							//model.remove(d);
							//model.fireTableDataChanged();
							notificar("Devolução estornada com sucesso.", BarraNotificacao.SUCESSO);
							c.carregarDevolucoes();
							c.carregarEquipamentos();
							c.atualizarInformacoesSuperiores();
					}
				}catch (MensagemSaoDimasException ex) {
					notificar(ex.getMessage(),BarraNotificacao.AVISO);
				} catch (Exception ex) {
					notificar("Falha ao estornar a devolução.",
							BarraNotificacao.ERRO);
				}
			}
		

		}
	}

}
