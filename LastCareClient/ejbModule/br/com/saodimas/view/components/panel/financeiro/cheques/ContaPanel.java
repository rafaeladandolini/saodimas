package br.com.saodimas.view.components.panel.financeiro.cheques;

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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.BancoVO;
import br.com.saodimas.model.beans.ContaVO;
import br.com.saodimas.model.beans.TipoContaVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;

@SuppressWarnings("serial")
public class ContaPanel extends JPanel {
	
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	
	private ContaVO conta;
	private BarraNotificacao barNotificacao;
		
	private JComboBox<BancoVO> cbBanco;
	private JTextField txtAgencia;
	private JTextField txtNumeroConta;
	private JComboBox<TipoContaVO> cbTipoConta;
	private JTextField txtNomeConta;
	
	private JLabel lbBanco;
	private JLabel lbAgencia;
	private JLabel lbNumeroConta;
	private JLabel lbTipoConta;
	private JLabel lbNomeConta;
	
	private JButton btCancelar;
	private JButton btSalvar;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public ContaPanel() {
		initialize();
		configure();
	}

	
	public void setConta(ContaVO conta) {
		this.conta = conta;
		this.carregarBancos();
		this.carregarTipoContas();
		if(conta !=null){
			txtAgencia.setText(conta.getAgencia());
			txtNumeroConta.setText(conta.getNumero());
			txtNomeConta.setText(conta.getNome());
			cbBanco.setSelectedItem(conta.getBanco());
			cbTipoConta.setSelectedItem(conta.getTipoConta());
		}else
		{
			limparCampos();
		}
	}
		
	private void carregarTipoContas(){
		List<TipoContaVO> list = Controller.getInstance().getAllTiposConta();
		cbTipoConta.removeAllItems();
		for(TipoContaVO c : list)
			cbTipoConta.addItem(c);		
	}
	
	private void carregarBancos(){
		List<BancoVO> list = Controller.getInstance().getAllBancos();
		cbBanco.removeAllItems();
		for(BancoVO b : list)
			cbBanco.addItem(b);	
	}
	
	@SuppressWarnings("static-access")
	public void limparCampos(){
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,	barNotificacao.DICA);
		txtAgencia.setText("");
		txtNumeroConta.setText("");
		txtNomeConta.setText("");
	}
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
	
		lbAgencia = new JLabel("Agência:* ", JLabel.RIGHT);
		lbAgencia.setPreferredSize(DLABEL);
		lbAgencia.setMinimumSize(DLABEL);
		
		CustomDocument aDoc = new CustomDocument(5);
		txtAgencia = new JTextField();
		txtAgencia.setDocument(aDoc);
		txtAgencia.setPreferredSize(DFIELDM);
		
		lbBanco = new JLabel("Banco: *", JLabel.RIGHT);
		lbBanco.setPreferredSize(DLABEL);
		lbBanco.setMinimumSize(DLABEL);
		
		cbBanco = new JComboBox<BancoVO>();
		cbBanco.setPreferredSize(DFIELDM);
		cbBanco.setMinimumSize(DFIELDM);
		
		lbNomeConta = new JLabel("Nome: *", JLabel.RIGHT);
		lbNomeConta.setPreferredSize(DLABEL);
		lbNomeConta.setMinimumSize(DLABEL);
		
		CustomDocument nDoc = new CustomDocument(20);
		txtNomeConta = new JTextField();
		txtNomeConta.setDocument(nDoc);
		txtNomeConta.setPreferredSize(DFIELDM);
		
		lbNumeroConta = new JLabel("Número: *", JLabel.RIGHT);
		lbNumeroConta.setPreferredSize(DLABEL);
		lbNumeroConta.setMinimumSize(DLABEL);
		
		CustomDocument numDoc = new CustomDocument(10);
		txtNumeroConta = new JTextField();
		txtNumeroConta.setDocument(numDoc);
		txtNumeroConta.setPreferredSize(DFIELDM);
		
		lbTipoConta = new JLabel("Tipo: *", JLabel.RIGHT);
		lbTipoConta.setPreferredSize(DLABEL);
		lbTipoConta.setMinimumSize(DLABEL);
		
		cbTipoConta = new JComboBox<TipoContaVO>();
		cbTipoConta.setPreferredSize(DFIELDM);
		cbTipoConta.setMinimumSize(DFIELDM);
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infCidade = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infCidade.add(lbBanco, c);
		c.gridy = 1; infCidade.add(lbTipoConta, c);
		c.gridy = 2; infCidade.add(lbAgencia, c);
		c.gridy = 3; infCidade.add(lbNumeroConta, c);
		c.gridy = 4; infCidade.add(lbNomeConta, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infCidade.add(cbBanco, c);
		c.gridy = 1; infCidade.add(cbTipoConta, c);
		c.gridy = 2; infCidade.add(txtAgencia, c);
		c.gridy = 3; infCidade.add(txtNumeroConta, c);
		c.gridy = 4; infCidade.add(txtNomeConta, c);
		
		infCidade.setBorder(BorderFactory.createTitledBorder("Conta"));
		adicionarAtalhosComandos(infCidade);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		JPanel panelCidade = new JPanel(new BorderLayout()); 
		panelCidade.add(infCidade, BorderLayout.CENTER);
		panelCidade.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelCidade);
		
		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(panelCidade, BorderLayout.CENTER);
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
			if (c instanceof ContaMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
		
	private class EventoBotaoControle implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e){
			
			if (e.getSource() == btCancelar){
				Component c = getPainelPrincipal();
				try{
					((ContaMainPanel)c).getPainelPrincipal().setVisible(false);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			} 
			
			if (e.getSource() == btSalvar)
			{
				try {
					validarCampos();
				
					Component c = getPainelPrincipal();
					ContaVO novaConta= new ContaVO();
					novaConta.setAgencia(txtAgencia.getText().trim());
					novaConta.setBanco((BancoVO)cbBanco.getSelectedItem());
					novaConta.setNome(txtNomeConta.getText().trim());
					novaConta.setNumero(txtNumeroConta.getText().trim());
					novaConta.setTipoConta((TipoContaVO)cbTipoConta.getSelectedItem());
					
					if(conta != null){
						novaConta.setId(conta.getId());
						Controller.getInstance().updateConta(novaConta);
						
					}else
					{
						Controller.getInstance().insertConta(novaConta);
						
					}				
					((ContaMainPanel)c).carregarContas();
					((ContaMainPanel)c).getIframeConta().setVisible(false);
					limparCampos();
					
				} catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(),	barNotificacao.ERRO);
					ex.printStackTrace();
				}

			}
		}
	}
	
	private void validarCampos()throws MensagemSaoDimasException
	{
		if(txtAgencia.getText().trim().equals(""))
			throw new MensagemSaoDimasException("Campo agência é obrigatório... ");
			
		if(txtNomeConta.getText().trim().equals(""))
			throw new MensagemSaoDimasException("Campo nome é obrigatório... ");
			
		if(txtNumeroConta.getText().trim().equals(""))
			throw new MensagemSaoDimasException("Campo número é obrigatório... ");
	}
	
	}	