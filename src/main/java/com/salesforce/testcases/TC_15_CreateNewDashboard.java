package com.salesforce.testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.salesforce.pages.LoginPage;
import com.salesforce.testng.api.base.ProjectSpecificMethods;

public class TC_15_CreateNewDashboard extends ProjectSpecificMethods {
	@BeforeTest
	public void setValues() {
		testCaseName = "TC_15_CreatNewDashboard";
		testDescription = "Create New Dashboard";
		nodes = "Dashboard Creation";
		authors = "Nhivashini";
		category = "Smoke";
		dataSheetName = "Login";
	}

	@Test(dataProvider = "fetchData")
	public void createLeaf(String uName, String pwd) throws IOException, InterruptedException {

		new LoginPage(driver, node).enterUserName(uName).enterPassword(pwd).clickLogin().clickApp()

				.clickViewAll().clickDashboard().newDash()
				.switchframe().enterDashName().clickCreate().switchframe().clickSave()
				.defaultFrame().clickDahboardLink().verifyCreatedDashboard();
	}
}
