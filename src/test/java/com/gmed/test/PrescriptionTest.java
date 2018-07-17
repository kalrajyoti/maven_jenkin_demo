


package com.gmed.test;

import static com.gmed.utils.StringUtility.stringContainsCsv;
import static org.testng.Assert.assertTrue;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;
import com.gmed.pages.ConfigurationPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;

import com.gmed.pages.PatientChart;

import com.gmed.pages.PrescriptionPage;
import com.gmed.pages.Profile;
import com.gmed.pages.ReportPage;
import com.gmed.utils.ConstantsFile;


public class PrescriptionTest extends BaseTestClass {
	/** Logger to log the PrescriptionTest log messages */
	private static Logger logger  = LogManager.getLogger(PrescriptionTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion preAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	/** MedicalChart Page reference for initiating the medical data present in medical page */
	private MedicalChartPage medicalchartpage;
	/** PatientChart Page reference used for various patient data */
	private PatientChart patientpageobj;
	/** Profile Page reference used for verifying demographics data in Profile Screen  */
	private Profile profilepageobj;

	/** Prescription Page reference used for adding medication data for Profile Screen  */
	private PrescriptionPage prescriptionobj;

	/** Report page reference used for generating report data in report  module*/
	private ReportPage reportpageobj;

	/** DocumentPage page reference used for documenting   data for  Prescription module*/
	private DocumentPage documentobj;
	
	/** Configuration page reference used for clicking on configuration option present in left panel */
	private ConfigurationPage configpageobj;


	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for Prescription test class....");
		loginPageObj                                                = new LoginPage();
		leftPanelpageobj                                            = new LeftPanelPage();
		medicalchartpage                                            = new MedicalChartPage();
		prescriptionobj                                             = new PrescriptionPage();
		profilepageobj                                              = new Profile();
		patientpageobj                                              = new PatientChart();
		reportpageobj                                               = new ReportPage();
		documentobj                                                 = new DocumentPage();
		configpageobj                                               = new ConfigurationPage();
		prescriptionobj.initClass();
	}

	@Test(description = "To verify that user have permission to prescribe and user is able to prescirbe medicaiton [not CS] by using  methods verbal Instruction ",groups = { "Prescription_Regression" },priority=1)
	public void prescribeByVerbalInstruction() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("entering required details for Prescribing any medication...");
		prescriptionobj.createRequireDetailsForPrescription(PrescriptionPage.keflexMedication);
		logger.info("verify provider is associated before prescribing medication");
		preAssert.assertTrue(prescriptionobj.verifyProvider());

		logger.info("Select verbal Instruction Fill method");
		prescriptionobj.selectMethod("selectVerbalInstruction");
		logger.info("override the dose alert if present...");
		prescriptionobj.checkDoseAlert();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify prescribe medication is documented in r panel");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionIsDocumented(PrescriptionPage.completeKeflexMedicationName));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "Prescirbe medicaiton [Non CS] by using  'Send' methods ",groups = { "Prescription_Regression" },priority=2)
	public void prescribeBySendMethod() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing ketek medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("entering required details for Prescribing any medication...");
		prescriptionobj.createRequireDetailsForPrescription(Profile.medicationUsingUserListForProfile);
		logger.info("verify provider is associated before prescribing medication");
		preAssert.assertTrue(prescriptionobj.verifyProvider());

		logger.info("Select send Instruction Fill method");
		prescriptionobj.selectMethod("selectSendMethod");
		logger.info("override the dose alert if present...");
		prescriptionobj.checkDoseAlert();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify prescribe medication is documented in r panel");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionIsDocumented(PrescriptionPage.completeKetekMedicationName));

		logger.info("clicking on E-Prescription from Queue Management");
		leftPanelpageobj.clickOnQueueManagement("EPrescription");
		logger.info("Verify E-Prescription Page is Opened..");
		prescriptionobj.verifyEPrescriptionPage();
		logger.info("Verify correct queue is generated in the e-prescription queue");
		preAssert.assertTrue(prescriptionobj.verifyEPrescriptionQueue());
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user have permission to prescribe and user is able to prescirbe medicaiton [not CS] by using  methods Call to Pharmacy ",groups = { "Prescription_Regression" },priority=3)
	public void prescribeByPharmacy() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("entering required details for Prescribing any medication...");
		prescriptionobj.createRequireDetailsForPrescription(PrescriptionPage.sronyxMedication);
		logger.info("verify provider is associated before prescribing medication");
		preAssert.assertTrue(prescriptionobj.verifyProvider());

