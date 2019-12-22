package com.jsonUtil;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;

public class TestngJsonRunner {

	String jsonFilePath1="C:/Users/shailendra.rajawat/git/iotronApex5/src/test/java/tests/resources/file1.json";
	String jsonFilePath2="C:/Users/shailendra.rajawat/git/iotronApex5/src/test/java/tests/resources/file2.json";
	
	@Test
	private void testasd() {
		JSONManager json=new JSONManager(Constant.getResourcesFolderPath() + "Qlik.json","T01_GoMaltaAppDataVerification","YoY Value Performance");
		
		
		System.out.println(json.getChildJSONObject("OB").getStr("removeFilterArr"));
		
		System.out.println(json.getChildJSONObject("applyFilter").getStr("filterName"));
		
		System.out.println(json.getParentJSONObject().getParentJSONObject().getParentJSONObject().getParentJSONObject().getStr("user"));
		
	}
	
	@Test
	private void thread1() {
		CustomReporter.report(STATUS.INFO, "thread1");
		JSONManager json=new JSONManager(jsonFilePath1);
		json.put("k1.1", "v1.1");
		json.put("k1.2", "v1.2");
		System.out.println(json.getStr("k1.1"));
	}
	
	@Test
	private void thread2() {
		CustomReporter.report(STATUS.INFO, "thread2");
		JSONManager json=new JSONManager(jsonFilePath1);
		json.put("k2.1", "v2.1");
		json.put("k2.2", "v2.2");
		System.out.println(json.getStr("k2.1"));
	}
	
	@Test
	private void thread3() {
		CustomReporter.report(STATUS.INFO, "thread3");
		JSONManager json=new JSONManager(jsonFilePath1);
		json.put("k3.1", "v3.1");
		json.put("k3.2", "v3.2");
		System.out.println(json.getStr("k3.1"));
		
	}
	
	@Test
	private void thread4() {
		CustomReporter.report(STATUS.INFO, "thread4");
		JSONManager json=new JSONManager(jsonFilePath2);
		json.put("k4.1", "v4.1");
		json.put("k4.2", "v4.2");
		System.out.println(json.getStr("k4.1"));
	}
	
	@Test
	private void thread5() {
		CustomReporter.report(STATUS.INFO, "thread5");
		JSONManager json=new JSONManager(jsonFilePath2);
		json.put("k5.1", "v5.1");
		json.put("k5.2", "v5.2");
		System.out.println(json.getStr("k5.1"));
	}
}
