/**
 * 
 */
package or.crm.prospectiveParticipants;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * THis class is use for Participant Detail update view all popup.
 * @author Archana Oct 9, 2019 12:12:24 PM
 *
 */
public class ParticipantDetails_ViewallUpdates {

	
	@FindBy(xpath = "//div[text()='Participant Details - View all Updates']")
	private WebElement heading_ViewallUpdates;
	
	@FindBy(xpath = "//i[@class='icon icon-close1-ie Modal_close_i']")
	private WebElement icon_Cross;
	
	@FindBy(xpath = "//span[@class='Update-all-1aa'][@xpath='1']")
	private WebElement text_Username;
	
	@FindBy(xpath = "//span[@class='Update-all-1ab'][@xpath='1']")
	private WebElement text_Updatetime;
	
	@FindBy(xpath = "//span[@class='Update-all-1ba'][@xpath='1']//strong")
	private WebElement text_UpdateText;
	

	@FindBy(xpath = "//div[contains(text(),'Updates:')]")
	private WebElement title_Updates;
	


	
	
	
	

	public static final String title = "admin/crm/participantability";
	private SeleniumMethods com;
	HCMCommon comm;
  
    
	public ParticipantDetails_ViewallUpdates() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

  /**
   * 
   * This method is use for Latest Update UI validation 
   * @author Archana Oct 9, 2019 13:00:24 PM
   */
public void latestUpdate_Viewall_UI_Verify() {
	Participant_Ability pa = new Participant_Ability();
	com.click_UsingAction(pa.link_ParticipantAbility, "link_ParticipantAbility");
	com.click_UsingAction(pa.button_viewall, "button_viewall");
	com.isElementPresent(title_Updates, "title_Updates");
	com.isElementPresent(title_Updates, "title_Updates");
	com.isElementPresent(title_Updates, "title_Updates");
	com.isElementPresent(icon_Cross, "icon_Cross");
	
}
	
	
	
}
