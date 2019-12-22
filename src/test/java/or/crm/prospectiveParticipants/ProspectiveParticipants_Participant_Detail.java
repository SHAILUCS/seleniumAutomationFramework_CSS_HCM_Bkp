/**
 * 
 */
package or.crm.prospectiveParticipants;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.crm.Create_EditParticipant;
import or.crm.Prospective_Participants_Dashboard;
import or.crm.StaffMembers;
import or.crm.Tab_ParticipantAbility;
import or.crm.Tab_ParticipantDetails;
import or.crm.Tab_RefererDetails;

/**
 * @author Archana Oct 2, 2019 11:55:57 AM
 *
 */
public class ProspectiveParticipants_Participant_Detail {
	@FindBy(xpath = "//a[@title='View']")
	private WebElement btn_more_view;

	@FindBy(xpath = "//div[contains(@class,'h-h1')]")
	private WebElement title_participantDetails;

	@FindBy(xpath = "//strong[contains(text(),'Assigned to:')]")
	private WebElement link_Assignedto;

	@FindBy(xpath = "//a[contains(@class,'btn-1 s2 px-4 ml-3')][contains(text(),'Change')]")
	private WebElement button_changeAssigneduser;

	@FindBy(xpath = "//div[contains(@class,'Lates_up_btn')]//i")
	private WebElement button_viewall;

	@FindBy(xpath = "//a[contains(text(),'Edit Participants Info')]")
	private WebElement button_EditParticipantsInfo;

	@FindBy(xpath = "//i[contains(@class,'more-less glyphicon glyphicon-plus')]")
	private WebElement icon_FMSexpand;;

	@FindBy(xpath = " //a[contains(@class,'btn-1')][contains(text(),'Manage  Attachments')]")
	public WebElement button_manageAttachments;

	@FindBy(xpath = "//strong[contains(text(),'Intake Progress:')]")
	private WebElement text_IntakeProgress;

	@FindBy(xpath = "//h1[(text()='Edit Prospective Participant')]")
	private WebElement heading_EditProspectiveParticipant;;

	@FindBy(xpath = "//a[(text()='Referer Details')]")
	private WebElement link_RefererDetails;

	@FindBy(xpath = "//div[(text()='Referer Details')]")
	private WebElement title_RefererDetails;

	@FindBy(xpath = "//a[(text()='Participant Details')]")
	private WebElement link_ParticipantDetails;

	@FindBy(xpath = "//li[contains(.,'Participant Details')]")
	private WebElement title_ParticipantDetails;

	@FindBy(xpath = "//a[(text()='Participant Ability')]")
	private WebElement link_ParticipantAbility;

	@FindBy(xpath = "//div[(text()='Participant Ability')]")
	public WebElement title_ParticipantAbility;

	@FindBy(xpath = "//form[@id='referral_details']//a[@class='btn-1'][contains(text(),'Save And Continue')]")
	private WebElement button_referralDetailsavebtn;

	@FindBy(xpath = "//input[@name='ndisno']")
	private WebElement input_NDISNumber;

	@FindBy(xpath = "//a[contains(text(),'Save And Create Participant')]")
	private WebElement button_SaveAndCreateParticipant;

	@FindBy(xpath = "//p[contains(text(),'Participant updated successfully')]")
	private WebElement popup_updatedsuccessfully;

	@FindBy(xpath = "//div[contains(@class,'time_d_style')][contains(.,'Stage 1')]")
	private WebElement section_Stage1;

	@FindBy(xpath = "//div[contains(@class,'time_d_style')][contains(.,'Stage 2')]")
	private WebElement section_Stage2;

	@FindBy(xpath = "//div[contains(@class,'time_d_style')][contains(.,'Stage 3')]")
	private WebElement section_Stage3;

	@FindBy(xpath = "//div[@id='accordion-controlled-example-heading-1']//i[@class='more-less glyphicon glyphicon-plus']")
	private WebElement icon_FNSexpand;

	@FindBy(xpath = "//div[@id='accordion-controlled-example-heading-2']//i[@class='icon icon-arrow-down']")
	private WebElement icon_Stage2Expand;

	private SeleniumMethods com;
	HCMCommon comm;

