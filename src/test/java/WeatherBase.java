import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WeatherBase {
	WebDriver driver;

	@Test
	public void setupOne() {

		WebDriverManager.chromedriver().setup();
		// 1. Launch application with URL
		driver = new ChromeDriver();
		driver.get("http://www.weatherbase.com/");
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String actualTitle = driver.getTitle();
		String expectedTitle = "Travel Weather Averages (Weatherbase)";

		assertEquals(actualTitle, expectedTitle);
	}
		// 2. Verify the default temps, under 'Set Units', is in Fahrenheit.
		// *Actual result is equal to expected result.
		// Try isSelected().
		@Test
		public void setUpTwo() {
		//WebElement fahrenheight = driver.findElement(By.xpath("//img[@src='/gr/f-head-blue.png']"));
			WebElement fahrenheight = driver.findElement(By.xpath("//*[@src='/gr/f-head-blue.png']"));
	
			
			//*[@id='/gr/f-head-blue.png']
		//WebElement fahrenheight=driver.get(
		Assert.assertTrue(fahrenheight.isDisplayed());
		}
	
	// 3. Click North America
	// Expected: Title of loaded page == expected title North America Travel Weather
	// Averages (Weatherbase)
	// Expected: URL of loaded page == L
	// <http://www.weatherbase.com/weather/country.php3?r=NOR&regionname=North-America>

	@Test
	public void setupThree() {

		driver.findElement(By.partialLinkText("North America")).click();
		String actualTitle2 = driver.getTitle();
		String expectedTitle2 = "North America Travel Weather Averages (Weatherbase)";

		Assert.assertEquals(actualTitle2, expectedTitle2);

	}

//	@Test
//	public void setUpFour() {
//
//		driver.findElement(By.xpath("//*[contains(text(),'United')]")).click();
//
//		String actualTitle3 = driver.getTitle();
//		String expectedTitle3 = "United States of America Travel Weather Averages (Weatherbase)";
//		Assert.assertEquals(actualTitle3, expectedTitle3);
//
//	}
}
