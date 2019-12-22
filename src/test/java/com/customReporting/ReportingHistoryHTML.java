package com.customReporting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.io.IOUtils;

import com.configData_Util.Constant;
import com.configData_Util.Util;

/**
 * @author shailendra.rajawat
 * This class will generate interactable html pages which will display the 
 * old reports of Test Runs. 
 * Features: 
 *  1. It has pagination, latest report displays on first page
 *  2. Quick view displays the Executed tests with status
 *  3. Links lets you open the report in new tab
 *  4. You can use keyboard to scroll up down and to navigate to next/prev page
 * */
public class ReportingHistoryHTML {

	private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMMdd_HHmmss");
	private final static String projectPlaceholder = "<!-- PROJECT -->";
	private final static String pickerQuickAccessStartPlaceholder = "<!-- PICKER0_START -->";
	private final static String pickerQuickAccessEndPlaceholder = "<!-- PICKER0_END -->";
	private final static String pickerDashboardStartPlaceholder = "<!-- PICKER1_START -->";
	private final static String pickerDashboardEndPlaceholder = "<!-- PICKER1_END -->";
	private final static String rowPlaceholder = "<!-- INSERT_ROW -->";
	private final static String totalRowsPlaceholder= "<!-- TOTAL ROWS -->";
	private final static String pageRowsPlaceholder= "<!-- PAGE ROWS -->";
	private final static String prevPlaceHolder_TOP= "<!-- PREV -->";
	private final static String nextPlaceHolder_TOP= "<!-- NEXT -->";
	private final static String paginationPlaceholder_TOP = "<!-- INSERT_TOP_PAGINATION -->";
	private final static String paginationPlaceholder_BOTTOM = "<!-- INSERT_BOTTOM_PAGINATION -->";
	private static String reportingHistoryFolderPath=Constant.getReportingHistoryFolderPath();
	private static String htmlreportingHistoryFolderPath=Constant.getReportingHistoryHTMLfolderPath();
	
	private static String redesignTemplateFilePath = Constant.getHistoryReportRedesignTemplateFilePath();

	public static void manageHTML() {
		//deleteOlderHTMLFile();
		Util.deleteFolderContentRecursively(new File(htmlreportingHistoryFolderPath), Constant.reportingHistoryHTMLFolderName);
		createHTML();
	}

