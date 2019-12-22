package com.seleniumExceptionHandling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.customReporting.snapshot.SnapshotManager;
import com.driverManager.DriverFactory;

/**
 * <b>Under Construction & Testing</b>
 * Use this class to work with React Tables in any web page
 * @author Shailendra
 */
public class ReactTable {

	/**
	 * Constants for Table Rows and Cell Locators
	 * */
	private static final String tableCellExpr = ".//child::div[contains(@class,'th') or contains(@class,'td')]";
	private static final String tableRowExpr = ".//div[contains(@class,'thead') or contains(@class,'tbody')]//div[contains(@class,'rt-tr')][@role='row']";
	
	/**
	 * Ivars
	 * */
	private By locator;
	private WebUtils webUtil;
	private List<WebElement> rowList;
	
	public ReactTable(Object element) {
		webUtil = new WebUtils();
		if(element instanceof WebElement) {
			this.locator = webUtil.getByObjectFromWebElement(element);
		}else {
			this.locator = (By) element;
		}
	}

	public String toString() {
		return locator.toString();
	}

	private WebElement getWebElement() {
		WebElement elem=null;
		try{
			WebDriver driver = DriverFactory.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
			elem=webUtil.getInteractableWebElementFromList(locator);
		}catch (Exception e) {
			new CustomExceptionHandler(e);
		}

		return elem;
	}

	/**
	 * This method provides you the list<WebElement> of all rows 
	 * of the table, whose locator was passed in WebTable 
	 * class constructor
	 * 
	 * @author shailendra.rajawat 19 Feb 2019
	 * */
	private List<WebElement> getRowsList() {
		if(rowList == null) {
			//List<WebElement> listTR = null;
			rowList = new ArrayList<WebElement>();
			try{ 
				DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				//listTR = new ArrayList<WebElement>();
				// Getting null pointer exception on this line, even though the table is getting displayed
				//listTR = getWebElement().findElements(By.xpath(tableRowExpr));
				rowList = getWebElement().findElements(By.xpath(tableRowExpr));
				DriverFactory.getDriver().manage().timeouts().implicitlyWait(Constant.implicitWait, TimeUnit.SECONDS);
			}catch(Exception e){
				CustomReporter.report(STATUS.FAIL, "getRowsList() method failed for locator "+locator+" <br/><br/> Outer HTML "+getWebElement().getAttribute("outerHTML"));
				new CustomExceptionHandler(e);
			}
		}
		return rowList;
	}

	/**
	 * use this method to get the Row count of a "table"
	 * 
	 * @return int rowCount the count of tr, and in case of exception will
	 *         return -1
	 */
	public int getRowCount() {

		int rowCount = -1;
		try {
			rowCount = getRowsList().size();
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}

		return rowCount;
	}

