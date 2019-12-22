/**
 * File: DataTable.java
 * -----------------------
 * This class provides many simple methods implementation to work with excel sheet.
 * 1. You can create a new file
 * 2. Read existing excel file
 * 3. Write data in existing excel file
 * It is thread safe ;-) <i>[I tested it on some scenarios and it worked pretty fine]</i>
 * 
 * Please note, this class does not provide implementation to every functionality provided by 
 * org.apache.poi.
 * 
 * The purpose of this class is to speed up the scripting process, by providing some very simple methods, which can interact with excel file
 * It abstracts the unessential details of org.apache.poi, so that readability of your scripts can be improved.
 * Also it handles some errors, so that the script execution does not break prematurely.  
 */ 
package com.xlUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.seleniumExceptionHandling.CustomExceptionHandler;

@ThreadSafe
public class DataTable {

	/**
	 * This is added to Speed up the r/w process on fixed column set, that means
	 * when you want to set/get a value based on column name, program will first
	 * come to this List and get the corresponding column number, because java
	 * uses column and row number to work with excel
	 */  
	private List<String> colHeaderList;

	// apache.poi object instances  
	private Sheet sheet;
	private Workbook workbook;
	private String workbookPath;

	/**
	 * Use class name to invoke this method as it is made static, 
	 * It creates a new excel file with .xlsx format
	 * @param filePath absolute path(including the workbook name) where you want to save your excel file, e.g.: "c:\folder\name.xlsx"
	 * @param sheetName The name of sheet in your new workbook, e.g.: "TestDataSheet" 
	 * @param colHeaderNames It accepts array of Strings, which will be your column names, e.g.: "ColName1","ColName2","ColName3"
	 * 
	 * <pre>
	 * USAGE : 
	 * DataTable.createExcelTemplate("c:\folder\name.xlsx","TestDataSheet","ColName1","ColName2","ColName3");
	 * <pre>
	 * */
	public static void createExcelTemplate(String filePath,String sheetName,String... colHeaderNames) {
		DataTableWriter.createExcelTemplate(filePath, sheetName, colHeaderNames);
	}

