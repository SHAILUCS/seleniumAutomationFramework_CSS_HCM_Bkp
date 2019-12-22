package com.customReporting;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.configData_Util.STATUS;
import com.extentReportingv3.ExtentManager;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.testNgListener.TestNGKeys;

public class ReportTester {
	public static void main(String[] args) {
		extentAndCustomReportTester();
		//onlyExtentTester();
		
	} 

	/*protected static void onlyExtentTester() {
		ExtentReports extentReport;
		extentReport = ExtentManager.GetExtentReports("Suite1","meth");
		
		ExtentManager.createTest("Test1","Test1", "ABC", "XYZ");
		ExtentManager.createNode("Node1.1");
		ExtentManager.GetExtentTest().log(STATUS.SKIP, "Log1.1.1");
		ExtentManager.GetExtentTest().log(Status.FAIL, "Log1.1.2");
		ExtentManager.createNode("Node1.2");
		ExtentManager.GetExtentTest().log(STATUS.SKIP, "Log1.2.1");
		ExtentManager.GetExtentTest().log(STATUS.SKIP, "Log1.2.2");
		
		test1=ExtentManager.createNode("Node1.3");
		test1.log(STATUS.SKIP, "Log1.3.1");
		test1=ExtentManager.createNode("Node1.4");
		test1.log(STATUS.SKIP, "Log1.4.1");
		test1=ExtentManager.createNode("Node1.5");
		test1.log(STATUS.SKIP, "Log1.5.1");
		test1=ExtentManager.createNode("Node1.6");
		test1.log(STATUS.SKIP, "Log1.6.1");
		test1=ExtentManager.createNode("Node1.7");
		test1.log(STATUS.SKIP, "Log1.7.1");
		test1=ExtentManager.createNode("Node1.8");
		test1.log(STATUS.SKIP, "Log1.8.1");
		test1=ExtentManager.createNode("Node1.9");
		test1.log(STATUS.SKIP, "Log1.9.1");
		test1=ExtentManager.createNode("Node1.10");
		test1.log(STATUS.SKIP, "Log1.10.1");
		test1=ExtentManager.createNode("Node1.11");
		test1.log(STATUS.SKIP, "Log1.11.1");

		ExtentManager.createTest("Test2","Test2", null, null);
		ExtentManager.createNode("Node2.1");
		ExtentManager.GetExtentTest().log(STATUS.SKIP, "Log2.1.1");
		ExtentManager.GetExtentTest().log(STATUS.SKIP, "Log2.1.2");
		ExtentManager.createNode("Node2.2");
		ExtentManager.GetExtentTest().log(STATUS.SKIP, "Log2.2.1");
		ExtentManager.GetExtentTest().log(STATUS.SKIP, "Log2.2.2");


		ExtentTest test=ExtentManager.createTest("Test1","Test1", null, null);
		ExtentTest test1=test.createNode("Node1.1");
		test1.log(STATUS.SKIP, "Log1.1.1");
		test1.log(Status.FAIL, "Log1.1.2");
		test1=test.createNode("Node1.2");
		test1.log(STATUS.SKIP, "Log1.2.1");
		test1.log(STATUS.SKIP, "Log1.2.2");

		test=ExtentManager.createTest("Test2","Test2", null, null);
		test1=test.createNode("Node2.1");
		test1.log(STATUS.SKIP, "Log2.1.1");
		test1.log(STATUS.SKIP, "Log2.1.2");
		test1=test.createNode("Node2.2");
		test1.log(STATUS.SKIP, "Log2.2.1");
		test1.log(STATUS.SKIP, "Log2.2.2");

		extentReport.flush();
	}*/

