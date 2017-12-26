package br.com.saodimas.view.components.text;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.Document;

import br.com.saodimas.view.components.document.CustomDocument;


@SuppressWarnings("serial")
public class CNPJTextField extends JTextField {
	private CustomDocument document;

	public CNPJTextField() {
		initialize();
	}

	@Override
	public Document getDocument() {
		return document;
	}

	@Override
	public void setDocument(Document doc) {
	}

	private void initialize(){
		document = new CustomDocument(18);
		super.setDocument(document);

		addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				formataCNPJ();
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar() < '0' || e.getKeyChar() > '9') && e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT  && e.getKeyCode() != KeyEvent.VK_RIGHT) 
					e.consume();
			}
		});
	}

	private void formataCNPJ(){
		String formatado = "";
		String semFormato =  getText().trim().replaceAll("[\\p{Punct}]", "");
		for (int i = 0; i < semFormato.length(); i++) {
			formatado += semFormato.charAt(i);
			if (i == 1 || i == 4) formatado += '.';
			if (i == 7) formatado += '/';
			if (i == 11) formatado += '-';
		}
		setText(formatado);
	}
}