	public ProspectiveParticipants_Participant_Detail() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	/*
	 * @author Archana Sept 20 , 2019
	 * 
	 * This Method is use for verify the Prospective Participants Dashboard UI
	 * 
	 */
	public void participant_Details_Dashboard_UI_Verification() {
		com.isElementPresent(title_participantDetails, "Participants detail heading is showing");
		com.isElementPresent(link_Assignedto, "Assigned to is present");
		com.isElementPresent(button_changeAssigneduser, "change Assigned user button is showing");
		com.isElementPresent(button_viewall, "Latest update View all button is present");
		com.isElementPresent(button_EditParticipantsInfo, "button_editparticipants button is present");
		com.isElementPresent(icon_FMSexpand, "FMS expand icon is present");
		com.isElementPresent(button_manageAttachments, "manageAttachments button");
		com.isElementPresent(text_IntakeProgress, "Intake progress is present");

	}

	/*
	 * @author Archana Oct 1 , 2019
	 * 
	 * This Method is use for Validate the Edit Prospective Participants.
	 * 
	 * 
	 * 
	 * 
	 */

	public void verify_EditProspectiveParticipant_UI() {
		com.click(button_EditParticipantsInfo, "button_EditParticipantsInfo");
		com.waitForElementsTobe_NotVisible(comm.loadingTextOrCircleLocator);
		
		com.isElementPresent(heading_EditProspectiveParticipant, "Heading Edit Prospective Participant");

		Tab_RefererDetails refererDetails = new Tab_RefererDetails();
		refererDetails.verifyReferDetailsTab_UI();
		
		Tab_ParticipantDetails participantDetails = new Tab_ParticipantDetails();
		participantDetails.verifyParticipantDetailsTab_UI();
		
		Tab_ParticipantAbility participantAbility = new Tab_ParticipantAbility();
		participantAbility.verifyParticipantAbilityTab_UI();
		
		com.click_UsingAction(button_SaveAndCreateParticipant, "Save n CreateParticipant clicked");
		com.wait(6);
		com.isElementPresent(popup_updatedsuccessfully, "updatedsuccessfully is present");

	}

	/*
	 * @author Archana Oct 1 , 2019
	 * 
	 * This Method is use for Validate the Assigned to Participants.
	 * 
	 * 
	 * 
	 * 
	 */
	public void assignedto_Functionalityvalidation(String data) {
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		com.click_UsingAction(button_changeAssigneduser, "change Assigned user button s clicked"); // Incorrect PAGE OBJECT MODEL??
		StaffMembers sm = new StaffMembers();
		sm.changeCrmUser(data);

	}
	/**
	 * @author Archana Oct 2 , 2019
	 * 
	 * This Method is use for Validate the Intake type stages.
	 * 
	 * @author shailendra Dec 2, 2019
	 */
	public void intake_Progress_Validation() {
		com.isElementPresent(text_IntakeProgress, "Intake progress");

		if (com.isElementPresent(section_Stage1,"section_Stage1")) {
			String stageData = com.getText(section_Stage1);

			if (stageData.contains("NDIS") || stageData.contains("Intake Participant Submission") || stageData.contains("View Attachments & Notes") || stageData.contains("Date:")) {
				CustomReporter.report(STATUS.PASS, "NDIS, Intake Participant Submission, View Attachments & Notes, Date: is present");
			} else {
				CustomReporter.report(STATUS.FAIL, "NDIS, Intake Participant Submission, View Attachments & Notes, Date: is not present");
			}
		}

		if (com.isElementPresent(section_Stage2,"section_Stage2")) {
			String stageData = com.getText(section_Stage2);

			if (stageData.contains("Intake") || stageData.contains("View Attachments & Notes")|| stageData.contains("Date:")) {
				CustomReporter.report(STATUS.PASS, "Intake, View Attachments & Notes, Date: is present");
			} else {
				CustomReporter.report(STATUS.FAIL, "Intake, View Attachments & Notes, Date: is NOT present");
			}

		}
		if (com.isElementPresent(section_Stage3,"section_Stage3")) {
			String stageData = com.getText(section_Stage3);

			if (stageData.contains("Plan Delegation") || stageData.contains("View Attachments & Notes")|| stageData.contains("Date:")) {
				CustomReporter.report(STATUS.PASS, "Intake, View Attachments & Notes, Date: is present");
			} else {
				CustomReporter.report(STATUS.FAIL, "Intake, View Attachments & Notes, Date: is NOT present");
			}


		}

	}

	public void fms_Case_validation() {
		
		com.click_UsingAction(icon_FMSexpand, "FMS expand icon clicked");
		ReactTable rct = new ReactTable(comm.reactTableLocator);
		rct.verifyColumnHeaders(1, "Case ID:", "Category:", "Event Date", "Description");

	}

}
