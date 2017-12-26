package br.com.saodimas.view.components.panel.apolice.editar.fatura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import br.com.saodimas.model.beans.ApoliceVO;
import br.com.saodimas.model.beans.FaturaVO;
import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.components.panel.BarraNotificacao;
import br.com.saodimas.view.components.table.FaturaTable;
import br.com.saodimas.view.components.table.model.FaturaTableModel;
import br.com.saodimas.view.util.ExpReg;
import br.com.saodimas.view.util.WinManager;

@SuppressWarnings("serial")
public class FaturaMainPanel extends JPanel {
	public static final String FORM_NAME = "Faturas"; 

	private BarraNotificacao barNotificacao;
	private JButton btPesquisar;
	private JButton btNovo;
	private JButton btEditar;
	private JButton btExcluir;
	private JButton btImprimir;
	private JButton btQuitar;
	private JToolBar barControle;
	
	private FaturaTable tbFaturas;
	private AbstractButton ultimoComando;

	private static final Dimension DBUTTON = new Dimension(30,30);

	private JTextField txtApolice;
	private JLabel lbApolice;
	private JButton btPesquisarApolice;
	private JPanel panelPesquisaApolice;
	private static final Dimension DLABEL = new Dimension(120,22);
	private static final Dimension DFIELDM = new Dimension(115,22);
	

