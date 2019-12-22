package or.crm.prospectiveParticipants;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.crm.editProspectiveParticipants.ParticipantAbility_CreatePartcipant;

/**
 * @author Archana Oct 1, 2019 7:53:20 PM This is used for all Attachment
 *         Management.
 */
public class Crm_AttachmentManagement {

	@FindBy(xpath = "//div[contains(text(),'Participant Details - Manage Attachments')]")
	private WebElement title_ParticipantDetails_ManageAttachments;

	@FindBy(xpath = "//div[(text()='Participant Details')]")
	private WebElement titel_ParticipantDetails;

	@FindBy(xpath = "//div[contains(text(),'Current Documents')]")
	private WebElement dropdown_Documenttype;

	@FindBy(xpath = "//input[@type='file']")
	private WebElement button_Uploaddocs;
	
    @FindBy(xpath = "//div[.='Participant Disability']//preceding::span[.='Browse']")
	private WebElement button_ParticipantAbilityUpload;
	
    @FindBy(xpath = "//div[.='Participant Disability']//following::span[.='Browse']")
	private WebElement button_ParticipantDisabilityUpload;
	
    @FindBy(xpath = "//form[@id='disability_docs']//span[contains(text(),'Relevant Attachments:')]")
	private WebElement titel_Uploadpopup;
    
    @FindBy(xpath = "//form[@id='disability_docs']//input[@placeholder='Please Enter Your Title']")
	private WebElement input_disabilitydocTitle;
	
    @FindBy(xpath = "//form[@id='disability_docs']//div[@class='modal-body']//input[@type='file']")
	private WebElement button_disability_docs;
     
    @FindBy(xpath = "//form[@id='disability_docs']//a[@class='btn-1']")
	private WebElement button_savedisabilitydocs;
	
            
	private SeleniumMethods com;
	HCMCommon comm;
	ProspectiveParticipants_Participant_Detail pd = new ProspectiveParticipants_Participant_Detail();

	public Crm_AttachmentManagement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();

	}
	/**
	 * @author Archana Oct 1, 2019 7:53:20 PM 
	 * This is used for all Attachment Management.
	 */
	public void select_Doc_Catogary() {
		com.click(pd.button_manageAttachments, "manage Attachments button");
		com.isElementPresent(title_ParticipantDetails_ManageAttachments,"ParticipantDetails_ManageAttachments is present");
		com.isElementPresent(titel_ParticipantDetails, "Participant Details is present");
		comm.selectByVisibleText(dropdown_Documenttype, "NDIS Plan");
		comm.uploadFile(button_Uploaddocs, Constant.getTestDataFolderPath()+"/fileUploadTest.PNG");
		
	}
     
	/**
	 * @author Archana Oct 11, 2019 1:53:20 PM 
	 * 
	 * This methos is use for Participants Ability doc upload at 
	 */
	public void ParticipantAbilityUpload() {
		click_On_Participant_Ability();
		//com.isElementPresent(button_ParticipantAbilityUpload, "button_ParticipantAbilityUpload");
		com.click(button_ParticipantAbilityUpload, "button_ParticipantAbilityUpload");
		com.wait(2);
		participantDocUpload();

		com.wait(2);
		
	}
	public void participantDisabilityUpload() {
		
		click_On_Participant_Ability();
		com.click(button_ParticipantDisabilityUpload, "button_ParticipantDisabilityUpload");
		participantDocUpload();
		com.wait(2);
		
		
	}
	/**
	 * @author Archana Oct 11, 2019 5:53:20 PM
	 * This method is use for doc upload at participant ability.
	 */
	private void participantDocUpload() {
		
		com.isElementPresent(titel_Uploadpopup, "titel_Uploadpopup");
		com.sendKeys("input_disabilitydocTitle", input_disabilitydocTitle, "Demo test Doc");
		comm.uploadFile(button_disability_docs, Constant.getTestDataFolderPath()+"/fileUploadTest.PNG");
		com.click_UsingAction(button_savedisabilitydocs, "button_savedisabilitydocs");
	}
	/**
	 * 
	 */
	private void click_On_Participant_Ability() {
		ParticipantAbility_CreatePartcipant pc=new ParticipantAbility_CreatePartcipant();
		com.click_UsingAction(pc.link_ParticipantAbility, "link_ParticipantAbility");

	}
	 
	/**
	  * @author Archana Oct 23, 2019 12:14:20 PM
	  * this method is for validating UI of a relavant attchment 
	 * 
	 */
	public void relavant_Attachment_UI_validation() {
		click_On_Participant_Ability();
		com.isElementPresent(button_ParticipantDisabilityUpload, "button_ParticipantDisabilityUpload");
		com.isElementPresent(button_ParticipantAbilityUpload, "button_ParticipantAbilityUpload");
		
	}
	
}