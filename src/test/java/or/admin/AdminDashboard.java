package or.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class AdminDashboard {
	
	public static final String title = "admin/dashboard";
	SeleniumMethods com;
	HCMCommon comm;

	public AdminDashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);

		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	@FindBy(xpath = "//a[contains(.,'UUser Management')]")
	public WebElement link_UserManagement;
	
	@FindBy(xpath = ("//a[contains(.,'LLogs')]"))
	public WebElement link_Logs;
	
    @FindBy(xpath = ("//a[contains(.,'AApproval')]"))
	public WebElement link_Approvals;
	
	
}
