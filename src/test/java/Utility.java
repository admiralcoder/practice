import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Utility {

	static String[] expected = { "ANNUAL ", "JAN ", "FEB ", "MAR ", "APR ", "MAY ", "JUN ", "JUL ", "AUG ", "SEP ", "OCT ",
			"NOV ", "DEC " };

	public static boolean verifyRowOneHeadings(WebDriver driver) {

		String[] actual = new String[13];

		for (int i = 1, j = 0; i <= 7; i++, j += 2) {
			actual[j] = driver.findElement(By.xpath("((//tbody/tr)[14])/td[@class='datahead1'][" + i + "]")).getText();
		}

		for (int i = 1, j = 1; i <= 6; i++, j += 2) {
			actual[j] = driver.findElement(By.xpath("((//tbody/tr)[14])/td[@class='datahead2'][" + i + "]")).getText();
		}

		for (int i = 0; i < 13; i++) {
			if (!(actual[i]).equals(expected[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean verifyRowTwoHeadings(WebDriver driver) {

		String[] actual = new String[13];

		for (int i = 1, j = 0; i <= 7; i++, j += 2) {
			actual[j] = driver.findElement(By.xpath("((//tbody/tr)[17])/td[@class='datahead1'][" + i + "]")).getText();
		}

		for (int i = 1, j = 1; i <= 6; i++, j += 2) {
			actual[j] = driver.findElement(By.xpath("((//tbody/tr)[17])/td[@class='datahead2'][" + i + "]")).getText();
		}

		for (int i = 0; i < 13; i++) {
			if (!(actual[i]).equals(expected[i])) {
				return false;
			}
		}

		return true;
	}
	
	public static boolean verifyRowThreeHeadings(WebDriver driver) {

		String[] actual = new String[13];

		for (int i = 1, j = 0; i <= 7; i++, j += 2) {
			actual[j] = driver.findElement(By.xpath("((//tbody/tr)[20])/td[@class='datahead1'][" + i + "]")).getText();
		}

		for (int i = 1, j = 1; i <= 6; i++, j += 2) {
			actual[j] = driver.findElement(By.xpath("((//tbody/tr)[20])/td[@class='datahead2'][" + i + "]")).getText();
		}

		for (int i = 0; i < 13; i++) {
			if (!(actual[i]).equals(expected[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean verifyRowFourHeadings(WebDriver driver) {

		String[] actual = new String[13];

		for (int i = 1, j = 0; i <= 7; i++, j += 2) {
			actual[j] = driver.findElement(By.xpath("((//tbody/tr)[23])/td[@class='datahead1'][" + i + "]")).getText();
		}

		for (int i = 1, j = 1; i <= 6; i++, j += 2) {
			actual[j] = driver.findElement(By.xpath("((//tbody/tr)[23])/td[@class='datahead2'][" + i + "]")).getText();
		}

		for (int i = 0; i < 13; i++) {
			if (!(actual[i]).equals(expected[i])) {
				return false;
			}
		}

		return true;
	}


}