/**
 * 
 */
package or.imail;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author shailendra Dec 14, 2019 5:47:56 PM
 *
 */
public class SideBarMenu {
	SeleniumMethods com;
	HCMCommon comm;

	public SideBarMenu() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	/**
	 * Side links
	 * 
	 * @author shailendra Dec 14, 2019
	 * 
	 * */
	
	@FindBy(linkText = "Drafts")
	public WebElement link_Drafts;
	

}
