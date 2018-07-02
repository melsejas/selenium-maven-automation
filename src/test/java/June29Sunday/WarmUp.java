package June29Sunday;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * navigate:
 * 		https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=wooden+spoon
 * print all item descriptions and prices 
 * 
 */
public class WarmUp {
	WebDriver driver;
	Faker data = new Faker();

	@BeforeClass 
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	// version 1
	public void getAllPrducts() {
		driver.get("https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=wooden+spoon");
		// find all descriptions
		// dont forget find elementS W an S at the end
//		List<WebElement> descriptions = driver.findElements(By.xpath("//h2"));
//		for (WebElement item : descriptions) {
//			System.out.println(item.getText());
//			// find all prices
//			List<WebElement> prices = driver.findElements(By.xpath("//span[@class='sx-price sx-price-large']"));
//			for (WebElement price : prices) {
//				System.out.println(price.getText());
//			}
//			for (int i = 0; i < descriptions.size(); i++) {
//				// printing descriptions and prices
//				System.out.println(descriptions.get(i).getText());
//				System.out.println(prices.get(i).getText());
//				System.out.println("=======================");

	//}
			//version 2 (different option)
			List<WebElement> wholeItems = driver.findElements(By.xpath("//div[@class='s-item-container']"));

			System.out.println("wholeItems.size():" + wholeItems.size());

			for (int i = 0; i < wholeItems.size(); i++) {
				if (wholeItems.get(i).getText().isEmpty()) continue;
			
			String desXpath = "(//div[@class='s-item-container'])[" + (i+1) + "]//h2";
			String priceXpath = "(//div[@class='s-item-container'])[" + (i+1) + "]//span[@class='sx-price sx-price-large']";
			
			System.out.println(driver.findElement(By.xpath(desXpath)).getText());
			System.out.println(driver.findElement(By.xpath(priceXpath)).getText());
			System.out.println("------------");
		
			}
		}

	
	@AfterClass
	public void tearDown() {
		//driver.close();
}
}