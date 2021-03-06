package br.com.saodimas.view.components.panel.apolice.editar.obito;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.CemiterioVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.GrupoTrabalhoVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ProdutoObitoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;
import br.com.saodimas.model.beans.TipoAtendimentoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.panel.obito.ObitoMainPanel;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.components.text.MoedaTextField;

@SuppressWarnings("serial")
public class ObitoPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigat�rio";
	
	private ApoliceVO apolice;
	private ObitoVO obito;
	private BarraNotificacao barNotificacao;
	private JComboBox cbAssociado;
	private DataTextField spnDataObito;
	private JTextField txtCausaObito;
	private JTextField txtAtestadoObito;
	private DataTextField spnDataExpedicao;
	private JTextField txtLocalFalecimento;
	private JComboBox cbCidadeFalecimento;
	private JTextField txtLocalVelorio;
	private JComboBox cbCidadeVelorio;
	private JComboBox cbCemiterioSepultamento;
	private JComboBox cbGrupoTrabalhoViagem;
	private JComboBox cbGrupoTrabalhoCortejo;
	private DataTextField spnDataSepultamento;
	private JTextField txtHoraSepultamento;
	private JComboBox<String> cbEstadoViagem;
	private JComboBox<String> cbEstadoCortejo;
	private JTabbedPane tabbedPanel;
	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbAssociado;
	private JLabel lbDataObito;
	private JLabel lbCausaObito;
	private JLabel lbAtestadoObito;
	private JLabel lbDataExpedicao;
	
	private JLabel lbLocalFalecimento;
	private JLabel lbCidadeFalecimento;
	private JLabel lbLocalVelorio;
	private JLabel lbCidadeVelorio;
	private JLabel lbCemiterioSepultamento;
	private JLabel lbMotoristaViagem;
	private JLabel lbMotoristaCortejo;
	private JLabel lbDataSepultamento;
	private JLabel lbHoraSepultamento;
		
	private JLabel lbTotalProduto;
	private MoedaTextField txtTotalProduto;
		
	private ObitoProdutoPanel produtosPanel;
	private ObitoServicoPanel servicosPanel;
	
	private boolean isTelaGerenciarObito = false;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public ObitoPanel() {
		initialize();
		configure();
	}

	public List<ProdutoObitoVO> getProdutosObitoRemover() {
		return produtosPanel.getProdutosObitoRemover();
	}
	
	public List<ServicoObitoVO> getServicosObitoRemover() {
		return servicosPanel.getServicosObitoRemover();
	}
	
	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
	}

	public void setObito(ObitoVO obito) {
		this.obito = obito;
		this.carregarCidadesByEstado("PR");
		this.carregarGruposTrabalho();
		this.carregarCemiterios();
		
		if(obito != null)
		{
				adicionaApolice(obito.getApolice());
				txtCausaObito.setText(obito.getCausa());
				txtAtestadoObito.setText(obito.getNumeroAtestado());
				cbAssociado.setSelectedItem(obito.getAssociado());
				spnDataObito.setText(formataData(obito.getData()));
				spnDataExpedicao.setText(formataData(obito.getDataExpedicao()));
				txtTotalProduto.setValor(calcularTotalObito());
				
				Object objCidadeF = obito.getCidadeFalecimento();
				Object objCidadeV = obito.getCidadeVelorio();
				Object objCemiterio = obito.getCemiterio();
				Object objMotoristaV = obito.getGrupoTrabalhoViagem();
				Object objMotoristaC = obito.getGrupoTrabalhoCortejo();
				
				
				txtLocalFalecimento.setText(obito.getLocalFalecimento());
				txtLocalVelorio.setText(obito.getLocalVelorio());
				
				if(objCidadeF instanceof CidadeVO)
					cbCidadeFalecimento.setSelectedItem((CidadeVO)objCidadeF);
				if(objCidadeV instanceof CidadeVO)
					cbCidadeVelorio.setSelectedItem((CidadeVO) objCidadeV);
				if(objCemiterio instanceof CemiterioVO)
					cbCemiterioSepultamento.setSelectedItem((CemiterioVO) objCemiterio);
				if(objMotoristaV instanceof GrupoTrabalhoVO)
					cbGrupoTrabalhoViagem.setSelectedItem((GrupoTrabalhoVO)  objMotoristaV);
				if(objMotoristaC instanceof GrupoTrabalhoVO)
					cbGrupoTrabalhoCortejo.setSelectedItem((GrupoTrabalhoVO) objMotoristaC);
				
				txtHoraSepultamento.setText(obito.getHoraSepultamento());
				spnDataSepultamento.setText(obito.getDataSepultamento() != null ? formataData(obito.getDataSepultamento()) : "");
				
				List <ProdutoObitoVO> produtos = new ArrayList<ProdutoObitoVO>();
				if (obito.getProdutos() != null) produtos.addAll(obito.getProdutos());
				produtosPanel.carregarProdutos(produtos);
				
				List <ServicoObitoVO> servicos = new ArrayList<ServicoObitoVO>();
				if (obito.getServicos() != null) servicos.addAll(obito.getServicos());
				servicosPanel.carregarServicos(servicos);
			
		}else
			limparCampos();
		
		
		
	}
	
	public void colocarModoVisualizacao(boolean isModoVisualizacao, boolean isTelaGerenciarObito)
	{
		this.isTelaGerenciarObito = isModoVisualizacao;
		
		this.produtosPanel.setBloqueioCampos(isModoVisualizacao);
		this.servicosPanel.setBloqueioCampos(isModoVisualizacao);
		this.cbAssociado.setEnabled(!isModoVisualizacao);
		this.spnDataObito.setEditable(!isModoVisualizacao);
		this.txtCausaObito.setEditable(!isModoVisualizacao);
		this.txtAtestadoObito.setEditable(!isModoVisualizacao);
		this.spnDataExpedicao.setEditable(!isModoVisualizacao);
		this.txtLocalFalecimento.setEditable(!isModoVisualizacao);;
		this.cbCidadeFalecimento.setEnabled(!isModoVisualizacao);
		this.txtLocalVelorio.setEditable(!isModoVisualizacao);
		this.cbCidadeVelorio.setEnabled(!isModoVisualizacao);
		this.cbCemiterioSepultamento.setEnabled(!isModoVisualizacao);
		this.cbGrupoTrabalhoViagem.setEnabled(!isModoVisualizacao);
		this.cbGrupoTrabalhoCortejo.setEnabled(!isModoVisualizacao);
		this.spnDataSepultamento.setEditable(!isModoVisualizacao);
		this.txtHoraSepultamento.setEditable(!isModoVisualizacao);
				
		this.btCancelar.setText(isModoVisualizacao ? "Fechar" : "Cancelar");
		this.btSalvar.setVisible(!isModoVisualizacao);
			
	}
	
	private double calcularTotalObito()
	{
		double valortotal = 0;
		
		if(obito != null){
			for (ProdutoObitoVO produtoObitoVO : obito.getProdutos()) {
				valortotal += produtoObitoVO.getTotal(); 
			}
			
			for (ServicoObitoVO servicoObitoVO : obito.getServicos()) {
				valortotal += servicoObitoVO.getTotal();
			}
		}
		return valortotal;
	}
	
	private void carregarCidadesByEstado(String estado)
	{
		List<CidadeVO> list = Controller.getInstance().getCidadeAllByEstado(estado);
		cbCidadeFalecimento.removeAllItems();
		cbCidadeVelorio.removeAllItems();
		
		String colNenhum = null;
		cbCidadeFalecimento.addItem(colNenhum);
		cbCidadeVelorio.addItem(colNenhum);
		for (int i=0; i< list.size(); i++) {
			cbCidadeFalecimento.addItem(list.get(i));
			cbCidadeVelorio.addItem(list.get(i));
		}
		
	}
	
	private void carregarGruposTrabalho()
	{
		List<GrupoTrabalhoVO> list = Controller.getInstance().getGrupoTrabalhoAll();
		cbGrupoTrabalhoCortejo.removeAllItems();
		cbGrupoTrabalhoViagem.removeAllItems();
		
		String colNenhum = null;
		cbGrupoTrabalhoCortejo.addItem(colNenhum);
		cbGrupoTrabalhoViagem.addItem(colNenhum);
		
		for (int i=0; i< list.size(); i++) {
			cbGrupoTrabalhoCortejo.addItem(list.get(i));
			cbGrupoTrabalhoViagem.addItem(list.get(i));
		}
		
	}
	
	private void carregarCemiterios()
	{
		List<CemiterioVO> list = Controller.getInstance().getCemiteriosAll();
		cbCemiterioSepultamento.removeAllItems();
		
		String colNenhum = null;
		cbCemiterioSepultamento.addItem(colNenhum);
		
	
		for (int i=0; i< list.size(); i++) {
			cbCemiterioSepultamento.addItem(list.get(i));
			
		}
		
	}
	
	
	public void adicionaApolice(ApoliceVO apolice) {
		this.apolice = apolice;
		if (apolice != null) {

			cbAssociado.removeAllItems();
			List<AssociadoVO> dependentes = apolice.getDependentes();

			if(dependentes != null) {
				for (AssociadoVO associadoVO : dependentes) {
					if (associadoVO.getObito() == null)
						cbAssociado.addItem(associadoVO);
				}
			}
			if(obito!= null && obito.getAssociado()!= null)
				cbAssociado.addItem(obito.getAssociado());	

		}
	}	

	public void adicionaProduto(ProdutoObitoVO p){
		produtosPanel.adicionaProduto(p);
	}

	public void adicionaServico(ServicoObitoVO s) {
		servicosPanel.adicionaServico(s);
	}	

	public void limparCampos() {
		txtCausaObito.setText("");
		txtAtestadoObito.setText("");
		txtTotalProduto.setValor(0.0);
		txtTotalProduto.setText("");
		produtosPanel.removeAllProdutos();
		servicosPanel.removeAllServicos();
		spnDataObito.setText("");
		spnDataExpedicao.setText("");
		
		txtLocalFalecimento.setText("");
		cbCidadeFalecimento.setSelectedIndex(0);
		txtLocalVelorio.setText("");
		cbCidadeVelorio.setSelectedIndex(0);
		cbCemiterioSepultamento.setSelectedIndex(0);
		cbGrupoTrabalhoViagem.setSelectedIndex(0);
		cbGrupoTrabalhoCortejo.setSelectedIndex(0);
		txtHoraSepultamento.setText("");
		spnDataSepultamento.setText("");
		
	}
		
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
				
		lbAssociado = new JLabel("Associado: *", JLabel.RIGHT);
		lbAssociado.setPreferredSize(DLABEL);
		lbAssociado.setMinimumSize(DLABEL);

		cbAssociado = new JComboBox();
		cbAssociado.setPreferredSize(DFIELDM);
		cbAssociado.setMinimumSize(DFIELDM);
		
		lbDataObito= new JLabel("Data do �bito: *", JLabel.RIGHT);
		lbDataObito.setPreferredSize(DLABEL);
		lbDataObito.setMinimumSize(DLABEL);
				
		spnDataObito = new DataTextField();
		spnDataObito.setPreferredSize(DFIELDM);
		spnDataObito.setMinimumSize(DFIELDM);

		lbCausaObito = new JLabel("Causa do �bito: ", JLabel.RIGHT);
		lbCausaObito.setPreferredSize(DLABEL);
		lbCausaObito.setMinimumSize(DLABEL);
		
		CustomDocument causaDoc = new CustomDocument(50);
		txtCausaObito = new JTextField();
		txtCausaObito.setDocument(causaDoc);
		txtCausaObito.setPreferredSize(DFIELDM);
		
		lbAtestadoObito = new JLabel("N� Atestado de �bito: ", JLabel.RIGHT);
		lbAtestadoObito.setPreferredSize(DLABEL);
		lbAtestadoObito.setMinimumSize(DLABEL);

		CustomDocument atestadoDoc = new CustomDocument(15);
		txtAtestadoObito = new JTextField();
		txtAtestadoObito.setDocument(atestadoDoc);
		txtAtestadoObito.setPreferredSize(DFIELDM);
		
		
		lbDataExpedicao= new JLabel("Expedido em: ", JLabel.RIGHT);
		lbDataExpedicao.setPreferredSize(DLABEL);
		lbDataExpedicao.setMinimumSize(DLABEL);
		
		
		spnDataExpedicao = new DataTextField();
		spnDataExpedicao.setPreferredSize(DFIELDM);
		spnDataExpedicao.setMinimumSize(DFIELDM);
		
		
		lbLocalFalecimento = new JLabel("Local Falecimento: ", JLabel.RIGHT);
		lbLocalFalecimento.setPreferredSize(DLABEL);
		lbLocalFalecimento.setMinimumSize(DLABEL);
		
		CustomDocument cLocal = new CustomDocument(100);
		txtLocalFalecimento = new JTextField();
		txtLocalFalecimento.setDocument(cLocal);
		txtLocalFalecimento.setPreferredSize(DFIELDM);
		
		
		lbCidadeFalecimento = new JLabel("Cidade Falecimento: ", JLabel.RIGHT);
		lbCidadeFalecimento.setPreferredSize(DLABEL);
		lbCidadeFalecimento.setMinimumSize(DLABEL);
		
		cbCidadeFalecimento = new JComboBox();
		//cbCidadeFalecimento.setPreferredSize(DFIELDM);
		cbCidadeFalecimento.setMinimumSize(DFIELDM);
		
		
		lbLocalVelorio = new JLabel("Local Vel�rio: ", JLabel.RIGHT);
		lbLocalVelorio.setPreferredSize(DLABEL);
		lbLocalVelorio.setMinimumSize(DLABEL);
		
		CustomDocument cLocalVelorio = new CustomDocument(100);
		txtLocalVelorio =  new JTextField();
		txtLocalVelorio.setPreferredSize(DFIELDM);
		txtLocalVelorio.setDocument(cLocalVelorio);
		
		lbCidadeVelorio = new JLabel("Cidade do Val�rio: ", JLabel.RIGHT);
		lbCidadeVelorio.setPreferredSize(DLABEL);
		lbCidadeVelorio.setMinimumSize(DLABEL);
		
		cbCidadeVelorio = new JComboBox();
		//cbCidadeVelorio.setPreferredSize(DFIELDM);
		cbCidadeVelorio.setMinimumSize(DFIELDM);
		
		lbCemiterioSepultamento = new JLabel("Cemit�rio Sepultamento: ", JLabel.RIGHT);
		lbCemiterioSepultamento.setPreferredSize(DLABEL);
		lbCemiterioSepultamento.setMinimumSize(DLABEL);
		
		cbCemiterioSepultamento = new JComboBox();
		//cbCemiterioSepultamento.setPreferredSize(DFIELDM);
		cbCemiterioSepultamento.setMinimumSize(DFIELDM);
		
		lbMotoristaViagem = new JLabel("Motorista da Translado: ", JLabel.RIGHT);
		lbMotoristaViagem.setPreferredSize(DLABEL);
		lbMotoristaViagem.setMinimumSize(DLABEL);
		
		cbGrupoTrabalhoViagem = new JComboBox();
		//cbMotoristaViagem.setPreferredSize(DFIELDM);
		cbGrupoTrabalhoViagem.setMinimumSize(DFIELDM);
		
		lbMotoristaCortejo = new JLabel("Motorista Cortejo: ", JLabel.RIGHT);
		lbMotoristaCortejo.setPreferredSize(DLABEL);
		lbMotoristaCortejo.setMinimumSize(DLABEL);
		
		cbGrupoTrabalhoCortejo =  new JComboBox();
		//cbMotoristaSepultamento.setPreferredSize(DFIELDM);
		cbGrupoTrabalhoCortejo.setMinimumSize(DFIELDM);

		lbDataSepultamento = new JLabel("Data Sepultamento: ", JLabel.RIGHT);
		lbDataSepultamento.setPreferredSize(DLABEL);
		lbDataSepultamento.setMinimumSize(DLABEL);
		
		spnDataSepultamento = new DataTextField();
		spnDataSepultamento.setPreferredSize(DFIELDM);
		spnDataSepultamento.setMinimumSize(DFIELDM);
		
		lbHoraSepultamento = new JLabel("Hora Sepultamento: ", JLabel.RIGHT);
		lbHoraSepultamento.setPreferredSize(DLABEL);
		lbHoraSepultamento.setMinimumSize(DLABEL);
		
		CustomDocument cHora = new CustomDocument(5);
		txtHoraSepultamento = new JTextField();
		txtHoraSepultamento.setDocument(cHora);
		txtHoraSepultamento.setPreferredSize(DFIELDM);
		
		tabbedPanel = new JTabbedPane();
		produtosPanel = new ObitoProdutoPanel();
		servicosPanel = new ObitoServicoPanel();
		
			
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		
		lbTotalProduto = new JLabel("Total Gasto pelo Plano:  ", JLabel.RIGHT);
		//lbTotalProduto.setPreferredSize(DLABEL);
		lbTotalProduto.setMinimumSize(DLABEL);

		txtTotalProduto = new MoedaTextField();
		txtTotalProduto.setPreferredSize(DFIELDM);
		txtTotalProduto.setMinimumSize(DFIELDM);
		txtTotalProduto.setEditable(false);

	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infObito = new JPanel(new GridBagLayout());
	
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infObito.add(lbAssociado, c);
		c.gridy = 1; infObito.add(lbDataObito, c);
		c.gridy = 2; infObito.add(lbDataExpedicao, c);
		c.gridy = 3; infObito.add(lbCausaObito, c);
		c.gridy = 4; infObito.add(lbAtestadoObito, c);
		c.gridy = 5; infObito.add(new JLabel(""), c);
		c.gridy = 6; infObito.add(lbLocalFalecimento, c);
		c.gridy = 7; infObito.add(lbCidadeFalecimento, c);
		c.gridy = 8; infObito.add(lbMotoristaViagem, c);
		c.gridy = 9; infObito.add(new JLabel(""), c);
		c.gridy = 10; infObito.add(lbLocalVelorio, c);
		c.gridy = 11; infObito.add(lbCidadeVelorio, c);
		c.gridy = 12; infObito.add(lbCemiterioSepultamento, c);
		c.gridy = 13; infObito.add(lbMotoristaCortejo, c);
		c.gridy = 14; infObito.add(lbDataSepultamento, c);
		c.gridy = 15; infObito.add(lbHoraSepultamento, c);
		c.gridy = 16; infObito.add(new JLabel(""), c);
		c.gridy = 17; infObito.add(lbTotalProduto, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infObito.add(cbAssociado, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1; infObito.add(spnDataObito, c);
		c.gridy = 2; infObito.add(spnDataExpedicao, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 3; infObito.add(txtCausaObito, c);
		c.gridy = 4; infObito.add(txtAtestadoObito, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 5; infObito.add(new JLabel(" "), c);
		c.gridy = 6; infObito.add(txtLocalFalecimento, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 7; infObito.add(cbCidadeFalecimento, c);
		c.gridy = 8; infObito.add(cbGrupoTrabalhoViagem, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 9; infObito.add(new JLabel(" "), c);
		c.gridy = 10; infObito.add(txtLocalVelorio, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 11; infObito.add(cbCidadeVelorio, c);
		c.gridy = 12; infObito.add(cbCemiterioSepultamento, c);
		c.gridy = 13; infObito.add(cbGrupoTrabalhoCortejo, c);
		c.gridy = 14; infObito.add(spnDataSepultamento, c);
		c.gridy = 15; infObito.add(txtHoraSepultamento, c);
		c.weighty = 2;
		c.gridy = 16; infObito.add(new JLabel(" "), c);
		c.gridy = 17; infObito.add(txtTotalProduto, c);
		
		
		infObito.setBorder(BorderFactory.createTitledBorder("Registro de �bito"));
		adicionarAtalhosComandos(infObito);
	
		tabbedPanel.addTab("�bito", new ImageIcon("imagens/obitos.gif"), infObito);
		tabbedPanel.addTab("Produtos Adicionais", new ImageIcon("imagens/produtos.gif"), produtosPanel);
		tabbedPanel.addTab("Servi�os Adicionais", new ImageIcon("imagens/servicos.gif"), servicosPanel);
		
		tabbedPanel.addChangeListener(new EventoAbas());
		tabbedPanel.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btCancelar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) btSalvar.doClick();
				else super.keyPressed(e);
			}
		});
	
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		JPanel panelObito = new JPanel(new BorderLayout()); 
		panelObito.add(tabbedPanel, BorderLayout.CENTER);
		panelObito.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelObito);
		
		JPanel pObito = new JPanel(new BorderLayout());
		pObito.add(barNotificacao, BorderLayout.NORTH);
		pObito.add(panelObito, BorderLayout.CENTER);
		pObito.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(pObito);
		
		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		formPrincipal.add(pObito);
		adicionarAtalhosComandos(formPrincipal);
		
		setLayout(new BorderLayout());
		add(formPrincipal, BorderLayout.CENTER);
		adicionarAtalhosComandos(this);
	}

	private void adicionarAtalhosComandos(JPanel panel){
		for (Component c : panel.getComponents()) {
			c.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btCancelar.doClick();
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btSalvar.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}

	private ApoliceEditarMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceEditarMainPanel) return (ApoliceEditarMainPanel)c;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel)c;
	}	
	
	private ObitoMainPanel getPainelPrincipalObito(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ObitoMainPanel) return (ObitoMainPanel)c;
			c = c.getParent();
		}
		return (ObitoMainPanel)c;
	}		


	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				if (c != null && !isTelaGerenciarObito){
					c.getIframeObito().setVisible(false);
				}else if(c != null && isTelaGerenciarObito)
				{
					ObitoMainPanel cO = getPainelPrincipalObito();
					cO.getIframeObito().setVisible(false);
				}
			} 
			else if (e.getSource() == btSalvar){
				
				try{
					if(obito != null)
					{
						obito.setApolice(apolice);
						obito.setAssociado((AssociadoVO)cbAssociado.getSelectedItem());
						obito.setCausa(txtCausaObito.getText());
						obito.setData(transformarData(spnDataObito.getText()));
						
						obito.setDataExpedicao(transformarData(spnDataExpedicao.getText()));
						obito.setNumeroAtestado(txtAtestadoObito.getText());
						
						obito.setLocalFalecimento(txtLocalFalecimento.getText());
						obito.setCidadeFalecimento((CidadeVO)cbCidadeFalecimento.getSelectedItem());
						obito.setGrupoTrabalhoViagem((GrupoTrabalhoVO) cbGrupoTrabalhoViagem.getSelectedItem());
						obito.setLocalVelorio(txtLocalVelorio.getText());
						obito.setCidadeVelorio((CidadeVO)cbCidadeVelorio.getSelectedItem());
						obito.setGrupoTrabalhoCortejo((GrupoTrabalhoVO )cbGrupoTrabalhoCortejo.getSelectedItem());
						obito.setCemiterio((CemiterioVO)cbCemiterioSepultamento.getSelectedItem());
						
								
						obito.setDataSepultamento((transformarData(spnDataSepultamento.getText())));
						obito.setHoraSepultamento((txtHoraSepultamento.getText()));
						
						obito.setProdutos(produtosPanel.getAllProdutos(obito));
						obito.setServicos(servicosPanel.getAllServicos(obito));
						Controller.getInstance().deleteProdutosObitos(produtosPanel.getProdutosObitoRemover());
						Controller.getInstance().deleteServicosObitos(servicosPanel.getServicosObitoRemover());
						Controller.getInstance().updateObito(obito);
						c.carregarObitos();
						c.carregarDependentes();
						c.atualizarInformacoesSuperiores();
						c.mostrarMensagemObito("Altera��o realizada com sucesso!", BarraNotificacao.SUCESSO);
						
					}else
					{
						ObitoVO novoobito = new ObitoVO();
						
						novoobito.setApolice(apolice);
						
						AssociadoVO asso = (AssociadoVO)cbAssociado.getSelectedItem(); 
						asso.setObito(novoobito);
						
						novoobito.setAssociado(asso);
						novoobito.setCausa(txtCausaObito.getText());
						novoobito.setData(transformarData(spnDataObito.getText()));
						novoobito.setDataExpedicao(transformarData(spnDataExpedicao.getText()));
						novoobito.setNumeroAtestado(txtAtestadoObito.getText());
						
						novoobito.setLocalFalecimento(txtLocalFalecimento.getText());
						novoobito.setCidadeFalecimento((CidadeVO)cbCidadeFalecimento.getSelectedItem());
						novoobito.setGrupoTrabalhoViagem((GrupoTrabalhoVO ) cbGrupoTrabalhoViagem.getSelectedItem());
						novoobito.setLocalVelorio(txtLocalVelorio.getText());
						novoobito.setCidadeVelorio((CidadeVO)cbCidadeVelorio.getSelectedItem());
						novoobito.setGrupoTrabalhoCortejo((GrupoTrabalhoVO ) cbGrupoTrabalhoCortejo.getSelectedItem());
						novoobito.setCemiterio((CemiterioVO)cbCemiterioSepultamento.getSelectedItem());
							
						novoobito.setDataSepultamento((transformarData(spnDataSepultamento.getText())));
						novoobito.setHoraSepultamento((txtHoraSepultamento.getText()));
						
						novoobito.setProdutos(produtosPanel.getAllProdutos(novoobito));
						novoobito.setServicos(servicosPanel.getAllServicos(novoobito));
						
						TipoAtendimentoVO tipoPlano = new TipoAtendimentoVO();
						tipoPlano.setId(1);
						novoobito.setTipoAtendimento(tipoPlano);
						
						Controller.getInstance().insertObito(novoobito);
						c.carregarObitos();
						c.carregarDependentes();
						c.atualizarApolice();
						c.mostrarMensagemObito("Obito criado com sucesso!", BarraNotificacao.SUCESSO);
					}
				}catch (MensagemSaoDimasException ex) {
					ex.printStackTrace();					
					c.mostrarMensagemObito(ex.getMessage(), BarraNotificacao.ERRO);
				}
				c.getIframeObito().setVisible(false);
				c.atualizarInformacoesSuperiores();
				
				
			}

		}
	}
	
	private class EventoAbas implements ChangeListener{
		public void stateChanged(ChangeEvent ev) {
			if (tabbedPanel.getSelectedComponent() == produtosPanel)
				barNotificacao.mostrarMensagem("   Selecione um produto para remover ou adicione um outro produto!", BarraNotificacao.DEFAULT);
			else if (tabbedPanel.getSelectedComponent() == servicosPanel)
				barNotificacao.mostrarMensagem("   Selecione um servi�o para remover ou adicione um outro servi�o!", BarraNotificacao.DEFAULT);
			else
				barNotificacao.mostrarMensagem("   (*) Preenchimento obrigat�rio", BarraNotificacao.DEFAULT);

		}
	}
	
	private Date transformarData(String data)
	{
		try{
			SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
			return simpledataformat.parse(data);
		}catch (ParseException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	private String formataData(Date date)
	{
		if(date != null){
			SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
			return simpledataformat.format(date);
		}else
			return "";
		
	}
}	