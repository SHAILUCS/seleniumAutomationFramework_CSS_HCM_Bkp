package or.schedule;

import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class Schedule_Dashboard {
	public static final String title = "admin/schedule/unfilled/unfilled";
	SeleniumMethods com;
	HCMCommon comm;
	
	public Schedule_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
		
	}

}
