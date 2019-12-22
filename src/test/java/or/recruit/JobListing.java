package or.recruit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class JobListing {

	public static String title = "recruitment/job_opening/jobs";
	SeleniumMethods com;
	HCMCommon comm;

	public JobListing() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
  @FindBy(xpath = "//a[.='Create New Job']")
  public WebElement link_CreateJob;


	
	
	
}
