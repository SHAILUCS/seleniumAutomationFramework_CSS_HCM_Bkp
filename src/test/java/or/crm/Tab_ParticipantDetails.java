/**
 * 
 */
package or.crm;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shailendra Dec 3, 2019 10:34:32 AM
 *
 */
public class Tab_ParticipantDetails extends Create_EditParticipant {

	public static final String sampleFilePath = Constant.getTestDataFolderPath() + "/" + "CRM" + "/"
			+ "createProspectiveParticipant" + "/" + "sample.pdf";
	public static final String sampleFilePath2 = Constant.getTestDataFolderPath() + "/" + "CRM" + "/"
			+ "createProspectiveParticipant" + "/" + "sample2.pdf";

	SeleniumMethods com;
	HCMCommon comm;

	public Tab_ParticipantDetails() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(name = "ndisno")
	private WebElement text_NdisNumber;

	@FindBy(name = "firstname")
	private WebElement text_FirstName;

	@FindBy(name = "lastname")
	private WebElement text_LastName;

	@FindBy(name = "preferredfirstname")
	private WebElement text_PreferredFirstName;

	@FindBy(name = "email")
	private WebElement text_Email;

	@FindBy(name = "phonenumber")
	private WebElement text_PhoneNumber;

	@FindBy(name = "Dob")
	private WebElement date_Dob;

	@FindBy(name = "Address")
	private WebElement text_Address;

	@FindBy(xpath = "//label[contains(.,'state')]//following-sibling::div//span[@class='Select-arrow-zone']")
	private WebElement select_State;

	@FindBy(name = "postcode")
	private WebElement text_PostCode;

	@FindBy(name = "medicare")
	private WebElement text_Medicare;

	@FindBy(name = "crn")
	private WebElement text_Crn;

	@FindBy(xpath = "//label[contains(.,'Preferred Contact')]//following-sibling::div//span[@class='Select-arrow-zone']")
	private WebElement select_PreferredContact;

	@FindBy(xpath = "//label[contains(.,'Marital Status')]//following-sibling::div//span[@class='Select-arrow-zone']")
	private WebElement select_MaritalStatus;

	@FindBy(xpath = "//label[contains(.,'Living Situation')]//following-sibling::div//span[@class='Select-arrow-zone']")
	private WebElement select_LivingSituation;

	@FindBy(css = "input[name='aboriginal_tsi'][value='0']")
	private WebElement radio_Aboriginal_No;

	@FindBy(css = "input[name='aboriginal_tsi'][value='1']")
	private WebElement radio_Aboriginal_Yes;

	@FindBy(css = "input[name='gender'][value='0']")
	private WebElement radio_Gender_Female;

	@FindBy(css = "input[name='gender'][value='1']")
	private WebElement radio_Gender_Male;

	@FindBy(css = "input[name='current_behavioural'][value='1']")
	private WebElement radio_CurrentBehavioural_Yes;

	@FindBy(css = "input[name='current_behavioural'][value='0']")
	private WebElement radio_CurrentBehavioural_No;

	@FindBy(css = "input[name='hearing_file']")
	private WebElement file_CurrentBehavioural_Yes_FileUpload;

	@FindBy(css = "input[name='other_relevent_plans'][value='0']")
	private WebElement radio_OtherRelevantPlans_No;

	@FindBy(css = "input[name='other_relevent_plans'][value='1']")
	private WebElement radio_OtherRelevantPlans_Yes;

	@FindBy(css = "input[name='plan_management'][value='1']")
	private WebElement radio_PlanMgmt_SelfManaged;

	@FindBy(css = "input[name='plan_management'][value='2']")
	private WebElement radio_PlanMgmt_PortalManaged;

	@FindBy(css = "input[name='plan_management'][value='3']")
	private WebElement radio_PlanMgmt_Provider;

	@FindBy(name = "provide_plan")
	private WebElement text_PlanMgmtProvider_Name;

