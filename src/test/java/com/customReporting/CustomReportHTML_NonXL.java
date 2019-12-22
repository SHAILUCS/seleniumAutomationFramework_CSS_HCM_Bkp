package com.customReporting;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
/**
 * Discontinued on 15-Jan-2019, because Reporting History 
 * does not need this report in its quick view section.
 * New Redesigned Report has modified to provide details of Test Names with Status  
 * This class generates the HTML Report in Tabular Format
 * @author shailendra.rajawat
 * */
public class CustomReportHTML_NonXL {


	private static List<ArrayList<Test>> listOfList;
	private static final String htmlreportPath=Constant.getResultHtmlFilePath();

	private final static String pickerStartPlaceholder = "<!-- PICKER1_START -->";
	private final static String pickerEndPlaceholder = "<!-- PICKER1_END -->";
	private final static String resultPlaceholder = "<!-- INSERT_RESULTS -->";
	private final static String totalScenarioPlaceholder = "<!-- TOTAL_SCENARIOS -->";
	private final static String passScenarioPlaceholder = "<!-- PASSED_SCENARIOS -->";
	private final static String failScenarioPlaceholder = "<!-- FAILED_SCENARIOS -->";
	private final static String skippedScenarioPlaceholder = "<!-- SKIPPED_SCENARIOS -->";
	private final static String errorScenarioPlaceholder = "<!-- ERROR_SCENARIOS -->";
	private final static String warningScenarioPlaceholder = "<!-- WARNING_SCENARIOS -->";
	private final static String fatalScenarioPlaceholder = "<!-- FATAL_SCENARIOS -->";

	private final static String totalStepPlaceholder = "<!-- TOTAL_Step -->";
	private final static String passStepPlaceholder = "<!-- PASSED_Step -->";
	private final static String failStepPlaceholder = "<!-- FAILED_Step -->";
	private final static String skippedStepPlaceholder = "<!-- SKIPPED_Step -->";
	private final static String errorStepPlaceholder = "<!-- ERROR_Step -->";
	private final static String warningStepPlaceholder = "<!-- WARNING_Step -->";
	private final static String fatalStepPlaceholder = "<!-- FATAL_Step -->";

	private static long stepCounter=0;

	private static String styleHTML="<style>"
			+ "body{font-family: calibri;background-color: #DFDFDF;}"
			+ "h2{color:midnightblue;}"
			+ "table{border: 1px solid #AAAAAA;}"
			+ "th{border-bottom: 1px solid #AAAAAA;}"
			+ "tr{background: aliceblue;}"
			+ "p{max-height: 200px;overflow: auto;}"
			+ ".dashboard-header{border-radius: 20px; background: #8a8a8a; color: #fff; padding: 4px 0px; text-align: center; font-weight: 600;}"
			+ ".properties-div{border: solid black 0px; margin: 0px; padding: 0px; width: max-content; color:midnightblue; text-align: left;}"
			+ ".properties-span{border: solid black 0px; margin: 5px; padding: 3px; }"
			+ ".testNGInfo{font-size: small;font-family: monospace;display: block;padding: 0px;margin: 1px;}"
			+ ".hr {display: block;height: 1px;border: 0;border-top: 1px solid #DFDFDF;margin: 1px;padding: 0px;}"
			+ ".mainData-div{width: inherit; max-width: 100%; max-height: 250px; overflow-y: auto;}"
			+ ".mainData-span{word-wrap: break-word;}"
			+ ".bigData{width: 500px;}"
			+ ".crossChecker{border: 0px; margin: 0px; padding: 0px; width: 45px; color: gray; display: inline-block; text-align: center;}"
			+ "</style>";
	
	private static String scriptHTML="<script>"
			+ "function checkUncheck(obj){"
			+ "var parentObj=obj.parentElement;"
			+ "do{"
			+ "		parentObj=parentObj.parentElement;"
			+ "}while(parentObj.nodeName!='TR');"
			+ "if(obj.checked){"
			+ "		parentObj.style.background='yellow';"
			+ "}else{"
			+ "		parentObj.style.background='aliceblue';"
			+ "}"
			+ "}"
			+ "</script>";

	private static String scenario, status, description, errorScreenshotURL,scenarioCounter="",startTime,executionTime,browser,platform,timeStamp;

