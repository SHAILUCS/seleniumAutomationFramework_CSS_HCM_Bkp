package or.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.CustomExceptionHandler;
import com.seleniumExceptionHandling.ReactTable;
import com.seleniumExceptionHandling.SeleniumMethods;

public class HCMCommon {

	private SeleniumMethods com;

	public HCMCommon() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
	}

	/** Spinner By Object which should be used across HCM application */
	public By spinnerLocator = By.xpath("//div[contains(@class,'spinner')]");

	/** Loading By Object which should be used across HCM application */
	public By loadingTextOrCircleLocator = By.xpath("//*[contains(@class,'loading')]");

	/**
	 * Loading Three Green Bullet By Object which should be used across HCM
	 * application
	 */
	public By loadingBullet = By.xpath("*[contains(@class,'loading-bullet')]");

	/** Success popup By Object which should be used across HCM application */
	public By successPopup = By.cssSelector("div.Toastify div[class*='success']");

	/** Error popup By Object which should be used across HCM application */
	public By errorPopup = By.cssSelector("div.Toastify div[class*='error']");

	/** React Table locator, common across all pages of HCM application */
	public By reactTableLocator = By.xpath("//div[contains(@class,'rt-table')]");

	@FindBy(xpath = "//span[contains(@class,'arrow-left')]//parent::button[@class='Table__pageButton']")
	public WebElement icon_Pagination_LeftButton;

	@FindBy(xpath = "//span[contains(@class,'arrow-right')]//parent::button[@class='Table__pageButton']")
	public WebElement icon_Pagination_RightButton;

	@FindBy(xpath = "//div[@class='view_by_']//li[contains(.,'10')]")
	public WebElement link_ViewBy_10;

	@FindBy(xpath = "//div[@class='view_by_']//li[contains(.,'20')]")
	public WebElement link_ViewBy_20;

	@FindBy(xpath = "//div[@class='view_by_']//li[contains(.,'50')]")
	public WebElement link_ViewBy_50;

	@FindBy(css = "div[class*='next'] button[disabled]")
	public WebElement icon_Next_Disabled;

	@FindBy(css = "div[class*='next'] button")
	public WebElement icon_Next;

	@FindBy(xpath = "//li[contains(.,'50')]")
	public WebElement link_50;

	@FindBy(xpath = "//button[.='Confirm']")
	public WebElement button_Confirm_ConfirmationPopup;
	
	@FindBy(xpath = "//button[.='Cancel']")
	public WebElement button_Cancel_ConfirmationPopup;

	@FindBy(css = "span[class*='icon-cross']")
	public WebElement icon_Close_ConfirmationPopup;
	


	public void verifyUI_Pagination_ViewBy() {
		CustomReporter.createNode("verifying Pagination and View By links");
		com.isElementPresent(icon_Pagination_LeftButton, "icon_Pagination_LeftButton");
		com.isElementPresent(icon_Pagination_RightButton, "icon_Pagination_RightButton");
		com.isElementPresent(link_ViewBy_10, "link_ViewBy_10");
		com.isElementPresent(link_ViewBy_20, "link_ViewBy_20");
		com.isElementPresent(link_ViewBy_50, "link_ViewBy_50");
	}

	/**
	 * Use this method to select any visible text value from HCM dropdowns
	 * 
	 * @param dropdown    The Webelement object
	 * @param visibleText The visible text which you want to select
	 * @author shailendra
	 */
	public void selectByVisibleText(Object dropdown, String visibleText) {
		try {
			CustomReporter.report(STATUS.INFO, "Selecting option: [" + visibleText + "]");
			com.waitForElementsTobe_Present(dropdown);
			com.click_UsingAction(dropdown);
			//dropdown.click();
			com.wait(.5);
			By xp = By.xpath("//div[contains(@aria-label,'" + visibleText + "')]");
			com.javaScript_ScrollIntoMIDDLEView_AndHighlight(xp);
			// SnapshotManager.takeSnapShot("selectByVisibleText");
			DriverFactory.getDriver().findElement(xp).click();
			com.waitForElementTobe_NotVisible(loadingTextOrCircleLocator,5);
		} catch (Exception e) {
			new CustomExceptionHandler(e, "Visible Text " + visibleText);
		}
	}

	/**
	 * This method will check the valid text search functionality, on every Listing
	 * Page
	 * 
	 * @return row number of searched data
	 * 
	 * @author shailendra Aug 29, 2019
	 */
	public int verifyValid_TextSearch(Object text_Search, String searchData) {
		com.waitForElementTobe_NotVisible(loadingTextOrCircleLocator);
		com.sendKeys(text_Search, searchData + Keys.ENTER);
		
		com.wait(1);
		com.waitForElementTobe_NotVisible(loadingTextOrCircleLocator);
		
		ReactTable rct = new ReactTable(reactTableLocator);
		int row = rct.getRowWithCellText(searchData);
		if (row >= 1) {
			CustomReporter.report(STATUS.PASS,
					"Searched Data '" + searchData + "' successfully displayed in Result Table");
		} else {
			CustomReporter.report(STATUS.FAIL,
					"Searched Data '" + searchData + "' failed to displayed in Result Table");
		}

		return row;
	}

	/**
	 * This method will check the invalid text search functionality, on every
	 * Listing Page
	 * 
	 * @author shailendra Sep 3, 2019
	 */
	public void verifyInvalid_TextSearch(WebElement text_Search, String searchData, String expected) {
		com.sendKeys(text_Search, searchData + Keys.ENTER);
		com.waitForElementsTobe_NotVisible(loadingTextOrCircleLocator);

		ReactTable rct = new ReactTable(reactTableLocator);
		int row = rct.getRowWithCellText(expected);
		if (row >= 2) {
			CustomReporter.report(STATUS.PASS, "Data '" + expected + "' successfully displayed in Result Table");
		} else {
			CustomReporter.report(STATUS.FAIL, "Data '" + expected + "' failed to displayed in Result Table");
		}
	}

	/**
	 * Use this method to verify visible text of some/all options of every HCM
	 * dropdown
	 * 
	 * @param dropdown     The Webelement object
	 * @param visibleTexts The comma separated visible texts which you want to
	 *                     verify in the dropdown
	 * @author shailendra 30/08/2019
	 */
	public void verifyOptionsVisibleText(WebElement dropdown, String... visibleTexts) {
		try {
			dropdown.click();
			com.wait(.5);

			for (String visTxt : visibleTexts) {

				try {
					By xp = By.xpath("//div[contains(@aria-label,'" + visTxt + "')]");
					DriverFactory.getDriver().findElement(xp);
					com.javaScript_ScrollIntoMIDDLEView_AndHighlight(xp);
					CustomReporter.report(STATUS.PASS, "\"" + visTxt + "\" is displayed in the dropdown");
				} catch (Exception e) {
					CustomReporter.report(STATUS.FAIL, "\"" + visTxt + "\" is NOT displayed in the dropdown");
				}

			}
			dropdown.click();
		} catch (Exception e) {
			new CustomExceptionHandler(e, "Visible Text " + visibleTexts);
		}
	}

	// Table
	// $x("//div[@class='rt-table']")

	// Table >> Tbody || Thead >> Tr
	// $x("//div[@class='rt-table']//div[contains(@class,'thead') or
	// contains(@class,'tbody')]/div[contains(@class,'tr')]")

	// Thead
	// $x("//div[@class='rt-table']/div[contains(@class,'thead')]")

	// Thead >> Tr
	// $x("//div[@class='rt-table']/div[contains(@class,'thead')]/div[contains(@class,'tr')]")

	// Thead >> Tr[1] >> Td
	// $x("(//div[@class='rt-table']/div[contains(@class,'thead')]/div[contains(@class,'tr')])[1]//div[contains(@class,'td')
	// or contains(@class,'th')]")

	// Tbody
	// $x("//div[@class='rt-table']/div[contains(@class,'tbody')]")

	// Tbody >> Tr
	// $x("//div[@class='rt-table']/div[contains(@class,'tbody')]/div[contains(@class,'tr')]")

	// Tbody >> Tr[1] >> Td
	// $x("(//div[@class='rt-table']/div[contains(@class,'tbody')]/div[contains(@class,'tr')])[1]//div[contains(@class,'td')
	// or contains(@class,'th')]")

	/*
	 * 
	 * This method is used to select the month year and date from date picker
	 */

	public void monthYearAndDate(String dateObj, String dd) {
		try {

			WebDriver driver = DriverFactory.getDriver();

			String currMonth = driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']"))
					.getText();

			if (dateObj.equals(currMonth)) {
				CustomReporter.report(STATUS.PASS, "Month already selected");
			} else {
				for (int i = 1; i <= 12; i++) {
					driver.findElement(By.xpath("//button[contains(.,'Next month')]")).click();
					currMonth = driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']"))
							.getText();
					if (dateObj.equals(currMonth)) {
						CustomReporter.report(STATUS.PASS, "Month selected");
						break;
					}
				}
			}

			WebElement OnDate = driver.findElement(By.xpath("//div[@class='react-datepicker']"));

			List<WebElement> listOfWebEle = OnDate
					.findElements(By.xpath("//div[@class='react-datepicker__month']//following-sibling::div"));

			for (WebElement cell : listOfWebEle) {

				if (cell.getText().equals(dd)) {
					com.wait(2);
					cell.click();
					com.wait(2);
					CustomReporter.report(STATUS.PASS, "Date selected");
					break;
				}

			}

		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * 
	 * @author Shailendra 3 oct 19
	 */
	public void uploadFile(Object inputFileUploadObject, String absFilePath) {
		try {
			if (inputFileUploadObject instanceof By) {
				((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].className=''",
						com.getDynamicElement((By) inputFileUploadObject));
			} else {
				((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].className=''",
						(WebElement) inputFileUploadObject);
			}
			com.sendKeys(inputFileUploadObject, absFilePath);
		} catch (Exception e) {
			new CustomExceptionHandler(e, inputFileUploadObject.toString() + "|" + absFilePath);
		}
	}

	/**
	 * This will click on the date field to open the calendar, then it will select
	 * the passed date value
	 * 
	 * @param text_Date WebElement/By object
	 * @param date      string should be in dd/MM/YYYY format only
	 * @author Shailendra 22-Oct-2019
	 */
	public void selectDate(Object text_Date, String date) {
		String sep = "/";
		By button_Next = By.xpath("//button[contains(.,'Next month')]");
		By button_Prev = By.xpath("//button[contains(.,'Previous Month')]");
		By data_CurrentMonth = By.xpath("//div[contains(@class,'current-month')]");

		// Clicking on Date field, to open calendar
		com.click(text_Date);
		com.wait(.5);

		try {

			// From the passed date String, getting the numeric day, month, and year values
			int passedDay = Integer.parseInt(date.split(sep)[0]);
			int passedMonth = Integer.parseInt(date.split(sep)[1]) - 1;
			int passedYear = Integer.parseInt(date.split(sep)[2]);

			// Creating dynamic xpath to select day from visible calendar [month year]
			By day_Xpath = By.xpath(
					"//div[contains(@class,'day')][contains(@aria-label,'day')][not(contains(@class,'outside'))][.='"
							+ passedDay + "']");

			// reading the currently displayed default month and year from calendar
			String currentMonthFromCal = com.getText(data_CurrentMonth);

			// converting the String month and year values to number
			int currentMonthIndex = getMonthIndex(currentMonthFromCal.split(" ")[0]);
			int currentYear = Integer.parseInt(currentMonthFromCal.split(" ")[1]);

			// Now getting the difference in year to calculate how many clicks required on
			// Next icon
			if(passedYear > currentYear){
			
				int yearDiff = passedYear - currentYear;
	
				if (yearDiff > 0) {
	
					int loopCount = (11 - currentMonthIndex) + 12 * (yearDiff - 1) + 1;
	
					for (int i = 0; i < loopCount; i++) {
						com.click(button_Next);
					}
	
				}
			}else{
				int yearDiff = currentYear - passedYear ;
				
				if (yearDiff > 0) {
	
					int loopCount = (currentMonthIndex+1) * yearDiff;
	
					for (int i = 0; i < loopCount; i++) {
						com.click(button_Prev);
					}
	
				}
			}

			// After selecting the passed year, getting the current shown month to calculate
			// clicks for going to passed month
			currentMonthFromCal = com.getText(data_CurrentMonth);
			currentMonthIndex = getMonthIndex(currentMonthFromCal.split(" ")[0]);

			if(passedMonth > currentMonthIndex){
				int monthDiff = passedMonth - currentMonthIndex;
				if (monthDiff > 0) {
					for (int i = 0; i < monthDiff; i++) {
						com.click(button_Next);
					}
				}
			}else{
				int monthDiff = currentMonthIndex - passedMonth;
				if (monthDiff > 0) {
					for (int i = 0; i < monthDiff; i++) {
						com.click(button_Prev);
					}
				}
			}
			// Now we have reached to the desired month and year, selecting the valid day
			// from the shown calendar
			com.click(day_Xpath, "Selecting date [" + date + "]");

		} catch (Exception e) {
			new CustomExceptionHandler(e, date);
		}
	}

	/**
	 * This method returns the index of passed string month, used in selectDate method
	 * @author Shailendra 22-Oct-2019
	 */
	private int getMonthIndex(String monthName) {
		switch (monthName) {
		case "January":
			return 0;

		case "February":
			return 1;

		case "March":
			return 2;

		case "April":
			return 3;

		case "May":
			return 4;

		case "June":
			return 5;

		case "July":
			return 6;

		case "August":
			return 7;

		case "September":
			return 8;

		case "October":
			return 9;

		case "November":
			return 10;

		case "December":
			return 11;

		default:
			CustomReporter.report(STATUS.FAIL, "Invalid Input " + monthName + " to getMonthIndex method");
			return 0;
		}
	}
}
