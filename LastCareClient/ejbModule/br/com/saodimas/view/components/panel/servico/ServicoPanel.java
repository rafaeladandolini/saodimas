package br.com.saodimas.view.components.panel.servico;

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
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.panel.plano.PlanoMainPanel;
import br.com.saodimas.view.components.text.MoedaTextField;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.validators.ValidaBasico;

@SuppressWarnings("serial")
public class ServicoPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	
	private ServicoVO servico;
	private BarraNotificacao barNotificacao;
	private JTextField txtNome;
	private JComboBox cbReferenciaValor;
	private MoedaTextField txtValor;
	private JButton btCancelar;
	private JButton btSalvar;

	private JLabel lbNome;
	private JLabel lbReferenciaValor;
	private JLabel lbValor;
	private JLabel lbStatus;
	private JComboBox cbStatus;

	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	private static final Dimension DFIELDP = new Dimension(75,22);

	public ServicoPanel() {
		initialize();
		configure();
	}

	public ServicoVO getServico() {
		return servico;
	} 

	private void pack()
	{
		Component c = getPainelPrincipal();
		((ServicoMainPanel)c).getIframeServico().pack();
	}
	
	public void setServico(ServicoVO servico) {
		this.servico = servico;
		if(servico != null){
			txtNome.setText(servico.getNome());
			txtValor.setValor(servico.getValor());
			cbReferenciaValor.setSelectedItem(servico.getReferenciaValor());
			cbStatus.setSelectedItem(servico.getStatus());
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
		lbStatus.setVisible(false);
		cbStatus.setVisible(false);
		pack();
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
		
		lbNome = new JLabel("Nome: *", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);

		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);


		lbValor = new JLabel("Valor (em R$): *", JLabel.RIGHT);
		lbValor.setPreferredSize(DLABEL);
		lbValor.setMinimumSize(DLABEL);

		txtValor = new MoedaTextField();
		txtValor.setPreferredSize(DFIELDP);
		txtValor.setMinimumSize(DFIELDP);

		lbReferenciaValor = new JLabel("Referêcia Valor: *", JLabel.RIGHT);
		lbReferenciaValor.setPreferredSize(DLABEL);
		lbReferenciaValor.setMinimumSize(DLABEL);

		cbReferenciaValor = new JComboBox(ListasComuns.REF_SERVICOS);
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
		JPanel infServico = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infServico.add(lbStatus, c);
		c.gridy = 1; infServico.add(lbNome, c);
		c.gridy = 2; infServico.add(lbValor, c);
		c.gridy = 3; infServico.add(lbReferenciaValor, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infServico.add(cbStatus, c);
		c.gridy = 1; infServico.add(txtNome, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 2; infServico.add(txtValor, c);
		c.weighty = 1;
		c.gridy = 3; infServico.add(cbReferenciaValor, c);

		infServico.setBorder(BorderFactory.createTitledBorder("Serviço"));
		adicionarAtalhosComandos(infServico);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);

		JPanel panelServico = new JPanel(new BorderLayout());
		panelServico.add(barNotificacao, BorderLayout.NORTH);
		panelServico.add(infServico, BorderLayout.CENTER);
		panelServico.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelServico);

		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		formPrincipal.add(panelServico);
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
			if (c.getClass() == ServicoMainPanel.class || c.getClass() == PlanoMainPanel.class || c.getClass() == ApoliceEditarMainPanel.class) return c;
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
					if (c.getClass() == ServicoMainPanel.class)
						((ServicoMainPanel)c).getIframeServico().setVisible(false);
					else if (c.getClass() == PlanoMainPanel.class)
						((PlanoMainPanel)c).getIframeServico().setVisible(false);
					else if (c.getClass() == ApoliceEditarMainPanel.class)
						((ApoliceEditarMainPanel)c).getIframeServico().setVisible(false);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			} 
			
			if (e.getSource() == btSalvar)
			{
				try{
					ValidaBasico.validaCamposServico(txtNome.getText(), txtValor.getText());
					Component c = getPainelPrincipal();
					ServicoVO novoServico = new ServicoVO();
					novoServico.setNome(txtNome.getText());
					novoServico.setReferenciaValor(cbReferenciaValor.getSelectedItem().toString());
					novoServico.setValor(txtValor.getValor());
					if(servico!= null)
					{
						novoServico.setId(servico.getId());
						novoServico.setStatus(cbStatus.getSelectedItem().toString());
						Controller.getInstance().updateServico(novoServico);
						barNotificacao.mostrarMensagem("Alterações efetuadas com sucesso.",	barNotificacao.INFO);
					}else{
						novoServico.setStatus(ListasComuns.STATUS_ITENS[0]);
						Controller.getInstance().insertServico(novoServico);
						barNotificacao.mostrarMensagem("Cadastro efetuado com sucesso.",	barNotificacao.INFO);
					}		
					
					if(c instanceof ServicoMainPanel){
						((ServicoMainPanel)c).carregarServicosTable();
						((ServicoMainPanel)c).getIframeServico().setVisible(false);
					}else
					if(c instanceof PlanoMainPanel){	
						((PlanoMainPanel)c).adicionaServico(novoServico);
						((PlanoMainPanel)c).getIframeServico().setVisible(false);
					}/*else
					if(c instanceof ApoliceEditarMainPanel){	
						((ApoliceEditarMainPanel)c).adicionaServico(novoServico);
						((ApoliceEditarMainPanel)c).getIframeServico().setVisible(false);
					}*/
				}catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(),	barNotificacao.ERRO);
					ex.printStackTrace();
				}			
			}
		}
	}
}	