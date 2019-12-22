package or.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.common.SideBar_Admin;

public class UserManagement {

	public static final String title = "admin/user/list";
	SeleniumMethods com;
	HCMCommon comm;

	public UserManagement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//div[contains(@class,'back_arrow')]/..")
	private WebElement icon_Back;

	@FindBy(xpath = "//h1[.='User Management']")
	private WebElement heading_UserManagement;

	@FindBy(id = "searchRoles")
	public WebElement text_Search;

	@FindBy(xpath = "(//span[contains(@id,'react-select')][contains(@class,'value-wrapper')])[2]")
	public WebElement dropdown_Filter;

	@FindBy(xpath = "//a[contains(.,'Create New User')]")
	public WebElement button_CreateNewUser;

	@FindBy(css = "div[class*='rt-noData']")
	private WebElement data_NoRoles;

	@FindBy(css = "span[class*='not_A']")
	private WebElement cell_redHighLight;

	@FindBy(css = "button[title='Active']")
	private WebElement icon_ActiveUser;

	@FindBy(css = "i[title='Archive']")
	private WebElement icon_Archive;

	@FindBy(css = "button[title='Inactive']")
	private WebElement icon_InActiveUser;

	@FindBy(xpath="//button[@class='Confirm_btn_Conf']")
	private WebElement button_ConfirmArchieve; 
	
	
	/**
	 * This method will verify the page content
	 * */
	public void verifyUI() {
		com.isElementPresent(icon_Back, "Back icon");
		com.isElementPresent(heading_UserManagement, "heading_UserManagement");
		com.isElementPresent(text_Search, "text_Search");
		com.isElementPresent(dropdown_Filter, "dropdown_Filter");
		com.isElementPresent(button_CreateNewUser, "button_CreateNewUser");

		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.verifyColumnHeaders(1, "ID", "First Name", "Last Name", "Email", "Phone", "Username", "Action");
		int row = 2;
		int col = 7;
		com.isElementPresent(tab.getChildObject(row, col, "i", 0),
				"Update Icon in " + row + " Row and " + col + " Col");
		com.isElementPresent(tab.getChildObject(row, col, "i", 1),
				"Archieve Icon in " + row + " Row and " + col + " Col");
		com.isElementPresent(tab.getChildObject(row, col, "i", 1),
				"Inactive Icon in " + row + " Row and " + col + " Col");

		comm.verifyUI_Pagination_ViewBy();

		SideBar_Admin sidebar = new SideBar_Admin();
		sidebar.verifyUI_LeftNavigationPane();
	}


	/**
	 * This method will check the Filter drop down functionality
	 * First it will check Inactive users, then Archived then Active users
	 * @author shailendra Aug 29, 2019 
	 * */
	public void verifyFilterDropDown() {

		// Inactive only filter drop down func
		CustomReporter.createNode("Inactive only");
		comm.selectByVisibleText(dropdown_Filter, "Inactive only");

		if (!com.waitForElementsTobe_NotVisible(data_NoRoles, 1)) {
			CustomReporter.report(STATUS.WARNING, "No Roles message is getting displayed");
		} else {

			if (!com.waitForElementsTobe_NotVisible(cell_redHighLight, 1)) {
				CustomReporter.report(STATUS.PASS, "Some rows are getting displayed with Red Background");

				// Checking active icon should be present on page
				if (com.isElementPresent(icon_ActiveUser)) {
					CustomReporter.report(STATUS.PASS,
							"Active Icon is present, Filter is properly working, Inactive user are getting displayed");
				} else {
					CustomReporter.report(STATUS.FAIL,
							"Active Icon is present, Filter is NOT working, Inactive user are NOT getting displayed");
				}
			} else {
				CustomReporter.report(STATUS.FAIL, "Rows are NOT getting displayed with Red Background");
			}
		}

		// Archive only filter drop down func
		CustomReporter.createNode("Archive only");
		comm.selectByVisibleText(dropdown_Filter, "Archive only");

		if (!com.waitForElementsTobe_NotVisible(data_NoRoles, 1)) {
			CustomReporter.report(STATUS.WARNING, "No Roles message is getting displayed");
		} else {

			if (!com.waitForElementsTobe_NotVisible(cell_redHighLight, 1)) {
				CustomReporter.report(STATUS.PASS, "Some rows are getting displayed with Red Background");

				// Checking Archive icon should not be present on page
				if (com.waitForElementsTobe_NotVisible(icon_Archive, 1)) {
					CustomReporter.report(STATUS.PASS,
							"Archive Icon is not present, Filter is properly working, Archived user are getting displayed");
				} else {
					CustomReporter.report(STATUS.FAIL,
							"Archive Icon is present, Filter is NOT working, Archived user are NOT getting displayed");
				}

			} else {
				CustomReporter.report(STATUS.FAIL, "Rows are NOT getting displayed with Red Background");
			}
		}

		// Active only filter drop down func
		// checks Inactive and Archive user are not getting displayed
		CustomReporter.createNode("Active only");
		comm.selectByVisibleText(dropdown_Filter, "Active only");

		if (!com.waitForElementsTobe_NotVisible(data_NoRoles, 1)) {
			CustomReporter.report(STATUS.WARNING, "No Roles message is getting displayed");
		} else {

			if (!com.waitForElementsTobe_NotVisible(cell_redHighLight, 3)) {
				CustomReporter.report(STATUS.FAIL,
						"Active only Filter is NOT working, Inactive/Archive user are getting displayed");
			} else {
				CustomReporter.report(STATUS.PASS,
						"Active only Filter is working, Inactive/Archive user are NOT getting displayed");
			}

		}

	}


