package com.comcast.crm.contactTestPOM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.objectrepoUtility.ContactInfoPage;
import com.comcast.crm.objectrepoUtility.ContactPage;
import com.comcast.crm.objectrepoUtility.CreateNewContactPage;
import com.comcast.crm.objectrepoUtility.HomePage;
import com.comcast.crm.objectrepoUtility.LoginPage;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class CreateContactTestPOM {

	public static void main(String[] args) throws Throwable 
	{
		WebDriverUtility wLib=new WebDriverUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		ExcelUtility eLib=new ExcelUtility();		
	
	String URL = fLib.getDataFromPropertiesFile("url");
	String BROWSER = fLib.getDataFromPropertiesFile("browser");
	String USERNAME = fLib.getDataFromPropertiesFile("username");
	String PASSWORD = fLib.getDataFromPropertiesFile("password");
	System.out.println(URL);
	

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
	driver.get(URL);
	
     //step1:login to app
	 LoginPage lp = new LoginPage(driver);
	 lp.loginToApp(USERNAME,PASSWORD);
	
	//Navigate to contactModule
	HomePage hp=new HomePage(driver);
	hp.navigateToContactModule();
	hp.getContactLink().click();
	
	//Navigate to contact create page
	ContactPage cp=new ContactPage(driver);
	cp.getCreateNewContactBtn().click();
	
	//enter mandatory details & click on save button
	
	CreateNewContactPage cncp=new CreateNewContactPage(driver);
	cncp.createContact(LastName);
	
	//verify lastname Txt Fld
	ContactInfoPage cip=new ContactInfoPage(driver);
	String actLastName = cip.getVerifyLastNameTxtFld().getText();
	if(actLastName.equals(LastName))
	{
		System.out.println(LastName + "information is passed");
	}
	else
	{
		System.out.println(LastName + "information is not passed");
	}
	hp.logOut();
	wLib.postCondition(driver);       


}
}



