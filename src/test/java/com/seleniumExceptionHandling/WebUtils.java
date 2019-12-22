package com.seleniumExceptionHandling;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.configData_Util.Util;
import com.customReporting.CustomReporter;
import com.customReporting.snapshot.SnapshotManager;
import com.driverManager.DriverFactory;

class WebUtils {

	/**
	 * @author shailendra.rajawat 
	 * @return webelement object
	 * @param element WebElement or By object
	 * This method takes WebElement or By object as
	 *         an input and then returns a WebElement object
	 */
	WebElement getWebElement(Object element) {
		WebElement elem = null;
		WebDriver driver = DriverFactory.getDriver();
		if (element instanceof By) {
			By byObj = (By) element;
			elem = driver.findElement(byObj);
		} else if (element instanceof WebElement) {
			elem = (WebElement) element;
		}
		return elem;
	}

	/**
	 * From multiple list of WebElement, when only one is Clickable/visible. This method will 
	 * return that element.  
	 * 
	 * Lets say from the list first few are not Clickable/visible, then this method will wait 
	 * for 0 second for each non-interactable element. and once the one element is found 
	 * which is clickable/visible. It will return that
	 * 
	 * Note: This method will return the "first" interactable element.
	 * 
	 * @param element Can be WebElement/By Object, whose locator is finding multiple 
	 * 			elements on the web page. and only one is displayed at a time
	 * 
	 * @return WebElement which are clickable/visible and with which Interaction is possible
	 *
	 */
	public WebElement getInteractableWebElementFromList(Object element) {
		WebElement elem = null;
		try{
			// Getting byObject from the passed element object, so that we can
			// apply findElements method on it
			By byObj = null;
			WebDriver driver = DriverFactory.getDriver();
			if (element instanceof By) {
				byObj = (By) element;
			} else if (element instanceof WebElement) {
				byObj =  getByObjectFromWebElement((WebElement) element);
			}

			/* Applying the findElements method, to get all the web elements who
			 * have the same By Object
			 */
			List<WebElement> list=driver.findElements(byObj);
			WebDriverWait wait=new WebDriverWait(driver, 0);
			
			// Looping through all web element objects to get the first
			// interactive element, by applying the checks 
			for (WebElement webElement : list) {
				
				//Checking WebElement is clickable or not
				try{
					elem=wait.until(ExpectedConditions.elementToBeClickable(webElement));
					break;
				}catch (Exception e) {}

				// Checking that an element, known to be present on the
				// DOM of a page, isvisible. Visibility means that the element
				// is not only displayed but also has a height and width that is
				// greater than 0.
				try{
					elem=wait.until(ExpectedConditions.visibilityOf(webElement));
					break;
				}catch (Exception e) {}
				
				// Checking that an element has a height and width that is
				// greater than 0.
				try{
					int height = webElement.getSize().getHeight();
					int width = webElement.getSize().getWidth();
					if(height > 0 && width > 0){
						elem = webElement;
						break;
					}
				}catch (Exception e) {}
				
			}
			
			// Even after performing the above clickability and visibility
			// checks, due to some issue elem can be null, so in that case we
			// are throwing exception with all relevant details, this will 
			// enhance the traceability of the actual issue 
			if(elem == null){
				new CustomExceptionHandler(new NullPointerException(),
						"getInteractableWebElementFromList() method: Element is null, By Object [" + byObj
								+ "] and size of webElements list [" + list.size() + "]");
			}
			
		}catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return elem;
	}

