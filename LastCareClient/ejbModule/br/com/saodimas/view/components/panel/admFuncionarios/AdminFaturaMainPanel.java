package br.com.saodimas.view.components.panel.admFuncionarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.CustomLayeredPanel;
import br.com.saodimas.view.components.table.AdminFaturaTable;
import br.com.saodimas.view.components.table.model.AdminFaturaTableModel;

@SuppressWarnings("serial")
public class AdminFaturaMainPanel extends CustomLayeredPanel {
	public static final String FORM_NAME = "AdminFaturas"; 
	public static final String MENSAGEM_PADRAO = "   Resumo das faturas quitadas no dia atual pelo usuário logado no sistema!";

	private BarraNotificacao barNotificacao;
		
	private AdminFaturaTable tbFaturas;
	
	private JToolBar barControle;
	
	private JLabel labelTotal;
	
	public AdminFaturaMainPanel(){
		
		initialize();
		configure();
	}
	
	public void carregarFaturasDiaTable() {
		List<FaturaVO> lis = Controller.getInstance().consultaFaturas(SaoDimasMain.colaborador, null, null, new Date(), new Date(), new Date(), new Date()); 
		((AdminFaturaTableModel) tbFaturas.getModel())
				.loadData(lis);
		
		Double total = 0.0;
		DecimalFormat df = new DecimalFormat("#,###,###.00");
		df.setCurrency(Currency.getInstance(new Locale("pt","br")));
		for (FaturaVO faturaVO : lis) {
			total += faturaVO.getValor() - faturaVO.getValorDesconto() + faturaVO.getValorMulta() - faturaVO.getValorParceiro();
		}
		String totalCalculado = "R$ " + df.format(total);
		labelTotal.setText("Total:" + totalCalculado); 
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			carregarFaturasDiaTable();
		}
	}
	
	
	@Override
	public void setEnabled(boolean enabled) {
		tbFaturas.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	private void initialize() {
		tbFaturas = new AdminFaturaTable();
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		labelTotal = new JLabel("");
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(labelTotal);
		
	}

	private void configure() {
		
		GridBagConstraints c = new GridBagConstraints();
		JPanel infCidade = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infCidade.add(barControle, c);
		
		JScrollPane prodPanel = new JScrollPane(tbFaturas);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infCidade.add(prodPanel, c);
		
		infCidade.setBorder(BorderFactory.createTitledBorder("Listagem das faturas quitadas hoje!"));
		
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
		c.gridy = 1; panelPrincipal.add(infCidade, c);

		getPainelPrincipal().add(panelPrincipal, BorderLayout.CENTER);	
	}
	
}
