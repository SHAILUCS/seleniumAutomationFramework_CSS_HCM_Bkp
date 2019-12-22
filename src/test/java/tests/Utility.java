package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.configData_Util.Constant;
import com.xlUtil.DataTable;

import or.admin.AdminDashboard;
import or.admin.UserManagement;
import or.admin.UserManagement_Create_Edit;
import or.common.LoginPage;
import or.common.Navigator;

public class Utility {

	@Parameters({"startRowIndex","endRowIndex"})
	@Test(description = "Create New User")
	private void createUser(String startRowIndex, String endRowIndex) {
		int startIndex = Integer.parseInt(startRowIndex);
		int endIndex = Integer.parseInt(endRowIndex);
		
		LoginPage log = new LoginPage();
		Navigator nav = new Navigator();
		AdminDashboard ap = new AdminDashboard();
		UserManagement um = new UserManagement();
		
		nav.toAdminDashboard(Constant.SIX_DIGIT_PIN)
				.traverseMenu_VerifyPageTitle(UserManagement.title, ap.link_UserManagement);
		
		UserManagement_Create_Edit user = new UserManagement_Create_Edit();
		
		DataTable data = new DataTable(Constant.getTestDataFilePath(), Constant.get_HCM_EnvironmentInfoSheet());
		
		for (int rowIndex = startIndex; rowIndex <= endIndex; rowIndex++) {
			nav.traverseMenu_VerifyPageTitle(UserManagement_Create_Edit.title_Create, um.button_CreateNewUser);
			user.createNewUser(data, rowIndex);
			log.performIntelligentLogin("Admin");
			nav.toAdminDashboard(Constant.SIX_DIGIT_PIN)
			.traverseMenu_VerifyPageTitle(UserManagement.title, ap.link_UserManagement);
		}
		
	}
	
}
