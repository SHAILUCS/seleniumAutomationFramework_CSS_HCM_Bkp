package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

public class Sequential {

	@Test(priority=0,description = "ADARSH1")
	public void Test1_S(){
		
		DateFormat formatter = new SimpleDateFormat("hh:mm:ss.sss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		String hhmmss = formatter.format(calendar.getTime());
		System.out.println("Time Test: "+hhmmss);
		
		/*
		 * Thread.sleep(5000); CustomReporter.report(STATUS.PASS, "ADARSH1 - STEP 1.1");
		 * CustomReporter.report(STATUS.INFO, "ADARSH1 - STEP 1.2");
		 * CustomReporter.report(STATUS.WARNING, "ADARSH1 - STEP 1.3");
		 * CustomReporter.report(STATUS.PASS, "ADARSH1 - STEP 1.4");
		 * CustomReporter.report(STATUS.PASS, "ADARSH1 - STEP 1.5");
		 * CustomReporter.report(STATUS.PASS, "ADARSH1 - STEP 1.6");
		 */
	}
	
	/*
	 * @Test(priority=1,description = "ADARSH2") public void Test2_S() throws
	 * InterruptedException { Thread.sleep(5000); CustomReporter.report(STATUS.PASS,
	 * "ADARSH2 - STEP 1"); CustomReporter.report(STATUS.INFO, "ADARSH2 - STEP 2");
	 * CustomReporter.report(STATUS.WARNING, "ADARSH2 - STEP3");
	 * CustomReporter.report(STATUS.PASS, "ADARSH2 - STEP 4");
	 * CustomReporter.report(STATUS.PASS, "ADARSH2 - STEP 5");
	 * CustomReporter.report(STATUS.PASS, "ADARSH2 - STEP 6"); }
	 */

}
