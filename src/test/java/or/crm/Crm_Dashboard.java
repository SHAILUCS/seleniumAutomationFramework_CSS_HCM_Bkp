
package or.crm;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.itextpdf.text.log.SysoCounter;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.Dashboard;
import or.common.HCMCommon;
import or.common.SideBar_CRM;

public class Crm_Dashboard {

	private Crm_Dashboard crm;

	/*
	 * @author Archana
	 * 
	 */
	public static final String titleUser = "admin/crm/participantuser";

	/*
	 * @author shwetha Dont make changes in below title
	 */
	public static final String titleAdmin = "admin/crm/participantadmin";

	@FindBy(xpath = "//h1[@class='my-0 color']")
	private WebElement Text_CRMAdminDashboard_Heading;

	@FindBy(xpath = "//h1[@class='my-0 color']")
	private WebElement Text_CRMUserDashboard_Heading;

	@FindBy(xpath = "//div[contains(text(),'New assigned Participants')]")
	private WebElement Text_NewAssignedParticipants_Heading;

	@FindBy(xpath = "//div[contains(text(),'Due Tasks: (Next 5 day)')]")
	private WebElement Text_DueTasks_Heading_5days;

	@FindBy(xpath = "//a[contains(text(),'Personal View')]")
	private WebElement Link_PersonalView;

	@FindBy(xpath = "//a[contains(text(),'CRM Department View')]")
	private WebElement Link_CRMDepartmentView;

	@FindBy(xpath = "//div[contains(text(),'Latest Action')]")
	private WebElement Text_Latest_Action;

	@FindBy(xpath = "//div[contains(text(),'Due Tasks:')]")
	private WebElement Text_DueTask_Heading;

	@FindBy(xpath = "//div[contains(text(),'Latest Updates')]")
	private WebElement Text_Latest_Updates;

	@FindBy(xpath = "//div[@class='col-lg-4 col-lg-offset-1 col-md-5']//a[@class='btn-1 w-100'][contains(text(),'View All')]")
	private WebElement btn_ViewAll;

	@FindBy(xpath = "//div[contains(text(),'User')]")
	private WebElement text_User;

	@FindBy(xpath = "//div[@class='col-lg-4 col-lg-offset-1 col-md-5']//div[@class='rt-resizable-header-content'][contains(text(),'Task')]")
	private WebElement Text_Task;

	@FindBy(xpath = "//div[@class='col-lg-4 col-lg-offset-1 col-md-5']//div[3]//div[1]")
	private WebElement Text_Action;

	@FindBy(xpath = "//div[contains(@class,'col-lg-5 col-md-5')]//div[contains(@class,'rt-resizable-header-content')][contains(text(),'Task')]")
	private WebElement Text_Duetask_task;

	@FindBy(xpath = "//div[contains(text(),'Participant')]")
	private WebElement Text_Duetask_Participants;

	@FindBy(xpath = "//div[contains(text(),'Due Date')]")
	private WebElement Text_Duetask_Duedate;

	@FindBy(xpath = "//div[contains(@class,'col-lg-5 col-md-5')]//div[contains(@class,'rt-resizable-header-content')][contains(text(),'Task')]")
	private WebElement Btn_Duetask_Viewall;

	@FindBy(xpath = "//div[@class='rt-table']//div[1]//div[1]//div[1]//div[1]//div[1]//p[2]")
	private WebElement Text_Active_LatedUpdate;

	@FindBy(xpath = "//div[@class='rt-table']//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]")
	private WebElement Text_Datetime_LatedUpdate;

	@FindBy(xpath = "//div[@class='rt-table']//div[1]//div[1]//div[1]//div[1]//div[1]//p[1]")
	private WebElement Text_ParticipantsName_LatedUpdate;

	@FindBy(xpath = "//div[@class='rt-table']//div[1]//div[1]//div[1]//div[1]//div[1]//p[3]")
	private WebElement Text_UserName_LatedUpdate;

	@FindBy(xpath = "//div[@class='rt-table']//div[1]//div[1]//div[1]//div[1]//div[1]//span[1]//a[1]//i[1]")
	private WebElement Icon_View;

	@FindBy(xpath = "//div[@class='col-lg-2 col-md-2']//a[@class='btn-1 w-100'][contains(text(),'View All')]")
	private WebElement btn_LatestUpdate_Viewall;

	@FindBy(xpath = "//div[@class='col-lg-5 col-lg-offset-1 col-md-6']//a[@class='btn-1 w-100'][contains(text(),'View All')]")
	private WebElement btn_Newassing_viewall;

	@FindBy(xpath = "//div[contains(text(),'Participant Name')]")
	private WebElement Text_ParticipantName;

