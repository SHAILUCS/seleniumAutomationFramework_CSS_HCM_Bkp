/**
 * 
 */
package or.crm.editProspectiveParticipants;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.driverManager.DriverFactory;
import com.seleniumExceptionHandling.SeleniumMethods;

import or.common.HCMCommon;

/**
 * @author Archana Oct 3, 2019 5:13:42 PM
 *This method is use for Edi and Update Prospective Participant DEtails.
 */
public class EditProspectiveParticipant {

	private SeleniumMethods com;
    HCMCommon comm;

public EditProspectiveParticipant() {
	PageFactory.initElements(DriverFactory.getDriver(), this);
	com = new SeleniumMethods();
	comm = new HCMCommon();

	
}
	
	
	/**
	 * 
	 * @author Archana Oct 17, 2019 12:07:12 PM
	 * 
	 * This method is use for validating finctions of Participant ability Page.
	 */
	private void participant_Ability_Functions() {
		ParticipantAbility_CreatePartcipant pac = new ParticipantAbility_CreatePartcipant();
		com.click_UsingAction(pac.link_ParticipantAbility, "link_ParticipantAbility");
        com.click_UsingAction(pac.button_EditParticipantInfo, "button_EditParticipantInfo");
        comm.selectByVisibleText(pac.dropdown_CognitiveLevel, "Poor");
        comm.selectByVisibleText(pac.dropdown_Communication, "Other");
        
        
       
		
		

	}
}
