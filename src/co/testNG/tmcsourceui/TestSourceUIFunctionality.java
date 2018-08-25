package co.testNG.tmcsourceui;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utility.TestCompose255BlankModeUtil;
import com.utility.TestCompose255BulkUtil;
import com.utility.TestCompose255SingleLocUtil;
import com.utility.TestCreateTagUtil;
import com.utility.TestHideFlagUtil;
import com.utility.TestProjectStatusUtil;
import com.utility.TestRecommFlagStatus;
import com.utility.TestSourceStatusUtil;
import com.utility.TestValidLoginUtil;
import com.utility.TestValidTagUtil;

public class TestSourceUIFunctionality {
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
					driver.get("http://localhost:8085/tmc-source-ws/html");
					
				} catch (Exception e) {
					e.printStackTrace();
				}	
		  }
		  
		  @DataProvider
		  public Iterator<Object[]> getValidLoginData(){
			  ArrayList<Object[]> myValidData=TestValidLoginUtil.getTestData();
			  return myValidData.iterator();
			  
			  
		  }
		  @DataProvider
		  public Iterator<Object[]> getProjectStatusData(){
			  ArrayList<Object[]> myProjectStatusData=TestProjectStatusUtil.getTestData();
			  return myProjectStatusData.iterator();
		  }
		  
		  @DataProvider
		  public Iterator<Object[]> getSourceStatusData(){
			  ArrayList<Object[]>mySourceStatusData=TestSourceStatusUtil.getTestData();
			  return mySourceStatusData.iterator();
		  }
		  
		  @DataProvider
		  public Iterator<Object[]> getHideFlagData(){
			  ArrayList<Object[]>myHideFlagData=TestHideFlagUtil.getTestData();
			  return myHideFlagData.iterator();
		  }
		  
		  @DataProvider
		  public Iterator<Object[]> getCreateTagData(){
			  ArrayList<Object[]> myCreateTagData=TestCreateTagUtil.getTestData();
			  return myCreateTagData.iterator();
		  }
		  
		 @DataProvider
		 public Iterator<Object[]> getvalidTagData(){
			  ArrayList<Object[]> myValidTagData=TestValidTagUtil.getTestData();
			  return myValidTagData.iterator();
		  }
		 
		 @DataProvider
		 public Iterator<Object[]> getCompose255BulkData(){
			 ArrayList<Object[]> myCompose255BulkData=TestCompose255BulkUtil.getTestData();
			 return myCompose255BulkData.iterator();
		 }
		 
		 
		 @DataProvider
		 public Iterator<Object[]> getCompose255SingleModeData(){
			 ArrayList<Object[]> myCompose255SingleLocData=TestCompose255SingleLocUtil.getTestData();
			 return myCompose255SingleLocData.iterator();
		 }
		 
		 @DataProvider
		 public Iterator<Object[]> getCompose255BlankModeData(){
			 ArrayList<Object[]> myCompose255BlankModeData=TestCompose255BlankModeUtil.getTestData();
			 return myCompose255BlankModeData.iterator();
		 }
		 
		 @DataProvider
		 public Iterator<Object[]> getRecommendedFlagStatus(){
			 ArrayList<Object[]> myRecommFlagStaus=TestRecommFlagStatus.getTestData();
			 return myRecommFlagStaus.iterator();
		 }
		 
		  @Test(dataProvider="getValidLoginData",groups="loginfunctionality")
		  public void loginTMCSoure(String username,String password) throws InterruptedException{
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
						//driver.navigate().to(driver.getCurrentUrl());
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
		  
		  @Test(dependsOnMethods="loginTMCSoure",groups="uploadTMCSource")
		  public void uploadTMCSourceFile(){
			  try {
				  Thread.sleep(5000);
				  WebElement fileupload=driver.findElement(By.xpath("//form[@id='upload-form']/div[1]/input[@id='sourcemdb']"));
				  Thread.sleep(5000);
				  fileupload.sendKeys("C:\\Users\\deshpanr\\Desktop\\KT Doc\\TMC_AND_Agencia de Mobilitat_1_0.0\\TMC_BEL_TECHNUM_1_2.10.MDB");
				  driver.findElement(By.xpath("//form[@id='upload-form']/button[@type='submit']")).click();
				  new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='upload-form']/div[3]/img[@id='loadingGif']")));
				  Assert.assertFalse(driver.findElement(By.xpath("//form[@id='upload-form']/div[3]/img[@id='loadingGif']")).isDisplayed());
			 
			} catch (Exception e) {
				e.printStackTrace();
			}
			  
		  }
		  @Test(dataProvider="getHideFlagData",dependsOnMethods="loginTMCSoure",groups="editablefields")
		  public void hideFlagStatusCheck(String hideFlagValue) throws InterruptedException{
			  Actions action = new Actions(driver);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[10]/div[contains(@class,'btn-group')]/button[contains(@class,'btn btn-xs btn-info dropdown-toggle')]/span[contains(@class,'caret')]"))).click();
				WebElement dropdown=driver.findElement(By.xpath("//tbody/tr[1]/td[10]/div[contains(@class,'btn-group')]/ul[contains(@class,'dropdown-menu')]"));
				action.moveToElement(dropdown);
				List<WebElement> options=driver.findElements(By.xpath("//tbody/tr[1]/td[10]/div[contains(@class,'btn-group')]/ul[contains(@class,'dropdown-menu')]/li"));
				for (WebElement webElement : options) {
					if(webElement.getText().equals(hideFlagValue)){
						webElement.click();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formHideFlag']/div[3]/button[@type='submit']"))).click();
						String updhideFlagStatus=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody/tr[1]/td[10]/div[1]/button[@id='hideFlagText']")).getText();
						Assert.assertEquals(updhideFlagStatus,hideFlagValue);
		  }
				}
		  }
					
		  @Test(dataProvider="getProjectStatusData",dependsOnMethods="loginTMCSoure",groups="editablefields")
		  public void projectStatusCheck(String ProjectStatus) throws InterruptedException{
			  Actions action = new Actions(driver);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[8]/div[contains(@class,'btn-group')]/button[contains(@class,'btn btn-xs btn-info dropdown-toggle')]/span[contains(@class,'caret')]"))).click();
				WebElement dropdown=driver.findElement(By.xpath("//tbody/tr[1]/td[8]/div[contains(@class,'btn-group')]/ul[contains(@class,'dropdown-menu')]"));
				action.moveToElement(dropdown);
				List<WebElement> options=driver.findElements(By.xpath("//tbody/tr[1]/td[8]/div[contains(@class,'btn-group')]/ul[contains(@class,'dropdown-menu')]/li"));
				for (WebElement webElement : options) {
					if(webElement.getText().equals(ProjectStatus)){
						webElement.click();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formProjectStatus']/div[3]/button[@type='submit']"))).click();
						String upProjStatus=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody/tr[1]/td[8]/div[1]/button[@id='projectStatus']")).getText();
						Assert.assertEquals(upProjStatus, ProjectStatus);
		  }
}
}
		  
		  @Test(dataProvider="getSourceStatusData",dependsOnMethods="loginTMCSoure",groups="editablefields")
		  public void sourceStatusCheck(String sourceStatus) throws InterruptedException{
			  Actions action = new Actions(driver);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[9]/div[contains(@class,'btn-group')]/button[contains(@class,'btn btn-xs btn-info dropdown-toggle')]/span[contains(@class,'caret')]"))).click();
				WebElement dropdown=driver.findElement(By.xpath("//tbody/tr[1]/td[9]/div[contains(@class,'btn-group')]/ul[contains(@class,'dropdown-menu')]"));
				action.moveToElement(dropdown);
				List<WebElement> options=driver.findElements(By.xpath("//tbody/tr[1]/td[9]/div[contains(@class,'btn-group')]/ul[contains(@class,'dropdown-menu')]/li"));
				for (WebElement webElement : options) {
					if(webElement.getText().equals(sourceStatus)){
						webElement.click();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formSourceStatus']/div[3]/button[@type='submit']"))).click();
						String upSourceStatus=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody/tr[1]/td[9]/div[1]/button[@id='sourceStatusText']")).getText();
						Assert.assertEquals(upSourceStatus,sourceStatus);
		  }
				}
		  }
		  
		  @Test(dataProvider="getCreateTagData",dependsOnMethods="loginTMCSoure",groups="createTag")
		  public void createTag(String tagName,String expectedmsg){
				try {
					Thread.sleep(1000);
					driver.findElement(By.id("create-tag")).click();
					driver.findElement(By.id("inputTagName")).sendKeys(tagName);
					driver.findElement(By.xpath("//form[@id='formTagCreate']/div[3]/button[@type='submit']")).click();
					Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
					String alertmsg=a.getText();
					a.accept();
					//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='formTagCreate']/div[3]/button[1]"))).click();
					driver.findElement(By.xpath(".//*[@id='formTagCreate']/div[3]/button[1]")).click();
					Assert.assertEquals(alertmsg,expectedmsg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		  @Test(dataProvider="getvalidTagData",dependsOnMethods="loginTMCSoure",groups="createTag")
		  public void checkTagCreation(String tagName,String expectedMsg){
			  try {
					Thread.sleep(1000);
					driver.findElement(By.id("create-tag")).click();
					driver.findElement(By.id("inputTagName")).sendKeys(tagName);
					driver.findElement(By.xpath("//form[@id='formTagCreate']/div[3]/button[@type='submit']")).click();
					Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
					String alertMsg=driver.switchTo().alert().getText();
					a.accept();
					if(!(alertMsg.equalsIgnoreCase(expectedMsg))){
						boolean invalidTag=true;
						driver.findElement(By.xpath(".//*[@id='formTagCreate']/div[3]/button[1]")).click();
						Assert.assertFalse(invalidTag, "Invalid Tag");	
					}
					Select tagdropdown=new Select(driver.findElement(By.xpath(".//*[@id='tag-form']/div/select")));
					List<WebElement> tagOptions=tagdropdown.getOptions();
					boolean tagFound=false;
					for (WebElement webElement : tagOptions) {
						if(webElement.getText().equalsIgnoreCase(tagName)){
							tagFound=true;
							break;
						}
					}
					Assert.assertTrue(tagFound);
			  } catch (InterruptedException e) {
					e.printStackTrace();
				}
		  }
		  @Test(dataProvider="getCompose255BulkData",dependsOnMethods="loginTMCSoure",groups="compose 255")
		  public void checkCompose255BulkMode(String mode,String countryCode,String locationNumber,String fromProvider,String toProvider,String fromVersion,String toVersion,String expectedResult){
			  try {
					Thread.sleep(1000);
					driver.findElement(By.id("compose-255-cpvl")).click();
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']"))).click();
					Thread.sleep(1000);
					Select compose255=new Select (driver.findElement(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']")));
					compose255.selectByVisibleText(mode);
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("ipCountryCodeBulk"))).sendKeys(countryCode);
					driver.findElement(By.id("ipLocationTableNumberBulk")).sendKeys(locationNumber);
					driver.findElement(By.id("ipFromProvider")).sendKeys(fromProvider);
					driver.findElement(By.id("ipToProvider")).sendKeys(toProvider);
					driver.findElement(By.id("ipFromVersion")).sendKeys(fromVersion);
					driver.findElement(By.id("ipToVersion")).sendKeys(toVersion);
					driver.findElement(By.xpath("//form[@id='formCompose255']/div[3]/button[@type='submit']")).click();
					Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
					String alertmsg=a.getText();
					a.accept();
					driver.findElement(By.xpath(".//*[@id='formCompose255']/div[3]/button[1]")).click();
					Assert.assertEquals(alertmsg,expectedResult );
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		  }
		  
		  @Test(dataProvider="getCompose255SingleModeData",dependsOnMethods="loginTMCSoure",groups="compose 255")
		  public void checkCompose255SingleMode(String mode,String countryCode,String locationNumber,String Provider,String version,String locCode,String composeFlag,String comment,String expectedResult){
			  try {
					Thread.sleep(1000);
					driver.findElement(By.id("compose-255-cpvl")).click();
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']"))).click();
					Thread.sleep(1000);
					Select compose255=new Select (driver.findElement(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']")));
					compose255.selectByVisibleText(mode);
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("ipCountryCode"))).sendKeys(countryCode);
					driver.findElement(By.id("ipLocationTableNumber")).sendKeys(locationNumber);
					driver.findElement(By.id("ipProvider")).sendKeys(Provider);
					driver.findElement(By.id("ipVersion")).sendKeys(version);
					driver.findElement(By.id("ipLocationcode")).sendKeys(locCode);
					new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@ng-show='singleModeOn']/div[3]/div[2]/select[@name='composedFlagValue']"))).click();
					Select flagValue=new Select(driver.findElement(By.xpath("//div[@ng-show='singleModeOn']/div[3]/div[2]/select[@name='composedFlagValue']")));
					flagValue.selectByVisibleText(composeFlag);
					driver.findElement(By.id("composedFlagComment")).sendKeys(comment);
					driver.findElement(By.xpath("//form[@id='formCompose255']/div[3]/button[@type='submit']")).click();
					Alert a = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
					String alertmsg=a.getText();
					a.accept();
					driver.findElement(By.xpath(".//*[@id='formCompose255']/div[3]/button[1]")).click();
					Assert.assertEquals(alertmsg, expectedResult);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		  }
		  
		 @Test(dataProvider="getRecommendedFlagStatus",dependsOnMethods="loginTMCSoure",groups="editablefields")
		  public void recommendedFlagCheck(String currentFlagStatus1,String changedFlagStatus1,String currentFlagStatus2,String changedFlagStatus2){
			 
				try {
					Thread.sleep(1000);
					String beforeclickFlag=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody[1]/tr[1]/td[1]/span[@ng-click='!isuserauthenticated.length || onRecommendedVersionClick(m)']")).getAttribute("class");
					if(beforeclickFlag.equalsIgnoreCase(currentFlagStatus1)){
						driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[@ng-click='!isuserauthenticated.length || onRecommendedVersionClick(m)']")).click();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formrecommendedFlag']/div[3]/button[@type='submit']"))).click();
						String afterClickRecommValue=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody[1]/tr[1]/td[1]/span[@ng-click='!isuserauthenticated.length || onRecommendedVersionClick(m)']")).getAttribute("class");
						Assert.assertEquals(afterClickRecommValue, changedFlagStatus1);
					}
					else if(beforeclickFlag.equalsIgnoreCase(currentFlagStatus2)){
						driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[@ng-click='!isuserauthenticated.length || onRecommendedVersionClick(m)']")).click();
						new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formrecommendedFlag']/div[3]/button[@type='submit']"))).click();
						String afterClickRecommValue=driver.findElement(By.xpath("//table[@ng-init='loadTableData()']/tbody[1]/tr[1]/td[1]/span[@ng-click='!isuserauthenticated.length || onRecommendedVersionClick(m)']")).getAttribute("class");
						Assert.assertEquals(afterClickRecommValue, changedFlagStatus2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		  
		  @Test(dataProvider="getCompose255BlankModeData",dependsOnMethods="loginTMCSoure",groups="compose 255")
		  public void checkCompose255BlankMode(String mode,String expectedResult) throws InterruptedException{
			  Thread.sleep(1000);
				driver.findElement(By.id("compose-255-cpvl")).click();
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']"))).click();
				Thread.sleep(1000);
				Select compose255=new Select (driver.findElement(By.xpath("//form[@id='formCompose255']/div[2]/div[1]/div[1]/select[@name='select-mode']")));
				compose255.selectByVisibleText(mode);
				driver.findElement(By.xpath("//form[@id='formCompose255']/div[3]/button[@type='submit']")).click();
				Alert alert=driver.switchTo().alert();
				String alertmsg=driver.switchTo().alert().getText();
				alert.accept();
				driver.findElement(By.xpath(".//*[@id='formCompose255']/div[3]/button[1]")).click();
				Assert.assertEquals(alertmsg, expectedResult);
				
		  }

		  @AfterClass(groups="closeBrowserfunctionality")
		  public void functionalityCloseBrowser(){
			  driver.quit();
		}
	}
}
