/**
 * 
 */
package or.crm.prospectiveParticipants;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Util;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * This Class Use For Participant Ability Page.
 * 
 * @author Archana Oct 4, 2019 3:27:12 PM
 *
 */
public class Participant_Ability {

	@FindBy(xpath = "//a[contains(text(),'Participant Ability')]") 
	public WebElement link_ParticipantAbility;

	@FindBy(xpath = "//div[@class='col-md-6 align-self-center br-1']//div[contains(text(),'Participant Ability')]")
	private WebElement title_ParticipantAbility;

	@FindBy(xpath = "//span[@class='icon icon-back1-ie']")
	private WebElement icon_PriviousArrow;

	@FindBy(xpath = "//div[contains(text(),'Latest Update:')]")
	private WebElement text_LatestUpdate;

	@FindBy(xpath = "//div[@class='Lates_up_txt2 btn-1']")
	private WebElement label_Date;

	@FindBy(xpath = "//div[contains(@class,'Lates_up_btn')]//i")
	public WebElement button_viewall;

	@FindBy(xpath = "//div[@class='col-lg-9 col-md-8 align-self-center par_abil_title'][contains(text(),'Participant Ability')]")
	private WebElement text_ParticipantAbility;

	@FindBy(xpath = "//div[contains(@class,'col-lg')][contains(@class,'col-md')][contains(.,'Participant Cognitive Level')]")
	private WebElement data_ParticipantAbility;

	@FindBy(xpath = "//a[contains(text(),'Edit Participants Ability Info')]")
	public WebElement button_EditParticipantInfo;

	@FindBy(xpath = "//div[@class='overlay_text_p01']")
	private WebElement ber_PercentBar;

	public static final String title = "admin/crm/participantability";
	private SeleniumMethods com;
	HCMCommon comm;

	public Participant_Ability() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	/**
	 * This is Use For Verify Participant Ability Page UI.
	 * 
	 * @author Archana Oct 4, 2019 3:27:12 PM
	 *
	 */

	public void participant_Ability_PageUI() {
		com.click_UsingAction(link_ParticipantAbility, "link_ParticipantAbility");
		com.isElementPresent(title_ParticipantAbility, "title_ParticipantAbility");
		com.isElementPresent(text_LatestUpdate, "text_LatestUpdate");
		com.isElementPresent(text_ParticipantAbility, "text_ParticipantAbility");
		com.isEnabled(icon_PriviousArrow, "icon_PriviousArrow");
		com.isElementPresent(label_Date, "label_Date");
		// com.isElementPresent(element, desc);

		com.isElementPresent(button_viewall, "Latest update View all button is present");

		String text = com.getText(data_ParticipantAbility);

		Util.comparator_PageValues("Participant Ability", text, "Participant Ability");
		Util.comparator_PageValues("Latest Update:", text, "Latest Update:");

		Util.comparator_PageValues("View all Updates", text, "View all Updates");
		Util.comparator_PageValues("Participant Cognitive Level:", text, "Participant Cognitive Level:");

		Util.comparator_PageValues("Edit Participants Ability Info", text, "Edit Participants Ability Info");

		Util.comparator_PageValues("Is the participant of culturally and linguistically diverse background?", text,
				"Is the participant of culturally and linguistically diverse background?:");

		Util.comparator_PageValues("Communication:", text, "Communication:");

		Util.comparator_PageValues("Languages Spoken:", text, "Languages Spoken:");// Please add :

		Util.comparator_PageValues("Hearing impaired interpreter required?:", text,
				"Hearing impaired interpreter required?:");// Please add :

		Util.comparator_PageValues("Language interpreter required:", text, "Language interpreter required:");

		Util.comparator_PageValues("Participant Assistance Requirements:", text,
				"Participant Assistance Requirements:");
		Util.comparator_PageValues("Participant Mobility Requirements:", text, "Participant Mobility Requirements:");

		Util.comparator_PageValues("Relevant Attachments:", text, "Relevant Attachments:");

		Util.comparator_PageValues("Participant Disability", text, "Participant Disability");

		Util.comparator_PageValues("Fomal Diagnosis (Primary):", text, "Fomal Diagnosis (Primary):");

		Util.comparator_PageValues("Fomal Diagnosis (Secondary):", text, "Fomal Diagnosis (Secondary):");

		Util.comparator_PageValues("Other relevant information:", text, "Other relevant information:");
		Util.comparator_PageValues("Relevant Attachments:", text, "Relevant Attachments:");
		Util.comparator_PageValues(
				"Are there any legal issues that may affect services? (eg. apprehended violence order):", text,
				"Are there any legal issues that may affect services? (eg. apprehended violence order):");

	}
	
	

}