	public static synchronized void createHTML_NonXl(){
		listOfList=CustomReporter.getListOfList();

		assignSerialNumber();

		String reportIn="";
		try{
			//SETTING REPORT DASHBOARD DATA
			prepareDashboardCountData();

			reportIn = "<html>" + "<head>"
					+ "<link rel='icon href='http://www.seleniumhq.org/selenium-favicon.ico' type='image/vnd.microsoft.icon'>"
					+ "<title>IOTRON Functional Automation Test Result Report</title>"
					+ styleHTML
					+ "<head>"
					+ "<body><center>"
					+ "<h2>IOTRON Functional Automation Test Result Report</h2>"

				+ "<div class='properties-div'><fieldset><legend>Framework Cofiguration</legend>"
				+ "<span class='properties-span'>Parallel Mode:<b> "+ Test.getInParallel() +"</b></span>"
				+ "<span class='properties-span'>Assertion Enabled:<b> "+ Constant.enableAssertions +"</b></span>"
				+ "<span class='properties-span'>Capturing Snapshots:<b> "+ Constant.enableCaptureSnapshots() +"</b></span>"
				+ "</fieldset></div>"

				+ "<h4 >"
				+pickerStartPlaceholder

				+ "<table border='0'>"
				+ "<tbody>"

				+ "<tr>"
				+ "<td colspan='8' text-align='center' class='dashboard-header' width='50px'>"
					+ "Dashboard - "
					+ "<span style='font-size: smaller;font-family: monospace;word-break: break-all;max-width: 500px;'>"
					+ "Env:"+Constant.get_HCM_EnvironmentInfoSheet()+", "
					+ "Suite:" + Test.getTestNG_SuiteName() + ", "
					+ "Test:" + Test.getTestNG_TestName() + "</span>"
				+" </td></tr>"

				+ "<tr>"
				+ "<td colspan='3' style='font-weight: bold; text-align: center;'>"+ "Start"+ "</td>"
				+ "<td  colspan='3' style='font-weight: bold; text-align: center;'>"+ "End"+ "</td>"
				+ "<td  colspan='2' style='font-weight: bold; text-align: center;'>"+ "Time Taken"+ "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td colspan='3' style='text-align: center;'>"+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(Test.getTestExecutionStartDate())+ "</td>"
				+ "<td colspan='3' style='text-align: center;'>"+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(Test.getTestExecutionEndDate())+ "</td>"
				+ "<td colspan='2' style='text-align: center;'>"+ Test.getTestExecutionTime()+ "</td>"
				+ "</tr>"

				+ "<tr></tr>"
				+ "<tr></tr>"



				+ "<tr style='font-weight: 600;'>"
				+ "<td></td>"
				+ "<td style='color:midnightblue;'>#Total</td>"
				+ "<td style='color:green;'>#Passed</td>"
				+ "<td style='color:red;'>#Failed</td>"
				+ "<td style='color:brown;'>#Skipped</td>"
				+ "<td style='color:#ec407a;'>#Error</td>"
				+ "<td style='color:orange;'>#Warning</td>"
				+ "<td style='color:#B71C1C;'>#Fatal</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='font-weight: 600;'>Scenario</td>"
				+ "<td style='text-align: center;'>"+totalScenarioPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+passScenarioPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+failScenarioPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+skippedScenarioPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+errorScenarioPlaceholder+"</td>"
				+ "<td style='text-align: center;'> "+warningScenarioPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+fatalScenarioPlaceholder+"</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td style='font-weight: 600;'>Steps</td>"
				+ "<td style='text-align: center;'>"+totalStepPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+passStepPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+failStepPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+skippedStepPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+errorStepPlaceholder+"</td>"
				+ "<td style='text-align: center;'> "+warningStepPlaceholder+"</td>"
				+ "<td style='text-align: center;'>"+fatalStepPlaceholder+"</td>"
				+ "</tr>"


				+ "</tbody></table>"
				+ pickerEndPlaceholder
				+ "</h4>"

				+"<pre style='text-align: left;'><b>Note:</b> <i>Report follows hh:mm:ss time format</i></pre>"

				+ "<table border='0'>"
				+ "<tr>"
				+ "<th >Sr.No.</th>"
				+ "<th >Start Time</th>"
				+ "<th >Test Case Name</th>"
				+ "<th >#</th>"
				+ "<th >Step Description</th>"
				+ "<th >Status</th>"
				+ "<th >Time Stamp</th>"
				+ "<th >Execution Time</th>"
				+ "<th >Platform</th>"
				+ "<th >Browser</th>"
				+ "<th >Screenshot</th>"
				+ "</tr>"
				+ resultPlaceholder 
				+ "</table>" 
				+ "<p><b>End of Report</b></p>"
				+ "</center></body>"
				+ scriptHTML
				+ "</html>";

			reportIn = reportIn.replaceFirst(totalScenarioPlaceholder,getDashBoardNumberColor(Test.getTotalScenario(),"total"));
			reportIn = reportIn.replaceFirst(passScenarioPlaceholder,getDashBoardNumberColor(Test.getPassedScenario(),"pass"));
			reportIn = reportIn.replaceFirst(failScenarioPlaceholder,getDashBoardNumberColor(Test.getFailedScenario(),"fail"));
			reportIn = reportIn.replaceFirst(skippedScenarioPlaceholder,getDashBoardNumberColor(Test.getSkippedScenario(),"skip"));
			reportIn = reportIn.replaceFirst(errorScenarioPlaceholder,getDashBoardNumberColor(Test.getErrorScenario(),"error"));
			reportIn = reportIn.replaceFirst(warningScenarioPlaceholder,getDashBoardNumberColor(Test.getWarningScenario(),"warn"));
			reportIn = reportIn.replaceFirst(fatalScenarioPlaceholder,getDashBoardNumberColor(Test.getFatalScenario(),"fatal"));

			reportIn = reportIn.replaceFirst(totalStepPlaceholder,getDashBoardNumberColor(Test.getTotalStep(),"total"));
			reportIn = reportIn.replaceFirst(passStepPlaceholder,getDashBoardNumberColor(Test.getPassedStep(),"pass"));
			reportIn = reportIn.replaceFirst(failStepPlaceholder,getDashBoardNumberColor(Test.getFailedStep(),"fail"));
			reportIn = reportIn.replaceFirst(skippedStepPlaceholder,getDashBoardNumberColor(Test.getSkippedStep(),"skip"));
			reportIn = reportIn.replaceFirst(errorStepPlaceholder,getDashBoardNumberColor(Test.getErrorStep(),"error"));
			reportIn = reportIn.replaceFirst(warningStepPlaceholder,getDashBoardNumberColor(Test.getWarningStep(),"warn"));
			reportIn = reportIn.replaceFirst(fatalStepPlaceholder,getDashBoardNumberColor(Test.getFatalStep(),"fatal"));


			for (List<Test> tempObj : listOfList) {
				long arraySize=tempObj.size();
				for (int i = 0; i < arraySize; i++) {
					scenarioCounter=tempObj.get(i).getSrNo();
					scenario=tempObj.get(i).getScenario();
					status=tempObj.get(i).getStatus();
					description=tempObj.get(i).getDescription();
					startTime=tempObj.get(i).getStartTime_Scenario()!=0?Util.convertToHHMMSS(tempObj.get(i).getStartTime_Scenario()):"";
					executionTime= tempObj.get(i).getExecutionTime_Scenario();
					platform=tempObj.get(i).getPlatform();
					browser=tempObj.get(i).getBrowser();
					errorScreenshotURL=tempObj.get(i).getSnapshotURL();
					timeStamp=tempObj.get(i).getTimeStamp();

					if (tempObj.get(i).getTestNG_MethodName() != null
							&& !tempObj.get(i).getTestNG_MethodName().equals("")) {
						String data = "<div class='testNGInfo'>Class{" + tempObj.get(i).getClassName()+ "} </div>"
								+ "<div class='hr'></div>"
								+ "<div class='testNGInfo'>Method{"+ tempObj.get(i).getTestNG_MethodName() + "}, "
									+ "Priority{" +(tempObj.get(i).getTestNG_Priority().equals("0")?"NA":tempObj.get(i).getTestNG_Priority())+ "}</div>";
						description = "" + data + "";
					}

					reportIn = reportIn.replaceFirst(resultPlaceholder,
							"<tr "+getScenarioRowStyle(scenario,status)+">"
									+ "<td id="+scenarioCounter+">" + scenarioCounter + "</td>"
									+ "<td>" +startTime+"</td>"
									+ "<td class='bigData'>" + getStyledDataBasedOnStatus(status,scenario) + "</td>"
									+ "<td ><span class='crossChecker'>" + getStepCounter(scenario) + "</span></td>"
									+ "<td class='bigData'>"+getStyledDataBasedOnStatus(status,description)+"</td>"
									+ "<td "+getStatusTextStyle(status)+">" + status + "</td>"
									+ "<td><p style='color:gray;'>" +timeStamp+"</p></td>"
									+ "<td>" +executionTime+"</td>"
									+ "<td>" +platform+"</td>"
									+ "<td>" +browser+"</td>"
									+ "<td>" +constructScreenshotElementTag(errorScreenshotURL)+"</td>"
									+ "</tr>" 
									+ resultPlaceholder);
					
					List<Test> subStepsList=tempObj.get(i).getList();
					int size=subStepsList.size();
					if (size>0) {
						for (int j = 0; j < size; j++) {

							scenario="";
							status=subStepsList.get(j).getStatus();
							description=subStepsList.get(j).getDescription();
							errorScreenshotURL=subStepsList.get(j).getSnapshotURL();
							timeStamp=subStepsList.get(j).getTimeStamp();
							reportIn = reportIn.replaceFirst(resultPlaceholder,
									"<tr "+getScenarioRowStyle(scenario,status)+">"
											+ "<td ></td>"
											+ "<td></td>"
											+ "<td class='bigData'>" + getStyledDataBasedOnStatus(status,scenario) + "</td>"
											+ "<td ><span class='crossChecker'>" + getStepCounter(scenario) + "</span></td>"
											+ "<td class='bigData'>"+getStyledDataBasedOnStatus(status,description)+"</td>"
											+ "<td "+getStatusTextStyle(status)+">" + status + "</td>"
											+ "<td><p style='color:gray;'>" +timeStamp+"</p></td>"
											+ "<td></td>"
											+ "<td></td>"
											+ "<td></td>"
											+ "<td>" +constructScreenshotElementTag(errorScreenshotURL)+"</td>"
											+ "</tr>" 
											+ resultPlaceholder);
						}
					}


				}
			}

			//Creating the new file
			Files.write(Paths.get(htmlreportPath),reportIn.getBytes(),StandardOpenOption.CREATE); 
		}catch(Exception e){
			System.err.println("EXCEPTION FOR DATA:/n");
			System.err.println("<tr "+getScenarioRowStyle(scenario,status)+">"
					+ "<td id="+scenarioCounter+">" + scenarioCounter + "</td>"
					+ "<td>" +startTime+"</td>"
					+ "<td>" + scenario + "</td>"
					+ "<td><p style='color:gray;'>" + getStepCounter(scenario) + "</p></td>"
					+ "<td>"+getStyledDataBasedOnStatus(status,description)+"</td>"
					+ "<td "+getStatusTextStyle(status)+">" + status + "</td>"
					+ "<td><p style='color:gray;'>" +timeStamp+"</p></td>"
					+ "<td>" +executionTime+"</td>"
					+ "<td>" +platform+"</td>"
					+ "<td>" +browser+"</td>"
					+ "<td>" +errorScreenshotURL+"</td>"
					+ "</tr>" );

			e.printStackTrace();
		}

	}

