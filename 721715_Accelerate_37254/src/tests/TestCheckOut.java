package tests;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.APCheckOut;
import setup.TestSetUp;

public class TestCheckOut {
	
	WebDriver driver;
	TestSetUp objSetUp=new TestSetUp(this.getClass());
	APCheckOut objChkOut;
	
	
	

  @Test(dataProvider = "Inputs")
  public void testCheckOut(String strEmail, String strPassword,String keyword,String quant,String size) throws InterruptedException {
	  objChkOut=new APCheckOut(driver);
	  
	  objChkOut.loginPage();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objChkOut.login(strEmail, strPassword);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objChkOut.searchProd(keyword);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objChkOut.prodDetail();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objChkOut.AddProdToCart(quant, size);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objChkOut.closeCartAlert();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  objChkOut.goToCheckOut();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
	  
	  try{
		   WebElement summary =objChkOut.getProductDesc();
		   assertEquals(true, summary.isDisplayed());
		   Reporter.log("Product in the cart- "+summary.getText()+"<br>");
		   objChkOut.summaryCheckOut();
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in order summary page<br>");
			  Reporter.log(e.getMessage());
		  }
	  
	  
	  
	  try{
		   WebElement add =objChkOut.getDeliveryAddr();
		   assertEquals(true, add.isDisplayed());
		   Reporter.log("Delivery Address "+add.getText()+"<br>");
		   objChkOut.addressCheckOut();
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in Address page<br>");
			  Reporter.log(e.getMessage());
		  }
	  
	  
	  
	  try{
		   WebElement ship =objChkOut.termsAndCond();
		   assertEquals(false, ship.isSelected());
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   objChkOut.termsAgree();
		   Reporter.log("Agreed to shipping terms<br>");
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   objChkOut.shippingCheckOut();
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in shipping page<br>");
			  Reporter.log(e.getMessage());
		  }
	  
	  objChkOut.makePayment();
	  
	  
	  try{
		   WebElement smry =objChkOut.getPaymentSummary();
		   assertEquals(true, smry.isDisplayed());
		   Reporter.log("Payment Summary - "+smry.getText()+"<br>");
		   objChkOut.confirmPayment();
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in confirming payment<br>");
			  Reporter.log(e.getMessage());
		  }
	  
	  objChkOut.getSuccessMessage();

  }
  
  
 
  
  
  @BeforeTest
  public void setUp() {
	  
	  driver=objSetUp.setTest();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	  
  }

  @AfterMethod
  public void afterTest() {
	  driver.quit();
  }  
  
  @DataProvider
	public Object[][] Inputs() {
	  return new Object[][] {
	      new Object[] { "actuallyashish@gmail.com", "12345", "Summer Dress","3","M" },
	  };
}}
