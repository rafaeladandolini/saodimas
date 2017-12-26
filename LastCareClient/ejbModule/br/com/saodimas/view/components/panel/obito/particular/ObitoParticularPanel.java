package br.com.saodimas.view.components.panel.obito.particular;

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
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.CemiterioVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.model.beans.GrupoTrabalhoVO;
import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.TipoAtendimentoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.obito.ObitoMainPanel;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.util.ListasComuns;

@SuppressWarnings("serial")
public class ObitoParticularPanel extends JPanel {
	private static final String MENSAGEM_PADRAO = "   (*) Preenchimento obrigatório";
	
	private ObitoVO obito;
	private BarraNotificacao barNotificacao;
	private JLabel txtNumero;
	private JTextField txtNomeFalecido;
	private DataTextField spnDataObito;
	private JTextField txtCausaObito;
	private JTextField txtAtestadoObito;
	private DataTextField spnDataExpedicao;
	private JTextField txtLocalFalecimento;
	private JComboBox<String> cbEstadoFalecimento;
	private JComboBox cbCidadeFalecimento;
	private JTextField txtLocalVelorio;
	private JComboBox<String> cbEstadoVelorio;
	private JComboBox cbCidadeVelorio;
	private JComboBox cbCemiterioSepultamento;
	private JComboBox cbGrupoTrabalhoViagem;
	private JComboBox cbGrupoTrabalhoCortejo;
	private DataTextField spnDataSepultamento;
	private JTextField txtHoraSepultamento;
	private JComboBox<TipoAtendimentoVO> cbTipoAtendimento;
	
	private JButton btCancelar;
	private JButton btSalvar;
	
	private JLabel lbNumero;
	private JLabel lbTipoAtendimento;
	private JLabel lbAssociado;
	private JLabel lbDataObito;
	private JLabel lbCausaObito;
	private JLabel lbAtestadoObito;
	private JLabel lbDataExpedicao;
	
	private JLabel lbLocalFalecimento;
	private JLabel lbCidadeFalecimento;
	private JLabel lbLocalVelorio;
	private JLabel lbCidadeVelorio;
	private JLabel lbCemiterioSepultamento;
	private JLabel lbMotoristaViagem;
	private JLabel lbMotoristaCortejo;
	private JLabel lbDataSepultamento;
	private JLabel lbHoraSepultamento;
	
		
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public ObitoParticularPanel() {
		initialize();
		configure();
	}

	public void setObito(ObitoVO obito) {
		this.obito = obito;
		this.carregarGrupoTrabalhos();
		this.carregarCemiterios();
		this.carregarTipoAtendimento();
		
		if(obito != null)
		{
				cbTipoAtendimento.setSelectedItem(obito.getTipoAtendimento());		
				txtNumero.setText(obito.getId().toString());
				txtCausaObito.setText(obito.getCausa());
				txtAtestadoObito.setText(obito.getNumeroAtestado());
				txtNomeFalecido.setText(obito.getNomeFalecido());
				spnDataObito.setText(formataData(obito.getData()));
				spnDataExpedicao.setText(formataData(obito.getDataExpedicao()));
								
				Object objCidadeF = obito.getCidadeFalecimento();
				Object objCidadeV = obito.getCidadeVelorio();
				Object objCemiterio = obito.getCemiterio();
				Object objMotoristaV = obito.getGrupoTrabalhoViagem();
				Object objMotoristaC = obito.getGrupoTrabalhoCortejo();
				
				
				txtLocalFalecimento.setText(obito.getLocalFalecimento());
				txtLocalVelorio.setText(obito.getLocalVelorio());
				
				if(objCidadeF instanceof CidadeVO){
					cbEstadoFalecimento.setSelectedItem(((CidadeVO)objCidadeF).getEstado());
					this.carregarCidadeByEstadoFalecimento(((CidadeVO)objCidadeF).getEstado());
					cbCidadeFalecimento.setSelectedItem((CidadeVO)objCidadeF);
					
				}
				if(objCidadeV instanceof CidadeVO){
					cbEstadoFalecimento.setSelectedItem(((CidadeVO)objCidadeV).getEstado());
					this.carregarCidadeByEstadoVelorio(((CidadeVO)objCidadeV).getEstado());
					cbCidadeVelorio.setSelectedItem((CidadeVO) objCidadeV);
				}
				if(objCemiterio instanceof CemiterioVO)
					cbCemiterioSepultamento.setSelectedItem((CemiterioVO) objCemiterio);
				if(objMotoristaV instanceof GrupoTrabalhoVO)
					cbGrupoTrabalhoViagem.setSelectedItem((GrupoTrabalhoVO)  objMotoristaV);
				if(objMotoristaC instanceof GrupoTrabalhoVO)
					cbGrupoTrabalhoCortejo.setSelectedItem((GrupoTrabalhoVO) objMotoristaC);
				
				txtHoraSepultamento.setText(obito.getHoraSepultamento());
				spnDataSepultamento.setText(obito.getDataSepultamento() != null ? formataData(obito.getDataSepultamento()) : "");
						
		}else{
			this.carregarCidadeByEstadoFalecimento("PR");
			this.carregarCidadeByEstadoVelorio("PR");
			limparCampos();
		}
		
		
		
	}
	