	@FindBy(xpath = "//div[contains(text(),'Intake Type')]")
	private WebElement Text_IntakeType;

	@FindBy(xpath = "//div[contains(text(),'Intake Date')]")
	private WebElement Text_IntakeDate;

	@FindBy(xpath = "//div[contains(@class,'txt_t1')][1]")
	private WebElement Text_ParicipantsName_Quikeview;

	@FindBy(xpath = "//b[contains(text(),'NDIS No.:')]")
	private WebElement Text_NDISNumber_Quikeview;

	@FindBy(xpath = "//div[@class='txt_t2 my-2 pt-0']//b[contains(text(),'Email:')]")
	private WebElement Text_Email_QuikeView;

	
	@FindBy(xpath = "//button[contains(@class,'btn cmn-btn1 apli_btn__ eye-btn')]")
	private WebElement Btn_MoreInfo;

	@FindBy(xpath = "//div[@class='rt-tr-group']//div[@class='progressInline']")
	private WebElement Bar_Percentage_lessview;

	@FindBy(xpath = "//div[@class='progress-b2 progress']")
	private WebElement Bar_Percenta_Moreview;

	@FindBy(xpath = "//div[@class='Lates_up_txt2 btn-1'][1]")
	private WebElement LatestUpdate_Date;

	@FindBy(xpath = "//div[@class='Lates_up_a col-md-3 align-self-center text-right']")
	private WebElement Text_LatestUpdate;

	@FindBy(xpath = " //div[@class='rt-td T_align_m1'][1]")
	private WebElement Test_demo;

	@FindBy(xpath = "//a[contains(text(),'Staff Members')]")
	private WebElement staff_members;

	/*
	 * @FindBy(xpath = "//a[@class='v-c-btn1']") private WebElement add_crmBtn;
	 * 
	 * @FindBy(xpath =
	 * "//label[contains(.,'Search for a user:')]//following-sibling::div//input")
	 * // try now private WebElement text_search;
	 * 
	 * @FindBy(xpath = "//div[@class='col-md-3 pull-right py-3']") private
	 * WebElement Save_UserBtn;
	 */

	@FindBy(xpath = "//strong[contains(text(),'Prospective Participants')]")
	private WebElement Link_Prospective_Participants;

	@FindBy(xpath = " //div[contains(text(),'Assigned To:')]")
	private WebElement List_AssignedTo;

	@FindBy(xpath = "//div[contains(@class,'rt-table')]//div[1]//div[1]//div[7]//div[1]//i[1]")
	private WebElement Icon_Expand;

	@FindBy(xpath = "//span[@class='icon icon-view1-ie']")
	private WebElement Icon_MoreInfo;

	@FindBy(xpath = "//div[@class='progress-b2 progress']")
	private WebElement Bar_P_PercentageIntake;

	@FindBy(xpath = "//a[@class='in_prog_btn']")
	private WebElement btn_P_Status;

	@FindBy(xpath = "//strong[contains(text(),'Participant Information')]")
	private WebElement Text_ParticipantInformation;

	@FindBy(xpath = " //strong[contains(text(),'FMS Cases')]")
	private WebElement Heading_FMS;

	@FindBy(xpath = "//strong[contains(text(),'Plans and Attachments:')]")
	private WebElement Text_PlanAttachments;

	@FindBy(xpath = "//strong[contains(text(),'Intake Progress:')]")
	private WebElement Text_IntakePraocess;

	@FindBy(xpath = "//a[contains(text(),'Shift')]")
	private WebElement link_shift;

	@FindBy(xpath = "//div[contains(@class,'h-h1')]")
	private WebElement text_shiftHeader;

	@FindBy(xpath = "//div[contains(@class,'h-h1')]")
	private WebElement title_participantDetails;

	@FindBy(xpath = "//strong[contains(text(),'Assigned to:')]")
	private WebElement link_Assignedto;

	@FindBy(xpath = "//a[contains(@class,'btn-1 s2 px-4 ml-3')][contains(text(),'Change')]")
	private WebElement button_changeAssigneduser;

	@FindBy(xpath = "//div[contains(@class,'Lates_up_btn')]//i")
	private WebElement button_viewall;

	@FindBy(xpath = "//a[contains(text(),'Edit Participants Info')]")
	private WebElement button_editparticipants;

	@FindBy(xpath = "//i[contains(@class,'more-less glyphicon glyphicon-plus')]")
	private WebElement icon_FMSexpand;;

	@FindBy(xpath = " //a[contains(@class,'btn-1')][contains(text(),'Manage  Attachments')]")
	public WebElement button_manageAttachments;

