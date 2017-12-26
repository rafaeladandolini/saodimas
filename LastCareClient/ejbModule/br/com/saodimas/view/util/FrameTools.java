package br.com.saodimas.view.util;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;

public final class FrameTools {
	public static final int LF_SYSTEM	= 0; 
	public static final int LF_MOTIF	= 1; 
	public static final int LF_METAL	= 2; 
	
	
	public static void centralize(JWindow window){
		int fWidth = window.getWidth();
		int fHeight = window.getHeight();
		int screenWidth = (int)(window.getToolkit().getScreenSize().getWidth());
		int screenHeight = (int)(window.getToolkit().getScreenSize().getHeight());
		
		Point position = new Point((screenWidth - fWidth)/2,(screenHeight - fHeight)/2);
		
		window.setLocation(position);
	}

	public static void centralize(JFrame frame){
		int fWidth = frame.getWidth();
		int fHeight = frame.getHeight();
		int screenWidth = (int)(frame.getToolkit().getScreenSize().getWidth());
		int screenHeight = (int)(frame.getToolkit().getScreenSize().getHeight());
		
		Point position = new Point((screenWidth - fWidth)/2,(screenHeight - fHeight)/2);
		
		frame.setLocation(position);
	}

	public static void centralize(JDialog dialog){
		int fWidth = dialog.getWidth();
		int fHeight = dialog.getHeight();
		int screenWidth = (int)(dialog.getToolkit().getScreenSize().getWidth());
		int screenHeight = (int)(dialog.getToolkit().getScreenSize().getHeight());
		
		Point position = new Point((screenWidth - fWidth)/2,(screenHeight - fHeight)/2);
		
		dialog.setLocation(position);
	}
	
	public static void defaultSize(JFrame frame){
		int defWidth = (int)(frame.getToolkit().getScreenSize().getWidth() * 0.90f);
		int defHeight = (int)(frame.getToolkit().getScreenSize().getHeight() * 0.90f);
		
		frame.setSize(defWidth, defHeight);
	}

	/**
	 * Retorna o tamanho padrão que um frame poderá ter. 
	 * @return (Dimension)
	 */
	public static Dimension getDefaultDimension(){
		JFrame frame = new JFrame();
		int defWidth = (int)(frame.getToolkit().getScreenSize().getWidth() * 0.90f);
		int defHeight = (int)(frame.getToolkit().getScreenSize().getHeight() * 0.90f - 55);
		
		return new Dimension(defWidth, defHeight);
	}	
}
