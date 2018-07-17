package com.gmed.test;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;

import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.utils.ConstantsFile;




public class MedicalChartTest  extends BaseTestClass {
	/** Logger to log the MedicalChartTest log messages */
	private static Logger logger  = LogManager.getLogger(MedicalChartTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion MedicalAssert = new Assertion();
	/** MedicalChart Page reference for initiating the patient data present in medical page */
	private MedicalChartPage medicalchartpage;
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	/** Document Page reference used for clicking on okay button of alerts  */
	private DocumentPage documentpageobj;
	/**contains the medical page data*/
	public static Map<String, String> medicalData;

	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for medical chart test class....");	
		medicalchartpage                = new MedicalChartPage();
		loginPageObj                    = new LoginPage();
		leftPanelpageobj                = new LeftPanelPage();
		documentpageobj                 = new DocumentPage();
		medicalchartpage.initClass();
	}
	
	@Test(description = "To verify the functionality Search filter in Medical Chart ",groups = { "MedicalChart_Regression" },priority=2)
	public void searchPatient() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(MedicalChartPage.existingPatientfirstname,MedicalChartPage.existingPatientlastname);
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(MedicalChartPage.existingPatientfirstname,MedicalChartPage.existingPatientlastname), true);
		logger.info("Searching the patient with SSN  in medical chart...");
		medicalchartpage.searchExistingPatientWithSSN();
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithSSN(), true);
		logger.info("Searching the patient with DATE OF BIRTH  in medical chart...");
		medicalchartpage.searchExistingPatientWithDOB();
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithDOB(), true);
		logger.info("Searching the patient with POLICYID  in medical chart...");
		medicalchartpage.searchPatientWithPolicyId();
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithPolicyID(), true);
		logger.info("Searching the patient with RECORD NUMBER  in medical chart...");
		medicalchartpage.searchExistingPatientWithRecordNumber();
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithRecordNumber(), true);
		logger.info("Searching the patient with PHONE NUMBER  in medical chart...");
		medicalchartpage.searchPatientWithPhoneNumber();
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(MedicalChartPage.existingPatientfirstname,MedicalChartPage.existingPatientlastname), true);
		logger.info("Searching the patient with ACCOUNT NUMBER  in medical chart...");
		//medicalchartpage.searchPatientWithAccountNumber();
		//MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(existingPatientfirstname,existingPatientlastname), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify Right click option are available(View,Modify,Delete) for patient ",groups = { "MedicalChart_Regression" },priority=2)
	public void verifyRClickOptionForPatient() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(MedicalChartPage.existingPatientfirstname,MedicalChartPage.existingPatientlastname);
		logger.info("Right clicking on searched row & select view option in medical chart...");
		medicalchartpage.rClickOnSerchedPatient("RClickPatientViewOption");
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientViewFormIsOpened(), true);
		logger.info("Right clicking on searched row & select Modify option in medical chart...");
		medicalchartpage.rClickOnSerchedPatient("RclickModifyPatientOption");
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientDetailsFormIsOpened(), true);
		logger.info("Modify patient details in medical chart...");
		medicalchartpage.modifyPatientDetails();
		logger.info("saving patient details in medical chart...");
		medicalchartpage.savePatientDetails();
		logger.info("Right clicking again on searched row & select again Modify option in medical chart...");
		medicalchartpage.rClickOnSerchedPatient("RclickModifyPatientOption");
		MedicalAssert.assertEquals(medicalchartpage.verifyModifiedDataForPatient(), true);
		logger.info("Right clicking on searched row & select Delete option in medical chart...");
		medicalchartpage.rClickOnSerchedPatient("RClickPatientDeleteOption");
		logger.info("click on Yes button present in alert in medical chart...");
		documentpageobj.clickOnYesPopUp();
		leftPanelpageobj.clickOnLogout();	
	}
	@Test(description = "To verify deleted patient should be restored ",groups = { "MedicalChart_Regression" },priority=3)
	public void retoreDeletedPatient() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(MedicalChartPage.existingPatientfirstname,MedicalChartPage.existingPatientlastname);
		logger.info("Searching the patient with status as deleted in medical chart...");
		medicalchartpage.searchPatientWithStatus("Deleted");
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(MedicalChartPage.existingPatientfirstname,MedicalChartPage.existingPatientlastname), true);
		logger.info("Right clicking on searched row & select Undelete option in medical chart...");
		medicalchartpage.rClickOnSerchedPatient("RclickUndeleteOption");
		logger.info("click on Yes button present in alert in medical chart...");
		documentpageobj.clickOnYesPopUp();
		MedicalAssert.assertEquals(medicalchartpage.verifyRowIsDisappeared(), true);
		logger.info("click on clear filter present in medical chart...");
		medicalchartpage.clearFliter();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.searchExistingPatientWithName(MedicalChartPage.existingPatientfirstname,MedicalChartPage.existingPatientlastname);
		MedicalAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(MedicalChartPage.existingPatientfirstname,MedicalChartPage.existingPatientlastname), true);
		leftPanelpageobj.clickOnLogout();	
	}
	
	@AfterClass()
	public void classTearDown(){
		medicalchartpage                = null;
		loginPageObj                    = null;
		leftPanelpageobj                = null;
		documentpageobj                 = null;
		logger.info("Exiting the classTearDown method for medical chart test class \n\n");
	}

}
