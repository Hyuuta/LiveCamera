package com.hyuutalab.livecamera;

import java.util.Calendar;

public class GetFormattedDT {
	public static String GetFormattedDateAndTime(){
		Calendar cal = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.valueOf(cal.get(Calendar.YEAR)));
		
		sb.append(String.format("%02d",cal.get(Calendar.MONTH)));
		sb.append(String.format("%02d",cal.get(Calendar.DATE)));
		sb.append("_");
		
		sb.append(String.format("%02d",cal.get(Calendar.HOUR_OF_DAY)));
		sb.append(String.format("%02d",cal.get(Calendar.MINUTE)));
		
		return sb.toString();
	}
	
	
}
