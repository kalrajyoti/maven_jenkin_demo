package com.gmed.test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseTestClass;
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.ConfigurationPage;
import com.gmed.pages.DemographicsPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.OrdersPage;
import com.gmed.pages.PatientChart;
import com.gmed.pages.PatientInterviewForm;
import com.gmed.pages.Profile;
import com.gmed.test.RecallTest;
import com.gmed.utils.ConstantsFile;

public class PatientInterviewFormTest extends BaseTestClass {
	/** Logger to log the Recall log messages */
	private static Logger logger  = LogManager.getLogger(PatientInterviewFormTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion pifAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	/** Configuration Page reference for initiating the Configuration data present in Configuration page */
	private ConfigurationPage configpageobj;
	/** PIF Page reference used for various PIF data */
	private PatientInterviewForm pifobj;

	/** MedicalChart Page reference for initiating the medical data present in medical page */
	private MedicalChartPage medicalchartpage;
	/** PatientChart Page reference used for various patient data */
	private PatientChart patientpageobj;
	/** Order Page reference used for clicking on new Button  */
	private OrdersPage orderPageeobj;
     private AppointmentPage appointmentpageobj;
     private DocumentPage documentobj;
	private Profile profilepageobj;	                                    	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for Prescription test class....");
		loginPageObj                                                                   = new LoginPage();
		leftPanelpageobj                                                               = new LeftPanelPage();
		configpageobj                                                                  = new ConfigurationPage();
		pifobj                                                                         = new PatientInterviewForm();
		medicalchartpage                                                               = new MedicalChartPage();
		profilepageobj                                                                     = new Profile();
		patientpageobj                                                                 = new PatientChart();
		orderPageeobj                                                                  = new OrdersPage();
		appointmentpageobj                                                                 = new AppointmentPage();
		documentobj                                                                    = new DocumentPage();
		pifobj.initClass();
	}
	
	@Test(description = "The purpose of this test case is to create a Global PIF for Current locations ",groups = { "PIF_Regression","smoke"},priority=1)
	public void ConfigureGlobalPIF() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		pifAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on Configuration menu present in left panel...");
		leftPanelpageobj.selectLeftMainMenu("Configuration");
		configpageobj.switchToConfigurationMainFrame();
		logger.info("clicking on Interview in configuration module");
		leftPanelpageobj.clickOnToolBar("Interview"+'\n'+"Forms");	
		leftPanelpageobj.switchToPIFMainFrame();
		logger.info("Delete all the existing PIF present");
		pifobj.deleteExistingPIf();
		logger.info("Clicking on New Button For creating Gloval PIF");
		orderPageeobj.clickOnNewButton();
		logger.info("Configuring global PIF & Removing Patient Information Section");
		pifobj.configureServiceLocationPIF("selectPatientInformation");
		pifAssert.assertTrue(pifobj.verifyGlobalPIFCreated());
	    logger.info("Logging out from application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "The purpose of this test case is launch Global PIF from Patient Chart ",groups = { "PIF_Regression","smoke"},priority=2)
	public void verifyGlobalPIFFromPatientChart() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		pifAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
	    logger.info("clicking on Medical Chart Menu Present in Left Panel");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		pifAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		pifAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("create colonoscopy service for the patient  in patient chart screen.....");
		patientpageobj.clickOnProcedure("Colonoscopy");
		leftPanelpageobj.clickOnNewInToolTip();
		leftPanelpageobj.switchToTemplateFrame();
		logger.info("Clicking on Interview button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Interview");
		pifobj.switchToPIFFrame();
		logger.info("verify Global PIF is Launched...");
		pifAssert.assertTrue(pifobj.verifyPIFData());
		profilepageobj.clickOnCloseButtonInProfile();
	    logger.info("Logging out from application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "The purpose of this test case is to launch Global PIF from Appointment screen",groups = { "PIF_Regression","smoke"},priority=3)
	public void verifyGlobalPIFFromScheduler() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		pifAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on Scheduler menu present in left panel...");
		leftPanelpageobj.selectLeftMainMenu("Scheduler");
		
		logger.info("verify Scheduler Module is opened...");
		pifAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
		logger.info("clicking on New Button for scheduling any appointment for patient...");
		appointmentpageobj.clickOnNewButton();
		logger.info("verify Appointment Module is opened...");
		pifAssert.assertEquals(appointmentpageobj.verifyAppointmentPageIsOpened(), true);
		logger.info("Select the patient for scheduling any Appointment...");
		appointmentpageobj.clickonPatientNameDropDown();
		pifAssert.assertEquals(appointmentpageobj.verifyPatientScreenInAppointment(), true);
		appointmentpageobj.searchExistingPatientName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		pifAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname),  true);
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		pifAssert.assertEquals(appointmentpageobj.verifyPatientIsSearched(), true);
		
		logger.info("Select the Activity for scheduling any Appointment...");
		appointmentpageobj.selectActivity("Colonoscopy");
		pifAssert.assertTrue(appointmentpageobj.verifyRequiredFieldvaldation());
		appointmentpageobj.clickOnSaveButtonofUserPage();
		documentobj.clickOnYesPopUp();	
		logger.info("Right click & select check in submenu");
		appointmentpageobj.checkInAppointmentRow("Colonoscopy");
		appointmentpageobj.switchToCheckinFrame();
		logger.info("clicking on interview option present in check in screen");
		leftPanelpageobj.clickOnToolBarMenus("Interview");
		appointmentpageobj.switchToPanProfile();
		logger.info("verify Global PIF is Launched...");
		pifAssert.assertTrue(pifobj.verifyPIFData());
	    logger.info("Logging out from application");
		leftPanelpageobj.clickOnLogout();
	}
}
