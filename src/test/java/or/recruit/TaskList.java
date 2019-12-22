package or.recruit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * 
 * @author Adarsh 01-Oct-2019
 */

public class TaskList {
	public static final String title = "recruitment/action/task";
	SeleniumMethods com;
	HCMCommon comm;

	public TaskList() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//h1[contains(.,'Tasks')]")
	private WebElement label_Task;

	@FindBy(xpath = "//a[contains(.,'Create New Task')]")
	private WebElement button_CreateNewTask;

	@FindBy(xpath = "//input[contains(@class,'srch-inp')]")
	private WebElement input_SearchBox;

	@FindBy(xpath = "//div[contains(@class,'Select-value')]")
	private WebElement select_FiterDropdown;

	@FindBy(xpath = "//div[.='No Record Found']")
	private WebElement data_NoRows;

	@FindBy(xpath = "//button[.='Create Task']")
	private WebElement button_CreateTask;

	@FindBy(xpath = "//button[.='Confirm']")
	private WebElement button_Confirm;

	@FindBy(xpath = "//a[.='Manage Group Interview']")
	private WebElement link_ManageGroupInterview;

	/**
	 * verify a Button Create New Task
	 * 
	 * @author Adarsh 07-Oct-2019
	 */
	public void openCreateNewTaskPopup() {
		com.click(button_CreateNewTask, "button_Create New Task");
	}

	/**
	 * Verify CreTask UI
	 * 
	 * @author Adarsh 01-Oct-2019
	 */
	public void verifyTaskUI() {

		com.isElementPresent(label_Task, "Task");
		com.isElementPresent(input_SearchBox, "Search");
		com.isElementPresent(button_CreateNewTask, "button_CreateNewTask");

		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.verifyColumnHeaders(1, "Task Name", "Stage", "Date", "Primary Recruiter", "Available Slots:", "Status",
				"Action");

		com.isElementPresent(tab.getChildObject(2, 7, "a", 0), "Complete");
		com.isElementPresent(tab.getChildObject(2, 7, "a", 1), "Edit/View");
		com.isElementPresent(tab.getChildObject(2, 7, "a", 2), "Archive");

	}

	/**
	 * @param taskData
	 * @author Adarsh 24-Oct-2019
	 * 
	 */
	public void verifySearch(String taskData) {
		comm.verifyValid_TextSearch(input_SearchBox, taskData);
	}

	/**
	 * Verify a Filters (In Progress,Complete ,Archive) for Tasklist page
	 */
	public void checkFilter() {
		selectFilterAndVerifyTableData("In Progress");
		selectFilterAndVerifyTableData("Completed");
		selectFilterAndVerifyTableData("Archive");

	}

	/**
	 * Verify Filter data on TaskList page
	 * 
	 * @author Adarsh 15-Oct-2019
	 */
	private void selectFilterAndVerifyTableData(String filterVal) {

		CustomReporter.createNode(filterVal + " Only");
		comm.selectByVisibleText(select_FiterDropdown, filterVal);
		
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

		if (!com.waitForElementsTobe_NotVisible(data_NoRows, 1)) {
			CustomReporter.report(STATUS.WARNING, "No Roles message is getting displayed");
		} else {

			// First of all selecting 50 rows, so that pages count will become less
			// com.click(comm.link_50, "50 Rows");

			// com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

			ReactTable tab = new ReactTable(comm.reactTableLocator);

			boolean flag = true;
			for (int row = 2; row < tab.getRowCount(); row++) {
				String temp = tab.getCellText(row, 6);

				if (!temp.toLowerCase().contains(filterVal.toLowerCase())) {
					CustomReporter.report(STATUS.FAIL,
							"Incorrect Status is getting displayed on row [" + row + "] actual status is " + temp);
					flag = false;
					break;
				}
			}

			if (flag) {
				CustomReporter.report(STATUS.PASS, "Correct Status is getting displayed in all rows");
			}
		}

	}

	/**
	 * Create a method to verify a pop up
	 * 
	 * @author Adarsh 15-Oct-2019
	 */
	public void verifyViewTaskPopUp() {

		ReactTable tab = new ReactTable(comm.reactTableLocator);

		String taskName = tab.getCellText(2, 1);
		String stage = tab.getCellText(2, 2);
		String date = tab.getCellText(2, 3);
		String primaryR = tab.getCellText(2, 4);

		clickOnEdit_ViewLink(2);

		CreateNewTask CreateNewTask = new CreateNewTask();

		CreateNewTask.pageContentVerificationDemo(taskName, stage, date, primaryR);

	}

