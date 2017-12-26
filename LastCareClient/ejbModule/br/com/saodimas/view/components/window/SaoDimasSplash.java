package br.com.saodimas.view.components.window;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import br.com.saodimas.view.util.FrameTools;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class SaoDimasSplash extends JWindow {
	private JProgressBar progressBar = new JProgressBar();
	private JPanel loaderPanel;
	private JLabel lbLoading;
	private JLabel lbSplash;
	private Thread loader;

	public SaoDimasSplash() { 
		initialize();
	}

	private void initialize(){
		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(10000);
		progressBar.setStringPainted(true);
		progressBar.setValue(progressBar.getMinimum());
		progressBar.setOpaque(false);
		progressBar.setForeground(new Color(225,185,110));

		lbLoading = new JLabel(new ImageIcon("imagens/carregando.gif"));
		lbLoading.setVisible(false);
		lbLoading.setBackground(Color.white);
		lbLoading.setOpaque(true);

		loaderPanel = new JPanel(new BorderLayout());
		loaderPanel.add(lbLoading, BorderLayout.NORTH);
		loaderPanel.add(progressBar, BorderLayout.SOUTH);

		lbSplash = new JLabel(new ImageIcon("imagens/logoS.png"));

		this.getContentPane().add(lbSplash, BorderLayout.CENTER);
		this.getContentPane().add(loaderPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.pack();
		FrameTools.centralize(this);
		
		if (loader == null) {
			loader = new SaoDimasLoader();
			loader.start(); 
		}
	}

	class SaoDimasLoader extends Thread { 
		public void run() {
			Runnable runner = new Runnable() {
				public void run() {
					int valor = progressBar.getValue();
					progressBar.setValue(valor+1);
				}
			};
			int progresso = 0;
			while(WinManager.getJanelaInicial() == null ){
				if (progresso > progressBar.getMaximum()){
					progressBar.setVisible(false);
					progressBar.setValue(progressBar.getMaximum());
					lbLoading.setVisible(true);
				}
				else{
					try {
						SwingUtilities.invokeAndWait(runner); 
					}
					catch (Exception e){
						e.printStackTrace();
					}
					progresso++;
				}
			}
			
			progressBar.setValue(progressBar.getMaximum());
			WinManager.getJanelaInicial().toFront();
			dispose();

			loader = null; 
		}
	}
}

