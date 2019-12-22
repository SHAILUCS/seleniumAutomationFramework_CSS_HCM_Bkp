package com.seleniumExceptionHandling;

import java.util.ArrayList;
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
 * Use this class to work with standard Tables in any web page, <br>
 * <table><tbody> 
 * <tr> <th>Heading1</th> <th>Heading2</th> </tr>
 * <tr> <td>Data1.1</td> <td>Data1.2</td> </tr>
 * <tr> <td>Data2.1</td> <td>Data2.2</td> </tr>
 * </tbody> </table>
 */
public class WebTable {

	private WebUtils webUtil;

	private By locator;
	public WebTable(By locator) {
		this.locator=locator;
		webUtil=new WebUtils();
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
		List<WebElement> listTR = null;
		try{
			DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			listTR = new ArrayList<WebElement>();
			// Getting null pointer exception on this line, even though the table is getting displayed 
			List<WebElement> listHeadTR = getWebElement().findElements(By.xpath("thead/tr"));
			listTR.addAll(listHeadTR);
			List<WebElement> listBodyTR = getWebElement().findElements(By.xpath("tbody/tr"));
			listTR.addAll(listBodyTR);
			DriverFactory.getDriver().manage().timeouts().implicitlyWait(Constant.implicitWait, TimeUnit.SECONDS);
		}catch(Exception e){
			CustomReporter.report(STATUS.FAIL, "getRowsList() method failed for locator "+locator+" <br/><br/> Outer HTML "+getWebElement().getAttribute("outerHTML"));
			new CustomExceptionHandler(e);
		}
		return listTR;
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
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath("td|th"));
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
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath("th|td"));
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
			List<WebElement> listTD_TH=listTR.get(rowNum-1).findElements(By.xpath("th|td")); 
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
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath("th|td"));
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
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath("th|td"));
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
			List<WebElement> listTD_TH = listTR.get(rowNum - 1).findElements(By.xpath("th|td"));
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
	public WebTable initHeaderColumnList(int headerRow) {
		try{
			headerColumnList= new ArrayList<String>();
			List<WebElement> listTR = getRowsList();
			List<WebElement> thList=listTR.get(headerRow-1).findElements(By.xpath("th|td"));
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
}
