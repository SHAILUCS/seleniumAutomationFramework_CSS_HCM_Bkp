package com.extentReportingv3;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

class ExtentFromExcel {

    private static final String FILE_NAME = "E:/JavaWorkspace/IOTRON-AutomationFramework/Downloads/MagicChrome.xlsx";

    public static void main(String[] args) {

        try {
        	ExtentReports extentReport;
        	ExtentTest test;
        	String suiteName="Magic Regression Suit";
        	String testName="Magic Regression Test";
        	extentReport = ExtentManager.GetExtentReports(suiteName,"none");
        	
        	
        	
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = WorkbookFactory.create(excelFile);
            Sheet datatypeSheet = workbook.getSheet("Results");
            int Sccol=1;            	
        	int OScol=6;            	
        	int Statuscol=3;        	
        	int Desccol=2;
        	
            for (int row = 1; row < datatypeSheet.getPhysicalNumberOfRows(); row++) {
            	
            	if (datatypeSheet.getRow(row)!=null) {
            		if(datatypeSheet.getRow(row).getCell(Sccol)!=null && !datatypeSheet.getRow(row).getCell(Sccol).getStringCellValue().equals("")){
	            		System.out.println(datatypeSheet.getRow(row).getCell(Sccol).getStringCellValue());
	            		ExtentManager.createTest(datatypeSheet.getRow(row).getCell(Sccol).getStringCellValue()+" |os/browser: "+datatypeSheet.getRow(row).getCell(OScol).getStringCellValue(),datatypeSheet.getRow(row).getCell(Sccol).getStringCellValue(), suiteName, testName);
            		}else{
    					test=ExtentManager.GetExtentTest();
    					System.out.println(datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue());
    					if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("pass")) {
    						test.log(Status.PASS, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} else if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("fail")) {
    						test.log(Status.FAIL, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} else if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("debug")) {
    						test.log(Status.DEBUG, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} else if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("error")) {
    						test.log(Status.ERROR, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} else if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("fatal")) {
    						test.log(Status.FATAL, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} else if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("info")) {
    						test.log(Status.INFO, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} else if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("done")) {
    						test.log(Status.INFO, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} else if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("warn")) {
    						test.log(Status.WARNING, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} else if (datatypeSheet.getRow(row).getCell(Statuscol).getStringCellValue().toLowerCase().contains("skip")) {
    						test.log(Status.SKIP, datatypeSheet.getRow(row).getCell(Desccol).getStringCellValue() );
    					} 
    				}
				}
            	
			}
            
            extentReport.flush();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	
}
