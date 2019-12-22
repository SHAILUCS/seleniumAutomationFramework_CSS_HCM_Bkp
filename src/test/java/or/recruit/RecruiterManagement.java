package or.recruit;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;
import or.common.SideBar_Recruitment;

public class RecruiterManagement {

	public static final String title = "admin/recruitment/user_management";
	SeleniumMethods com;
	HCMCommon comm;

	public RecruiterManagement() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//span[@class='icon icon-back1-ie']")
	private WebElement icon_Back;

	@FindBy(xpath = "//h1[.='Recruiter Management']")
	private WebElement heading_RecruiterManagement;

	@FindBy(xpath = "//a[.='Add Recruiter']")
	private WebElement button_AddRecruiter;

	@FindBy(name = "srch_box")
	private WebElement text_SearchBox;

	@FindBy(xpath = "(//span[contains(@id,'react-select')][contains(@class,'value-wrapper')])[2]")
	private WebElement select_FilterDropdown;

	@FindBy(xpath = "//div[@class='trngBoxAc usrMngBox']")
	private WebElement data_QuickView;

	@FindBy(xpath = "//label[.='Allocated Recruitment Area:']/..//span[@class='Select-arrow']")
	private WebElement icon_Select_AllocatedRecruitmentArea;

	@FindBy(xpath = "//label[.='Preferred Recruitment Area:']/..//span[@class='Select-arrow']")
	private WebElement icon_Select_PreferredRecruitmentArea;

	@FindBy(xpath = "//button[contains(.,'Recruiter Details')]")
	private WebElement button_RecruiterDetails;

	@FindBy(xpath = "//span[contains(.,'Disable Recruiter')]")
	private WebElement link_DisableRecruiter;

	@FindBy(xpath = "//h3[contains(.,'Disable Recruiter')]")
	private WebElement heading_DisableRecruiter;

	@FindBy(xpath = "//h3[contains(.,'Disable Recruiter')]/span")
	private WebElement icon_Close_DisableRecruiter;

	@FindBy(xpath = "//label[contains(.,'account:')]//following-sibling::div//div[contains(@class,'place')]")
	private WebElement select_DisableRecruiterOption;

	@FindBy(xpath = "//label[contains(.,'Allocations:')]//following-sibling::div//div[contains(@class,'place')]")
	private WebElement select_AccountAllocation;

	private By disableRecruiterApplicantTable = By
			.xpath("//div[contains(@class,'Disable-Recruiter')]//div[contains(@class,'rt-table')]");

	@FindBy(name = "relevant_note")
	private WebElement text_DisableRecruiter_RelevantNotes;

	@FindBy(xpath = "//button[contains(.,'Disable Recruiter')]")
	private WebElement button_DisableRecruiter;

	// ADD RECRUITER POPOP OBJECTS

	@FindBy(xpath = "//h3[.='Add Recruiter']")
	private WebElement heading_AddRecruiter;

	@FindBy(xpath = "//h3[.='Add Recruiter']//span")
	private WebElement icon_AddRecruiter_Close;

	@FindBy(xpath = "//h3[.='Search for a recruiter:']/..//input")
	private WebElement text_SearchForRecruiter;

	@FindBy(xpath = "//h3[.='Recruiter Information:']")
	private WebElement label_RecruiterInformation;

	@FindBy(xpath = "//label[.='Name:']")
	private WebElement label_Name;

	@FindBy(xpath = "//label[.='HCM ID:']")
	private WebElement label_HcmId;

	@FindBy(xpath = "//label[.='Allocated Recruitment Area/s:']")
	private WebElement label_AllocatedRecruitmentAreas;

	@FindBy(xpath = "//label[.='Preferred Recruitment Area:']")
	private WebElement label_PreferredRecruitmentArea;

	@FindBy(xpath = "//label[.='Permission:']")
	private WebElement label_Permission;

	@FindBy(xpath = "//label[.='Email:']")
	private WebElement label_Email;

	@FindBy(xpath = "//label[.='Phone:']")
	private WebElement label_Phone;

	@FindBy(xpath = "//button[.='Apply Changes']")
	private WebElement button_ApplyChanges;

