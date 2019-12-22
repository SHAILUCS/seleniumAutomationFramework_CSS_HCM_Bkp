/**
 * 
 */
package or.planManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.common.Navigator;

/**
 * @author Shwetha S Nov 5, 2019 
 *
 */
public class PlanManagement_Dashboard {
	

	SeleniumMethods com;
	HCMCommon comm;

	public PlanManagement_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//button[contains(text(),'Fetch Invoice')]")
	public WebElement button_FetchInvoice;
	
	@FindBy(xpath = "//a[contains(text(),'Pending Invoices')]")
	public WebElement main_Pending_Invoices;
	
	@FindBy(xpath = "//a[contains(text(),'Approved Invoices')]")
	public WebElement main_Approved_Invoices;
	
	@FindBy(xpath = "//button[contains(text(),'Python Script')]")
	public WebElement button_PythonScript; 
	
	@FindBy(xpath = "//canvas[@class='chartjs-render-monitor']")
	public WebElement chart_Invoices; 
	

	@FindBy(xpath = "//button[contains(.,'Paid')]")
	public WebElement button_paid;
	
	@FindBy(xpath = "//button[contains(.,'Duplicate')]")
	public WebElement button_Duplicate; 

	@FindBy(xpath = "//button[contains(.,'Automated')]")
	public WebElement button_Automated; 

	@FindBy(xpath = "//button[contains(.,'Manual')]")
	public WebElement button_Manual; 

	@FindBy(xpath = "//li[contains(.,'Quantity')]")
	public WebElement list_Quantity; 
	
	@FindBy(xpath = "//li[contains(.,'Dollar Value')]")
	public WebElement list_Dollar_Value;
	
	@FindBy(xpath = "//div[@class='compoBoxDiv__ d-flex row justify-content-center']//div[1]//div[1]")
	public WebElement compBox_InvoicesProcessedLast24hrs;
	
	@FindBy(xpath = "(//div[@class='compBox text-center colored'])[1]")
	public WebElement compBox_TotalInvoicesProcessed;
	
	@FindBy(xpath = "//div[@class='compBox text-center']//p[contains(.,'Pending Invoices')]")
	public WebElement pending_Invoices;
	
	@FindBy(xpath = "(//div[@class='compBox text-center'])[1]")
	public WebElement duplicate_Invoices;
	
	@FindBy(xpath = "(//div[@class='compBox text-center'])[2]")
	public WebElement invoices_FollowUp;

	@FindBy(xpath = "//input[@name='sync_search']")
	public WebElement sync_search;
	
	@FindBy(xpath = "//p[@class='grpMes cmn_clr1']//strong[contains(.,'Qty')]")
	public WebElement Qty;
	
	@FindBy(xpath = "//p[@class='grpMes cmn_clr1']//strong[contains(.,'Dollar')]")
	public WebElement Dollar;
	
	@FindBy(xpath = "//a[@href='/pendinginvoices']//button[contains(.,'Start')]")
	public WebElement button_PendingInvoiceStart;
	
	@FindBy(xpath = "//a[@href='/duplicateinvoices']//button[contains(.,'Start')]")
	public WebElement button_DuplicateinvoicesStart;
	
	@FindBy(xpath = "//a[@href='/followup']//button[contains(.,'Start')]")
	public WebElement button_FollowupStart;
	
	@FindBy(xpath = "//div[(@class='pagination-bottom')]")
	public WebElement Pagination;
	
	/*pagination*/
	@FindBy(xpath = "//span[contains(@class,'icon icon-arrow-1-left previous')]")
	public WebElement icon_Pagination_LeftButton;

	@FindBy(xpath = "//span[contains(@class,'icon icon-arrow-1-right next')]")
	public WebElement icon_Pagination_RightButton;
	
	@FindBy(xpath = "//span[@class='select-wrap -pageSizeOptions']//select")
	public WebElement expandArrow_pageSizeOptionsDropdown;
	


	@FindBy(xpath = "//option[contains(text(),'10 rows')]")
	public WebElement link_ViewBy_10;

	@FindBy(xpath = "//option[contains(text(),'20 rows')]")
	public WebElement link_ViewBy_20;

	@FindBy(xpath = "//option[contains(text(),'50 rows')]")
	public WebElement link_ViewBy_50;
	

	

