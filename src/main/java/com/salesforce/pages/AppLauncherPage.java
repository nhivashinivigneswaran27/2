package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.salesforce.testng.api.base.ProjectSpecificMethods;



public class AppLauncherPage extends ProjectSpecificMethods 
{
	
	public AppLauncherPage(RemoteWebDriver driver, ExtentTest node) {
		this.driver=driver;
		this.node=node;
	}
	public DashBoardPage clickDashboard()
	{
		
		WebElement dash = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", dash);
		return new DashBoardPage(driver, node);
	
	}


}