	@FindBy(xpath = "//strong[contains(text(),'Intake Progress:')]")
	private WebElement text_intakeProgress;

	@FindBy(xpath = "//a[contains(.,'Create New Participant')]")
	public WebElement button_createNewparticipants;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement search_btn;

	@FindBy(xpath = "//div[@class='rt-tr -odd']")
	private WebElement latest_Textbox;

	private SeleniumMethods com;
	HCMCommon comm;
	private SideBar_CRM sidebar_crm;

	public Crm_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
		sidebar_crm = new SideBar_CRM();
	}

	/**
	 * Use this to verify UI of CRM_Admin Dashboard Page
	 */

	public void verify_CRMAdmin_UI() {
		com.isElementPresent(Text_CRMAdminDashboard_Heading, "Text_CRMAdminDashboard_Heading is present");
		com.click_UsingAction(Link_PersonalView, "Link_PersonalView is Clicking");
		com.isElementPresent(Text_NewAssignedParticipants_Heading, "Text_NewAssignedParticipants_Heading is Present");
		com.isElementPresent(Text_DueTasks_Heading_5days, "Due Tasks is present");
		com.click_UsingAction(Link_CRMDepartmentView, "Link_CRMDepartmentView is Clicking");
		com.isElementPresent(Text_Latest_Action, "Text_Latest_Action is present");
		com.isElementPresent(Text_DueTask_Heading, "Text_DueTask_Heading is present");
		com.isElementPresent(Text_Latest_Updates, "Text_Latest_Updates is Present");

	}

	public void verify_CRMUser_UI() {
		com.isElementPresent(Text_CRMUserDashboard_Heading, "Text_CRMUserDashboard_Heading is present");
		com.isElementPresent(Text_NewAssignedParticipants_Heading, "Text_NewAssignedParticipants_Heading is Present");
		com.isElementPresent(Text_DueTasks_Heading_5days, "Text_DueTasks_Heading is Present");
		com.refresh();

	}
	
	

	public void Verify_Latestactions() {
		com.isElementPresent(Text_Latest_Action, "Latest Action is Present");
		if (com.waitForElementsTobe_NotVisible(comm.reactTableLocator)) {

			ReactTable rct = new ReactTable(comm.reactTableLocator);
			rct.verifyColumnHeaders(1, "User", "Task", "Action");

			int row = rct.getRowCount();
			if (row <= 10) {
				System.out.println(row);
			} else {
				System.out.println("Row Count is :" + row);
			}
			int col = rct.getColCount(2);
			System.out.println(col);
			String TaskName = rct.getCellText(2, 1);
			System.out.println(TaskName);
		} else {
			System.out.println();

		}
	}
	
	
	/**
	 * 
	 */
	private void navigateToViewmore() {
		com.isElementPresent(Bar_Percentage_lessview, "% Bar is showing"); 
		ReactTable rctt = new ReactTable(comm.reactTableLocator); 
		com.click(rctt.getChildObject(2,7, "i", 0));
		System.out.println(com.getText(Text_ParicipantsName_Quikeview));
		System.out.println(com.getText(Text_NDISNumber_Quikeview));
		System.out.println(com.getText(Text_Email_QuikeView));
		//System.out.println(com.getText(Text_Reffer_Name_Quickview));
		com.click(Btn_MoreInfo); 
	}

	public void Verify_DueTask() {

		com.isElementPresent(Text_DueTask_Heading, "Text_DueTask_Heading is present");

		com.isElementPresent(Text_Duetask_task, "Task is Present");
		com.isElementPresent(Text_Duetask_Participants, "Participants is present");
		com.isElementPresent(Text_Duetask_Duedate, "Due date is Present");
		com.isElementPresent(Btn_Duetask_Viewall, "View all Button is showing");
		//System.out.println(com.getText(Text_ParticipantsName_LatedUpdate));
		//System.out.println(com.getText(Text_Active_LatedUpdate));
	}

	public void reactTableExample() {
		// Either WebElement or By Object

		// By obj = By.xpath("//asdasd");
		com.click_UsingAction(sidebar_crm.link_Prospective_Participants, "Prospective Participant Clicked");
		ReactTable rct = new ReactTable(By.xpath("comm.reactTableLocator"));

		// Quick view object

		WebElement quickView = rct.getChildObject(2, 7, "i", 0);

		com.click(quickView, "Quick View");

		// Alternate Way
		com.click(rct.getChildObject(2, 7, "i", 0), "Quick View");

		/*
		 * int row = rct.getRowCount(); if (row <= 10) { System.out.println(row); } else
		 * { System.out.println("Row Count is :" + row); } int col = rct.getColCount(2);
		 * System.out.println(col); rct.getCellText(2, 1);
		 */
	}

	public void verify_LatestUpdates() {
		String txtTab = com.getText(By.xpath("//div[.='Latest Updates']//following-sibling::div/div[contains(@class,'ReactTable')]"));
		System.out.println(txtTab);
		
		  if (txtTab.contains("No Update")) { 
			  CustomReporter.report(STATUS.PASS, "No Record Found message is displaying"); 
		  }else { 
			  latestUpdatetext();
		  
		  }
		 
			
		
	

	}
	/**
	 * @return 
	 * 
	 */
	private String latestUpdatetext() {
		com.isElementPresent(Text_Latest_Updates, "Latest Update is Present");
		com.isElementPresent(latest_Textbox);
		com.isElementPresent(Text_ParticipantsName_LatedUpdate, "Text_PartticipantsName_LatedUpdate");
		return null;

	}

	// div[@class='ReactTable -striped -highlight']//div[@class='rt-table']"

	public void Verify_NewAssignedParticipants() {

		try {
			com.isElementPresent(Text_CRMUserDashboard_Heading, "Text_CRMUserDashboard_Heading is present");
		} catch (Exception e) {
			System.out.println(e);
		}
		com.isElementPresent(Text_CRMUserDashboard_Heading, "Text_CRMUserDashboard_Heading is present");
		com.isElementPresent(Text_NewAssignedParticipants_Heading, "Text_NewAssignedParticipants_Heading is Present");
		/*
		 * ReactTable rct = new ReactTable( By.xpath("comm.reactTableLocator"));
		 * com.wait(4); int row = rct.getRowCount(); if (row <= 10) {
		 * System.out.println(row); } else { System.out.println("Row Count is :" + row);
		 * } int col = rct.getColCount(2); System.out.println(col); rct.getCellText(2,
		 * 1);
		 */
		// com.isElementPresent(btn_Newassing_viewall, "View all is present");
		com.isElementPresent(Text_ParticipantName, "Paticipant Name is present");
		com.isElementPresent(Text_IntakeType, "Intake Type is Present");
		com.isElementPresent(Text_IntakeDate, "Intake date is Present");

		String FN = com.getText(Test_demo);
		System.out.println("Participant name is : " + FN);

	}

	/*
	 * public void Verify_QuickView() {
	 * com.click_UsingAction(Link_Prospective_Participants); navigateToViewmore();
	 * com.isElementPresent(Bar_Percenta_Moreview, "% bar is Present");
	 * 
	 * }
	 */

	public void LatestUpdates_ParticipantsScreen() {
		com.click_UsingAction(Link_Prospective_Participants);
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		String txtTab = com.getText(By.xpath("//div[@class='Crm-Applicant_tBL']//following-sibling::div/div[contains(@class,'ReactTable')]"));
		//System.out.println(txtTab);

		  if (txtTab.contains("No rows found")) { 
		
			  CustomReporter.report(STATUS.PASS, "No rowa found message is displaying"); 
		  }
		else {
		
			navigateToViewmore();
			
		}

		
		
		
		 
	}

	public void Intakeprogressbar() {
		com.click_UsingAction(Link_Prospective_Participants);
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
		String txtTab = com.getText(By.xpath("//div[@class='Crm-Applicant_tBL']//following-sibling::div/div[contains(@class,'ReactTable')]"));
		System.out.println(txtTab);

	  if (txtTab.contains("No rows found")) { 
		
			  CustomReporter.report(STATUS.PASS, "No rowa found message is displaying"); 
		  }
		else {
		
		com.isElementPresent(Bar_Percentage_lessview, "% Bar is showing");
		com.click(Icon_Expand, "Icon_Expand is clicked");
		com.isElementPresent(Bar_Percenta_Moreview, "% bar is Present");
		}
	}

	public void shift_UI_verify() {
		
		navigateToViewmore();
		com.click(link_shift, "Clied on shift side link");
		com.isElementPresent(text_shiftHeader, "Shit hearder is present");
		

	}

	/**
	 * 
	 */
	public void Verify_ViewParticipantDetailsScreen() {
		com.click_UsingAction(Link_Prospective_Participants);
		com.isElementPresent(title_participantDetails, "Participants detail heading is showing");
		com.isElementPresent(search_btn, "Search Button is present");
		ReactTable rct = new ReactTable(comm.reactTableLocator);
		rct.verifyColumnHeaders(1, "NDIS No", "Participant Name", "Stage", "Assigned To", "Intake Submisson Date",
				"Intake Type");
		com.isElementPresent(button_createNewparticipants, "Clicked to create new participants");
		comm.verifyUI_Pagination_ViewBy();
	}
}