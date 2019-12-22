  package or.common;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.CustomExceptionHandler;
import com.seleniumExceptionHandling.SeleniumMethods;

public class Navigator {

	private SeleniumMethods com;
	
	public Navigator() {
		com = new SeleniumMethods();
	}
	
	/**
	 * This method will click on all the passed links one by one and then checks the
	 * last opened page url is what we have expected.
	 * 
	 * @author shailendra
	 */
	public Navigator traverseMenu_VerifyPageTitle(String PagesTitle, Object... elementArr) {
		Perform_traverseMenu_VerifyPageTitle( PagesTitle, elementArr);
		return this;
	}
	
	private static synchronized boolean Perform_traverseMenu_VerifyPageTitle(String PagesTitle,
			Object... elementArr) {
		CustomReporter.createNode("Navigating to ["+PagesTitle+"] page");
		SeleniumMethods com=new SeleniumMethods();
		HCMCommon comm = new HCMCommon();
		String linkTexts="";
		/***Solving the recurring, no such element: Unable to locate element*/
		if (elementArr.length>0) {
			for (int i = 0; i < elementArr.length; i++) {
				
				String xpath="";
				if (elementArr[i] instanceof WebElement) {
					String webElementInString=elementArr[i].toString();
					int locatorStartIndex=webElementInString.indexOf("->")+2;
					int locatorEndIndex=webElementInString.indexOf(":",locatorStartIndex);
					int expressionStartIndex=locatorEndIndex+1;
					int expressionEndIndex=webElementInString.length()-1;
					
					//[[ChromeDriver: chrome on XP (d20ae2a31239671c5aad2f7789dc343e)] -> id: P22_USERNAME]
					//[[ChromeDriver: chrome on XP (d20ae2a31239671c5aad2f7789dc343e)] -> xpath: //a[.='Login'] | //button[.='Login']]
					//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> class name: container]
					//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> css selector: button]
					//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> link text: Some Text In A Link]
					//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> name: P22_USERNAME]
					//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> partial link text: link]
					//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> tag name: input]

					String LOCATOR=webElementInString.substring(locatorStartIndex, locatorEndIndex).trim().toLowerCase();
					String EXPRESSION=webElementInString.substring(expressionStartIndex, expressionEndIndex).trim();
					switch (LOCATOR) {
					case "xpath":
						xpath=EXPRESSION;
						break;
					case "id":
						xpath="//*[@id='"+EXPRESSION+"']";
						break;
					case "class name":
						xpath="//*[@class='"+EXPRESSION+"']";
						break;
					case "css selector":
						new CustomExceptionHandler(new InvalidSelectorException("ONLY XPATH IS ALLOWED"));
						break;
					case "link text":
						xpath="//a[normalize-space()='"+EXPRESSION+"']";
						break;
					case "name":
						new CustomExceptionHandler(new InvalidSelectorException("ONLY XPATH IS ALLOWED"));
						break;
					case "partial link text":
						xpath="//a[contains(normalize-space(),'"+EXPRESSION+"')]";
						break;
					case "tag name":
						new CustomExceptionHandler(new InvalidSelectorException("ONLY XPATH IS ALLOWED"));
						break;
					default:
						break;
					}
				}else{
					xpath=elementArr[i].toString();
					xpath=xpath.substring(xpath.indexOf(" ")+1, xpath.length());
				}
				//CustomReporter.report(Status.INFO, "IDENTIFIED XPATH : "+xpath);
				//String currentlinkText=xpath.substring(xpath.indexOf("'")+1, xpath.lastIndexOf("'"));
				linkTexts=linkTexts+" -> "+xpath;
				try{
					
					WebDriver driver=DriverFactory.getDriver();
					
					// Firefox is not waiting for page to load, so what I am doing here is
					// I will wait for the first element to be available for click, from the passed hierarchy 
					// com.waitForElementTobe_Clickable(elementArr[0]);
					
					((JavascriptExecutor)driver).executeScript("document.evaluate(\""+xpath+"\",document,null,XPathResult.ORDERED_NODE_ITERATOR_TYPE,null).iterateNext().focus()");
					com.wait(1);
					((JavascriptExecutor)driver).executeScript("document.evaluate(\""+xpath+"\",document,null,XPathResult.ORDERED_NODE_ITERATOR_TYPE,null).iterateNext().blur()");
					com.click_UsingAction(By.xpath(xpath));
					//Actions act=new Actions(driver);
					//act.click(driver.findElement(By.xpath(xpath))).build().perform();
					//.click();
					com.wait(1);
					com.waitForElementsTobe_NotVisible(comm.spinnerLocator);
				}catch(Exception ee){
					new CustomExceptionHandler(ee);
				}
			}
		}
		
		String styledLinkText="<b style='font-size: small;font-family: monospace;'>"+linkTexts+"</b>";

		
		return verifyPageUrlContains(PagesTitle, styledLinkText);

	} 
	
