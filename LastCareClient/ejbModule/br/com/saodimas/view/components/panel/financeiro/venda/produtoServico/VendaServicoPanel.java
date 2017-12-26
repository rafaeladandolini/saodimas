package br.com.saodimas.view.components.panel.financeiro.venda.produtoServico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.saodimas.model.beans.ServicoVendaVO;
import br.com.saodimas.model.beans.VendaVO;
import br.com.saodimas.view.components.panel.financeiro.cliente.editar.ClienteEditarMainPanel;
import br.com.saodimas.view.components.table.VendaServicoTable;
import br.com.saodimas.view.components.table.model.VendaServicoTableModel;

@SuppressWarnings("serial")
public class VendaServicoPanel extends JPanel {
	
	private JButton btAdicionar;
	private JButton btRemover;
	private JButton btEditar;
	private JToolBar barControle;
	
	private VendaServicoTable tbServicos;
	private List<ServicoVendaVO> removerServicosVenda;
	

	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public VendaServicoPanel() {
		initialize();
		configure();
	}
	
	public List<ServicoVendaVO> getServicosVendaRemover(){ return removerServicosVenda;}

	public void setBloqueioCampos(boolean value)
	{
		btAdicionar.setEnabled(!value);
		btEditar.setEnabled(!value);
		btRemover.setEnabled(!value);
	}
	
	public void adicionaServico(ServicoVendaVO p){
		VendaServicoTableModel model = (VendaServicoTableModel)tbServicos.getModel();
		boolean editando = false; 
		
		
			for(Object item: model.getDataVector()){
				ServicoVendaVO planProd = (ServicoVendaVO)item;
				
				if ((p.getId() == null && p.getValor().equals(planProd.getValor()) && planProd.getServico().getId().equals(p.getServico().getId())))
				{
					planProd.setQuantidade(planProd.getQuantidade() + p.getQuantidade());
					planProd.setTotal(p.getValor() * planProd.getQuantidade());
					planProd.setValor(p.getValor());
					editando = true;
					break;
				}else						
				if(p.getId() != null && p.getId().equals(planProd.getId())){
						planProd.setQuantidade(p.getQuantidade());
						planProd.setTotal(p.getValor() * planProd.getQuantidade());
						planProd.setValor(p.getValor());
						editando = true;
						break;
				}
				
			}
		
    	if (!editando){
    	
    		model.add(p);
    	}
		model.fireTableDataChanged();
		tbServicos.getSelectionModel().addSelectionInterval(0, 0);
	}

	public List<ServicoVendaVO> getAllServicos(VendaVO venda)
	{
		VendaServicoTableModel model = (VendaServicoTableModel)tbServicos.getModel();
		Vector<ServicoVendaVO> v = model.getDataVector();
		List<ServicoVendaVO> s = new ArrayList<ServicoVendaVO>();
		
		for (ServicoVendaVO servicoPlanoVO : v) {
			servicoPlanoVO.setVenda(venda);
			s.add(servicoPlanoVO);
		}
		return s;
	}
	
	public void carregarServicos(List<ServicoVendaVO> servicos){
		removerServicosVenda.clear();
		VendaServicoTableModel model = (VendaServicoTableModel)tbServicos.getModel();
		model.removeAll();
		if(servicos != null){
			for (ServicoVendaVO serObito : servicos) {
				model.add(serObito);
			}
		}
	}
	
	public void removeAllServicos()
	{
		VendaServicoTableModel model = (VendaServicoTableModel)tbServicos.getModel();
		model.removeAll();
	}
	
	private void initialize() {
		
		removerServicosVenda = new ArrayList<ServicoVendaVO>();
		
		btAdicionar = new JButton();
		btAdicionar.setIcon(new ImageIcon("imagens/add.gif"));
		btAdicionar.addActionListener(new EventoBotaoControle());
		btAdicionar.setToolTipText("Clique para adicionar um serviço ao óbito!");
		btAdicionar.setPreferredSize(DBUTTON);
		
		btRemover = new JButton();
		btRemover.setIcon(new ImageIcon("imagens/remove.gif"));
		btRemover.addActionListener(new EventoBotaoControle());
		btRemover.setToolTipText("Clique para remover um serviço do óbito!");
		btRemover.setPreferredSize(DBUTTON);
		btRemover.setEnabled(false);

		btEditar = new JButton();
		btEditar.setIcon(new ImageIcon("imagens/edit.gif"));
		btEditar.addActionListener(new EventoBotaoControle());
		btEditar.setToolTipText("Editar Produto!");
		btEditar.setPreferredSize(DBUTTON);
		btEditar.setEnabled(false);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btAdicionar);
		barControle.add(btEditar);
		barControle.add(btRemover);
		
			
		tbServicos = new VendaServicoTable();
		tbServicos.getSelectionModel().addListSelectionListener(new EventoTabela());
	}
	
	private void configure() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel infServicos = new JPanel(new GridBagLayout());

		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 1, 0, 1);
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0; infServicos.add(barControle, c);
		
		JScrollPane prodPanel = new JScrollPane(tbServicos);
		prodPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		prodPanel.setPreferredSize(new Dimension(500, 200));
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infServicos.add(prodPanel, c);
		
		infServicos.setBorder(BorderFactory.createTitledBorder("Serviços"));
		
		setLayout(new BorderLayout());
		add(infServicos, BorderLayout.CENTER);
	}
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ClienteEditarMainPanel) return c;
			c = c.getParent();
		}
		return c;
	}
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btRemover.setEnabled(tbServicos.getSelectedRow() > -1);
			btEditar.setEnabled(tbServicos.getSelectedRow() > -1);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ClienteEditarMainPanel c = (ClienteEditarMainPanel)getPainelPrincipal();
			if (e.getSource() == btAdicionar){
				c.getIframeVendaServico().setVisible(true);
				c.getIframeVendaServico().setServico(null);
			}
			else if (e.getSource() == btRemover){
				if (c.mostraConfirmacao("Confirmação", "Confirma a remoção do serviço para este óbito?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					VendaServicoTableModel model = (VendaServicoTableModel)tbServicos.getModel();
					int row = tbServicos.getSelectedRow();
					removerServicosVenda.add((ServicoVendaVO)model.getSelectedValue(row));
					model.removeAt(row);
										
				}
			}
			else if (e.getSource() == btEditar){
				int row = tbServicos.getSelectedRow();
				if (row >= 0){
					VendaServicoTableModel model = (VendaServicoTableModel)tbServicos.getModel();
					ServicoVendaVO prod = (ServicoVendaVO)model.getSelectedValue(row);
					c.getIframeVendaServico().setVisible(true);
					c.getIframeVendaServico	().setServico(prod);
				}
			}
		}
	}
}