	public By getByObjectFromWebElement(Object element) {
		
		if (element instanceof By) {
			return (By)element;
		}
		
		By byObj=null;
		try{
			String webElementInString=element.toString();
			int locatorStartIndex = -1, locatorEndIndex = -1, expressionStartIndex = -1, expressionEndIndex = -1;
			if(webElementInString.contains("Proxy element for")){
				// Proxy element for: DefaultElementLocator 'By.cssSelector: button[title='Close'][class*='fade-button']'
				locatorStartIndex = webElementInString.indexOf("By")+3;
				locatorEndIndex = webElementInString.indexOf(":",locatorStartIndex);
				expressionStartIndex = locatorEndIndex+1;
				expressionEndIndex = webElementInString.length()-1;
			}else{
				//[[ChromeDriver: chrome on XP (d20ae2a31239671c5aad2f7789dc343e)] -> id: P22_USERNAME]
				//[[ChromeDriver: chrome on XP (d20ae2a31239671c5aad2f7789dc343e)] -> xpath: //a[.='Login'] | //button[.='Login']]
				//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> class name: container]
				//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> css selector: button]
				//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> link text: Some Text In A Link]
				//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> name: P22_USERNAME]
				//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> partial link text: link]
				//[[ChromeDriver: chrome on XP (900e5a8b2929a57615ca5873a347116a)] -> tag name: input]
				
				locatorStartIndex = webElementInString.indexOf("->")+2;
				locatorEndIndex = webElementInString.indexOf(":",locatorStartIndex);
				expressionStartIndex = locatorEndIndex+1;
				expressionEndIndex = webElementInString.length()-1;
			}
			String LOCATOR=webElementInString.substring(locatorStartIndex, locatorEndIndex).trim().toLowerCase().replaceAll(" ", "");
			String EXPRESSION=webElementInString.substring(expressionStartIndex, expressionEndIndex).trim();
			
			switch (LOCATOR) {
			case "xpath":
				byObj=By.xpath(EXPRESSION);
				break;
			case "id":
				byObj=By.id(EXPRESSION);
				break;
			case "classname":
				byObj=By.className(EXPRESSION);
				break;
			case "cssselector":
				byObj=By.cssSelector(EXPRESSION);
				break;
			case "linktext":
				byObj=By.linkText(EXPRESSION);
				break;
			case "name":
				byObj=By.name(EXPRESSION);
				break;
			case "partiallinktext":
				byObj=By.partialLinkText(EXPRESSION);
				break;
			case "tagname":
				byObj=By.tagName(EXPRESSION);
				break;
			default:
				break;
			}
		}catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return byObj;
	}

	/**
	 * Pause the current Thread execution.
	 * 
	 * @param seconds
	 *            The double seconds to pause the thread for fraction values
	 *            like 0.5 or 0.75 seconds.
	 */
	public void wait(double seconds) {
		int val = (int) (seconds * 1000);
		ThreadSleep(val);
	}

	/**
	 * Pause the current Thread execution.
	 * 
	 * @param seconds
	 *            The int seconds to pause the thread for whole number values
	 *            like 1 or 5 seconds.
	 */
	public void wait(int seconds) {
		int val = seconds * 1000;
		ThreadSleep(val);
	}

