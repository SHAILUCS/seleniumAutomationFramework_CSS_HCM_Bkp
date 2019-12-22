/**
 * 
 */
package or.planManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shwetha Nov 6, 2019 3:38:08 PM
 *
 */
public class AllInvoices_Page {
	
	public static final String titleInvoicedetails = "/allinvoices/detail/45";
	SeleniumMethods com;
	HCMCommon comm;

	public AllInvoices_Page() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//span[contains(.,'All Invoices: ')]")
	public WebElement header_AllInvoices;
	
	
	
}