		logger.info("Select call to pharmacy Fill method");
		prescriptionobj.selectMethod("callToPharmacy");
		logger.info("override the dose alert if present...");
		prescriptionobj.checkDoseAlert();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify prescribe medication is documented in r panel");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionIsDocumented(PrescriptionPage.sronyxCompleteName));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user have permission to prescribe and user is able to prescirbe medicaiton [not CS] by using  methods Print ",groups = { "Prescription_Regression" },priority=4)
	public void prescribeByPrint() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("entering required details for Prescribing any medication...");
		prescriptionobj.createRequireDetailsForPrescription(PrescriptionPage.feroconMedication);
		logger.info("verify provider is associated before prescribing medication");
		preAssert.assertTrue(prescriptionobj.verifyProvider());

		logger.info("Select Print Fill method");
		prescriptionobj.selectMethod("selectPrintMethod");
		logger.info("override the dose alert if present...");
		prescriptionobj.checkDoseAlert();
		prescriptionobj.clickPrint();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify prescribe medication is documented in r panel");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionIsDocumented(PrescriptionPage.medication4CompleteName));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user have permission to prescribe and user is able to prescirbe medicaiton [not CS] by using  methods Handwritten ",groups = { "Prescription_Regression" },priority=5)
	public void prescribeByHandwritten() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("entering required details for Prescribing any medication...");
		prescriptionobj.createRequireDetailsForPrescription(PrescriptionPage.medicationForHandwritten);
		logger.info("verify provider is associated before prescribing medication");
		preAssert.assertTrue(prescriptionobj.verifyProvider());

		logger.info("Select Handwritten Fill method");
		prescriptionobj.selectMethod("clickOnHandwritten");
		logger.info("override the dose alert if present...");
		prescriptionobj.checkDoseAlert();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify prescribe medication is documented in r panel");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionIsDocumented(PrescriptionPage.handwrittenMedicationCompleteName));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user have permission to prescribe and user is able to prescirbe medicaiton [not CS] by using  methods Sample only ",groups = { "Prescription_Regression" },priority=6)
	public void prescribeBySample() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("entering required details for Prescribing any medication...");
		prescriptionobj.createRequireDetailsForPrescription(PrescriptionPage.medicationForSample);
		logger.info("verify provider is associated before prescribing medication");
		preAssert.assertTrue(prescriptionobj.verifyProvider());

		logger.info("Select Sample only Fill method");
		prescriptionobj.selectMethod("selectSampleMethod");
		logger.info("override the dose alert if present...");
		prescriptionobj.checkDoseAlert();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify prescribe medication is documented in r panel");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionIsDocumented(PrescriptionPage.sampleMedicationCompleteName));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify 'Print' icon on prescriptions right  panel  ",groups = { "Prescription_Regression" },priority=7)
	public void verifyPrintIcon() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.rClickPrescriptionMedication();
		logger.info("clicking on Print icon for printing current medication");
		leftPanelpageobj.clickOnToolBarMenus("Print");
		prescriptionobj.clickPrint();
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify the functionality of  'Medication Reconciliation' icon on prescriptions right panel",groups = { "Prescription_Regression" },priority=8)
	public void verifyMedReconciliation() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		logger.info("verify patient chart is opened...");
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		logger.info("clicking on Medication Reconciliation icon....");
		leftPanelpageobj.clickOnToolBarMenus("Medication Reconciliation Reviewed");
		logger.info(" verify System should display Medication reconciliation last performed: mm/dd/yyyy by <user full name>");
		preAssert.assertTrue(prescriptionobj.verifyMedicationReconciliationReviewed());

		logger.info("clicking on Patient History present in presecription right panel");
		leftPanelpageobj.clickOnToolBarMenus("All Statuses");
		profilepageobj.switchToTooltip();
		logger.info(" verify System should dispaly Medication Reconciliation in Patient History");
		preAssert.assertTrue(prescriptionobj.verifyMedicationReconciliationReviewed());
		logger.info("close the history window");
		profilepageobj.closePrintWindow();

		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on current medication option present in presecription right panel");
		leftPanelpageobj.clickOnToolBarMenus("Current meds");
		profilepageobj.switchToTooltip();
		logger.info("System should display Medication Reconciliation in 'current Meds' tab");
		preAssert.assertTrue(prescriptionobj.verifyMedicationReconciliationReviewed());
		logger.info("close the current Meds window");
		profilepageobj.closePrintWindow();

		logger.info("clicking on Profile  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Profile");
		profilepageobj.switchToProfileFrame();
		logger.info("System should dispaly Medication Reconciliation in Patient profile below 'Current Medication' section");
		preAssert.assertTrue(prescriptionobj.verifyMedicationReconciliationReviewed());
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify 'ePrescription' icon on prescription right panel",groups = { "Prescription_Regression" },priority=9)
	public void verifyEPrescription() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		logger.info("verify patient chart is opened...");
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		leftPanelpageobj.clickOnToolBarMenus("E-Prescriptions");
		leftPanelpageobj.verifyToolTipTitle("Outbound queue");
		profilepageobj.switchToTooltip();
		logger.info("Verify correct queue is generated in the e-prescription queue");
		preAssert.assertTrue(prescriptionobj.verifyEPrescriptionOutboundQueue());
		logger.info("close the history window");
		profilepageobj.closePrintWindow();
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To veriify that 'Patient Takes No Medications' check box in Prescriptions right panel",groups = { "Prescription_Regression" },priority=10)
	public void verifyNoMedicationCheckbox() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname1,PrescriptionPage.existingPatientlastname1);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname1,PrescriptionPage.existingPatientlastname1));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		logger.info("verify patient chart is opened...");
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname1,PrescriptionPage.existingPatientlastname1));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		logger.info("select No Medication checkbox present in right panel");
		prescriptionobj.selectNoCurrentMedicationCheckbox();
		logger.info("clicking on Profile  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Profile");
		logger.info("verify Patient Take no Medication text is document for  patient on current medication section...");
		preAssert.assertEquals(prescriptionobj.verifyCurrentMedicationTextInProfile(), true);	
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that Search icon on Prescription window",groups = { "Prescription_Regression" },priority=11)
	public void verifySearchIcon() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("clicking on search icon for searching the medication with alphabet names");
		prescriptionobj.clickOnSearchIcon();
		logger.info("Selecting the "+PrescriptionPage.alphabetName1 +"for searching the medication");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName1);
		logger.info("verify all the medications list starts with "+PrescriptionPage.alphabetName1);
		preAssert.assertTrue(prescriptionobj.verifyRow(PrescriptionPage.alphabetName1));

		logger.info("closing the medication window");
		prescriptionobj.closeMedicationWindow();
		logger.info("clicking on search icon for searching the medication with Medication name text box");
		prescriptionobj.clickOnSearchIcon();
		logger.info("Searching the medication in medication text box");
		prescriptionobj.searchMedicationByName();
		logger.info("verify all the medications list starts with "+PrescriptionPage.keflexMedication);
		preAssert.assertTrue(prescriptionobj.verifyRow(PrescriptionPage.keflexMedication));
		prescriptionobj.closeMedicationWindow();
		logger.info("closing the medication window");
		profilepageobj.closePrintWindow();
		logger.info("closing the Prescription window");
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that Search icon on Prescription window",groups = { "Prescription_Regression" },priority=12)
	public void verifySearchTab() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.switchToPrescriptionRightPanelFrame();
		logger.info("Selecting the "+PrescriptionPage.alphabetName1 +"for searching the medication");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName1);
		preAssert.assertTrue(prescriptionobj.verifyRow(PrescriptionPage.alphabetName1));
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user can search the medication by entering medication name in the 'Medicaiton' field ",groups = { "Prescription_Regression" },priority=13)
	public void verifyMedicationField() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.switchToPrescriptionRightPanelFrame();
		logger.info("Selecting the "+PrescriptionPage.alphabetName1 +"for searching the medication");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName1);
		preAssert.assertTrue(prescriptionobj.verifyRow(PrescriptionPage.alphabetName1));
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user can search medication from 'Search' field in 'Configure My Medication' Tab",groups = { "Prescription_Regression" },priority=14)
	public void verifySearchField() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.switchToTabFrame();

		logger.info("switching in configure My Medication tab");
		leftPanelpageobj.switchToDifferentTab(" Configure My Medications ");

		prescriptionobj.switchToConfigureMyMedicatioFrame();
		prescriptionobj.searchMedicineInConfigureMyMedicationTab(Profile.recordExistingMedicationForProfile);
		logger.info("Selecting the "+Profile.recordExistingMedicationForProfile +"for searching the medication");
		prescriptionobj.switchToTabFrame();
		preAssert.assertTrue(prescriptionobj.verifyRow(Profile.recordExistingMedicationForProfile));
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user can search medication by selecting alphabet on 'Configure my Medication' tab",groups = { "Prescription_Regression" },priority=15)
	public void searchMedication() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.switchToTabFrame();


		logger.info("switching in configure My Medication tab");
		leftPanelpageobj.switchToDifferentTab(" Configure My Medications ");
		prescriptionobj.switchToConfigureMyMedicatioFrame();

		logger.info("Selecting the "+PrescriptionPage.alphabetName1 +"for searching the medication in Configure My Medication Tab");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName1);
		prescriptionobj.switchToTabFrame();
		logger.info("verify that user can search medication by selecting alphabet on 'Configure my Medication' tab");
		preAssert.assertTrue(prescriptionobj.verifyRow(PrescriptionPage.alphabetName1));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify r-click 'Modify Item' in Search Tab",groups = { "Prescription_Regression" },priority=16)
	public void verifyModifyItem() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.switchToPrescriptionRightPanelFrame();

		logger.info("Selecting the "+PrescriptionPage.alphabetName1 +"for searching the medication");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName1);
		logger.info("Right click on the medication row & select Modify item menu");
		preAssert.assertTrue(prescriptionobj.rClickInSearchTab(PrescriptionPage.alphabetName1,"modifyItem"));
		profilepageobj.switchToTooltip();
		logger.info("Modify the medication details...");
		prescriptionobj.modifyMedication();
		logger.info("saving the details");
		medicalchartpage.clickOnSaveButton();
		prescriptionobj.switchToRclickFrame();
		logger.info("Verify System should save the  medication with modified details");
		preAssert.assertTrue(prescriptionobj.verifyRclickRow());
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify r-click 'Delete' Item in Search Tab",groups = { "Prescription_Regression" },priority=17)
	public void verifyDeleteOption() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.switchToPrescriptionRightPanelFrame();

		logger.info("Selecting the "+PrescriptionPage.alphabetName2 +"for searching the medication");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName2);
		logger.info("Right click on the medication row & select Modify item menu");
		preAssert.assertTrue(prescriptionobj.rClickInSearchTab(PrescriptionPage.alphabetName2,"deleteItem"));

		leftPanelpageobj.clickOnYesPopUp();
		logger.info("verify Medication is deleted..");
		preAssert.assertTrue(prescriptionobj.verifyDeletedMedication());
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify r-click 'Context'  & 'Properties'in Search Tab",groups = { "Prescription_Regression" },priority=18)
	public void verifyContextPropertyOption() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.switchToPrescriptionRightPanelFrame();

		logger.info("Selecting the "+PrescriptionPage.alphabetName2 +"for searching the medication");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName2);
		logger.info("Right click on the medication row & select Context item menu");
		preAssert.assertTrue(prescriptionobj.rClickInSearchTab(PrescriptionPage.alphabetName2,"contextItem"));

		profilepageobj.switchToTooltip();
		logger.info("Selecting the  service Type "+Profile.existingActivityForProfilePatient );
		prescriptionobj.clickOnProcedure(Profile.existingActivityForProfilePatient);
		logger.info("Saving the service details");
		medicalchartpage.clickOnSaveButton();


		profilepageobj.switchToSocialHistoryFrame();
		prescriptionobj.switchToPrescriptionRightPanelFrame();
		logger.info("Selecting the "+PrescriptionPage.alphabetName2 +"for searching the medication");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName2);
		logger.info("Right click on the medication row & select Property item menu");
		preAssert.assertTrue(prescriptionobj.rClickInSearchTab(PrescriptionPage.alphabetName2,"propertyItem"));
		profilepageobj.switchToTooltip();
		logger.info("System should open the Properties window with the Created on & Service Type details ");
		assertTrue(prescriptionobj.verifyPropertyDetails());
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "R-Click 'Add to Favorites' in Configure My Medication tab",groups = { "Prescription_Regression" },priority=19)
	public void  RClickAddToFavorites() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		prescriptionobj.switchToTabFrame();


		logger.info("switching in configure My Medication tab");
		leftPanelpageobj.switchToDifferentTab(" Configure My Medications ");
		prescriptionobj.switchToConfigureMyMedicatioFrame();

		logger.info("Selecting the "+PrescriptionPage.alphabetName2 +"for searching the medication in Configure My Medication Tab");
		prescriptionobj.searchMedicationByAlaphabet(PrescriptionPage.alphabetName2);
		prescriptionobj.switchToTabFrame();
		logger.info("Right click on the medication row & select Property item menu");
		preAssert.assertTrue(prescriptionobj.rClickInSearchTab(PrescriptionPage.alphabetName2,"favorites"));
		profilepageobj.switchToSocialHistoryFrame();
		prescriptionobj.switchToTabFrame();
		prescriptionobj.switchToConfigureMyMedicatioFrame();
		prescriptionobj.removeMedicationName();
		preAssert.assertTrue(prescriptionobj.verifyMedicationIsAdded("Favorites"));	
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify  Administered Medication Report ",groups = { "Prescription_Regression" },priority=20)
	public void verifyMedicationReport() throws FindFailed{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));

		logger.info("Delete if existing service present in patient chart");
		profilepageobj.verifyAddedServicesDetails();
		logger.info("creating Colonoscopy service in patient chart...");
		patientpageobj.clickOnProcedure(Profile.existingActivityForProfilePatient);
		logger.info("Switching to Colonoscopy document after opening it");
		documentobj.switchToFraDocumentFrame();
		logger.info("Adding the administered medication to colonoscopy");
		documentobj.addAdministeredMedication(PrescriptionPage.keflexMedication);
		documentobj.switchToFraDocumentFrame();
		logger.info("Verifying that Administered Medication is added successfully in the document");
		preAssert.assertTrue(documentobj.getElementText(DocumentPage.administmedication).contains(PrescriptionPage.keflexMedication));

		logger.info("Clicking On Report Button...");
		leftPanelpageobj.clickOnReport("clickReport");
		logger.info("Clicking On Administered Medications Report Button...");
		reportpageobj.clickOnPatientByAgeReport("Administered Medications Report Administered Medications Report");
		logger.info("verify On Administered Medications Report is opened...");
		preAssert.assertTrue(reportpageobj.verifyReportPageIsDisplayed("Reports: Administered Medications Report"));
		preAssert.assertTrue(reportpageobj.verifyTopMenuItemsInPatientAgeReport());
		logger.info("verify On all the default checkboxes are selected in Administered Medications Report ...");
		preAssert.assertTrue(reportpageobj.verifyDefaultCheckboxValueInPatientAgeReport());

		reportpageobj.enterMedicationName(PrescriptionPage.keflexMedication);
		logger.info("finding the patient  in administered Medications Reportt ...");
		reportpageobj.findTextInPatientAgeReport(PrescriptionPage.existingPatientfirstname);
		preAssert.assertTrue(reportpageobj.verifyHighlightedTextInPatientAgeReport());

		logger.info("clicking forward and backward for opening the different pages  in Pdministered Medications Report ...");
		reportpageobj.clickOnNextAndBackButtonInPatientAgeReport();
		logger.info("clicking on Collaspe & Expand Button indministered Medications  Report ...");
		reportpageobj.clickOnCollspedAndExpandButton();
		logger.info("clicking on Export Menu in dministered Medications Report ...");
		reportpageobj.clickOnExportButton();
		logger.info("verify report content in administered Medications Report ...");
		preAssert.assertTrue(reportpageobj.verifyRecords(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that Drug Alergy Intractions",groups = { "Prescription_Regression" },priority=21)
	public void  verifyDrugAllergy() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Profile  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Profile");
		logger.info("clicking on Medical order section present in profile...");
		profilepageobj.clickOnAllergySectionInProfile();
		logger.info("verify existing medication are present then void it ");
		prescriptionobj.deleteAddedAllergyDetails();

		logger.info("Search for an allergy from alphabet search");
		profilepageobj.addAllergyDataFromMyAllergiesUserList();
		preAssert.assertEquals(profilepageobj.verifyAllergiesPopDetails(), true);

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("Entering the medication " +Profile.selectAllergyFormMyAllergies);
		prescriptionobj.enterMedicationName(Profile.selectAllergyFormMyAllergies);
		profilepageobj.switchToProblemFrame();
		logger.info("Selecting the medication from list");
		prescriptionobj.searchMedicationFromList(Profile.selectAllergyFormMyAllergies);
		logger.info(" verify that Drug Allergy Intractions pop up");
		prescriptionobj.switchToInteractionAlertFrame();
		preAssert.assertTrue(prescriptionobj.checkDrugAllergyAlerts());
		profilepageobj.switchToTooltip();
		logger.info("entering the medication details...");
		prescriptionobj.selectQuantity();
		logger.info("Select Handwritten Fill method");
		prescriptionobj.selectMethod("clickOnHandwritten");
		logger.info("logging out from the application");	
		leftPanelpageobj.clickOnLogout();

	}
	@Test(description = "To verify Reports: Clinical Interactions Activity ",groups = { "Prescription_Regression" },dependsOnMethods = { "verifyDrugAllergy" },priority=22)
	public void verifyClinicalInteractionsReport() throws FindFailed{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("Clicking On Report Button...");
		leftPanelpageobj.clickOnReport("clickReport");
		logger.info("Clicking On Clinical Interactions Report Button...");
		reportpageobj.clickOnPatientByAgeReport("Clinical Interactions Activity Clinical Interactions Activity");
		logger.info("verify On Clinical Interactions Activity is opened...");
		preAssert.assertTrue(reportpageobj.verifyReportPageIsDisplayed("Reports: Clinical Interactions Activity"));
		preAssert.assertTrue(reportpageobj.verifyTopMenuItemsInPatientAgeReport());
		logger.info("verify On all the default checkboxes are selected in Clinical Interactions Activity ...");
		preAssert.assertTrue(reportpageobj.verifyDefaultCheckboxValueInPatientAgeReport());

		logger.info("clicking on user drop down menu");
		prescriptionobj.clickUserDropDown();
		prescriptionobj.switchToPopup3Frame();
		logger.info("Searching the current user");
		prescriptionobj.searchUser();
		logger.info("selecting the patient...");	
		medicalchartpage.selectPatient();
		logger.info("clicking on run report button");
		prescriptionobj.runReport();

		logger.info("clicking on Collaspe & Expand Button in Clinical Interactions Activity  ...");
		reportpageobj.clickOnCollspedAndExpandButton();
		logger.info("clicking on Export Menu in Clinical Interactions Activity  ...");
		reportpageobj.clickOnExportButton();
		logger.info("verify report content in Clinical Interactions Activity  ...");
		preAssert.assertTrue(prescriptionobj.verifyRecords());
		logger.info("logging out from the application");	
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To veify reports Medication Per Patient ",groups = { "Prescription_Regression" },dependsOnMethods = { "prescribeByVerbalInstruction" },priority=23)
	public void verifyMedicationPerPatientReport() throws FindFailed{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("Clicking On Report Button...");
		leftPanelpageobj.clickOnReport("clickReport");
		logger.info("Clicking On Reports: Medication Per Patient Report Button...");
		reportpageobj.clickOnPatientByAgeReport("Medication Per Patient Medication Per Patient");
		logger.info("verify On Medication�Per�Patient is opened...");
		preAssert.assertTrue(reportpageobj.verifyReportPageIsDisplayed("Reports: Medication Per Patient"));
		preAssert.assertTrue(reportpageobj.verifyTopMenuItemsInPatientAgeReport());
		logger.info("verify On all the default checkboxes are selected in Reports: Medication Per Patient ...");
		preAssert.assertTrue(reportpageobj.verifyDefaultCheckboxValueInPatientAgeReport());
		prescriptionobj.selectMedicationForMedicationPerPatientReport(PrescriptionPage.keflexMedication);

		logger.info("clicking on run report button");
		prescriptionobj.runReport();

		logger.info("clicking on Collaspe & Expand Button in Medication Per Patient Report  ...");
		reportpageobj.clickOnCollspedAndExpandButton();
		logger.info("clicking on Export Menu in Medication Per Patient Report  ...");
		reportpageobj.clickOnExportButton();
		logger.info("verify report content in Medication Per Patient Report  ...");
		preAssert.assertTrue(prescriptionobj.verifyRecordsForMedicationPerPatientReport(PrescriptionPage.patientcompleteName));
		logger.info("logging out from the application");	
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify Drug Drug Interactions check ",groups = { "Prescription_Regression" },dependsOnMethods = { "prescribeBySendMethod" },priority=24)
	public void verifyDrugDrugInteration() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		leftPanelpageobj.switchToLeftPanel();
		logger.info("clicking on Pref Menuu present in left panel...");
		leftPanelpageobj.clickOnToolBarMenus("Prefs");
		profilepageobj.switchToTooltip();
		leftPanelpageobj.clickOnToolBar("Rx");	
		logger.info("Set warning level as Contraindicated Severe, and Moderate");
		prescriptionobj.selectWarningLevelForDrugInteration("Contraindicated, Severe, and Moderate");	
		profilepageobj.closePrintWindow();

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();		
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing ketek medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("Entering the medication " +PrescriptionPage.keflexMedication);
		prescriptionobj.enterMedicationName(PrescriptionPage.keflexMedication);
		profilepageobj.switchToProblemFrame();
		logger.info("Selecting the medication from list");
		prescriptionobj.searchMedicationFromList(PrescriptionPage.keflexMedication);
		logger.info(" verify that Drug Drug Intractions pop up");
		prescriptionobj.switchToInteractionAlertFrame();
		preAssert.assertTrue(prescriptionobj.checkDrugAllergyAlerts());
		logger.info("logging out from the application");	
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To validate current medication functionality ",groups = { "Prescription_Regression" },priority=25)
	public void prescribeCurrentMedication () throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		logger.info("verify patient chart is opened...");
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		

		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on current medication option present in presecription right panel");
		leftPanelpageobj.clickOnToolBarMenus("Current meds");
		profilepageobj.switchToTooltip();
		
		
		logger.info("Enter current medication data..");
		prescriptionobj.enterCurrentMedData();
		profilepageobj.switchToProblemFrame();
		logger.info("Selecting the medication from list");
		prescriptionobj.searchMedicationFromList(PrescriptionPage.currentMedicationName);
		medicalchartpage.clickOnSaveButton();
		logger.info("verify current medication is documented in r panel");
		profilepageobj.switchToSocialHistoryFrame();
		preAssert.assertTrue(prescriptionobj.verifyCurrentMedIsDocumented(PrescriptionPage.currentMedicationName));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user is able to Renew any prescirbed medication",groups = { "Prescription_Regression" },priority=26)
	public void  RClickRenewMedication() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		logger.info("R clicking on medication & select Renew Option");
		prescriptionobj.rClickMedicationOptions(PrescriptionPage.keflexMedication,"renew");
		profilepageobj.switchToTooltip();
		logger.info("Override if any interaction Alert is present");
		prescriptionobj.checkDrugAllergyAlerts();
		logger.info("Verify all the Renew properties");
		preAssert.assertTrue(prescriptionobj.verifyRClickProperties());
		
		logger.info("Select Handwritten Fill method");
		prescriptionobj.selectMethod("clickOnHandwritten");
		prescriptionobj.switchToInteractionAlertFrame();
		logger.info("Override if any interaction Alert is present");
		prescriptionobj.checkDrugAllergyAlerts();
		
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("Right click the medication and select history option");
		prescriptionobj.rClickMedicationOptions(PrescriptionPage.keflexMedication,"rclickHistory");
		logger.info("verify the medication status should be renew in history");
		preAssert.assertTrue(prescriptionobj.checkMedicationStatusInHistory());	
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user is able to stop any prescirbed medication",groups = { "Prescription_Regression" },dependsOnMethods = { "prescribeByPharmacy" },priority=27)
	public void  RClickStopMedication() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		logger.info("R clicking on medication & select Renew Option");
		prescriptionobj.rClickMedicationOptions(PrescriptionPage.sronyxMedication,"stopMedication");
		profilepageobj.switchToTooltip();
		logger.info("stopping the presribed medication");
		prescriptionobj.stopMedication();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify the medication should be present in stopped medication present in right panel");
		preAssert.assertTrue(prescriptionobj.verifyStoppedMedication());
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user is able to stop any prescirbed medication",groups = { "Prescription_Regression" },priority=28)
	public void  RClickHistory() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		logger.info("R clicking on medication & select History Option");
		prescriptionobj.rClickMedicationOptions(PrescriptionPage.keflexMedication,"history");
		profilepageobj.switchToTooltip();
		logger.info("verify all the correct headers are present in history page");
		preAssert.assertTrue(stringContainsCsv(prescriptionobj.verifyHistoryData(), PrescriptionPage.historyHeader));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify History icon on Prescription right panel near ePrescription icon",groups = { "Prescription_Regression" },priority=29)
	public void verifyHistoryIcon() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		logger.info("verify patient chart is opened...");
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		logger.info("clicking on History icon on Prescription right panel near ePrescription icon");
		leftPanelpageobj.clickOnToolBarMenus("All Statuses");
		profilepageobj.switchToTooltip();
	
		logger.info("verify all the correct headers are present in history page");
		preAssert.assertTrue(stringContainsCsv(prescriptionobj.verifyHistoryData(), PrescriptionPage.historyMainHeader));
		logger.info("close the history window");
		profilepageobj.closePrintWindow();
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that Agent can Electronically Prescribe the medication using Send method ",groups = { "Prescription_Regression" },priority=30)
	public void prescribeByAgent() throws Exception{
		logger.info("logging into gmed application...");
		logger.info(" Login with another user which we used as Recipients while creating new Task");
		loginPageObj.loginToGmedWithBreakTheGlassUser(PrescriptionPage.unprescribeUser,PrescriptionPage.unprescribePassword);
		preAssert.assertTrue(loginPageObj.verifyHomePageTitle(),"Title should contain gMed gGastro");
		logger.info("clicking on configuration menu present in left panel");
		
		configpageobj.clickOnConfiguration();
		configpageobj.switchToConfigurationMainFrame();
		logger.info("clicking on memeber present in configuration module");
		leftPanelpageobj.clickOnToolBar("Members");	
        configpageobj.switchToConfigurationInsideFrame();
        logger.info("selecting the created member..");
        prescriptionobj.selectPrescriptionMember();
        configpageobj.switchToModulePageFrame();
        logger.info("verify that unprescribed user have correctly agent of prescribe user");
        preAssert.assertTrue(prescriptionobj.verifyPrecondtionAgentDetails());
        configpageobj.clickOnBackInConfiguration();
		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopup());

		logger.info("entering required details for Prescribing any medication...");
		prescriptionobj.createRequireDetailsForPrescription(PrescriptionPage.keflexMedication);
		logger.info("verify provider is associated before prescribing medication");
		preAssert.assertTrue(prescriptionobj.verifyProvider());

		logger.info("Select send Fill method");
		prescriptionobj.selectMethod("selectSendMethod");
		logger.info("override the dose alert if present...");
		prescriptionobj.checkDoseAlert();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify prescribe medication is documented in r panel");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionIsDocumented(PrescriptionPage.completeKeflexMedicationName));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user have permission to prescribe and user is able to prescirbe medicaiton [not CS] by using  methods verbal Instruction ",groups = { "Prescription_Regression" ,"Smoke"},priority=31)
	public void prescribeRecordExisting() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		preAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);

		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();

		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname);
		preAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		preAssert.assertTrue(patientpageobj.verifyPatientChartIsOpened1(PrescriptionPage.existingPatientfirstname,PrescriptionPage.existingPatientlastname));
		medicalchartpage.dismissGuideline();

		logger.info("clicking on Prescription  menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		logger.info("verify prescription rPanel is opened...");
		preAssert.assertTrue(prescriptionobj.verifyprescriptionRPanel());
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("clicking on prescribe button for prescibing keflex medication");
		leftPanelpageobj.clickOnToolBarMenus("Record Existing");
		logger.info("verify prescribe popup is opened...");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionDetailsPopupForExistingMedication());

		logger.info("entering required details for Prescribing any medication...");
		prescriptionobj.createRequireDetailsForPrescription(PrescriptionPage.keflexMedication);
		

		logger.info("clicking on save button");
		medicalchartpage.clickOnSaveButton();
		logger.info("override the dose alert if present...");
		prescriptionobj.checkDoseAlert();
		profilepageobj.switchToSocialHistoryFrame();
		logger.info("verify prescribe medication is documented in r panel");
		preAssert.assertTrue(prescriptionobj.verifyPrescriptionIsDocumented(PrescriptionPage.completeKeflexMedicationName));
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
	}

	@AfterClass()
	public void classTearDown(){
		loginPageObj                                = null;
		leftPanelpageobj                            = null;
		medicalchartpage                            = null;
		prescriptionobj                             = null;
		profilepageobj                              = null;
		patientpageobj                              = null;
		logger.info("Exiting the classTearDown method for Prescription test class \n\n");
	}
}

