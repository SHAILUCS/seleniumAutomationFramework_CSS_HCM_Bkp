/**
 * 
 */
package or.planManagement;

import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Archana Nov 6, 2019 11:25:44 AM
 *
 */
public class Duplicate_InvoicesPage {

public static final String title = "/duplicateinvoices";
	
	
	SeleniumMethods com;
	HCMCommon comm;

	public Duplicate_InvoicesPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
}