	public static void killChromeProcess() {
		try{
			Runtime.getRuntime().exec("cmd /c taskkill /f /im "+com.configData_Util.Constant.chromeDriverFileName_Win);
			//Runtime.getRuntime().exec("cmd /c taskkill /f /im chrome.exe");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		killChromeProcess();
	}

	/**
	 * Parameterized constructor to create DataTable object by passing workbookPath and sheetIndex
	 *  
	 * @param workbookPath absolute path(including the workbook name) e.g.: "c:\folder\name.xlsx"
	 * @param sheetIndex index of sheet whose data you want to r/w(index starts with 0) 
	 * 
	 * <pre>
	 * USAGE : 
	 * DataTable tab=new DataTable("c:\folder\name.xlsx",0);
	 * <pre>
	 * */
	public DataTable(String workbookPath,int sheetIndex) {
		this.workbookPath=workbookPath;
		initWorkbookObject();
		initSheetObject(sheetIndex);
		initColHeader();
	}

	/**
	 * Parameterized constructor to create DataTable object by passing workbookPath and sheetName
	 *  
	 * @param workbookPath absolute path(including the workbook name) e.g.: "c:\folder\name.xlsx"
	 * @param sheetName name of sheet whose data you want to r/w 
	 * 
	 * <pre>
	 * USAGE : 
	 * DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 * <pre>
	 * */
	public DataTable(String workbookPath,String sheetName) {
		this.workbookPath=workbookPath;
		initWorkbookObject();
		initSheetObject(sheetName);
		initColHeader();
	}

	/**
	 * This method returns you the data of a cell object in String format 
	 *  
	 * @param cell An Object of org.apache.poi.ss.usermodel.Cell
	 * @return String value 
	 * */
	private String getCellValue(Cell cell){
		String val="";
		if(cell!=null){
			DataFormatter objDefaultFormat = new DataFormatter();
			CreationHelper b=workbook.getCreationHelper();
			FormulaEvaluator objFormulaEvaluator = b.createFormulaEvaluator();
			objFormulaEvaluator.evaluate(cell); // This will evaluate the cell, And any type of cell will return string value
			val = objDefaultFormat.formatCellValue(cell,objFormulaEvaluator);
		}
		return val.trim();
	}

	/**
	 * This method provides the count of column present in the passed row
	 * number, As each row may have different column count, it is mandatory to
	 * pass row index value
	 * 
	 * @param rowindex index of the row(index starts with 0)
	 * @return count of columns, if no column is present then -1
	 *            <pre>
	 *            USAGE : 
	 *            DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 *            int colCount=tab.getColumnCount(0);// 0 represents first row
	 *            <pre>
	 */
	public int getColumnCount(int rowindex) {
		int colCount=-1;
		try{
			Row row=sheet.getRow(rowindex);
			colCount=row.getLastCellNum();
		}catch(Exception e){
			System.err.println("Row index "+rowindex+" does not exist in sheet");
			//e.printStackTrace();
		}
		return colCount;
	}

	/**
	 * This method provides the column index of the passed column name(it works 
	 * only on the header row i.e first row with index 0)
	 * 
	 * @param colName name of the Column whose index is required
	 * @return index of column, if column is not present then -1
	 *            <pre>
	 *            USAGE : 
	 *            DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 *            int colIndex=tab.getColumnHeaderNum("ColName1");// this can be used further
	 *            <pre>
	 */
	public int getColumnHeaderNum(String colName) {
		for (int j = 0; j < colHeaderList.size(); j++) {
			if(colHeaderList.get(j).equalsIgnoreCase(colName)){
				return j;
			}
		}
		return -1;
	}

	/**
	 * This provides the row count.
	 * it is a thread safe method.
	 * 
	 * @return row count, if row is not present then 0
	 *            <pre>
	 *            USAGE : 
	 *            DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 *            int rowCount=tab.getRowCount;// this can be used further
	 *            <pre>
	 */
	public int getRowCount() {
		return DataTableWriter.getRowCount(this);
	}


	/**
	 * This provides the index of Last row, 0 based
	 * it is a thread safe method.
	 *  
	 * @return row count, if row is not present then 0
	 *            <pre>
	 *            USAGE : 
	 *            DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 *            int rowCount=tab.getRowCount;// this can be used further
	 *            <pre>
	 */
	public int getLastRowIndex() {
		return DataTableWriter.getLastRowNum(this);
	}

	/**
	 * This method provides the cell(located by rowIndex X colIndex) value in String format 
	 * 
	 * @param rowIndex index of the row
	 * @param colIndex index of the column
	 * @return String formatted value stored in the cell, if nothing found at the location then 
	 * will return ""  
	 *            <pre>
	 *            USAGE : 
	 *            DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 *            String cellTxt=tab.getValue(1,1);// this will return the value in B2 Cell
	 *            <pre>
	 */
	public String getValue(int rowIndex,int colIndex){
		String val="";
		Row row=null;
		Cell cell=null;
		try{
			row = sheet.getRow(rowIndex);
			if(row!=null){
				cell=row.getCell(colIndex);
				if(cell!=null){
					val=getCellValue(cell);
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return val;
	}	

	/**
	 * This method provides the cell text(located by rowIndex X colIndex) in String format 
	 * 
	 * @param rowIndex index of the row
	 * @param colIndex index of the column
	 * @return String formatted value stored in the cell, if nothing found at the location then 
	 * will return ""  
	 *            <pre>
	 *            USAGE : 
	 *            DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 *            String cellTxt=tab.getValue(1,1);// this will return the value in B2 Cell
	 *            <pre>
	 */
	public String getValue(int rowIndex,String colName) {
		String val="";
		Row row=null;
		Cell cell=null;
		try{
			row = sheet.getRow(rowIndex);
			if(row!=null){
				int colNum=getColumnHeaderNum(colName);
				cell=row.getCell(colNum);
				if(cell!=null){
					val=getCellValue(cell);
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return val;
	}	

	/**
	 * This method initializes the column header list and save it for future. 
	 * Used by getColumnHeaderNum(), getCellValue(int,String) and setCellValue(int,String,String)methods 
	 */ 
	private void initColHeader() {
		try{
			colHeaderList = new ArrayList<String>();
			Row row = sheet.getRow(0);
			if(row!=null){
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell=row.getCell(j);
					if(cell!=null){
						colHeaderList.add(getCellValue(cell));
					}else{
						colHeaderList.add("");
					}
				}
			}
		}catch (Exception e) {
			new CustomExceptionHandler(e, "File path " + workbookPath);
		}
	}


	/**
	 * This method initializes the sheet Object.
	 * @param sheetIndex index of the sheet(index starts with 0) 
	 */ 
	private void initSheetObject(int sheetIndex) {
		try{
			sheet = workbook.getSheetAt(sheetIndex);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes the sheet Object.
	 * @param sheetName Name of the sheet 
	 */  
	private void initSheetObject(String sheetName) {
		try{
			sheet = workbook.getSheet(sheetName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes the workbook Object.
	 */  
	private void initWorkbookObject() {
		File file = null;
		FileInputStream inputStream =null;
		try{
			file = new File(workbookPath);
			inputStream = new FileInputStream(file);
			workbook = WorkbookFactory.create(inputStream);
		}catch(Exception e){
			e.printStackTrace();
		}

		/*XSSFWorkbook workBookObj=null;
		try{
			OPCPackage pkg = OPCPackage.open(new File(workbookPath));
			workBookObj = new XSSFWorkbook(pkg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return workBookObj;*/
	}

	public  void killExcelProcess() {
		try{
			//Runtime.getRuntime().exec("cmd /c taskkill /f /im "+Constant.chromeDriverFileName_Win);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method is Thread safe.
	 * This method sets the provided String value in cell(located by rowIndex X colIndex) 
	 * 
	 * @param rowIndex index of the row
	 * @param colIndex index of the column
	 * @param data String value which is to be stored in excel 
	 *            <pre>
	 *            USAGE : 
	 *            DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 *            tab.setValue(1,1,"Hello");// this will store Hello in B2 Cell
	 *            <pre>
	 */
	public void setValue(int rowIndex,int colIndex,String data){
		DataTableWriter.setValue(this,rowIndex, colIndex, data);
	}	

	/**
	 * This method is Thread safe.
	 * This method sets the provided String value in cell(located by rowIndex X colName) 
	 * 
	 * @param rowIndex index of the row
	 * @param colName Name of the column
	 * @param data String value which is to be stored in excel 
	 *            <pre>
	 *            USAGE : 
	 *            DataTable tab=new DataTable("c:\folder\name.xlsx","sheetName");
	 *            tab.setValue(1,"ColName1","Hello");// this will store Hello in 2nd row of column whose name is ColName1
	 *            <pre>
	 */
	public void setValue(int rowIndex,String colName,String data){
		DataTableWriter.setValue(this,rowIndex, colName, data);
	}

	public String getWorkbookPath() {
		return workbookPath;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public Sheet getSheet() {
		return sheet;
	}

}

@ThreadSafe
class DataTableWriter{

	private static Cell cell;
	private static FileOutputStream outputStream;
	private static Row row;
	private static Sheet sheet;
	private static Workbook workbook;
	private static String workbookPath;

	public static synchronized void createExcelTemplate(String filePath,String sheetName,String... colHeaderNames) {
		//Creating a new sheet
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet(sheetName);
		row=sheet.createRow(0);
		for (int i = 0; i < colHeaderNames.length; i++) {
			row.createCell(i).setCellValue(colHeaderNames[i]);
		}
		writeXlFile(filePath);
	}

	public static synchronized int getLastRowNum(DataTable tab) {
		init(tab);
		return sheet.getLastRowNum();
	}

	public static synchronized int getRowCount(DataTable tab) {
		init(tab);
		return sheet.getLastRowNum()+1;
		// return sheet.getLastRowNum();
	}

	private static synchronized void init(DataTable tab) {
		workbookPath=tab.getWorkbookPath();
		workbook=tab.getWorkbook();
		sheet=tab.getSheet();
	}	

	public static synchronized void setValue(DataTable tab, int rowNum,int colNum,String data){
		init(tab);
		row = sheet.getRow(rowNum);
		if (row == null) {
			sheet.createRow(rowNum);
			row=sheet.getRow(rowNum);
		}
		cell=row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
			cell.setCellValue(data);
		} else {
			cell.setCellValue(data);
		}
		writeXlFile(workbookPath);
	}

	public static synchronized void setValue(DataTable tab,int rowNum,String colName,String data){
		init(tab);
		int colNum=tab.getColumnHeaderNum(colName);
		row=sheet.getRow(rowNum);
		if (row == null) {
			sheet.createRow(rowNum);
			row=sheet.getRow(rowNum);
		}

		cell=row.getCell(colNum);
		if (cell == null) {
			cell = sheet.getRow(rowNum).createCell(colNum);
			cell.setCellValue(data);
		} else {
			cell.setCellValue(data);
		}

		writeXlFile(workbookPath);
	}

	private static synchronized void writeXlFile(String filePath) {
		try {
			outputStream=new FileOutputStream(new File(filePath));
			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
