package or.recruit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;

/**
 * This is the page object class to store the object of recruitment dashboard page
 * @author user
 *
 */
public class RecruitmentDashboard {

	public static String title = "admin/recruitment/dashboard";

	public RecruitmentDashboard() {
		
		PageFactory.initElements(DriverFactory.getDriver(),this);

	}

	@FindBy(xpath="//a[.='Applicant List']")
	public WebElement link_ApplicantList;

	@FindBy(xpath ="//li/a[.='Jobs']")
	public WebElement link_Job;

	@FindBy(xpath ="//li/a[.='Recruiter']")
	public WebElement link_Recruiter;

	public void verify_UI() {
		
	}

	
}
