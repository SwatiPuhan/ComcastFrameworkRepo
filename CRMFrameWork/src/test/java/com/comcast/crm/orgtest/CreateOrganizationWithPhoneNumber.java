package com.comcast.crm.orgtest;
import java.time.Duration;
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


public class CreateOrganizationWithPhoneNumber {


	public static void main(String[] args) throws Throwable {

		//common data from notepad

		WebDriverUtility wLib=new WebDriverUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();
		
	
		String URL = fLib.getDataFromPropertiesFile("url");
		String BROWSER =fLib.getDataFromPropertiesFile("browser");
		String UN =fLib.getDataFromPropertiesFile("username");
		String PWD =fLib.getDataFromPropertiesFile("password");
		System.out.println(URL);

		//generate random number
//		Random random=new Random();
//		int randomvalue=random.nextInt(1000);

		//Read the test script data from excel file
        //7,2,3
		
		String orgName=eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNumber();
		String PhoneNo=eLib.getDataFromExcel("org", 7, 3)+jLib.getRandomNumber();
		
		


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
		//generate random number
	//	Random ran= new Random();
		//int randomvalue = ran.nextInt(1000);

		//step1:login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UN);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).submit();

		//step2: Navigate to Organisation module 
		driver.findElement(By.linkText("Organizations")).click();

		//step3: click on Create Organisation button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		//step4: Enter all the details to the Organisation name textfield
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		driver.findElement(By.id("phone")).sendKeys(PhoneNo);

		//step5: click on save button
		//driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

	//verify phone number
		
		String actPhoneNo = driver.findElement(By.id("dtlview_Phone")).getText();
		if(actPhoneNo.equals(PhoneNo))
		{
			System.out.println(PhoneNo + "information is passed");
		}
		else
		{
			System.out.println(PhoneNo + "information is not passed");
		}
		
		
		wLib.postCondition(driver);     
	}
}












	

	