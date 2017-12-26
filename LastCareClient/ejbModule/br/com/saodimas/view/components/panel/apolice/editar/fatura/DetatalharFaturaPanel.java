package br.com.saodimas.view.components.panel.apolice.editar.fatura;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.components.text.MoedaTextField;

@SuppressWarnings("serial")
public class DetatalharFaturaPanel extends JPanel{
	public static final String FORM_NAME = "Detalhar Fatura"; 
	private static final String MENSAGEM_PADRAO = "Detalhe Fatura Selecionada";
	
	@SuppressWarnings("unused")
	private FaturaVO fatura;
	
	private BarraNotificacao barNotificacao;
	private JTextField txtParceiro;
	private JTextField txtColaborador;
	private DataTextField txtDataEmissao;
	private DataTextField txtDataVencimento;
	private DataTextField txtDataPagamento;
	private JTextField txtValorFaturas;
	private JTextField txtValorMulta;
	private JTextField txtValorDesconto;
	private JTextField txtValorParceiro;
	private JTextField txtValorTotal;

	private JButton btConfirmar;

	private JLabel lbParceiro;
	private JLabel lbColaborador;
	private JLabel lbDataEmissao;
	private JLabel lbDataVencimento;
	private JLabel lbDataPagamento;
	private JLabel lbValorFatura;
	private JLabel lbValorMulta;
	private JLabel lbValorParceiro;
	private JLabel lbValorDesconto;
	private JLabel lbValorTotal;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	private static final Dimension DFIELDP = new Dimension(75,22);


	public DetatalharFaturaPanel() {
		initialize();
		configure();
	}

	public void setFatura(FaturaVO fatura)
	{
		this.fatura = fatura;
		SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
		txtColaborador.setText(fatura.getColaborador() != null ? fatura.getColaborador().getNome() : "");
		txtDataEmissao.setText(sformat.format(fatura.getDataEmissao()));
		txtDataPagamento.setText( fatura.getDataPagamento() != null ? sformat.format(fatura.getDataPagamento()) : "");
		txtDataVencimento.setText(sformat.format(fatura.getDataVencimento()));
		txtParceiro.setText(fatura.getParceiro() != null ? fatura.getParceiro().getDescricao() : "");
		txtValorDesconto.setText(this.formataPreco(fatura.getValorDesconto()));
		txtValorFaturas.setText(this.formataPreco(fatura.getValor()));
		txtValorMulta.setText(this.formataPreco(fatura.getValorMulta()));
		txtValorParceiro.setText(this.formataPreco(fatura.getValorParceiro()));
		txtValorTotal.setText(this.formataPreco(fatura.getValor() + fatura.getValorMulta() - fatura.getValorParceiro() - fatura.getValorDesconto()));
	}
	
    private String formataPreco(double valor)
    {
    	DecimalFormat df = new DecimalFormat("#,###,###.00");
		df.setCurrency(Currency.getInstance(new Locale("pt","br")));
		String preco = "R$ " + df.format(valor);
		return preco;
    }
	

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbParceiro = new JLabel("Parceiro:  ", JLabel.RIGHT);
		lbParceiro.setPreferredSize(DLABEL);
		lbParceiro.setMinimumSize(DLABEL);
		
		txtParceiro = new JTextField();
		txtParceiro.setPreferredSize(DFIELDP);
		txtParceiro.setMinimumSize(DFIELDP);
		txtParceiro.setEditable(false);
		
		lbColaborador = new JLabel("Colaborador:  ", JLabel.RIGHT);
		lbColaborador.setPreferredSize(DLABEL);
		lbColaborador.setMinimumSize(DLABEL);
		
		txtColaborador = new JTextField();
		txtColaborador.setPreferredSize(DFIELDP);
		txtColaborador.setMinimumSize(DFIELDP);
		txtColaborador.setEditable(false);
		
		lbDataEmissao = new JLabel("Data Emissão:  ", JLabel.RIGHT);
		lbDataEmissao.setPreferredSize(DLABEL);
		lbDataEmissao.setMinimumSize(DLABEL);
		
		txtDataEmissao = new DataTextField();
		txtDataEmissao.setPreferredSize(DFIELDM);
		txtDataEmissao.setMinimumSize(DFIELDM);
		txtDataEmissao.setEditable(false);
		txtDataEmissao.setHorizontalAlignment(JTextField.RIGHT);
		
		lbDataVencimento = new JLabel("Data Vencimento:  ", JLabel.RIGHT);
		lbDataVencimento.setPreferredSize(DLABEL);
		lbDataVencimento.setMinimumSize(DLABEL);
		
		txtDataVencimento = new DataTextField();
		txtDataVencimento.setPreferredSize(DFIELDM);
		txtDataVencimento.setMinimumSize(DFIELDM);
		txtDataVencimento.setEditable(false);
		txtDataVencimento.setHorizontalAlignment(JTextField.RIGHT);
		
		String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		lbDataPagamento = new JLabel("Data Pagamento:  ", JLabel.RIGHT);
		lbDataPagamento.setPreferredSize(DLABEL);
		lbDataPagamento.setMinimumSize(DLABEL);

