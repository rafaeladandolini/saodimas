package br.com.saodimas.view.components.panel.apolice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.view.components.button.ErrorButton;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.CPFTextField;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.util.validators.ValidaTitular;

@SuppressWarnings("serial")
public class ApoliceTitularPanel extends JPanel {
	public static final String FORM_NAME = "Titular";
	
	private ApoliceVO apolice;
	private JTextField txtNome;
	private CPFTextField txtCPF;
	private JTextField txtRG;
	private DataTextField txtDataNascimento;
	private JRadioButton rdMasculino;
	private JRadioButton rdFeminino;
	private ButtonGroup bgrSexo;
	
	private JLabel lbNome;
	private JLabel lbCPF;
	private JLabel lbRG;
	private JLabel lbDataNascimento;
	private JLabel lbSexo;
	
	private ErrorButton btErroNome;
	private ErrorButton btErroCPF;
	private ErrorButton btErroDataNasc;

	private BarraNotificacao barNotificacao;
	private ActionListener listenerControles;
	private JButton btVoltar;
	private JButton btAvancar;
	private JButton btSalvar;
	private JButton btCancelar;

	private int etapa;
	private int numEtapas;
	
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	public ApoliceTitularPanel(ActionListener listenerControles, int etapa, int numEtapas, BarraNotificacao  bar) {
		this.etapa = etapa;
		this.numEtapas = numEtapas;
		this.listenerControles = listenerControles;
		this.barNotificacao = bar;
		initialize();
		configure();
	}
	
	@Override
	public void setVisible(boolean flag) {
		if (flag) barNotificacao.mostrarMensagem("", BarraNotificacao.DEFAULT);
		else barNotificacao.escondeMensagem();
		
		super.setVisible(flag);
		if (flag){
			txtNome.requestFocus();
			
		}
	}


	public AssociadoVO getAssociado() {
		return apolice.getTitular();
	}

	public void setApolice(ApoliceVO apolice)
	{
		this.apolice = apolice;
	}
	
	public void limparCampos() {
		txtNome.setText("");
		txtCPF.setText("");
		txtRG.setText("");
		bgrSexo.setSelected(rdMasculino.getModel(), true);
	}
	
	@Override
	public void requestFocus() {
		txtNome.requestFocus();
	}

	private void initialize() {
		lbNome = new JLabel("Nome: *", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);
		
		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);
		
		lbCPF = new JLabel("C.P.F.: ", JLabel.RIGHT);
		lbCPF.setPreferredSize(DLABEL);
		lbCPF.setMinimumSize(DLABEL);
		
		txtCPF = new CPFTextField();
		txtCPF.setPreferredSize(DFIELDM);
		txtCPF.setMinimumSize(DFIELDM);
		
		lbRG = new JLabel("RG: ", JLabel.RIGHT);
		lbRG.setPreferredSize(DLABEL);
		lbRG.setMinimumSize(DLABEL);
		
		CustomDocument rgDoc = new CustomDocument(15);
		txtRG = new JTextField();
		txtRG.setPreferredSize(DFIELDM);
		txtRG.setDocument(rgDoc);
		txtRG.setMinimumSize(DFIELDM);
		
		lbDataNascimento = new JLabel("Data Nascimento: ", JLabel.RIGHT);
		lbDataNascimento.setPreferredSize(DLABEL);
		lbDataNascimento.setMinimumSize(DLABEL);
		
		txtDataNascimento = new DataTextField();
		txtDataNascimento.setPreferredSize(DFIELDM);
		txtDataNascimento.setMinimumSize(DFIELDM);
		
		lbSexo = new JLabel("Sexo: *", JLabel.RIGHT);
		lbSexo.setPreferredSize(DLABEL);
		lbSexo.setMinimumSize(DLABEL);
		
		rdMasculino = new JRadioButton("Masculino");
		rdFeminino = new JRadioButton("Feminino");
		bgrSexo = new ButtonGroup();
		bgrSexo.add(rdMasculino);
		bgrSexo.add(rdFeminino);
		bgrSexo.setSelected(rdMasculino.getModel(), true);
		
		btErroNome = new ErrorButton(txtNome, barNotificacao);
		btErroCPF = new ErrorButton(txtCPF, barNotificacao);
		btErroDataNasc = new ErrorButton(txtDataNascimento, barNotificacao);

		btVoltar = new JButton("Voltar", new ImageIcon("imagens/back.gif"));
		btVoltar.addActionListener(listenerControles);
		btVoltar.setHorizontalAlignment(JButton.LEFT);
		btVoltar.setActionCommand(ApolicePanel.ACAO_VOLTAR);
		