	private static void createHTML() {
		try{
			String tempFolderName,customReportPath,NEWcustomReportPath,extentReportPath;
			String reportBase=new String(Files.readAllBytes(Paths.get(redesignTemplateFilePath)));
			
			List<Date> foldersNameList= ReportingHistoryManager.getFoldersNameList();
			Collections.sort(foldersNameList);

			int foldersCount=foldersNameList.size();
			int totalPages=	1;
			int diff=Constant.getReportinghistoryfolderperpage();
			if(foldersCount > diff){
				totalPages=(foldersCount + diff - 1) / diff;
			}

			String data="Page ";
			for (int page = 1; page <= totalPages; page++) {
				data=data+"<a href='page"+page+".html'>"+page+"</a> ";
			}
			
			reportBase=reportBase.replaceFirst(totalRowsPlaceholder,foldersCount+totalRowsPlaceholder);
			reportBase=reportBase.replaceFirst(pageRowsPlaceholder,Constant.getReportinghistoryfolderperpage()+pageRowsPlaceholder);
			reportBase=reportBase.replaceFirst(paginationPlaceholder_TOP,data+paginationPlaceholder_TOP);
			reportBase=reportBase.replaceFirst(paginationPlaceholder_BOTTOM,data+paginationPlaceholder_BOTTOM);
			
			String paginationData=data;
			
			diff=diff-1;
			int startDiff=0,start=foldersCount,end=0,srNum=1;
			for (int page = 1; page <= totalPages; page++) {
				String reportIn= reportBase;
				start=start-startDiff-1;
				startDiff=diff;
				end=(start-diff)<=0?0:(start-diff);
				for (int i = start; i >=end ; i--) {
					tempFolderName=dateFormat.format(foldersNameList.get(i));
					customReportPath= reportingHistoryFolderPath+ "/" +tempFolderName+ "/" +Constant.resultFolderName+ "/" +Constant.resultHTMLFileName;
					NEWcustomReportPath= reportingHistoryFolderPath+ "/" +tempFolderName+ "/" +Constant.resultFolderName+ "/" +Constant.reportRedesignTemplateName;
					extentReportPath= reportingHistoryFolderPath+ "/" +tempFolderName+ "/" +Constant.resultFolderName+ "/" +Constant.resultExtentHTMLFileName;

					reportIn = reportIn.replaceFirst(rowPlaceholder,"<tr>"
							+ "<td>" + srNum++ + "</td>"
							+ "<td>" + tempFolderName + "</td>"
							+ "<td>" 
							+ 	Matcher.quoteReplacement(getDashboardContent(customReportPath,NEWcustomReportPath)) 
							+ "</td>"
							+ "<td width='50%'> "
							+ 	Matcher.quoteReplacement(getQuickViewContent(customReportPath,NEWcustomReportPath))
							+ "</td>"
							+ "<td>"
							+ 	Matcher.quoteReplacement(getLinks(customReportPath,NEWcustomReportPath,extentReportPath))
							+ "</td>"
							+ "</tr>" 
							+ rowPlaceholder);
				}
				String prev = page==1?"":"<a href='page"+(page-1)+".html'> <span class='prev'> &#8592 Prev </span></a>";
				String next = page==totalPages?"":"<a href='page"+(page+1)+".html'> <span class='next'>Next &#8594</span></a>";
				String tempdata = data.replace("<a href='page"+page+".html'>"+page+"</a> ", "<a href='page"+page+".html'><span class='currentPage'>"+page+"</span></a> ");
				reportIn=reportIn.replace(paginationData,tempdata);
				reportIn=reportIn.replaceAll(projectPlaceholder,Constant.PROJECT);
				reportIn=reportIn.replaceAll(prevPlaceHolder_TOP,prev);
				reportIn=reportIn.replaceAll(nextPlaceHolder_TOP,next);
				Files.write(Paths.get(htmlreportingHistoryFolderPath+"/page"+page+".html"),reportIn.getBytes(),StandardOpenOption.CREATE);
			}

		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	private static String getLinks(String customReportPath, String NEWcustomReportPath, String extentReportPath) {
		String links="";
		try {
			File f=new File(customReportPath);
			/* We need href = '../2019Apr22_013641/Result/Report_Redesign_Template.html#1'  */
			if(f.exists()){
				String href = customReportPath.substring(customReportPath.indexOf(Constant.reportingHistoryFolderName)+Constant.reportingHistoryFolderName.length());
				links+=" <a href='.."+href+"#1' target ='_blank'>Custom</a> <br/>";
			}

			f=new File(NEWcustomReportPath);
			if(f.exists()){
				String href = NEWcustomReportPath.substring(NEWcustomReportPath.indexOf(Constant.reportingHistoryFolderName)+Constant.reportingHistoryFolderName.length());
				links+=" <a href='.."+href+"#1' target ='_blank' style='color:red;'>Custom_NEW</a> <br/>";
			}

			f=new File(extentReportPath);
			if(f.exists()){
				String href = extentReportPath.substring(extentReportPath.indexOf(Constant.reportingHistoryFolderName)+Constant.reportingHistoryFolderName.length());
				links+=" <a href='.."+href+"' target ='_blank'>Extent</a>";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return links;
	}

	public static String getQuickViewContent(String customReportPath, String NEWcustomReportPath) {
		String quickAccessContent="";
		try {
			File f=new File(NEWcustomReportPath);
			if(!f.exists()){
				f=new File(customReportPath);
			}
			String wholeHtmlContent = IOUtils.toString(new FileInputStream(f));
			int pickerStart=wholeHtmlContent.indexOf(pickerQuickAccessStartPlaceholder);
			int pickerEnd=wholeHtmlContent.indexOf(pickerQuickAccessEndPlaceholder);
			if(pickerStart>0){
				quickAccessContent="<div class='quickView'>"+wholeHtmlContent.substring(pickerStart, pickerEnd)+"</div>";
			}else{
				quickAccessContent="<iframe src='"+customReportPath+"#1' width='99%'></iframe> ";
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return quickAccessContent;
	}

	public static String getDashboardContent(String customReportPath, String NEWcustomReportPath){
		String dashBoardContent="";
		try {
			File f=new File(NEWcustomReportPath);
			if(!f.exists()){
				f=new File(customReportPath);
			}
			String wholeHtmlContent = IOUtils.toString(new FileInputStream(f));
			int pickerStart=wholeHtmlContent.indexOf(pickerDashboardStartPlaceholder);
			int pickerEnd=wholeHtmlContent.indexOf(pickerDashboardEndPlaceholder);
			dashBoardContent=wholeHtmlContent.substring(pickerStart, pickerEnd);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dashBoardContent;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		manageHTML();
	}

}
