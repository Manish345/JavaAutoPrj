package example;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import reusablesMethods.ReusableMethods;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class firstTestCase {

	/*
	 * private WebDriver driver;
	 * 
	 * @Test public void testEasy() {
	 * driver.get("http://demo.guru99.com/test/guru99home/"); String title =
	 * driver.getTitle(); Assert.assertTrue(title.contains("Demo Guru99 Page")); }
	 * 
	 * @BeforeTest public void beforeTest() {
	 * System.setProperty("webdriver.chrome.driver",
	 * "C:\\Work\\Softwares\\Selenium\\Webdrivers\\chromedriver.exe"); driver = new
	 * ChromeDriver(); }
	 * 
	 * @AfterTest public void afterTest() { driver.quit(); }
	 */
	
	ReusableMethods obj = new ReusableMethods();

	@Test
	public void testEasy() {
		String title = obj.getTitle();
		System.out.println("Title: "+title);
		Assert.assertTrue(title.contains("Demo Guru99 Page"));
		By strLocator = By.xpath("//a[@title='Home' and @style='font-weight: 50000;']");
		obj.getText(strLocator);
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		obj.openApplication();
	}

	@AfterTest
	public void afterTest() {
		obj.quitDriver();
	}
}