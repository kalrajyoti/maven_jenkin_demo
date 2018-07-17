package com.gmed.test;

import static com.gmed.utils.StringUtility.stringContainsCsv;



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

import com.gmed.pages.ReportPage;
import com.gmed.utils.ConstantsFile;


public class CQMReport extends BaseTestClass {
		/** Logger to log the MUReport log messages */
		private static Logger logger  = LogManager.getLogger(CQMReport.class); 
		/**Assertion to verify different elements of the page */
		private Assertion cqmAssert = new Assertion();
		/** Login Page reference used to login for logging in for test  */
		private LoginPage loginPageObj;
		/** Report page reference used for generating report data in report  module*/
		private ReportPage reportpageobj;
		/** Document page reference used for generating report data in report  module*/
		private DocumentPage docupageobj;
	
		@BeforeClass
		public void initClass() throws Exception{
			logger.info("inside the initClass method for CQM test class....");
			loginPageObj                                = new LoginPage();
			leftPanelpageobj                            = new LeftPanelPage();
			reportpageobj                               = new ReportPage();
			docupageobj                                 = new DocumentPage();
			logger.info("Getting the excel Data for CQM test class....");
			reportpageobj.initClass();
		
	}
		@Test(description = "To verify that  CQM report can run successfully and verify corrects fields are displayed on CQM Report Screen. ",groups = { "CQMReport_Regression" },priority=1)
		public void verifyCQMReport() throws Exception{
			logger.info("logging into gmed application...");
			loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
			cqmAssert.assertTrue(loginPageObj.verifyHomePageTitle());
			logger.info("clicking on Report menu & Select CQM Report present in left panel...");
			leftPanelpageobj.clickOnReport("clickOnCQM");
			logger.info("verify CQM report Page is displayed");
			reportpageobj.switchToCQMFrame();
			cqmAssert.assertTrue(reportpageobj.verifyReportScreen("Clinical Quality Measures"));
			logger.info("select provider ,desination & date range in the cqm report");
			reportpageobj.switchToCQMFrame();
			logger.info("adding report details for generationg data..");
			reportpageobj.addReportDetails(ReportPage.existingProfileProviderfirstname,ReportPage.existingProfileProviderlastname);
			docupageobj.clickOnOkay();
			logger.info("verify all the results show for selected  measures for selected provider.");
			cqmAssert.assertTrue(stringContainsCsv(reportpageobj.verifyCQMData(), ReportPage.cqmMeasures));
			logger.info("logging out from the application");
			leftPanelpageobj.clickOnLogout();
		}
		@AfterClass()
		public void classTearDown() {
			loginPageObj           = null;
			leftPanelpageobj       = null;
			reportpageobj          = null;
			docupageobj            = null;
			logger.info("Exiting the classTearDown method for CQM test class \n\n");
		}
}
