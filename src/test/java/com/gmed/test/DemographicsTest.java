package com.gmed.test;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.DemographicsPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.PatientChart;
import com.gmed.pages.ReportPage;


import com.gmed.utils.ConstantsFile;




public class DemographicsTest extends BaseTestClass {
	/** Logger to log the DemographicsTest log messages */
	private static Logger logger  = LogManager.getLogger(DemographicsTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion demographicsAssert = new Assertion();
	
	/** MedicalChart Page reference for initiating the patient data present in medical page */
	private MedicalChartPage medicalchartpage;
	
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	
	/** PatientChart Page reference used for various patient data */
	private PatientChart patientpageobj;
	
	/** Demographic page reference used for documenting demographics data in demographics module*/
	private DemographicsPage demographicspageobj;
	
	/** MedicalChart page reference used for documenting medical data in Medical chart module*/
	private MedicalChartPage medicalchartobj;
	
	/** Appointment page reference used for creating appointment  data for  demographics module*/
	private AppointmentPage appointmentpageobj;
	
	/** Report page reference used for generating report data in report  module*/
 	private ReportPage reportpageobj;
 	
	/** DocumentPage page reference used for documenting   data for  demographics module*/
	private DocumentPage documentobj;
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
		loginPageObj                                     = new LoginPage();
		medicalchartpage                                 = new MedicalChartPage();
		patientpageobj                                   = new PatientChart();
		demographicspageobj                              = new DemographicsPage();
		leftPanelpageobj                                 = new LeftPanelPage();
		medicalchartobj                                  = new MedicalChartPage();
		reportpageobj                                    = new ReportPage();
		appointmentpageobj                               = new AppointmentPage();
		documentobj                                      = new DocumentPage();
		demographicspageobj.initClass();

	}
	@Test(description = "To verify that user can modify demographic details ,Insurance and Guarantor information in Demographics ",groups = { "Demographics_Regression" },priority=1)
	public void modifyPatient() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		demographicsAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		demographicsAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		demographicsAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("clicking on demographic present in left panel in patient chart...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Demographics");
		demographicsAssert.assertEquals(demographicspageobj.verifyDemographicsModuleOpened(), true);
		patientpageobj.enterValidEmailId();
		medicalchartobj.clickOnSaveButton();
		demographicspageobj.addRamdomFieldsDataInDemographics();
		demographicspageobj.savePatientInDemographicsPresentInPatientChart();
		logger.info("clicking on demographic present in left panel in patient chart...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Demographics");
		logger.info("clicking on Insurance tab present in demographics module...");
		demographicsAssert.assertEquals(demographicspageobj.verifyDemographicsModuleOpened(), true);
		demographicsAssert.assertEquals(demographicspageobj.verifyModifiedDataInDemographics(), true);
		//demographicspageobj.switchToDemographicsFrame();
		demographicspageobj.clickOnInsuranceTab();
		demographicsAssert.assertEquals(demographicspageobj.verifyInsuranceTabOpened(), true);
		demographicspageobj.verifyAddedInsurancesDetail();
		leftPanelpageobj.clickLeftNavigationPanelValue("Demographics");
		demographicsAssert.assertEquals(demographicspageobj.verifyDemographicsModuleOpened(), true);
		demographicspageobj.clickOnInsuranceTab();
		demographicsAssert.assertEquals(demographicspageobj.verifyInsuranceTabOpened(), true);
		logger.info("entering primary insurances details in Insurance tab present in demographics module...");
		demographicspageobj.switchToDemographicsInsuranceFrame();
		demographicspageobj.enterPrimaryInsuranceDetails();
		demographicspageobj.switchToDemographicsInsuranceFrame();
		demographicspageobj.copySocialSecurityNumber();
		logger.info("entering secondary insurances details in Insurance tab present in demographics module...");
		demographicspageobj.enterSecondaryInsuranceDetails();
		demographicspageobj.switchToDemographicsInsuranceFrame();
		demographicspageobj.copySocialSecurityNumber();
		logger.info("verify primary & secondary insurances are added in demographics module...");
		demographicsAssert.assertEquals(demographicspageobj.verifyInsurancesDetail(), true);
		demographicspageobj.clickOnGuarantorTab();
		demographicsAssert.assertEquals(demographicspageobj.verifyGuarantorData(), true);
		demographicspageobj.savePatientInDemographicsPresentInPatientChart();
		leftPanelpageobj.clickOnLogout();		
	}

	@Test(description = "To verify that patient by age Report for demographics Module ",groups = { "Demographics_Regression" },priority=2)
	public void verifyPatientByAgeReport() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		demographicsAssert.assertEquals(loginPageObj.verifyHomePageIsLoaded(), true);
		logger.info("Clicking On Report Button...");
		leftPanelpageobj.clickOnReport("clickReport");
		logger.info("Clicking On Patient By Age Report Button...");
		reportpageobj.clickOnPatientByAgeReport("Patient By Age Patient By Age");
		logger.info("verify On Patient By Age Report is opened...");
		demographicsAssert.assertEquals(reportpageobj.verifyReportPageIsDisplayed("Reports: Patient By Age"), true);
		demographicsAssert.assertEquals(reportpageobj.verifyTopMenuItemsInPatientAgeReport(), true);
		logger.info("verify On all the default checkboxes are selected in Patient By Age Report ...");
		demographicsAssert.assertEquals(reportpageobj.verifyDefaultCheckboxValueInPatientAgeReport(), true);
		logger.info("Entering valid Age(5-7) for the patient  in Patient By Age Report ...");
		reportpageobj.enterValidAgeInReport();
		logger.info("finding the patient  in Patient By Age Report ...");
		reportpageobj.findTextInPatientAgeReport(DemographicsPage.existingPatientfirstname);
		demographicsAssert.assertEquals(reportpageobj.verifyHighlightedTextInPatientAgeReport(), true);
		logger.info("clicking forward and backward for opening the different pages  in Patient By Age Report ...");
		reportpageobj.clickOnNextAndBackButtonInPatientAgeReport();
		logger.info("clicking on Collaspe & Expand Button in Patient By Age Report ...");
		reportpageobj.clickOnCollspedAndExpandButton();
		logger.info("clicking on Export Menu in Patient By Age Report ...");
		reportpageobj.clickOnExportButton();
		logger.info("verify report content in Patient By Age Report ...");
		demographicsAssert.assertEquals(reportpageobj.verifyRecords(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that patient by Zip Address Report for demographics Module ",groups = { "Demographics_Regression" },priority=3)
	public void verifyPatientByZipCodeReport() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		demographicsAssert.assertEquals(loginPageObj.verifyHomePageIsLoaded(), true);
		logger.info("Clicking On Scheduler Module...");
		appointmentpageobj.clickOnScheduler();
		logger.info("verify Scheduler Module is opened...");
		demographicsAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
		logger.info("clicking on New Button for scheduling any appointment for patient...");
		appointmentpageobj.clickOnNewButton();
		logger.info("verify Appointment Module is opened...");
		demographicsAssert.assertEquals(appointmentpageobj.verifyAppointmentPageIsOpened(), true);
		logger.info("Select the patient for scheduling any Appointment...");
		appointmentpageobj.clickonPatientNameDropDown();
		demographicsAssert.assertEquals(appointmentpageobj.verifyPatientScreenInAppointment(), true);
		appointmentpageobj.searchExistingPatientName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		demographicsAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname),  true);
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		demographicsAssert.assertEquals(appointmentpageobj.verifyPatientIsSearched(), true);
		logger.info("Select the Activity for scheduling any Appointment...");
		appointmentpageobj.setExistingActivity();
		demographicsAssert.assertTrue(appointmentpageobj.verifyRequiredFieldvaldation());
		appointmentpageobj.clickOnSaveButtonofUserPage();
		documentobj.clickOnYesPopUp();
		logger.info("Clicking On Report Button...");
		leftPanelpageobj.clickOnReport("clickReport");
		logger.info("Clicking On Patient By zip Address Report Button...");
		reportpageobj.clickOnPatientByAgeReport("Patient By Zip Code Patients By Zip Code");
		logger.info("verify On patient by zip address Report is opened...");
		demographicsAssert.assertEquals(reportpageobj.verifyReportPageIsDisplayed("Reports: Patient By Zip Code"), true);
		demographicsAssert.assertEquals(reportpageobj.verifyTopMenuItemsInPatientAgeReport(), true);
		logger.info("verify On all the default checkboxes are selected in Patient By Age Report ...");
		demographicsAssert.assertEquals(reportpageobj.verifyDefaultCheckboxValueInPatientAgeReport(), true);
		logger.info("Entering valid zip address for the patient  in Patient By zip address Report ...");
		reportpageobj.enterValidZipCodeInReport();
		logger.info("finding the patient  in Patient By Zip Address Report ...");
		reportpageobj.findTextInPatientAgeReport(DemographicsPage.existingPatientfirstname);
		//demographicsAssert.assertEquals(reportpageobj.verifyHighlightedTextInPatientAgeReport(), true);
		logger.info("clicking forward and backward for opening the different pages  in Patient By Zip Address Report ...");
		reportpageobj.clickOnNextAndBackButtonInPatientAgeReport();
		logger.info("clicking on Collaspe & Expand Button in  Patient By Zip Address Report ...");
		reportpageobj.clickOnCollspedAndExpandButton();
		logger.info("clicking on Export Menu in  Patient By Zip Address Report ...");
		reportpageobj.clickOnExportButton();
		logger.info("verify report content in  Patient By Zip Address Report ...");
		demographicsAssert.assertEquals(reportpageobj.verifyRecords(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		leftPanelpageobj.clickOnLogout();
	}
	@AfterClass()
	public void classTearDown(){
		loginPageObj                                     = null;
		medicalchartpage                                 = null;
		patientpageobj                                   = null;
		demographicspageobj                              = null;
		leftPanelpageobj                                 = null;
		medicalchartobj                                  = null;
		reportpageobj                                    = null;
		appointmentpageobj                               = null;
		documentobj                                      = null;
		logger.info("Exiting the classTearDown method for Demographics test class \n\n");
	}

}
