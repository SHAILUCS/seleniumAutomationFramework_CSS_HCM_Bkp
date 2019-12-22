/**
 * 
 */
package or.imail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shailendra Dec 14, 2019 5:44:34 PM
 *
 */
public class Drafts {
	public static final String title = "draft";
	SeleniumMethods com;
	HCMCommon comm;

	public Drafts() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	@FindBy(name = "search")
	private WebElement text_Search;
	
	@FindBy(xpath = "//h1[.='No Result']")
	private WebElement data_NoResult;


	/**
	 * @param subject
	 * @author shailendra Dec 14, 2019
	 */
	public void verifyMailIsPresent(String subject) {
		performSearch(subject);
	}
	
	/**
	 * @param data
	 * @author shailendra Dec 14, 2019
	 */
	private void performSearch(String data) {
		com.sendKeys(data, text_Search, data + Keys.ENTER);
		
		if(!com.waitForElementTobe_NotVisible(data_NoResult,5)) {
			CustomReporter.report(STATUS.WARNING, "No Data found is displayed check the passed data");
		}else {
			com.waitForElementsTobe_Present(By.xpath("//div[@class='Internal_M_D']//a[contains(.,'"+data+"')]"), "Mail found "+data);
		}
		
	}

}
