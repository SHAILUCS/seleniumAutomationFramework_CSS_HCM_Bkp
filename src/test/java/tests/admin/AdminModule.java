package tests.admin;

import java.util.Date;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;

import or.admin.AdminDashboard;
import or.admin.Approvals;
import or.admin.Logs;
import or.admin.UserManagement;
import or.admin.UserManagement_Create_Edit;
import or.common.Dashboard;
import or.common.LoginPage;
import or.common.Navigator;
import or.common.SideBar_CRM;
import or.common.SideBar_Main;
import or.crm.Crm_Dashboard;
import or.crm.StaffMembers;
import or.imail.Imail_Dashboard;
import or.member.Member_Dashboard;
import or.organization.Organization_Dashboard;
import or.participant.Participant_Dashboard;
import or.recruit.RecruitmentDashboard;
import or.schedule.Schedule_Dashboard;

public class AdminModule {

	private Navigator navigateToUserManagementPage() {
		Navigator nav = new Navigator();
		AdminDashboard ad = new AdminDashboard();
		nav.toAdminDashboard(Constant.SIX_DIGIT_PIN).traverseMenu_VerifyPageTitle(UserManagement.title,
				ad.link_UserManagement);
		return nav;
	}
	

	/**
	 * This method will navigate to Create User Page and will create a new user with passed data
	 * @author shailendra Oct 21, 2019
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
	public LoginPage createNewUser(String fname, String lname, String pos, String dept, String phone, String email,
			String uname, String pwd, String commaSeparatedAccessPermissions) {
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		user.createNewUser(fname, lname, pos, dept, phone, email, uname, pwd, commaSeparatedAccessPermissions);
		
		return new LoginPage();
	}
	
	
	/**
	 * It Navigates to Logs Page 
	 * @author shwetha
	 */
	private Navigator navigateToLogs() {
		Navigator nav = new Navigator();
		AdminDashboard ad = new AdminDashboard();
		nav.toAdminDashboard(Constant.SIX_DIGIT_PIN).traverseMenu_VerifyPageTitle(Logs.title,ad.link_Logs);
		return nav;
	}
	
	/**
	 * It Navigates to Approvals Page 
	 * @author shwetha
	 */
	private Navigator navigateToApprovals() {
		Navigator nav = new Navigator();
		AdminDashboard ad = new AdminDashboard();
		nav.toAdminDashboard(Constant.SIX_DIGIT_PIN).traverseMenu_VerifyPageTitle(Approvals.title,ad.link_Approvals);
		return nav;
	}

	/**
	 * @author shailendra
	 */
	@Test(description = "TC01_Admin_UIVerification_UserMgmt",groups = {"UI Only"})
	private void TC01_Admin_UIVerification_UserMgmt() {
		navigateToUserManagementPage();
		UserManagement um = new UserManagement();
		um.verifyUI();
	}

	/**
	 * @author shailendra
	 */
	@Test(description = "TC02_Admin_FilterDropdown",groups = {"Func Only"})
	private void TC02_Admin_FilterDropdown() {
		navigateToUserManagementPage();
		UserManagement um = new UserManagement();
		um.verifyFilterDropDown();
	}

