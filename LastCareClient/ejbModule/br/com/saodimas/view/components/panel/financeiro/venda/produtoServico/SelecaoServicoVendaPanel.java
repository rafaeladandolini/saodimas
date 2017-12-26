package br.com.saodimas.view.components.panel.financeiro.venda.produtoServico;

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
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.financeiro.cliente.editar.ClienteEditarMainPanel;
import br.com.saodimas.view.components.table.renderer.SpinnerRenderer;
import br.com.saodimas.view.components.text.MoedaTextField;

@SuppressWarnings("serial")
public class SelecaoServicoVendaPanel extends JPanel {
	
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigat�rio";
	
	private ServicoVendaVO servicoObito;
	private BarraNotificacao barNotificacao;
	private JComboBox<ServicoVO> cbNomes;
	private MoedaTextField txtValorProduto;
	private JTextField txtReferencia;
	private MoedaTextField txtValorInformado;
	private SpinnerRenderer spinnerQuantidade;
	
	
	private JButton btCancelar;
	private JButton bAdicionar;

	private JLabel lbQuantidade;
	private JLabel lbNome;
	private JLabel lbReferenciaValor;
	private JLabel lbValorProduto;
	private JLabel lbValorInformado;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	private DecimalFormat formatter = new DecimalFormat("#0.00");

	public SelecaoServicoVendaPanel() {
		initialize();
		configure();
	}

	public ServicoVendaVO getServico() {
		return servicoObito;
	}

	public void setServico(ServicoVendaVO servicoObito) {
	
		this.servicoObito = servicoObito;
		if(servicoObito != null){
			ServicoVO prod = servicoObito.getServico();
			cbNomes.setSelectedItem(prod);
			txtReferencia.setText(prod.getReferenciaValor());
			txtValorProduto.setText(formatter.format(prod.getValor()));
			txtValorInformado.setText(formatter.format(servicoObito.getValor()));
			spinnerQuantidade.setValue(servicoObito.getQuantidade());			
		}else
		{
			this.carregarCompoServicos();
			txtValorInformado.setText("");
			spinnerQuantidade.setValue(1);
		}
	}
	
	
	public void recarregarTabela() {
		carregarCompoServicos();
	}
	
	private void carregarCompoServicos() {
		List<ServicoVO> list = Controller.getInstance().getServicoAtivos();
		cbNomes.removeAllItems();
		for(ServicoVO servico : list)
			cbNomes.addItem(servico);
	}

	public void focoPadrao(){
		cbNomes.requestFocus();
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		

		lbNome = new JLabel("Nome: *", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		
		cbNomes = new JComboBox<ServicoVO>();
		//cbNomes.setPreferredSize(DFIELDM);
		//cbNomes.setMinimumSize(new Dimension(200,22));
		cbNomes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ServicoVO pro = (ServicoVO)cbNomes.getSelectedItem();
				if(pro != null){
					
					txtReferencia.setText(pro.getReferenciaValor());
					txtValorInformado.setText(formatter.format(pro.getValor()));
					txtValorProduto.setText(formatter.format(pro.getValor()));
				}
					
			}
		});
		
		lbValorProduto = new JLabel("Valor: *", JLabel.RIGHT);
		lbValorProduto.setPreferredSize(DLABEL);
		lbValorProduto.setMinimumSize(DLABEL);
		
		txtValorProduto = new MoedaTextField();
		txtValorProduto.setEditable(false);
		txtValorProduto.setPreferredSize(DFIELDM);
		txtValorProduto.setMinimumSize(DFIELDM);
		
		lbReferenciaValor = new JLabel("Refer�ncia: *", JLabel.RIGHT);
		lbReferenciaValor.setPreferredSize(DLABEL);
		lbReferenciaValor.setMinimumSize(DLABEL);
		
		txtReferencia = new JTextField();
		txtReferencia.setEditable(false);
		txtReferencia.setPreferredSize(DFIELDM);
		txtReferencia.setMinimumSize(DFIELDM);
		
		lbQuantidade = new JLabel("Quantidade: *", JLabel.RIGHT);
		lbQuantidade.setPreferredSize(DLABEL);
		lbQuantidade.setMinimumSize(DLABEL);
		
		spinnerQuantidade = new SpinnerRenderer();
		spinnerQuantidade.setValue(1);
		
		lbValorInformado = new JLabel("Valor Venda: *", JLabel.RIGHT);
		lbValorInformado.setPreferredSize(DLABEL);
		lbValorInformado.setMinimumSize(DLABEL);
		
		txtValorInformado = new MoedaTextField();
		txtValorInformado.setPreferredSize(DFIELDM);
		txtValorInformado.setMinimumSize(DFIELDM);

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		bAdicionar = new JButton("Adicionar", new ImageIcon("imagens/save.gif"));
		bAdicionar.addActionListener(new EventoBotaoControle());
		bAdicionar.setHorizontalAlignment(JButton.LEFT);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infProduto = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infProduto.add(lbNome, c);
		c.gridy = 1; infProduto.add(lbValorProduto, c);
		c.gridy = 2; infProduto.add(lbReferenciaValor, c);
		c.gridy = 3; infProduto.add(lbQuantidade, c);
		c.gridy = 4; infProduto.add(lbValorInformado, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.NONE;
		c.gridy = 0; infProduto.add(cbNomes, c);
		c.gridy = 1; infProduto.add(txtValorProduto, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 2; infProduto.add(txtReferencia, c);
		c.weighty = 1;
		c.gridy = 3; infProduto.add(spinnerQuantidade, c);
		c.gridy = 4; infProduto.add(txtValorInformado, c);

		infProduto.setBorder(BorderFactory.createTitledBorder("Produto"));
		adicionarAtalhosComandos(infProduto);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(bAdicionar);
		controle.add(btCancelar);
		//controle.setMinimumSize(new Dimension(200, 22));
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
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) bAdicionar.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}

	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c.getClass() == ClienteEditarMainPanel.class) return c;
			c = c.getParent();
		}
		return c;
	}	

	private class EventoBotaoControle implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btCancelar) {
				Component c = getPainelPrincipal();
				try {
					if (c.getClass() == ClienteEditarMainPanel.class)
						((ClienteEditarMainPanel) c).getIframeVendaServico().setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (e.getSource() == bAdicionar) {
				Component c = getPainelPrincipal();
				ServicoVO servicoSelecioando = (ServicoVO) cbNomes.getSelectedItem();

				ServicoVendaVO servicoAdicionar = new ServicoVendaVO();
				servicoAdicionar.setId(servicoObito != null ? servicoObito.getId() : null);
				servicoAdicionar.setServico(servicoSelecioando);
				servicoAdicionar.setQuantidade((Integer) spinnerQuantidade.getValue());
				servicoAdicionar.setValor(txtValorInformado.getValor());
				servicoAdicionar.setTotal(servicoAdicionar.getQuantidade()* servicoAdicionar.getValor());

				try {
					if (c.getClass() == ClienteEditarMainPanel.class) {
						((ClienteEditarMainPanel) c).getIframeVenda().adicionarServico(servicoAdicionar);
						((ClienteEditarMainPanel) c).getIframeVendaServico().setVisible(false);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}
	}
}	