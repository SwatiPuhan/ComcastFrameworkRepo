package com.comcast.crm.contacttest;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable 
	{
		WebDriverUtility wLib=new WebDriverUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();		
	
	String URL = fLib.getDataFromPropertiesFile("url");
	String BROWSER = fLib.getDataFromPropertiesFile("browser");
	String UN = fLib.getDataFromPropertiesFile("username");
	String PWD = fLib.getDataFromPropertiesFile("password");
	

	//generate random number
//	Random random=new Random();
//	int randomvalue=random.nextInt(1000);
	
	//Read the data from excel file
	//6 row 3 column
	String LastName=eLib.getDataFromExcel("contact", 6, 3)+jLib.getRandomNumber();
	
	
	


	WebDriver driver=null;
	
	if(BROWSER.equals("chrome")) 
	{
		driver=new ChromeDriver();
	}
	else if(BROWSER.equals("edge"))
	{
		driver=new EdgeDriver();
		
	}
	wLib.preCondition(driver);
	wLib.waitForPageToLoad(driver);
	
	 
	//step1:login to app
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.get(URL);
	driver.findElement(By.name("user_name")).sendKeys(UN);
	driver.findElement(By.name("user_password")).sendKeys(PWD);
	driver.findElement(By.id("submitButton")).submit();
	
	//Navigate to contactModule
	driver.findElement(By.linkText("Contacts")).click();
	
	
	//Navigate to contact create
	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	driver.findElement(By.name("lastname")).sendKeys(LastName);
	
	//step3: click on save button
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//verify
	String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
	if(actLastName.equals(LastName))
	{
		System.out.println(LastName + "information is passed");
	}
	else
	{
		System.out.println(LastName + "information is not passed");
	}
	wLib.postCondition(driver);       


}
}



