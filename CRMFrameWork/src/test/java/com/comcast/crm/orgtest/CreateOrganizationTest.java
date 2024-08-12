package com.comcast.crm.orgtest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class CreateOrganizationTest 
{
	public static void main(String[] args) throws Throwable {

		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		//common data from notepad
		String URL = fLib.getDataFromPropertiesFile("url");
		String BROWSER =fLib.getDataFromPropertiesFile("browser");
		String UN =fLib.getDataFromPropertiesFile("username");
		String PWD =fLib.getDataFromPropertiesFile("password");
		
		
		//generate Random number
		//read data Excel
		String orgName=eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();
		

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
		//Random ran= new Random();
		//int randomvalue = ran.nextInt(1000);

		//step1:login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(UN);
		driver.findElement(By.name("user_password")).sendKeys(PWD);
		driver.findElement(By.id("submitButton")).submit();
		
		wLib.webPageScreenShot(driver, "homePage");

		//step2: Navigate to Organisation module 
		driver.findElement(By.linkText("Organizations")).click();

		//step3: click on Create Organisation button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		//step4: Enter the values to the Organisation name textfield
         driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//step5: click on save button
		//driver.findElement(By.name("button")).click();
         driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();


		//verify  Headermsg ExpectedREsult

		String headerinfo =driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgName)) {
			System.out.println(orgName+ "verfied=pass");
		}
		else {
			System.out.println(orgName+ "verfied=Fail");
		}
		//verify  Header orgName info ExpectedREsult
		
		String HeaderorgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(HeaderorgName.equals(orgName)) {
			System.out.println(orgName+ "verfied=pass");
		}
		else {
			System.out.println(orgName+ "verfied=Fail");
		}
		wLib.postCondition(driver);        
	}
}




		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


		