	/**
	 * This method will verify the UI of Recruiter Management page
	 * 
	 * @author shailendra Sep 4, 2019
	 */
	public void verifyUI() {
		com.isElementPresent(icon_Back, "Back icon");
		com.isElementPresent(heading_RecruiterManagement, "heading_Recruiter Management");
		com.isElementPresent(button_AddRecruiter, "button_Add Recruiter");
		com.isElementPresent(text_SearchBox, "text_Search Box");
		com.isElementPresent(select_FilterDropdown, "select_Filter Dropdown");

		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.verifyColumnHeaders(1, "Name", "HCMGR-ID", "Recruitment Area", "Start Date", "");
		int row = 2;
		int col = 5;
		com.isElementPresent(tab.getChildObject(row, col, "i", 0),
				"Quick View Icon in " + row + " Row and " + col + " Col");

		comm.verifyUI_Pagination_ViewBy();

		SideBar_Recruitment sidebar = new SideBar_Recruitment();
		
		sidebar.verifyUI_LeftNavigationPane();
	}

	/**
	 * Verifies the filter dropdown func
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	public void verifyFilterDropDownFunc() {
		applyFilterAndCheckImpact("Disabled");
		applyFilterAndCheckImpact("Active");
	}

	/**
	 * Will apply filter and check the impact
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	private void applyFilterAndCheckImpact(String data) {
		comm.selectByVisibleText(select_FilterDropdown, data);
		openQuickView();

		String quickViewContent = com.getText(data_QuickView);

		if (quickViewContent.contains(data)) {
			CustomReporter.report(STATUS.PASS, "Filter [" + data + "] is working");
		} else {
			CustomReporter.report(STATUS.FAIL, "Filter [" + data + "] is NOT working");
		}
	}

	/**
	 * Verifies the filter dropdown func
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	public void verifyQuickViewContent() {
		openQuickView();

		String quickViewContent = com.getText(data_QuickView);

		Util.comparator_NonNumbers("Recruiter Status:", quickViewContent, "Quick View Section");
		Util.comparator_NonNumbers("HCMGR-ID:", quickViewContent, "Quick View Section");
		Util.comparator_NonNumbers("Contact:", quickViewContent, "Quick View Section");
		Util.comparator_NonNumbers("Phone (Primary):", quickViewContent, "Quick View Section");
		Util.comparator_NonNumbers("Email (Primary):", quickViewContent, "Quick View Section");
		Util.comparator_NonNumbers("Experience:", quickViewContent, "Quick View Section");
		Util.comparator_NonNumbers("Access Permissions:", quickViewContent, "Quick View Section");
		Util.comparator_NonNumbers("Allocated Recruitment Area:", quickViewContent, "Quick View Section");

	}

	/**
	 * This will verify the valid text search
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	public void performTextSearch(String data) {
		com.waitForElementsTobe_Present(text_SearchBox);
		comm.verifyValid_TextSearch(text_SearchBox, data);
	}

	/**
	 * Opens quick view and verify the Allocated Preferred Recruitment Areas func
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	public void verifyAllocatedPreferredRecruitmentAreas_OnQuickView() {
		openQuickView();

		removePreviousAllocatedRecruitmentAreas();
		removePreviousPreferredRecruitmentAreas();

		selectAny_AllocatedRecruitmentArea();
		selectAny_PreferredRecruitmentArea();

	}

	/**
	 * This will select any available Prefrerred Recruitment Area
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	private void selectAny_PreferredRecruitmentArea() {
		CustomReporter.createNode("Adding Preferred Recruitment Areas");
		By selectIcon = By.xpath("//label[.='Preferred Recruitment Area:']/..//span[@class='Select-arrow-zone']");
		By options_All = By.xpath("//label[.='Preferred Recruitment Area:']/..//div[@role='option']");
		By options_First = By.xpath("(//label[.='Preferred Recruitment Area:']/..//div[@role='option'])[1]");
		By closeIcon_All = By.xpath("//label[.='Preferred Recruitment Area:']/..//span[contains(@class,'icon')]");

		selectFirstOption(selectIcon, options_All, options_First, closeIcon_All);

	}

	/**
	 * This will select first options from Allocated/Preferred Recruitment Area
	 * 
	 * @author shailendra Sep 17, 2019
	 * @param closeIcon_All
	 */
	private void selectFirstOption(By selectIcon, By options_All, By options_First, By closeIcon_All) {

		com.waitForElementsTobe_Present(selectIcon);

		com.click(selectIcon, "Select Icon");

		if (com.getDynamicElements(options_All).size() > 0) {

			int oldCount = com.getDynamicElements(closeIcon_All).size();

			String label = com.getText(options_First);
			com.click(options_First, "Selecting [" + label + "]");
			com.wait(2);
			com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

			int newCount = com.getDynamicElements(closeIcon_All).size();

			if (newCount > oldCount) {
				CustomReporter.report(STATUS.PASS, label + " successfully added");
			} else {
				CustomReporter.report(STATUS.FAIL, label + " is NOT added");
			}
		}
	}

