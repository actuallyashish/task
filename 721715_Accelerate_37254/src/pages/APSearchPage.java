package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.APHomePage;
public class APSearchPage {
	WebDriver driver;
	//Actions act=new Actions(driver);
	APHomePage objHomePage;

	By searchResult = By.xpath(".//*[@id='center_column']/h1");
	By searchButton= By.xpath(".//*[@id='searchbox']/button");
	By viewProd =By.xpath(".//*[@id='center_column']/ul/li[3]/div/div[2]/h5/a");
	//.//*[@id='center_column']/ul/li/div/div[2]/h5/a
	
	
	//*[@id='center_column']/h1/span[2]
	
    //By homePageSignOut=By.xpath(".//*[@id='nav-item-signout']/span");
	
	public APSearchPage(WebDriver driver){
    	this.driver = driver;
    }
	
	public APSearchPage searchProduct(String keyword){
		this.setSearch(keyword);
		this.clickSearch();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	return this;
    }
  
    //Get the title of Home Page
    public String getSearchPageTitle(){
    	return driver.getTitle();
    }
    
   public void setSearch(String keyword){
	    objHomePage=new APHomePage(driver);
    	objHomePage.getHomePageSearchBar().clear();
    	objHomePage.getHomePageSearchBar().sendKeys(keyword);
    	
    }
   
   public void clickSearch(){
	   objHomePage.getHomePageSearchButton().click();
   }
    
    
    //Get the User name from Home Page
    public WebElement getSearchResult(){
    	return driver.findElement(searchResult);
    }
    
    public WebElement view(){
       
  	   return driver.findElement(viewProd);
    }
   
    
    
}
