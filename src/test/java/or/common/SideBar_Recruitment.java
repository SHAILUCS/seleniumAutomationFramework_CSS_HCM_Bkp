package or.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class SideBar_Recruitment {

	SeleniumMethods com;
	HCMCommon comm;

	public SideBar_Recruitment() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	@FindBy(xpath = "//div[contains(@class,'sideHead')]")
	public WebElement heading_Recruitment;

	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Dashboard')]")
	public WebElement link_Dashboard;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Flagged Applicants')]")
	public WebElement link_FlaggedApplicants;

	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Duplicate Applicants')]")
	public WebElement link_DuplicateApplicants;

	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Pay Scale Confirmations')]")
	public WebElement link_PayScaleConfirmations;

	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[.='Schedules']")
	public WebElement link_Schedules;

	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Task Schedules')]")
	public WebElement link_TaskSchedules;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Tasks List')]")
	public WebElement link_TasksList;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[.='Jobs']")
	public WebElement link_Jobs_Main;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Jobs')][contains(@href,'jobs')]")
	public WebElement link_Jobs;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[.='Applicants']")
	public WebElement link_Applicants;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[.='Applicant List']")
	public WebElement link_ApplicantsList;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Training')]")
	public WebElement link_Training;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[.='Group Interview']")
	public WebElement link_GroupInterview;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Manage Group Interview')]")
	public WebElement link_ManageGroupInterview;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Question List')][contains(@href,'group_interview')]")
	public WebElement link_QuestionList_GroupInterview;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[.='CAB Day']")
	public WebElement link_CabDay;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Manage CAB Day')]")
	public WebElement link_ManageCabDay;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Question List')][contains(@href,'cab')]")
	public WebElement link_QuestionList_CabDay;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[.='Devices']")
	public WebElement link_Devices;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Devices List')]")
	public WebElement link_DevicesList;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Manage Devices')]")
	public WebElement link_ManageDevices;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Ipad')]")
	public WebElement link_Ipad;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Recruiter Management')]")
	public WebElement link_RecruiterManagement;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[.='Recruiter']")
	public WebElement link_Recruiter;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Department')]")
	public WebElement link_Department;
	
	@FindBy(xpath = "//aside[contains(@class,'sidebar')]//a[contains(.,'Round Robin Management')]")
	public WebElement link_RoundRobinManagement;
	
	public void verifyUI_LeftNavigationPane() {
		CustomReporter.createNode("verifying Left Navigation Pane Content");
		com.isElementPresent(heading_Recruitment,"heading_Recruitment");

		com.isElementPresent(link_Dashboard,"link_Dashboard");
		com.isElementPresent(link_FlaggedApplicants,"link_FlaggedApplicants");
		com.isElementPresent(link_DuplicateApplicants,"link_DuplicateApplicants");
		com.isElementPresent(link_PayScaleConfirmations,"link_PayScaleConfirmations");
		
		com.wait(2);
		com.isElementPresent(link_Schedules,"link_Schedules");
		com.isElementPresent(link_TaskSchedules,"link_TaskSchedules");
		com.isElementPresent(link_TasksList,"link_TasksList");
		com.isElementPresent(link_Jobs_Main,"link_Jobs_Main");
		com.isElementPresent(link_Jobs,"link_Jobs");
		com.isElementPresent(link_Applicants,"link_Applicants");
		com.isElementPresent(link_ApplicantsList,"link_ApplicantsList");
		com.isElementPresent(link_Training,"link_Training");
		com.isElementPresent(link_GroupInterview,"link_GroupInterview");
		com.isElementPresent(link_ManageGroupInterview,"link_ManageGroupInterview");
		com.isElementPresent(link_QuestionList_GroupInterview,"link_QuestionList_GroupInterview");
		com.isElementPresent(link_CabDay,"link_CabDay");
		com.isElementPresent(link_ManageCabDay,"link_ManageCabDay");
		com.isElementPresent(link_QuestionList_CabDay,"link_QuestionList_CabDay");
		com.isElementPresent(link_Devices,"link_Devices");
		com.isElementPresent(link_DevicesList,"link_DevicesList");
		com.isElementPresent(link_ManageDevices,"link_ManageDevices");
		com.isElementPresent(link_Ipad,"link_Ipad");
		com.isElementPresent(link_RecruiterManagement,"link_RecruiterManagement");
		com.isElementPresent(link_Recruiter,"link_Recruiter");
		com.isElementPresent(link_Department,"link_Department");
		com.isElementPresent(link_RoundRobinManagement,"link_RoundRobinManagement");
	}


}
