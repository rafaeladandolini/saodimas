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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.EmprestimoEquipamentoVO;
import br.com.saodimas.model.beans.EquipamentoVO;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class ConsultaEmprestimoPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = " ";
	
	private BarraNotificacao barNotificacao;
	private JComboBox cbEquipamentos;
	private JComboBox cbCidades;
	
	private ButtonGroup btGroup;
	private JRadioButton rdTodos;
	private JRadioButton rdEmprestado;
	private JRadioButton rdDevolvido;
	
	private JButton btCancelar;
	private JButton btEnviar;
	
	private JLabel lbEquipamento;
	private JLabel lbCidade;
	private JLabel lbStatus;
	
	
	private static final Dimension DLABEL = new Dimension(120,22);
	
	
	public ConsultaEmprestimoPanel() {
		initialize();
		configure();
	}

		
	public void focoPadrao(){
		
		this.loadEquipamentos();
		this.loadCidades();
	}	
	
	private void loadEquipamentos()
	{
		cbEquipamentos.removeAllItems();
		List<EquipamentoVO> list = Controller.getInstance().getEquipamentoAllComQde();
		EquipamentoVO equiTodos = new EquipamentoVO();
		equiTodos.setId(null);
		equiTodos.setDescricao("Todos");
		cbEquipamentos.addItem(equiTodos);
		for ( int i = 0 ; i < list.size(); i++) {
			cbEquipamentos.addItem(list.get(i));
		}
		
		
	}
	

	private void loadCidades()
	{
		cbCidades.removeAllItems();
		List<CidadeVO> list = Controller.getInstance().getCidadeAll();
		CidadeVO cidadeTodos = new CidadeVO();
		cidadeTodos.setId(null);
		cidadeTodos.setNome("Todas");
		cbCidades.addItem(cidadeTodos);
		for ( int i = 0 ; i < list.size(); i++) {
			cbCidades.addItem(list.get(i));
		}
		
		
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbEquipamento = new JLabel("Equipamento: ", JLabel.RIGHT);
		lbEquipamento.setVisible(true);
		
		cbEquipamentos = new JComboBox();
		cbEquipamentos.setVisible(true);
		
		lbCidade = new JLabel("Cidade: ", JLabel.RIGHT);
		lbCidade.setPreferredSize(DLABEL);
		lbCidade.setMinimumSize(DLABEL);
		lbCidade.setVisible(true);
		
		cbCidades = new JComboBox();
		cbCidades.setVisible(true);

		lbStatus = new JLabel("Com o Status... ", JLabel.RIGHT);
		lbStatus.setPreferredSize(DLABEL);
		lbStatus.setMinimumSize(DLABEL);
		
		btGroup = new ButtonGroup();
		rdTodos = new JRadioButton(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[0]);
		rdEmprestado = new JRadioButton(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[1]);
		rdDevolvido = new JRadioButton(ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[2]);
		
		btGroup.add(rdTodos);
		btGroup.add(rdEmprestado);
		btGroup.add(rdDevolvido);
		
		btGroup.setSelected(rdEmprestado.getModel(), true);
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btEnviar = new JButton("Enviar", new ImageIcon("imagens/submit.gif"));
		btEnviar.addActionListener(new EventoBotaoControle());
		btEnviar.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infServico = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;

		c.gridy = 0; infServico.add(lbEquipamento, c);
		c.gridy = 1; infServico.add(lbCidade, c);
		c.gridy = 2; infServico.add(lbStatus, c);
		
		
		JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelStatus.add(rdTodos);
		panelStatus.add(rdEmprestado);
		panelStatus.add(rdDevolvido);
		panelStatus.setMinimumSize(new Dimension(200, 22));
		panelStatus.setBorder(BorderFactory.createEmptyBorder());		
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.NONE;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infServico.add(cbEquipamentos, c);
		c.gridy = 1; infServico.add(cbCidades, c);
		c.gridy = 2; infServico.add(panelStatus, c);
	
		infServico.setBorder(BorderFactory.createTitledBorder("Parâmetros da consulta"));
		adicionarAtalhosComandos(infServico);
		
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btEnviar);
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
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btEnviar.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}	
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof EmprestimoEquipamentoMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	
	private String getSelectedStatus(){
		if(rdTodos.isSelected())return ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[0];
		else if(rdEmprestado.isSelected())return ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[1];
		else if(rdDevolvido.isSelected())return ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[2];
		else return ListasComuns.STATUS_EMPRESTIMO_EQUIPAMENTO[0];

	}
		
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			EmprestimoEquipamentoMainPanel c = (EmprestimoEquipamentoMainPanel)getPainelPrincipal();
			if (e.getSource() == btCancelar){
				c.getIFrameConsEmprestimoEquipamento().setVisible(false);
			}
			else if (e.getSource() == btEnviar){
				consultaServicosAtivos();
			}
		}
	}

	
	
	private void consultaServicosAtivos(){
		EmprestimoEquipamentoMainPanel c = (EmprestimoEquipamentoMainPanel)getPainelPrincipal();
		List <EmprestimoEquipamentoVO> emprestimoEquipamentoList = Controller.getInstance().consultaEmprestimos((EquipamentoVO)cbEquipamentos.getSelectedItem(), (CidadeVO)cbCidades.getSelectedItem(), getSelectedStatus());
		if (emprestimoEquipamentoList.size() > 0){
			c.carregarEmprestimoEquipamentoTable(emprestimoEquipamentoList);
			c.mostrarMensagem("Consulta efetuada com sucesso! " + (emprestimoEquipamentoList.size() == 1 ? " Apenas 1 emprestimo encontrado ..." : emprestimoEquipamentoList.size() + " emprestimos encontrados ..."), BarraNotificacao.SUCESSO);
			c.getIFrameConsEmprestimoEquipamento().setVisible(false);
		}
		else{
			c.carregarEmprestimoEquipamentoTable(null);
			c.mostrarMensagem("", BarraNotificacao.DEFAULT);
			barNotificacao.mostrarMensagem("Nenhum emprestimo encontrado com esses critérios...", BarraNotificacao.ERRO);
		}
	}
}