	@FindBy(name = "provide_email")
	private WebElement text_PlanMgmtProvider_Email;

	@FindBy(name = "provide_address")
	private WebElement text_PlanMgmtProvider_Address;

	@FindBy(xpath = "//label[contains(.,'Plan Management Provider')]//following::*/label[contains(.,'state')]//following-sibling::div//span[@class='Select-arrow-zone']")
	private WebElement select_PlanMgmtProvider_State;

	@FindBy(name = "provide_postcode")
	private WebElement text_PlanMgmtProvider_PostCode;

	@FindBy(name = "ndis_file")
	private WebElement file_NdisFile;

	@FindBy(xpath = "(//i[contains(@class,'icon-add')])[1]")
	private WebElement icon_NextOfKin_Add;

	@FindBy(xpath = "(//i[contains(@class,'icon-add')])[2]")
	private WebElement icon_BookerDetails_Add;

	@FindBy(xpath = "//form[@id='partcipant_details']//a[@class='btn-1'][contains(text(),'Save And Continue')]")
	private WebElement button_SaveAndContinue;

	/**
	 * @author Archana Oct 1, 2019 3:45:37 PM
	 * 
	 *         This method is used for varify participants detail of partcipants.
	 * 
	 * @author shailendra Dec 3, 2019
	 */
	public void verifyParticipantDetailsTab_UI() {
		CustomReporter.createNode("Participant Details Section");
		com.isElementPresent(text_NdisNumber, "NdisNumber text");
		com.isElementPresent(text_FirstName, "FirstName text");
		com.isElementPresent(text_LastName, "LastName text");
		com.isElementPresent(text_PreferredFirstName, "PreferredFirstName text");
		com.isElementPresent(text_Email, "Email text");
		com.isElementPresent(text_PhoneNumber, "PhoneNumber text");
		com.isElementPresent(date_Dob, "Dob date");
		com.isElementPresent(text_Address, "Address text");
		com.isElementPresent(select_State, "State select");
		com.isElementPresent(text_PostCode, "PostCode text");
		com.isElementPresent(text_Medicare, "Medicare text");
		com.isElementPresent(text_Crn, "Crn text");
		com.isElementPresent(select_PreferredContact, "PreferredContact select");

		CustomReporter.createNode("Participant Living Situation");
		com.isElementPresent(select_MaritalStatus, "MaritalStatus select");
		com.isElementPresent(select_LivingSituation, "LivingSituation select");
		com.isElementPresent(radio_Aboriginal_No, "Aboriginal_No radio");
		com.isElementPresent(radio_Aboriginal_Yes, "Aboriginal_Yes radio");
		com.isElementPresent(radio_Gender_Female, "Gender_Female radio");
		com.isElementPresent(radio_Gender_Male, "Gender_Male radio");
		com.isElementPresent(radio_CurrentBehavioural_Yes, "CurrentBehavioural_Yes radio");
		com.isElementPresent(radio_CurrentBehavioural_No, "CurrentBehavioural_No radio");
		com.isElementPresent(file_CurrentBehavioural_Yes_FileUpload, "CurrentBehavioural_Yes_FileUpload file");
		com.isElementPresent(radio_OtherRelevantPlans_No, "OtherRelevantPlans_No radio");
		com.isElementPresent(radio_OtherRelevantPlans_Yes, "OtherRelevantPlans_Yes radio");

		CustomReporter.createNode("Participant NDIS Plan:");
		com.isElementPresent(radio_PlanMgmt_SelfManaged, "PlanMgmt_SelfManaged radio");
		com.isElementPresent(radio_PlanMgmt_PortalManaged, "PlanMgmt_PortalManaged radio");
		com.isElementPresent(radio_PlanMgmt_Provider, "PlanMgmt_Provider radio");
		com.isElementPresent(text_PlanMgmtProvider_Name, "PlanMgmtProvider_Name text");
		com.isElementPresent(text_PlanMgmtProvider_Email, "PlanMgmtProvider_Email text");
		com.isElementPresent(text_PlanMgmtProvider_Address, "PlanMgmtProvider_Address text");
		com.isElementPresent(select_PlanMgmtProvider_State, "PlanMgmtProvider_State select");
		com.isElementPresent(text_PlanMgmtProvider_PostCode, "PlanMgmtProvider_P text");
		com.isElementPresent(file_NdisFile, "NdisFile file");
		com.isElementPresent(icon_NextOfKin_Add, "NextOfKin_Add icon");
		com.isElementPresent(icon_BookerDetails_Add, "BookerDetails_Add icon");

		com.isElementPresent(button_SaveAndContinue, "SaveAndContinue button");
	}

