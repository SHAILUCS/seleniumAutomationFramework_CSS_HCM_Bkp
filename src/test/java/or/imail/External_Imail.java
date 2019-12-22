/**
 * 
 */
package or.imail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.clientPortal.LoginPage_ClientPortal;
import or.clientPortal.Dashboard_ClientPortal;
import or.common.HCMCommon;
import or.common.LoginPage;

/**
 * @author Shwetha S Oct 7, 2019
 *
 */
public class External_Imail {

	public static final String title = "admin/imail/external/inbox";

	SeleniumMethods com;
	HCMCommon comm;

	public External_Imail() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//h1[.='Inbox']")
	private WebElement heading_Inbox;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement text_SearchField;

	@FindBy(xpath = "(//span[contains(@id,'react-select')][contains(@class,'value-wrapper')])[2]")
	public WebElement dropDown_FilterBy;

	@FindBy(xpath = "(//span[contains(@id,'react-select')][contains(@class,'value-wrapper')])[3]")
	private WebElement dropDown_SortBy;

	@FindBy(xpath = "//span[contains(.,'Compose New Message')]")
	private WebElement button_ComposeNewMessage;

	@FindBy(xpath = "//div[.='To:'][@class='Select-placeholder']//following-sibling::div/input")
	private WebElement text_To;

	@FindBy(xpath = "//div[.='CC:'][@class='Select-placeholder']//following-sibling::div/input")
	private WebElement text_CC;

	@FindBy(xpath = "//input[@placeholder='Subject:']")
	private WebElement txtField_Subject;

	@FindBy(xpath = "//textarea[@placeholder='Your Message here']")
	private WebElement textarea_MessageBody;

	@FindBy(xpath = "//i[contains(@class,'icon-document')]/..")
	private WebElement button_Draft;

	@FindBy(xpath = "//i[contains(@class,'share')]/..")
	private WebElement button_Send;

	
	//TODO NEED TO FIX
	@FindBy(xpath = "//i[@class='icon icon-flag-im flag-im color']")
	private WebElement icon_Flagged;

	@FindBy(xpath = "//i[@class='icon icon-favorite-im active']")
	private WebElement icon_Favourites;

	@FindBy(xpath = "//i[@class='icon icon-envelope-im active']")
	private WebElement icon_click_To_Make_MarkRead;

	@FindBy(xpath = "//i[@class='icon icon-favorite-im ']")
	private WebElement icon_click_To_Make_Favourite;

	@FindBy(xpath = "//div[@class='col-md-6 Imail_btn_right_v1 text-right parant_active']//i[@class='icon icon-flag-im ']")
	private WebElement icon_click_To_Make_Flaged;

	@FindBy(xpath = "//div[@class='col-md-6 Imail_btn_right_v1 text-right parant_active']//i[@class='icon icon-block-im ']")
	private WebElement icon_click_To_Make_Block;

	@FindBy(xpath = "//div[@class='col-md-6 Imail_btn_right_v1 text-right parant_active']//i[@class='icon icon-archive-im ']")
	private WebElement icon_click_To_Make_Archieve;

	@FindBy(xpath = "//i[@class='icon icon-block-im attach_im color']")
	private WebElement icon_Block;

	@FindBy(xpath = "//div[@class='mess_V_b'][contains(.,'23/10/2019 10:58 PM')]")
	private WebElement list_FavouritesDate;

	@FindBy(xpath = "(//div[@class='mess_vn_in_2']//p[@class='my-0 ML_show_fixed'][contains(.,'new  test')])")
	private WebElement subject_Text;

	@FindBy(xpath = "(//div[@class='ml-0 mb-0']//span[@class='add_access p-colr'])[1]")
	private WebElement Participant_P;

	@FindBy(xpath = "//div[@class='Imail_btn_right_v1 text-right']//i[@class='icon icon-share-icon']")
	private WebElement In_draft_shareIcon;

	/**
	 * Objects
	 * 
	 * @author Adarsh 29-Nov-2019
	 */
	@FindBy(xpath = "//h1[@class='not_msg_v1'][.='No Result']")
	private WebElement data_NoResult_LeftSide;

