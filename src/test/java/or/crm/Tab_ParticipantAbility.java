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
 * @author Shailendra Dec 3, 2019 10:51:20 AM
 *
 */
public class Tab_ParticipantAbility extends Create_EditParticipant {
	SeleniumMethods com;
	HCMCommon comm;

	public Tab_ParticipantAbility() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	@FindBy(xpath = "//span[@id='react-select-8--value-item']")
	private WebElement value_CognitiveLevel;

	@FindBy(xpath = "//form[@id='partcipant_ability']//a[@class='btn-1'][contains(text(),'Save And Continue')]")
	private WebElement button_partcipantAbilitysavebtn;

	/**
	 * @author Archana Oct 1, 2019 3:45:37 PM
	 * 
	 *         This method is used for varify participants Ability of partcipants.
	 */
	public void verifyParticipantAbilityTab_UI() {
		comm.selectByVisibleText(value_CognitiveLevel, "Very Good");
		com.click_UsingAction(button_partcipantAbilitysavebtn, "Save n continue clicked");

		

	}
	
	/**
	 * This method will click on Save and Continue button and will verify next tab opened or not
	 * @author shailendra Dec 2, 2019 
	 */
	private void saveParticipantAbility() {
		String clsName = com.getAttribute(By.xpath("//li[contains(.,'Shift')]"), "class");

		if (clsName.toLowerCase().contains("active")) {
			CustomReporter.report(STATUS.PASS, "Shift Tab opened");
		} else {
			CustomReporter.report(STATUS.FAIL, "Shift Tab NOT opened");
		}
	}	
}