	public void colocarModoVisualizacao(boolean isTelaConsulta)
	{
		this.txtNomeFalecido.setEnabled(!isTelaConsulta);
		this.spnDataObito.setEditable(!isTelaConsulta);
		this.txtCausaObito.setEditable(!isTelaConsulta);
		this.txtAtestadoObito.setEditable(!isTelaConsulta);
		this.spnDataExpedicao.setEditable(!isTelaConsulta);
		this.txtLocalFalecimento.setEditable(!isTelaConsulta);;
		this.cbCidadeFalecimento.setEnabled(!isTelaConsulta);
		this.txtLocalVelorio.setEditable(!isTelaConsulta);
		this.cbCidadeVelorio.setEnabled(!isTelaConsulta);
		this.cbCemiterioSepultamento.setEnabled(!isTelaConsulta);
		this.cbGrupoTrabalhoViagem.setEnabled(!isTelaConsulta);
		this.cbGrupoTrabalhoCortejo.setEnabled(!isTelaConsulta);
		this.spnDataSepultamento.setEnabled(!isTelaConsulta);
		this.txtHoraSepultamento.setEditable(!isTelaConsulta);
		this.cbTipoAtendimento.setEnabled(!isTelaConsulta);
				
		this.btCancelar.setText(isTelaConsulta ? "Fechar" : "Cancelar");
		this.btSalvar.setVisible(!isTelaConsulta);
		
		
	}
	
		
	private void carregarGrupoTrabalhos()
	{
		List<GrupoTrabalhoVO> list = Controller.getInstance().getGrupoTrabalhoAll();
		cbGrupoTrabalhoCortejo.removeAllItems();
		cbGrupoTrabalhoViagem.removeAllItems();
		
		String colNenhum = null;
		cbGrupoTrabalhoCortejo.addItem(colNenhum);
		cbGrupoTrabalhoViagem.addItem(colNenhum);
		
		for (int i=0; i< list.size(); i++) {
			cbGrupoTrabalhoCortejo.addItem(list.get(i));
			cbGrupoTrabalhoViagem.addItem(list.get(i));
		}
		
	}
	
	private void carregarCemiterios() 
	{
		List<CemiterioVO> list = Controller.getInstance().getCemiteriosAll();
		cbCemiterioSepultamento.removeAllItems();

		String colNenhum = null;
		cbCemiterioSepultamento.addItem(colNenhum);

		for (int i = 0; i < list.size(); i++) {
			cbCemiterioSepultamento.addItem(list.get(i));

		}

	}
	
	private void carregarTipoAtendimento()
	{
		cbTipoAtendimento.removeAllItems();
		List<TipoAtendimentoVO> list = Controller.getInstance().getAllTiposAtendimentoVOs();
		for (int i = 0; i < list.size(); i++) {
			if(!list.get(i).getId().equals(1))// nao colocar a opcao de plano
				cbTipoAtendimento.addItem(list.get(i));
		}
	}
	
