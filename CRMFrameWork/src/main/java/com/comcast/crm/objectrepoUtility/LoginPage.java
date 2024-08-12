package com.comcast.crm.objectrepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility                 //Rule 1:-Create a separate java class
{   
	WebDriver driver;   //globally decalred
	public LoginPage(WebDriver driver)
	{
		this.driver=driver; //create global var and at the time of initialization any how testScript writter passing the dr. object.......
		                    //initialize the object in global variable...in order to loacal var and global var same name to avoid confusion using
		                    //  "this" keyword inside the constructor
		PageFactory.initElements(driver ,this);
	}
	
	
    //Rule 2:-object Creation
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;          //Rule 3:-Object Initialization

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() { 
		
		return loginBtn;                 //Rule 4:- Object Encapsulationn
	}

//Rule 5 :- Object Utilization via getters or 
	
//busniess action/library this is called business utility ,this is for specific app you cannot use all application
	
	public void loginToApp(String username , String password)
	{
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
		
	}
	
	
	
	
	

}
