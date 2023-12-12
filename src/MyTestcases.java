import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.radians;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyTestcases {

	String Url = "https://www.almosafer.com/en";
	WebDriver driver = new EdgeDriver();

	SoftAssert softassert = new SoftAssert();

	@BeforeTest
	public void myBeforeTest() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));
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

	@Test(enabled = false)

	public void CheckTheDateOfTheWebSite() {

//		
//		System.out.println(today);
//		System.out.println(today.plusDays(2));
//		System.out.println(today.plusDays(28));

		LocalDate today = LocalDate.now();
//
//		System.out.println(today);
//
//		System.out.println(today.getDayOfWeek().plus(1).getDisplayName(TextStyle.FULL, Locale.ENGLISH));

		// Actual Values on the Website
		String ActualNameMonth = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-hvvHee cuAEQj']"))
				.getText();
		int ActualDayAsNumber = Integer.parseInt(driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText());
		String ActualNameOftheDay = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']"))
				.getText();

// expected Values that i am as qa expected 
		String ExpectedNameMonth = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		int ExpectedDayAsNumber = today.plusDays(1).getDayOfMonth();

		String ExpectedNameOftheDay = today.plusDays(1).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

		Assert.assertEquals(ActualNameMonth, ExpectedNameMonth);
		Assert.assertEquals(ActualDayAsNumber, ExpectedDayAsNumber);
		Assert.assertEquals(ActualNameOftheDay, ExpectedNameOftheDay);

	}

	@Test(enabled = true)
	public void HotelTabSwitch() throws InterruptedException {
		Thread.sleep(1000);
		Random rand = new Random();
		String[] arabicCities = { "دبي", "جدة" };
		String[] englishCities = { "dubai", "jeddah", "riyadh", "amman", "muscat" };

		int RandomArabicCity = rand.nextInt(arabicCities.length);
		int RandomEnglishCity = rand.nextInt(englishCities.length);

		String[] myWebSites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int randomNumber = rand.nextInt(myWebSites.length);
		driver.get(myWebSites[randomNumber]);
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		Thread.sleep(3000);

		if (driver.getCurrentUrl().contains("ar")) {
			WebElement SearchAboutHotelTab = driver
					.findElement(By.xpath("//input[@placeholder='البحث عن فنادق أو وجهات']"));

			SearchAboutHotelTab.sendKeys(arabicCities[RandomArabicCity] + Keys.ENTER);

			driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
			Thread.sleep(10000);
			WebElement mySelectElement = driver
					.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
			Select selector = new Select(mySelectElement);
			selector.selectByIndex(rand.nextInt(2));
			String resultsFound = driver
					.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
			Assert.assertEquals(resultsFound.contains("وجدنا"), true);

			driver.findElement(By.xpath("//button[contains(text(),'الأقل سعراً')]")).click();
			Thread.sleep(2000);

		} else {
			WebElement SearchAboutHotelTab = driver
					.findElement(By.xpath("//input[@placeholder='Search for hotels or places']"));

			SearchAboutHotelTab.sendKeys(englishCities[RandomEnglishCity] + Keys.ENTER);
			driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();

			Thread.sleep(10000);
			WebElement mySelectElement = driver
					.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
			Select selector = new Select(mySelectElement);
			selector.selectByIndex(rand.nextInt(2));

			String resultsFound = driver
					.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
			Assert.assertEquals(resultsFound.contains("found"), true);
			driver.findElement(By.xpath("//button[normalize-space()='Lowest price']")).click();
			Thread.sleep(2000);

		}

		WebElement rightSection = driver.findElement(By.xpath("//div[@class='sc-htpNat KtFsv col-9']"));
		List<WebElement> Prices = rightSection.findElements(By.className("Price__Value"));

		int LowestPrice = 0;
		int HighestPrice = 0;

		for (int i = 0; i < Prices.size(); i++) {

			LowestPrice = Integer.parseInt(Prices.get(0).getText());
			HighestPrice = Integer.parseInt(Prices.get(Prices.size() - 1).getText());

			Assert.assertEquals(LowestPrice < HighestPrice, true);

		}
		System.out.println(LowestPrice + " this is the lowest price ");
		System.out.println(HighestPrice + " this is the highest price ");

	}

	@AfterTest
	public void myAfterTest() {

	}

}
