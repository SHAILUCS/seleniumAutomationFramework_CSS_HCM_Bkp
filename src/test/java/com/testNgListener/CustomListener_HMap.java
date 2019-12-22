/**
 * CustomListener_HMap.java
 * All public methods of this class will be called by TestNG framework
 * Their calling order is explained in comments 
 * */
package com.testNgListener;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.customReporting.ReportingHistoryManager;
import com.customReporting.Test;
import com.customReporting.TestRunHistoryManager;
import com.customReporting.snapshot.SnapshotManager;
import com.customReporting.snapshot.SnapshotsMovieMaker;
import com.driverManager.DriverFactory;
import com.extentReportingv3.ExtentManager;
import com.mailUtil.MailUtil;

import or.common.LoginPage;
import or.planManagement.LoginPage_PlanManagement;

public class CustomListener_HMap implements ITestListener,IExecutionListener{

	private ExtentReports extentReport;
	private String parallelFlag;
	private String suiteName;
	private String testName;
	
	/**
	 * <pre>
	 * Calling order of this method is : 1.
	 * When the Execution is just started, i.e. In terms of testng.xml file, 
	 * when jvm hits <b>"suite"</b> tag this method will be triggered.
	 * <b>Initializes</b> the SnapshotManager, CustomReporter classes, and remove
	 * the older execution files
	 * </pre>
	 */
	public void onExecutionStart() {
		//System.out.println("onExecutionStart for Thread: "+Thread.currentThread().getId());
		Util.deleteFolderContentRecursively(new File(Constant.getDownloadsPath()),Constant.downloadFolderName);
		SnapshotManager.initialize();
		CustomReporter.initialize();
		LoginPage.unblockAllUsers();
	}

	/**
	 * <pre>
	 * Calling order of this method is : 2.
	 * When jvm hits <b>"test"</b> tag in testng.xml file, this method 
	 * will be triggered,
	 * <b>Instantiates</b> the class variables/objects parallelFlag, suiteName, 
	 * testName and extentReport.
	 * <b>Sets</b> the environment value to the Constant class variable, 
	 * for further use in framework(mainly to access login credentials 
	 * from test data)  
	 * <b>Setups</b> the DriverFactory and SnapshotManager classes   
	 * </pre> 
	 */
	public void onStart(ITestContext context) {
		// System.out.println("bro onStart "+Thread.currentThread().getId());
		HashMap<TestNGKeys, String> testDataMap = INIT_TEST_DATA_MAP(context);
		
		parallelFlag = testDataMap.get(TestNGKeys.parallel);
		suiteName = testDataMap.get(TestNGKeys.suite);
		testName = testDataMap.get(TestNGKeys.test);
		Constant.set_HCM_EnvironmentInfoSheet(testDataMap.get(TestNGKeys.environment));

		extentReport = ExtentManager.GetExtentReports(testDataMap);
		CustomReporter.onStart(testDataMap);

		if (parallelFlag.equals("none") || parallelFlag.equals("tests")) {
			DriverFactory.setUp(testDataMap);
			SnapshotManager.setUp(context.getName());
		}
		Test.setTotalScenario(context.getSuite().getAllMethods().size());
	}

	/**
	 * <pre>
	 * Calling order of this method is : 3.
	 * When jvm hits <b>"include"</b> tag in testng.xml file, this method 
	 * will be triggered,
	 * <b>Initilizes</b> the testData hash map
	 * <b>Setups</b> the DriverFactory and SnapshotManager classes
	 * Performs login to opened app based on environment and user   
	 * </pre> 
	 */
	public void onTestStart(ITestResult result) {
		//System.out.println("onTestStart "+Thread.currentThread().getId());
		//System.out.println("Started: "+Arrays.toString(result.getMethod().getMethodsDependedUpon()));
		
		HashMap<TestNGKeys,String> testDataMap=INIT_TEST_DATA_MAP(result);
		
		ExtentManager.createTest(testDataMap);
		CustomReporter.onTestStart(testDataMap);
		
		if(!parallelFlag.equals("none") && !parallelFlag.equals("tests")){
			//DriverFactory.setUp(remoteURL,browser,platform);
			DriverFactory.setUp(testDataMap);
			SnapshotManager.setUp(result.getName());
		}else{
			SnapshotManager.setRunningMethodName(result.getName());
		}
		
		if(testDataMap.get(TestNGKeys.environment).toUpperCase().contains("PM-AWS")){
			new LoginPage_PlanManagement().performIntelligentLogin(testDataMap.get(TestNGKeys.user));
		}else{
			new LoginPage().performIntelligentLogin(testDataMap.get(TestNGKeys.user));
		}

	}
	

