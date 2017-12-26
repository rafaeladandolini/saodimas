package br.com.saodimas.view.components.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.saodimas.view.components.menu.BarraMenu;
import br.com.saodimas.view.components.panel.BarraStatus;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.panel.admFuncionarios.AdminFaturaGeralMainPanel;
import br.com.saodimas.view.components.panel.admFuncionarios.AdminFaturaMainPanel;
import br.com.saodimas.view.components.panel.apolice.ApoliceMainPanel;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.panel.cidade.CidadeMainPanel;
import br.com.saodimas.view.components.panel.colaborador.ColaboradorMainPanel;
import br.com.saodimas.view.components.panel.equipamento.EmprestimoEquipamentoMainPanel;
import br.com.saodimas.view.components.panel.equipamento.EquipamentoMainPanel;
import br.com.saodimas.view.components.panel.financeiro.cheques.ChequeMainPanel;
import br.com.saodimas.view.components.panel.financeiro.cheques.ContaMainPanel;
import br.com.saodimas.view.components.panel.financeiro.cliente.ClienteMainPanel;
import br.com.saodimas.view.components.panel.financeiro.cliente.editar.ClienteEditarMainPanel;
import br.com.saodimas.view.components.panel.financeiro.compra.CompraMainPanel;
import br.com.saodimas.view.components.panel.financeiro.fornecedor.FornecedorMainPanel;
import br.com.saodimas.view.components.panel.financeiro.venda.VendaMainPanel;
import br.com.saodimas.view.components.panel.obito.ObitoMainPanel;
import br.com.saodimas.view.components.panel.parceiro.ParceiroMainPanel;
import br.com.saodimas.view.components.panel.plano.PlanoMainPanel;
import br.com.saodimas.view.components.panel.produto.ProdutoMainPanel;
import br.com.saodimas.view.components.panel.relacao.RelacaoTitularMainPanel;
import br.com.saodimas.view.components.panel.servico.ServicoMainPanel;


@SuppressWarnings("serial")
public class FramePrincipal extends JFrame {
	private JPanel painelPrincipal;
	private PlanoMainPanel pPlano;
	private ServicoMainPanel pServico;
	private ProdutoMainPanel pProduto;
	private ApoliceMainPanel pApolice;
	private ObitoMainPanel pObito;
	private ApoliceEditarMainPanel pEditApolice;
	private ColaboradorMainPanel pColadorador;
	private ParceiroMainPanel pParceiro;
	private EquipamentoMainPanel pEquipamento;
	private EmprestimoEquipamentoMainPanel pEmprestimoEquipamento;
	private CidadeMainPanel pCidade;
	private RelacaoTitularMainPanel pRelacao;
	private AdminFaturaMainPanel pAdmFaturas;
	private AdminFaturaGeralMainPanel pAdmGeralFaturas;
	private ClienteMainPanel pCliente;
	private ClienteEditarMainPanel pEditCliente;
	private FornecedorMainPanel pEmpresa;
	private CompraMainPanel pCompra;
	private VendaMainPanel pVenda;
	private ContaMainPanel pConta;
	private ChequeMainPanel pCheque;
	
	private CustomLayeredPanel pVazio;
	private BarraStatus barStatus;

	public FramePrincipal() {
		initialize();
		configure();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		System.exit(0);
	}
	
	public JPanel getPainelPrincipal(){
		return painelPrincipal;
	}
	
	public PlanoMainPanel getPPlano() {
		return pPlano;
	}

	public ServicoMainPanel getPServico() {
		return pServico;
	}

	public ProdutoMainPanel getPProduto() {
		return pProduto;
	}

	public ClienteMainPanel getpCliente() {
		return pCliente;
	}
	
	public ApoliceMainPanel getPApolice() {
		return pApolice;
	}
	
	public ObitoMainPanel getPObito(){
		return pObito;
	}
	

	public ApoliceEditarMainPanel getPEditApolice() {
		return pEditApolice;
	}
	
	public ClienteEditarMainPanel getClienteEditarMainPanel() {
		return pEditCliente;
	}

	public ColaboradorMainPanel getPColadorador() {
		return pColadorador;
	}
	
	public ParceiroMainPanel getPParceiro() {
		return pParceiro;
	}
	
	public EquipamentoMainPanel getPEquipamento() {
		return pEquipamento;
	}
	
	public EmprestimoEquipamentoMainPanel getPEmprestimoEquipamento() {
		return pEmprestimoEquipamento;
	}

	public CidadeMainPanel getPCidade() {
		return pCidade;
	}

	public RelacaoTitularMainPanel getPRelacao() {
		return pRelacao;
	}

	public AdminFaturaMainPanel getPAdminFaturas() {
		return pAdmFaturas;
	}
	
	public AdminFaturaGeralMainPanel getPAdminGeralFaturas() {
		return pAdmGeralFaturas;
	}
	
	public FornecedorMainPanel getpEmpresa() {
		return pEmpresa;
	}

	public CompraMainPanel getpCompra() {
		return pCompra;
	}
	
	public VendaMainPanel getpVenda() {
		return pVenda;
	}
	
	public CustomLayeredPanel getPVazio() {
		return pVazio;
	}

	public void desabilitarTudo(){
		for (Component c : painelPrincipal.getComponents()) {
			if(c instanceof CustomLayeredPanel){
				CustomLayeredPanel clp = (CustomLayeredPanel)c;
				clp.setEnabled(false);
			}
		}		
		getJMenuBar().setEnabled(false);
	}

