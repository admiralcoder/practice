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
	}
	
	@Test
	public void testOne() {
		driver.get("http://www.weatherbase.com/");
		driver.findElement(By.linkText("North America")).click();
		driver.findElement(By.linkText("United States of America")).click();
		driver.findElement(By.linkText("District of Columbia")).click();
		
		String actual1 = driver.findElement(By.xpath("(//td[@class='datacunit'])[1]")).getText();
		actual1 = actual1.substring(actual1.length()-3);
		String actual2 = driver.findElement(By.xpath("(//td[@class='datacunit'])[2]")).getText();
		actual2 = actual2.substring(actual2.length()-3);
		String actual3 = driver.findElement(By.xpath("(//td[@class='datacunit'])[3]")).getText();
		actual3 = actual3.substring(actual3.length()-3);
		
		String expected = "(F)";
		
		Assert.assertEquals(actual1, expected);
		Assert.assertEquals(actual2, expected);
		Assert.assertEquals(actual3, expected);
		
		System.out.println("pass");

		}
		
	@AfterMethod
	public void tearDown(){
		driver.close();
	}

}