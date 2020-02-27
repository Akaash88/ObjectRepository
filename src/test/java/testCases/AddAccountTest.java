package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;



public class AddAccountTest extends TestBase {

	@Test(dataProvider ="testdata" )
	public void addAccountTest(String firstName, String lastName, String pincode) throws Exception {
		click("acbtn");
		type("firstname",firstName);
		type("lastname",lastName);
		type("pincode",pincode);
		click("addbtn");
		logger.info("Account Added");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(pOR.getProperty("alertMsg")));
		alert.accept();
	}
	
	@DataProvider(name = "testdata")
	public Object[][] getData() {
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath+"/src/test/resources/excel/testData.xlsx";
		Object data[][] = testData(excelPath,"AddAccountData");
		return data;
	}
}
