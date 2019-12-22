package com.seleniumExceptionHandling;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;

public class CustomExceptionHandler extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * In case test data also needs to be displayed in report,
	 * pass the stringified test data in this method
	 * */
	public CustomExceptionHandler(Exception e,String data) {
		CustomReporter.report(STATUS.FATAL,  e.getMessage() + "<br/> Test Data:-<br/> ["+data+"] <br/> Stack Trace :- <b>"+getReadableStackTrace(e)+"</b>");
		e.printStackTrace();
	}
	
	public CustomExceptionHandler(Exception e) {
		CustomReporter.report(STATUS.FATAL,  e.getMessage() + "<br/> Stack Trace :- <b>"+getReadableStackTrace(e)+"</b>");
		e.printStackTrace();
	}

	private String getReadableStackTrace(Exception e) {
		String readableStackTrace=null;
		for (StackTraceElement elem : e.getStackTrace()) {
			readableStackTrace=readableStackTrace+"<br/>"+elem.toString();
		}
		return readableStackTrace;
	}
	
	

}
