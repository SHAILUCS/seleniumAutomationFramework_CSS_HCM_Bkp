package or.organization;

import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class Organization_Dashboard {
	public static final String title = "admin/organisation/dashboard";
	SeleniumMethods com;
	HCMCommon comm;
	
	public Organization_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
		
	}

}
