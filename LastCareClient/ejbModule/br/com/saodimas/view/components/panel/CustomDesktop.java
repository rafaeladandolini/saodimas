package br.com.saodimas.view.components.panel;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDesktopPane;

import br.com.saodimas.view.util.FrameTools;


@SuppressWarnings("serial")
public class CustomDesktop extends JDesktopPane {
	public CustomDesktop() {
		int frameWidth = FrameTools.getDefaultDimension().width;
		int frameHeight = FrameTools.getDefaultDimension().height;
		
		this.setOpaque(false);
		this.setLocation(new Point(0, 0));
		this.setSize(frameWidth - 7, frameHeight - 2);
		this.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
		this.setDoubleBuffered(true);
		this.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				int x = e.getComponent().getWidth() / 2;
				int y = e.getComponent().getHeight() / 2;
				
				for (Component c : getComponents()) {
					if (c.isVisible()){
						c.setLocation(x - c.getSize().width / 2, y - c.getSize().height / 2);
					}
					c.repaint();
				}
				super.componentResized(e);
			}
		});
	}
}
