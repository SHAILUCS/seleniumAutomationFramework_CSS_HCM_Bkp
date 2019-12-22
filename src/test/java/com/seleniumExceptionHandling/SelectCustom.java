package com.seleniumExceptionHandling;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.customReporting.snapshot.SnapshotManager;
import com.driverManager.DriverFactory;

class SelectCustom extends WebUtils{
	/**
	 * Clear all selected entries. This is only valid when the SELECT supports multiple selections.
	 * 
	 * @param element
	 *            The By/WebElement Object
	 *
	 * @throws UnsupportedOperationException If the SELECT does not support multiple selections
	 *
	 */
	public void deselectAll(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = new SeleniumMethods().getWebElement(element);

			Select sel = new Select(elem);
			if(sel.isMultiple()){
				if (elem.getAttribute("innerHTML").contains("<option")) {
					sel.deselectAll();
					javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
					SnapshotManager.takeSnapShot(methodName);
				} else {
					CustomReporter.report(STATUS.FAIL, "Dropdown does not have any options, Locator: "+getByObjectFromWebElement(elem));
				}
			}else{
				CustomReporter.report(STATUS.FAIL, "Passed dropdown is not a MultiSelect");
			}
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Deselect the option at the given index. This is done by examining the "index" attribute of an
	 * element, and not merely by counting.
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param index The option at this index will be deselected
	 * @throws NoSuchElementException If no matching option elements are found
	 * @throws UnsupportedOperationException If the SELECT does not support multiple selections
	 * 
	 */
	public void deselectByIndex(Object element, int index) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);

			Select sel = new Select(elem);

