package br.com.saodimas.view.components.panel.apolice.editar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JToolBar;
import javax.swing.SwingWorker;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.CidadeVO;
import br.com.saodimas.view.components.button.ErrorButton;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.text.CEPTextField;
import br.com.saodimas.view.components.text.FoneTextField;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.service.EnderecoService;
import br.com.saodimas.view.util.service.EnderecoVO;

@SuppressWarnings("serial")
public class ApoliceEditarContatoPanel extends JPanel {
	public static final String FORM_NAME = "Endereço";

	private ApoliceVO apolice;

	private FoneTextField txtTelefone;
	private FoneTextField txtCelular;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JComboBox cbCidade;
	private JComboBox cbEstado;
	private JToolBar barConsultaEndereco;
	private JButton btConsultaEndereco;
	private JLabel lbAguardeEndereco;
	private CEPTextField txtCep;
	private JTextArea txaComplemento;

	private JLabel lbTelefone;
	private JLabel lbCelular;
	private JLabel lbEmail;
	private JLabel lbEndereco;
	private JLabel lbBairro;
	private JLabel lbCidade;
	private JLabel lbEstado;
	private JLabel lbCep;
	private JLabel lbComplemento;

	private ErrorButton btErroTelefone;
	private ErrorButton btErroEndereco;
	private ErrorButton btErroBairro;
	private ErrorButton btErroCidade;
	private ErrorButton btErroEstado;

	private BarraNotificacao barNotificacao;
	private ApoliceContatoListener contatoListener;

	private boolean carrengando;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	

	public ApoliceEditarContatoPanel(BarraNotificacao bar) {
		this.barNotificacao = bar;
		initialize();
		configure();
	}

	public void atulizadaDadosApolice()
	{
		apolice.setTelefone(txtTelefone.getText());
		apolice.setCelular(txtCelular.getText());
		apolice.setEmail(txtEmail.getText());
		apolice.setEndereco(txtEndereco.getText());
		apolice.setBairro(txtBairro.getText());
		apolice.setCidade((CidadeVO)cbCidade.getSelectedItem());
		apolice.setCep(txtCep.getText());
		apolice.setCompEndereco(txaComplemento.getText());
		
	}

	@Override
	public void setVisible(boolean flag) {
		super.setVisible(flag);
		if (flag) txtTelefone.requestFocus();
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
		removeListeners();
		if (apolice != null){
			txtTelefone.setText(apolice.getTelefone());
			txtCelular.setText(apolice.getCelular());
			txtEmail.setText(apolice.getEmail());
			txtCep.setText(apolice.getCep());
			txtEndereco.setText(apolice.getEndereco());
			txtBairro.setText(apolice.getBairro());
			txaComplemento.setText(apolice.getCompEndereco());
			if (apolice.getCidade() != null){
				cbEstado.setSelectedItem(apolice.getCidade().getEstado());
				loadCidadeByEstado(cbEstado.getSelectedItem().toString());
				cbCidade.setSelectedItem(apolice.getCidade());
			}
			addListeners();
		}
	}
	
	@Override
	public void requestFocus() {
		txtTelefone.requestFocus();
	}

