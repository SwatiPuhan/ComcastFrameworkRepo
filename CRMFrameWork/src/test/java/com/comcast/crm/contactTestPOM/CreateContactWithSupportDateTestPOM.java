package com.comcast.crm.contactTestPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.objectrepoUtility.ContactInfoPage;
import com.comcast.crm.objectrepoUtility.CreateNewContactPage;
import com.comcast.crm.objectrepoUtility.HomePage;
import com.comcast.crm.objectrepoUtility.LoginPage;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class CreateContactWithSupportDateTestPOM {
	public static void main(String[] args) throws Throwable {
		FileUtility Fu = new FileUtility();
		ExcelUtility Eu = new ExcelUtility();
		JavaUtility Ju = new JavaUtility();
		WebDriverUtility Wu = new WebDriverUtility();

		String URL = Fu.getDataFromPropertiesFile("url");
		String BROWSER = Fu.getDataFromPropertiesFile("browser");
		String USERNAME = Fu.getDataFromPropertiesFile("username");
		String PASSWORD = Fu.getDataFromPropertiesFile("password");

		// Read the test script data from excel file
		String lastName = Eu.getDataFromExcel("org", 4, 2) + Ju.getRandomNumber();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();

		}

		Wu.preCondition(driver);
		driver.get(URL);

		// step1:login to app
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		// Navigate to contactModule
		HomePage hp = new HomePage(driver);
		hp.navigateToContactModule();
		hp.getContactLink().click();

		// Navigate to contactcreate
		String Startdate = Ju.getSystemDateYYYYMMDD();
		String Enddate = Ju.getRequiredDateYYYYMMDD(30);

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameTxtFld().sendKeys(lastName);
		cncp.getSupportStartDate().clear();
		cncp.getSupportStartDate().sendKeys(Startdate);

		cncp.getSupportEndDate().clear();
		cncp.getSupportEndDate().sendKeys(Enddate);

		// step3: click on save button
		cncp.getSaveBtn();

		// Verify Start date and end date
		ContactInfoPage cip=new ContactInfoPage(driver);
		String ActlastName=cip.getVerifyLastNameTxtFld().getText();
		if (ActlastName.equals(lastName)) {
			System.out.println(lastName + "verfied=pass");
		} else {
			System.out.println(lastName + "verfied=Fail");
		}
		String ActStartDate = cip.getVerifyStartDate().getText();
		if (ActStartDate.equals(Startdate)) {
			System.out.println(ActStartDate + "verfied=pass");
		} else {
			System.out.println(ActStartDate + "verfied=Fail");
		}

		String ActEndDate = cip.getVerifyEndDate().getText();
		if (ActEndDate.equals(Enddate)) {
			System.out.println(ActEndDate + "verfied=pass");
		} else {
			System.out.println(ActEndDate + "verfied=Fail");
		}

		Wu.postCondition(driver);
	}
}
