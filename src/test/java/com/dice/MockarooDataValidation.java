package com.dice;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooDataValidation {

	WebDriver driver;
	List<String> cities = new ArrayList<String>();
	List<String> countries = new ArrayList<String>();

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@BeforeMethod // runs once for the whole tests
	public void navigateToHomePage() {
		driver.get("https://mockaroo.com/");
	}

	// Step3
	@Test
	public void assertTitle() {
		assertTrue(driver.getTitle().contains("Mockaroo"));
	}

	// Step4
	@Test
	public void ifMockarooIsDisplayed() {

		String expected1 = "mockaroo";
		String expected2 = "realistic data generator";

		String actual1 = driver.findElement(By.xpath("//div[@class='brand']")).getText();
		String actual2 = driver.findElement(By.xpath("//div[@class='tagline']")).getText();

		assertEquals(actual1, expected1);
		assertEquals(actual2, expected2);
	}

	// Step5
	@Test
	public void removeX() {

		List<WebElement> el = driver.findElements(By.xpath("//a[@class='close remove-field remove_nested_fields']"));

		for (WebElement webElement : el) {
			webElement.click();
		}
	}

	// Step6
	@Test
	public void areDisplayed() {
		String actual = driver.findElement(By.xpath("//div[@class='column column-header column-name']")).getText();
		String expected = "Field Name";
		Assert.assertEquals(actual, expected);

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='column column-header column-type']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='column column-header column-options']")).isDisplayed());
	}

	// Step7
	@Test
	public void addAnother() {
		assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']"))
				.isEnabled());

	}

	// Step 8
	@Test
	public void assertDefaultNumberOfRows() {

		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1000']")).isDisplayed());
	}

	// Step 9
	@Test(priority = 7)
	public void ifDefaultIsCVS() {
		String expected = "CSV";
		String actual = driver.findElement(By.xpath("//select[@id='schema_file_format']/option[1]")).getText();
		assertEquals(actual, expected);
	}

	// step 10
	@Test
	public void lineEnding() {
		String actual = driver.findElement(By.xpath("//*[.='Unix (LF)']")).getText();
		String expected = "Unix (LF)";
		assertEquals(actual, expected);
	}

	// Step11
	@Test
	public void checkedUnchecked() {

		WebElement header = driver.findElement(By.xpath("//*[@id=\"schema_include_header\"]"));
		WebElement bom = driver.findElement(By.xpath("//input[@name='schema[bom]']"));

		Assert.assertTrue(header.isSelected());
		Assert.assertFalse(bom.isSelected());

	}

	// Step 12
	@Test(priority = 10) 
	public void addAnotherFieldAndTypeSomething() {
		
		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
		driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[7]")).sendKeys("City");
	}
	//Step 13
	@Test
	public void assertChooseTypeIsDisplayed() throws InterruptedException {
		driver.findElement(By.xpath("//input[@data-action='type']")).click();
	
		Thread.sleep(2000);
		String choose = driver.findElement(By.xpath("//h3[@class='modal-title']")).getText();
		String expected = "Choose a Type";
		Assert.assertEquals(choose, expected);
	}
	
	//Step 14
	// Search for “city” and click on City on search results.
	@Test
	public void searchCity() {

		driver.findElement(By.xpath("//input[@class='btn btn-default']")).click();
		driver.findElement(By.id("type_search_field")).sendKeys("City");
		driver.findElement(By.xpath("//div[@class='examples']")).click();

	}
	//Step 15 Repeat steps 12-14 with field name and type “Country”
	 @Test 
	public void fieldNameNType() {

		driver.findElement(By.xpath("//a[@class='btn btn-default add-column-btn add_nested_fields']")).click();
		driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[2]/input")).clear();
		driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[2]/input")).sendKeys("Country");
		// Click on Choose type and assert that Choose a Type dialog box is displayed.
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[3]/input[3]")).isDisplayed());
        // Click on ‘Add another field’ and enter name “Country”
        driver.findElement(By.xpath("//div[@id='fields']/div[8]/div[3]/input[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"type_search_field\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"type_search_field\"]")).sendKeys("country");
        driver.findElement(By.xpath("(//div[@class='type-name'])[1]")).click();
	}
	        
	//Step 16 Click on Download Data.
	@Test
	public void download() {
		driver.findElement(By.id("download")).click();
		
	}
	
	//step 17 Open the downloaded file using BufferedReader
	@Test
	public void downloadBufferRea() {
		anaylzeFile("/home/ussr/Downloads/MOCK_DATA.csv");
	}
	
	//Step18 Assert that first row is matching with Field names that we selected.
	@Test
	public void firstRow() {
		String firstLine = cities.get(0) + countries.get(0);
		String expected = "CityCountry";
		assertEquals(firstLine, expected);
	}

	//Step19 Assert that there are 1000 records
	@Test
	public void oneHunRecords() {
		assertEquals(cities.size() - 1, 1000);
	}
	//Step20 From file add all Cities to Cities ArrayList
	//Step21 Add all countries to Countries ArrayList
	//Step22 Sort all cities and find the city with the longest name and shortest name
	@Test
	public void findAllCitiesWithLongestName() {
		Collections.sort(cities);
		System.out.println("Shortest named city :\t" + shortLong(cities)[0]);
		System.out.println("Shortest named city :\t" + shortLong(cities)[1]);

	}
	//Step23-25 In Countries ArrayList, find how many times each Country is mentioned. and print out
	//ex: Indonesia-10
	//Russia-7 etc
	@Test 
	public void inCountriesArrayLis() {
		Set<String> sortedCountry = new TreeSet<>(countries);
		for (String country : sortedCountry) {
			System.out.println(country + "-" + Collections.frequency(countries, country));
			// 24-25. From file add all Cities to citiesSet HashSet
			Set<String> uniqueCities = new HashSet<>(cities);
			// 25. Count how many unique cities are in Cities ArrayList and assert that it
			// is matching with the count of citiesSet HashSet.
			int NumberOfUniqueCities = uniqueCities.size();
			// from:
			// https://stackoverflow.com/questions/12719998/how-to-count-unique-values-in-an-arraylist
			int NumberOfUniqueCitiesExpected = (int) cities.stream().distinct().count();
			assertEquals(NumberOfUniqueCities, NumberOfUniqueCitiesExpected);

		}
	}
	//26. Add all Countries to countrySet HashSet
	//27. Count how many unique cities are in Countries ArrayList and assert that it is matching 
	//with the count of countrySet HashSet.
	@Test
	public void uniqueCountries() {
		Set<String> uniqueCountries = new HashSet<>(countries);
		int NumberOfUniqueCountries = uniqueCountries.size();
		int NumberOfUniqueCountriesExpected = (int) countries.stream().distinct().count();
		assertEquals(NumberOfUniqueCountries, NumberOfUniqueCountriesExpected);
	}

	public String[] shortLong(List<String> cities2) {
		// this method finds out the longest and shortest words in a String List
		// it returns a two dimensional String Array
		// index[0] stores the shortest word
		// index[1] stores the longest word
		String min = cities.get(0);
		String max = cities.get(0);
		for (String city : cities2) {
			if (city.length() < min.length())
				min = city;
			else if (city.length() > max.length())
				max = city;
		}
		String result[] = { min, max };
		
		return result;
	}

	public boolean removeAllFields(List<WebElement> allremoveableFields) {

		try {
			for (WebElement webElement : allremoveableFields) {
				webElement.click();
			}
		} catch (Exception e) {
			return false;
		}
			return true;
		}

	public void anaylzeFile(String fileLocation) {
		String stamp = LocalDateTime.now().toString();
		File file = new File(fileLocation);
		File file_updated = new File("/home/ussr/Downloads/MOCK_DATA" + stamp + ".csv");
		file.renameTo(file_updated);
		try (FileReader fr = new FileReader(file_updated);
				BufferedReader br = new BufferedReader(fr);
				CSVReader reader = new CSVReader(br)) {

			String[] temp;

			while ((temp = reader.readNext()) != null) {
				cities.add(temp[0]);
				countries.add(temp[temp.length - 1]);
			}

		} catch (IOException e) {
			System.out.println("File not found");
			System.out.println(e.getStackTrace());
		}

	}
	@AfterMethod
	public void afterMethod() {

	}
}



