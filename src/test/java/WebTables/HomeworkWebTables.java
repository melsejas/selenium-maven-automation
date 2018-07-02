package WebTables;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * homework
  1) goto https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8
	2) Create a HashMap
	3) change row number to 100, read all data on first page and put uniquID as a KEY 
	and Applicant info as a Value to a map. 
	
	applicants.put(29,"Amer, Sal-all@dsfdsf.com-554-434-4324-130000")
	
	4) Click on next page , repeat step 3
	5) Repeat step 4 for all pages 
	6) print count of items in a map. and assert it is matching
	with a number at the buttom
	======================================
 */
public class HomeworkWebTables {

	WebDriver driver;
	String url = "https://forms.zohopublic.com/murodil/report/Applicants/reportperma/DibkrcDh27GWoPQ9krhiTdlSN4_34rKc8ngubKgIMy8";
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	@Test
	public void listApplicants() {
		
	}
}
