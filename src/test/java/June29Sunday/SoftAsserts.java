package June29Sunday;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {

	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void test1() {
		int i= 100; 
		int j = 200;
		//recommended to enter a message to find which assortion is failing
		System.out.println("First Assertion");
		softAssert.assertEquals(i,  j, "I and J is not equals");
		
		System.out.println("Second Assertion");
		softAssert.assertEquals(i, j);
		
		System.out.println("Third Assertion");
		softAssert.assertEquals(i, j, " I is not greater than J");
		
		//checks all assortions 
		softAssert.assertAll();
	}
	
	
	
	
	
	
	
}
