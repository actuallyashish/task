package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class APSignUpPage {
WebDriver driver;
	
	By signUpForm = By.xpath(".//*[@id='account-creation_form']/div[1]/h3");
	By genderRadBtn = By.name("id_gender");
	By dayDrpDwn = By.id("days");
	By monthDrpDwn = By.id("months");
	By yearDrpDwn = By.id("years");
	By stateDrpDwn = By.id("id_state");
	By countryDrpDwn = By.id("id_country");
	By fname = By.id("customer_firstname");
	By lname= By.id("customer_lastname");
	By email= By.id("email");
	By pswd= By.id("passwd");
	By fname1= By.id("firstname");
	By lname1= By.id("lastname");
	By company= By.id("company");
	By address1= By.id("address1");
	By address2= By.id("address1");
	By city= By.id("city");
	By zip= By.id("postcode");
	By addtnlInfo= By.id("other");
	By phone= By.id("phone");
	By mobile= By.id("phone_mobile");
	By addrAlias= By.id("alias");
	By register= By.id("submitAccount");
	
	By chkBox1=By.id("newsletter");
	By chkBox2=By.id("optin");
	By signIn=By.xpath(".//*[@id='header']/div[2]/div/div/nav/div[1]/a");
	
	By err=By.xpath(".//*[@id='center_column']/div");
	
	
	public APSignUpPage(WebDriver driver){
    	this.driver = driver;
    }

	 public WebElement getSignUpPageSignIn(){
	    	return driver.findElement(signIn);
	    }
    //Get the title of Home Page
    public WebElement getSignUpForm(){
    	return driver.findElement(signUpForm);
    }
    
    public List<WebElement> getRadioBtn(){
    	return driver.findElements(genderRadBtn);
    }
    
    public void setTitle(String title){
    	if (title!=null){
    		if (title=="Mrs.")
    			driver.findElements(genderRadBtn).get(1).click();
    		else
    			driver.findElements(genderRadBtn).get(0).click();
    	}
    }
    
    public void setFName(String fName){
    	driver.findElement(fname).sendKeys(fName);
    }
    
    public void setLName(String lName){
    	driver.findElement(lname).sendKeys(lName);
    }
    
    public void setEmail(String eMail){
    	driver.findElement(email).sendKeys(eMail);
    }
    
    public String getEmail(){
    	return driver.findElement(email).getAttribute("value");
    }
    
    public void setPassword(String pswrd){
    	driver.findElement(pswd).sendKeys(pswrd);
    	
    }
    
    public void setDOB(String day,String month,String year){
    	Select dd = new Select(driver.findElement(dayDrpDwn));
    	Select mm = new Select(driver.findElement(monthDrpDwn));
    	Select yy = new Select(driver.findElement(yearDrpDwn));
    	
    		dd.selectByValue(day);
    	
    		mm.selectByVisibleText(month+" ");
    	
    		yy.selectByValue(year);
    	
    }
    
    public void setChkBox1(){
    	driver.findElement(chkBox1).click();
    }
    
    public void setChkBox2(){
    	driver.findElement(chkBox2).click();
    }
    
    public String getAddrFName(){
    	return driver.findElement(fname1).getAttribute("value");
    }
    
    public String getAddrLName(){
    	return driver.findElement(lname1).getAttribute("value");
    }
    
    public void setCompany(String comp){
    	driver.findElement(company).sendKeys(comp);
    }
    
    public void setAddr1(String addr1){
    	driver.findElement(address1).sendKeys(addr1);
    }
    
    public void setAddr2(String addr2){
    	driver.findElement(address2).sendKeys(addr2);
    }
    
    public void setCity(String town){
    	driver.findElement(city).sendKeys(town);
    }
    
    public void setCountry(String nation){
    	if(nation!=""){
    	Select cc = new Select(driver.findElement(countryDrpDwn));
    	cc.selectByVisibleText(nation);
    }}
    
    public void setState(String state){
    	if(state!=""){
    	Select ss = new Select(driver.findElement(stateDrpDwn));
    	ss.selectByVisibleText(state);}
    }
    
    public void setZip(String pincode){
    	driver.findElement(zip).sendKeys(pincode);
    }
    
    public void setInfo(String info){
    	driver.findElement(addtnlInfo).sendKeys(info);
    }
    
    public void setPhone(String phoneNo){
    	driver.findElement(phone).sendKeys(phoneNo);
    }
    
    public void setMobile(String mobNo){
    	driver.findElement(mobile).sendKeys(mobNo);
    }
    
    public void setAddrFName(String fname){
 
    		   String firstName=this.getAddrLName();
    			  if(!(firstName.equals(fname)) && fname!=""){
    				  driver.findElement(fname1).clear();
    			      driver.findElement(fname1).sendKeys(fname);
    			  }
    }
    
    public void setAddrLName(String lname){
    	String lastName=this.getAddrLName();
		if(!(lastName.equals(lname)) && lname!=""){
    	driver.findElement(lname1).clear();
    	driver.findElement(lname1).sendKeys(lname);
    }}
    
    public String getAlias(){
    	return driver.findElement(addrAlias).getAttribute("value");
    }
    
    public void setAlias(String aka){
    	String ala=this.getAlias();
		  if(!(ala.equals(aka)) && aka!=""){
    	driver.findElement(addrAlias).clear();
    	driver.findElement(addrAlias).sendKeys(aka);
    }}
    
    public void sbmtInfo(){
    	driver.findElement(register).click();
    }
    
    public WebElement getSignUpPageError(){
    	return driver.findElement(err);
    }
    
    
    
    public MyAccountPage signUp(String title,String fName,String lName,String email,String pswrd,String day,String month,String year,String chkbox1,String chkbox2,String addrFname,String addrLname,String comp,String addr1,String addr2,String town,String nation,String state,String pincode,String info,String phoneNo,String mobNo,String alias ) throws InterruptedException{

        //Fill user name
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	this.setTitle(title);
        this.setFName(fName);
        this.setLName(lName);
        if(!(this.getEmail().equalsIgnoreCase(email)))
        	this.setEmail(email);
        this.setPassword(pswrd);
        if(day!="" && month!="" && year!=null)
        try{
        	this.setDOB(day, month, year);
        	}
        catch(Exception e){
        	System.out.println("Invalid day/month/year");
        	System.out.println(e.getMessage());
        }
        if (chkbox1=="Y")
        		this.setChkBox1();
        if (chkbox2=="Y")
        		this.setChkBox2();
        if (!(addrFname==this.getAddrFName()))
        	this.setAddrFName(addrFname);
        if (!(addrLname==this.getAddrLName()))
        	this.setAddrLName(addrLname);
        this.setCompany(comp);
        this.setAddr1(addr1);
        this.setAddr2(addr2);
        this.setCity(town);
        try{
        this.setCountry(nation);
        }catch(Exception e){
        	System.out.println("Invalid country selection");
        	System.out.println(e.getMessage());
        }
        if (nation=="United States")
        	try{
        	this.setState(state);}
        catch(Exception e){
        	System.out.println("Invalid state selection");
        	System.out.println(e.getMessage());
        	}
        
        this.setZip(pincode);
        this.setInfo(info);
        this.setPhone(phoneNo);
        this.setMobile(mobNo);
        if (alias!=null)
        	this.setAlias(alias);

        this.sbmtInfo();  
        Thread.sleep(3000);
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return new MyAccountPage(driver);

        

    }
    
    
}
