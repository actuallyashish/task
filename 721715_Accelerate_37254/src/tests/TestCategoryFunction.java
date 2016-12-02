package tests;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.APHomePage;
import pages.APCategoryDisplayPage;
import setup.TestSetUp;

public class TestCategoryFunction {
	WebDriver driver;
	
	APHomePage objHomePage;
	APCategoryDisplayPage objCategoryPage;
	TestSetUp objSetUp=new TestSetUp(this.getClass());
	
	@Test
	public void testCategories(){

	      //Create Home Page object
		  //objHomePage = new APHomePage(driver);
		  //objHomePage.getMainCat().click();
		objCategoryPage=new APCategoryDisplayPage(driver);
		objCategoryPage=objCategoryPage.getCategory();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		  
		  try{
			  String actualTitle = objCategoryPage.getCategoryPageTitle();
			  String expectedTitle = "Women - My Store";
			  assertEquals(actualTitle, expectedTitle);
			  Reporter.log("Title of the Category page verified<br>");
			  }
			  catch (AssertionError e){
				  Reporter.log("Error in search page title<br>");
				  Reporter.log(e.getMessage());
			  }
		  
		  try{
			  WebElement srchRslt=objCategoryPage.getCategoryResult();
			  assertEquals(true,srchRslt.isDisplayed());
		      Reporter.log("Category result summary - "+objCategoryPage.getCategoryResult().getText()+"<br>");
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      objCategoryPage.getSubCategory();
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		      WebElement srchSubRslt=objCategoryPage.getSubCategoryResult();
			  assertEquals(true,srchSubRslt.isDisplayed());
		      Reporter.log("Sub-Category result summary - "+objCategoryPage.getSubCategoryResult().getText()+"<br>");
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in Category/Subcategory display<br>");
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
	  
}
