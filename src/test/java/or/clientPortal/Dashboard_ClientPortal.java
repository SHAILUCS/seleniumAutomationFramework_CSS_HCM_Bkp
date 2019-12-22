/**
 * 
 */
package or.clientPortal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shwetha S Oct 29, 2019
 *
 */
public class Dashboard_ClientPortal {

	public static final String title = "dashboard";
	SeleniumMethods com;
	HCMCommon comm;

	public Dashboard_ClientPortal() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//a[contains(.,'Messages & Notiﬁcations')]")
	public WebElement messagesAndNotiﬁcations;

	@FindBy(xpath = "//textarea[@class='text_Notif']")
	public WebElement text_Area;

	@FindBy(xpath = "//button[contains(.,'Send')]")
	public WebElement button_Send;

	@FindBy(xpath = "//h1[contains(.,'Messages & Notifications')]")
	public WebElement header_text_MessagesAndNotifications;

	@FindBy(xpath = "//i[@class='icon icon-log-out log_out_icon']")
	public WebElement icon_logout;

	public void verifyTheMail() {
		com.wait(2);
		com.click(messagesAndNotiﬁcations, "click on messages");
		com.isElementPresent(header_text_MessagesAndNotifications, "MessagesAndNotifications is present");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.click(tab.getCellObject(2, 4), "Click on newly created Mail");
	}

	public void sendBackTheReplyForMail() {
		com.wait(2);
		com.click(messagesAndNotiﬁcations, "click on messages");
		com.wait(2);
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.click(tab.getCellObject(2, 4));
		com.waitForElementsTobe_Present(text_Area);
		com.sendKeys(text_Area, "test Replying back from Client to Admin");
		com.click(button_Send);
		com.click(icon_logout);
		com.navigateTo("https://admin.dev.healthcaremgr.net");
	}

}