	public void habilitarTudo(){
		for (Component c : painelPrincipal.getComponents()) {
			if(c instanceof CustomLayeredPanel){
				CustomLayeredPanel clp = (CustomLayeredPanel)c;
				clp.setEnabled(true);
			}
		}		
		getJMenuBar().setEnabled(true);
	}
	
	private void initialize() {
		this.painelPrincipal = new JPanel(new CardLayout(0, 0));
		
		this.pApolice = new ApoliceMainPanel();
		this.pObito = new ObitoMainPanel();
		
		this.pEditApolice = new ApoliceEditarMainPanel();
		this.pEditCliente = new ClienteEditarMainPanel();
		this.pPlano = new PlanoMainPanel();
		this.pServico = new ServicoMainPanel();
		this.pProduto = new ProdutoMainPanel();
		this.pColadorador = new ColaboradorMainPanel();
		this.pParceiro = new ParceiroMainPanel();
		this.pEquipamento = new EquipamentoMainPanel();
		this.pEmprestimoEquipamento = new EmprestimoEquipamentoMainPanel();
		this.pCidade = new CidadeMainPanel();
		this.pRelacao = new RelacaoTitularMainPanel();
		this.pAdmFaturas = new AdminFaturaMainPanel();
		this.pCliente = new ClienteMainPanel();
		this.pEmpresa = new FornecedorMainPanel();
		this.pCompra = new CompraMainPanel();
		this.pVenda = new VendaMainPanel();
		this.pAdmGeralFaturas = new AdminFaturaGeralMainPanel();
		this.pVazio = new CustomLayeredPanel();
		this.barStatus = new BarraStatus();
		this.pConta = new ContaMainPanel();
		this.pCheque = new ChequeMainPanel();
		
		this.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				
				int frameWidth = e.getComponent().getWidth();
				int frameHeight = e.getComponent().getHeight();
				
				for (Component c : painelPrincipal.getComponents()) {
					if (c instanceof CustomLayeredPanel){
						CustomLayeredPanel clp = (CustomLayeredPanel)c;
						clp.getDesktop().setSize(frameWidth - 5, frameHeight - 75);
						clp.getDesktop().repaint();
						clp.getGlass().setSize(frameWidth - 5, frameHeight - 75);
						clp.getGlass().repaint();
						clp.getPainelPrincipal().setSize(frameWidth - 5, frameHeight - 75);
						clp.getPainelPrincipal().repaint();
					}
				}
			}
		});
	}
	
	private void configure(){
		this.painelPrincipal.setDoubleBuffered(true);
		this.painelPrincipal.add(pVazio, "Default");
		this.painelPrincipal.add(pApolice, ApoliceMainPanel.FORM_NAME);
		this.painelPrincipal.add(pEditApolice, ApoliceEditarMainPanel.FORM_NAME);
		this.painelPrincipal.add(pPlano, PlanoMainPanel.FORM_NAME);
		this.painelPrincipal.add(pObito, ObitoMainPanel.FORM_NAME);
		this.painelPrincipal.add(pServico, ServicoMainPanel.FORM_NAME);
		this.painelPrincipal.add(pProduto, ProdutoMainPanel.FORM_NAME);
		this.painelPrincipal.add(pColadorador, ColaboradorMainPanel.FORM_NAME);
		this.painelPrincipal.add(pParceiro, ParceiroMainPanel.FORM_NAME);
		this.painelPrincipal.add(pEquipamento, EquipamentoMainPanel.FORM_NAME);
		this.painelPrincipal.add(pEmprestimoEquipamento, EmprestimoEquipamentoMainPanel.FORM_NAME);
		this.painelPrincipal.add(pCidade, CidadeMainPanel.FORM_NAME);
		this.painelPrincipal.add(pRelacao, RelacaoTitularMainPanel.FORM_NAME);
		this.painelPrincipal.add(pAdmFaturas, AdminFaturaMainPanel.FORM_NAME);
		this.painelPrincipal.add(pCliente, ClienteMainPanel.FORM_NAME);
		this.painelPrincipal.add(pEditCliente, ClienteEditarMainPanel.FORM_NAME);
		this.painelPrincipal.add(pAdmGeralFaturas, AdminFaturaGeralMainPanel.FORM_NAME);
		this.painelPrincipal.add(pEmpresa, FornecedorMainPanel.FORM_NAME);
		this.painelPrincipal.add(pCompra, CompraMainPanel.FORM_NAME);
		this.painelPrincipal.add(pVenda, VendaMainPanel.FORM_NAME);
		this.painelPrincipal.add(pConta, ContaMainPanel.FORM_NAME);
		this.painelPrincipal.add(pCheque, ChequeMainPanel.FORM_NAME);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(this.painelPrincipal, BorderLayout.CENTER);
		this.getContentPane().add(this.barStatus, BorderLayout.SOUTH);
		this.setJMenuBar(new BarraMenu(this.painelPrincipal));
		this.setTitle("São Dimas - Plano de Assistência Familiar");
		this.setIconImage(new ImageIcon("imagens/blackRibbon.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}
	
	public void mostrarTelaLogin(boolean mostrar){
		if (mostrar){
			pVazio.getIframeLogon().setVisible(true);
		}
	}
}
