package br.com.saodimas.view.components.panel.plano;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ProdutoPlanoVO;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.beans.ServicoPlanoVO;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.MoedaTextField;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.validators.ValidaBasico;

@SuppressWarnings("serial")
public class PlanoPanel extends JPanel {

	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	private static final String MENSAGEM_PRODUTO = "   Selecione um produto para remover ou adicione um outro produto!";
	private static final String MENSAGEM_SERVICO = "   Selecione um serviço para remover ou adicione um outro serviço!";
	
	private PlanoVO plano;
	private BarraNotificacao barNotificacao;
	private JRadioButton rdEmpresarial;
	private JRadioButton rdFamiliar;
	private ButtonGroup bgrTipo;
	private JTextField txtNome;
	private JSpinner spnCarencia;
	private JSpinner spnNumDependentes;
	private JSpinner spnNumAssociadoExtra;
	private MoedaTextField txtValorBase;
	private JTabbedPane tabbedPanel;
	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbTipo;
	private JLabel lbNome;
	private JLabel lbCarencia;
	private JLabel lbNumDependentes;
	private JLabel lbValorBase;
	private JLabel lbStatus;
	private JComboBox cbStatus;
	private JLabel lbNumAssociadoExtra;
	
	private PlanoProdutoPanel produtosPanel;
	private PlanoServicoPanel servicosPanel;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	private static final Dimension DFIELDP = new Dimension(75,22);
	
	public PlanoPanel() {
		initialize();
		configure();
	}

	public PlanoVO getPlano() {
		return plano;
	}

	private void pack()
	{
		Component c = getPainelPrincipal();
		((PlanoMainPanel)c).getIframePlano().pack();
	}
	
