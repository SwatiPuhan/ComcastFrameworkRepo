package com.comcast.crm.orgtestWithPOM;
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

public class DeleteOrgAndHandlingDynamicEleTestPOM 

{
	public static void main(String[] args) throws Throwable {

		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		//common data from notepad
		String URL = fLib.getDataFromPropertiesFile("url");
		String BROWSER =fLib.getDataFromPropertiesFile("browser");
		String USERNAME =fLib.getDataFromPropertiesFile("username");
		String PASSWORD =fLib.getDataFromPropertiesFile("password");
		System.out.println(URL);

		//String orgName=eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();

		String orgName=eLib.getDataFromExcel("org", 10, 2)+jLib.getRandomNumber();


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

		//single element perform an action , initialization happens during run time
		/*  LoginPage lp = PageFactory.initElements(driver ,LoginPage.class);
			lp.getUsernameEdit().sendKeys("admin");
			lp.getPasswordEdit().sendKeys("admin");
			lp.getLoginBtn().click();   */


		//instead of this ,using business action 
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME,PASSWORD);

		//step2: Navigate to Organisation module 

		HomePage homePage=new  HomePage(driver);
		homePage.getOrganizationLink().click();


		//step3: click on Create Organisation button
		OrganizationPage createNewPage=new OrganizationPage(driver);
		createNewPage.getCreateNewOrgBtn().click();


		//step4: Enter the values to the Organisation name textfield
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName, orgName);

		//save it
		cnop.getSaveBtn();

		//verify  Headermsg ExpectedREsult
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName=oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName))
		{
			System.out.println(orgName + "name is verified");
		}
		else
		{
			System.out.println(orgName + "name is not verified");
		}


		//go back to organization  page 

		homePage.getOrganizationLink().click();
		

		//search for organization
		
		createNewPage.getSearchTxtFld().sendKeys(orgName);
		wLib.selectByVisibleText(createNewPage.getSearchDropDwon(), "Organization Name");
		createNewPage.getSearchNowBtn().click();
		
		
	    //dynamic xpath:-
	    //in dynamic web table select and delete org:-
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		wLib.switchToAlertAndAccept(driver);
        
		//step 5: logout operation
		homePage.logOut();
		wLib.postCondition(driver);        
	}
}





























