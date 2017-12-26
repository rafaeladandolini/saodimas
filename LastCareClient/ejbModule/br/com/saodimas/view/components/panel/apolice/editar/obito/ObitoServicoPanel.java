package br.com.saodimas.view.components.panel.apolice.editar.obito;

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

import br.com.saodimas.model.beans.ObitoVO;
import br.com.saodimas.model.beans.ServicoObitoVO;
import br.com.saodimas.view.components.panel.apolice.editar.ApoliceEditarMainPanel;
import br.com.saodimas.view.components.table.ObitoServicoTable;
import br.com.saodimas.view.components.table.model.ObitoServicoTableModel;

@SuppressWarnings("serial")
public class ObitoServicoPanel extends JPanel {
	private JButton btAdicionar;
	private JButton btRemover;
	private JButton btEditar;
	private JToolBar barControle;
	
	private ObitoServicoTable tbServicos;
	private List<ServicoObitoVO> removerServicosObitos;
	

	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public ObitoServicoPanel() {
		initialize();
		configure();
	}
	
	public List<ServicoObitoVO> getServicosObitoRemover(){ return removerServicosObitos;}

	public void setBloqueioCampos(boolean value)
	{
		btAdicionar.setEnabled(!value);
		btEditar.setEnabled(!value);
		btRemover.setEnabled(!value);
	}
	
	public void adicionaServico(ServicoObitoVO p){
		ObitoServicoTableModel model = (ObitoServicoTableModel)tbServicos.getModel();
		boolean editando = false; 
		
		
			for(Object item: model.getDataVector()){
				ServicoObitoVO planProd = (ServicoObitoVO)item;
				
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

	public List<ServicoObitoVO> getAllServicos(ObitoVO o)
	{
		ObitoServicoTableModel model = (ObitoServicoTableModel)tbServicos.getModel();
		Vector<ServicoObitoVO> v = model.getDataVector();
		List<ServicoObitoVO> s = new ArrayList<ServicoObitoVO>();
		
		for (ServicoObitoVO servicoPlanoVO : v) {
			servicoPlanoVO.setObito(o);
			s.add(servicoPlanoVO);
		}
		return s;
	}
	
	public void carregarServicos(List<ServicoObitoVO> servicos){
		removerServicosObitos.clear();
		ObitoServicoTableModel model = (ObitoServicoTableModel)tbServicos.getModel();
		model.removeAll();
		for (ServicoObitoVO serObito : servicos) {
			model.add(serObito);
		}
	}
	
	public void removeAllServicos()
	{
		ObitoServicoTableModel model = (ObitoServicoTableModel)tbServicos.getModel();
		model.removeAll();
	}
	
	private void initialize() {
		
		removerServicosObitos = new ArrayList<ServicoObitoVO>();
		
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
		
		
		tbServicos = new ObitoServicoTable();
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
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1; infServicos.add(prodPanel, c);
		
		infServicos.setBorder(BorderFactory.createTitledBorder("Serviços"));
		//infServicos.setMaximumSize(new Dimension(305, 75));
		//infServicos.setPreferredSize(new Dimension(305, 75));
		
		setLayout(new BorderLayout());
		add(infServicos, BorderLayout.CENTER);
	}
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof ApoliceEditarMainPanel) return c;
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
			ApoliceEditarMainPanel c = (ApoliceEditarMainPanel)getPainelPrincipal();
			if (e.getSource() == btAdicionar){
				c.getIframeSelecaoServico().setVisible(true);
				c.getIframeSelecaoServico().setServico(null);
			}
			else if (e.getSource() == btRemover){
				if (c.mostraConfirmacao("Confirmação", "Confirma a remoção do serviço para este óbito?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					ObitoServicoTableModel model = (ObitoServicoTableModel)tbServicos.getModel();
					int row = tbServicos.getSelectedRow();
					removerServicosObitos.add((ServicoObitoVO)model.getSelectedValue(row));
					model.removeAt(row);
										
				}
			}
			else if (e.getSource() == btEditar){
				int row = tbServicos.getSelectedRow();
				if (row >= 0){
					ObitoServicoTableModel model = (ObitoServicoTableModel)tbServicos.getModel();
					ServicoObitoVO prod = (ServicoObitoVO)model.getSelectedValue(row);
					c.getIframeSelecaoServico().setVisible(true);
					c.getIframeSelecaoServico().setServico(prod);
				}
			}
		}
	}
}