	private static void assignSerialNumber() {
		for (int i = 1; i <= listOfList.size(); i++) {
			List<Test> resultList=listOfList.get(i-1);
			Test r=resultList.get(0);
			r.setSrNo(i+"");
		}
	}

	private static String constructScreenshotElementTag(String errorScreenshotURL) {
		if ("".equals(errorScreenshotURL) || errorScreenshotURL==null) {
			return "";
		}else if (errorScreenshotURL.contains(Constant.snapshotsMovieTemplateName)) {
			return 	"<a href='"+errorScreenshotURL+"' target ='_blank'>MOVIE</a>";
		}
		return 	"<a href='"+errorScreenshotURL+"' target ='_blank'><img src='"+errorScreenshotURL+"' style='max-width: 250px;max-height: 250px;'></a>";
	}

	private static String getStepCounter(String scenario) {
		if(scenario!=null && !scenario.equals("")){
			stepCounter=0;
			return "";
		}else{
			stepCounter++;
			return stepCounter+"<input type='checkbox' onchange='checkUncheck(this)'></input>";
		}
	}

	private static String getDashBoardNumberColor(long number, String string) {
		String placeHolderWithColor="";
		if("0".equals(number+"")){
			placeHolderWithColor="<span style='color:white;'>"+number+"</span>";
		}else{
			switch (string) {
			case "total":
				placeHolderWithColor="<span style='color:midnightblue'>"+number+"</span>";
				break;
			case "pass":
				placeHolderWithColor="<span style='color:green;'>"+number+"</span>";
				break;
			case "fail":
				placeHolderWithColor="<span style='color:red;'>"+number+"</span>";
				break;
			case "skip":
				placeHolderWithColor="<span style='color:brown;'>"+number+"</span>";
				break;
			case "error":
				placeHolderWithColor="<span style='color:#ec407a;'>"+number+"</span>";
				break;
			case "warn":
				placeHolderWithColor="<span style='color:orange;'>"+number+"</span>";
				break;
			case "fatal":
				placeHolderWithColor="<span style='color:#B71C1C;'>"+number+"</span>";
				break;
			}
		}

		return placeHolderWithColor;
	}


