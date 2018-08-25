package co.testNG.tmcsourceui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.utility.TestLoginUtil;

public class TMCSourceUILogin {
	WebDriver driver;
  @BeforeClass(groups="invokeBrowser")
  public void invokeBrowser(){
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get("http://localhost:8085/tmc-source-ws/html");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
  @DataProvider
  public Iterator<Object[]> getData(){
	  ArrayList<Object[]> testData=TestLoginUtil.getTestData();
	  return testData.iterator();
  }
  
  @Test(dataProvider="getData",groups="login")
  public void tmcSourceLogin(String username,String password) throws InterruptedException{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//form[@id='login-form']/button[@class='btn btn-primary']")).click();
		Thread.sleep(1000);
		if(isAlertPresent()){
		driver.switchTo().alert().accept();
		boolean Upload=driver.findElement(By.xpath("//form[@id='upload-form']/button[1]/span[@class='glyphicon glyphicon-upload']")).isDisplayed();
		Assert.assertTrue(Upload);
			}
			else{
				boolean Upload=driver.findElement(By.xpath("//form[@id='upload-form']/button[1]/span[@class='glyphicon glyphicon-upload']")).isDisplayed();
				Assert.assertEquals(Upload, true);
				driver.navigate().to(driver.getCurrentUrl());
			}
	}
  private boolean isAlertPresent() {
		boolean foundAlert = false;
	    WebDriverWait wait = new WebDriverWait(driver, 0 /*timeout in seconds*/);
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        foundAlert = true;
	    } catch (TimeoutException eTO) {
	        foundAlert = false;
	    }
	    return foundAlert;
	}
  
  @AfterClass(groups="closeBrowser")
  public void closeBrowser(){
	  driver.quit();
  }
  
  
}

