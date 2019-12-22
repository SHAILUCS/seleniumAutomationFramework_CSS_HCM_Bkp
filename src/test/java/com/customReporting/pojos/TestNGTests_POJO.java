package com.customReporting.pojos;

import java.util.ArrayList;
import java.util.List;

public class TestNGTests_POJO {
	List<TestNGClass_POJO> classList;

	private String testName;
	
	public TestNGTests_POJO(String testName) {
		this.testName = testName;
	}
	
	public String getTestName() {
		return testName;
	}

	public TestNGClass_POJO addClass(String className) {
		if (classList == null) {
			initClassList();
		}
		
		TestNGClass_POJO testNGClass= new TestNGClass_POJO(className);
		classList.add(testNGClass);
		return testNGClass;
	}
	
	public List<TestNGClass_POJO> getClassList() {
		return classList;
	}

	public void initClassList() {
		this.classList = new ArrayList<TestNGClass_POJO>();
	}

	public TestNGClass_POJO containsClass(String testClass) {
		if(classList !=null) {
			for (TestNGClass_POJO testNGClass : classList) {
				if (testNGClass.getClassName().equalsIgnoreCase(testClass)) {
					return testNGClass;
				}
			}
		}
		return null;
	}
	
	
}
