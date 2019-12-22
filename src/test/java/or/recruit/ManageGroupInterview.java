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
 * @author Adarsh 30-Oct-2019
 *
 */
public class ManageGroupInterview {
	public static final String title = "/admin/recruitment/group_interview/manage_group_interview";
	SeleniumMethods com;
	HCMCommon comm;

	public ManageGroupInterview() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	
	
	}

	@FindBy(xpath = "//input[@name='srch_box']")
	private WebElement input_Search;

	

	
	
	
	
	/**
	 * @param taskName
	 */
	public void verifyTaskIsPresentOnPage(String taskName) {
		
		comm.verifyInvalid_TextSearch(input_Search, taskName, taskName);
		
	}
	
}