	/**
	 * @author shailendra Aug 29, 2019
	 */
	@Test(description = "TC03_Admin_TextSearch",groups = {"UI and Func"})
	private void TC03_Admin_TextSearch() {
		navigateToUserManagementPage();
		UserManagement um = new UserManagement();
		um.verifyValid_TextSearch("super");
	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Make
	 * it inactive 3. Try to Login with inactive dummy user 4. System should not
	 * allow login
	 * 
	 * @author shailendra Aug 29, 2019
	 */
	@Test(description = "TC04_Admin_Create A New User then make it Inactive",groups = {"Func Only"})
	private void TC04_Admin_CreateUser_InactiveUser() {

		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");
		
		LoginPage login = new LoginPage();


		login.performIntelligentLogin("Admin");
		
		navigateToUserManagementPage();
		
		um.inactiveUser(uname);
		
		if (login.logoutThenPerformLogin(uname, pwd)) {
			CustomReporter.report(STATUS.FAIL, "Inactive user is able to login into application");
		} else {
			CustomReporter.report(STATUS.PASS, "Inactive user is NOT able to login into application");
		}

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Make
	 * it inactive 3. Try to Login with inactive dummy user, and verify System
	 * should not allow login 4. Login with Admin, Goto User page search for
	 * dummy user 5. Make it Active 6. Login with active dummy user and verify
	 * Dashboard is getting displayed
	 * 
	 * @author shwetha sep 4, 2019
	 * @throws InterruptedException
	 */

	@Test(description = "TC05_Admin_CreateUser_Inactive_ActiveUser",groups = {"Func Only"})
	private void TC05_Admin_CreateUser_Inactive_ActiveUser() {
		// 1. create a new dummy user
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");
		
		LoginPage login = new LoginPage();


		login.performIntelligentLogin("Admin");
		
		navigateToUserManagementPage();
		// 2. Make it inactive
		um.inactiveUser(uname);

		// 3. Try to Login with inactive dummy user, and verify System should
		// not allow login
		

		if (login.logoutThenPerformLogin(uname, pwd)) {
			CustomReporter.report(STATUS.FAIL,
					"Inactive user is able to login into application once after activating ");

		} else {
			CustomReporter.report(STATUS.PASS, "Inactive user is NOT able to login into application ");

		}

		// 4. Login with Admin,
		login.performIntelligentLogin("Admin");

		// Navigating to user management page
		navigateToUserManagementPage();

		// Goto User page search for dummy user and Make it Active
		um.activateTheInactivatedUser(uname);

		// Login with active dummy user and verify Dashboard is getting
		// displayed
		if (login.logoutThenPerformLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Inactive user is able to login into application, After re-activating");
		} else {
			CustomReporter.report(STATUS.FAIL, "Inactive user is NOT able to login into application");
		}
		// Script is completed !!
	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Make
	 * it archive 3. Try to Login with archived dummy user, and verify System
	 * should not allow login
	 * 
	 * @author shwetha sep 5, 2019
	 */
	@Test(description = "TC06_Admin_CreateUser_ArchiveUser",groups = {"Func Only"})
	private void TC06_Admin_CreateUser_ArchiveUser() {
		// 1. create a new dummy user
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");
		
		LoginPage login = new LoginPage();


		login.performIntelligentLogin("Admin");
		
		navigateToUserManagementPage();
		// 2. Make it archive
		um.archieveUser(uname);

		

		if (login.logoutThenPerformLogin(uname, pwd)) {
			CustomReporter.report(STATUS.FAIL, "Archieve user is able to login into application");

		} else {
			CustomReporter.report(STATUS.PASS, "Archieve user is NOT able to login into application ");

		}

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2.
	 * Verify that the saved details are what we provided the first time 3. Edit
	 * all the details of this user 4. Verify that the saved details are what we
	 * provided the second time
	 * 
	 * @author Shwetha sep 6, 2019
	 */
	@Test(description = "TC07_Admin_EditUser",groups = {"Func Only"})
	private void TC07_Admin_EditUser() {
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");
		
		LoginPage login = new LoginPage();


		login.performIntelligentLogin("Admin");
		
		navigateToUserManagementPage();
		
		um.searchAndEditUser(uname);

		String fname = "A_ESSS";
		String lname = "Ln";
		String pos = "Automation111";
		String dept = "Finance";
		String phone = "3123456789";
		
		String commaSeparatedAccessPermissions ="Finance Planner";
		
		
		String email =  user.editUser(fname, lname, pos, dept, phone, Constant.TEST_TEAM_DEVELOPER_EMAIL,uname, pwd, commaSeparatedAccessPermissions);
		
		um.searchAndEditUser(uname);
		user.verifyUserDetails(fname, lname, pos, dept, phone, email,uname, pwd, commaSeparatedAccessPermissions);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Admin Role 3. Login with that user and verify Admin link is visible
	 * for him 4. Also verify that all Admin links are getting displayed on
	 * Admin Dashboard
	 * 
	 * @author Shwetha sep 9, 2019
	 */
	@Test(description = "TC08_Admin_CreateNewUser_CheckRole_Admin",groups = {"Func Only","Mail"})
	private void TC08_Admin_CreateNewUser_CheckRole_Admin() {

		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Admin");
		
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Admin user is able to login into application");
		} else {
			CustomReporter.report(STATUS.FAIL, "Admin user is NOT able to login into application ");
		}

		Navigator nav = new Navigator();
		nav.toAdminDashboard(Constant.SIX_DIGIT_PIN);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Participants Role 3. Login with that user and verify Participants link
	 * is visible for him 4. Also verify that all Participants links are getting
	 * displayed on Participants Dashboard
	 * 
	 * @author  Shwetha sep 9, 2019
	 */
	@Test(description = "TC09_Admin_CreateNewUser_CheckRole_Participants",groups = {"Func Only"})
	private void TC09_Admin_CreateNewUser_CheckRole_Participants() {
		Navigator nav = new Navigator();

		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Participant");

		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Participant user is able to login into application");

		} else {
			CustomReporter.report(STATUS.FAIL, "Participant user is NOT able to login into application ");

		}

		Dashboard dash = new Dashboard();

		nav.traverseMenu_VerifyPageTitle(Participant_Dashboard.title, dash.link_Participants);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Member Role 3. Login with that user and verify Member link is visible
	 * for him 4. Also verify that all Member links are getting displayed on
	 * Member Dashboard
	 * 
	 * @author  Shwetha sep 10, 2019
	 */
	@Test(description = "TC10_Admin_CreateNewUser_CheckRole_Member",groups = {"Func Only"})
	private void TC10_Admin_CreateNewUser_CheckRole_Member() {

		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Member");
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Member user is able to login into application");

		} else {
			CustomReporter.report(STATUS.FAIL, "Member user is NOT able to login into application ");

		}

		Dashboard dash = new Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(Member_Dashboard.title, dash.link_Members);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Organization Role 3. Login with that user and verify Organization link
	 * is visible for him 4. Also verify that all Organization links are getting
	 * displayed on Organization Dashboard
	 * 
	 * @author  Shwetha sep 10, 2019
	 */
	@Test(description = "TC11_Admin_CreateNewUser_CheckRole_Organization",groups = {"Func Only"})
	private void TC11_Admin_CreateNewUser_CheckRole_Organization() {
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Organization");
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Organization user is able to login into application");

		} else {
			CustomReporter.report(STATUS.FAIL, "Organization user is NOT able to login into application ");

		}

		Dashboard dash = new Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(Organization_Dashboard.title, dash.link_Organisation);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Schedule Role 3. Login with that user and verify Schedule link is
	 * visible for him 4. Also verify that all Schedule links are getting
	 * displayed on Schedule Dashboard
	 * 
	 * @author  Shwetha sep 11, 2019
	 */
	@Test(description = "TC12_Admin_CreateNewUser_CheckRole_Schedule",groups = {"Func Only"})
	private void TC12_Admin_CreateNewUser_CheckRole_Schedule() {

		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Schedule");
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Organization user is able to login into application");

		} else {
			CustomReporter.report(STATUS.FAIL, "Organization user is NOT able to login into application ");

		}

		Dashboard dash = new Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(Schedule_Dashboard.title, dash.link_Schedule);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Fms Role 3. Login with that user and verify Fms link is visible for
	 * him 4. Also verify that all Fms links are getting displayed on Fms
	 * Dashboard
	 * 
	 * @author  Shwetha sep 11, 2019
	 */
	@Test(description = "TC13_Admin_CreateNewUser_CheckRole_Fms",groups = {"Func Only","Mail"})
	private void TC13_Admin_CreateNewUser_CheckRole_Fms() {

		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "fms", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Fms");
		
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "FMS user is able to login into application");
		} else {
			CustomReporter.report(STATUS.FAIL, "FMS user is NOT able to login into application");
		}

		Navigator nav = new Navigator();
		nav.toFmsDashboard(Constant.SIX_DIGIT_PIN);
		

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Fms and Fms Incident Role 3. Login with that user and verify Fms link
	 * is visible for him 4. Also verify that Incidents level links are getting
	 * displayed on Fms Dashboard
	 * 
	 * @author  Shwetha sep 12, 2019
	 */
	@Test(description = "TC14_Admin_CreateNewUser_CheckRole_Fms_Incidents",groups = {"Func Only","Mail"})
	private void TC14_Admin_CreateNewUser_CheckRole_Fms_Incidents() {
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Fms, incidents");
		LoginPage login = new LoginPage();

		
		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "FMS Incident user is able to login into application");
		} else {
			CustomReporter.report(STATUS.FAIL, "FMS Incident user is NOT able to login into application");
		}

		Navigator nav = new Navigator();
		nav.toFmsDashboard(Constant.SIX_DIGIT_PIN);


		

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Imail Role 3. Login with that user and verify Imail link is visible
	 * for him 4. Also verify that all Imail links are getting displayed on
	 * Imail Dashboard
	 * 
	 * @author  Shwetha sep 12, 2019
	 */
	@Test(description = "TC15_Admin_CreateNewUser_CheckRole_Imail",groups = {"Func Only"})
	private void TC15_Admin_CreateNewUser_CheckRole_Imail() {
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Imail");
		
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Imail user is able to login into application");

		} else {
			CustomReporter.report(STATUS.FAIL, "Imail user is NOT able to login into application ");

		}

		Dashboard dash = new Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(Imail_Dashboard.title, dash.link_Imail);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Recruitment Role 3. Login with that user and verify Recruitment link
	 * is visible for him 4. Also verify that only Recruiter level links are
	 * getting displayed on Recruitment Dashboard
	 * 
	 * @author  Shwetha sep 13, 2019
	 */
	@Test(description = "TC16_Admin_CreateNewUser_CheckRole_Recruitment",groups = {"Func Only"})
	private void TC16_Admin_CreateNewUser_CheckRole_Recruitment() {
		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");
		
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Recruitment user is able to login into application");
		} else {
			CustomReporter.report(STATUS.FAIL, "Recruitment user is NOT able to login into application ");
		}

		Dashboard dash = new Dashboard();
		Navigator nav = new Navigator();
		//nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, dash.link_Recruitment);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Recruitment and Recruitment Admin Role 3. Login with that user and
	 * verify Recruitment link is visible for him 4. Also verify that all
	 * Recruiter Admin level links are getting displayed on Recruitment
	 * Dashboard
	 * 
	 * @author Shwetha sep 13, 2019
	 */
	@Test(description = "TC17_Admin_CreateNewUser_CheckRole_RecruitmentAdmin",groups = {"Func Only"})
	private void TC17_Admin_CreateNewUser_CheckRole_RecruitmentAdmin() {

		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment,  Recruitment Admin");
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Recruitment Admin user is able to login into application");

		} else {
			CustomReporter.report(STATUS.FAIL, "Recruitment Admin user is NOT able to login into application ");

		}

		Dashboard dash = new Dashboard();
		Navigator nav = new Navigator();
		//nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, dash.link_Recruitment);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Crm Role 3. Login with that user and verify Crm link is visible for
	 * him 4. Also verify that all Crm user level links are getting displayed on
	 * Crm Dashboard
	 * 
	 * @author shwetha 16, 2019
	 */
	@Test(description = "TC18_Admin_CreateNewUser_CheckRole_Crm",groups = {"Func Only"})
	private void TC18_Admin_CreateNewUser_CheckRole_Crm() {
		Navigator nav = new Navigator();
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";
		
		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789", Constant.TEST_TEAM_DEVELOPER_EMAIL,
				uname, pwd, "CRM");
		
		LoginPage login=new LoginPage();
		
		login.performIntelligentLogin("Admin");
		
	    SideBar_Main main = new SideBar_Main();
		SideBar_CRM crm= new SideBar_CRM();
		main.openSideBar();
		nav.traverseMenu_VerifyPageTitle(Crm_Dashboard.titleAdmin, main.link_CRM); 
		main.closeSideBar(); 
		nav.traverseMenu_VerifyPageTitle(StaffMembers.titleUsermanagement, crm.link_Staff_Members);
		
		StaffMembers staffMembers = new StaffMembers();
		staffMembers.addCrmUser(uname);
		
		 if(login.logoutThenPerformLogin(uname,pwd)) {
		  CustomReporter.report(STATUS.PASS,"CRM user is able to login into application");
		
		  }else { 
			  
			  CustomReporter.report(STATUS.FAIL,"CRM  user is NOT able to login into application ");
		  }
		 
		    Dashboard dash = new Dashboard();
			
			nav.traverseMenu_VerifyPageTitle(Crm_Dashboard.titleUser, dash.link_CRM_User);
		  
		  }
		
		 

	

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Crm and Crm Admin Role 3. Login with that user and verify Crm link is
	 * visible for him 4. Also verify that all Crm admin level links are getting
	 * displayed on Crm Dashboard
	 * 
	 * @author shwetha sep 16, 2019
	 */
	@Test(description = "TC19_Admin_CreateNewUser_CheckRole_CrmAdmin",groups = {"Func Only"})
	private void TC19_Admin_CreateNewUser_CheckRole_CrmAdmin() {
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "CRM, Crm Admin");
		LoginPage login = new LoginPage();
	
		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "CRM Admin user is able to login into application");

		} else {
			CustomReporter.report(STATUS.FAIL, "CRM Admin user is NOT able to login into application ");

		}