	private static synchronized void prepareDashboardCountData(){
		//This method will get the counts of (pass, fail, skip, error, fatal) scenarios, and Also change the scenario status as per the overall status of steps
		updateNODEStatusBasedOnSteps(STATUS.SKIP.value);
		updateNODEStatusBasedOnSteps(STATUS.WARNING.value);
		updateNODEStatusBasedOnSteps(STATUS.ERROR.value);
		updateNODEStatusBasedOnSteps(STATUS.FAIL.value);
		updateNODEStatusBasedOnSteps(STATUS.FATAL.value);
		
		updateScenarioStatusBasedOnSteps(STATUS.SKIP.value);
		//updateScenarioStatusBasedOnSteps(Constant.WARNING);
		updateScenarioStatusBasedOnSteps(STATUS.ERROR.value);
		updateScenarioStatusBasedOnSteps(STATUS.FAIL.value);
		updateScenarioStatusBasedOnSteps(STATUS.FATAL.value);

		//Getting the pass scenario count
		long skipCount=getScenarioCountBasedOnStatus(STATUS.SKIP.value);
		
		//Getting the pass scenario count
		long passCount=getScenarioCountBasedOnStatus(STATUS.PASS.value);
		
		//Getting the fatal scenario count
		long fatalCount=getScenarioCountBasedOnStatus(STATUS.FATAL.value);
		
		//Getting the fail scenarios count
		long failCount=getScenarioCountBasedOnStatus(STATUS.FAIL.value);
		
		//Getting the error scenario count
		long errorCount=getScenarioCountBasedOnStatus(STATUS.ERROR.value);
		
		//Getting the warning scenario count
		long warningCount=getScenarioCountBasedOnStatus(STATUS.WARNING.value);

		// Setting the total Test count from TestNG Listener Class 
		// Test.setTotalScenario(passCount+failCount+skipCount+errorCount+fatalCount+warningCount);
		Test.setPassedScenario(passCount);
		Test.setFailedScenario(failCount);
		Test.setSkippedScenario(skipCount);
		Test.setErrorScenario(errorCount);
		Test.setFatalScenario(fatalCount);
		Test.setWarningScenario(warningCount);


		long infoStep=getStepCountBasedOnStatus(STATUS.INFO.value);
		long passStep=getStepCountBasedOnStatus(STATUS.PASS.value);
		long failStep=getStepCountBasedOnStatus(STATUS.FAIL.value);
		long skipStep=getStepCountBasedOnStatus(STATUS.SKIP.value);
		long errorStep=getStepCountBasedOnStatus(STATUS.ERROR.value);
		long warnStep=getStepCountBasedOnStatus(STATUS.WARNING.value);
		long fatalStep=getStepCountBasedOnStatus(STATUS.FATAL.value);
		
		Test.setTotalStep(infoStep+passStep+failStep+skipStep+errorStep+warnStep+fatalStep);
		Test.setPassedStep(passStep+infoStep);
		Test.setFailedStep(failStep);
		Test.setSkippedStep(skipStep);
		Test.setErrorStep(errorStep);
		Test.setWarningStep(warnStep);
		Test.setFatalStep(fatalStep);
	}

