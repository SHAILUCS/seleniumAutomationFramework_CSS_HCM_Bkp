/**
 * 
 */
package or.planManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.common.Navigator;

/**
 * @author Shwetha Nov 6, 2019 11:12:59 AM
 *
 */
public class Pending_Invoice_Page {

	public static final String title = "/pendinginvoices";
	public static final String titleViewPending = "/viewpendinginvoice/753";
	
	
	
	@FindBy(xpath = "//input[@name='search']")
	public WebElement field_Search; 
	
	@FindBy(xpath = "//button[contains(.,'Run Planner')]")
	public WebElement button_Run_Planner; 
	
	@FindBy(xpath = "//a[@href='/pendinginvoices/viewpendinginvoice/753']//i[@class='icon icon-view see_ic']")
	public WebElement icon_View;
	
	@FindBy(xpath = "//div[(@class='pagination-bottom')]")
	public WebElement Pagination;
	
	@FindBy(xpath = "//div[@class='main_heading_cmn-']//h1//span[contains(.,'Pending Invoice')]")
	public WebElement header_ViewPendingInvoice;
	
	
	
	
	SeleniumMethods com;
	HCMCommon comm;

	public Pending_Invoice_Page() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	public void verify_Pending_Page_Contents(){
		com.isElementPresent(field_Search,"Search");
		com.isElementPresent(button_Run_Planner,"Run_Planner");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
        tab.verifyColumnHeaders(1, "HCM ID","NDIS ID","Last Name","First Name","Invoice Number","Due Date","Invoice Total","Biller","Status");
		com.isElementPresent(icon_View,"view");
		com.isElementPresent(Pagination,"Pagination");
	}
	
	public void verifyRunplanner(){
		Navigator nav=new Navigator();
		nav.traverseMenu_VerifyPageTitle(Pending_Invoice_Page.titleViewPending,button_Run_Planner);
	}

	public void Pending_Invoices_Searchfield(String HcmID,String NDISID,String lastName,String firstName,String InvoiceNumber,String DueDate,String InvoiceTotal,String biller){
		comm.verifyValid_TextSearch(field_Search, HcmID);
		com.wait(2);
		comm.verifyValid_TextSearch(field_Search, NDISID);
		com.wait(2);
		comm.verifyValid_TextSearch(field_Search, lastName);
		com.wait(2);
		comm.verifyValid_TextSearch(field_Search, firstName);
		com.wait(2);
		comm.verifyValid_TextSearch(field_Search, InvoiceNumber);
		com.wait(2);
		comm.verifyValid_TextSearch(field_Search, DueDate);
		com.wait(2);
		comm.verifyValid_TextSearch(field_Search, InvoiceTotal);
		com.wait(2);
		comm.verifyValid_TextSearch(field_Search, biller);
	}
	
	public void verifyPendingInvoiceView(){
	   ReactTable tab = new ReactTable(comm.reactTableLocator);
       com.click(tab.getCellObject(2, 10));
       com.isElementPresent(header_ViewPendingInvoice,"View Pending Invoice");
	}
	
	public void Pending_Invoices_Pagination(){
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.verifyUI_Pagination_ViewBy();
	}
}
