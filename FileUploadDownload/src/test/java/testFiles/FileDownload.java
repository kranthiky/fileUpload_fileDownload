package testFiles;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FileDownload {

	WebDriver driver;
	File folder;
	JavascriptExecutor js;
	
	@BeforeTest
	public void setUp() {
	
		System.setProperty("webdriver.chrome.driver", "P:/Softwares/chromedriver_win32_102/chromedriver.exe");
		folder = new File(System.getProperty("user.dir")+"/FileDownlodFolder");
		folder.mkdir();
		
		ChromeOptions options = new ChromeOptions();
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", folder.getAbsolutePath());
		
		options.setExperimentalOption("prefs", prefs);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
	}
	@Test
	public void downloadFile() throws InterruptedException {		
		
		driver.get("http://www.lancsngfl.ac.uk/cmsmanual/index.php?category_id=14");
		WebElement doc_link = driver.findElement(By.xpath("//a[text()='.doc - a Word Document']"));
		js =  (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", doc_link);
		
		doc_link.click();
		Thread.sleep(5000);
		
		File listOfFiles[] = folder.listFiles();
		Assert.assertTrue(listOfFiles.length>0);
		
		for(File file: listOfFiles)
			Assert.assertTrue(file.length()>0);		
	}
	
	@AfterTest
	public void clearFolder() {
		driver.quit();
		for(File file: folder.listFiles())
			file.delete();
		folder.delete();
	}
	
}