	public void verifyUI_Pagination_ViewBy() {
		CustomReporter.createNode("verifying Pagination and View By links");
		com.isElementPresent(icon_Pagination_LeftButton, "icon_Pagination_LeftButton");
		com.isElementPresent(icon_Pagination_RightButton, "icon_Pagination_RightButton");
		com.click(expandArrow_pageSizeOptionsDropdown);
		com.isElementPresent(link_ViewBy_10, "link_ViewBy_10");
		com.isElementPresent(link_ViewBy_20, "link_ViewBy_20");
		com.isElementPresent(link_ViewBy_50, "link_ViewBy_50");
	}
	
	
	public void VerifyUI(){
		com.isElementPresent( button_FetchInvoice,"FetchInvoice");
		com.isElementPresent( button_PythonScript,"PythonScript");
		com.isElementPresent( chart_Invoices,"chart_Invoices");
		com.isElementPresent( button_paid,"button_paid");
		com.isElementPresent( button_Duplicate,"Duplicate");
		com.isElementPresent( button_Automated,"Automated");
		com.isElementPresent( button_Manual,"Manual");
		com.isElementPresent( list_Quantity,"Quantity");
		com.isElementPresent( list_Dollar_Value,"Dollar_Value");
		com.wait(2);
		com.isElementPresent( compBox_InvoicesProcessedLast24hrs,"InvoicesProcessedLast24hrs");
		com.wait(2);
		com.isElementPresent( compBox_TotalInvoicesProcessed,"InvoicesProcessedtillDate");
		com.isElementPresent(  pending_Invoices,"pending_Invoices");
		com.isElementPresent(  duplicate_Invoices,"duplicate_Invoices");
		com.isElementPresent(  invoices_FollowUp,"invoices_FollowUp");
		com.isElementPresent(  sync_search,"sync_search");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.verifyColumnHeaders(1, "HCM ID","Participant's Name","Available Funds","Pending","Follow Up","Duplicate","Paid");
        com.isElementPresent(Pagination,"Pagination");
	}
	
	public void clickOnFetchInvoice(){
		com.click(button_FetchInvoice);
	}
	
	
	
	
	
	public void clickOnPythonScript(){
		com.click(button_PythonScript);
	}
	
	public void Click_On_Paid_Duplicate_Automated_Manual(){
		com.click(button_paid);
		com.click(button_Duplicate);
		com.click(button_Automated);
		com.click(button_Manual);
	}
	
	public void Click_On_Quantity_And_Dollar_Value(){
		com.click(list_Quantity,"list_Quantity");
		com.isElementPresent(Qty);
		com.click(button_paid);
		com.click(button_Duplicate);
		com.click(button_Automated);
		com.wait(2);
		com.click(list_Dollar_Value);
		com.isElementPresent(Dollar);
		com.click(button_paid);
		com.click(button_Duplicate);
		com.click(button_Automated);
		com.click(button_Manual);
	}
	
	public void verify_Search_field(String HcmID,String ParticipantName,String dollar){
		comm.verifyValid_TextSearch(sync_search, HcmID);
		com.wait(2);
		comm.verifyValid_TextSearch(sync_search, ParticipantName);
		com.wait(2);
		comm.verifyValid_TextSearch(sync_search, dollar);
	}
	
	public void click_on_ParticipantsName(){
		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int desiredCol = tab.initHeaderColumnList(1).getColumnNumber("Participant's Name");
		com.click(tab.getCellObject(2, desiredCol));
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		AllInvoices_Page aip=new AllInvoices_Page();
        com.isElementPresent(aip.header_AllInvoices,"AllInvoices");
		
	}
	
	
	public void click_On_StatusLink_Pending(){
		ReactTable tab = new ReactTable(comm.reactTableLocator);

		int desiredCol1 = tab.initHeaderColumnList(1).getColumnNumber("Pending");
		com.click(tab.getCellObject(2, desiredCol1));
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		AllInvoices_Page aip=new AllInvoices_Page();
        com.isElementPresent(aip.header_AllInvoices,"AllInvoices");
        
       
		
	}
	public void click_On_StatusLink_Follow_Up(){
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		int desiredCol2 = tab.initHeaderColumnList(1).getColumnNumber("Follow Up");
		com.click(tab.getCellObject(2, desiredCol2));
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		AllInvoices_Page aip=new AllInvoices_Page();
		com.isElementPresent(aip.header_AllInvoices,"AllInvoices");
	}
	
	public void click_On_StatusLink_Duplicate(){
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		int desiredCol3 = tab.initHeaderColumnList(1).getColumnNumber("Duplicate");
		com.click(tab.getCellObject(2, desiredCol3));
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		AllInvoices_Page aip=new AllInvoices_Page();
		com.isElementPresent(aip.header_AllInvoices,"AllInvoices");
	}
	
	public void click_On_StatusLink_Paid(){
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		int desiredCol4 = tab.initHeaderColumnList(1).getColumnNumber("Paid");
		com.click(tab.getCellObject(2, desiredCol4));
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		AllInvoices_Page aip=new AllInvoices_Page();
		com.isElementPresent(aip.header_AllInvoices,"AllInvoices");
	}
	 
	
	
}
