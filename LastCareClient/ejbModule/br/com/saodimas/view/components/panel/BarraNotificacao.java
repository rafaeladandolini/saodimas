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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BarraNotificacao extends JPanel {
	public static final int DEFAULT = -1;
	public static final int ERRO = 0;
	public static final int AVISO = 1;
	public static final int SUCESSO = 2;
	public static final int DICA = 3;
	public static final int INFO = 4;

	private JLabel bordaEsq;
	private JLabel mensagem;
	private JLabel bordaDir;
	private String textoDefault;

	public BarraNotificacao(String textoDefault){
		this.textoDefault = textoDefault.trim();
		this.initialize();
		this.configure();
		mostrarMensagem(textoDefault, DEFAULT);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		//SEMPRE HABILITADO
		super.setEnabled(enabled || !enabled);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		BufferedImage image = loadBufferedImage("imagens/tabCenter.gif");		
		Graphics2D graphicAux = (Graphics2D) g;
		if(image != null) {
			Rectangle2D bgArea = new Rectangle2D.Double(0, 0, image.getWidth(), image.getHeight());
			TexturePaint bgTexture = new TexturePaint(image, bgArea);
			graphicAux.setPaint(bgTexture);
		}
		Rectangle2D r = (Rectangle2D)this.getBounds();  
		graphicAux.fill(r);
	}	

	public void mostrarMensagem(String texto, int tipoMensagem){
		if (texto == null || texto.trim().compareTo("") == 0) escondeMensagem();
		else{
			texto = texto.trim();
			switch (tipoMensagem) {
			case ERRO:
				this.mensagem.setIcon(new ImageIcon("imagens/error.gif"));
				this.mensagem.setText(texto.trim());
				this.mensagem.setOpaque(false);
				this.mensagem.setFont(this.mensagem.getFont().deriveFont(Font.PLAIN));

				this.bordaDir.setVisible(true);
				this.bordaEsq.setVisible(true);
				break;
			case SUCESSO:
				this.mensagem.setIcon(new ImageIcon("imagens/accept.gif"));
				this.mensagem.setText(texto);
				this.mensagem.setOpaque(false);
				this.mensagem.setFont(this.mensagem.getFont().deriveFont(Font.PLAIN));

				this.bordaDir.setVisible(true);
				this.bordaEsq.setVisible(true);
				new Thread(){
					@Override
					public void run() {
						try {
							sleep(10000);
							escondeMensagem();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
				break;
			case AVISO:
				this.mensagem.setIcon(new ImageIcon("imagens/warning.gif"));
				this.mensagem.setText(texto);
				this.mensagem.setOpaque(false);
				this.mensagem.setFont(this.mensagem.getFont().deriveFont(Font.PLAIN));

				this.bordaDir.setVisible(true);
				this.bordaEsq.setVisible(true);
				break;
			case DICA:
				this.mensagem.setIcon(new ImageIcon("imagens/hint.gif"));
				this.mensagem.setText(texto);
				this.mensagem.setOpaque(false);
				this.mensagem.setFont(this.mensagem.getFont().deriveFont(Font.PLAIN));

				this.bordaDir.setVisible(true);
				this.bordaEsq.setVisible(true);
				break;
			case INFO:
				this.mensagem.setIcon(new ImageIcon("imagens/info.gif"));
				this.mensagem.setText(texto);
				this.mensagem.setOpaque(false);
				this.mensagem.setFont(this.mensagem.getFont().deriveFont(Font.PLAIN));

				this.bordaDir.setVisible(true);
				this.bordaEsq.setVisible(true);
				break;
			default: 
				escondeMensagem();
				this.mensagem.setText(texto);
				break;
			}
		}
		this.repaint();
	}

	public void escondeMensagem(){
		if (textoDefault != null && textoDefault.trim().length() > 0){
			this.mensagem.setIcon(new ImageIcon("imagens/hint.gif"));
			this.mensagem.setText(textoDefault.trim());
			this.mensagem.setOpaque(false);
			this.mensagem.setFont(this.mensagem.getFont().deriveFont(Font.PLAIN));

			this.bordaDir.setVisible(true);
			this.bordaEsq.setVisible(true);
		}
		else{
			this.mensagem.setText(textoDefault);
			this.mensagem.setIcon(null);
			this.mensagem.setOpaque(true);
			this.mensagem.setFont(this.mensagem.getFont().deriveFont(Font.ITALIC));
			this.setVisible(true);
			this.bordaDir.setVisible(false);
			this.bordaEsq.setVisible(false);
		}
	}

	private final void initialize(){
		this.repaint();
		this.setPreferredSize(new Dimension(272, 22));
		this.setMinimumSize(new Dimension(272, 22));

		this.bordaEsq = new JLabel(new ImageIcon("imagens/tabLeft.gif")){
			@Override
			public void setEnabled(boolean enabled) {
				super.setEnabled(enabled || !enabled);
			}
		};
		this.bordaEsq.setPreferredSize(new Dimension(11, 22));
		this.bordaEsq.setMinimumSize(new Dimension(11, 22));
		this.bordaEsq.setMaximumSize(new Dimension(11, 22));
		this.bordaEsq.setOpaque(true);

		this.mensagem = new JLabel("", null, JLabel.LEFT){
			@Override
			public void setEnabled(boolean enabled) {
				super.setEnabled(enabled || !enabled);
			}
		};
		this.mensagem.setPreferredSize(new Dimension(250, 22));
		this.mensagem.setFont(this.mensagem.getFont().deriveFont(Font.PLAIN));
		this.mensagem.setOpaque(false);

		this.bordaDir = new JLabel(new ImageIcon("imagens/tabRight.gif")){
			@Override
			public void setEnabled(boolean enabled) {
				super.setEnabled(enabled || !enabled);
			}
		};
		this.bordaDir.setPreferredSize(new Dimension(11, 22));
		this.bordaDir.setMinimumSize(new Dimension(11, 22));
		this.bordaDir.setMaximumSize(new Dimension(11, 22));
		this.bordaDir.setOpaque(true);

	}

	private final void configure(){
		GridBagConstraints gc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

		gc.anchor	= GridBagConstraints.LINE_START;
		gc.fill		= GridBagConstraints.NONE;
		gc.weightx	= 0.0;
		gc.weighty	= 1.0;
		gc.gridy	= 0;
		gc.gridx	= 0; this.add(bordaEsq, gc);

		gc.anchor	= GridBagConstraints.LAST_LINE_START;
		gc.fill		= GridBagConstraints.HORIZONTAL;
		gc.weightx	= 1.0;
		gc.weighty	= 1.0;
		gc.gridx	= 1; this.add(mensagem, gc);

		gc.anchor	= GridBagConstraints.LINE_END;
		gc.fill		= GridBagConstraints.NONE;
		gc.weightx	= 0.0;
		gc.weighty	= 1.0;
		gc.gridx	= 2; this.add(bordaDir, gc);
	}

	private final BufferedImage loadBufferedImage(String imageName){
		try {
			return ImageIO.read(new File(imageName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
