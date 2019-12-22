package or.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

public class SideBar_Main {
	
	private SeleniumMethods com;
	private HCMCommon comm;
	
	public SideBar_Main() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
		com = new SeleniumMethods();
		comm = new HCMCommon();
	}
	
	

	@FindBy(xpath = "//div[@class='overlay_bg']")
	public WebElement overlay_Background;
	
	@FindBy(xpath = "//button[@title='Left Menu']")
	public WebElement button_LeftTopMenu;
	
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='P']")
	public WebElement link_Participants;
	
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='O']")
	public WebElement link_Organisation;
	
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='F']")
	public WebElement link_FMS;
	
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='I']")
	public WebElement link_Imail;
	
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='M']")
	public WebElement link_Members;
	
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='S']")
	public WebElement link_Schedule;
	
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='A']")
	public WebElement link_Admin;
   
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='C']")
	public WebElement link_CRM;
    
	@FindBy(xpath = "//div[@class='nav_apps text-left']//div//a[.='R']")
  	public WebElement link_Recruit;
	
	@FindBy(xpath = "//button[contains(.,'Logout')]")
	public WebElement button_Logout; // THeses are all links thats why we need to append link_...kcool

	/**
	 * PLEASE PROVIDE COMMETNS HERE
	 * @author Shwetha S Sep 12, 2019
	 * */
	public void openSideBar() {
		com.click(button_LeftTopMenu,"Main menu Side Bar");
		com.wait(1);
	}
	
	/**
	 * PLEASE PROVIDE COMMETNS HERE
	 * @author Shwetha S Sep 12, 2019
	 * */
	public void logout() {
		openSideBar();
		com.click(button_Logout, "Logout Button");
		com.wait(1);
	}

	public void closeSideBar() {
		com.click(overlay_Background, "Close Main SideBar");
		com.wait(1);
	}

}
