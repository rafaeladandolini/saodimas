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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.MoedaTextField;
import br.com.saodimas.view.util.ListasComuns;
import groovy.swing.factory.TDFactory;
import groovy.swing.factory.VGlueFactory;

@SuppressWarnings("serial")
public class ConsultaProdutoPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = " ";
	private static final int QUALQUER = 0;
	private static final int NOME = 1;
	private static final int PRECO = 2;
	private static final String[] OPCOES_CONSULTA = {"Qualquer","Nome","Preço"}; 
	
	private BarraNotificacao barNotificacao;
	private JComboBox cbOpcoes;
	private JTextField txtNome;
	private MoedaTextField txtPrecoMin;
	private MoedaTextField txtPrecoMax;
	private JPanel panelPrecos;
	private ButtonGroup bgrStatus;
	private JRadioButton rdQualquer;
	private JRadioButton rdAtivo;
	private JRadioButton rdCancelado;
	private ButtonGroup bgrTipo;
	private JRadioButton rdQualquerTipo;
	private JRadioButton rdVenda;
	private JRadioButton rdConsumoInterno;
	
	private JButton btCancelar;
	private JButton btEnviar;
	
	private JLabel lbOpcoes;
	private JLabel lbNome;
	private JLabel lbPreco;
	private JLabel lbStatus;
	private JLabel lbTipo;
		
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	private static final Dimension DFIELDP = new Dimension(75,22);
	
	public ConsultaProdutoPanel() {
		initialize();
		configure();
	}

	public void limparCampos() {
		txtNome.setText("");
		txtPrecoMin.setValor(0d);
		txtPrecoMax.setValor(0d);
		rdQualquer.setSelected(true);
		cbOpcoes.setSelectedIndex(0);
		barNotificacao.escondeMensagem();
	}
	
	public void focoPadrao(){
		cbOpcoes.requestFocus();
	}		

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbOpcoes = new JLabel("Consultar por... ", JLabel.RIGHT);
		lbOpcoes.setPreferredSize(DLABEL);
		lbOpcoes.setMinimumSize(DLABEL);
		
		cbOpcoes = new JComboBox(OPCOES_CONSULTA);
		cbOpcoes.setPreferredSize(DFIELDM);
		cbOpcoes.setMinimumSize(DFIELDM);
		cbOpcoes.addItemListener(new EventoComboOpcoes());
		
		lbNome = new JLabel("Que contenha... ", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		lbNome.setVisible(false);
		
		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);
		txtNome.setVisible(false);
		
		lbPreco = new JLabel("Custando entre...", JLabel.RIGHT);
		lbPreco.setPreferredSize(DLABEL);
		lbPreco.setMinimumSize(DLABEL);
		lbPreco.setVisible(false);
		
		txtPrecoMin = new MoedaTextField();
		txtPrecoMin.setPreferredSize(DFIELDP);
		txtPrecoMin.setMinimumSize(DFIELDP);

		txtPrecoMax = new MoedaTextField();
		txtPrecoMax.setPreferredSize(DFIELDP);
		txtPrecoMax.setMinimumSize(DFIELDP);

		panelPrecos = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelPrecos.add(txtPrecoMin);
		panelPrecos.add(new JLabel(" e "));
		panelPrecos.add(txtPrecoMax);
		panelPrecos.setMinimumSize(new Dimension(200, 22));
		panelPrecos.setBorder(BorderFactory.createEmptyBorder());
		panelPrecos.setVisible(false);
		
		lbStatus = new JLabel("Com o Status... ", JLabel.RIGHT);
		lbStatus.setPreferredSize(DLABEL);
		lbStatus.setMinimumSize(DLABEL);
		
		bgrStatus = new ButtonGroup();
		rdQualquer = new JRadioButton("Qualquer");
		rdAtivo = new JRadioButton(ListasComuns.STATUS_ITENS[0]);
		rdCancelado = new JRadioButton(ListasComuns.STATUS_ITENS[1]);
		
		
		bgrStatus.add(rdQualquer);
		bgrStatus.add(rdAtivo);
		bgrStatus.add(rdCancelado);
		
		bgrStatus.setSelected(rdQualquer.getModel(), true);
		
		
		lbTipo = new JLabel("Tipo Produto: ", JLabel.RIGHT);
		lbTipo.setPreferredSize(DLABEL);
		lbTipo.setMinimumSize(DLABEL);
		
		
		bgrTipo = new ButtonGroup();
		rdQualquerTipo = new JRadioButton("Qualquer");
		rdVenda = new JRadioButton(ProdutoVO.TIPO_PRODUTO_VENDA);
		rdConsumoInterno = new JRadioButton(ProdutoVO.TIPO_PRODUTO_CONSUMO_INTERNO);
		
		
		bgrTipo.add(rdQualquerTipo);
		bgrTipo.add(rdVenda);
		bgrTipo.add(rdConsumoInterno);
		
		bgrTipo.setSelected(rdQualquerTipo.getModel(), true);
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btEnviar = new JButton("Enviar", new ImageIcon("imagens/submit.gif"));
		btEnviar.addActionListener(new EventoBotaoControle());
		btEnviar.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infProd = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;

		c.gridy = 0; infProd.add(lbOpcoes, c);
		c.gridy = 1; infProd.add(lbNome, c);
		c.gridy = 2; infProd.add(lbPreco, c);
		c.gridy = 3; infProd.add(lbTipo, c);
		c.gridy = 4; infProd.add(lbStatus, c);
		
		JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelStatus.add(rdQualquer);
		panelStatus.add(rdAtivo);
		panelStatus.add(rdCancelado);
		
		panelStatus.setMinimumSize(new Dimension(200, 22));
		panelStatus.setBorder(BorderFactory.createEmptyBorder());
		
		JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelTipo.add(rdQualquerTipo);
		panelTipo.add(rdVenda);
		panelTipo.add(rdConsumoInterno);
		
		panelTipo.setMinimumSize(new Dimension(200, 22));
		panelTipo.setBorder(BorderFactory.createEmptyBorder());
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infProd.add(cbOpcoes, c);
		c.fill = GridBagConstraints.NONE;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 1; infProd.add(txtNome, c);
		c.gridy = 2; infProd.add(panelPrecos, c);
		c.weighty = 1;
		c.gridy = 3; infProd.add(panelTipo, c);
		c.gridy = 4; infProd.add(panelStatus, c);
		
		infProd.setBorder(BorderFactory.createTitledBorder("Parâmetros da consulta"));
		adicionarAtalhosComandos(infProd);
		
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btEnviar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(infProd, BorderLayout.CENTER);
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
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btEnviar.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}	
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ProdutoMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	
	private class EventoComboOpcoes implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if (e.getItem() == OPCOES_CONSULTA[QUALQUER]){
				lbNome.setVisible(false);
				lbPreco.setVisible(false);
				
				txtNome.setVisible(false);
				panelPrecos.setVisible(false);
			}
			else if (e.getItem() == OPCOES_CONSULTA[NOME]){
				lbNome.setVisible(true);
				lbPreco.setVisible(false);
				
				txtNome.setVisible(true);
				panelPrecos.setVisible(false);
			}
			else if (e.getItem() == OPCOES_CONSULTA[PRECO]){
				lbNome.setVisible(false);
				lbPreco.setVisible(true);
				
				txtNome.setVisible(false);
				panelPrecos.setVisible(true);
			}
			Component c = getPainelPrincipal();
			try{
				((ProdutoMainPanel)c).getIframeConsultaProduto().pack();
			}
			catch(ClassCastException cex){
				cex.printStackTrace();
			}
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ProdutoMainPanel c = (ProdutoMainPanel)getPainelPrincipal();
			if (e.getSource() == btCancelar){
				c.getIframeConsultaProduto().setVisible(false);
			}
			else if (e.getSource() == btEnviar){
				consultaProdutosAtivos();
			}
		}
	}
	
	private String getSelectedStatus(){
		if(rdAtivo.isSelected())return ListasComuns.STATUS_ITENS[0];
		else if(rdCancelado.isSelected())return ListasComuns.STATUS_ITENS[1];
		else return "Qualquer";
	}
	
	private String[] getParamentros(){
		List<String> p = new ArrayList<String>();
		String[] parametros = null;
		
		if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[NOME]))
			p.add(txtNome.getText());
		else if(cbOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[PRECO])){
			p.add(txtPrecoMin.getValor().toString());
			p.add(txtPrecoMax.getValor().toString());
		}
		else
			p = null;
						
		if(p != null){
			parametros = new String[p.size()];
			p.toArray(parametros);
		}
		return parametros;
	}
	
	private void consultaProdutosAtivos(){
		ProdutoMainPanel c = (ProdutoMainPanel)getPainelPrincipal();
		String tipo = cbOpcoes.getSelectedItem().toString();
		List <ProdutoVO> produtosEncontrados = Controller.getInstance().consultaProdutos(tipo, getParamentros(), getSelectedStatus());
		if (produtosEncontrados.size() > 0){
			c.carregarProdutosTable(produtosEncontrados);
			c.mostrarMensagem("Consulta efetuada com sucesso! " + (produtosEncontrados.size() == 1 ? " Apenas 1 produto encontrado ..." : produtosEncontrados.size() + " produtos encontrados ..."), BarraNotificacao.SUCESSO);
			c.getIframeConsultaProduto().setVisible(false);
		}
		else{
			c.carregarProdutosTable(null);
			c.mostrarMensagem("", BarraNotificacao.DEFAULT);
			barNotificacao.mostrarMensagem("Nenhum produto encontrado com esses critérios...", BarraNotificacao.ERRO);
		}
	}
}
