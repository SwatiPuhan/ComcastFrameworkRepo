package com.comcast.crm.orgtestWithPOM;

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
import com.comcast.crm.objectrepoUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepoUtility.HomePage;
import com.comcast.crm.objectrepoUtility.LoginPage;
import com.comcast.crm.objectrepoUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepoUtility.OrganizationPage;
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
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
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

	
		//step1:login to app
		 driver.get(URL);
		 LoginPage lp = new LoginPage(driver);
		 lp.loginToApp(USERNAME,PASSWORD);
		 
		
		//step2: Navigate to Organisation module 
		 HomePage homePage=new  HomePage(driver);
		 homePage.getOrganizationLink().click();
	
		 //step3: click on Create Organisation button
		 OrganizationPage createNewPage=new OrganizationPage(driver);
		 createNewPage.getCreateNewOrgBtn().click();
		 

		//step4: Enter all the details to the Organisation name textfield
		 CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
	     //cnop.createOrg(orgName);

		//before saving we need to select dropdown
		cnop.createOrg(orgName, industry, type);
        
		//step5: click on save button
        //cnop.getSaveBtn();

        //verify the dropdown industry and type info
        OrganizationInfoPage oip=new OrganizationInfoPage(driver);
        String actIndustryName=oip.getIndustryMsg().getText();
        if(actIndustryName.contains(industry))
        {
       	 System.out.println(industry + "name is verified");
        }
        else
        {
       	 System.out.println(industry + "name is not verified");
        }

		//type dropdown verify

        
        String actTypeName=oip.getTypeMsg().getText();
        if(actTypeName.contains(type))
        {
       	 System.out.println(type + "name is verified");
        }
        else
        {
       	 System.out.println(type + "name is not verified");
        }
        
        
        homePage.logOut();
        wLib.postCondition(driver);        
	}
}













