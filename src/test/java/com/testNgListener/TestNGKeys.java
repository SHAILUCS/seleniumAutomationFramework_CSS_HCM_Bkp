package com.testNgListener;

import java.util.ArrayList;

public enum TestNGKeys {
	priority("priority"),
	parallel("parallel"),
	browser("browser"), 
	platform("platform"),
	remoteURL("remoteURL"),
	environment("environment"),
	description("description"),
	user("user"),
	suite("suite"),
	test("test"),
	className("className"),
	methodName("methodName"),
	group("group"),
	dependsOn("dependsOn"), 
	threadCount("threadCount");
	
	public String value;  
	private TestNGKeys(String value){  
		this.value=value;  
	} 
	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (TestNGKeys item : TestNGKeys.values()) {
			list.add(item.value);
		}
		return list;
	}
}