	/**
	 * use this method to get the Column count in a Row of a "table"
	 * 
	 * @param rowNum
	 *            The Row number, from which the column count is need to be
	 *            fetched
	 * @return int colCount the count of td inside tr, and in case of exception
	 *         will return -1
	 */
	public int getColCount(int rowNum) {
		int colCount = -1;
		try {
			List<WebElement> listTR = getRowsList();
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath(tableCellExpr));
			colCount = listTD_TH.size();
			//List<WebElement> listTR = getRowList();
			// List<WebElement>
			// listTD_TH=listTR.get(rowNum-1).findElements(By.cssSelector("th,td"));

		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}
		return colCount;
	}

	/**
	 * use this method to get the String innerText of the cell pointed by rowNum
	 * and colNum
	 * 
	 * @param rowNum
	 *            The Row number of cell
	 * @param rowNum
	 *            The Column number of cell
	 * @return The innerText displayed in the cell, and in case of exception
	 *         will return null
	 */
	public String getCellText(int rowNum, int colNum) {

		String cellTxt = null;
		try {
			List<WebElement> listTR = getRowsList();
			// List<WebElement>
			// listTD_TH=listTR.get(rowNum-1).findElements(By.cssSelector("th,td"));
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath(tableCellExpr));
			WebElement cellObj = listTD_TH.get(colNum - 1);
			webUtil.javaScript_ScrollIntoMIDDLEView_AndHighlight(cellObj);
			SnapshotManager.takeSnapShot("getCellText()");
			cellTxt = cellObj.getText().replace("\r\n", " ").replace("\n", " ");
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}
		return cellTxt;
	}

	/**
	 * use this method to get the WebElement Object(<td>) pointed by rowNum
	 * and colNum. This method is useful when we want to apply getAttribute 
	 * method on the Table cell itself.
	 *  
	 * @param rowNum
	 *            The Row number of cell
	 * @param colNum
	 *            The Column number of cell
	 * @return The WebElement Object, and in case of
	 *         exception will return null
	 */
	public WebElement getCellObject(int rowNum, int colNum) {
		WebElement cell = null;
		try {
			List<WebElement> listTR = getRowsList();
			// List<WebElement>
			//listTD_TH = listTR.get(rowNum - 1).findElements(By.cssSelector("th,td"));
			List<WebElement> listTD_TH=listTR.get(rowNum-1).findElements(By.xpath(tableCellExpr)); 
			WebElement cellObj = listTD_TH.get(colNum - 1);
			webUtil.javaScript_ScrollIntoMIDDLEView_AndHighlight(cellObj);
			cell = cellObj;
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}
		return cell;
	}


	/**
	 * use this method to get the List of WebElements matching the passed
	 * Tag(for e.g. "img","a" etc) present in the cell(pointed by passed row and
	 * col num)
	 * 
	 * @param rowNum
	 *            The Row number of cell
	 * @param rowNum
	 *            The Column number of cell
	 * @param tag
	 *            The tag of desired webelement, it could be any valid html tag,
	 *            common examples are "a","img".
	 * @return The matching tags inside the passed cell, it will skip all
	 *         hierarchy and fetch the desired objects list, and in case of
	 *         exception will return null
	 */
	public List<WebElement> getChildObjects(int rowNum, int colNum, String tag) {

		List<WebElement> childObjects = null;
		try {
			List<WebElement> listTR = getRowsList();
			// List<WebElement>
			// listTD_TH=listTR.get(rowNum-1).findElements(By.cssSelector("th,td"));
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath(tableCellExpr));
			childObjects = listTD_TH.get(colNum - 1).findElements(By.tagName(tag));
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}
		return childObjects;
	}

	/**
	 * use this method to get the single WebElement matching the passed Tag(for
	 * e.g. "img","a" etc) present in the cell(pointed by passed row and col
	 * num) based on passed index
	 * 
	 * @param rowNum
	 *            The Row number of cell
	 * @param rowNum
	 *            The Column number of cell
	 * @param tag
	 *            The tag of desired webelement, it could be any valid html tag,
	 *            common examples are "a","img".
	 * @param index
	 *            The index of desired webelement from the list, index starts
	 *            with 0
	 * @return The element which is present on the passed index, matching the
	 *         tag in a cell pointed by passed row and col nums, and in case of
	 *         exception will return null
	 */
	public WebElement getChildObject(int rowNum, int colNum, String tag, int index) {

		WebElement childObject = null;
		try {
			List<WebElement> listTR = getRowsList();
			// List<WebElement>
			// listTD_TH=listTR.get(rowNum-1).findElements(By.cssSelector("th,td"));
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath(tableCellExpr));
			childObject = listTD_TH.get(colNum - 1).findElements(By.tagName(tag)).get(index);
			webUtil.javaScript_ScrollIntoMIDDLEView_AndHighlight(getCellObject(rowNum, colNum));
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}
		return childObject;
	}

	/**
	 * use this method to get the count of WebElements matching the passed
	 * Tag(for e.g. "img","a" etc) present in the cell(pointed by passed row and
	 * col num)
	 * 
	 * @param rowNum
	 *            The Row number of cell
	 * @param rowNum
	 *            The Column number of cell
	 * @param tag
	 *            The tag of desired webelement, it could be any valid html tag,
	 *            common examples are "a","img".
	 * @return The count of webelement, matching the tag in a cell pointed by
	 *         passed row and col nums, and in case of exception will return -1
	 */
	public int getChildObjectsCount(int rowNum, int colNum, String tag) {

		int childObjectsCount = -1;
		try {
			List<WebElement> listTR = getRowsList();
			// List<WebElement>
			// listTD_TH=listTR.get(rowNum-1).findElements(By.cssSelector("th,td"));
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath(tableCellExpr));
			if(colNum<=getColCount(rowNum)){
				String innerHTML=listTD_TH.get(colNum - 1).getAttribute("innerHTML");
				if(innerHTML.contains(tag)){
					childObjectsCount = listTD_TH.get(colNum - 1).findElements(By.tagName(tag)).size();	
				}
			}
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}
		return childObjectsCount;
	}

	/**
	 * use this method to get the Row number which has the passed text value
	 * 
	 * @param visibleTxt
	 *            The visible text, for which the row count is needed to be
	 *            found
	 * @return The first row number that has the text which matches the passed
	 *         parameter value, and in case of exception will return -1
	 * 
	 */
	public int getRowWithCellText(String visibleTxt) {
		int rowNum = -1;
		String temp="";
		try {
			List<WebElement> listTR = getRowsList();
			for (int row = 0; row < listTR.size(); row++) {
				temp = listTR.get(row).getText();
				if (temp.toLowerCase().contains(visibleTxt.toLowerCase())) {
					rowNum = row+1;
					break;
				}
			}
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}
		return rowNum;
	}


	/**
	 * use this method to get the Row number which has the passed text value
	 * 
	 * @param startRow
	 *            the begin row count, inclusive.
	 * @param visibleTxt
	 *            The visible text, for which the row count is needed to be
	 *            found
	 * @return The first row number that has the text which matches the passed
	 *         parameter value, and in case of exception will return -1
	 * 
	 */
	public int getRowWithCellText(int startRow,String visibleTxt) {
		int rowNum = -1;
		String temp="";
		try {
			List<WebElement> listTR = getRowsList();
			for (int row = startRow-1; row < listTR.size(); row++) {
				temp = listTR.get(row).getText();
				if (temp.toLowerCase().contains(visibleTxt.toLowerCase())) {
					rowNum = row+1;
					break;
				}
			}
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}
		return rowNum;
	}

	/** Call getColumnNumber method after running this method
	 * @headerRow Starts with 1
	 * */
	private List<String> headerColumnList;
	public ReactTable initHeaderColumnList(int headerRow) {
		try{
			headerColumnList= new ArrayList<String>();
			List<WebElement> listTR = getRowsList();
			List<WebElement> thList=listTR.get(headerRow-1).findElements(By.xpath(tableCellExpr));
			for (WebElement temp : thList) {
				webUtil.javaScript_ScrollIntoMIDDLEView_AndHighlight(temp);
				headerColumnList.add(temp.getText().replace("\r\n", " ").replace("\n", " "));
			}
		} catch (Exception e) {
			CustomReporter.report(STATUS.WARNING, "initHeaderColumnList() method failed for headerRow "+headerRow+" in table "+locator+" <br/><br/> Exception message "+e.getMessage());
			//new CustomExceptionHandler(e);
		}
		return this;
	}

	/** Call initHeaderColumnList method prior to calling this method
	 * @return -1 if column name is not found
	 * */
	public int getColumnNumber(String colName) {
		int foundColNum=-1;
		try{
			if(headerColumnList!=null){
				for (int colNum = 0; colNum < headerColumnList.size(); colNum++) {
					if(headerColumnList.get(colNum).equalsIgnoreCase(colName)){
						foundColNum=colNum+1;
						break;
					}
				}
			}else{
				System.err.println("Call InitColumnHeader method first");
			}
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
		}

		if (foundColNum==-1) {
			CustomReporter.report(STATUS.INFO, "Passed Column ["+colName+"] is not found in column header list "+headerColumnList);
		}

		return foundColNum;
	}
	
	/**
	 * Use this method to verify Column Headers Names
	 * @param headerRow the Row Number of Header Row, always starts with 1
	 * @param expectedColNames Comma Separated String column names, to be checked with application
	 * */
	public void verifyColumnHeaders(int headerRow, String... expectedColNames) {
		verifyColumnHeaders(headerRow, Arrays.asList(expectedColNames));
	}
	
	/**
	 * Use this method to verify Column Headers Names 
	 * @param headerRow the Row Number of Header Row, always starts with 1
	 * @param expectedColNames List<String> column names, to be checked
	 *                         with application
	 */
	public void verifyColumnHeaders(int headerRow, List<String> expectedColNames) {
		boolean flag = true;
		List<String> actualColNames = new ArrayList<>();
		for (int i = 0; i < expectedColNames.size(); i++) {
			String tempColName = getCellText(headerRow, i + 1);
			actualColNames.add(tempColName);
			if (!tempColName.toLowerCase().contains(expectedColNames.get(i).toLowerCase())) {
				CustomReporter.report(STATUS.FAIL, "On Row["+headerRow+"] and Col["+(i+1)+"] Column text '" + expectedColNames.get(i) + "' not found but column '"
						+ tempColName + "' is getting displayed");
				flag = false;
			}
		}

		if (flag) {
			CustomReporter.report(STATUS.PASS, "All columns found : " + expectedColNames);
		} else {
			CustomReporter.report(STATUS.INFO, "COLUMNS EXPECTED: [" + getStringifiedColNames(expectedColNames) + "]");
			CustomReporter.report(STATUS.INFO, "COLUMNS ACTUAL : [" + getStringifiedColNames(actualColNames) + "]");
		}
	}
	
	/**
	 * Returns the double quoted comma separated string
	 * i.e. if you pass an List with values => v1,v2,v3
	 * You will get => "v1", "v2", "v3"
	 * So that you can directly copy it from html report or console 
	 * and paste to Java Class file :)
	 * 
	 * @author shailendra.rajawat 07-May-2019
	 * */
	private String getStringifiedColNames(List<String> colNamesList){
		String colNames="";
		for (String temp: colNamesList) {
			colNames=colNames+", \""+temp+"\"";
		}
		return colNames.length()>1 ? colNames.substring(2) : colNames; 
	}
	
}
