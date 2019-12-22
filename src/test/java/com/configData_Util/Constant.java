package com.configData_Util;

import java.io.File;

import org.testng.Assert;

import com.customReporting.CustomReporter;

/**
 * <pre>
 * This class holds some values which controls specific features/flow of
 * framework and these values does not get changed unless you explicitly change
 * them here,
 * 
 * Description for each constant field is provided in the class body.
 * 
 * <b>Note</b>: 
 * All Constant fields are created static so that we can directly use
 * them, without creating object of Constant Class.
 * 
 * <b>Usage</b>: 
 * String filePath=Constant.getTestDataFilePath();
 * System.out.println(filePath); //This will print the test data file (TestData.xlsx) path
 * </pre>
 */
public class Constant {

	/**
	 * This constant holds the project name which is displayed in the HTML Reports
	 */
	public static final String PROJECT="HCM";

	/**
	 * This constant holds the project name which is displayed in the HTML Reports
	 */
	public static final String SIX_DIGIT_PIN="123456";
	
	/**
	 * This constant holds the EMAIL address, which will be filled during execution 
	 */
	public static final String EMAIL="shailendra.rajawat@cssindiaonline.com";
	

	/**
	 * This constant holds the EMAIL address of testteam.developer@gmail.com, which will be filled while creating a new user 
	 */
	public static final String TEST_TEAM_DEVELOPER_EMAIL = "testteam.developer@gmail.com";
	public static final String TEST_TEAM_DEVELOPER_YDT_EMAIL = "developer@ydt.com.au";
	//public static final String TEST_TEAM_DEVELOEPR_EMAIL1 = "s.shwetha@cssindiaonline.com";
	
	/**
	 * {@code captureSnapshots} : set false for speeding up the execution, It
	 * will stop capturing snapshots of each and every action(click/sendkeys etc) performed on
	 * application, but it will not stop the snapshots capturing of failed/fatal
	 * methods,
	 * this flag also controls the movie generation
	 * if this is true then only movie will be generated
	 * 
	 * Creating this method to prevent the 100% Disk usage on Jenkins.
	 * As Jenkins is installed on Linux machine, so creating a logic to stop
	 * capturing movie of Execution
	 * @author shailendra Oct 29, 2019
	 * */
	public static boolean enableCaptureSnapshots() {
		if(Util.getOSName().toLowerCase().contains("lin")) {
			return false;
		}
		return true;
	}
	
	/**
	 * {@code enableAssertions} : set true to enable TestNG Assertion feature while
	 * execution, this will stop the execution of a method once a failure is reported through
	 * <br><b><code>CustomReporter.report(Status.FAIL, "");</code></b>, or in case any exception.
	 */
	public static final boolean enableAssertions = false;
	
	/**
	 * {@code enableConsoleLogs} : set true to enable the report pass,fail etc
	 * statements to be printed on console while execution
	 */
	public static final boolean enableConsoleLogs = true;
	

	/**
	 * {@code enableMailNotification} : If set true, then framework will start 
	 * sending the custom email notification after completion of execution
	 */
	public static final boolean enableMailNotification = false;

	/**
	 * This constant controls how many old reports will be stored, once
	 * ReportingHistory folder count reaches this number, old folders will be deleted
	 * to make the folder count equal to this number.
	 * 
	 * Assign 0 to stop manageHistory Code
	 * 
	 * Creating this method to prevent the 100% Disk usage on Jenkins.
	 * As Jenkins is installed on Linux machine, so creating a logic to stop
	 * maintaining history of more than 50 executions 
	 * @author shailendra Oct 29, 2019
	 * */
	public static int reportingHistoryFolderLimit() {
		if(Util.getOSName().toLowerCase().contains("lin")) {
			return 10;
		}
		return 500;
	}
	
	/**
	 * This constant will control how rows one page of reporting history HTML
	 * should display, this enables the pagination.
	 */
	private static final int reportingHistoryFolderPerPage = 10;
	public static final String reportingHistoryFolderName = "ReportingHistory";
	public static final String reportingHistoryHTMLFolderName= "HTML";