	/**
	 * This method will click on Save and Continue button and will verify next tab
	 * opened or not
	 * 
	 * @author shailendra Dec 2, 2019
	 */
	public void saveParticipantDetails() {
		com.click_UsingAction(button_SaveAndContinue, "Save n continue clicked");
		com.wait(2);
		String clsName = com.getAttribute(By.xpath("//li[contains(.,'Participant Ability')]"), "class");

		if (clsName.toLowerCase().contains("active")) {
			CustomReporter.report(STATUS.PASS, "Participants Ability Tab opened");
		} else {
			CustomReporter.report(STATUS.FAIL, "Participants Ability Tab NOT opened");
		}
	}

	/**
	 * Fills the Participant Details Section form
	 * 
	 * @author shailendra Dec 3, 2019
	 * @param ndisNum
	 * @param fName
	 * @param lName
	 * @param prefFName
	 * @param email
	 * @param phone
	 * @param dob         date string should be in dd/MM/YYYY format only
	 * @param streetAdd
	 * @param state
	 * @param post
	 * @param medicareNum
	 * @param crnNum
	 */
	public String fillParticipantDetails(String ndisNum, String fName, String lName, String prefFName, String email,
			String phone, String dob, String streetAdd, String state, String post, String medicareNum, String crnNum) {

		String ndis_Unique_9_Digit = Util.getTimeStamp_InMilliSec().substring(4, 13);

		CustomReporter.createNode("Filling Participant Details Section form");

		if (ndisNum != null) {
			ndis_Unique_9_Digit = ndisNum;
			com.sendKeys("NDIS Num", text_NdisNumber, ndis_Unique_9_Digit);
		} else {
			com.sendKeys("NDIS Num", text_NdisNumber, ndis_Unique_9_Digit);
		}

		if (fName != null) {
			com.sendKeys("FName", text_FirstName, fName);
		}

		if (lName != null) {
			com.sendKeys("LName", text_LastName, lName);
		}

		if (prefFName != null) {
			com.sendKeys("prefFName", text_PreferredFirstName, prefFName);
		}

		String newEmail = email;
		if (email != null) {
			if (!newEmail.contains("+")) {
				String emailParts[] = newEmail.split("@");
				emailParts[0] = emailParts[0] + "+" + new Date().getTime();
				newEmail = emailParts[0] + "@" + emailParts[1];
			}

			com.sendKeys("Email", text_Email, newEmail);
		}

		if (phone != null) {
			com.sendKeys("Phone", text_PhoneNumber, phone);
		}

		if (dob != null) {
			comm.selectDate(date_Dob, dob);
		}

		if (streetAdd != null) {
			com.sendKeys(text_Address, streetAdd + Keys.ENTER);
		}

		if (state != null) {
			CustomReporter.report(STATUS.WARNING, "state field is not editable");
		}

		if (post != null) {
			CustomReporter.report(STATUS.WARNING, "post field is not editable");
		}

		if (medicareNum != null) {
			com.sendKeys("MedicareNum", text_Medicare, medicareNum);
		}

		if (crnNum != null) {
			com.sendKeys("Crn Num", text_Crn, crnNum);
		}

		return ndis_Unique_9_Digit;
	}

