package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class NEWCreateContactWithOrg1 
{

	static WebDriver driver;
	public static void main(String[] args) throws Throwable{

		        FileUtility Fu=new FileUtility();
				ExcelUtility Eu=new ExcelUtility();
				JavaUtility Ju=new JavaUtility();
				WebDriverUtility Wu=new WebDriverUtility();

				String URL = Fu.getDataFromPropertiesFile("url");
				String BROWSER = Fu.getDataFromPropertiesFile("browser");
				String USERNAME = Fu.getDataFromPropertiesFile("username");
				String PASSWORD = Fu.getDataFromPropertiesFile("password");

				//Read the test script data from excel file
				String LastName = Eu.getDataFromExcel("org", 7, 3)+Ju.getRandomNumber();
				String orgname = Eu.getDataFromExcel("org", 7, 2);

				WebDriver driver=null;

				if(BROWSER.equals("chrome")) 
				{
					driver=new ChromeDriver();
				}
				else if(BROWSER.equals("edge"))
				{
					driver=new EdgeDriver();

				}
				Wu.preCondition(driver);
				
                 //Create Organisation
                 //step1:login to app
			    driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).submit();


				//step2: Navigate to Organisation module 
				driver.findElement(By.linkText("Organizations")).click();

				//step3: click on Create Organisation button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

				//step3: Enter the values to the Organisation name textfield

				driver.findElement(By.name("accountname")).sendKeys(orgname);
				//step3: click on save button
				driver.findElement(By.name("button")).click();

				//verify  Headermsg ExpectedREsult

				String headerinfo =driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerinfo.contains(orgname)) {
					System.out.println(orgname+ "verfied=pass");
				}
				else {
					System.out.println(orgname+ "verfied=Fail");
				}
				//verify  Header orgName info ExpectedREsult
				String HeaderorgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
				if(HeaderorgName.equals(orgname)) {
					System.out.println(orgname+ "verfied=pass");
				}
				else {
					System.out.println(orgname+ "verfied=Fail");
				}


				//Navigate to contactModule
				driver.findElement(By.linkText("Contacts")).click();
				//Navigate to contactcreate
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				driver.findElement(By.name("lastname")).sendKeys(LastName);

				//Click on Organisation look up icon
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")).click();


				//Switch to child window
				Wu.switchNewBrowserTab(driver, "Accounts&action");
				driver.findElement(By.id("search_txt")).sendKeys(orgname);
				driver.findElement(By.name("search")).click();
				
				//dynamic Xpath
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();

                //Switch to parent window
				Wu.switchNewBrowserTab(driver, "Contacts&action");

				//step3: click on save button
				//Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				//verify lastname and contact info ExpectedREsult
				String Actlastname = driver.findElement(By.id("dtlview_Last Name")).getText().trim();
				System.out.println(Actlastname);
				System.out.println(LastName);
				if(Actlastname.equals(LastName))
				{
					System.out.println(Actlastname+ "verfied=pass");
				}
				else 
				{
					System.out.println(Actlastname+ "verfied=Fail");
				}

				//verify Org name info
				String ActOrgname = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText().trim();

				if(ActOrgname.equals(orgname)) {
					System.out.println(orgname+ "verfied=pass");
				}
				else {
					System.out.println(orgname+ "verfied=Fail");
				}


				Wu.postCondition(driver);


			}
		}






