import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC0010AleG {
	WebDriver driver;
	String url = "http://www.weatherbase.com/";

	@BeforeMethod
	public void setUpMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("goes to websait");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Test
	public void verifyingPage() {

		// 1)Title of loaded page is equal to expected title "Travel Weather Averages
		// (Weatherbase)"
		String expected = "Travel Weather Averages (Weatherbase)";
		String answer = driver.getTitle();

		assertEquals(answer, expected);

		// 2) Verify the default temperature under Set Units is in Fahrenheit.??

		assertTrue(driver.findElement(By.xpath("//img[@src='/gr/f-head-blue.png']")).isDisplayed());
		System.out.println(
				"Faringate selec: " + driver.findElement(By.xpath("//img[@src='/gr/f-head-blue.png']")).isDisplayed());

		// 3) click North America
		// * Title of loaded page is equal to expected title 'North America Travel
		// Weather Averages (Weatherbase)'
		driver.findElement(By.xpath("//*[@id=\"left-170\"]/ul/li[11]/a")).click();
		String expectedNorthAmerica = "North America Travel Weather Averages (Weatherbase)";
		String answerNorthAmerica = driver.getTitle();
		assertEquals(answerNorthAmerica, expectedNorthAmerica);
		// * URL of loaded page is equal to expected URL
		// <http://www.weatherbase.com/weather/country.php3?r=NOR&regionname=North-America>

		String expectedURLNorthAmerica = "http://www.weatherbase.com/weather/country.php3?r=NOR&regionname=North-America";
		String answerURLNorthAmerica = driver.getCurrentUrl();
		assertEquals(answerURLNorthAmerica, expectedURLNorthAmerica);
		// 4) Click on United States link.Title of loaded page is equal to expected
		// title "United States of America Travel Weather Averages (Weatherbase)"
		driver.findElement(By.linkText("United States of America")).click();
		String expectedUnitedStates = "United States of America Travel Weather Averages (Weatherbase)";
		String answerUnitedStates = driver.getTitle();
		assertEquals(answerUnitedStates, expectedUnitedStates);
		// 5) Click on District of Columbia link.*Title of loaded page is equal to
		// expected title "District of Columbia, United States of America Travel Weather
		// Averages (Weatherbase)"

		driver.findElement(By.linkText("District of Columbia")).click();
		String expectedDistrictOfColumbia = "District of Columbia, United States of America Travel Weather Averages (Weatherbase)";
		String answerDistrictOfColumbia = driver.getTitle();
		assertEquals(answerDistrictOfColumbia, expectedDistrictOfColumbia);

		// 6) Click on Washington link. Title of loaded page is equal to expected title
		// "Washington, District of Columbia Travel Weather Averages (Weatherbase)"

		driver.findElement(By.linkText("Washington")).click();
		String expectedWashington = "Washington, District of Columbia Travel Weather Averages (Weatherbase)";
		String answerWashington = driver.getTitle();
		assertEquals(answerWashington, expectedWashington);

		// 7) Click C on Set Unit link to change from imperial to metric system. Check
		// if C button under Set Unit is selected
		// img[@src='/gr/c-head-gray-flare.png']
		driver.findElement(By.xpath("//img[@src='/gr/c-head-gray.png']")).click();
		System.out.println("Celcius is selected:"
				+ driver.findElement(By.xpath("//img[@src='/gr/c-head-blue.png']")).isSelected());
		assertTrue(driver.findElement(By.xpath("//img[@src='/gr/c-head-blue.png']")).isDisplayed());
		// 8) Verify the numeric values from MONTHLY - WEATHER AVERAGES SUMMARY table
		// under Average Temperature are present.
		// Actual result should match expected result of numeric values to be present
		// inside of the table for Average Temperature for ANNUAL and JAN-DEC tabs

		String temp = driver.findElement(By.xpath("//*[@id=\"left-weather-content\"]/div/table[2]/tbody/tr[2]/td[2]"))
				.getText().replace(".", "");
		
		for (int i = 0; i < temp.length(); i++) {
			assertTrue(Character.isDigit(temp.charAt(i)));
			System.out.println();
			}
		}
		
}
