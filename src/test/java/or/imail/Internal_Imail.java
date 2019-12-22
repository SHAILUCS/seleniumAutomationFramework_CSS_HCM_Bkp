/**
 * 
 */
package or.imail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.clientPortal.LoginPage_ClientPortal;
import or.clientPortal.Dashboard_ClientPortal;
import or.common.HCMCommon;
import or.common.LoginPage;

/**
 * 
 * 
 * @author Shwetha S Oct 29, 2019
 */
public class Internal_Imail {
	
	public static final String title = "admin/imail/internal/inbox";
	SeleniumMethods com;
	HCMCommon comm;

	public Internal_Imail() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	@FindBy(xpath = "(//div[@class='ml-0 mb-0']//span[@class='add_access p-colr'])[1]")
	private WebElement Participant_P;
	

	@FindBy(xpath = "//div[@class='search_bar']//div[@class='input_search P_25_TB']//input[@name='search']")
	private WebElement search_Field;
	
	@FindBy(xpath = "(//span[contains(@id,'react-select')][contains(@class,'value-wrapper')])[2]")
	public WebElement dropDown_FilterBy;
	
	
	@FindBy(xpath = "//div[@class='Select-placeholder'][contains(.,'Sort by: Date')]")
	private WebElement dropDown_FilterByDate;
	
	@FindBy(xpath = "//span[contains(.,'Compose New Message')]")
	private WebElement button_ComposeNewMessage;
	
	@FindBy(xpath = "//span[(@id='react-select-5--value')]//div[contains(.,'To:')]//following-sibling::div//input")
	private WebElement txtField_To;
	
	@FindBy(xpath = "//span[(@id='react-select-6--value')]//div[contains(text(),'CC:')]//following-sibling::div//input")
	private WebElement txtField_CC;
	
	@FindBy(xpath = "//input[@placeholder='Subject:']")
	private WebElement txtField_Subject;
	
	@FindBy(xpath = "//div[@class='col-md-12 px-0 int_text_but']//textarea")
	private WebElement txtArea_MessageBody;
	
	@FindBy(xpath = "//div[@class='row mt-5']//button[1]")
	private WebElement button_Draft;
	
	@FindBy(xpath = "//button[@class='default_btn']//i[@class='icon icon-share-icon']")
	private WebElement button_Send;
	
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
	
	@FindBy(xpath = "//a[contains(.,'Group Messages')]")
	private WebElement Group_Messages;


	@FindBy(xpath = "//div[@class='button_plus__']//i[@class='icon icon-add-icons Add-2-1']")
	private WebElement button_Plus;
	
	@FindBy(xpath = "//input[@name='team_name']")
	private WebElement team_Name;
	
	@FindBy(xpath = "//label[@class='btn btn-default btn-sm center-block btn-file']//div[@class='Select-input']//input")
	private WebElement search_TeamMemberLookup;
	
	@FindBy(xpath = "//button[contains(.,'Save Team')]")
	private WebElement button_SaveTeam;
	
	@FindBy(xpath = "//a[contains(.,'Team Name1')]")
	private WebElement groupName;
	

	@FindBy(xpath = "//textarea[@placeholder='Your Message here']")
	private WebElement messageBody;
	
	@FindBy(xpath = "//div[@class='col-md-12 Imail_btn_left_v1 px-0 py-3']//i[@class='icon icon-share-icon']")
	private WebElement btn_Send;
	
	
	
	 
	/**
	 * Verifying the Internal Imail UI
	 * @author Shwetha S Nov 4, 2019
	 */ 
	 
