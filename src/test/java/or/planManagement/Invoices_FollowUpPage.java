/**
 * 
 */
package or.planManagement;

import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shwetha Nov 6, 2019 12:37:48 PM
 *
 */
public class Invoices_FollowUpPage {

	public static final String title = "/followup";
	SeleniumMethods com;
	HCMCommon comm;

	public Invoices_FollowUpPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
}
