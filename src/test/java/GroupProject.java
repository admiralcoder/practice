import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GroupProject {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().fullscreen();
		
		driver.get("http://www.weatherbase.com/");
		driver.findElement(By.linkText("North America")).click();
		driver.findElement(By.linkText("United States of America")).click();
		driver.findElement(By.linkText("District of Columbia")).click();
		
		
		String text1 = driver.findElement(By.xpath("(//td[@class='datacunit'])[1]")).getText();
		String text2 = driver.findElement(By.xpath("(//td[@class='datacunit'])[2]")).getText();
		String text3 = driver.findElement(By.xpath("(//td[@class='datacunit'])[3]")).getText();
		
		if(text1.equals("Average Temperature (F)") && text2.equals("Average High Temperature (F)") && text3.equals("Average Low Temperature (F)")) {
			System.out.println("pass");
		}else {
			System.out.println("fail");
			System.out.println(text1 + " is not a Fahrenheit type");
		}
		
		driver.close();

	}

}