	@FindBy(xpath = "//h1[@class='not_msg_v1'][.='No Message Select']")
	private WebElement data_NoResult_RightSide;

	@FindBy(xpath = "//div[@class='Internal_M_D']//div[@class='mess_V_a1'][contains(.,'From')]")
	private WebElement link_From_LeftSection;

	@FindBy(xpath = "//i[contains(@class,'favorite')]")
	private WebElement icon_Favourite_RightSection;

	@FindBy(xpath = "//div[@class='Internal_M_D_2']//i[contains(@class,'icon-flag-im')]")
	private WebElement icon_Flag_RightSection;

	@FindBy(xpath = "//i[contains(@class,'icon-envelope-im')]")
	private WebElement icon_Unread_RightSection;
	
	@FindBy(xpath = "//div[@class='Internal_M_D_2']//i[contains(@class,'icon-attach-im attach_im')]")
	private WebElement icon_Attachment_RightSection;
	
	@FindBy(xpath = "//div[@class='Internal_M_D_2']//i[contains(@class,'icon-warning-im')]")
	private WebElement icon_Highpriority_RightSection;
	
	/**
	 * Verifying the External Imail UI
	 * 
	 * @author Shwetha S Nov 4, 2019
	 * @author Adarsh 29-Nov-2019
	 */
	public void verifyUI() {
		com.isElementPresent(heading_Inbox,"heading_Inbox");
		com.isElementPresent(text_SearchField, "search field present");
		com.isElementPresent(dropDown_FilterBy, "Filter by select present");
		com.isElementPresent(dropDown_SortBy, "Filter by date");
		com.isElementPresent(button_ComposeNewMessage, "ComposeNewMessage");
	}

	/**
	 * Select the date by FilterByDate
	 * 
	 * @author Shwetha S Nov 4, 2019
	 * @author Adarsh 29-Nov-2019
	 */
	public void verify_SortingFilterByDate() {
		// Functionality is not yet developed by developers
		// Exclude this Test
		comm.selectByVisibleText(dropDown_SortBy, "");
		
	}

	/**
	 * Select the status of mail by FilterBySelect such as Flagged,Favourite
	 * 
	 * @author Shwetha S Nov 4, 2019
	 */
	public void verifyFilterBySelect() {

		com.isElementPresent(dropDown_FilterBy, "Filter By Dropdown");

		/**
		 * @author Adarsh 29-Nov-2019 
		 * Fixing this script, Script was breaking coz below
		 *         object was hard coded.
		 * 
		 * @FindBy(xpath = "//div[@class='mess_V_b'][contains(.,'23/10/2019 10:58
		 *               PM')]") private WebElement list_FavouritesDate;
		 */
		
		applyfilter("Unread", icon_Unread_RightSection);
		applyfilter("Flagged", icon_Flag_RightSection);
		applyfilter("Favourite", icon_Favourite_RightSection);
		applyfilter("High Priority", icon_Highpriority_RightSection);
		applyfilter("Attachment", icon_Attachment_RightSection);

	
	}

	/**
	 * @param filterValue single value to be selected from filter dropdown
	 * @author Adarsh 29-Nov-2019
	 */
	private void applyfilter(String filterValue, WebElement icon_RightSection) {
		CustomReporter.createNode("Checking filter " + filterValue);

		comm.selectByVisibleText(dropDown_FilterBy, filterValue);

		if (!com.waitForElementTobe_NotVisible(data_NoResult_LeftSide, 0)) {
			CustomReporter.report(STATUS.WARNING, "No Result are getting dislpayed in left section");
		} else {
			CustomReporter.report(STATUS.PASS, "Some rows are getting displayed in left section");
			com.click(link_From_LeftSection, "First Mail in Left Section");
			com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

			if(filterValue.contains("High") || filterValue.contains("Attach")) {
				if (!com.waitForElementTobe_NotVisible(icon_RightSection, 0)) {
					CustomReporter.report(STATUS.PASS, filterValue + " Mail is getting displayed");
				} else {
					CustomReporter.report(STATUS.FAIL, filterValue + " Mail is NOT getting displayed");
				}
			}else {
				String className = com.getAttribute(icon_RightSection, "class");
	
				if (className.contains("active")) {
					CustomReporter.report(STATUS.PASS, filterValue + " Mail is getting displayed");
				} else {
					CustomReporter.report(STATUS.FAIL, filterValue + " Mail is NOT getting displayed");
				}
			}
		}

	}

