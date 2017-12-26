package br.com.saodimas.view.components.menu;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import br.com.saodimas.backup.AdminBancoDados;
import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.panel.admFuncionarios.AdminFaturaGeralMainPanel;
import br.com.saodimas.view.components.panel.admFuncionarios.AdminFaturaMainPanel;
import br.com.saodimas.view.components.panel.apolice.ApoliceMainPanel;
import br.com.saodimas.view.components.panel.cidade.CidadeMainPanel;
import br.com.saodimas.view.components.panel.colaborador.ColaboradorMainPanel;
import br.com.saodimas.view.components.panel.equipamento.EmprestimoEquipamentoMainPanel;
import br.com.saodimas.view.components.panel.equipamento.EquipamentoMainPanel;
import br.com.saodimas.view.components.panel.financeiro.cheques.ChequeMainPanel;
import br.com.saodimas.view.components.panel.financeiro.cheques.ContaMainPanel;
import br.com.saodimas.view.components.panel.financeiro.cliente.ClienteMainPanel;
import br.com.saodimas.view.components.panel.financeiro.compra.CompraMainPanel;
import br.com.saodimas.view.components.panel.financeiro.fornecedor.FornecedorMainPanel;
import br.com.saodimas.view.components.panel.financeiro.venda.VendaMainPanel;
import br.com.saodimas.view.components.panel.obito.ObitoMainPanel;
import br.com.saodimas.view.components.panel.parceiro.ParceiroMainPanel;
import br.com.saodimas.view.components.panel.plano.PlanoMainPanel;
import br.com.saodimas.view.components.panel.produto.ProdutoMainPanel;
import br.com.saodimas.view.components.panel.relacao.RelacaoTitularMainPanel;
import br.com.saodimas.view.components.panel.servico.ServicoMainPanel;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class BarraMenu extends JMenuBar {
	public static final int SEM_PERFIL = 0;
	public static final int PERFIL_ADMIN = 1;
	public static final int PERFIL_FUNC = 2;
	public static final int PERFIL_FUNC_ESPECIAL = 3;

	private static JMenu menuArquivo;
	private static JMenuItem mniConectar;
	private static JMenuItem mniDesconectar;
	private static JMenuItem mniAlterarSenha;
	private static JMenuItem mniSair;

	private static JMenu menuGerenciar;
	private static JMenuItem mniApolice;
	private static JMenuItem mniObitoPlano;
	private static JMenuItem mniClientes;
	private static JMenuItem mniObitoParticular;

	
	private static JMenu menuFuncionario;
	private static JMenuItem mniAdmFaturas;
	
	private static JMenu menuSistema;
	private static JMenuItem mniUsuarios;
	private static JMenuItem mniAdminGeralFaturas;
	private static JMenuItem mniAdmParceiro;
	private static JMenuItem mniPlano;
	private static JMenuItem mniProduto;
	private static JMenuItem mniServico;
	private static JMenuItem mniCidade;
	private static JMenuItem mniRelecaoTitular;
	private static JMenuItem mniConta;
	
	private static JMenu menuEquipamento;
	private static JMenuItem mniAdmEquipamento;
	private static JMenuItem mniAdmEmprestimoEquipamento;
	
	private static JMenu menuFinanceiro;
	private static JMenuItem mniCliente;
	private static JMenuItem mniVendas;
	private static JMenuItem mniCompra;
	private static JMenuItem mniFornecedor;
	private static JMenuItem mniCheques;

	private static JMenu menuFerramenta;
	private static JMenuItem mniPreferencias;

	private static JMenu menuAjuda;
	private static JMenuItem mniSobre;

	private static JMenu menuSeguranca;
	private static JMenuItem mniBackup;
	
	private static JPanel painelPrincipal;
		
	public BarraMenu(JPanel painel) {
		painelPrincipal = painel;
		initialize();
		configure();
	}

	@Override
	public void setEnabled(boolean enabled) {
		for (Component c : getComponents()) {
			c.setEnabled(enabled);
		}
		if (enabled) carregaPerfil();
		super.setEnabled(enabled);
	}

	private void initialize() {
		menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic(KeyEvent.VK_A);
		menuArquivo.setMinimumSize(new Dimension(50, 22));

		mniConectar = new JMenuItem("Efetuar Logon", KeyEvent.VK_F){
			@Override
			public void setVisible(boolean flag) {
				if (mniDesconectar != null) mniDesconectar.setVisible(!flag);
				super.setVisible(flag);
			}
		};
		mniConectar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		mniConectar.getAccessibleContext().setAccessibleDescription("Conectar-se ao servidor!");
		mniConectar.addActionListener(new EventoMenu());

		mniDesconectar = new JMenuItem("Efetuar Logoff", KeyEvent.VK_F);
		mniDesconectar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		mniDesconectar.getAccessibleContext().setAccessibleDescription("Desconectar do servidor!");
		mniDesconectar.addActionListener(new EventoMenu());

		mniAlterarSenha = new JMenuItem("Alterar Senha", KeyEvent.VK_L);
		mniAlterarSenha.getAccessibleContext().setAccessibleDescription("Altere sua senha de acesso!");
		mniAlterarSenha.addActionListener(new EventoMenu());

		mniSair = new JMenuItem("Sair", KeyEvent.VK_S);
		mniSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		mniSair.addActionListener(new EventoMenu());

		menuGerenciar = new JMenu("Gerenciar");
		menuGerenciar.setMnemonic(KeyEvent.VK_G);
		menuGerenciar.setMinimumSize(new Dimension(50, 22));

		mniApolice = new JMenuItem("Apólice", KeyEvent.VK_A);
		mniApolice.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
		mniApolice.getAccessibleContext().setAccessibleDescription("Manter apólice dos associados...");
		mniApolice.addActionListener(new EventoMenu());
		
		mniObitoPlano = new JMenuItem("Obitos", KeyEvent.VK_O);
		mniObitoPlano.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
		mniObitoPlano.getAccessibleContext().setAccessibleDescription("Controle de óbitos...");
		mniObitoPlano.addActionListener(new EventoMenu());

		mniClientes = new JMenuItem("Clientes", KeyEvent.VK_C);
		mniClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
		mniClientes.getAccessibleContext().setAccessibleDescription("Controle de Clientes...");
		mniClientes.addActionListener(new EventoMenu());

		mniPlano = new JMenuItem("Plano", KeyEvent.VK_L);
		mniPlano.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));
		mniPlano.getAccessibleContext().setAccessibleDescription("Manter planos de assistência...");
		mniPlano.addActionListener(new EventoMenu());

		mniProduto = new JMenuItem("Produto", KeyEvent.VK_P);
		mniProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
		mniProduto.getAccessibleContext().setAccessibleDescription("Manter produtos disponíveis...");
		mniProduto.addActionListener(new EventoMenu());

		mniServico = new JMenuItem("Serviço", KeyEvent.VK_S);
		mniServico.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.CTRL_MASK));
		mniServico.getAccessibleContext().setAccessibleDescription("Manter serviços disponíveis...");
		mniServico.addActionListener(new EventoMenu());

		mniCidade = new JMenuItem("Cidades", KeyEvent.VK_C);
		mniCidade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, ActionEvent.CTRL_MASK));
		mniCidade.getAccessibleContext().setAccessibleDescription("Manter cidades disponíveis...");
		mniCidade.addActionListener(new EventoMenu());

		mniRelecaoTitular = new JMenuItem("Relações com o Titular", KeyEvent.VK_R);
		mniRelecaoTitular.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, ActionEvent.CTRL_MASK));
		mniRelecaoTitular.getAccessibleContext().setAccessibleDescription("Manter lista das relações com o titular...");
		mniRelecaoTitular.addActionListener(new EventoMenu());

		menuFuncionario = new JMenu("Funcionários");
		
		mniConta = new JMenuItem("Manter contas de bancos");
		mniConta.getAccessibleContext().setAccessibleDescription("Manter as contas de bancos..");
		mniConta.addActionListener(new EventoMenu());
				
		mniAdmFaturas = new JMenuItem("Resumo faturas quitadas");
		mniAdmFaturas.getAccessibleContext().setAccessibleDescription("Resumo das faturas quitadas hoje..");
		mniAdmFaturas.addActionListener(new EventoMenu());
		
		menuSistema = new JMenu("Administrador");
		
		mniUsuarios = new JMenuItem("Usuários", KeyEvent.VK_C);
		mniUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, ActionEvent.CTRL_MASK));
		mniUsuarios.getAccessibleContext().setAccessibleDescription("Manter usuários do sistema...");
		mniUsuarios.addActionListener(new EventoMenu());
		
		mniAdminGeralFaturas = new JMenuItem("Resumo faturas quitadas");
		mniAdminGeralFaturas.getAccessibleContext().setAccessibleDescription("Resumo das faturas quitadas hoje...");
		mniAdminGeralFaturas.addActionListener(new EventoMenu());
		
		mniAdmParceiro = new JMenuItem("Parceiros");
		mniAdmParceiro.getAccessibleContext().setAccessibleDescription("Manter os parceiros...");
		mniAdmParceiro.addActionListener(new EventoMenu());
		
		menuEquipamento = new JMenu("Equipamento");
		
		mniAdmEquipamento = new JMenuItem("Equipamento");
		mniAdmEquipamento.getAccessibleContext().setAccessibleDescription("Manter os equipamentos...");
		mniAdmEquipamento.addActionListener(new EventoMenu());
		
		mniAdmEmprestimoEquipamento = new JMenuItem("Emprestimo Equip.");
		mniAdmEmprestimoEquipamento.getAccessibleContext().setAccessibleDescription("Visualizar os emprestimos...");
		mniAdmEmprestimoEquipamento.addActionListener(new EventoMenu());
		
		menuFinanceiro = new JMenu("Financeiro");
		menuFinanceiro.setMnemonic(KeyEvent.VK_F);
		menuFinanceiro.setMinimumSize(new Dimension(50, 22));
		
		mniCliente = new JMenuItem("Cliente ", KeyEvent.VK_P);
		mniCliente.getAccessibleContext().setAccessibleDescription("Manter clientes do sistema...");
		mniCliente.addActionListener(new EventoMenu());
		
		mniVendas = new JMenuItem("Vendas ", KeyEvent.VK_V);
		mniVendas.getAccessibleContext().setAccessibleDescription("Manter vendas do sistema...");
		mniVendas.addActionListener(new EventoMenu());
		
		
		mniCompra = new JMenuItem("Compra", KeyEvent.VK_P);
		mniCompra.getAccessibleContext().setAccessibleDescription("Manter compras do sistema...");
		mniCompra.addActionListener(new EventoMenu());
		
		mniFornecedor = new JMenuItem("Fornecedor      ", KeyEvent.VK_P);
		mniFornecedor.getAccessibleContext().setAccessibleDescription("Manter fornecedores do sistema...");
		mniFornecedor.addActionListener(new EventoMenu());
		
		mniCheques = new JMenuItem("Cheques      ", KeyEvent.VK_P);
		mniCheques.getAccessibleContext().setAccessibleDescription("Manter cheques emitidos e recebidos...");
		mniCheques.addActionListener(new EventoMenu());
		
		menuFerramenta = new JMenu("Ferramentas");
		menuFerramenta.setMnemonic(KeyEvent.VK_F);
		menuFerramenta.setMinimumSize(new Dimension(50, 22));

		mniPreferencias = new JMenuItem("Preferências", KeyEvent.VK_P);
		mniPreferencias.getAccessibleContext().setAccessibleDescription("Alterar as configurações do sistema...");
		mniPreferencias.addActionListener(new EventoMenu());

		menuAjuda = new JMenu("Ajuda");
		menuAjuda.setMnemonic(KeyEvent.VK_J);
		menuAjuda.setMinimumSize(new Dimension(50, 22));

		mniSobre = new JMenuItem("Sobre", KeyEvent.VK_S);
		mniSobre.getAccessibleContext().setAccessibleDescription("Sobre o sistema...");
		mniSobre.addActionListener(new EventoMenu());
		
		menuSeguranca = new JMenu("Segurança");
		menuSeguranca.setMinimumSize(new Dimension(50, 22));

		mniBackup = new JMenuItem("Backup");
		mniBackup.getAccessibleContext().setAccessibleDescription("Backup do banco de dados...");
		mniBackup.addActionListener(new EventoMenu());
		
	}

	private void configure() {
		menuArquivo.add(mniConectar);
		menuArquivo.add(mniDesconectar);
		menuArquivo.add(mniAlterarSenha);
		menuArquivo.addSeparator();
		menuArquivo.add(mniSair);
		add(menuArquivo);

		menuGerenciar.add(mniApolice);
		menuGerenciar.add(mniObitoPlano);
		menuGerenciar.add(mniClientes);
		
		add(menuGerenciar);

		menuFuncionario.add(mniAdmFaturas);
		add(menuFuncionario);
				
		menuEquipamento.add(mniAdmEquipamento);
		menuEquipamento.add(mniAdmEmprestimoEquipamento);
		add(menuEquipamento);
		
		menuSistema.add(mniPlano);
		menuSistema.add(mniProduto);
		menuSistema.add(mniServico);
		menuSistema.add(mniCidade);
		menuSistema.add(mniRelecaoTitular);
		menuSistema.addSeparator();
		menuSistema.add(mniUsuarios);
		menuSistema.add(mniAdmParceiro);
		menuSistema.addSeparator();
		menuSistema.add(mniAdminGeralFaturas);
		menuSistema.addSeparator();
		menuSistema.add(mniConta);
		add(menuSistema);
				
		menuFinanceiro.add(mniFornecedor);
		menuFinanceiro.add(mniCompra);
		menuFinanceiro.addSeparator();
		menuFinanceiro.add(mniCheques);
		menuFinanceiro.add(mniCliente);
		menuFinanceiro.add(mniVendas);
		
		add(menuFinanceiro);
		
		menuFerramenta.add(mniPreferencias);
		add(menuFerramenta);
		
		menuSeguranca.add(mniBackup);
		add(menuSeguranca);
		
		menuAjuda.add(mniSobre);
		add(menuAjuda);

		
		carregaPerfil();
	}

	public static void carregaPerfil(){
		ColaboradorVO funcionario = Controller.getInstance().getUsuarioLogado();
		int perfil = (funcionario == null) ? SEM_PERFIL : (funcionario.getTipoColaborador().startsWith("A")) ?	PERFIL_ADMIN : PERFIL_FUNC;

		mniConectar.setVisible(perfil == SEM_PERFIL);
		mniAlterarSenha.setEnabled(perfil != SEM_PERFIL);

		menuFuncionario.setEnabled(perfil != SEM_PERFIL);
		menuGerenciar.setEnabled(perfil != SEM_PERFIL);
		mniApolice.setEnabled(perfil != SEM_PERFIL);
		mniPlano.setEnabled(perfil != SEM_PERFIL);
		mniProduto.setEnabled(perfil != SEM_PERFIL);
		mniServico.setEnabled(perfil != SEM_PERFIL);
		mniRelecaoTitular.setEnabled(perfil != SEM_PERFIL);
		mniCidade.setEnabled(perfil != SEM_PERFIL);
		menuEquipamento.setEnabled(perfil != SEM_PERFIL);

		menuSistema.setEnabled(perfil == PERFIL_ADMIN);
		mniUsuarios.setEnabled(perfil == PERFIL_ADMIN);
		mniAdminGeralFaturas.setEnabled(perfil == PERFIL_ADMIN);
		mniCheques.setEnabled(perfil == PERFIL_ADMIN);
		mniAdmParceiro.setEnabled(perfil == PERFIL_ADMIN);
		mniAdmEquipamento.setEnabled(perfil == PERFIL_ADMIN);
		menuFinanceiro.setEnabled(perfil == PERFIL_ADMIN);
		mniAdmEmprestimoEquipamento.setEnabled(perfil == PERFIL_ADMIN);

		menuFerramenta.setEnabled(perfil == PERFIL_ADMIN);
		//mniRelatorios.setEnabled(perfil != SEM_PERFIL);
		mniPreferencias.setEnabled(perfil == PERFIL_ADMIN);
		mniCompra.setEnabled(perfil == PERFIL_ADMIN);
		menuSeguranca.setEnabled(perfil == PERFIL_ADMIN);
	}

	private class EventoMenu implements ActionListener{
		public void actionPerformed(ActionEvent ev) {
			CardLayout cards = (CardLayout)painelPrincipal.getLayout();
			if (ev.getSource() == mniConectar){
				for (Component c : painelPrincipal.getComponents()) {
					if (c.isVisible() && c instanceof CustomLayeredPanel){
						CustomLayeredPanel logon = (CustomLayeredPanel)c;
						logon.getIframeLogon().setVisible(true);
						break;
					}
				}
			}
			else if (ev.getSource() == mniAlterarSenha){
				for (Component c : painelPrincipal.getComponents()) {
					if (c.isVisible() && c instanceof CustomLayeredPanel){
						CustomLayeredPanel profile = (CustomLayeredPanel)c;
						profile.getIframeManutencaoConta().setVisible(true);
						break;
					}
				}
			}
	
			else if (ev.getSource() == mniApolice) cards.show(painelPrincipal, ApoliceMainPanel.FORM_NAME);
			else if (ev.getSource() == mniObitoPlano) cards.show(painelPrincipal, ObitoMainPanel.FORM_NAME);
			else if (ev.getSource() == mniClientes) cards.show(painelPrincipal, ClienteMainPanel.FORM_NAME);
			else if (ev.getSource() == mniPlano) cards.show(painelPrincipal, PlanoMainPanel.FORM_NAME);
			else if (ev.getSource() == mniServico) cards.show(painelPrincipal, ServicoMainPanel.FORM_NAME);
			else if (ev.getSource() == mniProduto) cards.show(painelPrincipal, ProdutoMainPanel.FORM_NAME);
			else if (ev.getSource() == mniUsuarios) cards.show(painelPrincipal, ColaboradorMainPanel.FORM_NAME);
			else if (ev.getSource() == mniCliente) cards.show(painelPrincipal, ClienteMainPanel.FORM_NAME);
			else if (ev.getSource() == mniFornecedor) cards.show(painelPrincipal, FornecedorMainPanel.FORM_NAME);
			else if (ev.getSource() == mniCompra) cards.show(painelPrincipal, CompraMainPanel.FORM_NAME);
			else if (ev.getSource() == mniVendas) cards.show(painelPrincipal, VendaMainPanel.FORM_NAME);
			else if (ev.getSource() == mniCidade) cards.show(painelPrincipal, CidadeMainPanel.FORM_NAME);
			else if (ev.getSource() == mniCheques) cards.show(painelPrincipal, ChequeMainPanel.FORM_NAME);
			else if (ev.getSource() == mniConta) cards.show(painelPrincipal, ContaMainPanel.FORM_NAME);
			else if (ev.getSource() == mniRelecaoTitular) cards.show(painelPrincipal, RelacaoTitularMainPanel.FORM_NAME);
			else if (ev.getSource() == mniAdmFaturas)cards.show(painelPrincipal, AdminFaturaMainPanel.FORM_NAME);
			else if (ev.getSource() == mniAdminGeralFaturas)cards.show(painelPrincipal, AdminFaturaGeralMainPanel.FORM_NAME);
			else if (ev.getSource() == mniAdmParceiro)cards.show(painelPrincipal, ParceiroMainPanel.FORM_NAME);
			else if (ev.getSource() == mniAdmEquipamento)cards.show(painelPrincipal, EquipamentoMainPanel.FORM_NAME);
			else if (ev.getSource() == mniAdmEmprestimoEquipamento)cards.show(painelPrincipal, EmprestimoEquipamentoMainPanel.FORM_NAME);
			else if (ev.getSource() == mniPreferencias){
				for (Component c : painelPrincipal.getComponents()) {
					if (c.isVisible() && c instanceof CustomLayeredPanel){
						CustomLayeredPanel prefs = (CustomLayeredPanel)c;
						prefs.getIframePrefs().setVisible(true);
						break;
					}
				}
			}
			else if (ev.getSource() == mniSobre){
				for (Component c : painelPrincipal.getComponents()) {
					if (c.isVisible() && c instanceof CustomLayeredPanel){
						CustomLayeredPanel help = (CustomLayeredPanel)c;
						help.getIframeSobre().setVisible(true);
						break;
					}
				}
			}
			else if (ev.getSource() == mniDesconectar){
				cards.show(painelPrincipal, "Default");
				Controller.getInstance().setUsuarioLogado(null);
				// guarda o ultimo usuario, para quando fazer novo logon, verificar se usuario é novo
				// e zerar a data na tela QuitacaoFaturaPanel
				SaoDimasMain.ultimoColaborador = SaoDimasMain.colaborador;
				carregaPerfil();	
				
			}
			else if (ev.getSource() == mniSair) WinManager.getJanelaInicial().dispose();
			
			else if (ev.getSource() == mniBackup) new AdminBancoDados().efetuarBackupMySql();
			
			painelPrincipal.repaint();
		}
	}
}

