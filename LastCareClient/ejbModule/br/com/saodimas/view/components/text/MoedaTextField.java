package br.com.saodimas.view.components.text;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.Document;

import br.com.saodimas.view.components.document.CustomDocument;
import br.com.saodimas.view.util.ExpReg;


@SuppressWarnings("serial")
public class MoedaTextField extends JTextField {
	private CustomDocument document;

	public MoedaTextField() {
		initialize();
	}

	public Double getValor(){
		String valorTexto = getText().trim();
		if(valorTexto.length() > 0){
			try{
				Double valor = Double.valueOf(valorTexto.replaceAll("\\p{Punct}", ""));
				return valor / 100;
			}
			catch(NumberFormatException ex){
				setText("0,00");
				return 0d;
			}
		}
		return 0d;
	}


	public void setValor(Double valor){
		DecimalFormat formatter = new DecimalFormat("#0.00");
		setText(formatter.format(valor));
		formataMoeda();
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
		super.setHorizontalAlignment(RIGHT);

		addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				formataMoeda();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				Pattern p = Pattern.compile(ExpReg.NUMERIC);
				Matcher m = p.matcher(e.getKeyChar() + "");

				if (!m.matches() && e.getKeyCode() != KeyEvent.VK_TAB && e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_DELETE && e.getKeyCode() != KeyEvent.VK_LEFT  && e.getKeyCode() != KeyEvent.VK_RIGHT) 
					e.consume();
			}
		});
	}

	private void formataMoeda(){
		try{
			Long valorNumerico = Long.parseLong(getText().replaceAll("\\p{Punct}", ""));
			String semFormato = valorNumerico + "";
			
			if (semFormato.length() == 0) setText("0,00");
			else if (semFormato.length() == 1) setText("0,0" + semFormato);
			else if (semFormato.length() == 2) setText("0," + semFormato);
			else{
				String integerValue = semFormato.substring(0, semFormato.length() - 2);
				String fractionValue = semFormato.substring(semFormato.length() - 2);
				int counter = 0;
				String formatado = "";

				for (int i = integerValue.length()-1; i >= 0; i--){
					if (counter % 3 == 0 && i < integerValue.length()-1) formatado = (integerValue.charAt(i) + "")  + "." + formatado;
					else formatado = integerValue.charAt(i) + formatado;
					counter++;
				}
				formatado = formatado + "," + fractionValue;
				setText(formatado);
			}
		}
		catch(Exception e){
			setText("0,00");
		}
	}
}