	private void initialize() {
		contatoListener = new ApoliceContatoListener();
		
		lbTelefone = new JLabel("Telefone: ", JLabel.RIGHT);
		lbTelefone.setPreferredSize(DLABEL);
		lbTelefone.setMinimumSize(DLABEL);

		txtTelefone = new FoneTextField();
		txtTelefone.setPreferredSize(DFIELDM);
		txtTelefone.setMinimumSize(DFIELDM);

		lbCelular = new JLabel("Celular:  ", JLabel.RIGHT);
		lbCelular.setPreferredSize(DLABEL);
		lbCelular.setMinimumSize(DLABEL);

		txtCelular = new FoneTextField();
		txtCelular.setPreferredSize(DFIELDM);
		txtCelular.setMinimumSize(DFIELDM);

		lbEmail = new JLabel("E-mail:  ", JLabel.RIGHT);
		lbEmail.setPreferredSize(DLABEL);
		lbEmail.setMinimumSize(DLABEL);

		CustomDocument mailDoc = new CustomDocument("^([\\p{Lower}\\p{Digit}\\.\\@\\-\\_]+)$", 100);
		txtEmail = new JTextField();
		txtEmail.setDocument(mailDoc);
		txtEmail.setPreferredSize(DFIELDM);
		txtEmail.setMinimumSize(DFIELDM);

		lbCep = new JLabel("C.E.P.:  ", JLabel.RIGHT);
		lbCep.setPreferredSize(DLABEL);
		lbCep.setMinimumSize(DLABEL);

		lbAguardeEndereco = new JLabel("Pesquisando...", new ImageIcon("imagens/wait.gif"), JLabel.HORIZONTAL);
		lbAguardeEndereco.setPreferredSize(new Dimension(105, 18));
		lbAguardeEndereco.setOpaque(false);
		lbAguardeEndereco.setVisible(false);

		btConsultaEndereco = new JButton();
		btConsultaEndereco.setIcon(new ImageIcon("imagens/search.png"));
		btConsultaEndereco.addActionListener(new EventoBotaoCEP());
		btConsultaEndereco.setToolTipText("Clique para pesquisar pelo CEP!");

		txtCep = new CEPTextField(btConsultaEndereco);
		txtCep.setPreferredSize(DFIELDM);

		barConsultaEndereco = new JToolBar();
		barConsultaEndereco.setFloatable(false);
		barConsultaEndereco.setOpaque(false);
		barConsultaEndereco.setBorderPainted(false);
		barConsultaEndereco.setBorder(BorderFactory.createEmptyBorder());
		barConsultaEndereco.setMargin(new Insets(0, 0, 0, 0));
		barConsultaEndereco.setPreferredSize(new Dimension(115, 22));
		barConsultaEndereco.setMinimumSize(new Dimension(115, 22));
		barConsultaEndereco.add(txtCep);
		barConsultaEndereco.add(btConsultaEndereco);
		barConsultaEndereco.add(lbAguardeEndereco);

		lbEndereco = new JLabel("Endereço: *", JLabel.RIGHT);
		lbEndereco.setPreferredSize(DLABEL);
		lbEndereco.setMinimumSize(DLABEL);

		txtEndereco = new JTextField();
		txtEndereco.setDocument(new CustomDocument(100));
		txtEndereco.setPreferredSize(DFIELDM);

		lbBairro = new JLabel("Bairro: *", JLabel.RIGHT);
		lbBairro.setPreferredSize(DLABEL);
		lbBairro.setMinimumSize(DLABEL);

		txtBairro = new JTextField();
		txtBairro.setDocument(new CustomDocument(20));
		txtBairro.setPreferredSize(DFIELDM);
		txtBairro.setMinimumSize(DFIELDM);

		lbCidade = new JLabel("Cidade: *", JLabel.RIGHT);
		lbCidade.setPreferredSize(DLABEL);
		lbCidade.setMinimumSize(DLABEL);

		cbCidade = new JComboBox();
		cbCidade.setPreferredSize(DFIELDM);

		lbEstado = new JLabel("Estado: *", JLabel.RIGHT);
		lbEstado.setPreferredSize(DLABEL);
		lbEstado.setMinimumSize(DLABEL);

		lbComplemento = new JLabel("Complemento: *", JLabel.RIGHT);
		lbComplemento.setPreferredSize(DLABEL);
		lbComplemento.setMinimumSize(DLABEL);
		
		txaComplemento = new JTextArea();
		txaComplemento.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txaComplemento.setDocument(new CustomDocument(255));
		
		cbEstado= new JComboBox(ListasComuns.ESTADOS);
		cbEstado.setSelectedItem("PR");
		cbEstado.addItemListener(new ApoliceContatoListener());

		btErroTelefone = new ErrorButton(txtTelefone, barNotificacao);
		btErroCidade = new ErrorButton(cbCidade, barNotificacao);
		btErroEstado = new ErrorButton(cbEstado, barNotificacao);
		btErroEndereco = new ErrorButton(txtEndereco, barNotificacao);
		btErroBairro = new ErrorButton(txtBairro, barNotificacao);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();

		c = new GridBagConstraints();
		JPanel infContato = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infContato.add(lbTelefone, c);
		c.gridy = 1; infContato.add(lbCelular, c);
		c.gridy = 2; infContato.add(lbEmail, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infContato.add(txtTelefone, c);
		c.gridy = 1; infContato.add(txtCelular, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		c.gridy = 2; infContato.add(txtEmail, c);

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 0; infContato.add(btErroTelefone, c);

		infContato.setBorder(BorderFactory.createTitledBorder("Contato"));

		c = new GridBagConstraints();
		JPanel infEndereco = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infEndereco.add(lbCep, c);
		c.gridy = 1; infEndereco.add(lbEstado, c);
		c.gridy = 2; infEndereco.add(lbCidade, c);
		c.gridy = 3; infEndereco.add(lbEndereco, c);
		c.gridy = 4; infEndereco.add(lbBairro, c);
		c.gridy = 5; infEndereco.add(lbComplemento, c);
		

		JScrollPane scrollComplemento = new JScrollPane(txaComplemento);
		scrollComplemento.setPreferredSize(new Dimension(50, 10));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infEndereco.add(barConsultaEndereco, c);
		c.gridy = 1; infEndereco.add(cbEstado, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 2; infEndereco.add(cbCidade, c);
		c.gridy = 3; infEndereco.add(txtEndereco, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 4; infEndereco.add(txtBairro, c);
		c.weighty = 1;
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridx = 1;
		c.gridy = 5; infEndereco.add(scrollComplemento, c);

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 1; infEndereco.add(btErroEstado, c);
		c.gridy = 2; infEndereco.add(btErroCidade, c);
		c.gridy = 3; infEndereco.add(btErroEndereco, c);
		c.gridy = 4; infEndereco.add(btErroBairro, c);

		infEndereco.setBorder(BorderFactory.createTitledBorder("Endereço"));

		JPanel panelPrincipal = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 0;
		c.gridy = 0; panelPrincipal.add(infContato, c);

		c.weighty = 0.4;
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1; panelPrincipal.add(infEndereco, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.6;
		c.gridx = 0;
		c.gridy = 2; panelPrincipal.add(new JLabel(), c);



		setLayout(new BorderLayout());

		JLabel lbTitulo = new JLabel("Contato & Endereço", JLabel.LEADING);
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
		txtTelefone.addFocusListener(contatoListener);
		txtCelular.addFocusListener(contatoListener);
		txtEmail.addFocusListener(contatoListener);
		txtCep.addFocusListener(contatoListener);
		cbEstado.addItemListener(contatoListener);
		cbCidade.addItemListener(contatoListener);
		txtEndereco.addFocusListener(contatoListener);
		txtBairro.addFocusListener(contatoListener);
		txaComplemento.addFocusListener(contatoListener);
	}

	private void removeListeners(){
		txtTelefone.removeFocusListener(contatoListener);
		txtCelular.removeFocusListener(contatoListener);
		txtEmail.removeFocusListener(contatoListener);
		txtCep.removeFocusListener(contatoListener);
		cbEstado.removeItemListener(contatoListener);
		cbCidade.removeItemListener(contatoListener);
		txtEndereco.removeFocusListener(contatoListener);
		txtBairro.removeFocusListener(contatoListener);
		txaComplemento.removeFocusListener(contatoListener);
	}
	
	private ApoliceEditarMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceEditarMainPanel) break;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel)c;
	}
	
	private class EventoBotaoCEP implements ActionListener{
		
		@SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e) {
			try{
				if (txtCep.getText().length() == 10){
					if (!carrengando) {
						carrengando = true;
						lbAguardeEndereco.setVisible(true);
						barConsultaEndereco.setPreferredSize(new Dimension(220, 22));
						barConsultaEndereco.setMinimumSize(new Dimension(220, 22));
						txtEndereco.setEnabled(false);
						txtBairro.setEnabled(false);
						cbCidade.setEnabled(false);
						cbEstado.setEnabled(false);

						new SwingWorker() {

							protected Object doInBackground() throws Exception {
								EnderecoVO endereco = EnderecoService.consultar(txtCep.getText());
								if (endereco.getResultado().compareTo(EnderecoService.OK) == 0){
									String logradouro = endereco.getTipoLogradouro().trim() + " " + endereco.getLogradouro().trim() + ", ";
									txtEndereco.setText(logradouro);
									txtEndereco.grabFocus();
									txtEndereco.setCaretPosition(logradouro.length());
									txtBairro.setText(endereco.getBairro().trim());
									cbCidade.setSelectedItem(endereco.getCidade().trim());
									cbEstado.setSelectedItem(endereco.getUf().trim());
									barNotificacao.escondeMensagem();
								}
								else{
									barNotificacao.mostrarMensagem("CEP não encontrado!", BarraNotificacao.ERRO);
								}
								return null;
							}

							protected void done() {
								carrengando = false;
								barConsultaEndereco.setPreferredSize(new Dimension(115, 22));
								barConsultaEndereco.setMinimumSize(new Dimension(115, 22));
								lbAguardeEndereco.setVisible(false);
								txtEndereco.setEnabled(true);
								txtBairro.setEnabled(true);
								cbCidade.setEnabled(true);
								cbEstado.setEnabled(true);
							}
						}.execute();
					}					
				}
				else{
					barNotificacao.mostrarMensagem("O CEP " + txtCep.getText() + " está incompleto!", BarraNotificacao.ERRO);
				}
			}
			catch(Exception ex){
				barNotificacao.mostrarMensagem("Serviço indisponível!", BarraNotificacao.ERRO);
				btConsultaEndereco.setEnabled(false);
				ex.printStackTrace();
			}
		}
	}
	
	private void loadCidadeByEstado(String estado)
	{
		List<CidadeVO> list = Controller.getInstance().getCidadeAllByEstado(estado);
		cbCidade.removeAllItems();
		for (int i=0; i< list.size(); i++) {
			cbCidade.addItem(list.get(i));
		}
		if(!apolice.getCidade().getStatus().equals("Ativo"))
			cbCidade.addItem(apolice.getCidade());
	}
	
	private class ApoliceContatoListener extends FocusAdapter implements ItemListener{
		public void apoliceChange() {
			if (apolice != null){
				boolean hasChanged = getPainelPrincipal().hasChanged();

				hasChanged = hasChanged || (apolice.getTelefone().compareTo(txtTelefone.getText()) != 0);
				hasChanged = hasChanged || (apolice.getCelular().compareTo(txtCelular.getText()) != 0);
				hasChanged = hasChanged || ((apolice.getEmail() != null ? apolice.getEmail() : "").compareTo(txtEmail.getText()) != 0);
				hasChanged = hasChanged || (apolice.getCep().compareTo(txtCep.getText()) != 0);
				if (apolice.getCidade() != null){
					hasChanged = hasChanged || !apolice.getCidade().getEstado().equals(cbEstado.getSelectedItem());
					hasChanged = hasChanged || !apolice.getCidade().equals(cbCidade.getSelectedItem());
				}
				hasChanged = hasChanged || (apolice.getEndereco().compareTo(txtEndereco.getText()) != 0);
				hasChanged = hasChanged || (apolice.getBairro().compareTo(txtBairro.getText()) != 0);
				hasChanged = hasChanged || ((apolice.getCompEndereco() != null ? apolice.getCompEndereco() : "").compareTo(txaComplemento.getText()) != 0);
				if (hasChanged)
					barNotificacao.mostrarMensagem("Alterações foram efetuadas!", BarraNotificacao.INFO);

				getPainelPrincipal().setChanged(hasChanged);
			}
		}
		
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == cbEstado){
				String estado = e.getItem().toString();
				loadCidadeByEstado(estado);
			}
			apoliceChange();
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
}