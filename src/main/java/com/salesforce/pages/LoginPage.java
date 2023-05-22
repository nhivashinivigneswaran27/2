package com.salesforce.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.salesforce.testng.api.base.ProjectSpecificMethods;


public class LoginPage extends ProjectSpecificMethods {

	public LoginPage(RemoteWebDriver driver, ExtentTest node) throws IOException {
		this.driver = driver;
		this.node = node;

	}

	public LoginPage enterUserName(String data) {
		clearAndType(locateElement("id", "username"), data);
		return this;
	}

	public LoginPage enterPassword(String data) {
		clearAndType(locateElement("id", "password"), data);
		return this;
	}

	public HomePage clickLogin() throws IOException, InterruptedException {
		
		click(driver.findElement(By.id("Login")));
		return new HomePage(driver, node);
	}


}
