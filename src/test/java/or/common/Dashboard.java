package or.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class Dashboard {
	
	public static final String title = "admin/dashboard";
	SeleniumMethods com;
	HCMCommon comm;

	@FindBy(partialLinkText = "Recruitment")
	public WebElement link_Recruitment;
	
	@FindBy(partialLinkText = "Participants")
	public WebElement link_Participants;
	
	@FindBy(partialLinkText = "FMS")
	public WebElement link_FMS;
	
	@FindBy(xpath="//small[contains(text(),'incidents')]")
	public WebElement link_FMS_Incidents;
	
	
	@FindBy(partialLinkText = "Imail")
	public WebElement link_Imail;
	
	@FindBy(partialLinkText = "CRM Admin")
	public WebElement link_CRM_Admin;
	
	@FindBy(partialLinkText = "CRM User")
	public WebElement link_CRM_User;
	
	
	
	@FindBy(xpath = "//a[contains(.,'AAdmin')]")
	public WebElement link_Admin;

	@FindBy(name = "pin")
	public WebElement text_Pin;
	
	@FindBy(xpath="//ul[@class='but_around_second text-center']//a[@title='Members']")
	public WebElement link_Members;
	
	@FindBy(xpath="//ul[@class='but_around_second text-center']//a[@title='Organisation']")
	public WebElement link_Organisation;
	
	@FindBy(xpath="//ul[@class='but_around_second text-center']//a[@title='Schedule']")
	public WebElement link_Schedule;
	
	
	public Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		
			com = new SeleniumMethods();
			comm = new HCMCommon();
		}
	
	
}
