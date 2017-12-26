package br.com.saodimas.view.components.panel.apolice;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.panel.BarraNotificacao;

@SuppressWarnings("serial")
public class ApolicePanel extends JPanel {
	
	private static final int POS_INFO = 0;
	private static final int POS_TITULAR = 1;
	private static final int POS_CONTATO = 2;
	public static final String ACAO_VOLTAR = "Voltar";
	public static final String ACAO_AVANCAR = "Avançar";
	public static final String ACAO_SALVAR = "Salvar";
	public static final String ACAO_CANCELAR = "Cancelar";
	private static final String MENSAGEM_PADRAO = "(*) Preenchimento obrigatório";

	private ApoliceVO apolice;
	private BarraNotificacao barNotificacao;
	private JPanel layeredPanel;

	private ApoliceInfoPanel apolicePanel;
	private ApoliceEndContatoPanel contatoPanel;
	private ApoliceTitularPanel titularPanel;
	private ArrayList<String> listaEtapas;
	private String etapaAtual;

	public ApolicePanel() {
		initialize();
		configure();
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if(aFlag)
		{
			if(apolicePanel != null)
				apolicePanel.setVisible(aFlag);
		}
			
	}
	
	public BarraNotificacao getBarraNotificacao(){
		return barNotificacao;
	}

	public void limparCampos() {
		barNotificacao.escondeMensagem();
		apolicePanel.limparCampos();
		contatoPanel.limparCampos();
		titularPanel.limparCampos();
		etapaAtual = "";
		apolice = new ApoliceVO();
		apolicePanel.setApolice(apolice);
		contatoPanel.setApolice(apolice);
		titularPanel.setApolice(apolice);
		proxima();
	}

	public void focoPadrao(){
		layeredPanel.getComponent(0).requestFocus();
		this.apolicePanel.carregarNumContratoProximo();
	}

	private void initialize() {
		apolice = new ApoliceVO();

		listaEtapas = new ArrayList<String>();
		listaEtapas.add(ApoliceInfoPanel.FORM_NAME);
		listaEtapas.add(ApoliceTitularPanel.FORM_NAME);
		listaEtapas.add(ApoliceEndContatoPanel.FORM_NAME);
		listaEtapas.set(POS_INFO, ApoliceInfoPanel.FORM_NAME);
		listaEtapas.set(POS_TITULAR, ApoliceTitularPanel.FORM_NAME);
		listaEtapas.set(POS_CONTATO, ApoliceEndContatoPanel.FORM_NAME);
	
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);

		EventoBotaoControle ev = new EventoBotaoControle();
		layeredPanel = new JPanel(new CardLayout(0, 0));
		apolicePanel = new ApoliceInfoPanel(ev, POS_INFO + 1, listaEtapas.size(), barNotificacao);
		contatoPanel = new ApoliceEndContatoPanel(ev, POS_CONTATO + 1, listaEtapas.size(), barNotificacao);
		titularPanel = new ApoliceTitularPanel(ev, POS_TITULAR + 1, listaEtapas.size(), barNotificacao);
	
	}

	private void configure() {
		apolicePanel.setApolice(apolice);
		contatoPanel.setApolice(apolice);
		titularPanel.setApolice(apolice);
		
		adicionarAtalhosComandos(apolicePanel);
		adicionarAtalhosComandos(contatoPanel);
		adicionarAtalhosComandos(titularPanel);
		
		layeredPanel.add(apolicePanel, ApoliceInfoPanel.FORM_NAME);
		layeredPanel.add(contatoPanel, ApoliceEndContatoPanel.FORM_NAME);
		layeredPanel.add(titularPanel, ApoliceTitularPanel.FORM_NAME);

		layeredPanel.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) sair();
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) salvar();
				else super.keyPressed(e);
			}
		});

		JPanel pApolice = new JPanel(new BorderLayout());
		pApolice.add(barNotificacao, BorderLayout.NORTH);
		pApolice.add(layeredPanel, BorderLayout.CENTER);
		adicionarAtalhosComandos(pApolice);

		setLayout(new BorderLayout());
		add(pApolice, BorderLayout.CENTER);
		adicionarAtalhosComandos(this);
		etapaAtual = "";
		proxima();
	}

	private void sair(){
		Component c = getPainelPrincipal();
		try{
			((ApoliceMainPanel)c).getIframeApolice().setVisible(false);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		limparCampos();
	}

	private void salvar() {
		final Component c = getPainelPrincipal();
		try {
			apolice.setStatus("Ativo");
			
			/*AssociadoVO titular = apolice.getTitular();
			AssociadoVO dependenteTitular = new AssociadoVO();
			dependenteTitular.setNome(titular.getNome());
			dependenteTitular.setCPF(titular.getCPF());
			dependenteTitular.setDataNascimento(titular.getDataNascimento());
			dependenteTitular.setSexo(titular.getSexo());
			
			apolice.getDependentes().add(dependenteTitular);*/			
			
			apolice.setColaborador(SaoDimasMain.colaborador);
		
			((ApoliceMainPanel) c).salvarApolice(apolice);
			((ApoliceMainPanel) c).getIframeApolice().setVisible(false);
			((ApoliceMainPanel) c)
					.mostrarMensagem("Apólice registrada com sucesso!",
							BarraNotificacao.SUCESSO);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			barNotificacao.mostrarMensagem(ex.getMessage(),
					BarraNotificacao.ERRO);
		}

		limparCampos();
		etapaAtual = "";
		proxima();

	}

	private void proxima(){
		int nextIndex = (listaEtapas.indexOf(etapaAtual) + 1) % listaEtapas.size();
		etapaAtual = listaEtapas.get(nextIndex);
		CardLayout card = (CardLayout)layeredPanel.getLayout();
		card.show(layeredPanel, etapaAtual);
	}

	private void voltar(){
		int previousIndex = ((listaEtapas.indexOf(etapaAtual) - 1) < 0) ? 0 : listaEtapas.indexOf(etapaAtual) - 1;
		etapaAtual = listaEtapas.get(previousIndex);
		CardLayout card = (CardLayout)layeredPanel.getLayout();
		card.show(layeredPanel, etapaAtual);
	}

	private void adicionarAtalhosComandos(JPanel panel){
		for (Component c : panel.getComponents()) {
			c.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) sair();
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) salvar();
					else super.keyPressed(e);
				}
			});
		}
	}

	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().compareTo(ACAO_CANCELAR) == 0) sair();
			else if (e.getActionCommand().compareTo(ACAO_VOLTAR) == 0) voltar();
			else if (e.getActionCommand().compareTo(ACAO_AVANCAR) == 0) proxima();
			else if (e.getActionCommand().compareTo(ACAO_SALVAR) == 0) salvar();
		}
	}
	
}	