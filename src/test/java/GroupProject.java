import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GroupProject {

	public static void verifyingAction(String answer, String expected) {
		System.out.println(answer);
		if (expected.equals(answer)) {
			System.out.println("Test pass, we good");
		} else {
			System.out.println("Test fail");
			System.out.println("Expected result :" + expected);
			System.out.println("Reality :" + answer);
		}
	}

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		String url = "http://www.weatherbase.com/";
		driver.get(url);

		// 1)Title of loaded page is equal to expected title "Travel Weather Averages
		// (Weatherbase)"
		String expected = "Travel Weather Averages (Weatherbase)";
		String answer = driver.getTitle();
		verifyingAction(answer, expected);

		// 2) Verify the default temperature under Set Units is in Fahrenheit.??

		driver.findElement(By.xpath("//img[@src='/gr/f-head-blue.png']")).click();
		
		// 3) click North America
		// * Title of loaded page is equal to expected title 'North America Travel
		// Weather Averages (Weatherbase)'

		driver.findElement(By.xpath("//*[@id=\"left-170\"]/ul/li[11]/a")).click();
		String expectedNorthAmerica = "North America Travel Weather Averages (Weatherbase)";
		String answerNorthAmerica = driver.getTitle();
		verifyingAction(answerNorthAmerica, expectedNorthAmerica);
		// * URL of loaded page is equal to expected URL
		// <http://www.weatherbase.com/weather/country.php3?r=NOR&regionname=North-America>

		String expectedURLNorthAmerica = "http://www.weatherbase.com/weather/country.php3?r=NOR&regionname=North-America";
		String answerURLNorthAmerica = driver.getCurrentUrl();
		verifyingAction(answerURLNorthAmerica, expectedURLNorthAmerica);

		// 4) Click on United States link.Title of loaded page is equal to expected
		// title "United States of America Travel Weather Averages (Weatherbase)"
		driver.findElement(By.linkText("United States of America")).click();
		String expectedUnitedStates = "United States of America Travel Weather Averages (Weatherbase)";
		String answerUnitedStates = driver.getTitle();
		verifyingAction(answerUnitedStates, expectedUnitedStates);

		// 5) Click on District of Columbia link.*Title of loaded page is equal to
		// expected title "District of Columbia, United States of America Travel Weather
		// Averages (Weatherbase)"

		driver.findElement(By.linkText("District of Columbia")).click();
		String expectedDistrictOfColumbia = "District of Columbia, United States of America Travel Weather Averages (Weatherbase)";
		String answerDistrictOfColumbia = driver.getTitle();
		verifyingAction(answerDistrictOfColumbia, expectedDistrictOfColumbia);

		// 6)  Click on Washington link. Title of  loaded page is equal to expected title "Washington, District of Columbia Travel Weather Averages (Weatherbase)"
		
		driver.findElement(By.linkText("Washington")).click();
		String expectedWashington = "Washington, District of Columbia Travel Weather Averages (Weatherbase)";
		String answerWashington = driver.getTitle();
		verifyingAction(answerWashington, expectedWashington);
		
		// 7) Click C on Set Unit link to change from imperial to metric system. Check if C button under Set Unit is selected
		
		driver.findElement(By.name("fc")).click();
		System.out.println("Celcius is selected:" + driver.findElement(By.name("fc")).isSelected());
		
		//8) Verify the numeric values from MONTHLY - WEATHER AVERAGES SUMMARY table under Average Temperature  are present.
		//Actual result should match expected result of numeric values to be present inside of the table for Average Temperature for ANNUAL  and JAN-DEC tabs
		
		// Thread.sleep(30000);
		// // closing tab that was previusly open
		// driver.close();
	}

}
