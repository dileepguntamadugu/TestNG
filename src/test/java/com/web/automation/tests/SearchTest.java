package com.web.automation.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.web.automation.ui.pages.SearchPage;

public class SearchTest {
	
	private WebDriver driver;
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions",/*"--headless",*/"--start-maximized","--no-sandbox");
		DesiredCapabilities capability = DesiredCapabilities.chrome();  
		//driver = new ChromeDriver(options);
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capability);
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