			if (elem.getAttribute("innerHTML").contains("<option")) {
				sel.deselectByIndex(index);
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have any options, Locator: "+getByObjectFromWebElement(elem));
			}

		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
	}


	/**
	 * Deselect all options that have a value matching the argument. That is, when given "foo" this
	 * would deselect an option like:
	 * 
	 * &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 *
	 * @param element
	 *            The By/WebElement Object
	 *
	 *
	 * @param value The value to match against
	 * @throws NoSuchElementException If no matching option elements are found
	 * @throws UnsupportedOperationException If the SELECT does not support multiple selections
	 * 
	 */
	public void deselectByValue(Object element, String value) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);

			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				sel.deselectByValue(value);
				SnapshotManager.takeSnapShot(methodName);
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have any options, Locator: "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Deselect all options that display text matching the argument. That is, when given "Bar" this
	 * would deselect an option like:
	 *
	 * &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 *
	 * @param element
	 *            The By/WebElement Object
	 *
	 *
	 * @param text The visible text to match against
	 * @throws NoSuchElementException If no matching option elements are found
	 * @throws UnsupportedOperationException If the SELECT does not support multiple selections
	 * 
	 */
	public void deselectByVisibleText(Object element, String text) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);

			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				sel.deselectByVisibleText(text);
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have any options, Locator: "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Loops through the shuttle options 
	 * and deselect them one by one by double clicking.
	 * */
	public void deselectAllValues_Shuttle_DoubleClick(Object select_Elem) {
		/*
		 * In many cases, when the shuttle does not have any options, this
		 * method is waiting for the pre-set implicit wait. Which is increasing
		 * the test execution time, so removing the implicit wait for some time,
		 * then again adding it at the last point in this method
		 */
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		List<WebElement> list_Options = getOptions(select_Elem);
		for (WebElement option : list_Options) {
			doubleClick(option);
		}
		wait(.5);
		
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Constant.implicitWait, TimeUnit.SECONDS);
	}

	/**
	 * @param element
	 *            The By/WebElement Object
	 * @return Whether this select element support selecting multiple options at the same time? This
	 *         is done by checking the value of the "multiple" attribute.
	 * @throws NoSuchElementException
	 *             If no matching option elements are found
	 */
	public boolean isMultiple(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		boolean bool=false;
		try {
			WebElement elem = getWebElement(element);
			Select sel = new Select(elem);
			bool=sel.isMultiple();
			javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return bool;
	}

	/**
	 * Select all options that have a value matching the argument. That is, when
	 * given "foo" this would select an option like:
	 *
	 * &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param value
	 *            The value to match against
	 * @throws NoSuchElementException
	 *             If no matching option elements are found
	 */
	public void selectByValue(Object element, String value) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				sel.selectByValue(value);
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options to select, Locator : "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Deselect all options that display text containing the argument. That is, when given "Bar" this
	 * would deselect all options like:
	 *
	 * &lt;option value="foo"&gt;1. Bar&lt;/option&gt;
	 * 
	 * and in case of multiselect dropdown this would select all options that has text partially matching with the argument like:
	 * 
	 * &lt;option value="foo"&gt;1. BarOne&lt;/option&gt;
	 * &lt;option value="foo"&gt;2. BarTwo&lt;/option&gt;
	 * 
	 * @param element
	 *            The By/WebElement Object
	 *
	 * @param text The visible text to match against
	 * @throws NoSuchElementException If no matching option elements are found
	 * @throws UnsupportedOperationException If the SELECT does not support multiple selections
	 * 
	 */
	public void deselectByPartialVisibleText(Object element, String text) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);

			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				if (!sel.isMultiple()) {
					throw new UnsupportedOperationException(
							"You may only deselect options of a multi-select");
				}

				List<WebElement> options = elem.findElements(By.xpath(
						".//option[contains(normalize-space(.) , " + Quotes.escape(normalizeSpace(text)) + ")]"));

				boolean matched = false;
				for (WebElement option : options) {
					setSelected(option, false);
					matched = true;
				}

				if (!matched) {
					throw new NoSuchElementException("Cannot locate element with text: " + text);
				}
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have any options, Locator: "+getByObjectFromWebElement(elem));
			}

		}catch (Exception e) {

			new CustomExceptionHandler(e);
		}
	}
	
	public void selectByPartialVisibleText_DoubleClick_FromArray(Object element, String... arrValues) {
		for (int i = 0; i < arrValues.length; i++) {
			selectByPartialVisibleText_DoubleClick(element, arrValues[i]);
		}
		SnapshotManager.takeSnapShot("selectByPartialVisibleText_DoubleClick_FromArray");
	}

	public void selectByPartialVisibleText_DoubleClick(Object element, String text) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			javaScript_ScrollIntoBOTTOMView(elem);
			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				// try to find the option via XPATH ...
				List<WebElement> options =
						elem.findElements(By.xpath(".//option[contains(normalize-space(.) , " + Quotes.escape(normalizeSpace(text)) + ")]"));

				boolean matched = false;
				for (WebElement option : options) {
					setSelected_DoubleClick(option);
					if (!sel.isMultiple()) {
						return;
					}
					matched = true;
				}

				if (options.isEmpty() && text.contains(" ")) {
					String subStringWithoutSpace = getLongestSubstringWithoutSpace(text);
					List<WebElement> candidates;
					if ("".equals(subStringWithoutSpace)) {
						// hmm, text is either empty or contains only spaces - get all options ...
						candidates = elem.findElements(By.tagName("option"));
					} else {
						// get candidates via XPATH ...
						candidates =
								elem.findElements(By.xpath(".//option[contains(., " +
										Quotes.escape(subStringWithoutSpace) + ")]"));
					}
					for (WebElement option : candidates) {
						if (text.equals(option.getText())) {
							setSelected_DoubleClick(option);
							if (!sel.isMultiple()) {
								return;
							}
							matched = true;
						}
					}
					javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
					SnapshotManager.takeSnapShot(methodName);
				}

				if (!matched) {
					throw new NoSuchElementException("Cannot locate element with text: " + text);
				}
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options to select, Locator : "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}

	}

	/**
	 * CustomReporter.report(STATUS.PASS, "'" + text + "' successfully selected in shuttle/dropdown old values '"+ oldVal +"'");
	 * */
	public void selectByPartialVisibleText(Object element, String text, boolean runReport) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		String oldVal="";
		boolean flag = false;
		try {
			WebElement elem = getWebElement(element);
			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				// try to find the option via XPATH ...
				List<WebElement> options =
						elem.findElements(By.xpath(".//option[contains(normalize-space(.) , " + Quotes.escape(normalizeSpace(text)) + ")]"));

				boolean matched = false;
				if(runReport){
					if(sel.isMultiple()){
						oldVal = getAllSelectedOptionsVisibleText_FromPassedSelObj(sel).toString();
					}else{
						oldVal = sel.getFirstSelectedOption().getText();
					}
				}
				
				for (WebElement option : options) {
					setSelected(option, true);
					flag = true;
					if (!sel.isMultiple()) {
						return;
					}
					matched = true;
				}

				if (options.isEmpty() && text.contains(" ")) {
					String subStringWithoutSpace = getLongestSubstringWithoutSpace(text);
					List<WebElement> candidates;
					if ("".equals(subStringWithoutSpace)) {
						// hmm, text is either empty or contains only spaces - get all options ...
						candidates = elem.findElements(By.tagName("option"));
					} else {
						// get candidates via XPATH ...
						candidates =
								elem.findElements(By.xpath(".//option[contains(., " +
										Quotes.escape(subStringWithoutSpace) + ")]"));
					}
					for (WebElement option : candidates) {
						if (text.equals(option.getText())) {
							setSelected(option, true);
							flag = true;
							if (!sel.isMultiple()) {
								return;
							}
							matched = true;
						}
					}
				}

				if (!matched) {
					throw new NoSuchElementException("Cannot locate element with text: " + text);
				}
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options to select, Locator : "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}finally {
			javaScript_ScrollIntoMIDDLEView_AndHighlight(element);
			SnapshotManager.takeSnapShot(methodName);
			if (runReport) {
				if (flag) {
					CustomReporter.report(STATUS.PASS, "'" + text + "' successfully selected in shuttle/dropdown old values '"+ oldVal +"'");
				} else {
					CustomReporter.report(STATUS.FAIL, "'" + text + "' is NOT selected from dropdown");
				}
			}
		}
	}
	

	/**
	 * Select all options that display text containing the argument. That is, when
	 * given "Bar" this would select all options like:
	 *
	 * &lt;option value="foo"&gt;1. Bar&lt;/option&gt;
	 * 
	 * and in case of multiselect dropdown this would select all options that has text partially matching with the argument like:
	 * 
	 * &lt;option value="foo"&gt;1. BarOne&lt;/option&gt;
	 * &lt;option value="foo"&gt;2. BarTwo&lt;/option&gt;
	 * 
	 * Will take snapshot
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @param text
	 *            The visible text to match against
	 * @throws NoSuchElementException
	 *             If no matching option elements are found
	 */
	public void selectByPartialVisibleText(Object element, String text) {
		selectByPartialVisibleText(element, text, false);
	}
	
	
	private String getLongestSubstringWithoutSpace(String s) {
		String result = "";
		StringTokenizer st = new StringTokenizer(s, " ");
		while (st.hasMoreTokens()) {
			String t = st.nextToken();
			if (t.length() > result.length()) {
				result = t;
			}
		}
		return result;
	}

	/**
	 * Select or deselect specified option
	 *
	 * @param option
	 *          The option which state needs to be changed
	 * @param select
	 *          Indicates whether the option needs to be selected (true) or
	 *          deselected (false)
	 */
	private void setSelected(WebElement option, boolean select) {
		boolean isSelected=option.isSelected();
		if ((!isSelected && select) || (isSelected && !select)) {
			option.click();
		}
	}

	private void setSelected_DoubleClick(WebElement option) {
		setSelected(option, true);
		Actions act=new Actions(DriverFactory.getDriver());
		act.moveToElement(option).doubleClick().build().perform();
	}


	/**
	 * Select all options that display text matching the argument. That is, when
	 * given "Bar" this would select an option like:
	 *
	 * &lt;option value="foo"&gt;Bar&lt;/option&gt;
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @param text
	 *            The visible text to match against
	 * @throws NoSuchElementException
	 *             If no matching option elements are found
	 */
	public void selectByVisibleText(Object element, String text) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				sel.selectByVisibleText(text);
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options to select, Locator : "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
	}
	
	
	public void selectByPartialVisibleText_DoubleClick_FromCommaSeparated(Object selectObj,String commaSeperatedValues) {
		String[] valList=null;
		String singleVal="";
		if(commaSeperatedValues.contains(",")){
			valList=commaSeperatedValues.split(",");
			for (int i = 0; i < valList.length; i++) {
				singleVal=valList[i];
				selectByPartialVisibleText_DoubleClick(selectObj, singleVal);
			}
		}else{
			selectByPartialVisibleText_DoubleClick(selectObj, commaSeperatedValues);
		}
	}
	
	public void selectByVisibleText_DoubleClick_FromArray(Object element, String... arrValues) {
		for (int i = 0; i < arrValues.length; i++) {
			selectByVisibleText_DoubleClick(element, arrValues[i]);
		}
	}


	public void selectByVisibleText_DoubleClick(Object element, String text) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				// try to find the option via XPATH ...
				List<WebElement> options =
						elem.findElements(By.xpath(".//option[normalize-space(.) = " + Quotes.escape(normalizeSpace(text)) + "]"));

				boolean matched = false;
				for (WebElement option : options) {
					setSelected_DoubleClick(option);
					if (!sel.isMultiple()) {
						return;
					}
					matched = true;
				}

				if (options.isEmpty() && text.contains(" ")) {
					String subStringWithoutSpace = getLongestSubstringWithoutSpace(text);
					List<WebElement> candidates;
					if ("".equals(subStringWithoutSpace)) {
						// hmm, text is either empty or contains only spaces - get all options ...
						candidates = elem.findElements(By.tagName("option"));
					} else {
						// get candidates via XPATH ...
						candidates =
								elem.findElements(By.xpath(".//option[.= " +
										Quotes.escape(subStringWithoutSpace) + "]"));
					}
					for (WebElement option : candidates) {
						if (text.equals(option.getText())) {
							setSelected_DoubleClick(option);
							if (!sel.isMultiple()) {
								return;
							}
							matched = true;
						}
					}
					javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
					SnapshotManager.takeSnapShot(methodName);
				}

				if (!matched) {
					throw new NoSuchElementException("Cannot locate element with text: " + text);
				}
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options to select, Locator : "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}

	}

	/**
	 * CustomReporter.report(Status.PASS, "'" + text + "' successfully selected in dropdown replacing '"+ oldVal +"'");
	 * */
	public void selectByVisibleText(Object element, String text, boolean runReport) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		String oldVal="BLANK";
		boolean flag = false;
		try {
			WebElement elem = getWebElement(element);
			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				oldVal=sel.getFirstSelectedOption().getText();
				sel.selectByVisibleText(text);
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
				flag = true;
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options to select, Locator : "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		} finally {
			if (runReport) {
				if (flag) {
					CustomReporter.report(STATUS.PASS, "'" + text + "' successfully selected in dropdown replacing '"+ oldVal +"'");
				} else {
					CustomReporter.report(STATUS.FAIL, "'" + text + "' is NOT selected from dropdown");
				}
			}
		}
	}

	/**
	 * Will return WebElement List<> of all options of passed select/shuttle object
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return All options belonging to this select tag
	 */
	public List<WebElement> getOptions(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		List<WebElement> listObj = new ArrayList<>();
		try {
			WebElement elem = getWebElement(element);
			Select sel = new Select(elem);
			listObj = sel.getOptions();
			javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
			SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return listObj;
	}
	
	/**
	 * Will return Visible Text (String List<>) of all selected options of passed select object
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return All selected options belonging to this select tag
	 */
	public List<String> getAllSelectedOptionsVisibleText(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		List<String> listStr = new ArrayList<String>();
		try {
			WebElement elem = getWebElement(element);
			String innerHTML=elem.getAttribute("innerHTML");
			if (innerHTML.contains("option")) {
				Select sel = new Select(elem);
				listStr = getAllSelectedOptionsVisibleText_FromPassedSelObj(sel);
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return listStr;
	}

	/**
	 * Returns the list of visible texts of selected options, from the passed
	 * select object
	 * 
	 */
	private List<String> getAllSelectedOptionsVisibleText_FromPassedSelObj(Select sel) {
		List<String> listStr = new ArrayList<String>();
		List<WebElement> optionsList=sel.getAllSelectedOptions();
		if (optionsList.size() > 0) {
			for (WebElement temp : optionsList) {
				listStr.add(temp.getText().trim());
			}
		}
		return listStr;
	}

	/**
	 * Will return Visible Text (String List<>) of all options of passed select object
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return All options belonging to this select tag
	 */
	public List<String> getAllOptionsVisibleText(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		List<String> listStr = new ArrayList<String>();
		try {
			WebElement elem = getWebElement(element);
			String innerHTML=elem.getAttribute("innerHTML");
			if (innerHTML.contains("option")) {
				Select sel = new Select(elem);
				List<WebElement> optionsList=sel.getOptions();
				if (optionsList.size() > 0) {
					for (WebElement temp : optionsList) {
						listStr.add(temp.getText().trim());
					}
					javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
					SnapshotManager.takeSnapShot(methodName);
				}
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return listStr;
	}

	/**
	 * Will return WebElement List<> of all options which are selected
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return All selected options belonging to this select tag
	 */
	public List<WebElement> getAllSelectedOptions(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		List<WebElement> listObj = new ArrayList<>();
		try {
			WebElement elem = getWebElement(element);
			Select sel = new Select(elem);
				listObj = sel.getAllSelectedOptions();
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return listObj;
	}

	/**
	 * Select the option at the given index. This is done by examining the
	 * "index" attribute of an element, and not merely by counting.
	 *
	 * @param element
	 *            The By/WebElement Object
	 * @param index
	 *            The option at this index will be selected
	 * @throws NoSuchElementException
	 *             If no matching option elements are found
	 */
	public void selectByIndex(Object element, int index) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);

			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				sel.selectByIndex(index);
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options to select, Locator : "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
	}



	/**
	 * Clear all selected entries. Then select all Entries. This is only valid when the SELECT supports multiple selections.
	 * 
	 * @param element
	 *            The By/WebElement Object
	 *
	 * @throws UnsupportedOperationException If the SELECT does not support multiple selections
	 *
	 */
	public void selectAll(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);

			Select sel = new Select(elem);
			if(sel.isMultiple()){
				List<WebElement> options=sel.getOptions();
				if (options.size() > 0) {
					sel.deselectAll();
					for (int index = 0; index <options.size(); index++) {
						sel.selectByIndex(index);
					}
					javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
					SnapshotManager.takeSnapShot(methodName);
				} else {
					CustomReporter.report(STATUS.FAIL, "Dropdown does not have any options, Locator: "+getByObjectFromWebElement(elem));
				}
			}else{
				CustomReporter.report(STATUS.FAIL, "Passed dropdown is not a MultiSelect");
			}
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
	}

	/**
	 * Will return the first Selected Option WebElement object from the Options
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return The first selected option in this select tag (or the currently
	 *         selected option in a normal select)
	 * @throws NoSuchElementException
	 *             If no option is selected
	 */
	public WebElement getFirstSelectedOption(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		WebElement elem = null;
		try {
			elem = getWebElement(element);
			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				elem = sel.getFirstSelectedOption();
				javaScript_ScrollIntoMIDDLEView_AndHighlight(elem);
				SnapshotManager.takeSnapShot(methodName);
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options, Locator: "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {

			new CustomExceptionHandler(e);
		}
		return elem;
	}


	/**
	 * Will return the Visible text of first Selected Option WebElement object from the Options
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return The first selected option in this select tag (or the currently
	 *         selected option in a normal select)
	 * @throws NoSuchElementException
	 *             If no option is selected
	 */
	public String getFirstSelectedOptionVisibleText(Object element) {
		StackTraceElement[] ste =Thread.currentThread().getStackTrace();
		String methodName=ste[1].getMethodName();
		WebElement elem = null;
		try {
			elem = getWebElement(element);
			Select sel = new Select(elem);
			if (elem.getAttribute("innerHTML").contains("<option")) {
				elem = sel.getFirstSelectedOption();
				javaScript_ScrollIntoMIDDLEView_AndHighlight(element);
				SnapshotManager.takeSnapShot(methodName);
				return elem.getText();
			} else {
				CustomReporter.report(STATUS.FAIL, "Dropdown does not have options, Locator: "+getByObjectFromWebElement(elem));
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return null;
	}
	
	/**
	 * Just like normalize-space() function in xpath.
	 * This method will remove all the leading and trailing white spaces.
	 * and replace multiple white spaces from the middle by single space.
	 * @param str The String from which spaces needs to be removed
	 * @return a normalized String will be returned
	 * @author shailendra.rajawat 06-May-2019
	 * */
	private String normalizeSpace(String str){
		return str.replaceAll("^ +| +$|( )+", "$1");
	}

}
