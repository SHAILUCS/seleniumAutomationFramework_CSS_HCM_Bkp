package or.admin;

import java.util.Date;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.mailUtil.MailUtil;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.xlUtil.DataTable;

import or.common.HCMCommon;
import or.common.LoginPage;

/**
 * Page object class for User Management - Create/Edit page
 */
public class UserManagement_Create_Edit {

	public static final String title_Create = "admin/user/create";
	public static final String title_Edit = "admin/user/update";
	

	private boolean resetPinFlag;
	private SeleniumMethods com;
	private HCMCommon comm;

	public UserManagement_Create_Edit() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'User Management - ')]")
	private WebElement heading_UserManagement;

	@FindBy(name = "firstname")
	private WebElement text_Name;

	@FindBy(name = "lastname")
	private WebElement text_LastName;

	@FindBy(name = "position")
	private WebElement text_Position;

	@FindBy(xpath = "//label[.='Department:']//following::span[contains(@id,'react-select')]")
	private WebElement dropdown_Department;

	@FindBy(name = "phone_0")
	private WebElement text_Phone;

	@FindBy(name = "email_0")
	private WebElement text_Email;

	@FindBy(name = "username")
	private WebElement text_Username;

	@FindBy(name = "password")
	private WebElement text_Password;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Admin']")
	private WebElement link_Admin;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Participant']")
	private WebElement link_Participant;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Member']")
	private WebElement link_Member;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Organization']")
	private WebElement link_Organization;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Schedule']")
	private WebElement link_Schedule;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Fms']")
	private WebElement link_Fms;

	@FindBy(xpath = "//ul[@class='but_around']//label[contains(.,'Incidents')]/input")
	private WebElement checkbox_Incidents;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Imail']")
	private WebElement link_Imail;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Recruitment']")
	private WebElement link_Recruitment;

	@FindBy(xpath = "//ul[@class='but_around']//label[contains(.,'Recruitment Admin')]/input")
	private WebElement checkbox_RecruitmentAdmin;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='CRM']")
	private WebElement link_CRM;

	@FindBy(xpath = "//ul[@class='but_around']//label[contains(.,'Crm Admin')]/input")
	private WebElement checkbox_CrmAdmin;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Finance Planner']")
	private WebElement link_FinancePlanner;

	@FindBy(xpath = "//ul[@class='but_around']//span[@title='Finance']")
	private WebElement link_Finance;

	@FindBy(xpath = "//ul[@class='but_around']//label[contains(.,'Finance Admin')]/input")
	private WebElement checkbox_FinanceAdmin;
	
	@FindBy(xpath = "//button[.='Save User']")
	private WebElement button_SaveUser;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement text_NewPassword;

	@FindBy(xpath = "//input[@name='confirm_password']")
	private WebElement text_ConfirmPassword;

	@FindBy(xpath = "//input[@name='pin']")
	private WebElement text_NewPin;

	@FindBy(xpath = "//input[@name='confirm_pin']")
	private WebElement text_ConfirmPin;

	@FindBy(xpath = "//button[contains(.,'Save password')]")
	private WebElement button_SavePassword;

	@FindBy(xpath = "//a[contains(.,'Login here')]")
	private WebElement link_LoginHere;

	/**
	 * This method will create a new user and provide appropriate accesses
	 * 
	 * @author shailendra Aug 21, 2019
	 * @param data
	 * @param rowIndex
	 */
	public void createNewUser(DataTable data, int rowIndex) {
		String newEmail = createNewUser(data.getValue(rowIndex, "Name"), data.getValue(rowIndex, "Last"),
				data.getValue(rowIndex, "Position"), data.getValue(rowIndex, "Department"),
				data.getValue(rowIndex, "Phone"), data.getValue(rowIndex, "Email"), data.getValue(rowIndex, "username"),
				data.getValue(rowIndex, "password"), data.getValue(rowIndex, "Comma Separated Access Permissions"));

		// Writing the new Email in excel
		data.setValue(rowIndex, "Email", newEmail);
	}

	/**
	 * Overloaded method to create a new user
	 * 
	 * @author shailendra Aug 29, 2019
	 */
	public String createNewUser(String fname, String lname, String pos, String dept, String phone, String email,
			String uname, String pwd, String commaSeparatedAccessPermissions) {

		CustomReporter.createNode("Creating a new user " + uname);

		String newEmail = fillForm(fname, lname, pos, dept, phone, email, uname, pwd, commaSeparatedAccessPermissions);
		
		com.wait(2);

		com.waitForElementsTobe_Present(By.xpath("//div[@class='rt-tr -odd']//div[@class='rt-td'][contains(.,'" + uname + "')]"),
				"Newly created user");

		// For any type of user that we are creating
		// as a standard are logging out. 
		// Since FMS and Admin, needs to reset the pwd/pin. 
		LoginPage login = new LoginPage();
		login.logout();

		if (resetPinFlag) {
			resetPinAndPassword(uname, pwd);
		}

		return newEmail;
	}

	/**
	 * This method get the url of reset password link from the gmail
	 * 
	 * @param uname
	 * @author Shwetha S Oct 1, 2019
	 */
	public void resetPinAndPassword(String uname, String pwd) {
		// First Logout from the System
		LoginPage login = new LoginPage();

		// We want to Access to Gmail, and get the password Reset link
		com.wait(10);
		Map<String, String> mailContent = MailUtil.readMail_SpamFolder(uname);

		if (mailContent != null) {
			String body = mailContent.get(MailUtil.BODY);

			int startIndex = body.indexOf("https://");
			int endIndex = body.indexOf("\"", startIndex);
			// System.out.println(startIndex);
			// System.out.println(endIndex);

			String url = body.substring(startIndex, endIndex);
			// System.out.println(url);
			// we will open that password reset link, and do the reset pin
			// action
			com.navigateTo(url);
			com.wait(2);
			com.sendKeys(text_NewPassword, pwd);
			com.wait(2);
			com.sendKeys(text_ConfirmPassword, pwd);
			com.sendKeys(text_NewPin, Constant.SIX_DIGIT_PIN);
			com.sendKeys(text_ConfirmPin, Constant.SIX_DIGIT_PIN);
			com.click(button_SavePassword);
		}

		login.verifyLoginPageIsDisplayed();

	}

	private String fillForm(String fname, String lname, String pos, String dept, String phone, String email,
			String uname, String pwd, String commaSeparatedAccessPermissions) {
		com.wait(1);
		com.waitForElementTobe_NotVisible(comm.loadingBullet);

		if (fname != null) {
			com.sendKeys("Name", text_Name, fname);
		}

		if (lname != null) {
			com.sendKeys("Last Name", text_LastName, lname);
		}

		if (pos != null) {
			com.sendKeys("Position", text_Position, pos);
		}

		if (dept != null) {
			comm.selectByVisibleText(dropdown_Department, dept);
		}

		if (phone != null) {
			com.sendKeys("Phone", text_Phone, phone);
		}

		//String newEmail = email;
		String newEmail = Constant.TEST_TEAM_DEVELOPER_YDT_EMAIL;
		if (email != null) {
			if (!newEmail.contains("+")) {
				String emailParts[] = newEmail.split("@");
				emailParts[0] = emailParts[0] + "+" + new Date().getTime();
				newEmail = emailParts[0] + "@" + emailParts[1];
			}

			com.sendKeys("Email", text_Email, newEmail);

		}

		if (uname != null) {
			com.sendKeys("Username", text_Username, uname);
		}

		if (pwd != null) {
			com.sendKeys("Password", text_Password, pwd);
		}

		if (commaSeparatedAccessPermissions != null) {
			if (!commaSeparatedAccessPermissions.trim().equals("")) {
				selectRoles(commaSeparatedAccessPermissions);
			}
		}
        
		com.click(button_SaveUser, "Save User");
		
		com.wait(2);

		com.waitForElementsTobe_NotVisible(comm.loadingBullet);

		return newEmail;
	}

	/**
	 * 
	 * Copy of Create user
	 * 
	 * @param fname
	 * @param lname
	 * @param pos
	 * @param dept
	 * @param phone
	 * @param email
	 * @param uname
	 * @param pwd
	 * @param commaSeparatedAccessPermissions
	 * @return
	 */
	public String editUser(String fname, String lname, String pos, String dept, String phone, String email,
			String uname, String pwd, String commaSeparatedAccessPermissions) {

		CustomReporter.createNode("Editing user " + uname);

		// you can change only 8 fields,now we know which fields we can edit, we
		// will call our fillForm method

		String newEmail = fillForm(fname, lname, pos, dept, phone, email, null, pwd, commaSeparatedAccessPermissions); // this
																														// method
																														// will
																														// change
																														// everything
																														// except
																														// username
																														// understand

		com.waitForElementsTobe_Present(By.xpath("//div[@class='rt-td'][contains(.,'" + uname + "')]"),
				"Newly created user");

		return newEmail;
	}

	/**
	 * This method will accept comma separated roles and send them one by one to
	 * selectRole method
	 * 
	 * @author shailendra Aug 22, 2019
	 * @param commaSeparatedRoles
	 */
	private void selectRoles(String commaSeparatedRoles) {

		CustomReporter.createNode("Assigning roles [" + commaSeparatedRoles + "]");

		String roleArray[] = null;

		if (commaSeparatedRoles.contains(",")) {
			roleArray = commaSeparatedRoles.split(",");
		} else {
			roleArray = new String[1];
			roleArray[0] = commaSeparatedRoles;
		}

		for (String role : roleArray) {
			selectRole(role);
		}

	}

	/**
	 * This method will accept string role and select the matching role in
	 * application
	 * 
	 * @author shailendra Aug 22, 2019
	 * @param role
	 */
	private void selectRole(String role) {

		switch (role.trim().toLowerCase()) {

		case "admin":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Admin, "Admin");
			CustomReporter.report(STATUS.WARNING, "Contact Developer to create six digit pin 123456 for this user");
			resetPinFlag = true;
			break;

		case "participant":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Participant, "Participant");
			break;

		case "member":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Member, "Member");
			break;

		case "organization":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Organization, "Organization");
			break;

		case "schedule":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Schedule, "Schedule");
			break;

		case "fms":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Fms, "Fms");
			CustomReporter.report(STATUS.WARNING, "Contact Developer to create six digit pin 123456 for this user");
			// resetPinFlag = true;
			break;

		case "incidents":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(checkbox_Incidents, "Incidents");
			break;

		case "imail":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Imail, "Imail");
			break;

		case "recruitment":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Recruitment, "Recruitment");
			break;

		case "recruitment admin":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(checkbox_RecruitmentAdmin, "Recruitment Admin");
			break;

		case "crm":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_CRM, "Crm");
			break;

		case "crm admin":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(checkbox_CrmAdmin, "Crm Admin");
			break;

		case "finance planner":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_FinancePlanner, "Finance Planner");
			break;

		case "finance":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(link_Finance, "Finance");
			break;

		case "finance admin":
			com.javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(checkbox_FinanceAdmin, "Finance Admin");
			break;
			
		default:
			CustomReporter.report(STATUS.FAIL,
					"Invalid Access Permissions is provided, valid values are [Admin, Participant, Member, Organization, Schedule, Fms,  incidents, Imail, Recruitment, Recruitment Admin, CRM, Crm Admin, Finance Planner, Finance, Finance Admin]");
			break;
		}

	}

	public void verifyUserDetails(String fname, String lname, String pos, String dept, String phone,
			String testTeamDeveloeprEmail1, String uname, String pwd, String commaSeparatedAccessPermissions) {
		String fn = com.getAttribute(text_Name, "value");
		Util.comparator_NonNumbers(fname, fn, "Edited value of FName");

		String ln = com.getAttribute(text_LastName, "value");
		Util.comparator_NonNumbers(lname, ln, "Edited value of LName");

		String ps = com.getAttribute(text_Position, "value");
		Util.comparator_NonNumbers(pos, ps, "Edited value of Position");

		String ph = com.getAttribute(text_Phone, "value");
		Util.comparator_NonNumbers(phone, ph, "Edited value of Phone");

		String email = com.getAttribute(text_Email, "value");
		Util.comparator_NonNumbers(testTeamDeveloeprEmail1, email, "Edited value of Email");

		String un = com.getAttribute(text_Username, "value");
		Util.comparator_NonNumbers(uname, un, "Edited value of Uname");

		String pd = com.getAttribute(text_Password, "value");
		Util.comparator_NonNumbers(pwd, pd, "Edited value of Password");

	}

}
