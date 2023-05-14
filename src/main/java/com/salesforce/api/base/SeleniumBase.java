package com.salesforce.api.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.selenium.api.design.Browser1;
import com.salesforce.selenium.api.design.Element;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Reporter;

public class SeleniumBase extends Reporter implements Browser1, Element {
	public RemoteWebDriver driver;
	public WebDriverWait wait;

	public void click(WebElement ele) {
		String text = "";
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			text = ele.getText();
			ele.click();
			reportStep1("The Element " + text + " clicked", "pass");
		} catch (Exception e) {
			reportStep1("The Element " + text + " could not be clicked", "fail");
			throw new RuntimeException();
		}

	}

	public void append(WebElement ele, String data) {
		ele.sendKeys(data);

	}

	public void clear(WebElement ele) {
		try {
			ele.clear();
			reportStep1("The field is cleared Successfully", "pass");
		} catch (Exception e) {
			reportStep1("The field is not Interactable", "fail");
			throw new RuntimeException();
		}
	}

	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep1("The Data :" + data + " entered Successfully", "pass");
		} catch (Exception e) {
			reportStep1("The Element " + ele + " is not Interactable", "fail");
			throw new RuntimeException();
		}

	}

	public String getElementText(WebElement ele) {
		String text = ele.getText();
		return text;
	}

	public void selectDropDownUsingText(WebElement ele, String value) {
		new Select(ele).selectByVisibleText(value);

	}

	public boolean verifyExactText(WebElement ele, String expectedText) {
		try {
			if (ele.getText().equals(expectedText)) {
				reportStep1("The expected text contains the actual " + expectedText, "pass");
				return true;
			} else {
				reportStep1("The expected text doesn't contain the actual " + expectedText, "fail");
			}
		} catch (Exception e) {
			System.out.println("Unknown exception occured while verifying the Text");
		}

		return false;
	}

	public boolean verifyPartialText(WebElement ele, String expectedText) {
		try {
			if (ele.getText().contains(expectedText)) {
				reportStep1("The expected text contains the actual " + expectedText, "pass");
				return true;
			} else {
				reportStep1("The expected text doesn't contain the actual " + expectedText, "fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		}

		return false;
	}

	public boolean verifyDisplayed(WebElement ele) {
		try {
			if (ele.isDisplayed()) {
				reportStep1("The element " + ele + " is visible", "pass");
				return true;
			} else {
				reportStep1("The element " + ele + " is not visible", "fail");
			}
		} catch (Exception e) {
			System.out.println("WebDriverException : " + e.getMessage());
		}
		return false;

	}

	public boolean verifyEnabled(WebElement ele) {
		try {
			if (ele.isEnabled()) {
				reportStep1("The element " + ele + " is Enabled", "pass");
				return true;
			} else {
				reportStep1("The element " + ele + " is not Enabled", "fail");
			}
		} catch (Exception e) {
			System.out.println("WebDriverException : " + e.getMessage());
		}
		return false;
	}

	public void verifySelected(WebElement ele) {
		try {
			if (ele.isSelected()) {
				reportStep1("The element " + ele + " is selected", "pass");
				// return true;
			} else {
				reportStep1("The element " + ele + " is not selected", "fail");
			}
		} catch (Exception e) {
			System.out.println("WebDriverException : " + e.getMessage());
		}

	}

	public RemoteWebDriver startApp(String url) {
		return startApp("Chrome", url);
	}

	public RemoteWebDriver startApp(String browser, String url) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		} catch (Exception e) {
			reportStep1("The Browser Could not be Launched. Hence Failed", "fail");
			throw new RuntimeException();
		}
		return driver;
	}

	public WebElement locateElement(String locatorType, String value) {
		try {
			switch (locatorType.toLowerCase()) {
			case "id":
				return driver.findElement(By.id(value));
			case "name":
				return driver.findElement(By.name(value));
			case "class":
				return driver.findElement(By.className(value));
			case "link":
				return driver.findElement(By.linkText(value));
			case "xpath":
				return driver.findElement(By.xpath(value));
			}
		} catch (Exception e) {
			reportStep1("The Element with locator:" + locatorType + " Not Found with value: " + value, "fail");
			throw new RuntimeException();
		}
		return null;

	}

	public WebElement locateElement(String value) {
		WebElement findElementById;
		try {
			findElementById = driver.findElement(By.id(value));
		} catch (Exception e) {
			reportStep1("The Element Not Found with value: " + value, "fail");
			throw new RuntimeException();
		}
		return findElementById;
	}

	public List<WebElement> locateElements(String type, String value) {
		try {
			switch (type.toLowerCase()) {
			case "id":
				return driver.findElements(By.id(value));
			case "name":
				return driver.findElements(By.name(value));
			case "class":
				return driver.findElements(By.className(value));
			case "link":
				return driver.findElements(By.linkText(value));
			case "xpath":
				return driver.findElements(By.xpath(value));
			}
		} catch (Exception e) {
			System.err.println("The Element with locator:" + type + " Not Found with value: " + value);
			throw new RuntimeException();
		}
		return null;
	}

	public void switchToWindow(int index) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			List<String> allhandles = new ArrayList<String>(allWindows);
			String exWindow = allhandles.get(index);
			driver.switchTo().window(exWindow);
			System.out.println("The Window With index: " + index + " switched successfully");
		} catch (Exception e) {
			System.err.println("The Window With index: " + index + " not found");
		}
	}

	public void switchToWindow(String title) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String eachWindow : allWindows) {
				driver.switchTo().window(eachWindow);
				if (driver.getTitle().equals(title)) {
					break;
				}
			}
			System.out.println("The Window With Title: " + title + "is switched ");
		} catch (Exception e) {
			System.err.println("The Window With Title: " + title + " not found");
		} finally {
			takeSnap();
		}
	}

	public void switchToFrame(int index) {
		driver.switchTo().frame(index);

	}

	public void switchToFrame(WebElement ele) {
		driver.switchTo().frame(ele);
	}

	public void switchToFrame(String idOrName) {
		driver.switchTo().frame(idOrName);

	}

	public void defaultContent() {
		driver.switchTo().defaultContent();
	}

	public boolean verifyUrl(String url) {
		if (driver.getCurrentUrl().equals(url)) {
			System.out.println("The url: "+url+" matched successfully");
			return true;
		} else {
			System.out.println("The url: "+url+" not matched");
		}
		return false;
	}

	public boolean verifyTitle(String title) {
		if (driver.getTitle().equals(title)) {
			System.out.println("Page title: "+title+" matched successfully");
			return true;
		} else {
			System.out.println("Page url: "+title+" not matched");
		}
		return false;
	}

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();

	}

	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
					new File("./reports/images/" + number + ".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

}
