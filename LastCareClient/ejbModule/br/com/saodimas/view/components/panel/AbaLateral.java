package br.com.saodimas.view.components.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.saodimas.view.components.label.JVerticalLabel;


@SuppressWarnings("serial")
public class AbaLateral extends JPanel{
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	private JLabel bordaInf;
	private JVerticalLabel lbVertical;
	private JLabel bordaSup;
	private int posicao;
	private String texto;
	
	public AbaLateral(String texto, int posicao){
		this.posicao = posicao;
		this.texto = texto;
		this.initialize();
		this.configure();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		BufferedImage image = posicao % 2 == 0 ? loadBufferedImage("verticalTabLeft.gif") : loadBufferedImage("verticalTabRight.gif");
		Graphics2D graphicAux = (Graphics2D) g;
		Rectangle2D bgArea = new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
		TexturePaint bgTexture = new TexturePaint(image, bgArea);
			
        graphicAux.setPaint(bgTexture);
        Rectangle2D r = (Rectangle2D)this.getBounds();  
        graphicAux.fill(r);
	}	
	
	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.bordaInf.setIcon(posicao % 2 == 0 ? new ImageIcon("imagens/verticalTabBottomLeft.gif"):  new ImageIcon("imagens/verticalTabBottomRight.gif"));
		this.lbVertical.setHorizontalAlignment(posicao % 2 == 0 ? JLabel.LEFT : JLabel.RIGHT);
		this.bordaSup.setIcon(posicao % 2 == 0 ? new ImageIcon("imagens/verticalTabTopLeft.gif"):  new ImageIcon("imagens/verticalTabTopRight.gif"));
		this.posicao = posicao;
		repaint();
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	private final void initialize(){
		//this.setVisible(false);
		//this.setOpaque(false);

		this.bordaInf = new JLabel(posicao % 2 == 0 ? new ImageIcon("imagens/verticalTabBottomLeft.gif"):  new ImageIcon("imagens/verticalTabBottomRight.gif"));
		this.bordaInf.setPreferredSize(new Dimension(22, 11));
		this.bordaInf.setMinimumSize(new Dimension(22, 11));
		this.bordaInf.setMaximumSize(new Dimension(22, 11));
		this.bordaInf.setOpaque(true);

		this.lbVertical = new JVerticalLabel(texto, null, JLabel.LEFT);
		this.lbVertical.setPreferredSize(new Dimension(22, 100));
		this.lbVertical.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
		this.lbVertical.setFont(this.lbVertical.getFont().deriveFont(Font.PLAIN, 11f));
		this.lbVertical.setOpaque(false);
		
		this.bordaSup = new JLabel(posicao % 2 == 0 ? new ImageIcon("imagens/verticalTabTopLeft.gif"):  new ImageIcon("imagens/verticalTabTopRight.gif")); 
		this.bordaSup.setPreferredSize(new Dimension(22, 11));
		this.bordaSup.setMinimumSize(new Dimension(22, 11));
		this.bordaSup.setMaximumSize(new Dimension(22, 11));
		this.bordaSup.setOpaque(true);
		setPreferredSize(new Dimension(22, 122));
		this.repaint();
	}
	
	private final void configure(){
		GridBagConstraints gc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		gc.anchor	= GridBagConstraints.LINE_START;
		gc.fill		= GridBagConstraints.NONE;
		gc.weightx	= 0.0;
		gc.weighty	= 0.0;
		gc.gridx	= 0;
		gc.gridy	= 0; this.add(bordaSup, gc);
		
		gc.anchor	= GridBagConstraints.LAST_LINE_START;
		gc.fill		= GridBagConstraints.HORIZONTAL;
		gc.weightx	= 1.0;
		gc.weighty	= 1.0;
		gc.gridy	= 1; this.add(lbVertical, gc);
		
		gc.anchor	= GridBagConstraints.LINE_END;
		gc.fill		= GridBagConstraints.NONE;
		gc.weightx	= 0.0;
		gc.weighty	= 0.0;
		gc.gridy	= 2; this.add(bordaInf, gc);
	}
	
	private final BufferedImage loadBufferedImage(String imageName){
		try {
			return ImageIO.read(new File("imagens/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
