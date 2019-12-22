/**
 * 
 */
package or.crm.editProspectiveParticipants;

import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.common.LoginPage;

/**
 * @author Archana Oct 3, 2019 5:14:21 PM
 *
 */
public class ParticipantDetails_CreatePartcipant {

	@FindBy(name="ndisno")
	private WebElement text_INDSNo;
	
	@FindBy(name="Address")
	private WebElement text_Address;
	
	@FindBy(xpath = "//div[@class='Select-value']//span[contains(.,'Select Martial State')]")
	private WebElement dropdown_MaritalStatus;
	
	@FindBy(xpath = "//label[contains(text(),'Living Situation')]/following-sibling::div")
	private WebElement dropdown_Living ;
	
	@FindBy(name="plan_management")
	private WebElement rodiobutton_Planmanagement;
	
	@FindBy(xpath = "//form[@id='partcipant_details']//a[@class='btn-1'][contains(text(),'Save And Continue')]")
	private WebElement button_Save;
	
	@FindBy(xpath="//label[contains(text(),'state')]/following-sibling::div//input")
	private WebElement dropdown_State;

	@FindBy(xpath="//input[@name='postcode']")
	private WebElement text_Pin;

 


	private SeleniumMethods com;
	HCMCommon comm;

	public ParticipantDetails_CreatePartcipant() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	
	public String PrticipantDetailFillNDISNo(String num) {
		if (num != null) {
			com.sendKeys("INDSNo", text_INDSNo, num);
	
			
			
		}
		return num;
	}
		/**
		 * 
		 */
		public void ParticipantDetailFllAddress(String Address) {
			if (Address != null) {
				com.sendKeys("Address", text_Address, Address);
				com.wait(5);
				com.sendKeys(text_Address, Keys.DOWN, Keys.ENTER);
				
				//com.click(By.xpath("//div[@class='Select-menu-outer']//div[@role='option'][contains(.,'"+Address+"')]"),"Street Address:["+Address+"]");
				com.wait(1);
				
			/*
			 * com.sendKeys(text_Address, "Arrival Drive");
			 * comm.selectByVisibleText(dropdown_State,"VIC"); com.sendKeys(text_Pin,
			 * "3043");
			 */
				
		}
			
		}
		
		/**
		 * 
		 */
		public void Marital_Status() {
			comm.selectByVisibleText(dropdown_MaritalStatus, "Single");

		}
		
	
		/**
		 * 
		 */
		public void Living_Situation() {
			comm.selectByVisibleText(dropdown_Living, "Homeless");
			com.click(rodiobutton_Planmanagement);
			com.click(button_Save);
			clickPartcipantsdetailPage();
		}
	
	
		private void clickPartcipantsdetailPage() {
			ParticipantAbility_CreatePartcipant prc = new ParticipantAbility_CreatePartcipant();
			com.click(prc.link_ParticipantAbility);

		}
}