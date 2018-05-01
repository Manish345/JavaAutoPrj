package example;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import reusablesMethods.ReusableMethods;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class firstTestCase {

	ReusableMethods obj = new ReusableMethods();

	@Test
	public void testTitle() {
		String title = obj.getTitle();
		System.out.println("Title: "+title);
		Assert.assertTrue(title.contains("Demo Guru99 Page"));
		By strLocator = By.xpath("//a[@title='Home' and @style='font-weight: 500;']");
		obj.getText(strLocator);
	}
	
	@Test(dependsOnMethods="testTitle")
	public void testHoverOverElement() {
		By parentLocator = By.xpath("//li[@class='item118 parent']");
		By childLocator = By.xpath("a[@class='item']");
		WebElement hoverOverElement = obj.getChainedElement(parentLocator, childLocator);
//		By testingElement = By.xpath("//li[@class='item118 parent']/a[@class='item']");
		obj.hoverOverLocator(hoverOverElement);
		By testingOption = By.xpath("//a[@class='item' and contains(text(), 'QTP')]");
		obj.clickElement(testingOption);
	}

	@BeforeClass
	public void beforeTest() throws MalformedURLException {
		System.out.println("Running First test case");
		obj.setUpDriver();
		obj.maximizeBrowserWindow();
		obj.openApplication("http://demo.guru99.com/test/guru99home/");
	}

	@AfterClass
	public void afterTest() {
		obj.quitDriver();
	}
}