	/**
	 * controls the driver object parameters like : 
	 * 	explicit wait time
	 * 	implicit wait time
	 * 	dimensions width X height
	 * */
	public static final long wait = 30;
	public static final long implicitWait = 0;
	public static final int width = 5000;
	public static final int height = 3000;
	
	
	/**
	 * the directory in which our project is placed 
	 * */
	public static final String root = System.getProperty("user.dir");
	
	
	/**
	 * Driver server exe's information
	 * */
	private static final String serverExesFolderName = "ServerExes";
	private static final String bit = "64bit";
	public static final String geckoDriverFileName_Win="geckodriver.exe";
	public static final String geckoDriverFileName_Linux="geckodriver-v0_18_0-linux64";
	public static final String chromeDriverFileName_Win="chromedriver.exe";
	public static final String chromeDriverFileName_Linux="chromedriver_linux64_FILE";
	public static final String ieDriverFileName = "IEDriverServer.exe";
	public static final String edgeDriverFileName = "MicrosoftWebDriver.exe";
	
	/**Test Data information*/
	private static final String testDataFolderName = "TestData";
	private static final String dbQueriesFolderName = "db_queries";
	private static final String testDataFileName = "TestData.xlsx";
	public static final String snapshotsMovieTemplateName = "snapshotsMovieTemplate.html";
	public static final String reportRedesignTemplateName = "Report_Redesign_Template.html";
	public static final String historyReportRedesignTemplateName = "page.html";
	
	
	/**Results will be stored in this folder, with following names*/
	public static final String resultFolderName = "Result";
	public static final String resultHTMLFileName = "Result.html";
	public static final String resultExtentHTMLFileName = "Result_Extentv3.html";

	/**All downloaded files will be stored in this folder*/
	public static final String downloadFolderName = "Downloads";
	
	/**All Qlik downloaded files will be stored in this folder*/
	public static final String downloadFolderName_Qlik = "Downloads_Qlik";
	
	/**All snapshots will be stored in this folder*/
	public static final String snapshotsFolderName = "Snapshots";
	
	/**These variables are used for deciding the run time behavior*/
	private static String environmentInfoSheet="AWS";
	
	public static final String testsUserMappingSheet="testsUserMapping";

	/**This method will tell you how many items will get display on single html page of Reporting History*/
	public static int getReportinghistoryfolderperpage() {
		int val=reportingHistoryFolderPerPage;
		if (val <= 0) {
			return 1;
		}else{
			return val;
		}
	}
	
	/**
	 * @author shailendra Dec 14, 2019
	 * */
	public static String get_ClientPortal_EnvironmentInfoSheet() {
		if(environmentInfoSheet.toLowerCase().contains("aws"))
			return "ClientPortal-AWS";
		else
			return "ClientPortal-YDT";
	}
	
	/**@return Name of the environment Info Sheet*/
	public static String get_HCM_EnvironmentInfoSheet() {
		return environmentInfoSheet;
	}
	
	/**set the Name of the environment Info Sheet*/
	public static void set_HCM_EnvironmentInfoSheet(String environment) {
			if (environment!=null && !environment.equals("") && !environment.equals("${environment}")) {
				environmentInfoSheet = environment.toUpperCase();
			}else{
				System.err.println("Warning: TestNG parameter 'environment' value= {"+environment+"} is incorrectly provided, running the tests on '"+environmentInfoSheet+"' enviroment");
			}
	}

	public static String snapshotsMovieTemplateFilePath() {
		String path = root + "/" + testDataFolderName + "/" + snapshotsMovieTemplateName;
		return path;
	}

	public static String getReportRedesignTemplateFilePath() {
		String path = root + "/" + testDataFolderName + "/" + reportRedesignTemplateName;
		return path;
	}
	
	public static String getHistoryReportRedesignTemplateFilePath() {
		String path = root + "/" + testDataFolderName + "/" + historyReportRedesignTemplateName;
		return path;
	}
	
	public static String getDbQueriesFolderPath() {
		String path = root + "/" + dbQueriesFolderName +"/" ;
		return path;
	}
	
	public static String getTestDataFolderPath() {
		String path = root + "/" + testDataFolderName ;
		return path;
	}
	
	public static String getTestDataFilePath() {
		String path = root + "/" + testDataFolderName + "/" + testDataFileName;
		return path;
	}

	public static String getSnapShotsFolderPath() {
		String path = root + "/" + snapshotsFolderName;
		return path;
	}

	public static String getResultextenthtmlfilePath() {
		String path = root + "/" + resultFolderName + "/" + resultExtentHTMLFileName;
		return path;
	}

	public static String getResultHtmlFilePath() {
		String path = root + "/" + resultFolderName + "/" + reportRedesignTemplateName;
		return path;
	}

