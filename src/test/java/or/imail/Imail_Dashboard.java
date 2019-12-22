package or.imail;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class Imail_Dashboard {
	public static final String title = "admin/imail/dashboard";
	SeleniumMethods com;
	HCMCommon comm;

	public Imail_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//a/h4[contains(.,'External Imail')]")
	public WebElement link_External_Imail;
	
	@FindBy(xpath = "//a/h4[contains(.,'Internal Imail')]")
	public WebElement link_Internal_Imail;
	
	@FindBy(xpath = "//li[2]//ul[1]//li[1]//a[contains(.,'Inbox')]")
	public WebElement external_Inbox;
	
	@FindBy(xpath = "//li[2]//ul[1]//li[2]//a[contains(.,'Drafts')]")
	public WebElement external_Drafts;
	
	@FindBy(xpath = "//li[2]//ul[1]//li[3]//a[contains(.,'Archived')]")
	public WebElement external_Archived;
	
	@FindBy(xpath = "//li[3]//ul[1]//li[1]//a[contains(.,'Inbox')]")
	public WebElement internal_Inbox;
	
	@FindBy(xpath = "//li[3]//ul[1]//li[2]//a[contains(.,'Drafts')]")
	public WebElement internal_Drafts;
	
	
	@FindBy(xpath = "//li[3]//ul[1]//li[3]//a[contains(.,'Group Messages')]")
	public WebElement internal_Group_Messages;
	
	@FindBy(xpath = "//li[3]//ul[1]//li[4]//a[contains(.,'Archived')]")
	public WebElement internal_Archived;
	
	/**
	 * Use this to verify UI of Imail Dashboard Page
	 * */
	public void verifyUI() {
		com.isElementPresent(link_External_Imail, "External_Imail Center Link");
		com.isElementPresent(link_Internal_Imail, "Internal_Imail Center Link");
		
	}
	
}
