/**
 * 
 */
package or.participant;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Archana Sep 20, 2019 4:28:00 PM
 *
 */
public class ParticipantDetails {
//public static final String title = "admin/participant/dashboard";
	
	SeleniumMethods com;
	HCMCommon comm;
	
	
	
	
	
	public ParticipantDetails() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
           
	
	
	@FindBy(xpath = "//div[@class='row mt-4']//div[1]")
	public WebElement link_UpadateAbout;
	
	@FindBy(xpath = "//input[@name='ndis_num']")
	public WebElement ndisNum;
	
	@FindBy(xpath = "//button[contains(.,'Update')]")
	public WebElement button_update;
	
	
	
	/*
	public void EditFname(){
		com.waitForElementsTobe_Present(link_UpadateAbout);
		com.click(link_UpadateAbout);
		com.wait(4);
	    com.click(ndisNum);
		com.wait(4);
		com.sendKeys("1234");
		com.wait(4);
		com.click(button_update);
		
	}
	*/

}
