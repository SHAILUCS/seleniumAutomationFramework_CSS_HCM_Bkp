/**
 * 
 */
package or.planManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.common.LoginPage;

/**
 * @author shwetha Nov 4, 2019 6:06:15 PM
 *
 */
public class LoginPage_PlanManagement {


	SeleniumMethods com;
	HCMCommon comm;

	public LoginPage_PlanManagement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//input[@placeholder='Username']")
	public WebElement userName; 
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement password;
	
	@FindBy(xpath = "//span[contains(text(),'Remember Me')]")
	public WebElement remember_Me;
	
	@FindBy(xpath = "//input[@value='Logon']")
	public WebElement button_Logon;
	
	@FindBy(xpath = "//a[contains(.,'Forgot Password?')]")
	public WebElement link_Forgot_Password;
	


	/**
	 * @param type
	 */
	public void performIntelligentLogin(String type) {
		LoginPage.sendUserPassAndClickLogin(userName, password, button_Logon, type);
	}
	
	
}
