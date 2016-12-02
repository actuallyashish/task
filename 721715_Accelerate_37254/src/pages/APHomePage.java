package pages;



//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;

public class APHomePage {
	WebDriver driver;
	//Actions act=new Actions(driver);
	
	By homePageHeader = By.xpath(".//*[@id='header']/div[3]");
	By homePageLogo = By.xpath(".//*[@id='header_logo']/a/img");
	By homePageSearchBar = By.xpath(".//*[@id='search_query_top']");
	
	By homePageSearchButton= By.xpath(".//*[@id='searchbox']/button");
	By homePageCatNav= By.xpath(".//*[@id='block_top_menu']/ul");
	By homePageSignIn=By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a");
	By homePageMainCat=By.xpath(".//*[@id='block_top_menu']/ul/li[1]/a");
    
    //By homePageSignOut=By.xpath(".//*[@id='nav-item-signout']/span");
	
	public APHomePage(WebDriver driver){
    	this.driver = driver;
    }

  
    //Get the title of Home Page
    public String getHomePageTitle(){
    	return driver.getTitle();
    }
    
    public WebElement getMainCat(){
    	return driver.findElement(homePageMainCat);
    }
    
    
    //Get the User name from Home Page
    public WebElement getHomePageHeader(){
    	return driver.findElement(homePageHeader);
    }
    
    
    public WebElement getHomePageLogo(){
    	return driver.findElement(homePageLogo);
    }
    
    
    public WebElement getHomePageSearchBar(){
    	return driver.findElement(homePageSearchBar);
    }
    
    
    public WebElement getHomePageSearchButton(){
    	return driver.findElement(homePageSearchButton);
    }
    
    
    public WebElement getHomePageCatNav(){
    	return driver.findElement(homePageCatNav);
    }
    
   /* public void setSearchBar(String keyword){
    	this.getHomePageSearchBar().clear();
    	this.getHomePageSearchBar().sendKeys(keyword);
    	
    }
    
   
    
    public APSearchPage searchProduct(String keyword){
    	this.setSearchBar(keyword);
		this.getHomePageSearchButton().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	return new APSearchPage(driver);
    }*/
    
    
    
    
    
    
    
  /*  public WebElement getHomePageMainCat(){
    	return driver.findElement(homePageMainCat);
    }
    
    public WebElement getHomePageSubCategories(){
    	  Actions act=new Actions(driver);
		  act.moveToElement(getHomePageMainCat()).build().perform();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  return driver.findElement(homePageSubCats);
    } */
    
    
      public WebElement getHomePageSignIn(){
    	return driver.findElement(homePageSignIn);
    }
    
      /* public WebElement getHomePageSignOut(){
    	Actions act1=new Actions(driver);
    	act1.moveToElement(getHomePageSignIn()).build().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	return driver.findElement(homePageSignOut);
    } */
    
    

}
