package br.com.saodimas.view.components.panel.apolice.editar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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

@SuppressWarnings("serial")
public class ApoliceEditarTitularPanel extends JPanel {
	public static final String FORM_NAME = "Titular";

	private ApoliceVO apolice;
	private JTextField txtNome;
	private CPFTextField txtCPF;
	private JTextField txtRG;
	private DataTextField spnDataNascimento;
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
	private ApoliceTitularListener titularListener;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);

	
	public ApoliceEditarTitularPanel(BarraNotificacao bar) {
		this.barNotificacao = bar;
		initialize();
		configure();
	}

	public void atulizadaDadosApolice()
	{
		if (apolice.getTitular() != null){
			apolice.getTitular().setNome(txtNome.getText());
			apolice.getTitular().setDataNascimento(this.parseDate(spnDataNascimento.getText()));
			apolice.getTitular().setSexo(rdMasculino.isSelected() ? 'M' : 'F');
			apolice.getTitular().setApolice(apolice);
			apolice.getTitular().setCPF(txtCPF.getText());
			apolice.getTitular().setRg(txtRG.getText());
		}
	}

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag) txtNome.requestFocus();
	}


	public AssociadoVO getAssociado() {
		return apolice.getTitular();
	}

	public void setApolice(ApoliceVO a) {
		
		if(a != null){
			apolice = a;
			AssociadoVO titular = apolice.getTitular();
			removeListeners();
	
			String nome = (titular == null ? "" : titular.getNome());
			String cpf = (titular == null ? "" : titular.getCPF());
			String rg = (titular == null ? "" : titular.getRg());
			String dataNasc = (titular == null ? spnDataNascimento.getText() : this.parseDate(titular.getDataNascimento()));
			char sexo = (titular == null ? 'M' : titular.getSexo());
			txtNome.setText(nome);
			txtCPF.setText(cpf);
			txtRG.setText(rg);
			if(dataNasc != null)
				spnDataNascimento.setText(dataNasc);
			rdFeminino.setSelected(sexo == 'F');
			rdMasculino.setSelected(sexo == 'M');
			
			addListeners();
		}
	}

	@Override
	public void requestFocus() {
		txtNome.requestFocus();
	}

	private void initialize() {
		titularListener = new ApoliceTitularListener();

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

		lbRG = new JLabel("RG.: ", JLabel.RIGHT);
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

		spnDataNascimento = new DataTextField();
		spnDataNascimento.setPreferredSize(DFIELDM);
		spnDataNascimento.setMinimumSize(DFIELDM);

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
		btErroDataNasc = new ErrorButton(spnDataNascimento, barNotificacao);
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
		c.gridy = 4; infPessoais.add(spnDataNascimento, c);

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 0; infPessoais.add(btErroNome, c);
		c.gridy = 1; infPessoais.add(btErroCPF, c);
		c.gridy = 3; infPessoais.add(btErroDataNasc, c);

		infPessoais.setBorder(BorderFactory.createTitledBorder("Associado"));

		JPanel panelPrincipal = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(1, 1, 1, 1);
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 0.4;
		c.gridy = 0; panelPrincipal.add(infPessoais, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.6;
		c.gridx = 0;
		c.gridy = 2; panelPrincipal.add(new JLabel(), c);		

		setLayout(new BorderLayout());

		JLabel lbTitulo = new JLabel("Titular ou Responsável", JLabel.LEADING);
		lbTitulo.setLayout(null);
		lbTitulo.setBackground(new Color(255, 255, 255));
		lbTitulo.setFont(lbTitulo.getFont().deriveFont(Font.BOLD, 12));
		lbTitulo.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				BorderFactory.createEmptyBorder(2, 0, 3, 1)
		)
		);
		add(lbTitulo, BorderLayout.NORTH);
		add(panelPrincipal, BorderLayout.CENTER);
	}

	private void addListeners(){
		txtNome.addFocusListener(titularListener);
		txtCPF.addFocusListener(titularListener);
		txtRG.addFocusListener(titularListener);
		rdFeminino.addFocusListener(titularListener);
		rdMasculino.addFocusListener(titularListener);
		spnDataNascimento.addFocusListener(titularListener);
	}

	private void removeListeners(){
		txtNome.removeFocusListener(titularListener);
		txtCPF.removeFocusListener(titularListener);
		txtRG.removeFocusListener(titularListener);
		rdFeminino.removeFocusListener(titularListener);
		rdMasculino.removeFocusListener(titularListener);
		spnDataNascimento.removeFocusListener(titularListener);
	}

	private ApoliceEditarMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceEditarMainPanel) break;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel)c;
	}

	private class ApoliceTitularListener extends FocusAdapter{
		public void apoliceChange() {
			if (apolice != null ){
				boolean hasChanged = getPainelPrincipal().hasChanged();
				String nome = (apolice.getTitular() == null ? "" : (apolice.getTitular().getNome() == null ? "" : apolice.getTitular().getNome()));
				String cpf = (apolice.getTitular() == null ? "" : (apolice.getTitular().getCPF() == null ? "" : apolice.getTitular().getCPF()));
				String rg = (apolice.getTitular() == null ? "" : (apolice.getTitular().getRg() == null ? "" : apolice.getTitular().getRg()));
				String dataNasc = (apolice.getTitular() == null ? spnDataNascimento.getText() : parseDate(apolice.getTitular().getDataNascimento()));
				String dataDefault = spnDataNascimento.getText();
				char sexo = (apolice.getTitular() == null ? 'M' : apolice.getTitular().getSexo());

				hasChanged = hasChanged || (nome.compareTo(txtNome.getText()) != 0);
				hasChanged = hasChanged || (cpf.compareTo(txtCPF.getText()) != 0);
				hasChanged = hasChanged || (rg.compareTo(txtRG.getText()) != 0);
				hasChanged = hasChanged || (sexo != 'F' && rdFeminino.isSelected());
				hasChanged = hasChanged || (sexo != 'M' && rdMasculino.isSelected());
				hasChanged = hasChanged || (dataDefault.compareTo(dataNasc) != 0);
				if (hasChanged)
					barNotificacao.mostrarMensagem("Alterações foram efetuadas!", BarraNotificacao.INFO);
				getPainelPrincipal().setChanged(hasChanged);
			}
		}

		public void focusGained(FocusEvent e) {
			apoliceChange();
			super.focusGained(e);
		}

		public void focusLost(FocusEvent e) {
			apoliceChange();
			super.focusLost(e);
		}
	}
	
	private Date parseDate(String strData){
		if(strData == null || strData.trim().equals(""))
			return null;
		SimpleDateFormat st = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return st.parse(strData);
		} catch (ParseException e) {
			barNotificacao.mostrarMensagem("Data nascimento inválida!", BarraNotificacao.ERRO);
			e.printStackTrace();
		}
		return null;
	}
	
	private String parseDate(Date data){
		if(data == null)
			return "";
		SimpleDateFormat st = new SimpleDateFormat("dd/MM/yyyy");
		return st.format(data);
	}
}
