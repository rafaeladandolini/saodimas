package br.com.saodimas.view.components.text;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.text.Document;

import br.com.saodimas.view.components.document.CustomDocument;


@SuppressWarnings("serial")
public class CPFTextField extends JTextField {
	private CustomDocument document;

	public CPFTextField() {
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
				formataCPF();
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar() < '0' || e.getKeyChar() > '9') && e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT  && e.getKeyCode() != KeyEvent.VK_RIGHT) 
					e.consume();
			}
		});
	}

	private void formataCPF(){
		String formatado = "";
		String semFormato =  getText().trim().replaceAll("[\\p{Punct}]", "");
		for (int i = 0; i < semFormato.length(); i++) {
			formatado += semFormato.charAt(i);
			if (i == 2 || i == 5) formatado += '.';
			if (i == 8) formatado += '-';
		}
		setText(formatado);
	}
}
