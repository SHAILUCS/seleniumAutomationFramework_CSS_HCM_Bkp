package com.csvUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.seleniumExceptionHandling.CustomExceptionHandler;

public class CSVManager {
	
	private String CSV_FILE_PATH;
	private Reader reader;
	private boolean withFirstRecordAsHeader=false;
	
	/** Stores the List of row wise csv records, which will save the time during execution */
	List<CSVRecord> csvRecordsList; 

	/**
	 * Use this constructor if you want to fetch cell text based on column Index 
	 * It sets withFirstRecordAsHeader to FALSE, so rowIndex 0 will point to excel row 1
	 * */
	public CSVManager(String CSV_FILE_PATH) {
		this.CSV_FILE_PATH=CSV_FILE_PATH;
	}

	/**
	 * Use this constructor if you want to fetch cell text based on column name 
	 * If withFirstRecordAsHeader is Set to FALSE, then rowIndex 0 will point to excel row 1
	 * If withFirstRecordAsHeader is Set to TRUE, then rowIndex 0 will point to excel row 2
	 * */
	public CSVManager(String CSV_FILE_PATH, boolean withFirstRecordAsHeader) {
		this.CSV_FILE_PATH=CSV_FILE_PATH;
		this.withFirstRecordAsHeader=withFirstRecordAsHeader;
	}

	/**
	 * Returns the object of csvParser which is used to get the records out of CSV File
	 * */
	private CSVParser getFreshCsvParser() {
		CSVParser csvParser = null;
		try {
			reader=new BufferedReader(new InputStreamReader(new FileInputStream(CSV_FILE_PATH),"ISO-8859-1"));
			//reader=new BufferedReader(new InputStreamReader(new FileInputStream(CSV_FILE_PATH),"utf-8"));
			//reader= Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
			if (withFirstRecordAsHeader) {
				csvParser = new CSVParser(reader, CSVFormat.DEFAULT
						.withFirstRecordAsHeader()
						.withIgnoreHeaderCase()
						.withTrim());
			}else{
				csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			}
		} catch (IOException e) {
			new CustomExceptionHandler(e,CSV_FILE_PATH);
		}
		return csvParser;
	}

	
	/**
	 *  Returns a List<CSVRecord>, where one object of CSVRecord holds one row value.
	 *  To get all values present inside a row use CSVRecordObject.get() method
	 *  <pre>
	 *  Usage : 
	 *  CSVManager csv=new CSVManager(filePath);
	 *  List<CSVRecord> list = csv.getRecords();
	 *  for (CSVRecord csvRecord : list) {
	 *  	System.out.print(csvRecord.getRecordNumber() + "\t");
	 *  	for(int i=0; i < csvRecord.size(); i++){
	 *  		System.out.print(csvRecord.get(i) + "\t");
	 *  	}
	 *  System.out.println();
	 *  }
	 *  </pre>
	 * */
	private List<CSVRecord> getRecords(){
		if(csvRecordsList == null){
			csvRecordsList = new ArrayList<CSVRecord>();
			try{
				csvRecordsList = getFreshCsvParser().getRecords();
			}catch (IOException e) {
				new CustomExceptionHandler(e);
			}
		}
		return csvRecordsList;
	}

	/** @return Row Count*/
	public int getRowCount() {
		int rowCount=0;
		try{
			rowCount = getRecords().size();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	/** @return Column Count*/
	public int getColumnCount() {
		return getRecords().get(0).size();
	}

	/**
	 * @return if nothing found then "", else String {@value} present in the cell
	 * 
	 * @param rowIndex = 0 refers to Excel row 1 -> When the {@link withFirstRecordAsHeader} is FALSE
	 * @param rowIndex = 0 refers to Excel row 2 -> When the {@link withFirstRecordAsHeader} is TRUE
	 * @param colName = pass any value from first row when {@link withFirstRecordAsHeader} is TRUE
	 * 
	 * */
	public String getValue(int rowIndex,String colName) {
		String value="";
		try {
			List<CSVRecord> csvRecordsList = getRecords();
			value = csvRecordsList.get(rowIndex).get(colName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * @return if nothing found then "", else String {@value} present in the cell
	 * 
	 * @param rowIndex = 0 refers to Excel row 1 -> When the {@link withFirstRecordAsHeader} is FALSE
	 * @param rowIndex = 0 refers to Excel row 2 -> When the {@link withFirstRecordAsHeader} is TRUE
	 * 
	 * @param colIndex = 0 refers to Excel col A
	 * */
	public String getValue(int rowIndex,int colIndex){
		String value="";
		try{
			List<CSVRecord> csvRecordsList = getRecords();
			value = csvRecordsList.get(rowIndex).get(colIndex);
		}catch (Exception e) {
			System.err.println("Row Index : "+rowIndex+ " Col Index : "+ colIndex);
			e.printStackTrace();
		}
		return value;
	}
}
