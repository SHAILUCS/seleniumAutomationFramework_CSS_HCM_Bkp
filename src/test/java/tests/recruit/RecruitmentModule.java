package tests.recruit;

import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.db.ConnectionManager;
import com.db.ContentReader;
import com.mailUtil.MailUtil;

import or.common.Dashboard;
import or.common.Navigator;
import or.common.SideBar_Recruitment;
import or.recruit.ApplicantListing;
import or.recruit.CreateJob;
import or.recruit.CreateNewTask;
import or.recruit.JobListing;
import or.recruit.ManageCabDay;
import or.recruit.ManageGroupInterview;
import or.recruit.RecruitmentDashboard;
import or.recruit.TaskList;

public class RecruitmentModule {

	private void navigateToApplicantListing() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(ApplicantListing.title, rctDsh.link_ApplicantList);
	}

	/**
	 * where is proper comments?
	 *
	 * @author user
	 * 
	 *         Covered:
	 * 
	 * 
	 *         Pending:
	 * 
	 * 
	 *         Depends on:
	 * 
	 */
	@Test(description = "HCM196_Recruit_TC01_RAdmin_ApplicantListingColumnHeader")
	private void HCM196_Recruit_TC01_RAdmin_ApplicantListingColumnHeader() {
		//

		/*
		 * what is this?
		 * 
		 * 
		 * 
		 */

		navigateToApplicantListing();

		ApplicantListing appList = new ApplicantListing();
		appList.verifyColumnHeader();

	}

	/**
	 * @author user
	 * 
	 *         Covered:
	 * 
	 * 
	 *         Pending:
	 * 
	 * 
	 *         Depends on:
	 * 
	 */
	@Test(description = "HCM196_Recruit_TC02_RAdmin_QuickView")
	private void HCM196_Recruit_TC02_RAdmin_QuickView() {

		navigateToApplicantListing();

		ApplicantListing appList = new ApplicantListing();

		appList.verifyQuickView();

	}

	/** I am not able to test this Point beacuse this point not implement */
	@Test(description = "HCM196_Recruit_TC03_RAdmin_SubStage")
	private void HCM196_Recruit_TC03_RAdmin_SubStage() {

		navigateToApplicantListing();

		ApplicantListing appList = new ApplicantListing();

		appList.checkSubStageOnQuickView();

	}

	/*
	 * Not able to test beacuse stages not working
	 */
	@Test(description = "HCM196_Recruit_TC04_RAdmin_Stage1ShowSeekAns")
	public void HCM196_Recruit_TC04_RAdmin_Stage1ShowSeekAns() {

		navigateToApplicantListing();

		ApplicantListing appList = new ApplicantListing();

		appList.Stage1ShowSeekAns();
	}

	@Test(description = "HCM196_Recruit_TC05_RAdmin_ListingDropdown")

	private void HCM196_Recruit_TC05_RAdmin_ListingDropdown() {

		navigateToApplicantListing();

		ApplicantListing appList = new ApplicantListing();

		appList.checkFilterByDropdownFunctionality();

	}

	@Test(description = "HCM198_Recruit_TC01_Recruit_ApplicantListing")
	private void HCM198_Recruit_TC01_Recruit_ApplicantListing() {

		navigateToApplicantListing();

		ApplicantListing appList = new ApplicantListing();

		appList.verifyColumnHeader();
	}

	@Test(description = "HCM198_Recruit_TC02_Recruiter_FlagedoOrDuplicantAppNot")
	private void HCM198_Recruit_TC02_Recruit_FlagedoOrDuplicantAppNot() {

		navigateToApplicantListing();

		ApplicantListing appList = new ApplicantListing();

	}

	@Test(description = "HCM198_Recruit_TC03_Recruiter_QuickView")
	private void HCM198_Recruit_TC03_Recruiter_QuickView() {

		navigateToApplicantListing();

		ApplicantListing applist = new ApplicantListing();

		applist.verifyQuickView();
	}

	@Test(description = "HCM198_Recruit_TC05_Recruiter_PendingSubStage")
	private void HCM198_Recruit_TC08_Recruit_PendingSubStage() {

		navigateToApplicantListing();

		ApplicantListing applist = new ApplicantListing();

		applist.checkSubStageOnQuickView();
	}

	@Test(description = "HCM198_Recruit_TC07_Recruiter_FilterListingDropdown")
	private void HCM198_Recruit_TC07_Recruiter_FilterListingDropdown() {

		navigateToApplicantListing();

		ApplicantListing applist = new ApplicantListing();

		applist.checkFilterByDropdownFunctionality();

	}

	/* Start scripting on jira 188 */

	@Test(description = "HCM188_Recruit_TC01_RecruiterAdmin_The admin is able to select the job type, "
			+ "job category, job sub category,employment type and salary range from a list of predefined values on the create job screen")
	private void HCM188_Recruit_TC01_RecruiterAdmin_CreateJobSelectDataOnDropdown() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();
		createJob.fillJobDetails("NDIS", "Cat 1", "Cat 44", "Manager", "Full Time", "$30K-$40K", null, null,
				"melbourne", "300000000", "Test@gmail.com", "www.test.com");

	}

	@Test(description = "HCM188_Recruit_TC02_RecruiterAdmin_The list of pre-defined values in the drop-downs will be as follows,")
	private void HCM188_Recruit_TC02_RecruiterAdmin_VerifyJobTypeJobCategoryJobSubCategoryEmploymentTypeSalaryRange() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();
		createJob.verify_DropdownValues();

	}

	@Test(description = "HCM188_Recruit_TC03_RecruiterAdmin_CheckSalaryCheckBoxFunction")
	private void HCM188_Recruit_TC03_RecruiterAdmin_CheckSalaryCheckBoxFunction() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.checkBox_SalaryPublish();
	}

	@Test(description = "HCM188_Recruit_TC04_RecruiterAdmin_VerifyJobDetailsOnPreviewpost")
	private void HCM188_Recruit_TC04_RecruiterAdmin_VerifyJobDetailsOnPreviewpost() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.verifyJobDetailsOnPreviewpost();
	}

	@Test(description = "HCM188_Recruit_TC07_RecruiterAdminCreateJob_CheckALlRequiredValidation")
	private void HCM188_Recruit_TC07_RecruiterAdminCreateJob_CheckALlRequiredValidation() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.verify_ClickCheckAllValidation();
	}

	@Test(description = "HCM188_Recruit_TC14_RecruiterAdminJob_ResetFunctionality")
	private void HCM188_Recruit_TC14_RecruiterAdminJob_ResetFunctionality() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.job_ResetFunctionality("NDIS", "Cat 1", "Cat 44", "Manager", "Full Time", "$30K-$40K", null,
				null, "melbourne", "300000000", "Test@gmail.com", "www.test.com");

	}

	@Test(description = "HCM188_Recruit_TC15_RecruiterAdminJob_SaveAsDraftFunctionality")
	private void HCM188_Recruit_TC15_RecruiterAdminJob_SaveAsDraftFunctionality() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.job_SaveAsDraft("NDIS", "Cat 1", "Cat 44", "Manager", "Full Time", "$30K-$40K", null, null,
				"melbourne", "300000000", "Test@gmail.com", "www.test.com");
	}

	@Test(description = "HCM188_Recruit_TC16_RecruiterJobVerify_JobDetailsOnPreviewAndJobPost")
	private void HCM188_Recruit_TC16_RecruiterJobVerify_JobDetailsOnPreviewAndJobPost() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.job_PreviewJobPost("NDIS", "Cat 1", "Cat 44", "Manager", "Full Time", "$30K-$40K", null,
				null, "melbourne", "300000000", "Test@gmail.com", "www.test.com");

	}

	@Test(description = "HCM188_Recruit_TC09_RecruiterJobVerify_DateRecurringCheckBox")
	private void HCM188_Recruit_TC09_RecruiterJobVerify_DateRecurringCheckBox() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.job_RecurringCheckBox();
	}

	@Test(description = "HCM188_Recruit_TC013_RecruiterJobVerify_TextLayOutSection")
	private void HCM188_Recruit_TC013_RecruiterJobVerify_TextLayOutSection() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.verify_JobTextLayoutContent();

	}

	@Test(description = "HCM188_Recruit_TC08_RecruiterJobVerify_ValidationStartDateOrEndDate")
	private void HCM188_Recruit_TC08_RecruiterJobVerify_ValidationStartDateOrEndDate() {

		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		RecruitmentDashboard rctDsh = new RecruitmentDashboard();
		JobListing JobList = new JobListing();

		nav.traverseMenu_VerifyPageTitle(RecruitmentDashboard.title, d.link_Recruitment);
		nav.traverseMenu_VerifyPageTitle(JobListing.title, rctDsh.link_Job);
		nav.traverseMenu_VerifyPageTitle(CreateJob.title, JobList.link_CreateJob);

		CreateJob createJob = new CreateJob();

		createJob.verifyValidation_StartDateOrEnddate();

	}

	/**
	 * This test will navigate to Recruitment >> Recruiter Management Page >>
	 * Disable Recruiter popup UI
	 * 
	 * @author shailendra Sep 18, 2019
	 */

	// @Test=Annotation for testng
	@Test(description = "HCM_Recruit_TC01_RecruiterCreateTask_CheckMaxApplicantValidation")
	private void HCM_Recruit_TC01_RecruiterCreateTask_CheckMaxApplicantValidation() {

		// Create navigator class object
		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.openCreateNewTaskPopup();

		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.checkMaxApplicantValidation();

	}

	@Test(description = "HCM_Recruit_TC02_RecruiterCreateTask_VerifyUI")
	private void HCM_Recruit_TC02_RecruiterCreateTask_VerifyUI() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.openCreateNewTaskPopup();

		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.createTaskVerifyUI();

	}

	@Test(description = "HCM_Recruit_TC03_RecruiterCreateTask_SearchRecruiter")
	private void HCM_Recruit_TC03_RecruiterCreateTask_SearchRecruiter() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.openCreateNewTaskPopup();

		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.createTaskSearchRecruiter("gourav3,shailendra_R4");

	}

	@Test(description = "HCM_Recruit_TC04_RecruiterCreateTask_CreateNewTask")
	private void HCM_Recruit_TC04_RecruiterCreateTask_CreateNewTask() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.openCreateNewTaskPopup();

		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.createTask("2 Oct Automate Task", "None", null, "09:30 PM", "10:30 PM",
				"HCM Training Facility - Training Room", "10", "gourav3", "gourav3", "This task created bu automate",
				"er");

	}

	@Test(description = "HCM_Recruit_TC05_RecruiterCreateTask_DateTimeDuration")
	private void HCM_Recruit_TC05_RecruiterCreateTask_DateTimeDuration() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.openCreateNewTaskPopup();

		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.createTask("2 Oct Automate Task", "None", null, null, null,
				"HCM Training Facility - Training Room", "10", "gourav3", "gourav3", "This task created bu automate",
				"er");
	}

	@Test(description = "HCM_Recruit_TC06_RecruiteTask_VerifyUI")
	private void HCM_Recruit_TC06_RecruiteTask_VerifyUI() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.verifyTaskUI();

	}

	@Test(description = "Recruit_TC07_TaskList_verify_SearchFunctionlity")
	private void Recruit_TC07_TaskList_verify_SearchFunctionlity() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		CustomReporter.createNode("Search By Task Name");
		taskList.verifySearch("Task");

		CustomReporter.createNode("Search By Recruiter Name");
		taskList.verifySearch("R4");
	}

	@Test(description = "Recruit_TC08_TaskList_CheckFilter")
	private void Recruit_TC08_TaskList_CheckFilter() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.checkFilter();
	}

	@Test(description = "Recruit_TC09_TaskList_ViewTaskPopUp")
	private void Recruit_TC09_TaskList_ViewTaskPopUp() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.verifyViewTaskPopUp();

	}

	@Test(description = "Recruit_TC010_TaskList_CheckCompleteATask",groups = {"Functionality Only"})
	private void Recruit_TC010_TaskList_CheckCompleteATask() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Comp" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.verifyCompleteTask(taskName, "None", todayDate, null, null, "HCM Training Facility - Training Room",
				"10", recruiter, recruiter, "Test", "Lilas Soall");

	}

	@Test(description = "Recruit_TC011_TaskList_CheckArchiveTask",groups = {"Functionality Only"})
	private void Recruit_TC011_TaskList_CheckArchiveTask() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.verifyCompleteTask(taskName, "None", todayDate, null, null, "HCM Training Facility - Training Room",
				"10", recruiter, recruiter, "Test", "Lilas Soall");

	}

	@Test(description = "Recruit_TC012_TaskList_CheckManageGroupDayTask",groups = {"Functionality Only"})
	private void Recruit_TC012_TaskList_CheckManageGroupDayTask() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		// DB code

		// DB code

		String taskName = "GI" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "Group Interview", todayDate, null, null, "HCM Training Facility - Training Room",
				"10", recruiter, recruiter, "Test", "Marice Jansik");

		nav.traverseMenu_VerifyPageTitle(ManageGroupInterview.title, sr.link_ManageGroupInterview);

		ManageGroupInterview manageGroupInterview = new ManageGroupInterview();

		manageGroupInterview.verifyTaskIsPresentOnPage(taskName);

	}

	@Test(description = "Recruit_TC013_TaskPopUp_AttachAndRemoveApplicant",groups = {"Functionality Only"})
	private void Recruit_TC013_TaskPopUp_AttachAndRemoveApplicant() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.openCreateNewTaskPopup();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";

		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.fillTaskDetails(taskName, "None", todayDate, null, null, "HCM Training Facility - Training Room",
				"10", recruiter, recruiter, "Test", "Lilas Soall,Maryanne,Beitris,Cesar,Vanny");

		createNewTask.removeApplicants("Maryanne,Beitris,Cesar,Vanny");
	}

	@Test(description = "Recruit_TC014_TaskList_ManageCabTask")
	private void Recruit_TC014_TaskList_ManageCabTask() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "cabDayStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "CAB day", todayDate, null, null, "HCM Training Facility - Training Room", "10",
				recruiter, recruiter, "Test", applicantName);

		nav.traverseMenu_VerifyPageTitle(ManageCabDay.title, sr.link_CabDay);

		ManageCabDay manageCabDay = new ManageCabDay();

		manageCabDay.verifyTaskIsPresentOnPage(taskName);

	}

	@Test(description = "Recruit_TC015_CreateTaskPopUP_Add_RemovePrimaryRecruiter",groups = {"Functionality Only"})
	private void Recruit_TC015_CreateTaskPopUP_Add_RemovePrimaryRecruiter() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		taskList.openCreateNewTaskPopup();

		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.check_AddRemovePrimaryRecruiter_OnCreateTaskPopup("gourav3", "R4");
	}

	@Test(description = "Recruit_TC016_EditTaskPopUP_Add_RemovePrimaryRecruiter",groups = {"Functionality Only"})
	private void Recruit_TC016_EditTaskPopUP_Add_RemovePrimaryRecruiter() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		String taskName = "TC016_" + Util.getTimeStamp_InMilliSec();
		String recruiter1 = "gourav3";
		String recruiter2 = "R4";

		TaskList taskList = new TaskList();
		taskList.check_AddRemovePrimaryRecruiterEditTask(taskName, "None", null, null, null,
				"HCM Training Facility - Training Room", "10", recruiter1, recruiter1, "Test", "Jo", recruiter2);

	}

	@Test(description = "Recruit_TC017_EditTaskPopUP_Add_RemoveApplicants",groups = {"Functionality Only"})
	private void Recruit_TC017_EditTaskPopUP_Add_RemoveApplicants() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String recruiter = "gourav3";

		CreateNewTask createNewTask = new CreateNewTask();

		taskList.createTask(taskName, "None", null, null, null, "HCM Training Facility - Training Room", "10",
				recruiter, recruiter, "Test", "Lilas Soall,Maryanne,Beitris,Cesar,Vanny");

		taskList.verifySearch(taskName);

		taskList.clickOnEdit_ViewLink(2);

		createNewTask.removeApplicants("Maryanne,Beitris,Cesar,Vanny");

		createNewTask.searchAndAddApplicants("Josepha Graffham,Cissy Mickleburgh,Emmeline Boundley");

	}

	@Test(description = "Recruiter_TC018_CreateTask_CheckApplicantFor_1ReviewOnlineApplication",groups = {"Functionality Only"})
	private void Recruiter_TC018_CreateTask_CheckApplicantFor_1ReviewOnlineApplication() {

		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "reviewFirstStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "Review Online Application", todayDate, null, null,
				"HCM Training Facility - Training Room", "10", recruiter, recruiter, "Test", applicantName);

	}

	@Test(description = "Recruiter_TC19_CreateTask_CheckApplicantFor_2PhoneInterview",groups = {"Functionality Only"})
	private void Recruiter_TC19_CreateTask_CheckApplicantFor_2PhoneInterview() {

		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "phoneInterviewStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "Phone interview", todayDate, null, null, "HCM Training Facility - Training Room",
				"10", recruiter, recruiter, "Test", applicantName);

	}

	@Test(description = "Recruiter_TC20_CreateTask_CheckApplicantFor_3Group_Interview",groups = {"Functionality Only"})
	private void Recruiter_TC20_CreateTask_CheckApplicantFor_3Group_Interview() {

		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "groupInterviewStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "Group Interview", todayDate, null, null, "HCM Training Facility - Training Room",
				"10", recruiter, recruiter, "Test", applicantName);

	}

	@Test(description = "Recruiter_TC21_CreateTask_CheckApplicantFor_4Mandatory_Documentation",groups = {"Functionality Only"})
	private void Recruiter_TC21_CreateTask_CheckApplicantFor_4Mandatory_Documentation() {

		ConnectionManager con = new ConnectionManager();

		String query = ContentReader
				.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "mandatoryDocumentStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "Mandatory Documentation", todayDate, null, null,
				"HCM Training Facility - Training Room", "10", recruiter, recruiter, "Test", applicantName);

	}

	@Test(description = "Recruiter_TC22_CreateTask_CheckApplicantFor_5Reference_Checks",groups = {"Functionality Only"})
	private void Recruiter_TC22_CreateTask_CheckApplicantFor_5Reference_Checks() {

		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "referenceCheckStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "Reference Checks", todayDate, null, null,
				"HCM Training Facility - Training Room", "10", recruiter, recruiter, "Test", applicantName);

	}

	@Test(description = "Recruiter_TC23_CreateTask_CheckApplicantFor_6CAB_day",groups = {"Functionality Only"})
	private void Recruiter_TC23_CreateTask_CheckApplicantFor_6CAB_day() {

		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "cabDayStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "CAB day", todayDate, null, null, "HCM Training Facility - Training Room", "10",
				recruiter, recruiter, "Test", applicantName);

	}

	@Test(description = "Recruiter_TC24_CreateTask_CheckApplicantFor_7HiringStage",groups = {"Functionality Only"})
	private void Recruiter_TC24_CreateTask_CheckApplicantFor_7HiringStage() {

		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "hiringStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "Hiring Stage", todayDate, null, null, "HCM Training Facility - Training Room",
				"10", recruiter, recruiter, "Test", applicantName);

	}

	@Test(description = "Recruiter_TC25_CreateTask_CheckApplicantFor_8NoneStage",groups = {"Functionality Only"})
	private void Recruiter_TC25_CreateTask_CheckApplicantFor_8NoneStage() {

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "None", todayDate, null, null, "HCM Training Facility - Training Room", "10",
				recruiter, recruiter, "Test", "Trip Rhodes");

	}
	
	
    private static String getStartAndEndTimeForTask() {

    	// GETTING SYS TIME
    	String HR = Util.convertToString("hh", new Date());
    	String MIN = Util.convertToString("mm", new Date());
    	String AMPM = Util.convertToString("a", new Date());
    	
    	// Assigning them to x, y and am to perform logics
    	int x = Integer.parseInt(HR);
    	int y = Integer.parseInt(MIN);
    	String am = AMPM;
    	
    	String amPm = " AM";
    	
    	if (y>=45) {
    		x = x+1;
    		y=00;
    	} else if(y>=30 && y<45){
			y=45;
		}else if (y>=15 && y<30) {
			y=30;
		}else if (y>=0 && y<15) {
			y=15;
		}else {
			
		}
    	
    	
    	if(x > 12) {
    		x = x - 12;
        	// AM PM Logic
    		if(am.equalsIgnoreCase("am")) {
    			amPm = " PM";
    		}
    	}

    	// HR MIN Logic, to append & prepend 0 in hr and min
    	String hr = (x<9)?("0"+x):x+"";
    	String min = (y+"0").substring(0,2);
    	
    	return hr+":"+min+amPm;
	}
			

	@Test(description = "Recruiter_TC26_CreateTask_CheckMail_GroupInterview",groups = {"Functionality Only"})
	private void Recruiter_TC26_CreateTask_CheckMail_GroupInterview() {
		
		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "groupInterviewStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0).split(" ")[0];

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String date = Util.convertToString("dd/MM/YYYY", cal.getTime());
		
		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String recruiter = "gourav3";
		String startTime = getStartAndEndTimeForTask();
		taskList.createTask(taskName, "Group Interview", date, startTime, startTime, "HCM Training Facility - Training Room", "10",
				recruiter, recruiter, "Test", applicantName);

		MailUtil.readMail_SpamFolder(60, applicantName, date, startTime);
		
		taskList.searchAndArchiveTask(taskName);
	}
	
	@Test(description = "Recruiter_TC27_CreateTask_CheckMail_Cab_DayStage",groups = {"Functionality Only"})
	private void Recruiter_TC27_CreateTask_CheckMail_Cab_DayStage() {
		
		ConnectionManager con = new ConnectionManager();

		String query = ContentReader.readLineByLineJava8(Constant.getDbQueriesFolderPath() + "cabDayStage.sql");

		con.executeQuery(query);

		String applicantName = con.getColumnValues("label").get(0);

		con.closeConnection();

		Navigator nav = new Navigator();

		// Creating Object
		Dashboard d = new Dashboard();

		// Creating Object
		SideBar_Recruitment sr = new SideBar_Recruitment();

		nav.traverseMenu_VerifyPageTitle(TaskList.title, d.link_Recruitment, sr.link_TasksList);

		TaskList taskList = new TaskList();

		String taskName = "Arc" + Util.getTimeStamp_InMilliSec();
		String todayDate = Util.convertToString("dd/MM/YYYY", new Date());
		String recruiter = "gourav3";
		taskList.createTask(taskName, "CAB day", todayDate, null, null, "HCM Training Facility - Training Room", "10",
				recruiter, recruiter, "Test", applicantName);

		MailUtil.readMail_SpamFolder(60, applicantName);
		
		taskList.searchAndArchiveTask(taskName);
		
	}
	/*
	 * @Test(description = "It Will Come In Report") private void abcd() { //
	 * Creating Object Navigator nav= new Navigator();
	 * 
	 * // Creating Object Dashboard d = new Dashboard();
	 * 
	 * // Creating Object SideBar_Recruitment sr=new SideBar_Recruitment();
	 * 
	 * // Normal way
	 * 
	 * // Calling method and page objects
	 * nav.traverseMenu_VerifyPageTitle("/recruitment/dashboard",
	 * d.link_Recruitment);
	 * 
	 * 
	 * // Calling method and page objects
	 * nav.traverseMenu_VerifyPageTitle("/recruitment/action/task",
	 * sr.link_TasksList);
	 * 
	 * 
	 * // Alternate way nav.traverseMenu_VerifyPageTitle("/recruitment/action/task",
	 * d.link_Recruitment, sr.link_TasksList);
	 */

}
