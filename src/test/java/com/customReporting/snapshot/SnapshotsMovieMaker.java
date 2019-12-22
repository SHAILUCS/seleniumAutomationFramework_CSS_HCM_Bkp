package com.customReporting.snapshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.configData_Util.Constant;
import com.configData_Util.Util;

public class SnapshotsMovieMaker {
	private static String snapshotMovieTemplateFilePath=Constant.snapshotsMovieTemplateFilePath();
	private static final String snapsArrayPlaceholder="<!-- snapsArray -->";

	public static void main(String[] args) {
		createMovie("AgrRwrk_AgreementDetailsPage_1_SettlementTabContentVerification");
	}

	public static String createMovie(String testFolderName) {

		String createdMoviePath=null;

		System.out.println("===============================================================================");
		System.out.println(Util.convertToHHMMSS(new Date().getTime()) +" | "+ Thread.currentThread().getId()
				+" | "+ "Snapshot Movie Maker STARTED for TEST: " + testFolderName + " | " + new Date());
		if (!Constant.enableCaptureSnapshots()) {
			System.out.println(Util.convertToHHMMSS(new Date().getTime()) +" | "+ Thread.currentThread().getId()
					+" | "+ "Snapshot Movie Maker feature is STOPPED by Constant.captureSnapshots | "+ new Date());
			System.out.println("===============================================================================");
		}else{
			try {
				String snapshotFolderPath=Constant.getSnapShotsFolderPath()+"/"+testFolderName;
				List<String> snapsList=getSnapsNameList(snapshotFolderPath);
				String snapsArr="";
				for (String string : snapsList) {
					snapsArr=snapsArr+",\""+string+"\"";
				}
				snapsArr=snapsArr.substring(1, snapsArr.length());

				String reportIn = new String(Files.readAllBytes(Paths.get(snapshotMovieTemplateFilePath)));
				reportIn = reportIn.replaceFirst(snapsArrayPlaceholder,"["+snapsArr+"]");
				String moviePath = snapshotFolderPath + "/" + Constant.snapshotsMovieTemplateName;
				//Util.deleteSpecificFile(moviePath);
				Files.write(Paths.get(moviePath),reportIn.getBytes(),StandardOpenOption.CREATE);
				
				createdMoviePath="../"+Constant.snapshotsFolderName+"/"+testFolderName+ "/" + Constant.snapshotsMovieTemplateName;
				createdMoviePath = createdMoviePath.replace("\\", "/");
			} catch (Exception e) {
				System.out.println("Error when writing report file:\n" + e.toString());
			}finally {
				System.out.println(Util.convertToHHMMSS(new Date().getTime()) +" | "+ Thread.currentThread().getId()
						+" | "+ "Snapshot Movie Maker ENDED for TEST: "+testFolderName+" | "+ new Date());
				System.out.println("===============================================================================");
			}
		}
		
		
		return createdMoviePath;
	}


	public static List<String> getSnapsNameList(String snapshotFolderPath){

		List<String> snapshotsNameList=new ArrayList<>();
		try{
			File dir= new File(snapshotFolderPath);
			if (dir.isDirectory()) { 
				File[] children = dir.listFiles();
				for (int i = 0; i < children.length; i++) {

					String fileName=children[i].getName();
					if(fileName.contains(".jp")){
						snapshotsNameList.add(fileName);
					}
				} 
			} 		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return snapshotsNameList;
	}
}
