package variousConcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class LaunchChrome {

	WebDriver driver;
	static String browser = null;
	static String url = null;

	@BeforeClass
	public static void readConfig() {

		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("src\\main\\java\\data\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
			System.out.println("Browser used: " + browser);
			System.out.println("Environment: " + url);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Before
	public void launchBrowser() throws InterruptedException {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void test1() throws InterruptedException {

		driver.get(url);
		/*String Title = driver.getTitle();
		Assert.assertEquals("Title dosen't match!!!", "Google", Title);*/
		
		String Gmail_element = driver.findElement(By.xpath("//*[@id=\"gbw\"]/div/div/div[1]/div[1]/a")).getText();
		Assert.assertEquals("Title dosen't match!!!", "Gmail1", Gmail_element);

		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");

		// Element Liberary
		// Type Name = Value
		/*WebElement SEARCH_BUTTON = driver
				.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]"));
		By SEARCH_BUTTON_LOCATOR = By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON));

		// SEARCH_BUTTON.click();
		driver.findElement(SEARCH_BUTTON_LOCATOR).click();*/

		Thread.sleep(3000);
	}

	// @Test
	public void test2() throws InterruptedException {
		driver.get("https://google.com");
		Thread.sleep(3000);
	}

	// @Test
	public void test3() throws InterruptedException {
		driver.get("https://google.com");
		Thread.sleep(3000);
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
