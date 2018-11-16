package com.web.automation.ui.pages;

import java.util.List;

import org.testng.asserts.SoftAssert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
	
	private SoftAssert sAssert;
	
	private WebDriver driver;
	
	@FindBy(xpath="//button[contains(text(),'✕')]")
	private WebElement closeLoginPopup;
	
	@FindBy(css="input[type='text']")
	private WebElement searchTextBox;
	
	@FindBy(css="button[type='submit']")
	private WebElement searchSubmitButton;
	
	@FindBy(xpath="//*[contains(text(),'results for')]")
	private WebElement searchResultsPageText;
	
	@FindBy(xpath="//div[@data-tkid]/a[2]")
	private List<WebElement> searchResultsItems;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		sAssert = new SoftAssert();
	}
	
	public void clickOnSearchTextBoxAndSearchForValue(String searchValue) {
		closeLoginPopup.click();
		searchTextBox.sendKeys(searchValue);
		searchSubmitButton.click();
	}
	
	public void assertWhetherAllTheSearchResultsContainShoesInDescription(String expectedValue) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchResultsPageText));
		for(WebElement element : searchResultsItems) {
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
			String actualValue = element.getText().toLowerCase();
			System.out.println("Actual Value: "+actualValue+"; Expected Value: "+ expectedValue);
			sAssert.assertEquals(stringContainCheck(actualValue,expectedValue), true);
		}
		sAssert.assertAll();
	}
	
	public boolean stringContainCheck(String wholeString, String subsetString) {
		if(wholeString.contains(subsetString)) {
			return true;
		}
		return false;
	}
}
