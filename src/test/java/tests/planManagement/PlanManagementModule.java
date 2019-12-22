/**
 * 
 */
package tests.planManagement;

import org.testng.annotations.Test;

import or.admin.Approvals;
import or.admin.Logs;
import or.common.Navigator;
import or.planManagement.ApprovedInvoicesPage;
import or.planManagement.Duplicate_InvoicesPage;
import or.planManagement.Invoices_FollowUpPage;
import or.planManagement.LoginPage_PlanManagement;
import or.planManagement.Pending_Invoice_Page;
import or.planManagement.PlanManagement_Dashboard;

/**
 * @author Shwetha Nov 4, 2019 6:12:53 PM
 *
 */
public class PlanManagementModule {
    
	
	@Test(description = "TC01_PlanManagement_Loginpage",groups = {"UI and Func"})
	private void TC01_PlanManagement_Loginpage(){   
		
		
		}
	
	@Test(description = "TC02_PlanManagement_Dashboard_PageContents",groups = {"UI Only"})
	private void TC02_PlanManagement_Dashboard_PageContents(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.VerifyUI();
		
		}
	
	@Test(description = "TC03_PlanManagement_fetchInvoice",groups = {"Func only"})
	private void TC03_PlanManagement_fetchInvoice(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.clickOnFetchInvoice();
		
		}
	@Test(description = "TC04_PlanManagement_PythonScript",groups = {"Func only"})
	private void TC04_PlanManagement_PythonScript(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.clickOnPythonScript();
		
		}
	
	@Test(description = "TC05_PlanManagement_Chart_For_Paid_Duplicate_Automated_Manual",groups = {"Func only"})
	private void TC05_PlanManagement_Chart_For_Paid_Duplicate_Automated_Manual(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.Click_On_Paid_Duplicate_Automated_Manual();
		
		}
	

	@Test(description = "TC06_PlanManagement_toggle_between_Quantity_and_DollarValue",groups = {"UI and Func only"})
	private void TC06_PlanManagement_toggle_between_Quantity_and_DollarValue(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.Click_On_Quantity_And_Dollar_Value();
		
		}
	
	@Test(description = "TC07_PlanManagement_check_for_Pending_Invoices",groups = {"Func only"})
	private void TC07_PlanManagement_check_for_Pending_Invoices(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		Pending_Invoice_Page pi = new Pending_Invoice_Page();
		nav.traverseMenu_VerifyPageTitle(pi.title,pmd.button_PendingInvoiceStart);
		
		
		}
	
	@Test(description = "TC08_PlanManagement_check_for_Duplicate_Invoices",groups = {"Func only"})
	private void TC08_PlanManagement_check_for_Duplicate_Invoices(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		Duplicate_InvoicesPage di = new Duplicate_InvoicesPage();
		nav.traverseMenu_VerifyPageTitle(di.title,pmd.button_DuplicateinvoicesStart);
		
		}
	

	@Test(description = "TC09_PlanManagement_Invoices_FollowUp",groups = {"Func only"})
	private void TC09_PlanManagement_Invoices_FollowUp(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		Invoices_FollowUpPage ifp = new Invoices_FollowUpPage();
		nav.traverseMenu_VerifyPageTitle(ifp.title,pmd.button_FollowupStart);
		
		}
	
	@Test(description = "TC10_PlanManagement_Summary_Of_Invoices_search",groups = {"Func only"})
	private void TC10_PlanManagement_Summary_Of_Invoices_search(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.verify_Search_field("45","Archana", "184");
		
		}

	@Test(description = "TC11_PlanManagement_Participants_Name_Link",groups = {"Func only"})
	private void TC11_PlanManagement_Participants_Name_Link(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.click_on_ParticipantsName();
		
		}
	
	@Test(description = "TC12_PlanManagement_Status_Links_pending",groups = {"Func only"})
	private void TC12_PlanManagement_Status_Links_pending(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.click_On_StatusLink_Pending();
		
		}
	
	@Test(description = "TC13_PlanManagement_Status_Links_Duplicate",groups = {"Func only"})
	private void TC13_PlanManagement_Status_Links_Duplicate(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.click_On_StatusLink_Duplicate();
		
		}
	
	@Test(description = "TC14_PlanManagement_Status_Links_Follow_Up",groups = {"Func only"})
	private void TC14_PlanManagement_Status_Links_Follow_Up(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.click_On_StatusLink_Follow_Up();
		
		}
	
