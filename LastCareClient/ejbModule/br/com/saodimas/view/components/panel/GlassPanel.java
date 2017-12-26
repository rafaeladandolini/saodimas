package br.com.saodimas.view.components.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalLabelUI;

import br.com.saodimas.view.util.FrameTools;


@SuppressWarnings("serial")
public class GlassPanel extends JPanel{
	public GlassPanel() {
		int frameWidth = FrameTools.getDefaultDimension().width;
		int frameHeight = FrameTools.getDefaultDimension().height;

		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setLocation(new Point(0, 0));
		this.setSize(frameWidth - 7, frameHeight - 2);
		this.setDoubleBuffered(true);
		this.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				for (Component c : getComponents()) {
					c.setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
					c.repaint();
				}
				super.componentResized(e);
			}
		});
		
		JLabel darkTint = new JLabel();
		darkTint.setBackground(new Color(0,0,0,130));
		darkTint.setUI(new MetalLabelUI());
		darkTint.setOpaque(true);
		this.add(darkTint, BorderLayout.CENTER);
	}
}