	/**
	 * 
	 * Make sure all parameters have same array length
	 * 
	 * Usage
	 *
	 * <pre>
	 * fillNextOfKins(Util.getArray("FName1", "FName2", "FName3"), Util.getArray("LName1", "LName2", "LName3"),
	 * 		Util.getArray("Brother", "Sister", "Father"), Util.getArray("1234567890", "1234567891", "1234567892"),
	 * 		Util.getArray("abcd@gmail.com", "abcde@gmail.com", "abcdf@gmail.com"));
	 * </pre>
	 * 
	 * @author shailendra Dec 3, 2019
	 */
	public void fillNextOfKins(String[] fnameArr, String[] lnameArr, String[] relationArr, String[] phoneArr,
			String[] emailArr) {

		for (int i = 0; i < fnameArr.length; i++) {

			com.sendKeys(By.name("first_name_" + i), fnameArr[i]);
			com.sendKeys(By.name("last_name_" + i), lnameArr[i]);
			comm.selectByVisibleText(By.xpath("//input[@name='last_name_'" + i
					+ "]/../..//following-sibling::div//span[@class='Select-arrow-zone']"), relationArr[i]);
			com.sendKeys(By.name("phone_" + i), phoneArr[i]);
			com.sendKeys(By.name("email_" + i), emailArr[i]);

			if (i < fnameArr.length - 1) {
				com.click(icon_NextOfKin_Add, "icon_NextOfKin_Add");
			}
		}
	}

	/**
	 * 
	 * Make sure all parameters have same array length
	 * 
	 * Usage
	 *
	 * <pre>
	 * fillBookerDetails(Util.getArray("BFName1", "BFName2", "BFName3"), Util.getArray("BLName1", "BLName2", "BLName3"),
	 * 		Util.getArray("Father", "Sister", "Mother"), Util.getArray("1234567811", "1234567822", "1234567833"),
	 * 		Util.getArray("bbabcd@gmail.com", "bbabcde@gmail.com", "bbabcdf@gmail.com"));
	 * </pre>
	 * 
	 * @author shailendra Dec 3, 2019
	 */
	public void fillBookerDetails(String[] fnameArr, String[] lnameArr, String[] relationArr, String[] phoneArr,
			String[] emailArr) {

		for (int i = 0; i < fnameArr.length; i++) {

			com.sendKeys(By.name("first_name_b_" + i), fnameArr[i]);
			com.sendKeys(By.name("last_name_b_" + i), lnameArr[i]);
			comm.selectByVisibleText(By.xpath("//input[@name='last_name_b_'" + i
					+ "]/../..//following-sibling::div//span[@class='Select-arrow-zone']"), relationArr[i]);
			com.sendKeys(By.name("phone_b_" + i), phoneArr[i]);
			com.sendKeys(By.name("email_b_" + i), emailArr[i]);

			if (i < fnameArr.length - 1) {
				com.click(icon_BookerDetails_Add, "icon_BookerDetails_Add");
			}
		}
	}

