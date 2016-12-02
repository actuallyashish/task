package tests;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.APLoginPage;
import pages.APSearchPage;
import pages.MyAccountPage;
import pages.ProdCartDetails;
import setup.TestSetUp;

public class TestProdCartDetail {
	WebDriver driver;
	ProdCartDetails objProductDetail;
	APSearchPage objSearch;
	APLoginPage objLoginPage;
	
	MyAccountPage objAccount;
	//String details;
	TestSetUp objSetUp=new TestSetUp(this.getClass());
	String expPrice;
	
	
  
  @Test(dataProvider = "Details")
  public void testAddToCart(String strEmail, String strPassword,String keyword,String quant,String size) throws InterruptedException{
	  Reporter.log("<br><b>This method tests product detail display and add to cart functionality</b><br>");
      //Create Home Page object
	 objProductDetail = new ProdCartDetails(driver);
	 objProductDetail.goToLoginPage();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 objProductDetail.getLogin(strEmail, strPassword);
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 objProductDetail.getSearch(keyword);
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 objProductDetail.viewProduct();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 
	 
	 
	 try{
		   WebElement pic =objProductDetail.getProductPic();
		   assertEquals(true, pic.isDisplayed());
		   Reporter.log("Product picture verified<br>");
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in product picture<br>");
			  Reporter.log(e.getMessage());
		  }
	 
	 try{
		   WebElement name =objProductDetail.getProductName();
		   assertEquals(true, name.isDisplayed());
		   Reporter.log("Product name verified - "+name.getText()+"<br>");
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in product name<br>");
			  Reporter.log(e.getMessage());
		  }
	 
	 try{
		   WebElement price=objProductDetail.getProductPrice();
		   this.expPrice=price.getText();
		   assertEquals(true, price.isDisplayed());
		   Reporter.log("Product price verified - " +expPrice+"<br>");
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in product price<br>");
			  Reporter.log(e.getMessage());
		  }
	 
	 try{
		   WebElement qty=objProductDetail.getQuantAvlbl();
		   assertEquals(true, qty.isDisplayed());
		   Reporter.log("Available product quantity is verified "+qty.getText()+"<br>");
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in product price<br>");
			  Reporter.log(e.getMessage());
		  }
	 
	 
	 objProductDetail.AddToCart(quant, size);
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 //Reporter.log(driver.findElement(By.xpath(".//*[@id='layer_cart']/div[1]/div[2]/div[3]/span")).getAttribute("textContent"));
	 objProductDetail.closeCartDetails();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 objProductDetail.goToMyCart();
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	 try{
		    String s = objProductDetail.getTotalCartPrice();
		    Pattern p = Pattern.compile("[^0-9]*([0-9]+(\\.[0-9]*)?)");
		    Matcher m = p.matcher(s);
		    m.matches();
		    String ss = m.group(1);
		    
		    String sh = objProductDetail.getShipping();
		    p = Pattern.compile("[^0-9]*([0-9]+(\\.[0-9]*)?)");
		    m = p.matcher(sh);
		    m.matches();
		    String sh1 = m.group(1);
		    
		    String ta = objProductDetail.getTax();
		    p = Pattern.compile("[^0-9]*([0-9]+(\\.[0-9]*)?)");
		    m = p.matcher(ta);
		    m.matches();
		    String ta1 = m.group(1);
		    
		    float actAmt=Math.round(Float.valueOf(ss)*100)/100;
		    Reporter.log(String.valueOf(actAmt));
		    
		    String s1 = expPrice;
		    m = p.matcher(s1);
		    m.matches();
		    String ss1 = m.group(1);
		    float a=(Float.valueOf(ss1) * Float.valueOf(quant))+(Float.valueOf(sh1)+Float.valueOf(ta1));
		    float expAmt=Math.round(a*100)/100;
		    assertEquals(expAmt, actAmt);;
		    Reporter.log("Total cart amount matches expected amount - "+actAmt+"<br>");
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in price calculation<br>");
			  Reporter.log(e.getMessage());
		  }
	 
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
	public Object[][] Details() {
	  return new Object[][] {
	      new Object[] { "actuallyashish@gmail.com", "12345", "Summer Dress","3","M" },
	  };

}}
