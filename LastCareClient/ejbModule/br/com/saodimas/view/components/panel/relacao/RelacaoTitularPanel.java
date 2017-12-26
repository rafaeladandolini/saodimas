package br.com.saodimas.view.components.panel.relacao;

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
import br.com.saodimas.model.beans.RelacaoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.validators.ValidaBasico;

@SuppressWarnings("serial")
public class RelacaoTitularPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	
	private RelacaoVO relacaoTitular;
	private BarraNotificacao barNotificacao;
	private JTextField txtRelacaoTitular;
	private JComboBox cbTipo;
	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbRelacaoTitular;
	private JLabel lbTipo;
	private JLabel lbStatus;
	private JComboBox cbStatus;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public RelacaoTitularPanel() {
		initialize();
		configure();
	}

	public RelacaoVO getRelacaoTitular() {
		return relacaoTitular;
	}

	private void pack()
	{
		Component c = getPainelPrincipal();
		((RelacaoTitularMainPanel)c).getIframeRelacaoTitular().pack();
	}
	
	public void setRelacaoTitular(RelacaoVO relacaoTitular) {
		this.relacaoTitular = relacaoTitular;
		if(relacaoTitular != null){
			txtRelacaoTitular.setText(relacaoTitular.getDescricao());
			cbTipo.setSelectedItem(relacaoTitular.getTipoRelacao());
			cbStatus.setSelectedItem(relacaoTitular.getStatus());
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
		txtRelacaoTitular.setText("");
		cbTipo.setSelectedIndex(0);
		lbStatus.setVisible(false);
		cbStatus.setVisible(false);
		pack();
	}
	
	public void focoPadrao(){
		txtRelacaoTitular.requestFocus();
		
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
		
		lbRelacaoTitular = new JLabel("Relação: *", JLabel.RIGHT);
		lbRelacaoTitular.setPreferredSize(DLABEL);
		lbRelacaoTitular.setMinimumSize(DLABEL);
		
		CustomDocument nomeDoc = new CustomDocument(50);
		txtRelacaoTitular = new JTextField();
		txtRelacaoTitular.setDocument(nomeDoc);
		txtRelacaoTitular.setPreferredSize(DFIELDM);
		
		lbTipo = new JLabel("Tipo: *", JLabel.RIGHT);
		lbTipo.setPreferredSize(DLABEL);
		lbTipo.setMinimumSize(DLABEL);
		
		cbTipo= new JComboBox(new String[]{"PF", "PJ"});
		cbTipo.setSelectedItem("PF");
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infRelacaoTitular = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infRelacaoTitular.add(lbStatus, c);
		c.gridy = 1; infRelacaoTitular.add(lbRelacaoTitular, c);
		c.gridy = 2; infRelacaoTitular.add(lbTipo, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infRelacaoTitular.add(cbStatus, c);
		c.gridy = 1; infRelacaoTitular.add(txtRelacaoTitular, c);
		c.fill = GridBagConstraints.NONE;
		c.weighty = 1;
		c.gridy = 2; infRelacaoTitular.add(cbTipo, c);
		
		infRelacaoTitular.setBorder(BorderFactory.createTitledBorder("RelacaoTitular"));
		adicionarAtalhosComandos(infRelacaoTitular);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		JPanel panelRelacaoTitular = new JPanel(new BorderLayout()); 
		panelRelacaoTitular.add(infRelacaoTitular, BorderLayout.CENTER);
		panelRelacaoTitular.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelRelacaoTitular);
		
		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(panelRelacaoTitular, BorderLayout.CENTER);
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
			if (c instanceof RelacaoTitularMainPanel) return c;
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
					((RelacaoTitularMainPanel)c).getIframeRelacaoTitular().setVisible(false);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			} 
			
			if (e.getSource() == btSalvar)
			{
				
				try {
					ValidaBasico.validaCamposRelacao(txtRelacaoTitular.getText());
				
					Component co = getPainelPrincipal();
					RelacaoVO novaRelacao = new RelacaoVO();
					novaRelacao.setDescricao(txtRelacaoTitular.getText());
					novaRelacao.setTipoRelacao(cbTipo.getSelectedItem().toString());
					if(relacaoTitular != null){
						novaRelacao.setId(relacaoTitular.getId());
						novaRelacao.setStatus(cbStatus.getSelectedItem().toString());
						Controller.getInstance().updateRelacao(novaRelacao);
						barNotificacao.mostrarMensagem("Alterações efetuadas com sucesso.",	barNotificacao.INFO);
					}else{
						novaRelacao.setStatus(ListasComuns.STATUS_ITENS[0]);
						Controller.getInstance().insertRelacao(novaRelacao);
						barNotificacao.mostrarMensagem("Cadastro efetuado com sucesso.",	barNotificacao.INFO);
					}
					
					((RelacaoTitularMainPanel)co).carregarRelacoesTable();
					((RelacaoTitularMainPanel)co).getIframeRelacaoTitular().setVisible(false);

					limparCampos();
				} catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(),	barNotificacao.ERRO);
					ex.printStackTrace();
				}
				
			}
		}
	}
}	