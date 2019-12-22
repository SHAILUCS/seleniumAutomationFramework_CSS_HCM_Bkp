package or.common;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.xlUtil.DataTable;

public class LoginPage {

	private SeleniumMethods com;
	//private HCMCommon comm;

	@FindBy(name = "username")
	private WebElement text_UserName;

	@FindBy(name = "password")
	private WebElement text_Password;

	@FindBy(xpath = "//button[.='Submit']")
	private WebElement button_Login;

	public static final String title = "Healthcare Manager";

	public LoginPage() {
		com = new SeleniumMethods();
		//comm = new HCMCommon();
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	private static HashMap<Long, Integer> map_RowNumAsPerThread;

	/**
	 * This method will provide HashMap Object, to store the {thread id : row
	 * Num} map. This is important while running the tests in parallel, without
	 * this whole intelligent Login feature will fail
	 * 
	 * @author shailendra Aug 21, 2019
	 */
	private static HashMap<Long, Integer> getMap() {
		if (map_RowNumAsPerThread == null) {
			map_RowNumAsPerThread = new HashMap<Long, Integer>();
		}
		return map_RowNumAsPerThread;
	}

	/**
	 * UNDER CONSTRUCTION This method will be executed whenever test needs to
	 * logout
	 * 
	 * @param userType
	 *            This is the user type like Admin, Recruiter, Recruiter Admin
	 *            etc
	 * @author shailendra Aug 21, 2019
	 */
	public void logout() {
		SideBar_Main side = new SideBar_Main();
		side.logout();
		if (com.verifyPageTitle(title)) {
			CustomReporter.report_ExitCurrentNode(STATUS.PASS, "Logout succeed");
		} else {
			CustomReporter.report_ExitCurrentNode(STATUS.FAIL, "Logout failed");
		}
		unblockUser();
	}

	/**
	 * UNDER CONSTRUCTION This method will be executed whenever test needs to
	 * logout then login again
	 * 
	 * @param userType
	 *            This is the user type like Admin, Recruiter, Recruiter Admin
	 *            etc
	 * @author shailendra Aug 21, 2019
	 */
	public boolean logoutThenPerformLogin(String userType) {
		return performIntelligentLogin(userType);
	}
	
	/**
	 * 
	 * This will pass the uname and pwd and click on submit button 
	 * @author shailendra Aug 21, 2019
	 * */
	public boolean performLogin(String userName, String pwd) {
		return sendUserPassAndClickLogin(text_UserName, text_Password, button_Login, userName, pwd);
	}

	/**
	 * 
	 * This will logout then pass the uname and pwd and click on submit button 
	 * @author Shwetha S Oct 1, 2019
	 * */
	public boolean logoutThenPerformLogin(String userName, String pwd) {
		logout();
		return sendUserPassAndClickLogin(text_UserName, text_Password, button_Login, userName, pwd);
	}

	/**
	 * This method will be executed on start of each test to perform login to
	 * the application in a synchronized(thread-safe) manner
	 * 
	 * @param userType
	 *            This is the user type like Admin, Recruiter, Recruiter Admin
	 *            etc
	 * @author shailendra Aug 21, 2019
	 */
	public boolean performIntelligentLogin(String userType) {
		CustomReporter.report(STATUS.INFO,
				"Login Process Started for user: " + "<br/><b style='font-size: small;'>" + userType + "</b>");
		boolean bool = false;
		SeleniumMethods com = new SeleniumMethods();
		if (com.verifyPageTitle(title, true)) {
			bool = sendUserPassAndClickLogin(userType);
		}
		return bool;
	}

	public static synchronized boolean sendUserPassAndClickLogin(WebElement text_UserName, WebElement text_Password,
			WebElement button_Login, String user, String pass) {

		boolean bool = false;
		SeleniumMethods com = new SeleniumMethods();
		HCMCommon comm = new HCMCommon();

		com.sendKeys(text_UserName, user);
		com.sendKeys(text_Password, pass);
		com.click(button_Login);
		com.wait(2);
		com.waitForElementsTobe_NotVisible(comm.loadingBullet);
		com.wait(1);
		
		if(Constant.get_HCM_EnvironmentInfoSheet().contains("PM")){
			if (com.getCurrentUrl().contains("dashboard")) {  
				bool = true;
			}
		}else{
			if (com.getCurrentUrl().contains(Dashboard.title)) {  
				bool = true;
			}
		}
		return bool;
	}

	/**
	 * This method will be executed on start of each test to perform login to
	 * the application in a synchronized(thread-safe) manner
	 * 
	 * @param text_UserName
	 *            WebElement object of UserName field
	 * @param text_Password
	 *            WebElement object of Password field
	 * @param button_Login
	 *            WebElement object of Login button
	 * @param type
	 *            This is the user type like Admin, Recruiter, Recruiter Admin
	 *            etc
	 * @author shailendra Aug 21, 2019
	 */
	public static synchronized boolean sendUserPassAndClickLogin(WebElement text_UserName, WebElement text_Password,
			WebElement button_Login, String type) {

		String user = "";
		String pass = "";
		String userMap = "";

		DataTable DataTable = new DataTable(Constant.getTestDataFilePath(), Constant.get_HCM_EnvironmentInfoSheet());
		int rowCount = DataTable.getRowCount();
		int credentialsRow = -1;
		for (int row = 1; row < rowCount; row++) {

			if(! getMap().values().contains(row)) {
				String userType = DataTable.getValue(row, "Position");
				// String inUse = DataTable.getValue(row, "inuse");
				//if (type.equalsIgnoreCase(userType) && inUse.equalsIgnoreCase("")) {
				if (type.equalsIgnoreCase(userType)) {
					credentialsRow = row;
					break;
				}
			}
			
		}

		if (credentialsRow != -1) {
			userMap = DataTable.getValue(credentialsRow, "user type");
			user = DataTable.getValue(credentialsRow, "username");
			pass = DataTable.getValue(credentialsRow, "password");
		} else {
			CustomReporter.report(STATUS.FAIL, "Passsed user type '" + type + "' is not present in the test data sheet "
					+ Constant.get_HCM_EnvironmentInfoSheet());
		}

		boolean bool = sendUserPassAndClickLogin(text_UserName, text_Password, button_Login, user, pass);

		if (bool) {
			blockUser(DataTable, credentialsRow);
			CustomReporter.report(STATUS.PASS, "Login succeed with user id: " + userMap + " and username: " + user);
		} else {
			CustomReporter.report(STATUS.PASS,
					"Login Failed with user mapping id: " + userMap + " and username: " + user);
			Assert.fail("Login Failed with user mapping id: " + userMap + " and username: " + user);
		}

		if (bool) {
			blockUser(DataTable, credentialsRow);
		} 
		return bool;
	}

	/**
	 * This method will be executed on start of each test to perform login to
	 * the application in a synchronized(thread-safe) manner
	 * 
	 * @param type
	 *            This is the user type like Admin, Recruiter, Recruiter Admin
	 *            etc
	 * @author shailendra Aug 21, 2019
	 */
	public boolean sendUserPassAndClickLogin(String type) {
		return sendUserPassAndClickLogin(text_UserName, text_Password, button_Login, type);
	}

	/**
	 * This method will be executed on start of each test to block the user, so
	 * that other test can not use this user
	 * 
	 * @param dataTable
	 *            DataTable object passed from sendUserPassAndClickLogin method
	 *            so that time can be saved
	 * @param rowNum
	 *            The row in which we will put "Y" using DataTable object
	 * @author shailendra Aug 21, 2019
	 */
	private static void blockUser(DataTable dataTable, int rowNum) {
		//dataTable.setValue(rowNum, "inuse", "Y");
		getMap().put(Thread.currentThread().getId(), rowNum);
	}

	/**
	 * This method will be executed on success/failure of each test to unblock
	 * the blocked user, so that new test can use this available user
	 * 
	 * @author shailendra Aug 21, 2019
	 */
	public static synchronized void unblockUser() {
		long thid = Thread.currentThread().getId();
		if (getMap().containsKey(thid)) {
			int row = getMap().get(thid);
			//DataTable dataTable = new DataTable(Constant.getTestDataFilePath(), Constant.getEnvironmentInfoSheet());
			//CustomReporter.report(STATUS.INFO, "Unblocking user [" + dataTable.getValue(row, "user type") + "]");
			//dataTable.setValue(row, "inuse", "");
			getMap().remove(thid);
		}
	}

	/**
	 * This method will be executed on start of Execution to unblock all users,
	 * so that any failures in previous execution should not prevent usage of
	 * all available users
	 * 
	 * @author shailendra Aug 21, 2019
	 */
	public static void unblockAllUsers() {
		Util.killExcelProcess(); 
		
		// Not needed as we are not writing anything to excel
		
		/*
		 * DataTable dataTable = new
		 * DataTable(Constant.getTestDataFilePath(),
		 * Constant.getEnvironmentInfoSheet()); int rowCount = dataTable.getRowCount();
		 * for (int row = 1; row < rowCount; row++) { String inUse =
		 * dataTable.getValue(row, "inuse"); if (inUse.toLowerCase().contains("y")) {
		 * dataTable.setValue(row, "inuse", ""); } }
		 */
	}

	/**
	 * this verifies that login page is displaying/loaded
	 * @author Shwetha S Oct 1, 2019
	 */
	public void verifyLoginPageIsDisplayed() {
		com.waitForElementTobe_Visible(button_Login, "Login Page");
	}

}
