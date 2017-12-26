package br.com.saodimas.view.components.panel.equipamento;

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
import br.com.saodimas.model.beans.EquipamentoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.table.renderer.SpinnerRenderer;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.components.text.MoedaTextField;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class EquipamentoPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";

	private EquipamentoVO equipamento;
	private BarraNotificacao barNotificacao;
	private JTextField txtDescricao;
	private JTextField txtModelo;
	private DataTextField dataAquisicao;
	private JComboBox cbStatus;
	private JTextArea txtaObservacao;
	private MoedaTextField txtValor;
	private SpinnerRenderer spinnerQtde;

	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbDescricao;
	private JLabel lbModelo;
	private JLabel lbDataAquisicao;
	private JLabel lbStatus;
	private JLabel lbObservacao;
	private JLabel lbValor;
	private JLabel lbQuantidade;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public EquipamentoPanel() {
		initialize();
		configure();
	}
	
	public EquipamentoVO getEquipamentoVO()
	{
		return this.equipamento;
	}

	public void setEquipamento(EquipamentoVO equipamento) {
		this.equipamento = equipamento;
		if(equipamento != null){
			txtaObservacao.setText(equipamento.getObservacao());
			txtDescricao.setText(equipamento.getDescricao());
			txtModelo.setText(equipamento.getModelo());
			txtValor.setValor(equipamento.getValor());
			SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
			dataAquisicao.setText(sformat.format(equipamento.getDataAquisicao()));
			spinnerQtde.setValue(new Integer(equipamento.getQuantidade()));
		}
		else{limparCampos();}
		
		this.barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,BarraNotificacao.DICA);
	}

	public void limparCampos() {
		txtaObservacao.setText("");
		txtDescricao.setText("");
		txtModelo.setText("");
		txtValor.setValor(0d);
		dataAquisicao.setText("");
		spinnerQtde.setValue(new Integer(0));
	}
	
	public void focoPadrao(){
		txtDescricao.requestFocus();
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
	
		lbDataAquisicao = new JLabel("Data Aquisição: *", JLabel.RIGHT);
		lbDataAquisicao.setPreferredSize(DLABEL);
		lbDataAquisicao.setMinimumSize(DLABEL);
		
		dataAquisicao = new DataTextField();
		dataAquisicao.setPreferredSize(DFIELDM);
		
		lbDescricao = new JLabel("Descrição: *", JLabel.RIGHT);
		lbDescricao.setPreferredSize(DLABEL);
		lbDescricao.setMinimumSize(DLABEL);
		
		txtDescricao = new JTextField();
		txtDescricao.setPreferredSize(DFIELDM);
		
		lbModelo = new JLabel("Modelo: ", JLabel.RIGHT);
		lbModelo.setPreferredSize(DLABEL);
		lbModelo.setMinimumSize(DLABEL);
		
		txtModelo = new JTextField();
		txtModelo.setPreferredSize(DFIELDM);
		
		lbObservacao = new JLabel("Observação :", JLabel.RIGHT);
		lbObservacao.setPreferredSize(DLABEL);
		lbObservacao.setMinimumSize(DLABEL);
		
		txtaObservacao = new JTextArea();
		
		lbStatus = new JLabel("Status *:", JLabel.RIGHT);
		lbStatus.setPreferredSize(DLABEL);
		lbStatus.setMinimumSize(DLABEL);
		
		cbStatus = new JComboBox(ListasComuns.STATUS_EQUIPAMENTO);
		
		lbValor = new JLabel("Valor *:", JLabel.RIGHT);
		lbValor.setPreferredSize(DLABEL);
		lbValor.setMinimumSize(DLABEL);
		
		txtValor = new MoedaTextField();
		txtValor.setPreferredSize(DFIELDM);
		
		lbQuantidade = new JLabel("Quantidade *:", JLabel.RIGHT);
		lbQuantidade.setPreferredSize(DLABEL);
		lbQuantidade.setMinimumSize(DLABEL);
		
		spinnerQtde = new SpinnerRenderer();
		spinnerQtde.setPreferredSize(DFIELDM);
		spinnerQtde.setMinimumSize(DFIELDM);
			
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
		c.gridy = 1; infUser.add(lbModelo, c);
		c.gridy = 2; infUser.add(lbDataAquisicao, c);
		c.gridy = 3; infUser.add(lbQuantidade, c);
		c.gridy = 4; infUser.add(lbValor, c);
		c.gridy = 5; infUser.add(lbStatus, c);
		c.gridy = 6; infUser.add(lbObservacao, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(txtDescricao, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1; infUser.add(txtModelo, c);
		c.gridy = 2; infUser.add(dataAquisicao, c);
		c.gridy = 3; infUser.add(spinnerQtde, c);
		c.gridy = 4; infUser.add(txtValor, c);
		c.gridy = 5; infUser.add(cbStatus, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JScrollPane paneObs = new JScrollPane(txtaObservacao);
		paneObs.setPreferredSize(new Dimension(200, 100));
		
		c.gridy = 6; infUser.add(paneObs, c);
		
		infUser.setBorder(BorderFactory.createTitledBorder("Equipamento"));
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
			if (c instanceof EquipamentoMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	
	private void validarEquipamento()throws MensagemSaoDimasException
	{
		boolean isValido = true;
		
		isValido = isValido && !(txtDescricao.getText().trim().equals(""));
		isValido = isValido && !(dataAquisicao.getText().trim().equals(""));
		isValido = isValido && txtValor.getValor() != null;
		
		if(!isValido)
			throw new MensagemSaoDimasException("Campos obrigatórios não preenchidos.");
		
	}
	
	private class EventoBotaoControle implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			Component c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				try{
					((EquipamentoMainPanel)c).getIframeEquipamento().setVisible(false);
				}
				catch(ClassCastException cex){
					cex.printStackTrace();
				}
			}else
			if (e.getSource() == btSalvar)
			{
				
				EquipamentoVO novoEqui = new EquipamentoVO();
				
				try {
				
					validarEquipamento();
						
					SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyy");
					novoEqui.setDataAquisicao(sformat.parse(dataAquisicao.getText()));
					novoEqui.setDescricao(txtDescricao.getText());
					novoEqui.setModelo(txtModelo.getText());
					novoEqui.setObservacao(txtaObservacao.getText());
					novoEqui.setStatus(cbStatus.getSelectedItem().toString());
					novoEqui.setValor(txtValor.getValor());
					novoEqui.setQuantidade(new Integer(spinnerQtde.getValue().toString()).intValue());
				
					if(equipamento != null){
						novoEqui.setId(equipamento.getId());
						
							Controller.getInstance().updateEquipamento(novoEqui);
						
						((EquipamentoMainPanel)c).mostrarMensagem("Alterações efetuadas com sucesso.",	barNotificacao.SUCESSO);
					}else
					{
						Controller.getInstance().insertEquipamento(novoEqui);
						((EquipamentoMainPanel)c).mostrarMensagem("Cadastro efetuado com sucesso.",	barNotificacao.SUCESSO);
					}				
					((EquipamentoMainPanel)c).getIframeEquipamento().setVisible(false);
					((EquipamentoMainPanel)c).carregarEquipamentoTable();
				} catch (MensagemSaoDimasException e1) {
					barNotificacao.mostrarMensagem(e1.getMessage(),	barNotificacao.ERRO);
					e1.printStackTrace();
				} catch (ParseException ex) {
					barNotificacao.mostrarMensagem("Informe uma data válida.", BarraNotificacao.ERRO);
					ex.printStackTrace();
				}
				
			}

		}
	}
}	