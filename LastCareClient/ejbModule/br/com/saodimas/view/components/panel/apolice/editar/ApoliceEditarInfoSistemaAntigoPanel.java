package br.com.saodimas.view.components.panel.apolice.editar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.ColaboradorVO;
import br.com.saodimas.principal.SaoDimasMain;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.menu.BarraMenu;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.table.renderer.SpinnerRenderer;

@SuppressWarnings("serial")
public class ApoliceEditarInfoSistemaAntigoPanel extends JPanel {
	public static final String FORM_NAME = "Informações Sistema Antigo";

	private ApoliceVO apolice;

	private JTextArea txaObservacoes;
	private SpinnerRenderer spinnerQtdeFaturasPagas;
	private SpinnerRenderer spinnerQtdeObitos;

	private JLabel lbQtdeFaturasPagas;
	private JLabel lbObitos;
	
	private BarraNotificacao barNotificacao;
	private ApoliceInfoListener infoListener;

	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	

	public ApoliceEditarInfoSistemaAntigoPanel(BarraNotificacao bar) {
		this.barNotificacao = bar;
		initialize();
		configure();
	}

	public void atulizadaDadosApolice()
	{
		apolice.setObservacaoAntiga(txaObservacoes.getText());
		apolice.setFaturasPagasAntigas((Integer)spinnerQtdeFaturasPagas.getValue());
		apolice.setObitoOcorridosAntigos((Integer)spinnerQtdeObitos.getValue());
		this.setEnableCamposPorPerfil();
	}
	
	@Override
	public void setVisible(boolean flag) {
		
		super.setVisible(flag);
		if (flag){
			spinnerQtdeFaturasPagas.requestFocus();
		}
		this.setEnableCamposPorPerfil();
	}

	public ApoliceVO getApolice() {
		return apolice;
	}

	
	public void setApolice(ApoliceVO apolice) {
		this.apolice = apolice;
		removeListeners();
		if (apolice != null){
			
			txaObservacoes.setText(apolice.getObservacaoAntiga());
			spinnerQtdeFaturasPagas.setValue(new Integer(apolice.getFaturasPagasAntigas()));
			spinnerQtdeObitos.setValue(new Integer(apolice.getObitoOcorridosAntigos()));
			addListeners();
		}
	}

	@Override
	public void requestFocus() {
		spinnerQtdeFaturasPagas.requestFocus();
	}

	private void initialize() {
		infoListener = new ApoliceInfoListener();

		lbQtdeFaturasPagas = new JLabel("Faturas Pagas:  ", JLabel.RIGHT);
		lbQtdeFaturasPagas.setPreferredSize(DLABEL);
		lbQtdeFaturasPagas.setMinimumSize(DLABEL);

		spinnerQtdeFaturasPagas = new SpinnerRenderer();
		spinnerQtdeFaturasPagas.setPreferredSize(DFIELDM);
		spinnerQtdeFaturasPagas.setMinimumSize(DFIELDM);
		
		lbObitos = new JLabel("Obitos Ocorridos:  ", JLabel.RIGHT);
		lbObitos.setPreferredSize(DLABEL);
		lbObitos.setMinimumSize(DLABEL);

		spinnerQtdeObitos = new SpinnerRenderer();
		spinnerQtdeObitos.setPreferredSize(DFIELDM);
		spinnerQtdeObitos.setMinimumSize(DFIELDM);

		txaObservacoes = new JTextArea();
		txaObservacoes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txaObservacoes.setDocument(new CustomDocument(499));


	}

	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infApolice = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 2, 1);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infApolice.add(lbQtdeFaturasPagas, c);
		c.gridy = 1; infApolice.add(lbObitos, c);
				
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 0; infApolice.add(spinnerQtdeFaturasPagas, c);
		c.gridy = 1; infApolice.add(spinnerQtdeObitos, c);
				
		infApolice.setBorder(BorderFactory.createTitledBorder("Apólice"));

		JScrollPane paneObs = new JScrollPane(txaObservacoes);
		paneObs.setBorder(BorderFactory.createTitledBorder("Observações"));
		paneObs.setPreferredSize(new Dimension(50, 10));
		
		JPanel panelPrincipal = new JPanel(new GridBagLayout()); 
		c = new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; panelPrincipal.add(infApolice, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.4;
		c.gridx = 0;
		c.gridy = 1; panelPrincipal.add(paneObs, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0.6;
		c.gridx = 0;
		c.gridy = 2; panelPrincipal.add(new JLabel(), c);

		setLayout(new BorderLayout());

		JLabel lbTitulo = new JLabel("Dados do Sistema Antigo", JLabel.LEADING);
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
		
		spinnerQtdeObitos.addFocusListener(infoListener);
		spinnerQtdeFaturasPagas.addFocusListener(infoListener);
		txaObservacoes.addFocusListener(infoListener);
	}

	private void removeListeners(){
		spinnerQtdeFaturasPagas.removeFocusListener(infoListener);
		spinnerQtdeObitos.removeFocusListener(infoListener);
		txaObservacoes.removeFocusListener(infoListener);
	}

	private ApoliceEditarMainPanel getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceEditarMainPanel) break;
			c = c.getParent();
		}
		return (ApoliceEditarMainPanel)c;
	}

	private class ApoliceInfoListener  extends FocusAdapter implements ItemListener{
		public void apoliceChange() {
			if (apolice != null){
				boolean hasChanged = getPainelPrincipal().hasChanged();

				hasChanged = hasChanged || ((apolice.getObservacaoAntiga() != null ? apolice.getObservacaoAntiga() : "").compareTo(txaObservacoes.getText()) != 0);
				hasChanged = hasChanged || apolice.getFaturasPagasAntigas() != ((Integer)spinnerQtdeFaturasPagas.getValue()).intValue();
				hasChanged = hasChanged || apolice.getObitoOcorridosAntigos() != ((Integer)spinnerQtdeObitos.getValue()).intValue();
				if (hasChanged)
					barNotificacao.mostrarMensagem("Alterações foram efetuadas!", BarraNotificacao.INFO);
				
				getPainelPrincipal().setChanged(hasChanged);
			}
		}

		public void itemStateChanged(ItemEvent e) {

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
	
	public void setEnableCamposPorPerfil()
	{
		ColaboradorVO funcionario = SaoDimasMain.colaborador;
		int perfil = (funcionario == null) ? BarraMenu.SEM_PERFIL : (funcionario.getTipoColaborador().startsWith("A")) ?	BarraMenu.PERFIL_ADMIN : BarraMenu.PERFIL_FUNC;
				
		txaObservacoes.setEditable(perfil == BarraMenu.PERFIL_ADMIN);
		spinnerQtdeFaturasPagas.setEnabled(perfil == BarraMenu.PERFIL_ADMIN);
		spinnerQtdeObitos.setEnabled(perfil == BarraMenu.PERFIL_ADMIN);
	}
}