package vtiger;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoMaven {
	  private WebDriver driver;
	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @BeforeTest
	  public void setUp() throws Exception {
/*		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe") ; 
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability("nativeEvents", true);
		driver = new InternetExplorerDriver(capabilities); */
		  
		System.setProperty("webdriver.gecko.driver",
				"G:\\Selenium\\drivers\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		File pathBinary = new File("C:\\Uday\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(firefoxBinary, firefoxProfile);

	    baseUrl = "http://localhost:8888/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void vtiger() throws Exception {
	    driver.get(baseUrl + "/index.php");
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("admin");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("uday");
	    driver.findElement(By.xpath("//button[@type='submit']")).sendKeys(Keys.ENTER);
	    //driver.findElement(By.xpath("//button[@type='submit']")).click();
	    System.out.println("Logged into Vtiger App");
	    new WebDriverWait(driver, 20000).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Administrator")));
	    driver.findElement(By.partialLinkText("Administrator")).click();
	    new WebDriverWait(driver, 20000).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign Out")));
	    driver.findElement(By.linkText("Sign Out")).click();
	    System.out.println("Logged out from Vtiger App");

	    try {
	      Assert.assertEquals("Sign in", driver.findElement(By.xpath("//button[@type='submit']")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	  }
	  
	  @AfterTest
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      Assert.fail(verificationErrorString);
	    }
	  }

	

}
