package tests.recruit;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.configData_Util.Util;

import or.common.Dashboard;
import or.common.LoginPage;
import or.common.Navigator;
import or.common.SideBar_Recruitment;
import or.recruit.ApplicantInfo;
import or.recruit.ApplicantListing;
import or.recruit.RecruiterDetails;
import or.recruit.RecruiterManagement;
import or.recruit.RecruitmentDashboard;
import tests.admin.AdminModule;

public class Recruitment_HCM166 {
	
	private final String Recruit_Admin = "Recruit Admin";

	/**
	 * Takes care of navigation to Recruiter Maagement Page
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	private void navigate_To_RecruiterManagementPage() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rd = new RecruitmentDashboard();

		nav.traverseMenu_VerifyPageTitle(RecruiterManagement.title, d.link_Recruitment, rd.link_Recruiter);
	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page and will
	 * verify the page content
	 * 
	 * @author shailendra Sep 4, 2019
	 */
	@Test(description = "HCM166_Recruit_TC01_Recruiter Management_Verify Page Content"
			, groups = {"UI Only"})
	private void HCM166_Recruit_TC01_RecruiterManagement_VerifyPageContent() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		rm.verifyUI();

	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page and will
	 * verify the Filter dropdown
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	@Test(description = "HCM166_Recruit_TC02_Recruiter Management_Verify Filter Dropdown func"
			, groups = {"UI and Func"})
	private void HCM166_Recruit_TC02_RecruiterManagement_VerifyFilterDropdownFunc() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		rm.verifyFilterDropDownFunc();

	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page and will
	 * verify the Quick View content
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	@Test(description = "HCM166_Recruit_TC03_Recruiter Management_Verify Quick View content"
			, groups = {"UI Only"})
	private void HCM166_Recruit_TC03_RecruiterManagement_VerifyQuickViewContent() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		rm.verifyQuickViewContent();

	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page and will
	 * verify the Text Search func
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	@Test(description = "HCM166_Recruit_TC04_Recruiter Management_Verify Text Search func"
			, groups = {"UI and Func"})
	private void HCM166_Recruit_TC04_RecruiterManagement_VerifyTextSearch() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		rm.performTextSearch("shailendra");
	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	@Test(description = "HCM166_Recruit_TC06_Recruiter Management_Verify Allocated Preferred Recruitment Areas"
			, groups = {"Func Only"})
	private void HCM166_Recruit_TC06_RecruiterManagement_VerifyAllocatedPreferredRecruitmentAreas() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		rm.verifyAllocatedPreferredRecruitmentAreas_OnQuickView();
	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >>
	 * Recruiter Details Page UI
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	@Test(description = "HCM166_Recruit_TC07_Recruiter Management_Verify Recruiter Details Page Content"
			, groups = {"UI Only"})
	private void HCM166_Recruit_TC07_RecruiterManagement_VerifyRecruiterDetailsContent() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		rm.openRecruiterDetailsPage("shailendra_r4");

		RecruiterDetails rd = new RecruiterDetails();
		rd.verify_UI();

	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >>
	 * Recruiter Details >> Create Task
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	@Test(description = "HCM166_Recruit_TC08_Recruiter Management_Create Task"
			, groups = {"Func Only"})
	private void HCM166_Recruit_TC08_RecruiterManagement_CreateTask() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		rm.openRecruiterDetailsPage("shailendra_r4");

		RecruiterDetails rd = new RecruiterDetails();

