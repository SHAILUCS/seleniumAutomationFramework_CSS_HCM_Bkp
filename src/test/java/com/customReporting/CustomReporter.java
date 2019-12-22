package com.customReporting;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.snapshot.SnapshotManager;
import com.extentReportingv3.ExtentManager;
import com.testNgListener.TestNGKeys;

public class CustomReporter {
	private static int testCounter=1;
	static Map<Integer, List<Test>> testMap = new HashMap<Integer, List<Test>>();
	private static List<ArrayList<Test>> listOfList;

	public static void initialize() {
		listOfList = new ArrayList<ArrayList<Test>>();
	}

	public static synchronized List<ArrayList<Test>> getListOfList() {
		return listOfList;
	}

	public static void onStart(HashMap<TestNGKeys, String> testDataMap) {
		Test.setInParallel(testDataMap.get(TestNGKeys.parallel));
		Test.setThreadCount(testDataMap.get(TestNGKeys.threadCount));
		Test.setTestNG_SuiteName(testDataMap.get(TestNGKeys.suite));
		Test.setTestNG_TestName(testDataMap.get(TestNGKeys.test));
		Test.setTestExecutionStartDate((new Date()).getTime());
	}

	public static synchronized void onExecutionFinish() {
		File file=new File(Constant.getResultFolderPath());
		deleteDirectory(file);
		long time_start = Long.MAX_VALUE;
		long time_end = Long.MIN_VALUE;
		//System.out.println("Finished: ");
		Test.setTestExecutionEndDate((new Date()).getTime());
		time_start = Math.min(Test.getTestExecutionStartDate(), time_start);
		time_end = Math.max(Test.getTestExecutionEndDate(), time_end);
		Test.setTestExecutionTime(Util.timeConversion((time_end - time_start)));
		
		//Discontinued on 15 Jan 2019
		//CustomReportHTML_NonXL.createHTML_NonXl(); 

		CustomReportHTML_Redesign.createHTML();

	}

	
	public static synchronized void onTestStart(HashMap<TestNGKeys, String> testDataMap) {
		Test r = new Test();
		r.setStatus(STATUS.PASS.value);
		r.setScenario(testDataMap.get(TestNGKeys.description));
		r.setDescription("");
		r.setStartTime_Scenario((new Date()).getTime());
		r.setBrowser(testDataMap.get(TestNGKeys.browser));
		r.setPlatform(testDataMap.get(TestNGKeys.platform));
		r.setTimeStamp(Util.convertToHHMMSS((new Date()).getTime()));
		r.setClassName(testDataMap.get(TestNGKeys.className));
		r.setMethodName(testDataMap.get(TestNGKeys.methodName));
		r.setPriority(testDataMap.get(TestNGKeys.priority));
		r.setDependsOn(testDataMap.get(TestNGKeys.dependsOn));
		r.setGroup(testDataMap.get(TestNGKeys.group));
		System.out.println("****************************************************************************************");
		System.out.println(testCounter++ + "/" + Test.getTotalScenario() + " | "
				+ Util.convertToHHMMSS(new Date().getTime()) + " | " + Thread.currentThread().getId() + " | "
				+ "Scenario: " + testDataMap.get(TestNGKeys.description) + "| Browser: "
				+ testDataMap.get(TestNGKeys.browser) + "| Platform: " + testDataMap.get(TestNGKeys.platform)
				+ "| Priority: " + testDataMap.get(TestNGKeys.priority));
		getCurrentThreadTestList_FromMap().add(r);
	}

	public static synchronized void onTestEnd_NonXl(String testExecutionMoviePath) {
		Test rs=getCurrentThreadTestList_FromMap().get(0);
		long time2=(new Date()).getTime();
		rs.setEndTime_Scenario(time2);
		long time1=rs.getStartTime_Scenario();
		rs.setExecutionTime_Scenario(Util.timeConversion(time2 - time1));
		if (testExecutionMoviePath!=null) {
			rs.setSnapshotURL(testExecutionMoviePath);
		}
		listOfList.add((ArrayList<Test>) getCurrentThreadTestList_FromMap());
		testMap.remove((int) (long) (Thread.currentThread().getId()));
	}

	//called by @test methods
	private static List<Test> getCurrentThreadTestList_FromMap(){
		List<Test> tempListObj=testMap.get((int) (long) (Thread.currentThread().getId()));
		if(tempListObj==null){
			tempListObj= new ArrayList<Test>();
			testMap.put((int) (long) (Thread.currentThread().getId()), tempListObj);
			//System.out.println("Created an ArrayList for storing the results thread: "+Thread.currentThread().getId());
		}else{
			//System.out.println("Existing ArrayList size: "+tempListObj.size()+" for storing the results thread: "+Thread.currentThread().getId());
		}
		return tempListObj; 
	}

	/**@author shailendra.rajawat
	 * Use this method to add the snapshot of passed web element object in Report
	 * */
	public static synchronized void report(String Constan, String description, Object element){
		//SessionId session = ((ChromeDriver)BaseTest.getDriver()).getSessionId();
		if(Constant.enableConsoleLogs){
			System.out.println(Thread.currentThread().getId()+" | "+Constan+" | "+description);
		}
		String url="";
		Test r = new Test();
		r.setTimeStamp(Util.convertToHHMMSS((new Date()).getTime()));
		r.setStatus(Constan);
		r.setDescription(description);
		r.setScenario("");

		if(Constan.equalsIgnoreCase(STATUS.FAIL.value) || Constan.equalsIgnoreCase(STATUS.FATAL.value)){
			url=SnapshotManager.takeSnapShot("failure");
			r.setSnapshotURL(url);
		}else if(element!=null){
			url=SnapshotManager.takeSnapShot("",element);
			r.setSnapshotURL(url);
		}
		//System.out.println("List Size "+customTestList.size()+" for Thread: "+Thread.currentThread().getId());
		getCurrentThreadTestList_FromMap().add(r);
		logExtentTest(Constan, description, url);
		manageAssertions(Constan, description);
	}

