package tests;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import pages.APHomePage;
//import pages.APLoginPageError;
import pages.APLoginPage;
import pages.APSignUpPage;
import pages.MyAccountPage;
import setup.TestSetUp;

public class TestAPLoginPage {
	WebDriver driver;
	String url;
	//String text;
	//boolean flag;
	APHomePage objHomePage; 
	MyAccountPage objMyAccount;
	APLoginPage objLoginPage;
	//APLoginPageError objLoginErrorPage;
	APSignUpPage objSignUpPage;
	//boolean flag;
	//objHomePage = new APHomePage(driver);
	//static Logger log=Logger.getLogger(TestAPHomePage.class);
	TestSetUp objSetUp=new TestSetUp(this.getClass());
	
	
	
  @Test(dataProvider = "Users",priority=0,alwaysRun=true)
  public void testLoginPage(String email, String pswrd, String type) throws InterruptedException {
	  objLoginPage = new APLoginPage(driver);
	  
	/* //Verify page title
	  try{
	  String actualTitle = objLoginPage.getLoginPageTitle();
	  String expectedTitle = "Login - My Store";
	  assertEquals(actualTitle, expectedTitle);
	  Reporter.log("title done");
	  }
	  catch (AssertionError e){
		  Reporter.log("Error in home page title");
		  Reporter.log(e.getMessage());
	  } */
	  
	  
	  //login to application
	  if (type=="valid"){
	  objMyAccount = objLoginPage.loginAsValidUser(email, pswrd);
	  

	    // go the next page

	    //objHomePage = new AmazonHomePage(driver);

	    //Verify home page
	    try{
	    	WebElement signOut=objMyAccount.myAccountPageSignOut();
	    	assertEquals(true, signOut.isDisplayed());
	    	Reporter.log("Sign out found<br>");
	    	Reporter.log("Sign in valid<br>");
	    	signOut.click();
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }
	    catch (AssertionError e){
			  Reporter.log("Error in login<br>");
			  Reporter.log(e.getMessage());
		  }
	  }
	    
	    else{
	    objLoginPage.loginAsInvalidUser(email, pswrd);
	    try{
	    	String text="There is 1 error";
	    	Thread.sleep(1000);
	    	boolean flag=objLoginPage.loginError().getText().contains(text);
	    	//Reporter.log(String.valueOf(flag));
	    	//Reporter.log(objLoginErrorPage.loginError().getText());
	    	assertEquals(true, flag);
	    	Reporter.log("Sign in Invalid due to incorrect username/password<br>");
	    }
	    catch (AssertionError e){
			  Reporter.log("Incorrect username/password<br>");
			  Reporter.log(e.getMessage());
		  }
	    }
	  }
	  
  
  
  @Test(dataProvider = "SignUp",priority=1,alwaysRun=true)
  public void testSignUpEmail(String email, String type) throws InterruptedException {
	  objLoginPage = new APLoginPage(driver);
	  
	  //login to application
	  if (type=="valid"){
	  objSignUpPage = objLoginPage.signUpAsValidUser(email);
	  

	    // go the next page

	    //objHomePage = new AmazonHomePage(driver);

	    //Verify home page
	    try{
	    	WebElement signUp=objSignUpPage.getSignUpForm();
	    	assertEquals(true, signUp.isDisplayed());
	    	Reporter.log("Signup form found<br>");
	    	WebElement signIn=objSignUpPage.getSignUpPageSignIn();
	    	signIn.click();
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    }
	    catch (AssertionError e){
			  Reporter.log("Error in Signup<br>");
			  Reporter.log(e.getMessage());
		  }
	  }
	    
	    else{
	    objLoginPage.signUpAsInvalidUser(email);
	    try{
	    	Thread.sleep(3000);
	       WebElement msg =objLoginPage.signUpError();
	  	   assertEquals(true, msg.isDisplayed());
	    	Reporter.log("Sign up error message displayed " +msg.getText()+"<br>");
	    }
	    catch (AssertionError e){
			  Reporter.log("Error while signing up<br>");
			  Reporter.log(e.getMessage());
		  }
	    }
	  }
	  
  
  
 
  
  
  @BeforeClass
  public void setUp() {
	 /* BasicConfigurator.configure();
	  ConsoleAppender ca=new ConsoleAppender(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN));
	  log.addAppender(ca);
	  
	  //System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
	  driver = new FirefoxDriver();
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  url = "http://www.automationpractice.com/";
	  driver.get(url); */
	  driver=objSetUp.setTest();
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objHomePage=new APHomePage(driver);
	  //driver.manage().window().maximize();
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objHomePage.getHomePageSignIn().click();
	  
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterClass
  public void afterTest() {
	  driver.quit();
  }
  
  
  @DataProvider
  public Object[][] Users() {
	  return new Object[][] {
	      new Object[] { "actuallyashish@gmail.com", "12345","valid" },
	      new Object[] { "721715@xyz.com", "abcde","valid" },
	      new Object[] { "abc@xyz.com", "abcde","invalid" },
	      new Object[] { "xyz@abc.com", "12345","invalid"  },
	  };
	}
  
  @DataProvider
	public Object[][] SignUp() {
	  return new Object[][] {
	      new Object[] { "alan@shore.com", "valid" },
	      new Object[] { "bill@carr.com", "valid" },
	      new Object[] { "virat@", "invalid" },
	      new Object[] { "actuallyashish@gmail.com", "invalid" },
	  };
	} 

	

	


}