	@SuppressWarnings("static-access")
	public void setPlano(PlanoVO plano) {
		this.plano = plano;
		if (this.plano != null){
			if (plano.isEmpresarial()) bgrTipo.setSelected(rdEmpresarial.getModel(), true);
			else bgrTipo.setSelected(rdFamiliar.getModel(), true);
			txtNome.setText(plano.getDescricao());
			txtValorBase.setValor(plano.getMensalidade());
			spnCarencia.setValue(plano.getCarencia());
			spnNumDependentes.setValue(plano.getLimiteAssociado());
			spnNumAssociadoExtra.setValue(plano.getAssociado_extra());
			List <ProdutoPlanoVO> produtos = new ArrayList<ProdutoPlanoVO>();
			if (plano.getProdutos() != null) produtos.addAll(plano.getProdutos());
			produtosPanel.carregarProdutos(produtos);
			
			List <ServicoPlanoVO> servicos = new ArrayList<ServicoPlanoVO>();
			if (plano.getServicos() != null) servicos.addAll(plano.getServicos());
			servicosPanel.carregarServicos(servicos);
			pack();
			
			cbStatus.setSelectedItem(plano.getStatus());
			lbStatus.setVisible(true);
			cbStatus.setVisible(true);
			barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,	barNotificacao.DICA);
		}
		else{ 
			limparCampos();
			pack();
		}
	}
	

	
	@SuppressWarnings("static-access")
	public void limparCampos() {
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,	barNotificacao.DICA);
		txtNome.setText("");
		spnCarencia.setValue(90);
		spnNumDependentes.setValue(1);
		spnNumAssociadoExtra.setValue(0);
		txtValorBase.setValor(0d);
		bgrTipo.setSelected(rdFamiliar.getModel(), true);
		produtosPanel.removeAllProdutos();
		servicosPanel.removeAllServicos();
		lbStatus.setVisible(false);
		cbStatus.setVisible(false);
	}
	
	public void adicionaProduto(ProdutoVO p){
		produtosPanel.adicionaProduto(p);
	}
	

	public void adicionaServico(ServicoVO s) {
		servicosPanel.adicionaServico(s);
	}	

	public void focoPadrao(){
		txtNome.requestFocus();
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbStatus = new JLabel("Status:  ", JLabel.RIGHT);
		lbStatus.setPreferredSize(DLABEL);
		lbStatus.setMinimumSize(DLABEL);
		lbStatus.setVisible(false);

		cbStatus = new JComboBox(ListasComuns.STATUS_ITENS);
		cbStatus.setPreferredSize(DFIELDM);
		cbStatus.setMinimumSize(DFIELDM);
		cbStatus.setVisible(false);
		
		lbTipo = new JLabel("Tipo:  ", JLabel.RIGHT);
		lbTipo.setPreferredSize(DLABEL);
		lbTipo.setMinimumSize(DLABEL);
		
		rdEmpresarial = new JRadioButton("Empresarial");
		rdFamiliar = new JRadioButton("Particular");
		
		bgrTipo = new ButtonGroup();
		bgrTipo.add(rdFamiliar);
		bgrTipo.add(rdEmpresarial);
		bgrTipo.setSelected(rdFamiliar.getModel(), true);
		
		lbNome = new JLabel("Nome: *", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		
		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);
		
		lbValorBase = new JLabel("Valor Base: *", JLabel.RIGHT);
		lbValorBase.setPreferredSize(DLABEL);
		lbValorBase.setMinimumSize(DLABEL);
		
		txtValorBase = new MoedaTextField();
		txtValorBase.setDocument(nomeDoc);
		txtValorBase.setPreferredSize(DFIELDM);
		
		lbNumDependentes = new JLabel("Qtde. Dependentes: *", JLabel.RIGHT);
		lbNumDependentes.setPreferredSize(DLABEL);
		lbNumDependentes.setMinimumSize(DLABEL);
		
		spnNumDependentes = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
		spnNumDependentes.setPreferredSize(DFIELDP);
		spnNumDependentes.setMinimumSize(DFIELDP);
		
		lbNumAssociadoExtra = new JLabel("Qtde. Associado Extra: *", JLabel.RIGHT);
		lbNumAssociadoExtra.setPreferredSize(DLABEL);
		lbNumAssociadoExtra.setMinimumSize(DLABEL);
		
		spnNumAssociadoExtra = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
		spnNumAssociadoExtra.setPreferredSize(DFIELDP);
		spnNumAssociadoExtra.setMinimumSize(DFIELDP);
		
		lbCarencia = new JLabel("Carência (em dias): *", JLabel.RIGHT);
		lbCarencia.setPreferredSize(DLABEL);
		lbCarencia.setMinimumSize(DLABEL);
		
		spnCarencia = new JSpinner(new SpinnerNumberModel(90, 0, 355, 1));
		spnCarencia.setPreferredSize(DFIELDP);
		spnCarencia.setMinimumSize(DFIELDP);

		tabbedPanel = new JTabbedPane();
		tabbedPanel.setFocusable(false);

		produtosPanel = new PlanoProdutoPanel();
		servicosPanel = new PlanoServicoPanel();

		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);

	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infPlano = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infPlano.add(lbTipo, c);
		c.gridy = 1; infPlano.add(lbStatus, c);
		c.gridy = 2; infPlano.add(lbNome, c);
		c.gridy = 3; infPlano.add(lbValorBase, c);
		c.gridy = 4; infPlano.add(lbNumDependentes, c);
		c.gridy = 5; infPlano.add(lbCarencia, c);
		c.gridy = 6; infPlano.add(lbNumAssociadoExtra, c);
		
		JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTipo.add(rdFamiliar);
		panelTipo.add(rdEmpresarial);
		panelTipo.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(panelTipo);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infPlano.add(panelTipo, c);
		c.gridy = 1; infPlano.add(cbStatus, c);
		c.gridy = 2; infPlano.add(txtNome, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 3; infPlano.add(txtValorBase, c);
		c.gridy = 4; infPlano.add(spnNumDependentes, c);
		c.weighty = 1;
		c.gridy = 5; infPlano.add(spnCarencia, c);
		c.gridy = 6; infPlano.add(spnNumAssociadoExtra, c);
		
		infPlano.setBorder(BorderFactory.createTitledBorder("Plano de Assistência"));
		adicionarAtalhosComandos(infPlano);

		tabbedPanel.addTab("Plano", new ImageIcon("imagens/planos.gif"), infPlano);
		tabbedPanel.addTab("Produtos", new ImageIcon("imagens/produtos.gif"), produtosPanel);
		tabbedPanel.addTab("Serviços", new ImageIcon("imagens/servicos.gif"), servicosPanel);
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
		
		JPanel panelPlano = new JPanel(new BorderLayout()); 
		panelPlano.add(tabbedPanel, BorderLayout.CENTER);
		panelPlano.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelPlano);
		
		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(panelPlano, BorderLayout.CENTER);
		panelColaborador.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelColaborador);
		
		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		formPrincipal.add(panelColaborador);
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
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c.getClass() == PlanoMainPanel.class) return c;
			c = c.getParent();
		}
			
		return c;
	}		
	
	private class EventoBotaoControle implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btCancelar){
				Component c = getPainelPrincipal();
				try{
					((PlanoMainPanel)c).getIframePlano().setVisible(false);
				}
				catch(Exception ex){
					ex.printStackTrace();
					}
			}else
			if (e.getSource() == btSalvar)
			{
				try{
					ValidaBasico.validaCamposPlano(txtNome.getText(), txtValorBase.getText());
					
					Component c = getPainelPrincipal();
					PlanoVO novoPlano = new PlanoVO();
					novoPlano.setCarencia(Integer.parseInt(spnCarencia.getValue().toString()));
					novoPlano.setDescricao(txtNome.getText());
					novoPlano.setEmpresarial(rdEmpresarial.isSelected());
					novoPlano.setLimiteAssociado(Integer.parseInt(spnNumDependentes.getValue().toString()));
					novoPlano.setAssociado_extra(Integer.parseInt(spnNumAssociadoExtra.getValue().toString()));
					novoPlano.setMensalidade(txtValorBase.getValor());
					novoPlano.setProdutos(produtosPanel.getAllProdutos(novoPlano));
					novoPlano.setServicos(servicosPanel.getAllServicos(novoPlano));
					
					if(plano != null){
						novoPlano.setId(plano.getId());
						novoPlano.setStatus(cbStatus.getSelectedItem().toString());
						Controller.getInstance().updatePlano(novoPlano);
						barNotificacao.mostrarMensagem("Alterações efetuadas com sucesso.",	barNotificacao.INFO);
					}else
					{
						novoPlano.setStatus(ListasComuns.STATUS_ITENS[0]);
						Controller.getInstance().insertPlano(novoPlano);
						barNotificacao.mostrarMensagem("Cadastro efetuado com sucesso.",	barNotificacao.INFO);
					}				
					((PlanoMainPanel)c).carregarPlanosTable();
					((PlanoMainPanel)c).getIframePlano().setVisible(false);
				
				}catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(),	barNotificacao.ERRO);
					ex.printStackTrace();
				}
			}
		}
	}
	
	private class EventoAbas implements ChangeListener{
		public void stateChanged(ChangeEvent ev) {
			if (tabbedPanel.getSelectedComponent() == produtosPanel)
				barNotificacao.mostrarMensagem(MENSAGEM_PRODUTO, BarraNotificacao.DEFAULT);
			else if (tabbedPanel.getSelectedComponent() == servicosPanel)
				barNotificacao.mostrarMensagem(MENSAGEM_SERVICO, BarraNotificacao.DEFAULT);
			else
				barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, BarraNotificacao.DEFAULT);

		}
	}
}	