package br.com.saodimas.view.components.panel.servico;

import java.awt.Color;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.panel.plano.PlanoMainPanel;
import br.com.saodimas.view.components.table.ServicoTable;
import br.com.saodimas.view.components.table.model.ServicoTableModel;

@SuppressWarnings("serial")
public class ServicoSelecaoPanel extends JPanel {
	
	private ServicoVO servicoSelecionado;
	private JTextField txtServico;
	private JButton btFiltrar;
	private JToolBar barPesquisa;
	private ServicoTable tbServicos;
	private JButton btCancelar;
	private JButton btSelecionar;
	
	public ServicoSelecaoPanel() {
		initialize();
		configure();
	}

	public ServicoVO getServicoSelecionado() {
		return servicoSelecionado;
	}

	public void setServicoSelecionado(ServicoVO servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}
	
	public void recarregarTabela(){
		limparTabela();
		ServicoTableModel model = (ServicoTableModel)tbServicos.getModel();
		model.loadData(Controller.getInstance().getServicoAtivos());
		model.fireTableDataChanged();
		txtServico.requestFocus();
	}
	
	public void limparTabela(){
		ServicoTableModel model = (ServicoTableModel)tbServicos.getModel();
		model.removeAll();
	}
	
	public void focoPadrao(){
		txtServico.requestFocus();
	}
	
	private void initialize() {
		txtServico = new JTextField();
		txtServico.setToolTipText("Digite parte do nome de um serviço!");
		txtServico.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) btFiltrar.doClick();
			}
		});

		btFiltrar = new JButton();
		btFiltrar.setIcon(new ImageIcon("imagens/filter.gif"));
		btFiltrar.addActionListener(new EventoBotaoFiltrar());
		btFiltrar.setToolTipText("Clique para filtrar!");

		barPesquisa = new JToolBar();
		barPesquisa.setFloatable(false);
		barPesquisa.setOpaque(true);
		barPesquisa.setBorderPainted(false);
		barPesquisa.setBorder(BorderFactory.createEmptyBorder());
		barPesquisa.setMargin(new Insets(0, 0, 0, 0));
		barPesquisa.setPreferredSize(new Dimension(200, 22));
		barPesquisa.setMinimumSize(new Dimension(200, 22));
		barPesquisa.add(txtServico);
		barPesquisa.add(btFiltrar);
		barPesquisa.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btCancelar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) btSelecionar.doClick();
				else super.keyPressed(e);
			}
		});		

		tbServicos = new ServicoTable();
		tbServicos.setPreferredSize(new Dimension(300, 200));
		tbServicos.setMinimumSize(new Dimension(300, 200));
		tbServicos.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) btSelecionar.doClick();
				super.mouseClicked(e);
			}
		});
		tbServicos.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btCancelar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) btSelecionar.doClick();
				else super.keyPressed(e);
			}
		});
		tbServicos.getSelectionModel().addListSelectionListener(new EventoTabela());
		
		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);
		btCancelar.addKeyListener(tbServicos.getKeyListeners()[0]);
		
		btSelecionar = new JButton("Selecionar", new ImageIcon("imagens/accept.gif"));
		btSelecionar.addActionListener(new EventoBotaoControle());
		btSelecionar.setHorizontalAlignment(JButton.LEFT);
		btSelecionar.setEnabled(false);
		btSelecionar.addKeyListener(tbServicos.getKeyListeners()[0]);
	}

	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; add(barPesquisa, c);

		JScrollPane prodPanel = new JScrollPane(tbServicos);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		prodPanel.setPreferredSize(new Dimension(300, 200));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; add(prodPanel, c);

		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btSelecionar);
		controle.add(btCancelar);
		controle.setMinimumSize(new Dimension(300, 22));
		adicionarAtalhosComandos(controle);
		
		c.anchor = GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridy = 2; add(controle, c);
		adicionarAtalhosComandos(this);
	}
	
	private void adicionarAtalhosComandos(JPanel panel){
		for (Component c : panel.getComponents()) {
			c.addKeyListener(new KeyAdapter(){
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btCancelar.doClick();
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) btSelecionar.doClick();
					else super.keyPressed(e);
				}
			});
		}
	}
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c.getClass() == PlanoMainPanel.class  || c.getClass() == ApoliceEditarMainPanel.class) return c;
			c = c.getParent();
		}
		return c;
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Component c = getPainelPrincipal();
			if (e.getSource() == btCancelar){
				try{
					if (c.getClass() == PlanoMainPanel.class)
						((PlanoMainPanel)c).getIframeSelecaoServico().setVisible(false);
					else if (c.getClass() == ApoliceEditarMainPanel.class)
						((ApoliceEditarMainPanel)c).getIframeSelecaoServico().setVisible(false);
					else System.out.println(c.getClass());
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			} 
			if (e.getSource() == btSelecionar){
				ServicoTableModel model = (ServicoTableModel)tbServicos.getModel();
				try{
					if (tbServicos.getSelectedRow() >= 0){ 
						servicoSelecionado = (ServicoVO)model.getValueAt(tbServicos.getSelectedRow(), ServicoTableModel.SERVICO);
						if (c.getClass() == PlanoMainPanel.class)
							((PlanoMainPanel)c).adicionaServico(servicoSelecionado);
					/*	else if (c.getClass() == ApoliceEditarMainPanel.class)
							((ApoliceEditarMainPanel)c).adicionaServico(servicoSelecionado);*/
					}
					if (c.getClass() == PlanoMainPanel.class)
						((PlanoMainPanel)c).getIframeSelecaoServico().setVisible(false);
					else if (c.getClass() == ApoliceEditarMainPanel.class)
						((ApoliceEditarMainPanel)c).getIframeSelecaoServico().setVisible(false);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}	
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent ev) {
			btSelecionar.setEnabled(tbServicos.getSelectedRow() >= 0); 
		}
	}
	
	private class EventoBotaoFiltrar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btFiltrar){
				recarregarTabela();
				ServicoTableModel model = (ServicoTableModel)tbServicos.getModel();
				String texto = txtServico.getText().trim().toLowerCase();
				Object servicos[] = model.getDataVector().toArray();
				for (Object item : servicos) {
					ServicoVO p = (ServicoVO)item;
					if (!p.getNome().toLowerCase().startsWith(texto))
						model.remove(p);
				}
				model.fireTableDataChanged();
			}
		}
	}
}
