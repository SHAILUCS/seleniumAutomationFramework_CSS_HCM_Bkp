package com.seleniumExceptionHandling;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.customReporting.snapshot.SnapshotManager;
import com.driverManager.DriverFactory;

/**
 * This class is holding all the methods that will perform operation on WebPage
 * using Selenium API. Also this class provide following additional
 * functionalities - Exception handling - Snapshots capturing - Reporting
 * 
 * Feel free to add new methods if not found here. Please follow the similar
 * structure of existing methods.
 * 
 * @author shailendra.rajawat
 * 
 */
public class SeleniumMethods extends SelectCustom {

	/**
	 * Clicks on the checkbox to select it only if it is not already checked
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * 
	 * @return True if the element is successfully selected
	 */
	public boolean check_Checkbox(Object element) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (!isSelected(elem)) {
				javaScript_Click(elem);
			}
			bool = true;
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return bool;
	}

	/**
	 * Clicks on the checkbox to deselect/uncheck it only if it is already
	 * checked
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * 
	 * @return True if the element is successfully selected
	 */
	public boolean uncheck_Checkbox(Object element) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (isSelected(elem)) {
				javaScript_Click(elem);
			}
			bool = true;
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return bool;
	}

	/**
	 * Clicks on the checkbox to select it, then check isSelected for
	 * verification, Runs Report
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * @param objName
	 *            Name of the object to be display in report
	 * @return True if the element is successfully selected
	 */
	public boolean check_CheckboxAndConfirm(Object element, String objName) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			// elem.click();
			javaScript_Click(elem);
			if (elem.isSelected()) {
				bool = true;
				SnapshotManager.takeSnapShot(methodName);
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (bool) {
				CustomReporter.report(STATUS.PASS, "'" + objName + "' checkbox is successfully checked");
			} else {
				CustomReporter.report(STATUS.FAIL, "'" + objName + "' checkbox is NOT checked");
			}
		}

		return bool;
	}

	/**
	 * checks whether some value is filled in textbox or not, Also run reporter.
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * @param objName
	 *            Name of the Object, this will get printed in the report
	 * @return true if value attribute of textbox is not empty.
	 */
	public boolean checkBlank(Object element, String objName) {
		boolean bool = false;
		// String methodName = "checkBlank method";
		String val = "";
		try {
			WebElement elem = getWebElement(element);

			val = elem.getAttribute("value").trim();
			if (!"".equalsIgnoreCase(val)) {
				bool = true;
			}
			// SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (bool) {
				CustomReporter.report(STATUS.PASS, "Value: '" + val + "' is filled in \"" + objName +"\" field");
			} else {
				CustomReporter.report(STATUS.FAIL,  "Value: '" + val + "' is Not filled in \"" + objName +"\" field");
			}
		}
		return bool;
	}

	/**
	 * Click this element. If this causes a new page to load, you should discard
	 * all references to this element and any further operations performed on
	 * this element will throw a StaleElementReferenceException.
	 *
	 * Note that if click() is done by sending a native event (which is the
	 * default on most browsers/platforms) then the method will _not_ wait for
	 * the next page to load and the caller should verify that themselves.
	 *
	 * There are some preconditions for an element to be clicked. The element
	 * must be visible and it must have a height and width greater then 0.
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @return true if operation is successful
	 * @throws StaleElementReferenceException
	 *             If the element no longer exists as initially defined
	 */
	public boolean click(Object element) {
		return click(element, "");
	}

	/**
	 * Click this element. If this causes a new page to load, you should discard
	 * all references to this element and any further operations performed on
	 * this element will throw a StaleElementReferenceException.
	 *
	 * Note that if click() is done by sending a native event (which is the
	 * default on most browsers/platforms) then the method will _not_ wait for
	 * the next page to load and the caller should verify that themselves.
	 *
	 * There are some preconditions for an element to be clicked. The element
	 * must be visible and it must have a height and width greater then 0.
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param desc
	 *            element description to be display in report
	 * @return true if operation is successful
	 * @throws StaleElementReferenceException
	 *             If the element no longer exists as initially defined
	 */
	public boolean click(Object element, String desc) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
			SnapshotManager.takeSnapShot(methodName);
			elem.click();
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
		return bool;
	}

	public void click_UsingAction(Object element, String desc) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		boolean bool = false;
		try {
			WebElement elem = getWebElement(element);
			javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
			SnapshotManager.takeSnapShot(methodName);
			Actions build = new Actions(DriverFactory.getDriver());
			build.click(elem).build().perform();
			//build.moveToElement(elem).click().build().perform();
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

	public void contextClick_UsingAction(Object element) {
		 contextClick_UsingAction(element, "");
	}
	
	public void contextClick_UsingAction(Object element, String desc) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		boolean bool = false;
		try {
			WebElement elem = getWebElement(element);
			javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
			SnapshotManager.takeSnapShot(methodName);
			Actions build = new Actions(DriverFactory.getDriver());
			build.contextClick(elem).build().perform();
			//build.moveToElement(elem).click().build().perform();
			bool = true;
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, "'" + desc + "' is context clicked");
				} else {
					CustomReporter.report(STATUS.FAIL, "'" + desc + "' object is NOT context clicked, due to some exception");
				}
			}
		}
	}

	public void click_UsingAction(Object element) {
		click_UsingAction(element, "");
	}

	/**
	 * UNDER CONSTRUCTION, haven't encountered any scenario which require this
	 * method.
	 *
	 * @return boolean.
	 */
	public boolean clickToAvoidStaleElementException(int timeout) {
		boolean bool = false;
		/*
		 * new WebDriverWait(driver, timeout)
		 * .ignoring(StaleElementReferenceException.class) .until(new
		 * Predicate<WebDriver>() {
		 * 
		 * @Override public boolean apply(@Nullable WebDriver driver) {
		 * driver.findElement(By.id("checkoutLink")).click(); return true; } });
		 */
		return bool;
	}

	/**
	 * Close the current Thread's "BaseTest.getDriver()" Driver window, quitting
	 * the browser if it's the last window currently open.
	 */
	public void close() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			SnapshotManager.takeSnapShot(methodName);
			DriverFactory.getDriver().close();
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Switches to the currently active modal dialog for this particular driver
	 * instance, accept/reject it and returns the text displayed on the alert.
	 *
	 * @param accept
	 *            pass true for clicking Ok/Yes on the alert
	 * @return String displayed on the alert dialog.
	 * @throws NoAlertPresentException
	 *             If the dialog cannot be found
	 */
	public String closeAlertAndGetItsText(boolean accept) {
		String alertText = null;
		try {
			WebDriver driver = DriverFactory.getDriver();
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
			if (accept) {
				alert.accept();
			} else {
				alert.dismiss();
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return alertText;
	}

	/**
	 * Load a new web page in the current browser window. This is done using an
	 * HTTP GET operation, and the method will block until the load is complete.
	 * This will follow redirects issued either by the server or as a
	 * meta-redirect from within the returned HTML. Should a meta-redirect
	 * "rest" for any duration of time, it is best to wait until this timeout is
	 * over, since should the underlying page change whilst your test is
	 * executing the results of future calls against this interface will be
	 * against the freshly loaded page. Synonym for
	 * {@link org.openqa.selenium.WebDriver.Navigation#to(String)}.
	 *
	 * @param url
	 *            The String URL to load. It is best to use a fully qualified
	 *            URL
	 */
	public void get(String url) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			DriverFactory.getDriver().get(url);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Get the value of the given attribute of the element. Will return the
	 * current value, even if this has been modified after the page has been
	 * loaded.
	 *
	 * <p>
	 * More exactly, this method will return the value of the property with the
	 * given name, if it exists. If it does not, then the value of the attribute
	 * with the given name is returned. If neither exists, null is returned.
	 *
	 * <p>
	 * The "style" attribute is converted as best can be to a text
	 * representation with a trailing semi-colon.
	 *
	 * <p>
	 * The following are deemed to be "boolean" attributes, and will return
	 * either "true" or null:
	 *
	 * <p>
	 * async, autofocus, autoplay, checked, compact, complete, controls,
	 * declare, defaultchecked, defaultselected, defer, disabled, draggable,
	 * ended, formnovalidate, hidden, indeterminate, iscontenteditable, ismap,
	 * itemscope, loop, multiple, muted, nohref, noresize, noshade, novalidate,
	 * nowrap, open, paused, pubdate, readonly, required, reversed, scoped,
	 * seamless, seeking, selected, truespeed, willvalidate
	 *
	 * <p>
	 * Finally, the following commonly mis-capitalized attribute/property names
	 * are evaluated as expected:
	 *
	 * <ul>
	 * <li>If the given name is "class", the "className" property is returned.
	 * <li>If the given name is "readonly", the "readOnly" property is returned.
	 * </ul>
	 *
	 * <i>Note:</i> The reason for this behavior is that users frequently
	 * confuse attributes and properties. If you need to do something more
	 * precise, e.g., refer to an attribute even when a property of the same
	 * name exists, then you should evaluate Javascript to obtain the result you
	 * desire.
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param name
	 *            The name of the attribute.
	 * @return The attribute/property's current value or null if the value is
	 *         not set.
	 */
	public String getAttribute(Object element, String name) {
		String val = null;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		if (name.toLowerCase().contains("innertext")) {
			val = getText(element);
		} else {
			try {
				WebElement elem = getWebElement(element);
				val = elem.getAttribute(name);
				SnapshotManager.takeSnapShot(methodName);
			} catch (Exception e) {
				new CustomExceptionHandler(e, element.toString() +" Attribute "+ name);
			}
		}
		return val;
	}

	/**
	 * Get a string representing the current URL that the browser is looking at.
	 *
	 * @return The String URL of the page currently loaded in the browser
	 */
	public String getCurrentUrl() {
		String currentURL = "";
		// StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		// String methodName=ste[1].getMethodName();
		try {
			currentURL = DriverFactory.getDriver().getCurrentUrl();
			// SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return currentURL;
	}

	/**
	 * Get the source of the last loaded page. If the page has been modified
	 * after loading (for example, by Javascript) there is no guarantee that the
	 * returned text is that of the modified page. Please consult the
	 * documentation of the particular driver being used to determine whether
	 * the returned text reflects the current state of the page or the text last
	 * sent by the web server. The page source returned is a representation of
	 * the underlying DOM: do not expect it to be formatted or escaped in the
	 * same way as the response sent from the web server. Think of it as an
	 * artist's impression.
	 *
	 * @return The source of the current Threads "BaseTest.getDriver()" browser
	 *         page
	 */
	public String getPageSource() {
		String pageSource = "";
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			pageSource = DriverFactory.getDriver().getPageSource();
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return pageSource;
	}

	/**
	 * Get the visible (i.e. not hidden by CSS) innerText of this element,
	 * including sub-elements, without any leading or trailing whitespace.
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return The innerText of this element.
	 */
	public String getText(Object element) {
		String str = "";
		/*
		 * StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		 * String methodName=ste[1].getMethodName();
		 */
		try {
			WebElement elem = getWebElement(element);
			str = elem.getText().trim();
			// javaScriptScrollIntoViewAndHighlight(element);
			// SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
		return str;
	}

	/**
	 * Returns the title of the current page.
	 *
	 * @return The title of the current page, with leading and trailing
	 *         whitespace stripped, or null if one is not already set
	 */
	public String getTitle() {
		String title = null;
		// String methodName = "getTitle method";
		try {
			WebDriver driver = DriverFactory.getDriver();
			title = driver.getTitle();
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return title;
	}

	/**
	 * Return an opaque handle to this window that uniquely identifies it within
	 * this driver instance. This can be used to switch to this window at a
	 * later date
	 *
	 * @return the current window handle
	 */
	public String getWindowHandle() {
		String currentWinHandle = "";
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			currentWinHandle = DriverFactory.getDriver().getWindowHandle();
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return currentWinHandle;
	}

	/**
	 * Return a set of window handles which can be used to iterate over all open
	 * windows of this WebDriver instance by passing them to
	 * {@link #switchTo()}.{@link Options#window()}
	 *
	 * @return A set of window handles which can be used to iterate over all
	 *         open windows.
	 */
	public Set<String> getWindowHandles() {
		Set<String> winHandles = null;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			winHandles = DriverFactory.getDriver().getWindowHandles();
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return winHandles;
	}

	/**
	 * Check for this particular driver instance if currently active modal
	 * dialog present.
	 *
	 * @return true if alert dialog is present
	 * @throws NoAlertPresentException
	 *             If the dialog cannot be found
	 */
	public boolean isAlertPresent() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		boolean bool = false;
		try {
			WebDriver driver = DriverFactory.getDriver();
			driver.switchTo().alert();
			SnapshotManager.takeSnapShot(methodName);
			bool = true;
		} catch (Exception e) {
		}
		return bool;

	}

	/**
	 * Is the element currently enabled or not? This will generally return true
	 * for everything but disabled input elements.
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * @return True if the element is enabled, false otherwise.
	 */
	public boolean isDisplayed(Object element) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (elem.isDisplayed()) {
				bool = true;
				SnapshotManager.takeSnapShot(methodName);
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return bool;
	}

	/**
	 * Overloaded method of isElementPresent(Object webelement_obj, String desc)
	 * Will NOT take Snapshot, Will NOT run Reporter
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return True if the element is enabled or is displayed.
	 * 
	 */
	public boolean isElementPresent(Object element) {
		return isElementPresent(element, "");

	}

	/**
	 * Is this element (Displayed || Enabled) or not? This method avoids the
	 * problem of having to parse an element's "style" attribute. Or Is the
	 * element currently enabled or not? This will generally return true for
	 * everything but disabled input elements. Will NOT take Snapshot, Will run
	 * Reporter
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param desc
	 *            element description to be display in report
	 * @return True if the element is enabled or is displayed.
	 * 
	 */
	public boolean isElementPresent(Object element, String desc) {
		boolean bool = false;
		boolean reportFlag = false;
		WebElement elem = null;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			elem = getWebElement(element);
			if (elem.isDisplayed() || elem.isEnabled()) {
				bool = true;
				reportFlag = true;
			}
			javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			bool = false;
			reportFlag = false;
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (reportFlag) {
					CustomReporter.report(STATUS.PASS, "'" + desc + "' is displayed");
				} else {
					CustomReporter.report(STATUS.FAIL, "'" + desc + "' is NOT displayed");
				}
			}
		}

		return bool;
	}

	/**
	 * Is the element currently enabled or not? This will generally return true
	 * for everything but disabled input elements.
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * @return True if the element is enabled, false otherwise.
	 */
	public boolean isEnabled(Object element) {
		return isEnabled(element, "");
	}

	/**
	 * Is the element currently enabled or not? This will generally return true
	 * for everything but disabled input elements.
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * @return True if the element is enabled, false otherwise.
	 */
	public boolean isEnabled(Object element, String desc) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (elem.isEnabled()) {
				bool = true;
				SnapshotManager.takeSnapShot(methodName);
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, "'" + desc + "' is displayed as enabled");
				} else {
					CustomReporter.report(STATUS.FAIL, "'" + desc + "' is NOT displayed as enabled");
				}
			}
		}
		return bool;
	}

	/**
	 * Determine whether or not this element is selected or not. This operation
	 * only applies to input elements such as checkboxes, options in a select
	 * and radio buttons.
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * @return True if the element is currently selected or checked, false
	 *         otherwise.
	 */
	public boolean isSelected(Object element) {
		return isSelected(element, "");
	}

	/**
	 * Determine whether or not this element is selected or not. This operation
	 * only applies to input elements such as checkboxes, options in a select
	 * and radio buttons.
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * @return True if the element is currently selected or checked, false
	 *         otherwise.
	 */
	public boolean isSelected(Object element, String desc) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (elem.isSelected()) {
				bool = true;
				SnapshotManager.takeSnapShot(methodName);
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, "'" + desc + "' is displayed as selected");
				} else {
					CustomReporter.report(STATUS.FAIL, "'" + desc + "' is NOT displayed as selected");
				}
			}
		}
		return bool;
	}

	/**
	 * Move back a single "item" in the browser's history.
	 */
	public void navigateBack() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			SnapshotManager.takeSnapShot(methodName);
			DriverFactory.getDriver().navigate().back();
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Move a single "item" forward in the browser's history. Does nothing if we
	 * are on the latest page viewed.
	 */
	public void navigateForward() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			SnapshotManager.takeSnapShot(methodName);
			DriverFactory.getDriver().navigate().forward();
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Load a new web page in the current browser window. This is done using an
	 * HTTP GET operation, and the method will block until the load is complete.
	 * This will follow redirects issued either by the server or as a
	 * meta-redirect from within the returned HTML. Should a meta-redirect
	 * "rest" for any duration of time, it is best to wait until this timeout is
	 * over, since should the underlying page change whilst your test is
	 * executing the results of future calls against this interface will be
	 * against the freshly loaded page.
	 *
	 * @param url
	 *            The URL to load. It is best to use a fully qualified URL
	 */
	public void navigateTo(String url) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			DriverFactory.getDriver().navigate().to(url);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Overloaded version of {@link #to(String)} that makes it easy to pass in a
	 * URL.
	 *
	 * @param url
	 *            java.net.URL to load.
	 */
	public void navigateTo(URL url) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			DriverFactory.getDriver().navigate().to(url);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Will click on the passed object then verify the title of the page, Will
	 * Run the reporter.
	 * 
	 * @param element
	 *            A WebElement/By object
	 * @param title
	 *            Expected page title string(pass exact value)
	 * @return true if title matched with the expected value
	 */
	public boolean navigateToAndVerifyPageTitle(Object element, String title) {
		CustomReporter.createNode("Navigating to ["+title+"] page");
		boolean bool = false;
		// if(isElementPresent(obj,"link for '"+desc+"' Page ")){
		click(element);
		if (verifyPageTitle(title, true)) {
			bool = true;
		}
		// }
		return bool;
	}

	/**
	 * Will click on the passed object then verify the URL of the page, Will
	 * Run the reporter.
	 * 
	 * @param element
	 *            A WebElement/By object
	 * @param title
	 *            Expected page title string(pass exact value)
	 * @return true if title matched with the expected value
	 */
	public boolean navigateToAndVerifyPageUrl(Object element, String title) {
		CustomReporter.createNode("Navigating to ["+title+"] page");
		boolean bool = false;
		// if(isElementPresent(obj,"link for '"+desc+"' Page ")){
		click(element);
		if (verifyPageUrl(title, true)) {
			bool = true;
		}
		// }
		return bool;
	}

	/**
	 * Call this method for performing the built sequence of steps.
	 */
	public void performAction(Action builtAction) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();

		try {
			builtAction.perform();
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Refresh the current page
	 */
	public void refresh() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			SnapshotManager.takeSnapShot(methodName);
			DriverFactory.getDriver().navigate().refresh();
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Use this method to simulate typing into an element, which may set its
	 * value.
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param desc
	 *            element description to be display in report
	 * @param keysToSend
	 *            character sequence to send to the element you can send
	 *            multiple comma separated values for e.g.
	 *            sendKeys(webElementobj,"HelloWorld", {@link Keys}.DOWN,
	 *            {@link Keys}.ENTER)
	 */
	public boolean sendKeys(Object element, CharSequence... keysToSend) {
		return sendKeys("", element, keysToSend);
	}

	/**
	 * Use this method to simulate typing into an element, which may set its
	 * value.
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param desc
	 *            element description to be display in report
	 * @param keysToSend
	 *            character sequence to send to the element you can send
	 *            multiple comma separated values for e.g.
	 *            sendKeys(webElementobj,"HelloWorld", {@link Keys}.DOWN,
	 *            {@link Keys}.ENTER)
	 */
	public boolean sendKeys(String desc, Object element, CharSequence... keysToSend) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			elem.clear();
			elem.sendKeys(keysToSend);
			bool = true;
			SnapshotManager.takeSnapShot(methodName);

		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				checkBlank(element, desc);
			}
		}
		return bool;
	}

	/**
	 * If this element is a text entry element, this will clear the value. Has
	 * no effect on other elements. Text entry elements are INPUT and TEXTAREA
	 * elements. Note that the events fired by this event may not be as you'd
	 * expect. In particular, we don't fire any keyboard or mouse events. If you
	 * want to ensure keyboard events are fired, consider using something like
	 * sendKeys(CharSequence) with the backspace key. To ensure you get a change
	 * event, consider following with a call to sendKeys(CharSequence) with the
	 * tab key.
	 * 
	 */
	public void clear(Object element) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			elem.clear();
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Switches to the element that currently has focus within the document
	 * currently "switched to", or the body element if this cannot be detected.
	 * This matches the semantics of calling "document.activeElement" in
	 * Javascript.
	 * 
	 * @return The WebElement with focus, or the body element if no element with
	 *         focus can be detected.
	 */
	public WebElement switchTo_ActiveElement() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebElement obj = null;
		try {
			obj = DriverFactory.getDriver().switchTo().activeElement();
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return obj;
	}

	/**
	 * Selects either the first frame on the page, or the main document when a
	 * page contains iframes.
	 *
	 * @return This driver focused on the top window/first frame.
	 */
	public WebDriver switchTo_DefaultContent() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebDriver frame = null;
		try {
			frame = DriverFactory.getDriver().switchTo().defaultContent();
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return frame;
	}

	/**
	 * Select a frame by its (zero-based) index. Selecting a frame by index is
	 * equivalent to the JS expression window.frames[index] where "window" is
	 * the DOM window represented by the current context. Once the frame has
	 * been selected, all subsequent calls on the WebDriver interface are made
	 * to that frame.
	 *
	 * @param index
	 *            (zero-based) index
	 * @return This driver focused on the given frame
	 * @throws NoSuchFrameException
	 *             If the frame cannot be found
	 */
	public WebDriver switchTo_Frame(int index) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebDriver frame = null;
		try {
			frame = DriverFactory.getDriver().switchTo().frame(index);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return frame;
	}

	/**
	 * Select a frame using its previously located {@link WebElement}.
	 * 
	 * @param element
	 *            The By/WebElement Object The frame element to switch to.
	 * @return This driver focused on the given frame.
	 * @throws NoSuchFrameException
	 *             If the given element is neither an IFRAME nor a FRAME
	 *             element.
	 * @throws StaleElementReferenceException
	 *             If the WebElement has gone stale.
	 * @see WebDriver#findElement(By)
	 */
	public WebDriver switchTo_Frame(Object element) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebDriver frame = null;
		try {
			if (element instanceof By) {
				frame = DriverFactory.getDriver().switchTo().frame(DriverFactory.getDriver().findElement((By) element));
			} else if (element instanceof WebElement) {
				frame = DriverFactory.getDriver().switchTo().frame((WebElement) element);
			}
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return frame;
	}

	/**
	 * Select a frame by its name or ID. Frames located by matching name
	 * attributes are always given precedence over those matched by ID.
	 *
	 * @param nameOrId
	 *            the name of the frame window, the id of the &lt;frame&gt; or
	 *            &lt;iframe&gt; element, or the (zero-based) index
	 * @return This driver focused on the given frame
	 * @throws NoSuchFrameException
	 *             If the frame cannot be found
	 */
	public WebDriver switchTo_Frame(String nameOrId) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebDriver frame = null;
		try {
			frame = DriverFactory.getDriver().switchTo().frame(nameOrId);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return frame;
	}

	/**
	 * Change focus to the parent context. If the current context is the top
	 * level browsing context, the context remains unchanged.
	 *
	 * @return This driver focused on the parent frame
	 */
	public WebDriver switchTo_ParentFrame() {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebDriver frame = null;
		try {
			frame = DriverFactory.getDriver().switchTo().parentFrame();
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return frame;
	}
	
	/**
	 * Switch the focus of future commands for this driver to Other Available window.
	 * Use this method when you have only two tabs, and in case you want to alternate between them
	 *
	 * @return the window handle of old tab
	 * @throws NoSuchWindowException
	 *             If the window cannot be found
	 */
	public void switchTo_Tab_TitleContains(String containsTitle) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebDriver driver = DriverFactory.getDriver();
		try {
			for (String currentTab : driver.getWindowHandles()) {
				driver.switchTo().window(currentTab);
				if(driver.getTitle().toLowerCase().contains(containsTitle.toLowerCase())){
					SnapshotManager.takeSnapShot(methodName);
					break;
				}
			}
			
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Switch the focus of future commands for this driver to the window with
	 * the given name/handle.
	 *
	 * @param nameOrHandle
	 *            The name of the window or the handle as returned by
	 *            {@link WebDriver#getWindowHandle()}
	 * @return This driver focused on the given window
	 * @throws NoSuchWindowException
	 *             If the window cannot be found
	 */
	public WebDriver switchTo_Window(String nameOrHandle) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebDriver win = null;
		try {
			win = DriverFactory.getDriver().switchTo().window(nameOrHandle);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return win;
	}

	/**
	 * Clicks on the checkbox to de-select it, then check isSelected for
	 * verification, Runs Report
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * @param objName
	 *            Name of the object to be display in report
	 * @return True if the element is successfully de-selected
	 */
	public boolean uncheck_CheckboxAndConfirm(Object element, String objName) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			elem.click();
			if (!elem.isSelected()) {
				bool = true;
				SnapshotManager.takeSnapShot(methodName);
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (bool) {
				CustomReporter.report(STATUS.PASS, "'" + objName + "' checkbox is successfully un-checked");
			} else {
				CustomReporter.report(STATUS.FAIL, "'" + objName + "' checkbox is NOT un-checked");
			}
		}

		return bool;
	}

	/**
	 * Verify the page url with the parameter value, Reporter will not run.
	 *
	 * @param url
	 *            expected url 
	 * @return true if page title is matched with the parameter value
	 */
	public boolean verifyPageUrl(String url) {
		return verifyPageUrl(url, false);
	}

	/**
	 * Verify the page url with the parameter value, Run the reporter.
	 *
	 * @param url
	 *            expected url
	 * @param runReport
	 *            true - reporter will run, false - reporter will not run
	 * @return true if page title is matched with the parameter value
	 */
	public boolean verifyPageUrl(String url, boolean runReport) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();

		try {
			WebDriver driver = DriverFactory.getDriver();
			if (driver.getCurrentUrl().toLowerCase().contains(url.toLowerCase())) {
				bool = true;
				SnapshotManager.takeSnapShot(methodName);
			}

			if (runReport) {
				if (bool) {
					CustomReporter.report(STATUS.PASS,
							"'<b>" + url + "</b>' page is displayed <br/>" + getCurrentUrl());
				} else {
					CustomReporter.report(STATUS.FAIL,
							"'<b>" + url + "</b>' page is NOT displayed <br/>" + getCurrentUrl());
					Assert.fail("'<b>" + url + "</b>' page is NOT displayed <br/>" + getCurrentUrl());
				}
			} else {
				if (!bool) {
					CustomReporter.report(STATUS.FAIL,
							"'<b>" + url + "</b>' page is NOT displayed <br/>" + getCurrentUrl());
					Assert.fail("'<b>" + url + "</b>' page is NOT displayed <br/>" + getCurrentUrl());
				}
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return bool;
	}
	
	
	/**
	 * Verify the page url with the parameter value, Reporter will not run.
	 *
	 * @param title
	 *            expected title name
	 * @return true if page title is matched with the parameter value
	 */
	public boolean verifyPageTitle(String title) {
		return verifyPageTitle(title, false);
	}

	/**
	 * Verify the page url with the parameter value, Run the reporter.
	 *
	 * @param title
	 *            expected title name
	 * @param runReport
	 *            true - reporter will run, false - reporter will not run
	 * @return true if page title is matched with the parameter value
	 */
	public boolean verifyPageTitle(String title, boolean runReport) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();

		try {
			WebDriver driver = DriverFactory.getDriver();
			if (driver.getTitle().toLowerCase().contains(title.toLowerCase())) {
				bool = true;
				SnapshotManager.takeSnapShot(methodName);
			}

			if (runReport) {
				if (bool) {
					CustomReporter.report(STATUS.PASS,
							"'<b>" + title + "</b>' page is displayed <br/>" + getCurrentUrl());
				} else {
					CustomReporter.report(STATUS.FAIL,
							"'<b>" + title + "</b>' page is NOT displayed <br/>" + getCurrentUrl());
					Assert.fail("'<b>" + title + "</b>' page is NOT displayed <br/>" + getCurrentUrl());
				}
			} else {
				if (!bool) {
					CustomReporter.report(STATUS.FAIL,
							"'<b>" + title + "</b>' page is NOT displayed <br/>" + getCurrentUrl());
					Assert.fail("'<b>" + title + "</b>' page is NOT displayed <br/>" + getCurrentUrl());
				}
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return bool;
	}

	/**
	 * if (element instanceof By) {
	 * wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
	 * } else if (element instanceof WebElement) {
	 * wait.until(ExpectedConditions.visibilityOf((WebElement) element)); } An
	 * expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but
	 * also has a height and width that is greater than 0. Also takes timeout as
	 * a parameter for applying the wait for element to get visible Will Take
	 * Snapshot
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @param timeoutSeconds
	 *            used to wait for element visibility
	 * @param desc
	 *            element description to be display in report
	 * @return true - element is located and visible, false - element is not
	 *         visible within the passed timeout value
	 */
	public boolean waitForElementTobe_NotStaleAndPresent(By locator, long timeoutSeconds, String desc) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		boolean bool = false;
		try {
			WebDriver driver = DriverFactory.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
			bool = true;
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, desc + " is displayed");
				} else {
					CustomReporter.report(STATUS.FAIL,
							desc + " is NOT displayed after waiting for '" + timeoutSeconds + "' seconds");
				}
			}
		}
		return bool;
	}

	/**
	 * see public boolean waitForElementTobeNotStaleAndPresent(Object element,
	 * long timeoutSeconds, String desc)
	 */
	public boolean waitForElementTobe_NotStaleAndPresent(By element, String desc) {
		return waitForElementTobe_NotStaleAndPresent(element, Constant.wait, desc);
	}

	/**
	 * 
	 * <table border="1">
	 * 	<tbody>
	 * 		<tr>
	 * 			<th>Element Visible</th><th>Timeout</th><th>Return</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>Yes</td><td>Yes</td><td>False</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>No</td><td>No</td><td>True</td>
	 * 		</tr>
	 * 	</tbody>
	 * </table>
	 * An expectation for checking that an element is NOT available on the DOM
	 * of a page it will wait for {@link Constant}.wait seconds. Will take
	 * Snapshot, Will not run the Reporter
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param timeOutInSeconds
	 *            Seconds to wait before returning false
	 * @return true - element got deleted within time, false - element is still
	 *         visible after timeout
	 */
	public boolean waitForElementTobe_NotVisible(Object element) {
		return waitForElementTobe_NotVisible(element, Constant.wait, "");
	}


	/**
	 * 
	 * <table border="1">
	 * 	<tbody>
	 * 		<tr>
	 * 			<th>Element Visible</th><th>Timeout</th><th>Return</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>Yes</td><td>Yes</td><td>False</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>No</td><td>No</td><td>True</td>
	 * 		</tr>
	 * 	</tbody>
	 * </table>
	 * An expectation for checking that an element is NOT available on the DOM
	 * of a page. Will take Snapshot
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param timeOutInSeconds
	 *            Seconds to wait before returning false
	 * @return true - element got deleted within time, false - element is still
	 *         visible after timeout
	 */
	public boolean waitForElementTobe_NotVisible(Object element, long timeOutInSeconds) {
		return waitForElementTobe_NotVisible(element, timeOutInSeconds, "");
	}


	/**
	 * 
	 * <table border="1">
	 * 	<tbody>
	 * 		<tr>
	 * 			<th>Element Visible</th><th>Timeout</th><th>Return</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>Yes</td><td>Yes</td><td>False</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>No</td><td>No</td><td>True</td>
	 * 		</tr>
	 * 	</tbody>
	 * </table>
	 * An expectation for checking that an element is NOT available on the DOM
	 * of a page it will wait for passed seconds. Will take Snapshot, Will run
	 * the Reporter
	 *
	 * <b>Will not throw/report exception[will return only true/false]</b>
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param timeOutInSeconds
	 *            Seconds to wait before returning false
	 * @param desc
	 *            element description to be display in report
	 * @return true - element got deleted within time, false - element is still
	 *         visible after timeout
	 */
	public boolean waitForElementTobe_NotVisible(Object element, long timeOutInSeconds, String desc) {
		
		// Adding this wait for spinner Loading item to load
		wait(2);
		
		boolean bool = true;
		boolean runner = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		
		if (timeOutInSeconds == 0) 	timeOutInSeconds = 1;
		
		long counter = timeOutInSeconds;
		try {

			DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			// In case element got deleted even before this line execution then
			// exception will trigger
			WebElement webObj = getWebElement(element);
			do {
				// In case element got deleted after few iteration, then on this
				// line exception will trigger
				if (webObj.isDisplayed()) {
					runner = true;
					wait(1);
					counter--;
				} else {
					runner = false;
				}
				
				if (counter < 1) {
					bool = false;
					runner = false;
				}
			} while (runner);
			
		} catch (Exception e) {
			bool = true;
			// new CustomExceptionHandler(e);
		} finally {
			SnapshotManager.takeSnapShot(methodName);
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, desc + " is removed from the page");
				} else {
					CustomReporter.report(STATUS.FAIL,
							desc + " is NOT removed after waiting for '" + timeOutInSeconds + "' seconds");
				}
			}
			DriverFactory.getDriver().manage().timeouts().implicitlyWait(Constant.implicitWait, TimeUnit.SECONDS);
		}
		return bool;
	}

	/**
	 * 
	 * <table border="1">
	 * 	<tbody>
	 * 		<tr>
	 * 			<th>Element Visible</th><th>Timeout</th><th>Return</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>Yes</td><td>Yes</td><td>False</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>No</td><td>No</td><td>True</td>
	 * 		</tr>
	 * 	</tbody>
	 * </table>
	 * 
	 * An expectation for checking that all the elements matching the provided
	 * By Object are NOT available on the DOM of a page it will wait for passed
	 * seconds.
	 * 
	 * <b>Will not throw/report exception[will return only true/false]</b>
	 * 
	 * Will take Snapshot, Will run the Reporter
	 *
	 * @param element
	 *            The By Object
	 * @param timeOutInSeconds
	 *            Seconds to wait before returning false
	 * @param desc
	 *            element description to be display in report
	 * @return true - element got deleted within timeout, false - element is
	 *         still visible after timeout
	 */
	public boolean waitForElementsTobe_NotVisible(Object element, long timeOutInSeconds, String desc) {

		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		WebDriver driver = DriverFactory.getDriver();
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			By byObj = getByObjectFromWebElement(element);
			wait.until(ExpectedConditions.numberOfElementsToBe(byObj, 0));
			bool = true;
		} catch (Exception e) {
			// new CustomExceptionHandler(e);
		} finally {
			SnapshotManager.takeSnapShot(methodName);
			driver.manage().timeouts().implicitlyWait(Constant.implicitWait, TimeUnit.SECONDS);
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, "All Elements of " + desc + " are removed from the page");
				} else {
					CustomReporter.report(STATUS.FAIL, "All Elements of " + desc
							+ " are NOT removed after waiting for '" + timeOutInSeconds + "' seconds");
				}
			}
		}
		return bool;
	}

	/**
	 * <table border="1">
	 * 	<tbody>
	 * 		<tr>
	 * 			<th>Element Visible</th><th>Timeout</th><th>Return</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>Yes</td><td>Yes</td><td>False</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>No</td><td>No</td><td>True</td>
	 * 		</tr>
	 * 	</tbody>
	 * </table>
	 * */
	public boolean waitForElementsTobe_NotVisible(Object element, String desc) {
		return waitForElementsTobe_NotVisible(element, Constant.wait, desc);
	}

	/**
	 * <table border="1">
	 * 	<tbody>
	 * 		<tr>
	 * 			<th>Element Visible</th><th>Timeout</th><th>Return</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>Yes</td><td>Yes</td><td>False</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>No</td><td>No</td><td>True</td>
	 * 		</tr>
	 * 	</tbody>
	 * </table>
	 * */
	public boolean waitForElementsTobe_NotVisible(Object element, long timeOutInSeconds) {
		return waitForElementsTobe_NotVisible(element, timeOutInSeconds, "");
	}

	/**
	 * <table border="1">
	 * 	<tbody>
	 * 		<tr>
	 * 			<th>Element Visible</th><th>Timeout</th><th>Return</th>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>Yes</td><td>Yes</td><td>False</td>
	 * 		</tr>
	 * 		<tr>
	 * 			<td>No</td><td>No</td><td>True</td>
	 * 		</tr>
	 * 	</tbody>
	 * </table>
	 * */
	public boolean waitForElementsTobe_NotVisible(Object element) {
		return waitForElementsTobe_NotVisible(element, Constant.wait, "");
	}

	/**
	 * An expectation for checking that an element is NOT available on the DOM
	 * of a page it will wait for {@link Constant}.wait seconds. Will take
	 * Snapshot, Will run the Reporter
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @return true - element got deleted within time, false - element is still
	 *         visible after timeout
	 */
	public boolean waitForElementTobe_NotVisible(Object element, String desc) {
		return waitForElementTobe_NotVisible(element, Constant.wait, desc);
	}

	/**
	 * see public boolean waitForElementTobeVisible(Object element, long
	 * timeoutSeconds, String desc)
	 */
	public boolean waitForElementTobe_Visible(Object element) {
		return waitForElementTobe_Visible(element, Constant.wait, "");
	}

	/**
	 * see public boolean waitForElementTobeVisible(Object element, long
	 * timeoutSeconds, String desc)
	 */
	public boolean waitForElementTobe_Visible(Object element, long timeoutSeconds) {
		return waitForElementTobe_Visible(element, timeoutSeconds, "");
	}

	/**
	 * <pre>
	 * if (element instanceof By) {
	 * wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
	 * } else if (element instanceof WebElement) {
	 * wait.until(ExpectedConditions.visibilityOf((WebElement) element)); 
	 * }
	 * </pre> 
	 * 
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but
	 * also has a height and width that is greater than 0.
	 *  Also takes timeout as a parameter for applying the wait for element to get visible 
	 *  Will Take Snapshot
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @param timeoutSeconds
	 *            used to wait for element visibility
	 * @param desc
	 *            element description to be display in report
	 * @return true - element is located and visible, false - element is not
	 *         visible within the passed timeout value
	 *         
	 * @throws TimeoutException
	 */
	public boolean waitForElementTobe_Visible(Object element, long timeoutSeconds, String desc) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebDriver driver = DriverFactory.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			if (element instanceof By) {
				wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
			} else if (element instanceof WebElement) {
				wait.until(ExpectedConditions.visibilityOf((WebElement) element));
			}
			bool = true;
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			SnapshotManager.takeSnapShot(methodName);
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, desc + " is displayed");
				} else {
					CustomReporter.report(STATUS.FAIL,
							desc + " is NOT displayed after waiting for '" + timeoutSeconds + "' seconds");
				}
			}
		}
		return bool;
	}

	/**Refer to waitForElementsTobe_Present(Object element, long timeoutSeconds, String desc)*/
	public boolean waitForElementsTobe_Present(Object element, long timeoutSeconds) {
		return waitForElementsTobe_Present(element, timeoutSeconds, "");
	}

	/**Refer to waitForElementsTobe_Present(Object element, long timeoutSeconds, String desc)*/
	public boolean waitForElementsTobe_Present(Object element, String desc) {
		return waitForElementsTobe_Present(element, Constant.wait, desc);
	}

	/**Refer to waitForElementsTobe_Present(Object element, long timeoutSeconds, String desc)*/
	public boolean waitForElementsTobe_Present(Object element) {
		return waitForElementsTobe_Present(element, Constant.wait, "");
	}

	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * <code>
	 * 	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byObj));
	 * </code>
	 * Will Take Snapshot
	 * Will print exception
	 *  
	 * @param element : WebElement/By object
	 * @param timeoutSeconds : timeout to wait in case element is not found
	 * @param desc : Description to be print in HTML report
	 * @return true if element is present
	 * @author shailendra.rajawat
	 * */
	public boolean waitForElementsTobe_Present(Object element, long timeoutSeconds, String desc) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebDriver driver = DriverFactory.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			By byObj = null;
			if (element instanceof WebElement) {
				wait.until(ExpectedConditions.visibilityOf((WebElement)element));
			} else if (element instanceof By) {
				byObj = (By) element;
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byObj));
			}
			
			bool = true;
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			SnapshotManager.takeSnapShot(methodName);
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, desc + " is displayed");
				} else {
					CustomReporter.report(STATUS.FAIL,
							desc + " is NOT displayed after waiting for '" + timeoutSeconds + "' seconds");
				}
			}
		}
		return bool;
	}

	/**
	 * An expectation for checking an element is visible and enabled such that
	 * you can click it.
	 * 
	 * Gives TimeOutException and add exception details in the report 
	 * 
	 * @Param : locator or WebElement
	 * @Return : the WebElement once it is located and clickable (visible and
	 *         enabled)
	 */
	public WebElement waitForElementTobe_Clickable(Object element, long timeoutSeconds, String desc) {
		WebElement webElem = null;
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebDriver driver = DriverFactory.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			if (element instanceof By) {
				webElem = wait.until(ExpectedConditions.elementToBeClickable((By) element));
			} else if (element instanceof WebElement) {
				webElem = wait.until(ExpectedConditions.elementToBeClickable((WebElement) element));
			}
			bool = true;
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (!desc.equals("")) {
				if (bool) {
					CustomReporter.report(STATUS.PASS, desc + " is Clickable");
				} else {
					CustomReporter.report(STATUS.FAIL,
							desc + " is NOT Clickable after waiting for '" + timeoutSeconds + "' seconds");
				}
			}
		}
		return webElem;
	}

	/**Refer to waitForElementTobe_Clickable(Object element, long timeoutSeconds, String desc)*/
	public WebElement waitForElementTobe_Clickable(Object element, String desc) {
		return waitForElementTobe_Clickable(element, Constant.wait, desc);
	}
	
	/**Refer to waitForElementTobe_Clickable(Object element, long timeoutSeconds, String desc)*/
	public WebElement waitForElementTobe_Clickable(Object element, long timeoutSeconds) {
		return waitForElementTobe_Clickable(element, timeoutSeconds, "");
	}
	
	/**Refer to waitForElementTobe_Clickable(Object element, long timeoutSeconds, String desc)*/
	public WebElement waitForElementTobe_Clickable(Object element) {
		return waitForElementTobe_Clickable(element, Constant.wait, "");
	}

	/**
	 * see public boolean waitForElementTobeVisible(Object element, long
	 * timeoutSeconds, String desc)
	 */
	public boolean waitForElementTobe_Visible(Object element, String desc) {
		return waitForElementTobe_Visible(element, Constant.wait, desc);
	}

	/**
	 * Returns the integer height of the passed By/WebElement Object
	 * Provided that element is present.
	 * Sometimes the element is present but hidden so its height will be 0.
	 * This method will be useful to make decisions
	 * @return height of the passed WebElement/By Object
	 * @param elem the By/WebElement Object
	 * @param waitTime the time to wait in case element is not present
	 * @throws does not throws any exception  
	 * */
	public int getHeight(Object elem, long waitTime) {
		int height = 0;
		try{
			DriverFactory.getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			WebElement element = getWebElement(elem);
			height = element.getRect().getHeight();
		}catch (Exception e) {
		}finally {
			DriverFactory.getDriver().manage().timeouts().implicitlyWait(Constant.implicitWait, TimeUnit.SECONDS);
		}
		return height;
	}
	
	/** refer getHeight(elem, waitTime) */
	public int getHeight(Object elem) {
		return getHeight(elem, Constant.implicitWait);
	}
}