	/**
	 * Select the status of mail by FilterBySelect such as Unread
	 * 
	 * @author Shwetha S Nov 4, 2019
	 */

	public void External_verifySortingFilterByUnread() {
		Imail_Dashboard imd = new Imail_Dashboard();
		com.click(imd.link_External_Imail);
		com.isElementPresent(dropDown_FilterBy, "present");

		comm.selectByVisibleText(dropDown_FilterBy, "Unread");
		com.wait(2);
		com.click(Participant_P, "Participant replied mail");
	}

	/**
	 * 
	 * @author Shwetha S Nov 4, 2019
	 * 
	 */
	/*
	 * public void External_verifySortingFilterByDate() { Imail_Dashboard imd=new
	 * Imail_Dashboard(); com.click(imd.link_External_Imail);
	 * com.isElementPresent(dropDown_FilterByDate);
	 * 
	 * }
	 */

	/**
	 * enter the value in search field and verify
	 * 
	 * @author Shwetha S Nov 4, 2019
	 * @author Adarsh 29-Nov-2019
	 */
	public void verify_SearchFunctionlity(String searchData) {
		com.isElementPresent(text_SearchField);
		com.sendKeys(text_SearchField, searchData);
		com.sendKeys(text_SearchField, Keys.ENTER);
		
		com.waitForElementsTobe_NotVisible(comm.loadingTextOrCircleLocator,5);
		
		if (!com.waitForElementTobe_NotVisible(data_NoResult_LeftSide, 0)) {
			CustomReporter.report(STATUS.WARNING, "No Result are getting dislpayed in left section");
		} else {
			CustomReporter.report(STATUS.PASS, "Some rows are getting displayed in left section");
		}
		
	}

	
	/**
	 * 
	 */
	private void fillMailForm(String to_User, String cc_User, String subject, String body, String attachmentPath) {

		if(to_User!=null) {
			com.sendKeys(text_To, to_User);
			com.wait(3);
			com.click(By.xpath("//div[@class='Select-menu-outer']//div[contains(@class,'option')][contains(.,'"
					+ to_User + "')]"), "to_User "+to_User);
			com.wait(2);
		}
		

		if(cc_User!=null) {
			com.sendKeys(text_CC, cc_User);
			com.wait(3);
			com.click(By.xpath("//div[@class='Select-menu-outer']//div[contains(@class,'option')][contains(.,'"
					+ cc_User + "')]"), "cc_User "+ cc_User);
			com.wait(2);
		}
		
		if(subject!=null) {
			com.sendKeys("subject",txtField_Subject, subject);
			com.wait(2);
		}
		
		if (body!=null) {
			com.sendKeys("body",textarea_MessageBody, body);
		}

		if (attachmentPath!=null) {
			com.sendKeys("attachment",icon_Attachment_RightSection, attachmentPath);
		}
		
		
	}
	
	
	/**
	 * Composing the mail
	 * 
	 * @author Shwetha S Nov 4, 2019
	 */
	public void composeMail(String to_User, String cc_User, String subject, String body, String attachmentPath) {
		
		CustomReporter.createNode("Composing a new mail");

		com.click(button_ComposeNewMessage, "button_Compose New Message");
		com.wait(2);
		fillMailForm(to_User, cc_User, subject, body, attachmentPath);
	}

	/**
	 * @author shailendra Dec 14, 2019
	 */
	public void createDraft(String to_User, String cc_User, String subject, String body, String attachmentPath) {
		composeMail(to_User, cc_User, subject, body, attachmentPath);
		com.click(button_Draft, "button_Draft");
		com.waitForElementsTobe_Present(By.xpath("//h3[.='Save as draft successfully.']"), "Draft Saved Success message");
	}
	
