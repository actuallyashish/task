package tests;

import static org.testng.AssertJUnit.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.APHomePage;
import pages.APLoginPage;
import pages.APSignUpPage;
//import pages.ForgotPasswordPage;
import pages.MyAccountPage;
import setup.TestSetUp;

public class TestSignUpPage {
	WebDriver driver;
	String url;
	APHomePage objHomePage; 
	APLoginPage objLoginPage;
	APSignUpPage objSignUpPage;
	MyAccountPage objMyAccount;
	//static Logger log=Logger.getLogger(TestAPHomePage.class);
	TestSetUp objSetUp=new TestSetUp(this.getClass());
	 
	@Test(dataProvider = "Emails",alwaysRun=true)
	 public void testSignUpPage(String validity,String title,String fName,String lName,String email,String pswrd,String day,String month,String year,String chkbox1,String chkbox2,String addrFname,String addrLname,String comp,String addr1,String addr2,String town,String nation,String state,String pincode,String info,String phoneNo,String mobNo, String alias) throws InterruptedException {
		  objLoginPage = new APLoginPage(driver);
		  objSignUpPage=objLoginPage.signUpAsValidUser(email);
		  Thread.sleep(3000);
		  boolean flag=true;
		  List<WebElement> RadButtonList = objSignUpPage.getRadioBtn();
		  RadButtonList.get(0).click();
		  for (int i=1;i<RadButtonList.size();i++){
			  if (RadButtonList.get(i).isSelected()){
				  flag=false;
				  break;
			  }
		  }
		  try{
		  assertEquals(true, flag);
		  Reporter.log("Radio button checked- Only one option can be selected in radio button<br>");
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in radio button<br>");
			  Reporter.log(e.getMessage());
		  }
		  
		  
	/*	  String mail=objSignUpPage.getEmail();
		  
		  if(mail.equals(email))
			  Reporter.log("Mails are same");
		  else
			  Reporter.log("Email changed");
	
	  if(fName!=null){
	  String firstName=objSignUpPage.getAddrFName();
	  
		  if(firstName.equals(fName))
			  Reporter.log("first Name is same");
		  else
			  Reporter.log("first Name changed");
	  }  
	  
		if (lName!=null){  
	   String lastName=objSignUpPage.getAddrLName();
		  
		  if(lastName.equals(lName))
			  Reporter.log("last Name is same");
		  else
			  Reporter.log("last Name changed");
		}
		
		if (alias!=null){  
			   String addrAlias=objSignUpPage.getAlias();
				  
				  if(addrAlias.equals(alias))
					  Reporter.log("Alias is same");
				  else
					  Reporter.log("Alias changed");
				} */
		  
		  objMyAccount =objSignUpPage.signUp(title,fName,lName,email,pswrd,day,month,year,chkbox1,chkbox2,addrFname,addrLname,comp,addr1,addr2,town,nation,state,pincode,info,phoneNo,mobNo,alias);
		  
		  
			if(validity=="R"){
			try{
		    	WebElement signOut=objMyAccount.myAccountPageSignOut();
		    	assertEquals(true, signOut.isDisplayed());
		    	Reporter.log("Sign out found<br>");
		    	Reporter.log("Sign up valid<br>");
		    	signOut.click();
		    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    }
		    catch (AssertionError e){
				  Reporter.log("Error in sign up<br>");
				  Reporter.log(e.getMessage());
			  }
			}
			else{
				try{
				Thread.sleep(3000);
				WebElement error=objSignUpPage.getSignUpPageError();
				assertEquals(true, error.isDisplayed());
				Reporter.log("Following errors are displayed - <br>"+error.getText()+"<br>");
				WebElement signIn=objSignUpPage.getSignUpPageSignIn();
		    	signIn.click();
		    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);}
				catch (AssertionError e){
					  Reporter.log("Can not find sign up<br>");
					  Reporter.log(e.getMessage());
				  }
			}
		  
		  
		  }
	
	 @BeforeTest
	  public void beforeTest() {
		  
		  driver=objSetUp.setTest();
		  objHomePage=new APHomePage(driver);
		  driver.manage().window().maximize();
		  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  objHomePage.getHomePageSignIn().click();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  /* objLoginPage=new APLoginPage(driver);
		  objLoginPage.setSignUpEmail(strEmail);
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); */
		  
	  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
	  
	  @DataProvider
	  public Object[][] Emails() {
		  return new Object[][] {
		      new Object[] { "W","","","","virat@kohli.com","","","","","","","","","","","","","","","","","","","" },
		      new Object[] { "W","Ms.","Virat@7","Kohli*6","virat@kohli.com","1234","","","","L","M","","","","@","%","23@","India","Kent","9876","","","+8765432345","" },
		      new Object[] { "R","Mr.","Roger","Federer","roger@infosys.com","12345","16","January","1988","Y","","","","","Napier Town","","Louisville","United States","Alabama","40201","","","+8765432345","" },
		      new Object[] { "R","Mrs.","Maria","Sharapova","maria@infosys.com","09876","20","March","1986","N","Y","Masha","Shara","Oz","Wright Town","cb","Omaha","United States","Alaska","68114","player","87654323","+9865432176","addr"  },
		  };
		}
}
