package com.extentReportingv3;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.testNgListener.TestNGKeys;

public class ExtentManager {
	private static ExtentReports extentReport;

	private static ExtentHtmlReporter htmlReporter;
	private static final String filePath = Constant.getResultextenthtmlfilePath();
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	//called by onStart
	public static synchronized ExtentReports GetExtentReports(HashMap<TestNGKeys, String> testData) {
		if (extentReport != null)
			return extentReport; //avoid creating new instance of html file
		extentReport = new ExtentReports();
		extentReport.setSystemInfo("Test Environment", Constant.get_HCM_EnvironmentInfoSheet());
		extentReport.setSystemInfo("Parallel Mode", testData.get(TestNGKeys.parallel));
		extentReport.setSystemInfo("Assertion Enabled", Constant.enableAssertions+"");
		extentReport.setSystemInfo("Capturing Snapshots", Constant.enableCaptureSnapshots()+"");
		extentReport.attachReporter(getHtmlReporter("Environment: '" + Constant.get_HCM_EnvironmentInfoSheet() + "'"
				+ " | suite: " + testData.get(TestNGKeys.suite) + " | test: " + testData.get(TestNGKeys.test) + ""));
		extentReport.setAnalysisStrategy(AnalysisStrategy.TEST);
		return extentReport;
	}


	//called by onStart
	public static synchronized ExtentReports GetExtentReports(String suiteName,String inParallel){
		if (extentReport != null)
			return extentReport; //avoid creating new instance of html file
		extentReport = new ExtentReports();
		extentReport.setSystemInfo("Test Environment", Constant.get_HCM_EnvironmentInfoSheet());
		extentReport.setSystemInfo("Parallel Mode", inParallel);
		extentReport.setSystemInfo("Assertion Enabled", Constant.enableAssertions+"");
		extentReport.setSystemInfo("Capturing Snapshots", Constant.enableCaptureSnapshots()+"");
		extentReport.attachReporter(getHtmlReporter("Environment: '" +Constant.get_HCM_EnvironmentInfoSheet() + "'"));
		extentReport.setAnalysisStrategy(AnalysisStrategy.TEST);
		return extentReport;
	}

	//called by onTestStart
	public static synchronized ExtentTest createTest(HashMap<TestNGKeys, String> testDataMap) {
		String startTag="<b style='font-size: small;font-family: monospace;'>";
		String endTag="</b>";

		ExtentTest extentTest = extentReport.createTest(
				testDataMap.get(TestNGKeys.description) 
				+ " | os: "+startTag + testDataMap.get(TestNGKeys.platform)+ endTag+""
				+ " | browser: "+startTag + testDataMap.get(TestNGKeys.browser) + endTag+" |"
				,
				" | class: "+startTag + testDataMap.get(TestNGKeys.className)+ endTag+""
						+ " | Method: "+startTag + testDataMap.get(TestNGKeys.methodName)+ endTag+""
						+ " | Priority: "+startTag + testDataMap.get(TestNGKeys.priority)+ endTag+" |"
				);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), extentTest);
		return extentTest;
	}

	//called by onTestStart
	public static synchronized ExtentTest createTest(String name, String description, String testNG_testName, String testNG_suiteName){
		ExtentTest extentTest = extentReport.createTest(name, description+" | TestNG-test : "+testNG_testName+" | TestNG-suite : "+testNG_suiteName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), extentTest);
		return extentTest;
	}

	//called by @test methods
	public static synchronized ExtentTest GetExtentTest(){
		return extentTestMap.get((int) (long) (Thread.currentThread().getId())); 
	}

	//called by local GetExtentReports method
	private static synchronized ExtentHtmlReporter getHtmlReporter(String reportName) {
		htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.setAppendExisting(false);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("ExtentReports 3.0");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("MM/dd/yyyy, HH:mm:ss a");
		return htmlReporter;
	}

	public static synchronized ExtentTest createNode(String description) {
		ExtentTest extentNode = null ;
		try{
			extentNode= GetExtentTest().createNode(description);
			extentTestMap.put((int) (long) (Thread.currentThread().getId()), extentNode);
		}catch (Exception e) {
		}
		return extentNode;
	}

	public static Status getStatus(String status) {
		Status val=null;
		if(status.equalsIgnoreCase(STATUS.PASS.value)){
			val=Status.PASS;
		}else if(status.equalsIgnoreCase(STATUS.FAIL.value)){
			val=Status.FAIL;
		}else if(status.equalsIgnoreCase(STATUS.SKIP.value)){
			val=Status.SKIP;
		}else if(status.equalsIgnoreCase(STATUS.ERROR.value)){
			val=Status.ERROR;
		}else if(status.equalsIgnoreCase(STATUS.FATAL.value)){
			val=Status.FATAL;
		}else if(status.equalsIgnoreCase(STATUS.INFO.value)){
			val=Status.INFO;
		}else if(status.equalsIgnoreCase(STATUS.WARNING.value)){
			val=Status.WARNING;
		}
		return val;
	}

}