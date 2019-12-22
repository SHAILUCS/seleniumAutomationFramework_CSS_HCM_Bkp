package com.configData_Util;

import java.util.ArrayList;

public enum STATUS {
	PASS("Pass"),
	FAIL("Fail"),
	FATAL("Fatal"), 
	ERROR("Error"),
	WARNING("Warning"),
	SKIP("Skip"),
	INFO("Info"),
	NODE("Node"),
	;
	
	public String value;  
	private STATUS(String value){  
		this.value=value;  
	} 

	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (STATUS item : STATUS.values()) {
			list.add(item.value);
		}
		return list;
	}
}
