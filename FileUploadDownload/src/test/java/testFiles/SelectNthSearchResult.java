package testFiles;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectNthSearchResult {

	WebDriver driver = null;
	
	@BeforeTest
	public void Setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@Test
	public void SearchAndSelectNthResult() {
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("Selenium");
		List<WebElement> listOfSearchResults = driver.findElements(By.xpath("//ul[@role='listbox']//li"));
		
		for(int i=0;i<listOfSearchResults.size();i++) {
			if(i==2)									//3rd result
				{
					System.out.println(listOfSearchResults.get(i).getText());
					listOfSearchResults.get(i).click();
					break;
				}
		}
	}
	
}
