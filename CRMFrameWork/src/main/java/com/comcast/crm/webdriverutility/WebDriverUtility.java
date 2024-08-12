package com.comcast.crm.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	

	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void waitForElementToBeClick(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitForElementVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	public void waitForAllElementVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	public void waitForUrlLoad(WebDriver driver,String value)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains(value));
	}
	public void waitForTitleLoad(WebDriver driver,String value)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains(value));
	}
	public void preCondition(WebDriver driver)
	{
		driver.manage().window().minimize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void postCondition(WebDriver driver)
	{
		driver.manage().window().minimize();
		driver.quit();
	}
 public void switchNewBrowserTab(WebDriver driver , String partialUrl)
	{
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		
		while(it.hasNext())
		{
			String windowID=it.next();
			driver.switchTo().window(windowID);
			
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains(partialUrl))
			{
				break;
			}
		}
	}
		 public void switchToTabOnTitle(WebDriver driver , String partialTitle)
			{
				Set<String> set=driver.getWindowHandles();
				Iterator<String> it=set.iterator();
				
				while(it.hasNext())
				{
					String windowID=it.next();
					driver.switchTo().window(windowID);
					
					String actUrl=driver.getTitle();
					if(actUrl.contains(partialTitle))
					{
						break;
					}
				}
	}
	
	public void doubleClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();

	}
	public void mouseMoveElement(WebDriver driver , WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void contextClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();

	}

	public void clickAndHold(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.clickAndHold(element);
	}
	public void scrollByAmout(WebDriver driver,WebElement element) {
		Actions act=new Actions(driver);
		act.scrollByAmount(90, 89);
		
	}
	public void scrollToElement(WebDriver driver,WebElement element) {
		Actions act=new Actions(driver);
		act.scrollToElement(element);
	}

	public void dragAndDrop(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.dragAndDrop(element, element);

	}
	public void switchToFrameByIndex(WebDriver driver ,int index) 
	{
	 driver.switchTo().frame(index);
		
	}
	
	public void switchToFrameByName(WebDriver driver ,String nameorId ) //frame RT WD
	{
		 driver.switchTo().frame(nameorId);
		
	}
	public void switchToFrameByValue(WebDriver driver ,String value ) //frame RT WD
	{
	       driver.switchTo().frame(value);
		
	}
	public void switchToFrameEle(WebDriver driver ,WebElement element ) //frame RT WD
	{
	       driver.switchTo().frame(element);
		
	
	}
	public void switchToDefaultFrame(WebDriver driver ) //frame RT WD
	{
	       driver.switchTo().defaultContent();
	}
	public void switchToParentFrame(WebDriver driver) //frame RT WD
	{
	       driver.switchTo().parentFrame();
	}
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	public void selectByVisibleText(WebElement element , String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
		
	}
	public void selectByIndex(WebElement element , int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void selectByValue(WebElement element , String Value)
	{
		Select sel=new Select(element);
		sel.selectByValue(Value);
	}	
	public void deSelectDropDown(WebElement element , String text)
	{
		Select sel=new Select(element);
		sel.deselectByVisibleText(text);
	}
	public void deSelectDropDownByValue(WebElement element , String value)
	{
		Select sel=new Select(element);
		sel.deselectByValue(value);
	}
	public void deSelectDropDown1(WebElement element , int index)
	{
		Select sel=new Select(element);
		sel.deselectByIndex(index);
	}
	public void webPageScreenShot(WebDriver driver,String name) throws IOException
	{
		JavaUtility ju=new JavaUtility();
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm=new File("./errorshots/"+name+" "+ju.getLocalDateAndTime()+".png");
	    org.openqa.selenium.io.FileHandler.copy(temp, perm);
	}
	public void webElementSS(WebElement element , String pagename) throws IOException
	
	{
		JavaUtility ju=new JavaUtility();
		File temp = element.getScreenshotAs(OutputType.FILE);
		File perm=new File("./bugShots/"+pagename+ju.getLocalDateAndTime()+".png");
	    org.openqa.selenium.io.FileHandler.copy(temp, perm);
		
		
	}
	public void javaScriptScrollDown(WebDriver driver , int scrollamount)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;

		js.executeScript("window.scrollBy(0,"+scrollamount+");");
	}
	
	public void javaScriptScrollUntilElementVisible(WebElement element)
	{
	JavascriptExecutor js=(JavascriptExecutor)element;
	int y=element.getLocation().getY();
	js.executeScript("window.scrollBy(0,"+y+");");

	
	
	}
}




