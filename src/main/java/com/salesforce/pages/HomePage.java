package com.salesforce.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.salesforce.testng.api.base.ProjectSpecificMethods;


public class HomePage extends ProjectSpecificMethods {
	public HomePage(RemoteWebDriver driver, ExtentTest node) throws IOException {
		this.driver = driver;
		this.node = node;
	}

	public AppsPage clickApp() throws IOException, InterruptedException {
		Thread.sleep(5000);
		click(driver.findElement(By.className("slds-icon-waffle")));
		return new AppsPage(driver, node);
	}

//	public AppLauncherPage viewAllApp() {
//		WebElement viewAll = driver.findElement(By.xpath("(//button[@class='slds-button'])[2]"));
//		viewAll.click();
//		return new AppLauncherPage(driver);

	}


