package com.web.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.web.automation.ui.pages.SearchPage;

public class SearchTest {
	
	private WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions",/*"--headless",*/"--start-maximized");
		driver = new ChromeDriver(options);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	@Test
	public void testSearchResults() {
		driver.get("https://www.flipkart.com/");
		SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
		searchPage.clickOnSearchTextBoxAndSearchForValue("shoes");
		searchPage.assertWhetherAllTheSearchResultsContainShoesInDescription("shoe");
	}

}
