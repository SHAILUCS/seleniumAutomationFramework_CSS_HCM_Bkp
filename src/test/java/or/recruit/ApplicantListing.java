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

public class ApplicantListing {
	public static String title = "recruitment/applicants";
	SeleniumMethods com;
	HCMCommon comm;

	public ApplicantListing() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//div[.='Name'][contains(@class,'ellipsis_line1__')]")
	private WebElement colHead_Name;

	@FindBy(xpath = "//div[.='Job Position'][contains(@class,'ellipsis_line1__')]")
	private WebElement colHead_JobPosition;

	@FindBy(xpath = "//div[.='Date Applied'][contains(@class,'ellipsis_line1__')]")
	private WebElement colHead_DateApplied;

	@FindBy(xpath = "//div[.='Recruitment Stage'][contains(@class,'ellipsis_line1__')]")
	private WebElement colHead_RecruitmentStage;

	@FindBy(xpath = "//p[contains(.,'APP')]")
	private WebElement applicant_ID;

	@FindBy(xpath = "//p[contains(.,'Phone')]")
	private WebElement data_PhoneDetails;

	@FindBy(xpath = "//p[contains(.,'Email')]")
	private WebElement data_EmailDetails;

	@FindBy(xpath = "//p[contains(.,'Pending')]")
	private WebElement data_PendingStage;

	@FindBy(xpath = "//p[contains(.,'Assigned to')]")
	private WebElement data_assignedToDetail;

	@FindBy(xpath = "//p[contains(.,'Website Questions :') or contains(.,'Seek Questions :')]")
	private WebElement data_SeekWebsiteQuestionData;

	@FindBy(xpath = "//span[.='Archive Applicant']")
	private WebElement link_ArchiveApplicant;

	@FindBy(xpath = "//span[.='Flag Applicant']")
	private WebElement link_FlagApplicant;

	@FindBy(xpath = "(//span[contains(@id,'select')][contains(@class,'value-wrapper')])[2]")
	private WebElement dropdown_FilterBy;

	@FindBy(xpath = "//button[contains(.,'View Applicant Information')]")
	private WebElement button_ViewApplicantInformation;
	
	@FindBy(xpath = "//div[contains(@class,'rt-noData')]")
	private WebElement data_NoRecordFound;
	


	/**
	 * Opens the quick view of First Row
	 * 
	 * @author shailendra Oct 2, 2019
	 */
	private ReactTable openQuickView() {
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.click(tab.getChildObject(2, 5, "i", 0), "Quick View icon");
		return tab;
	}

	public void verifyColumnHeader() {
		com.isElementPresent(colHead_Name, "Col Heading - Name");
		com.isElementPresent(colHead_JobPosition, "Col Heading - Job Position");
		com.isElementPresent(colHead_DateApplied, "Col Heading - DateApplied");
		com.isElementPresent(colHead_RecruitmentStage, "Col Heading - RecruitmentStage");

	}

	public void verifyQuickView() {
		openQuickView();
		com.isElementPresent(applicant_ID, "Applicant ID");
		com.isElementPresent(data_PhoneDetails, "Phone detial");
		com.isElementPresent(data_EmailDetails, "Email Details");
		com.isElementPresent(data_PendingStage, "Verify Pending Stage");
		com.isElementPresent(data_assignedToDetail, "Assined to details");
		com.isElementPresent(data_SeekWebsiteQuestionData, "Seek Quetions Details");
		com.isElementPresent(link_ArchiveApplicant, "Archive Applicant Link");
		com.isElementPresent(link_FlagApplicant, "Link Flag Applicant");
	}

	// CHANGE METHOD NAME TO SOMETHING APPROPRIATE
	public void checkSubStageOnQuickView() {
		openQuickView();

	}

	// CHANGE METHOD NAME TO SOMETHING APPROPRIATE
	// verifySeekAnswersShowingOnlyForStage1
	public void Stage1ShowSeekAns() {

	}

	public void checkFilterByDropdownFunctionality() {

		// checking default value of the Filter By Dropdown
		String txt = com.getText(dropdown_FilterBy);
		// Checking actual value is equal with expected
		if (txt.equals("In-Progress")) {
			CustomReporter.report(STATUS.PASS, "Default value is successfully displayed as In-Progress");
		} else {
			openQuickView();
			
			com.click(button_ViewApplicantInformation, "button_ViewApplicantInformation");

			ApplicantInfo appInf = new ApplicantInfo();

			String stPage = appInf.getApplicantStatus();

			if (stPage.toLowerCase().contains("in-progress")) {
				CustomReporter.report(STATUS.PASS, "In-Progress status is displaying");
			} else {
				CustomReporter.report(STATUS.FAIL, "In-Progress status is not displayed but displayed "+stPage);
			}
			
			com.navigateBack();
		}

		// Test FilterBy dropdown functionality "hired".

		comm.selectByVisibleText(dropdown_FilterBy, "Hired");
		if (!com.waitForElementTobe_NotVisible(data_NoRecordFound, 1)) {
			CustomReporter.report(STATUS.PASS, "No Record Found message is displaying");
		} else {

			openQuickView();

			com.click(button_ViewApplicantInformation, "button_ViewApplicantInformation");

			ApplicantInfo appInf = new ApplicantInfo();

			String stPage = appInf.getApplicantStatus();

			if (stPage.toLowerCase().contains("hire")) {
				CustomReporter.report(STATUS.PASS, "Hired status is displaying");
			} else {
				CustomReporter.report(STATUS.FAIL, "Hired status is not displayed but displayed "+stPage);
			}
			com.navigateBack();
		}

		// Test FilterBy dropdown functionality "Rejected Applicants".

		comm.selectByVisibleText(dropdown_FilterBy, "Rejected Applicants");
		if (!com.waitForElementTobe_NotVisible(data_NoRecordFound, 1)) {
			CustomReporter.report(STATUS.PASS, "No Record Found message is displaying");
		} else {
			openQuickView();
			
			com.click(button_ViewApplicantInformation, "button_ViewApplicantInformation");

			ApplicantInfo appInf = new ApplicantInfo();

			String stPage = appInf.getApplicantStatus();

			if (stPage.toLowerCase().contains("reject")) {
				CustomReporter.report(STATUS.PASS, "reject status is displaying");
			} else {
				CustomReporter.report(STATUS.FAIL, "reject status is not displayed but displayed "+stPage);
			}
			
			com.navigateBack();
		}

	}

	/**
	 * This will open quick view of first row >> Click on View Applicant Info button
	 * 
	 * @return applicantName
	 * @author shailendra Oct 2, 2019
	 */
	public String navigateToApplicantInformation() {
		ReactTable tab = openQuickView();

		String applicantName = tab.getCellText(2, 1);

		com.navigateToAndVerifyPageUrl(button_ViewApplicantInformation, ApplicantInfo.title);

		return applicantName;
	}

}