	/**
	 * Verify a completed task Create a task and mark as complete Verify complete
	 * task with apply filter
	 * 
	 * @author Adarsh 24-Oct-2019
	 */
	public void verifyCompleteTask(String taskName, String stage, String date, String fromTime, String toTime,
			String location, String maxApplicant, String addRecruiter, String primaryRecruiter, String taskNotes,
			String commaSeparatedMultipleApplicants) {

		createTask(taskName, stage, date, fromTime, toTime, location, maxApplicant, addRecruiter, primaryRecruiter,
				taskNotes, commaSeparatedMultipleApplicants);

		comm.verifyValid_TextSearch(input_SearchBox, taskName);

		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.click(tab.getChildObject(2, 7, "a", 0), "Complete");
		com.click(button_Confirm, "Button_Confirm");
		selectFilterAndVerifyTableData("Completed");

	}

	/**
	 * Verify a Archive task Create a task and mark as Archive Verify Archive task
	 * with apply filter
	 * 
	 * @author Adarsh 24-Oct-2019
	 */
	public void verifyArchiveTask(String taskName, String stage, String date, String fromTime, String toTime,
			String location, String maxApplicant, String addRecruiter, String primaryRecruiter, String taskNotes,
			String commaSeparatedMultipleApplicants) {

		createTask(taskName, stage, date, fromTime, toTime, location, maxApplicant, addRecruiter, primaryRecruiter,
				taskNotes, commaSeparatedMultipleApplicants);

		comm.verifyValid_TextSearch(input_SearchBox, taskName);

		archiveTask(2);
		selectFilterAndVerifyTableData("Archive");

	}

	/**
	 * Creates a new Task
	 * 
	 * @author shailendra Sep 18, 2019
	 * @param string6
	 * @param string5
	 * @param date      should be in dd/MM/YYYY format only
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

		openCreateNewTaskPopup();

		CreateNewTask obj = new CreateNewTask();

		obj.createTask(taskName, stage, date, fromTime, toTime, location, maxApplicant, addRecruiter, primaryRecruiter,
				taskNotes, commaSeparatedMultipleApplicants);
	}

	/**
	 * @param recruiter2 
	 * @param string4 
	 * @param string3 
	 * @param recruiter12 
	 * @param recruiter1 
	 * @param string2 
	 * @param string 
	 * @param object2 
	 * @param object 
	 * @param todayDate 
	 * @param string
	 * @param string2
	 */
	public void check_AddRemovePrimaryRecruiterEditTask(String taskName, String stage, String date, String fromTime, String toTime, String location,
			String maxApplicant, String commaSeparatedRecruiter, String primaryRecruiter, String taskNotes,
			String commaSeparatedMultipleApplicants, String recruiter2) {
		
		createTask(taskName, stage, date, fromTime, toTime, location, maxApplicant, commaSeparatedRecruiter, primaryRecruiter, taskNotes, commaSeparatedMultipleApplicants);
		
		CustomReporter.createNode("Search By Task Name");
		
		verifySearch(taskName);

		clickOnEdit_ViewLink(2);
		
		CreateNewTask createNewTask = new CreateNewTask();

		createNewTask.check_AddRemovePrimaryRecruiter_OnEditTaskPopup(primaryRecruiter, recruiter2);
	}

	
	/**
	 * This method is verify a Task. 
	 * @author Adarsh 04-Nov-2019
	 */
	public void clickOnEdit_ViewLink(int row) {
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.click(tab.getChildObject(row, 7, "a", 1), "Edit/View");
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
	}
	
	/**
	 * This method is creating for archive a Task
	 * @author Adarsh 04-Nov-2019
	 */
	public void archiveTask(int row) {
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.click(tab.getChildObject(row, 7, "a", 2), "Archive");
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		com.click(button_Confirm, "Button_Confirm");
		
	}

	/**
	 * This method creating for search a task
	 * @param taskName
	 */
	public void searchAndArchiveTask(String taskName) {
		verifySearch(taskName);
		archiveTask(2);
		comm.selectByVisibleText(select_FiterDropdown, "Archive");
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		verifySearch(taskName);
	}

}
