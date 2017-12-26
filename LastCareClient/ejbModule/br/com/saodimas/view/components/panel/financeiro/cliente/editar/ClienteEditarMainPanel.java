package br.com.saodimas.view.components.panel.financeiro.cliente.editar;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.frame.FramePrincipal;
import br.com.saodimas.view.components.iframe.VendaIFrame;
import br.com.saodimas.view.components.iframe.VendaProdutoIFrame;
import br.com.saodimas.view.components.iframe.VendaServicoIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.panel.financeiro.cliente.ClienteMainPanel;
import br.com.saodimas.view.components.table.model.ClienteTableModel;


@SuppressWarnings("serial")
public class ClienteEditarMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Controle de Clientes"; 
	public static final String MENSAGEM_PADRAO = "Altere os dados do cliente e realize vendas.";

	
	private ClienteVO cliente;
	private BarraNotificacao barNotificacao;
	private JButton btSalvar;
	private JButton btCancelar;
	private JButton btImprimirDadosApolice;

	private VendaIFrame iframeVenda;
	private VendaProdutoIFrame iframeVendaProduto;
	private VendaServicoIFrame iframeVendaServico;

	private JTabbedPane apoliceTabPanel;
	private ClienteEditarContatoPanel contatoPanel;
	private ClienteEditarDadosClientePanel dadosClientePanel;
	
	private JTabbedPane detalhesTabPanel;
	private ClienteEditarVendaPanel vendaClientePanel;
	


	private static final Dimension DBUTTON = new Dimension(30,30);
	private JToolBar barControle;
	private JLabel labelApoliceSuperior = new JLabel();
	
	private Boolean changed;
	
	private ClienteTableModel copyClienteTableModel;
	
	public ClienteEditarMainPanel(){
		initialize();
		configure();
	}
	
	
	public ClienteEditarVendaPanel getVendaClientePanel() {
		return vendaClientePanel;
	}

	public VendaIFrame getIframeVenda() {
		return iframeVenda;
	}	
	

	public VendaServicoIFrame getIframeVendaServico() {
		
		return iframeVendaServico;
	}

	public VendaProdutoIFrame getIframeVendaProduto() {
		return iframeVendaProduto;
	}	

	
	public ClienteEditarContatoPanel getContatoPanel() {
		return contatoPanel;
	}

	public ClienteEditarDadosClientePanel getDadosClientePanel() {
		return dadosClientePanel;
	}

	public void mostrarMensagem(String msg, int tipo){
		barNotificacao.mostrarMensagem(msg, tipo);
	}
	
	public void mostrarMensagemVendas(String msg, int tipo){
		this.vendaClientePanel.notificar(msg, tipo);
	}
		
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
		this.carregarDadosClientes();
		this.vendaClientePanel.setCliente(cliente);
	
		this.atualizarInformacoesSuperiores();
		this.setChanged(false);
	}
	
	private void carregarDadosClientes()
	{
		this.dadosClientePanel.setCliente(cliente);
		this.contatoPanel.setCliente(cliente);
		this.atualizarInformacoesSuperiores();
	}
	
	/**
	 * Atualizar quantidade de óbitos, emprestimos, e devoluções
	 */
	public void atualizarInformacoesSuperiores()
	{
		labelApoliceSuperior.setText("                        "+  cliente.getNome());
	}
	
	public void setCopyClienteTableModel(ClienteTableModel copyClienteTable) {
		this.copyClienteTableModel = copyClienteTable;
	}
	
	public boolean hasChanged(){
		return changed;
	}

	public void setChanged(boolean hasChanged) {
		changed = hasChanged;
		btSalvar.setEnabled(changed);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		habilitarComponentes(getComponents(), enabled);
		super.setEnabled(enabled);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		
		if (cliente == null){
			barNotificacao.mostrarMensagem("O cliente não foi escolhido!", BarraNotificacao.ERRO);
			setEnabled(false);
		}
		else{
			barNotificacao.escondeMensagem();
			setEnabled(true);
			detalhesTabPanel.setSelectedIndex(0);
									
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;

		iframeVendaProduto = new VendaProdutoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				super.setVisible(flag);
				this.habilitarControles(!flag);
			}
		};
		iframeVendaProduto.setLocation(x - iframeVendaProduto.getSize().width / 2, y - iframeVendaProduto.getSize().height / 2);
		
		iframeVendaServico= new VendaServicoIFrame(){
			@Override
			public void setVisible(boolean flag) {
				super.setVisible(flag);
				this.habilitarControles(false);
			}
		};
		iframeVendaServico.setLocation(x - iframeVendaServico.getSize().width / 2, y - iframeVendaServico.getSize().height / 2);
		
		
		iframeVenda = new VendaIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (flag == false){
					iframeVendaProduto.setVisible(false);
					iframeVendaServico.setVisible(false);
				}
				super.setVisible(flag);
				this.habilitarControles(!flag);
			}
		};
		iframeVenda.setLocation(x - iframeVenda.getSize().width / 2, y - iframeVenda.getSize().height / 2);

		
		getDesktop().add(iframeVendaProduto);
		getDesktop().add(iframeVendaServico);
		getDesktop().add(iframeVenda);
	


		dadosClientePanel = new ClienteEditarDadosClientePanel(barNotificacao);
		contatoPanel = new ClienteEditarContatoPanel(barNotificacao);
		

		apoliceTabPanel = new JTabbedPane();
		apoliceTabPanel.addTab("Dados do Cliente", new ImageIcon("imagens/apolice.gif"), dadosClientePanel);
		apoliceTabPanel.addTab("Contato/Endereço", new ImageIcon("imagens/home.gif"), contatoPanel);
		
		apoliceTabPanel.setTabPlacement(JTabbedPane.LEFT);

		vendaClientePanel = new ClienteEditarVendaPanel();
		
		detalhesTabPanel = new JTabbedPane();
		detalhesTabPanel.addTab("Vendas", new ImageIcon("imagens/faturas.gif"), vendaClientePanel);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif")){
			@Override
			public void setEnabled(boolean b) {
				super.setEnabled(b && hasChanged());
			}
		};
		
		btSalvar.addActionListener(new EventoBotaoComando());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		///btSalvar.setActionCommand(ClientePanel.ACAO_SALVAR);
		btSalvar.setEnabled(false);

		btCancelar = new JButton("Voltar", new ImageIcon("imagens/back.gif"));
		btCancelar.addActionListener(new EventoBotaoComando());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		//btCancelar.setActionCommand(ApolicePanel.ACAO_CANCELAR);
					
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		
		btImprimirDadosApolice = new JButton("Impressão dados cliente",new ImageIcon("imagens/imprimir.gif"));
		btImprimirDadosApolice.setPreferredSize(DBUTTON);
		btImprimirDadosApolice.addActionListener(new EventoBotaoComando());
		btImprimirDadosApolice.setHorizontalAlignment(JButton.LEFT);
		btImprimirDadosApolice.setToolTipText("Imprimir dados cliente");
		barControle.add(btImprimirDadosApolice);
		
				
		this.setChanged(false);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infApolice = new JPanel(new GridBagLayout());

		c.insets = new Insets(0, 1, 0, 1);
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.LEADING));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.add(new JLabel("                                                               "));
		controle.add(barControle);
		
		controle.add(labelApoliceSuperior);
		
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 0;
		c.weighty = 0;
		c.gridy = 0;
		c.gridx = 0; infApolice.add(controle, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth= 1;
		c.weightx = 0;
		c.weighty = 1;
		c.gridy = 1;
		c.gridx = 0; infApolice.add(apoliceTabPanel, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridy =1;
		c.gridx = 1; infApolice.add(detalhesTabPanel, c);

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
		c.gridy = 1; panelPrincipal.add(infApolice, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}

	private void habilitarComponentes(Component[] comps, boolean habilitar){
		for (int i= 0; i < comps.length; i++){
			if (comps[i] != null){
				comps[i].setEnabled(habilitar);
				if (comps[i] instanceof Container && !(comps[i] instanceof JInternalFrame))
					habilitarComponentes(((Container)comps[i]).getComponents(), habilitar);
			}
		}
	}
	
	private void voltar(){
		Component c = getParent();
		while (!(c instanceof JFrame)){
			if (c == null) break;
			c = c.getParent();
		}
		if (c != null){
			JPanel p = ((FramePrincipal)c).getPainelPrincipal();
			CardLayout cards = (CardLayout)p.getLayout();
			cards.show(p, ClienteMainPanel.FORM_NAME);
		}
		copyClienteTableModel.loadData(null);
	}
	
	private void salvar(){
	
	try {
		
			contatoPanel.atulizadaDadosCliente();
			dadosClientePanel.atualizaDadosCliente();
		
			Controller.getInstance().updateCliente(cliente);
				
			this.atualizaDadosCliente();
				
			barNotificacao.mostrarMensagem("Alterações salvas com sucesso.",BarraNotificacao.SUCESSO);
			setChanged(false);
			
			copyClienteTableModel.loadData(null);
		
		} catch (MensagemSaoDimasException e) {
			barNotificacao.mostrarMensagem(e.getMessage(), BarraNotificacao.ERRO);
			e.printStackTrace();
		}
	}
	
	public void atualizaDadosCliente() throws MensagemSaoDimasException
	{
		//this.apolice = Controller.getInstance().carregarApolice(apolice.getId());
		this.carregarDadosClientes();
	}
	
	private class EventoBotaoComando implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btCancelar){
				if (changed){
					int opcao = mostraConfirmacao("Aviso", "Os dados da apólice foram alterados. Deseja salvar antes de sair?", JOptionPane.YES_NO_CANCEL_OPTION);
					if (opcao == JOptionPane.YES_OPTION){
						salvar();
											
					}
					else if (opcao == JOptionPane.NO_OPTION)
						voltar();
				}
				else voltar();
			}else if (e.getSource() == btSalvar){
				salvar();
			
			}/*else if (e.getSource() == btImprimirDadosApolice){
				List<ApoliceVO> list = new ArrayList<ApoliceVO>();
				list.add(apolice);
				if(apolice.getEmpresa() == null)
					new GeradorRelatorio().gerarComSubRelatorioDependentes(NomeRelatorio.DADOS_APOLICE, list);
				else
					new GeradorRelatorio().gerarComSubRelatorioDependentes(NomeRelatorio.DADOS_APOLICE_EMPRESA, list);
			}
			else if (e.getSource() == btImprimirFinanceiro){
				List<ApoliceVO> list = new ArrayList<ApoliceVO>();
				list.add(apolice);
				new GeradorRelatorio().gerarComSubRelatorioFinanceiro(NomeRelatorio.DADOS_FINANCEIRO_APOLICE, list);
			}
			else if (e.getSource() == btImprimirCarteirinhas){
				iframeCarteirinhaQuantidade.setApolice(apolice);
				iframeCarteirinhaQuantidade.setVisible(true);
			}*/
		}
	}

}
