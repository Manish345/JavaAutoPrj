package example;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import reusablesMethods.ReusableMethods;

public class testUploadFile {

		ReusableMethods obj = new ReusableMethods();

		@Test(priority=0)
		public void testTitle() {
			String title = obj.getTitle();
			System.out.println("Title: "+title);
			Assert.assertTrue(title.contains("File Upload Demo"));
		}
		
		@Test(priority=1)
		public void testFileUpload() throws InterruptedException {
			
			String strFilePath = System.getProperty("user.dir") + "\\src\\resource\\java\\downloads\\msgr11us.exe";
			obj.verifyFileExists(strFilePath);
			
			obj.openApplication("http://demo.guru99.com/test/upload/");
			String uploadFilePath = System.getProperty("user.dir") + "\\src\\resource\\java\\uploadFiles\\upload_Test.txt";
			By uploadElement = By.id("uploadfile_0");
			obj.setText(uploadElement, uploadFilePath);
			By clickIAgree = By.id("terms");
			obj.clickElement(clickIAgree);
			By uploadFile = By.name("send");
			obj.clickElement(uploadFile);
		}
		
		@Test(priority=1)
		public void testFileDownload() throws InterruptedException, IOException {
			obj.openApplication("http://demo.guru99.com/test/yahoo.html");
			WebElement downloadButton = obj.getElementFromLocator(By.id("messenger-download"));
			String downloadSourceLocator = downloadButton.getAttribute("href");
			String executablePath = System.getProperty("user.dir") + "\\src\\resource\\java\\executables\\wget.exe";
			String downloadPath = System.getProperty("user.dir") + "\\src\\resource\\java\\downloads";
			int responseCode = obj.downloadFile(downloadSourceLocator, executablePath, downloadPath);
			assertEquals(responseCode, 0);
		}

		@BeforeClass
		public void beforeClass() throws MalformedURLException {
			System.out.println("Running Upload file test case");
			obj.setUpDriver();
			obj.maximizeBrowserWindow();
			obj.openApplication("http://demo.guru99.com/test/upload/");
		}

		@AfterClass
		public void afterClass() {
			obj.quitDriver();
		}
}