	protected static void extentAndCustomReportTester(){
		SeleniumMethods com=new SeleniumMethods();
		
		//START
		//CustomListener
		HashMap<TestNGKeys,String> testDataMap= new HashMap<>();
		String parallelFlag="Methods";
		testDataMap.put(TestNGKeys.parallel, parallelFlag);
		testDataMap.put(TestNGKeys.browser, "FireFox");
		testDataMap.put(TestNGKeys.platform, "Platform");
		testDataMap.put(TestNGKeys.remoteURL, "");
		testDataMap.put(TestNGKeys.environment, "Preprodd");
		testDataMap.put(TestNGKeys.suite, "SuiteName");
		testDataMap.put(TestNGKeys.test, "UI_TEST");
		testDataMap.put(TestNGKeys.className, "test.Class");

		CustomReporter.initialize();

		CustomReporter.onStart(testDataMap);
		ExtentReports extentReport = ExtentManager.GetExtentReports(testDataMap);

		testDataMap.put(TestNGKeys.description, "Test1");
		testDataMap.put(TestNGKeys.methodName, "UI_Rep_TotalAndPremiumNumberVolumeAndTAPCharges_634");
		testDataMap.put(TestNGKeys.priority, "2");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		
		//object repository

		CustomReporter.createNode("Node0.0");
		CustomReporter.createNode("Node1.0");
		CustomReporter.report(STATUS.INFO,"Test1.1");
		CustomReporter.createNode("Node1.1");
		
		CustomReporter.report(STATUS.PASS,"Test1.1");
		CustomReporter.report(STATUS.PASS,"Test1.2");
		CustomReporter.report(STATUS.PASS,"Test1.3");
		
		CustomReporter.createNode("Node2.0");
		CustomReporter.report(STATUS.PASS,"Test2.1");
		CustomReporter.report(STATUS.PASS,"Test2.2");
		CustomReporter.report(STATUS.WARNING,"Test2.3");
		
		CustomReporter.report_ExitCurrentNode(STATUS.INFO,"Test3.1");
		//com.wait(1);
		
		//CustomListener
		CustomReporter.onTestEnd_NonXl("");
		

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3123123123");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");
		
		
		
		
		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3123123");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		
		
		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3w54343");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		
		
		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test323321");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		
		
		
		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3fsdf");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.FAIL,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		
		
		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3sdf");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.PASS,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		
		
		

