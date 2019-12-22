/**
 * 
 */
package or.crm.editProspectiveParticipants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Archana Oct 3, 2019 5:14:36 PM
 * This Class is use for Participant Ability Detail
 */
public class ParticipantAbility_CreatePartcipant {

	@FindBy(xpath = "//a[contains(text(),'Participant Ability')]") 
	public WebElement link_ParticipantAbility;

	@FindBy(xpath = "//span[@class='color']")
	private WebElement heading_UpdateParticipantDetails;

	@FindBy(xpath = "//a[@class='close_i']")
	private WebElement icon_cross;

	@FindBy(xpath = "//label[contains(text(),'Participant Cognitive Level:')]/following-sibling::div") 
	public WebElement dropdown_CognitiveLevel;

	@FindBy(xpath = "//label[contains(text(),'Communication:')]/following-sibling::div")
	public WebElement dropdown_Communication;

	@FindBy(xpath = "//div[@class='row mb-5']//div[@class='col-md-6']//div[@class='Scroll_div_parents']")
	private WebElement checklist_AssistanceRequirements;

	@FindBy(xpath = "//div[@class='row mx-0 my-4']//div[@class='col-md-6']")
	private WebElement name;

	@FindBy(xpath = "//input[@type='radio']") 
	public By button_Radio;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement button_Checkbox;

	@FindBy(xpath = "//textarea[@name='primary_fomal_diagnosis_desc']")
	private WebElement inputbox_Diagnosisdesc;

	@FindBy(xpath = "//a[contains(text(),'Save Change')]")
	private WebElement button_Savechange;

	@FindBy(xpath = "//a[contains(text(),'Edit Participants Ability Info')]")
	public WebElement button_EditParticipantInfo;
	
	  private SeleniumMethods com;
	     HCMCommon comm;

	public ParticipantAbility_CreatePartcipant() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();

		
	}

	/**
	 * 
	 * This is Use For Edit and Update Participant Ability PageUI validation.
	 * 
	 * @author Archana Oct 4, 2019 6:40:20 PM
	 *
	 */
	public void edit_Participant_Details_UI_Validation() {
		com.click_UsingAction(link_ParticipantAbility, "link_ParticipantAbility");
        com.click_UsingAction(button_EditParticipantInfo, "button_EditParticipantInfo");
        com.wait(5);
		com.isElementPresent(heading_UpdateParticipantDetails, "heading_UpdateParticipantDetails");
		com.isElementPresent(icon_cross, "icon_cross");
		com.isEnabled(dropdown_CognitiveLevel, "dropdown_CognitiveLevel");
		com.isElementPresent(dropdown_Communication, "dropdown_Communication");
		com.isElementPresent(checklist_AssistanceRequirements, "checklist_AssistanceRequirements");
		//com.isElementPresent(button_Radio, "button_Radio");
		com.isElementPresent(button_Checkbox, "button_Checkbox");
		com.isElementPresent(inputbox_Diagnosisdesc, "inputbox_Diagnosisdesc");
		com.isElementPresent(button_Savechange, "button_Savechange");

	}
	
	/**
	 * 
	 */
	public void fillParticipantAbilityDetails(String Cognitive,String Communication,String Text) {
		
		comm.selectByVisibleText(dropdown_CognitiveLevel, Cognitive);
		
		comm.selectByVisibleText(dropdown_Communication, Communication);
		com.check_Checkbox(button_Checkbox);
		com.sendKeys(inputbox_Diagnosisdesc, Text);
		com.click(button_Savechange);
		clickShiftPage();
	}
	 /**
	 * 
	 */
	private void clickShiftPage() {
		Shift_CreatePartcipant sc = new Shift_CreatePartcipant();
		

	}
}
