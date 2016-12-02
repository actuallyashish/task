package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class APCategoryDisplayPage {
	WebDriver driver;
	APHomePage objHomePage;

	By categoryResult = By.xpath(".//*[@id='center_column']/h1");
	By subCategory=By.xpath(".//*[@id='subcategories']/ul/li[1]/h5/a");
	
	public APCategoryDisplayPage(WebDriver driver){
    	this.driver = driver;
    }
	
	//Navigate to Category display page from the home page
	public APCategoryDisplayPage getCategory(){
		objHomePage=new APHomePage(driver);
		objHomePage.getMainCat().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return this;
	   }
    
	//Returns
    public String getCategoryPageTitle(){
    	return driver.getTitle();
    }
    
    public WebElement getCategoryResult(){
    	return driver.findElement(categoryResult);
    }
    
    public void getSubCategory(){
    	
    	driver.findElement(subCategory).click();
    }
    
    public WebElement getSubCategoryResult(){
    	return driver.findElement(categoryResult);
    }
}
