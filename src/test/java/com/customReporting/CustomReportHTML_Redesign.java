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
import com.customReporting.pojos.TestNGClass_POJO;
import com.customReporting.pojos.TestNGMethods_POJO;
import com.customReporting.pojos.TestNGSuite_POJO;
import com.customReporting.pojos.TestNGTests_POJO;

/** 
 * @author shailendra.rajawat 
 * created date 24 Aug 2018
 *  */
public class CustomReportHTML_Redesign {

	private static List<ArrayList<Test>> listOfList;
	private static final String htmlreportFolderPath=Constant.getResultFolderPath();
	private static String reportRedesignTemplateFilePath = Constant.getReportRedesignTemplateFilePath();

	private final static String projectPlaceholder = "<!-- PROJECT -->";
	
	private final static String paralleModePlaceholder = "<!-- paralleMode -->";
	private final static String threadCount = "<!-- threadCount -->";
	private final static String assertionEnabledPlaceholder = "<!-- assertionEnabled -->";
	private final static String capturingSnapshotsPlaceholder = "<!-- capturingSnapshots -->";
	private final static String implicitWait = "<!-- implicitWait -->";
	private final static String explicitWait = "<!-- explicitWait -->";
	private final static String width = "<!-- width -->";
	private final static String height = "<!-- height -->";
	
	private final static String envPlaceholder = "<!-- Environment -->";
	private final static String suitePlaceholder = "<!-- Suite -->";
	private final static String testPlaceholder = "<!-- Test -->";

	private final static String execStartPlaceholder = "<!-- execStartPlaceholder -->";
	private final static String execEndPlaceholder = "<!-- execEndPlaceholder -->";
	private final static String execTimePlaceholder = "<!-- execTimePlaceholder -->";

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
	

	/**Added pass, fail and skip percent in Dashboard @author shailendra Oct 23, 2019*/
	private final static String percentageBar = "<!-- percentageBar -->";
	private final static String quickAccess_ItemsPlaceholder = "<!-- quickAccess_items -->";
	

	private static String scenario, status, description, errorScreenshotURL, startTime, endTime,
			executionTime, browser, platform, timeStamp, methodNameTestNG, classNameTestNG, groupNameTestNG,
			priorityTestNG, dependsOnTestNG;

