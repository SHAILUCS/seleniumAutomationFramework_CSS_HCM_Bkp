package tests.crm;

import org.testng.annotations.Test;

import com.configData_Util.Util;

import or.common.Dashboard;
import or.common.Navigator;
import or.common.SideBar_CRM;
import or.crm.Create_EditParticipant;
import or.crm.Crm_Dashboard;
import or.crm.Prospective_Participants_Dashboard;
import or.crm.Tab_ParticipantDetails;
import or.crm.Tab_RefererDetails;
import or.crm.editProspectiveParticipants.ParticipantAbility_CreatePartcipant;
import or.crm.editProspectiveParticipants.ParticipantDetails_CreatePartcipant;
import or.crm.editProspectiveParticipants.RefererDetails_CreatePartcipant;
import or.crm.prospectiveParticipants.Crm_AttachmentManagement;
import or.crm.prospectiveParticipants.FundingDetails;
import or.crm.prospectiveParticipants.ParticipantDetails_ViewallUpdates;
import or.crm.prospectiveParticipants.Participant_Ability;
import or.crm.prospectiveParticipants.ProspectiveParticipants_Participant_Detail;
import or.crm.prospectiveParticipants.Shift;

public class CRM_Tests {

	private Navigator navigateToCRM_Admin() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		nav.traverseMenu_VerifyPageTitle(Crm_Dashboard.titleAdmin, d.link_CRM_Admin);
		return nav;
	}

	private Navigator navigateToCRM_User() {
		Navigator nav = new Navigator();
		Dashboard d = new Dashboard();
		nav.traverseMenu_VerifyPageTitle(Crm_Dashboard.titleUser, d.link_CRM_User);
		return nav;
	}

	/**
	 * Covered:HCM-90(1,2)
	 * 
	 * Pending:(3,4)
	 * 
	 * Depends on:create participants
	 * 
	 */
	@Test(description = "HCM89_TC01_UI Verification of CRMPage_CrmAdmin")
	private void HCM89_TC01_UIVerification_CRMPage_CrmAdmin() {
		navigateToCRM_Admin();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.verify_CRMAdmin_UI();
	}

	/**
	 * Covered:
	 * 
	 * Pending:
	 * 
	 * Depends on:
	 * 
	 */
	@Test(description = "HCM90_TC01_UIVerification_CRMPage_CrmUser")
	private void HCM90_TC01_UIVerification_CRMPage_CrmUser() {
		navigateToCRM_User();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.verify_CRMUser_UI();

	}

	
	  @Test(description = "HCM94_TC01_ParticipantDetails_CRMPage_CrmAdmin") private
	  void HCM94_TC01_ParticipantDetails_CRMPage_CrmAdmin() {
	  navigateToCRM_Admin(); 
	  Crm_Dashboard crm = new Crm_Dashboard();
	  crm.Verify_ViewParticipantDetailsScreen();
	  
	  }
	  
	  @Test(description = "HCM94_TC02_ParticipantDetails_CRMPage_CrmUser") private
	  void HCM94_TC02_ParticipantDetails_CRMPage_CrmUser() { navigateToCRM_User();
	  Crm_Dashboard crm = new Crm_Dashboard();
	  crm.Verify_ViewParticipantDetailsScreen();
	  
	  }
	 

	@Test(description = "HCM95_TC01_ParticipantintakeLatestactions_CRMPage_CrmAdmin")
	private void HCM95_TC01_ParticipantintakeLatestactions_CRMPage_CrmAdmin() {
		navigateToCRM_Admin();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.Verify_Latestactions();
	}

	@Test(description = "HCM96_TC01_ParticipantintakeDuetask_CRMPage_CrmAdmin")
	private void HCM96_TC01_ParticipantintakeDuetask_CRMPage_CrmAdmin() {
		navigateToCRM_Admin();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.Verify_DueTask();
	}

	@Test(description = "HCM97_TC01_ParticipantintakeLatestUpdates_CRMPage_CrmAdmin")
	private void HCM97_TC01_ParticipantintakeLatestUpdates_CRMPage_CrmAdmin() {
		navigateToCRM_Admin();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.verify_LatestUpdates();
	}

	@Test(description = "HCM98_TC01_ParticipantDetails_CRMPage_CrmUser")
	private void HCM98_TC01_ParticipantDetails_CRMPage_CrmUser() {
		navigateToCRM_User();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.Verify_NewAssignedParticipants();

	}

	@Test(description = "HCM107_TC01_ParticipantQuikeView_CRMPage_CrmAdmin")
	private void HCM107_TC01_ParticipantQuikeView_CRMPage_CrmAdmin() {
		navigateToCRM_Admin();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.LatestUpdates_ParticipantsScreen();

	}


	@Test(description = "HCM108_TC01_LatestUpdates_ParticipantsScreen_CRMPage_CrmAdmin")
	private void HCM108_TC01_LatestUpdates_ParticipantsScreen_CRMPage_CrmAdmin() {
		navigateToCRM_Admin();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.LatestUpdates_ParticipantsScreen();

	}

	@Test(description = "HCM108_TC01_LatestUpdates_ParticipantsScreen_CRMPage_CrmUser")
	private void HCM108_TC01_LatestUpdates_ParticipantsScreen_CRMPage_CrmUser() {
		navigateToCRM_User();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.LatestUpdates_ParticipantsScreen();}

	@Test(description = "HCM109_TC01_ParticipantsScreen_Intakeprogressbar_CRMPage_CrmAdmin")
	private void HCM109_TC01_ParticipantsScreen_Intakeprogressbar_CRMPage_CrmAdmin() {
		navigateToCRM_Admin();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.Intakeprogressbar();

	}

	@Test(description = "HCM120_TC01_Participant_Shifts_CRMPage_CrmAdmin")
	private void HCM120_TC01_Participant_Shifts_CRMPage_CrmAdmin() {
		navigateToCRM_Admin();
		Crm_Dashboard crm = new Crm_Dashboard();
		crm.shift_UI_verify();

	}

	/*
	 * 
	 * This Method is use for React Table.
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test
	private void REACT_TABLE() {
		navigateToCRM_Admin();

		Crm_Dashboard crm = new Crm_Dashboard();
		crm.reactTableExample();

	}

	/*
	 * This Method is use for Validating Prospective Participant UI Elements
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC01_CRM_prospective_participants_UI_Elements_varify", groups = {"UI Only"})
	private void TC01_CRM_prospective_participants_UI_Elements_varify() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.prospective_Participants_UI_Elements_Varify();

	}

	/*
	 * This Method is use for Validating Prospective Participant Detail Functions.
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC02_CRM_prospective_participants_Details_Dashboard_UI_verification", groups = {"UI Only"})
	private void TC02_CRM_prospective_participants_Details_Dashboard_UI_verification() {

		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		ProspectiveParticipants_Participant_Detail pd = new ProspectiveParticipants_Participant_Detail();
		pd.participant_Details_Dashboard_UI_Verification();

		/*
		 * This Method is use for Validating Edit Participant Detail.
		 * 
		 * @author Archana Oct 2 , 2019
		 */
	}

	@Test(description = "TC03_CRM_EditParticipant_info_varification", groups = {"UI and Func"})
	private void TC03_CRM_EditParticipant_info_varification() {

		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		
		ProspectiveParticipants_Participant_Detail pd = new ProspectiveParticipants_Participant_Detail();
		pd.verify_EditProspectiveParticipant_UI();

	}

	/*
	 * This Method is use for Validating Assigned To User For a Participant Detail.
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC04_CRM_Assignedto_Functionalityvalidation", groups = {"UI and Func"})
	private void TC04_CRM_Assignedto_Functionalityvalidation() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		ProspectiveParticipants_Participant_Detail pd = new ProspectiveParticipants_Participant_Detail();
		pd.assignedto_Functionalityvalidation("Archana");

	}

	/*
	 * This Method is use for Validating Manage attcthmant in Participant Detail .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC05_CRM_Manage attcthmant validation", groups = {"UI and Func"})
	private void TC05_CRM_Manage_Attcthmant_Validation() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		Crm_AttachmentManagement ca = new Crm_AttachmentManagement();
		ca.select_Doc_Catogary();

	}

	/**
	 * This Method is use for Validating Intake Progress in Participant Detail .
	 * 
	 * @author Archana Oct 2 , 2019
	 * 
	 * @author shailendra Dec 2, 2019 Fixed the script
	 */
	@Test(description = "TC06_CRM_Intake Progress validation", groups = {"UI Only"})
	private void TC06_CRM_Intake_Progress_Validation() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		
		ProspectiveParticipants_Participant_Detail  pd = new ProspectiveParticipants_Participant_Detail ();
		pd.intake_Progress_Validation();
	}

	/*
	 * This Method is use for Validating FMS Case in Participant Detail .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC07_CRM_FMS_Case_Validation", groups = {"UI Only"})
	private void TC07_CRM_FMS_Case_Validation() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		ProspectiveParticipants_Participant_Detail  pd = new 	ProspectiveParticipants_Participant_Detail ();
		pd.fms_Case_validation();
	}

	/*
	 * This Method is use for Validating Participant ability UI .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC08_CRM_Participant ability_Page UI verification", groups = {"UI Only"})
	private void TC08_CRM_Participant_Ability_Page_UI_Verification() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		Participant_Ability pa = new Participant_Ability();
		pa.participant_Ability_PageUI();
	}

	/*
	 * This Method is use for Validating Edit and Update Participant ability UI .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC09_CRM_edit_Participant ability_Page UI verification", groups = {"UI and Func"})
	private void TC09_CRM_edit_Participant_Ability_Page_UI_Verification() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		ParticipantAbility_CreatePartcipant pa = new ParticipantAbility_CreatePartcipant();
		pa.edit_Participant_Details_UI_Validation();
	}
	/*
	 * This Method is use for Validating Edit and Update Participant ability UI .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC10_CRM_edit_Participant ability_Page UI verification", groups = {"UI Only"})
	private void TC010_CRM_latest_Update_Page_UI_Verification() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		ParticipantDetails_ViewallUpdates va = new ParticipantDetails_ViewallUpdates();
		va.latestUpdate_Viewall_UI_Verify();
	}
	/*
	 * This Method is use for Validating relavant_Attachment ability UI .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC11_CRM_relavant_Attachment_Verification", groups = {"UI and Func"})
	private void TC11_CRM_relavant_Attachment_Verification() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		Crm_AttachmentManagement ca = new Crm_AttachmentManagement();
		ca.ParticipantAbilityUpload();
		ca.participantDisabilityUpload();
	}
	/*
	 * This Method is use for Validating relavant_Attachment Participant ability UI .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC12_CRM_relavant_Attachment_Verification", groups = {"UI and Func"})
	private void TC12_CRM_relavant_Attachment_Verification() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		Crm_AttachmentManagement ca = new Crm_AttachmentManagement();
		//ca.relavant_Attachment_UI_validation();
	}
	/*
	 * This Method is use for Validating shift_UI Participant ability UI .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC13_CRM_shift_UI_Verification", groups = {"UI Only"})
	private void TC13_CRM_shift_UI_Verification() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		Shift s = new Shift();
		s.shift_UI_Validation();
	}    
	/* 
	 * This Method is use for Validating shift_Function Participant ability UI .
	 * 
	 * @author Archana Oct 2 , 2019 
	 */
	@Test(description = "TC14_CRM_shift_Function_Verification", groups = {"Func Only"})
	private void TC14_CRM_shift_Function_Verification() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		Shift s = new Shift();
		s.shift_Page_Functional_Validation();
	}
	/*
	 * This Method is use for Validating Edit and Update Participant ability UI .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC15_CRM_funding_Detail_UI_Validation", groups = {"UI Only"})
	private void TC15_CRM_funding_Detail_UI_Validation() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		FundingDetails fd = new FundingDetails();
		fd.funding_Detail_UI_Validation();
	}
	/*
	 * This Method is use for Validating Edit and Update Participant ability UI .
	 * 
	 * @author Archana Oct 2 , 2019
	 */
	@Test(description = "TC16_CRM_funding_Detail_Function_Validation")
	private void TC16_CRM_funding_Detail_Function_Validation() {
		SideBar_CRM sc = new SideBar_CRM();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Prospective_Participants_Dashboard.title,
				sc.link_Prospective_Participants);
		Prospective_Participants_Dashboard pp = new Prospective_Participants_Dashboard();
		pp.openQuickViewAndClickOnViewButton();
		FundingDetails fd = new FundingDetails();
		fd.funding_Detail_Function_Validate();
	}
	
	
	@Test(description = "TC17_CRM_create_participant")
	private void TC17_CRM_create_participant() {
		
		/*
		 * SideBar_CRM sc = new SideBar_CRM();
		 * navigateToCRM_Admin().traverseMenu_VerifyPageTitle(
		 * Prospective_Participants_Dashboard.title, sc.link_Dashboard);
		 */
		RefererDetails_CreatePartcipant rc = new RefererDetails_CreatePartcipant();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(RefererDetails_CreatePartcipant.title_Create,
				rc.link_CreateParticipant);
		
		rc.createNewUser("Archana", "Mohini","Archanatest@gmail.com", "9620146277");
		ParticipantDetails_CreatePartcipant pc = new ParticipantDetails_CreatePartcipant();
		
		String num = Util.getTimeStamp_InMilliSec().substring(4, 13);
		pc.PrticipantDetailFillNDISNo(num);
		pc.ParticipantDetailFllAddress("arc");
		pc.Marital_Status();
		pc.Living_Situation();
		ParticipantAbility_CreatePartcipant pac = new ParticipantAbility_CreatePartcipant();
		pac.fillParticipantAbilityDetails("Good","Verbal","Good communication");
	}
	
	
	/**
	 * @author shailendra Dec 3, 2019
	 * */
	@Test(description = "TC18_CRM_create_Prospective Participant")
	private void TC18_CRM_create_ProspectiveParticipant() {
		Crm_Dashboard d = new Crm_Dashboard();
		navigateToCRM_Admin().traverseMenu_VerifyPageTitle(Create_EditParticipant.title,
				d.button_createNewparticipants);
		
		// 
		Tab_RefererDetails ref = new Tab_RefererDetails();

		String ref_Fname = "Andrew", ref_Lname = "Garfield", ref_Org = "Sony", ref_Email = "andrew.garfield@sony.com",
				ref_Phno = "1234567898", ref_RelToPart = "Participant";
		
		ref.fillRefererDetails(ref_Fname, ref_Lname, ref_Org, ref_Email, ref_Phno, ref_RelToPart);
		ref.saveReferrerDetails();
		
		Tab_ParticipantDetails det = new Tab_ParticipantDetails();		
		
		// Participant Details Section
		String det_prefFName = "Spidey", det_email = "spidey@sony.com", det_phone = "1234567890", det_dob = "12/12/2000",
				det_streetAdd = "la trobe", det_medicareNum = "3456987012", det_crnNum = "2345678901";
		
		String ndis = det.fillParticipantDetails(null, null, null, det_prefFName, det_email, det_phone,
				det_dob, det_streetAdd, null, null, det_medicareNum, det_crnNum);
		
		// Next of Kin Section
		det.fillNextOfKins(Util.getArray("FName1", "FName2", "FName3"), Util.getArray("LName1", "LName2", "LName3"),
		 		Util.getArray("Brother", "Sister", "Father"),
		 		Util.getArray("1234567890", "1234567891", "1234567892"),
		 		Util.getArray("abcd@gmail.com", "abcde@gmail.com", "abcdf@gmail.com"));
		 
		// Booker Details Section
		det.fillBookerDetails(Util.getArray("BFName1", "BFName2", "BFName3"), Util.getArray("BLName1", "BLName2", "BLName3"),
		 		Util.getArray("Father", "Sister", "Mother"),
		 		Util.getArray("1234567811", "1234567822", "1234567833"),
		 		Util.getArray("bbabcd@gmail.com", "bbabcde@gmail.com", "bbabcdf@gmail.com"));
		 
		// Participant Living Situation Section
		det.fillParticipantLivingSituation("Single", "At risk (eg. eviction, behind in rent, family violence)", true,
				true, true, Tab_ParticipantDetails.sampleFilePath, true);
		
		// Participant NDIS Plan Section
		det.fillParticipantNdisPlan("Provider", "ProviderName", "provider@email.com", "ProviderAddress", "ACT", "3000",
		 		Tab_ParticipantDetails.sampleFilePath2);
		 
	}
}