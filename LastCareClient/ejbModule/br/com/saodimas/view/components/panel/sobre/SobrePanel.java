package br.com.saodimas.view.components.panel.sobre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.saodimas.view.components.panel.CustomLayeredPanel;


@SuppressWarnings("serial")
public class SobrePanel extends JPanel {
	public static final String FORM_NAME = "Sobre"; 

	private JLabel lbImagem;
	private JEditorPane edtSobre;
	private JButton btOk;
	
	private static final Dimension DSOBRE = new Dimension(350,170);
	
	public SobrePanel() {
		initialize();
		configure();
	}

	private void initialize() {
		lbImagem = new JLabel(new ImageIcon("imagens/stDimas.png"));
		lbImagem.setOpaque(true);
		lbImagem.setBackground(Color.white);
		lbImagem.setPreferredSize(new Dimension(63, 165));
		lbImagem.setMinimumSize(new Dimension(63, 165));
		lbImagem.setMaximumSize(new Dimension(63, 165));
		
		edtSobre = new JEditorPane();
		edtSobre.setPreferredSize(DSOBRE);
		edtSobre.setMinimumSize(DSOBRE);
		edtSobre.setEditable(false);
		try {
			edtSobre.setPage(getClass().getResource("about.html"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		btOk = new JButton("OK", new ImageIcon("imagens/accept.gif"));
		btOk.addActionListener(new EventoBotaoControle());
		btOk.setHorizontalAlignment(JButton.LEFT);
	}
	
	private void configure() {
		setLayout(new BorderLayout());
		
		JPanel controle = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		controle.add(btOk);
		controle.setMinimumSize(new Dimension(200, 22));
		
		add(lbImagem, BorderLayout.WEST);
		add(edtSobre/*scrollSobre*/, BorderLayout.CENTER);
		add(controle, BorderLayout.SOUTH);
	}
	
	private Component getPainelPrincipal(){
		Component c = getParent();
		while (c != null){
			if (c instanceof CustomLayeredPanel) return c;
			c = c.getParent();
		}
		return c;
	}	
	
	private class EventoBotaoControle implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btOk){
				CustomLayeredPanel c = (CustomLayeredPanel)getPainelPrincipal();
				c.getIframeSobre().setVisible(false);
			} 
		}
	}
}	