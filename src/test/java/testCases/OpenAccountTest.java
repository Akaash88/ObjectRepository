package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class OpenAccountTest extends TestBase {
	@Test(dataProvider = "testdata")
	public void openAccountTest( String CustomerName ,String Currency) throws Exception {
		click("opacbtn");
		select("custdd",CustomerName);
		select("curdd", Currency);
		click("processbtn");
		driver.switchTo().alert().accept();
	}
		@DataProvider(name = "testdata")
		public  Object[][] getData() {
			String projectPath = System.getProperty("user.dir");
			String excelPath = projectPath+"/src/test/resources/excel/testData.xlsx";
			Object data[][] = testData(excelPath,"OpenAccountTest");
			return data;
		}
		
	
	
	}

	
	