		testDataMap.put(TestNGKeys.description, "7233 - Forecast Operator Level Growth - Enable bulk update for all services and event types (FE)");
		testDataMap.put(TestNGKeys.methodName, "ForecastOperatorLevelGrowthEnableBulkUpdateForAllServicesAndEventTypesFE_7233");
		testDataMap.put(TestNGKeys.priority, "8");
		testDataMap.put(TestNGKeys.className, "tests.MainRegression.KPN_DealTracker7216");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.INFO,"'Login Page' page is displayed http://10.184.40.120/pls/apex/f?p=10132:22::::::");
		CustomReporter.report(STATUS.FAIL,"'SHAILENDRA.RAJAWAT' got displayed on NGC Home Page");
		CustomReporter.report(STATUS.SKIP,"Navigation complete to_IOTRONHomePage http://10.184.40.120/pls/apex/f?p=10145:252:1838571049922::NO:::");
		CustomReporter.report(STATUS.SKIP,"<b style='font-size: small;font-family: monospace;'>Deal Tracker Module -&gt; Deal Tracker Summary</b><br>'<span style='color: black;font-style: italic;font-weight: bold;'>Deal Tracker</span>' page is displayed <br>http://10.184.40.120/pls/apex/f?p=22244:4:1838571049922::NO:4:P4_DISPLAY_REPORTS,P4_IOT_AGREEMENT_ID:N,1&amp;cs=16CD4420D3C13A7D72F75069CC63E760F");
		CustomReporter.report(STATUS.SKIP,"'Edit Market Volume' page is displayed http://10.184.40.120/pls/apex/f?p=22244:5:1838571049922::NO:5:P5_REPORTING_YEAR,P5_REPORTING_YEAR_SHORT,P5_NEXT_YEAR_SHORT:2017,17,18&cs=1EA532116412FCB1C6E05D782B261D12B");
		CustomReporter.report(STATUS.SKIP,"'<b>Edit Proportional Share</b>' page is displayed <br>http://10.184.40.120/pls/apex/f?p=22244:10:1838571049922::NO:10:P10_REPORTING_YEAR,P10_REPORTING_YEAR_SHORT,P10_NEXT_YEAR_SHORT:2017,17,18&amp;cs=12BACDC029DEAA64A184A81DB5D5FAB4E");
		CustomReporter.report(STATUS.SKIP,"'Edit Market Volume' page is displayed http://10.184.40.120/pls/apex/f?p=22244:5:1838571049922::NO:5:P5_REPORTING_YEAR,P5_REPORTING_YEAR_SHORT,P5_NEXT_YEAR_SHORT:2017,17,18&cs=1EA532116412FCB1C6E05D782B261D12B");
		CustomReporter.report(STATUS.SKIP,"'Edit Market Volume' page is displayed http://10.184.40.120/pls/apex/f?p=22244:5:1838571049922::NO:5:P5_REPORTING_YEAR,P5_REPORTING_YEAR_SHORT,P5_NEXT_YEAR_SHORT:2017,17,18&cs=1EA532116412FCB1C6E05D782B261D12B");
		CustomReporter.report(STATUS.SKIP,"'Edit Market Volume' page is displayed http://10.184.40.120/pls/apex/f?p=22244:5:1838571049922::NO:5:P5_REPORTING_YEAR,P5_REPORTING_YEAR_SHORT,P5_NEXT_YEAR_SHORT:2017,17,18&cs=1EA532116412FCB1C6E05D782B261D12B");
		CustomReporter.report(STATUS.SKIP,"'Edit Market Volume' page is displayed http://10.184.40.120/pls/apex/f?p=22244:5:1838571049922::NO:5:P5_REPORTING_YEAR,P5_REPORTING_YEAR_SHORT,P5_NEXT_YEAR_SHORT:2017,17,18&cs=1EA532116412FCB1C6E05D782B261D12B");
		CustomReporter.report(STATUS.SKIP,"'Edit Market Volume' page is displayed http://10.184.40.120/pls/apex/f?p=22244:5:1838571049922::NO:5:P5_REPORTING_YEAR,P5_REPORTING_YEAR_SHORT,P5_NEXT_YEAR_SHORT:2017,17,18&cs=1EA532116412FCB1C6E05D782B261D12B");
		CustomReporter.report(STATUS.SKIP,"'Edit Market Volume' page is displayed http://10.184.40.120/pls/apex/f?p=22244:5:1838571049922::NO:5:P5_REPORTING_YEAR,P5_REPORTING_YEAR_SHORT,P5_NEXT_YEAR_SHORT:2017,17,18&cs=1EA532116412FCB1C6E05D782B261D12B");
		CustomReporter.report(STATUS.SKIP,"'Edit Market Volume' page is displayed http://10.184.40.120/pls/apex/f?p=22244:5:1838571049922::NO:5:P5_REPORTING_YEAR,P5_REPORTING_YEAR_SHORT,P5_NEXT_YEAR_SHORT:2017,17,18&cs=1EA532116412FCB1C6E05D782B261D12B");
		//com.wait(2);
		CustomReporter.onTestEnd_NonXl("");

		
		


		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		testDataMap.put(TestNGKeys.description, "discAgr_T09_Verification of 'Forecasting Method and Home Currency' and 'Client and Partner Currency' data from NGC - 'Maintain IOT client, Edit and Update/IOT Approve Addendum' pages with AUSTA - 'Create Agreement' page");
		testDataMap.put(TestNGKeys.methodName, "Test3");
		testDataMap.put(TestNGKeys.priority, "1");
		CustomReporter.onTestStart(testDataMap);
		ExtentManager.createTest(testDataMap);
		CustomReporter.report(STATUS.SKIP,"It depends on methods which got failed");
		//com.wait(3);
		CustomReporter.onTestEnd_NonXl("");

		
		
		
		
		CustomReporter.onExecutionFinish();
		extentReport.flush();
		//END
	}

}
