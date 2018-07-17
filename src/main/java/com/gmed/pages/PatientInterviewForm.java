package com.gmed.pages;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.SikuliException;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;

import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;

public class PatientInterviewForm extends BaseAbstractPage {
	/** Logger to log the PatientInterviewForm log messages */
	private static Logger logger                        = LogManager.getLogger(PatientInterviewForm.class);
	public static By interview_icon                     = By.id("btnInterviewFormType_ImageContainer");
	public static By addlocation                        = By.id("txtEntityAdd");
	public static By addservice                         = By.id("txtServiceTypeAdd");
	public static By interviewIcon                      = By.id("btnPatientInterviewForm_SpanBGColor");
	public static By allergyTitle                       = By.id("ifeAllergy_Title");
	public static By textBoxarea                        = By.id("txtDescription_TextBox");
	public static By immunizationtext                   = By.id("ifeImmunization_Title");
	public static By immunizationTextBox                = By.id("txtVaccine_TextBox");
	public static By problemtext                        = By.id("ifeDiagnosticStudy_Title");
	public static By presentMedicalConditions           = By.id("ifeIllness_Title");
	public static By previousProcedure                  = By.id("ifeProcedure_Title");
	public static By maritalStatus                       =By.id("ifeMaritalStatus_Title");
	public static By alcoholTitle                        =By.id("ifeAlcohol_Title");
	public static By pifTextBox                          =By.id("txtType_TextBox");
	public static By quantityTextBox                     =By.id("txtQuantity_TextBox");
	public static By freqTextBox                         =By.id("txtFrequencyValue_TextBox");
	public static By caffeineTitle                       =By.id("ifeCaffeine_Title");
	public static By intakeTitle                         =By.id("txtIntake_TextBox");
	public static By tobaccoTitle                        =By.id("ifeTobacco_CurrentEveryDaySmoker_ItemContent");
	public static By drugTitle                           =By.id("ifeDrug_Title");
	public static By exerciseTitle                       =By.id("ifeExercise_Title");
	public static By alcoholTextBox                      =By.id("snpAlcohol_Data");
	public static By caffeineTextBox                     =By.id("snpCaffeine_Data");
	public static By tobaccoTextBox                      =By.id("snpTobacco_Data");
	public static By drugTextBox                         =By.id("snpDrug_Data");
	public static By exerciseTextBox                     =By.id("snpExercise_Data");
	public static By familyTitle                         =By.id("ifeFamily_Title");
	public static By reviewWithSection                   =By.id("ifeReview_Title");
	public static By pifSection                          =By.className("InterviewFormEntityItem");
	public static By pifAllergyData                      =By.id("ifeAllergy_Div");

	public static boolean isGlobalPifPresent=false;
	public  boolean isSeviceLocation=false;
	public  boolean islocation=false;
	public  boolean isService=false;

	/**contains the pif page data*/
	public static Map<String, String> pifData;


	/**contains the demographics Chart page data*/
	public static Map<String, String> demographicsData;

	/**contains the Profile page data*/
	public static Map<String, String> profileData;

	/**contains the medical page data*/
	public static Map<String, String> medicalData;

	/**These are the variables which are used to store different data for PIF module*/
	public static String cleanupOperationText;
	public static String signOperationText;
	public static String printOperationText;
	public static String faxOperationText;
	public static String existingPatientfirstname;
	public static String existingPatientlastname;
	public static String publishToPortalOperationText;
	public static String documentText;
	public static String sendForSignatureOperationText;
	public static String sendForSignatureText;
	public static String sendForReviewOperationText;
	public static String sendForReviewText;
	public static String pifAllergyText;
	public  String currentLocation;
	public static String interviewformname;

