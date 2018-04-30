package example;

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

		@Test
		public void testTitle() {
			String title = obj.getTitle();
			System.out.println("Title: "+title);
			Assert.assertTrue(title.contains("File Upload Demo"));
		}
		
		@Test(dependsOnMethods="testTitle")
		public void testFileUpload() throws InterruptedException {
			
			By uploadElement = By.id("uploadfile_0");
			obj.setText(uploadElement, "C:\\Users\\manish2\\Desktop\\upload_Test.txt");
			By clickIAgree = By.id("terms");
			obj.clickElement(clickIAgree);
			obj.externalWait(2000);
			By UploadFile = By.name("send");
			obj.clickElement(UploadFile);
			obj.externalWait(2000);
		}

		@BeforeClass
		public void beforeTest() throws MalformedURLException {
			System.out.println("Running Upload file test case");
			obj.setUpDriver();
			obj.maximizeBrowserWindow();
			obj.openApplication("http://demo.guru99.com/test/upload/");
		}

		@AfterClass
		public void afterTest() {
			obj.quitDriver();
		}
}
