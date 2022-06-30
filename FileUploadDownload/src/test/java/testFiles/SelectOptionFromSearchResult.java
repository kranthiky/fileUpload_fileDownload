package testFiles;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectOptionFromSearchResult {

	WebDriver driver = null;
	
	@BeforeTest
	public void Setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@Test
	public void SearchAndSelectFromResult() throws InterruptedException {
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium");
		List<WebElement> listOfResults = driver.findElements(By.xpath("//ul[@role='listbox']//li"));
		
		for(int i=0;i<listOfResults.size();i++) {
			if( listOfResults.get(i).getText().contains("selenium testing")) {   //specific option to select
					listOfResults.get(i).click();
					break;
				}
		}
	}
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(3);
		System.out.println("Test execution completed..!");
		driver.quit();
	}
	
}