	public void limparCampos() {
		txtCausaObito.setText("");
		txtAtestadoObito.setText("");
		spnDataObito.setText("");
		spnDataExpedicao.setText("");
		txtLocalFalecimento.setText("");
		cbCidadeFalecimento.setSelectedIndex(0);
		txtLocalVelorio.setText("");
		cbCidadeVelorio.setSelectedIndex(0);
		cbCemiterioSepultamento.setSelectedIndex(0);
		cbGrupoTrabalhoViagem.setSelectedIndex(0);
		cbGrupoTrabalhoCortejo.setSelectedIndex(0);
		txtHoraSepultamento.setText("");
		spnDataSepultamento.setText("");
		txtNumero.setText("");
	}
	

		
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);
				
		lbNumero = new JLabel("Numero: *", JLabel.RIGHT);
		lbNumero.setPreferredSize(DLABEL);
		lbNumero.setMinimumSize(DLABEL);
		
		txtNumero = new JLabel("");
		
		lbTipoAtendimento = new JLabel("Tipo Atendimento: *", JLabel.RIGHT);
		lbTipoAtendimento.setPreferredSize(DLABEL);
		lbTipoAtendimento.setMinimumSize(DLABEL);
		
		cbTipoAtendimento = new JComboBox<TipoAtendimentoVO>();
		
		lbAssociado = new JLabel("Nome: *", JLabel.RIGHT);
		lbAssociado.setPreferredSize(DLABEL);
		lbAssociado.setMinimumSize(DLABEL);

		txtNomeFalecido = new JTextField();
		
		lbDataObito= new JLabel("Data do óbito: *", JLabel.RIGHT);
		lbDataObito.setPreferredSize(DLABEL);
		lbDataObito.setMinimumSize(DLABEL);
				
		spnDataObito = new DataTextField();
		spnDataObito.setPreferredSize(DFIELDM);
		spnDataObito.setMinimumSize(DFIELDM);

		lbCausaObito = new JLabel("Causa do óbito: ", JLabel.RIGHT);
		lbCausaObito.setPreferredSize(DLABEL);
		lbCausaObito.setMinimumSize(DLABEL);
		
		CustomDocument causaDoc = new CustomDocument(50);
		txtCausaObito = new JTextField();
		txtCausaObito.setDocument(causaDoc);
		txtCausaObito.setPreferredSize(DFIELDM);
		
		lbAtestadoObito = new JLabel("Nº Atestado de óbito: ", JLabel.RIGHT);
		lbAtestadoObito.setPreferredSize(DLABEL);
		lbAtestadoObito.setMinimumSize(DLABEL);

		CustomDocument atestadoDoc = new CustomDocument(15);
		txtAtestadoObito = new JTextField();
		txtAtestadoObito.setDocument(atestadoDoc);
		txtAtestadoObito.setPreferredSize(DFIELDM);
		
		
		lbDataExpedicao= new JLabel("Expedido em: ", JLabel.RIGHT);
		lbDataExpedicao.setPreferredSize(DLABEL);
		lbDataExpedicao.setMinimumSize(DLABEL);
		
		
		spnDataExpedicao = new DataTextField();
		spnDataExpedicao.setPreferredSize(DFIELDM);
		spnDataExpedicao.setMinimumSize(DFIELDM);
		
		
		lbLocalFalecimento = new JLabel("Local Falecimento: ", JLabel.RIGHT);
		lbLocalFalecimento.setPreferredSize(DLABEL);
		lbLocalFalecimento.setMinimumSize(DLABEL);
		
		CustomDocument cLocal = new CustomDocument(100);
		txtLocalFalecimento = new JTextField();
		txtLocalFalecimento.setDocument(cLocal);
		txtLocalFalecimento.setPreferredSize(DFIELDM);
		
		
		lbCidadeFalecimento = new JLabel("Cidade Falecimento: ", JLabel.RIGHT);
		lbCidadeFalecimento.setPreferredSize(DLABEL);
		lbCidadeFalecimento.setMinimumSize(DLABEL);
		
		cbEstadoFalecimento = new JComboBox(ListasComuns.ESTADOS);
		cbEstadoFalecimento.setSelectedItem("PR");
		cbEstadoFalecimento.addItemListener(new EventoBotaoControle());
		
		cbCidadeFalecimento = new JComboBox<CidadeVO>();
		cbCidadeFalecimento.setMinimumSize(DFIELDM);
		
		
		lbLocalVelorio = new JLabel("Local Velório: ", JLabel.RIGHT);
		lbLocalVelorio.setPreferredSize(DLABEL);
		lbLocalVelorio.setMinimumSize(DLABEL);
		
		CustomDocument cLocalVelorio = new CustomDocument(100);
		txtLocalVelorio =  new JTextField();
		txtLocalVelorio.setPreferredSize(DFIELDM);
		txtLocalVelorio.setDocument(cLocalVelorio);
		
		lbCidadeVelorio = new JLabel("Cidade do Velório: ", JLabel.RIGHT);
		lbCidadeVelorio.setPreferredSize(DLABEL);
		lbCidadeVelorio.setMinimumSize(DLABEL);
		
		cbEstadoVelorio = new JComboBox(ListasComuns.ESTADOS);
		cbEstadoVelorio.setSelectedItem("PR");
		cbEstadoVelorio.addItemListener(new EventoBotaoControle());
		
		cbCidadeVelorio = new JComboBox<CidadeVO>();
		cbCidadeVelorio.setMinimumSize(DFIELDM);
		
		lbCemiterioSepultamento = new JLabel("Cemitério Sepultamento: ", JLabel.RIGHT);
		lbCemiterioSepultamento.setPreferredSize(DLABEL);
		lbCemiterioSepultamento.setMinimumSize(DLABEL);
		
		cbCemiterioSepultamento = new JComboBox<String>();
		cbCemiterioSepultamento.setMinimumSize(DFIELDM);
		
		lbMotoristaViagem = new JLabel("Motorista da Translado: ", JLabel.RIGHT);
		lbMotoristaViagem.setPreferredSize(DLABEL);
		lbMotoristaViagem.setMinimumSize(DLABEL);
		
		cbGrupoTrabalhoViagem = new JComboBox<GrupoTrabalhoVO>();
		cbGrupoTrabalhoViagem.setMinimumSize(DFIELDM);
		
		lbMotoristaCortejo = new JLabel("Motorista Cortejo: ", JLabel.RIGHT);
		lbMotoristaCortejo.setPreferredSize(DLABEL);
		lbMotoristaCortejo.setMinimumSize(DLABEL);
		
		cbGrupoTrabalhoCortejo =  new JComboBox<GrupoTrabalhoVO>();
		cbGrupoTrabalhoCortejo.setMinimumSize(DFIELDM);

		lbDataSepultamento = new JLabel("Data Sepultamento: ", JLabel.RIGHT);
		lbDataSepultamento.setPreferredSize(DLABEL);
		lbDataSepultamento.setMinimumSize(DLABEL);
		
		spnDataSepultamento = new DataTextField();
		spnDataSepultamento.setPreferredSize(DFIELDM);
		spnDataSepultamento.setMinimumSize(DFIELDM);
		
		lbHoraSepultamento = new JLabel("Hora Sepultamento: ", JLabel.RIGHT);
		lbHoraSepultamento.setPreferredSize(DLABEL);
		lbHoraSepultamento.setMinimumSize(DLABEL);
		
		CustomDocument cHora = new CustomDocument(5);
		txtHoraSepultamento = new JTextField();
		txtHoraSepultamento.setDocument(cHora);
		txtHoraSepultamento.setPreferredSize(DFIELDM);
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		
		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(new EventoBotaoControle());
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		
		
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infObito = new JPanel(new GridBagLayout());
	
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.gridy = 0; infObito.add(lbNumero, c);
		c.gridy = 1; infObito.add(lbTipoAtendimento, c);
		c.gridy = 2; infObito.add(lbAssociado, c);
		c.gridy = 3; infObito.add(lbDataObito, c);
		c.gridy = 4; infObito.add(lbDataExpedicao, c);
		c.gridy = 5; infObito.add(lbCausaObito, c);
		c.gridy = 6; infObito.add(lbAtestadoObito, c);
		c.gridy = 7; infObito.add(new JLabel(""), c);
		c.gridy = 8; infObito.add(lbLocalFalecimento, c);
		c.gridy = 9; infObito.add(lbCidadeFalecimento, c);
		c.gridy = 10; infObito.add(lbMotoristaViagem, c);
		c.gridy = 11; infObito.add(new JLabel(""), c);
		c.gridy = 12; infObito.add(lbLocalVelorio, c);
		c.gridy = 13; infObito.add(lbCidadeVelorio, c);
		c.gridy = 14; infObito.add(lbCemiterioSepultamento, c);
		c.gridy = 15; infObito.add(lbMotoristaCortejo, c);
		c.gridy = 16; infObito.add(lbDataSepultamento, c);
		c.gridy = 17; infObito.add(lbHoraSepultamento, c);
				
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		
		JPanel panelEstaoFalecimento = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0; panelEstaoFalecimento.add(cbEstadoFalecimento, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1; panelEstaoFalecimento.add(cbCidadeFalecimento, c);
		
		JPanel panelEstadoVelorio = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0; panelEstadoVelorio.add(cbEstadoVelorio,c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1; panelEstadoVelorio.add(cbCidadeVelorio,c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infObito.add(txtNumero, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1; infObito.add(cbTipoAtendimento, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 2; infObito.add(txtNomeFalecido, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 3; infObito.add(spnDataObito, c);
		c.gridy = 4; infObito.add(spnDataExpedicao, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 5; infObito.add(txtCausaObito, c);
		c.gridy = 6; infObito.add(txtAtestadoObito, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 7; infObito.add(new JLabel(" "), c);
		c.gridy = 8; infObito.add(txtLocalFalecimento, c);
		//c.fill = GridBagConstraints.NONE;
		c.gridy = 9; infObito.add(panelEstaoFalecimento, c);
		c.gridy = 10; infObito.add(cbGrupoTrabalhoViagem, c);
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 11; infObito.add(new JLabel(" "), c);
		c.gridy = 12; infObito.add(txtLocalVelorio, c);
		//c.fill = GridBagConstraints.NONE;
		c.gridy = 13; infObito.add(panelEstadoVelorio, c);
		c.gridy = 14; infObito.add(cbCemiterioSepultamento, c);
		c.gridy = 15; infObito.add(cbGrupoTrabalhoCortejo, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 16; infObito.add(spnDataSepultamento, c);
		c.gridy = 17; infObito.add(txtHoraSepultamento, c);
		
			
		infObito.setBorder(BorderFactory.createTitledBorder("Registro de Óbito"));
		adicionarAtalhosComandos(infObito);
	
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSalvar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);
		
		JPanel panelObito = new JPanel(new BorderLayout()); 
		panelObito.add(infObito, BorderLayout.CENTER);
		panelObito.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(panelObito);
		
		JPanel pObito = new JPanel(new BorderLayout());
		pObito.add(barNotificacao, BorderLayout.NORTH);
		pObito.add(panelObito, BorderLayout.CENTER);
		pObito.add(controle, BorderLayout.SOUTH);
		adicionarAtalhosComandos(pObito);
		
		JPanel formPrincipal = new JPanel(new FlowLayout(FlowLayout.LEADING));
		formPrincipal.add(pObito);
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

	private ObitoMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ObitoMainPanel) return (ObitoMainPanel)c;
			c = c.getParent();
		}
		return (ObitoMainPanel)c;
	}	

	private class EventoBotaoControle implements ActionListener, ItemListener{
		
		public void actionPerformed(ActionEvent e) {
			ObitoMainPanel c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				if (c != null){
					c.getIframeObitoPartifular().setVisible(false);
				}
			} 
			else 
				if (e.getSource() == btSalvar){
				
				try{
					if(obito != null)
					{
						obito.setTipoAtendimento((TipoAtendimentoVO)cbTipoAtendimento.getSelectedItem());
						obito.setAssociado(null);
						obito.setNomeFalecido(txtNomeFalecido.getText());
						obito.setCausa(txtCausaObito.getText());
						obito.setData(transformarData(spnDataObito.getText()));
						
						obito.setDataExpedicao(transformarData(spnDataExpedicao.getText()));
						obito.setNumeroAtestado(txtAtestadoObito.getText());
						
						obito.setLocalFalecimento(txtLocalFalecimento.getText());
						obito.setCidadeFalecimento((CidadeVO)cbCidadeFalecimento.getSelectedItem());
						obito.setGrupoTrabalhoViagem((GrupoTrabalhoVO) cbGrupoTrabalhoViagem.getSelectedItem());
						obito.setLocalVelorio(txtLocalVelorio.getText());
						obito.setCidadeVelorio((CidadeVO)cbCidadeVelorio.getSelectedItem());
						obito.setGrupoTrabalhoCortejo((GrupoTrabalhoVO ) cbGrupoTrabalhoCortejo.getSelectedItem());
						obito.setCemiterio((CemiterioVO)cbCemiterioSepultamento.getSelectedItem());
						
								
						obito.setDataSepultamento((transformarData(spnDataSepultamento.getText())));
						obito.setHoraSepultamento((txtHoraSepultamento.getText()));
						
						Controller.getInstance().updateObito(obito);
						c.mostrarMensagem("Alteração realizada com sucesso!", BarraNotificacao.SUCESSO);
						
					}else
					{
						ObitoVO novoobito = new ObitoVO();
						
						novoobito.setTipoAtendimento((TipoAtendimentoVO)cbTipoAtendimento.getSelectedItem());
						novoobito.setAssociado(null);
						novoobito.setNomeFalecido(txtNomeFalecido.getText());
						novoobito.setCausa(txtCausaObito.getText());
						novoobito.setData(transformarData(spnDataObito.getText()));
						novoobito.setDataExpedicao(transformarData(spnDataExpedicao.getText()));
						novoobito.setNumeroAtestado(txtAtestadoObito.getText());
						
						novoobito.setLocalFalecimento(txtLocalFalecimento.getText());
						novoobito.setCidadeFalecimento((CidadeVO)cbCidadeFalecimento.getSelectedItem());
						novoobito.setGrupoTrabalhoViagem((GrupoTrabalhoVO ) cbGrupoTrabalhoViagem.getSelectedItem());
						novoobito.setLocalVelorio(txtLocalVelorio.getText());
						novoobito.setCidadeVelorio((CidadeVO)cbCidadeVelorio.getSelectedItem());
						novoobito.setGrupoTrabalhoCortejo((GrupoTrabalhoVO ) cbGrupoTrabalhoCortejo.getSelectedItem());
						novoobito.setCemiterio((CemiterioVO)cbCemiterioSepultamento.getSelectedItem());
							
						novoobito.setDataSepultamento((transformarData(spnDataSepultamento.getText())));
						novoobito.setHoraSepultamento((txtHoraSepultamento.getText()));
						
						Controller.getInstance().insertObito(novoobito);
						c.mostrarMensagem("Obito criado com sucesso!", BarraNotificacao.SUCESSO);
						
					}
				}catch (MensagemSaoDimasException ex) {
					ex.printStackTrace();					
					c.mostrarMensagem(ex.getMessage(), BarraNotificacao.ERRO);
				}
				
				c.getIframeObitoPartifular().setVisible(false);
				
			}

		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == cbEstadoFalecimento){
				String estado = e.getItem().toString();
				carregarCidadeByEstadoFalecimento(estado);
			}else if (e.getSource() == cbEstadoVelorio){
				String estado = e.getItem().toString();
				carregarCidadeByEstadoVelorio(estado);
			}
			
		}
	}
	
	private void carregarCidadeByEstadoVelorio(String estado)
	{
		List<CidadeVO> list = Controller.getInstance().getCidadeAllByEstado(estado);
		cbCidadeVelorio.removeAllItems();
		for (int i=0; i< list.size(); i++) {
			cbCidadeVelorio.addItem(list.get(i));
		}
	
	}
	
	private void carregarCidadeByEstadoFalecimento(String estado)
	{
		List<CidadeVO> list = Controller.getInstance().getCidadeAllByEstado(estado);
		cbCidadeFalecimento.removeAllItems();
		for (int i=0; i< list.size(); i++) {
			cbCidadeFalecimento.addItem(list.get(i));
		}
		
	}

	
	private Date transformarData(String data)
	{
		try{
			SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
			return simpledataformat.parse(data);
		}catch (ParseException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	private String formataData(Date date)
	{
		if(date != null){
			SimpleDateFormat simpledataformat = new SimpleDateFormat("dd/MM/yyyy");
			return simpledataformat.format(date);
		}else
			return "";
		
	}
}	