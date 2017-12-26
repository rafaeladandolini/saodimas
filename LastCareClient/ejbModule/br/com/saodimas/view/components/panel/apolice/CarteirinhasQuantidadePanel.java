package br.com.saodimas.view.components.panel.apolice;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.relatorio.GeradorRelatorio;
import br.com.saodimas.relatorio.NomeRelatorio;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;

@SuppressWarnings("serial")
public class CarteirinhasQuantidadePanel extends JPanel {
	private static final String MENSAGEM_PADRAO = " ";
	
	private ApoliceVO apolice;
	private BarraNotificacao barNotificacao;
	private JSpinner spnnerQtde;
	private JButton btCancelar;
	private JButton btOk;

	private JLabel lbQuantidade;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);

	public CarteirinhasQuantidadePanel() {
		initialize();
		configure();
	}

	public void limparCampos() {
		spnnerQtde.setValue(new Integer(1));
		barNotificacao.escondeMensagem();
	}
	
	public void setApolice(ApoliceVO apolice)
	{
		this.apolice = apolice;
	}
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);

		lbQuantidade = new JLabel("Quantidade: ", JLabel.RIGHT);
		lbQuantidade.setPreferredSize(DLABEL);
		lbQuantidade.setMinimumSize(DLABEL);

		spnnerQtde = new JSpinner();
		spnnerQtde.setValue(new Integer(1));
		spnnerQtde.setPreferredSize(DFIELDM);
		spnnerQtde.setMinimumSize(DFIELDM);
		spnnerQtde.setVisible(true);
				
				
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		btOk = new JButton("Ok", new ImageIcon("imagens/imprimir.gif"));
		btOk.addActionListener(new EventoBotaoControle());
		btOk.setHorizontalAlignment(JButton.LEFT);
	}

	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c.getClass() == ApoliceEditarMainPanel.class) return c;
			c = c.getParent();
		}

		return c;
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infApolice = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;

		c.gridy = 0; infApolice.add(lbQuantidade, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infApolice.add(spnnerQtde, c);
		

		infApolice.setBorder(BorderFactory.createTitledBorder("Parāmetros da consulta"));
		adicionarAtalhosComandos(infApolice);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btOk);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);

		JPanel panelApolice = new JPanel(new BorderLayout());
		panelApolice.add(barNotificacao, BorderLayout.NORTH);
		panelApolice.add(infApolice, BorderLayout.CENTER);
		panelApolice.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelApolice);

		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		formPrincipal.add(panelApolice);
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
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btOk.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}


	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = (ApoliceEditarMainPanel)getPainelPrincipal();		
			if (e.getSource() == btCancelar)
			{
				c.getIframeCarteirinhaQuantidade().setVisible(false);
			}
			else if (e.getSource() == btOk)
			{
				int qnt = ((Integer)spnnerQtde.getValue()).intValue();
				List<ApoliceVO> list = new ArrayList<ApoliceVO>();
				for(int i =0; i < qnt ;i++)
					list.add(apolice); 
				
					new GeradorRelatorio().gerarCarteirinhasDependentes(NomeRelatorio.CARTEIRINHAS, list);
				c.getIframeCarteirinhaQuantidade().setVisible(false);	
			}

		}
	}
}
