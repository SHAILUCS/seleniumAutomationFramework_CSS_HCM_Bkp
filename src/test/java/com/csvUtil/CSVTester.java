package com.csvUtil;

import java.io.IOException;

import com.configData_Util.Util;

public class CSVTester {
	public static void main(String[] args) throws IOException {
	
		String filePath = "C:\\Users\\shailendra.rajawat\\Downloads\\Overview_report_download_csv - Verizon.csv";
		
		CSVManager csv=new CSVManager(filePath);
		
		String d2 = Util.getTimeStamp_In_dd_MMM_yyyy_HH_mm_ss_S();
		
		String[] arr = Util.getArray("Home  Operator Name"	,"Traffic Period"	,"Partner Group Name"	,"Country","Partner PMN"); 
		
		for (int row = 0; row < 10; row++) {
			System.out.print(row + "\t");
			for (int col = 0; col < csv.getColumnCount(); col++) {
				System.out.print(csv.getValue(row, col)+"\t");
			}
			/*
			for (String colName : arr) {
				System.out.print(csv.getValue(row, colName)+"\t");
			}*/
			
			System.out.println();
		}
		
		String d3 = Util.getTimeStamp_In_dd_MMM_yyyy_HH_mm_ss_S();
		System.out.println("**************************************************************************************************");

		System.out.println(d2);
		System.out.println(d3);
		
		/*CSVManager csv1=new CSVManager(filePath,true);
		for (int row = 1; row <= csv1.getRowCount(); row++) {
			for (int col = 1; col <= csv1.getColumnCount(); col++) {
				System.out.print(csv1.getValue(row, col)+"\t");
			}
			System.out.println();
		}
		
		System.out.println("**************************************************************************************************");
		for (int row = 1; row <= csv1.getRowCount(); row++) {
			
				System.out.print(csv1.getValue(row, "Country Name")+"\t");
				System.out.print(csv1.getValue(row, "TrafficDirection")+"\t");
				System.out.print(csv1.getValue(row, "Service Type")+"\t");
				System.out.print(csv1.getValue(row, "Event Type")+"\t");
				System.out.print(csv1.getValue(row, "Jan 17")+"\t");
		
			System.out.println();
		}*/
		
	}
}