	/**
	 * This will select any available Allocated Recruitment Area
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	private void selectAny_AllocatedRecruitmentArea() {
		CustomReporter.createNode("Adding Allocated Recruitment Areas");
		By selectIcon = By
				.xpath("//label[contains(.,'Allocated Recruitment Area')]/..//span[@class='Select-arrow-zone']");
		By options_All = By.xpath("//label[contains(.,'Allocated Recruitment Area')]/..//div[@role='option']");
		By options_First = By.xpath("(//label[contains(.,'Allocated Recruitment Area')]/..//div[@role='option'])[1]");
		By closeIcon_All = By
				.xpath("//label[contains(.,'Allocated Recruitment Area')]/..//span[contains(@class,'icon')]");

		selectFirstOption(selectIcon, options_All, options_First, closeIcon_All);
	}

	/**
	 * This method will remove the Previously selected Allocated Recruitment Areas,
	 * so that we can check the add new functionality
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	private void removePreviousPreferredRecruitmentAreas() {
		CustomReporter.createNode("Removing Previously Preferred Recruitment Areas");
		By closeIcon_All = By
				.xpath("//label[contains(.,'Preferred Recruitment Area')]/..//span[contains(@class,'icon')]");
		By closeIcon_First = By
				.xpath("(//label[contains(.,'Preferred Recruitment Area')]/..//span[contains(@class,'icon')])[1]");
		By closeIcon_Text = By
				.xpath("(//label[contains(.,'Preferred Recruitment Area')]/..//span[contains(@class,'label')])[1]");

		removeRecursively(closeIcon_All, closeIcon_First, closeIcon_Text);

	}

	private void removeRecursively(By closeIcon_All, By closeIcon_First, By closeIcon_Text) {
		while (true) {
			int count = com.getDynamicElements(closeIcon_All).size();
			if (count > 1) {
				String label = com.getText(closeIcon_Text);
				com.click(closeIcon_First, "Removing [" + label + "]");
				com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);
				com.wait(2);
			} else {
				break;
			}
		}
	}

	/**
	 * This method will remove the Previously selected Preferred Recruitment Areas,
	 * so that we can check the add new functionality
	 * 
	 * @author shailendra Sep 17, 2019
	 */
	private void removePreviousAllocatedRecruitmentAreas() {
		CustomReporter.createNode("Removing Previously Allocated Recruitment Areas");
		By closeIcon_All = By.xpath("//label[.='Allocated Recruitment Area:']/..//span[contains(@class,'icon')]");
		By closeIcon_First = By
				.xpath("(//label[.='Allocated Recruitment Area:']/..//span[contains(@class,'icon')])[1]");
		By closeIcon_Text = By
				.xpath("(//label[.='Allocated Recruitment Area:']/..//span[contains(@class,'label')])[1]");

		removeRecursively(closeIcon_All, closeIcon_First, closeIcon_Text);
	}

