package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class APCheckOut {
	WebDriver driver;
	
	ProdCartDetails objProdCart;
	
	By chkOut1=By.xpath(".//*[@id='center_column']/p[2]/a[1]/span");
	By prodDescr=By.xpath(".//*[@id='center_column']/p[2]/a[1]/span");
	By chkOut2=By.xpath(".//*[@id='center_column']/form/p/button");
	By deliveryAddr=By.xpath(".//*[@id='address_delivery']");
	By tAndC=By.id("cgv");
	By chkOut3=By.xpath(".//*[@id='form']/p/button");
	By pay=By.xpath(".//*[@id='HOOK_PAYMENT']/div[2]/div/p/a");
	By paySmry=By.xpath(".//*[@id='center_column']/form/div/h3");
	By chkOut4=By.xpath(".//*[@id='cart_navigation']/button");
	
	By success=By.xpath(".//*[@id='center_column']/p[1]");
	
	public APCheckOut(WebDriver driver){
		this.driver=driver;
	}
	
	public void loginPage() throws InterruptedException{
		objProdCart=new ProdCartDetails(driver);
		objProdCart.goToLoginPage();
		Thread.sleep(1000);
	}
	
	public void login(String email, String pswrd) throws InterruptedException{
		objProdCart=new ProdCartDetails(driver);
		objProdCart.getLogin(email, pswrd);
		Thread.sleep(1000);
	}
	
	public void searchProd(String keyword) throws InterruptedException{
		objProdCart=new ProdCartDetails(driver);
		objProdCart.getSearch(keyword);
		Thread.sleep(1000);
	}
	
	public void prodDetail() throws InterruptedException{
		objProdCart=new ProdCartDetails(driver);
		objProdCart.viewProduct();
		Thread.sleep(1000);
	}
	
	public void AddProdToCart(String quant, String size) throws InterruptedException{
		objProdCart=new ProdCartDetails(driver);
		objProdCart.AddToCart(quant, size);
		Thread.sleep(1000);
	}
	
	public void closeCartAlert() throws InterruptedException{
		objProdCart=new ProdCartDetails(driver);
		objProdCart.closeCartDetails();
		Thread.sleep(1000);
	}
	
	public void goToCheckOut() throws InterruptedException{
		objProdCart=new ProdCartDetails(driver);
		objProdCart.goToMyCart();
		Thread.sleep(1000);
	}
	
	public void summaryCheckOut(){
		driver.findElement(chkOut1).click();
	}
	
	public WebElement getProductDesc(){
		return driver.findElement(prodDescr);
	}
	
	public WebElement getDeliveryAddr(){
		return driver.findElement(deliveryAddr);
	}
	
	public void addressCheckOut(){
		driver.findElement(chkOut2).click();
	}
	
	public WebElement termsAndCond(){
		return driver.findElement(tAndC);
	}
	
	public void shippingCheckOut(){
		driver.findElement(chkOut3).click();
	}
	
	public void termsAgree(){
		driver.findElement(tAndC).click();
	}
	
	public void makePayment(){
		driver.findElement(pay).click();
	}
	
	public WebElement getPaymentSummary(){
		return driver.findElement(paySmry);
	}
	
	public void confirmPayment(){
		driver.findElement(chkOut4).click();
	}
	
	public WebElement getSuccessMessage(){
		return driver.findElement(success);
	}
	
}