	private void ThreadSleep(int microseconds) {
		try {
			Thread.sleep(microseconds);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}





	/**
	 * An expectation for checking non-present of elements based on count of
	 * with given locator on page, returns null if the object is not found
	 * 
	 * @param byObj
	 *            used to find the element
	 * @param timeOutInSeconds
	 *            The timeout in seconds when an expectation is called
	 * @return The first matching element on the current page or NULL if number
	 *         of elements are not more than 0 within time out.
	 */
	public WebElement getDeletedDynamicElement(By byObj, long timeOutInSeconds) {
		WebElement temp = null;
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebDriver driver = DriverFactory.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait = new WebDriverWait(driver, timeOutInSeconds);
			temp = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(byObj, 0)).get(0);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			// new CustomExceptionHandler(e);
		}
		return temp;
	}

	/**
	 * Overloaded method of getDynamicElement(By byObj,boolean trueForSnapshot),
	 * Will not take snapshot
	 *
	 * @param byObj
	 *            The By Object, The locating mechanism
	 * @return The first matching element on the current page
	 * 
	 */
	public WebElement getDynamicElement(By byObj) {
		return getDynamicElement(byObj, "", false);
	}

	/**
	 * Overloaded method of getDynamicElement(By byObj,boolean trueForSnapshot),
	 * Will not take snapshot
	 *
	 * @param byObj
	 *            The By Object, The locating mechanism
	 * @return The first matching element on the current page
	 * 
	 */
	public WebElement getDynamicElement(Object parentElem, By childByObj) {
		return getDynamicElement(parentElem, childByObj,"", false);
	}

	/**
	 * Find the child element of the passed Web Element, Also Takes snapshot
	 * if parameter is set true 
	 * @param elem
	 *            The WebElement Object, The WebElement whose child is required 
	 * @param byObj
	 *            The By Object, The locating mechanism of child Object
	 * @param desc
	 *            element description to be display in report
	 * @param trueForSnapshot
	 *            pass true if you want to take snapshot
	 * @return The first matching child element of the passed parent WebElement
	 * @throws NoSuchElementException
	 *             If no matching elements are found
	 */
	public WebElement getDynamicElement(Object parentElem, By childByObj, String desc, boolean trueForSnapshot) {
		boolean reportFlag = false;
		WebElement temp=null;
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement parentWebElem=getWebElement(parentElem);
			temp=parentWebElem.findElement(childByObj);
			reportFlag = true;
			if (trueForSnapshot) {
				SnapshotManager.takeSnapShot(methodName);
			}
		} catch (Exception e) {
			CustomReporter.report(STATUS.ERROR, "Element with locator ["+childByObj+"] is Not found");
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (reportFlag) {
					CustomReporter.report(STATUS.PASS, desc + " is displayed");
				} else {
					CustomReporter.report(STATUS.FAIL, desc + " is NOT displayed");
				}
			}
		}
		return temp;
	}
	/**
	 * Find the first matching element on the current page, Also Takes snapshot
	 * if parameter is set true This method is affected by the
	 * '{@link Constant}.wait' times in force at the time of execution. It will
	 * return a matching element, or try again repeatedly until the configured
	 * timeout is reached.
	 *
	 * @param byObj
	 *            The By Object, The locating mechanism
	 * @param trueForSnapshot
	 *            pass true if you want to take snapshot
	 * @return The first matching element on the current page
	 * @throws NoSuchElementException
	 *             If no matching elements are found
	 */
	public WebElement getDynamicElement(By byObj, boolean trueForWholePageSnapshot) {
		return getDynamicElement(byObj, "", trueForWholePageSnapshot);
	}

	/**
	 * Find the first matching element on the current page, Also Takes snapshot
	 * if parameter is set true This method is affected by the
	 * '{@link Constant}.wait' times in force at the time of execution. It will
	 * return a matching element, or try again repeatedly until the configured
	 * timeout is reached.
	 *
	 * @param byObj
	 *            The By Object, The locating mechanism
	 * @param desc
	 *            element description to be display in report
	 * @return The first matching element on the current page
	 * @throws NoSuchElementException
	 *             If no matching elements are found
	 */
	public WebElement getDynamicElement(By byObj, String desc) {
		return getDynamicElement(byObj, desc, true);
	}

	/**
	 * Find the first matching element on the current page, Also Takes snapshot
	 * if parameter is set true This method is affected by the
	 * '{@link Constant}.wait' times in force at the time of execution. It will
	 * return a matching element, or try again repeatedly until the configured
	 * timeout is reached.
	 *
	 * @param byObj
	 *            The By Object, The locating mechanism
	 * @param desc
	 *            element description to be display in report
	 * @param trueForSnapshot
	 *            pass true if you want to take snapshot
	 * @return The first matching element on the current page
	 * @throws NoSuchElementException
	 *             If no matching elements are found
	 */
	public WebElement getDynamicElement(By byObj, String desc, boolean trueForSnapshot) {
		WebElement temp = null;
		boolean reportFlag = false;
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebDriver driver = DriverFactory.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, Constant.wait);
			temp = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(byObj, 0)).get(0);
			reportFlag = true;
			if (trueForSnapshot) {
				SnapshotManager.takeSnapShot(methodName);
			}
		} catch (Exception e) {
			CustomReporter.report(STATUS.ERROR, "Element with locator ["+byObj+"] is Not found");
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (reportFlag) {
					CustomReporter.report(STATUS.PASS, desc + " is displayed");
				} else {
					CustomReporter.report(STATUS.FAIL, desc + " is NOT displayed");
				}
			}
		}
		return temp;
	}


	/**
	 * Find all elements within the current page using the given mechanism based
	 * on count of with given locator. This method is affected by the
	 * '{@link Constant}.wait' times in force at the time of execution. When
	 * waiting, this method will return as soon as there are more than 0 items
	 * in the found collection, or will return an empty list if the timeout is
	 * reached.
	 *
	 * @param byObj
	 *            The locating mechanism to use
	 * @return A list of all {@link WebElement}s, or an empty list if nothing
	 *         matches
	 * @see org.openqa.selenium.By
	 * @see org.openqa.selenium.support.ui.WebDriverWait
	 */
	public List<WebElement> getDynamicElements(By byObj) {
		List<WebElement> temp = new ArrayList<WebElement>();
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebDriver driver = DriverFactory.getDriver();
			temp = driver.findElements(byObj);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
		return temp;
	}


	public void doubleClick(WebElement element) {
		doubleClick(element, "");
	}

	public void doubleClick(WebElement element,String desc) {
		boolean bool = false;
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
			SnapshotManager.takeSnapShot(methodName);
			Actions build=new Actions(DriverFactory.getDriver()) ;
			build.moveToElement(elem).doubleClick().build().perform();
			bool = true;
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, "'" + desc + "' is clicked");
				} else {
					CustomReporter.report(STATUS.FAIL, "'" + desc + "' object is NOT clicked, due to some exception");
				}
			}
		}
	}
	/**
	 * Will perform java script click on the passed element Object
	 * 
	 * @param element
	 *            A WebElement/By object
	 * 
	 */
	public void javaScript_Click(Object element) {
		javaScript_Click(element,"");
	}

	public void javaScript_Click(Object element,String desc) {
		boolean bool=false;
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", elem);
			SnapshotManager.takeSnapShot(methodName);
			bool=true;
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}finally {
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, desc +" is clicked");
				} else {
					CustomReporter.report(STATUS.FAIL, desc +" is NOT clicked");
				}

			}
		}

	}

	/**
	 * Bring the Element into Screen BOTTOM view only.
	 * 
	 * @param element
	 *            A WebElement/By object
	 */
	public void javaScript_ScrollIntoBOTTOMView(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(false);", elem);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}
	
	/**
	 * javaScript_ScrollIntoBOTTOMView_AndHighlight
	 * Bring the Element into Screen BOTTOM view, and also changes its background to
	 * Yellow
	 * 
	 * @param element
	 *            A WebElement/By object
	 */
	
	public void javaScript_ScrollIntoMIDDLEView_AndHighlight(Object element) {
		try {
			WebElement elem = getWebElement(element);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(false);",elem);//Scroll to bottom visible area
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.scrollTo(0,0);");//Scroll window to top
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript(""
					+ "var ele=arguments[0].getBoundingClientRect().top;"
					+ "var win=window.innerHeight;"
					+ "window.scrollTo(0,(ele-win/1.2));"	//Scroll window to Object so that it comes just above from bottom part
					+ "",elem);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("var st = arguments[0].style;"
					+ "st.color = 'red';"
					+ "st.background = 'yellow';"
					+ "st.borderBottom = '1px solid red';"
					+ "st.borderTop = '1px solid red';", elem);
		} catch (Exception e) {
			//new CustomExceptionHandler(e);
			CustomReporter.report(STATUS.WARNING, "javaScript_ScrollIntoMIDDLEView_AndHighlight failed to execute on "+getByObjectFromWebElement(element));
		}
	}


	
	/**
	 * Bring the Element into Screen BOTTOM view, and also changes its background to
	 * Yellow
	 * 
	 * @param element
	 *            A WebElement/By object
	 */
	public void javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(Object element) {
		javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(element, null);
	}
	public void javaScript_ScrollIntoBOTTOMView_AndHighlight_ThenClick(Object element,String desc) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {

			if (desc!=null && !desc.equals("")) {
				CustomReporter.report(STATUS.INFO, "Clicking on "+desc);
			}

			WebElement elem = getWebElement(element);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(false);", elem);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("var st = arguments[0].style;"
					+ "st.color = 'red';"
					+ "st.background = 'yellow';"
					+ "st.borderBottom = '1px solid red';"
					+ "st.borderTop = '1px solid red';", elem);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click()", elem);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}


	/**
	 * Bring the Element into Screen TOP view, and also changes its background to
	 * Yellow
	 * 
	 * @param element
	 *            A WebElement/By object
	 */
	public void javaScript_ScrollIntoTOPView_AndHighlight(Object element) {
		try {
			WebElement elem = getWebElement(element);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", elem);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("var st = arguments[0].style;"
					+ "st.color = 'red';"
					+ "st.background = 'yellow';"
					+ "st.borderBottom = '1px solid red';"
					+ "st.borderTop = '1px solid red';", elem);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}


	/**
	 * Bring the Element into Screen TOP view only.
	 * 
	 * @param element
	 *            A WebElement/By object
	 */
	public void javaScript_ScrollIntoTOPView(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", elem);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Bring the Element into Screen TOP view, and also changes its background to
	 * Yellow
	 * 
	 * @param element
	 *            A WebElement/By object
	 */
	public void javaScript_ScrollIntoTOPView_AndHighlight_ThenClick(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", elem);
			((JavascriptExecutor) DriverFactory.getDriver()).executeScript("var st = arguments[0].style;"
					+ "st.color = 'red';"
					+ "st.background = 'yellow';"
					+ "st.borderBottom = '1px solid red';"
					+ "st.borderTop = '1px solid red';", elem);((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click()", elem);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

}
