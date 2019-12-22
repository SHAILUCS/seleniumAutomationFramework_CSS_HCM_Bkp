package or.member;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class Member_Dashboard {
	
	public static final String title = "admin/member/dashboard";
	SeleniumMethods com;
	HCMCommon comm;
	
	public Member_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
		
	}
	
	
 

}
