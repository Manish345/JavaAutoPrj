/*This package contain functions which can be used through out the 
 * application.
 */
package reusablesMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ReusableMethods {
	
	private WebDriver driver;
	private int avgWait = 10;// In sec
	
	public ReusableMethods() {
		// TODO Auto-generated constructor stub
	}
	
	public void setUpDriver() {
		String driverResourcePath = System.getProperty("user.dir") + "\\src\\resource\\java\\drivers\\";
		System.setProperty("webdriver.chrome.driver", driverResourcePath + "chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	// This function will be used to open the application as per provided URL
	public void openApplication(String strURL) {
		driver.get(strURL);
	}
	
	// This function will be used to maximize the browser window
	public void maximizeBrowserWindow() {
		driver.manage().window().maximize() ;
	}
	
	// This function will return element of the provided element locator
	public WebElement getElementFromLocator(By elementLocator) throws TimeoutException, NoSuchElementException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, avgWait);
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
		WebElement element = this.getElementFromLocator(elementLocator);
		element.clear();
		element.sendKeys(strText);
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
	
	// This function will be used to get the chained child element
	public WebElement getChainedElement(By parentLocator, By childLocator) {
		WebElement element = this.getElementFromLocator(parentLocator);
		element = element.findElement(childLocator);
		return element;
	}
	
	// This function will be used to hover over element
	public void hoverOverLocator(WebElement testingElement) {
		Actions builder = new Actions(driver);
		builder.moveToElement(testingElement).perform();
	}
	
	// This function will be used to scroll to element
	public void scrollToElement(By elementLocator) {
		WebElement element = this.getElementFromLocator(elementLocator);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	
	// This function will be used when the element visibility is hindered by some other element
	public void scrollToElementNotVisible(By elementLocator) {
		WebElement element = this.getElementFromLocator(elementLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// This function will be used to provide defined wait time
	public void externalWait(int intWait) throws InterruptedException {
		Thread.sleep(intWait);
	}
	
	// This function will be used to download a file from specified locator at defined location
	public int downloadFile(String downloadSourceLocator, String executablePath, String downloadPath) throws IOException, InterruptedException {
		String wget_command = "cmd /c "+ executablePath +" -P "+ downloadPath +" --no-check-certificate " + downloadSourceLocator;
		Process exec = Runtime.getRuntime().exec(wget_command);
	    int exitVal = exec.waitFor();
	    System.out.println("Exit value: " + exitVal);
	    return exitVal;
	}
	
	public Boolean verifyFileExists(String strFilePath) {
		File fileObj = new File(strFilePath);
		if(fileObj.exists() && !fileObj.isDirectory()) { 
			Boolean fileExistResponse = fileObj.exists();
			return fileExistResponse;
		}
		else {
			return false;
		}	
	}
	
	public Boolean deleteFile(String strFilePath) {
		File fileObj = new File(strFilePath);
		if(fileObj.exists() && !fileObj.isDirectory()) { 
			Boolean fileExistResponse = fileObj.exists();
			return fileExistResponse;
		}
		else {
			return false;
		}	
	}
	
}
