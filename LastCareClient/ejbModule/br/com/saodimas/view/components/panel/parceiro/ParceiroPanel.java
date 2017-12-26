package br.com.saodimas.view.components.panel.parceiro;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.ParceiroVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.FoneTextField;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class ParceiroPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";

	private ParceiroVO parceiro;
	private BarraNotificacao barNotificacao;
	
	private JTextField txtDescricao;
	private JTextField txtResponsavel;
	private FoneTextField txtTelefone;
	private JTextField txtEndeteco;
	private JComboBox cbCidade;
	private JComboBox cbEstado;
	private JTextField txtDataCadastro;
	private JComboBox cbSituacao;
	private JTextArea txtaObs;
	
	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbDescricao;
	private JLabel lbResponsavel;
	private JLabel lbTelefone;
	private JLabel lbEndereco;
	private JLabel lbCidade;
	private JLabel lbEstado;
	private JLabel lbDataCadastro;
	private JLabel lbSituacao;
	private JLabel lbObservacao;
		
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public ParceiroPanel() {
		initialize();
		configure();
	}
	
	
	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
			if (flag) barNotificacao.mostrarMensagem(MENSAGEM_PADRAO, BarraNotificacao.DEFAULT);
			else barNotificacao.escondeMensagem();
			if (flag) 
				txtDescricao.requestFocus();
		
	}

	public ParceiroVO getParceiro() {
		return parceiro;
	}

	public void setParceiro(ParceiroVO parceiro) {
		this.parceiro = parceiro;
		cbEstado.setSelectedItem("PR");
		this.loadCidadeByEstado(cbEstado.getSelectedItem().toString());
		if(parceiro != null){
			txtDescricao.setText(parceiro.getDescricao());
			txtResponsavel.setText(parceiro.getResponsavel());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			txtDataCadastro.setText(sdf.format(parceiro.getDataInicio()));
			txtTelefone.setText(parceiro.getTelefone());
			cbSituacao.setSelectedItem(parceiro.getStatus());
			cbSituacao.setEnabled(true);
			cbEstado.setSelectedItem(parceiro.getCidade().getEstado());
			cbCidade.setSelectedItem(parceiro.getCidade());
			txtEndeteco.setText(parceiro.getEndereco());
			txtaObs.setText(parceiro.getObservacao());
		}
		else{limparCampos();}
	}

	public void limparCampos() {
		txtDescricao.setText("");
		txtResponsavel.setText("");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		txtDataCadastro.setText(sdf.format(new Date()));
		txtTelefone.setText("");
		cbSituacao.setSelectedIndex(0);
		txtaObs.setText("");
		txtEndeteco.setText("");
	}
	
	public void focoPadrao(){
		txtDescricao.requestFocus();
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		lbDescricao = new JLabel("Descrição: *", JLabel.RIGHT);
		lbDescricao.setPreferredSize(DLABEL);
		lbDescricao.setMinimumSize(DLABEL);
		
		
		txtDescricao = new JTextField();
		txtDescricao.setDocument(new CustomDocument(45));
		txtDescricao.setPreferredSize(DFIELDM);
		
		lbResponsavel = new JLabel("Responsável: *", JLabel.RIGHT);
		lbResponsavel.setPreferredSize(DLABEL);
		lbResponsavel.setMinimumSize(DLABEL);
		
		txtResponsavel = new JTextField();
		txtResponsavel.setPreferredSize(DFIELDM);
		txtResponsavel.setDocument(new CustomDocument(45));
		
		lbTelefone = new JLabel("Telefone: ", JLabel.RIGHT);
		lbTelefone.setPreferredSize(DLABEL);
		lbTelefone.setMinimumSize(DLABEL);
		
		txtTelefone = new FoneTextField();
		txtTelefone.setPreferredSize(DFIELDM);
		
		lbDataCadastro = new JLabel("Data Cadastro:  ", JLabel.RIGHT);
		lbDataCadastro.setPreferredSize(DLABEL);
		lbDataCadastro.setMinimumSize(DLABEL);
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		String dataFin = f.format(new Date(c.getTimeInMillis()));

		txtDataCadastro = new JTextField(dataFin);
		txtDataCadastro.setPreferredSize(DFIELDM);
		txtDataCadastro.setMinimumSize(DFIELDM);
		txtDataCadastro.setEditable(false);
		
		lbEndereco = new JLabel("Endereço: ", JLabel.RIGHT);
		lbEndereco.setPreferredSize(DLABEL);
		lbEndereco.setMinimumSize(DLABEL);
		
		txtEndeteco = new JTextField();
		txtEndeteco.setDocument(new CustomDocument(45));
		txtEndeteco.setPreferredSize(DFIELDM);
		
		lbCidade = new JLabel("Cidade: *", JLabel.RIGHT);
		lbCidade.setPreferredSize(DLABEL);
		lbCidade.setMinimumSize(DLABEL);

		cbCidade = new JComboBox();
		
		lbEstado = new JLabel("Estado: *", JLabel.RIGHT);
		lbEstado.setPreferredSize(DLABEL);
		lbEstado.setMinimumSize(DLABEL);

		cbEstado= new JComboBox(ListasComuns.ESTADOS);
		cbEstado.setSelectedItem("PR");
		cbEstado.addItemListener(new ApoliceParceiroListener());

		lbSituacao = new JLabel("Status:  ", JLabel.RIGHT);
		lbSituacao.setPreferredSize(DLABEL);
		lbSituacao.setMinimumSize(DLABEL);
		
		cbSituacao = new JComboBox(ListasComuns.STATUS_ITENS);
		cbSituacao.setPreferredSize(DFIELDM);
		cbSituacao.setMinimumSize(DFIELDM);
		
		lbObservacao = new JLabel("Observação: ", JLabel.RIGHT);
		lbObservacao.setPreferredSize(DLABEL);
		lbObservacao.setMinimumSize(DLABEL);
		
		txtaObs = new JTextArea();
		txtaObs.setDocument(new CustomDocument(200));
		txtaObs.setPreferredSize(DFIELDM);
				
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

		c.gridy = 0; infUser.add(lbDescricao, c);
		c.gridy = 1; infUser.add(lbResponsavel, c);
		c.gridy = 2; infUser.add(lbDataCadastro, c);
		c.gridy = 3; infUser.add(lbTelefone, c);
		c.gridy = 4; infUser.add(lbEndereco, c);
		c.gridy = 5; infUser.add(lbEstado, c);
		c.gridy = 6; infUser.add(lbCidade, c);
		c.gridy = 7; infUser.add(lbSituacao, c);
		c.gridy = 8; infUser.add(lbObservacao, c);		
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(txtDescricao, c);
		c.gridy = 1; infUser.add(txtResponsavel, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 2; infUser.add(txtDataCadastro, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 3; infUser.add(txtTelefone, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 4; infUser.add(txtEndeteco, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 5; infUser.add(cbEstado, c);
		c.gridy = 6; infUser.add(cbCidade, c);
		c.weighty = 1;
		c.gridy = 7; infUser.add(cbSituacao, c);
		
		JScrollPane paneObs = new JScrollPane(txtaObs);
		paneObs.setPreferredSize(new Dimension(200, 100));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 8; infUser.add(paneObs, c);
		
		infUser.setBorder(BorderFactory.createTitledBorder("Parceiros"));
		adicionarAtalhosComandos(infUser);
		
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(300, 22));
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
			if (c instanceof ParceiroMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	

	private void loadCidadeByEstado(String estado)
	{
		List<CidadeVO> list = Controller.getInstance().getCidadeAllByEstado(estado);
		cbCidade.removeAllItems();
		for (int i=0; i< list.size(); i++) {
			cbCidade.addItem(list.get(i));
		}
	
	}
	
	private class ApoliceParceiroListener implements ItemListener{
		
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == cbEstado){
				String estado = e.getItem().toString();
				loadCidadeByEstado(estado);
			}
			
		}
	}
	
	private void validarParceiro()throws MensagemSaoDimasException
	{
		boolean isValido = true;
	
		isValido = isValido && !(cbCidade.getSelectedItem() == null);
		SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			sformat.parse(txtDataCadastro.getText());
			isValido = isValido && !txtDescricao.getText().trim().equals("");
			isValido = isValido && !txtResponsavel.getText().trim().equals("");
			
		}catch (ParseException e) {
			isValido = false;
			throw new MensagemSaoDimasException("Data Inválida");
		}
		
		if(!isValido)
		{
			throw new MensagemSaoDimasException("Campos Obrigatórios não preenchidos.");
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			Component c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				try{
					((ParceiroMainPanel)c).getParceiroIFrame().setVisible(false);
					
				}
				catch(ClassCastException cex){
					cex.printStackTrace();
				}
			}else
			if (e.getSource() == btSalvar)
			{
				ParceiroVO novoParceiro = new ParceiroVO();
				novoParceiro.setCidade((CidadeVO)cbCidade.getSelectedItem());
				SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
				try {
					novoParceiro.setDataInicio(sformat.parse(txtDataCadastro.getText()));
					novoParceiro.setDescricao(txtDescricao.getText());
					novoParceiro.setEndereco(txtEndeteco.getText());
					novoParceiro.setObservacao(txtaObs.getText());
					novoParceiro.setResponsavel(txtResponsavel.getText());
					novoParceiro.setStatus(cbSituacao.getSelectedItem().toString());
					novoParceiro.setTelefone(txtTelefone.getText());
					
					validarParceiro();
					
					if(parceiro != null){
						novoParceiro.setId(parceiro.getId());
						Controller.getInstance().updateParceiro(novoParceiro);
						((ParceiroMainPanel)c).mostrarMensagem("Alterações efetuadas com sucesso.",	barNotificacao.INFO);
					}else
					{
						Controller.getInstance().insertParceiro(novoParceiro);
						((ParceiroMainPanel)c).mostrarMensagem("Cadastro efetuado com sucesso.",	barNotificacao.INFO);
					}				
					((ParceiroMainPanel)c).carregarParceirosTable();
					((ParceiroMainPanel)c).getParceiroIFrame().setVisible(false);
				} catch (ParseException e1) {
					barNotificacao.mostrarMensagem("Informe uma data válida.", BarraNotificacao.ERRO);
					e1.printStackTrace();
				} catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(), BarraNotificacao.ERRO);
					ex.printStackTrace();
				}
				
				
			}

		}
	}
}	