	@Test(description = "TC15_PlanManagement_Status_Links_Paid",groups = {"Func only"})
	private void TC15_PlanManagement_Status_Links_Paid(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		pmd.click_On_StatusLink_Paid();
		
		}
	
	@Test(description = "TC16_PlanManagement_Pending_Invoices_Page_Contents",groups = {"Func only"})
	private void TC16_PlanManagement_Pending_Invoices_Page_Contents(){ 
		Navigator nav = new Navigator();
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		nav.traverseMenu_VerifyPageTitle(Pending_Invoice_Page.title,pmd.main_Pending_Invoices);
		Pending_Invoice_Page pendingInPage=new Pending_Invoice_Page();
		pendingInPage.verify_Pending_Page_Contents();
		
		}
	
	@Test(description = "TC17_Invoices_RunPlanner",groups = {"Func only"})
	private void TC17_Invoices_RunPlanner(){ 
		Navigator nav = new Navigator();
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		nav.traverseMenu_VerifyPageTitle(Pending_Invoice_Page.title,pmd.main_Pending_Invoices);
		Pending_Invoice_Page pendingInPage=new Pending_Invoice_Page();
		pendingInPage.verifyRunplanner();
		
		}
	
	@Test(description = "TC18_PlanManagement_Pending_Invoices_Searchfield",groups = {"Func only"})
	private void TC18_PlanManagement_Pending_Invoices_Searchfield(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(Pending_Invoice_Page.title,pmd.main_Pending_Invoices);
		Pending_Invoice_Page pi=new Pending_Invoice_Page();
		pi.Pending_Invoices_Searchfield("45","1111","Mohini","Ram","02222","2019-02-17","2.0","agent");
		
		}
	
	@Test(description = "TC19_PlanManagement_Pending_Invoices_View",groups = {"Func only"})
	private void TC19_PlanManagement_Pending_Invoices_View(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(Pending_Invoice_Page.title,pmd.main_Pending_Invoices);
		Pending_Invoice_Page pi=new Pending_Invoice_Page();
		pi.verifyPendingInvoiceView();
		
	   }
	
	@Test(description = "TC20_PlanManagement_Pending_Invoices_Pagination",groups = {"Func only"})
	private void TC20_PlanManagement_Pending_Invoices_Pagination(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(Pending_Invoice_Page.title,pmd.main_Pending_Invoices);
		Pending_Invoice_Page pi=new Pending_Invoice_Page();
		pi.Pending_Invoices_Pagination();
		
	   }
	
	@Test(description = "TC21_PlanManagement_Approved_Invoices_PageContents",groups = {"Func only"})
	private void TC21_PlanManagement_Approved_Invoices_PageContents(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(ApprovedInvoicesPage.title,pmd.main_Approved_Invoices);
		ApprovedInvoicesPage ai=new ApprovedInvoicesPage();
		ai.verify_Approved_Page_Contents();
		
	   }
	@Test(description = "TC22_PlanManagement_Approved_Invoices_search",groups = {"Func only"})
	private void TC22_PlanManagement_Approved_Invoices_search(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(ApprovedInvoicesPage.title,pmd.main_Approved_Invoices);
		ApprovedInvoicesPage ai=new ApprovedInvoicesPage();
		ai.Approved_Invoices_Searchfield("45","1111","Mohini","Ram","02222","2019-02-17","2.0","agent");
	   }
	@Test(description = "TC23_PlanManagement_Approved_Invoices_Button_ExportCSV",groups = {"Func only"})
	private void TC23_PlanManagement_Approved_Invoices_Button_ExportCSV(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(ApprovedInvoicesPage.title,pmd.main_Approved_Invoices);
		ApprovedInvoicesPage ai=new ApprovedInvoicesPage();
		ai.verify_export_Csv();
	   }
	
	@Test(description = "TC24_PlanManagement_Approved_Invoices_Pagination",groups = {"Func only"})
	private void TC24_PlanManagement_Approved_Invoices_Pagination(){   
		PlanManagement_Dashboard pmd=new PlanManagement_Dashboard();
		Navigator nav = new Navigator();
		nav.traverseMenu_VerifyPageTitle(ApprovedInvoicesPage.title,pmd.main_Approved_Invoices);
		Pending_Invoice_Page pi=new Pending_Invoice_Page();
		pi.Pending_Invoices_Pagination();
	   }
	
}
