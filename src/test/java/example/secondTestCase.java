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

public class secondTestCase {

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
//		By testingElement = By.className("item118 parent").className("item");
		By testingElement = By.xpath("//li[@class='item118 parent']/a[@class='item']");
		obj.hoverOverLocator(testingElement);
		By testingOption = By.xpath("//a[@class='item' and contains(text(), 'QTP')]");
		obj.clickElement(testingOption);
	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		System.out.println("Running Second test case");
		obj.openApplication();
	}

	@AfterTest
	public void afterTest() {
		obj.quitDriver();
	}
}