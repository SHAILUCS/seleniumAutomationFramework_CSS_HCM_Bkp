package or.recruit;

import java.util.Calendar;

import org.openqa.selenium.By;
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

public class CreateNewTask {
	SeleniumMethods com;
	HCMCommon comm;

	public CreateNewTask() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(name = "task_name")
	private WebElement text_TaskName;

	@FindBy(xpath = "//label[.='Stage:']/..//span[contains(@id,'react-select')]/div")
	private WebElement select_Stage;

	@FindBy(name = "PostDate")
	private WebElement text_Date;

	@FindBy(name = "from_time")
	private WebElement text_FromTime;

	@FindBy(name = "to_time")
	private WebElement text_ToTime;

	@FindBy(xpath = "//label[.='Location:']/..//span[contains(@id,'react-select')]/div")
	private WebElement select_Location;

	@FindBy(name = "max_applicant")
	private WebElement text_MaxApplicant;

	@FindBy(xpath = "//span[.='Search Recruiter'][contains(@class,'multi')]//following-sibling::span")
	private WebElement icon_SearchRecruiter;

	@FindBy(name = "relevant_task_note")
	private WebElement text_TaskNotes;

	@FindBy(xpath = "//button[contains(.,'Clear All')]")
	private WebElement button_ClearAll;

	@FindBy(xpath = "//span[.='Search for Applicants'][contains(@class,'multi')]//input")
	private WebElement text_SearchForApplicants;

	@FindBy(xpath = "//button[.='Create Task']")
	private WebElement button_CreateTask;

	@FindBy(xpath = "//label[contains(.,'Task Name:')]")
	private WebElement label_TaskName;

	@FindBy(xpath = "//label[contains(.,'Stage:')]")
	private WebElement label_Stage;

	@FindBy(xpath = "//label[contains(.,'Date:')]")
	private WebElement label_Date;

	@FindBy(xpath = "//label[contains(.,'Time/Duration:')]")
	private WebElement label_TimeDuration;

	@FindBy(xpath = "//label[contains(.,'Location:')]")
	private WebElement label_Location;

	@FindBy(xpath = "//label[contains(.,'Max Applicants:')]")
	private WebElement label_MaxApplicant;

	@FindBy(xpath = "//label[contains(.,'Assigned to Recruiter/s:')]")
	private WebElement label_AssignRecruiter;

	@FindBy(xpath = "//label[contains(.,'Relevant Task Notes:')]")
	private WebElement label_RelevantTaskNotes;

	@FindBy(xpath = "//b[contains(.,'Attach Applicant')]")
	private WebElement label_AttachApplicant;

	// TODO view task objects

	@FindBy(xpath = "//h3[contains(.,'Edit Task')]")
	private WebElement heading_EditTask;

	@FindBy(xpath = "//h3[contains(.,'View Task')]")
	private WebElement heading_ViewTask;

	@FindBy(xpath = "//label[contains(.,'Task Name:')]//following-sibling::p")
	private WebElement data_TaskName;

	@FindBy(xpath = "//label[contains(.,'Stage')]//following-sibling::p")
	private WebElement data_Stage;

	@FindBy(xpath = "//label[contains(.,'Date:')]//following-sibling::p")
	private WebElement data_Date;

	@FindBy(xpath = "//p[contains(.,'From')]")
	private WebElement data_FromTime;

	@FindBy(xpath = "//p[contains(.,'To')]")
	private WebElement data_ToTime;

	@FindBy(xpath = "//label[contains(.,'Location:')]//following-sibling::p")
	private WebElement data_Location;

	@FindBy(xpath = "//label[contains(.,'Notes:')]//following-sibling::p")
	private WebElement data_RelevantTaskNotes;

	@FindBy(xpath = "//h3[contains(.,'Attach Applicant')]/..//div[contains(@class,'ReactTable')]")
	private WebElement reactTable_AttachedApplicants;