	/** These are the variables which are present on "PIF" sheet in the excel*/
	public static final String CLEAN_UP_TEXT 				                                  = "cleanupOperationText";
	public static final String SIGN_TEXT 				                                      = "signOperationText";
	public static final String PRINT_TEXT 				                                      = "printOperationText";
	public static final String FAX_TEXT 				                                      = "faxOperationText";
	public static final String PATIENT_FIRSTNAME 				                              = "patientfirstname";
	public static final String PATIENT_LASTNAME 				                              = "patientlastname";
	public static final String PUBLISH_TO_PORTAL_TEXT 				                          = "publishOperationText";
	public static final String DOCUMENT_TEXT 				                                  = "documentTextInPP";
	public static final String SEND_FOR_SIGNATURE_TEXT 				                          = "sendForSignatureOperationText";
	public static final String SIGNATURE_TEXT 				                                  = "signText";
	public static final String SEND_FOR_REVIEW_TEXT 				                          = "sendForReviewOperationText";
	public static final String REVIEW_TEXT 				                                      = "reviewText";
	public static final String ALLERGY_TEXT 				                                  = "pifglobaltext";
	public static final String CURRENT_LOCATION 				                              = "currentlocation";
	public static final String INTERVIEW_FORM_NAME                                            = "inteviewformname";


	public void initClass() throws Exception{
		pifData                                                                        = ExcelFileUtilty.readExcelSheet("PIF");
		demographicsData                                                               = ExcelFileUtilty.readExcelSheet("Demographics");
		profileData                                                                    = ExcelFileUtilty.readExcelSheet("Profile");
		medicalData                                                                    = ExcelFileUtilty.readExcelSheet("MedicalChart");
		DemographicsPage.existingPatientfirstname                                      = demographicsData.get(DemographicsPage.PATIENT_FIRSTNAME);	
		DemographicsPage.existingPatientlastname                                       = demographicsData.get(DemographicsPage.PATIENT_LASTNAME);
		Profile.existingProfileProviderfirstname                                       = profileData.get(Profile.PROFILE_PROVIDER_FIRSTNAME);
		Profile.existingProfileProviderlastname                                        = profileData.get(Profile.PROFILE_PROVIDER_LASTNAME);
		Profile.userNameForAdvanceDirectiveProfile                                     = profileData.get(Profile.PROFILE_USER_NAME);
		MedicalChartPage.existingPatientfirstname                                      = medicalData.get(MedicalChartPage.MEDICAL_PATIENT_FIRSTNAME);	
		MedicalChartPage.existingPatientlastname                                       = medicalData.get(MedicalChartPage.MEDICAL_PATIENT_LASTNAME);
		pifAllergyText                                                                 = pifData.get(ALLERGY_TEXT);
		currentLocation                                                                = pifData.get(CURRENT_LOCATION);
		interviewformname                                                              = pifData.get(INTERVIEW_FORM_NAME);
	}


