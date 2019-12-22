package or.recruit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class RecruiterDetails {
	public static final String title = "recruitment/staff_details";
	SeleniumMethods com;
	HCMCommon comm;

	public RecruiterDetails() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//h4[not(@class)]/b")
	private WebElement label_RecruiterName;

	@FindBy(xpath = "//h4[.='Task Detail']")
	private WebElement label_TaskDetail;

	@FindBy(xpath = "//a[.='New Task ']")
	private WebElement button_NewTask;

	@FindBy(xpath = "//h4[.='Applicant Status:']")
	private WebElement label_ApplicantStatus;

	@FindBy(xpath = "//h4[.='Successful Applicants:']")
	private WebElement label_SuccessfulApplicants;

	@FindBy(xpath = "//b[.='Current Task Schedules']")
	private WebElement label_CurrentTaskSchedules;

	@FindBy(xpath = "//b[.='Task Stage:']")
	private WebElement label_TaskStage;

	@FindBy(xpath = "//button[.='View All Task']")
	private WebElement button_ViewAllTask;

	@FindBy(xpath = "//span[.='Disable Recruiter']")
	private WebElement link_DisableRecruiter;

	@FindBy(xpath = "//div[@class='row'][contains(.,'HCMGR-Id')]")
	private WebElement content_RecruiterDetails;

	@FindBy(xpath = "(//div[contains(@class,'calendar')]//button//div[contains(@class,'task')])[1]")
	private WebElement button_CalendarNumber;

	@FindBy(xpath = "//b[.='Applicant Name:']")
	private WebElement data_ApplicantName;

	@FindBy(xpath = "//b[.='Task Notes:']")
	private WebElement data_TaskNotes;

	@FindBy(xpath = "//b[.='Date:']")
	private WebElement data_Date;

	@FindBy(xpath = "//span[@class='sbTsk_li'][contains(.,'View')]")
	private WebElement link_View;

	@FindBy(xpath = "//span[@class='sbTsk_li'][contains(.,'Complete')]")
	private WebElement link_Complete;

	@FindBy(xpath = "//span[@class='sbTsk_li'][contains(.,'Archive')]")
	private WebElement link_Archive;

	/**
	 * Verifies the page UI Objects
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	public void verify_UI() {
		CustomReporter.createNode("Section verification");
		com.isElementPresent(label_RecruiterName, "label_RecruiterName");
		com.isElementPresent(label_TaskDetail, "label_TaskDetail");
		com.isElementPresent(button_NewTask, "button_NewTask");
		com.isElementPresent(label_ApplicantStatus, "label_ApplicantStatus");
		com.isElementPresent(label_SuccessfulApplicants, "label_SuccessfulApplicants");
		com.isElementPresent(label_CurrentTaskSchedules, "label_CurrentTaskSchedules");
		com.isElementPresent(label_TaskStage, "label_TaskStage");
		com.isElementPresent(button_ViewAllTask, "button_ViewAllTask");
		com.isElementPresent(link_DisableRecruiter, "link_DisableRecruiter");
		com.isElementPresent(content_RecruiterDetails, "content_RecruiterDetails");
	}

	/**
	 * Create a new Task, Click on Calendar number link and verify Task Page is
	 * getting displayed Then Verify that newly created task is displayed in the
	 * list without the need of searching.
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	public void verifyCalendarNumLinksFunc() {

		CustomReporter.createNode("verify Calendar Num Links Func");

		com.navigateToAndVerifyPageUrl(button_CalendarNumber, TaskList.title);
	}

	/**
	 * Creates a new Task
	 * 
	 * @author shailendra Sep 18, 2019
	 * @param string6
	 * @param string5
	 * @param string4
	 * @param string3
	 * @param i
	 * @param string2
	 * @param object2
	 * @param fromTime
	 * @param todayDate
	 * @param string
	 * @param taskName
	 */
	public void createTask(String taskName, String stage, String date, String fromTime, String toTime, String location,
			String maxApplicant, String addRecruiter, String primaryRecruiter, String taskNotes,
			String commaSeparatedMultipleApplicants) {

		com.click(button_NewTask, "button_New Task");

		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.createTask(taskName, stage, date, fromTime, toTime, location, maxApplicant, addRecruiter,
				primaryRecruiter, taskNotes, commaSeparatedMultipleApplicants);

	}

	/**
	 * Will click on Disable Recruiter link to open the Disbale Recruiter Popup
	 * 
	 * All Objects and Methods of Disable Recruiter Popup are present on
	 * {@link RecruiterManagement} class
	 * 
	 * @author shailendra Oct 3, 2019
	 */
	public void openDisableRecruiterPopup() {
		com.click(link_DisableRecruiter, "link_Disable Recruiter");
	}

	/**
	 * Opens quick view of the Task and verify the UI Objects
	 * 
	 * @author shailendra Oct 29, 2019
	 */
	public void verifyTaskQuickViewUI() {
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		int totalRow = tab.getRowCount();

		if (totalRow > 1) {
			com.click(tab.getChildObject(2, 3, "i", 1), "Quick View icon");
			com.isElementPresent(data_ApplicantName, "data_Applicant Name");
			com.isElementPresent(data_TaskNotes, "data_Task Notes");
			com.isElementPresent(data_Date, "data_Date");
			com.isElementPresent(link_View, "link_View");
			com.isElementPresent(link_Complete, "link_Complete");
			com.isElementPresent(link_Archive, "link_Archive");
		} else {
			CustomReporter.report(STATUS.FAIL, "No Rows are getting displyed even after creating a new task");
		}
	}

	/**
	 * @param taskName
	 * @param stage
	 * @param date
	 * @param fromTime
	 * @param toTime
	 * @param location
	 * @param maxApplicant
	 * @param addRecruiter
	 * @param primaryRecruiter
	 * @param taskNotes
	 * @param commaSeparatedMultipleApplicants
	 */
	public void verifyTaskView_EditPopup(String taskName, String stage, String date, String fromTime, String toTime,
			String location, String maxApplicant, String addRecruiter, String primaryRecruiter, String taskNotes,
			String commaSeparatedMultipleApplicants) {

		CustomReporter.createNode("Verify Task View/Edit Popup Content");

		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int totalRow = tab.getRowCount();

		if (totalRow > 1) {

			int row = tab.getRowWithCellText(taskName);

			if (row != -1) {

				com.click(tab.getChildObject(row, 3, "i", 0), "Eye icon");

				com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

				CreateNewTask createNewTask = new CreateNewTask();

				createNewTask.verify_View_EditTaskPopUp_UI(taskName, stage, date, fromTime, toTime, location,
						maxApplicant, addRecruiter, primaryRecruiter, taskNotes, commaSeparatedMultipleApplicants);
			} else {
				CustomReporter.report(STATUS.FAIL,
						"[" + taskName + "] is not getting displayed on Recruiter Details page");
			}
		} else {
			CustomReporter.report(STATUS.FAIL, "No Rows are getting displyed even after creating a new task");
		}
	}

	/**
	 * This method will look for the passed task name in the list, and if the task
	 * found then it will complete it
	 * 
	 * @param taskName
	 * @author shailendra Nov 1, 2019
	 */
	public void completeTask(String taskName) {
		

		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int totalRow = tab.getRowCount();

		if (totalRow > 1) {

			// Adding this fail safe 
			int row = tab.getRowWithCellText(taskName);
			if (row == -1) {
				row = 2;
				taskName = tab.getCellText(row, 2);
			}
			
			CustomReporter.createNode("Completing the task " + taskName);

			com.click(tab.getChildObject(row, 3, "i", 1), "Quick View Icon");

			com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
			
			com.click(link_Complete, "link_Complete");
			
			com.click(comm.button_Confirm_ConfirmationPopup, "button_Confirm on Confirmation Popup");
			
			com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

			row = tab.getRowWithCellText(taskName);
			
			if (row==-1) {
				CustomReporter.report(STATUS.PASS, taskName+" is Removed from the Recruiter Details Page");
			} else {
				CustomReporter.report(STATUS.FAIL, taskName+" is NOT Removed from the Recruiter Details Page");
			}
				
		} else {
			CustomReporter.report(STATUS.FAIL, "No Rows are getting displyed even after creating a new task");
		}
	}

}