	/**
	 * Will fill the Task Form
	 * 
	 * @author shailendra Sep 18, 2019
	 * 
	 * @param date should be in dd/MM/YYYY format only
	 */
	public void fillTaskDetails(String taskName, String stage, String date, String fromTime, String toTime,
			String location, String maxApplicant, String commaSeparatedMultipleRecruiter, String primaryRecruiter,
			String taskNotes, String commaSeparatedMultipleApplicants) {

		CustomReporter.createNode("Filling Create Task Popup Details");

		if (taskName != null) {
			com.sendKeys("Task Name", text_TaskName, taskName);
		}

		if (stage != null) {
			comm.selectByVisibleText(select_Stage, stage);
		}

		if (date != null) {
			// Selecting passed date
			comm.selectDate(text_Date, date);
		} else {
			// Selecting 2 days later date value
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 2);
			comm.selectDate(text_Date, Util.convertToString("dd/MM/YYYY", cal.getTime()));
		}

		By from_To_Time_Xp_FirstEnabled = By.xpath("//li[not(contains(@class,'disable'))][contains(@class,'time')]");
		By from_Time_Xp_PassedData = By
				.xpath("//li[not(contains(@class,'disable'))][contains(@class,'time')][.='" + fromTime + "']");
		By to_Time_Xp_PassedData = By
				.xpath("//li[not(contains(@class,'disable'))][contains(@class,'time')][.='" + toTime + "']");

		if (fromTime == null) {
			com.click(text_FromTime, "From Time");
			com.wait(.5);
			com.click(from_To_Time_Xp_FirstEnabled, com.getText(from_To_Time_Xp_FirstEnabled));
		} else {
			com.click(text_FromTime);
			com.wait(.5);
			com.click(from_Time_Xp_PassedData, "From Time: " + fromTime);
		}

		if (toTime == null) {
			com.click(text_ToTime, "To Time");
			com.wait(.5);
			com.click(from_To_Time_Xp_FirstEnabled, com.getText(from_To_Time_Xp_FirstEnabled));
		} else {
			com.click(text_ToTime);
			com.wait(.5);
			com.click(to_Time_Xp_PassedData, "To Time: " + toTime);
		}

		if (location != null) {
			comm.selectByVisibleText(select_Location, location);
		}

		if (maxApplicant != null) {
			com.click(text_MaxApplicant);
			com.sendKeys("Max Applicant", text_MaxApplicant, maxApplicant);
		}

		if (commaSeparatedMultipleRecruiter != null) {
			searchAndAddRecruiter(commaSeparatedMultipleRecruiter);
		}

		if (primaryRecruiter != null) {
			selectPrimaryRecruiter(primaryRecruiter);
		}

		if (taskNotes != null) {
			com.sendKeys("Task Notes", text_TaskNotes, taskNotes);
		}

