package br.com.saodimas.view.components.panel.financeiro.cliente;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ClienteVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.frame.FramePrincipal;
import br.com.saodimas.view.components.iframe.ClienteIFrame;
import br.com.saodimas.view.components.iframe.ConsClienteIFrame;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.panel.financeiro.cliente.editar.ClienteEditarMainPanel;
import br.com.saodimas.view.components.table.ClienteTable;
import br.com.saodimas.view.components.table.model.ClienteTableModel;


@SuppressWarnings("serial")
public class ClienteMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "Clientes"; 
	public static final String MENSAGEM_PADRAO = "   Pressione a tecla [F3] para Pesquisar, [INSERT] para Cadastrar e [ENTER] ou [DELETE] para editar/excluir um cliente selecionado!";
	
	private BarraNotificacao barNotificacao;
	private JButton btPesquisar;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btMovimentarFinanceiro;
	private JToolBar barControle;
	
	private ClienteTable tbClientes;
	private ClienteIFrame iframeCliente;
	private ConsClienteIFrame iframeConsultaCliente;
	
	private AbstractButton ultimoComando;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public ClienteMainPanel(){
		initialize();
		configure();
	}
	
	public void carregarClientesTable()
	{
		((ClienteTableModel)tbClientes.getModel()).loadData(Controller.getInstance().getAllClientes());
	}
	
	public void carregarClientesTable(List<ClienteVO> list)
	{
		((ClienteTableModel)tbClientes.getModel()).loadData(list);
	}
	
	public ClienteIFrame getIframeCliente() {
		return iframeCliente;
	}

	public ConsClienteIFrame getIframeConsultaCliente() {
		return iframeConsultaCliente;
	}
	
	public void selecionaUltimoComando(){
		if (ultimoComando != null){
			if (ultimoComando != btEditar && ultimoComando != btExcluir){
				tbClientes.getSelectionModel().clearSelection();
				ultimoComando.requestFocus();
			}
			else tbClientes.requestFocus();
		}
	}	

	@Override
	public void setEnabled(boolean enabled) {
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbClientes.getSelectedRow() > -1);
		btMovimentarFinanceiro.setEnabled(enabled && tbClientes.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbClientes.getSelectedRow() > -1);
		barControle.setEnabled(enabled);
		tbClientes.setEnabled(enabled);
		super.setEnabled(enabled);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			carregarClientesTable();
			barNotificacao.mostrarMensagem("", BarraNotificacao.DICA);
		}
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao("");
		int x = getDesktop().getWidth() / 2;
		int y = getDesktop().getHeight() / 2;
		
		iframeCliente = new ClienteIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();
				super.setVisible(flag);
			}
		};
		iframeCliente.setLocation(x - iframeCliente.getSize().width / 2, y - iframeCliente.getSize().height / 2);
			
		iframeConsultaCliente = new ConsClienteIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (getGlass() != null) getGlass().setVisible(flag);
				if (!flag) selecionaUltimoComando();
				super.setVisible(flag);
			}
		};
		iframeConsultaCliente.setLocation(x - iframeConsultaCliente.getSize().width / 2, y - iframeConsultaCliente.getSize().height / 2);
		
		getDesktop().add(iframeCliente);
		getDesktop().add(iframeConsultaCliente);
				
		tbClientes = new ClienteTable();
		tbClientes.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbClientes.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbClientes.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btPesquisar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				super.keyPressed(e);
			}
		});
		
		btPesquisar = new JButton();
		btPesquisar.setIcon(new ImageIcon("imagens/search.gif"));
		btPesquisar.addActionListener(new EventoBotaoControle());
		btPesquisar.setToolTipText("Pesquisar clientes!");
		btPesquisar.setPreferredSize(DBUTTON);
		btPesquisar.addKeyListener(tbClientes.getKeyListeners()[0]);
		
		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addEmployee.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra um novo colaborador!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbClientes.getKeyListeners()[0]);
		
		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de um colaborador!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbClientes.getKeyListeners()[0]);
		
		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um colaborador!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbClientes.getKeyListeners()[0]);
		
		btMovimentarFinanceiro = new JButton();
		btMovimentarFinanceiro.setIcon(new ImageIcon("imagens/faturas.gif"));
		btMovimentarFinanceiro.addActionListener(new EventoBotaoControle());
		btMovimentarFinanceiro.setToolTipText("Movimentar Financeiro do Cliente!");
		btMovimentarFinanceiro.setPreferredSize(DBUTTON);
		btMovimentarFinanceiro.setEnabled(false);
		btMovimentarFinanceiro.addKeyListener(tbClientes.getKeyListeners()[0]);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		
		barControle.add(btPesquisar);
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		barControle.addSeparator();
		barControle.add(btMovimentarFinanceiro);
		
		ultimoComando = btNovo;
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
		c.gridy = 0; infColaborador.add(barControle, c);
		
		JScrollPane depPanel = new JScrollPane(tbClientes);
		depPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infColaborador.add(depPanel, c);
		
		infColaborador.setBorder(BorderFactory.createTitledBorder("Clientes"));
		
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
		c.gridy = 1; panelPrincipal.add(infColaborador, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);
	}
	
	public void mostrarMensagem(final String mensagem, final int tipoMensagem) {
		barNotificacao.mostrarMensagem(mensagem, tipoMensagem);
	}
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btEditar.setEnabled(tbClientes.getSelectedRow() > -1);
			btExcluir.setEnabled(tbClientes.getSelectedRow() > -1);
			btMovimentarFinanceiro.setEnabled(tbClientes.getSelectedRow() > -1);
		}
	}
	
	private void loadEditaPanelCliente(ClienteVO cliente)
	{
		Integer id = null;
		if(cliente != null && cliente.getId() != null && cliente.getId() > 0)
			id = cliente.getId();
		
		//try {
			//cliente = Controller.getInstance().carregarApolice(id);
		
			Component c = getParent();
			while (!(c instanceof JFrame)){
				if (c == null) break;
				c = c.getParent();
			}
			if (c != null && cliente != null){
				JPanel p = ((FramePrincipal)c).getPainelPrincipal();
				ClienteEditarMainPanel editPanel = ((FramePrincipal)c).getClienteEditarMainPanel();
				
				editPanel.setCliente(cliente);
				editPanel.setCopyClienteTableModel((ClienteTableModel)tbClientes.getModel());
				
				CardLayout cards = (CardLayout)p.getLayout();
				cards.show(p, ClienteEditarMainPanel.FORM_NAME);
				
			}
			
			if(cliente == null)
			{
				
			}
		
		//} catch (MensagemSaoDimasException e) {
	//		barNotificacao.mostrarMensagem(e.getMessage(), BarraNotificacao.ERRO);
	//		e.printStackTrace();
		//}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ultimoComando = (AbstractButton)e.getSource();
			
			if (e.getSource() == btPesquisar){
				iframeConsultaCliente.setVisible(true);
				iframeConsultaCliente.habilitarControles(false);		
			}
			else
			if (e.getSource() == btNovo){
				iframeCliente.setCliente(null);
				iframeCliente.setVisible(true);
				iframeCliente.habilitarControles(false);				
			}
			else if (e.getSource() == btEditar){
				int row = tbClientes.getSelectedRow();
				ClienteTableModel model = (ClienteTableModel)tbClientes.getModel();
				ClienteVO dep = (ClienteVO)model.getSelectedValue(row);
				
				iframeCliente.setCliente(dep);
				iframeCliente.setVisible(true);
				iframeCliente.habilitarControles(false);
							
				
			}else if(e.getSource() == btMovimentarFinanceiro){
				int row = tbClientes.getSelectedRow();
				if (row > -1){
					barNotificacao.escondeMensagem();
					ClienteTableModel model = (ClienteTableModel)tbClientes.getModel();
					ClienteVO cliente = (ClienteVO)model.getSelectedValue(row);
					loadEditaPanelCliente(cliente);
				}
			}
			else{
				if (e.getSource() == btExcluir && mostraConfirmacao("Confirmação", "Confirma a exclusão do cliente?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbClientes.getSelectedRow();
					ClienteTableModel model = (ClienteTableModel)tbClientes.getModel();
					ClienteVO col = (ClienteVO)model.getSelectedValue(row);
					try{
						Controller.getInstance().deleteCliente(col);
					} catch (MensagemSaoDimasException ex) {
						barNotificacao.mostrarMensagem(ex.getMessage(),	BarraNotificacao.ERRO);
					}
					carregarClientesTable();
				}
			}
		}
	}

}