	private void initializeBuscaApolice()
	{
		lbApolice = new JLabel("Apolice: *", JLabel.RIGHT);
		lbApolice.setPreferredSize(DLABEL);
		lbApolice.setMinimumSize(DLABEL);
		
		CustomDocument contratoDoc = new CustomDocument(ExpReg.NUMERIC, 25);
		txtApolice = new JTextField();
		txtApolice.setDocument(contratoDoc);
		txtApolice.setPreferredSize(DFIELDM);
		txtApolice.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btPesquisar.doClick();
				else super.keyPressed(e);
			}
		});
		
		
		btPesquisarApolice = new JButton();
		btPesquisarApolice.setIcon(new ImageIcon("imagens/search.png"));
		btPesquisarApolice.addActionListener(new EventoBotaoControle());
		btPesquisarApolice.setToolTipText("Clique para pesquisar uma apólice!");
		btPesquisarApolice.addKeyListener(txtApolice.getKeyListeners()[0]);

		panelPesquisaApolice = new JPanel();
		panelPesquisaApolice.add(lbApolice);
		panelPesquisaApolice.add(txtApolice);
		panelPesquisaApolice.add(btPesquisarApolice);
	}
	
	public FaturaMainPanel(BarraNotificacao barNotificacao){
		this.barNotificacao = barNotificacao;
		initialize();
		configure();
	}

	public void adicionaApolice(ApoliceVO a) {
		if(a != null){
			txtApolice.setText(a.getNumeroContrato());
			FaturaTableModel model = (FaturaTableModel)tbFaturas.getModel();
			List<FaturaVO> listFaturas = new ArrayList<FaturaVO>();
			listFaturas.addAll(a.getFaturas());
			model.removeAll();
			model.loadData(listFaturas);
		}
	}	
	
	public void selecionaUltimoComando(){
		if (ultimoComando != null){
			if (ultimoComando != btEditar && ultimoComando != btExcluir){
				tbFaturas.getSelectionModel().clearSelection();
				ultimoComando.requestFocus();
			}
			else tbFaturas.requestFocus();
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		btPesquisar.setEnabled(enabled);
		btNovo.setEnabled(enabled);
		btEditar.setEnabled(enabled && tbFaturas.getSelectedRow() > -1);
		btExcluir.setEnabled(enabled && tbFaturas.getSelectedRow() > -1);
		btImprimir.setEnabled(enabled && haSelecao());
		btQuitar.setEnabled(enabled && haSelecao());
		barControle.setEnabled(enabled);
		tbFaturas.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			ultimoComando = btPesquisar;
			selecionaUltimoComando();
		}
	}

	private void initialize() {
		this.initializeBuscaApolice();

		tbFaturas = new FaturaTable();
		tbFaturas.getModel().addTableModelListener(new EventoTabela());
		tbFaturas.getSelectionModel().addListSelectionListener(new EventoTabela());
		tbFaturas.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1 && btEditar.isEnabled()) btEditar.doClick();
				super.mouseClicked(e);
			}
		});
		tbFaturas.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) btEditar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_DELETE) btExcluir.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_INSERT) btNovo.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_F3) btPesquisar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_X){
					FaturaTableModel model = (FaturaTableModel)tbFaturas.getModel();
					int row = tbFaturas.getSelectedRow();
					if (row >= 0){
						boolean marcar = !(Boolean)model.getValueAt(row, FaturaTableModel.SELECAO);
						model.setValueAt(new Boolean(marcar), row, FaturaTableModel.SELECAO);
					}
				}
				super.keyPressed(e);
			}
		});

		btPesquisar = new JButton();
		btPesquisar.setIcon(new ImageIcon("imagens/search.gif"));
		btPesquisar.addActionListener(new EventoBotaoControle());
		btPesquisar.setToolTipText("Pesquisar faturas!");
		btPesquisar.setPreferredSize(DBUTTON);
		btPesquisar.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addBill.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastra uma nova fatura!");
		btNovo.setPreferredSize(DBUTTON);
		btNovo.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Edita os dados de uma fatura!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		btEditar.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btExcluir = new JButton();
		btExcluir.setIcon(new ImageIcon("imagens/remove.gif"));
		btExcluir.addActionListener(new EventoBotaoControle());
		btExcluir.setToolTipText("Remove um fatura!");
		btExcluir.setPreferredSize(DBUTTON);
		btExcluir.setEnabled(false);
		btExcluir.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btImprimir = new JButton();
		btImprimir.setIcon(new ImageIcon("imagens/imprimir.gif"));
		btImprimir.addActionListener(new EventoBotaoControle());
		btImprimir.setToolTipText("Imprimir faturas selecionadas!");
		btImprimir.setPreferredSize(DBUTTON);
		btImprimir.setEnabled(true);
		btImprimir.addKeyListener(tbFaturas.getKeyListeners()[0]);

		btQuitar = new JButton();
		btQuitar.setIcon(new ImageIcon("imagens/quitarFaturas.gif"));
		btQuitar.addActionListener(new EventoBotaoControle());
		btQuitar.setToolTipText("Dar baixa nas faturas selecionadas!");
		btQuitar.setPreferredSize(DBUTTON);
		btQuitar.setEnabled(true);//alterado devido ao erro gerado no metodo tableChanged
		btQuitar.addKeyListener(tbFaturas.getKeyListeners()[0]);

		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btPesquisar);
		barControle.add(btNovo);
		barControle.add(btEditar);
		barControle.add(btExcluir);
		barControle.addSeparator();
		barControle.add(btImprimir);
		barControle.add(btQuitar);
		
		ultimoComando = btPesquisar;
		
		
		
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infFatura = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infFatura.add(panelPesquisaApolice, c);
		c.gridy = 1; infFatura.add(barControle, c);

		JScrollPane depPanel = new JScrollPane(tbFaturas);
		depPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2; infFatura.add(depPanel, c);

		infFatura.setBorder(BorderFactory.createTitledBorder("Faturas"));

		JPanel panelPrincipal = new JPanel(new GridBagLayout()); 
		c = new GridBagConstraints();

		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; panelPrincipal.add(barNotificacao, c);

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; panelPrincipal.add(infFatura, c);

		this.add(panelPrincipal, BorderLayout.CENTER);
	}

	private boolean haSelecao(){
		FaturaTableModel model = (FaturaTableModel)tbFaturas.getModel();
		int numLinhas = model.getRowCount();
		boolean haFaturasSelecionadas = false;

		for (int i = 0; i < numLinhas; i++){
			haFaturasSelecionadas = (Boolean)model.getValueAt(i, FaturaTableModel.SELECAO);
			if (haFaturasSelecionadas) break;
		}

		return haFaturasSelecionadas;
	}

	private class EventoTabela implements TableModelListener, ListSelectionListener{
		public void tableChanged(TableModelEvent ev) {
			
			//btImprimir.setEnabled(haSelecao());
			//btImprimir.setEnabled(true);
			//btQuitar.setEnabled(haSelecao());
		}

		public void valueChanged(ListSelectionEvent e) {
			btEditar.setEnabled(tbFaturas.getSelectedRow() > -1);
			btExcluir.setEnabled(tbFaturas.getSelectedRow() > -1);
		}
	}

	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ultimoComando = (AbstractButton)e.getSource();
			
			if (e.getSource() == btPesquisarApolice){
				//iframeSelecaoApolice.setVisible(true);
				
			}
			
			if (e.getSource() == btPesquisar){
				//iframeConsultaFatura.setVisible(true);
				//iframeConsultaFatura.habilitarControles(false);				
			}
			else if (e.getSource() == btNovo){
//				iframeFatura.setFatura(null);
//				iframeFatura.setTableModel((FaturaTableModel)tbFaturas.getModel());
//				iframeFatura.setVisible(true);
//				iframeFatura.habilitarControles(false);				
			}
			else if (e.getSource() == btEditar){
//				int row = tbFaturas.getSelectedRow();
//				FaturaTableModel model = (FaturaTableModel)tbFaturas.getModel();
//				FaturaVO dep = (FaturaVO)model.getSelectedValue(row);
//
//				iframeFatura.setFatura(dep);
//				iframeFatura.setVisible(true);
//				iframeFatura.habilitarControles(false);				
			}
			else if (e.getSource() == btExcluir){
				if (JOptionPane.showConfirmDialog(WinManager.getJanelaInicial(), "Confirma a exclusão da fatura?", "Confirmação", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					int row = tbFaturas.getSelectedRow();
					FaturaTableModel model = (FaturaTableModel)tbFaturas.getModel();
					model.removeAt(row);
				}
			}
			else if (e.getSource() == btImprimir){
				JOptionPane.showMessageDialog(WinManager.getJanelaInicial(), "Impressão de faturas indisponível!", "Aviso", JOptionPane.WARNING_MESSAGE);
			}
			else if (e.getSource() == btQuitar){
				if (JOptionPane.showConfirmDialog(WinManager.getJanelaInicial(), "Confirma a quitação das faturas selecionadas?", "Pergunta", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					FaturaTableModel model = (FaturaTableModel)tbFaturas.getModel();
					for (Object item : model.getDataVector()) {
						FaturaVO f = (FaturaVO)item;
						int row = model.getDataVector().indexOf(f);
						if ((Boolean)model.getValueAt(row, FaturaTableModel.SELECAO)){
							f.setDataPagamento(new Date(Calendar.getInstance().getTimeInMillis()));
							model.setValueAt(false, row, FaturaTableModel.SELECAO);
						}
					}
					model.fireTableDataChanged();
				}
			}
		}
	}
}
