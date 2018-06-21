import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC012 {

	/*
	 * Verify the heading of columns in Monthly Weather Averages table are displayed
	 * (Positive test)
	 */

	WebDriver driver;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		String url = "http://www.weatherbase.com/";
		driver.get(url);

		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void verifyHomeTitle() {

		String actual = "Travel Weather Averages (Weatherbase)";
		String expected = driver.getTitle();

		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2)
	public void verifyDefaultTemperature() {

		try {
			WebElement defaultFElement = driver.findElement(By.xpath("//img[@src='/gr/f-head-blue.png']"));
			Assert.assertTrue(defaultFElement.isDisplayed()); // verifies F is selected

		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Farenheit Not Selected By Default\n");

			WebElement defaultCElement = driver.findElement(By.xpath("//img[@src='/gr/f-head-gray.png']"));
			Assert.assertFalse(defaultCElement.isDisplayed()); // verifies C is not selected
		}

	}

	@Test (priority=3)
	public void verifyUSTitle(){
		
		WebElement titleLinkElement=driver.findElement(By.linkText("United States"));
		titleLinkElement.click();
		
		String actual=driver.getTitle();
		String expected="United States of America Travel Weather Averages (Weatherbase)";
		
		Assert.assertEquals(actual,expected);
		
	}
	
	
	@Test (priority=4)
	public void verifyColumbiaTitle(){
		
		WebElement titleLinkElement=driver.findElement(By.linkText("District of Columbia"));
		titleLinkElement.click();
		
		String actual=driver.getTitle();
		String expected="District of Columbia, United States of America Travel Weather Averages (Weatherbase)";
		
		Assert.assertEquals(actual,expected);
		
	}
	
	@Test (priority=5)
	public void verifyWashingtonTitle(){
		
		WebElement titleLinkElement=driver.findElement(By.linkText("Washington"));
		titleLinkElement.click();
		
		String actual=driver.getTitle();
		String expected="Washington, District of Columbia Travel Weather Averages (Weatherbase)";
		
		Assert.assertEquals(actual,expected);
		
	}
	
	
	@Test (priority=6)
	public void verifyHeadingsOfColumns() {
	
		boolean result= Utility.verifyRowTwoHeadings(driver) && Utility.verifyRowTwoHeadings(driver) && Utility.verifyRowThreeHeadings(driver) && Utility.verifyRowFourHeadings(driver); 
	Assert.assertTrue(result);
	
	
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
