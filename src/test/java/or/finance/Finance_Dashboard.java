package or.finance;

import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class Finance_Dashboard {
	
	public static final String title = "";
    private SeleniumMethods com;
	
	public Finance_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

}
