/**
 * 
 */
package or.crm.prospectiveParticipants;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.Util;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.common.SideBar_CRM;

/**
 * @author Archana Oct 31, 2019 2:03:00 PM
 * This class is use for funding page.
 */
public class FundingDetails {
	
	@FindBy(xpath = " //a[contains(text(),'Funding Details')]")
	private WebElement link_FundingDetails;
	
	@FindBy(xpath = "//div[@class='col-md-6 align-self-center']//div[contains(.,'Funding Details')]")
	private WebElement header_FundingDetails;
	
	@FindBy(xpath = "//span[@class='Select-value-label']")
	private WebElement Dropdown_Active;
	
	@FindBy(xpath = "//div[contains(@class,'row d-flex mt')]")
	private WebElement fundsBreakdown_text;
	

	
	
	
	
	
	
	private SeleniumMethods com;
	HCMCommon comm;
	private SideBar_CRM sidebar_crm;

	public FundingDetails() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
		sidebar_crm = new SideBar_CRM();
	}
	

	/**
	 * This method is use for Funding UI page.
	 */
	public void funding_Detail_UI_Validation() {
		com.click(link_FundingDetails);
		com.isElementPresent(header_FundingDetails, "header_FundingDetails");
	 	com.isElementPresent(Dropdown_Active, "Dropdown_Active");
		com.isElementPresent(fundsBreakdown_text, "fundsBreakdown_text");
		ReactTable rct = new ReactTable(comm.reactTableLocator);
		rct.verifyColumnHeaders(1,"Plan id", "Plan Name", "Start Date", "End Date");
		
		
		

	}
	
	/**
	 * 
	 */
	public void funding_Detail_Function_Validate() {
		com.click(link_FundingDetails);
		dropdown_Select_FundingPage();
		getText_FundingBreak();
	}
	
	/**
	 * 
	 */
	private void dropdown_Select_FundingPage() {
	
		comm.selectByVisibleText(Dropdown_Active, "All");

	}
	
	
	/**
	 * 
	 */
	private void getText_FundingBreak() {
		String text = com.getText(fundsBreakdown_text);
		
		Util.comparator_PageValues("Plan1_Jenny_2019_NDIS number", text, "Plan1_Jenny_2019_NDIS number");
		Util.comparator_PageValues("Participant Plan Type", text, "Participant Plan Type");
		Util.comparator_PageValues("Start Date:", text, "Start Date:");
		Util.comparator_PageValues("End Date:", text, "End Date:");
		Util.comparator_PageValues("Finance Breakdown:", text, "Finance Breakdown:");
		Util.comparator_PageValues("Plan Breakdown:", text, "Plan Breakdown:");
	}
}
