package com.customReporting.pojos;

import java.util.ArrayList;
import java.util.List;

public class TestNGSuite_POJO {
	
	public TestNGSuite_POJO(String suiteName) {
		this.suiteName = suiteName;
	}
	
	List<TestNGTests_POJO> testList;

	private String suiteName;
	
	public String getSuiteName() {
		return suiteName;
	}

	public TestNGTests_POJO addTests(String testName) {
		if(testList == null) {
			initTestList();
		}
		
		TestNGTests_POJO testNGTests = new TestNGTests_POJO(testName);
		testList.add(testNGTests);
		return testNGTests;
	}
	
	public List<TestNGTests_POJO> getTestList() {
		return testList;
	}

	public void initTestList() {
		this.testList = new ArrayList<TestNGTests_POJO>();
	}
	
}
