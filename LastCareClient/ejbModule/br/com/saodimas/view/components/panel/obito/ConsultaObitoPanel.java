package br.com.saodimas.view.components.panel.obito;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.commons.collections.iterators.ArrayListIterator;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.TipoAtendimentoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.ApoliceMainPanel;
import br.com.saodimas.view.components.spinner.DataSpinner;
import br.com.saodimas.view.util.ExpReg;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class ConsultaObitoPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = " ";
	
	private BarraNotificacao barNotificacao;
	
	private JLabel lbOpcoes;
	private JLabel lbCidade;
	private JLabel lbContrato;
	private JLabel lbNome;
	private JLabel lbData;
	private JLabel lbTipo;
	
	private JComboBox<String> comboOpcoes;
	private JComboBox<CidadeVO> cbCidades;
	private DataSpinner spnDataIni;
	private DataSpinner spnDataFin;
	private JPanel panelPeriodo;
	private JTextField txtContrato;
	private JTextField txtNome;
	
	private JRadioButton btnRadioTodos;
	private ButtonGroup groupRadios = new ButtonGroup();
	private HashMap<Integer, JRadioButton> listRadionsButosTipoAtendimento;
	private JPanel panelRadios = new JPanel();
	private List<TipoAtendimentoVO> listTipoAtendimentos;
	
	private JButton btCancelar;
	private JButton btEnviar;
	
	
	public static final int QUALQUER = 0;
	public static final int CONTRATO = 1;
	public static final int NOME = 2;
	public static final int DATA = 3;
	public static final String[] OPCOES_CONSULTA = {"Qualquer","N. Apólice","Nome","Data do Óbito"}; 

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	
	public ConsultaObitoPanel() {
		initialize();
		configure();
		this.loadAndCreateTipoAtendimento();
	}

		
	public void focoPadrao(){
		
		this.loadCidades();
		
	}	
	
	/*
	 * Cria os componentes dinamicamente TIPO ATENDIMENTO
	 */
	private void loadAndCreateTipoAtendimento()
	{
		this.listRadionsButosTipoAtendimento = new HashMap<Integer, JRadioButton>();
		
		btnRadioTodos =  new  JRadioButton("Todos");
		groupRadios = new ButtonGroup();
		groupRadios.add(btnRadioTodos);
		
		panelRadios.add(btnRadioTodos);
		
		listTipoAtendimentos = Controller.getInstance().getAllTiposAtendimentoVOs();
		for(int i = 0; i < listTipoAtendimentos.size(); i++)
		{
			JRadioButton jb = new JRadioButton(listTipoAtendimentos.get(i).getDescricao());
			listRadionsButosTipoAtendimento.put(listTipoAtendimentos.get(i).getId(), jb);
			groupRadios.add(jb);
			panelRadios.add(jb);
		}
		
		btnRadioTodos.setSelected(true);
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
	
	public void limparCampos() {
		//btnRadioTodos.setSelected(true);
		txtNome.setText("");
		txtContrato.setText("");
		spnDataIni.setValue(new Date(Calendar.getInstance().getTimeInMillis()));
		spnDataFin.setValue(new Date(Calendar.getInstance().getTimeInMillis()));
		comboOpcoes.setSelectedIndex(0);
		barNotificacao.escondeMensagem();
		txtContrato.requestFocus();
	}

	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
		
		lbOpcoes = new JLabel("Pesquisar por... ", JLabel.RIGHT);
		lbOpcoes.setVisible(true);
		
		lbCidade = new JLabel("Cidade: ", JLabel.RIGHT);
		lbCidade.setPreferredSize(DLABEL);
		lbCidade.setMinimumSize(DLABEL);
		lbCidade.setVisible(true);
		
		cbCidades = new JComboBox<CidadeVO>();
		cbCidades.setVisible(true);
		
		comboOpcoes = new JComboBox<String>(OPCOES_CONSULTA);
		comboOpcoes.setSelectedIndex(1);
		comboOpcoes.setPreferredSize(DFIELDM);
		comboOpcoes.setMinimumSize(DFIELDM);
		comboOpcoes.addItemListener(new EventoComboOpcoes());
		
		lbTipo = new JLabel("Tipo:", JLabel.RIGHT);
		lbTipo.setPreferredSize(DLABEL);
		lbTipo.setMinimumSize(DLABEL);
		lbTipo.setVisible(true);

		
		
		lbContrato = new JLabel("N. Apólice.:", JLabel.RIGHT);
		lbContrato.setPreferredSize(DLABEL);
		lbContrato.setMinimumSize(DLABEL);
		lbContrato.setVisible(true);

		CustomDocument contrDoc = new CustomDocument(ExpReg.NUMERIC, 20);
		txtContrato = new JTextField();
		txtContrato.setDocument(contrDoc);
		txtContrato.setPreferredSize(DFIELDM);
		txtContrato.setVisible(true);
		txtContrato.requestFocus();

		lbNome = new JLabel("Que contenha... ", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		lbNome.setVisible(false);
		
		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);
		txtNome.setVisible(false);
		
		lbData = new JLabel("No período de...", JLabel.RIGHT);
		lbData.setPreferredSize(DLABEL);
		lbData.setMinimumSize(DLABEL);
		lbData.setVisible(false);
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -500);
		String dataIni = f.format(new Date(c.getTimeInMillis()));

		c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		String dataSel = f.format(new Date(c.getTimeInMillis()));

		String dataFin = f.format(new Date(Calendar.getInstance().getTimeInMillis()));

		spnDataIni = new DataSpinner(dataSel, dataIni, dataFin);
		spnDataIni.setPreferredSize(DFIELDM);
		spnDataIni.setMinimumSize(DFIELDM);

		spnDataFin = new DataSpinner(dataFin, dataIni, dataFin);
		spnDataFin.setPreferredSize(DFIELDM);
		spnDataFin.setMinimumSize(DFIELDM);

		panelPeriodo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelPeriodo.add(spnDataIni);
		panelPeriodo.add(new JLabel(" a "));
		panelPeriodo.add(spnDataFin);
		panelPeriodo.setMinimumSize(new Dimension(200, 22));
		panelPeriodo.setBorder(BorderFactory.createEmptyBorder());
		panelPeriodo.setVisible(false);
		
			
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

		c.gridy = 0; infServico.add(lbOpcoes, c);
		c.gridy = 1; infServico.add(lbNome, c);
		c.gridy = 2; infServico.add(lbContrato, c);
		c.gridy = 3; infServico.add(lbData, c);
		c.gridy = 4; infServico.add(lbCidade, c);
		c.gridy = 5; infServico.add(lbTipo, c);
							
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.NONE;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infServico.add(comboOpcoes, c);
		c.gridy = 1; infServico.add(txtNome, c);
		c.gridy = 2; infServico.add(txtContrato, c);
		c.gridy = 3; infServico.add(panelPeriodo, c);
		c.gridy = 4; infServico.add(cbCidades, c);
		c.gridy = 5; infServico.add(panelRadios, c);
				
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
			if (c instanceof ObitoMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	

	private class EventoComboOpcoes implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			
			boolean showContrato	= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[CONTRATO];
			boolean showNome		= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[NOME];
			boolean showData		= e.getItem() != OPCOES_CONSULTA[QUALQUER] && e.getItem() == OPCOES_CONSULTA[DATA];

			lbContrato.setVisible(showContrato);
			lbNome.setVisible(showNome);
			lbData.setVisible(showData);
	
			txtContrato.setVisible(showContrato);
			txtNome.setVisible(showNome);
			panelPeriodo.setVisible(showData);
			
			Component c = getPainelPrincipal();
			try{
				((ApoliceMainPanel)c).getIframeConsultaApolice().pack();
			}
			catch(ClassCastException cex){}
		}
	}
		
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ObitoMainPanel c = (ObitoMainPanel)getPainelPrincipal();
			if (e.getSource() == btCancelar){
				c.getIFrameConsObito().setVisible(false);
			}
			else if (e.getSource() == btEnviar){
				try{
					validarCamposPesquisa();
					consultaObitos();
				}catch (MensagemSaoDimasException ex) {
					barNotificacao.mostrarMensagem(ex.getMessage(), BarraNotificacao.ERRO);
				}
			}
		}
	}

	private String[] getParamentros()
	{
		List<String> p = new ArrayList<String>();
		String[] parametros = null;

		if(comboOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[NOME]))
			p.add(txtNome.getText());
		else if(comboOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[CONTRATO]))	
			p.add(txtContrato.getText());
	    else if(comboOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[DATA])){
			p.add(new SimpleDateFormat("yyyy/MM/dd").format((Date)spnDataIni.getValue()));
	 		p.add(new SimpleDateFormat("yyyy/MM/dd").format((Date)spnDataFin.getValue()));
		}
		else
			p = null;

		if(p != null){
			parametros = new String[p.size()];
			p.toArray(parametros);
		}
		return parametros;
	}
	
	private TipoAtendimentoVO getSelectedTipoAtendimento(){
		if(btnRadioTodos.isSelected()) return null;
		else
			for(TipoAtendimentoVO tipo : listTipoAtendimentos)
			{
				if(this.listRadionsButosTipoAtendimento.get(tipo.getId()).isSelected())
					return tipo;
			}
		return null;

	}
	
	private void consultaObitos(){
		ObitoMainPanel c = (ObitoMainPanel)getPainelPrincipal();
		String tipo = comboOpcoes.getSelectedItem().toString();
		
		List <ObitoVO> obitoList = Controller.getInstance().consultaObitos(tipo, getParamentros(), (CidadeVO)cbCidades.getSelectedItem(), getSelectedTipoAtendimento());
		
		if (obitoList.size() > 0){
			c.carregarObitoTable(obitoList);
			c.mostrarMensagem("Consulta efetuada com sucesso! " + (obitoList.size() == 1 ? " Apenas 1 obito encontrado ..." : obitoList.size() + " obitos encontrados ..."), BarraNotificacao.SUCESSO);
			c.getIFrameConsObito().setVisible(false);
		}
		else{
			c.carregarObitoTable(null);
			c.mostrarMensagem("", BarraNotificacao.DEFAULT);
			barNotificacao.mostrarMensagem("Nenhum obito encontrado com esses critérios...", BarraNotificacao.ERRO);
		}
	}
	
	private void validarCamposPesquisa()throws MensagemSaoDimasException
	{
		if(comboOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[NOME]) && (txtNome.getText() == null || txtNome.getText().trim().equals("")))
				throw new MensagemSaoDimasException("Você deve informar um nome para busca."); 
		
		else if(comboOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[CONTRATO]) && (txtContrato.getText() == null || txtContrato.getText().trim().equals("")))
			throw new MensagemSaoDimasException("Você deve informar numero apolice.");
	
		else if(comboOpcoes.getSelectedItem().toString().equals(OPCOES_CONSULTA[DATA]) && (spnDataFin.getValue() == null || spnDataIni.getValue()==null)){
			throw new MensagemSaoDimasException("Você deve informar as data inicio e/ou fim");
		}
	}
	
	
	
}
