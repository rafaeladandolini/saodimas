package br.com.saodimas.view.components.panel.sobre;

import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import br.com.saodimas.view.components.iframe.SobreIFrame;


@SuppressWarnings("serial")
public class AjudaPanel extends JLayeredPane {
	public static final String FORM_NAME = "Ajuda"; 
	
	private JPanel glass;
	private JDesktopPane desktop;
	private SobreIFrame iframeSobre;

	public AjudaPanel(JPanel glassPanel, JDesktopPane desktopPane) {
		this.glass = glassPanel;
		this.desktop = desktopPane;
		initialize();
		configure();
	}

	public SobreIFrame getIframeSobre() {
		return iframeSobre;
	}

	private void initialize(){
		int x = desktop.getWidth() / 2;
		int y = desktop.getHeight() / 2;
		
		iframeSobre = new SobreIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (glass != null) glass.setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeSobre.setLocation(x - iframeSobre.getSize().width / 2, y - iframeSobre.getSize().height / 2);
		
		desktop.add(iframeSobre);
	}
	
	private void configure(){
		add(glass, JLayeredPane.MODAL_LAYER, 0);
		add(desktop, JLayeredPane.DRAG_LAYER, 1);
		setOpaque(false);
	}
}