	/**
	 * Fills the Participant Living Situation Section form
	 * 
	 * sample
	 * 
	 * <pre>
	 * fillParticipantLivingSituation("Single", "At risk (eg. eviction, behind in rent, family violence)", true, true,
	 * 		true, Tab_ParticipantDetails.sampleFilePath, true)
	 * </pre>
	 * 
	 * @author shailendra Dec 3, 2019
	 * @param maritalStatus
	 * @param livingSituation
	 * @param aboriginal_TrueForYes
	 * @param gender_TrueForMale
	 * @param supportPlan_TrueForYes
	 * @param supportPlanDocPath Tab_ParticipantDetails.sampleFilePath
	 * @param otherReleventPlans_TrueForYes
	 */
	public void fillParticipantLivingSituation(String maritalStatus, String livingSituation,
			boolean aboriginal_TrueForYes, boolean gender_TrueForMale, boolean supportPlan_TrueForYes,
			String supportPlanDocPath, boolean otherReleventPlans_TrueForYes) {

		CustomReporter.createNode("Filling Participant Living Situation Section form");

		if (maritalStatus != null) {
			comm.selectByVisibleText(select_MaritalStatus, maritalStatus);
		}

		if (livingSituation != null) {
			comm.selectByVisibleText(select_LivingSituation, livingSituation);
		}

		if (aboriginal_TrueForYes) {
			com.javaScript_Click(radio_Aboriginal_Yes, "radio_Aboriginal_Yes");
		}

		if (gender_TrueForMale) {
			com.javaScript_Click(radio_Gender_Male, "radio_Gender_Male");
		}

		if (supportPlan_TrueForYes) {
			com.javaScript_Click(radio_CurrentBehavioural_Yes, "radio_CurrentBehaviouralSupportPlan_Yes");

			if (supportPlanDocPath != null) {
				com.sendKeys(file_CurrentBehavioural_Yes_FileUpload, supportPlanDocPath);
			}

		}

		if (otherReleventPlans_TrueForYes) {
			com.javaScript_Click(radio_OtherRelevantPlans_Yes, "radio_OtherRelevantPlans_Yes");
		}

	}

	/**
	 * Fills the Participant NDIS Plan Section form
	 * 
	 * sample
	 * 
	 * <pre>
	 * fillParticipantNdisPlan("Provider", "ProviderName", "provider@email.com", "ProviderAddress", "ACT", "3000",
	 * 		Tab_ParticipantDetails.sampleFilePath2)
	 * </pre>
	 * 
	 * @author shailendra Dec 3, 2019
	 * @param selfOrPortalOrProvider        values can be
	 *                                      null/"Self"/"Portal"/"Provider"
	 * @param nameOfProvider
	 * @param emailOfProvider
	 * @param addressOfProvider
	 * @param stateOfProvider
	 * @param postcodeOfProvider
	 * @param ndisPlanFilePath Tab_ParticipantDetails.sampleFilePath2
	 */
	public void fillParticipantNdisPlan(String selfOrPortalOrProvider, String nameOfProvider, String emailOfProvider,
			String addressOfProvider, String stateOfProvider, String postcodeOfProvider, String ndisPlanFilePath) {

		CustomReporter.createNode("Filling Participant NDIS Plan Section form");

		if (selfOrPortalOrProvider != null) {

			switch (selfOrPortalOrProvider) {
			case "Self":
				com.javaScript_Click(radio_PlanMgmt_SelfManaged, "radio_Plan Mgmt_Self Managed");
				break;
				
			case "Portal":
				com.javaScript_Click(radio_PlanMgmt_PortalManaged, "radio_Plan Mgmt_Portal Managed");
				break;
				
			case "Provider":
				com.javaScript_Click(radio_PlanMgmt_Provider, "radio_Plan Mgmt_Portal Managed");
				break;

			default:
				CustomReporter.report(STATUS.ERROR, "In correct Option provided pls provide any one from \"Self\"/\"Portal\"/\"Provider\"");
				break;
			}

		}

		if (nameOfProvider != null) {
			com.sendKeys("PlanMgmtProvider_Name",text_PlanMgmtProvider_Name, nameOfProvider);
		}

		if (emailOfProvider!=null) {
			com.sendKeys("PlanMgmtProvider_Email",text_PlanMgmtProvider_Email, emailOfProvider);
		}

		if (addressOfProvider!=null) {
			com.sendKeys("PlanMgmtProvider_Address",text_PlanMgmtProvider_Address, addressOfProvider);
		}

		if (stateOfProvider != null) {
			comm.selectByVisibleText(select_State, stateOfProvider);
		}

		if (postcodeOfProvider != null) {
			com.sendKeys("PostcodeOfProvider",text_PlanMgmtProvider_PostCode, postcodeOfProvider);
		}
		
		if (ndisPlanFilePath != null) {
			com.sendKeys(file_NdisFile, ndisPlanFilePath);
		}
	}

}
