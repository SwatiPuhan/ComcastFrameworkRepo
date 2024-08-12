package com.comcast.crm.orgtest;

	import java.time.Duration;
	import java.util.Properties;
	import java.util.Random;

	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;


	public class CreateOrgWithIndustryTest 
	{


		public static void main(String[] args) throws Throwable {
			
			FileUtility fLib=new FileUtility();
			ExcelUtility eLib=new ExcelUtility();
			JavaUtility jLib=new JavaUtility();
			WebDriverUtility wLib=new WebDriverUtility();

			//common data from notepad

		
			String URL = fLib.getDataFromPropertiesFile("url");
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String UN = fLib.getDataFromPropertiesFile("username");
			String PWD = fLib.getDataFromPropertiesFile("password");


			System.out.println(URL);

            //Read the test script data from excel file
            //4 row 2,3,4 column
		
			String orgName =eLib.getDataFromExcel("org", 4, 2)+jLib.getRandomNumber();
			String industry = eLib.getDataFromExcel("org", 4, 3);
			String type =eLib.getDataFromExcel("org", 4, 4);
			


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

			//before saving we need to select dropdown
			WebElement industryDropdown = driver.findElement(By.name("industry"));
			Select select=new Select(industryDropdown);
			select.selectByValue("Energy");

			WebElement typeDropdown = driver.findElement(By.name("accounttype"));
			Select sel=new Select(typeDropdown);
			sel.selectByValue("Press");
			
		//	String Heading=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();



			//step5: click on save button
			//driver.findElement(By.name("button")).click();
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();


			//verify the dropdown industry and type info
			
			String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
			if(actIndustry.equals(industry))
			{
				System.out.println(industry + "information is passed");
			}
			else
			{
				System.out.println(industry + "information is not passed");
			}
			
			//type dropdown verify
			
			String actType=driver.findElement(By.xpath("//span[@id='dtlview_Type']/font")).getText();

			if(actType.equals(type))
			{
				System.out.println(type + "information is passed");
			}
			else
			{
				System.out.println(type + "information is not passed");
			}
			
			
		wLib.postCondition(driver);        
		}
	}













