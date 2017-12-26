package br.com.saodimas.view.components.panel.apolice.editar.dependente;

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
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.AssociadoVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.model.beans.RelacaoVO;
import br.com.saodimas.model.exception.MensagemSaoDimasException;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.button.ErrorButton;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.menu.BarraMenu;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.model.DependenteTableModel;
import br.com.saodimas.view.components.text.CPFTextField;
import br.com.saodimas.view.components.text.DataTextField;
import br.com.saodimas.view.util.ListasComuns;
import br.com.saodimas.view.util.validators.ValidaDependente;

@SuppressWarnings("serial")
public class DependentePanel extends JPanel {
	public static final String FORM_NAME = "Dependente"; 
	private static final String MENSAGEM_PADRAO = "(*) Preenchimento obrigatório";

	private AssociadoVO dependente;
	private BarraNotificacao barNotificacao;
	private JTextField txtNome;
	private CPFTextField txtCPF;
	private DataTextField txtDataNascimento;
	private DataTextField txtDataAdesao;
	private JRadioButton rdMasculino;
	private JRadioButton rdFeminino;
	private ButtonGroup bgrSexo;
	private JComboBox cbSituacao;
	private JComboBox cbParentesco;

	private ErrorButton btErroNome;
	private ErrorButton btErroCPF;
	private ErrorButton btErroDataNasc;
	private ErrorButton btErroDataAdesao;

	private JButton btCancelar;
	private JButton btOk;

	private JLabel lbSituacao;
	private JLabel lbNome;
	private JLabel lbCPF;
	private JLabel lbDataNascimento;
	private JLabel lbDataAdesao;
	private JLabel lbSexo;
	private JLabel lbParentesco;

	private DependenteTableModel dependenteTableModel;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	
	private ApoliceVO apolice;

