package com.comcast.crm.baseClassUtility;

import java.sql.SQLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generifilecutility.ExcelUtility;
import com.comcast.crm.generifilecutility.FileUtility;
import com.comcast.crm.objectrepoUtility.HomePage;
import com.comcast.crm.objectrepoUtility.LoginPage;
import com.comcast.crm.webdriverutility.JavaUtility;
import com.comcast.crm.webdriverutility.UtilityClassObject;
import com.comcast.crm.webdriverutility.WebDriverUtility;

public class BaseClass {

	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public DataBaseUtility dbLib = new DataBaseUtility();

	public WebDriver driver = null;
	public static WebDriver sDriver = null;

	@BeforeSuite(groups = { "smokeTest", "RegressionTest" })
	public void configBS() throws SQLException {
		System.out.println("=============connect to DB , Report Config========");
		dbLib.getDbConnection();

	}

//	@Parameters("BROWSER")
	@BeforeClass(groups = { "smokeTest", "RegressionTest" })

//    public void configBC(String browser) throws Throwable
	public void configBC() throws Throwable
	{
		System.out.println("=====launch browser");
	//	String BROWSER = browser;

		String BROWSER=fLib.getDataFromPropertiesFile("browser");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else {
			driver = new FirefoxDriver();
		}

		sDriver = driver;
		UtilityClassObject.setDriver(driver);

		String URL = fLib.getDataFromPropertiesFile("url");
		driver.get(URL);

	}

	@BeforeMethod(groups = { "smokeTest", "RegressionTest" })
	public void configBM() throws Throwable {
		System.out.println("=====login=====");

		//String BROWSER = fLib.getDataFromPropertiesFile("browser");
		//String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	}

	@AfterMethod(groups = { "smokeTest", "RegressionTest" })
	public void configAM() {
		System.out.println("======logout=====");
		HomePage hp = new HomePage(driver);
		hp.logOut();
	}

	@AfterClass(groups = { "smokeTest", "RegressionTest" })
	public void configAC() {
		System.out.println("=====Close the Browser=======");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "RegressionTest" })
	public void configAS() throws SQLException {
		System.out.println("=============close to DB , Report backup========");
		dbLib.closeDbConnection();
	
	}

}
