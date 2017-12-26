package br.com.saodimas.view.components.panel.apolice.editar.emprestimo;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.text.DataTextField;

@SuppressWarnings("serial")
 public class DevolucaoEmprestimoEquipamentoPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";

	private EmprestimoEquipamentoVO emprestimoEquipamento;
	private BarraNotificacao barNotificacao;
	
	private JTextField cbEquipamento;
	private DataTextField dataEmprestimo;
	private DataTextField dataPrevistaDevolucao;
	private DataTextField dataDevolucao;
	private JTextArea txtaObservacao;

	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbEquipamento;
	private JLabel lbdataEmprestimo;
	private JLabel lbPrevisaoDevolucao;
	private JLabel lbDataDelovucao;
	private JLabel lbObservacao;
	
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public DevolucaoEmprestimoEquipamentoPanel() {
		initialize();
		configure();
	}
	
	public EmprestimoEquipamentoVO getEmprestimoEquipamentoVO()
	{
		return this.emprestimoEquipamento;
	}

	public void setEmprestimoEquipamento(EmprestimoEquipamentoVO emprestimoEquipamento) {
		this.emprestimoEquipamento = emprestimoEquipamento;
		if(emprestimoEquipamento != null){
			cbEquipamento.setText(emprestimoEquipamento.getEquipamentoVO().getDescricao());
			txtaObservacao.setText(emprestimoEquipamento.getObservacao());
			SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
			dataDevolucao.setText(emprestimoEquipamento.getDataDevolucao() == null ? "" : sformat.format(emprestimoEquipamento.getDataDevolucao()));
			dataEmprestimo.setText(emprestimoEquipamento.getDataEmpresatimo() == null ? "" : sformat.format(emprestimoEquipamento.getDataEmpresatimo()));
			dataPrevistaDevolucao.setText(emprestimoEquipamento.getDataPrevisaDevolucao() == null ? "" : sformat.format(emprestimoEquipamento.getDataPrevisaDevolucao()));
		}
		else{limparCampos();}
		
		this.barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,BarraNotificacao.DICA);
	}
	
	public void limparCampos() {
		txtaObservacao.setText("");
		dataDevolucao.setText("");
		dataEmprestimo.setText("");
		dataPrevistaDevolucao.setText("");
	}
	
	public void focoPadrao(){
		dataDevolucao.requestFocus();
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
	
		lbPrevisaoDevolucao = new JLabel("Data Prevista Devolução:", JLabel.RIGHT);
		lbPrevisaoDevolucao.setPreferredSize(DLABEL);
		lbPrevisaoDevolucao.setMinimumSize(DLABEL);
		
		dataPrevistaDevolucao = new DataTextField();
		dataPrevistaDevolucao.setPreferredSize(DFIELDM);
		dataPrevistaDevolucao.setEditable(false);
		
		lbEquipamento = new JLabel("Equipamento: ", JLabel.RIGHT);
		lbEquipamento.setPreferredSize(DLABEL);
		lbEquipamento.setMinimumSize(DLABEL);
		
		cbEquipamento = new JTextField();
		cbEquipamento.setEditable(false);
		
		lbdataEmprestimo = new JLabel("Data Empréstimo : ", JLabel.RIGHT);
		lbdataEmprestimo.setPreferredSize(DLABEL);
		lbdataEmprestimo.setMinimumSize(DLABEL);
		
		dataEmprestimo = new DataTextField();
		dataEmprestimo.setPreferredSize(DFIELDM);
		dataEmprestimo.setEditable(false);
		
		lbObservacao = new JLabel("Observação :", JLabel.RIGHT);
		lbObservacao.setPreferredSize(DLABEL);
		lbObservacao.setMinimumSize(DLABEL);
		
		txtaObservacao = new JTextArea();
		
		lbDataDelovucao = new JLabel("Data Devolução *:", JLabel.RIGHT);
		lbDataDelovucao.setPreferredSize(DLABEL);
		lbDataDelovucao.setMinimumSize(DLABEL);
		
		dataDevolucao = new DataTextField();
		dataDevolucao.setPreferredSize(DFIELDM);
		
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

		c.gridy = 0; infUser.add(lbEquipamento, c);
		c.gridy = 1; infUser.add(lbdataEmprestimo, c);
		c.gridy = 2; infUser.add(lbPrevisaoDevolucao, c);
		c.gridy = 3; infUser.add(lbDataDelovucao, c);
		c.gridy = 4; infUser.add(lbObservacao, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(cbEquipamento, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1; infUser.add(dataEmprestimo, c);
		c.gridy = 2; infUser.add(dataPrevistaDevolucao, c);
		c.gridy = 3; infUser.add(dataDevolucao, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JScrollPane paneObs = new JScrollPane(txtaObservacao);
		paneObs.setPreferredSize(new Dimension(200, 100));
		
		c.gridy = 4; infUser.add(paneObs, c);
		
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
			if (c instanceof ApoliceEditarMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	
	private void validarDevolucaoEmprestimoEquipamento()throws MensagemSaoDimasException
	{
			
		boolean isValido = !dataDevolucao.getText().trim().equals("");	
		if(!isValido)
			throw new MensagemSaoDimasException("Campos obrigatórios não preenchidos.");
		
	}
	
	private class EventoBotaoControle implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			Component c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				try{
					((ApoliceEditarMainPanel)c).getIframeDevolucaoEmprestimoEquipamento().setVisible(false);
				}
				catch(ClassCastException cex){
					cex.printStackTrace();
				}
			}else
			if (e.getSource() == btSalvar)
			{
				
				try {
				
					validarDevolucaoEmprestimoEquipamento();
						
					SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyy");
					emprestimoEquipamento.setDataDevolucao(dataDevolucao.getText().trim().equals("")? null : sformat.parse(dataDevolucao.getText()));
					emprestimoEquipamento.setObservacao(txtaObservacao.getText());
					Controller.getInstance().updateEmprestimoEquipamento(emprestimoEquipamento);
						
					((ApoliceEditarMainPanel)c).mostrarMensagemEquipamento("Devolução efetuada com sucesso.",	barNotificacao.SUCESSO);
					((ApoliceEditarMainPanel)c).getIframeDevolucaoEmprestimoEquipamento().setVisible(false);
					((ApoliceEditarMainPanel)c).carregarEquipamentos();
					((ApoliceEditarMainPanel)c).carregarDevolucoes();					
					((ApoliceEditarMainPanel)c).atualizarInformacoesSuperiores();
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