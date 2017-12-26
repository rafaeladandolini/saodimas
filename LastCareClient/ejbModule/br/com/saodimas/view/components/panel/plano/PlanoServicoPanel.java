package br.com.saodimas.view.components.panel.plano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import br.com.saodimas.model.beans.PlanoVO;
import br.com.saodimas.model.beans.ServicoPlanoVO;
import br.com.saodimas.model.beans.ServicoVO;
import br.com.saodimas.view.components.table.PlanoServicoTable;
import br.com.saodimas.view.components.table.model.PlanoServicoTableModel;

@SuppressWarnings("serial")
public class PlanoServicoPanel extends JPanel {
	private JButton btAdicionar;
	private JButton btRemover;
	private JButton btNovo;
	private JToolBar barControle;
	
	private PlanoServicoTable tbServicos;
	
	private static final Dimension DBUTTON = new Dimension(30,30);
	
	public PlanoServicoPanel() {
		initialize();
		configure();
	}

	public void adicionaServico(ServicoVO p){
		PlanoServicoTableModel model = (PlanoServicoTableModel)tbServicos.getModel();
		boolean prodExists = false; 
		for(Object item: model.getDataVector()){
			ServicoPlanoVO planProd = (ServicoPlanoVO)item;
    		int quantidade = planProd.getQuantidade();
			if (p.getNome().compareToIgnoreCase(planProd.getServico().getNome()) == 0){
				planProd.setQuantidade(quantidade + 1);
				prodExists = true;
				System.out.println("Achei o serviço na tabela!");
				break;
			}
		}
    	if (!prodExists){
    		ServicoPlanoVO pp = new ServicoPlanoVO();
    		pp.setServico(p);
    		pp.setQuantidade(1);
    		model.add(pp);
    	}
		model.fireTableDataChanged();
		tbServicos.getSelectionModel().addSelectionInterval(0, 0);
	}

	public void removeAllServicos()
	{
		PlanoServicoTableModel model = (PlanoServicoTableModel)tbServicos.getModel();
		model.removeAll();
	}
	
	public Set<ServicoPlanoVO> getAllServicos(PlanoVO p)
	{
		PlanoServicoTableModel model = (PlanoServicoTableModel)tbServicos.getModel();
		Vector<ServicoPlanoVO> v = model.getDataVector();
		Set<ServicoPlanoVO> s = new HashSet<ServicoPlanoVO>();
		
		for (ServicoPlanoVO servicoPlanoVO : v) {
			servicoPlanoVO.setPlano(p);
			s.add(servicoPlanoVO);
		}
		return s;
	}
	
	public void carregarServicos(List<ServicoPlanoVO> servicos) {
		PlanoServicoTableModel model = (PlanoServicoTableModel)tbServicos.getModel();
		model.removeAll();
		for (ServicoPlanoVO servPlano : servicos) {
			model.add(servPlano);
		}
	}

	private void initialize() {
		btAdicionar = new JButton();
		btAdicionar.setIcon(new ImageIcon("imagens/add.gif"));
		btAdicionar.addActionListener(new EventoBotaoControle());
		btAdicionar.setToolTipText("Clique para adicionar um serviço ao plano!");
		btAdicionar.setPreferredSize(DBUTTON);
		
		btRemover = new JButton();
		btRemover.setIcon(new ImageIcon("imagens/remove.gif"));
		btRemover.addActionListener(new EventoBotaoControle());
		btRemover.setToolTipText("Clique para remover um serviço do plano!");
		btRemover.setPreferredSize(DBUTTON);
		btRemover.setEnabled(false);

		btNovo = new JButton();
		btNovo.setIcon(new ImageIcon("imagens/addServices.gif"));
		btNovo.addActionListener(new EventoBotaoControle());
		btNovo.setToolTipText("Cadastrar um novo serviço!");
		btNovo.setPreferredSize(DBUTTON);
		
		barControle = new JToolBar();
		barControle.setFloatable(false);
		barControle.setOpaque(false);
		barControle.setBorderPainted(false);
		barControle.setBorder(BorderFactory.createEmptyBorder());
		barControle.setMargin(new Insets(0, 0, 0, 0));
		barControle.add(btAdicionar);
		barControle.add(btRemover);
		barControle.addSeparator();
		barControle.add(btNovo);
		
		tbServicos = new PlanoServicoTable();
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
		infServicos.setMaximumSize(new Dimension(305, 75));
		infServicos.setPreferredSize(new Dimension(305, 75));
		
		setLayout(new BorderLayout());
		add(infServicos, BorderLayout.CENTER);
	}

	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c.getClass() == PlanoMainPanel.class) return c;
			c = c.getParent();
		}
			
		return c;
	}	
	
	private class EventoTabela implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			btRemover.setEnabled(tbServicos.getSelectedRow() > -1);
		}
	}
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			PlanoMainPanel c = (PlanoMainPanel)getPainelPrincipal();
			if (e.getSource() == btAdicionar){
				c.getIframeSelecaoServico().setVisible(true);
			}
			else if (e.getSource() == btRemover){
				if (c.mostraConfirmacao("Confirmação", "Confirma a remoção do serviço para este plano?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){				
					int row = tbServicos.getSelectedRow();
					PlanoServicoTableModel model = (PlanoServicoTableModel)tbServicos.getModel();
					model.removeAt(row);
					model.fireTableDataChanged();
				}
			}
			else if (e.getSource() == btNovo){
				c.getIframeServico().setVisible(true);
			}
		}
	}
}
