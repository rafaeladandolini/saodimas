package br.com.saodimas.view.components.text;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.Document;

import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.util.ExpReg;


@SuppressWarnings("serial")
public class LoginTextField extends JTextField {
	private CustomDocument document;

	public LoginTextField() {
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
				formataLogin();
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				Pattern p = Pattern.compile(ExpReg.LOGIN);
				Matcher m = p.matcher(e.getKeyChar() + "");
				
				if (!m.matches() && e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT  && e.getKeyCode() != KeyEvent.VK_RIGHT) 
					e.consume();
			}
		});
	}

	private void formataLogin(){
		String formatado = getText().trim().toLowerCase();
		setText(formatado);
	}
}
