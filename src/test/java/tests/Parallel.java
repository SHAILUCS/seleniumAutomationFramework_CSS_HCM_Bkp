package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.configData_Util.STATUS;
import com.customReporting.CustomReporter;
import com.customReporting.snapshot.SnapshotManager;
import com.seleniumExceptionHandling.SeleniumMethods;

public class Parallel {
	@Test(description = "1 + Test - P",priority=1)
	public void Test1_P() {
		SnapshotManager.takeSnapShot("Test1_P");
		System.out.println("1 + Test - P");
		/*new SeleniumMethods().wait(10);*/
	}
	
	@Test(description = "2 + Test - P",priority=1)
	public void Test2_P() {
		SnapshotManager.takeSnapShot("Test2_P");
		System.out.println("2 + Test - P");
		CustomReporter.report(STATUS.FAIL, "SOME FAILURE");
		Assert.fail();
		/*new SeleniumMethods().wait(10);*/
	}

	@Test(description = "3 + Test - P",priority=1,dependsOnMethods="Test2_P")
	public void Test3_P() {
		SnapshotManager.takeSnapShot("Test3_P");
		System.out.println("3 + Test - P");
		new SeleniumMethods().wait(10);
	}
	
	@Test(description = "4 + Test - P")
	public void Test4_P() {
		System.out.println("4 + Test - P");
		new SeleniumMethods().wait(10);
	}
}
