/**
 * 
 */
package or.recruit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Shailendra Oct 2, 2019 4:44:55 PM
 *
 */
public class ApplicantInfo {

	public static final String title = "admin/recruitment/applicant";
	SeleniumMethods com;
	HCMCommon comm;

	public ApplicantInfo() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//h5[.='Assigned Recruiter:']//following-sibling::h3")
	private WebElement data_RecruiterName;

	@FindBy(xpath = "//h5[.='Assigned Recruiter:']//following-sibling::div//div[@class='Select-placeholder'][.='Search']")
	private WebElement select_SearchForRecruiter;
	
	@FindBy(xpath = "//h5[.='Assigned Recruiter:']//following-sibling::div//span[.='Yes']")
	private WebElement button_Yes;
	
	@FindBy(xpath = "//h4[contains(.,'APP-')]//following-sibling::div")
	private WebElement data_Status;
	

	

	/**
	 * Will update the recruiter name
	 * @param recruiterName
	 * @author shailendra Oct 2, 2019
	 */
	public void changeRecruiter(String recruiterName) {
		com.click(data_RecruiterName);
		
		com.click(select_SearchForRecruiter);
		
		com.sendKeys("Change Recruiter",com.switchTo_ActiveElement(), recruiterName);
		
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		
		com.click(By.xpath("//div[contains(@class,'menu-outer')]//div[contains(@class,'option')][contains(.,'"+recruiterName+"')]"), "Selecting Recruiter ["+recruiterName+"]");
		
		com.click(button_Yes, "Yes");
		
		com.wait(2);
		
	}


	/**
	 * @return status of Applicant
	 * @author Adarsh 21-Oct-2019
	 */
	public String getApplicantStatus() {
		com.waitForElementsTobe_Present(data_Status);
		return com.getText(data_Status);
	}
	

	
}