	public static synchronized void createHTML(){
		listOfList=CustomReporter.getListOfList();

		assignSerialNumber();

		try{
			String reportIn = new String(Files.readAllBytes(Paths.get(reportRedesignTemplateFilePath)));

			//SETTING REPORT DASHBOARD DATA
			prepareDashboardCountData();

			reportIn = reportIn.replaceFirst(projectPlaceholder,Constant.PROJECT);
			reportIn = reportIn.replaceFirst(paralleModePlaceholder,Test.getInParallel());
			reportIn = reportIn.replaceFirst(threadCount,Test.getThreadCount());
			reportIn = reportIn.replaceFirst(assertionEnabledPlaceholder,Constant.enableAssertions+"");
			reportIn = reportIn.replaceFirst(capturingSnapshotsPlaceholder,Constant.enableCaptureSnapshots()+"");
			reportIn = reportIn.replaceFirst(implicitWait,Constant.implicitWait+"");
			reportIn = reportIn.replaceFirst(explicitWait,Constant.wait+"");
			reportIn = reportIn.replaceFirst(width,Constant.width+"");
			reportIn = reportIn.replaceFirst(height,Constant.height+"");

			reportIn = reportIn.replaceFirst(envPlaceholder,"<span class='"+Constant.get_HCM_EnvironmentInfoSheet()+"'>"+Constant.get_HCM_EnvironmentInfoSheet()+"</span>");
			reportIn = reportIn.replaceFirst(suitePlaceholder,Test.getTestNG_SuiteName());
			reportIn = reportIn.replaceFirst(testPlaceholder,Test.getTestNG_TestName()); 

			reportIn = reportIn.replaceFirst(execStartPlaceholder,new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(Test.getTestExecutionStartDate())); 
			reportIn = reportIn.replaceFirst(execEndPlaceholder,new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(Test.getTestExecutionEndDate())); 
			reportIn = reportIn.replaceFirst(execTimePlaceholder,Test.getTestExecutionTime()); 

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

			reportIn = reportIn.replaceFirst(percentageBar, generateDashboardPercentageBarData());
			

			String stepsAndTitle="";
			for (List<Test> listOfResultObjs : listOfList) {

				Test test_title=listOfResultObjs.get(0);
				scenario=test_title.getScenario();
				status=test_title.getStatus();
				startTime=test_title.getStartTime_Scenario()!=0?Util.convertToHHMMSS(test_title.getStartTime_Scenario()):"";
				endTime=test_title.getEndTime_Scenario()!=0?Util.convertToHHMMSS(test_title.getEndTime_Scenario()):"";
				executionTime= test_title.getExecutionTime_Scenario();
				platform=test_title.getPlatform();
				browser=test_title.getBrowser();
				timeStamp=test_title.getTimeStamp();
				errorScreenshotURL=test_title.getSnapshotURL();
				classNameTestNG=test_title.getClassName();
				methodNameTestNG=test_title.getTestNG_MethodName();
				priorityTestNG=test_title.getTestNG_Priority();
				groupNameTestNG=test_title.getGroup();
				dependsOnTestNG=test_title.getDependsOn();


				String title_SECTION = "<input type='checkbox'  onclick='checkUncheckTitle(this)'>"
						+ "<div class='title' id = 'qa_" +methodNameTestNG+"'>"
						+ "<div class='"+status.toLowerCase()+"'>"
						+ "<div class='title-testStatus'>"
						+ "<span >"+status+"</span>"
						+ "</div>"


						+ "<div class='title-startTime'>"
						+ "<span >"+startTime+"</span>"
						+ "</div>"

						+ "<div class='title-Platform'>"
						+ "	<span >"+platform+"</span>"
						+ "</div>"

						+ "<div class='title-Browser'>"
						+ "	<span >"+browser+"</span>"
						+ "</div>"

						+ constructScreenshotElementTag(errorScreenshotURL)

						+ "<div class='title-text'>"
						+ "	<span >"
						+ (scenario!=null?Matcher.quoteReplacement(scenario):"")
						+ "</span>"
						+ "</div>"
						+ "<div class='title-testNGInfo'>"
						+ "	<table border='0'>"
						+ "		<tbody>	"
						+ "			<tr><td><b>Start / End / Elapsed:</b></td>"
						+ "				<td><span class='title-start-time-scenario'>"+startTime+"</span> / "
						+ "					<span class='title-end-time-scenario'>"+endTime+"</span> / "
						+ "					<span class='title-exec-time-scenario'>"+executionTime+"</span>"
						+ "			</td></tr>"
						+ "			<tr><td><b>Group:</b></td><td>"+groupNameTestNG+"</td></tr>"
						+ "			<tr><td><b>Class:</b></td><td>"+classNameTestNG+"</td></tr>"
						+ "			<tr><td><b>Method:</b></td><td>"+methodNameTestNG+"</td></tr>"
						+ "			<tr><td><b>Priority:</b></td><td>"+priorityTestNG+"</td></tr>"
						+ "			<tr><td><b>Depends On:</b></td><td>"+dependsOnTestNG+"</td></tr>"
						+ "			<tr><td><b>Steps:</b></td><td><!-- stepsCounter --></td></tr>"
						+ "		</tbody>"	
						+ "	</table>"							
						+ "	</div>"							
						+ "	</div>"
						+ "	</div>";

				String steps_LI ="";

				long arraySize=listOfResultObjs.size();

				//TODO ADDING STEPS OF TEST
				int stepCounter=0;
				for (int i = 1; i < arraySize; i++) {

					//TODO In case there are NODES, Add [DIV > OL > LI] Elements
					Test test_step=listOfResultObjs.get(i);

					List<Test> subSteps=test_step.getList();
					int subStepsSize=subSteps.size();
					if(subStepsSize>0){

						String nodeTitle_SECTION =  "<input type='checkbox'  onclick='checkUncheckNode(this)'>"
													+ "<div class='node'>"
													+ "<div class='"+test_step.getStatus().toLowerCase()+"'>"
													+ "<div class='title-testStatus'>"
													+ "<span >"+test_step.getStatus()+"</span>"
													+ "</div>"
			
													+ "<div class='title-startTime'>"
													+ "<span >"+test_step.getTimeStamp()+"</span>"
													+ "</div>"
			
													+ "<div class='title-text'>"
													+ "	<span >"
													+ (test_step.getDescription()!=null?Matcher.quoteReplacement(test_step.getDescription()):"")
													+ "</span>"
													+ "</div>"
													+ "<div class='stepCount'>Steps: "+subStepsSize+"</div>"							
													+ "	</div>"
													+ "	</div>";

						String nodeSteps_LI="";
						for (int j = 0; j < subStepsSize; j++) {
							stepCounter++;
							Test test_SubStep=subSteps.get(j);
							nodeSteps_LI =nodeSteps_LI+"<li class='stepLI'>"
									+"<div class='step'>"

									+"<div class='steps-step-Status'>"
									+"<span class="+test_SubStep.getStatus().toLowerCase()+">"+test_SubStep.getStatus()+"</span>"
									+"</div>"

									+"<div class='steps-step-startTime'>"
									+"<span >"+test_SubStep.getTimeStamp()+"</span>"
									+"</div>"

									+"<div class='steps-step-text'>"
									+ "<input type='checkbox'  onclick='checkUncheck(this)'>"
									+"<span class="+getStyledStepDesc_BasedOnStatus(test_SubStep.getStatus())+">"+(test_SubStep.getDescription()!=null?Matcher.quoteReplacement(test_SubStep.getDescription()):"")+"</span>"
									+"</div>"

									+constructScreenshotElementTag(test_SubStep.getSnapshotURL())

									+"</div>"	
									+"</li>";					
						}
						steps_LI=steps_LI+
								"<li class='stepLI'>"
								+nodeTitle_SECTION
								+"<div id='steps'>"
								+ "<ol>"
								/*+ "<input type='text'  onkeyup='performStepsSearch(this)' placeholder='Search Steps'>"*/
								+ nodeSteps_LI
								+ "</ol>"
								+ "</div>"
								+"</li>";
					}else{
						//TODO In case there are just normal steps, Add LI Elements
						description=test_step.getDescription();
						errorScreenshotURL=test_step.getSnapshotURL();
						timeStamp=test_step.getTimeStamp();
						status=test_step.getStatus();

						if (status.equals(STATUS.NODE.value)) {
							String nodeTitle_SECTION =  "<li class='stepLI'>"
									+"<input type='checkbox'  onclick='checkUncheckNode(this)'>"
									+ "<div class='node'>"
									+ "<div class='info'>"
									+ "<div class='title-testStatus'>"
									+ "<span >"+STATUS.INFO.value+"</span>"
									+ "</div>"

									+ "<div class='title-startTime'>"
									+ "<span >"+test_step.getTimeStamp()+"</span>"
									+ "</div>"

									+ "<div class='title-text'>"
									+ "	<span >"
									+ (test_step.getDescription()!=null?Matcher.quoteReplacement(test_step.getDescription()):"")
									+ "</span>"
									+ "</div>"
									+ "<div class='stepCount'>Steps: 0</div>"							
									+ "	</div>"
									+ "	</div>"
									+ "</li>";
							
							steps_LI =steps_LI+nodeTitle_SECTION;
						}else{
							stepCounter++;
							steps_LI =steps_LI+"<li class='stepLI'>"
									+"<div class='step'>"

								+"<div class='steps-step-Status'>"
								+"<span class="+status.toLowerCase()+">"+status+"</span>"
								+"</div>"

								+"<div class='steps-step-startTime'>"
								+"<span >"+timeStamp+"</span>"
								+"</div>"

								+"<div class='steps-step-text'>"
								+ "<input type='checkbox'  onclick='checkUncheck(this)'>"
								+"<span class="+getStyledStepDesc_BasedOnStatus(status)+">"+(description!=null?Matcher.quoteReplacement(description):"")+"</span>"
								+"</div>"

								+constructScreenshotElementTag(errorScreenshotURL)

								+"</div>"	
								+"</li>";
						}

					}

				}

				String steps_SECTION ="<div id='steps'>"
						+ "<ol>"
						/*+ "<input type='text' onkeyup='performStepsSearch(this)' placeholder='Search Steps'>"*/
						+ steps_LI
						+ "</ol>"
						+ "</div>";

				title_SECTION=title_SECTION.replaceFirst("<!-- stepsCounter -->", stepCounter+"");
				stepsAndTitle=stepsAndTitle+"<li class='test'>"
						+title_SECTION
						+steps_SECTION
						+"</li>";
			}
			reportIn = reportIn.replaceFirst(resultPlaceholder,
					stepsAndTitle + resultPlaceholder);
			
			// Adding Quick Access Data as well, rather than generating it
			// on page load by java script, This data will be picked up by
			// reporting history manager and will be displayed in quick view
			// section
			String quicAccessData=constructQuickAccessDataElements();
			reportIn = reportIn.replaceFirst(quickAccess_ItemsPlaceholder,
					quicAccessData + quickAccess_ItemsPlaceholder);
			
			//Creating the new file
			Files.write(Paths.get(htmlreportFolderPath+"/"+Constant.reportRedesignTemplateName),reportIn.getBytes(),StandardOpenOption.CREATE); 
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * This method will add the Passing, Failing and Skipping percent in Dashboard 
	 * @param reportIn
	 * @return reportIn
	 * @author shailendra Nov 1, 2019
	 */
	private static String generateDashboardPercentageBarData() {
		
		String mainDivContent = "";
		String divMiniBarMarginBottomPx = "30";
		String divMiniMiddleMarginBottomPx = "-25";
		
		if(Test.getPassingPercent()!=0.0) {
			String tempPerc = Test.getPassingPercent()+"";
			tempPerc = (tempPerc.length()>4)?tempPerc.substring(0, 5):tempPerc ;
			tempPerc = tempPerc + "%";
			
			String div = "<div class='miniBarProgress tooltips green' style='width: "+tempPerc+"; background-color: green;'>" + 
					"	<div class='mini-middle'>" +  
					tempPerc + 
					"	</div>" + 
					"</div>";
			mainDivContent = mainDivContent + div;
			//reportIn = reportIn.replaceAll(passingPercent,div);
		}
		
		if(Test.getFailurePercent()!=0.0) {
			String tempPerc = Test.getFailurePercent()+"";
			tempPerc = (tempPerc.length()>4)?tempPerc.substring(0, 5):tempPerc;
			tempPerc = tempPerc+"%";
			
			String margin_bottom = "";
			if(Test.getFailurePercent() < 20.0 && Test.getPassingPercent() > 0.0 && Test.getSkipPercent() > 0.0) {
				margin_bottom = "margin-bottom: " + divMiniMiddleMarginBottomPx + "px;";
				divMiniBarMarginBottomPx = "55";
			}
			
			String div = "<div class='miniBarProgress tooltips red' style='width: "+tempPerc+"; background-color: red;'>" + 
					"									<div class='mini-middle' style='"+margin_bottom+"'>" +  
					tempPerc + 
					"									</div>" + 
					"								</div>";
			
			mainDivContent = mainDivContent + div;
			//reportIn = reportIn.replaceAll(failurePercent,div);
		}

		if(Test.getSkipPercent()!=0.0) {
			String tempPerc = Test.getSkipPercent()+"";
			tempPerc = (tempPerc.length()>4)?tempPerc.substring(0, 5):tempPerc;
			tempPerc = tempPerc+"%";
			
			String div = "<div class='miniBarProgress tooltips brown' style='width: "+tempPerc+"; background-color: brown;'>" + 
					"									<div class='mini-middle'>" + 
					tempPerc+ 
					"									</div>" + 
					"								</div>";
			
			mainDivContent = mainDivContent + div;
			
			//reportIn = reportIn.replaceAll(skippedPercent,div);
		}
		
		mainDivContent = "<div class='miniBar' style='margin-bottom: " + divMiniBarMarginBottomPx + "px;'>" + mainDivContent + "</div>";
		
		return mainDivContent;
	}

	
	private static String constructQuickAccessDataElements() {
		
		String testNgSuite = Test.getTestNG_SuiteName();
		
		TestNGSuite_POJO suite = new TestNGSuite_POJO(testNgSuite);
		
		String testNgTest = Test.getTestNG_TestName();
		
		TestNGTests_POJO testNGTests = suite.addTests(testNgTest);
		
		for (ArrayList<Test> test : listOfList) {
			
			String testClass=test.get(0).getClassName();
			TestNGClass_POJO testNGClass = testNGTests.containsClass(testClass);
			if(testNGClass==null) {
				testNGClass = testNGTests.addClass(testClass);
			}
			
			testNGClass.addMethod(test.get(0));
			
		}
		
		
		
		return generateHTML_QuickAccessDataElements(suite);
	}
	
	private static String generateHTML_QuickAccessDataElements(TestNGSuite_POJO suite) {
		// Constructing the HTML Hierarchy
		List<TestNGTests_POJO> testList = suite.getTestList();
		String testHtml = "<ol style='padding-left:20px;'>";
		
		for (TestNGTests_POJO test : testList) {
			testHtml = testHtml +  "<li class='add-custom-arrow' onclick='hideShowQuicViewNextList(this)'>" + test.getTestName() + "</li>";
			
			List<TestNGClass_POJO> classList = test.getClassList();
			String classHtml = "<ol style='padding-left:20px;'>";
			for (TestNGClass_POJO clas : classList) {
				classHtml = classHtml +  "<li class='add-custom-arrow' onclick='hideShowQuicViewNextList(this)'>" + clas.getClassName() + "</li>";
				
				List<TestNGMethods_POJO> methList = clas.getMethodsList();
				
				String methHtml = "<ol class = 'quickviewMethodList'>";
				
				for (TestNGMethods_POJO meth : methList) {
					
					methHtml = methHtml + "<li class='"+meth.getStatus()+"'>"  
					
							+ "<span onclick=\"window.location.href='#qa_"+meth.getMethodName()+ "'\" class='w3-bar-item w3-button' title='"+meth.getDesc()+"'>"
							+ meth.getDesc() + " | " + meth.getStatus()
							+ "<div class='set_time_sheduleds'>" + 
							"    <span class='btn_0 title-start-time-scenario'>"+meth.getStart()+"</span> / " + 
							"    <span class='btn_0 title-end-time-scenario'>"+meth.getEnd()+"</span> / " + 
							"     <span class='btn_0 title-exec-time-scenario'>"+meth.getElapsed()+"</span>" + 
							"    <span class='btn_0 group'>"+meth.getGroup()+"</span>" + 
							"</div>"
							+ "</span>"
							+ "</li>";
					
				}
				methHtml = methHtml + "</ol>";
				classHtml = classHtml + methHtml;
			}
			classHtml = classHtml +  "</ol>";
			testHtml = testHtml + classHtml;
		}
		testHtml = testHtml + "</ol>";
		
		String elements = "<div class='sidenavItems'"
				+ "<ol>"
				+ "<li>"
				+ suite.getSuiteName()
				+ testHtml
				+ "</li>"
				+ "</ol>"
				+ "</div>";
		return elements;
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
			return 	"<div class='title-Movie'>"+"<a href='"+errorScreenshotURL+"' target ='_blank'>MOVIE</a>"+"</div>";
		}
		return 	"<div class='steps-step-snap'>"+"<a href='"+errorScreenshotURL+"' target ='_blank'><img src='"+errorScreenshotURL+"' style='max-width: 250px;max-height: 250px;'></a>"+"</div>";
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

		updateNODEStatusBasedOnSteps(STATUS.INFO.value);
		updateNODEStatusBasedOnSteps(STATUS.PASS.value);
		updateNODEStatusBasedOnSteps(STATUS.WARNING.value);
		updateNODEStatusBasedOnSteps(STATUS.SKIP.value);
		updateNODEStatusBasedOnSteps(STATUS.ERROR.value);
		updateNODEStatusBasedOnSteps(STATUS.FAIL.value);
		updateNODEStatusBasedOnSteps(STATUS.FATAL.value);

		updateScenarioStatusBasedOnSteps(STATUS.WARNING.value);
		updateScenarioStatusBasedOnSteps(STATUS.SKIP.value);
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
		
		long totalCount = passCount+failCount+skipCount+errorCount+fatalCount+warningCount;
		
		Test.setPassedScenario(passCount);
		Test.setFailedScenario(failCount);
		Test.setSkippedScenario(skipCount);
		Test.setErrorScenario(errorCount);
		Test.setFatalScenario(fatalCount);
		Test.setWarningScenario(warningCount);
		
		Test.setPassingPercent(((double)passCount+(double)warningCount)*100/(double)totalCount);
		Test.setFailurePercent(((double)failCount+(double)errorCount+(double)fatalCount)*100/(double)totalCount);
		Test.setSkipPercent((double)skipCount*100/(double)totalCount);

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

	private static long getStepCountBasedOnStatus(String status) {
		long count=0;
		for (int i = 0; i < listOfList.size(); i++) {
			List<Test> stepsList=listOfList.get(i);
			for (int j = 1; j < stepsList.size(); j++) {
				List<Test> subStepsList=stepsList.get(j).getList();
				int size=subStepsList.size();
				if (size>0) {
					for (int k = 0; k < size; k++) {
						String subStepStatus=subStepsList.get(k).getStatus();
						if (subStepStatus.equalsIgnoreCase(status)) {
							count++;
						}
					}
				}else{
					String stepStatus=stepsList.get(j).getStatus();
					if (stepStatus.equalsIgnoreCase(status)) {
						count++;
					}
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
		
	
	private static void updateScenarioStatusBasedOnSteps(String status){
		for (int i = 0; i < listOfList.size(); i++) {
			List<Test> stepsList=listOfList.get(i);
			for (int j = 1; j < stepsList.size(); j++) {
				String stepStatus=stepsList.get(j).getStatus();
				if (stepStatus.equalsIgnoreCase(status)) {
					stepsList.get(0).setStatus(status);
					break;
				}
			}
		}
	}

	private static synchronized String getStyledStepDesc_BasedOnStatus(String status){
		String font_color_Class="none";
		//Setting up the font color based on status value
		if(status!=null){
			if(status.equalsIgnoreCase(STATUS.INFO.value)) {
				font_color_Class="info";
			}else if(status.equalsIgnoreCase(STATUS.FATAL.value)) {
				font_color_Class="fatal";
			}else if(status.equalsIgnoreCase(STATUS.FAIL.value)) {
				font_color_Class="fail";
			}else if(status.equalsIgnoreCase(STATUS.WARNING.value)) {
				font_color_Class="warning";
			}else if(status.equalsIgnoreCase(STATUS.ERROR.value)) {
				font_color_Class="error";
			}
		}
		return font_color_Class; 
	}

}
