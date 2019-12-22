package or.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class SideBar_Admin {

	SeleniumMethods com;
	HCMCommon comm;

	public SideBar_Admin() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	@FindBy(xpath = "//div[contains(@class,'sideHead')]")
	public WebElement heading_Admin;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Approvals')]")
	public WebElement link_Approvals;

	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Reports')]")
	public WebElement link_Reports;

	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Logs')]")
	public WebElement link_Logs;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'User Management')]")
	public WebElement link_UserManagement;
	
	public void verifyUI_LeftNavigationPane() {
		CustomReporter.createNode("verifying Left Navigation Pane Content");
		com.isElementPresent(heading_Admin, "heading_Admin");
		com.isElementPresent(link_Approvals, "link_Approvals");
		com.isElementPresent(link_Reports, "link_Reports");
		com.isElementPresent(link_Logs, "link_Logs");
		com.isElementPresent(link_UserManagement, "link_UserManagement");
	}


}
