package tests;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import setup.TestSetUp;
import pages.APHomePage;
import pages.APSearchPage;
public class TestAPSearchFunction {
	WebDriver driver;
	APHomePage objHomePage;
	APSearchPage objSearchPage;
	TestSetUp objSetUp=new TestSetUp(this.getClass());
	
	@Test(dataProvider="Keywords")
	public void testSearchFunction(String keyword){

	      //Create Home Page object
		  /*objHomePage = new APHomePage(driver);
		  objHomePage.getHomePageSearchBar().sendKeys(keyword);
		  objHomePage.getHomePageSearchButton().click();*/
		  objSearchPage=new APSearchPage(driver);
		  objSearchPage=objSearchPage.searchProduct(keyword);
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  try{
			  String actualTitle = objSearchPage.getSearchPageTitle();
			  String expectedTitle = "Search - My Store";
			  assertEquals(actualTitle, expectedTitle);
			  Reporter.log("Search page title verified<br>");
			  }
			  catch (AssertionError e){
				  Reporter.log("Error in search page title");
				  Reporter.log(e.getMessage());
			  }
		  
		  try{
			  //objSearchPage=new APSearchPage(driver);
			  WebElement srchRslt=objSearchPage.getSearchResult();
			  assertEquals(true,srchRslt.isDisplayed());
		      Reporter.log("Search result summary - "+objSearchPage.getSearchResult().getText()+"<br>");
		  }
		  catch (AssertionError e){
			  Reporter.log("Error in Search<br>");
			  Reporter.log(e.getMessage());
		  }
	}
	
	@BeforeTest
	  public void setUp() {
		  
		  driver=objSetUp.setTest();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  }

	  @AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
	  
	  @DataProvider
	  public Object[][] Keywords() {
		  return new Object[][] {
		      new Object[] { "Shirts" },
		      new Object[] { "Books" },
		      new Object[] { "Blouses" },
		      
		  };
		}
}
