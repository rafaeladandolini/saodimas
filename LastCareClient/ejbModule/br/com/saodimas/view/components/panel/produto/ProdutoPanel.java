package br.com.saodimas.view.components.panel.produto;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.panel.plano.PlanoMainPanel;
import br.com.saodimas.view.components.text.MoedaTextField;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.validators.ValidaBasico;

@SuppressWarnings("serial")
public class ProdutoPanel extends JPanel {
	
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	
	private ProdutoVO produto;
	private BarraNotificacao barNotificacao;
	
	private JComboBox cbTipoProduto;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JComboBox cbReferenciaValor;
	private MoedaTextField txtValor;
	private JComboBox cbStatus;
	
	private JLabel lbTipoProduto;
	private JLabel lbNome;
	private JLabel lbdescricão;
	private JLabel lbReferenciaValor;
	private JLabel lbValor;
	private JLabel lbStatus;
	
	private JButton btCancelar;
	private JButton btSalvar;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	private static final Dimension DFIELDP = new Dimension(75,22);

	public ProdutoPanel() {
		initialize();
		configure();
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	private void pack()
	{
		Component c = getPainelPrincipal();
		((ProdutoMainPanel)c).getIframeProduto().pack();
	}
	
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
		if(produto != null){
			txtNome.setText(produto.getNome());
			txtDescricao.setText(produto.getDescricao());
			if(produto.getValor() != null)
				txtValor.setValor(produto.getValor());
			else
				txtValor.setValor(new Double(0));
			cbReferenciaValor.setSelectedItem(produto.getReferenciaValor());
			cbStatus.setSelectedItem(produto.getStatus());
			cbTipoProduto.setSelectedItem(produto.getTipoProduto());
			lbStatus.setVisible(true);
			cbStatus.setVisible(true);
			pack();
		}else
		{
			limparCampos();
		}
	}