	public DependentePanel() {
		initialize();
		configure();
	}

	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
	}
	
	public AssociadoVO getDependente() {
		return dependente;
	}

	public void setDependente(AssociadoVO dependente) {
		this.dependente = dependente;
		barNotificacao.escondeMensagem();
		if (this.dependente != null){
			txtNome.setText(this.dependente.getNome());
			txtCPF.setText(this.dependente.getCPF());
			if(dependente.getDataNascimento() != null)
				txtDataNascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.dependente.getDataNascimento()));
			else
				txtDataNascimento.setText("");
			
			if(dependente.getDataAdesao() != null)
				txtDataAdesao.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.dependente.getDataAdesao()));
			else
				txtDataAdesao.setText("");
			
			rdFeminino.setSelected(this.dependente.getSexo() == 'F');
			rdMasculino.setSelected(this.dependente.getSexo() == 'M');
			cbParentesco.setSelectedItem(dependente.getRelacao());
			cbParentesco.repaint();
		}
		else limparCampos();
		
		carregarRelacoes();
		this.setEnableCamposPorPerfil();
	}

	private void carregarRelacoes() {

		List<RelacaoVO> list = Controller.getInstance().getRelacoesAtivasByTipo(apolice.getPlano().isEmpresarial() ? "PJ" : "PF");
		cbParentesco.removeAllItems();
		for (int i=0; i< list.size(); i++) {
			cbParentesco.addItem(list.get(i));
			if(this.dependente != null && dependente.getRelacao().getId().equals(list.get(i).getId()))
				cbParentesco.setSelectedIndex(i);
		}
		
			
		cbParentesco.repaint();
		
	}

	public DependenteTableModel getTableModel() {
		return dependenteTableModel;
	}

	public void setTableModel(DependenteTableModel tableModel) {
		this.dependenteTableModel = tableModel;
	}

	public void limparCampos() {
		txtNome.setText("");
		txtCPF.setText("");
		bgrSexo.setSelected(rdMasculino.getModel(), true);
		txtDataNascimento.setText("");
		txtDataAdesao.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	}
	
	private void initialize() {
		barNotificacao = new BarraNotificacao(MENSAGEM_PADRAO);

		lbSituacao = new JLabel("Status:  ", JLabel.RIGHT);
		lbSituacao.setPreferredSize(DLABEL);
		lbSituacao.setMinimumSize(DLABEL);

		cbSituacao = new JComboBox(ListasComuns.STATUS_ITENS);
		cbSituacao.setEnabled(dependente != null);
		cbSituacao.setPreferredSize(DFIELDM);
		cbSituacao.setMinimumSize(DFIELDM);

		lbNome = new JLabel("Nome: *", JLabel.RIGHT);
		lbNome.setPreferredSize(DLABEL);
		lbNome.setMinimumSize(DLABEL);

		CustomDocument nomeDoc = new CustomDocument(50);
		txtNome = new JTextField();
		txtNome.setDocument(nomeDoc);
		txtNome.setPreferredSize(DFIELDM);

		lbCPF = new JLabel("C.P.F.:  ", JLabel.RIGHT);
		lbCPF.setPreferredSize(DLABEL);
		lbCPF.setMinimumSize(DLABEL);

		txtCPF = new CPFTextField();
		txtCPF.setPreferredSize(DFIELDM);
		txtCPF.setMinimumSize(DFIELDM);
		txtCPF.setEditable(dependente == null);

		lbDataNascimento = new JLabel("Data Nascimento: ", JLabel.RIGHT);
		lbDataNascimento.setPreferredSize(DLABEL);
		lbDataNascimento.setMinimumSize(DLABEL);

		txtDataNascimento = new DataTextField();
		txtDataNascimento.setPreferredSize(DFIELDM);
		txtDataNascimento.setMinimumSize(DFIELDM);

		lbDataAdesao = new JLabel("Data Adesão: ", JLabel.RIGHT);
		lbDataAdesao.setPreferredSize(DLABEL);
		lbDataAdesao.setMinimumSize(DLABEL);

		txtDataAdesao = new DataTextField();
		txtDataAdesao.setPreferredSize(DFIELDM);
		txtDataAdesao.setMinimumSize(DFIELDM);

		lbSexo = new JLabel("Sexo: *", JLabel.RIGHT);
		lbSexo.setPreferredSize(DLABEL);
		lbSexo.setMinimumSize(DLABEL);

		rdMasculino = new JRadioButton("Masculino");
		rdFeminino = new JRadioButton("Feminino");
		bgrSexo = new ButtonGroup();
		bgrSexo.add(rdMasculino);
		bgrSexo.add(rdFeminino);
		bgrSexo.setSelected(rdMasculino.getModel(), true);

		lbParentesco = new JLabel("Relação c/ o Titular: *", JLabel.RIGHT);
		lbParentesco.setPreferredSize(DLABEL);
		lbParentesco.setMinimumSize(DLABEL);

		cbParentesco = new JComboBox();
		cbParentesco.setPreferredSize(DFIELDM);
		cbParentesco.setMinimumSize(DFIELDM);

		btErroNome = new ErrorButton(txtNome, barNotificacao);
		btErroCPF = new ErrorButton(txtCPF, barNotificacao);
		btErroDataNasc = new ErrorButton(txtDataNascimento, barNotificacao);
		btErroDataAdesao = new ErrorButton(txtDataAdesao, barNotificacao);

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		btOk = new JButton("OK", new ImageIcon("imagens/accept.gif"));
		btOk.addActionListener(new EventoBotaoControle());
		btOk.setHorizontalAlignment(JButton.LEFT);
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
		c.gridy = 2; infPessoais.add(lbSexo, c);
		c.gridy = 3; infPessoais.add(lbDataNascimento, c);
		c.gridy = 4; infPessoais.add(lbParentesco, c);
		c.gridy = 5; infPessoais.add(lbDataAdesao, c);

		JPanel panelSexo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panelSexo.add(rdMasculino);
		panelSexo.add(rdFeminino);
		panelSexo.setMinimumSize(new Dimension(200, 22));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.75;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infPessoais.add(txtNome, c);
		c.fill = GridBagConstraints.NONE;
		c.gridy = 1; infPessoais.add(txtCPF, c);
		c.gridy = 2; infPessoais.add(panelSexo, c);
		c.gridy = 3; infPessoais.add(txtDataNascimento, c);
		c.weighty = 1;
		c.gridy = 4; infPessoais.add(cbParentesco, c);
		c.weighty = 2;
		c.gridy = 5; infPessoais.add(txtDataAdesao, c);

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 0; infPessoais.add(btErroNome, c);
		c.gridy = 1; infPessoais.add(btErroCPF, c);
		c.gridy = 3; infPessoais.add(btErroDataNasc, c);
		c.gridy = 5; infPessoais.add(btErroDataAdesao, c);

		infPessoais.setBorder(BorderFactory.createTitledBorder("Identificação"));
		adicionarAtalhosComandos(infPessoais);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btOk);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(200, 22));
		adicionarAtalhosComandos(controle);

		JPanel panelColaborador = new JPanel(new BorderLayout());
		panelColaborador.add(barNotificacao, BorderLayout.NORTH);
		panelColaborador.add(infPessoais, BorderLayout.CENTER);
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
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btOk.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}

	private ApoliceEditarMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceEditarMainPanel) break;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel)c;
	}
	
	public void setEnableCamposPorPerfil()
	{
		ColaboradorVO funcionario = SaoDimasMain.colaborador;
		int perfil = funcionario.getPerfilColoborador();		
		
		// habilita para edição de data ADMIN e Funcionários especial.
		txtDataAdesao.setEditable(false);
		txtDataAdesao.setEditable(perfil == BarraMenu.PERFIL_ADMIN || perfil == BarraMenu.PERFIL_FUNC_ESPECIAL);
						
	}

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ApoliceEditarMainPanel c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				c.getIframeDependente().setVisible(false);
			} 
			else if (e.getSource() == btOk)
			{
				String erroNome = "", erroCPF = "", erroData = "", erroDataAdesao = "";
				if (dependenteTableModel != null){
					Date dataNascimento = null;
					try {
						if(txtDataNascimento.getText().trim().length() > 0)
						dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(txtDataNascimento.getText());
					} catch (ParseException e1) {
						erroData = "Data Inválida";
					}
					if (erroData.length() > 0) btErroDataNasc.setMensagem(erroData);	
					
					Date dataAdesao = null;
					try {
						if(txtDataAdesao.getText().trim().length() > 0)
						dataAdesao = new SimpleDateFormat("dd/MM/yyyy").parse(txtDataAdesao.getText());
					} catch (ParseException e1) {
						erroDataAdesao = "Data Inválida";
					}
					if (erroDataAdesao.length() > 0) btErroDataAdesao.setMensagem(erroDataAdesao);
					
					
					erroNome = ValidaDependente.validaNome(txtNome.getText());
					if (erroNome.length() > 0) btErroNome.setMensagem(erroNome);

					erroCPF = ValidaDependente.validaCPF(txtCPF.getText());
					if (erroCPF.length() > 0) btErroCPF.setMensagem(erroCPF);

					if (erroNome.length() > 0 || erroCPF.length() > 0 || erroData.length() > 0 || erroDataAdesao.length() > 0)
						barNotificacao.mostrarMensagem("Erro ao salvar o Dependente.", BarraNotificacao.ERRO);
					else{
						btErroNome.setMensagem(null);
						btErroDataNasc.setMensagem(null);
						btErroCPF.setMensagem(null);
						btErroDataAdesao.setMensagem(null);
						
						try{
							//Edição de Dependente
							Vector <AssociadoVO> list = dependenteTableModel.getDataVector();
							if (dependente != null){
									dependente.setStatus(cbSituacao.getSelectedItem().toString());
									dependente.setNome(txtNome.getText().trim());
									dependente.setCPF(txtCPF.getText().trim());
									dependente.setSexo(rdMasculino.isSelected()?'M':'F');
									dependente.setDataNascimento(dataNascimento);
									dependente.setDataAdesao(dataAdesao);
									dependente.setRelacao((RelacaoVO)cbParentesco.getSelectedItem());
									dependenteTableModel.fireTableDataChanged();
									Controller.getInstance().saveOrUpdateDependente(dependente);
									c.carregarDependentes();
									c.mostrarMensagemDependente("Dependente alterado com sucesso.!", BarraNotificacao.SUCESSO);
							}
							
							//Novo Dependente
							else{
								AssociadoVO d = new AssociadoVO();
								d.setApolice(apolice);
								d.setStatus(ListasComuns.STATUS_ITENS[0]);
								d.setNome(txtNome.getText().trim());
								d.setCPF(txtCPF.getText().trim());
								d.setSexo(rdMasculino.isSelected()?'M':'F');
								d.setDataNascimento(dataNascimento);
								d.setDataAdesao(dataAdesao);
								d.setRelacao((RelacaoVO)cbParentesco.getSelectedItem());
	
								list.add(d);
								apolice.getDependentes().add(d);
								dependenteTableModel.fireTableDataChanged();
								Controller.getInstance().saveOrUpdateDependente(d);
								c.mostrarMensagemDependente("Dependente criado com sucesso.!", BarraNotificacao.SUCESSO);
							}
							
							getPainelPrincipal().getIframeDependente().setVisible(false);
							getPainelPrincipal().carregarDependentes();
						}catch(MensagemSaoDimasException ex)
						{
							c.mostrarMensagemDependente(ex.getMessage(), BarraNotificacao.ERRO);	
						}
					}
					c.getIframeDependente().pack();
				}
			}
		}
		
		
	}
}
