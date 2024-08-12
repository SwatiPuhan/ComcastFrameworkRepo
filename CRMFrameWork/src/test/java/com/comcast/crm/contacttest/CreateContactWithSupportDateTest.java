package com.comcast.crm.contacttest;
    import java.text.SimpleDateFormat;
	import java.time.Duration;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.Properties;
	import java.util.Random;

	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class CreateContactWithSupportDateTest 
{
	   static WebDriver driver;
		public static void main(String[] args) throws Throwable {

			WebDriverUtility wLib=new WebDriverUtility();
			FileUtility fLib=new FileUtility();
			JavaUtility jLib=new JavaUtility();
			ExcelUtility eLib=new ExcelUtility();
			
		    String URL = fLib.getDataFromPropertiesFile("url");
			String BROWSER =fLib.getDataFromPropertiesFile("browser");
			String UN =fLib.getDataFromPropertiesFile("username");
			String PWD =fLib.getDataFromPropertiesFile("password");
			
			
			String lastName=eLib.getDataFromExcel("contact", 1, 2);
			int nn=jLib.getRandomNumber();
			
			if(BROWSER.equals("chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(BROWSER.equals("edge"))
			{
				driver=new EdgeDriver();
			}
			wLib.postCondition(driver);
			wLib.waitForPageToLoad(driver);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("url");
			driver.findElement(By.name("user_name")).sendKeys("username"); 
			driver.findElement(By.name("user_password")).sendKeys("password");
			
			driver.findElement(By.id("submitButton")).click();
			driver.findElement(By.linkText("Contacts")).click();
			
			driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
			driver.findElement(By.name("lastname")).sendKeys(lastName+nn);
			
			driver.findElement(By.name("support_start_date")).clear();
			driver.findElement(By.name("support_start_date")).sendKeys(jLib.getSystemDateYYYYMMDD());
			
			driver.findElement(By.name("support_end_date")).clear();
			driver.findElement(By.name("support_end_date")).sendKeys(jLib.getRequiredDateYYYYMMDD(nn));
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			String heading=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(heading.contains(lastName+nn))
					{
				      System.out.println("contact is created");
					}
			else
			{
				System.out.println("contact is not created");
			}
			String lastname=driver.findElement(By.id("dtlview_Last Name")).getText();
			if(lastname.equals(nn))
			{
				System.out.println("last name is verified");
			}
			else
			{
				System.out.println("last name is not verified");
			}
			String startdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
			if(startdate.equals(jLib.getSystemDateYYYYMMDD()))
			{
				System.out.println("the start date is verified");
			}
			else
			{
				System.out.println("the start date is not verified");
			}
			String enddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
			if(enddate.equals(jLib.getRequiredDateYYYYMMDD(nn)))
			{
				System.out.println(enddate+" last date is verified");
			}
			else
			{
				System.out.println(enddate+" teh last date is not verified");
			}
			driver.findElement(By.xpath("(//img)[4]")).click();
		    driver.findElement(By.linkText("Sign Out")).click();
		    wLib.postCondition(driver);
		}


	}






