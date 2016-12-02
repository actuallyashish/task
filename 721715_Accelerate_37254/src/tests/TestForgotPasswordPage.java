package tests;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;


import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pages.APHomePage;
import pages.APLoginPage;
import pages.ForgotPasswordPage;
import setup.TestSetUp;

public class TestForgotPasswordPage {
	WebDriver driver;
	String url;
	APHomePage objHomePage; 
	APLoginPage objLoginPage;
	ForgotPasswordPage objForgotPasswordPage;
	//static Logger log=Logger.getLogger(TestAPHomePage.class);
	TestSetUp objSetUp=new TestSetUp(this.getClass());
  
  @Test(dataProvider = "Emails")
  public void testForgotPasswordPage(String email, String type) throws InterruptedException {
	  objForgotPasswordPage = new ForgotPasswordPage(driver);
	  
	  
	  String text;
	  boolean flag;
	  //login to application
	  
	  objForgotPasswordPage = objForgotPasswordPage.forgotPassword(email);
	  
	    // go the next page

	    //objHomePage = new AmazonHomePage(driver);
	  if (type=="valid"){
	    //Verify home page
	    try{
	    	text="A confirmation email has been sent to your address:";
	    	flag=objForgotPasswordPage.getMessage().contains(text);
	    	
	    	assertEquals(true, flag);
	    	Reporter.log("Password retrieved <br>");
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	driver.navigate().back();
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	objForgotPasswordPage.clearEmail();
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }
	    catch (AssertionError e){
			  Reporter.log("Error in retrieving password <br>");
			  Reporter.log(e.getMessage());
		  }
	  }
	    
	    else{
	    	try{
	    		text="There is 1 error";
		    	flag=objForgotPasswordPage.getMessage().contains(text);
		    	
		    	assertEquals(true, flag);
		    	
		    	
		    	Reporter.log("Password can't be retrieved <br>");
		    	objForgotPasswordPage.clearEmail();
		    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    }
		    catch (AssertionError e){
				  Reporter.log("Error in retrieving password <br>");
				  Reporter.log(e.getMessage());
			  }
	    }
	    }
	  
  
  
 
  
  
 
  
  
 
  
  
  @DataProvider
  public Object[][] Emails() {
	  return new Object[][] {
		  new Object[] { "actuallyashish@gmail.com", "valid" },
		  new Object[] { "721715@xyz.com", "valid" },
	      new Object[] { "bhgfre@xyz", "invalid" },
	      new Object[] { "nbgahf@abc.com","invalid"  },
	      
	  };
	}
  
  
  
  @BeforeTest
  public void beforeTest() {
	  /*BasicConfigurator.configure();
	  ConsoleAppender ca=new ConsoleAppender(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN));
	  log.addAppender(ca);
	  
	  //System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
	  driver = new FirefoxDriver();
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  url = "http://www.automationpractice.com/";
	  driver.get(url);*/
	  
	  driver=objSetUp.setTest();
	  objHomePage=new APHomePage(driver);
	  driver.manage().window().maximize();
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objHomePage.getHomePageSignIn().click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objLoginPage=new APLoginPage(driver);
	  objLoginPage.clickForgotPassword();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
