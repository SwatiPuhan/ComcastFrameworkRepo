package com.comcast.crm.orgtestWithPOM;
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
import com.comcast.crm.objectrepoUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepoUtility.HomePage;
import com.comcast.crm.objectrepoUtility.LoginPage;
import com.comcast.crm.objectrepoUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepoUtility.OrganizationPage;
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
		String USERNAME =fLib.getDataFromPropertiesFile("username");
		String PASSWORD =fLib.getDataFromPropertiesFile("password");
		System.out.println(URL);
		
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
		
		//step1:login to app
		
		 driver.get(URL);
		 LoginPage lp = new LoginPage(driver);
		 lp.loginToApp(USERNAME,PASSWORD);
		
		//step2: Navigate to Organisation module 
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();

		//step3: click on Create Organisation button
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		//step4: Enter all the details to the Organisation name textfield
		
		 CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	     cnop.createOrg(orgName, PhoneNo);
	     
	    //verify phone number
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actPhoneNo=oip.getPhoneMsg().getText();
		
		 if(actPhoneNo.contains(PhoneNo))
	        {
	       	 System.out.println(PhoneNo + "name is verified");
	        }
	        else
	        {
	       	 System.out.println(PhoneNo + "name is not verified");
	        }
		
		hp.logOut();
		wLib.postCondition(driver);     
	}
}












	

	