		btAvancar = new JButton("Próximo", new ImageIcon("imagens/next.gif"));
		btAvancar.addActionListener(new EventoBotaoComando());
		btAvancar.setHorizontalAlignment(JButton.LEFT);
		btAvancar.setActionCommand(ApolicePanel.ACAO_AVANCAR);

		btSalvar = new JButton("Salvar", new ImageIcon("imagens/save.gif"));
		btSalvar.addActionListener(listenerControles);
		btSalvar.setHorizontalAlignment(JButton.LEFT);
		btSalvar.setActionCommand(ApolicePanel.ACAO_SALVAR);		
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(listenerControles);
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		btCancelar.setActionCommand(ApolicePanel.ACAO_CANCELAR);
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infPessoais = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infPessoais.add(lbNome, c);
		c.gridy = 1; infPessoais.add(lbCPF, c);
		c.gridy = 2; infPessoais.add(lbRG, c);
		c.gridy = 3; infPessoais.add(lbSexo, c);
		c.gridy = 4; infPessoais.add(lbDataNascimento, c);
		
		JPanel panelSexo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelSexo.add(rdMasculino);
		panelSexo.add(rdFeminino);
		panelSexo.setMinimumSize(new Dimension(200, 22));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0; infPessoais.add(txtNome, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1; infPessoais.add(txtCPF, c);
		c.gridy = 2; infPessoais.add(txtRG, c);
		c.gridy = 3; infPessoais.add(panelSexo, c);
		c.weighty = 1;
		c.gridy = 4; infPessoais.add(txtDataNascimento, c);
				
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 0; infPessoais.add(btErroNome, c);
		c.gridy = 1; infPessoais.add(btErroCPF, c);
		c.gridy = 4; infPessoais.add(btErroDataNasc, c);
		
		infPessoais.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		setLayout(new BorderLayout());
		
		JLabel lbTitulo = new JLabel("Titular ou Responsável (" + etapa + "/" + numEtapas + ")", new ImageIcon("imagens/titular.gif"), JLabel.LEADING);
		lbTitulo.setLayout(null);
		lbTitulo.setBackground(new Color(255, 255, 255));
		lbTitulo.setFont(lbTitulo.getFont().deriveFont(Font.BOLD, 12));
		lbTitulo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				BorderFactory.createEmptyBorder(2, 0, 3, 1)
			)
		);
		
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		if (etapa > 1) controle.add(btVoltar);
		if (etapa == numEtapas) controle.add(btSalvar);
		else controle.add(btAvancar);
		controle.add(btCancelar);
		
		add(lbTitulo, BorderLayout.NORTH);
		add(infPessoais, BorderLayout.CENTER);
		add(controle, BorderLayout.SOUTH);
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	private class EventoBotaoComando implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btAvancar || e.getSource() == btSalvar){
				String erros = "";
				erros += ValidaTitular.validaNome(txtNome.getText());
				btErroNome.setMensagem(ValidaTitular.validaNome(txtNome.getText()));
				
				SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
				Date d = null;
				try{
				
					if(!txtDataNascimento.getText().trim().equals(""))
					d = f.parse(txtDataNascimento.getText());
//					String er = ValidaTitular.validaDataNasc(d);
//					erros += er;
//					btErroDataNasc.setMensagem(er);
				
				}catch (ParseException ex) {
					erros += "Data Inválida: "+ txtDataNascimento.getText();
					btErroDataNasc.setMensagem(erros);
				}
				

				if(txtCPF.getText() != null && txtCPF.getText().trim().length() > 0)
				{
					erros += ValidaTitular.validaCPF(txtCPF.getText());
					btErroCPF.setMensagem(ValidaTitular.validaCPF(txtCPF.getText()));
				}else{btErroCPF.setMensagem(null);}
				
				if (erros.length() > 0) barNotificacao.mostrarMensagem("Erro ao submeter os dados! Clique no botão de notificação para exibir a mensagem!", BarraNotificacao.AVISO);
				else{
					btErroNome.setMensagem(null);
					btErroDataNasc.setMensagem(null);
					btErroCPF.setMensagem(null);
					AssociadoVO titular = new AssociadoVO();
					titular.setNome(txtNome.getText());
					titular.setApolice(apolice);
					titular.setSexo(rdFeminino.isSelected()? 'F' : 'M');
					titular.setCPF(txtCPF.getText());
					titular.setRg(txtRG.getText());
					titular.setDataNascimento(d);
					titular.setStatus("Ativo");
					apolice.setTitular(titular);
						
					listenerControles.actionPerformed(e);
				}
			}
		}
	}

}
