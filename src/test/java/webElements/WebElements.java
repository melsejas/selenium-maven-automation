package webElements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	WebDriver driver;
	Faker data = new Faker();

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test 
	public void myLinks() {
		driver.get("https://github.com");
		//find multiple elements .."a" each link on the html page has a tag name 'a'
		List<WebElement> links = driver.findElements(By.tagName(("a")));
		
		//how many links on github home page
		int numberOfLinksOnGithub = links.size();
		System.out.println("number of links:" + numberOfLinksOnGithub);
		
		//print text of each link 
		//for (WebElement link : links) {
					//OR 
		for (WebElement link : links) {
			if (!link.getText().isEmpty()) { //to remove empty text when printing in console 
				System.out.println(link.getText());
			}
		}
		//add each link text into a list of Strings 
		List<String>  linkNames = new ArrayList<>();
		
		//links= a list of webelemnts
		for (WebElement link : links) {
			if(!link.getText().isEmpty()) {
				linkNames.add(link.getText());
			}
		}
		System.out.println(linkNames.toString());
	}
		/*
		 * navigate to page 
		 * https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg
		 * find all input boxes and assing to list of webelements --2
		 * find all drop down boxes and assing to another List of webelements --3
		 * find all check boxes and assing to another List of webelements --9
		 * find all radio buttoms and assing to another List of webelements --9
		 * find all buttons and assing to another List of webelements --1
		 * assert each one's count
		 */
		@Test
		public void SeleniumWebElementsForm() {
			driver.get("https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
			List<WebElement> inputBoxes = driver.findElements(By.xpath(("//input[@type='text']")));
			List<WebElement> dropDownBoxes = driver.findElements(By.tagName(("Select")));
			List<WebElement> checkBoxes = driver.findElements(By.xpath(("//input[@type='checkbox']")));
			List<WebElement> radioButtons = driver.findElements(By.xpath(("//input[@type='radio']")));
			List<WebElement> buttons = driver.findElements(By.tagName(("button")));
			
			assertEquals(inputBoxes.size(), 2);
			assertEquals(dropDownBoxes.size(), 3);
			assertEquals(checkBoxes.size(), 9);
			assertEquals(radioButtons.size(), 9, "Message will show if it fails");
			assertEquals(buttons.size(), 1);
		
			/*
			 * HOMEWORK:
			 * loop through each inputbox and random names
			 * loop through each dropbox and randomly select by index 
			 * loop through each checkboxes and select each one
			 * loop through each radiobuttons and click one by one by waiting one second intervals
			 * click all buttons
			 */
		//loop through each inputbox and random names	
		for (int i = 0; i < inputBoxes.size(); i++) {
			String input = data.name().fullName();
			inputBoxes.get(i).sendKeys(input);
		}
		//loop through each dropbox and randomly select by index
		for (int i = 0; i < dropDownBoxes.size(); i++) {
			int drop = data.number().numberBetween(0, 4);
			Select select = new Select(dropDownBoxes.get(i));
			select.selectByIndex(drop);

		}
		//loop through each checkboxes and select each one
		for (int i = 0; i < checkBoxes.size(); i++) {

			checkBoxes.get(i).click();

		}
		//loop through each radiobuttons and click one by one by waiting one second intervals
		for (int i = 0; i < radioButtons.size(); i++) {

			radioButtons.get(i).click();
			
		}

	}
}