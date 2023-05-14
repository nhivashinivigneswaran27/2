package com.salesforce.selenium.api.design;

import org.openqa.selenium.WebElement;

public interface Element {
	public void click(WebElement ele);

	public void append(WebElement ele, String data);

	public void clear(WebElement ele);

	public void clearAndType(WebElement ele, String data);

	public String getElementText(WebElement ele);

	public void selectDropDownUsingText(WebElement ele, String value);

	public boolean verifyExactText(WebElement ele, String expectedText);

	public boolean verifyPartialText(WebElement ele, String expectedText);

	public boolean verifyDisplayed(WebElement ele);

	public boolean verifyEnabled(WebElement ele);

	public void verifySelected(WebElement ele);

}
