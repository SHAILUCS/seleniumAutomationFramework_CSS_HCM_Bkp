package com.driverManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.configData_Util.Constant;
import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.seleniumExceptionHandling.CustomExceptionHandler;
import com.testNgListener.TestNGKeys;
import com.xlUtil.DataTable;

public class DriverFactory {
	static Map<Integer, WebDriver> driverList = new HashMap<Integer, WebDriver>();
	private static WebDriver driver = null;

	public static WebDriver getDriver() {
		//SessionId session = ((ChromeDriver)driverList.get((int) (long) (Thread.currentThread().getId()))).getSessionId();
		//System.out.println(Thread.currentThread().getId()+ " Session Id "+ session.toString());
		return driverList.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void setUp(HashMap<TestNGKeys, String> testData) {
		driver = initiateDriver(testData.get(TestNGKeys.remoteURL),testData.get(TestNGKeys.browser),testData.get(TestNGKeys.platform));
		
		
		Capabilities cap=((RemoteWebDriver)driver).getCapabilities();
		CustomReporter.report(STATUS.INFO, "BROWSER DETAILS: "+"<br/><br/><b style='font-size: small;'>"+cap.asMap().toString()+"</b>");
		
		String baseUrl = new DataTable(Constant.getTestDataFilePath(), Constant.get_HCM_EnvironmentInfoSheet()).getValue( 1, "url");
		
		/* In case of Qlik application we have to keep the resolution fix among all platfoms */
		if(Constant.get_HCM_EnvironmentInfoSheet().toUpperCase().contains("QLIK")){
			driver.manage().window().setSize(new Dimension(1600,900));
		}else{
			driver.manage().window().setSize(new Dimension(Constant.width,Constant.height));
		}
		//driver.manage().window().maximize();
		if(Constant.implicitWait>0){
			driver.manage().timeouts().implicitlyWait(Constant.implicitWait,TimeUnit.SECONDS);
		}
		driver.get(baseUrl);
		CustomReporter.report(STATUS.INFO, "Fired up url: "+"<br/><b style='font-size: small;'>"+baseUrl+"</b>");
	}

	public static void tearDown() {
		//System.out.println("Driver quit for Thread: "+Thread.currentThread().getId() + getDriver().getTitle());
		getDriver().quit();
		driverList.remove((int) (long) (Thread.currentThread().getId()));
	}

	private static DriverType determineEffectiveDriverType(String browser) {
		DriverType driverType =  DriverType.CHROME;
		try {
			driverType = DriverType.valueOf(browser.toUpperCase());
		} catch (IllegalArgumentException ignored) {
			System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
		} catch (NullPointerException ignored) {
			System.err.println("No driver specified, defaulting to '" + driverType + "'...");
		}
		return driverType;
	}

	private static WebDriver instantiateAppropriateWebDriver(String remoteURL,DriverType selectedDriverType, String desiredPlatform,MutableCapabilities mutableCapabilities){
		try {
			if (!remoteURL.equals("")) {
				URL seleniumGridURL = new URL(remoteURL);
				String desiredBrowserVersion = selectedDriverType.toString();
				DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities.merge(mutableCapabilities);

				if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
					desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
				}

				if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
					desiredCapabilities.setVersion(desiredBrowserVersion);
				}

				return new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
			} else {
				return selectedDriverType.getWebDriverObject(mutableCapabilities);
			}
		} catch (Exception e) {
			new CustomExceptionHandler(e);
		}
		return null;
	}

	// Driver Object Method
	private static synchronized WebDriver initiateDriver(String remoteURL,String browser, String platform) {
		// System.out.println("Driver fired for Thread:
		// "+Thread.currentThread().getId());
		WebDriver driver = driverList.get((int) (long) (Thread.currentThread().getId()));
		if (driver == null) {
			try {

				boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
				String proxyHostname = System.getProperty("proxyHost");
				Integer proxyPort = Integer.getInteger("proxyPort");
				String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);
				Proxy proxy = null;
				if (proxyEnabled) {
					proxy = new Proxy();
					proxy.setProxyType(ProxyType.MANUAL);
					proxy.setHttpProxy(proxyDetails);
					proxy.setSslProxy(proxyDetails);
				}
				DriverType driverType=determineEffectiveDriverType(browser);
				MutableCapabilities mutableCapabilities = driverType.getDesiredCapabilities(proxy);
				driver=instantiateAppropriateWebDriver(remoteURL, driverType, platform, mutableCapabilities);
				driverList.put((int) (long) (Thread.currentThread().getId()), driver);

				/*	if(remoteURL.equals("")){
					//To Run on local machine
					if(browser.toLowerCase().contains("fire")){
						FirefoxProfile profile = new FirefoxProfile();
						profile.setPreference("browser.download.folderList", 2);
						profile.setPreference("browser.download.dir", Constant.getDownloadsPath());
						//System.setProperty("webdriver.gecko.driver", Constant.getFirefoxDriverLocation(platform));
						driver = new FirefoxDriver(profile);
						driver= new FirefoxDriver();
					}else if(browser.toLowerCase().contains("ch")){
						HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
						chromePrefs.put("profile.default_content_settings.popups", 0);
						chromePrefs.put("download.default_directory", Constant.getDownloadsPath());
						chromePrefs.put("ssl.error_override_allowed", true);
						ChromeOptions options = new ChromeOptions();
						options.setExperimentalOption("prefs", chromePrefs);
						DesiredCapabilities cap = DesiredCapabilities.chrome();
						cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
						cap.setCapability(ChromeOptions.CAPABILITY, options);
						//System.setProperty("webdriver.chrome.driver", Constant.getChromeDriverLocation(platform));
						driver = new ChromeDriver(options);


					}else if(browser.toLowerCase().contains("ie")){
						DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
						capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
						InternetExplorerOptions options= new InternetExplorerOptions();
						options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
						options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

						//System.setProperty("webdriver.ie.driver",Constant.getIEDriverLocation());
						driver = new InternetExplorerDriver(options);						
					}
				}else{
					//To Run on GRID
					if(browser.toLowerCase().contains("fire") && platform.toLowerCase().contains("lin")){
						new DesiredCapabilities();
						DesiredCapabilities capability = DesiredCapabilities.firefox();
						capability.setBrowserName("firefox");
						capability.setPlatform(Platform.LINUX);
						capability.setCapability("marionette", false);
						driver = new RemoteWebDriver(new URL(remoteURL), capability);
					}else if(browser.toLowerCase().contains("ch") && platform.toLowerCase().contains("lin")){
						new DesiredCapabilities();
						DesiredCapabilities capability = DesiredCapabilities.chrome();
						capability.setBrowserName("chrome");
						capability.setPlatform(Platform.LINUX);
						capability.setCapability("profile.default_content_settings.popups", 0);
						capability.setCapability("download.default_directory", Constant.getDownloadsPath());
						capability.setCapability("ssl.error_override_allowed", true);
						driver = new RemoteWebDriver(new URL(remoteURL), capability);
					}else if(browser.toLowerCase().contains("ch") && platform.toLowerCase().contains("win")){
						new DesiredCapabilities();
						DesiredCapabilities capability = DesiredCapabilities.chrome();
						capability.setBrowserName("chrome");
						capability.setPlatform(Platform.WINDOWS);
						capability.setCapability("profile.default_content_settings.popups", 0);
						capability.setCapability("download.default_directory", Constant.getDownloadsPath());
						capability.setCapability("ssl.error_override_allowed", true);
						driver = new RemoteWebDriver(new URL(remoteURL), capability);
					}else if(browser.toLowerCase().contains("fire") && platform.toLowerCase().contains("win")){
						new DesiredCapabilities();
						DesiredCapabilities capability = DesiredCapabilities.firefox();
						capability.setBrowserName("firefox");
						capability.setPlatform(Platform.WINDOWS);
						driver = new RemoteWebDriver(new URL(remoteURL), capability);
					}else if(browser.toLowerCase().contains("ie") && platform.toLowerCase().contains("win")){
						new DesiredCapabilities();
						DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
						capability.setBrowserName("ie");
						capability.setPlatform(Platform.WINDOWS);
						driver = new RemoteWebDriver(new URL(remoteURL), capability);
					}
				}
				 */
				// System.out.println("Driver added in HashMap for Thread:
				// "+Thread.currentThread().getId());
			} catch (Exception e) {
				new CustomExceptionHandler(e);
			}
		}
		return driver;
	}

}
