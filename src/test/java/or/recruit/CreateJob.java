package or.recruit;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class CreateJob {

	public static String title = "recruitment/job_opening/create_job";
	SeleniumMethods com;
	HCMCommon comm;

	public CreateJob() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//label[contains(.,'Job Type:')]//following-sibling::span//div[@class='Select-control']")
	private WebElement select_JobType;

	@FindBy(xpath = "//label[contains(.,'Job Category:')]//following-sibling::span//div[@class='Select-control']")
	private WebElement select_JobCategory;

	@FindBy(xpath = "//label[contains(.,'Job Sub Category')]//following-sibling::span//div[@class='Select-control']")
	private WebElement select_JobSubCategory;

	@FindBy(xpath = "//label[contains(.,'Job Position:')]//following-sibling::span//div[@class='Select-control']")
	private WebElement select_Jobposition;

	@FindBy(xpath = "//label[contains(.,'Employment Type:')]//following-sibling::span//div[@class='Select-control']")
	private WebElement select_EmploymentType;

	@FindBy(xpath = "//label[contains(.,'Salary Range:')]//following-sibling::span//div[@class='Select-control']")
	private WebElement selet_SalaryRange;

	@FindBy(xpath = "//label[contains(.,'Publish Salary')]")
	private WebElement checkBox_SalaryPublish;

	@FindBy(name = "from_date")
	private WebElement date_FromDate;

	@FindBy(xpath = "//input[@name='is_recurring']")
	private WebElement checkbox_DateRecurring;

	@FindBy(name = "to_date")
	private WebElement date_ToDate;

	@FindBy(xpath = "//input[@value='Preview Job']")
	private WebElement button_PreviewJob;

	@FindBy(xpath = "//div[@class='content_main']")
	private WebElement data_PreviewJob;

	@FindBy(xpath = "//input[@name='job_location']")
	private WebElement input_JobLocation;

	@FindBy(xpath = "//input[@name='phone']")
	private WebElement input_Phone;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement input_Email;

	@FindBy(xpath = "//input[@name='website']")
	private WebElement input_website;

	@FindBy(xpath = "//input[@value='Save As Draft']")
	private WebElement button_SaveAsDraft;

	@FindBy(xpath = "//input[@value='Post Job']")
	private WebElement button_PostJob;

	@FindBy(xpath = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	private WebElement editBox_JobTextLayout;

	@FindBy(xpath = "//iframe[@title='Rich Text Editor, editor1']")
	private WebElement iframe_JobText;

	@FindBy(xpath = "//span[contains(.,'Reset All Fields')]")
	private WebElement link_ResetAll;

	@FindBy(xpath = "//div[contains(@class,'Dialog')][contains(.,'Manage Required Documents')]//span[contains(@class,'close')]")
	private WebElement icon_Close_ManageDocument;

	// Job Text Layout objects
	@FindBy(xpath = "//a[contains(@title,'Bold')]")
	private WebElement icon_Bold;

	@FindBy(xpath = "//a[contains(@title,'Italic')]")
	private WebElement icon_Italic;

	@FindBy(xpath = "//a[contains(@title,'Underline')]")
	private WebElement icon_Underline;

	@FindBy(xpath = "//a[contains(@title,'Numbered')]")
	private WebElement icon_Numbered;

	@FindBy(xpath = "//a[contains(@title,'Bulleted')]")
	private WebElement icon_Bulleted;

	@FindBy(xpath = "//a[contains(@title,'Align Left')]")
	private WebElement icon_AlignLeft;

	@FindBy(xpath = "//a[contains(@title,'Center')]")
	private WebElement icon_Center;

	@FindBy(xpath = "//a[contains(@title,'Align Right')]")
	private WebElement icon_AlignRight;

	@FindBy(xpath = "//a[contains(@title,'Justify')]")
	private WebElement icon_Justify;

	/**
	 * 1.Go to Jobs page and enter create job fill Job details
	 * 
	 * @author Adarsh Aug 25, 2019
	 * @param data
	 * @param rowIndex
	 */
	public void fillJobDetails_RecurringJob() {

		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DAY_OF_MONTH, 1);
		String fromDate = Util.convertToString("dd/MM/yyyy", cal.getTime());
		com.sendKeys(date_FromDate, fromDate + Keys.ENTER);

		com.javaScript_Click(checkbox_DateRecurring);

	}

	/**
	 * 1.Go to Jobs page and enter create job fill Job details
	 * 
	 * @author Adarsh Aug 25, 2019
	 * @param fromDate string should be in format dd/MM/YYYY
	 * @param toDate string should be in format dd/MM/YYYY
	 * @param rowIndex
	 */
	public void fillJobDetails(String jobType, String jobCat, String jobSubCat, String jobPosition, String empType,
			String salaryRange, String fromDate, String toDate, String jobLoc, String phone, String email,
			String website) {

		
		if (jobType != null) {
			comm.selectByVisibleText(select_JobType, jobType );
		}
		
		if (jobCat != null) {
			comm.selectByVisibleText(select_JobCategory, jobCat );
		}
		
		if (jobSubCat != null) {
			comm.selectByVisibleText(select_JobSubCategory, jobSubCat );
		}
		
		if (jobPosition != null) {
			comm.selectByVisibleText(select_Jobposition, jobPosition );
		}
		
		if (empType != null) {
			comm.selectByVisibleText(select_EmploymentType, empType );
		}
		
		if (salaryRange != null) {
			comm.selectByVisibleText(selet_SalaryRange, salaryRange );
		}
		
		if (fromDate!=null) {
			comm.selectDate(date_FromDate, fromDate);
		}else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			comm.selectDate(date_FromDate, Util.convertToString("dd/MM/YYYY", cal.getTime()));
		}
		
		
		if (toDate !=null) {
			comm.selectDate(date_ToDate, toDate);
		}else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			comm.selectDate(date_ToDate, Util.convertToString("dd/MM/YYYY", cal.getTime()));
		}
		
		if (jobLoc != null) {
			com.sendKeys(input_JobLocation, jobLoc);
			com.wait(1);
			com.sendKeys(input_JobLocation, Keys.DOWN, Keys.ENTER);
			com.wait(1);
		}
		
		if (phone != null) {
			com.sendKeys(input_Phone, phone);
		}
		
		if (email != null) {
			com.sendKeys(input_Email, email);
		}
		
		if (website != null) {
			com.sendKeys(input_website, website);
		}

	}

	/** Go to Create Job page and verify a dropdowns value */
	public void verify_DropdownValues() {
		comm.verifyOptionsVisibleText(select_JobType, "NDIS", "Welfare", "Internal");
		comm.verifyOptionsVisibleText(select_JobCategory, "Cat 1", "Cat 2", "Cat 3");
		comm.selectByVisibleText(select_JobCategory, "Cat 1");
		comm.verifyOptionsVisibleText(select_JobSubCategory, "Cat 44");
		comm.verifyOptionsVisibleText(select_Jobposition, "Manager","Team Lead", "Developer", "QA");
		comm.verifyOptionsVisibleText(select_EmploymentType, "Full Time", "Casual", "Part Time");
		comm.verifyOptionsVisibleText(selet_SalaryRange, "$0-$30K", "$30K-$40K", "$40K-$50K", "$50K-$60K", "$60K-$70K",
				"$70K-$80K", "$80K-$90K", "$90K-$100K", "$100K-$150K", "$150K-$200K", "$200K+");

	}

	/**
	 * Go to Create job page and verify publish salory checkbox on preview page
	 * 
	 * @author Adarsh 19-Sep-2019
	 */
	public void checkBox_SalaryPublish() {
		comm.selectByVisibleText(selet_SalaryRange, "$30K-$40K");
		com.click(checkBox_SalaryPublish, "Click on check box for publish a Salary");
		com.click(button_PreviewJob, "Preview Page Showing Publish Salary");

		verifyContentOnPreviewJobPopup("Publish salary:");

	}

	/**
	 * -Go to Create Job page fill job details -Then go to verify all details on
	 * preview post
	 * 
	 * @author Adarsh Aug 25, 2019
	 * @param data
	 * @param rowIndex
	 */
	public void verifyJobDetailsOnPreviewpost() {

		comm.selectByVisibleText(select_Jobposition, "QA");
		com.sendKeys(input_JobLocation, "melbourne");
		com.wait(1);
		com.sendKeys(input_JobLocation, Keys.DOWN, Keys.ENTER);
		com.sendKeys(input_Phone, "3000000000000");
		com.sendKeys(input_Email, "abcd@gmail.com");
		com.sendKeys(input_website, "www.abcd.com");
		com.click(button_PreviewJob, "Preview Page Showing job details");

	}

	/**
	 * On preview job pop up. This method Verify a given data of a job preview
	 * popup. If correct data is present the Pass will be reported. If correct data
	 * not present the fail will be reported.
	 * 
	 * @author Adarsh 17-Sep-2019
	 */
	private void verifyContentOnPreviewJobPopup(String textToVerify) {
		String wholeContent = com.getText(data_PreviewJob);

		if (wholeContent.toLowerCase().contains(textToVerify.toLowerCase())) {
			CustomReporter.report(STATUS.PASS, "Preview Job Popup has " + textToVerify);
		} else {
			CustomReporter.report(STATUS.FAIL, "Preview Job Popup does NOT has " + textToVerify);
		}

	}

	public void verify_ClickCheckAllValidation() {
		com.click(button_SaveAsDraft, "button_SaveAsDraft");

		List<WebElement> ls = com
				.getDynamicElements(By.xpath("//div[@role='tooltip'][contains(.,'This field is required.')]"));
		if (ls.size() == 13) {
			CustomReporter.report(STATUS.PASS, "[This field is required] message is displayed for 13 elements");
		} else {
			CustomReporter.report(STATUS.FAIL, "[This field is required] message is NOT displayed for 13 elements");
		}

		ls = com.getDynamicElements(By.xpath("//div[@class='tooltip-inner'][contains(.,'Add address')]"));

		if (ls.size() == 1) {
			CustomReporter.report(STATUS.PASS, "[Add Address] message is displayed for 1 elements");
		} else {
			CustomReporter.report(STATUS.FAIL, "[Add Address] message is NOT displayed for 1 elements");
		}

		// This is a alternate way.
		/*
		 * try { WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(),
		 * Constant.wait); wait.until(ExpectedConditions.numberOfElementsToBe(
		 * By.xpath("//div[@role='tooltip'][contains(.,'This field is required.')]"),
		 * 14)); CustomReporter.report(STATUS.PASS,
		 * "[This field is required] message is displayed for 14 elements"); } catch
		 * (Exception e) { new CustomExceptionHandler(e,
		 * "[This field is required] element count to be 14"); }
		 */

	}

	/**
	 * 1.Go to Jobs page fill all data them click on reset button to check reset
	 * functionality
	 * 
	 * @author Adarsh 06-Sep-2019
	 */
	public void job_ResetFunctionality(String jobType, String jobCat, String jobSubCat, String jobPosition, String empType,
			String salaryRange, String fromDate, String toDate, String jobLoc, String phone, String email,
			String website) {
		fillJobDetails(jobType, jobCat, jobSubCat, jobPosition, empType, salaryRange, fromDate, toDate, jobLoc, phone, email, website);

		addDocument("Resume:Mandatory", "Cover Letter:Optional");
		/* com.sendKeys(editBox_JobTextLayout, "Test") */;
		com.click(link_ResetAll, "Click and reset all field");
	}

	/**
	 * This will add the document from Manage Document popup
	 * 
	 * @param documents its value should be like this "Resume:Mandatory", "Cover
	 *                  Letter:Optional", "First Aid Certificate"
	 * @author Adarsh 06-Sep-2019
	 */
	private void addDocument(String... documents) {

		CustomReporter.createNode("Adding documents " + Arrays.toString(documents));

		for (String singleItem : documents) {
			String docName = singleItem;
			String manOptional = "";

			if (singleItem.contains(":")) {
				docName = singleItem.split(":")[0];
				manOptional = singleItem.split(":")[1];
			}

			com.click(By.xpath("(//div[@class='Manage_li_']//span[contains(.,'" + docName + "')])[1]"), docName);

			if (!manOptional.equals("")) {
				com.click(By
						.xpath("//div[@class='Manage_li_'][contains(.,'" + docName + "')]//a[.='" + manOptional + "']"),
						manOptional);
			}

		}

	}

	public void job_SaveAsDraft(String jobType, String jobCat, String jobSubCat, String jobPosition, String empType,
			String salaryRange, String fromDate, String toDate, String jobLoc, String phone, String email,
			String website) {
		fillJobDetails(jobType, jobCat, jobSubCat, jobPosition, empType, salaryRange, fromDate, toDate, jobLoc, phone, email, website);
		addDocument("Resume", "Cover Letter");
		fillJobText("Abcd test");
		com.click(button_SaveAsDraft);
		com.waitForElementsTobe_Present(By.xpath("//p[contains(.,'Job saved')]"), "Job Saved as Draft successfully.");
	}

	private void fillJobText(String string) {
		com.switchTo_Frame(iframe_JobText);
		com.sendKeys(editBox_JobTextLayout, string);
		com.switchTo_DefaultContent();
	}

	/**
	 * Fill all mandatory fields on create job page Then verify job details on
	 * Preview page and click on Job post to save a job
	 */
	public void job_PreviewJobPost(String jobType, String jobCat, String jobSubCat, String jobPosition, String empType,
			String salaryRange, String fromDate, String toDate, String jobLoc, String phone, String email,
			String website) {
		fillJobDetails(jobType, jobCat, jobSubCat, jobPosition, empType, salaryRange, fromDate, toDate, jobLoc, phone, email, website);
		addDocument("Resume", "Cover Letter");
		fillJobText("Abcd test");
		com.click(button_PreviewJob);

		// Now verifying all the job details

		verifyContentOnPreviewJobPopup(jobType);
		verifyContentOnPreviewJobPopup(jobCat);
		verifyContentOnPreviewJobPopup(jobSubCat);
		verifyContentOnPreviewJobPopup(jobPosition);
		verifyContentOnPreviewJobPopup(empType);
		verifyContentOnPreviewJobPopup(salaryRange);
		verifyContentOnPreviewJobPopup(jobLoc);
		verifyContentOnPreviewJobPopup(phone);
		verifyContentOnPreviewJobPopup(email);
		verifyContentOnPreviewJobPopup(website);

		if(fromDate!=null) {
			verifyContentOnPreviewJobPopup(fromDate);
		}else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			verifyContentOnPreviewJobPopup(Util.convertToString("dd-MM-yyyy", cal.getTime()));
		}

		if(toDate!=null) {
			verifyContentOnPreviewJobPopup(toDate);
		}else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			verifyContentOnPreviewJobPopup(Util.convertToString("dd-MM-yyyy", cal.getTime()));
		}

		verifyContentOnPreviewJobPopup("Seek");
		verifyContentOnPreviewJobPopup("Website");
		com.click(button_PostJob);
		com.waitForElementsTobe_Present(By.xpath("//p[contains(.,'Job saved')]"), "Job Saved success message");
	}

	public void job_RecurringCheckBox() {
		fillJobDetails_RecurringJob();

		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DAY_OF_MONTH, 1);
		String fromDate = Util.convertToString("dd-MM-yyyy", cal.getTime());

		com.click(button_PreviewJob);

		verifyContentOnPreviewJobPopup(fromDate);
		verifyContentOnPreviewJobPopup("Is Recurring: Yes");

	}

	/**
	 * Verify job text layout on create job page
	 * 
	 * @author Adarsh 17-Sep-2019
	 */

	public void verify_JobTextLayoutContent() {
		com.isElementPresent(icon_Bold, "Bold");
		com.isElementPresent(icon_Italic, "Italic");
		com.isElementPresent(icon_Underline, "Underline");
		com.isElementPresent(icon_Numbered, "Numbered");
		com.isElementPresent(icon_Bulleted, "Bulleted");
		com.isElementPresent(icon_AlignLeft, "Align Left");
		com.isElementPresent(icon_Center, "Center");
		com.isElementPresent(icon_AlignRight, "AlignRight");
		com.isElementPresent(icon_Justify, "Justify");
	}

	/**
	 * Verify validation start date or end date
	 * 
	 * @author Adarsh 17-Sep-2019
	 */
	public void verifyValidation_StartDateOrEnddate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String fromDate = Util.convertToString("dd/MM/yyyy", cal.getTime());
		com.sendKeys(date_FromDate, fromDate + Keys.ENTER);

		cal.add(Calendar.DAY_OF_MONTH, -1);
		String toDate = Util.convertToString("dd/MM/yyyy", cal.getTime());
		com.sendKeys(date_ToDate, toDate + Keys.ENTER);

		com.click(button_SaveAsDraft, "button_SaveAsDraft");
	}

}