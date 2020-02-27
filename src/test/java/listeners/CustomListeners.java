package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.TestBase;
import utilities.ScreenShot;

public class CustomListeners extends TestBase  implements ITestListener{

	 public void onTestStart(ITestResult result) {
	       eTest =rep.createTest(result.getName());
	        
	    }
	    public void onTestSuccess(ITestResult result) {
	        
	        eTest.pass(result.getName().toUpperCase()+" test passed");
	        
	       
	    }
	    public void onTestFailure(ITestResult result) {
	     String scrnShot = ScreenShot.captureScreenShot(driver); 
	    	try {
				eTest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(scrnShot).build());
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	    }
	    public void onTestSkipped(ITestResult result) {
	     
	    }
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        
	        
	       
	    }
	    public void onStart(ITestContext context) {
	       
	        
	    }
	    public void onFinish(ITestContext context) {
	        
	        rep.flush();
	    }
	}
	

