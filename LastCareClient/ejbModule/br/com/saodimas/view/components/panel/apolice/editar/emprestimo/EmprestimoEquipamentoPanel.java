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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.model.beans.EquipamentoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.text.DataTextField;

@SuppressWarnings("serial")
 public class EmprestimoEquipamentoPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";

	private ApoliceVO apolice;
	private EmprestimoEquipamentoVO emprestimoEquipamento;
	private BarraNotificacao barNotificacao;
	
	private JComboBox cbEquipamento;
	private JComboBox cbAssociado;
	private DataTextField dataEmprestimo;
	private DataTextField dataPrevistaDevolucao;
	private JTextArea txtaObservacao;

	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbEquipamento;
	private JLabel lbAssociado;
	private JLabel lbdataEmprestimo;
	private JLabel lbPrevisaoDevolucao;
	private JLabel lbObservacao;
	
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public EmprestimoEquipamentoPanel() {
		initialize();
		configure();
	}
	
	public void setApolice(ApoliceVO apolice)
	{
		this.apolice = apolice;
	}
	
	public EmprestimoEquipamentoVO getEmprestimoEquipamentoVO()
	{
		return this.emprestimoEquipamento;
	}

	public void setEmprestimoEquipamento(EmprestimoEquipamentoVO emprestimoEquipamento) {
		this.emprestimoEquipamento = emprestimoEquipamento;
		this.loadEquipamentos();
		this.loadAssociados();
		if(emprestimoEquipamento != null)
		{
			txtaObservacao.setText(emprestimoEquipamento.getObservacao());
			SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyyy");
			dataEmprestimo.setText(emprestimoEquipamento.getDataEmpresatimo() == null ? "" : sformat.format(emprestimoEquipamento.getDataEmpresatimo()));
			dataPrevistaDevolucao.setText(emprestimoEquipamento.getDataPrevisaDevolucao() == null ? "" : sformat.format(emprestimoEquipamento.getDataPrevisaDevolucao()));
			cbEquipamento.setSelectedItem(emprestimoEquipamento.getEquipamentoVO());
			cbEquipamento.repaint();
			if(emprestimoEquipamento.getAssociado() != null)
				cbAssociado.setSelectedItem(emprestimoEquipamento.getAssociado());	
			cbAssociado.repaint();
		}else
		{
			limparCampos();
		}
		
		this.barNotificacao.mostrarMensagem(MENSAGEM_PADRAO,BarraNotificacao.DICA);
	}
	
	private void loadEquipamentos()
	{
		cbEquipamento.removeAllItems();
		List<EquipamentoVO> list = Controller.getInstance().getEquipamentoAllComQde();
		for ( int i = 0 ; i < list.size(); i++) {
			cbEquipamento.addItem(list.get(i));
			if(this.emprestimoEquipamento != null && this.emprestimoEquipamento.getEquipamentoVO().getId().equals(list.get(i).getId()))
				cbEquipamento.setSelectedIndex(i);
		}
		
		cbEquipamento.repaint();
	}
	
	
	
	private void loadAssociados()
	{
		cbAssociado.removeAllItems();
		List<AssociadoVO> list = apolice.getDependentes();
		for ( int i = 0 ; i < list.size(); i++) {
			
			if(list.get(i).getObito() == null)
				cbAssociado.addItem(list.get(i));
			
			if( this.emprestimoEquipamento != null && this.emprestimoEquipamento.getAssociado() != null && this.emprestimoEquipamento.getAssociado().getId().equals(list.get(i).getId()))
				cbAssociado.setSelectedIndex(i);
		}
		
		cbAssociado.repaint();
	}

	public void limparCampos() {
		txtaObservacao.setText("");
		dataEmprestimo.setText("");
		dataPrevistaDevolucao.setText("");
	}
	
	public void focoPadrao(){
		dataEmprestimo.requestFocus();
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
	
		lbPrevisaoDevolucao = new JLabel("Data Prevista Devolução:", JLabel.RIGHT);
		lbPrevisaoDevolucao.setPreferredSize(DLABEL);
		lbPrevisaoDevolucao.setMinimumSize(DLABEL);
		
		dataPrevistaDevolucao = new DataTextField();
		dataPrevistaDevolucao.setPreferredSize(DFIELDM);
		
		lbEquipamento = new JLabel("Equipamento: *", JLabel.RIGHT);
		lbEquipamento.setPreferredSize(DLABEL);
		lbEquipamento.setMinimumSize(DLABEL);
		
		cbEquipamento = new JComboBox();
		
		lbdataEmprestimo = new JLabel("Data Empréstimo *: ", JLabel.RIGHT);
		lbdataEmprestimo.setPreferredSize(DLABEL);
		lbdataEmprestimo.setMinimumSize(DLABEL);
		
		lbAssociado = new JLabel("Associado: *", JLabel.RIGHT);
		lbAssociado.setPreferredSize(DLABEL);
		lbAssociado.setMinimumSize(DLABEL);
		
		cbAssociado = new JComboBox();
		
		dataEmprestimo = new DataTextField();
		dataEmprestimo.setPreferredSize(DFIELDM);
		
		lbObservacao = new JLabel("Observação :", JLabel.RIGHT);
		lbObservacao.setPreferredSize(DLABEL);
		lbObservacao.setMinimumSize(DLABEL);
		
		txtaObservacao = new JTextArea();
				
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

		c.gridy = 0; infUser.add(lbAssociado, c);
		c.gridy = 1; infUser.add(lbEquipamento, c);
		c.gridy = 2; infUser.add(lbdataEmprestimo, c);
		c.gridy = 3; infUser.add(lbPrevisaoDevolucao, c);
		c.gridy = 4; infUser.add(lbObservacao, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infUser.add(cbAssociado, c);
		c.gridy = 1; infUser.add(cbEquipamento, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 2; infUser.add(dataEmprestimo, c);
		c.gridy = 3; infUser.add(dataPrevistaDevolucao, c);
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
	
	private void validarEmprestimoEquipamento(boolean isNovo)throws MensagemSaoDimasException
	{
		boolean isValido = true;
	
		EquipamentoVO equipa = (EquipamentoVO) cbEquipamento.getSelectedItem();
		isValido = isValido && (equipa != null);
		isValido = isValido && !(dataEmprestimo.getText().trim().equals(""));
			
		if(!isValido)
			throw new MensagemSaoDimasException("Campos obrigatórios não preenchidos.");
		
		if(isNovo || !equipa.getId().equals(emprestimoEquipamento.getEquipamentoVO().getId()))
		{
			if((equipa.getQuantidade() - equipa.getQtdeEmprestimo() < 1))
				throw new MensagemSaoDimasException("Não há equipamentos disponível no estoque");
		}				
	}
	
	private class EventoBotaoControle implements ActionListener{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			
			Component c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				try{
					((ApoliceEditarMainPanel)c).getIframeEmprestimoEquipamento().setVisible(false);
				}
				catch(ClassCastException cex){
					cex.printStackTrace();
				}
			}else
			if (e.getSource() == btSalvar)
			{
				
				EmprestimoEquipamentoVO  novoEqui = new EmprestimoEquipamentoVO();
				
				try {
				
					validarEmprestimoEquipamento(emprestimoEquipamento == null);
						
					SimpleDateFormat sformat = new SimpleDateFormat("dd/MM/yyy");
					novoEqui.setApolice(apolice);
					novoEqui.setDataEmpresatimo(dataEmprestimo.getText().trim().equals("")? null : sformat.parse(dataEmprestimo.getText()));
					novoEqui.setDataPrevisaDevolucao(dataPrevistaDevolucao.getText().trim().equals("")? null : sformat.parse(dataPrevistaDevolucao.getText()));
					novoEqui.setEquipamentoVO((EquipamentoVO)cbEquipamento.getSelectedItem());
					novoEqui.setObservacao(txtaObservacao.getText());
					novoEqui.setAssociado((AssociadoVO)cbAssociado.getSelectedItem());
					
					if(emprestimoEquipamento != null){
						novoEqui.setId(emprestimoEquipamento.getId());
						
							Controller.getInstance().updateEmprestimoEquipamento(novoEqui);
						
						((ApoliceEditarMainPanel)c).mostrarMensagemEquipamento("Alterações efetuadas com sucesso.",	barNotificacao.SUCESSO);
					}else
					{
						Controller.getInstance().insertEmprestimoEquipamento(novoEqui);
						((ApoliceEditarMainPanel)c).mostrarMensagem("Cadastro efetuado com sucesso.",	barNotificacao.SUCESSO);
						((ApoliceEditarMainPanel)c).carregarEquipamentos();
						((ApoliceEditarMainPanel)c).atualizarInformacoesSuperiores();
					}				
					((ApoliceEditarMainPanel)c).getIframeEmprestimoEquipamento().setVisible(false);
					((ApoliceEditarMainPanel)c).carregarEquipamentos();
					loadEquipamentos();
					
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