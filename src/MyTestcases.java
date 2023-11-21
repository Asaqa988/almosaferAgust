import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyTestcases {

	String Url = "https://www.almosafer.com/ar";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void myBeforeTest() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get(Url);
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/button[1]")).click();
	}

	@Test()
	public void checktheLanguage() {

		String ActualLanguageofTheWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguageofTheWebsite, "ar", "this is to test the langauge");

	}

	@Test()
	public void checktheCurrency() {
		String ActualCurrencyonTheWebSite = driver.findElement(By.xpath("//button[normalize-space()='SAR']")).getText();

		Assert.assertEquals(ActualCurrencyonTheWebSite, "SAR", "this is to test the currency");
	}

	public void myAfterTest() {
	}

}