		String taskName = "Task" + Util.getTimeStamp_InMilliSec();

		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());

		rd.createTask(taskName, "None", todayDate, null, null, "At Desk", "10", "R5", "R5", "Task Notes " + taskName,
				"Jenifer Tansly, Jo");
	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >>
	 * Recruiter Details >> Calendar Number Links
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	@Test(description = "HCM166_Recruit_TC09_Recruiter Management_Current Task Schedules_Calendar Num Links"
			, groups = {"Func Only"})
	private void HCM166_Recruit_TC09_RecruiterManagement_CurrentTaskSchedules_CalendarNumLinks() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		
		rm.openRecruiterDetailsPage("shailendra_r4");

		RecruiterDetails rd = new RecruiterDetails();

		String taskName = "Task" + Util.getTimeStamp_InMilliSec();

		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());

		rd.createTask(taskName, "None", todayDate, null, null, "At Desk", "10", null, "R4", "Task Notes " + taskName,
				"Jenifer Tansly, Jo");

		rd.verifyCalendarNumLinksFunc();
	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >>
	 * Disable Recruiter popup UI
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	@Test(description = "HCM166_Recruit_TC10_Recruiter Management_Current Task Schedules_Calendar Num Links"
			, groups = {"Func Only"})
	private void HCM166_Recruit_TC10_RecruiterManagement_CurrentTaskSchedules_DisableRecruiterPopupUI() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();

		rm.verifyDisablePopupUI("shailendra_r4");

	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >> Add
	 * Recruiter Popup UI
	 * 
	 * @author shailendra Sep 23, 2019
	 */
	@Test(description = "Recruit_TC11_Recruiter Management_Add Recruiter_Verify UI"
			, groups = {"UI Only"})
	private void Recruit_TC11_RecruiterManagement_AddRecruiter_VerifyUI() {
		navigate_To_RecruiterManagementPage();
		
		RecruiterManagement rm = new RecruiterManagement();

		rm.verifyAddRecruiterPopupUI("Auto");

	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >> Add a
	 * Recruiter
	 * 
	 * @author shailendra Sep 23, 2019
	 */
	@Test(description = "Recruit_TC12_Recruiter Management_Add a New Recruiter"
			, groups = {"Func Only"})
	private void Recruit_TC12_RecruiterManagement_AddRecruiter() {

		AdminModule adminModule = new AdminModule();
		
		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		LoginPage log = adminModule.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");

		log.performIntelligentLogin(Recruit_Admin);
		
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		
		Map<String, String> map = rm.addRecruiter_GetRecruiterFullNameAndId(uname);

		rm.performTextSearch(map.get("name").split(" ")[0]);
	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >> Add a
	 * fresh Recruiter >> Disable Recruiter
	 * 
	 * @author shailendra Sep 23, 2019
	 */
	@Test(description = "Recruit_TC13_Recruiter Management_Disable Recruiter_No Participants"
			, groups = {"Func Only"})
	private void Recruit_TC13_RecruiterManagement_DisableRecruiter_NoParticipants() {

		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();

		rm.verifyDisableRecruiter_NoParticipants("Auto1", "Relevant Notes");
	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >>
	 * Disable Recruiter
	 * 
	 * @author shailendra Oct 2, 2019
	 */
	@Test(description = "Recruit_TC14_Recruiter Management_Disable Recruiter_Attach Participants_Auto Allocation"
			, groups = {"Func Only"})
	private void Recruit_TC14_RecruiterManagement_DisableRecruiter_AttachParticipants_AutoAllocation() {
		
		AdminModule adminModule = new AdminModule();
		
		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";

		LoginPage log = adminModule.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");

		log.performIntelligentLogin(Recruit_Admin);
		
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();

		//rm.verifyDisableRecruiter_AttachParticipants_AutoAllocation("Auto1", "Relevant Notes");
		
		Map<String, String> map = rm.addRecruiter_GetRecruiterFullNameAndId(uname);

		Navigator nav = new Navigator();
		
		SideBar_Recruitment s = new SideBar_Recruitment();
		
		nav.traverseMenu_VerifyPageTitle(ApplicantListing.title, s.link_ApplicantsList);
		
		ApplicantListing appList = new ApplicantListing();
		
		appList.navigateToApplicantInformation();
		
		ApplicantInfo appInfo = new ApplicantInfo();
		
		appInfo.changeRecruiter(map.get("name").split(" ")[0]);
		
		nav.traverseMenu_VerifyPageTitle(RecruiterManagement.title, s.link_Recruiter);
		
		rm.performTextSearch(map.get("name").split(" ")[0]);
		
		rm.openQuickView();
		
		rm.openDisableRecruiterPopup();
		
		rm.disableRecruiter_AutoAllocation("Relevant Notes");
	}

	/**
	 * This test will add a new recruiter >> Attach an applicant to that Recruiter
	 * >> Disable the Recruiter By Allocating custom Recruiter
	 * 
	 * @author shailendra Oct 2, 2019
	 */
	@Test(description = "Recruit_TC15_Recruiter Management_Disable Recruiter_Attach Participants_Custom Allocation"
			, groups = {"Func Only"})
	private void Recruit_TC15_RecruiterManagement_DisableRecruiter_AttachParticipants_CustomAllocation() {

		AdminModule adminModule = new AdminModule();
		
		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";
		String newRecruiter = "shailendra";
		String relevantNotes = "Relevant Notes";

		LoginPage log = adminModule.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");

		log.performIntelligentLogin(Recruit_Admin);
		
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		
		Map<String, String> map = rm.addRecruiter_GetRecruiterFullNameAndId(uname);


		//rm.verifyDisableRecruiter_AttachParticipants_CustomAllocation("Auto1", "shailendra", "Relevant Notes");
		
		Navigator nav = new Navigator();
		
		SideBar_Recruitment s = new SideBar_Recruitment();
		
		nav.traverseMenu_VerifyPageTitle(ApplicantListing.title, s.link_ApplicantsList);
		
		ApplicantListing appList = new ApplicantListing();
		
		appList.navigateToApplicantInformation();
		
		ApplicantInfo appInfo = new ApplicantInfo();
		
		appInfo.changeRecruiter(map.get("name").split(" ")[0]);
		
		nav.traverseMenu_VerifyPageTitle(RecruiterManagement.title, s.link_Recruiter);
		
		rm.performTextSearch(map.get("name").split(" ")[0]);
		
		rm.openQuickView();
		
		rm.openDisableRecruiterPopup();
		
		rm.disableRecruiter_CustomAllocation(newRecruiter, relevantNotes);
	}
	
	/**
	 * This test will add a new recruiter >> Attach an applicant to that Recruiter
	 * >> Go to Recruiter Details Page
	 * >> Disable the Recruiter By Auto Allocating Recruiter
	 * 
	 * @author shailendra Oct 3, 2019
	 */
	@Test(description = "Recruit_TC16_Recruiter Details_Disable Recruiter_Attach Participants_Custom Allocation"
			, groups = {"Func Only"})
	private void Recruit_TC16_RecruiterDetails_DisableRecruiter_AttachParticipants_CustomAllocation() {
		AdminModule adminModule = new AdminModule();
		
		String uname = "Auto" + Util.getTimeStamp_InMilliSec();
		String pwd = "Test@123";
		String newRecruiter = "shailendra";
		String relevantNotes = "Relevant Notes";

		LoginPage log = adminModule.createNewUser(uname, "Last", "Automation", "Marketing", "3123456789",
				Constant.TEST_TEAM_DEVELOPER_EMAIL, uname, pwd, "Recruitment");

		log.performIntelligentLogin(Recruit_Admin);
		
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		
		Map<String, String> map = rm.addRecruiter_GetRecruiterFullNameAndId(uname);

		Navigator nav = new Navigator();
		
		SideBar_Recruitment s = new SideBar_Recruitment();
		
		nav.traverseMenu_VerifyPageTitle(ApplicantListing.title, s.link_ApplicantsList);
		
		ApplicantListing appList = new ApplicantListing();
		
		appList.navigateToApplicantInformation();
		
		ApplicantInfo appInfo = new ApplicantInfo();
		
		appInfo.changeRecruiter(map.get("name").split(" ")[0]);
		
		nav.traverseMenu_VerifyPageTitle(RecruiterManagement.title, s.link_Recruiter);
		
		rm.performTextSearch(map.get("name").split(" ")[0]);
		
		rm.openQuickView();
		
		rm.navigateToRecruiterDetails();
		
		RecruiterDetails rd = new RecruiterDetails();
		
		rd.openDisableRecruiterPopup();
		
		rm.disableRecruiter_CustomAllocation(newRecruiter, relevantNotes);
	}
	
	/**
	 * This test will navigate to Recruiter Details page and will create a task and
	 * open its quick view to verify the UI objects
	 * 
	 * @author shailendra Oct 29, 2019
	 */
	@Test(description = "Recruit_TC17_Recruiter Details_Task Detail_Quick View_Verify UI"
			, groups = {"UI Only"})
	private void Recruit_TC17_RecruiterDetails_TaskDetail_QuickView_VerifyUI() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		rm.openRecruiterDetailsPage("shailendra_r4");

		RecruiterDetails rd = new RecruiterDetails();
		
		String taskName = "Task" + Util.getTimeStamp_InMilliSec();

		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());

		rd.createTask(taskName, "None", todayDate, null, null, "At Desk", "10", null, "R4", "Task Notes" + taskName,
				"Jo");
		
		rd.verifyTaskQuickViewUI();
		
	}
	
	/**
	 * This test will navigate to Recruiter Details page
	 * Then it will create a new Task, and will verify that the provided details are what displayed on popup
	 * 
	 *@author shailendra Oct 30, 2019 
	 */
	@Test(description = "Recruit_TC18_Recruiter Details_Verify Saved Task On View Popup"
			, groups = {"UI and Func"})
	private void Recruit_TC18_RecruiterDetails_VerifySavedTaskOnViewPopup() {
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		
		String primaryRecruiter = "shailendra_R4";
		
		rm.openRecruiterDetailsPage(primaryRecruiter);

		RecruiterDetails rd = new RecruiterDetails();
		
		String taskName = "Task" + Util.getTimeStamp_InMilliSec();
		String stage = "None";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String date = Util.convertToString("dd/MM/YYYY", cal.getTime());
		String fromTime = "12:00 AM";
		String toTime = "12:30 AM";
		String location = "At Desk";
		String maxApplicant = "10";
		
		String taskNotes = "Task Notes"+taskName;
		String commaSeparatedMultipleApplicants = "Jo";
		
		rd.createTask(taskName, stage, date, fromTime, toTime, location, maxApplicant, null, primaryRecruiter, taskNotes, commaSeparatedMultipleApplicants);
		
		rd.verifyTaskView_EditPopup(taskName, stage, date, fromTime, toTime, location, maxApplicant, primaryRecruiter, primaryRecruiter, taskNotes, commaSeparatedMultipleApplicants);
		
	}
	
	
	/**
	 * This test will navigate to Recruiter Details page
	 * Then it will create a new Task, and will verify that the provided details are what displayed on popup
	 * 
	 * @author shailendra Nov 1, 2019
	 */
	@Test(description = "Recruit_TC19_Recruiter Details_Complete A Task"
			, groups = {"Func Only"})
	private void Recruit_TC19_RecruiterDetails_CompleteATask() {
		
		navigate_To_RecruiterManagementPage();

		RecruiterManagement rm = new RecruiterManagement();
		
		String primaryRecruiter = "shailendra_R4";
		
		rm.openRecruiterDetailsPage(primaryRecruiter);

		RecruiterDetails rd = new RecruiterDetails();
		
		String taskName = "Task" + Util.getTimeStamp_InMilliSec();
		String stage = "None";
		Calendar cal = Calendar.getInstance();
		String date = Util.convertToString("dd/MM/YYYY", cal.getTime());
		String fromTime = "12:00 AM";
		String toTime = "12:00 AM";
		String location = "At Desk";
		String maxApplicant = "10";
		
		String taskNotes = "Task Notes"+taskName;
		String commaSeparatedMultipleApplicants = "Jo";
		
		rd.createTask(taskName, stage, date, fromTime, toTime, location, maxApplicant, null, primaryRecruiter, taskNotes, commaSeparatedMultipleApplicants);
		
		// As a precaution adding one minute wait, so that the task is started, even if we have created this task at exactly 12:00 AM
		//com.wait(60);
		
		rd.completeTask(taskName);
	}
	
}