	public void Admin_Internal_verifyUI() {
		
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.link_Internal_Imail,"Click on Internal Imail");
		com.isElementPresent(search_Field,"search field present");
		com.isElementPresent(dropDown_FilterBy,"Filter by select present");
		com.isElementPresent(dropDown_FilterByDate,"Filter by date");
		com.isElementPresent(button_ComposeNewMessage,"ComposeNewMessage");
	}
	
	/**
	 * enter the value in search field and verify
	 * @author Shwetha S Nov 4, 2019
	 */
	public void verify_InternalImail_SearchFunctionlity() {
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.link_Internal_Imail,"Click on Internal Imail");
		 
        com.isElementPresent(search_Field,"Text To present");
		
		com.sendKeys(search_Field, "shwetha");
		com.sendKeys(search_Field, Keys.ENTER);
		
	}
	

	/**
	 * Select the date by FilterByDate
     * @author Shwetha S Nov 4, 2019
	 * 
	 */
	public void Internal_verifySortingFilterByDate() {
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.link_Internal_Imail,"Click on Internal Imail");
		com.isElementPresent(dropDown_FilterByDate);
		
	}
	

	/**
	 * Composing the mail
	 * @author Shwetha S Nov 4, 2019
	 */
	public void createNewMail(String userTxtForTo,String userTxtForFrom ){
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.link_Internal_Imail);
		com.click(button_ComposeNewMessage);
        com.wait(2);
        
        com.isElementPresent(txtField_To,"Text To present");
		
		com.sendKeys(txtField_To, userTxtForTo);
		
		com.wait(3);
		
		
		com.click(By.xpath(
				"//div[@class='Select-menu-outer']//div[contains(@class,'option')][contains(.,'" + userTxtForTo + "')]"),
				userTxtForTo + " option");
		
		com.wait(2);
		
		com.sendKeys(txtField_CC, userTxtForFrom);

		com.wait(3);
		
		
		com.click(By.xpath(
				"//div[@class='Select-menu-outer']//div[contains(@class,'option')][contains(.,'" + userTxtForFrom + "')]"),
				userTxtForFrom + " option");
		
		com.wait(2);
		com.sendKeys(txtField_Subject, "test");
		com.wait(2);
		com.sendKeys(txtArea_MessageBody, "test");
	}
	
	/*
	
	public void verify_SendDraftMail() {
		createNewMail("shwetha","Arch");
		com.click(button_Draft);
	}*/
	
	
	/**
	 * Compose the mail >>send >>login to OCS>>verify the sent mail
	 * @author Shwetha S Nov 4, 2019
	 * 
	 */
	public void ComposeNewMailOnClientPortal_VerifyAdmin() {
		createNewMail("shwetha","Arch");
		com.click(button_Send);
	}

	/**
	 * compose the mail >>send >> click on mail>> mark as unread
	 * @author Shwetha S Nov 4, 2019
	 */
	public void Internal_verifySortingFilterByUnread() {
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.link_External_Imail);
		com.isElementPresent(dropDown_FilterBy,"present");
	
		
		comm.selectByVisibleText(dropDown_FilterBy, "Unread");
	    com.wait(2);
		com.click(Participant_P,"Participant replied mail");
	}
	
	/**
	 * compose the mail and click on Draft
	 * @author Shwetha S Nov 4, 2019
	 */
	public void Internal_verify_SendDraftMail() {
		createNewMail("shwetha","Arch");
		com.click(button_Draft);
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.internal_Drafts);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		
		
		
	}
	/**
	 * compose the mail >>send >> click on mail>> mark as unread
	 * @author Shwetha S Nov 4, 2019
	 */ 
		
   public void Internal_Check_MailMarkAsUnRead() {
		
		createNewMail("6Romil", "arc");
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.click(icon_click_To_Make_MarkRead,"Mark as Unread");
		}



	/**
	 * Compose the mail >>send >> click on mail>> mark as Favourite
	 * @author Shwetha S Nov 4, 2019
	 */
	public void Internal_Check_MailMarkAsFavourites() {
		createNewMail("6Romil", "arc");
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.wait(2);
		com.isElementPresent(icon_click_To_Make_Favourite);
		com.click(icon_click_To_Make_Favourite,"Mark as Favourite");
		
	}
	
	

	/**
	 * Compose the mail >>send >> click on mail>> mark as Flagged
	 * @author Shwetha S Nov 4, 2019
	 */
	 public void Admin_Internal_Check_MailSetAsItemFlag() {
		createNewMail("6Romil", "arc");
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.waitForElementsTobe_Present(icon_click_To_Make_Flaged);
		com.click(icon_click_To_Make_Flaged,"Mark as Flaged");
		
	}

	 /**
	 * Compose the mail >>send >> click on mail>> mark as Block
	 * @author Shwetha S Nov 4, 2019
     */
	public void Internal_Check_Mail_BlockItem() {
	
		createNewMail("6Romil", "arc");
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.waitForElementsTobe_Present(icon_click_To_Make_Block);
		com.click(icon_click_To_Make_Block,"Mark as Block");
		com.waitForElementsTobe_Present(icon_Block,"Mail is set as blocked");
	 
    }



	/**
	* Compose the mail >>send >> click on mail>> mark as Archive
	 * @author Shwetha S Nov 4, 2019
	 */
	public void Internal_Check_Mail_Archive() {
		createNewMail("6Romil", "arc");
		com.click(button_Send);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		com.waitForElementsTobe_Present(icon_click_To_Make_Archieve);
		com.click(icon_click_To_Make_Archieve,"Mark as Archieve");
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.internal_Archived);
		com.wait(2);
		com.click(By.xpath("//a[contains(.,'test')]"));
		
	}
	
	/**
	 * Create the group
	 * @author Shwetha S Nov 4, 2019
	 */
	public void Verify_For_GroupMessages(String name){
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.link_Internal_Imail);
		
		com.waitForElementsTobe_Present(Group_Messages);
		com.click(Group_Messages,"Group_Messages clicked");
		com.waitForElementsTobe_Present(button_Plus);
		com.click(button_Plus);
		com.waitForElementsTobe_Present(team_Name);
		com.sendKeys(team_Name, "Team Name1");
        com.wait(2);
		com.isElementPresent(search_TeamMemberLookup);
		com.sendKeys(search_TeamMemberLookup, name);
		com.wait(3);
		
		
		com.click(By.xpath(
				"//div[@class='Select-menu-outer']//div[contains(@class,'option')][contains(.,'" + name + "')]"),
				name + " option");
		
		com.wait(2);
		com.click(button_SaveTeam);
		
	}
	

	/**
	 * Send the message in Created group
	 * @author Shwetha S Nov 4, 2019
	 */
	public void sendTheMessageInGroup(){
		Imail_Dashboard imd=new Imail_Dashboard();
		com.click(imd.link_Internal_Imail);
		
		com.waitForElementsTobe_Present(Group_Messages);
		com.click(Group_Messages,"Group_Messages clicked");
		
		com.click(groupName);
		com.sendKeys(messageBody, "testMessage");
		com.click(btn_Send);
	}
}
