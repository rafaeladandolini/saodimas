package br.com.saodimas.view.components.panel.financeiro.fornecedor;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.FornecedorVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.FoneTextField;

@SuppressWarnings("serial")
public class FornecedorPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigat�rio";

	private FornecedorVO fornecedorVO;
	private BarraNotificacao barNotificacao;
		
	private JTextField txtNome;
	private FoneTextField txtContato;
  	
	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbNome;
	private JLabel lbContato;
	
	private static final Dimension DLABEL = new Dimension(120,22);
		
	public FornecedorPanel(){
		initialize();
		configure();
	}

	public FornecedorVO getEmpresaCompraVO() {
		return fornecedorVO;
	}

	public void setEmpresaCompra(FornecedorVO empresa) {
		this.fornecedorVO = empresa;
		limparCampos();
		if(empresa != null){
			txtNome.setText(empresa.getNome());
			txtContato.setText(empresa.getContato());
		}
		
	}

	public void limparCampos() {
	
		txtNome.setText("");
		txtContato.setText("");		
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, BarraNotificacao.DEFAULT);
	}
	
	public void focoPadrao(){
		txtNome.requestFocus();
	}
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbNome = new JLabel("Nome *: ", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		
		CustomDocument nomeDoc = new CustomDocument(150);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(new Dimension(200,22));
		
		lbContato = new JLabel("Contato: ", JLabel.RIGHT);
		lbContato.setPreferredSize(DLABEL);
		lbContato.setMinimumSize(DLABEL);
		
		txtContato = new FoneTextField();
		txtContato.setPreferredSize(new Dimension(100,22));
				
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infUser = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infUser.add(lbNome, c);
		c.gridy = 1; infUser.add(lbContato, c);
						
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(txtNome, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1; infUser.add(txtContato, c);
		c.fill = GridBagConstraints.NONE;
				
		
		infUser.setBorder(BorderFactory.createTitledBorder("Empresa"));
		adicionarAtalhosComandos(infUser);
		
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(infUser, BorderLayout.CENTER);
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
			if (c instanceof FornecedorMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	

	private class EventoBotaoControle implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			
			Component c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				try{
					((FornecedorMainPanel)c).getIFrameEmpresaCompra().setVisible(false);
				}
				catch(ClassCastException cex){
					cex.printStackTrace();
				}
			}else
			if (e.getSource() == btSalvar)
			{
				salvarEmpresaCompra();	
			}
		}
	}

	@SuppressWarnings("static-access")
	private void salvarEmpresaCompra()
	{
		FornecedorVO novaEmpresaCompra = new FornecedorVO();
		Component c = getPainelPrincipal();
		
		try{
			this.validarCampos();
			novaEmpresaCompra.setNome(txtNome.getText());
			novaEmpresaCompra.setContato(txtContato.getText());
		
			if (fornecedorVO != null) {
				novaEmpresaCompra.setId(fornecedorVO.getId());
				Controller.getInstance().updateEmpresaCompra(novaEmpresaCompra);
				((FornecedorMainPanel) c).mostrarMensagem(
						"Altera��es efetuadas com sucesso.",
						barNotificacao.INFO);
			} else {
				Controller.getInstance().insertEmpresaCompra(novaEmpresaCompra);
				((FornecedorMainPanel) c).mostrarMensagem(
						"Cadastro efetuado com sucesso.", barNotificacao.INFO);
			}

			((FornecedorMainPanel) c).carregarEmpresasCompras();
			((FornecedorMainPanel) c).getIFrameEmpresaCompra().setVisible(false);

		} catch (MensagemSaoDimasException ex) {
			barNotificacao.mostrarMensagem(ex.getMessage(), barNotificacao.ERRO);
		}
	}
	
	private void validarCampos() throws MensagemSaoDimasException
	{
		if(txtNome.getText().trim().equals(""))
		{
			txtNome.requestFocus();
			throw new MensagemSaoDimasException("Campo NOME � obrigat�rio...");
		}
	}
	
	
}	