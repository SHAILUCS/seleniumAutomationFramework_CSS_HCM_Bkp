/**
 * 
 */
package or.clientPortal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Constant;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;
import com.xlUtil.DataTable;

import or.common.HCMCommon;

/**
 * @author Shwetha S Oct 29, 2019
 */
public class LoginPage_ClientPortal {

	SeleniumMethods com;
	HCMCommon comm;

	public LoginPage_ClientPortal() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//input[@name='username']")
	public WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	public WebElement password;

	@FindBy(xpath = "//button[@class='but_login']")
	public WebElement button_submit;

	public void loginToClientPortal(String uname, String pwd) {

		CustomReporter.createNode("Navigating and Logging in to Client Portal");

		CustomReporter.createNode("Navigating and Logging in to Client Portal");

		DataTable xl = new DataTable(Constant.getTestDataFilePath(), Constant.get_ClientPortal_EnvironmentInfoSheet());
		
		com.navigateTo(xl.getValue(1, 0));
		
		com.sendKeys(username, uname);
		com.sendKeys(password, pwd);
		com.click(button_submit);
		
		com.verifyPageUrl(Dashboard_ClientPortal.title, true);
		
	}

}
