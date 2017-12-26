package br.com.saodimas.view.util.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidaDatas {

	public static boolean isDataValida(String inDate) {
		if (inDate == null) {
			return false;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if (inDate.trim().length() != dateFormat.toPattern().length()) {
			return false;
		}
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
	
	public static Date formatDate(String inDate) {
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return null;
		}
		
	}
}