	/**
	 * This method will open the quick view of first row
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	public void openQuickView() {
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		int row = 2;
		int col = 5;
		com.click(tab.getChildObject(row, col, "i", 0), "Quick View Icon");
	}

	/**
	 * Perform search for passed data Open Quick view of first row click on view
	 * recruiter button
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	public void openRecruiterDetailsPage(String data) {
		comm.verifyValid_TextSearch(text_SearchBox, data);
		openQuickView();
		com.navigateToAndVerifyPageUrl(button_RecruiterDetails, RecruiterDetails.title);
	}

	/**
	 * Verifies the UI of Disable Recruiter Popup
	 * 
	 * @author shailendra Sep 18, 2019
	 */
	public void verifyDisablePopupUI(String data) {
		performTextSearch(data);
		openQuickView();
		com.click(link_DisableRecruiter, "link_DisableRecruiter");
		com.wait(1);

		com.isElementPresent(heading_DisableRecruiter, "heading_Disable Recruiter");
		com.isElementPresent(icon_Close_DisableRecruiter, "icon_Close_Disable Recruiter");
		com.isElementPresent(select_DisableRecruiterOption, "select_Disable Recruiter Option");
		com.isElementPresent(select_AccountAllocation, "select_Account Allocation");
		com.isElementPresent(disableRecruiterApplicantTable, "disable Recruiter Applicant Table");

		ReactTable tab = new ReactTable(disableRecruiterApplicantTable);
		tab.verifyColumnHeaders(1, "Name:", "Stage:", "Allocate To");

		com.isElementPresent(text_DisableRecruiter_RelevantNotes, "text_Disable Recruiter_Relevant Notes");
		com.isElementPresent(button_DisableRecruiter, "button_Disable Recruiter");

	}

	/**
	 * This will search for Recruiter and Select Allocated and Preferred areas
	 * 
	 * @author shailendra Oct 2, 2019
	 */
	private void fillAddRecruiterForm(String recruiterName) {
		searchForRecruiter_ToAdd(recruiterName);

		selectAny_PreferredRecruitmentArea();

		selectAny_AllocatedRecruitmentArea();
	}

	/**
	 * This will click on Add Reruiter button, to open the Add Recruiter Popup
	 * 
	 * @author shailendra Sep 23, 2019
	 * @param recruiterName Passing the Recruiter name(shail) to add him
	 * @return map A Map which stores values like this {"id":"360", "name":"Shailendra Rajawat"}
	 */
	public Map<String,String> addRecruiter_GetRecruiterFullNameAndId(String recruiterName) {
		
		CustomReporter.createNode("Searching and Adding a Recruiter ["+recruiterName+"] to Recruitment module");
		
		Map<String, String> map = new HashMap<String, String>();
		
		fillAddRecruiterForm(recruiterName);

		String recruiterFullName = com.getText(By.xpath("//h3[.='Search for a recruiter:']//following-sibling::div"));
		map.put("name", recruiterFullName);
		
		String hcmgrId = com.getText(By.xpath("//label[.='HCM ID:']//following-sibling::p"));
		map.put("id", hcmgrId);

		com.click(button_ApplyChanges, "button_Apply Changes");
		
		return map;
	}

	/**
	 * This will click on Add Reruiter button, to open the Add Recruiter Popup
	 * 
	 * @author shailendra Sep 23, 2019
	 * @param recruiterName This name will be searched
	 */
	public void searchForRecruiter_ToAdd(String recruiterName) {
		com.click(button_AddRecruiter, "button_Add Recruiter");

		com.wait(2);
		
		com.sendKeys("Recruiter", text_SearchForRecruiter, recruiterName);
		
		com.waitForElementTobe_NotVisible(comm.loadingTextOrCircleLocator);

		By autoFillXpath = By.xpath("//div[contains(.,'" + recruiterName + "')][@role='option']"); 
		
		com.waitForElementsTobe_Present(autoFillXpath);
		
		com.click_UsingAction(autoFillXpath, "Selecting "+recruiterName+" from Autofill");

	}

	/**
	 * Verifies the UI of Add Recruiter Popup
	 * 
	 * @param recruiterName This value will be sent to Search Recruiter Popup
	 * @author shailendra Sep 23, 2019
	 */
	public void verifyAddRecruiterPopupUI(String recruiterName) {

		searchForRecruiter_ToAdd(recruiterName);

		com.isElementPresent(heading_AddRecruiter, "heading_Add Recruiter");
		com.isElementPresent(icon_AddRecruiter_Close, "icon_Add Recruiter_Close");
		com.isElementPresent(text_SearchForRecruiter, "text_Search For Recruiter");
		com.isElementPresent(label_RecruiterInformation, "label_Recruiter Information");
		com.isElementPresent(label_Name, "label_Name");
		com.isElementPresent(label_HcmId, "label_HcmId");
		com.isElementPresent(label_AllocatedRecruitmentAreas, "label_Allocated Recruitment Areas");
		com.isElementPresent(label_PreferredRecruitmentArea, "label_Preferred Recruitment Area");
		com.isElementPresent(label_Permission, "label_Permission");
		com.isElementPresent(label_Email, "label_Email");
		com.isElementPresent(label_Phone, "label_Phone");
		com.isElementPresent(button_ApplyChanges, "button_ApplyChanges");
	}

