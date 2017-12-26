package br.com.saodimas.view.components.document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class CustomDocument extends PlainDocument {
	private int maxLength;
	private String regEx;

	public CustomDocument() {
		this.maxLength = Integer.MAX_VALUE;
		this.regEx = "";
	}

	public CustomDocument(int maxLength){
		this.maxLength = maxLength;
		this.regEx = "";
	}

	public CustomDocument(String pattern){
		this.maxLength = Integer.MAX_VALUE;
		this.regEx = pattern;
	}

	public CustomDocument(String pattern, int maxLength){
		this.maxLength = maxLength;
		this.regEx = pattern;
	}

	public void insertString(int offset, String texto, AttributeSet attr) throws BadLocationException {
		if (texto.length() + getLength() <= maxLength){
			if (regEx.length() > 0){
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(texto);

				if (m.matches()){
					super.insertString(offset, texto, attr);
				}
			}
			else super.insertString(offset, texto, attr);
		}
	}	

	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength the maxLength to set
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * @return the regEx
	 */
	public String getRegEx() {
		return regEx;
	}

	/**
	 * @param regEx the regEx to set
	 */
	public void setRegEx(String regEx) {
		this.regEx = regEx;
	}
}
