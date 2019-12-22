package com.customReporting.pojos;

import java.util.ArrayList;
import java.util.List;

import com.customReporting.Test;

public class TestNGClass_POJO {
	private List<TestNGMethods_POJO> methodsList;

	private String className;
	
	public TestNGClass_POJO(String className) {
		this.className = className;
	}

	public void addMethod(Test test) {
		
		if(methodsList == null) {
			initMethodsList();
		}
		
		methodsList.add(new TestNGMethods_POJO(test));
	}
	
	public List<TestNGMethods_POJO> getMethodsList() {
		return methodsList;
	}

	public void initMethodsList() {
		this.methodsList = new ArrayList<TestNGMethods_POJO>();
	}

	public String getClassName() {
		return className;
	}
	
}
