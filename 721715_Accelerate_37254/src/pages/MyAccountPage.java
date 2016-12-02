package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage {
	WebDriver driver;
	
	By accountPageSignOut = By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[2]/a");
	
	public MyAccountPage(WebDriver driver){
    	this.driver = driver;
    }

  
    //Get the title of Home Page
    public WebElement myAccountPageSignOut(){
    	return driver.findElement(accountPageSignOut);
    }
}