	private static void updateNODEStatusBasedOnSteps(String status){
		for (int i = 0; i < listOfList.size(); i++) {
			List<Test> stepsList=listOfList.get(i);
			for (int j = 1; j < stepsList.size(); j++) {

				List<Test> subStepsList=stepsList.get(j).getList();
				if (subStepsList.size()>0) {
					for (int k = 0; k < subStepsList.size(); k++) {
						String subStepStatus=subStepsList.get(k).getStatus();
						if (subStepStatus.equalsIgnoreCase(status)) {
							stepsList.get(j).setStatus(status);
							break;
						}
					}
				}
			}
		}
	}
	
	private static long getStepCountBasedOnStatus(String status) {
		long count=0;
		for (int i = 0; i < listOfList.size(); i++) {
			List<Test> resultList=listOfList.get(i);
			for (int j = 1; j < resultList.size(); j++) {
				String stepStatus=resultList.get(j).getStatus();
				if (stepStatus.equalsIgnoreCase(status)) {
					count++;
				}
			}
		}
		return count;
	}


	private static long getScenarioCountBasedOnStatus(String status){
		long count=0;
		for (int i = 0; i < listOfList.size(); i++) {
			List<Test> resultList=listOfList.get(i);
			String scenarioStatus=resultList.get(0).getStatus();
			if (scenarioStatus.equalsIgnoreCase(status)) {
				count++;
			}
		}
		return count;
	}
	
