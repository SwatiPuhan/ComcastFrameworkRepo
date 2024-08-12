package com.comcast.crm.contacttest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest 
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

    //String orgName=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
	
	//String contactLastName=eLib.getDataFromExcel("contact", 7, 3);
		int nn=jLib.getRandomNumber();
	
	
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
	driver.findElement(By.name("user_name")).sendKeys(UN);
	driver.findElement(By.name("user_password")).sendKeys(PWD);
	driver.findElement(By.id("submitButton")).click();
	driver.findElement(By.linkText("Organizations")).click();
	driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	driver.findElement(By.name("accountname")).sendKeys(eLib.getDataFromExcel("org", 1, 2)+nn);
	//driver.findElement(By.id("phone")).sendKeys(sh.getRow(7).getCell(4).toString());
	driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
	
	String Heading=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(Heading.contains(eLib.getDataFromExcel("org", 1, 2)+nn))
	{
		System.out.println("verified");
	}
	else
	{
		System.out.println("not as expected");
	}
	String organizationname=driver.findElement(By.id("dtlview_Organization Name")).getText();
	if(organizationname.equals(eLib.getDataFromExcel("org", 1, 2)+nn))
	{
		System.out.println("organization name verified");
	}
	else
	{
		System.out.println("name is verified");
	}

	driver.findElement(By.linkText("Contacts")).click();
	driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	driver.findElement(By.name("lastname")).sendKeys(eLib.getDataFromExcel("contact", 1, 2)+nn);
	driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img")).click();
	//switch to child window
	
	wLib.switchNewBrowserTab(driver, "module=Accounts&action");
    driver.findElement(By.name("search_text")).sendKeys(eLib.getDataFromExcel("org", 1, 2));
	driver.findElement(By.name("search")).click();
    driver.findElement(By.xpath("//a[text()='"+eLib.getDataFromExcel("org", 1, 2)+nn+"']")).click();
	wLib.switchNewBrowserTab(driver, "module=Contacts&action");
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				   
	     String heading=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				 
				    if(heading.contains(eLib.getDataFromExcel("contact",1, 2)+nn))
				  {
			         System.out.println("contact is created");
				  }
					else
					{
					System.out.println("contact is not created");
					}
					String lastname=driver.findElement(By.id("dtlview_Last Name")).getText();
					if(lastname.equals(eLib.getDataFromExcel("contact", 1, 2)+nn))
					{
					System.out.println("last name is verified");
					}
					else
					{
					System.out.println("last name is not verified");
					}
					String orglink=driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText();
					if(orglink.equals(eLib.getDataFromExcel("org", 1, 2)+nn))
					{
						System.out.println("organization verified");
					}
					else
					{
						System.out.println("organization not verified");
					}
					driver.findElement(By.xpath("(//img)[4]")).click();
					driver.findElement(By.linkText("Sign Out")).click();
					wLib.postCondition(driver);
	}
}
	
