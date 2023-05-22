package com.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import com.salesforce.testng.api.base.ProjectSpecificMethods;

public class DashBoardPage extends ProjectSpecificMethods {

	/**
	 * Constructor method to pass driver session ID, to invoke parallel execution
	 */
	public DashBoardPage(RemoteWebDriver driver, ExtentTest node) {

		this.driver = driver;
		this.node=node;
	}

	/**
	 * Dashboard page consists of following pages 1. DashboardListPage This page has
	 * methods like Create New Dashboard, Verification for created, Edited and
	 * Deleted Dashboard Names, Searching of Dashboard Names
	 */

	public DashBoardPage newDash() {
		click3("New Dashboard");

		return new DashBoardPage(driver, node);

	}

	public void verifyCreatedDashboard() {
		verifyPartialText(driver.findElement(By.xpath("//a[contains(@title,'" + createDashboardName + "')]")),
				createDashboardName);
	}

	public DashBoardPage searchCreatedDashName() {

		searchName(driver.findElement(By.xpath("//input[contains(@class,'search-text-field slds-input')]")),
				createDashboardName);
		return new DashBoardPage(driver, node);

	}

	

	public DashBoardPage clickCreatedName() {
		click(driver.findElement(By.xpath("//a[contains(@title,'"+ createDashboardName +"')]")));
		return new DashBoardPage(driver, node);
	}
	
	public void verifyEditDashboard() {
		verifyPartialText(driver.findElement(By.xpath("//a[contains(@title,'"+ createDashboardName +"')]")),
				createDashboardName);
	}

	public void verifyDeleteDashboard() {
		verifyPartialText(driver.findElement(By.xpath("//span[text()='No results found']")), "No results found");
	}

	/**
	 * 2. DashboardViewPage * This page has methods like Switch Frame, Save the
	 * Dashboard details, Navigate back to Dashboard List page, Edit the Dashboard
	 * details, Delete the Dashboard details.
	 */
	public DashBoardPage switchframe() throws InterruptedException {
		switchToFrame(driver.findElement(By.xpath("//div[@class='dashboardContainer']//iframe")));
		return new DashBoardPage(driver, node);
	}
	

	public DashBoardPage clickSave() throws InterruptedException {
		click2("Save");
		return new DashBoardPage(driver, node);
	}

	public DashBoardPage defaultFrame() throws InterruptedException {
		defaultContent();
		return new DashBoardPage(driver, node);
	}

	public DashBoardPage clickDahboardLink() {

		click1("Dashboards");
		return new DashBoardPage(driver, node);

	}

	public DashBoardPage clickEdit() throws InterruptedException {
		click(driver.findElement(By.xpath("//div[@class='slds-page-header__info']//button[text()='Edit']")));
		return new DashBoardPage(driver, node);
	}

	public DashBoardPage clickDelete() {
		try {
			switchframe();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		click(driver.findElement(By.xpath(
				"//div[contains(@class,'slds-dropdown-trigger slds-dropdown-trigger_click slds-button_last')]")));
		click1("Delete");
		click2("Delete");
		return new DashBoardPage(driver, node);
	}
	/**
	 * 3. EditDashboardPage * This page has methods like Clicking Edit icon and Type
	 * the name
	 */
	
	public DashBoardPage clickEditicon() throws InterruptedException {
		Thread.sleep(5000);
		click1("Edit Dashboard name");
		return new DashBoardPage(driver, node);
	}

	
	public DashBoardPage clearAndEditName() {

		faker = new Faker();
		createRandomName = faker.name().firstName();
	createDashboardName = createRandomName + "_Workout";
		clearAndType(driver.findElement(By.xpath("//input[@id='edit-dashboard-title']")), createDashboardName);
		System.out.println("Edit:"+createDashboardName);
		return new DashBoardPage(driver, node);
	}

	/**
	 * 4. NewDashboardPage * This page has methods like Typing name, Clicking Create
	 * button for new Dashbord
	 */

	public DashBoardPage enterDashName(){

		// Enter the Dashboard name as "YourName_Workout"
		faker = new Faker();
		createRandomName = faker.name().firstName();
	createDashboardName = createRandomName + "_Workout";
		append(driver.findElement(By.xpath("//input[@id='dashboardNameInput']")), createDashboardName);
		System.out.println("Create:"+createDashboardName);
		return new DashBoardPage(driver, node);
	}

	public DashBoardPage clickCreate() {
		click2("Create");

		return new DashBoardPage(driver, node);
	}

}
