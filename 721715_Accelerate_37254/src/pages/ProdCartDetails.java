package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import pages.APHomePage;
import pages.APLoginPage;
import pages.APSearchPage;
public class ProdCartDetails {
	WebDriver driver;
	APLoginPage objLoginPage;
	APSearchPage objSearchPage;
	APHomePage objHomePage;

	By productPic=By.xpath(".//*[@id='center_column']/div/div/div[2]");
	By productName=By.xpath(".//*[@id='center_column']/div/div/div[3]/h1");
	By productPrice=By.id("our_price_display");
	By productQuantity=By.id("quantity_wanted");
	By productSize=By.id("group_1");
	By addToCart=By.name("Submit");
	By quantAvlbl=By.id("quantityAvailable");
	By cart=By.xpath(".//*[@id='header']/div[3]/div/div/div[3]/div/a/b");
	
	By totPrice=By.xpath(".//*[@id='total_price']");
	By shipping=By.xpath(".//*[@id='total_shipping']");
	By tax=By.xpath(".//*[@id='total_tax']");
	
	By cartDetail=By.xpath(".//*[@id='layer_cart']/div[1]/div[1]/span");

	public ProdCartDetails(WebDriver driver){
			this.driver = driver;
	}


//Get the title of Home Page
	public String getProductPageTitle(){
		return driver.getTitle();
	}
	
	public String getTotalCartPrice(){
		return driver.findElement(totPrice).getText();
	}
	
	public String getShipping(){
		return driver.findElement(shipping).getText();
	}
	
	public String getTax(){
		return driver.findElement(tax).getText();
	}
	

	public WebElement getProductPic(){
		return driver.findElement(productPic);
	}


//Get the User name from Home Page
	public WebElement getProductName(){
		return driver.findElement(productName);
		}


	public WebElement getProductPrice(){
		return driver.findElement(productPrice);
	}

	public WebElement getQuantAvlbl(){
		return driver.findElement(quantAvlbl);
	}

	public void goToMyCart(){
		driver.findElement(cart).click();
		
	}
	
	public void closeCartDetails(){
		driver.findElement(cartDetail).click();
		
	}


	public void setProductQuantity(String quant){
		driver.findElement(productQuantity).clear();
		driver.findElement(productQuantity).sendKeys(quant);
	}
	
	public void setProductSize(String size){
		Select s = new Select(driver.findElement(productSize));
		
		s.selectByVisibleText(size);
	}
	
	public void sbbmtToCart(){
		 driver.findElement(addToCart).click();
	}
	
	public void AddToCart(String quant,String size){
		if(Integer.valueOf(quant)<=(Integer.valueOf(this.getQuantAvlbl().getText())))
			this.setProductQuantity(quant);
		else
			System.out.println("Exceeded available quantity");
		if (size=="S" || size=="M"|| size=="L")
			this.setProductSize(size);
		else
			System.out.println("Invalid Size");
		this.sbbmtToCart();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	public void getLogin(String usr,String pwd) throws InterruptedException{
		objLoginPage=new APLoginPage(driver);
		objLoginPage.loginAsValidUser(usr, pwd);
		Thread.sleep(1000);
	}
	
	
	 
	public void viewProduct() throws InterruptedException{
		objSearchPage=new APSearchPage(driver);
		objSearchPage.view().click();
		Thread.sleep(1000);
	}
	
	
	public void getSearch(String keyword) throws InterruptedException{
		objSearchPage=new APSearchPage(driver);
		objSearchPage.searchProduct(keyword);
		Thread.sleep(1000);
		
	}
	
	public void goToLoginPage(){
		 objHomePage =new APHomePage(driver);
		 objHomePage.getHomePageSignIn().click();
	}
	
	

}