	/**
	 * <pre>
	 * Calling order of this method is also : 3.
	 * When jvm hits <b>"include"</b> tag in testng.xml file, this method 
	 * will be triggered, The main difference here is, when a method is 
	 * failed all the methods which are marked as dependent will be skipped 
	 * <b>Initilizes</b> the testData hash map
	 * <b>Adds</b> the appropriate message to the report
	 * <b>Finishes off</b> the CustomReporter Test > [Steps] cycle, by passing null as we don't have any snapshots to create a movie
	 * </pre> 
	 */
	public void onTestSkipped(ITestResult result) {
		//System.out.println("onTestSkip "+Thread.currentThread().getId());
		INIT_TEST_DATA_MAP(result);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed: '"+(result.getMethod().getMethodsDependedUpon().length>0?Arrays.toString(result.getMethod().getMethodsDependedUpon()):null)+"'");
		CustomReporter.onTestEnd_NonXl(null);
		CustomReporter.onExecutionFinish();
	}

	/**
	 * <pre>
	 * Calling order of this method is : 3.5
	 * When jvm completely finishes <b>"include"</b> tag in testng.xml file, this method 
	 * will be triggered,
	 * Performs the logout based on environment
	 * <b>Creates</b> snapshot movie
	 * <b>Finishes off</b> the CustomReporter Test > [Steps] cycle, by passing the snapshot movie path
	 * <b>Tears down</b> the DriverFactory, SnapshotManager classes
	 * </pre> 
	 */
	public void onTestSuccess(ITestResult result){
		//System.out.println("onTestSuccess "+Thread.currentThread().getId());
		
		HashMap<TestNGKeys,String> testDataMap=INIT_TEST_DATA_MAP(result);
		
		LoginPage.unblockUser();
		
		String moviePath=SnapshotsMovieMaker.createMovie(SnapshotManager.getSnapshotDestinationDirectory());
		CustomReporter.onTestEnd_NonXl(moviePath);
		
		if(!parallelFlag.equals("none") && !parallelFlag.equals("tests")){
			DriverFactory.tearDown();
			SnapshotManager.tearDown();
		}
		CustomReporter.onExecutionFinish();
	}

	/**
	 * <pre>
	 * Calling order of this method is also : 3.5.
	 * When jvm encounter any unhandeled exceptions during the execution of 
	 * <b>"include"</b> tag in testng.xml file, The test will be considered 
	 * as failure and this method will be triggered,
	 * 
	 * ERROR line will be added with stack trace in the html report, also
	 * the same stack trace can be seen in 
	 * [TestNG Results of running suite]>>[Failure Exception] section
	 * 
	 * Performs the logout based on environment
	 * <b>Creates</b> snapshot movie
	 * <b>Finishes off</b> the CustomReporter Test > [Steps] cycle, by passing the snapshot movie path
	 * <b>Tears down</b> the DriverFactory, SnapshotManager classes
	 * </pre> 
	 */
	public void onTestFailure(ITestResult result) {
		//System.out.println("onTestFailure "+Thread.currentThread().getId());
		HashMap<TestNGKeys,String> testDataMap=INIT_TEST_DATA_MAP(result);
		
		result.getThrowable().printStackTrace();
		String readableStackTrace=result.getThrowable().getMessage();
		for (StackTraceElement elem : result.getThrowable().getStackTrace()) {
			readableStackTrace=readableStackTrace+"<br/>"+elem.toString();
		}
		CustomReporter.report(STATUS.ERROR, "- PREMATURE EXECUTION STOPPED : Failure Exception :- <br/><b style='color:red;font-size: small;font-family: arial,sans-serif;'>"+readableStackTrace+"</b>");
		
		String moviePath=SnapshotsMovieMaker.createMovie(SnapshotManager.getSnapshotDestinationDirectory());
		CustomReporter.onTestEnd_NonXl(moviePath);
		if(!parallelFlag.equals("none") && !parallelFlag.equals("tests")){
			DriverFactory.tearDown();
			SnapshotManager.tearDown();
		}
		CustomReporter.onExecutionFinish();
	}
	

