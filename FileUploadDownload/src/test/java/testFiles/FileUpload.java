package testFiles;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUpload {
	WebDriver driver;
	JavascriptExecutor js;
	
	@BeforeTest
	public void SetUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		List<String> handles= (List<String>) driver.getWindowHandles(); why can't List be used for getWindowHandles- check return type of method :P
		
	}
	
//	@Test(priority = 1)
	public void fileUploadBySendKeys() throws InterruptedException {
		driver.get("https://www.monsterindia.com");
		driver.findElement(By.xpath("//a//span[text()='Upload Resume']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("file-upload")).sendKeys("C:/Users/DELL/Desktop/sanjay.docx");
		Thread.sleep(5000);
		
	}
	
	@Test(priority = 2)
	public void fileUploadByRobotClass() throws InterruptedException {
		driver.get("https://www.monsterindia.com");
		driver.findElement(By.xpath("//a//span[text()='Upload Resume']")).click();
		try {
			Robot robot = new Robot();
			robot.delay(2000);
			
			WebElement btn_fileUpload = driver.findElement(By.id("file-upload"));
			js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", btn_fileUpload);
			
			//1. Copy filepath to a clipboard
			StringSelection filePath = new StringSelection("C:\\Users\\DELL\\Desktop\\sanjay.docx");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath,null);
		//	System.out.println(filePath);
			
			//2. Press CTRL and Press V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(500); 
			robot.keyPress(KeyEvent.VK_V);
						
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(2000);
			
			//3. Press ENTER
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
		} catch (AWTException e) {
		
			e.printStackTrace();
		}		
	}
}
