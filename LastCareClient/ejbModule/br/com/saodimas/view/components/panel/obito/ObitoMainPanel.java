package br.com.saodimas.view.components.panel.obito;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.iframe.ConsObitoIFrame;
import br.com.saodimas.view.components.iframe.ObitoIFrame;
import br.com.saodimas.view.components.iframe.ObitoParticularIFrame;
import br.com.saodimas.view.components.iframe.ObitoProdutoIFrame;
import br.com.saodimas.view.components.iframe.ObitoServicoIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.ObitoConsultaTable;
import br.com.saodimas.view.components.table.model.ObitoConsultaTableModel;

@SuppressWarnings("serial")
public class ObitoMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Controle de Obitos";
	public static final String MENSAGEM_PADRAO = "Controle de �bitos";
	private static final Integer TIPO_ATENDIMENTO_PLANO = 1;

	private BarraNotificacao barNotificacao;
	private JToolBar barControle;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btnImprimir;
	private JButton btPesquisar;
	private JButton btVisualizar;

	private ObitoConsultaTable tbObito = new ObitoConsultaTable();

	private ConsObitoIFrame iframeConsultaObito;
	private ObitoIFrame iframeObito;
	private ObitoParticularIFrame iframeObitoParticular;
	private ObitoServicoIFrame iframeSelecaoServico;
	private ObitoProdutoIFrame iframeSelecaoProduto;

	
	private static final Dimension DBUTTON = new Dimension(30, 30);

	public ObitoMainPanel() {
		initialize();
		configure();
	}

	@Override
	public void setEnabled(boolean enabled) {
		barControle.setEnabled(enabled);
		tbObito.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {

			barNotificacao.mostrarMensagem("", BarraNotificacao.DICA);
			this.iframeConsultaObito.setVisible(true);
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao("");
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;

		iframeSelecaoServico = new ObitoServicoIFrame() {
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null)	getGlass().setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeSelecaoServico.setLocation(x - iframeSelecaoServico.getSize().width	/ 2, y - iframeSelecaoServico.getSize().height / 2);

		
		iframeSelecaoProduto = new ObitoProdutoIFrame() {
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null)	getGlass().setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeSelecaoProduto.setLocation(x - iframeSelecaoProduto.getSize().width	/ 2, y - iframeSelecaoProduto.getSize().height / 2);

		
		iframeConsultaObito = new ConsObitoIFrame() {
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null)	getGlass().setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeConsultaObito.setLocation(x - iframeConsultaObito.getSize().width	/ 2, y - iframeConsultaObito.getSize().height / 2);

		iframeObito = new ObitoIFrame() {
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null)	getGlass().setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeObito.setLocation(x - iframeObito.getSize().width / 2, y - iframeObito.getSize().height / 2);
		
		iframeObitoParticular = new ObitoParticularIFrame() {
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null)
					getGlass().setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeObitoParticular.setLocation(x - iframeObito.getSize().width / 2, y	- iframeObito.getSize().height / 2);

		getDesktop().add(iframeConsultaObito);
		getDesktop().add(iframeObito);
		getDesktop().add(iframeObitoParticular);
		getDesktop().add(iframeSelecaoProduto);
		getDesktop().add(iframeSelecaoServico);

		tbObito = new ObitoConsultaTable();

		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addDeath.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra uma novo obito!");
		btNovo.setPreferredSize(DBUTTON);
		
		
		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados do obito!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(true);
		

		
		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um obito!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(true);
				
		btnImprimir = new JButton();
		btnImprimir.setIcon(new ImageIcon("imagens/imprimir.gif"));
		btnImprimir.addActionListener(new EventoBotaoControle());
		btnImprimir.setToolTipText("Imprimir listagem emprestimo");
		btnImprimir.setPreferredSize(DBUTTON);

		btPesquisar = new JButton();
		btPesquisar.setIcon(new ImageIcon("imagens/search.gif"));
		btPesquisar.addActionListener(new EventoBotaoControle());
		btPesquisar.setToolTipText("Pesquisar");
		btPesquisar.setPreferredSize(DBUTTON);

		btVisualizar = new JButton() {
			@Override
			public void setEnabled(boolean enable) {
				boolean habilitar = tbObito.getRowCount() > 0
						&& tbObito.getSelectedRow() > -1;
				super.setEnabled(enable && habilitar);
			}
		};
		btVisualizar.setIcon(new ImageIcon("imagens/resumeContract.gif"));
		btVisualizar.addActionListener(new EventoBotaoControle());
		btVisualizar.setToolTipText("Visualizar");
		btVisualizar.setPreferredSize(DBUTTON);

		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btPesquisar);
		barControle.add(btVisualizar);
		barControle.add(btnImprimir);
		barControle.addSeparator();
		barControle.add(new JLabel("               Particular-Prefeitura:"));
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		barControle.add(new JLabel("               * Para cadastro e edi��o de obitos de plano, tem que acessar a  Ap�lice."));
		
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infColaborador = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		infColaborador.add(barControle, c);

		JScrollPane depPanel = new JScrollPane(tbObito);
		depPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		infColaborador.add(depPanel, c);

		infColaborador.setBorder(BorderFactory
				.createTitledBorder("Controle de �bitos"));

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
		panelPrincipal.add(infColaborador, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}

	public void mostrarMensagem(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}

	public ConsObitoIFrame getIFrameConsObito() {
		return iframeConsultaObito;
	}
	
	public ObitoServicoIFrame getIframeSelecaoServico() {
		return iframeSelecaoServico;
	}

	public ObitoProdutoIFrame getIframeSelecaoProduto() {
		return iframeSelecaoProduto;
	}
	
	public ObitoParticularIFrame getIframeObitoPartifular(){
		return iframeObitoParticular;
	}
	
	public ObitoIFrame getIframeObito(){
		return iframeObito;
	}

	public void carregarObitoTable(List<ObitoVO> list) {
		((ObitoConsultaTableModel) tbObito.getModel()).loadData(list);
	}

	private class EventoBotaoControle implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnImprimir) {
				
			}else if (e.getSource() == btNovo)
			{
				iframeObitoParticular.setObito(null);
				iframeObitoParticular.setVisible(true);
			}else if (e.getSource() == btPesquisar) 
			{
				iframeConsultaObito.setVisible(true);
				iframeConsultaObito.habilitarControles(false);
			}else if (e.getSource() == btVisualizar) 
			{
				int row = tbObito.getSelectedRow();
				if (row >= 0) 
				{
					ObitoConsultaTableModel model = (ObitoConsultaTableModel) tbObito.getModel();
					ObitoVO obi = (ObitoVO) model.getSelectedValue(row);
					ApoliceVO a = null;
					if(obi.getTipoAtendimento() == null || obi.getTipoAtendimento().getId().equals(TIPO_ATENDIMENTO_PLANO))
					{
						
						try {
							a = Controller.getInstance().carregarApolice(obi.getApolice().getId());
							obi.setApolice(a);
						} catch (MensagemSaoDimasException e1) {
							barNotificacao.mostrarMensagem(e1.getMessage(), BarraNotificacao.ERRO);
							e1.printStackTrace();
						}
						iframeObito.setObito(obi);
						iframeObito.habilitarControles(false);
						iframeObito.setConfigVisualizarObito(true, true);
						iframeObito.setVisible(true);
					}else
					{
						iframeObitoParticular.setObito(obi);
						iframeObitoParticular.setConfigVisualizarObito(true);
						iframeObitoParticular.setVisible(true);
						
					}
				}
			}
			else if (e.getSource() == btEditar)
			{
				
				int row = tbObito.getSelectedRow();
				if (row >= 0)
				{
					ObitoConsultaTableModel model = (ObitoConsultaTableModel) tbObito.getModel();
					ObitoVO obi = (ObitoVO) model.getSelectedValue(row);
					if(obi.getTipoAtendimento() == null || obi.getTipoAtendimento().getId().equals(TIPO_ATENDIMENTO_PLANO))
					{
						JOptionPane.showMessageDialog(null, " Opcao apenas para obito particular ou de prefeitura. ", "Aviso", JOptionPane.INFORMATION_MESSAGE );
					}else
					{
						iframeObitoParticular.setObito(obi);
						iframeObitoParticular.setVisible(true);
					}
				}
					
			}
			else if (e.getSource() == btExcluir)
			{
				int row = tbObito.getSelectedRow();
				if (row >= 0)
				{
					ObitoConsultaTableModel model = (ObitoConsultaTableModel) tbObito.getModel();
					ObitoVO obi = (ObitoVO) model.getSelectedValue(row);
					if(obi.getTipoAtendimento() == null || obi.getTipoAtendimento().getId().equals(TIPO_ATENDIMENTO_PLANO))
					{
						JOptionPane.showMessageDialog(null, " Opcao apenas para obito particular ou de prefeitura. ", "Aviso", JOptionPane.INFORMATION_MESSAGE );
					}else if (mostraConfirmacao("Confirma��o","Confirma a exclus�o do �bito?",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) 
					{
						try{
							Controller.getInstance().deleteObito(obi);
							barNotificacao.mostrarMensagem("Obito excluido com sucesso", BarraNotificacao.INFO);
						} catch (MensagemSaoDimasException e1) {
							barNotificacao.mostrarMensagem(e1.getMessage(), BarraNotificacao.ERRO);
							e1.printStackTrace();
						}
					}
				}
			}

		}
	}
	
}