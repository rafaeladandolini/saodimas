package br.com.saodimas.view.components.spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

@SuppressWarnings("serial")
public class DataSpinner extends JSpinner{
	public DataSpinner(String dataDefault, String menorData, String maiorData){
		super();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date initDate = f.parse(dataDefault);
			Date earliestDate = f.parse(menorData); 
			Date latestDate = f.parse(maiorData);
			
			SpinnerDateModel dateModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);
			setModel(dateModel);
			setEditor(new JSpinner.DateEditor(this, "dd/MM/yyyy"));
		}
		catch (ParseException e){
			e.printStackTrace();
		} 
	}
	
}
