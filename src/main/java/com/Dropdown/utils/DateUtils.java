package com.Dropdown.utils;

import java.util.Date;

public class DateUtils {
    //********To name report and screenshot by time DateUtils class is implemented********
	public static String getTimeStamp() {
		// TODO Auto-generated method stub
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");

	}

}
