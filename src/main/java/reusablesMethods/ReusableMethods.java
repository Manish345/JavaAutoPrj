/*This package contain functions which can be used through out the 
 * application.
 */
package reusablesMethods;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;

public class ReusableMethods {
	
	private WebDriver driver;
	private int avgWait = 10;// In sec
	
	// This function will be used to open the application as per provided URL
	public void openApplication() {
		String driverResourcePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\";
		System.setProperty("webdriver.chrome.driver",driverResourcePath + "chromedriver.exe");
		System.out.println(System.getProperty("webdriver.chrome.driver"));
	    driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/guru99home/");
	}
	
	public void openApplication1() {
		driver.get("http://demo.guru99.com/test/guru99home/");
	}
	public void chromeGridSetup() throws MalformedURLException {
		String nodeURL = "http://192.168.99.1:5555/wd/hub";
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WIN10);
		driver = new RemoteWebDriver(new URL(nodeURL), capability);
	}
	
	public void internetExplorerGridSetup() throws MalformedURLException {
		String nodeURL1 = "http://192.168.99.1:5555/wd/hub";
		DesiredCapabilities capability1 = DesiredCapabilities.internetExplorer();
		
		/*org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(nodeURL1)
		     .setFtpProxy(nodeURL1)
		     .setSslProxy(nodeURL1);
		capability1.setCapability(CapabilityType.PROXY, proxy);*/
		//capability1.setBrowserName("internet explorer");
		//capability1.setPlatform(Platform.WIN10);
		capability1.setCapability("timeouts.implicit" , 1000);
		capability1.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		//capability1.setBrowserName(browserName);
////		capability1.setCapability("ignoreZoomSetting", true);
		driver = new RemoteWebDriver(new URL(nodeURL1), capability1);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MINUTES);
	}
	
//	public void openApplication() throws MalformedURLException {
//		chromeGridSetup();
//		driver.get("http://demo.guru99.com/test/guru99home/");
//	}
	
	// This function will be used to maximize the browser window
	public void maximizeBrowserWindow() {
		driver.manage().window().maximize() ;
	}
	
	// This function will return element of the provided element locator
	public WebElement getElementFromLocator(By elementLocator) throws TimeoutException,NoSuchElementException {
		try {
			WebDriverWait wait=new WebDriverWait(driver, avgWait);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			return element;
		}
		catch(TimeoutException err) {
			System.out.println("TimeOut Exception");
			throw new TimeoutException("Timeout while finding an element");
		}
		catch(NoSuchElementException err) {
			System.out.println("Element not found Exception");
			throw new NoSuchElementException("Element not found Exception");
		}
	}
	
	// This function will wait for the provided  time and return element
	public WebElement getElementFromLocatorWithDefinedWait(By elementLocator, int waitForLocator) {
		WebDriverWait wait=new WebDriverWait(driver, waitForLocator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		return element;
	}
	
	// This function will be used to click the element
	public void clickElement(By elementLocator) {
		this.getElementFromLocator(elementLocator).click();
	}
	
	// This function will be used to enter the data
	public void setText(By elementLocator, String strText) {
		this.getElementFromLocator(elementLocator).sendKeys(strText);
	}
	
	//This function will be used to select an element on the basis of element's value tag 
	public void selectDropDownByValue(By elementLocator, String strText) {
		Select dropDownLocator = new Select(this.getElementFromLocator(elementLocator));
		dropDownLocator.selectByValue(strText);
	}
	
	//This function will be used to select an element on the basis of element's visible text 
	public void selectDropDownByVisibleText(By elementLocator, String strText) {
		Select dropDownLocator = new Select(this.getElementFromLocator(elementLocator));
		dropDownLocator.selectByVisibleText(strText);
	}
	
	// This function will be used to get the text of the element
	public String getText(By elementLocator) {
		WebElement element = this.getElementFromLocator(elementLocator);
		String text = element.getText();
		return text;
	}
	
	// This function will return application title
	public String getTitle() {
		String titleText = driver.getTitle();
		return titleText;
	}
	
	// This function will close the browser window
	public void closeDriver() {
		driver.close();
	}
	
	// This function will quit the browser window
	public void quitDriver() {
		driver.quit();
	}
	
	public void hoverOverLocator(By hoverOverElement) {
		Actions builder = new Actions(driver);
		WebElement element = getElementFromLocator(hoverOverElement);
		builder.moveToElement(element).perform();
	}

}
