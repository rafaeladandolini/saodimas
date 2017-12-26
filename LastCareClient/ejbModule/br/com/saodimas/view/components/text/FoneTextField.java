package br.com.saodimas.view.components.text;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.Document;

import br.com.saodimas.view.components.document.CustomDocument;


@SuppressWarnings("serial")
public class FoneTextField extends JTextField {
	private CustomDocument document;

	public FoneTextField() {
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
		document = new CustomDocument(14);
		super.setDocument(document);

		addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				formataFone();
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar() < '0' || e.getKeyChar() > '9') && e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT  && e.getKeyCode() != KeyEvent.VK_RIGHT) 
					e.consume();
			}
		});
	}

	private void formataFone(){
		String formatado = "";
		String semFormato =  getText().trim().replaceAll("[\\p{Punct}\\p{Blank}]", "");
		for (int i = 0; i < semFormato.length(); i++) {
			formatado += semFormato.charAt(i);
			if (i == 1) formatado =  "(" + formatado + ") ";
			if (i == 5) formatado += "-";
		}
		setText(formatado);
	}
}
