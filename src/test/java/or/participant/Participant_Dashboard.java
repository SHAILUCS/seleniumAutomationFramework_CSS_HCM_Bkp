package or.participant;

import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class Participant_Dashboard {

	public static final String title = "admin/participant/dashboard";
	
	SeleniumMethods com;
	HCMCommon comm;
	
	
	
	
	
	public Participant_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	


}
