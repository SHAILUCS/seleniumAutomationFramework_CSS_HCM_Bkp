package com.customReporting;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.configData_Util.Constant;
import com.xlUtil.DataTable;

public class TestRunHistoryManager {

	private static String sheetName_write = "TestRunHistoryData";
	private static String reportPath_write =Constant.getReportingHistoryFolderPath()+"/TestRunData.xlsx";

	private static HashMap<String, String> resultmap;

	private static void createExcelTemplate() {
		Workbook workBookObj = null;
		Sheet sheetObj=null;
		Row row=null;
		FileOutputStream outputStream=null;

		try{
			//Creating a new sheet
			workBookObj = new XSSFWorkbook();
			sheetObj = workBookObj.createSheet(sheetName_write);
			String cellA1 = "SrNo", cellB1 = "TestName";
			row=sheetObj.createRow(0);
			row.createCell(0).setCellValue(cellA1);
			row.createCell(1).setCellValue(cellB1);
			outputStream=new FileOutputStream(new File(reportPath_write));
			workBookObj.write(outputStream);
			outputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void readData() {
		resultmap = new HashMap<>();
		List<ArrayList<Test>> tempList=CustomReporter.getListOfList();
		for (int i = 0; i < tempList.size(); i++) {
			String scenario = tempList.get(i).get(0).getScenario();
			String status = tempList.get(i).get(0).getStatus();
			resultmap.put(scenario, status);
		}
	}

	private static void writeData() {
		DataTable writesheet = new DataTable(reportPath_write, sheetName_write);
		int lastColumnCount = writesheet.getColumnCount(0);
		writesheet.setValue(0, lastColumnCount, new Date()+"");

		if (resultmap.size()>0) {
			//matching loop
			int rowcount = writesheet.getRowCount();
			for (int row = 1; row <= rowcount; row++) {
				String tempdata = writesheet.getValue(row, "TestName");
				if(resultmap.containsKey(tempdata)){
					writesheet.setValue(row, lastColumnCount, resultmap.get(tempdata));
					resultmap.remove(tempdata);
				}
			}
			//inserting new data loop
			for (String tempkey : resultmap.keySet()) {
				rowcount = writesheet.getRowCount();
				writesheet.setValue(rowcount, 1, tempkey);
				writesheet.setValue(rowcount, lastColumnCount, resultmap.get(tempkey));
			}
		}		
	}
	public static void manageTestRunHistory() {
		System.out.println("===============================================================================");
		System.out.println("manage Test Run History STARTED "+new Date());
		try {
			File f=new File(reportPath_write);
			if (!f.exists()) {
				createExcelTemplate();	
			}
			readData();
			writeData();
			System.out.println("manage Test Run History ENDED "+new Date());
		}catch (Exception e) {
			System.out.println("manage Test Run History FAILED, Please edit at this line and run again to see exception "+new Date());
		}
		System.out.println("===============================================================================");
	}

	public static void main(String[] args) {
		manageTestRunHistory();
	}

}
