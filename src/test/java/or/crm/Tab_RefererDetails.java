/**
 * 
 */
package or.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shailendra Dec 3, 2019 10:26:47 AM
 *
 */
public class Tab_RefererDetails extends Create_EditParticipant {
	SeleniumMethods com;
	HCMCommon comm;

	public Tab_RefererDetails() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//div[(text()='Referer Details')]")
	private WebElement title_RefererDetails;

	@FindBy(xpath = "//input[@name='first_name']")
	private WebElement text_FirstName;

	@FindBy(xpath = "//input[@name='last_name']")
	private WebElement text_LastName;

	@FindBy(xpath = "//input[@name='organisation']")
	private WebElement text_Organisation;

	@FindBy(xpath = "//input[@name='remail']")
	private WebElement text_Email;

	@FindBy(xpath = "//input[@name='phone_number']")
	private WebElement text_Phonenumber;

	@FindBy(xpath = "//label[contains(.,'Relationship to Participant')]//following-sibling::div//span[@class='Select-arrow-zone']")
	private WebElement dropdown_RelationshipToParticipant;

	@FindBy(xpath = "//form[@id='referral_details']//a[@class='btn-1'][contains(text(),'Save And Continue')]")
	private WebElement button_SaveAndContinue;

	/**
	 * @author Archana Oct 1, 2019 3:45:37 PM
	 *
	 *         This method is used for varify reffer detail of participants.
	 * 
	 * @author shailendra Dec 2, 2019 changed the name to more appropriate name
	 */
	public void verifyReferDetailsTab_UI() {
		openReferDetailsTab();

		com.isElementPresent(title_RefererDetails, "title_RefererDetails is Present");
		com.isElementPresent(text_FirstName, "input_firstname");
		com.isElementPresent(text_LastName, "input_Lastname");
		com.isElementPresent(text_Organisation, "input_Organisation");
		com.isElementPresent(text_Email, "input_Email");
		com.isElementPresent(text_Phonenumber, "input_PhoneNumber");
		com.isElementPresent(dropdown_RelationshipToParticipant, "dropdown_RelationshipToParticipant");
		com.isElementPresent(button_SaveAndContinue, "button_SaveAndContinue");
	}

	/**
	 * This method will click on Save and Continue button and will verify next tab
	 * opened or not
	 * 
	 * @author shailendra Dec 2, 2019
	 */
	public void saveReferrerDetails() {
		com.click_UsingAction(button_SaveAndContinue, "Save n continue clicked");
		String clsName = com.getAttribute(By.xpath("//li[contains(.,'Participant Details')]"), "class");

		if (clsName.toLowerCase().contains("active")) {
			CustomReporter.report(STATUS.PASS, "Participants Details Tab opened");
		} else {
			CustomReporter.report(STATUS.FAIL, "Participants Details Tab NOT opened");
		}
	}

	/**
	 * @param fname
	 * @param lname
	 * @param org
	 * @param email
	 * @param phno
	 * @param relToPart
	 * @author shailendra Dec 3, 2019
	 */
	public void fillRefererDetails(String fname, String lname, String org, String email, String phno,
			String relToPart) {
		
		
		CustomReporter.createNode("Filling Referer Details");

		
		if (fname != null) {
			com.sendKeys("FName", text_FirstName, fname);
		}
		
		if (lname != null) {
			com.sendKeys("LName", text_LastName, lname);
		}
		
		if (org != null) {
			com.sendKeys("org", text_Organisation, org);
		}
		
		if (email!= null) {
			com.sendKeys("email", text_Email, email);
		}
		
		if (phno != null) {
			com.sendKeys("phno", text_Phonenumber, phno);
		}
		
		if (relToPart != null) {
			comm.selectByVisibleText(dropdown_RelationshipToParticipant, relToPart);
		}
		
	}

}