	/**
	 * This method is used to launch PIF in the gGastro application
	 * @throws Exception 
	 */
	public void launchPIF(){
		//switching in left panel frame
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu present on LeftPanel... ");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching in  configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on interview button to launch new PIF in configuration");
		SeleniumUtil.getElementWithFluentWait(interview_icon).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
		sleep(3000);
		//switching in  user page frame
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW);
		logger.info("clicking on new button to create new interview form");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.INTERVIEW);
		//switching in  user creation page frame
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		//Get the data to create new user present in excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("PIF");
		//Get the first name present in the excel
		ConstantsFile.interviewformname = ConstantsFile.loginData.get(ConstantsFile.INTERVIEW_FORM_NAME).concat(ConstantsFile.genData.generateRandomNumber(4));
		// Get the  name present in PIF sheet in excel and type in the pif name field
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchusertextbox).sendKeys(ConstantsFile.interviewformname);
		//get the location 
		SeleniumUtil.getElementWithFluentWait(addlocation).click();
		SeleniumUtil.waitForProgressBar(Frames.INTERVIEW_CREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("selecting the created location for the user");
		WebElement locationvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
		SeleniumUtil.scrolltoWebElement(locationvalues);
		locationvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		//again switch into patient interview form creation frame
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		SeleniumUtil.getElementWithFluentWait(addservice).click();
		SeleniumUtil.waitForProgressBar(Frames.INTERVIEW_CREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		List<WebElement> checkboxvalue = driver.findElements(By.xpath("//input[@class='rtChk']"));
		checkboxvalue.get(16).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		//switching in  user creation page frame
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.INTERVIEW_CREATION);
		sleep(3000);
	}

	/**
	 * This method is used to click on interview icon present on any started service
	 * 
	 */
	public void clickOnPIF(){
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.getElementWithFluentWait(interviewIcon).click();
	}
	public void switchToPIFFrame(){
		SeleniumUtil.switchToParentFrame(Frames.PIF);
	}
	/**
	 * This method is used to click on allergy section present in PIF Module
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnallergySectionInPif(String imageName) throws FindFailed{
		//SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement allergytitle =SeleniumUtil.getElementWithFluentWait(allergyTitle);
		SeleniumUtil.scrolltoWebElement(allergytitle);
		SeleniumUtil.rightClick(allergytitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli(imageName);
	}
	public void switchToPIFUserListFrameFromService(){
		SeleniumUtil.switchToParentFrame(Frames.PIFUSERLIST);
	}
	/**
	 * This method is used to verify allergy user list in opened in PIF module
	 * 
	 */
	public boolean verifyallergyUserListInPIF(){ 
		boolean isallergyUserList=false;
		//SeleniumUtil.switchToParentFrame(Frames.PIFUSERLIST);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys("Aciphex");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		//SeleniumUtil.waitForProgressBar(Frames.PIFUSERLIST);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement valueforallergy=SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow);
		action.moveToElement(valueforallergy).doubleClick().build().perform();
		isallergyUserList=true;
		return isallergyUserList;
	}
	/**
	 * This method is used for verify allergy pop up is opened in PIF module
	 * 
	 * 
	 */
	public boolean verifyAllergyPopupInPif(){
		boolean isAllergyPopupPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		isAllergyPopupPresent =SeleniumUtil.getElementWithFluentWait(textBoxarea)!=null;
		if(isAllergyPopupPresent){
			System.out.println("Allergies Details pop up with Name populated in Allergy Name field is present");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			isAllergyPopupPresent=true;
		}
		return isAllergyPopupPresent;
	}
	/**
	 * This method is used to click Immunization section present in PIF module
	 * @throws FindFailed 
	 * 
	 * 
	 */
	public void clickOnImmunizationSectionInPif() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement immunizationtitle = SeleniumUtil.getElementWithFluentWait(immunizationtext);
		SeleniumUtil.scrolltoWebElement(immunizationtitle);
		SeleniumUtil.rightClick(immunizationtitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("addAllerrgy");
	}
	/**
	 * This method is used to verify Immunization user list in opened in PIF module
	 * 
	 */
	public boolean verifyImmunizationUserListInPIF(){
		boolean isImmunizationUserList=false;
		SeleniumUtil.switchToParentFrame(Frames.PIFUSERLIST);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys("MMR");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		SeleniumUtil.waitForProgressBar(Frames.PIFUSERLIST);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement valueforallergy=SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow);
		action.moveToElement(valueforallergy).doubleClick().build().perform();
		isImmunizationUserList=true;
		return isImmunizationUserList;
	}
	/**
	 * This method is used for verify the Immunizations popup details
	 * 
	 */
	public boolean verifyImmunizationsPopupDetails() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String VaccineName= SeleniumUtil.getElementWithFluentWait(immunizationTextBox).getText();
		if(VaccineName!=null){
			System.out.println("Immunizations field is present in the condtion detail pop");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			ConstantsFile.immunizationsdocumented=true;
		}
		else{
			System.out.println("Immunizations field is blank in the condtion detail pop");
			ConstantsFile.immunizationsdocumented=false;
		}
		return ConstantsFile.immunizationsdocumented;
	}
	/**
	 * This method is used to click Diagnostic  section present in PIF module
	 * @throws FindFailed 
	 */
	public void clickOnDiagnosticSectionInPif() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement diagnostictitle = SeleniumUtil.getElementWithFluentWait(problemtext);
		SeleniumUtil.scrolltoWebElement(diagnostictitle);
		SeleniumUtil.rightClick(diagnostictitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("addAllerrgy");
	}
	/**
	 * This method is used to verify Diagnostic  user list in opened in PIF module
	 * 
	 */
	public boolean verifyDiagnosticUserListInPIF() throws Exception{
		boolean isDiagnosticUserList=false;
		SeleniumUtil.switchToParentFrame(Frames.PIFUSERLIST);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys("EGD");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		SeleniumUtil.waitForProgressBar(Frames.PIFUSERLIST);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement dxStudiesplussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'EGD')]"));
		action.moveToElement(dxStudiesplussign).doubleClick().build().perform();
		isDiagnosticUserList=true;
		return isDiagnosticUserList;
	}
	/**
	 * This method is used for verify the DxStudies popup details
	 * 
	 */
	public boolean verifyDxStudiesPopupDetails(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String diagnosticstudyName= SeleniumUtil.getElementWithFluentWait(textBoxarea).getText();
		if(diagnosticstudyName!=null){
			System.out.println("diagnostic Study field is present in the Diagnostic Study detail pop");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			ConstantsFile.dxStudiesdocumented=true;
		}
		else{
			System.out.println("diagnostic Study field is blank in the Diagnostic Study detail pop");
			ConstantsFile.dxStudiesdocumented=false;
		}
		return ConstantsFile.dxStudiesdocumented;
	}
	/**
	 * This method is used to click Present Medical Condition  section present in PIF module
	 * @throws FindFailed 
	 */
	public void clickOnPresentMedicalConditionSectionInPif() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement presentMedicationtitle = SeleniumUtil.getElementWithFluentWait(presentMedicalConditions);
		SeleniumUtil.scrolltoWebElement(presentMedicationtitle);
		SeleniumUtil.rightClick(presentMedicationtitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("addAllerrgy");
	}
	/**
	 * This method is used to verify Present Medication user list in opened in PIF module
	 * 
	 */
	public boolean verifyPresentMedicationUserListInPIF(){
		boolean isPresentMedicationUserList=false;
		SeleniumUtil.switchToParentFrame(Frames.PIFUSERLIST);
		WebElement plussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'Asthma')]"));
		if(plussign.isDisplayed()){
			System.out.println("right panel is opened");
			action.moveToElement(plussign).doubleClick().build().perform();
			isPresentMedicationUserList=true;
		}
		return isPresentMedicationUserList;
	}
	/**
	 * This method is used to click PrviousProcedure  section present in PIF module
	 * @throws FindFailed 
	 */
	public void clickOnPrviousProcedureSectionInPif() throws Exception{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement previousProceduretitle = SeleniumUtil.getElementWithFluentWait(previousProcedure);
		SeleniumUtil.scrolltoWebElement(previousProceduretitle);
		SeleniumUtil.rightClick(previousProceduretitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("addAllerrgy");
	}
	/**
	 * This method is used to verify Previous user list in opened in PIF module
	 * 
	 */
	public boolean verifyPreviousUserListInPIF(){
		boolean isPreviousUserList=false;
		SeleniumUtil.switchToParentFrame(Frames.PIFUSERLIST);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys("ACL repair");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		SeleniumUtil.waitForProgressBar(Frames.PIFUSERLIST);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement procedureplussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'ACL repair')]"));
		if(procedureplussign.isDisplayed()){
			System.out.println("right panel is opened");
			action.moveToElement(procedureplussign).doubleClick().build().perform();
			isPreviousUserList=true;
		}
		return isPreviousUserList;
	}
	public void switchToProcedurePopupFrameInPif(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
	}
	/**
	 * This method is used for verify the Procedures popup details
	 *
	 */
	public boolean verifyProceduresPopupDetails() {
		String ProcedureName=SeleniumUtil.getElementWithFluentWait(textBoxarea).getText();
		if(ProcedureName!=null){
			System.out.println("Procedure field is present in the Procedure detail pop");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			ConstantsFile.procedureisdocumented=true;
		}
		else{
			System.out.println("Procedure Study field is blank in the Procedure detail pop");
			ConstantsFile.procedureisdocumented=false;
		}
		return ConstantsFile.procedureisdocumented;
	}
	/**
	 * This method is used to click on close button for closing the medical history section PIF module
	 * 
	 */
	public void clickOnCloseButtonInMedical(){
		SeleniumUtil.switchToParentFrame(Frames.PIFUSERLIST);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).sendKeys(Keys.ESCAPE);
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		SeleniumUtil.getElementWithFluentWait(previousProcedure).sendKeys(Keys.ESCAPE);
	}
	/**
	 * This method is used to click in Marital Status section present in PIF module
	 * 
	 */
	public void clickOnMaritalStatus(){
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement maritaltitle = SeleniumUtil.getElementWithFluentWait(maritalStatus);
		SeleniumUtil.scrolltoWebElement(maritaltitle);
		List<WebElement> maritalItems = driver.findElements(pifSection);
		for(WebElement marritalText:maritalItems){
			String meritaltextvalue =marritalText.getText();
			if(meritaltextvalue.equalsIgnoreCase("Married")){
				marritalText.click();
				SeleniumUtil.waitForProgressBar(Frames.PIF);
				break;
			}
		}
	}
	/**
	 * This method is used to click in Alcohol  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnAlcoholSectionInPif() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement alcoholtitle = SeleniumUtil.getElementWithFluentWait(alcoholTitle);
		SeleniumUtil.scrolltoWebElement(alcoholtitle);
		SeleniumUtil.rightClick(alcoholtitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("ADDButton");
	}
	/**
	 * This method is used to enter  Alcohol  details  present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void EnterAlcholDetailsInPopup() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(pifTextBox).sendKeys("Beer");
		SeleniumUtil.getElementWithFluentWait(quantityTextBox).sendKeys("5");
		SeleniumUtil.getElementWithFluentWait(freqTextBox).sendKeys("2");
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("frequencyDropdown2");
		SeleniumUtil.clickOnImageWitScreenInSikuli("frequevalue");	
	}
	/**
	 * This method is used to click in Caffeine  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnCaffeineSectionInPif() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement cafferinetitle = SeleniumUtil.getElementWithFluentWait(caffeineTitle);
		SeleniumUtil.scrolltoWebElement(cafferinetitle);
		SeleniumUtil.rightClick(cafferinetitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("ADDButton");
	}
	/**
	 * This method is used to enter intake details in Caffeine section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void EnterIntakeDetailsInPopup() throws SikuliException{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(intakeTitle).sendKeys("10 Times");	
	}
	/**
	 * This method is used to click in Tabacco Smoking  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnTabaccoSmokingStatus(){
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement tabaccotitle = SeleniumUtil.getElementWithFluentWait(tobaccoTitle);
		SeleniumUtil.scrolltoWebElement(tabaccotitle);
		List<WebElement> tabaccotitleItems = driver.findElements(pifSection);
		for(WebElement tabaccoText:tabaccotitleItems){
			String tabaccotextvalue =tabaccoText.getText();
			if(tabaccotextvalue.equalsIgnoreCase("Current every day smoker")){
				tabaccoText.click();
				SeleniumUtil.waitForProgressBar(Frames.PIF);
				break;
			}
		}
	}
	/**
	 * This method is used to click in Drug  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnDrugSectionInPif() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement drugtitle = SeleniumUtil.getElementWithFluentWait(drugTitle);
		SeleniumUtil.scrolltoWebElement(drugtitle);
		SeleniumUtil.rightClick(drugtitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("ADDButton");
	}
	/**
	 * This method is used to enter drug details in Drug  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void EnterDrugDetailsInPopup() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys("Beer");
		SeleniumUtil.getElementWithFluentWait(quantityTextBox).sendKeys("5");
		SeleniumUtil.getElementWithFluentWait(freqTextBox).sendKeys("2");
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("frequencyDropdown2");
		SeleniumUtil.clickOnImageWitScreenInSikuli("frequevalue");	
	}
	/**
	 * This method is used to click in Exercise  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnExerciseSectionInPif() throws Exception{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement exercisetitle = SeleniumUtil.getElementWithFluentWait(exerciseTitle);
		SeleniumUtil.scrolltoWebElement(exercisetitle);
		SeleniumUtil.rightClick(exercisetitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("ADDButton");
	}
	/**
	 * This method is used to enter Exercise details in Exercise  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void EnterExerciseDetailsInPopup() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(pifTextBox).sendKeys("Beer");
		SeleniumUtil.getElementWithFluentWait(quantityTextBox).sendKeys("5");
		SeleniumUtil.getElementWithFluentWait(freqTextBox).sendKeys("2");
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("frequencyDropdown2");
		SeleniumUtil.clickOnImageWitScreenInSikuli("frequevalue");	
	}
	/**
	 * This method is used to click on close button for closing the social history section PIF module
	 * 
	 */
	public void clickOnCloseButtonInSocial(){
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		SeleniumUtil.getElementWithFluentWait(exerciseTitle).sendKeys(Keys.ESCAPE);
	}
	/**
	 * This method is used to verify Alochol  is documented in the service
	 * 
	 * 
	 */
	public boolean verifyAlocholisDocumented(){
		boolean isAlcoholDocumentedInPif =false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String AlcoholText = SeleniumUtil.getElementWithFluentWait(alcoholTextBox).getText();
		if(AlcoholText.isEmpty()){
			System.out.println("The Alcohol Details  get populated in Alcohol section on PIF");
			isAlcoholDocumentedInPif=false;
		}
		else{
			isAlcoholDocumentedInPif=true;
		}
		return isAlcoholDocumentedInPif;	
	}
	/**
	 * This method is used to verify Caffeine  is documented in the service
	 * 
	 * 
	 */
	public boolean verifyCaffeineisDocumented(){
		boolean isCaffeineDocumentedInPif =false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String iCaffeineText = SeleniumUtil.getElementWithFluentWait(caffeineTextBox).getText();
		if(iCaffeineText.isEmpty()){
			System.out.println("The Caffeine Details did not get populated in Caffeine section on PIF");
			isCaffeineDocumentedInPif=false;
		}
		else{
			isCaffeineDocumentedInPif=true;
		}
		return isCaffeineDocumentedInPif;	
	}
	/**
	 * This method is used to verify Tobacco  is documented in the service
	 * 
	 * 
	 */
	public boolean verifyTobaccoisDocumented(){
		boolean isTobaccoDocumentedInPif =false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String TobaccoDocumentedtext = SeleniumUtil.getElementWithFluentWait(tobaccoTextBox).getText();
		if(TobaccoDocumentedtext.isEmpty()){
			System.out.println("The Tobacco Details did not get populated in Tobacco section on PIF");
			isTobaccoDocumentedInPif=false;
		}
		else{
			isTobaccoDocumentedInPif=true;
		}
		return isTobaccoDocumentedInPif;	
	}
	/**
	 * This method is used to verify Drug  is documented in the service
	 * 
	 * 
	 */
	public boolean verifyDrugisDocumented() throws Exception{
		boolean isDrugDocumentedInPif =false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String DrugText = SeleniumUtil.getElementWithFluentWait(drugTextBox).getText();
		if(DrugText.isEmpty()){
			System.out.println("The Drug Details did not get populated in Drug section on PIF");
			isDrugDocumentedInPif=false;
		}
		else{
			isDrugDocumentedInPif=true;
		}
		return isDrugDocumentedInPif;	
	}
	/**
	 * This method is used to verify Exercise  is documented in the service
	 * 
	 * 
	 */
	public boolean verifyExerciseisDocumented() throws Exception{
		boolean isExerciseDocumentedInPif =false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String ExerciseText= SeleniumUtil.getElementWithFluentWait(exerciseTextBox).getText();
		if(ExerciseText.isEmpty()){
			System.out.println("The Exercise Details did not get populated in Exercise section on PIF");
			isExerciseDocumentedInPif=false;
		}
		else{
			isExerciseDocumentedInPif=true;
		}
		return isExerciseDocumentedInPif;	
	}
	/**
	 * This method is used to click in Family Medical History  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnFamilyMedicalHistorySectionInPif(String imageName) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement famialyHistorytitle = SeleniumUtil.getElementWithFluentWait(familyTitle);
		SeleniumUtil.scrolltoWebElement(famialyHistorytitle);
		SeleniumUtil.rightClick(famialyHistorytitle);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli(imageName);
	}
	public void clickOnNoFamilyHistorySectionInPif() throws Exception{
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		List <WebElement> famialyHistorytitle = driver.findElements(pifSection);
		for(WebElement nohistorytext:famialyHistorytitle){
			String textvalue =nohistorytext.getText();
			if(textvalue.equalsIgnoreCase("No family history of")){
				SeleniumUtil.rightClick(nohistorytext);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("addDiagnosis");
			}
		}
	}
	/**
	 * This method is used to click in diagnostic section of Family Medical History  section present in PIF module
	 * @throws FindFailed 
	 * 
	 */
	public boolean AddDiagnosticUserListInPIF() throws Exception{
		boolean isDiagnosticUserList=false;
		SeleniumUtil.switchToParentFrame(Frames.PIFDIAGONSTICUSERLIST);
		Thread.sleep(3000);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys("D77");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		SeleniumUtil.waitForProgressBar(Frames.PIFDIAGONSTICUSERLIST);
		Thread.sleep(3000);
		//SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		Thread.sleep(3000);
		WebElement diagonstic =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'Other')]"));
		action.moveToElement(diagonstic).doubleClick().build().perform();
		isDiagnosticUserList=true;
		return isDiagnosticUserList;
	}
	/**
	 * This method is used to click in Review with section present in PIF module
	 * 
	 */
	public void clickOnReviewWithSectionInPIF(){
		SeleniumUtil.switchToParentFrame(Frames.PIF);
		WebElement reviewWithTitle = SeleniumUtil.getElementWithFluentWait(reviewWithSection);
		SeleniumUtil.scrolltoWebElement(reviewWithTitle);
		List<WebElement> reviewWithTitleItems = driver.findElements(pifSection);
		for(WebElement reviewWithTitleItemsText:reviewWithTitleItems){
			String reviewWithTitleItemsTexttextvalue =reviewWithTitleItemsText.getText();
			if(reviewWithTitleItemsTexttextvalue.equalsIgnoreCase("Patient")){
				reviewWithTitleItemsText.click();
				SeleniumUtil.waitForProgressBar(Frames.PIF);
				break;
			}
		}
	}
	/**
	 * This method is used to verify Global PIF Present Or Not
	 * @throws FindFailed 
	 */
	public boolean verifyGlobalPifPresent() throws FindFailed{
		isGlobalPifPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW);
		List<WebElement> searchGlobalPifrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF PIF DATA IN THIS TABLE = "+searchGlobalPifrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchGlobalPifrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());

				if(col_num==2 && rowText.equalsIgnoreCase("Global") || col_num==2 && rowText.equalsIgnoreCase("Main office") ){
					System.out.println("Global PIF or location specfic pIf is present Present");
					if(rowText.equalsIgnoreCase("Main office")){
						SeleniumUtil.rightClick(trElement);
						SeleniumUtil.clickOnImageWitScreenInSikuli("deleteItem");
					}
					isGlobalPifPresent=true;
					//break;
				}
				/*else if((col_num==2 && rowText.equalsIgnoreCase("Main office"))){
					SeleniumUtil.rightClick(trElement);
					SeleniumUtil.clickOnImageWitScreenInSikuli("deleteItem");
				}*/

				col_num++;
			} 
			if(isGlobalPifPresent){
				break;
			}
			else{
				row_num++;
			}

		}
		return isGlobalPifPresent;

	}

	public void launchGlobalPif() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW);
		logger.info("clicking on new button to create new interview form");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.INTERVIEW);
		//switching in  user creation page frame
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		//Get the data to create new user present in excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("PIF");
		//Get the first name present in the excel
		ConstantsFile.interviewformname = ConstantsFile.loginData.get(ConstantsFile.INTERVIEW_FORM_NAME).concat(ConstantsFile.genData.generateRandomNumber(4));
		// Get the  name present in PIF sheet in excel and type in the pif name field
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchusertextbox).sendKeys(ConstantsFile.interviewformname);
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		logger.info("clicking on allergy section in pif");
		clickOnallergySectionInPif("addAllerrgy");
		switchToPIFUserListFrameFromConfiguration();
		verifyallergyUserListInPIF();
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.INTERVIEW_CREATION);
	}
	public void switchToPIFUserListFrameFromConfiguration(){
		SeleniumUtil.switchToParentFrame(Frames.PIF_USER_LIST);

	}
	public void switchToPIFInsideFrameFromConfiguration(){
		SeleniumUtil.switchToParentFrame(Frames.PIF);

	}

	/**
	 * This method is used to verify Global PIF Present Or Not
	 * @throws FindFailed 
	 */
	public boolean deleteExistingPIf() throws FindFailed{
		isGlobalPifPresent=false;
		//SeleniumUtil.switchToParentFrame(Frames.INTERVIEW);
		List<WebElement> searchGlobalPifrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF PIF DATA IN THIS TABLE = "+searchGlobalPifrow.size());
		for(WebElement trElement : searchGlobalPifrow)
		{
			String rowText=trElement.getText();
			System.out.println(rowText);
			if(rowText.contains(this.currentLocation) && rowText.contains("Colonoscopy")){
				logger.info("Service & Location Specfic PIF Present");
				SeleniumUtil.rightClick(trElement);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deletepif");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	

				isSeviceLocation=true;
			}
			else if(rowText.contains(this.currentLocation) &&  !(rowText.contains("Colonoscopy"))){
				logger.info("Location Specfic PIF Present");
				SeleniumUtil.rightClick(trElement);

				SeleniumUtil.clickOnImageWitScreenInSikuli("deletepif");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				islocation=true;
			}
			else if(rowText.contains("Follow Up")){
				logger.info("Sevice Specfic PIF Present");
				SeleniumUtil.rightClick(trElement);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deletepif");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				isService=true;
			}

			else if( rowText.contains("Global")  ){
				System.out.println("Global PIF Present");
				SeleniumUtil.rightClick(trElement);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deletepif");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				isGlobalPifPresent=true;

			}

		} 

		return isGlobalPifPresent;

	}
	public boolean checkAllergyDataInGlobalPIF() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		boolean isGlobalPIFPresent=false;
		String text=SeleniumUtil.getElementWithFluentWait(pifAllergyData).getText();
		System.out.println("text is" +text);
		if(text.contains(pifAllergyText)){
			logger.info("Allergy Data is already Present...");
			isGlobalPIFPresent=true;
		}
		else{
			clickOnallergySectionInPif("addAllerrgy"); 
			SeleniumUtil.switchToParentFrame(Frames.PIF_USER_LIST);
			verifyallergyUserListInPIF(); 
		}
		return isGlobalPIFPresent;
	}
	public void enterNameForPIF(){
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		interviewformname = interviewformname.concat(ConstantsFile.genData.generateRandomNumber(4));
		// Get the  name present in PIF sheet in excel and type in the pif name field
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchusertextbox).sendKeys(interviewformname);
	}
	public void enterLocation(){
		SeleniumUtil.getElementWithFluentWait(addlocation).click();
		SeleniumUtil.waitForProgressBar(Frames.INTERVIEW_CREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("selecting the created location for the user");
		WebElement locationvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ currentLocation +"')]"));
		SeleniumUtil.scrolltoWebElement(locationvalues);
		locationvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	public void selectService(){
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		SeleniumUtil.getElementWithFluentWait(addservice).click();
		SeleniumUtil.waitForProgressBar(Frames.INTERVIEW_CREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		List<WebElement> checkboxvalue = driver.findElements(By.xpath("//input[@class='rtChk']"));
		checkboxvalue.get(16).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		//switching in  user creation page frame
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);	
	}
	public void savePIFDetails(){
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
	}
	public void modifyPIFData(String imageName) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW_CREATION);
		SeleniumUtil.getElementWithFluentWait(By.id("btnSections_SpanBGColor")).click();
		SeleniumUtil.clickOnImageWitScreenInSikuli("selectPatientInformation");
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");		
	}
	public void configureServiceLocationPIF(String imageName) throws FindFailed{
		enterNameForPIF();
		//enterLocation();
		//selectService();
		modifyPIFData(imageName);
		savePIFDetails();
	}
	public boolean verifyPIFData(){
		boolean isLocationService=false;
		String rowText=SeleniumUtil.getElementWithFluentWait(DocumentPage.allvisibletext).getText();
		System.out.println(rowText);
		if(!(rowText.contains("Patient Information"))){
			logger.info("Location-Service PIF is launched...");
			isLocationService=true;
		}
		return isLocationService;
	}
	public boolean verifyGlobalPIFCreated(){
		boolean isGlobalPifCreated=false;
		SeleniumUtil.switchToParentFrame(Frames.INTERVIEW);
		List<WebElement> searchGlobalPifrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF PIF DATA IN THIS TABLE = "+searchGlobalPifrow.size());
		for(WebElement trElement : searchGlobalPifrow)
		{
			String rowText=trElement.getText();
			System.out.println(rowText);
			if( rowText.contains("Global")  ){
				System.out.println("Global PIF Present");
				isGlobalPifCreated=true;	
			}
		}
		return isGlobalPifCreated;
	}
}