	/**
	 * This method will add a new Recruiter and then Disable him, as this new
	 * Recruiter does not have any applicants attached to him
	 * 
	 * @author shailendra Sep 23, 2019
	 */
	public void verifyDisableRecruiter_NoParticipants(String recruiterName, String notes) {
		Map<String, String> map = addRecruiter_GetRecruiterFullNameAndId(recruiterName);
		performTextSearch(map.get("name").split(" ")[0]);
		openQuickView();
		openDisableRecruiterPopup();
		disableRecruiter_AutoAllocation(notes);
	}

	/**
	 * This method will Fill Disable Recruiter Form and Disable the recruiter, using
	 * Auto Allocation functionality
	 * 
	 * @param notes Relevant Notes to be filled on popup
	 * 
	 * @author shailendra Sep 23, 2019
	 */
	public void disableRecruiter_AutoAllocation(String notes) {

		comm.selectByVisibleText(select_DisableRecruiterOption, "Disable recruiter account");

		comm.selectByVisibleText(select_AccountAllocation, "Auto Allocation");

		com.sendKeys(text_DisableRecruiter_RelevantNotes, notes);

		com.click(button_DisableRecruiter, "button_Disable Recruiter");

		com.waitForElementTobe_Visible(By.xpath("//p[contains(.,'Staff disable successfully')]"),
				"Staff disable successfully Message");
	}
	
	/**
	 * Will click on Disable Recruiter link to open the Disbale Recruiter Popup
	 * 
	 * All Objects and Methods of Disable Recruiter Popup are present on {@link RecruiterManagement} class
	 * 
	 *  @author shailendra Oct 3, 2019
	 */
	public void openDisableRecruiterPopup() {
		com.click(link_DisableRecruiter, "link_Disable Recruiter");
	}

	/**
	 * This method will Fill Disable Recruiter Form and Disable the recruiter, using
	 * Custom Allocation functionality
	 * 
	 * @param newRecruiter
	 * @param relevantNotes
	 */
	public void disableRecruiter_CustomAllocation(String newRecruiter, String relevantNotes) {

		comm.selectByVisibleText(select_DisableRecruiterOption, "Disable recruiter account");

		comm.selectByVisibleText(select_AccountAllocation, "Custom Selection (Search)");
		
		// Now we will select the new recruiter in all applicants, using loop 
		ReactTable tab = new ReactTable(disableRecruiterApplicantTable);
		
		int rowCount = tab.getRowCount();
		
		if (rowCount>=2) {
			for (int row = 2; row < rowCount; row++) {
				WebElement input = tab.getChildObject(row, 3, "input", 0);
				com.sendKeys(input, newRecruiter);
				
				com.waitForElementsTobe_Present(By.xpath("//div[contains(.,'" + newRecruiter + "')][@role='option']"));
				
				com.click_UsingAction(By.xpath("//div[contains(.,'" + newRecruiter + "')][@role='option']"));
				
				com.wait(1);
				
				String rectPage = com.getText(tab.getCellObject(row, 3));
				String applcntPage = com.getText(tab.getCellObject(row, 1));
				if(rectPage.toLowerCase().contains(newRecruiter.toLowerCase())) {
					CustomReporter.report(STATUS.PASS, "New Recruiter ["+newRecruiter+"] assigned to applicant ["+applcntPage+"]");
				}else {
					CustomReporter.report(STATUS.FAIL, "Expected New Recruiter ["+newRecruiter+"] is NOT assigned to applicant ["+applcntPage+"], Actual value is ["+rectPage+"]");
				}
			}
		}

		com.sendKeys(text_DisableRecruiter_RelevantNotes, relevantNotes);

		com.click(button_DisableRecruiter, "button_Disable Recruiter");

		com.waitForElementTobe_Visible(By.xpath("//p[contains(.,'Staff disable successfully')]"),
				"Staff disable successfully Message");
	}

	/**
	 * Will click on Recruiter Details button on first row 
	 * @author shailendra Oct 3, 2019
	 */
	public void navigateToRecruiterDetails() {
		com.navigateToAndVerifyPageUrl(button_RecruiterDetails, RecruiterDetails.title);
	}

}
