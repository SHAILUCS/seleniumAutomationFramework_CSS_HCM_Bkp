/**
 * 
 */
package or.crm.prospectiveParticipants;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.common.SideBar_CRM;

/**
 * @author Archana Oct 29, 2019 4:18:39 PM
 *This Class is use for shift Page.
 */
public class Shift {
	
	public static final String title = "/admin/crm/shifts";
	
	@FindBy(xpath = "//a[contains(text(),'Shift')]")
	private WebElement link_Shift;
	
	@FindBy(xpath = "//div[@class='col-md-6 align-self-center br-1']//div[contains(.,'Shift')]")
	private WebElement header_Shift;
	
	@FindBy(xpath = "//div[@class='table_search_new']//input")
	private WebElement search_Box;
	
	@FindBy(xpath = "//label[contains(.,'From')]/following-sibling::div//input")
	private WebElement input_FromCalader;
	
	@FindBy(xpath = "//label[contains(.,'To')]/following-sibling::div//input")
	private WebElement input_ToCalader;
	

	


	
	
	
	private SeleniumMethods com;
	HCMCommon comm;
	private SideBar_CRM sidebar_crm;

	public Shift() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
		sidebar_crm = new SideBar_CRM();
	}
	
	/**
	 * @author Archana Oct 29, 2019 5:18:39 PM
	 * This Method is use forr UI Of shift 
	 */
	public void shift_UI_Validation() {
		;
		com.click(link_Shift);
		com.isElementPresent(header_Shift,"header_Shift");
		com.isElementPresent(search_Box,"search_Box");
		ReactTable rct = new ReactTable(comm.reactTableLocator);
		rct.verifyColumnHeaders(1,"", "ID", "Date", "For", "Start", "Duration");
		com.wait(5);
		WebElement quickView = rct.getChildObject(2, 8,"i", 0);
		com.click(quickView);
		comm.verifyUI_Pagination_ViewBy();
	}
	/**
	 * @author  Archana Oct 31 2019 10:39 AM
	 * This Method is use for validate Functiona of shift
	 */
	public void shift_Page_Functional_Validation() {
	
		com.click(link_Shift);
		com.wait(5);
		serachbox_shift_Page();
		com.refresh();
		calender_Function();
	
	}
	/**
	 * This is use for search box in shift page.
	 */
	private void serachbox_shift_Page() {
		com.sendKeys(search_Box,"29");
        com.wait(5);
        ReactTable rct = new ReactTable(comm.reactTableLocator);
      String IDNum =   rct.getCellText(3, 2);
		System.out.println(IDNum);
		
	}
	/**
	 * This is use for calander in shift page
	 */
	private void calender_Function() {
		comm.selectDate(input_FromCalader, "10/31/2019");
		com.wait(5);
		comm.selectDate(input_ToCalader, "11/05/2019");

	}
}
