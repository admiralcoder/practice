import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC006 {
	
	WebDriver driver;

	@BeforeMethod
	public void setUp(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test //step1
	public void stepOne() { 
		driver.get("http://www.weatherbase.com/");
		String expected = "Travel Weather Averages (Weatherbase)";
		String actual = driver.getTitle();
		assertEquals(actual, expected);
		
		//step2
		driver.findElement(By.linkText("North America")).click();
		expected = "North America Travel Weather Averages (Weatherbase)";
		actual = driver.getTitle();
		assertEquals(actual, expected);
		
		String expectedURL = "http://www.weatherbase.com/weather/country.php3?r=NOR&regionname=North-America";
		String actualURL = driver.getCurrentUrl();
		assertEquals(actualURL, expectedURL);
		
		//step3
		driver.findElement(By.partialLinkText("United States of America")).click();
		expected = "United States of America Travel Weather Averages (Weatherbase)";
		actual = driver.getTitle();
		assertEquals(actual, expected);
		
		//step4
		driver.findElement(By.linkText("District of Columbia")).click();
		expected = "District of Columbia, United States of America Travel Weather Averages (Weatherbase)";
		actual = driver.getTitle();
		assertEquals(actual, expected);
		
		//step5
		String expected1 = "Average Temperature (F)";
		String expected2 = "Average High Temperature (F)";
		String expected3 = "Average Low Temperature (F)";
		
		String actual1 = driver.findElement(By.xpath("(//td[@class='datacunit'])[1]")).getText();
		String actual2 = driver.findElement(By.xpath("(//td[@class='datacunit'])[2]")).getText();
		String actual3 = driver.findElement(By.xpath("(//td[@class='datacunit'])[3]")).getText();
		
		assertEquals(actual1, expected1);
		assertEquals(actual2, expected2);
		assertEquals(actual3, expected3);

		}
		
	@AfterMethod
	public void tearDown(){
		driver.close();
	}

}