		txtDataPagamento = new DataTextField();
		txtDataPagamento.setText(today);
		txtDataPagamento.setPreferredSize(DFIELDM);
		txtDataPagamento.setMinimumSize(DFIELDM);
		txtDataPagamento.setEditable(false);
		txtDataPagamento.setHorizontalAlignment(JTextField.RIGHT);

		lbValorFatura = new JLabel("Valor (R$):  ", JLabel.RIGHT);
		lbValorFatura.setPreferredSize(DLABEL);
		lbValorFatura.setMinimumSize(DLABEL);

		txtValorFaturas = new MoedaTextField();
		txtValorFaturas.setPreferredSize(DFIELDP);
		txtValorFaturas.setMinimumSize(DFIELDP);
		txtValorFaturas.setEditable(false);

		lbValorMulta = new JLabel("Multa (R$):  ", JLabel.RIGHT);
		lbValorMulta.setPreferredSize(DLABEL);
		lbValorMulta.setMinimumSize(DLABEL);

		txtValorMulta = new MoedaTextField();
		txtValorMulta.setPreferredSize(DFIELDP);
		txtValorMulta.setMinimumSize(DFIELDP);
		txtValorMulta.setEditable(false);
		
		lbValorDesconto = new JLabel("Desconto (R$):  ", JLabel.RIGHT);
		lbValorDesconto.setPreferredSize(DLABEL);
		lbValorDesconto.setMinimumSize(DLABEL);
		
		txtValorDesconto = new MoedaTextField();
		txtValorDesconto.setPreferredSize(DFIELDP);
		txtValorDesconto.setMinimumSize(DFIELDP);
		txtValorDesconto.setEditable(false);
		
		
		lbValorParceiro = new JLabel("Parceiro (R$):  ", JLabel.RIGHT);
		lbValorParceiro.setPreferredSize(DLABEL);
		lbValorParceiro.setMinimumSize(DLABEL);
		
		txtValorParceiro = new MoedaTextField();
		txtValorParceiro.setPreferredSize(DFIELDP);
		txtValorParceiro.setMinimumSize(DFIELDP);
		txtValorParceiro.setEditable(false);
		
		lbValorTotal = new JLabel("Total (R$):  ", JLabel.RIGHT);
		lbValorTotal.setPreferredSize(DLABEL);
		lbValorTotal.setMinimumSize(DLABEL);

		txtValorTotal = new MoedaTextField();
		txtValorTotal.setPreferredSize(DFIELDP);
		txtValorTotal.setMinimumSize(DFIELDP);
		txtValorTotal.setEditable(false);

		btConfirmar = new JButton("Ok", new ImageIcon("imagens/accept.gif"));
		btConfirmar.addActionListener(new EventoBotaoControle());
		btConfirmar.setHorizontalAlignment(JButton.LEFT);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infFatura = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 0; infFatura.add(lbDataEmissao, c);
		c.gridy = 1; infFatura.add(lbDataVencimento, c);
		c.gridy = 2; infFatura.add(lbDataPagamento, c);
		c.gridy = 3; infFatura.add(lbValorFatura, c);
		c.gridy = 4; infFatura.add(lbValorMulta, c);
		c.gridy = 5; infFatura.add(lbValorDesconto, c);
		c.gridy = 6; infFatura.add(lbValorParceiro, c);
		c.gridy = 7; infFatura.add(lbValorTotal, c);
		c.gridy = 8; infFatura.add(lbColaborador, c);
		c.gridy = 9; infFatura.add(lbParceiro, c);
		c.weighty = 1;
		c.gridwidth = 2;
		c.gridy = 10; infFatura.add(new JLabel(""), c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infFatura.add(txtDataEmissao, c);
		c.gridy = 1; infFatura.add(txtDataVencimento, c);
		c.gridy = 2; infFatura.add(txtDataPagamento, c);
		c.gridy = 3; infFatura.add(txtValorFaturas, c);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 7; infFatura.add(txtValorTotal, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 4; infFatura.add(txtValorMulta, c);
		c.gridy = 5; infFatura.add(txtValorDesconto, c);
		c.gridy = 6; infFatura.add(txtValorParceiro, c);
		c.gridy = 8; infFatura.add(txtColaborador, c);
		c.gridy = 9; infFatura.add(txtParceiro, c);

		infFatura.setBorder(BorderFactory.createTitledBorder("Fatura"));

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btConfirmar);
		controle.setMinimumSize(new Dimension(200, 22));

		JPanel panelFatura = new JPanel(new BorderLayout());
		panelFatura.add(infFatura, BorderLayout.CENTER);
		panelFatura.add(controle, BorderLayout.SOUTH);

		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelFatura.add(barNotificacao, BorderLayout.NORTH);
		formPrincipal.add(panelFatura);

		setLayout(new BorderLayout());
		add(formPrincipal, BorderLayout.CENTER);

	}
	
	private ApoliceEditarMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceEditarMainPanel) return (ApoliceEditarMainPanel)c;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel)c;
	}	

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btConfirmar){
				if (c != null){
						c.getIframeDetalharFatura().setVisible(false);
				}
			}
			
	}
	}
}

