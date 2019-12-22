package tests;

import org.testng.annotations.Test;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.Dashboard;
import or.common.HCMCommon;
import or.common.Navigator;
import or.imail.Imail_Dashboard;

public class Smoke {
	
	@Test(description = "Recruit Admin Page Navigation")
	private void recruitAdminPageNavigation() {
		Navigator nav = new Navigator();
		Dashboard d= new Dashboard();
		nav.traverseMenu_VerifyPageTitle("recruitment/dashboard", d.link_Recruitment);
	}
	
	@Test(description = "Participants Page Navigation")
	private void participantsPageNavigation() {
		Navigator nav = new Navigator();
		Dashboard d= new Dashboard();
		nav.traverseMenu_VerifyPageTitle("participant/dashboard", d.link_Participants);
	}

	@Test(description = "TC01_UI Verification of IMail Page")
	private void TC01_UIVerification_imailPage() throws InterruptedException {
		Navigator nav = new Navigator();
		Dashboard d= new Dashboard();
		
		/*
		 * SeleniumMethods com = new SeleniumMethods();
		 * com.click(d.link_Imail,"Imail Link");
		 * 
		 * HCMCommon comm= new HCMCommon();
		 * com.waitForElementsTobe_NotVisible(comm.spinner);
		 * 
		 * if(com.getCurrentUrl().contains("admin/imail/1dashboard")) {
		 * CustomReporter.report(STATUS.PASS, "Imail page opened successfully"); }else {
		 * CustomReporter.report(STATUS.FAIL, "Imail page NOT opened"); }
		 */
		
		nav.traverseMenu_VerifyPageTitle("admin/imail/dashboard", d.link_Imail);
		
		Imail_Dashboard imd = new Imail_Dashboard();
		imd.verifyUI();
		
	}
	@Test(description = "TC01_UI Verification of FMSPage")
	private void TC01_UIVerification_FMSPage() {
		Navigator nav = new Navigator();
		Dashboard d= new Dashboard();
		
		SeleniumMethods com = new SeleniumMethods();
		com.click(d.link_FMS,"FMS Link");
		
	}
	@Test(description = "TC01_UI Verification of CRMPage")
	private void TC01_UIVerification_CRMPage() {
		        
		
		
		
		
	}
	
	
	
}
    