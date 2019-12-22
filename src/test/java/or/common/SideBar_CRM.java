package or.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class SideBar_CRM {

	private SeleniumMethods com;
	private HCMCommon comm;
	
	public SideBar_CRM() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	// modify the xpths and use a object instead of strong k
	
	@FindBy(xpath = "//a[.='Dashboard']")
	public WebElement link_Dashboard;
	
	@FindBy(xpath = "//a[.='Prospective Participants']")
	public WebElement link_Prospective_Participants;
	
	@FindBy(xpath = "//a[.='Rejected Participants']")
	public WebElement link_Rejected_Participants;
	
	@FindBy(xpath = "//a[.='Reporting']")
	public WebElement link_Reporting;
	
	@FindBy(xpath = "//a[.='Onbording Analytics']")
	public WebElement link_Onbording_Analytics;
	
	@FindBy(xpath = "//a[.='Service Analytics']")
	public WebElement link_Service_Analytics;
	
	@FindBy(xpath = "//a[.='Schedules']")
	public WebElement link_Schedules;
	
	@FindBy(xpath = "//a[.='User Schedules']")
	public WebElement link_User_Schedules;
	
	@FindBy(xpath = "//a[.='User Tasks']")
	public WebElement link_User_Tasks;
	
	@FindBy(xpath = "//a[.='User Management']")
	public WebElement link_User_Management;
	
	@FindBy(xpath = "//a[.='Staff Members']")
	public WebElement link_Staff_Members;
	
	
	
	
	
	
	
	
	
	
	
}