	/**
	 * Compose the mail >>send >>login to OCS>>verify the sent mail
	 * 
	 * @author Shwetha S Nov 4, 2019
	 * 
	 */
	public void sendMail(String to_User, String cc_User, String subject, String body, String attachmentPath) {
		//createNewMail("Romil", "Arch");
		composeMail(to_User, cc_User, subject, body, attachmentPath);
		
		com.click(button_Send);

	}

	/**
	 * Compose the mail >>send >>login to OCS>>verify the sent mail >>Reply to the
	 * mail
	 * 
	 * @author Shwetha S Nov 4, 2019
	 * 
	 */
	public void ComposeNewMailOnClientPortal_VerifyAdmin(String to_User, String cc_User, String subject, String body, String attachmentPath) {
		//createNewMail("shwetha", "Arch");
		composeMail(to_User, cc_User, subject, body, attachmentPath);
		com.click(button_Send);
		LoginPage lp = new LoginPage();
		lp.logout();

		LoginPage_ClientPortal lpOCS = new LoginPage_ClientPortal();
		lpOCS.loginToClientPortal("shwethachethanjn", "SENKncgSEN");

		Dashboard_ClientPortal dbOCS = new Dashboard_ClientPortal();
		dbOCS.sendBackTheReplyForMail();

	}

	/**
	 * compose the mail >>send >> click on mail>> mark as unread
	 * 
	 * @author Shwetha S Nov 4, 2019
	 */
	public void External_Check_MailMarkAsUnRead(String to_User, String cc_User, String subject, String body, String attachmentPath) {

		//createNewMail("shwetha", "arc");
		composeMail(to_User, cc_User, subject, body, attachmentPath);
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.click(icon_click_To_Make_MarkRead, "Mark as Unread");
	}

	/**
	 * Compose the mail >>send >> click on mail>> mark as Favourite
	 * 
	 * @author Shwetha S Nov 4, 2019
	 */
	public void External_Check_MailMarkAsFavourites(String to_User, String cc_User, String subject, String body, String attachmentPath) {
		//createNewMail("shwetha", "arc");
		composeMail(to_User, cc_User, subject, body, attachmentPath);
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.wait(2);
		com.isElementPresent(icon_click_To_Make_Favourite);
		com.click(icon_click_To_Make_Favourite, "Mark as Favourite");

	}

	/**
	 * Compose the mail >>send >> click on mail>> mark as Flagged
	 * 
	 * @author Shwetha S Nov 4, 2019
	 */
	public void Admin_External_Check_MailSetAsItemFlag(String to_User, String cc_User, String subject, String body, String attachmentPath) {
		//createNewMail("shwetha", "arc");
		composeMail(to_User, cc_User, subject, body, attachmentPath);
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.waitForElementsTobe_Present(icon_click_To_Make_Flaged);
		com.click(icon_click_To_Make_Flaged, "Mark as Flaged");

	}

	/**
	 * Compose the mail >>send >> click on mail>> mark as Block
	 * 
	 * @author Shwetha S Nov 4, 2019
	 */
	public void External_Check_Mail_BlockItem(String to_User, String cc_User, String subject, String body, String attachmentPath) {

		//createNewMail("shwetha", "arc");
		composeMail(to_User, cc_User, subject, body, attachmentPath);
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.waitForElementsTobe_Present(icon_click_To_Make_Block);
		com.click(icon_click_To_Make_Block, "Mark as Block");
		com.waitForElementsTobe_Present(icon_Block, "Mail is set as blocked");

	}

	/**
	 * Compose the mail >>send >> click on mail>> mark as Archive
	 * 
	 * @author Shwetha S Nov 4, 2019
	 */
	public void External_Check_Mail_Archive(String to_User, String cc_User, String subject, String body, String attachmentPath) {
		//createNewMail("shwetha", "arc");
		composeMail(to_User, cc_User, subject, body, attachmentPath);
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.waitForElementsTobe_Present(icon_click_To_Make_Archieve);
		com.click(icon_click_To_Make_Archieve, "Mark as Archieve");
		Imail_Dashboard imd = new Imail_Dashboard();
		com.click(imd.external_Archived);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));

	}

}