	/**
	 * This method is used to verify the text search functionality of Admin Users Page
	 * Once called, this method will send the control to HCMCommon verifyValid_TextSearch method
	 * It will send the TextField Object and the data to be searched
	 * @author shailendra Sep 3, 2019
	 * */
	public void verifyValid_TextSearch(String searchData) {
		comm.verifyValid_TextSearch(text_Search, searchData);
	}


	/**
	 * This method will search for the passed user and will make it inactive,
	 * Also it will verify that the user is inactive or not.
	 * @param uname the username of user which needs to be made inactive
	 * @author shailendra Sep 4, 2019
	 * */
	public void inactiveUser(String uname) {
	
		CustomReporter.createNode("Inactive ["+uname+"] user");

		int row = comm.verifyValid_TextSearch(text_Search, uname);

		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int desiredCol = tab.initHeaderColumnList(1).getColumnNumber("Action");
		
		com.waitForElementsTobe_Present(tab.getChildObject(row, desiredCol, "i", 2));

		com.click(tab.getChildObject(row, desiredCol, "i", 2), "Inactive user link");

		com.wait(2);

		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

		com.isElementPresent(cell_redHighLight,"Red Highlighted User ID");

		com.isElementPresent(icon_ActiveUser,"Active User Icon");

	}

	/**
	 * activate the inactivated user and verifying the Inactive button
	 * @param uname
	 * @author Shwetha S Sep 5, 2019
	 */
	public void activateTheInactivatedUser(String uname){
		
		CustomReporter.createNode("Activate The Inactivated ["+uname+"] User");

		int row = comm.verifyValid_TextSearch(text_Search, uname);

		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int desiredCol = tab.initHeaderColumnList(1).getColumnNumber("Action");
		
		com.click(tab.getChildObject(row, desiredCol, "i", 2), "active user link"); 
	
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator); 

		com.isElementPresent(icon_InActiveUser,"Inactive user icon");
	}

	/**
	 * Archieve user and verify login
	 * @param uname
	 * @author Shwetha S Sep 5, 2019
	 */

	public void archieveUser(String uname){
		int row = comm.verifyValid_TextSearch(text_Search, uname);

		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int desiredCol = tab.initHeaderColumnList(1).getColumnNumber("Action");

		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

		com.click(tab.getChildObject(row, desiredCol, "i", 1), "Archieve user link");
     
		com.click(button_ConfirmArchieve);
	}
	
	
	/**
	 * Verifying  the details of user before and after Editing
	 * @param uname
	 * @author Shwetha S Sep 5, 2019
	 */
	public void searchAndEditUser(String uname){

		int row = comm.verifyValid_TextSearch(text_Search, uname);

		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int desiredCol = tab.initHeaderColumnList(1).getColumnNumber("Action");

		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

		com.click(tab.getChildObject(row, desiredCol, "i", 0), "Edit user link");
		
	}

}