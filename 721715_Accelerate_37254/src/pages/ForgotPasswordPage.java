package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ForgotPasswordPage {
	WebDriver driver;
	//Actions act=new Actions(driver);
	
	By email = By.xpath(".//*[@id='email']");
	By changePwrdBtn = By.xpath(".//*[@id='form_forgotpassword']/fieldset/p/button");
	
	
	public ForgotPasswordPage(WebDriver driver){
    	this.driver = driver;
    }

  
	 public void clearEmail(){

	        driver.findElement(email).clear();;

	    }
    
    
    
    
    //Get the User name from Home Page
    public void setEmail(String strUserEmail){

        driver.findElement(email).sendKeys(strUserEmail);

    }
    
    
    public void clickChangePassword(){

        driver.findElement(changePwrdBtn).click();
        //return new AmazonHomePage(driver);
}
    
    public String getMessage(){

        return driver.getPageSource();

        //return new AmazonHomePage(driver);
}
    
    
    public ForgotPasswordPage forgotPassword(String strEmail) {

        //Fill user name
    	driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
        this.setEmail(strEmail);
        //Thread.sleep(10000);
        //Fill password
        //Thread.sleep(10000);
        
        //Click Login button

        this.clickChangePassword();  
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return this;

        

    }
}