	/**
	 * <pre>
	 * Calling order of this method is : 4.
	 * When jvm finishes the <b>"test"</b> tag in testng.xml file, this method 
	 * will be triggered,
	 * <b>Tear down</b> the DriverFactory and SnapshotManager classes   
	 * </pre> 
	 * */
	public void onFinish(ITestContext context) {
		//System.out.println("onFinish "+Thread.currentThread().getId());
		if(parallelFlag.equals("none") || parallelFlag.equals("tests")){
			DriverFactory.tearDown();
			SnapshotManager.tearDown();
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
	
	/**
	 * <pre>
	 * Calling order of this method is : 5.
	 * When jvm finishes the <b>"suite"</b> tag in testng.xml file, this method 
	 * will be triggered,
	 * <b>Generates</b> the Custom and Extent HTML Report, Automation Coverage Matrix report 
	 * <b>Manages</b> Test Run History, Reporting History
	 * <b>Sends</b> the Notification mail attaching the html reports   
	 * </pre> 
	 * */
	public void onExecutionFinish() {
		System.out.println("===============================================================================\nCustom Report Generation STARTED "+new Date());
		CustomReporter.onExecutionFinish();
		System.out.println("Custom Report Generation ENDED "+new Date()+"\n===============================================================================");
		
		System.out.println("===============================================================================\nExtent Report Generation STARTED "+new Date());
		try{extentReport.flush();
		}catch(Exception e){e.printStackTrace();}
		System.out.println("Extent Report Generation ENDED "+new Date()+"\n===============================================================================");
		
		TestRunHistoryManager.manageTestRunHistory();
		ReportingHistoryManager.manageReportingHistory();
		MailUtil.sendNotificationMail("Suite["+suiteName+"] Test["+testName+"] Env["+Constant.get_HCM_EnvironmentInfoSheet()+"] "+new Date());
	}

	/**
	 * Initializes the testDataMap Hashmap from context object
	 * */
	private HashMap<TestNGKeys, String> INIT_TEST_DATA_MAP(ITestContext context){
		HashMap<TestNGKeys,String> testDataMap= new HashMap<>();
		testDataMap.put(TestNGKeys.threadCount, context.getCurrentXmlTest().getThreadCount()+""); 
		testDataMap.put(TestNGKeys.parallel, context.getSuite().getParallel());
		testDataMap.put(TestNGKeys.browser, context.getCurrentXmlTest().getParameter(TestNGKeys.browser.value));
		testDataMap.put(TestNGKeys.platform, context.getCurrentXmlTest().getParameter(TestNGKeys.platform.value));
		testDataMap.put(TestNGKeys.remoteURL, context.getCurrentXmlTest().getParameter(TestNGKeys.remoteURL.value));
		testDataMap.put(TestNGKeys.environment, context.getCurrentXmlTest().getParameter(TestNGKeys.environment.value));
		testDataMap.put(TestNGKeys.suite, context.getSuite().getName());
		testDataMap.put(TestNGKeys.test, context.getCurrentXmlTest().getName());
		return testDataMap;
	}
	
	/**
	 * Initializes the testDataMap Hashmap from result object
	 * */
	private HashMap<TestNGKeys,String> INIT_TEST_DATA_MAP(ITestResult result){
		HashMap<TestNGKeys,String> testDataMap= new HashMap<>();
		testDataMap.put(TestNGKeys.browser, result.getMethod().findMethodParameters(result.getTestContext().getCurrentXmlTest()).get(TestNGKeys.browser.value));
		testDataMap.put(TestNGKeys.platform, result.getMethod().findMethodParameters(result.getTestContext().getCurrentXmlTest()).get(TestNGKeys.platform.value));
		testDataMap.put(TestNGKeys.user, result.getMethod().findMethodParameters(result.getTestContext().getCurrentXmlTest()).get(TestNGKeys.user.value));
		testDataMap.put(TestNGKeys.environment, result.getMethod().findMethodParameters(result.getTestContext().getCurrentXmlTest()).get(TestNGKeys.environment.value));
		
		// In case you forgot to provide description to a test method, then
		// method name will be shown in HTML report instead of {null}
		if (result.getMethod().getDescription()==null) {
			testDataMap.put(TestNGKeys.description, result.getMethod().getMethodName());	
		}else{
			testDataMap.put(TestNGKeys.description, result.getMethod().getDescription());
		}
		
		testDataMap.put(TestNGKeys.className, result.getMethod().getRealClass().getName());
		testDataMap.put(TestNGKeys.methodName, result.getMethod().getMethodName());
		testDataMap.put(TestNGKeys.priority, result.getMethod().getPriority()+"");
		testDataMap.put(TestNGKeys.remoteURL, result.getTestContext().getCurrentXmlTest().getAllParameters().get(TestNGKeys.remoteURL.value));
		testDataMap.put(TestNGKeys.group,Arrays.toString(result.getMethod().getGroups()));
		testDataMap.put(TestNGKeys.dependsOn,Arrays.toString(result.getMethod().getMethodsDependedUpon()));
		
		return testDataMap;
	}
	
}