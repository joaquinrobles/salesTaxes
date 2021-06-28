package com.lastminute.formater;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DecimalFormater {
	
	public static String getBigDecimalWithFormat(BigDecimal amount) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setGroupingUsed(false);
		
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setDecimalSeparator('.');
		
		df.setDecimalFormatSymbols(decimalFormatSymbols);
		
		return df.format(amount);
	}

}
