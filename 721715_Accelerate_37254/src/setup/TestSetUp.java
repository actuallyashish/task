package setup;

import java.util.concurrent.TimeUnit;






import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TestSetUp {
	
	WebDriver driver;
	String url;
	Logger log;
	
	public TestSetUp(Class<?> className){
		this.log=Logger.getLogger(className);
	}
	
	public WebDriver setTest(){
		BasicConfigurator.configure();
		  ConsoleAppender ca=new ConsoleAppender(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN));
		  log.addAppender(ca);
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\ashish.tiwari12\\Downloads\\SelNoobie-master\\SelNoobie-master\\chromedriver.exe");
	driver = new FirefoxDriver();
	  //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	url = "http://www.automationpractice.com";
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;
}
	}