	public static String getResultFolderPath() {
		String val = root + "/" + resultFolderName;
		//Creating the Result Folder in case it is not already present
		File fileObj= new File(val);
		if(!fileObj.exists()) {
			fileObj.mkdir();
		}
		return val;
	}

	public static String getDownloadsPath() {
		//System.out.println("=============================================");
		String val = root + "\\" + downloadFolderName;
		//Creating the Result Folder in case it is not already present
		File fileObj= new File(val);
		if(!fileObj.exists()) {
			System.out.println("CREATING DIRECTORY");
			fileObj.mkdir();
		}
		//System.out.println("DOWNLOADS PATH : " + val);		
		//System.out.println("=============================================");
		return val;
	}
	
	/**
	 * This method checks, whether the Downloads_Qlik folder exists in 
	 * [C:/] directory in windows, and [/var/lib/jenkins/workspace/] directory in linux/jenkins
	 * If it is not found then a new folder will be created
	 * @return absolute path of Downloads_Qlik folder as per OS(C:\Downloads_Qlik\IOTRON) 
	 * */
	public static String getQlikDownloadsPath() {
		String folderDir = "";
		String platform = Util.getOSName();
		if(platform.toLowerCase().contains("win")){
			folderDir = "C:/" + downloadFolderName_Qlik + "/" + "IOTRON";
		}else{
			folderDir = "/var/lib/jenkins/workspace/"+ downloadFolderName_Qlik + "/" + "IOTRON";
		}
		
		File f = new File(folderDir);
		if(!f.exists()){
			if(f.mkdirs()){
				CustomReporter.report(STATUS.INFO, "DIRECTORY CREATED: "+ folderDir);
			} else {
				CustomReporter.report(STATUS.ERROR, "DIRECTORY CREATION FAILED: "+ folderDir);
				Assert.fail("STOPPING TESTS AS, DIRECTORY CREATION FAILED: "+ folderDir);
			}
		}
		return folderDir;
	}
	
	
	public static String getReportingHistoryFolderPath() {
		String val = root + "/" + reportingHistoryFolderName;
		//Creating the Result Folder in case it is not already present
		File fileObj= new File(val);
		if(!fileObj.exists()) {
			fileObj.mkdir();
		}
		return val;
	}
	
	public static String getReportingHistoryHTMLfolderPath(){
		String val = root + "/" + reportingHistoryFolderName + "/" + reportingHistoryHTMLFolderName;
		//Creating the Result Folder in case it is not already present
		File fileObj= new File(val);
		if(!fileObj.exists()) {
			fileObj.mkdir();
		}
		return val;
	}
	
	public static String getFirefoxDriverLocation(String platform) {
		String val="";
		if(platform.toLowerCase().contains("lin")){
			val=root+"/"+serverExesFolderName+"/"+geckoDriverFileName_Linux;
		}else if(platform.toLowerCase().contains("win")){
			val=root+"/"+serverExesFolderName+"/selenium_standalone_binaries/windows/marionette/"+bit+"/"+geckoDriverFileName_Win;
		}
		System.out.println("SHAILENDRA: "+val);
		return val;
	}

	public static String getChromeDriverLocation(String platform) {
		String val="";
		if(platform.toLowerCase().contains("lin")){
			val=root+"/"+serverExesFolderName+"/"+chromeDriverFileName_Linux;
		}else if(platform.toLowerCase().contains("win")){
			val=root+"/"+serverExesFolderName+"/selenium_standalone_binaries/windows/googlechrome/"+bit+"/"+chromeDriverFileName_Win;
		}
		System.out.println("SHAILENDRA: "+val);
		return val;
	}

	public static String getIEDriverLocation() {
		String val = root+"/"+serverExesFolderName+"/selenium_standalone_binaries/windows/internetexplorer/"+bit+"/"+ieDriverFileName;
		return val;
	}
	
	public static String getEdgeDriverLocation() {
		String val = root+"/"+serverExesFolderName+"/selenium_standalone_binaries/windows/edge/"+bit+"/"+edgeDriverFileName;
		return val;
	}
	
	public static String getResourcesFolderPath() {
		String val = root+"/"+"src/test/java/tests/resources/";
		return val;
	}
	
	public static String getJavaPackagePath() {
		String val = root+"/"+"src/test/java/";
		return val;
	}

}
