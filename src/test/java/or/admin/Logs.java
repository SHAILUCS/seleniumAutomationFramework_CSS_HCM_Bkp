package or.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.csvUtil.CSVManager;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

public class Logs {
	public static final String title = "admin/user/logs";

	SeleniumMethods com;
	HCMCommon comm;

	public Logs() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}

	@FindBy(xpath = "//div[@class='input_search change_b']//input")
	public WebElement search_field;

	@FindBy(xpath = "//div[@class='Table__pagination ']")
	public WebElement table_pagination;

	@FindBy(xpath = ("//span[@class='add_access  p-colr']"))
	public WebElement link_P;
	
	@FindBy(xpath = ("//span[@class='add_access  o-colr']"))
	public WebElement link_O;
	
	@FindBy(xpath = ("//span[@class='add_access  f-colr']"))
	public WebElement link_F;
	
	@FindBy(xpath = ("//span[@class='add_access  i-colr']"))
	public WebElement link_I;
	
	@FindBy(xpath = ("//span[@class='add_access  m-colr']"))
	public WebElement link_M;
	
	@FindBy(xpath = ("//span[@class='add_access  s-colr']"))
	public WebElement link_S;

	@FindBy(xpath = ("//div[@class='but']"))
	public WebElement button_DownloadAll;

	@FindBy(xpath = ("//a[contains(.,'Export Selected')]"))
	public WebElement button_Export_Selected;

	@FindBy(xpath = "//input[@name='shift_date']")
	public WebElement date_On_field;

	@FindBy(xpath = ("//div[@class='react-datepicker']"))
	public WebElement calender_form;
	
	@FindBy(xpath = ("//input[@name='start_date']"))
	public WebElement date_From_field;
	
	@FindBy(xpath = ("//input[@name='end_date']"))
	public WebElement date_To_field;
	
	@FindBy(xpath = ("//button[contains(.,'Next month')]"))
	public WebElement button_NextMonth;
	
	@FindBy(xpath = ("//div[@class='react-datepicker__current-month']"))
	public WebElement current_Month;
	
	@FindBy(xpath = ("(//div[@class='rt-td'])[4]"))
	public WebElement link_Edit;
	
	@FindBy(xpath = ("//div[@class='col-lg-8 col-lg-offset-1  col-md-12']"))
	public WebElement text_Partcipants;

	@FindBy(xpath = ("//label[@class='mb-0']//span"))
	public WebElement checkbox;
	
  
	
	/**
	 * 
	 * This method will verify the content of the Logs Page
	 * 
	 * @author Shwetha S Sep 18, 2019
	 */

	public void verify_pageContent() {
		com.isElementPresent(search_field, "Search Field is present");
		com.isElementPresent(link_P, "P link is present");
		com.isElementPresent(button_DownloadAll, "DownloadAll is present");
		com.isElementPresent(table_pagination, "Pagination  is present");
		com.isElementPresent(button_Export_Selected, "Export_Selected button is present");
	}

	/**
	 * 
	 * This method will verify the search field of the Logs Page
	 * 
	 * @author Shwetha S Sep 18, 2019
	 * @param searchData
	 */
	public void verify_textSearch(String searchData) {
		comm.verifyValid_TextSearch(search_field, searchData);
	}


	
	/**
	 * 
	 * comm.selectDate(date_Object,"30/Nov")
	 * 
	 * selectDate(WebElement dateObj, String dd_MMM)
	 * 
	 * Method to pass MonthYear and Date
	 * @author Shwetha S Sep 20, 2019 
	 */
	
	
	

	
	/****
	 * To select the On date
	 * @author Shwetha S Sep 20, 2019
	 * @param date string should be in dd/MM/YYYY format only
	 */
	
	public void datePickerOn(String date) {
		com.click(date_On_field);
		comm.selectDate(date_On_field,date);
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.waitForElementsTobe_Present(comm.reactTableLocator);
		String dateVal=tab.getCellText(2, 3);
		com.wait(2);
		if(dateVal.equals(date)){
			CustomReporter.report(STATUS.PASS, "Date "+date+" is displaying on based on entered date in table");
		}else {
			CustomReporter.report(STATUS.FAIL, "Date "+date+"is not displaying on based on entered date in table");
		}
		
	}
	

	/**
	 * To select FormDate 
	 * @author Shwetha S Sep 20, 2019
	 */
	
	public void FromDate(){
		// Click and open the datepickers
		com.click(date_From_field);
		comm.monthYearAndDate("September 2019","20");
		
     }
	
	
	/**
	 * To select ToDate 
	 * @author Shwetha S Sep 20, 2019
	 */
	public void ToDate(){
	com.click(date_To_field);
	comm.monthYearAndDate("September 2019","28");
	}
	
	
	/***
	 * 
	 * 1.Click on Participants
	 * 2.Verify for page content
	 * @author Shwetha S Sep 20, 2019
	 */
	public void  verify_Participants(){
		com.click(link_P, "P link is clicked");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.initHeaderColumnList(1);
		com.isElementPresent(search_field, "Search Field is present");
	    com.isElementPresent(button_DownloadAll, "DownloadAll is present");
		com.isElementPresent(table_pagination, "Pagination  is present");
		com.isElementPresent(button_Export_Selected, "Export_Selected button is present");
	}
	
	
	
	/***
	 * 
	 * 1.Click on Organisation
	 * 2.Verify for page content
	 * @author Shwetha S Oct 3, 2019
	 */
	public void  verify_Organisation(){
		com.click(link_O, "O link is clicked");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.initHeaderColumnList(1);
		com.isElementPresent(search_field, "Search Field is present");
	    com.isElementPresent(button_DownloadAll, "DownloadAll is present");
		com.isElementPresent(table_pagination, "Pagination  is present");
		com.isElementPresent(button_Export_Selected, "Export_Selected button is present");
	}

	

	/***
	 * 
	 * 1.Click on Fms
	 * 2.Verify for page content
	 * @author Shwetha S Oct 3, 2019
	 */
	public void  verify_Fms(){
		com.click(link_F, "F link is clicked");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.initHeaderColumnList(1);
		com.isElementPresent(search_field, "Search Field is present");
	    com.isElementPresent(button_DownloadAll, "DownloadAll is present");
		com.isElementPresent(table_pagination, "Pagination  is present");
		com.isElementPresent(button_Export_Selected, "Export_Selected button is present");
	}
	
	
	/***
	 * 
	 * 1.Click on Imail
	 * 2.Verify for page content
	 * @author Shwetha S Oct 3, 2019
	 */
	public void  verify_Imail(){
		com.click(link_I, "I link is clicked");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.initHeaderColumnList(1);
		com.isElementPresent(search_field, "Search Field is present");
	    com.isElementPresent(button_DownloadAll, "DownloadAll is present");
		com.isElementPresent(table_pagination, "Pagination  is present");
		com.isElementPresent(button_Export_Selected, "Export_Selected button is present");
	}

	/**
	1.Click on Members
	 * 2.Verify for page content
	 * @author Shwetha S Oct 3, 2019
	 */
	public void verify_Members() {
		com.click(link_M, "M link is clicked");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.initHeaderColumnList(1);
		com.isElementPresent(search_field, "Search Field is present");
	    com.isElementPresent(button_DownloadAll, "DownloadAll is present");
		com.isElementPresent(table_pagination, "Pagination  is present");
		com.isElementPresent(button_Export_Selected, "Export_Selected button is present");
	}
	
	
	

	/**
	 1.Click on Schedule
	 * 2.Verify for page content
	 * @author Shwetha S Oct 3, 2019
	 */
	public void verify_Schedule() {
		com.click(link_S, "S link is clicked");
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		tab.initHeaderColumnList(1);
		com.isElementPresent(search_field, "Search Field is present");
	    com.isElementPresent(button_DownloadAll, "DownloadAll is present");
		com.isElementPresent(table_pagination, "Pagination  is present");
		com.isElementPresent(button_Export_Selected, "Export_Selected button is present");
		
	}
	
	
	/**
	 * 1.Selects the Each row and
	 * 2.Click on ExportAll
	 * 3.And this will compares the downloded data with Table data
	 * @author Shwetha S Oct 3, 2019
	 */
	
	public void verify_ExportAll(){
		
		
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		
		String pageVal1 = tab.getCellText(2, 3);
		String pageVal2 = tab.getCellText(2, 5);
		
		com.javaScript_Click(tab.getChildObject(1, 1, "input", 0));
		
		com.click(button_Export_Selected);
		
		String filePath = Util.getDownloadedFilePath("log");
		
		CSVManager csv = new CSVManager(filePath);
		
		String csvVal1 = csv.getValue(1, 1);
		String csvVal2 = csv.getValue(1,3);
		
		Util.comparator_PageValues(pageVal1, csvVal1, "Date");
		Util.comparator_PageValues(pageVal2, csvVal2, "Description");
		
	}
	
	  public void Verify_Pagination(){
		comm.verifyUI_Pagination_ViewBy();
	  }
	
	
	/*public void Verify_participants(){
		SideBar_Main main=new SideBar_Main();
		main.openSideBar();
		com.click(main.link_Participants, "Click on Participants");
		main.closeSideBar();
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		com.waitForElementTobe_NotVisible(comm.loadingTextLocator);
        com.click(tab.getChildObject(2,7, "i", 0), "view link");
		com.wait(4);
		ParticipantDetails det=new ParticipantDetails();
		det.EditFname();
		com.wait(4);
		main.openSideBar();
		}
	
	  public void verify_LogsOfParticipants(){
		com.click(link_P);
		ReactTable tab = new ReactTable(comm.reactTableLocator);
		String text=tab.getCellText(2, 5);
		System.out.println(text);
		}
   */
	
	
	
	/***
	 * 
	 * Method to get Currentday
	 * @author Shwetha S Sep 20, 2019
	 */
	private String getCurrentDay() {

		// Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		// Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);

		// Integer to String Conversion
		String todayStr = Integer.toString(todayInt);

		return todayStr;
	}
	
	
	/** 
	 * method to getFutureDay +2
	 * @author Shwetha S Sep 20, 2019
	 */

	private String getFutureDay(){
	
		  DateFormat dateFormat = new SimpleDateFormat("dd");
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(new Date());
		    cal.add(Calendar.DATE, 2);
		    String newDate = dateFormat.format(cal.getTime());
            return newDate;
	}
}
			
	

