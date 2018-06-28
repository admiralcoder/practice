import static org.testng.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC014 {
	/*
	 * Verify MONTHLY - WEATHER AVERAGES SUMMARY [ Show All Data ] is not displayed
	 * (Negative test)
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

	@Test
	public void verifyHomeTitle() {

		String actual = "Travel Weather Averages (Weatherbase)";
		String expected = driver.getTitle();

		Assert.assertEquals(actual, expected);

	}

	@Test(dependsOnMethods = { "verifyHomeTitle" })
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

	@Test(dependsOnMethods = { "verifyDefaultTemperature" })
	public void verifyUSTitle() {

		WebElement titleLinkElement = driver.findElement(By.linkText("United States"));
		titleLinkElement.click();

		String actual = driver.getTitle();
		String expected = "United States of America Travel Weather Averages (Weatherbase)";

		Assert.assertEquals(actual, expected);

	}

	@Test(dependsOnMethods = { "verifyUSTitle" })
	public void verifyColumbiaTitle() {

		WebElement titleLinkElement = driver.findElement(By.linkText("District of Columbia"));
		titleLinkElement.click();

		String actual = driver.getTitle();
		String expected = "District of Columbia, United States of America Travel Weather Averages (Weatherbase)";

		Assert.assertEquals(actual, expected);

	}

	@Test(dependsOnMethods = { "verifyColumbiaTitle" })
	public void verifyWashingtonTitle() {

		WebElement titleLinkElement = driver.findElement(By.linkText("Washington"));
		titleLinkElement.click();

		String actual = driver.getTitle();
		String expected = "Washington, District of Columbia Travel Weather Averages (Weatherbase)";

		Assert.assertEquals(actual, expected);

	}

	@Test(dependsOnMethods = { "verifyWashingtonTitle" })
	public void verify_MONTHLY_WEATHER_AVERAGES_SUMMARY_Not_Displayed() {
		driver.findElement(By.xpath("//font/a[@class='grayglow'][contains(text(),'Show')]")).click();

		String actual=driver.findElement(By.xpath("//div[@id='headerfont'][contains(text(),'MONTHLY -')]")).getText(); 
		// returns MONTHLY - ALL WEATHER AVERAGES   [ Show Summary ]
		String expected="MONTHLY - WEATHER AVERAGES SUMMARY ";
		assertFalse(actual.contains(expected));
		
		 actual = driver.findElement(By.xpath("//font/a[@class='grayglow'][contains(text(),'Show')]")).getText(); //returns 'Show Summary'
		 expected = "Show All Data";
		assertFalse(actual.equals(expected));
		
	}
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
