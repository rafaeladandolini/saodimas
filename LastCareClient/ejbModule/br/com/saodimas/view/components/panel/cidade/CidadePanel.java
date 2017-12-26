package br.com.saodimas.view.components.panel.cidade;

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
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.validators.ValidaBasico;

@SuppressWarnings("serial")
public class CidadePanel extends JPanel {
	
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	
	private CidadeVO cidade;
	private BarraNotificacao barNotificacao;
	private JTextField txtCidade;
	private JComboBox cbEstado;
	private JButton btCancelar;
	private JButton btSalvar;

	private JLabel lbCidade;
	private JLabel lbEstado;
	private JLabel lbStatus;
	private JComboBox cbStatus;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public CidadePanel() {
		initialize();
		configure();
	}

	public CidadeVO getCidade() {
		return cidade;
	}

	private void pack()
	{
		Component c = getPainelPrincipal();
		((CidadeMainPanel)c).getIframeCidade().pack();
	}
	
	public void setCidade(CidadeVO cidade) {
		this.cidade = cidade;
		if(cidade!=null){
			txtCidade.setText(cidade.getNome());
			cbEstado.setSelectedItem(cidade.getEstado());
			cbStatus.setSelectedItem(cidade.getStatus());
			lbStatus.setVisible(true);
			cbStatus.setVisible(true);
			pack();
		}else
		{
			limparCampos();
		}
	}
		
	@SuppressWarnings("static-access")
	public void limparCampos(){
		barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,	barNotificacao.DICA);
		txtCidade.setText("");
		cbEstado.setSelectedItem("PR");
		lbStatus.setVisible(false);
		cbStatus.setVisible(false);
		pack();
	}
	
	public void focoPadrao(){
		txtCidade.requestFocus();
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
		
		lbCidade = new JLabel("Cidade: *", JLabel.RIGHT);
		lbCidade.setPreferredSize(DLABEL);
		lbCidade.setMinimumSize(DLABEL);
		
		CustomDocument nomeDoc = new CustomDocument(50);
		txtCidade = new JTextField();
		txtCidade.setDocument(nomeDoc);
		txtCidade.setPreferredSize(DFIELDM);
		
		lbEstado = new JLabel("Estado: *", JLabel.RIGHT);
		lbEstado.setPreferredSize(DLABEL);
		lbEstado.setMinimumSize(DLABEL);
		
		cbEstado= new JComboBox(ListasComuns.ESTADOS);
		cbEstado.setSelectedItem("PR");
		
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
		c.gridy = 0; infCidade.add(lbStatus, c);
		c.gridy = 1; infCidade.add(lbCidade, c);
		c.gridy = 2; infCidade.add(lbEstado, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infCidade.add(cbStatus, c);
		c.gridy = 1; infCidade.add(txtCidade, c);
		c.fill = GridBagConstraints.NONE;
		c.weighty = 1;
		c.gridy = 2; infCidade.add(cbEstado, c);
		
		infCidade.setBorder(BorderFactory.createTitledBorder("Cidade"));
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
			if (c instanceof CidadeMainPanel) return c;
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
					((CidadeMainPanel)c).getIframeCidade().setVisible(false);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			} 
			
			if (e.getSource() == btSalvar)
			{
				try {
					ValidaBasico.validaCamposCidade(txtCidade.getText());
				
					Component c = getPainelPrincipal();
					CidadeVO novacidade = new CidadeVO();
					novacidade.setNome(txtCidade.getText());
					novacidade.setEstado(cbEstado.getSelectedItem().toString());
					if(cidade != null){
						novacidade.setId(cidade.getId());
						novacidade.setStatus(cbStatus.getSelectedItem().toString());
						Controller.getInstance().updateCidade(novacidade);
						barNotificacao.mostrarMensagem("Alteração efetuada com sucesso.",	barNotificacao.INFO);
					}else
					{
						novacidade.setStatus(ListasComuns.STATUS_ITENS[0]);
						Controller.getInstance().insertCidade(novacidade);
						barNotificacao.mostrarMensagem("Cadastro efetuado com sucesso.",	barNotificacao.INFO);
					}				
					((CidadeMainPanel)c).carregarCidadesTable();
					((CidadeMainPanel)c).getIframeCidade().setVisible(false);
					limparCampos();
					
				} catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(),	barNotificacao.ERRO);
					ex.printStackTrace();
				}

			}
		}
	}
	
	}	