		Dashboard dash = new Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(Crm_Dashboard.titleAdmin, dash.link_CRM_Admin);

	}

	/**
	 * This test will perform following tasks 1. create a new dummy user 2. Give
	 * it Finance Role 3. Login with that user and verify Finance link is
	 * visible for him 4. Also verify that all Finance links are getting
	 * displayed on Finance Dashboard
	 * 
	 * @author shwetha sep 17, 2019
	 */
	@Test(description = "TC20_Admin_CreateNewUser_CheckRole_Finance",groups = {"Func Only"})
	private void TC20_Admin_CreateNewUser_CheckRole_Finance() {
		UserManagement um = new UserManagement();

		navigateToUserManagementPage().traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create,
				um.button_CreateNewUser);

		UserManagement_Create_Edit user = new UserManagement_Create_Edit();

		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		user.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Finance Planner");
		LoginPage login = new LoginPage();

		if (login.performLogin(uname, pwd)) {
			CustomReporter.report(STATUS.PASS, "Finance Planner user is able to login into application");

		} else {
			CustomReporter.report(STATUS.FAIL, "Finance Planner user is NOT able to login into application ");

		}

		Dashboard dash = new Dashboard();
		Navigator nav = new Navigator();
		//nav.traverseMenu_VerifyPageTitle(FinanceDashboard.title, dash.link_financePlanner);


	}
	
	/***
	 * This task performs1.Navigating to Logs Page
	 * 2.Verify Page Content
	 * @author Shwetha S Sep 18, 2019
	 */
   
	@Test(description = "TC21_Admin_Logs_VerifyPageContent",groups = {"UI Only"})
	private void TC21_Admin_Logs_VerifyPageContent() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_pageContent();

	}
	
	
	/***
	  This task performs1.Navigating to Logs Page
	 * 2.Verify Search field
	 * @author Shwetha S Sep 18, 2019
	 */
	
	@Test(description = "TC22_Admin_Logs_Text_Search",groups = {"Func Only"})
	private void TC22_Admin_Logs_Text_Search() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_textSearch("New admin");
	}
	
	

	/***
	  This task performs1.Navigating to Logs Page
	 * 2.Verify for OnDatePicker
	 * @author Shwetha S Sep 18, 2019
	 */
	
	@Test(description = "TC23_Admin_Logs_OnDatePicker",groups = {"Func Only"})
	private void TC23_Admin_Logs_OnDatePicker() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		String date = Util.convertToString("dd/MM/YYYY", new Date());
		log.datePickerOn(date);
	}
	

	/***
	  This task performs1.Navigating to Logs Page
	 * 2.Verify From_And_ TO_datePicker
	 * @author Shwetha S Sep 18, 2019
	 */
	@Test(description = "TC24_Admin-Logs-Verify_For_From_And_ TO_datePicker",groups = {"Func Only"})
	private void TC24_Admin_Logs_Verify_For_From_And_TO_datePicker() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.FromDate();
		log.ToDate();
		
	}
	
	/***
	  This task performs1.Navigating to Logs Page
	  2.Select the Participants
	 *3.Verify the table and page contents
	 * @author Shwetha S Sep 18, 2019
	 */
	
	@Test(description = "TC25_Admin-Logs-Verify_For_Logs_Participants",groups = {"UI and Func"})
	private void TC25_Admin_Logs_Verify_For_Logs_Participants() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_Participants();
	}
	
	
	/***
	  This task performs1.Navigating to Logs Page
	  2.Select the Organisation
	 *3.Verify the table and page contents
	 * @author Shwetha S Sep 24, 2019
	 */
	
	@Test(description = "TC26_Admin_Logs_Verify_For_Logs_Organisation",groups = {"UI Only"})
	private void TC26_Admin_Logs_Verify_For_Logs_Organisation() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_Organisation();
		}

	/***
	  This task performs1.Navigating to Logs Page
	  2.Select the Fms
	 *3.Verify the table and page contents
	 * @author Shwetha S Sep 24, 2019
	 */
	
	@Test(description = "TC27_Admin_Logs_Verify_For_Logs_Fms",groups = {"UI Only"})
	private void TC27_Admin_Logs_Verify_For_Logs_Fms() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_Fms();
		}
	
	
	/***
	  This task performs1.Navigating to Logs Page
	  2.Select the Imail
	 *3.Verify the table and page contents
	 * @author Shwetha S Sep 24, 2019
	 */
	@Test(description = "TC28_Admin_Logs_Verify_For_Logs_Imail",groups = {"UI Only"})
	private void TC28_Admin_Logs_Verify_For_Logs_Imail() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_Imail();
		}
	
	/***
	  This task performs1.Navigating to Logs Page
	  2.Select the Members and page contents
	 *3.Verify the table
	 * @author Shwetha S Oct 3, 2019 
	 */
	
	@Test(description = "TC29_Admin_Logs_Verify_For_Logs_Members",groups = {"UI Only"})
	private void TC29_Admin_Logs_Verify_For_Logs_Members() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_Members();
		}
	 
	

	/***
	  This task performs1.Navigating to Logs Page
	  2.Select the Schedule
	 *3.Verify the table and page contents
	 * @author Shwetha S Oct 3, 2019 
	 */      
	@Test(description = "TC30_Admin_Logs_Verify_For_Logs_Schedule",groups = {"UI Only"})
	private void TC30_Admin_Logs_Verify_For_Logs_Schedule() {
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_Schedule();
		}
	
	
	/***
	  This task performs1.Navigating to Logs Page
	 2.Verify the Export_Selected_Button present and it is working
	 * @author Shwetha S Oct 3, 2019 
	 */
	
	@Test(description = "TC31_Admin_Logs_Verify_For_Export_Selected_Button",groups = {"UI Only"})
	private void TC31_Admin_Logs_Verify_For_Export_Selected_Button(){
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.verify_ExportAll();
		}
	
	/***
	  This task performs
	 1.Navigating to Logs Page
	 2.Verify For Pagination 
	 * @author Shwetha S Oct 3, 2019 
	 */
	@Test(description = "TC32_Admin_Logs_Verify_For_Pagination",groups = {"Func Only"})
	private void TC32_Admin_Logs_Verify_For_Pagination(){
		navigateToLogs().traverseMenu_VerifyPageTitle(Logs.title);
		Logs log=new Logs();
		log.Verify_Pagination();
		}
	
	/***
	  This task performs
	 1.Navigating to Approvals Page
	 2.Verify For Search
	 * @author Shwetha S Oct 3, 2019 
	 */
	@Test(description = "TC33_Admin_Approvals_Verify_For_Search",groups = {"Func Only"})
	private void TC33_Admin_Approvals_Verify_For_Search(){
		navigateToApprovals().traverseMenu_VerifyPageTitle(Approvals.title);
		Approvals app=new Approvals();
		app.verify_text();
		}
	
	/***
	  This task performs
	 1.Navigating to Approvals Page
	 2.Verify For On date picker
	 * @author Shwetha S Oct 3, 2019 
	 */
	@Test(description = "TC34_Admin_Approvals_Verify_For_On_datePicker",groups = {"Func Only"})
	private void TC34_Admin_Approvals_Verify_For_On_datePicker(){
		navigateToApprovals().traverseMenu_VerifyPageTitle(Approvals.title);
		Approvals app=new Approvals();
		String date = Util.convertToString("dd/MM/YYYY", new Date());
		app.datePickerOn(date);
	}
	
	/***
	  This task performs
	 1.Navigating to Logs Page
	 2.Verify For On Icon_pin 
	 * @author Shwetha S Oct 3, 2019 
	 */
	@Test(description = "TC35_Admin_Approvals_Verify_For_Icon_Pin",groups = {"Func Only"})
	private void TC35_Admin_Approvals_Verify_For_Icon_Pin(){
		navigateToApprovals().traverseMenu_VerifyPageTitle(Approvals.title);
		Approvals app=new Approvals();
		app.verify_IconPin();
		}
	/***
	  This task performs
	 1.Navigating to Approvals Page
	 2.Verify For Icon_View
	 * @author Shwetha S Oct 3, 2019 
	 */
	@Test(description = "TC36_Admin_Approvals_Verify_For_Icon_View", groups = {"UI Only"})
	private void TC36_Admin_Approvals_Verify_For_Icon_View(){
		navigateToApprovals().traverseMenu_VerifyPageTitle(Approvals.title);
		Approvals app=new Approvals();
		app.verify_IconView();
		}
	
	/***
	  This task performs
	 1.Navigating to Approvals Page
	 2.Verify For  Pagination
	 * @author Shwetha S Oct 3, 2019 
	 */
	@Test(description = "TC37_Admin_Approvals_Verify_For_Pagination",groups = {"Func Only"})
	private void TC37_Admin_Approvals_Verify_For_Pagination(){   
		navigateToApprovals().traverseMenu_VerifyPageTitle(Approvals.title);
		Approvals app=new Approvals();
		app.Verify_Pagination();
		}


	
	
}
