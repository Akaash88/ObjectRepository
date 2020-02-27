package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TestProperties {

static WebDriver driver;
 


    
     
    public static void main(String[] args) 
    {
    	driver = new ChromeDriver();
    	driver.get("http://www.way2automation.com/angularjs-protractor/banking/#/manager/addCust");
		driver.findElement(By.xpath("//button[@class='btn btn-lg tab btn-primary']")).click();;
	}
    }