		if (commaSeparatedMultipleApplicants != null) {
			searchAndAddApplicants(commaSeparatedMultipleApplicants);
		}

	}

	/**
	 * @param primaryRecruiter
	 */
	private void selectPrimaryRecruiter(String primaryRecruiter) {
		com.javaScript_Click(By.xpath(
				"//div[@class='nme_assi'][contains(.,'" + primaryRecruiter + "')]//following-sibling::label//input"),
				"Primary Recruiter [" + primaryRecruiter + "]");
	}

	/**
	 * Search and Add a multiple Applicants
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	public void searchAndAddApplicants(String commaSeparatedMultipleApplicants) {
		String[] applicantArr = null;

		if (commaSeparatedMultipleApplicants.contains(",")) {
			applicantArr = commaSeparatedMultipleApplicants.split(",");
		} else {
			applicantArr = new String[1];
			applicantArr[0] = commaSeparatedMultipleApplicants;
		}

		for (String singleApplicant : applicantArr) {
			
			int counter = 0;
			
			while (counter++ < 5) {
				
				CustomReporter.report(STATUS.INFO, counter+" Attempt to add Applicant ["+singleApplicant+"]");
				
				com.wait(1);
				com.sendKeys(text_SearchForApplicants, singleApplicant);
				com.wait(1);
				com.waitForElementTobe_NotVisible(comm.spinnerLocator,5);

				By xp = By.xpath("//div[@role='option'][contains(.,'" + singleApplicant + "')]");

				if(!com.waitForElementTobe_NotVisible(xp,1)) {

					com.click(xp, "Selecting Applicant [" + singleApplicant + "]");
		
					verifyAddedApplicants(singleApplicant);
					
					break;
				}
				
			}
			
		}

	}

	/**
	 * Search and Add a Recruiter
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	private void searchAndAddRecruiter(String commaSeparatedMultipleRecruiter) {

		String[] recruiterArr = null;

		if (commaSeparatedMultipleRecruiter.contains(",")) {
			recruiterArr = commaSeparatedMultipleRecruiter.split(",");
		} else {
			recruiterArr = new String[1];
			recruiterArr[0] = commaSeparatedMultipleRecruiter;
		}

		for (String singleRecruiter : recruiterArr) {

			int counter = 0;

			while (counter++ < 5) {

				CustomReporter.report(STATUS.INFO, counter + " Attempt to add Recruiter [" + singleRecruiter + "]");
				
				com.click(icon_SearchRecruiter, "Search icon");

				com.sendKeys(com.switchTo_ActiveElement(), singleRecruiter);

				By singleRectXp = By.xpath("//div[@role='option'][contains(.,'" + singleRecruiter + "')]");

				com.wait(1);
				com.waitForElementTobe_NotVisible(comm.spinnerLocator);

				if(!com.waitForElementTobe_NotVisible(singleRectXp,1)) {

					com.click(singleRectXp, "Selecting Recruiter [" + singleRecruiter + "]");
					
					break;
				}

			}
		}

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
			String maxApplicant, String commaSeparatedMultipleRecruiter, String primaryRecruiter, String taskNotes,
			String commaSeparatedMultipleApplicants) {

		CustomReporter.createNode("Creating a New Task");

		fillTaskDetails(taskName, stage, date, fromTime, toTime, location, maxApplicant,
				commaSeparatedMultipleRecruiter, primaryRecruiter, taskNotes, commaSeparatedMultipleApplicants);

		com.click(button_CreateTask, "button_Create Task");

		com.waitForElementsTobe_Present(By.xpath("//p[contains(.,'Task created successfully')]"),
				"Task created successfully Message");

	}

	/**
	 * Check Max applicant validation 1.Select 1 on max applicant box 2.Search
	 * another applicant after select one applicant 3.search box disable after
	 * select 1 applicant
	 * 
	 * @author Adarsh 23-Sep-2019
	 **/
	public void checkMaxApplicantValidation() {

		fillTaskDetails(null, "None", null, null, null, null, "1", null, null, null, "Jose");

		if (com.waitForElementTobe_NotVisible(text_SearchForApplicants, 1)) {
			CustomReporter.report(STATUS.PASS,
					"not able to select after select one applicant, seacrh input is disabled");
		} else {
			CustomReporter.report(STATUS.FAIL, "If search box is enable");
		}
	}

	public void createTaskVerifyUI() {

		com.isElementPresent(label_TaskName, "Task Name");
		com.isElementPresent(label_Stage, "Stage");
		com.isElementPresent(label_TimeDuration, "Time/Duration");
		com.isElementPresent(label_Location, "Location");
		com.isElementPresent(label_MaxApplicant, "Select Maximum Applicant");
		com.isElementPresent(label_AssignRecruiter, "Assign Rescruiter");
		com.isElementPresent(label_RelevantTaskNotes, "Relevent Notes");
		com.isElementPresent(label_AttachApplicant, "Attach Applicant");

	}

	/**
	 *
	 * -This method create to search recruiter -Go to task pop up -search recruiter
	 * on recruiter search box
	 * 
	 * @author Adarsh 01-Oct-2019
	 */

	public void createTaskSearchRecruiter(String commaSeparatedMultipleRecruiter) {
		searchAndAddRecruiter(commaSeparatedMultipleRecruiter);

	}

	public void verify_View_EditTaskPopUp_UI(String taskName, String stage, String date, String fromTime, String toTime,
			String location, String maxApplicant, String commaSeparatedMultipleRecruiter, String primaryRecruiter,
			String taskNotes, String commaSeparatedMultipleApplicants) {

		// Verifying common objects of View/Edit Task Popup

		String taskNamePopUp = com.getText(data_TaskName);
		Util.comparator_NonNumbers(taskName, taskNamePopUp, "Task Name");

		String stagePopUp = com.getText(data_Stage);
		Util.comparator_NonNumbers(stage, stagePopUp, "Stage");

		String datePopUp = com.getText(data_Date);
		Util.comparator_NonNumbers(date, datePopUp, "Date");

		String fromPopUp = com.getText(data_FromTime);
		Util.comparator_NonNumbers(fromTime, fromPopUp, "From Time");

		String toPopUp = com.getText(data_ToTime);
		Util.comparator_NonNumbers(toTime, toPopUp, "To Time");

		String locationPopUp = com.getText(data_Location);
		Util.comparator_NonNumbers(location, locationPopUp, "Location");

		String notesPopUp = com.getText(data_RelevantTaskNotes);
		Util.comparator_NonNumbers(taskNotes, notesPopUp, "Relevant Notes");

		// Checking Added Recruiters
		verifyAddedRecruiters(commaSeparatedMultipleRecruiter);

		// Checking Primary Recruiter
		By primaryRectXp = By.xpath(
				"//div[@class='nme_assi'][contains(.,'" + primaryRecruiter + "')]//following-sibling::label//input");
		com.isSelected(primaryRectXp, "Primary Recruiter: " + primaryRecruiter);

		// Checking Added Applicants
		verifyAddedApplicants(commaSeparatedMultipleApplicants);

		if (!com.waitForElementsTobe_NotVisible(heading_EditTask, 1)) {
			// Verifying objects specific to Edit Task Popup
			com.isElementPresent(heading_EditTask, "Edit Task Heading");

			com.isElementPresent(icon_SearchRecruiter, "Search Recruiter Textfield on Edit Task Popup");

			com.isEnabled(button_ClearAll, "Clear All button on Edit Task Popup");

			com.isElementPresent(text_SearchForApplicants, "Search For Applicants Textfield on Edit Task Popup");

			// Verifying that Remove buttons should be displayed as active
			verifyRemoveButtons_Enable_Disable(true);

			com.isElementPresent(By.xpath("//button[.='Save Changes']"), "Save Changes button on Edit Task Popup");

		} else {
			// Verifying objects specific to View Task Popup
			com.isElementPresent(heading_ViewTask, "View Task Heading");

			if (com.waitForElementsTobe_NotVisible(icon_SearchRecruiter, 1)) {
				CustomReporter.report(STATUS.PASS, "Search Recruiter Textfield is Not displayed on View Task Popup");
			} else {
				CustomReporter.report(STATUS.FAIL,
						"Search Recruiter Textfield is getting displayed on View Task Popup");
			}

			if (!com.isEnabled(button_ClearAll)) {
				CustomReporter.report(STATUS.PASS, "Clear All button is displayed as Disabled on View Task Popup");
			} else {
				CustomReporter.report(STATUS.FAIL, "Clear All button is Not displayed as Disabled on View Task Popup");
			}

			if (com.waitForElementsTobe_NotVisible(text_SearchForApplicants, 1)) {
				CustomReporter.report(STATUS.PASS,
						"Search For Applicants Textfield is Not displayed on View Task Popup");
			} else {
				CustomReporter.report(STATUS.FAIL,
						"Search For Applicants Textfield is getting displayed on View Task Popup");
			}

			// Verifying that Remove buttons should be displayed as inactive
			verifyRemoveButtons_Enable_Disable(false);

			if (com.waitForElementsTobe_NotVisible(By.xpath("//button[.='Save Changes']"), 1)) {
				CustomReporter.report(STATUS.PASS, "Save Changes Button is Not displayed on View Task Popup");
			} else {
				CustomReporter.report(STATUS.FAIL, "Save Changes Button is getting displayed on View Task Popup");
			}
		}

	}

	/**
	 * Checks all Remove buttons are enable/disable as per passed parameter
	 * 
	 * @param trueForEnable
	 * @author shailendra Oct 31, 2019
	 */
	private void verifyRemoveButtons_Enable_Disable(boolean trueForEnable) {
		ReactTable tab = new ReactTable(reactTable_AttachedApplicants);

		for (int row = 2; row < tab.getRowCount(); row++) {

			boolean flag = (trueForEnable ? false : true);

			WebElement ele = tab.getChildObject(row, 6, "button", 0);

			if (com.isEnabled(ele)) {
				flag = (trueForEnable ? true : false);
			}

			if (flag) {
				CustomReporter.report(STATUS.PASS,
						"Remove button is displayed as " + (trueForEnable ? "Enabled" : "Disabled"));
			} else {
				CustomReporter.report(STATUS.FAIL,
						"Remove button is Not displayed as " + (trueForEnable ? "Enabled" : "Disabled"));
			}

		}
	}

	/**
	 * This method will verify whether the Added Applicants are getting displayed on
	 * the View/Edit popup or not
	 * 
	 * @param commaSeparatedMultipleApplicants
	 * @author shailendra Oct 31, 2019
	 */
	private void verifyAddedApplicants(String commaSeparatedMultipleApplicants) {
		String[] applicantArr = null;

		if (commaSeparatedMultipleApplicants.contains(",")) {
			applicantArr = commaSeparatedMultipleApplicants.split(",");
		} else {
			applicantArr = new String[1];
			applicantArr[0] = commaSeparatedMultipleApplicants;
		}

		ReactTable tab = new ReactTable(reactTable_AttachedApplicants);

		for (String singleApplicant : applicantArr) {
			int rowNum = tab.getRowWithCellText(singleApplicant);

			if (rowNum != -1) {
				CustomReporter.report(STATUS.PASS,
						"Applicant [" + singleApplicant + "] is added in the Applicant Table");
			} else {
				CustomReporter.report(STATUS.FAIL,
						"Applicant [" + singleApplicant + "] is NOT added in the Applicant Table");
			}

		}
	}

	/**
	 * This method will verify whether the Added Recruiters are getting displayed on
	 * the View/Edit popup or not
	 * 
	 * @param commaSeparatedMultipleRecruiter
	 * @author shailendra Oct 31, 2019
	 */
	private void verifyAddedRecruiters(String commaSeparatedMultipleRecruiter) {
		String[] recruiterArr = null;

		if (commaSeparatedMultipleRecruiter.contains(",")) {
			recruiterArr = commaSeparatedMultipleRecruiter.split(",");
		} else {
			recruiterArr = new String[1];
			recruiterArr[0] = commaSeparatedMultipleRecruiter;
		}

		for (String singleRecruiter : recruiterArr) {
			com.isElementPresent(By.xpath("//div[@class='nme_assi'][contains(.,'" + singleRecruiter + "')]"),
					"Recruiter " + singleRecruiter);
		}
	}

	/**
	 * @param commaSeparatedApplicants
	 */
	public void removeApplicants(String commaSeparatedApplicants) {
		String[] applicantArr = null;

		if (commaSeparatedApplicants.contains(",")) {
			applicantArr = commaSeparatedApplicants.split(",");
		} else {
			applicantArr = new String[1];
			applicantArr[0] = commaSeparatedApplicants;
		}

		ReactTable tab = new ReactTable(
				By.xpath("//h3[contains(.,'Attach Applicant')]//following::div[contains(@class,'ReactTable')]"));

		for (String singleApplicant : applicantArr) {
			int row = tab.getRowWithCellText(singleApplicant);
			if (row != -1) {

				int column = tab.initHeaderColumnList(1).getColumnNumber("Tasks");

				com.click(tab.getChildObject(row, column, "span", 0), "Remove button for " + singleApplicant);

				row = tab.getRowWithCellText(singleApplicant);
				if (row == -1) {
					CustomReporter.report(STATUS.PASS, "Applicant " + singleApplicant + " is removed from the list");
				} else {
					CustomReporter.report(STATUS.FAIL,
							"Applicant " + singleApplicant + " is NOT removed from the list");
				}
			} else {
				CustomReporter.report(STATUS.FAIL, "Applicant " + singleApplicant + " is NOT found");
			}
		}
	}

	/**
	 * Demo Purpose This method use to verify a task detail on view task pop up.
	 * 
	 * @author Adarsh 01-Nov-2019
	 */
	public void pageContentVerificationDemo(String taskName, String stage, String date, String primaryR) {

		String tN = com.getText(data_TaskName);
		String st = com.getText(data_Stage);
		String dt = com.getText(data_Date);

		Util.comparator_NonNumbers(taskName, tN, "Task Name");
		Util.comparator_NonNumbers(stage, st, "Stage");
		Util.comparator_NonNumbers(date, dt, "Date");

		if (!primaryR.equals("")) {
			com.isSelected(By.xpath("//div[contains(@class,'rec_assignees')]//div[contains(.,'" + primaryR
					+ "')]//following-sibling::label/input"), "Primary Recriter");
		}
	}

	/**
	 * @param primaryRecruiter1
	 * @param primaryRecruiter2
	 */
	public void check_AddRemovePrimaryRecruiter_OnCreateTaskPopup(String primaryRecruiter1, String primaryRecruiter2) {
		fillTaskDetails(null, null, null, null, null, null, null, primaryRecruiter1, primaryRecruiter1, null, null);

		fillTaskDetails(null, null, null, null, null, null, null, primaryRecruiter2, primaryRecruiter2, null, null);

		com.isSelected(By.xpath("//div[contains(@class,'rec_assignees')]//div[contains(.,'" + primaryRecruiter2
				+ "')]//following-sibling::label/input"), primaryRecruiter2 + " as a Primary Recriter");

		if (com.isSelected(By.xpath("//div[contains(@class,'rec_assignees')]//div[contains(.,'" + primaryRecruiter1
				+ "')]//following-sibling::label/input"))) {
			CustomReporter.report(STATUS.FAIL, primaryRecruiter1 + " is still a primary recruiter");
		} else {
			CustomReporter.report(STATUS.PASS, primaryRecruiter1 + " is Not a primary recruiter");
		}
	}

	/**
	 * @param primaryRecruiter
	 * @param recruiter2
	 */
	public void check_AddRemovePrimaryRecruiter_OnEditTaskPopup(String primaryRecruiter1, String primaryRecruiter2) {

		searchAndAddRecruiter(primaryRecruiter2);

		selectPrimaryRecruiter(primaryRecruiter2);

		com.isSelected(By.xpath("//div[contains(@class,'rec_assignees')]//div[contains(.,'" + primaryRecruiter2
				+ "')]//following-sibling::label/input"), primaryRecruiter2 + " as a Primary Recriter");

		if (com.isSelected(By.xpath("//div[contains(@class,'rec_assignees')]//div[contains(.,'" + primaryRecruiter1
				+ "')]//following-sibling::label/input"))) {
			CustomReporter.report(STATUS.FAIL, primaryRecruiter1 + " is still a primary recruiter");
		} else {
			CustomReporter.report(STATUS.PASS, primaryRecruiter1 + " is Not a primary recruiter");
		}
	}
}
