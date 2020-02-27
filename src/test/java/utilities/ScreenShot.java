package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class ScreenShot {

	public static String captureScreenShot(WebDriver driver ) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/ScreenShot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(source, destination);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	return path;
}
}