	public static synchronized void report_ExitCurrentNode(STATUS status, String description){
		if(Constant.enableConsoleLogs){
			System.out.println(Util.convertToHHMMSS(new Date().getTime())+" | "+Thread.currentThread().getId()+" | "+status.value+" | "+description);
		}
		String url="";

		Test r = new Test();
		r.setTimeStamp(Util.convertToHHMMSS((new Date()).getTime()));
		r.setStatus(status.value);
		r.setDescription(description);
		r.setScenario("");
		if(status.value.equalsIgnoreCase(STATUS.FAIL.value) || status.value.equalsIgnoreCase(STATUS.FATAL.value) ){
			url=SnapshotManager.takeSnapShot("failure");
			r.setSnapshotURL(url);
		}
		//System.out.println("List Size "+customTestList.size()+" for Thread: "+Thread.currentThread().getId());
		getCurrentThreadTestList_FromMap().add(r);

		logExtentTest(status.value, description, url);
		manageAssertions(status.value, description);
	}

	/**
	 * This method will add an Step to the Test. Which will then displayed in the
	 * HTML Report
	 * @param Status any of the Constants provided by STATUS enum
	 * @param description Detailed note about the step 
	 * @author shailendra.rajawat 05 Jan 2018 
	 */
	public static synchronized void report(STATUS Status, String description){
		//SessionId session = ((ChromeDriver)BaseTest.getDriver()).getSessionId();
		String Constan=Status.value;
		try{
			if(Constant.enableConsoleLogs){
				System.out.println(Util.convertToHHMMSS(new Date().getTime())+" | "+Thread.currentThread().getId()+" | "+Constan+" | "+description);
			}
			String url="";
			List<Test> customResultsList=getCurrentThreadTestList_FromMap();

			Test r = new Test();
			r.setTimeStamp(Util.convertToHHMMSS((new Date()).getTime()));
			r.setStatus(Constan);
			r.setDescription(description);
			r.setScenario("");
			if(Constan.equalsIgnoreCase(STATUS.FAIL.value) || Constan.equalsIgnoreCase(STATUS.FATAL.value)  || Constan.equalsIgnoreCase(STATUS.ERROR.value)){
				url=SnapshotManager.takeSnapShot("failure");
				r.setSnapshotURL(url);
			}
			
			int size=customResultsList.size();
			Test t=customResultsList.get(size-1);
			String status=t.getStatus();
			if (status.equalsIgnoreCase(STATUS.NODE.value)) {
				List<Test> nodeList=t.getList();
				nodeList.add(r);
			}else{
				//System.out.println("List Size "+customTestList.size()+" for Thread: "+Thread.currentThread().getId());
				customResultsList.add(r);
			}

			logExtentTest(Constan, description, url);
			manageAssertions(Constan, description);
		}catch (Exception e) {
		}
	}

	public static synchronized void createNode(String description) {
		//SessionId session = ((ChromeDriver)BaseTest.getDriver()).getSessionId();
		if(Constant.enableConsoleLogs){
			System.out.println(Util.convertToHHMMSS(new Date().getTime())+" | "+Thread.currentThread().getId()+" | "+STATUS.NODE.value+" | "+description);
		}
		
		Test r = new Test();
		r.setTimeStamp(Util.convertToHHMMSS((new Date()).getTime()));
		r.setStatus(STATUS.NODE.value);
		r.setDescription(description);
		r.setScenario("");

		//System.out.println("List Size "+customTestList.size()+" for Thread: "+Thread.currentThread().getId());
		getCurrentThreadTestList_FromMap().add(r);
		ExtentManager.createNode(description);
	}


	private static void logExtentTest(String Constan, String description,String url){
		try {
			ExtentTest test=ExtentManager.GetExtentTest();
			if(Constan.equalsIgnoreCase(STATUS.FAIL.value) || Constan.equalsIgnoreCase(STATUS.FATAL.value) || Constan.equalsIgnoreCase(STATUS.ERROR.value) ){
				if(url==null || "".equals(url)){
					test.log(ExtentManager.getStatus(Constan), description);
				}else{
					test.log(ExtentManager.getStatus(Constan), description,MediaEntityBuilder.createScreenCaptureFromPath(url).build());
				}
			}else{
				test.log(ExtentManager.getStatus(Constan), description);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	private static void manageAssertions(String Constan, String description) {
		if(Constant.enableAssertions){
			if(Constan.equalsIgnoreCase(STATUS.FAIL.value) || Constan.equalsIgnoreCase(STATUS.FATAL.value) || Constan.equalsIgnoreCase(STATUS.ERROR.value)){
				Assert.fail(description);
			}
		}
	}

	private static  boolean deleteDirectory(File dir) {
		boolean bool=false;
		if (dir.isDirectory()) { 
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) { 
				boolean success = deleteDirectory(children[i]);
				if (!success) { 
					return false; 
				} 
			} 
		} 
		// either file or an empty directory
		if(dir.getName().equals(Constant.resultFolderName)){
			//System.out.println("Skipping file or directory : " + dir.getName());
			bool=true;
		}else{
			//System.out.println("Removing file or directory : " + dir.getName());
			bool=dir.delete();
		}
		return bool;
	}

	


}
