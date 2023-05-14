package com.salesforce.testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.salesforce.pages.LoginPage;
import com.salesforce.testng.api.base.ProjectSpecificMethods;

public class LoginTestCase extends ProjectSpecificMethods{
	@BeforeTest
	public void setValues() {
		testCaseName = "Login";
		testDescription = "Login Positive Scenario";
		nodes = "Dashboard";
		authors = "Nhivashini";
		category = "Smoke";
		dataSheetName = "Login";
	}

	@Test(dataProvider = "fetchData")
	public void createLeaf(String uName, String pwd) throws IOException {
		new LoginPage(driver, node)
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickLogin().clickApp();
		
}
}
