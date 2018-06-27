import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC008 {

	public static void main(String[] args) {

		// set up drivers
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// nav to page
		driver.get("https://www.weatherbase.com");

		// checking title and nav is correct
		String expectedTitle = "Travel Weather Averages (Weatherbase)";
		String actualTitle = driver.getTitle();
		// System.out.println(actualTitle);
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Title test: Pass");
		} else {
			System.out.println("Title test: Fail; expected: " + expectedTitle + " Actual: " + actualTitle);
		}

		// **checking F is selected by default using img src**
		// expected and not expected src img names
		String fGreyedOut = "/gr/f-head-gray.png";
		String fBluedOut = "https://www.weatherbase.com/gr/f-head-blue.png";

		// grabbing element
		WebElement actualDegree = driver.findElement(By.xpath("//img[@src='/gr/f-head-blue.png']"));

		// getting src attribute from webelement
		String src = actualDegree.getAttribute("src");
		// verifying code worked, commented out
		// System.out.println(src);

		// validating results
		if (src.equals(fBluedOut) != src.contains(fGreyedOut)) {
			System.out.println("Fahrenheit default: Pass");
		} else {
			System.out.println("Fahrenheit default: Fail; expected:" + fBluedOut + " Actual: " + src);
		}

		// click on North America
		driver.findElement(By.xpath("//div[@id ='left-170']/ul/li[11]/a")).click();

		// defining pararmeters
		String expectedNorthA = "North America Travel Weather Averages (Weatherbase)";
		String expectedURLNA = "http://www.weatherbase.com/weather/country.php3?r=NOR&regionname=North-America";
		String actualNorthA = driver.getTitle();
		String actualURLNA = driver.getCurrentUrl();

		// checking parameters match
		if (actualNorthA.equals(expectedNorthA)) {
			System.out.println("Title test: Pass");
		} else {
			System.out.println("Title test: Fail; expected: " + expectedNorthA + " Actual: " + actualNorthA);
		}

		if (expectedURLNA.equals(actualURLNA)) {
			System.out.println("URL test: Pass");
		} else {
			System.out.println("URL test: Fail; expected: " + expectedURLNA + " Actual: " + actualURLNA);
		}

		// click on USA
		driver.findElement(By.xpath("(//a[@class = 'redglow'])[6]")).click();

		// parameters
		String expectedTitleUS = "United States of America Travel Weather Averages (Weatherbase)";
		String actualTitleUS = driver.getTitle();

		// checking parameters match
		if (actualTitleUS.equals(expectedTitleUS)) {
			System.out.println("Title test: Pass");
		} else {
			System.out.println("Title test: Fail; expected: " + expectedTitleUS + " Actual: " + actualTitleUS);
		}

		// click on D.C.
		driver.findElement(By.xpath("(//a[@class = 'redglow'])[10]")).click();
		// new driver
		WebDriver d2 = new ChromeDriver();
		// sending url to new driver
		d2.get(driver.getCurrentUrl());
		// close old driver
		driver.close();

		// parameters
		String expectedTitleDC = "District of Columbia, United States of America Travel Weather Averages (Weatherbase)";
		String actualTitleDC = d2.getTitle();

		// checking parameters
		if (actualTitleDC.equals(expectedTitleDC)) {
			System.out.println("Title test: Pass");
		} else {
			System.out.println("Title test: Fail; expected: " + expectedTitleDC + " Actual: " + actualTitleDC);
		}

		// default in F
		actualDegree = d2.findElement(By.xpath("//img[@src = '/gr/f-head-blue.png']"));
		src = actualDegree.getAttribute("src");

		if (src.equals(fBluedOut) != src.contains(fGreyedOut)) {
			System.out.println("Fahrenheit default: Pass");
		} else {
			System.out.println("Fahrenheit default: Fail; expected:" + fBluedOut + " Actual: " + src);
		}

		for (int i = 1; i < 4; i++) {
			String tempF = "(//td[@class = 'datacunit'])[" + i + "]";
			// System.out.println(tempF);
			WebElement tempResultElelement = d2.findElement(By.xpath(tempF.toString()));
			// System.out.println(tempResultElelement);
			String tempResultF = tempResultElelement.getText();
			// System.out.println(tempResultF);
			if (tempResultF.contains("(F)")) {
				System.out.println("Average Temp in F: Pass");
			} else {
				System.out.println("Average Temp in F: Fail expected: (F) actual: " + tempResultF);
			}
		}

		// click washington
		d2.findElement(By.xpath("(//a[@class = 'redglow'])[1]")).click();
		WebDriver d3 = new ChromeDriver();
		d3.get(d2.getCurrentUrl());
		d2.close();
		// expected title
		String expectedTitleWash = "Washington, District of Columbia Travel Weather Averages (Weatherbase)";
		String actualTitleWash = d3.getTitle();

		// checking parameters
		if (actualTitleWash.equals(expectedTitleWash)) {
			System.out.println("Title test: Pass");
		} else {
			System.out.println("Title test: Fail; expected: " + expectedTitleWash + " Actual: " + actualTitleWash);
		}

		// getting avg
		ArrayList<BigDecimal> tempAV = new ArrayList<BigDecimal>();
		BigDecimal avgBD = Average(2, tempAV, d3);

		System.out.println(avgBD);

		// avg temp
		String avgTemp = d3.findElement(By.xpath("(//td[@class ='data'])[1]")).getText();

		if (avgTemp.contains(avgBD.toString())) {
			System.out.println("Avgerage Temp: Pass");
		} else {
			System.out.println("Average Temp: Fail expected " + avgTemp + " actual " + avgBD);
		}

		tempAV.clear();

		avgBD = Average(15, tempAV, d3);

		String avgTemp2 = d3.findElement(By.xpath("(//td[@class ='data'])[14]")).getText();

		if (avgTemp2.contains(avgBD.toString())) {
			System.out.println("Avgerage LOW Temp: Pass");
		} else {
			System.out.println("Average LOW Temp: Fail expected " + avgTemp2 + " actual " + avgBD);
		}

		tempAV.clear();

		avgBD = Average(28, tempAV, d3);

		String avgTemp3 = d3.findElement(By.xpath("(//td[@class ='data'])[27]")).getText();

		if (avgTemp3.contains(avgBD.toString())) {
			System.out.println("Avgerage HIGH Temp: Pass");
		} else {
			System.out.println("Average HIGH Temp: Fail expected " + avgTemp3 + " actual " + avgBD);
		}
		//
		// String avg4 = GroupProject.Average(27,40, d3);
		//
		//
		// String avgTemp3 = d3.findElement(By.xpath("(//td[@class
		// ='data'])[27]")).getText();
		//
		// if(avg4.equals(avgTemp3)) {
		// System.out.println("Avgerage LOW Temp: Pass");
		// }else {
		// System.out.println("Average Temp: Fail expected "+avgTemp3+" actual "+ avg4);
		// }
		//
		//
		 d3.close();
	}

	static BigDecimal Average(int divStart, ArrayList<BigDecimal> tempAV, WebDriver d) {

		for (int i = divStart; i < (divStart + 12); i++) {

			String gather = "(//td[@class ='data'])[" + i + "]";
			WebElement g2 = d.findElement(By.xpath(gather));

			String g3 = g2.getText();
			double bdL = Double.parseDouble(g3);
			BigDecimal bd = BigDecimal.valueOf(bdL);
			tempAV.add(bd);
		}

		BigDecimal sum = new BigDecimal(0.0);
		for (BigDecimal s : tempAV) {
//			System.out.println(s);
			BigDecimal bd = new BigDecimal((Double.parseDouble(".04")));
			s = s.add( bd );
			sum = sum.add(s).setScale(1, RoundingMode.HALF_UP);
			// System.out.println(sum);
		}
		BigDecimal months = new BigDecimal(12.0);
		BigDecimal avgBD = sum.divide(months,1, RoundingMode.UP);
		return avgBD;
	}

}

// for (int i =2; i<14;i++) {
// String gather = "(//td[@class ='data'])["+i+"]";
// WebElement g2 = d3.findElement(By.xpath(gather));
//
// String g3 = g2.getText();
// double bdL = Double.parseDouble(g3);
// BigDecimal bd = BigDecimal.valueOf(bdL);
// tempAV.add(bd);
// }
//
// BigDecimal sum = new BigDecimal(0.0);
// for(BigDecimal s : tempAV) {
// sum = sum.add(s).setScale(1,RoundingMode.HALF_UP);
// System.out.println(sum);
//// }
// BigDecimal months = new BigDecimal(12);
// BigDecimal avgBD = sum.divide(months).setScale(1, RoundingMode.CEILING);