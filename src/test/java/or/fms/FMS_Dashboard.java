package or.fms;

import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class FMS_Dashboard {

public static final String title = "admin/fms/dashboard/new/case_ongoing";
	
	SeleniumMethods com;
	HCMCommon comm;
	
	
	
	
	
	public FMS_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	
}
