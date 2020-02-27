package testCases;



import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest extends TestBase {
	
	@Test
	public void loginAsBankManager() throws Exception {
		
		click("bmlbtn");
		Assert.assertTrue(isElementPresent(By.xpath(pOR.getProperty("acbtn"))),"Add_Account button is present");
		
	}
}