	private static void updateScenarioStatusBasedOnSteps(String status){
		for (int i = 0; i < listOfList.size(); i++) {
			List<Test> resultList=listOfList.get(i);
			for (int j = 1; j < resultList.size(); j++) {
				String stepStatus=resultList.get(j).getStatus();
				if (stepStatus.equalsIgnoreCase(status)) {
					resultList.get(0).setStatus(status);
					break;
				}
			}
		}
	}

	private static synchronized String getStatusTextStyle(String status){
		String font_color_val="blue";
		//Setting up the font color based on status value
		if (status!=null) {
			if(status.contains(STATUS.PASS.value)){
				font_color_val="green";
			}else if(status.contains(STATUS.FAIL.value)){
				font_color_val="#F44336";
			}else if(status.contains(STATUS.INFO.value)){
				font_color_val="blue";
			}else if(status.contains(STATUS.WARNING.value)){
				font_color_val="orange";
			}else if(status.contains(STATUS.SKIP.value)){
				font_color_val="brown";
			}else if(status.contains(STATUS.ERROR.value)){
				font_color_val="#ec407a";
			}else if(status.contains(STATUS.FATAL.value)){
				font_color_val="#B71C1C";
			}
		}
		return "style='color:"+font_color_val+";'";
	}

	private static synchronized String getStyledDataBasedOnStatus(String status,String description){
		if ("".equals(description) || description==null) {
			return "";
		}
		
		String font_color_val="gray";
		//Setting up the font color based on status value
		if(status!=null){
			if(status.contains(STATUS.INFO.value)){
				font_color_val="cornflowerblue";
			}else if(status.contains(STATUS.FATAL.value)){
				font_color_val="#B71C1C;";
			}
		}
		String span="<span class='mainData-span' >"+(description!=null?Matcher.quoteReplacement(description):"")+"</span>";
		String div="<div class='mainData-div' style='color: "+font_color_val+";'>"+span+"</div>";
		return div; 
	}

	private static synchronized String getScenarioRowStyle(String scenario,String status){
		String color_val="aliceblue";
		//Setting up the background color based on status value
		if(scenario!=null && !scenario.equals("")){
			if(status.contains(STATUS.FAIL.value)){
				color_val="lightpink";
			}else if(status.contains(STATUS.SKIP.value)){
				color_val="lightgray";
			}else if(status.contains(STATUS.PASS.value)){
				color_val="#CCFFCC";
			}else if(status.contains(STATUS.WARNING.value)){
				color_val="#FFFFCC";
			}else if(status.contains(STATUS.ERROR.value)){
				color_val="#FDC7E3";
			}else if(status.contains(STATUS.FATAL.value)){
				color_val="#FFCEAD";
			}
		}
		return "style='background: "+color_val+";'";
	}

}
