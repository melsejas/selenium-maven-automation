package com.dice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main (String [] args) {
		//set up chrome driver path 
		WebDriverManager.chromedriver().setup();
		
		//opens selenium webdriver
		WebDriver driver = new ChromeDriver();

		//full screen 
		driver.manage().window().fullscreen();
		//driver.manage().window().maximize();
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Step 1. Launch browser and navigate to https://dice.com 
			//Expected: dice home page should be displayed
		String url = "https://dice.com";
		driver.get(url);
		// Step 2. Insert search keyword and location then click on
			//find tech jobs
		String actualTittle = driver.getTitle();
		String expectedTittle = "Job Search for Technology Professionals | Dice.com";
		
		if(actualTittle.equals(expectedTittle)) {
			System.out.println("Step PASS. Dice homepage successfully loaded");
			
		}else {
			System.out.println("Step FAIL. Dice homepage did not successfully loaded");
			throw new RuntimeException("Step FAIL. Dice homepage did not successfully loaded");
		}
		//or this can be use 
		// Expected: -Search results page should be loaded.
		String keyword = "java developer"; //keyword=variable
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "22204";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		//-Page title should contain count of results , 
			//along with search keyword.
		driver.findElement(By.id("findTechJobs")).click();
	
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		//  -Count of results should be displayed on the page.
		int countResult = Integer.parseInt(count.replace(",",""));
		
			if(countResult > 0) {
				System.out.println( "Step PASS: Keyword : " + keyword +" search returned " +
				countResult +" results in " + location);
			}else {
				System.out.println( "Step FAIL: Keyword : " + keyword +" search returned " +
						countResult +" results in " + location);
			}
			
			driver.close();
			System.out.println("TEST COMPLETED -" + LocalDateTime.now());
		
		
		
//		
//		
//		Test case:
//		      Title: dice job search 
//
//		      Step 1. Launch browser and navigate to https://dice.com 
//		        Expected: dice home page should be displayed
//
//		      Step 2. Insert search keyword and location then click on
//		      find tech jobs
//		      Expected: -Search results page should be loaded.
//		                -Page title should contain count of results , 
//		                along with search keyword.
//		                -Count of results should be displayed on the page.
//		      ====================
//		      Steps to automate:
//		        -Make sure you understand what functionality is being tested 
//		        and each step. What is expected, what is being tested.
//
//		        If you don't understand:  Ask manual tester/person who wrote it.
//		        BAs, Developers, Lead 
//
//		        -Manually test it and make sure , it is passing manually.
//		        And make sure no defects/bugs around it.
//		        -if a test case is failing manually, it does not qualify 
//		        for automation.
//
//		        --> starts automating:
//		        Java + Selenium + Maven + Git > Github
//		      =========================
	}
}
