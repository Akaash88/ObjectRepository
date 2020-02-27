package base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ExcelUtil;
import utilities.ExtentManager;



public class TestBase {

	public static WebDriver driver;
	public static Properties pConfig = new Properties();
	public static Properties pOR = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest eTest;
	public static Logger logger = LogManager.getLogger(TestBase.class.getName());
	@BeforeSuite
	public void setUp() {
		String projectPath = System.getProperty("user.dir");
		 try {
			fis = new FileInputStream(projectPath+"/src/test/resources/properties/Config.properties");
			pConfig.load(fis);
			logger.debug("Config file loaded");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 try {
				fis = new FileInputStream(projectPath+"/src/test/resources/properties/OR.properties");
				pOR.load(fis);
				logger.debug("Object Repository file loaded");
			} catch (Exception e) {
				e.printStackTrace();
			}
		 if(pConfig.getProperty("browser").equalsIgnoreCase("chrome")) {
			 driver = new ChromeDriver();
			 logger.debug("Chrome Browser Selected");
			 driver.manage().window().maximize();	 
		 }
		 else if(pConfig.getProperty("browser").equalsIgnoreCase("firefox")) {
			 driver = new FirefoxDriver();
			 logger.debug("Firefox Browser is Selected");
			 driver.manage().window().maximize();
			
		 }
		 // converting string into int.	 
		 driver.manage().timeouts().implicitlyWait(Integer.parseInt(pConfig.getProperty("implicit.wait")), TimeUnit.SECONDS);
		 driver.get(pConfig.getProperty("baseURL"));
		 logger.debug("Base Url Launched");
		 wait = new WebDriverWait(driver, 5);
		 
	}
	public void click(String locator) {
		driver.findElement(By.xpath(pOR.getProperty(locator))).click();
		eTest.info("clicking on : "+locator);
	}
	public void type(String locator,String value) {
		driver.findElement(By.xpath(pOR.getProperty(locator))).sendKeys(value);
		eTest.info("typing in  : "+locator+" entered value is :"+value);
	}
	public void select(String locator,String value) {
		WebElement dropdown = driver.findElement(By.xpath(pOR.getProperty(locator)));
		Select sel =new Select(dropdown);
		sel.selectByVisibleText(value);
		eTest.info("selected element is  : "+locator+" and the value is :"+value);
	}
	
	
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public  Object[][] testData(String excelPath, String sheetName) {
		ExcelUtil excel = new ExcelUtil(excelPath, sheetName);
		int rowcount = excel.getRowCount(); 
		int colcount = excel.getColCount();

		Object data[][] = new Object[rowcount-1][colcount]; //Initializing array size.
		
		for(int i =1;i<rowcount;i++) {
			for(int j=0;j<colcount;j++) {
				String cellData = excel.getData(i, j);
				data[i-1][j] = cellData; 
			}
		}
		return data;
	}
		
	
	@AfterSuite
	public void tearDown() {
	
	driver.quit();	
	}
}
