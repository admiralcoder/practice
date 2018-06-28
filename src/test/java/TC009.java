import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC009 {
	WebDriver driver;
	String actualTitle;
	String expectedTitle;
	String actualUrl;
	String expectedUrl;

	@BeforeClass
	public void setup() {
		System.out.println("Setting up WebDriver");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test(priority = 0) // step 1
	public void verifyTitle() {
		driver.get("http://weatherbase.com");

		actualTitle = driver.getTitle();
		expectedTitle = "Travel Weather Averages (Weatherbase)";
		assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 1) // step2
	public void checkDefaultF() {
		assertTrue(driver.findElement(By.xpath("//img[@src='/gr/f-head-blue.png']")).isEnabled());
	}

	@Test(priority = 2) // step 3
	public void clickNA() {
		driver.findElement(By.linkText("North America")).click();
		// title
		actualTitle = driver.getTitle();
		expectedTitle = "North America Travel Weather Averages (Weatherbase)";
		assertEquals(actualTitle, expectedTitle);
		// url
		actualUrl = driver.getCurrentUrl();
		expectedUrl = "http://www.weatherbase.com/weather/country.php3?r=NOR&regionname=North-America";
		assertEquals(actualUrl, expectedUrl);
	}

	@Test(priority = 3) // step 4
	public void clickOnUSAlink() {
		driver.findElement(By.linkText("United States of America")).click();

		actualTitle = driver.getTitle();
		expectedTitle = "United States of America Travel Weather Averages (Weatherbase)";
		assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 4) // step 5
	public void clickOnDClink() {
		driver.findElement(By.linkText("District of Columbia")).click();

		actualTitle = driver.getTitle();
		expectedTitle = "District of Columbia, United States of America Travel Weather Averages (Weatherbase)";
		assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 5) // step 6
	public void clickonWashington() {
		driver.findElement(By.linkText("Washington")).click();

		actualTitle = driver.getTitle();
		expectedTitle = "Washington, District of Columbia Travel Weather Averages (Weatherbase)";
		assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 6) // step 7
	public void verifyNumericValues() {

		List<WebElement> tempEls = driver.findElements(By.xpath("//div[@class=\"p402_premium\"]//td[@class=\"data\"]"));
		List<String> temps = new ArrayList<String>();

		for (int i = 0; i < 13; i++) {
			if (tempEls.get(i).isDisplayed())
				temps.add(tempEls.get(i).getText());
			assertEquals(tempEls.get(i).getText(), temps.get(i));
		}

		// System.out.println(temps);
		// System.out.println("==========================================");

	}
}