	@SuppressWarnings("static-access")
	public void limparCampos() {
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,	barNotificacao.DICA);
		txtNome.setText("");
		txtValor.setText(null);
		txtDescricao.setText("");
		lbStatus.setVisible(false);
		cbStatus.setVisible(false);
		pack();
	}
	
	public void focoPadrao(){
		txtNome.requestFocus();
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		
		lbTipoProduto = new JLabel("Tipo de Produto:", JLabel.RIGHT);
		lbTipoProduto.setPreferredSize(DLABEL);
		lbTipoProduto.setMinimumSize(DLABEL);

		cbTipoProduto = new JComboBox(new String[]{ProdutoVO.TIPO_PRODUTO_VENDA, ProdutoVO.TIPO_PRODUTO_CONSUMO_INTERNO});
		cbTipoProduto.setPreferredSize(DFIELDP);
		cbTipoProduto.setMinimumSize(DFIELDP);
		
		lbStatus = new JLabel("Status:  ", JLabel.RIGHT);
		lbStatus.setPreferredSize(DLABEL);
		lbStatus.setMinimumSize(DLABEL);
		lbStatus.setVisible(false);

		cbStatus = new JComboBox(ListasComuns.STATUS_ITENS);
		cbStatus.setPreferredSize(DFIELDM);
		cbStatus.setMinimumSize(DFIELDM);
		cbStatus.setVisible(false);

		lbNome = new JLabel("Nome: *", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);

		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);
		
		lbdescricão = new JLabel("Descrição: *", JLabel.RIGHT);
		lbdescricão.setPreferredSize(DLABEL);
		lbdescricão.setMinimumSize(DLABEL);

		CustomDocument descricaoDoc = new CustomDocument(100);
		txtDescricao = new JTextField();
		txtDescricao.setDocument(descricaoDoc);
		txtDescricao.setPreferredSize(DFIELDM);


		lbValor = new JLabel("Valor (em R$): *", JLabel.RIGHT);
		lbValor.setPreferredSize(DLABEL);
		lbValor.setMinimumSize(DLABEL);

		txtValor = new MoedaTextField();
		txtValor.setPreferredSize(DFIELDP);
		txtValor.setMinimumSize(DFIELDP);

		lbReferenciaValor = new JLabel("Referêcia Valor: *", JLabel.RIGHT);
		lbReferenciaValor.setPreferredSize(DLABEL);
		lbReferenciaValor.setMinimumSize(DLABEL);

		cbReferenciaValor = new JComboBox(ListasComuns.REF_PRODUTOS);
		cbReferenciaValor.setPreferredSize(DFIELDP);
		cbReferenciaValor.setMinimumSize(DFIELDP);

		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infProduto = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infProduto.add(lbStatus, c);
		c.gridy = 1; infProduto.add(lbTipoProduto, c);
		c.gridy = 2; infProduto.add(lbNome, c);
		c.gridy = 3; infProduto.add(lbValor, c);
		c.gridy = 4; infProduto.add(lbdescricão, c);
		c.gridy = 5; infProduto.add(lbReferenciaValor, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infProduto.add(cbStatus, c);
		c.gridy = 1; infProduto.add(cbTipoProduto, c);
		c.gridy = 2; infProduto.add(txtNome, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 3; infProduto.add(txtValor, c);
		c.gridy = 4; infProduto.add(txtDescricao, c);
		c.weighty = 1;
		c.gridy = 5; infProduto.add(cbReferenciaValor, c);

		infProduto.setBorder(BorderFactory.createTitledBorder("Produto"));
		adicionarAtalhosComandos(infProduto);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		

		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(infProduto, BorderLayout.CENTER);
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
			if (c.getClass() == ProdutoMainPanel.class || c.getClass() == PlanoMainPanel.class  || c.getClass() == ApoliceEditarMainPanel.class) return c;
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
					if (c.getClass() == ProdutoMainPanel.class)
						((ProdutoMainPanel)c).getIframeProduto().setVisible(false);
					else if (c.getClass() == PlanoMainPanel.class)
						((PlanoMainPanel)c).getIframeProduto().setVisible(false);
					else if (c.getClass() == ApoliceEditarMainPanel.class)
						((ApoliceEditarMainPanel)c).getIframeProduto().setVisible(false);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
			
			if (e.getSource() == btCancelar)
			{
				try{
					/*Controller c = new Controller();
					c.insertPlano(txtNome.getText(),  carencia, limiteAssociado, mensalidade)
					c.insertP, estado)(txtCidade.getText(), cbEstado.getSelectedItem().toString());
					Component co = getPainelPrincipal();
					((CidadeTableModel)((CidadeMainPanel)co).getTbCidade().getModel()).loadData(new Controller().getCidadeAll());*/
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
			
			if (e.getSource() == btSalvar)
			{
				try{
					ValidaBasico.validaCamposProduto(txtNome.getText());
					Component c = getPainelPrincipal();
					ProdutoVO novoproduto = new ProdutoVO();
					
					novoproduto.setNome(txtNome.getText());
					novoproduto.setReferenciaValor(cbReferenciaValor.getSelectedItem().toString());
					novoproduto.setTipoProduto(cbTipoProduto.getSelectedItem().toString());
					novoproduto.setValor(txtValor.getValor());
					novoproduto.setDescricao(txtDescricao.getText());
					if(produto != null){
						novoproduto.setId(produto.getId());
						novoproduto.setStatus(cbStatus.getSelectedItem().toString());
						Controller.getInstance().updateProduto(novoproduto);
						barNotificacao.mostrarMensagem("Alterações efetuadas com sucesso.",	barNotificacao.INFO);
					}else
					{
						novoproduto.setStatus(ListasComuns.STATUS_ITENS[0]);
						Controller.getInstance().insertProduto(novoproduto);
						barNotificacao.mostrarMensagem("Cadastro efetuado com sucesso.",	barNotificacao.INFO);
					}				
					if(c instanceof ProdutoMainPanel){
						((ProdutoMainPanel)c).carregarProdutosTable();
						((ProdutoMainPanel)c).getIframeProduto().setVisible(false);
					}
					if(c instanceof PlanoMainPanel){	
						((PlanoMainPanel)c).adicionaProduto(novoproduto);
						((PlanoMainPanel)c).getIframeProduto().setVisible(false);
					}
					if(c instanceof ApoliceEditarMainPanel){	
						//((ApoliceEditarMainPanel)c).adicionaProduto(novoproduto);
						((ApoliceEditarMainPanel)c).getIframeProduto().setVisible(false);
					}
				}catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(),	barNotificacao.ERRO);
					ex.printStackTrace();
				}
			}
	}
	}
}	