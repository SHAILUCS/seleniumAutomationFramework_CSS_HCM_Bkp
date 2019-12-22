/**
 * 
 */
package or.planManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Util;
import com.csvUtil.CSVManager;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shwetha Nov 13, 2019 11:32:46 AM
 *
 */
public class ApprovedInvoicesPage {
	
	public static final String title = "/approvedinvoices";

	SeleniumMethods com;
	HCMCommon comm;

	public ApprovedInvoicesPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//div[@class='cstm_select_fINacne']//input[@name='search']")
	public WebElement search_field;
	
	@FindBy(xpath = "//button[@class='btn hdng_btn cmn-btn1']")
	public WebElement button_ExportCSV;
	

	@FindBy(xpath = "//div[(@class='pagination-bottom')]")
	public WebElement Pagination;
	
	
	public void verify_Approved_Page_Contents(){
		com.isElementPresent(search_field,"Search");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.wait(2);
        tab.verifyColumnHeaders(1,"", "HCM ID","NDIS ID","Last Name","First Name","Invoice Number.","Due Date","Invoice Total","Biller","HCM Status");
        com.isElementPresent( button_ExportCSV," button_ExportCSV");
		com.isElementPresent(Pagination,"Pagination");
	}
	
	public void Approved_Invoices_Searchfield(String HcmID,String NDISID,String lastName,String firstName,String InvoiceNumber,String DueDate,String InvoiceTotal,String biller){
		comm.verifyValid_TextSearch(search_field, HcmID);
		com.wait(2);
		comm.verifyValid_TextSearch(search_field, NDISID);
		com.wait(2);
		comm.verifyValid_TextSearch(search_field, lastName);
		com.wait(2);
		comm.verifyValid_TextSearch(search_field, firstName);
		com.wait(2);
		comm.verifyValid_TextSearch(search_field, InvoiceNumber);
		com.wait(2);
		comm.verifyValid_TextSearch(search_field, DueDate);
		com.wait(2);
		comm.verifyValid_TextSearch(search_field, InvoiceTotal);
		com.wait(2);
		comm.verifyValid_TextSearch(search_field, biller);
		
	}

	/**
	 * 
	 */
	public void verify_export_Csv() {
		
			
			
			ReactTable tab = new ReactTable(comm.reactTableLocator);
			
			String pageVal1 = tab.getCellText(2, 3);
			String pageVal2 = tab.getCellText(2, 5);
			
			com.javaScript_Click(tab.getChildObject(1, 1, "input", 0));
			
			com.click(button_ExportCSV);
			
			String filePath = Util.getDownloadedFilePath("log");
			
			CSVManager csv = new CSVManager(filePath);
			
			String csvVal1 = csv.getValue(1, 1);
			String csvVal2 = csv.getValue(1,3);
			
			Util.comparator_PageValues(pageVal1, csvVal1, "Date");
			Util.comparator_PageValues(pageVal2, csvVal2, "Description");
			
		
		
	}
	
	
}
