package co.testNG.tmcsourceui;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.Index;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.utility.TestLoginUtil;



public class TMCSourceUIFunctionality {
  WebDriver driver;
  @BeforeClass(groups="invokeBrowserfunctionality")
  public void functionalityInvokeBrowser() {
	  try {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get("http://localhost:8080/tmc-source-ws/html");
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
  }
  
  @DataProvider
  public Object[] getData(){
	  ArrayList<Object[]> myData=TestLoginUtil.getTestData();
	  return myData.get(1);
	  
  }
 
  @Test(dataProvider="getData",groups="loginfunctionality")
  public void loginTMCSoure(String username,String password){
	  driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//form[@id='login-form']/button[@class='btn btn-primary']")).click();
  }
  @Test(dependsOnMethods="loginTMCSoure",groups="uploadTMCSource")
  public void uploadTMCSourceFile(){
	  try {
		  Thread.sleep(5000);
		  WebElement fileupload=driver.findElement(By.xpath("//form[@id='upload-form']/div[1]/input[@id='sourcemdb']"));
		  Thread.sleep(5000);
		  fileupload.sendKeys("C:\\Users\\deshpanr\\Desktop\\TMC_BEL_TECHNUM_1_2.15.MDB");
		  driver.findElement(By.xpath("//form[@id='upload-form']/button[@type='submit']")).click();
		  new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='upload-form']/div[3]/img[@id='loadingGif']")));
		  Assert.assertFalse(driver.findElement(By.xpath("//form[@id='upload-form']/div[3]/img[@id='loadingGif']")).isDisplayed());
	 
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
  }
  
  @Test(dependsOnMethods="loginTMCSoure",groups="editablefields")
  
	public void recommendedFlagCheck(){
		try {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[@ng-click='!isuserauthenticated.length || onRecommendedVersionClick(m)']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formrecommendedFlag']/div[3]/button[@type='submit']"))).click();
			String recommClass=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody[1]/tr[1]/td[1]/span[@ng-click='!isuserauthenticated.length || onRecommendedVersionClick(m)']")).getAttribute("class");
			Assert.assertEquals(recommClass, "glyphicon glyphicon-star");
			//driver.findElement(By.xpath("//form[@id='formrecommendedFlag']/div[3]/button[@type='submit']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
  @Test(dependsOnMethods="loginTMCSoure",groups="editablefields")
 public void projectStatusCheck(){
	  try {
		Thread.sleep(5000);
					driver.findElement(By.xpath("//tbody/tr[1]/td[8]/div[@class='btn-group']/button[2]/span[@class='caret']")).click();
					 new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu']/li[3]/a[@id='fusion']"))).click();
			         //Select projectStatus = new Select(driver.findElements(By.xpath("//table[@ng-init='loadTableData()']/tbody/tr[1]/td[8]/div[1]/button[@data-toggle='dropdown']")));
			         //Select flagValue=new Select(driver.findElement(By.xpath("//div[@ng-show='singleModeOn']/div[3]/div[2]/select[@name='composedFlagValue']")));
			         //projectStatus.selectByVisibleText("ongoing");
			         new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formProjectStatus']/div[3]/button[@type='submit']"))).click();
			         String projectStatusValue=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody/tr[1]/td[8]/div[1]/button[@id='projectStatus']")).getText();
					 Assert.assertEquals(projectStatusValue, "Fusion in progress");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
			}
@Test(groups="editablefields")

public void sourceStatusCheck(){
	try {
		Thread.sleep(5000);
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@ng-init='loadTableData()']/tbody/tr[1]/td[9]/div[1]/button[@data-toggle='dropdown']"))).click();
		//JavascriptExecutor executor = (JavascriptExecutor)driver;
		//executor.executeScript("arguments[0].click();", sourceStatusdropdown);
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu']/li[3]/a[@id='certified']"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formSourceStatus']/div[3]/button[@type='submit']"))).click();
		String sourceStatus=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody/tr[1]/td[9]/div[1]/button[@id='sourceStatusText']")).getText();
		Assert.assertEquals(sourceStatus, "Certified");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
  
@Test(groups="editablefields")

public void hideFlagCheck(){
	try {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//tbody/tr[1]/td[10]/div[@class='btn-group']/button[2]/span[@class='caret']")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu']/li[3]/a[@id='notdelivered']"))).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formHideFlag']/div[3]/button[@type='submit']"))).click();	
		String hideFlagValue=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody/tr[1]/td[10]/div[1]/button[@id='hideFlagText']")).getText();
		Assert.assertEquals(hideFlagValue, "Not Delivered");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

@Test(groups="createTag")

public void createTag(){
	try {
		Thread.sleep(5000);
		driver.findElement(By.id("create-tag")).click();
		driver.findElement(By.id("inputTagName")).sendKeys("Test2");
		driver.findElement(By.xpath("//form[@id='formTagCreate']/div[3]/button[@type='submit']")).click();
		Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		String alertmsg=a.getText();
		a.accept();
		Assert.assertEquals(alertmsg, "Tag Added Successfully");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
@Test(groups="createTag")
public void createTagFieldValidationsWithBlankInputField(){
	try {
		//entering blank value in createTag text field
		Thread.sleep(5000);
		driver.findElement(By.id("create-tag")).click();
		driver.findElement(By.id("inputTagName")).sendKeys("");
		driver.findElement(By.xpath("//form[@id='formTagCreate']/div[3]/button[@type='submit']")).click();
		Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		String alertMsg=driver.switchTo().alert().getText();
		a.accept();
		driver.findElement(By.xpath("//form[@id='formTagCreate']/div[3]/button[@ng-click='onCloseClick()']")).click();
		Assert.assertEquals(alertMsg, "Tag Version must be filled out!");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
}
@Test(groups="createTag")
public void createTagFieldValidationsWithDuplicateTagName(){
	try {
		//Enter duplicate value in createTag input field
		Thread.sleep(5000);
		driver.findElement(By.id("create-tag")).click();
		driver.findElement(By.id("inputTagName")).sendKeys("Test2");
		driver.findElement(By.xpath("//form[@id='formTagCreate']/div[3]/button[@type='submit']")).click();
		Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		String alertMsg=driver.switchTo().alert().getText();
		a.accept();
		driver.findElement(By.xpath("//form[@id='formTagCreate']/div[3]/button[@ng-click='onCloseClick()']")).click();
		Assert.assertEquals(alertMsg, "Duplicate Entry for given tag version");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
}
@Test(groups="compose 255")
public void checkCompose255WithoutSelectingModeDropDown(){
	try {
		Thread.sleep(5000);
		driver.findElement(By.id("compose-255-cpvl")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formCompose255']/div[3]/button[@type='submit']"))).click();	
		//driver.findElement(By.xpath("//form[@id='formCompose255']/div[3]/button[@type='submit']")).click();
		Alert a=new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		String alertMsg=driver.switchTo().alert().getText();
		a.accept();
		driver.findElement(By.xpath("//form[@id='formCompose255']/div[3]/button[@ng-click='onCompare255CloseClick()']")).click();
		Assert.assertEquals(alertMsg, "Please choose an appropriate mode of action from the select drop-down");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
}

@Test(groups="compose 255")

public void compose255BulkMode(){
	try {
		Thread.sleep(5000);
		driver.findElement(By.id("compose-255-cpvl")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']"))).click();
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']")).click();
		//driver.findElement(By.xpath("//form[@id='formCompose255']/div[2]/div[@ng-show='bulkModeOn']")).click();
		Select compose255=new Select (driver.findElement(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']")));
		compose255.selectByVisibleText("Bulk mode");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("ipCountryCodeBulk"))).sendKeys("6");
		driver.findElement(By.id("ipLocationTableNumberBulk")).sendKeys("1");
		driver.findElement(By.id("ipFromProvider")).sendKeys("TECHNUM");
		driver.findElement(By.id("ipToProvider")).sendKeys("TECHNUM");
		driver.findElement(By.id("ipFromVersion")).sendKeys("2.72");
		driver.findElement(By.id("ipToVersion")).sendKeys("2.8");
		driver.findElement(By.xpath("//form[@id='formCompose255']/div[3]/button[@type='submit']")).click();
		//new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']/option[@label='Bulk mode']"))).click();
		Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		String alertmsg=a.getText();
		a.accept();
		Assert.assertEquals(alertmsg, "Done!");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
}

@Test(groups="compose 255")

public void compose255SingleLocation(){
	try {
		Thread.sleep(5000);
		driver.findElement(By.id("compose-255-cpvl")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']"))).click();
		Thread.sleep(5000);
		Select compose255=new Select (driver.findElement(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']")));
		compose255.selectByVisibleText("Single location mode");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("ipCountryCode"))).sendKeys("6");
		driver.findElement(By.id("ipLocationTableNumber")).sendKeys("1");
		driver.findElement(By.id("ipProvider")).sendKeys("TECHNUM");
		driver.findElement(By.id("ipVersion")).sendKeys("2.8");
		driver.findElement(By.id("ipLocationcode")).sendKeys("7400");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@ng-show='singleModeOn']/div[3]/div[2]/select[@name='composedFlagValue']"))).click();
		Select flagValue=new Select(driver.findElement(By.xpath("//div[@ng-show='singleModeOn']/div[3]/div[2]/select[@name='composedFlagValue']")));
		flagValue.selectByVisibleText("255");
		driver.findElement(By.id("composedFlagComment")).sendKeys("Test");
		driver.findElement(By.xpath("//form[@id='formCompose255']/div[3]/button[@type='submit']")).click();
		Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		String alertmsg=a.getText();
		a.accept();
		Assert.assertEquals(alertmsg, "Done!");
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

  @AfterClass(groups="closeBrowserfunctionality")
  public void functionalityCloseBrowser(){
	  driver.quit();
  }
  
}
