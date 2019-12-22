/**
 * 
 */
package or.crm.editProspectiveParticipants;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Archana Oct 3, 2019 5:14:05 PM
 *
 */
public class RefererDetails_CreatePartcipant {
	
	public static final String title = "/admin/crm/participantadmin";
	public static final String title_Create = "/admin/crm/createParticipant";
	
	@FindBy(xpath = "//span[contains(text(),'Create New Participant')]")
	public WebElement link_CreateParticipant;
	
	@FindBy(name = "first_name")
	private WebElement text_FirstName;

	@FindBy(name = "last_name")
	private WebElement text_LastName;

	@FindBy(name = "remail")
	private WebElement text_Mail;

	@FindBy(name = "phone_number")
	private WebElement text_PhoneNumber;

	@FindBy(xpath = "//div[contains(text(),'Select...')]")
	private WebElement dropdown_P_relation;

	@FindBy(xpath = "//form[@id='referral_details']//a[contains(text(),'Save And Continue')]")
	private WebElement bnt_Save;
	

	
	private SeleniumMethods com;
	HCMCommon comm;

	public RefererDetails_CreatePartcipant() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	private String RefferDetailFill(String fname, String lname, String email, String phone) {

		if (fname != null) {
			com.sendKeys("Name", text_FirstName, fname);
		}

		if (lname != null) {
			com.sendKeys("Last Name", text_LastName, lname);
		}

		if (phone != null) {
			com.sendKeys("Phone", text_PhoneNumber, phone);
		}

		String newEmail = email;
		if (email != null) {
			if (!newEmail.contains("+")) {
				String emailParts[] = newEmail.split("@");
				emailParts[0] = emailParts[0] + "+" + new Date().getTime();
				newEmail = emailParts[0] + "@" + emailParts[1];
			}

			com.sendKeys("Email", text_Mail, newEmail);

		}
		select_DropdownRelation();
		com.click(bnt_Save);
		com.wait(5);
		clickPartcipantsdetailPage();
		return newEmail;
	}
	
		
	
	/**
	 * 
	 */
	private void clickPartcipantsdetailPage() {
		com.click(By.xpath("//li[contains(.,'Participant Details')]"));

	}
	

	/**
	 * 
	 */
	private void select_DropdownRelation() {
		comm.selectByVisibleText(dropdown_P_relation, "Participant");
	}

	public String createNewUser(String fname, String lname, String phone, String email) {

		String changeEmail = RefferDetailFill(fname, lname, phone, email);

		return email;

	}
	

}