	public boolean navigateToPageNumber(String title, int pageNum) {
		String str = com.getCurrentUrl();
		int firstOccurence = str.indexOf(":", 6);
		int secondOccurence = str.indexOf(":", firstOccurence + 1);
		str = str.replace(":" + str.substring(firstOccurence + 1, secondOccurence) + ":", ":" + pageNum + ":");
		com.navigateTo(str);
		if (com.getTitle().equalsIgnoreCase(title)) {
			CustomReporter.report(STATUS.PASS, "'<span style='color: black;font-style: italic;font-weight: bold;'>"+ title + "</span>' page is displayed <br/>"+com.getCurrentUrl());
			return true;
		}

		CustomReporter.report(STATUS.FAIL, "'<span style='color: black;font-style: italic;font-weight: bold;'>"+ title + "</span>' page is NOT displayed but '<b>"+com.getTitle()+"</b>' is getting displayed <br/>"+com.getCurrentUrl());
		Assert.fail("navigateToPageNumber(String title, int pageNum) method failed for ["+ title +", "+ pageNum + "]");
		return false;
	}

	public static void main(String[] args) {
		String str = "http://10.184.40.115/pls/apex/f?p=200:252:5536814047963::NO:::";
		int firstOccurence = str.indexOf(":", 6);
		int secondOccurence = str.indexOf(":", firstOccurence + 1);
		str = str.replace(":" + str.substring(firstOccurence + 1, secondOccurence) + ":", ":22:");
		System.out.println(str);
	}

	/**
	 * Use this method to navigate to Admin Page
	 * @param passKey The secret key of the user
	 * */
	public Navigator toAdminDashboard(String passKey) {
		Dashboard d = new Dashboard();
		HCMCommon comm = new HCMCommon();
		
		com.click(d.link_Admin);
		com.waitForElementTobe_Visible(d.text_Pin);
		com.sendKeys(d.text_Pin, passKey, Keys.ENTER);
		com.wait(2);
		com.waitForElementsTobe_NotVisible(comm.spinnerLocator);
		verifyPageUrlContains("admin/user/dashboard", "link_Admin");
		return this;
	}
	
	
	/**
	 * Use this method to navigate to Fms Page
	 * @param passKey The secret key of the user
	 * */
	public Navigator toFmsDashboard(String passKey) {
		Dashboard d = new Dashboard();
		HCMCommon comm = new HCMCommon();
		
		com.click(d.link_FMS);
		// PIN POPUP IS NOT VISIBLE NOW
		/*
		 * com.waitForElementTobe_Visible(d.text_Pin); 
		 * com.sendKeys(d.text_Pin, passKey,Keys.ENTER); 
		 * com.wait(2);
		 */
		com.waitForElementsTobe_NotVisible(comm.spinnerLocator);
		verifyPageUrlContains("admin/fms/dashboard", "link_Fms");
		return this;
		
	}
	
	
	
	/**
	 * Use this method to verify the page url and to run the reporter statements
	 * */
	private static boolean verifyPageUrlContains(String expectedPageTitle, String styledLinkText) {
		SeleniumMethods com = new SeleniumMethods();
		if(com.getCurrentUrl().contains(expectedPageTitle)){
			CustomReporter.report(STATUS.PASS, styledLinkText+"<br/>'<span style='color: black;font-style: italic;font-weight: bold;'>"+ expectedPageTitle + "</span>' page is displayed <br/>"+com.getCurrentUrl());
			return true;
		} else {
			CustomReporter.report(STATUS.FAIL, styledLinkText+"<br/>'<span style='color: black;font-style: italic;font-weight: bold;'>"+ expectedPageTitle + "</span>' page is NOT displayed but '<b>"+com.getTitle()+"</b>' is getting displayed <br/>"+com.getCurrentUrl());
			//Assert.fail("traverseMenu_VerifyPageTitle(Object, PagesTitle, Object...) method failed for ["+ PagesTitle + "]");
			return false;
		}
	}

}
