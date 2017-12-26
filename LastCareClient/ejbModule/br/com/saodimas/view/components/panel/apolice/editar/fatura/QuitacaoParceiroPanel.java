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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.model.beans.ParceiroVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.model.FaturaTableModel;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.components.text.MoedaTextField;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class QuitacaoParceiroPanel extends JPanel{
	public static final String FORM_NAME = "Quitar Faturas Parceiros"; 
	private static final String MENSAGEM_PADRAO = "Faturas selecionadas: ";

	private BarraNotificacao barNotificacao;
	private JComboBox cbParceiros;
	private DataTextField txtDataPagamento;
	private MoedaTextField txtValorFaturas;
	private MoedaTextField txtValorMulta;
	private MoedaTextField txtValorDesconto;
	private MoedaTextField txtValorParceiro;
	private MoedaTextField txtValorTotal;

	private JButton btCancelar;
	private JButton btConfirmar;

	private JLabel lbParceiro;
	private JLabel lbDataPagamento;
	private JLabel lbValorFatura;
	private JLabel lbValorMulta;
	private JLabel lbValorParceiro;
	private JLabel lbValorDesconto;
	private JLabel lbValorTotal;
	private JLabel lbMultMulta;
	private JLabel lbMultDesconto;
	private JLabel lbMultParceiro;
	
	private int numFaturas;
	private FaturaTableModel faturasTableModel;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	private static final Dimension DFIELDP = new Dimension(75,22);
	
	private ApoliceVO apolice;

	public QuitacaoParceiroPanel() {
		initialize();
		configure();
	}

	public void setApolice(ApoliceVO apoliceVO) {
		this.apolice = apoliceVO;
	}
	
	public void setFaturasTableModel(FaturaTableModel faturasTableModel) {
		this.faturasTableModel = faturasTableModel;
	}
	
	public void recalcular(){
		numFaturas = 0;
		double total = 0d;
		int numLinhas = faturasTableModel.getRowCount();

		for (int i = 0; i < numLinhas; i++){
			numFaturas += ((Boolean)faturasTableModel.getValueAt(i, FaturaTableModel.SELECAO)) ? 1 : 0;
			total += ((Boolean)faturasTableModel.getValueAt(i, FaturaTableModel.SELECAO)) ? ((FaturaVO)faturasTableModel.getValueAt(i, FaturaTableModel.FATURA)).getValor() : 0;
		}
		
		txtValorFaturas.setValor(total);
		txtValorTotal.setValor(total + (txtValorMulta.getValor() * numFaturas) - (txtValorDesconto.getValor() * numFaturas) - (txtValorParceiro.getValor() * numFaturas));
		lbMultDesconto.setText(numFaturas + "x ");
		lbMultMulta.setText(numFaturas + "x ");
		lbMultParceiro.setText(numFaturas + "x ");
		//.setText(numFaturas + "x ");
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO + numFaturas, BarraNotificacao.DEFAULT);
	}

	public void limparCampos() {
		txtDataPagamento.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		txtValorFaturas.setValor(0d);
		txtValorMulta.setValor(0d);
		txtValorParceiro.setValor(0d);
		txtValorDesconto.setValor(0d);
		txtValorTotal.setValor(0d);
	}

	public void focoPadrao(){
		btConfirmar.requestFocus();
		ColaboradorVO vo = SaoDimasMain.colaborador;
		if(vo != null && vo.getTipoColaborador().equals(ListasComuns.ADMINISTRADOR))
			txtDataPagamento.setEditable(true);
		else
			txtDataPagamento.setEditable(false);
		
		this.carregarParceiros();
				
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO + numFaturas);
		
		cbParceiros = new JComboBox();
		
		lbParceiro = new JLabel("Parceiro:  ", JLabel.RIGHT);
		lbParceiro.setPreferredSize(DLABEL);
		lbParceiro.setMinimumSize(DLABEL);
		
		String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		lbDataPagamento = new JLabel("Data:  ", JLabel.RIGHT);
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
		txtValorMulta.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				recalcular();
				super.keyReleased(e);
			}
		});
		
		lbMultMulta = new JLabel("", JLabel.LEFT);

		lbValorDesconto = new JLabel("Desconto (R$):  ", JLabel.RIGHT);
		lbValorDesconto.setPreferredSize(DLABEL);
		lbValorDesconto.setMinimumSize(DLABEL);
		
				txtValorDesconto = new MoedaTextField();
		txtValorDesconto.setPreferredSize(DFIELDP);
		txtValorDesconto.setMinimumSize(DFIELDP);
		txtValorDesconto.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				recalcular();
				super.keyReleased(e);
			}
		});
		
		lbValorParceiro = new JLabel("Parceiro (R$):  ", JLabel.RIGHT);
		lbValorParceiro.setPreferredSize(DLABEL);
		lbValorParceiro.setMinimumSize(DLABEL);
		
		txtValorParceiro = new MoedaTextField();
		txtValorParceiro.setPreferredSize(DFIELDP);
		txtValorParceiro.setMinimumSize(DFIELDP);
		txtValorParceiro.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				recalcular();
				super.keyReleased(e);
			}
		});
		
		lbMultParceiro = new JLabel("", JLabel.LEFT);

		lbMultDesconto = new JLabel("", JLabel.LEFT);

		lbValorTotal = new JLabel("Total (R$):  ", JLabel.RIGHT);
		lbValorTotal.setPreferredSize(DLABEL);
		lbValorTotal.setMinimumSize(DLABEL);

		txtValorTotal = new MoedaTextField();
		txtValorTotal.setPreferredSize(DFIELDP);
		txtValorTotal.setMinimumSize(DFIELDP);
		txtValorTotal.setEditable(false);

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		btConfirmar = new JButton("Confirmar Pgto.", new ImageIcon("imagens/accept.gif"));
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
		c.gridy = 0; infFatura.add(lbParceiro, c);
		c.gridy = 1; infFatura.add(lbDataPagamento, c);
		c.gridy = 2; infFatura.add(lbValorFatura, c);
		c.gridy = 3; infFatura.add(lbValorMulta, c);
		c.gridy = 4; infFatura.add(lbValorDesconto, c);
		c.gridy = 5; infFatura.add(lbValorParceiro, c);
		c.gridy = 6; infFatura.add(lbValorTotal, c);
		c.weighty = 1;
		c.gridwidth = 2;
		c.gridy = 7; infFatura.add(new JLabel(""), c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infFatura.add(cbParceiros, c);
		c.gridy = 1; infFatura.add(txtDataPagamento, c);
		c.gridy = 2; infFatura.add(txtValorFaturas, c);
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.gridy = 3; infFatura.add(lbMultMulta, c);
		c.gridy = 4; infFatura.add(lbMultDesconto, c);
		c.gridy = 5; infFatura.add(lbMultParceiro, c);
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 1;
		c.gridy = 6; infFatura.add(txtValorTotal, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 3; infFatura.add(txtValorMulta, c);
		c.gridy = 4; infFatura.add(txtValorDesconto, c);
		c.gridy = 5; infFatura.add(txtValorParceiro, c);

		infFatura.setBorder(BorderFactory.createTitledBorder("Fatura"));
		adicionarAtalhosComandos(infFatura);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btConfirmar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);

		JPanel panelFatura = new JPanel(new BorderLayout());
		panelFatura.add(infFatura, BorderLayout.CENTER);
		panelFatura.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelFatura);

		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelFatura.add(barNotificacao, BorderLayout.NORTH);
		formPrincipal.add(panelFatura);
		adicionarAtalhosComandos(formPrincipal);

		setLayout(new BorderLayout());
		add(formPrincipal, BorderLayout.CENTER);
		adicionarAtalhosComandos(this);

	}
	
	private void carregarParceiros()
	{
		List<ParceiroVO> listPar = Controller.getInstance().getParceirosAllAtivos();
		cbParceiros.removeAllItems();
		for (ParceiroVO parceiroVO : listPar) {
			cbParceiros.addItem(parceiroVO);
		}
	}

	private void adicionarAtalhosComandos(JPanel panel){
		for (Component c : panel.getComponents()) {
			c.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btCancelar.doClick();
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btConfirmar.doClick();
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

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				if (c != null){
					limparCampos();
					c.getIframeQuitarParceiro().setVisible(false);
				}
			}
			else if (e.getSource() == btConfirmar){
				
				try{
					boolean camposValidos = true;
					for (Object item : faturasTableModel.getDataVector()) {
						FaturaVO f = (FaturaVO)item;
						if(f.isSelecionada()){
							f.setApolice(apolice);
							SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
							try {
								Date dataPagamento = sformat.parse(txtDataPagamento.getText());
								if(dataPagamento.after(new Date())){
									camposValidos = false;
									barNotificacao.mostrarMensagem("Data de pagamento n�o pode ser uma data futura.", BarraNotificacao.ERRO);
								}else
								{
									f.setDataPagamento(dataPagamento);
									f.setSelecionada(false);
									f.setColaborador(SaoDimasMain.colaborador);
									f.setStatus("Pago");
									f.setParceiro((ParceiroVO)cbParceiros.getSelectedItem());
									f.setValorDesconto((txtValorDesconto.getValor()));
									f.setValorMulta((txtValorMulta.getValor() ));
									f.setValorParceiro((txtValorParceiro.getValor()));
									Calendar cV = Calendar.getInstance();
									f.setDataBaixa(new Date(cV.getTimeInMillis()));
									Controller.getInstance().updateFatura(f);
									c.mostrarMensagemFatura("Baixa da fatura efetuada com sucesso.", BarraNotificacao.SUCESSO);
								}
							} catch (ParseException e1) {
								camposValidos = false;
								barNotificacao.mostrarMensagem("Informe uma data de pagamento v�lida.", BarraNotificacao.ERRO);
							}
							
						}
					}
					if(camposValidos){
						faturasTableModel.fireTableDataChanged();
						if (c != null){
							c.getIframeQuitarParceiro().setVisible(false);
						}
					}
				}
			catch(MensagemSaoDimasException ex)
			{
				ex.printStackTrace();
				barNotificacao.mostrarMensagem(ex.getMessage(), BarraNotificacao.ERRO);
			}
		}
	}
	}
}

