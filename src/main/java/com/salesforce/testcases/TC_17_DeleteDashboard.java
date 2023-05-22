package com.salesforce.testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.salesforce.pages.LoginPage;
import com.salesforce.testng.api.base.ProjectSpecificMethods;

public class TC_17_DeleteDashboard extends ProjectSpecificMethods {
	@BeforeTest
	public void setValues() {
		testCaseName = "TC_17_DeleteNewDashboard";
		testDescription = "Delete Dashboard";
		nodes = "Delete Dashboard";
		authors = "Nhivashini";
		category = "Smoke";
		dataSheetName = "Login";
	}

	@Test(dataProvider = "fetchData")
public void createLeaf(String uName, String pwd) throws IOException, InterruptedException {
	new LoginPage(driver, node)
	.enterUserName(uName)
		.enterPassword(pwd)
	.clickLogin().clickApp().clickViewAll().clickDashboard()
	  .searchCreatedDashName().clickCreatedName().clickDelete().clickDahboardLink().verifyDeleteDashboard();
		
}
}
