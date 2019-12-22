/**
* 
*/
package or.admin;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shwetha S Sep 25, 2019
 *
 */
public class Approvals {
	public static final String title = "admin/user/approval";

	SeleniumMethods com;
	HCMCommon comm;

	public Approvals() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//div[@class='input_search change_b']//input")
	public WebElement search_Field;

	@FindBy(xpath = "//div[@class='react-datepicker-wrapper']")
	public WebElement date_Picker;
	
	@FindBy(xpath = "(//div[@class='rt-td rt-expandable']//i[@class='icon icon-arrow-down'][0])")
	public WebElement down_Arrow;

	@FindBy(xpath = "//button[contains(.,'Confirm')]")
	public WebElement button_Confirm;

	@FindBy(xpath = "//i[@class='icon icon-cross-icons deny_request active']")
	public WebElement icon_Deny;

	@FindBy(xpath = "//i[@class='icon icon-accept-approve1-ie approve_request ']")
	public WebElement icon_Approve;
	
	
	@FindBy(xpath = "//div[.='No approvals']")
	private WebElement data_NoApprovals;
	


	/**
	 * Verify for the search field
	 * 
	 * @author Shwetha S Oct 3, 2019
	 */
	public void verify_text() {
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		comm.verifyValid_TextSearch(search_Field, "11");

	}

	/**
	 * It selects the provided date from date picker
	 * 
	 * @author Shwetha S Oct 3, 2019
	 */
	public void datePickerOn(String date) {
		com.click(date_Picker);
		comm.selectDate(date_Picker,date);
		
		
		if(! com.waitForElementTobe_NotVisible(data_NoApprovals,5)) {
			CustomReporter.report(STATUS.WARNING, "No approvals is getting dispplayed for "+date+" please change the test data");
		}else {
			ReactTable tab = new ReactTable(comm.reactTableLocator);
			com.waitForElementsTobe_Present(comm.reactTableLocator);
			String dateVal = tab.getCellText(2, 3);
			System.out.println(dateVal);
			if (dateVal.equals(date)) {
				CustomReporter.report(STATUS.PASS, "Data for Date "+date+" is displaying on based on entered date in table");
			} else {
				CustomReporter.report(STATUS.FAIL, "Data for Date "+date+" is not displaying on based on entered date in table but ["+dateVal+"] is getting displayed");
			}
		}
	}

	/**
	 * It clicks on Icon link
	 * 
	 * @author Shwetha S Oct 3, 2019
	 */
	public void verify_IconPin() {

		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int desiredCol = tab.initHeaderColumnList(1).getColumnNumber("Action");

		com.click(tab.getChildObject(2, desiredCol, "span", 0), "Pin_Icon_link");

	}

	/**
	 * It clicks on Icon View and verifies the deny and allow icons
	 * 
	 * @author Shwetha S Oct 3, 2019
	 */
	public void verify_IconView() {
		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int desiredCol = tab.initHeaderColumnList(1).getColumnNumber("Action");

		com.click(tab.getChildObject(2, desiredCol, "i", 1), "View_Icon_link");
		com.wait(2);
		ReactTable tab1 = new ReactTable(comm.reactTableLocator);
		tab1.initHeaderColumnList(1);
		com.wait(2);
		com.click(tab1.getChildObject(2, 4, "i", 0), "Down_Arrow");
		com.wait(2);
		com.isElementPresent(icon_Deny, "Deny Icon present");
		com.isElementPresent(icon_Approve, "Approve Icon present");
		com.isElementPresent(button_Confirm, "Confirm Button");

	}

	/**
	 * It verifies the Pagination
	 * 
	 * @author Shwetha S Oct 3, 2019
	 */
	public void Verify_Pagination() {
		comm.verifyUI_Pagination_ViewBy();
	}
}
