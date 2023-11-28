import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyTestcases {

	String Url = "https://www.almosafer.com/en";
	WebDriver driver = new ChromeDriver();

	SoftAssert myAssertion = new SoftAssert();

	@BeforeTest
	public void myBeforeTest() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get(Url);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/button[1]")).click();
	}

	@Test(enabled = false)
	public void checktheLanguage() {

		String ActualLanguageofTheWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguageofTheWebsite, "en", "this is to test the langauge");

	}

	@Test(enabled = false)
	public void checktheCurrency() {
		WebElement CurrencyOnTheWebSite = driver.findElement(By.className("fPnvOO"));

		String CurrencyActual = CurrencyOnTheWebSite.getText();
		Assert.assertEquals(CurrencyActual, "SAR");
	}

	@Test(enabled = false)
	public void ContactNumberCheck() {
		WebElement MobileNumberOnTheWebSite = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));

		String ActualMobileNumber = MobileNumberOnTheWebSite.getText();

		String ExpectedActualMobile = "+966554400000";

		Assert.assertEquals(ActualMobileNumber, ExpectedActualMobile);
	}

	@Test(enabled = false)

	public void checkQitafLogoIsThere() {
		WebElement Footer = driver.findElement(By.tagName("footer"));

		boolean isQitafDisplayed = Footer.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']//*[name()='svg']"))
				.isDisplayed();

		Assert.assertEquals(isQitafDisplayed, true);

	}

	@Test(enabled = false)
	public void CheckHotelTabIsNotSelected() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValue = HotelTab.getAttribute("aria-selected");
		Assert.assertEquals(ActualValue, "false");
	}

	@Test(invocationCount = 10, enabled = false)
	public void changeTheLanguageofTheWebsiteRandomly() throws InterruptedException {

		String[] myWebSites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		Random rand = new Random();

		int randomNumber = rand.nextInt(myWebSites.length);
		driver.get(myWebSites[randomNumber]);

		String myWebSiteURL = driver.getCurrentUrl();

		if (myWebSiteURL.contains("ar")) {
			String ActualLanguageofTheWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");

			Assert.assertEquals(ActualLanguageofTheWebsite, "ar", "this is to test the langauge");

		} else if (myWebSiteURL.contains("en")) {

			String ActualLanguageofTheWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(ActualLanguageofTheWebsite, "en", "this is to test the langauge");

		}
	}

	@Test()

	public void CheckTheDateOfTheWebSite() {

		LocalDate today = LocalDate.now();

		int expectedDepatureDate = today.plusDays(1).getDayOfMonth();

		int expectedReturnDate = today.plusDays(2).getDayOfMonth();

		WebElement ActualDepatureDateOnTheWebSite = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement ActualReturnDateOfTheWebSite = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));

		String ExpectedWelcomeMsg = "Let’s book your next trip! soso"; 
		String ActualWelcomeMsg = driver.findElement(By.xpath("//h1[contains(text(),'Let’s book your next trip!')]")).getText();
		
		// faliure 
		myAssertion.assertEquals(Integer.parseInt(ActualReturnDateOfTheWebSite.getText()), 600,"this is for the return date ");

		myAssertion.assertEquals(ActualWelcomeMsg, ExpectedWelcomeMsg,"we are checking the welcome msg bs we got an error");
		
		
		// pass 
		
		myAssertion.assertEquals(Integer.parseInt(ActualDepatureDateOnTheWebSite.getText()), 29);

		myAssertion.assertAll();

	}

	public void myAfterTest() {
	}

}
