package br.com.saodimas.view.components.panel.produto;

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
import br.com.saodimas.model.beans.ProdutoVO;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.panel.plano.PlanoMainPanel;
import br.com.saodimas.view.components.table.ProdutoTable;
import br.com.saodimas.view.components.table.model.ProdutoTableModel;

@SuppressWarnings("serial")
public class ProdutoSelecaoPanel extends JPanel {
	private ProdutoVO produtoSelecionado;
	private JTextField txtProduto;
	private JButton btFiltrar;
	private JToolBar barPesquisa;
	private ProdutoTable tbProdutos;
	private JButton btCancelar;
	private JButton btSelecionar;

	public ProdutoSelecaoPanel() {
		initialize();
		configure();
	}

	public ProdutoVO getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(ProdutoVO produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public void recarregarTabela(){
		limparTabela();
		ProdutoTableModel model = (ProdutoTableModel)tbProdutos.getModel();
		model.loadData(Controller.getInstance().getProdutoAtivos()); 
		model.fireTableDataChanged();
		txtProduto.requestFocus();
	}

	public void limparTabela(){
		ProdutoTableModel model = (ProdutoTableModel)tbProdutos.getModel();
		model.removeAll();
	}

	private void initialize() {
		txtProduto = new JTextField();
		txtProduto.setToolTipText("Digite parte do nome de um produto!");
		txtProduto.addKeyListener(new KeyAdapter(){
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
		barPesquisa.add(txtProduto);
		barPesquisa.add(btFiltrar);

		tbProdutos = new ProdutoTable();
		tbProdutos.setPreferredSize(new Dimension(300, 200));
		tbProdutos.setMinimumSize(new Dimension(300, 200));
		tbProdutos.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) btSelecionar.doClick();
				super.mouseClicked(e);
			}
		});
		tbProdutos.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) btCancelar.doClick();
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) btSelecionar.doClick();
				else super.keyPressed(e);
			}
		});
		tbProdutos.getSelectionModel().addListSelectionListener(new EventoTabela());

		btCancelar = new JButton("Cancelar", new ImageIcon("imagens/cancel.gif"));
		btCancelar.addActionListener(new EventoBotaoControle());
		btCancelar.setHorizontalAlignment(JButton.LEFT);

		btSelecionar = new JButton("Selecionar", new ImageIcon("imagens/accept.gif"));
		btSelecionar.addActionListener(new EventoBotaoControle());
		btSelecionar.setHorizontalAlignment(JButton.LEFT);
		btSelecionar.setEnabled(false);

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

		JScrollPane prodPanel = new JScrollPane(tbProdutos);
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
						((PlanoMainPanel)c).getIframeSelecaoProduto().setVisible(false);
					else if (c.getClass() == ApoliceEditarMainPanel.class)
						((ApoliceEditarMainPanel)c).getIframeSelecaoProduto().setVisible(false);
				}
				catch(Exception ex){}
			} 
			if (e.getSource() == btSelecionar){
				ProdutoTableModel model = (ProdutoTableModel)tbProdutos.getModel();
				try{
					if (tbProdutos.getSelectedRow() >= 0){ 
						produtoSelecionado = (ProdutoVO)model.getValueAt(tbProdutos.getSelectedRow(), ProdutoTableModel.PRODUTO);
						if (c.getClass() == PlanoMainPanel.class)
							((PlanoMainPanel)c).adicionaProduto(produtoSelecionado);
						//else if (c.getClass() == ApoliceEditarMainPanel.class)
						//	((ApoliceEditarMainPanel)c).adicionaProduto(produtoSelecionado);
					}
					if (c.getClass() == PlanoMainPanel.class)
						((PlanoMainPanel)c).getIframeSelecaoProduto().setVisible(false);
					else if (c.getClass() == ApoliceEditarMainPanel.class)
						((ApoliceEditarMainPanel)c).getIframeSelecaoProduto().setVisible(false);				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}	

	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent ev) {
			btSelecionar.setEnabled(tbProdutos.getSelectedRow() >= 0); 
			
		}
	}

	private class EventoBotaoFiltrar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btFiltrar){
				recarregarTabela();
				ProdutoTableModel model = (ProdutoTableModel)tbProdutos.getModel();
				String texto = txtProduto.getText().trim().toLowerCase();
				Object produtos[] = model.getDataVector().toArray();
				for (Object item : produtos) {
					ProdutoVO p = (ProdutoVO)item;
					if (!p.getNome().toLowerCase().startsWith(texto))
						model.remove(p);
				}
				model.fireTableDataChanged();
			}
		}
	}
}
