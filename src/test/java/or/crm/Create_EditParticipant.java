/**
 * 
 */
package or.crm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Archana Oct 1, 2019 3:45:37 PM
 * @author shailendra Dec 2, 2019 Changed the class name to more appropriate name
 */
public class Create_EditParticipant {

	public static final String title = "admin/crm/createParticipant";
	
	@FindBy(xpath = "//a[(text()='Referer Details')]")
	private WebElement link_RefererDetails;

	@FindBy(xpath = "//a[(text()='Participant Details')]")
	private WebElement link_ParticipantDetails;

	@FindBy(xpath = "//a[(text()='Participant Ability')]")
	private WebElement link_ParticipantAbility;

	@FindBy(xpath = "//a[(text()='Shift')]")
	private WebElement link_Shift;

	private SeleniumMethods com;
	HCMCommon comm;

	public Create_EditParticipant() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	/**
	 * @author shailendra Dec 3, 2019
	 * */
	public void openReferDetailsTab() {
		com.click_UsingAction(link_RefererDetails, "link_RefererDetails");
	}
	
	/**
	 * @author shailendra Dec 3, 2019
	 * */
	public void openParticipantDetailsTab() {
		com.click_UsingAction(link_ParticipantDetails, "link_ParticipantDetails");
	}
	
	/**
	 * @author shailendra Dec 3, 2019
	 * */
	public void openParticipantAbilityTab() {
		com.click_UsingAction(link_ParticipantAbility, "link_ParticipantAbility");
	}
	
	/**
	 * @author shailendra Dec 3, 2019
	 * */
	public void openShiftTab() {
		com.click_UsingAction(link_Shift, "link_ParticipantAbility");
	}

}
