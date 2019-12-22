package com.customReporting;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.configData_Util.Constant;

/**
 * @author shailendra.rajawat
 * created on 5 Jan 2018
 * */
public class ReportingHistoryManager {

	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMMdd_HHmmss");
	private static String reportingHistoryFolderPath=Constant.getReportingHistoryFolderPath();
	private static int limit=Constant.reportingHistoryFolderLimit();

	private static void removeOlderFolders() {
		try{
			List<Date> allFoldersNameList=getFoldersNameList();
			int foldersCount=allFoldersNameList.size();
			if(foldersCount>0){


				if(foldersCount>=limit){
					Collections.sort(allFoldersNameList);
					//System.out.println(allFoldersNameList);
					int counter=foldersCount-limit+1;
					for (int i = 0; i < counter; i++) {
						String deleteThisFolderName=dateFormat.format(allFoldersNameList.get(i));
						String deleteThisFolderPath=reportingHistoryFolderPath + "/" + deleteThisFolderName;
						File file= new File(deleteThisFolderPath);
						FileUtils.deleteDirectory(file);
						if(!file.exists()){
							System.out.println("DELETED: "+deleteThisFolderPath);
						}else{
							System.out.println("NOT DELETED: "+deleteThisFolderPath);
						}
					}

					//System.out.println(getFoldersNameList());
				}else{
					System.out.println("NOTHING IS DELETED FROM "+Constant.reportingHistoryFolderName+" current folder count: "+foldersCount+" and set limit: "+limit);
				}

			}else{
				System.out.println("NO FILES PRESENT IN "+Constant.reportingHistoryFolderName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Date> getFoldersNameList(){

		List<Date> foldersNameList=new ArrayList<>();
		try{
			File dir= new File(reportingHistoryFolderPath);
			if (dir.isDirectory()) { 
				File[] children = dir.listFiles();
				for (int i = 0; i < children.length; i++) {
					if(children[i].isDirectory()){
						String folderName=children[i].getName();
						if(!folderName.equals(Constant.reportingHistoryHTMLFolderName)){
							foldersNameList.add(dateFormat.parse(folderName));
						}
					}
				} 
			} 		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return foldersNameList;
	}

	private static String createNewFolder() {
		String newFolderName=dateFormat.format(new Date());
		String newFolderPath=reportingHistoryFolderPath+ "/" +newFolderName;
		File fileObj= new File(newFolderPath);
		if(!fileObj.exists()) {
			fileObj.mkdir();
			fileObj= new File(newFolderPath+ "/" +Constant.snapshotsFolderName);
			fileObj.mkdir();
			fileObj= new File(newFolderPath+ "/" +Constant.resultFolderName);
			fileObj.mkdir();
		}
		return newFolderPath;
	}

	public static void manageReportingHistory() {
		System.out.println("\n===========================MANAGING REPORTING HISTORY===========================");
		try {
		if(limit>0){
			System.out.println("================================== STARTED =======================================");
			removeOlderFolders();
			String newFolderPath=createNewFolder();
			copyReportContents(newFolderPath);
			ReportingHistoryHTML.manageHTML();

		}else{
			System.out.println("\n================================== STOPPED ===================================");
			System.out.println("TO START SET POSITIVE NUMBER IN Constant.reportingHistoryFolderLimit");
		}
		}catch (Exception e) {
			System.out.println("\n================================== FAILED  ===================================");
			System.out.println("Edit here to print exception details");
		}
		System.out.println("=================================== END ========================================");
	}




	private static void copyReportContents(String newFolderPath) {
		try {
			File srcDir = new File(Constant.getResultFolderPath());
			if(srcDir.exists()){
				File destDir = new File(newFolderPath+ "/" +Constant.resultFolderName);
				FileUtils.copyDirectory(srcDir, destDir);
			}
			srcDir = new File(Constant.getSnapShotsFolderPath());
			if(srcDir.exists()){
				File destDir = new File(newFolderPath+ "/" +Constant.snapshotsFolderName);
				FileUtils.copyDirectory(srcDir, destDir);
			}
			System.out.println("Copied the current run report in {"+newFolderPath+"} folder");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ParseException {
		manageReportingHistory();
		/*ReportingHistoryHTML.manageHTML();*/

	}
}
