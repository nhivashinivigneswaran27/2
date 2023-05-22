package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.salesforce.testng.api.base.ProjectSpecificMethods;



public class AppsPage extends ProjectSpecificMethods  {
	public AppsPage(RemoteWebDriver driver,  ExtentTest node)
	{
		this.driver=driver;
		this.node=node;
	}
	public AppLauncherPage clickViewAll() throws InterruptedException
	{
		Thread.sleep(5000);
	click(driver.findElement(By.xpath("(//button[@class='slds-button'])[2]")));
	return new AppLauncherPage(driver, node);
	
	}
}
