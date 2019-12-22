/**
 * 
 */
package tests.imail;

import org.testng.annotations.Test;

import com.configData_Util.Util;

import or.admin.UserManagement;
import or.clientPortal.LoginPage_ClientPortal;
import or.clientPortal.Dashboard_ClientPortal;
import or.common.Dashboard;
import or.common.LoginPage;
import or.common.Navigator;
import or.imail.Drafts;
import or.imail.External_Imail;
import or.imail.Imail_Dashboard;
import or.imail.Internal_Imail;
import or.imail.SideBarMenu;

/**
 * @author shwetha
 *
 */
public class Imail_Module {
	
	private Navigator navigateToImail_Dashboard() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		
		/**
		 * Fixing hard coded 
		 * @author Adarsh 29-Nov-2019 
		 * */
		nav.traverseMenu_VerifyPageTitle(Imail_Dashboard.title, d.link_Imail);
		return nav;
	}
	
	/**
	 * This will take us to External Imail Page
	 * @author Adarsh 29-Nov-2019 
	 * */
	private Navigator navigateToExternal_Imail_Inbox() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		Imail_Dashboard imd=new Imail_Dashboard();

		nav.traverseMenu_VerifyPageTitle(External_Imail.title, d.link_Imail, imd.link_External_Imail);
		return nav;
	}
	
	/**
	 * This will take us to Internal Imail Page
	 * @author Adarsh 29-Nov-2019 
	 * */
	private Navigator navigateToInternal_Imail_Inbox() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		Imail_Dashboard imd=new Imail_Dashboard();

		nav.traverseMenu_VerifyPageTitle(Internal_Imail.title, d.link_Imail, imd.link_Internal_Imail);
		return nav;
	}

	/****
	 * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.verify the UI
	 * @author Shwetha S Oct 7, 2019
	 * @author Adarsh 29-Nov-2019
	 */
	 
	@Test(description = "TC01_Admin_External_verifyUI", groups = {"UI Only"})
	public void Admin_External_verifyUI() throws InterruptedException{
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
		ei.verifyUI();
	}
  
	/****
	 * * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.verify the UI
	 * @author Shwetha S Oct 09, 2019
	 * @author Adarsh 29-Nov-2019
	 */
	@Test(description = "TC02_Admin-External_verify_SearchFunctionlity", groups = {"Func Only"})
	public void TC02_Admin_External_verify_SearchFunctionlity(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
		ei.verify_SearchFunctionlity("Auto");
		
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.verify SortingFilterBySelect
	 * @author Shwetha S Oct 09, 2019
	 * @author Adarsh 29-Nov-2019
	 */
	@Test(description = "TC03_Admin-External_verify_FilterByDropdown", groups = {"Func Only"})
	public void TC03_Admin_External_verify_FilterBy(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
		ei.verifyFilterBySelect();
	}
	
	/**
	 * EXCLUDED
	 * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.verify SortingFilterByDate
	 * @author Shwetha S Oct 09, 2019
	 * @author Adarsh 29-Nov-2019 
	 */
	@Test(description = "TC04_Admin_External_verify_SortingFilterByDate", groups = {"Func Only"})
	public void TC04_Admin_External_verify_SortingFilterByDate(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
		ei.verify_SortingFilterByDate();
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail  >> compose mail
	 * 3.verify SendDraftMail
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC05_Admin_External_verify_SendDraftMail", groups = {"Func Only"})
	public void TC05_Admin_External_verify_SendDraftMail(){
		Navigator nav = navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
	    
	    String timeStamp = "Auto"+Util.getTimeStamp_InMilliSec();
		ei.createDraft("brocks", "shailendra", timeStamp, timeStamp, null);
		
		SideBarMenu side = new SideBarMenu();
		
		nav.traverseMenu_VerifyPageTitle(Drafts.title, side.link_Drafts);
		
		Drafts dr = new Drafts();
		
		dr.verifyMailIsPresent(timeStamp);
	}
  
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail >> Compose Mail 
	 * 3. Verify ClientPortal
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC06_Admin_External_ComposeNewMail_Verify_ClientPortal", groups = {"Func Only", "Client Portal"})
	public void TC06_Admin_External_ComposeNewMail_Verify_ClientPortal(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
	    String timeStamp = "Auto"+Util.getTimeStamp_InMilliSec();
		ei.sendMail("brocks", "shailendra", timeStamp, timeStamp, null);
		
		LoginPage lp = new LoginPage();
		lp.logout();

		LoginPage_ClientPortal lpOCS = new LoginPage_ClientPortal();

		// @author adarsh 29 nov >> Changing the user name and password.
		lpOCS.loginToClientPortal("6Romil", "123456");

		Dashboard_ClientPortal dbOCS = new Dashboard_ClientPortal();
		dbOCS.verifyTheMail();
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail >> Compose Mail 
	 * 3. Verify ClientPortal >> Compose mail >>Verify Admin
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC07_Admin_External_ComposeNewMailOnClientPortal_VerifyAdmin", groups = {"Func Only","Client Portal"})
	public void TC07_Admin_External_ComposeNewMailOnClientPortal_VerifyAdmin(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
	    String timeStamp = "Auto"+Util.getTimeStamp_InMilliSec();
		ei.ComposeNewMailOnClientPortal_VerifyAdmin("brocks", "shailendra", timeStamp, timeStamp, null);
		LoginPage lp=new LoginPage();
	    lp.performIntelligentLogin("Admin");
		navigateToImail_Dashboard();
		ei.External_verifySortingFilterByUnread();
	}


	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.Mark Mail As Read
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC08_Admin_External_Check_MailMarkAsRead", groups = {"Func Only"})
	public void TC08_Admin_External_Check_MailMarkAsRead(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
	    String timeStamp = "Auto"+Util.getTimeStamp_InMilliSec();
		ei.External_Check_MailMarkAsUnRead("brocks", "shailendra", timeStamp, timeStamp, null);
	}
	
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.Mark Mail MailMarkAsFavourites
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC09_Admin_External_Check_MailMarkAsFavourites", groups = {"Func Only"})
	public void TC09_Admin_External_Check_MailMarkAsFavourites(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
	    String timeStamp = "Auto"+Util.getTimeStamp_InMilliSec();
		ei.External_Check_MailMarkAsFavourites("brocks", "shailendra", timeStamp, timeStamp, null);
	}
	
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.Mark Mail MailSetAsItemFlag
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	
	@Test(description = "TC10_Admin_External_Check_MailSetAsItemFlag", groups = {"Func Only"})
	public void TC10_Admin_External_Check_MailSetAsItemFlag(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
	    String timeStamp = "Auto"+Util.getTimeStamp_InMilliSec();
		ei.Admin_External_Check_MailSetAsItemFlag("brocks", "shailendra", timeStamp, timeStamp, null);
	}
	

	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.Mark MailBlockItem
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC11_Admin_External_Check_Mail_BlockItem", groups = {"Func Only"})
	public void TC11_Admin_External_Check_Mail_BlockItem(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
	    String timeStamp = "Auto"+Util.getTimeStamp_InMilliSec();
		ei.External_Check_Mail_BlockItem("brocks", "shailendra", timeStamp, timeStamp, null);
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail
	 * 3.Mark Mail_Archive
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC12_Admin_External_Check_Mail_Archive", groups = {"Func Only"})
	public void TC12_Admin_External_Check_Mail_Archive(){
		navigateToExternal_Imail_Inbox();
	    External_Imail ei=new External_Imail();
	    String timeStamp = "Auto"+Util.getTimeStamp_InMilliSec();
		ei.External_Check_Mail_Archive("brocks", "shailendra", timeStamp, timeStamp, null);
	}
	
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on Internal Imail
	 * 3.verify the UI
	 * @author Shwetha S Oct 7, 2019
	 * 
	 */
	 
	@Test(description = "TC13_Admin_Internal_verifyUI", groups = {"UI Only"})
	public void TC13_Admin_Internal_verifyUI(){
		navigateToImail_Dashboard();
	    Internal_Imail ii=new Internal_Imail();
		ii.Admin_Internal_verifyUI();
	}
	
	
	/**
	 * * 1.Naviating to Imail
	 * 2.Click on Internal Imail
	 * 3.verify the UI
	 * @author Shwetha S Oct 09, 2019
	 * 
	 * 
	 */
	
	@Test(description = "TC14_Admin_Internal_verify_SearchFunctionlity", groups = {"Func Only"})
	public void TC14_Admin_Internal_verify_SearchFunctionlity(){
		navigateToImail_Dashboard();
	    Internal_Imail ii=new Internal_Imail();
		ii.verify_InternalImail_SearchFunctionlity();
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on Internal Imail
	 * 3.verify SortingFilterByDate
	 * @author Shwetha S Oct 09, 2019
	 *  
	 * 
	 */
	@Test(description = "TC15_Admin_Internal_verify_SortingFilterByDate", groups = {"Func Only"})
	public void TC15_Admin_Internal_verify_SortingFilterByDate(){
		navigateToImail_Dashboard();
	    Internal_Imail ii=new Internal_Imail();
		ii.Internal_verifySortingFilterByDate();
	}
	

	/**
	 * 1.Naviating to Imail
	 * 2.Click on Internal Imail  >> compose mail
	 * 3.verify SendDraftMail
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC16_Admin_Internal_verify_SendDraftMail", groups = {"Func Only"})
	public void TC16_Admin_External_verify_SendDraftMail(){
		navigateToImail_Dashboard();
	    Internal_Imail ii=new Internal_Imail();
		ii.Internal_verify_SendDraftMail();
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on External Imail >> Compose Mail 
	 * 3. Verify ClientPortal >> Compose mail >>Verify Admin
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC17_Admin_Internal_ComposeNewMail_VerifyClient_Admin", groups = {"Func Only","Client Portal"})
	public void TC17_Admin_Internal_ComposeNewMail_VerifyClient_Admin(){
		navigateToImail_Dashboard();
	    Internal_Imail ii=new Internal_Imail();
		ii.ComposeNewMailOnClientPortal_VerifyAdmin();
		ii.Internal_verifySortingFilterByUnread();
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on Internal Imail
	 * 3.Mark Mail As Read
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC18_Admin_Internal_Check_MailMarkAsRead", groups = {"Func Only"})
	public void TC18_Admin_Internal_Check_MailMarkAsRead(){
		navigateToImail_Dashboard();
	    Internal_Imail ii=new Internal_Imail();
	    ii.Internal_Check_MailMarkAsUnRead();
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on Internal Imail
	 * 3.Mark MarkAsFavourites
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC19_Admin_Internal_Check_MailMarkAsFavourites", groups = {"Func Only"})
	public void TC19_Admin_Internal_Check_MailMarkAsFavourites(){
		navigateToImail_Dashboard();
		Internal_Imail ii=new Internal_Imail();
		ii.Internal_Check_MailMarkAsFavourites();
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on Internal Imail
	 * 3.Mark mail SetAsItemFlag
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC20_Admin_Internal_Check_MailSetAsItemFlag", groups = {"Func Only"})
	public void TC20_Admin_Internal_Check_MailSetAsItemFlag(){
		navigateToImail_Dashboard();
		Internal_Imail ii=new Internal_Imail();
		ii.Admin_Internal_Check_MailSetAsItemFlag();
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on Internal Imail
	 * 3.Mark mail BlockItem
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC21_Admin_Internal_Check_Mail_BlockItem", groups = {"Func Only"})
	public void TC21_Admin_Internal_Check_Mail_BlockItem(){
		navigateToImail_Dashboard();
		Internal_Imail ii=new Internal_Imail();
		ii.Internal_Check_Mail_BlockItem();
	}
	
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on Internal Archive
	 * 3.Mark mail BlockItem
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC22_Admin_Internal_Check_Mail_Archive", groups = {"Func Only"})
	public void TC22_Admin_Internal_Check_Mail_Archive(){
		navigateToImail_Dashboard();
		Internal_Imail ii=new Internal_Imail();
		ii.Internal_Check_Mail_Archive();
	}
	

	/**
	 * 1.Naviating to Imail
	 * 2.Click on GroupMessages
	 * 3.Create Group message
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC23_Admin_Internal_Check_GroupMessages", groups = {"Func Only"})
	public void TC23_Admin_Internal_Check_GroupMessages(){
		navigateToImail_Dashboard();
		Internal_Imail ii=new Internal_Imail();
		ii.Verify_For_GroupMessages("shwetha");
	}
	
	/**
	 * 1.Naviating to Imail
	 * 2.Click on GroupMessages
	 * 3.Create Group message and send the message in group
	 * @author Shwetha S Oct 09, 2019
	 *  
	 */
	@Test(description = "TC24_Admin_Internal_send_GroupMessages", groups = {"Func Only"})
	public void TC24_Admin_Internal_send_GroupMessages(){
		navigateToImail_Dashboard();
		Internal_Imail ii=new Internal_Imail();
		ii.sendTheMessageInGroup();
	}
}
