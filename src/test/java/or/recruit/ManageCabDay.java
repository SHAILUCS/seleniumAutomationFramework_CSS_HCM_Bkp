/**
 * 
 */
package or.recruit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 *@author Adarsh 30-Oct-2019
 *
 */
	public class ManageCabDay {
		public static final String title = "admin/recruitment/manage_cab_day";
		SeleniumMethods com;
		HCMCommon comm;

		public ManageCabDay() {
			PageFactory.initElements(DriverFactory.getDriver(), this);
			com = new SeleniumMethods();
			comm = new HCMCommon();

		
		}
		@FindBy(xpath = "//input[@placeholder='Search..']")
		private WebElement input_Search;

	/**
	 * In this method Create a Cab day task
	 * and verfiy a cab day task on Manage Cab day.
	 * @author Adarsh 30-Oct-2019
	 */
	public void verifyTaskIsPresentOnPage(String taskName) {
		comm.verifyInvalid_TextSearch(input_Search, taskName, taskName);
		
	}
	}
