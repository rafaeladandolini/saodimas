package br.com.saodimas.view.components.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import br.com.saodimas.controller.Controller;
import br.com.saodimas.model.beans.ColaboradorVO;

@SuppressWarnings("serial")
public class BarraStatus extends JPanel {
	private JLabel lbUsuario;
	private JLabel lbTempo;
	private long secs = 0;

	private static final Dimension DLABEL = new Dimension(75,20);
	private static final Dimension DPANEL = new Dimension(160,22);

	public BarraStatus() {
		initialize();
		configure();
		executar();
	}

	private void initialize(){
		lbUsuario = new JLabel(new ImageIcon("imagens/colaborador.gif"));
		lbUsuario.setHorizontalAlignment(JLabel.LEFT);
		lbUsuario.setFont(lbUsuario.getFont().deriveFont(10f).deriveFont(Font.PLAIN));
		lbUsuario.setBorder(BorderFactory.createEtchedBorder());
		lbUsuario.setOpaque(false);
		lbUsuario.setPreferredSize(DLABEL);
		lbUsuario.setMinimumSize(DLABEL);

		lbTempo = new JLabel(new ImageIcon("imagens/clock.gif"));
		lbTempo.setHorizontalAlignment(JLabel.LEFT);
		lbTempo.setFont(lbTempo.getFont().deriveFont(10f).deriveFont(Font.PLAIN));
		lbTempo.setBorder(BorderFactory.createEtchedBorder());
		lbTempo.setOpaque(false);
		lbTempo.setPreferredSize(DLABEL);
		lbTempo.setMinimumSize(DLABEL);

		secs = 0;
	}

	private void configure(){
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 1;
		c.gridy = 0; c.gridx = 0; add(lbUsuario, c);

		c.anchor = GridBagConstraints.LINE_END;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 0, 0, 0);
		c.weightx = 0;
		c.gridy = 0; c.gridx = 1; add(lbTempo, c);

		setPreferredSize(DPANEL);
		setMinimumSize(DPANEL);
	}

	private void executar(){
		SwingUtilities.invokeLater(new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception {
				new Thread(){
					@Override
					public void run() {
						while (true){
							int segundo = (int)(secs % 60);
							int minutos = (int)(secs / 60);
							int minuto = (int)(minutos % 60);
							int hora = (int)(minutos / 60);
							String hms = String.format ("%02d:%02d:%02d", hora, minuto, segundo);
							ColaboradorVO user = Controller.getInstance().getUsuarioLogado();

							if (user == null){
								secs = 0;
								lbTempo.setEnabled(false);
								lbUsuario.setEnabled(false);
								lbUsuario.setText("Usuário não logado.");
								lbTempo.setText(String.format ("%02d:%02d:%02d", 0, 0, 0));
							}
							else{
								lbUsuario.setEnabled(true);
								lbUsuario.setText("Usuário: " + user.getLogin());
								lbTempo.setEnabled(true);
								lbTempo.setText(hms);
								try {
									sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								secs++;
							}
						}
					}
				}.start();
				return null;
			}
		});
	} 
}
