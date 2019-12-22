package or.crm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class Prospective_Participants_Dashboard {

	public static final String title = "admin/crm/prospectiveparticipants";

	@FindBy(xpath = "//input[@name='search']")
	private WebElement search_btn;

	@FindBy(xpath = "//span[@class='Select-value-label']")
	private WebElement intaketype_dropdown;

	@FindBy(xpath = "//div[contains(text(),'NDIS No')]")
	private WebElement text_NDISno;

	@FindBy(xpath = "//div[contains(text(),'Participant Name')]")
	private WebElement text_participants_name;

	@FindBy(xpath = "//div[contains(text(),'Stage')]")
	private WebElement text_stage;

	@FindBy(xpath = "//div[contains(text(),'Assigned To')]")
	private WebElement text_assignedto;

	@FindBy(xpath = "//div[contains(text(),'Intake Submisson Date')]")
	private WebElement text_intakesubmissionDate;

	@FindBy(xpath = "//div[contains(text(),'Intake Type')]")
	private WebElement text_intakeType;

	@FindBy(xpath = "//a[@class='btn-1 w-100']")
	private WebElement button_createNewparticipants;

	@FindBy(xpath = "//a[@title='View']")
	private WebElement btn_more_view;

	@FindBy(xpath = "//a[(text()='Shift')]")
	private WebElement link_Shift;

	@FindBy(xpath = "//div[(text()='Shift')]")
	private WebElement title_Shift;

	@FindBy(xpath = "//input[@name='first_name']")
	private WebElement inpurt_firstname;;

	private SeleniumMethods com;
	HCMCommon comm;

	public Prospective_Participants_Dashboard() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();

	}

	/*
	 * This Method is use for verify the Prospective Participants
	 * 
	 * @author Archana Sept 19 , 2019
	 * 
	 * 
	 */
	public void prospective_Participants_UI_Elements_Varify() {

		com.isElementPresent(search_btn, "Search Button is present");
		ReactTable rct = new ReactTable(comm.reactTableLocator);
		rct.verifyColumnHeaders(1, "NDIS No", "Participant Name", "Stage", "Assigned To", "Intake Submisson Date",
				"Intake Type");
		com.isElementPresent(button_createNewparticipants, "Clicked to create new participants");
		comm.verifyUI_Pagination_ViewBy();

	}

	/**
	 * @author Archana Sept 19 , 2019
	 * 
	 *         This Method is use for open Quick View And Click On View more Button
	 * 
	 * 
	 */
	public void openQuickViewAndClickOnViewButton() {
		ReactTable rct = new ReactTable(comm.reactTableLocator);
		WebElement quickView = rct.getChildObject(2, 7, "i", 0);
		com.click(quickView, "QuickView is clicked");
		com.click(btn_more_view, "More_view button is clicked");

	}

}
