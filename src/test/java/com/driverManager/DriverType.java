package com.driverManager;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.configData_Util.Constant;

public enum DriverType implements DriverSetup {
    FIREFOX {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
        	FirefoxOptions options = new FirefoxOptions();
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.download.dir", Constant.getDownloadsPath());
			options.addPreference("browser.download.useDownloadDir", true);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/x-pdf,application/octet-stream,text/csv");
			options.addPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer
            return addProxySettings(options, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
        	if(System.getProperty("webdriver.gecko.driver")==null){
        		System.setProperty("webdriver.gecko.driver", Constant.getFirefoxDriverLocation("win"));
        	}
        	FirefoxOptions options = new FirefoxOptions();
        	options.merge(capabilities);
            return new FirefoxDriver(options);
        }
    },
    CHROME {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            chromePreferences.put("profile.default_content_settings.popups", 0);
            chromePreferences.put("download.default_directory", Constant.getDownloadsPath());
            chromePreferences.put("ssl.error_override_allowed", true);
            //chromePreferences.put("useAutomationExtension", false);
            
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("start-maximized");
            //TODO Adding this line in attempt to solve the browser spawning issue on jenkins 
            options.addArguments("--no-sandbox");
            
            options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("prefs", chromePreferences);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			/*capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
            //capabilities.setCapability("chrome.prefs", chromePreferences);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
        	if(System.getProperty("webdriver.chrome.driver")==null){
        		System.setProperty("webdriver.chrome.driver", Constant.getChromeDriverLocation("win"));
        	}
        	return new ChromeDriver(capabilities);
        }
    },
    IE {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability("requireWindowFocus", true);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
        	if(System.getProperty("webdriver.ie.driver")==null){
        		System.setProperty("webdriver.ie.driver", Constant.getIEDriverLocation());
        	}
        	InternetExplorerOptions options = new InternetExplorerOptions();
        	options.merge(capabilities);
            return new InternetExplorerDriver(options);
        }
    },
    EDGE {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.edge();
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
        	if(System.getProperty("webdriver.edge.driver")==null){
        		System.setProperty("webdriver.edge.driver", Constant.getEdgeDriverLocation());
        	}
        	EdgeOptions options = new EdgeOptions();
        	options.merge(capabilities);
            return new EdgeDriver(options);
        }
    },
    SAFARI {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new SafariDriver(capabilities);
        }
    },
    OPERA {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
            return addProxySettings(capabilities, proxySettings);
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new OperaDriver(capabilities);
        }
    },
    PHANTOMJS {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
            final List<String> cliArguments = new ArrayList<String>();
            cliArguments.add("--web-security=false");
            cliArguments.add("--ssl-protocol=any");
            cliArguments.add("--ignore-ssl-errors=true");
            capabilities.setCapability("phantomjs.cli.args", applyPhantomJSProxySettings(cliArguments, proxySettings));
            capabilities.setCapability("takesScreenshot", true);

            return capabilities;
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new PhantomJSDriver(capabilities);
        }
    },
    HTMLUNIT {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
            DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
            /*final List<String> cliArguments = new ArrayList<String>();
            cliArguments.add("--web-security=false");
            cliArguments.add("--ssl-protocol=any");
            cliArguments.add("--ignore-ssl-errors=true");
            capabilities.setCapability("phantomjs.cli.args", applyPhantomJSProxySettings(cliArguments, proxySettings));
            capabilities.setCapability("takesScreenshot", true);*/
            return capabilities;
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            return new HtmlUnitDriver(capabilities);
        }
    },
    CHROME_HEADLESS {
        public MutableCapabilities getDesiredCapabilities(Proxy proxySettings) {
        	
        	
        	//NEWER
        	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            chromePreferences.put("profile.default_content_settings.popups", 0);
            chromePreferences.put("download.default_directory", Constant.getDownloadsPath());
            chromePreferences.put("ssl.error_override_allowed", true);
            
            ChromeOptions options = new ChromeOptions();
            
            // Latest code for headless chrome browser 21/10/2019 @author Shailendra
            options.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors");
            
            //TODO Adding this line in attempt to solve the browser spawning issue on jenkins 
            options.addArguments("--no-sandbox");
            
            options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("prefs", chromePreferences);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			/*capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
            //capabilities.setCapability("chrome.prefs", chromePreferences);
            return addProxySettings(capabilities, proxySettings);
            
            
        	
        	//OLDER
            /*HashMap<String, Object> chromePreferences = new HashMap<String, Object>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            chromePreferences.put("profile.default_content_settings.popups", 0);
            chromePreferences.put("download.default_directory", Constant.getDownloadsPath());
            chromePreferences.put("ssl.error_override_allowed", true);
            
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            capabilities.setCapability("chrome.prefs", chromePreferences);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            return addProxySettings(capabilities, proxySettings);*/
        }

        public RemoteWebDriver getWebDriverObject(MutableCapabilities capabilities) {
        	if(System.getProperty("webdriver.chrome.driver")==null){
        		System.setProperty("webdriver.chrome.driver", Constant.getChromeDriverLocation("win"));
        	}
        	ChromeOptions options = new ChromeOptions();
        	options.merge(capabilities);
            return new ChromeDriver(options);
        }
    };

    protected MutableCapabilities addProxySettings(MutableCapabilities capabilities, Proxy proxySettings) {
        if (null != proxySettings) {
            capabilities.setCapability(PROXY, proxySettings);
        }

        return capabilities;
    }

    protected List<String> applyPhantomJSProxySettings(List<String> cliArguments, Proxy proxySettings) {
        if (null == proxySettings) {
            cliArguments.add("--proxy-type=none");
        } else {
            cliArguments.add("--proxy-type=http");
            cliArguments.add("--proxy=" + proxySettings.getHttpProxy());
        }
        return cliArguments;
    }
}